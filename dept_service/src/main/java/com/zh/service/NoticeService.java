package com.zh.service;

import com.zh.entity.Notice;

import java.util.List;

public interface NoticeService {


    List<Notice> getNoticesById(String receiverid);

}
