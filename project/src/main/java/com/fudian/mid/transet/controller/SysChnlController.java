package com.fudian.mid.transet.controller;

import com.fudian.mid.common.annotation.Log;
import com.fudian.mid.common.controller.BaseController;
import com.fudian.mid.common.domain.QueryRequest;
import com.fudian.mid.common.domain.ResponseBo;
import com.fudian.mid.common.util.StringUtils;
import com.fudian.mid.system.domain.Dict;
import com.fudian.mid.transet.dao.DictFldMapper;
import com.fudian.mid.transet.dao.SysChnlMapper;
import com.fudian.mid.transet.domain.DictFld;
import com.fudian.mid.transet.domain.SysChnl;
import com.fudian.mid.transet.domain.TranDef;
import com.fudian.mid.transet.service.DictFldService;
import com.fudian.mid.transet.service.SysChnlService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class SysChnlController extends BaseController {

    @Autowired
    SysChnlService sysChnlService;
    @Autowired
    SysChnlMapper sysChnlMapper;

    @RequestMapping("sysChnl_view")
    public String index() {
        return "transet/sysChnl/sysChnl_view";
    }

    @RequestMapping("sysChnl/list")
    @ResponseBody
    public Map<String, Object> list(QueryRequest request, SysChnl sysChnlysChnl) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Map> list = this.sysChnlMapper.findSysChnl(sysChnlysChnl);
        PageInfo<Map> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }

    @RequestMapping("sysChnl/get")
    @ResponseBody
    public ResponseBo getChnl(String  chnlno) {
        try {
            SysChnl chnl = this.sysChnlService.findById(chnlno);
            return ResponseBo.ok(chnl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("获取渠道信息失败，请联系网站管理员！");
        }
    }

    @Log("新增渠道 ")
    @RequiresPermissions("sysChnl:add")
    @RequestMapping("sysChnl/add")
    @ResponseBody
    public ResponseBo addChnl(SysChnl chnl) {
        try {
          /*  SysChnl chnltep  = this.sysChnlService.findById(chnl.getChnlno());
            if (chnltep!=null ){
                return ResponseBo.error("当前渠道号已存在，请确认");
            }*/
            chnl.setCname(this.getCurrentUser().getUsername());
            chnl.setCtime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") );
            this.sysChnlService.addChnl(chnl);
            return ResponseBo.ok("新增渠道成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("新增渠道失败，请联系网站管理员！");
        }
    }

    @Log("删除渠道")
    @RequiresPermissions("sysChnl:delete")
    @RequestMapping("sysChnl/delete")
    @ResponseBody
    public ResponseBo deleteChnls(String ids) {
        try {
            this.sysChnlService.deleteChnl(ids);
            return ResponseBo.ok("删除渠道成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("删除渠道失败，请联系网站管理员！");
        }
    }

    @Log("修改渠道 ")
    @RequiresPermissions("sysChnl:update")
    @RequestMapping("sysChnl/update")
    @ResponseBody
    public ResponseBo updateChnl(SysChnl chnl) {
        try {

            SysChnl chnltep = this.sysChnlService.findById(chnl.getChnlno());
            chnl.setCname(chnltep.getCname());
            chnl.setCtime(chnltep.getCtime());

            chnl.setMname(this.getCurrentUser().getUsername());
            chnl.setMtime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") );
            this.sysChnlService.updateChnl(chnl);
            return ResponseBo.ok("修改渠道成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("修改渠道失败，请联系网站管理员！");
        }
    }

    @RequestMapping("sysChnl/check")
    @ResponseBody
    public boolean check(String chnlno,String chnlnoold) {

        if(StringUtils.hasValue(chnlno)&&chnlno.equals(chnlnoold))
            return true;
        SysChnl result = this.sysChnlService.findById(chnlno);
        System.out.println("======================================="+result);
        if (result != null)
            return false;
        return true;
    }

    @RequestMapping("sysChnl/getChnlOption")
    @ResponseBody
    public List getDistinctChnlno(){
        List list=this.sysChnlService.getChnlOption();
        return list;
    }
}
