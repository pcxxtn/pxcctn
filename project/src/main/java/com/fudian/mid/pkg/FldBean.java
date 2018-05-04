package com.fudian.mid.pkg;

public class FldBean {

    /*
     *报文字段域
     */
    private String commstructfldname;

    /*
     *字段长度
     */
    private String fldlen;

    /*
     *字段类型
     */
    private String fldtype;

    /*
     *处理函数
     */
    private String procfunc;

    /*
     *填充符
     */
    private String fillsmbl;

    /*
     *对齐方式
     */
    private String alignmode;

    /*
     *分隔符
     */
    private String breaksmbl;

    /*
     *结束符标志
     */
    private String endsmblflag;

    /*
     *通讯结构编码方式
     */
    private String  encoding;

    public String getCommstructfldname() {
        return commstructfldname;
    }

    public void setCommstructfldname(String commstructfldname) {
        this.commstructfldname = commstructfldname;
    }

    public String getFldlen() {
        return fldlen;
    }

    public void setFldlen(String fldlen) {
        this.fldlen = fldlen;
    }

    public String getFldtype() {
        return fldtype;
    }

    public void setFldtype(String fldtype) {
        this.fldtype = fldtype;
    }

    public String getProcfunc() {
        return procfunc;
    }

    public void setProcfunc(String procfunc) {
        this.procfunc = procfunc;
    }

    public String getFillsmbl() {
        return fillsmbl;
    }

    public void setFillsmbl(String fillsmbl) {
        this.fillsmbl = fillsmbl;
    }

    public String getAlignmode() {
        return alignmode;
    }

    public void setAlignmode(String alignmode) {
        this.alignmode = alignmode;
    }

    public String getBreaksmbl() {
        return breaksmbl;
    }

    public void setBreaksmbl(String breaksmbl) {
        this.breaksmbl = breaksmbl;
    }

    public String getEndsmblflag() {
        return endsmblflag;
    }

    public void setEndsmblflag(String endsmblflag) {
        this.endsmblflag = endsmblflag;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public String toString() {
        return "FldBean{" +
                "commstructfldname='" + commstructfldname + '\'' +
                ", fldlen='" + fldlen + '\'' +
                ", fldtype='" + fldtype + '\'' +
                ", procfunc='" + procfunc + '\'' +
                ", fillsmbl='" + fillsmbl + '\'' +
                ", alignmode='" + alignmode + '\'' +
                ", breaksmbl='" + breaksmbl + '\'' +
                ", endsmblflag='" + endsmblflag + '\'' +
                ", encoding='" + encoding + '\'' +
                '}';
    }

}
