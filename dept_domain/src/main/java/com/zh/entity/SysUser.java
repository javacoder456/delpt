package com.zh.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hzhang
 * @since 2019-02-15
 */
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    private Integer roleid;

    @TableId(value = "userid", type = IdType.AUTO)
    private Integer userid;

    private String name;

    private String password;

    private Integer phonenumber;

    private String email;

    private String address;

    private String sex;

    private Integer age;

    private Integer lockednum;

    private String unlocked;

    private String faultrole;

    private Date registertime;

    private Date lastmodifytime;

    private String enable;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Integer phonenumber) {
        this.phonenumber = phonenumber;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getLockednum() {
        return lockednum;
    }

    public void setLockednum(Integer lockednum) {
        this.lockednum = lockednum;
    }
    public String getUnlocked() {
        return unlocked;
    }

    public void setUnlocked(String unlocked) {
        this.unlocked = unlocked;
    }
    public String getFaultrole() {
        return faultrole;
    }

    public void setFaultrole(String faultrole) {
        this.faultrole = faultrole;
    }
    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }
    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }
    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    @Override
    protected Serializable pkVal() {
        return this.userid;
    }

    @Override
    public String toString() {
        return "SysUser{" +
        "roleid=" + roleid +
        ", userid=" + userid +
        ", name=" + name +
        ", password=" + password +
        ", phonenumber=" + phonenumber +
        ", email=" + email +
        ", address=" + address +
        ", sex=" + sex +
        ", age=" + age +
        ", lockednum=" + lockednum +
        ", unlocked=" + unlocked +
        ", faultrole=" + faultrole +
        ", registertime=" + registertime +
        ", lastmodifytime=" + lastmodifytime +
        ", enable=" + enable +
        "}";
    }
}
