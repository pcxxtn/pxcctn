package com.fudian.mid.transet.domain;

import javax.persistence.*;

@Table(name = "T_SYS_COMM_STRUCT")
public class CommStruct {
    @Column(name = "CHNLNO")
    private String chnlno;

    @Column(name = "COMMSTRUCTNO")
    private String commstructno;

    @Column(name = "COMMSTRUCTNAME")
    private String commstructname;

    @Column(name = "COMMSTRUCTTYPE")
    private String commstructtype;

    @Column(name = "VERSION")
    private String version;

    @Column(name = "ENCODING")
    private String encoding;

    @Column(name = "IFSPACE")
    private String ifspace;

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
     * @return COMMSTRUCTNAME
     */
    public String getCommstructname() {
        return commstructname;
    }

    /**
     * @param commstructname
     */
    public void setCommstructname(String commstructname) {
        this.commstructname = commstructname == null ? null : commstructname.trim();
    }

    /**
     * @return COMMSTRUCTTYPE
     */
    public String getCommstructtype() {
        return commstructtype;
    }

    /**
     * @param commstructtype
     */
    public void setCommstructtype(String commstructtype) {
        this.commstructtype = commstructtype == null ? null : commstructtype.trim();
    }

    /**
     * @return VERSION
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    /**
     * @return ENCODING
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * @param encoding
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding == null ? null : encoding.trim();
    }

    /**
     * @return IFSPACE
     */
    public String getIfspace() {
        return ifspace;
    }

    /**
     * @param ifspace
     */
    public void setIfspace(String ifspace) {
        this.ifspace = ifspace == null ? null : ifspace.trim();
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