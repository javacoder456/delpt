package com.zh.service;


import com.baomidou.mybatisplus.service.IService;
import com.zh.entity.SysAuthority;

import java.util.List;

public interface SysAuthorityService extends IService<SysAuthority> {

    SysAuthority getResourceById(String authorityid);

    List<SysAuthority> getRootResource();

    List<SysAuthority> getAllResource();

    List<SysAuthority> getResources(String roleid,String child);

    List<SysAuthority> getSubMenuById(String authorityid,String child);

    void editAuth(SysAuthority authorities);

    void addAuth(SysAuthority authorities);

    void deleteAuth(String authorityid);


}
