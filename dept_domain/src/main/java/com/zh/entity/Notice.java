package com.zh.entity;

import java.util.Date;

public class Notice {
    private String uid;
    // 发送者
    private String senderid;
    // 发送者名称
    private String sendername;
    // 接收者
    private String receiverid;
    // 消息类型
    private String type;
    // 标题
    private String titile;
    // 发送的文本
    private String content;
    // 发送日期
    private Date sendtime;
    // 发送的文本
    private String status;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public String getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", senderid='" + senderid + '\'' +
                ", sendername='" + sendername + '\'' +
                ", receiverid='" + receiverid + '\'' +
                ", type='" + type + '\'' +
                ", titile='" + titile + '\'' +
                ", content='" + content + '\'' +
                ", sendtime=" + sendtime +
                ", status='" + status + '\'' +
                '}';
    }
}
