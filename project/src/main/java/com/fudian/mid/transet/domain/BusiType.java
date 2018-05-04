package com.fudian.mid.transet.domain;

import javax.persistence.*;

@Table(name = "T_SYS_BUSI_TYPE")
public class BusiType {
    @Id
    @Column(name = "BUSITYPE")
    private String busitype;

    @Column(name = "BUSINAME")
    private String businame;

    @Column(name = "DETTBLNAME")
    private String dettblname;

    @Column(name = "DETSTRUCTNAME")
    private String detstructname;

    @Column(name = "BUSISTATUS")
    private String busistatus;

    @Column(name = "CURDATE")
    private String curdate;

    @Column(name = "LASTDATE")
    private String lastdate;

    @Column(name = "LASTBATCHDATE")
    private String lastbatchdate;

    @Column(name = "TRANBEGINTIME")
    private String tranbegintime;

    @Column(name = "TRANENDTIME")
    private String tranendtime;

    @Column(name = "OTHERNAME")
    private String othername;

    @Column(name = "OTHERADDR")
    private String otheraddr;

    @Column(name = "OTHERPHONE")
    private String otherphone;

    @Column(name = "CNAME")
    private String cname;

    @Column(name = "CTIME")
    private String ctime;

    @Column(name = "MNAME")
    private String mname;

    @Column(name = "MTIME")
    private String mtime;

    @Column(name = "NOTE")
    private String note;

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
     * @return BUSINAME
     */
    public String getBusiname() {
        return businame;
    }

    /**
     * @param businame
     */
    public void setBusiname(String businame) {
        this.businame = businame == null ? null : businame.trim();
    }

    /**
     * @return DETTBLNAME
     */
    public String getDettblname() {
        return dettblname;
    }

    /**
     * @param dettblname
     */
    public void setDettblname(String dettblname) {
        this.dettblname = dettblname == null ? null : dettblname.trim();
    }

    /**
     * @return DETSTRUCTNAME
     */
    public String getDetstructname() {
        return detstructname;
    }

    /**
     * @param detstructname
     */
    public void setDetstructname(String detstructname) {
        this.detstructname = detstructname == null ? null : detstructname.trim();
    }

    /**
     * @return BUSISTATUS
     */
    public String getBusistatus() {
        return busistatus;
    }

    /**
     * @param busistatus
     */
    public void setBusistatus(String busistatus) {
        this.busistatus = busistatus == null ? null : busistatus.trim();
    }

    /**
     * @return CURDATE
     */
    public String getCurdate() {
        return curdate;
    }

    /**
     * @param curdate
     */
    public void setCurdate(String curdate) {
        this.curdate = curdate == null ? null : curdate.trim();
    }

    /**
     * @return LASTDATE
     */
    public String getLastdate() {
        return lastdate;
    }

    /**
     * @param lastdate
     */
    public void setLastdate(String lastdate) {
        this.lastdate = lastdate == null ? null : lastdate.trim();
    }

    /**
     * @return LASTBATCHDATE
     */
    public String getLastbatchdate() {
        return lastbatchdate;
    }

    /**
     * @param lastbatchdate
     */
    public void setLastbatchdate(String lastbatchdate) {
        this.lastbatchdate = lastbatchdate == null ? null : lastbatchdate.trim();
    }

    /**
     * @return TRANBEGINTIME
     */
    public String getTranbegintime() {
        return tranbegintime;
    }

    /**
     * @param tranbegintime
     */
    public void setTranbegintime(String tranbegintime) {
        this.tranbegintime = tranbegintime == null ? null : tranbegintime.trim();
    }

    /**
     * @return TRANENDTIME
     */
    public String getTranendtime() {
        return tranendtime;
    }

    /**
     * @param tranendtime
     */
    public void setTranendtime(String tranendtime) {
        this.tranendtime = tranendtime == null ? null : tranendtime.trim();
    }

    /**
     * @return OTHERNAME
     */
    public String getOthername() {
        return othername;
    }

    /**
     * @param othername
     */
    public void setOthername(String othername) {
        this.othername = othername == null ? null : othername.trim();
    }

    /**
     * @return OTHERADDR
     */
    public String getOtheraddr() {
        return otheraddr;
    }

    /**
     * @param otheraddr
     */
    public void setOtheraddr(String otheraddr) {
        this.otheraddr = otheraddr == null ? null : otheraddr.trim();
    }

    /**
     * @return OTHERPHONE
     */
    public String getOtherphone() {
        return otherphone;
    }

    /**
     * @param otherphone
     */
    public void setOtherphone(String otherphone) {
        this.otherphone = otherphone == null ? null : otherphone.trim();
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

    /**
     * @return NOTE
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}