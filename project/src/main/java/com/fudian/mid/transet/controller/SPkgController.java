package com.fudian.mid.transet.controller;

import com.fudian.mid.common.annotation.Log;
import com.fudian.mid.common.controller.BaseController;
import com.fudian.mid.common.domain.QueryRequest;
import com.fudian.mid.common.domain.ResponseBo;
import com.fudian.mid.common.util.DateUtil;
import com.fudian.mid.transet.dao.SysChnlMapper;
import com.fudian.mid.transet.domain.CommStruct;
import com.fudian.mid.transet.domain.SPkg;
import com.fudian.mid.transet.domain.SysChnl;
import com.fudian.mid.transet.service.CommStructService;
import com.fudian.mid.transet.service.SPkgService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("transet/spkg")
public class SPkgController extends BaseController {
    private static Logger log = LoggerFactory.getLogger(SysPkgFldController.class);
    @Autowired
    SPkgService sPkgService;
    @Autowired
    CommStructService commStructService;
    @Autowired
    SysChnlMapper sysChnlMapper;


    @RequestMapping("spkg_view")
    public String index() {
        return "transet/spkg/spkg_view";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> spkgrList(QueryRequest request, SPkg sPkg) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<SPkg> list = this.sPkgService.findSPkg(sPkg);
        PageInfo<SPkg> pageInfo = new PageInfo<SPkg>(list);
        log.info(""+list.size());
        return getDataTable(pageInfo);
    }

    @Log("新增报文格式")
    @RequiresPermissions("spkg:add")
    @RequestMapping("add")
    @ResponseBody
    public ResponseBo addSPkg(SPkg sPkg) {
        try {
            if ("".equalsIgnoreCase(sPkg.getDatapkgno()+"")||sPkg.getDatapkgno()==null){
                log.info(sPkg.getChnlno()+"渠道号：");
                int key = this.sPkgService.getMaxKey();
                sPkg.setDatapkgno(String.valueOf(key));
                sPkg.setCname(this.getCurrentUser().getUsername());
                sPkg.setCtime(DateUtil.getDateTime1());
                this.sPkgService.save(sPkg);
            } else{
                return ResponseBo.error("新增报文格式失败，请联系网站管理员！");
            }
            return ResponseBo.ok("新增报文格式成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("新增报文格式失败，请联系网站管理员！");
        }
    }

    @RequestMapping("getSPkg")
    @ResponseBody
    public ResponseBo getSPkg(String datapkgno,String chnlno) {
        try {
            if("".equals(datapkgno) || datapkgno == null){
                return ResponseBo.error("请选择报文格式！");
            }
            SPkg sPkg = new SPkg();
            sPkg.setDatapkgno(datapkgno);
            List<SPkg> list = this.sPkgService.findSPkg(sPkg);
            if(list.size()==0){return ResponseBo.error("无报文格式！");}
            return ResponseBo.ok(list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("获取报文格式信息失败，请联系网站管理员！");
        }
    }

    @Log("修改报文格式")
    @RequiresPermissions("spkg:update")
    @RequestMapping("update")
    @ResponseBody
    public ResponseBo updateSPkg(SPkg sPkg) {
        try {
            sPkg.setMname(this.getCurrentUser().getUsername());
            sPkg.setMtime(DateUtil.getDateTime());
            int i = this.sPkgService.updateSPkg(sPkg);
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
    @RequiresPermissions("spkg:delete")
    @RequestMapping("delete")
    @ResponseBody
    public ResponseBo deleteSPkg(String datapkgnos) {
        try {
            this.sPkgService.deleteSPkgs(datapkgnos);
            return ResponseBo.ok("删除报文格式成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("删除报文格式失败，请联系网站管理员！");
        }
    }

    @RequestMapping("sysChnlList")
    @ResponseBody
    public ResponseBo sysChnlList(SysChnl sysChnlysChnl) {
        try {
            List<Map> list = this.sysChnlMapper.findSysChnl(sysChnlysChnl);
//            List<CommStruct> list=this.commStructService.findCommStr(commStruct);
            Map<String, Object> map = new HashMap<>();
            log.info("渠道数量："+list.size());
            map.put("rows",list);
            return ResponseBo.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("获取渠道表失败！");
        }

    }

    @RequestMapping("dictList")
    @ResponseBody
    public ResponseBo dictList(String chnlno) {
        try {
            log.info("chnlno:"+chnlno);
            CommStruct commStruct = new CommStruct();
            commStruct.setChnlno(chnlno);
            List<CommStruct> list=this.commStructService.findCommStr(commStruct);
            Map<String, Object> map = new HashMap<>();
            log.info("通讯结构数量："+list.size());
            if(list.size()>0){
                map.put("rows",list);
                return ResponseBo.ok(list);
            }else{
                return ResponseBo.error("无可用通讯结构！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("获取通讯结构表失败！");
        }

    }


}
