package com.fudian.mid.transet.controller;

import com.fudian.mid.common.annotation.Log;
import com.fudian.mid.common.controller.BaseController;
import com.fudian.mid.common.domain.QueryRequest;
import com.fudian.mid.common.domain.ResponseBo;
import com.fudian.mid.common.util.StringUtils;
import com.fudian.mid.transet.domain.CommStruct;
import com.fudian.mid.transet.service.CommStructService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Controller
public class CommStructControlller extends BaseController {

    @Autowired
    CommStructService commstrs;

    @RequestMapping("commStruct_view")
    public String index(){return "transet/commStruct/commStruct_view";}

    @RequestMapping("commStruct/list")
    @ResponseBody
    public Map<String, Object> findCommStr(QueryRequest QRequest, CommStruct commStruct){
        PageHelper.startPage(QRequest.getPageNum(), QRequest.getPageSize());
        List<CommStruct> list=this.commstrs.findCommStr(commStruct);
        PageInfo<CommStruct> pageInfo=new PageInfo<>(list);
        return getDataTable(pageInfo);
    }


    @Log("检查通讯结构名称")
    @RequestMapping("commStruct/checkStructName")
    @ResponseBody
    public boolean checkStructName(String chnlno,String commstructname,String oldcommstructname){
        if(StringUtils.hasValue(oldcommstructname)&&commstructname.equals(oldcommstructname)) return true;
        Example example=new Example(CommStruct.class);
        Example.Criteria criteria=example.createCriteria();
        if(StringUtils.hasValue(chnlno)&&!"".equals(chnlno))
            criteria.andCondition("chnlno=",chnlno);
        if(StringUtils.hasValue(commstructname)&&!"".equals(commstructname))
            criteria.andCondition("commstructname=",commstructname);
        List list=this.commstrs.selectByExample(example);
        if(list.size()>0)return false;
        else return true;
    }

    @Log("检查通讯结构号")
    @RequestMapping("commStruct/checkStructNo")
    @ResponseBody
    public boolean checkStructNo(String chnlno,String commstructno,String oldcommstructno){
        if(StringUtils.hasValue(oldcommstructno)&&commstructno.equals(oldcommstructno)) return true;
        Example example=new Example(CommStruct.class);
        Example.Criteria criteria=example.createCriteria();
        if(StringUtils.hasValue(chnlno)&&!"".equals(chnlno))
            criteria.andCondition("chnlno=",chnlno);
        if(StringUtils.hasValue(commstructno)&&!"".equals(commstructno))
            criteria.andCondition("commstructno=",commstructno);
        List list=this.commstrs.selectByExample(example);
        if(list.size()>0)return false;
        else return true;
    }

    @Log("删除通讯结构")
    @RequiresPermissions("commStruct:delete")
    @RequestMapping("commStruct/delete")
    @ResponseBody
    public ResponseBo deleteCommStruct(CommStruct commStruct){
        System.out.println(commStruct.getChnlno());
        System.out.println(commStruct.getCommstructno());
        int ret=this.commstrs.deleteCommStruct(commStruct);
        if(ret>0) return ResponseBo.ok("删除通讯结构成功");
        else return ResponseBo.error("删除通讯结构失败,请检查该结构是否仍在使用");
    }

    @Log("增加通讯结构")
    @RequiresPermissions("commStruct:add")
    @RequestMapping("commStruct/add")
    @ResponseBody
    public ResponseBo addCommStruct(CommStruct commStruct){
        int ret=this.commstrs.saveCommStruct(commStruct);
        if(ret>0) return ResponseBo.ok("添加通讯结构成功");
        else return ResponseBo.error("添加通讯结构失败");
    }

    @Log("修改通讯结构")
    @RequiresPermissions("commStruct:update")
    @RequestMapping("commStruct/update")
    @ResponseBody
    public ResponseBo updateCommStruct(CommStruct commStruct){
        int ret=this.commstrs.updateCommStruct(commStruct);
        if(ret>0) return ResponseBo.ok("修改通讯结构成功");
        else return ResponseBo.error("修改通讯结构失败");
    }
}
