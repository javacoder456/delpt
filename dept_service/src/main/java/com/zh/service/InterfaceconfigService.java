package com.zh.service;

import com.baomidou.mybatisplus.service.IService;
import com.zh.entity.Interfaceconfig;



public interface InterfaceconfigService extends IService<Interfaceconfig> {

    Interfaceconfig getById(String tradeid);


    void save(Interfaceconfig config);

    void deleteById(String tradeserviceid);
}
