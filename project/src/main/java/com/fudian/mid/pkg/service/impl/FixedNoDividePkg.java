package com.fudian.mid.pkg.service.impl;

import com.fudian.mid.common.util.StringUtils;
import com.fudian.mid.pkg.FldBean;
import com.fudian.mid.pkg.ResovlePkgFld;
import com.fudian.mid.pkg.ValueCounter;
import com.fudian.mid.pkg.service.IPkg;
import com.fudian.mid.system.var.TransVariableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author m
 * @description
 * @date 2018-04-23 21:09
 */
@Service("FixedNoDividePkg")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true,rollbackFor = Exception.class)
public class FixedNoDividePkg implements IPkg {

    @Autowired ResovlePkgFld resovlePkgFld;

    private static Logger logger= LoggerFactory.getLogger(FixedNoDividePkg.class);

    /**
     * 功能描述：定长报文解包
     * @param message:报文
     * @param VarSet:变量池
     */
    @Override
    public void unpack(String message, TransVariableSet VarSet) {
        Long startTime=System.currentTimeMillis();
        logger.info(VarSet.getValue("chnlno"));
        logger.info(VarSet.getValue("ftrancode"));
        List<FldBean> list=this.resovlePkgFld.GetPkgFld(VarSet.getValue("chnlno"),VarSet.getValue("ftrancode"),"0");

        byte[] bytes=message.getBytes(Charset.forName("GBK"));
        int index=0;
        ParseDataPkg(index,list,bytes, VarSet);
        long endTime=System.currentTimeMillis();
        logger.info("原来时这么长时间啊----"+(endTime-startTime));
        logger.info(VarSet.getLocalVar().size()+"");
    }



    private int ParseDataPkg(int index,List<FldBean> list,byte[] bytes,TransVariableSet varSet){
        for(FldBean fldBean:list){
            if(fldBean.getProcfunc().equals("000000|0")){
                logger.info(fldBean.getCommstructfldname()+"=["+filterFillSyml(fldBean,new String(bytes, index, Integer.parseInt(fldBean.getFldlen()),Charset.forName("GBK")))+"]");
                String value=filterFillSyml(fldBean,new String(bytes, index, Integer.parseInt(fldBean.getFldlen()),Charset.forName("GBK")));
                int rowNum=varSet.getValueNum(fldBean.getCommstructfldname());
                varSet.setValue(fldBean.getCommstructfldname(),value,rowNum==-1?0:rowNum);
                index+=Integer.parseInt(fldBean.getFldlen());
            }else{
                //设置了子报文
                String datapkgno="";
                String procfunc=fldBean.getProcfunc();
                if(procfunc.contains("|")) {
                    datapkgno = procfunc.split("\\|")[0];
                }
                List<FldBean> fldBeanChild=this.resovlePkgFld.GetPkgFld(varSet.getValue("chnlno"),datapkgno,"2");
                if(procfunc.split("\\|").length>1){
                    //子报文指定了循环次数
                    String str=procfunc.split("\\|")[1];
                    int cycle=0;
                    if(StringUtils.hasValue(str)){
                        cycle=Integer.parseInt((varSet.getValue(procfunc.split("\\|")[1],varSet.getValueNum(procfunc.split("\\|")[1])-1)));
                    }
                    for(int i=0;i<cycle;i++){
                        index=ParseDataPkg(index,fldBeanChild,bytes,varSet);
                    }
                }else if(procfunc.split("\\|").length==1){
                    //子报文未指定循环次数,循环解包直至报文结束
                    while(index<bytes.length){
                        index=ParseDataPkg(index,fldBeanChild,bytes,varSet);
                    }
                }
            }
        }
        return index;
    }


    /**
     * 功能描述：定长报文打包
     * @param VarSet:变量池
     * @return String:报文
     */
    @Override
    public String pack(TransVariableSet VarSet) {
        List<FldBean> list=this.resovlePkgFld.GetPkgFld(VarSet.getValue("chnlno"),VarSet.getValue("ftrancode"),"1");
        ValueCounter valueCounter=new ValueCounter();
        valueCounter.setVar(VarSet);
        String data="";
        data=packPackage(data,list,VarSet,valueCounter);
        return data;
    }


    private String  packPackage(String data,List<FldBean> list,TransVariableSet varSet,ValueCounter valueCounter){
        for(FldBean fldBean:list){
            if(fldBean.getProcfunc().equals("000000|0"))
                data+=fillValue(fldBean,varSet,valueCounter)+"|";
            else{
                String func=fldBean.getProcfunc();
                String dataPkgno="";
                if(func.contains("|")){
                    dataPkgno=func.split("\\|")[0];
                }
                List<FldBean> fldBeanChild=this.resovlePkgFld.GetPkgFld(varSet.getValue("chnlno"),dataPkgno,"2");
                int cycle=0;

                if(func.split("\\|").length>1){
                    //按照循环次数打包
                    String cycleSyml=fldBean.getProcfunc().split("\\|")[1];
                    cycle=Integer.parseInt(varSet.getValue(cycleSyml,valueCounter.getFldIndex(cycleSyml)-1)+"");

                    for(int i=0;i<cycle;i++){
                        data=packPackage(data,fldBeanChild,varSet,valueCounter);
                    }
                } else{
                    //循环打包至变量池的值取完
                    cycle=varSet.getValueNum(fldBeanChild.get(0).getCommstructfldname());
                    for(int i=0;i<cycle;i++){
                        logger.info("循环打包至结束开始了--------------------");
                        data=packPackage(data,fldBeanChild,varSet,valueCounter);
                    }
                }
            }
        }
        return data;
    }

    /*
     *打包pack
     *字段长度不足既定位数,根据字段填充符和对齐方式补足位数
     */
    public String fillValue(FldBean fldBean,TransVariableSet varSet,ValueCounter valueCounter){
        logger.info(fldBean.getCommstructfldname()+"-->length======="+valueCounter.getFldIndex(fldBean.getCommstructfldname()));
        Object obj=varSet.getValue(fldBean.getCommstructfldname(),valueCounter.getFldIndex(fldBean.getCommstructfldname()));
        valueCounter.setFldIndex(fldBean.getCommstructfldname());
        String str=obj.toString();
        int strLen=str.getBytes(Charset.forName("GBK")).length;
        if(fldBean.getAlignmode().equals("0")){
            //左对齐
            for(int i=0;i<(Integer.parseInt(fldBean.getFldlen())-strLen);i++){
                str+=fldBean.getFillsmbl();
            }
        }else if(fldBean.getAlignmode().equals("1")){
            //右对齐
            for(int i=0;i<(Integer.parseInt(fldBean.getFldlen())-strLen);i++){
                str=fldBean.getFillsmbl()+str;
            }
        }
        return str;
    }


    /*
     *unpack
     *解包文时根据字段长度和对齐方式去除填充符
     */

    private String filterFillSyml(FldBean fldBean,String value){
        if(Integer.parseInt(fldBean.getFldlen())==0) return "";
        int symlLen=fldBean.getFillsmbl().length();
        int fldLen=value.length();
        if(fldBean.getAlignmode().equals("0")){
            //左对齐
            while(value.substring(fldLen-symlLen).equals(fldBean.getFillsmbl())){
                value=value.substring(0,fldLen-symlLen);
                fldLen-=symlLen;
            }
        }else if(fldBean.getAlignmode().equals("1")){
            //右对齐
            while(value.substring(0,symlLen).equals(fldBean.getFillsmbl())){
                value=value.substring(symlLen);
            }
        }
        return value;
    }
}
