package com.fudian.mid.transet.service.Impl;

import com.fudian.mid.common.domain.ResponseBo;
import com.fudian.mid.common.service.impl.BaseService;
import com.fudian.mid.common.util.StringUtils;
import com.fudian.mid.transet.dao.CommFldMapper;
import com.fudian.mid.transet.domain.CommFld;
import com.fudian.mid.transet.domain.SysPkgFld;
import com.fudian.mid.transet.service.CommFldService;
import com.fudian.mid.transet.service.SysPkgFldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

@Service("CommFldService")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true,rollbackFor = Exception.class)
public class CommFldServiceImpl extends BaseService<CommFld> implements CommFldService{

    @Autowired SysPkgFldService sysPkgFldService;
    @Autowired CommFldMapper commFldMapper;

    @Override
    public List<CommFld> findCommFld(CommFld commFld) {
        Example example=new Example(CommFld.class);
        Example.Criteria criteria=example.createCriteria();

        if(!"".equals(commFld.getChnlno())&&commFld.getChnlno()!=null)
            criteria.andCondition("chnlno=",commFld.getChnlno());
        if(!"".equals(commFld.getCommstructno())&&commFld.getCommstructno()!=null)
            criteria.andCondition("commstructno=",commFld.getCommstructno());
        if(!"".equals(commFld.getCommstructfldno())&&commFld.getCommstructfldno()!=null)
            criteria.andCondition("commstructfldno=",commFld.getCommstructfldno());
        if(!"".equals(commFld.getCommstructfldname())&&commFld.getCommstructfldname()!=null)
            criteria.andCondition("commstructfldname=",commFld.getCommstructfldname());
        List<CommFld> list=this.selectByExample(example);
        return list;
    }

    @Override
    public CommFld findById(Short commFld) {
        return null;
    }

    @Override
    public CommFld findByCommName(String commName) {
        return null;
    }

    @Override
    public int updateCommFld(CommFld commFld) {
        /*
         * 按条件更新值不为null的字段
         * int updateByExampleSelective(User record, UserExample example) thorws SQLException
         */
        Example example=new Example(CommFld.class);
        Example.Criteria criteria=example.createCriteria();

        if(!StringUtils.hasValue(commFld.getMacflag())) commFld.setMacflag("0");
        if(!StringUtils.hasValue(commFld.getPinflag())) commFld.setPinflag("0");

        if(!"".equals(commFld.getChnlno())&&commFld.getChnlno()!=null)
            criteria.andCondition("chnlno=",commFld.getChnlno());
        if(!"".equals(commFld.getCommstructno())&&commFld.getCommstructno()!=null)
            criteria.andCondition("commstructno=",commFld.getCommstructno());
        if(!"".equals(commFld.getCommstructfldno())&&commFld.getCommstructfldno()!=null)
            criteria.andCondition("commstructfldno=",commFld.getCommstructfldno());
        int ret=this.mapper.updateByExampleSelective(commFld,example);
        return ret;
    }

    @Override
    public ResponseBo deleteCommFld(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        CommFld commFld=new CommFld();

        Example example=new Example(SysPkgFld.class);
        Example.Criteria criteria;

        String msg="删除通讯字段成功";
        int ret=1;
        for(String str:list){
            String[] commfld=str.split("\\|");
            criteria=example.createCriteria();
            criteria.andCondition("chnlno=",commfld[0]);
            criteria.andCondition("commstructno=",commfld[1]);
            criteria.andCondition("commstructfldno=",commfld[2]);

            List pkgFldList=this.sysPkgFldService.selectByExample(example);
            if(pkgFldList.size()>0){
                msg="该结构字段仍在使用,不可以删除";
                ret=0;
                break;
            }

            commFld.setChnlno(commfld[0]);
            commFld.setCommstructno(commfld[1]);
            commFld.setCommstructfldno(Short.parseShort(commfld[2]));

            if(this.commFldMapper.deleteByKey(commFld)==0) {
                msg="删除通讯字段失败";
                ret=0;
                break;
            }
        }

        if(ret==0) return ResponseBo.error(msg);
        else return ResponseBo.ok(msg);
    }
}
