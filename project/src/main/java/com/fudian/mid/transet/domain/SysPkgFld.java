package com.fudian.mid.transet.domain;

import javax.persistence.*;

@Table(name = "T_SYS_PKG_FLD")
public class SysPkgFld {
    @Id
    @Column(name = "CHNLNO")
    private String chnlno;

    @Id
    @Column(name = "DATAPKGNO")
    private String datapkgno;

    @Id
    @Column(name = "DATAPKGFLDNO")
    private Short datapkgfldno;

    @Column(name = "COMMSTRUCTNO")
    private String commstructno;

    @Column(name = "COMMSTRUCTFLDNO")
    private Short commstructfldno;

    @Column(name = "CNAME")
    private String cname;

    @Column(name = "CTIME")
    private String ctime;

    @Column(name = "MNAME")
    private String mname;

    @Column(name = "MTIME")
    private String mtime;

    @Column(name = "PROCFUNC")
    private String procfunc;


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
     * @return DATAPKGFLDNO
     */
    public Short getDatapkgfldno() {
        return datapkgfldno;
    }

    /**
     * @param datapkgfldno
     */
    public void setDatapkgfldno(Short datapkgfldno) {
        this.datapkgfldno = datapkgfldno;
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

    /**
     * @return COMMSTRUCTFLDNO
     */
    public Short getCommstructfldno() {
        return commstructfldno;
    }

    /**
     * @param commstructfldno
     */
    public void setCommstructfldno(Short commstructfldno) {
        this.commstructfldno = commstructfldno;
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

    public String getProcfunc() {
        return procfunc;
    }

    public void setProcfunc(String procfunc) {
        this.procfunc = procfunc;
    }
}