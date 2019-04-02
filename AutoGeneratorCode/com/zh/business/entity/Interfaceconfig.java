package com.zh.business.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hzhang
 * @since 2019-03-14
 */
public class Interfaceconfig extends Model<Interfaceconfig> {

    private static final long serialVersionUID = 1L;

    private String tradeserviceid;

    private String tradeservicetype;

    private String tradeservicename;

    private String trademethod;

    private String opertion;

    private String serviceconsumerid;

    private String serviceconsumername;

    private String datasourceid;

    private String sqltext;

    private String recode;

    private String redesc;

    private String callbackid;

    private String createid;

    private Date createtime;

    private Date modifytime;

    public String getTradeserviceid() {
        return tradeserviceid;
    }

    public void setTradeserviceid(String tradeserviceid) {
        this.tradeserviceid = tradeserviceid;
    }
    public String getTradeservicetype() {
        return tradeservicetype;
    }

    public void setTradeservicetype(String tradeservicetype) {
        this.tradeservicetype = tradeservicetype;
    }
    public String getTradeservicename() {
        return tradeservicename;
    }

    public void setTradeservicename(String tradeservicename) {
        this.tradeservicename = tradeservicename;
    }
    public String getTrademethod() {
        return trademethod;
    }

    public void setTrademethod(String trademethod) {
        this.trademethod = trademethod;
    }
    public String getOpertion() {
        return opertion;
    }

    public void setOpertion(String opertion) {
        this.opertion = opertion;
    }
    public String getServiceconsumerid() {
        return serviceconsumerid;
    }

    public void setServiceconsumerid(String serviceconsumerid) {
        this.serviceconsumerid = serviceconsumerid;
    }
    public String getServiceconsumername() {
        return serviceconsumername;
    }

    public void setServiceconsumername(String serviceconsumername) {
        this.serviceconsumername = serviceconsumername;
    }
    public String getDatasourceid() {
        return datasourceid;
    }

    public void setDatasourceid(String datasourceid) {
        this.datasourceid = datasourceid;
    }
    public String getSqltext() {
        return sqltext;
    }

    public void setSqltext(String sqltext) {
        this.sqltext = sqltext;
    }
    public String getRecode() {
        return recode;
    }

    public void setRecode(String recode) {
        this.recode = recode;
    }
    public String getRedesc() {
        return redesc;
    }

    public void setRedesc(String redesc) {
        this.redesc = redesc;
    }
    public String getCallbackid() {
        return callbackid;
    }

    public void setCallbackid(String callbackid) {
        this.callbackid = callbackid;
    }
    public String getCreateid() {
        return createid;
    }

    public void setCreateid(String createid) {
        this.createid = createid;
    }
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    @Override
    protected Serializable pkVal() {
        return this.tradeserviceid;
    }

    @Override
    public String toString() {
        return "Interfaceconfig{" +
        "tradeserviceid=" + tradeserviceid +
        ", tradeservicetype=" + tradeservicetype +
        ", tradeservicename=" + tradeservicename +
        ", trademethod=" + trademethod +
        ", opertion=" + opertion +
        ", serviceconsumerid=" + serviceconsumerid +
        ", serviceconsumername=" + serviceconsumername +
        ", datasourceid=" + datasourceid +
        ", sqltext=" + sqltext +
        ", recode=" + recode +
        ", redesc=" + redesc +
        ", callbackid=" + callbackid +
        ", createid=" + createid +
        ", createtime=" + createtime +
        ", modifytime=" + modifytime +
        "}";
    }
}
