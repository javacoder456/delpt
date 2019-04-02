package com.zh.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zh.service.InterfaceconfigService;
import com.zh.dao.InterfaceconfigDao;
import com.zh.entity.Interfaceconfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InterfaceconfigServiceImpl extends ServiceImpl<InterfaceconfigDao, Interfaceconfig> implements InterfaceconfigService {


    @Autowired
    private InterfaceconfigDao interfaceDao;


    @Override
    public Interfaceconfig getById(String tradeid) {
        return interfaceDao.selectById(tradeid);
    }

    @Override
    public void save(Interfaceconfig config) {
        interfaceDao.insert(config);
    }

    @Override
    public void deleteById(String tradeserviceid) {
        interfaceDao.deleteById(tradeserviceid);
    }
}
