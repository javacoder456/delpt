package com.zh.service.impl;

import com.zh.service.CommonAuthTranService;
import com.zh.dao.CommonAuthTranDao;
import com.zh.entity.ComAuthConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonAuthTranServiceImpl implements CommonAuthTranService {

    @Autowired
    private CommonAuthTranDao commonAuthTranDao;

    @Override
    public void save(ComAuthConfig config) {
        commonAuthTranDao.saveComAuthConfig(config);
    }
}
