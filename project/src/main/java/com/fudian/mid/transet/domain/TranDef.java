package com.fudian.mid.transet.domain;

import javax.persistence.*;

@Table(name = "S_TRAN_DEF")
public class TranDef {
    @Id
    @Column(name = "TRAN_CODE")
    private String tranCode;

    @Column(name = "BUSINO")
    private String busino;

    @Column(name = "TRAN_CHN_NAME")
    private String tranChnName;

    @Column(name = "TRANCHAR")
    private String tranchar;

    @Column(name = "JOURFLAG")
    private String jourflag;

    @Column(name = "TRANLEVEL")
    private String tranlevel;

    @Column(name = "MODITIMES")
    private Short moditimes;

    @Column(name = "MODILEVEL")
    private String modilevel;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "CREATE_TIME")
    private String createTime;

    @Column(name = "MODIFY_TIME")
    private String modifyTime;

    /**
     * @return TRAN_CODE
     */
    public String getTranCode() {
        return tranCode;
    }

    /**
     * @param tranCode
     */
    public void setTranCode(String tranCode) {
        this.tranCode = tranCode == null ? null : tranCode.trim();
    }

    /**
     * @return BUSINO
     */
    public String getBusino() {
        return busino;
    }

    /**
     * @param busino
     */
    public void setBusino(String busino) {
        this.busino = busino == null ? null : busino.trim();
    }

    /**
     * @return TRAN_CHN_NAME
     */
    public String getTranChnName() {
        return tranChnName;
    }

    /**
     * @param tranChnName
     */
    public void setTranChnName(String tranChnName) {
        this.tranChnName = tranChnName == null ? null : tranChnName.trim();
    }

    /**
     * @return TRANCHAR
     */
    public String getTranchar() {
        return tranchar;
    }

    /**
     * @param tranchar
     */
    public void setTranchar(String tranchar) {
        this.tranchar = tranchar == null ? null : tranchar.trim();
    }

    /**
     * @return JOURFLAG
     */
    public String getJourflag() {
        return jourflag;
    }

    /**
     * @param jourflag
     */
    public void setJourflag(String jourflag) {
        this.jourflag = jourflag == null ? null : jourflag.trim();
    }

    /**
     * @return TRANLEVEL
     */
    public String getTranlevel() {
        return tranlevel;
    }

    /**
     * @param tranlevel
     */
    public void setTranlevel(String tranlevel) {
        this.tranlevel = tranlevel == null ? null : tranlevel.trim();
    }

    /**
     * @return MODITIMES
     */
    public Short getModitimes() {
        return moditimes;
    }

    /**
     * @param moditimes
     */
    public void setModitimes(Short moditimes) {
        this.moditimes = moditimes;
    }

    /**
     * @return MODILEVEL
     */
    public String getModilevel() {
        return modilevel;
    }

    /**
     * @param modilevel
     */
    public void setModilevel(String modilevel) {
        this.modilevel = modilevel == null ? null : modilevel.trim();
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

    /**
     * @return CREATE_TIME
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * @return MODIFY_TIME
     */
    public String getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
     */
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime == null ? null : modifyTime.trim();
    }
}