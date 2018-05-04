package com.fudian.mid.pkg.service.impl;

import com.fudian.mid.Application;
import com.fudian.mid.pkg.service.impl.PkgXmlService;
import com.fudian.mid.system.var.TransVariableSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class XMLPkgTest {
    @Autowired
    PkgXmlService pkgService;

    @Test
    public void testUnpack() throws Exception {
        //云南财政社保余额查询解包报文
//        String chnlno = "201";
//        String fTranCode = "B00201";
//        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<Root>\n" +
//                "\t<Head>\n" +
//                "\t\t<Version>v1.0</Version>\n" +
//                "\t\t<MsgType>B00201</MsgType>\n" +
//                "\t\t<Src>00111101</Src>\n" +
//                "\t\t<Dst>130</Dst>\n" +
//                "\t\t<MsgId>B002010011112017111600000173</MsgId>\n" +
//                "\t\t<MsgRef></MsgRef>\n" +
//                "\t\t<SessionId>asdsa</SessionId>\n" +
//                "\t\t<PageMaxSize>200</PageMaxSize>\n" +
//                "\t\t<PageCount>3</PageCount>\n" +
//                "\t\t<PageNo>1</PageNo>\n" +
//                "\t\t<RecordCount>200</RecordCount>\n" +
//                "\t\t<WorkDate>20171116</WorkDate>\n" +
//                "\t\t<Reserved></Reserved>\n" +
//                "\t</Head>\n" +
//                "\t<Body>\n" +
//                "\t\t<Record>\n" +
//                "\t\t\t<BankAcctId>ahdfkajh-dafdf-safdsf-adfasf</BankAcctId>\n" +
//                "\t\t\t<BankAcctNo>621008786</BankAcctNo>\n" +
//                "\t\t\t<BankAcctName>财政厅离休干部两费保障专户</BankAcctName>\n" +
//                "\t\t\t<EstbBankName>上海浦东发展银行股份有限公司支行</EstbBankName>\n" +
//                "\t\t\t<SubBankAcctNo>621008786-01</SubBankAcctNo>\n" +
//                "\t\t\t<Reserve1></Reserve1>\n" +
//                "\t\t\t<Reserve2></Reserve2>\n" +
//                "\t\t</Record>\n" +
//                "\t\t<Record>\n" +
//                "\t\t\t<BankAcctId>ahdfkajh-dafdf-safdsf-adfasf</BankAcctId>\n" +
//                "\t\t\t<BankAcctNo>621008786</BankAcctNo>\n" +
//                "\t\t\t<BankAcctName>财政厅基本养老专户</BankAcctName>\n" +
//                "\t\t\t<EstbBankName>上海浦东发展银行股份有限公司支行</EstbBankName>\n" +
//                "\t\t\t<SubBankAcctNo>621008786-02</SubBankAcctNo>\n" +
//                "\t\t\t<Reserve1></Reserve1>\n" +
//                "\t\t\t<Reserve2></Reserve2>\n" +
//                "\t\t</Record>\n" +
//                "\t</Body>\n" +
//                "</Root>\n";

        //云南财政社保获取秘钥解包报文
//        String chnlno = "201";
//        String fTranCode = "A00101";
//        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<Root>\n" +
//                "\t<Head>\n" +
//                "\t\t<Version>v1.0</Version>\n" +
//                "\t\t<MsgType>A00101</MsgType>\n" +
//                "\t\t<Src>130</Src>\n" +
//                "\t\t<Dst>00111101</Dst>\n" +
//                "\t\t<MsgId>A001010011112017111600000173</MsgId>\n" +
//                "\t\t<MsgRef></MsgRef>\n" +
//                "\t\t<SessionId>20170831110646200022</SessionId>\n" +
//                "\t\t<PageMaxSize>1</PageMaxSize>\n" +
//                "\t\t<PageCount>1</PageCount>\n" +
//                "\t\t<PageNo>1</PageNo>\n" +
//                "\t\t<RecordCount>1</RecordCount>\n" +
//                "\t\t<WorkDate>20171116</WorkDate>\n" +
//                "\t\t<Reserved></Reserved>\n" +
//                "\t</Head>\n" +
//                "\t<Body>\n" +
//                "\t\t<Record>\n" +
//                "\t\t\t<SId>123798127389126391278</SId>\n" +
//                "\t\t</Record>\n" +
//                "</Body>\n" +
//                "</Root>\n";

        //云南财政社保查询交易明细解包报文
//        String chnlno = "201";
//        String fTranCode = "B00301";
//        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<Root>\n" +
//                "\t<Head>\n" +
//                "\t\t<Version>v1.0</Version>\n" +
//                "\t\t<MsgType>B00301</MsgType>\n" +
//                "\t\t<Src>130</Src>\n" +
//                "\t\t<Dst>00111101</Dst>\n" +
//                "\t\t<MsgId>B003010011112017111600000173</MsgId>\n" +
//                "\t\t<MsgRef></MsgRef>\n" +
//                "\t\t<SessionId>123123126554</SessionId>\n" +
//                "\t\t<PageMaxSize>200</PageMaxSize>\n" +
//                "\t\t<PageCount>3</PageCount>\n" +
//                "\t\t<PageNo>1</PageNo>\n" +
//                "\t\t<RecordCount>200</RecordCount>\n" +
//                "\t\t<WorkDate>20171116</WorkDate>\n" +
//                "\t\t<Reserved></Reserved>\n" +
//                "\t</Head>\n" +
//                "\t<Body>\n" +
//                "\t\t<Record>\n" +
//                "\t\t\t<FromDateTime>20170801000000</FromDateTime>\n" +
//                "\t\t\t<ToDateTime>20170831000000</ToDateTime>\n" +
//                "\t\t\t<BankAcctNo>6294786</BankAcctNo>\n" +
//                "\t\t\t<BankAcctName>离休干部医药费</BankAcctName>\n" +
//                "\t\t\t<EstbBankName>上海浦东发展银行股份有限公司支行</EstbBankName>\n" +
//                "\t\t\t<SubBankAcctNo>6294786-01</SubBankAcctNo>\n" +
//                "\t\t\t<RqstPageNo>1</RqstPageNo>\n" +
//                "\t\t\t<OptSessionId>123123123123</OptSessionId>\n" +
//                "\t\t\t<Reserve1></Reserve1>\n" +
//                "\t\t\t<Reserve2></Reserve2>\n" +
//                "\t\t</Record>\n" +
//                "\t</Body>\n" +
//                "</Root>\n";

        //云南财政社保查询交易明细解包报文

        //

        //云南财政社保解包报文

        //云南财政社保查询社保基金存储月报表
        String chnlno = "201";
        String fTranCode = "B00401";
        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Root>\n" +
                "\t<Head>\n" +
                "\t\t<Version>v1.0</Version>\n" +
                "\t\t<MsgType>B00401</MsgType>\n" +
                "\t\t<Src>001111</Src>\n" +
                "\t\t<Dst>BK102</Dst>\n" +
                "\t\t<MsgId>B004010011112017111600000173</MsgId>\n" +
                "\t\t<MsgRef></MsgRef>\n" +
                "\t\t<SessionId>123123126554</SessionId>\n" +
                "\t\t<PageMaxSize>200</PageMaxSize>\n" +
                "\t\t<PageCount>1</PageCount>\n" +
                "\t\t<PageNo>1</PageNo>\n" +
                "\t\t<RecordCount>1</RecordCount>\n" +
                "\t\t<WorkDate>20171116</WorkDate>\n" +
                "\t\t<Reserved></Reserved>\n" +
                "\t</Head>\n" +
                "\t<Body>\n" +
                "\t\t<Record>\n" +
                "\t\t\t<BankCode>102</BankCode>\n" +
                "\t\t\t<RqstYearMonth>201706</RqstYearMonth>\n" +
                "\t\t\t<AgencyCode>001111</AgencyCode>\n" +
                "\t\t\t<AgencyName>云南省财政厅</AgencyName>\n" +
                "\t\t\t<Reserve1></Reserve1>\n" +
                "\t\t\t<Reserve2></Reserve2>\n" +
                "\t\t</Record>\n" +
                "\t</Body>\n" +
                "</Root>\n";


        TransVariableSet varSet = new TransVariableSet();
        varSet.setValue("chnlno", chnlno);
        varSet.setValue("trancode", fTranCode);
        long s = System.currentTimeMillis();
        pkgService.unpack(xmlStr, varSet);
        long t = System.currentTimeMillis();
        System.out.println("解包耗时 " + (t - s));


        //云南财政社保账户余额查询打包变量池放值
//        varSet.setValue("BlncDateTime", "201804271647381",0);
////        varSet.setValue("BankAcctId", "",0);
////        varSet.setValue("BankAcctNo", "",0);
////        varSet.setValue("SubBankAcctNo", "",0);
//        varSet.setValue("BlncAmount", "888888888.79",0);
//        varSet.setValue("SubBlncAmount", "369932708.78",0);
//        varSet.setValue("CrrnAmount", "369932708.78",0);
//        varSet.setValue("FxdAmount", "0",0);
//        varSet.setValue("currency", "RMB",0);
//        varSet.setValue("Reserve3", "",0);
//        varSet.setValue("Reserve4", "",0);
//
//        varSet.setValue("BlncDateTime", "201804271947381",1);
////        varSet.setValue("BankAcctId", "",1);
////        varSet.setValue("BankAcctNo", "",1);
////        varSet.setValue("SubBankAcctNo", "",1);
//        varSet.setValue("BlncAmount", "999999999.79",1);
//        varSet.setValue("SubBlncAmount", "32344423.78",1);
//        varSet.setValue("CrrnAmount", "32344423.78",1);
//        varSet.setValue("FxdAmount", "0",1);
//        varSet.setValue("currency", "RMB",1);
//        varSet.setValue("Reserve3", "",1);
//        varSet.setValue("Reserve4", "",1);

        //云南财政社保获取秘钥打包变量池放值

        //云南财政社保获取秘钥打包变量池放值
//        varSet.setValue("SM4", "LKHJASFHJASKDHFKJASLHF");

        //云南财政社保查询交易明细打包变量池放值（仅模拟报文结构，只有部分参数值）
//        varSet.setValue("BankAcctName", "离休干部医药费");
//        varSet.setValue("EstbBankName", "上海浦东发展银行股份有限公司支行");
//
//        varSet.setValue("SerialId", "20170831110646200022",0);
//        varSet.setValue("RcprBankAcctName", "财政厅（社保户",0);
//        varSet.setValue("Purpose", "社区补贴// 其他社会保险基金支出 347274.08",0);
//
//        varSet.setValue("SerialId", "20180428110646200022",1);
//        varSet.setValue("RcprBankAcctName", "财政厅（社保户",1);
//        varSet.setValue("Purpose", "社区补贴// 其他社会保险基金支出 347274.08",1);

        //云南财政社保查询社保基金存储月报表打包变量池放值（仅模拟报文结构，只有部分参数值）
//        varSet.setValue("BankCode", "离休干部医药费");
//        varSet.setValue("RqstYearMonth", "上海浦东发展银行股份有限公司支行");

        varSet.setValue("BankCode", "102",0);
        varSet.setValue("RqstYearMonth", "201706",0);
        varSet.setValue("InsTypeName", "企业职工基本养老保险基金",0);
        varSet.setValue("DpstAmount", "823178.9",0);

        varSet.setValue("BankCode", "102",1);
        varSet.setValue("RqstYearMonth", "201804",1);
        varSet.setValue("InsTypeName", "企业职工基本养老保险基金",1);
        varSet.setValue("DpstAmount", "888888.9",1);




        String message = pkgService.pack(varSet);
        System.out.println("打包耗时 " + (System.currentTimeMillis() - t));

        System.out.println("打包======" + message);
        String  msgtype = pkgService.getTagValue(message,"/Root/Head/MsgType");

        System.out.println("msgtype"+msgtype);
    }



}
