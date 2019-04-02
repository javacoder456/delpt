package com.zh.service;

import com.baomidou.mybatisplus.service.IService;
import com.zh.entity.SysRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hzhang
 * @since 2019-02-15
 */
public interface SysRoleService extends IService<SysRole> {


    List<SysRole> getAllRoles();

    SysRole getRoleById(String roleid);

    void save(SysRole role);

    void update(SysRole role);

    void delete(String roleid);

}
