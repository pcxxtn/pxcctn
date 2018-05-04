package com.fudian.mid.transet.controller;

import com.fudian.mid.common.annotation.Log;
import com.fudian.mid.common.controller.BaseController;
import com.fudian.mid.common.domain.QueryRequest;
import com.fudian.mid.common.domain.ResponseBo;
import com.fudian.mid.common.util.StringUtils;
import com.fudian.mid.system.domain.User;
import com.fudian.mid.transet.domain.BusiType;
import com.fudian.mid.transet.domain.SysChnl;
import com.fudian.mid.transet.service.BusiTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javafx.scene.layout.BackgroundSize;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("busiType")
public class BusiTypeController extends BaseController {

    @Autowired
    BusiTypeService busiTypeService;

    @RequestMapping("busiType_view")
    public String index() {
        return "transet/busiType/busiType_view";
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> busiTypeList(QueryRequest request,  BusiType busiType) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Map> list = this.busiTypeService.findBusiType(busiType);
        PageInfo<Map> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);

    }

    @Log("删除业务种类")
    @RequiresPermissions("busiType:delete")
    @RequestMapping("delete")
    @ResponseBody
    public ResponseBo deleteBusiType(String ids) {
        try {
            this.busiTypeService.deleteBusiType(ids);
            return ResponseBo.ok("删除业务种类成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("删除业务种类失败，请联系网站管理员！");
        }
    }

    @Log("新增业务种类")
    @RequiresPermissions("busiType:add")
    @RequestMapping("add")
    @ResponseBody
    public ResponseBo addBusiType(BusiType busiType) {
        try {

            User user = getCurrentUser();
            busiType.setCname(user.getUsername());
            busiType.setMname(user.getUsername());
            this.busiTypeService.addBusiType(busiType);
            return ResponseBo.ok("新增业务种类成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("新增业务种类失败，请联系网站管理员！");
        }
    }

    @Log("修改交易码")
    @RequiresPermissions("busiType:update")
    @RequestMapping("update")
    @ResponseBody
    public ResponseBo updateBusiType(BusiType busiType) {
        try {

            User user = getCurrentUser();
            busiType.setMname(user.getUsername());

            this.busiTypeService.updateBusiType(busiType);
            return ResponseBo.ok("修改业务种类成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("修改业务种类失败，请联系网站管理员！");
        }
    }

    /*通过主键查找数据*/
    @RequestMapping(value="findByPrimary")
    @ResponseBody
    public ResponseBo findByPrimary(String busitype) {

        BusiType busiType=this.busiTypeService.findByPrimary(busitype);
        if(busiType!=null){
            return ResponseBo.ok(busiType);
        }else{
            return ResponseBo.error("获取业务种类信息失败");
        }

    }

    /*通过主键查找数据*/
    @RequestMapping(value="checkRepet")
    @ResponseBody
    public boolean checkRepet(String busitype,String  busitypeold) {
        if(StringUtils.hasValue(busitype)&&busitype.equals(busitypeold))
            return true;

        BusiType busiType=this.busiTypeService.findByPrimary(busitype);
        if(busiType!=null)
            return false;
        return true;

    }
}
