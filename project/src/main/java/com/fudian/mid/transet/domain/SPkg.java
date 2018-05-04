package com.fudian.mid.transet.domain;

import javax.persistence.*;

@Table(name = "T_SYS_PKG")
public class SPkg {
    @Column(name = "CHNLNO")
    private String chnlno;

    @Column(name = "DATAPKGNO")
    private String datapkgno;

    @Column(name = "DATAPKGNAME")
    private String datapkgname;

    @Column(name = "COMMSTRUCTNO")
    private String commstructno;

//    @Column(name = "DATAPKGTYPE")
//    private String datapkgtype;

//    @Column(name = "PKGPROCCOMP")
//    private String pkgproccomp;

    @Column(name = "PREPROC")
    private String preproc;

    @Column(name = "ENDPROC")
    private String endproc;

    @Column(name = "MACMODE")
    private String macmode;

    @Column(name = "PINMODE")
    private String pinmode;

    @Column(name = "CREDTYPE")
    private String credtype;

    @Column(name = "ICREDFLAG")
    private String icredflag;

    @Column(name = "CNAME")
    private String cname;

    @Column(name = "CTIME")
    private String ctime;

    @Column(name = "MNAME")
    private String mname;

    @Column(name = "MTIME")
    private String mtime;

    public String getChnlno() {
        return chnlno;
    }

    public void setChnlno(String chnlno) {
        this.chnlno = chnlno;
    }

    /**
     * @return DATAPKGNO
     */
    public String getDatapkgno() {
        return datapkgno;
    }

    /**
     * @param datapkgno
     */
    public void setDatapkgno(String datapkgno) {
        this.datapkgno = datapkgno == null ? null : datapkgno.trim();
    }

    /**
     * @return DATAPKGNAME
     */
    public String getDatapkgname() {
        return datapkgname;
    }

    /**
     * @param datapkgname
     */
    public void setDatapkgname(String datapkgname) {
        this.datapkgname = datapkgname == null ? null : datapkgname.trim();
    }

    /**
     * @return COMMSTRUCTNO
     */
    public String getCommstructno() {
        return commstructno;
    }

    /**
     * @param commstructno
     */
    public void setCommstructno(String commstructno) {
        this.commstructno = commstructno == null ? null : commstructno.trim();
    }

//    /**
//     * @return DATAPKGTYPE
//     */
//    public String getDatapkgtype() {
//        return datapkgtype;
//    }
//
//    /**
//     * @param datapkgtype
//     */
//    public void setDatapkgtype(String datapkgtype) {
//        this.datapkgtype = datapkgtype == null ? null : datapkgtype.trim();
//    }

    /**
     * @return PREPROC
     */
    public String getPreproc() {
        return preproc;
    }

    /**
     * @param preproc
     */
    public void setPreproc(String preproc) {
        this.preproc = preproc == null ? null : preproc.trim();
    }

    /**
     * @return ENDPROC
     */
    public String getEndproc() {
        return endproc;
    }

    /**
     * @param endproc
     */
    public void setEndproc(String endproc) {
        this.endproc = endproc == null ? null : endproc.trim();
    }

    /**
     * @return MACMODE
     */
    public String getMacmode() {
        return macmode;
    }

    /**
     * @param macmode
     */
    public void setMacmode(String macmode) {
        this.macmode = macmode == null ? null : macmode.trim();
    }

    /**
     * @return PINMODE
     */
    public String getPinmode() {
        return pinmode;
    }

    /**
     * @param pinmode
     */
    public void setPinmode(String pinmode) {
        this.pinmode = pinmode == null ? null : pinmode.trim();
    }

    /**
     * @return CREDTYPE
     */
    public String getCredtype() {
        return credtype;
    }

    /**
     * @param credtype
     */
    public void setCredtype(String credtype) {
        this.credtype = credtype == null ? null : credtype.trim();
    }

    /**
     * @return ICREDFLAG
     */
    public String getIcredflag() {
        return icredflag;
    }

    /**
     * @param icredflag
     */
    public void setIcredflag(String icredflag) {
        this.icredflag = icredflag == null ? null : icredflag.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

//    public String getPkgproccomp() {
//        return pkgproccomp;
//    }
//
//    public void setPkgproccomp(String pkgproccomp) {
//        this.pkgproccomp = pkgproccomp;
//    }
}