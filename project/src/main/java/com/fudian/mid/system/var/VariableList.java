package com.fudian.mid.system.var;

import java.util.HashMap;

public class VariableList {
    private HashMap<String, Variable> varMap;

    public VariableList() {
        this.varMap = new HashMap<String,Variable>();
    }

    public boolean varInList(String name){
        if( varMap != null && varMap.size()>0 ){
            if( varMap.get(name) != null )
                return true;
        }
        return false;
    }

    public void setVarValue(String name,String value){
        if( !varInList(name) ){
            Variable var = new Variable();
            varMap.put(name,var);
        }
        ((Variable)varMap.get(name)).setValue(value);
    }

    public boolean setVarValue(String name,String value,int caseNo){
        if(caseNo <0){
            return false;
        }
        if( !varInList(name) ){
            Variable var = new Variable();
            varMap.put(name,var);
        }
        ((Variable)varMap.get(name)).setValue(value,caseNo);
        return true;
    }

    public String getVarValue(String name){
        if( !varInList(name)){
            return null;
        }
        return(  ((Variable)varMap.get(name)).getValue());

    }

    public String getVarValue(String name,int caseNo){
        if( caseNo < 0 ){
            return null;
        }
        if( !varInList(name)){
            return null;
        }
        return( ((Variable)varMap.get(name)).getValueOf(caseNo));
    }

    public int getVarCaseNum(String name){
        if( !varInList(name)){
            return -1;
        }
        return( ((Variable)varMap.get(name)).getCaseNum());
    }

    public HashMap getVarMap(){
        return this.varMap;
    }
}
