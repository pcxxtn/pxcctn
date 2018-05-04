package com.fudian.mid.system.var;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TransVariableSet {
    private VariableList localVariable;

    public TransVariableSet() {
        this.localVariable = new VariableList();
    }

    public void setValue(String name,String value){
        localVariable.setVarValue(name,value);
    }

    public boolean setValue(String name ,String value,int row){
        return localVariable.setVarValue(name,value,row);
    }

    public boolean setValue(String name,String value,String row){
        int caseNo = Integer.parseInt(row);
        return setValue(name,value,caseNo);
    }

    public void setValue(Map map){
        Set keys = map.keySet();
        String key = "";
        String value = null;
        Iterator it = keys.iterator();
        while (it.hasNext()){
            key = it.next().toString().trim();
            value = map.get(key).toString().trim();
            localVariable.setVarValue(key, value);
        }
    }

    public void setValue(Map map,int row){
        Set keys = map.keySet();
        String key = "";
        String value = null;
        Iterator it = keys.iterator();
        while (it.hasNext()){
            key = it.next().toString().trim();
            value = map.get(key).toString().trim();
            localVariable.setVarValue(key, value,row);
        }
    }

    public String getValue(String name){
        return this.localVariable.getVarValue(name);
    }

    public String getValue(String name,int row){
        return this.localVariable.getVarValue(name,row);
    }

    public String getValue(String name,String row){
        return this.localVariable.getVarValue(name,Integer.parseInt(row));
    }

    public Map getLocalVar(){
        return this.localVariable.getVarMap();
    }
    public int getValueNum(String name){
        return this.localVariable.getVarCaseNum(name);
    }
}
