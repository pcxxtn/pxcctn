package com.fudian.mid.pkg;

import com.fudian.mid.system.var.TransVariableSet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author m
 * @description
 *@date 2018-04-24 10:13
 * 功能描述:用于循环取值时.记载取值次数,控制varset在循环报文打包时的准确取值
 */
public class ValueCounter {
    /**
     * 计数map：key-varset中的变量;value-取值次数
     */
    public Map<String,Integer> mapIndexRecord;

    public ValueCounter(){
        this.mapIndexRecord=new HashMap<>();
    }

    public void setVar(TransVariableSet varSet){
        for(Object str:varSet.getLocalVar().keySet()){
            this.mapIndexRecord.put(str.toString(),0);
        }
    }

    public int getFldIndex(String str) {
        return mapIndexRecord.get(str);
    }

    public void setFldIndex(String str) {
        if(mapIndexRecord.containsKey(str)){
            mapIndexRecord.put(str,mapIndexRecord.get(str)+1);
        }
    }
}
