package com.zh.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zh.entity.ComAuthConfig;

public interface CommonAuthTranDao extends BaseMapper<ComAuthConfig> {


    void saveComAuthConfig(ComAuthConfig config);
}
