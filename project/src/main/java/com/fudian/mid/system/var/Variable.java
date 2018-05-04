package com.fudian.mid.system.var;

import java.util.Vector;

public class Variable {
    private String name;
    private Vector<String> datas = new Vector<String>();

    public Variable() {
        this.datas.add("");
    }

    public void setValue(String value){
        this.datas.clear();
        this.datas.add(value.trim());
    }

    public void setValue(String value,int caseNo){
        if (caseNo >= datas.size()) {
            datas.setSize(caseNo + 1);
        }
        datas.set(caseNo, value);
    }

    public String getValue() {
        if (datas.size() == 0)
            datas.add("");
        return datas.get(0);
    }

    public String getValueOf(int caseNo){
        if (caseNo < 0 || caseNo >= datas.size())
            return null;
        return datas.get(caseNo);
    }

    public int getCaseNum(){
        return(datas.size());
    }
}
