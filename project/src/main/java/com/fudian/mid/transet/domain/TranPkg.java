package com.fudian.mid.transet.domain;

import javax.persistence.*;

@Table(name = "T_SYS_TRAN_PKG")
public class TranPkg {
    @Column(name = "CHNLNO")
    private String chnlno;

    @Column(name = "FTRANCODE")
    private String ftrancode;

    @Column(name = "FTRANNAME")
    private String ftranname;

    @Column(name="PKGTYPE")
    private String pkgtype;

    @Column(name="PKGPROCCOMP")
    private String pkgproccomp;

    @Column(name = "PACKPKGNO")
    private String packpkgno;

    @Column(name = "UNPACKPKGNO")
    private String unpackpkgno;

    @Column(name = "ERRPKGNO")
    private String errpkgno;

    @Column(name="BUSITYPE")
    private String busitype;

    @Column(name="TRANCODE")
    private String trancode;

    @Column(name="TTRANNAME")
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
     * @return FTRANNAME
     */
    public String getFtranname() {
        return ftranname;
    }

    /**
     * @param ftranname
     */
    public void setFtranname(String ftranname) {
        this.ftranname = ftranname == null ? null : ftranname.trim();
    }

    /**
     * @return PACKPKGNO
     */
    public String getPackpkgno() {
        return packpkgno;
    }

    /**
     * @param packpkgno
     */
    public void setPackpkgno(String packpkgno) {
        this.packpkgno = packpkgno == null ? null : packpkgno.trim();
    }

    /**
     * @return UNPACKPKGNO
     */
    public String getUnpackpkgno() {
        return unpackpkgno;
    }

    /**
     * @param unpackpkgno
     */
    public void setUnpackpkgno(String unpackpkgno) {
        this.unpackpkgno = unpackpkgno == null ? null : unpackpkgno.trim();
    }

    /**
     * @return ERRPKGNO
     */
    public String getErrpkgno() {
        return errpkgno;
    }

    /**
     * @param errpkgno
     */
    public void setErrpkgno(String errpkgno) {
        this.errpkgno = errpkgno == null ? null : errpkgno.trim();
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
     * @return BUSITYPE
     */
    public String getBusitype() {
        return busitype;
    }

    /**
     * @param busitype
     */
    public void setBusitype(String busitype) {
        this.busitype = busitype;
    }

    /**
     * @return TRANCODE
     */
    public String getTrancode() {
        return trancode;
    }

    /**
     * @param trancode
     */
    public void setTrancode(String trancode) {
        this.trancode = trancode;
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
        this.ttranname = ttranname;
    }

    /**
     *
     * @return PKGTYPE
     */
    public String getPkgtype() {
        return pkgtype;
    }

    /**
     *
     * @param pkgtype
     */
    public void setPkgtype(String pkgtype) {
        this.pkgtype = pkgtype;
    }

    /**
     *
     * @return PKGPROCCOMP
     */
    public String getPkgproccomp() {
        return pkgproccomp;
    }

    /**
     *
     * @param pkgproccomp
     */
    public void setPkgproccomp(String pkgproccomp) {
        this.pkgproccomp = pkgproccomp;
    }
}