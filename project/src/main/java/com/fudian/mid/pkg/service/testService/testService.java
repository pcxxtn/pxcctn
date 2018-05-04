package com.fudian.mid.pkg.service.testService;

import com.fudian.mid.Application;
import com.fudian.mid.pkg.service.impl.PkgFixDelimiterService;
import com.fudian.mid.pkg.service.impl.PkgVarDelimiterService;
import com.fudian.mid.system.var.TransVariableSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 功能描述
 * 作者 zhaobei
 * 日期 2018-04-28 09:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class testService {
    @Autowired
    PkgFixDelimiterService pkgFixDelimiterService;
    @Autowired
    PkgVarDelimiterService pkgVarDelimiterService;


    /**
     * 变长分隔符打解包测试
     */
    @Test
    public void testUnpackAndPack() throws Exception {
        String chnlno = "409";
        String fTranCode = "999999";
        //测试报文
        String message = "9011|002069|999999|9|011|洪树楷|048|1|950212482||1|9410200000000000844|0010000000|||1|530123193609230015|20180414|111111|0|100.00|222222|0|200.00|333333|0|300.00|";

        TransVariableSet varSet = new TransVariableSet();
        varSet.setValue("chnlno", chnlno);
        varSet.setValue("trancode", fTranCode);

        try {
            pkgVarDelimiterService.unpack(message, varSet);//解包

            //水费新增变量池变量
//            varSet.setValue("respcode", "11");
//            varSet.setValue("retcode", "0001");
//            varSet.setValue("bankcode", "7");
//            varSet.setValue("userno", "407106757");
//            varSet.setValue("busiseqno", "201804135630");
//            varSet.setValue("qysystime", "20180319094750");
//            varSet.setValue("invoicenum", "1");
//            varSet.setValue("customerno", "407106757");
//            varSet.setValue("customername", "马利芬");
//            varSet.setValue("addr", "金星小区169号");
//            varSet.setValue("signdate", "20180311");
//            varSet.setValue("waterquantity", "19");
//            varSet.setValue("totalfee", "85.55");
//            varSet.setValue("realfee", "85.00");
//            varSet.setValue("releasefee", "0.15");
//            varSet.setValue("penalsum", "0.00");
//            varSet.setValue("totalsewagefee", "19.00");
//            varSet.setValue("totalwaterfee", "46.55");
//            varSet.setValue("addchargepart1", "垃圾处理费");
//            varSet.setValue("addfee1", "20.00");
//            varSet.setValue("addchargepart2", "");
//            varSet.setValue("addfee2", "0.00");
//            varSet.setValue("addchargepart3", "");
//            varSet.setValue("addfee3", "0.00");
//            varSet.setValue("invoicedate", "20180311");
//            varSet.setValue("arrearagenum", "1");
//            varSet.setValue("arrearage", "85.55");

            pkgVarDelimiterService.pack(varSet);//打包
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 定长分隔符打解包测试
     */
    @Test
    public void testUnpackAndPack1() throws Exception {
        String chnlno = "410";
        String fTranCode = "888888";
        //测试报文
        String message = "9011      |002069  |888888  |9|011|洪树楷                                                      |048|1|950212482                     |                              |1|9410200000000000844      |0010000000|                                                            |                    |1|530123193609230015  |20180414|111111    |0|100.00        |222222    |0|200.00        |333333    |0|300.00        |";
//        String message = "011|洪树楷......................................................|048|1|950212482.....................|..............................|1|9410200000000000844......|0010000000|............................................................|....................|1|530123193609230015..|20180414|111111....|0|100.00........|222222....|0|200.00........|333333....|0|300.00........|";
//        String message1 = "9011......|002069..|5501....|9|011|洪树楷......................................................|048|1|950212482.....................|..............................|1|9410200000000000844......|0010000000|............................................................|....................|1|530123193609230015..|20180414|111111....|0|100.00........|222222....|0|200.00........|333333....|0|300.00........|";

        TransVariableSet varSet = new TransVariableSet();
        varSet.setValue("chnlno", chnlno);
        varSet.setValue("trancode", fTranCode);

        try {
            //解包
            pkgFixDelimiterService.unpack(message, varSet);
            String str = "";
//            //水费新增变量池变量
//            varSet.setValue("respcode", "11");
//            varSet.setValue("retcode", "0001");
//            varSet.setValue("bankcode", "7");
//            varSet.setValue("userno", "407106757");
//            varSet.setValue("busiseqno", "201804135630");
//            varSet.setValue("qysystime", "20180319094750");
//            varSet.setValue("invoicenum", "1");
//            varSet.setValue("customerno", "407106757");
//            varSet.setValue("customername", "马利芬");
//            varSet.setValue("addr", "金星小区169号");
//            varSet.setValue("signdate", "20180311");
//            varSet.setValue("waterquantity", "19");
//            varSet.setValue("totalfee", "85.55");
//            varSet.setValue("realfee", "85.00");
//            varSet.setValue("releasefee", "0.15");
//            varSet.setValue("penalsum", "0.00");
//            varSet.setValue("totalsewagefee", "19.00");
//            varSet.setValue("totalwaterfee", "46.55");
//            varSet.setValue("addchargepart1", "垃圾处理费");
//            varSet.setValue("addfee1", "20.00");
//            varSet.setValue("addchargepart2", "");
//            varSet.setValue("addfee2", "0.00");
//            varSet.setValue("addchargepart3", "");
//            varSet.setValue("addfee3", "0.00");
//            varSet.setValue("invoicedate", "20180311");
//            varSet.setValue("arrearagenum", "1");
//            varSet.setValue("arrearage", "85.55");

            pkgFixDelimiterService.pack(varSet);//打包

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
