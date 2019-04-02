package com.zh.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zh.entity.SysRole;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hzhang
 * @since 2019-02-15
 */

public interface SysRoleDao extends BaseMapper<SysRole>{

    void saveRole(SysRole role);

    void updateRole(SysRole role);

    void deleteRole(String roleid);

}
