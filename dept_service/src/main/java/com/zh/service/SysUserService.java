package com.zh.service;

import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.service.IService;
import com.zh.entity.SysUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hzhang
 * @since 2019-02-15
 */
public interface SysUserService extends IService<SysUser> {
    SysUser findUserByName(String name);

    SysUser findUserById(String userid);

    List<SysUser> getAllUsers();

    void save(SysUser user);

    void update(SysUser user);

    void delete(String userid);

    PageInfo<SysUser> listForPage(int pageNum , int pageSize);

}
