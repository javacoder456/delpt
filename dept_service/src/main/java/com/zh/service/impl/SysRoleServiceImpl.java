package com.zh.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zh.entity.SysRole;
import com.zh.dao.SysRoleDao;
import com.zh.service.SysRoleService;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public List<SysRole> getAllRoles() {
        return sysRoleDao.selectList(new EntityWrapper<SysRole>());
    }

    @Override
    public SysRole getRoleById(String roleid) {
        return sysRoleDao.selectById(roleid);
    }

    @Override
    public void save(SysRole role) {
        sysRoleDao.saveRole(role);
    }

    @Override
    public void update(SysRole role) {
        sysRoleDao.updateRole(role);
    }

    @Override
    public void delete(String roleid) {
        sysRoleDao.deleteRole(roleid);
    }

}
