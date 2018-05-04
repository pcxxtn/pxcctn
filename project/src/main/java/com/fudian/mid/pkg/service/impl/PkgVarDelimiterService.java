package com.fudian.mid.pkg.service.impl;

import com.fudian.mid.pkg.FldBean;
import com.fudian.mid.pkg.ResovlePkgFld;
import com.fudian.mid.pkg.service.IPkg;
import com.fudian.mid.system.var.TransVariableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者 zhaobei
 * 功能注解： 变长报文打解包service
 * 日期 2018-04-23 17:04
 */
@Service("pkgVarDelimiterService")
public class PkgVarDelimiterService implements IPkg {
    private Logger log = LoggerFactory.getLogger(BreakPkgService.class);
    @Autowired
    ResovlePkgFld resovlePkgFld;

    private String data = null; //报文字符串


    /**
     * 功能描述：变长分隔符报文解包
     *
     * @param message:报文
     * @param VarSet:变量池
     */

    @Override
    public void unpack(String message, TransVariableSet VarSet) {
        try {
            String chnlno = VarSet.getValue("chnlno");
            String fTranCode = VarSet.getValue("trancode");
            //获取解包的报文域
            List<FldBean> list = this.resovlePkgFld.GetPkgFld(chnlno, fTranCode, "0");
            //报文示例
//            message = "011|洪树楷......................................................|048|1|950212482.....................|..............................|1|9410200000000000844......|0010000000|............................................................|....................|1|530123193609230015..|20180414|111111|0|200.00|222222|0|300.00|333333|0|300.00|444444|0|400.00|";
//            String data = message;
            if (list.size() > 0) {
                log.info("待解包的报文" + data);
                log.info("-------------------变长分隔符报文解包开始-----------------------");
                long startTime = System.currentTimeMillis();    //获取开始时间
                int row = 0;                                    //变量池深度从0开始取
                unPack(list, message, chnlno, row, VarSet);     //解包方法
                long endTime = System.currentTimeMillis();    //获取结束时间
                log.info("解包使用时间：" + (endTime - startTime) + "ms");
                log.info("--------------------变长分隔符报文解包结束-----------------------");
            } else {
                log.info("--------------------变长分隔符报文解包报文域为空-----------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    /**
     * 功能描述：变长分隔符报文打包
     *
     * @param VarSet:变量池
     */
    @Override
    public String pack(TransVariableSet VarSet) {
        try {
            String chnlno = VarSet.getValue("chnlno");
            String fTranCode = VarSet.getValue("trancode");
            List<FldBean> list = this.resovlePkgFld.GetPkgFld(chnlno, fTranCode, "1");
            String data = "";
            if (list.size() > 0) {
                log.info("-------------------变长分隔符报文打包开始-----------------------");
                long startTime = System.currentTimeMillis();    //获取开始时间
                int index = 0;
                data = packPackage(data, list, chnlno, index, VarSet);//开始打包
                long endTime = System.currentTimeMillis();    //获取结束时间
                log.info("打包的结果报文：" + data);
                log.info("打包使用时间：" + (endTime - startTime) + "ms");
                log.info("-------------------变长分隔符报文打包结束-----------------------");
            } else {
                log.info("-------------------变长长分隔符报文打包报文域为空-----------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return data;
    }


    /**
     * 功能描述： 变长分隔符报文解包方法
     *
     * @param list       :报文域字典list
     * @param dataTmp    :报文字符串
     * @param chnlno     ：渠道
     * @param row        ：变量池深度
     * @param varSet：变量池
     */
    private void unPack(List<FldBean> list, String dataTmp, String chnlno, int row, TransVariableSet varSet) {
        data = dataTmp;
        for (FldBean fldBean : list) {
            if (fldBean.getProcfunc().equals("000000|0")) {
                String split = fldBean.getBreaksmbl();  //取分隔符
                String value = data.substring(0, data.indexOf(split));   //截取报文第一个值为参数值
                log.info("截取报文字段:" + value + " 放入变量池: {key:" + fldBean.getCommstructfldname() + " value:" + value + " row:" + row + "}");
                varSet.setValue(fldBean.getCommstructfldname(), value, row);      //放变量池
                data = data.substring(value.length() + split.length(), data.length());//截取获得剩余的待解包报文
            } else {
                //设置了子报文
                String procfunc = fldBean.getProcfunc();  //处理参数
                String datapkgno = procfunc.split("\\|")[0]; //报文格式号
                int cyclesum = Integer.parseInt(procfunc.split("\\|")[1]); //循环次数
                List<FldBean> fldBeanChild = this.resovlePkgFld.GetPkgFld(chnlno, datapkgno, "2");
                if (cyclesum > 1) {
                    //子报文指定了循环次数
                    for (int i = 0; i < cyclesum; i++) {
                        log.info(">>>>>>>>>>>>循环域开始>>>>>>>>>>：" + i);
                        unPack(fldBeanChild, data, chnlno, i, varSet);
                    }
                } else if (cyclesum < 0) {
                    //子报文未指定循环次数,循环解包直至报文结束
                    while (0 < data.length()) {
                        log.info(">>>>>>>>>>>>循环域开始(无限循环)>>>>>>>>：");
                        unPack(fldBeanChild, data, chnlno, row, varSet);
                        row++;
                    }
                } else {
                    //循环次数为1，变量池深度从0开始放
                    unPack(fldBeanChild, data, chnlno, 0, varSet);
                }
            }
        }
    }


    /**
     * 功能描述： 变长分隔符打包方法
     *
     * @param data       :报文字符串
     * @param list       :报文域字典list
     * @param chnlno     ：渠道
     * @param index      ：报文域循环次数
     * @param varSet：变量池
     * @return data
     */
    private String packPackage(String data, List<FldBean> list, String chnlno, int index, TransVariableSet varSet) {
        for (FldBean fldBean : list) {
            if (fldBean.getProcfunc().equals("000000|0")) {
                data += fillBreak(fldBean, index, varSet);    //组包
                log.info("组包过程的报文：" + data);
            } else {
                String procfunc = fldBean.getProcfunc();  //处理参数
                String dataPkgno = procfunc.split("\\|")[0]; //取报文格式号
                int cyclesum = Integer.parseInt(procfunc.split("\\|")[1]); //取循环次数
                List<FldBean> fldBeanChild = this.resovlePkgFld.GetPkgFld(chnlno, dataPkgno, "2");
                if (cyclesum > 1) {
                    for (int i = 0; i < cyclesum; i++) {
                        log.info(">>>>>>>>>>>>>>>>>循环打包报文域>>>>>>>>>>：" + i);
                        data = packPackage(data, fldBeanChild, chnlno, i, varSet);
                    }
                } else {
                    data = packPackage(data, fldBeanChild, chnlno, 0, varSet);
                }
            }
        }
        return data;
    }

    /**
     * 功能描述： 字段后面添加分隔符
     *
     * @param fldBean    :报文字典bean
     * @param index      :报文域循环次数
     * @param varSet:变量池
     * @return value
     */
    public String fillBreak(FldBean fldBean, int index, TransVariableSet varSet) {
        Object obj = varSet.getValue(fldBean.getCommstructfldname(), index);//根据字典名和深度值获得变量值
        String value = obj.toString();
        value += fldBean.getBreaksmbl();//值+分隔符
        log.info("打包的字典名称：" + fldBean.getCommstructfldname() + "  变量池取变量： {key: " + fldBean.getCommstructfldname() + " value:" + obj.toString() + " row:" + index + "}");
        return value;
    }


}
