package com.fudian.mid.pkg.service.impl;

import com.fudian.mid.pkg.FldBean;
import com.fudian.mid.pkg.ResovlePkgFld;
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
 * 作者 zhaobei
 * 功能描述 定长分隔符打解包
 * 日期 2018-04-23 17:05
 */

@Service("fixedBreakPkgService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class FixedBreakPkgService implements IPkg {
    private Logger log = LoggerFactory.getLogger(FixedBreakPkgService.class);
    @Autowired
    ResovlePkgFld resovlePkgFld;


    /**
     * 功能描述：定长分隔符报文解包
     *
     * @param message:报文
     * @param VarSet:变量池
     */

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void unpack(String message, TransVariableSet VarSet) {
        try {
            String chnlno = VarSet.getValue("chnlno");
            String fTranCode = VarSet.getValue("trancode");
            List<FldBean> list = this.resovlePkgFld.GetPkgFld(chnlno, fTranCode,"0");
            String data = message;
//            示例解包报文
//            data = "011|洪树楷......................................................|048|1|950212482.....................|..............................|1|9410200000000000844......|0010000000|............................................................|....................|1|530123193609230015..|20180414|111111|0|200.00|222222|0|300.00|333333|0|300.00|444444|0|400.00|";
            byte[] bytes = data.getBytes(Charset.forName("GBK"));
            if (list.size() > 0) {
                log.info("待解包的报文" + data);
                log.info("-------------------定长分隔符报文解包开始-----------------------");
                long startTime = System.currentTimeMillis();    //获取开始时间
                int index = 0;  //从报文0下标位置开始截取
                int row = 0;  //从第0层开始放变量池
                int result = unPack(row, index, list, bytes, chnlno, VarSet);//解包方法
                long endTime = System.currentTimeMillis();    //获取结束时间
                log.info("解包使用时间：" + (endTime - startTime) + "ms");
                log.info("-------------------定长分隔符报文解包结束-----------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能描述：定长分隔符报文打包
     *
     * @param VarSet:变量池
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public String pack(TransVariableSet VarSet) {
        String data = "";
        try {
            String chnlno = VarSet.getValue("chnlno");
            String fTranCode = VarSet.getValue("trancode");
            //根据渠道号ChnlNo和外部交易码fTranCode获取打包报文格式字段,flag:1
            List<FldBean> list = this.resovlePkgFld.GetPkgFld(chnlno, fTranCode,"1");
            if (list.size() > 0) {
                log.info("-------------------定长分隔符报文打包开始-----------------------");
                long startTime = System.currentTimeMillis();    //获取开始时间
                int index = 0;
                data = packPackage(data, list, chnlno, index, VarSet);//打包
                long endTime = System.currentTimeMillis();    //获取结束时间
                log.info("打包结果报文：" + data);
                log.info("打包使用时间：" + (endTime - startTime) + "ms");
                log.info("-------------------定长分隔符报文打包结束-----------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 功能描述： 定长分隔符报文解包方法
     *
     * @param row      ：变量池深度
     * @param index    ：报文字段下标
     * @param list     :报文域字典list
     * @param dataByte :报文二进制串
     * @param chnlno   ：渠道
     * @param varSet   ：变量池
     */
    private int unPack(int row, int index, List<FldBean> list, byte[] dataByte, String chnlno, TransVariableSet varSet) {
        for (FldBean fldBean : list) {
            if (fldBean.getProcfunc().equals("000000|0")) {
                String split = fldBean.getBreaksmbl();  //取分隔符
                //截取报文第一个分隔符前的字段
                String firstVal = new String(dataByte, index, Integer.parseInt(fldBean.getFldlen()), Charset.forName("GBK"));
                String value = filterFillSyml(fldBean, firstVal);//去填充符
                log.info("要去填充符的报文字段:" + firstVal + " 放入变量池: {key:" + fldBean.getCommstructfldname() + " value:" + value + " row:" + row + "}");
                varSet.setValue(fldBean.getCommstructfldname(), value, row);      //存变量池
                index = Integer.parseInt(fldBean.getFldlen()) + split.length() + index;//下标右移
            } else {
                //有处理参数--有下一级报文格式
                String procfunc = fldBean.getProcfunc();  //处理参数
                String datapkgno = procfunc.split("\\|")[0]; //报文格式号
                int cyclesum = Integer.parseInt(procfunc.split("\\|")[1]); //循环次数
                List<FldBean> fldBeanChild = this.resovlePkgFld.GetPkgFld(chnlno, datapkgno,"2");
                if (cyclesum > 1) {
                    //子报文指定了循环次数
                    for (int i = 0; i < cyclesum; i++) {
                        log.info(">>>>>>>>>>>>循环报文域开始>>>>>>>>>>：" + i);
                        index = unPack(i, index, fldBeanChild, dataByte, chnlno, varSet);
                        log.info("----------row：" + i);
                    }
                } else if (cyclesum < 0) {
                    //子报文未指定循环次数,循环解包直至报文结束
                    while (index < dataByte.length) {
                        log.info(">>>>>>>>>>>>循环报文域开始(无限循环)>>>>>>>>：");
                        index = unPack(row, index, fldBeanChild, dataByte, chnlno, varSet);
                        row++;
                        log.info("----------row：" + row);
                    }
                }
            }
        }
        return index;
    }

    /**
     * 功能描述： 定长分隔符打包方法
     *
     * @param data   :报文字典bean
     * @param list   :报文域字典list
     * @param chnlno :渠道号
     * @param index  ：报文域循环次数（变量池深度）
     * @param varSet ：变量池
     */
    private String packPackage(String data, List<FldBean> list, String chnlno, int index, TransVariableSet varSet) {

        for (FldBean fldBean : list) {
            if (fldBean.getProcfunc().equals("000000|0")) {
                data += fillBreak(fldBean, index, varSet);    //组包
                log.info("组包过程报文：" + data);
            } else {
                String procfunc = fldBean.getProcfunc();  //处理参数
                String dataPkgno = procfunc.split("\\|")[0]; //取报文格式号
                int cyclesum = Integer.parseInt(procfunc.split("\\|")[1]); //取循环次数
                List<FldBean> fldBeanChild = this.resovlePkgFld.GetPkgFld(chnlno, dataPkgno,"2");
                if (cyclesum > 1) {
                    for (int i = 0; i < cyclesum; i++) {
                        log.info(">>>>>>>>>>>>>>>>>>>>>>循环打包报文域>>>>>>>>>>>>>：" + i);
                        data = packPackage(data, fldBeanChild, chnlno, i, varSet);
                    }
                }
            }
        }
        return data;
    }

    /**
     * 功能描述： 根据字段长度和对齐方式插入填充符
     *
     * @param fldBean :报文字典bean
     * @param index   :报文域循环次数（变量池深度）
     * @param varSet  :变量池
     */
    public String fillBreak(FldBean fldBean, int index, TransVariableSet varSet) {
        Object obj = varSet.getValue(fldBean.getCommstructfldname(), index);
        String str = obj.toString();
        int valueLength = str.getBytes(Charset.forName("GBK")).length;
        if (fldBean.getAlignmode().equals("0")) {
            //左对齐
            for (int i = 0; i < (Integer.parseInt(fldBean.getFldlen()) - valueLength); i++) {
                str += fldBean.getFillsmbl();
            }
            str += fldBean.getBreaksmbl();
        } else if (fldBean.getAlignmode().equals("1")) {
            //右对齐
            for (int i = 0; i < (Integer.parseInt(fldBean.getFldlen()) - valueLength); i++) {
                str = fldBean.getFillsmbl() + str;
            }
            str += fldBean.getBreaksmbl();
        }
        log.info("打包字典名称：" + fldBean.getCommstructfldname() + "，打包长度：" + fldBean.getFldlen() + "，对齐方式：" + fldBean.getAlignmode());
        log.info("变量池取变量： {key: " + fldBean.getCommstructfldname() + " value:" + obj.toString() + " row:" + index + "}");
        return str;
    }


    /**
     * 功能描述： 解包文时根据字段长度和对齐方式去除填充符
     *
     * @param fldBean:报文字典bean
     * @param value:截取的报文字段
     */
    private String filterFillSyml(FldBean fldBean, String value) {
        if (Integer.parseInt(fldBean.getFldlen()) == 0) return "";
        int symlLen = fldBean.getFillsmbl().length();
        int fldLen = value.length();
        if (fldBean.getAlignmode().equals("0")) {
            //左对齐
            while (value.substring(fldLen - symlLen).equals(fldBean.getFillsmbl())) {
                value = value.substring(0, fldLen - symlLen);
                fldLen -= symlLen;
                if (fldLen - symlLen < 1) {
                    value = "";
                    break;
                }
            }
        } else if (fldBean.getAlignmode().equals("1")) {
            //右对齐
            while (value.substring(0, symlLen).equals(fldBean.getFillsmbl())) {
                if (value.equals(fldBean.getFillsmbl())) {
                    value = "";
                    break;
                }
                value = value.substring(symlLen);
            }
        }
        return value;
    }

}
