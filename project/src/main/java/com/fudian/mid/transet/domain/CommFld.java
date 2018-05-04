package com.fudian.mid.transet.domain;

import javax.persistence.*;

@Table(name = "T_SYS_COMM_STRUCT_FLD")
public class CommFld
     //add xhx 实现数据字典排序
     implements Comparable<CommFld>{
    @Column(name = "CHNLNO")
    private String chnlno;

    @Column(name = "COMMSTRUCTNO")
    private String commstructno;

    @Column(name = "COMMSTRUCTFLDNO")
    private Short commstructfldno;

    @Column(name = "COMMSTRUCTFLDNAME")
    private String commstructfldname;

    @Column(name = "COMMSTRUCTFLDEXPR")
    private String commstructfldexpr;

    @Column(name = "FLDTYPE")
    private String fldtype;

    @Column(name = "STRUCTVARTYPE")
    private String structvartype;

    @Column(name = "MIXVARTYPE")
    private String mixvartype;

    @Column(name = "FLDLEN")
    private Short fldlen;

    @Column(name = "ALIGNMODE")
    private String alignmode;

    @Column(name = "FILLSMBL")
    private String fillsmbl;

    @Column(name = "ENDSMBLFLAG")
    private String endsmblflag;

    @Column(name = "BREAKSMBL")
    private String breaksmbl;

    @Column(name = "NLVARLEN")
    private Short nlvarlen;

    @Column(name = "NLVARFLAG")
    private String nlvarflag;

    @Column(name = "NLVARPOWER")
    private String nlvarpower;

    @Column(name = "VARNO")
    private Short varno;

    @Column(name = "PINFLAG")
    private String pinflag;

    @Column(name = "MACFLAG")
    private String macflag;

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
     * @return COMMSTRUCTFLDNAME
     */
    public String getCommstructfldname() {
        return commstructfldname;
    }

    /**
     * @param commstructfldname
     */
    public void setCommstructfldname(String commstructfldname) {
        this.commstructfldname = commstructfldname == null ? null : commstructfldname.trim();
    }

    /**
     * @return FLDTYPE
     */
    public String getFldtype() {
        return fldtype;
    }

    /**
     * @param fldtype
     */
    public void setFldtype(String fldtype) {
        this.fldtype = fldtype == null ? null : fldtype.trim();
    }

    /**
     * @return STRUCTVARTYPE
     */
    public String getStructvartype() {
        return structvartype;
    }

    /**
     * @param structvartype
     */
    public void setStructvartype(String structvartype) {
        this.structvartype = structvartype == null ? null : structvartype.trim();
    }

    /**
     * @return MIXVARTYPE
     */
    public String getMixvartype() {
        return mixvartype;
    }

    /**
     * @param mixvartype
     */
    public void setMixvartype(String mixvartype) {
        this.mixvartype = mixvartype == null ? null : mixvartype.trim();
    }

    /**
     * @return FLDLEN
     */
    public Short getFldlen() {
        return fldlen;
    }

    /**
     * @param fldlen
     */
    public void setFldlen(Short fldlen) {
        this.fldlen = fldlen;
    }

    /**
     * @return ALIGNMODE
     */
    public String getAlignmode() {
        return alignmode;
    }

    /**
     * @param alignmode
     */
    public void setAlignmode(String alignmode) {
        this.alignmode = alignmode == null ? null : alignmode.trim();
    }

    /**
     * @return FILLSMBL
     */
    public String getFillsmbl() {
        return fillsmbl;
    }

    /**
     * @param fillsmbl
     */
    public void setFillsmbl(String fillsmbl) {
        this.fillsmbl = fillsmbl;
    }

    /**
     * @return ENDSMBLFLAG
     */
    public String getEndsmblflag() {
        return endsmblflag;
    }

    /**
     * @param endsmblflag
     */
    public void setEndsmblflag(String endsmblflag) {
        this.endsmblflag = endsmblflag == null ? null : endsmblflag.trim();
    }

    /**
     * @return BREAKSMBL
     */
    public String getBreaksmbl() {
        return breaksmbl;
    }

    /**
     * @param breaksmbl
     */
    public void setBreaksmbl(String breaksmbl) {
        this.breaksmbl = breaksmbl;
    }

    /**
     * @return NLVARLEN
     */
    public Short getNlvarlen() {
        return nlvarlen;
    }

    /**
     * @param nlvarlen
     */
    public void setNlvarlen(Short nlvarlen) {
        this.nlvarlen = nlvarlen;
    }

    /**
     * @return NLVARFLAG
     */
    public String getNlvarflag() {
        return nlvarflag;
    }

    /**
     * @param nlvarflag
     */
    public void setNlvarflag(String nlvarflag) {
        this.nlvarflag = nlvarflag == null ? null : nlvarflag.trim();
    }

    /**
     * @return NLVARPOWER
     */
    public String getNlvarpower() {
        return nlvarpower;
    }

    /**
     * @param nlvarpower
     */
    public void setNlvarpower(String nlvarpower) {
        this.nlvarpower = nlvarpower == null ? null : nlvarpower.trim();
    }

    /**
     * @return VARNO
     */
    public Short getVarno() {
        return varno;
    }

    /**
     * @param varno
     */
    public void setVarno(Short varno) {
        this.varno = varno;
    }

    /**
     * @return PINFLAG
     */
    public String getPinflag() {
        return pinflag;
    }

    /**
     * @param pinflag
     */
    public void setPinflag(String pinflag) {
        this.pinflag = pinflag == null ? null : pinflag.trim();
    }

    /**
     * @return MACFLAG
     */
    public String getMacflag() {
        return macflag;
    }

    /**
     * @param macflag
     */
    public void setMacflag(String macflag) {
        this.macflag = macflag == null ? null : macflag.trim();
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
     * @return commstructfldexpr
     */
    public String getCommstructfldexpr() {
        return commstructfldexpr;
    }

    /**
     * @param commstructfldexpr
     */
    public void setCommstructfldexpr(String commstructfldexpr) {
        this.commstructfldexpr = commstructfldexpr;
    }


    // add  xhx
    // 实现commstructfldname  排序（升序）
    @Override
    public int compareTo(CommFld o) {
     return  this.commstructfldname.compareTo(o.commstructfldname);}


}
