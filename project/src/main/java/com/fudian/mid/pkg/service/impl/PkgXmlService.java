package com.fudian.mid.pkg.service.impl;

import com.fudian.mid.pkg.FldBean;
import com.fudian.mid.pkg.ResovlePkgFld;
import com.fudian.mid.pkg.service.IPkg;
import com.fudian.mid.system.var.TransVariableSet;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

/**
 * 功能描述 XML报文打解包
 * 作者 xu
 * 日期 2018-05-02 11:38
 */
@Service("PkgXmlService")
public class PkgXmlService  implements IPkg {

    private final static Logger log = LoggerFactory.getLogger(PkgXmlService.class);
    private String encoding ="";

    @Autowired
    ResovlePkgFld resovlePkgFld;
    /**
     * 功能描述：XML报文解包
     *
     * @param message:报文
     * @param VarSet:变量池
     */
    @Override
    public void unpack(String message, TransVariableSet VarSet) {
        try {
            log.info("unpack messag  :" + message);
            String chnlno = VarSet.getValue("chnlno");
            String fTranCode = VarSet.getValue("trancode");

            Map unpackdef = getUnPackDef(chnlno, fTranCode);
            Document document = DocumentHelper.parseText(message);
            Element root = document.getRootElement();
            Iterator<String> itDef = unpackdef.keySet().iterator();
            while (itDef.hasNext()) {
                String node = itDef.next();
                List nodes = document.selectNodes(node);
                Iterator itNodes = nodes.iterator();
                int i = 0;
                while (itNodes.hasNext()) {
                    Element e = (Element) itNodes.next();
                    VarSet.setValue(e.getName(), e.getText().trim(), i);
                    log.info("XML tag: {"+node+"["+i+"]="+ e.getText().trim()+"}");
                    log.info("变量池VarSet{" + e.getName() + "[" + i + "]:" + e.getText().trim() + "}");
                    i++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 功能描述：报文打包
     *
     * @param VarSet:变量池XML
     */
    @Override
    public String pack(TransVariableSet VarSet) {
        log.info("pack VarSet  :" + VarSet.toString());
        String message = "";
        try {
            String chnlno = VarSet.getValue("chnlno");
            String fTranCode = VarSet.getValue("trancode");
            Map packdef = getPackDef(chnlno, fTranCode);
            message = packXml(packdef, VarSet );
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("pack messag  :" + message);
        return message;
    }


    /**
     * 根据节点tag 获取节点的值
     */
    public  String getTagValue(String message, String  xmlTag) {
        String  tagValue ="";
        try {
            Document document = DocumentHelper.parseText(message);
            Node msgType = document.selectSingleNode(xmlTag);
            tagValue=  msgType.getText();

        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
        }
        return tagValue;
    }

    /**
     * 功能描述： 渠道及交易码获取解包报文格式定义
     */
    private Map getUnPackDef(String chnlno, String fTranCode) {
        //排序TreeMap  ，以节点名称降序排
        TreeMap<String, String> defMap = new TreeMap<String, String>(new Comparator<String>(){
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        List<FldBean> list = resovlePkgFld.GetPkgFld(chnlno, fTranCode,"0");
        for (FldBean bean : list) {
            String fldname = "/" + bean.getCommstructfldname();
            String pkgno = bean.getProcfunc().split("\\|")[0];
            if (!"000000".equalsIgnoreCase(pkgno)) {
                getPkgDef(defMap, chnlno, pkgno, fldname);
            }
        }
        log.info("解包报文格式定义："+defMap);


        return defMap;

    }

    /**
     * 功能描述： 渠道及交易码获取打包报文格式定义
     */
    private Map getPackDef(String chnlno, String fTranCode) {
        //排序TreeMap  ，以节点名称降序排
        TreeMap<String, String> defMap = new TreeMap<String, String>(new Comparator<String>(){
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        List<FldBean> fldList = resovlePkgFld.GetPkgFld(chnlno, fTranCode,"1");
        //对xml对应的是根节点，只有一条记录
        for (FldBean bean : fldList) {
            encoding=bean.getEncoding();
            String fldname = "/" + bean.getCommstructfldname();
            String pkgno = bean.getProcfunc().split("\\|")[0];
            if (!"000000".equalsIgnoreCase(pkgno)) {
                getPkgDef(defMap, chnlno, pkgno, fldname);
            }
        }
        log.info("打包报文格式定义："+defMap);

        return defMap;

    }


    /**
     * 功能描述： 渠道及交易码获取打包报文格式定义
     */
    private void getPkgDef(Map defMap, String chnlno, String datapkgno, String pName) {

        System.out.println("chnlno=="+chnlno +"     datapkgno "+datapkgno  +"    pName   "+pName);
        List<FldBean> fldList = resovlePkgFld.GetPkgFld(chnlno, datapkgno,"2");
        for (FldBean fldBean : fldList) {
            String fldname = pName + "/" + fldBean.getCommstructfldname();

            String pkgno = fldBean.getProcfunc().split("\\|")[0];
            String loop = fldBean.getProcfunc().split("\\|")[1];
            if (!"000000".equalsIgnoreCase(pkgno)) {
                getPkgDef(defMap, chnlno, pkgno, fldname);
            } else {
               defMap.put(fldname, loop);
            }
        }
    }

    /**
     * 功能描述： 报文结构定义及变量池打包XML
     */
    private String packXml(Map defMap, TransVariableSet varSet ) {
        String xml = "";
        //默认编码方式GBK
        String xmlEncoding ="GBK";
        if ("1".equals(encoding)){
            xmlEncoding="UTF-8";
        }
        Document doc = DocumentHelper.createDocument();
        doc.setXMLEncoding(xmlEncoding);
        Iterator<Map.Entry<String, String>> it = defMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> e = it.next();
            String key = e.getKey();
            String subKey = key.substring(key.lastIndexOf("/") + 1);


            int count = varSet.getValueNum(subKey);
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    //节点名称先给个下标[i],生成XML后再去除掉
                    String elePath = key.substring(0, key.lastIndexOf("/" + subKey)) + "[" + i + "]" + "/" + subKey;
                    elePath=preTag(elePath);
                    Element ele = DocumentHelper.makeElement(doc, elePath);
                    String value = (String) varSet.getValue(subKey, i);
                    if (value != null) {
                        ele.setText(value);
                    } else {
                        ele.setText("");
                    }
                }

            } else {
                //如果是单条数据 节点名称先给个下标[1],生成XML后再去除掉
                String elePath = key.substring(0, key.lastIndexOf("/" + subKey)) + "[" + 0 + "]" + "/" + subKey;
                elePath=preTag(elePath);
                Element ele = DocumentHelper.makeElement(doc, elePath);
                String value = (String) varSet.getValue(subKey);
                if (value != null) {
                    ele.setText(value);
                } else {
                    ele.setText("");
                }
            }

        }

        //格式化XML
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setExpandEmptyElements(false);
        format.setEncoding(xmlEncoding);
        StringWriter out = new StringWriter();
        XMLWriter writer = new XMLWriter(out, format);
        try {
            writer.write(doc);
        }
        catch (IOException e) {
            //格式化错误直接返回
            return  doc.asXML().replaceAll("\\[\\d*\\]", "");
        }
        xml = out.toString();
        // 生成XML后 ，去除节点的下标
        xml = xml.replaceAll("\\[\\d*\\]", "");
        return xml;
    }


    private String preTag(String tag) {
        String str = "";
        String[] tmp = tag.split("/");
        for (int i = 0; i < tmp.length; i++) {
            if (i != 0) {
                if (i != tmp.length - 1 && i != tmp.length - 2) {
                    str = str + "/" + tmp[i] + "[0]";
                } else {
                    str = str + "/" + tmp[i];
                }

            }
        }
        return str;
    }

}
