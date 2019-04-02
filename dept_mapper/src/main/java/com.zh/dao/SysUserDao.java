package com.zh.dao;

import com.zh.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hzhang
 * @since 2019-02-15
 */

public interface SysUserDao extends BaseMapper<SysUser> {

    void saveUser(SysUser user);

    void updateUser(SysUser user);

    void deleteUser(String userid);

}
