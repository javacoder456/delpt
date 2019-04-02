package com.zh.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zh.dao.SysAuthorityDao;
import com.zh.entity.SysAuthority;
import com.zh.service.SysAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysAuthorityServiceImpl extends ServiceImpl<SysAuthorityDao, SysAuthority>
        implements SysAuthorityService {

    @Autowired
    private SysAuthorityDao authoritiesDao;

    @Override
    public SysAuthority getResourceById(String authorityid) {
        return authoritiesDao.selectById(authorityid);
    }

    @Override
    public List<SysAuthority> getRootResource() {
        EntityWrapper<SysAuthority> wrapper = new EntityWrapper<>();
        wrapper.eq("children","0");
        return authoritiesDao.selectList(wrapper);
    }

    @Override
    public List<SysAuthority> getAllResource() {
        return authoritiesDao.selectList(new EntityWrapper<SysAuthority>());
    }

    @Override
    public List<SysAuthority> getResources(String roleid, String child) {
        EntityWrapper<SysAuthority> wrapper = new EntityWrapper<>();
        wrapper.eq("roleid",Integer.parseInt(roleid));
        wrapper.eq("children",child);
        return authoritiesDao.selectList(wrapper);
    }

    @Override
    public List<SysAuthority> getSubMenuById(String authorityid, String child) {
        EntityWrapper<SysAuthority> wrapper = new EntityWrapper<>();
        wrapper.eq("fatherid",Integer.parseInt(authorityid));
        wrapper.eq("children",child);
        return authoritiesDao.selectList(wrapper);
    }

    @Override
    public void editAuth(SysAuthority authorities) {
        authorities.setModiftytime(new Date());
        authoritiesDao.editAuth(authorities);
    }

    @Override
    public void addAuth(SysAuthority authorities) {
        authoritiesDao.addAuth(authorities);
    }

    @Override
    public void deleteAuth(String authorityid) {
        authoritiesDao.deleteAuth(authorityid);
    }

}
