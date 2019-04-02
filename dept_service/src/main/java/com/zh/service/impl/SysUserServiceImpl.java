package com.zh.business.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.dao.SysUserDao;
import com.zh.entity.SysUser;
import com.zh.service.SysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hzhang
 * @since 2019-02-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Autowired
    private SysUserDao usersDao;


    @Override
    public SysUser findUserByName(String name) {
        SysUser user = new SysUser();
        user.setName(name);
        return usersDao.selectOne(user);
    }

    @Override
    public SysUser findUserById(String userid) {
        SysUser user = new SysUser();
        user.setUserid(Integer.parseInt(userid));
        return usersDao.selectOne(user);
    }

    @Override
    public List<SysUser> getAllUsers() {
        return usersDao.selectList(new EntityWrapper());
    }

    @Override
    public void save(SysUser user) {
        usersDao.saveUser(user);
    }

    @Override
    public void update(SysUser user) {
        usersDao.updateUser(user);
    }

    @Override
    public void delete(String userid) {
        usersDao.deleteUser(userid);
    }

    @Override
    public PageInfo<SysUser> listForPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> result = getAllUsers();
        PageInfo page = new PageInfo(result);
        return page;
    }

}
