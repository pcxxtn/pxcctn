package com.fudian.mid.transet.controller;

import com.fudian.mid.common.annotation.Log;
import com.fudian.mid.common.controller.BaseController;
import com.fudian.mid.common.domain.QueryRequest;
import com.fudian.mid.common.domain.ResponseBo;
import com.fudian.mid.common.util.StringUtils;
import com.fudian.mid.system.domain.User;
import com.fudian.mid.transet.dao.DictFldMapper;
import com.fudian.mid.transet.domain.DictFld;
import com.fudian.mid.transet.service.DictFldService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class DictFldController extends BaseController {
    @Autowired DictFldService dictFldService;
    @Autowired DictFldMapper  dictFldMapper;


    @RequestMapping("dict_view")
    public String index() {
        return "transet/dictFld/dict_view";
    }

    @RequestMapping("dictFld/list")
    @ResponseBody
    public Map<String, Object> userList(QueryRequest request, DictFld dictFld) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Map> list = this.dictFldMapper.findDictFld(dictFld);
        PageInfo<Map> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }

    @Log("新增字典")
    @RequiresPermissions("dictFld:add")
    @RequestMapping("dictFld/add")
    @ResponseBody
    public ResponseBo addDictFld(DictFld dictFld) {
        System.out.println("!!!@@@@@@@@@@@@@@@@@@@");
        System.out.println(dictFld.getDictNo());
        try {
            if ("".equalsIgnoreCase(dictFld.getDictNo()+"")||dictFld.getDictNo()==null){
                dictFld.setDictNo(Short.valueOf("2"));
                if(dictFld.getDictMacSign()==null)dictFld.setDictMacSign(Short.valueOf("1"));
                if(dictFld.getDictPinSign()==null)dictFld.setDictPinSign(Short.valueOf("1"));
                dictFld.setDictCreateTime(DateFormatUtils.format(new Date(), "yyyyMMdd HHmmss"));
                this.dictFldService.save(dictFld);
            } else{
                this.dictFldService.updateNotNull(dictFld);
            }
//            dictFld.setDictNo(Short.valueOf("2"));
//            this.dictFldMapper.insert(dictFld);
            return ResponseBo.ok("新增字典成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("新增字典失败，请联系网站管理员！");
        }
    }

    @Log("修改字典")
    @RequiresPermissions("dictFld:update")
    @RequestMapping("dictFld/update")
    @ResponseBody
    public ResponseBo updateUser(DictFld dictFld) {
        try {
            if(dictFld.getDictMacSign()==null)dictFld.setDictMacSign(Short.valueOf("1"));
            if(dictFld.getDictPinSign()==null)dictFld.setDictPinSign(Short.valueOf("1"));
            this.dictFldService.updateAll(dictFld);
            return ResponseBo.ok("修改字典成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("修改字典失败，请联系网站管理员！");
        }
    }

    @Log("删除字典")
    @RequiresPermissions("dictFld:delete")
    @RequestMapping("dictFld/delete")
    @ResponseBody
    public ResponseBo deleteUsers(String ids) {
        try {
            System.out.println(ids);
            this.dictFldService.deleteDictFld(ids);
            return ResponseBo.ok("删除字典成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("删除字典失败，请联系网站管理员！");
        }
    }

    @RequestMapping(value="dictFld/findByPrimary")
    @ResponseBody
    public ResponseBo findByPrimary(Short dictNo){
        System.out.println(dictNo);
        DictFld dictFld=this.dictFldService.findById(dictNo);
        if(dictFld!=null){
            return ResponseBo.ok(dictFld);
        }else{
            return ResponseBo.error("获取字典信息失败");
        }

    }

    @RequestMapping(value="dictFld/checkDictName")
    @ResponseBody
    public boolean checkDictName(String dictName,String olddictName){
        System.out.println(dictName);
        if(StringUtils.hasValue(olddictName) &&olddictName.equals(dictName)) return true;
        DictFld dictFld=this.dictFldService.findByDictName(dictName);
        if(dictFld==null){
            return true;
        }else{
            return false;
        }
    }

}
