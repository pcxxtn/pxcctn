package com.fudian.mid.transet.controller;

import com.alibaba.fastjson.JSONObject;
import com.fudian.mid.common.annotation.Log;
import com.fudian.mid.common.controller.BaseController;
import com.fudian.mid.common.domain.QueryRequest;
import com.fudian.mid.common.domain.ResponseBo;
import com.fudian.mid.common.util.DateUtil;
import com.fudian.mid.transet.dao.SysChnlMapper;
import com.fudian.mid.transet.domain.*;
import com.fudian.mid.transet.service.CommFldService;
import com.fudian.mid.transet.service.CommStructService;
import com.fudian.mid.transet.service.SPkgService;
import com.fudian.mid.transet.service.SysPkgFldService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import static java.util.Collections.*;

@Controller
@RequestMapping("transet/sysPkgFld")
public class SysPkgFldController extends BaseController {
    private static Logger log = LoggerFactory.getLogger(SysPkgFldController.class);
    @Autowired
    SysPkgFldService sysPkgFldService;
    @Autowired
    CommFldService commFldService;

    String chnlno = "-";
    String datapkgno = "-";
    String commstructno = "-";
    @RequestMapping("spkgDicts")
    public String index(String chnlno1,String datapkgno1,String commstructno1) {
        chnlno = chnlno1;
        datapkgno = datapkgno1;
        commstructno = commstructno1;
        log.info("渠道号："+chnlno+"报文格式号"+datapkgno+"通讯结构号"+commstructno);
        return "transet/spkg/spkg_dicts";
    }

    //获取渠道下报文格式报文域
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> spkgrFldList(QueryRequest request, SysPkgFld sysPkgFld) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        //查询通讯结构字段号
        sysPkgFld.setChnlno(chnlno);
        sysPkgFld.setDatapkgno(datapkgno);
        sysPkgFld.setCommstructno(commstructno);
        List<Map> list = this.sysPkgFldService.spkgrFldList(sysPkgFld);

        sysPkgFld.setCommstructno("0");
        List<Map> listComm = this.sysPkgFldService.spkgrFldList(sysPkgFld);
        if(listComm.size()>0){
            list.add(this.sysPkgFldService.spkgrFldList(sysPkgFld).get(0));
        }

        PageInfo<Map> pageInfo = new PageInfo<Map>(list);

        return getDataTable(pageInfo);
    }

    //获取渠道和通讯结构下的数据字典
    @RequestMapping("commFldList")
    @ResponseBody
    public Map<String, Object> getSpkDicts(QueryRequest request, CommFld commFld) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        //查询通讯结构字段号
        commFld.setChnlno(chnlno);
        commFld.setCommstructno(commstructno);

        List<CommFld> list=this.commFldService.findCommFld(commFld);
        //根据数据字典实现排序
        sort(list);
        log.info("commfld size:"+list.size());

        commFld.setCommstructno("0");
        List<CommFld> listComm = this.commFldService.findCommFld(commFld);
        if(listComm.size()>0){
            list.add(this.commFldService.findCommFld(commFld).get(0));
        }
        log.info("commfld all size:"+list.size());
        PageInfo<CommFld> pageInfo=new PageInfo<>(list);
        return getDataTable(pageInfo);
    }

    @Log("新增报文域")
    @RequestMapping("add")
    @ResponseBody
    public ResponseBo addSPkgFld(String sysPkgFldList) {
        try {
            List<String> sysPkgFldlist = Arrays.asList(sysPkgFldList.split("\\+"));
            if (sysPkgFldlist.size()>0){

                log.info(sysPkgFldlist.size()+"Add报文域的sum：");

                //添加之前，删除之前所有的
                List dellist = new ArrayList();
                for(int i=0;i<sysPkgFldlist.size();i++){
                    JSONObject jsonStr = JSONObject.parseObject(sysPkgFldlist.get(i).toString());
                    jsonStr.remove("datapkgfldno");
                    jsonStr.put("datapkgno",datapkgno);
                    dellist.add(jsonStr);
                }
                this.sysPkgFldService.deleteSPkgFld(dellist);

                List addList = new ArrayList();
                for(int i=0;i<sysPkgFldlist.size();i++){
                    JSONObject jsStr = JSONObject.parseObject(sysPkgFldlist.get(i).toString());
                        jsStr.put("datapkgno",datapkgno);
                        jsStr.put("cname",this.getCurrentUser().getUsername());
                        jsStr.put("ctime",DateUtil.getDateTime());
                    addList.add(jsStr);
                }
                    this.sysPkgFldService.addSPkgFld(addList);
            } else{
                return ResponseBo.error("请先添加数据字典！");
            }
            return ResponseBo.ok("新增报文域成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("新增报文域失败，请联系网站管理员！");
        }
    }

    @RequestMapping("getSysPkgFld")
    @ResponseBody
    public ResponseBo getSysPkgFld(String chnlno,String datapkgfldno,String commstructno, String commstructfldno) {
        try {
            if("".equals(datapkgno) || datapkgno == null){
                return ResponseBo.error("请选择数据字典！");
            }
            SysPkgFld sysPkgFld = new SysPkgFld();
            sysPkgFld.setChnlno(chnlno);
            sysPkgFld.setDatapkgno(datapkgno);
            sysPkgFld.setDatapkgfldno(Short.parseShort(datapkgfldno));
            sysPkgFld.setCommstructno(commstructno);
            sysPkgFld.setCommstructno(commstructfldno);
            List<SysPkgFld> list = this.sysPkgFldService.spkgFldList(sysPkgFld);
            if(list.size()==0){return ResponseBo.error("无报文格域！");}
            return ResponseBo.ok(list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("获取报文域信息失败，请联系网站管理员！");
        }
    }

    @Log("修改报文域")
    @RequestMapping("update")
    @ResponseBody
    public ResponseBo updateSPkg(SysPkgFld sysPkgFld, String circleSum) {
        try {
            log.info(circleSum+"---------------circle");

            sysPkgFld.setProcfunc(sysPkgFld.getProcfunc()+"|"+circleSum);
            sysPkgFld.setMname(this.getCurrentUser().getUsername());
            sysPkgFld.setMtime(DateUtil.getDateTime());

            int i = this.sysPkgFldService.updateFld(sysPkgFld);
            if(i==1){
                return ResponseBo.ok("修改报文格式成功！");
            }
            return ResponseBo.ok("修改报文格式失败！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("修改报文格式失败，请联系网站管理员！");
        }
    }

    @Log("删除报文格式")
    @RequestMapping("delete")
    @ResponseBody
    public ResponseBo deleteSPkg(String deldata) {
        try {
            List<String> sysPkgFldlist = Arrays.asList(deldata.split("\\+"));
            if (sysPkgFldlist.size()>0){
                List list = new ArrayList();
                log.info(sysPkgFldlist.size()+"del报文域的sum：");
                for(int i=0;i<sysPkgFldlist.size();i++){
                    JSONObject jsStr = JSONObject.parseObject(sysPkgFldlist.get(i).toString());
                    list.add(jsStr);
                }
                this.sysPkgFldService.deleteSPkgFld(list);
            } else{
                return ResponseBo.error("请选择删除的报文域！");
            }
            return ResponseBo.ok("删除报文域成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("删除报文域失败，请联系网站管理员！");
        }
    }



}
