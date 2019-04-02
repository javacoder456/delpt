package com.zh.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zh.entity.SysAuthority;

public interface SysAuthorityDao extends BaseMapper<SysAuthority>  {

	void editAuth(SysAuthority authorities);

	void addAuth(SysAuthority authorities);

	void deleteAuth(String authorityid);
}
