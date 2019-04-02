package com.zh.entity;

import java.util.Date;

public class ComAuthConfig {
    private String authid;	//权限id
    private String userid;	//用户id
    private String username; //用户名称
    private String description; //描述
    private String configs; //资源集合
    private String createid; //创建时间
    private Date createtime; //创建时间
    private Date modiftytime; //修改时间
    private String enable; //是否可用

    public String getCreateid() {
        return createid;
    }

    public void setCreateid(String createid) {
        this.createid = createid;
    }

    public String getAuthid() {
        return authid;
    }

    public void setAuthid(String authid) {
        this.authid = authid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConfigs() {
        return configs;
    }

    public void setConfigs(String configs) {
        this.configs = configs;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModiftytime() {
        return modiftytime;
    }

    public void setModiftytime(Date modiftytime) {
        this.modiftytime = modiftytime;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
