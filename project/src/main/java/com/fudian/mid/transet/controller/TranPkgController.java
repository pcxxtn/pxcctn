package com.fudian.mid.transet.controller;

import com.fudian.mid.common.annotation.Log;
import com.fudian.mid.common.controller.BaseController;
import com.fudian.mid.common.domain.QueryRequest;
import com.fudian.mid.common.domain.ResponseBo;
import com.fudian.mid.common.util.StringUtils;
import com.fudian.mid.system.domain.User;
import com.fudian.mid.transet.domain.BusiType;
import com.fudian.mid.transet.domain.SPkg;
import com.fudian.mid.transet.domain.TranCodeConv;
import com.fudian.mid.transet.domain.TranPkg;
import com.fudian.mid.transet.service.BusiTypeService;
import com.fudian.mid.transet.service.SPkgService;
import com.fudian.mid.transet.service.TranCodeConvService;
import com.fudian.mid.transet.service.TranPkgService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("pkg")
public class TranPkgController extends BaseController {
@Autowired
    TranPkgService tranPkgService;
@Autowired
    TranCodeConvService tranCodeConvService;
@Autowired
    SPkgService sPkgService;
@Autowired
    BusiTypeService busiTypeService;

    @RequestMapping("pkg_view")
    public String index() {
        return "transet/tranPkg/pkg_view";
   }


    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> pkgList(QueryRequest request, TranPkg tranPkg) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Map> list = this.tranPkgService.findTranPkg(tranPkg);
        PageInfo<Map> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);

    }

    @Log("删除外部交易码")
    @RequiresPermissions("pkg:delete")
    @RequestMapping("delete")
    @ResponseBody
    public ResponseBo deleteTranPkg(String chnlNo,String fTranCode) {
        try {

//            for (TranPkg tranPkg: tranPkgs 赞不支持批量删除
//                 ) {
//                this.tranPkgService.deleteTranPkg(tranPkg);
//            }
            //若有映射，则把交易码映射记录删除
            TranCodeConv conv=this.tranCodeConvService.findByPrimary(chnlNo,fTranCode);
            if(conv!=null){
                this.tranCodeConvService.deleteTranCodeConv(chnlNo,fTranCode);
            }

            this.tranPkgService.deleteTranPkg(chnlNo,fTranCode);
            return ResponseBo.ok("删除交易码成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("删除外部交易码失败，请联系网站管理员！");
        }
    }

    @Log("新增外部交易码")
    @RequiresPermissions("pkg:add")
    @RequestMapping("add")
    @ResponseBody
    public ResponseBo addTran(TranPkg tranPkg) {
        try {
            User user = getCurrentUser();
            tranPkg.setCname(user.getUsername());
            this.tranPkgService.addTranPkg(tranPkg);
            return ResponseBo.ok("新增外部交易码成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("新增交易码失败，请联系网站管理员！");
        }
    }

    @Log("修改交易码")
    @RequiresPermissions("pkg:update")
    @RequestMapping("update")
    @ResponseBody
    public ResponseBo updateTran(TranPkg tranPkg) {
        try {
            User user = getCurrentUser();
            tranPkg.setMname(user.getUsername());

            this.tranPkgService.updateTranPkg(tranPkg);
            return ResponseBo.ok("修改外部交易码成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("修改外部交易码失败，请联系网站管理员！");
        }
    }

    /*通过主键查找数据*/
    @RequestMapping(value="findByPrimary")
    @ResponseBody
    public ResponseBo findByPrimary(String chnlNo,String fTranCode) {

         TranPkg tranPkg=this.tranPkgService.findByPrimary(chnlNo,fTranCode);
        if(tranPkg!=null){
            return ResponseBo.ok(tranPkg);
        }else{
            return ResponseBo.error("获取外部交易码信息失败");
        }

    }

    /*通过主键查找映射数据*/
    @RequestMapping(value="findConvByPrimary")
    @ResponseBody
    public ResponseBo findConvByPrimary(String chnlNo,String fTranCode) {

        TranCodeConv tranCodeConv = this.tranCodeConvService.findByPrimary(chnlNo,fTranCode);
        if(tranCodeConv != null){
            return ResponseBo.ok(tranCodeConv);
        }else{
            TranPkg tranPkg=this.tranPkgService.findByPrimary(chnlNo,fTranCode);
            if(tranPkg!=null){
                return ResponseBo.ok(tranPkg);
            }else {
                return ResponseBo.error("获取映射信息失败");
            }
        }

    }

    /*检查渠道号和交易码*/
    @RequestMapping(value="checkRepet")
    @ResponseBody
    public boolean checkRepet(String chnlNo,String fTranCode,String oldfTRanCode){
        if(StringUtils.hasValue(fTranCode)&&fTranCode.equals(oldfTRanCode)) return true;
        if (!StringUtils.hasValue(chnlNo) || !StringUtils.hasValue(fTranCode)) {
            return true;
        }

        TranPkg tranPkg=this.tranPkgService.findByPrimary(chnlNo,fTranCode);
        if(tranPkg!=null) return false;
        return true;
    }


    @Log("交易码映射")
    @RequiresPermissions("pkg:map")
    @RequestMapping("map")
    @ResponseBody
    public ResponseBo tranMap(TranCodeConv tranCodeConv) {
        try {
            String chnlNo = tranCodeConv.getChnlno();
            String fTranCode = tranCodeConv.getFtrancode();
            TranCodeConv conv=this.tranCodeConvService.findByPrimary(chnlNo,fTranCode);
            if(conv!=null){
                //update
                User user = getCurrentUser();
                tranCodeConv.setMname(user.getUsername());
                this.tranCodeConvService.updateTranCodeConv(tranCodeConv);
                return ResponseBo.ok("交易码映射修改成功！");
            }else{
                //insert
                User user = getCurrentUser();
                tranCodeConv.setCname(user.getUsername());
                this.tranCodeConvService.addTranCodeConv(tranCodeConv);
                return ResponseBo.ok("交易码映射成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("交易码映射失败，请联系网站管理员！");
        }
    }


    @Log("删除映射")
    @RequestMapping("delConv")
    @ResponseBody
    public ResponseBo deleteMap(TranCodeConv tranCodeConv) {
        try {
            String chnlNo = tranCodeConv.getChnlno();
            String fTranCode = tranCodeConv.getFtrancode();
            this.tranCodeConvService.deleteTranCodeConv(chnlNo,fTranCode);
            return ResponseBo.ok("删除映射成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("删除映射失败，请联系网站管理员！");
        }
    }

    //获取渠道下的包格式号
    @RequestMapping("pkgList")
    @ResponseBody
    public ResponseBo pkgList(String chnlno) {
        try {
            SPkg sPkg = new SPkg();
            sPkg.setChnlno(chnlno);
            List<SPkg> list = this.sPkgService.findSPkg(sPkg);
//            Map<String, Object> map = new HashMap<>();
            if(list.size()>0){
//                map.put("rows",list);
                return ResponseBo.ok(list);
            }else{
                return ResponseBo.error("无可用包格式号！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("获取包格式号失败！");
        }

    }

    @RequestMapping("busiTypeList")
    @ResponseBody
    public ResponseBo busiTypeList(BusiType busiType) {
        try {
            List<Map> list = this.busiTypeService.findBusiType(busiType);
            return ResponseBo.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("获取业务种类失败！");
        }

    }

}
