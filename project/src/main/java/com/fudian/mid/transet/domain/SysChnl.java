package com.fudian.mid.transet.domain;

import javax.persistence.*;

@Table(name = "T_SYS_CHNL")
public class SysChnl {
    @Id
    @Column(name = "CHNLNO")
    private String chnlno;

    @Column(name = "CHNLNAME")
    private String chnlname;

    @Column(name = "CHNLSTATUS")
    private String chnlstatus;

    @Column(name = "COMMPCOL")
    private String commpcol;

    @Column(name = "COMMMODE")
    private String commmode;

    @Column(name = "IPADDRESS")
    private String ipaddress;

    @Column(name = "RECVPORT")
    private String recvport;

    @Column(name = "SENDPORT")
    private String sendport;

    @Column(name = "TRACECODECLRFLAG")
    private String tracecodeclrflag;

    @Column(name = "GWPROCNO")
    private Short gwprocno;

    @Column(name = "RECVLEN")
    private Short recvlen;

    @Column(name = "TRACECODEOFFSET")
    private Short tracecodeoffset;

    @Column(name = "TRACECODELEN")
    private Short tracecodelen;

    @Column(name = "TRANCODEOFFSET")
    private Short trancodeoffset;

    @Column(name = "TRANCODELEN")
    private Short trancodelen;

    @Column(name = "RETCODEOFFSET")
    private Short retcodeoffset;

    @Column(name = "RETCODELEN")
    private Short retcodelen;

    @Column(name="GATEWAYPROC")
    private String gatewayproc;

    @Column(name = "INTERCEPT")
    private String intercept;

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
     * @return CHNLNAME
     */
    public String getChnlname() {
        return chnlname;
    }

    /**
     * @param chnlname
     */
    public void setChnlname(String chnlname) {
        this.chnlname = chnlname == null ? null : chnlname.trim();
    }

    /**
     * @return CHNLSTATUS
     */
    public String getChnlstatus() {
        return chnlstatus;
    }

    /**
     * @param chnlstatus
     */
    public void setChnlstatus(String chnlstatus) {
        this.chnlstatus = chnlstatus == null ? null : chnlstatus.trim();
    }

    /**
     * @return COMMPCOL
     */
    public String getCommpcol() {
        return commpcol;
    }

    /**
     * @param commpcol
     */
    public void setCommpcol(String commpcol) {
        this.commpcol = commpcol == null ? null : commpcol.trim();
    }

    /**
     * @return COMMMODE
     */
    public String getCommmode() {
        return commmode;
    }

    /**
     * @param commmode
     */
    public void setCommmode(String commmode) {
        this.commmode = commmode == null ? null : commmode.trim();
    }

    /**
     * @return IPADDRESS
     */
    public String getIpaddress() {
        return ipaddress;
    }

    /**
     * @param ipaddress
     */
    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress == null ? null : ipaddress.trim();
    }

    /**
     * @return RECVPORT
     */
    public String getRecvport() {
        return recvport;
    }

    /**
     * @param recvport
     */
    public void setRecvport(String recvport) {
        this.recvport = recvport == null ? null : recvport.trim();
    }

    /**
     * @return SENDPORT
     */
    public String getSendport() {
        return sendport;
    }

    /**
     * @param sendport
     */
    public void setSendport(String sendport) {
        this.sendport = sendport == null ? null : sendport.trim();
    }

    /**
     * @return TRACECODECLRFLAG
     */
    public String getTracecodeclrflag() {
        return tracecodeclrflag;
    }

    /**
     * @param tracecodeclrflag
     */
    public void setTracecodeclrflag(String tracecodeclrflag) {
        this.tracecodeclrflag = tracecodeclrflag == null ? null : tracecodeclrflag.trim();
    }

    /**
     * @return GWPROCNO
     */
    public Short getGwprocno() {
        return gwprocno;
    }

    /**
     * @param gwprocno
     */
    public void setGwprocno(Short gwprocno) {
        this.gwprocno = gwprocno;
    }

    /**
     * @return RECVLEN
     */
    public Short getRecvlen() {
        return recvlen;
    }

    /**
     * @param recvlen
     */
    public void setRecvlen(Short recvlen) {
        this.recvlen = recvlen;
    }

    /**
     * @return TRACECODEOFFSET
     */
    public Short getTracecodeoffset() {
        return tracecodeoffset;
    }

    /**
     * @param tracecodeoffset
     */
    public void setTracecodeoffset(Short tracecodeoffset) {
        this.tracecodeoffset = tracecodeoffset;
    }

    /**
     * @return TRACECODELEN
     */
    public Short getTracecodelen() {
        return tracecodelen;
    }

    /**
     * @param tracecodelen
     */
    public void setTracecodelen(Short tracecodelen) {
        this.tracecodelen = tracecodelen;
    }

    /**
     * @return TRANCODEOFFSET
     */
    public Short getTrancodeoffset() {
        return trancodeoffset;
    }

    /**
     * @param trancodeoffset
     */
    public void setTrancodeoffset(Short trancodeoffset) {
        this.trancodeoffset = trancodeoffset;
    }

    /**
     * @return TRANCODELEN
     */
    public Short getTrancodelen() {
        return trancodelen;
    }

    /**
     * @param trancodelen
     */
    public void setTrancodelen(Short trancodelen) {
        this.trancodelen = trancodelen;
    }

    /**
     * @return RETCODEOFFSET
     */
    public Short getRetcodeoffset() {
        return retcodeoffset;
    }

    /**
     * @param retcodeoffset
     */
    public void setRetcodeoffset(Short retcodeoffset) {
        this.retcodeoffset = retcodeoffset;
    }

    /**
     * @return RETCODELEN
     */
    public Short getRetcodelen() {
        return retcodelen;
    }

    /**
     * @param retcodelen
     */
    public void setRetcodelen(Short retcodelen) {
        this.retcodelen = retcodelen;
    }

    /**
     * @return INTERCEPT
     */
    public String getIntercept() {
        return intercept;
    }

    /**
     * @param intercept
     */
    public void setIntercept(String intercept) {
        this.intercept = intercept == null ? null : intercept.trim();
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
     * @return GATEWAYPROC
     */
    public String getGatewayproc() {
        return gatewayproc;
    }

    /**
     * @param gatewayproc
     */
    public void setGatewayproc(String gatewayproc) {
        this.gatewayproc = gatewayproc;
    }
}