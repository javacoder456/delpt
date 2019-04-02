package com.zh.service.impl;

import com.zh.service.NoticeService;
import com.zh.dao.NoticeDao;
import com.zh.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Override
    public List<Notice> getNoticesById(String receiverid) {
        return noticeDao.getNoticesById(receiverid);
    }
}
