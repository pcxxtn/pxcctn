package com.fudian.mid.pkg;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {

    public static void main(String[] args){
        String data="1|289725|20180413|  3|289725|林庆盛   |20180101|             7.89|289725|林庆盛   |20180201|            60.79|289725|林庆盛   |20180301|            57.70|123#";
        data=data.replace("|","");
//        byte[] bytes=data.getBytes();
//        System.out.println(bytes.length);
        int[] lengthList = {1,6,8,3,6,9,8,17,6,9,8,17,6,9,8,17,4};
        int start = 0;
        int end = lengthList[0];
        byte[] valueByte = data.getBytes(Charset.forName("GBK"));

        String valueSub="";
//        for (int i = 0; i < lengthList.length; i++) {
//            //按字节截取
////            valueSub = "["+new String(bytes,start,end-start)+"]";
//
//            valueSub = "["+new String(valueByte, start, end-start,Charset.forName("GBK"))+"]";
//            //按字符串截取
//            //valueSub += "'"+value.substring(start, end).trim()+"',";
//            start = lengthList[i] + start;
//            if (lengthList.length-1 != i) {
//                end = start + lengthList[i+1];
//            }
//            System.out.println(valueSub);
//        }

        Map<String,List<Object>> map=new HashMap<>();
        List list=new ArrayList();
        list.add(1);
        map.put("abc",list);
        if(map.containsKey("abc")){
            map.get("abc").add(2);
            map.get("abc").add(3);
            map.get("abc").add(4);
        }else{
            map.put("abc",list);
        }
        System.out.println(map.get("abc"));
    }
}
