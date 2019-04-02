package com.zh.service;

import com.zh.entity.SysRole;

import java.util.List;

public interface RoleService {


    List<SysRole> getAllRoles();

    SysRole getRoleById(String roleid);

    void save(SysRole role);

    void update(SysRole role);

    void delete(String roleid);
}
