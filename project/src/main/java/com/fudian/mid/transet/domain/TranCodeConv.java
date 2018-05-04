package com.fudian.mid.transet.domain;

import javax.persistence.*;

@Table(name = "T_SYS_TRAN_CODE_CONV")
public class TranCodeConv {
    @Column(name = "CHNLNO")
    private String chnlno;

    @Column(name = "FTRANCODE")
    private String ftrancode;

    @Column(name = "BUSITYPE")
    private String busitype;

    @Column(name = "TRANPATH")
    private String tranpath;

    @Column(name = "TTRANNAME")
    private String ttranname;

    @Column(name = "CNAME")
    private String cname;

    @Column(name = "CTIME")
    private String ctime;

    @Column(name = "MNAME")
    private String mname;

    @Column(name = "MTIME")
    private String mtime;

    /**
     * @return CHNLNO
     */
    public String getChnlno() {
        return chnlno;
    }

    /**
     * @param chnlno
     */
    public void setChnlno(String chnlno) {
        this.chnlno = chnlno == null ? null : chnlno.trim();
    }

    /**
     * @return FTRANCODE
     */
    public String getFtrancode() {
        return ftrancode;
    }

    /**
     * @param ftrancode
     */
    public void setFtrancode(String ftrancode) {
        this.ftrancode = ftrancode == null ? null : ftrancode.trim();
    }

    /**
     * @return BUSITYPE
     */
    public String getBusitype() {
        return busitype;
    }

    /**
     * @param busitype
     */
    public void setBusitype(String busitype) {
        this.busitype = busitype == null ? null : busitype.trim();
    }

    /**
     * @return TRANPATH
     */
    public String getTranpath() {
        return tranpath;
    }

    /**
     * @param tranpath
     */
    public void setTranpath(String tranpath) {
        this.tranpath = tranpath == null ? null : tranpath.trim();
    }

    /**
     * @return TTRANNAME
     */
    public String getTtranname() {
        return ttranname;
    }

    /**
     * @param ttranname
     */
    public void setTtranname(String ttranname) {
        this.ttranname = ttranname == null ? null : ttranname.trim();
    }

    /**
     * @return CNAME
     */
    public String getCname() {
        return cname;
    }

    /**
     * @param cname
     */
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    /**
     * @return CTIME
     */
    public String getCtime() {
        return ctime;
    }

    /**
     * @param ctime
     */
    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }

    /**
     * @return MNAME
     */
    public String getMname() {
        return mname;
    }

    /**
     * @param mname
     */
    public void setMname(String mname) {
        this.mname = mname == null ? null : mname.trim();
    }

    /**
     * @return MTIME
     */
    public String getMtime() {
        return mtime;
    }

    /**
     * @param mtime
     */
    public void setMtime(String mtime) {
        this.mtime = mtime == null ? null : mtime.trim();
    }
}