package com.fudian.mid.transet.domain;

import javax.persistence.*;

@Table(name = "S_DICT_FLD")
public class DictFld {
    public static final String SEQ = "seq_dictNo";

    @Id
    @Column(name = "DICT_NO")
    private Short dictNo;

    @Column(name = "DICT_CHN_NAME")
    private String dictChnName;

    @Column(name = "DICT_NAME")
    private String dictName;

    @Column(name = "DICT_CATE")
    private Short dictCate;

    @Column(name = "DICT_TYPE")
    private String dictType;

    @Column(name = "DICT_LEN")
    private Short dictLen;

    @Column(name = "DICT_ARRAY_NO")
    private Short dictArrayNo;

    @Column(name = "DICT_CREATE_TIME")
    private String dictCreateTime;

    @Column(name = "DICT_CHG_TIME")
    private String dictChgTime;

    @Column(name = "DICT_MAC_SIGN")
    private Short dictMacSign;

    @Column(name = "DICT_PIN_SIGN")
    private Short dictPinSign;

    @Column(name = "NOTE")
    private String note;

    /**
     * @return DICT_NO
     */
    public Short getDictNo() {
        return dictNo;
    }

    /**
     * @param dictNo
     */
    public void setDictNo(Short dictNo) {
        this.dictNo = dictNo;
    }

    /**
     * @return DICT_CHN_NAME
     */
    public String getDictChnName() {
        return dictChnName;
    }

    /**
     * @param dictChnName
     */
    public void setDictChnName(String dictChnName) {
        this.dictChnName = dictChnName == null ? null : dictChnName.trim();
    }

    /**
     * @return DICT_NAME
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * @param dictName
     */
    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    /**
     * @return DICT_CATE
     */
    public Short getDictCate() {
        return dictCate;
    }

    /**
     * @param dictCate
     */
    public void setDictCate(Short dictCate) {
        this.dictCate = dictCate;
    }

    /**
     * @return DICT_TYPE
     */
    public String getDictType() {
        return dictType;
    }

    /**
     * @param dictType
     */
    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
    }

    /**
     * @return DICT_LEN
     */
    public Short getDictLen() {
        return dictLen;
    }

    /**
     * @param dictLen
     */
    public void setDictLen(Short dictLen) {
        this.dictLen = dictLen;
    }

    /**
     * @return DICT_ARRAY_NO
     */
    public Short getDictArrayNo() {
        return dictArrayNo;
    }

    /**
     * @param dictArrayNo
     */
    public void setDictArrayNo(Short dictArrayNo) {
        this.dictArrayNo = dictArrayNo;
    }

    /**
     * @return DICT_CREATE_TIME
     */
    public String getDictCreateTime() {
        return dictCreateTime;
    }

    /**
     * @param dictCreateTime
     */
    public void setDictCreateTime(String dictCreateTime) {
        this.dictCreateTime = dictCreateTime == null ? null : dictCreateTime.trim();
    }

    /**
     * @return DICT_CHG_TIME
     */
    public String getDictChgTime() {
        return dictChgTime;
    }

    /**
     * @param dictChgTime
     */
    public void setDictChgTime(String dictChgTime) {
        this.dictChgTime = dictChgTime == null ? null : dictChgTime.trim();
    }

    /**
     * @return DICT_MAC_SIGN
     */
    public Short getDictMacSign() {
        return dictMacSign;
    }

    /**
     * @param dictMacSign
     */
    public void setDictMacSign(Short dictMacSign) {
        this.dictMacSign = dictMacSign;
    }

    /**
     * @return DICT_PIN_SIGN
     */
    public Short getDictPinSign() {
        return dictPinSign;
    }

    /**
     * @param dictPinSign
     */
    public void setDictPinSign(Short dictPinSign) {
        this.dictPinSign = dictPinSign;
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