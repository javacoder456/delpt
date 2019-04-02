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
@TableName("sys_authority")
public class SysAuthority extends Model<SysAuthority> {

    private static final long serialVersionUID = 1L;

    private Integer roleid;

    @TableId(value = "authorityid", type = IdType.AUTO)
    private Integer authorityid;

    private String authorityname;

    private Integer fatherid;

    private String url;

    private String icon;

    private String children;

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
    public Integer getAuthorityid() {
        return authorityid;
    }

    public void setAuthorityid(Integer authorityid) {
        this.authorityid = authorityid;
    }
    public String getAuthorityname() {
        return authorityname;
    }

    public void setAuthorityname(String authorityname) {
        this.authorityname = authorityname;
    }
    public Integer getFatherid() {
        return fatherid;
    }

    public void setFatherid(Integer fatherid) {
        this.fatherid = fatherid;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
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
        return this.authorityid;
    }

    @Override
    public String toString() {
        return "SysAuthority{" +
                "roleid=" + roleid +
                ", authorityid=" + authorityid +
                ", authorityname=" + authorityname +
                ", fatherid=" + fatherid +
                ", url=" + url +
                ", icon=" + icon +
                ", children=" + children +
                ", createid=" + createid +
                ", createname=" + createname +
                ", createtime=" + createtime +
                ", modiftytime=" + modiftytime +
                ", enable=" + enable +
                "}";
    }
}