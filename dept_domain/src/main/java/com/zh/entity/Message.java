package com.zh.entity;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    // 发送者
    private String senderid;
    // 发送者名称
    private String sendername;
    // 接收者
    private String receiverid;
    // 发送的文本
    private String text;
    // 消息类型
    private String type;
    // 发送日期
    private Date date;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
