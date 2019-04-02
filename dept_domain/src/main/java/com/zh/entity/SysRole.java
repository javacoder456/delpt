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
@TableName("sys_role")
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "roleid", type = IdType.AUTO)
    private Integer roleid;

    private String rolename;

    private String description;

    private String auths;

    private String isadmin;

    private Integer createid;

    private String createname;

    private Date createtime;

    private Date modiftytime;

    private String enable;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getAuths() {
        return auths;
    }

    public void setAuths(String auths) {
        this.auths = auths;
    }
    public String getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(String isadmin) {
        this.isadmin = isadmin;
    }
    public Integer getCreateid() {
        return createid;
    }

    public void setCreateid(Integer createid) {
        this.createid = createid;
    }
    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname;
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

    @Override
    protected Serializable pkVal() {
        return this.roleid;
    }

    @Override
    public String toString() {
        return "SysRole{" +
        "roleid=" + roleid +
        ", rolename=" + rolename +
        ", description=" + description +
        ", auths=" + auths +
        ", isadmin=" + isadmin +
        ", createid=" + createid +
        ", createname=" + createname +
        ", createtime=" + createtime +
        ", modiftytime=" + modiftytime +
        ", enable=" + enable +
        "}";
    }
}
