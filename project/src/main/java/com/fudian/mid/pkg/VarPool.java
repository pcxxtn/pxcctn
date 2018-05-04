package com.fudian.mid.pkg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VarPool {

    public Map<String,List<Object>> map;
    public Map<String,Integer> mapIndexRecord;

    public VarPool(){
        map=new HashMap<>();
        mapIndexRecord=new HashMap<>();
    }

    public void setValue(String var,Object value){
        if(map.containsKey(var)) map.get(var).add(value);
        else{
            map.put(var,addValue(value));
            mapIndexRecord.put(var,0);
        }

    }

    private List<Object> addValue(Object value){
        List<Object> list=new ArrayList<>();
        list.add(value);
        return list;
    }

    public Object getValue(String var){
        if(map.containsKey(var)) return map.get(var).get(0);
        else return null;
    }

    public Object getValue(String var,int index){
        if(map.containsKey(var)){
            if(map.get(var).size()>index) return map.get(var).get(index);
            else return null;
        }else return null;
    }

    public int getValueNum(String var){
        if(map.containsKey(var))
            return map.get(var).size();
        else return 0;
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
