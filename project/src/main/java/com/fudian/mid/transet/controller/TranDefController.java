package com.fudian.mid.transet.controller;

import com.fudian.mid.common.annotation.Log;
import com.fudian.mid.common.controller.BaseController;
import com.fudian.mid.common.domain.QueryRequest;
import com.fudian.mid.common.domain.ResponseBo;
import com.fudian.mid.common.util.StringUtils;
import com.fudian.mid.transet.dao.TranDefMapper;
import com.fudian.mid.transet.domain.TranDef;
import com.fudian.mid.transet.service.TranDefService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class TranDefController extends BaseController {
    @Autowired
    TranDefMapper tranDefMapper;
    @Autowired
    TranDefService tranDefService;

    @RequestMapping("tran_view")
    public String index() {
        return "transet/tranDef/tran_view";
    }

    @RequestMapping("tran/list")
    @ResponseBody
    public Map<String, Object> tranList(QueryRequest request, TranDef tranDef) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Map> list = this.tranDefMapper.findTran(tranDef);
        PageInfo<Map> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }

    @Log("删除交易码")
    @RequiresPermissions("tran:delete")
    @RequestMapping("tran/delete")
    @ResponseBody
    public ResponseBo deleteTran(String ids) {
        try {
            this.tranDefService.deleteTran(ids);
            return ResponseBo.ok("删除交易码成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("删除交易码失败，请联系网站管理员！");
        }
    }

    @Log("新增交易码")
    @RequiresPermissions("tran:add")
    @RequestMapping("tran/add")
    @ResponseBody
    public ResponseBo addTran(TranDef tranDef) {
        try {
            this.tranDefService.addTran(tranDef);
            return ResponseBo.ok("新增交易码成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("新增交易码失败，请联系网站管理员！");
        }
    }

    @Log("修改交易码")
    @RequiresPermissions("tran:update")
    @RequestMapping("tran/update")
    @ResponseBody
    public ResponseBo updateTran(TranDef tranDef) {
        try {
//           this.tranDefService.updateAll(tranDef);
            this.tranDefService.updateTran(tranDef);
            return ResponseBo.ok("修改交易码成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("修改交易码失败，请联系网站管理员！");
        }
    }

    @RequestMapping(value="tran/findByPrimary")
    @ResponseBody
    public ResponseBo findByPrimary(String tranCode){
        System.out.println(tranCode);
        TranDef tranDef=this.tranDefService.findByTranCode(tranCode);
        if(tranDef!=null){
            return ResponseBo.ok(tranDef);
        }else{
            return ResponseBo.error("获取交易码信息失败");
        }

    }

    @RequestMapping("tran/checkTranCode")
    @ResponseBody
    public boolean checkTranCode(String tranCode, String oldTranCode) {
        if (StringUtils.hasValue(oldTranCode) && tranCode.equalsIgnoreCase(oldTranCode)) {
            return true;
        }
        TranDef result = this.tranDefService.findByTranCode(tranCode);
        if (result != null)
            return false;
        return true;
    }
}
