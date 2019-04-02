package com.zh.entity;


import java.util.Date;

/**
 * 路径资源
 * @author hasee
 *
 */
public class Authorities {

	private String roleid; //角色id
	private String authorityid;//id 
	private String authorityname;//名称
	private String fatherid;//父节点id
	private String url;	//菜单路径
	private String createid;//创建id
	private String createname;//创建名称
	private Date createtime;//创建时间
	private Date modiftytime;//修改时间
	private String icon	;//图片名称
	private String children;//下级菜单
	private String enable;//是否可用

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getAuthorityid() {
		return authorityid;
	}
	public void setAuthorityid(String authorityid) {
		this.authorityid = authorityid;
	}
	public String getAuthorityname() {
		return authorityname;
	}
	public void setAuthorityname(String authorityname) {
		this.authorityname = authorityname;
	}
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}
	public String getCreateid() {
		return createid;
	}
	public void setCreateid(String createid) {
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
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}

	@Override
	public String toString() {
		return "Authorities{" +
				"roleid='" + roleid + '\'' +
				", authorityid='" + authorityid + '\'' +
				", authorityname='" + authorityname + '\'' +
				", fatherid='" + fatherid + '\'' +
				", url='" + url + '\'' +
				", createid='" + createid + '\'' +
				", createname='" + createname + '\'' +
				", createtime=" + createtime +
				", modiftytime=" + modiftytime +
				", icon='" + icon + '\'' +
				", children='" + children + '\'' +
				", enable='" + enable + '\'' +
				'}';
	}
}
