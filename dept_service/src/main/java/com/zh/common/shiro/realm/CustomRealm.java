package com.zh.common.shiro.realm;

import com.alibaba.fastjson.JSONObject;
import com.zh.dao.*;
import com.zh.common.CommonConstant;
import com.zh.common.socket.WebSocketUtils;
import com.zh.entity.*;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {


    private static final String lockNum = "3";

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysAuthorityDao sysAuthorityDao;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SessionDAO sessionDAO;

    /**
     * 对登陆账号进行角色资源授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        Set<String> roles = new LinkedHashSet<>();
        Set<String> permissions = new LinkedHashSet<>();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysRole role = sysRoleDao.selectById(user.getRoleid()); //获取角色
        roles.add(role.getRolename());
        List ids = JSONObject.parseArray(role.getAuths());//获取资源id集合
        if(ids != null){
            for(Object obj : ids){
                String id = (String)obj;
                SysAuthority authority =  sysAuthorityDao.selectById(id);
                permissions.add(authority.getUrl());
            }
        }
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    /*
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        String username = (String) authcToken.getPrincipal();
        SysUser sysUser = new SysUser();
        sysUser.setName(username);
        SysUser user = sysUserDao.selectOne(sysUser);
        if (user == null) {
            throw new UnknownAccountException();// 没找到帐号
        }
        if (lockNum.equals(user.getLockednum())) {
            throw new LockedAccountException(); // 帐号锁定
        }
        // 踢出已登录的用户
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            if (username.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))) {
                session.setTimeout(0);// 设置session立即失效，即将其踢出系统
                Message message = new Message();
                message.setType(CommonConstant.MSG_SYS);
                message.setReceiverid(user.getUserid().toString());
                message.setText("该用户已在其他地方登陆，如非本人请尽快修改密码！");
                WebSocketUtils.send(message);
                break;
            }
        }
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getName()); //盐值
        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo info =
                new SimpleAuthenticationInfo(user.getName(),user.getPassword(),credentialsSalt,getName());
        return info;
    }

}