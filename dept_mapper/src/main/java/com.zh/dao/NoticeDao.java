package com.zh.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zh.entity.Notice;

import java.util.List;

public interface NoticeDao extends BaseMapper<Notice> {

	List<Notice> getNoticesById(String userid);


}
