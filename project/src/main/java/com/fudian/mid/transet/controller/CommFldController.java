package com.fudian.mid.transet.controller;

import com.fudian.mid.common.annotation.Log;
import com.fudian.mid.common.controller.BaseController;
import com.fudian.mid.common.domain.QueryRequest;
import com.fudian.mid.common.domain.ResponseBo;
import com.fudian.mid.common.util.StringUtils;
import com.fudian.mid.transet.dao.CommFldMapper;
import com.fudian.mid.transet.domain.CommFld;
import com.fudian.mid.transet.service.CommFldService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class CommFldController extends BaseController{
    @Autowired CommFldService commFldService;
    @Autowired CommFldMapper commFldMapper;

    @RequestMapping("commFld_view")
    public String index(){
        return "transet/commFld/commFld_view";
    }

    @RequestMapping(value="commFld/list")
    @ResponseBody
    public Map<String,Object> findCommFld(QueryRequest QRequest, CommFld commFld){
        PageHelper.startPage(QRequest.getPageNum(), QRequest.getPageSize());
        System.out.println(commFld.getCommstructfldname());
        List<CommFld> list=this.commFldService.findCommFld(commFld);
        PageInfo<CommFld> pageInfo=new PageInfo<>(list);
        return getDataTable(pageInfo);
    }

    @Log("新增字典")
    @RequiresPermissions("commFld:delete")
    @RequestMapping("commFld/delete")
    @ResponseBody
    public ResponseBo deleteCommFld(String ids){
        return this.commFldService.deleteCommFld(ids);
    }

    @RequestMapping("commFld/checkCommName")
    @ResponseBody
    public boolean checkCommStrName(String chnlno,String commstructno,String commFldName,String oldCommFldName){
        if(StringUtils.hasValue(oldCommFldName)&&commFldName.equals(oldCommFldName)) return true;
        CommFld commFld=new CommFld();
        commFld.setChnlno(chnlno);
        commFld.setCommstructno(commstructno);
        commFld.setCommstructfldname(commFldName);
        List<CommFld> list=this.commFldService.findCommFld(commFld);
        if(list.size()>0) return false;
        else return true;
    }

    @Log("新增字典")
    @RequiresPermissions("commFld:add")
    @RequestMapping("commFld/add")
    @ResponseBody
    public ResponseBo AddCommFld(CommFld commFld){
        if(!StringUtils.hasValue(commFld.getMacflag())) commFld.setMacflag("0");
        if(!StringUtils.hasValue(commFld.getPinflag())) commFld.setPinflag("0");

        commFld.setCname("mrbird");
        commFld.setCtime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        int ret=this.commFldMapper.insertCommFld(commFld);
//        int ret=this.commFldService.save(commFld);
        if(ret>0) return ResponseBo.ok("新增字典成功");
        else return ResponseBo.error("新增字典失败");
    }

    @Log("修改字典")
    @RequiresPermissions("commFld:update")
    @RequestMapping("commFld/update")
    @ResponseBody
    public ResponseBo updateCommFld(CommFld commFld){
        commFld.setMtime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        commFld.setMname("mrbird");
        System.out.println(commFld.getChnlno());
        System.out.println(commFld.getCommstructno());
        System.out.println(commFld.getCommstructfldno());
        int ret=this.commFldService.updateCommFld(commFld);
        if(ret==0) return ResponseBo.error("修改字典失败");
        else return ResponseBo.ok("修改字典成功");
    }
}
