package com.fudian.mid.transet.service.Impl;

import com.fudian.mid.common.service.impl.BaseService;
import com.fudian.mid.common.util.StringUtils;
import com.fudian.mid.transet.domain.CommStruct;
import com.fudian.mid.transet.domain.SPkg;
import com.fudian.mid.transet.service.CommStructService;
import com.fudian.mid.transet.service.SPkgService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service("CommStructService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CommStructServiceImpl extends BaseService<CommStruct> implements CommStructService{
    @Autowired SPkgService sPkgService;

    @Override
    public List<CommStruct> findCommStr(CommStruct commStruct) {
        Example example=new Example(CommStruct.class);
        if(!"".equals(commStruct.getChnlno())&&commStruct.getChnlno()!=null)
            example.createCriteria().andCondition("lower(chnlno)=",commStruct.getChnlno());
        if(!"".equals(commStruct.getCommstructno())&&commStruct.getCommstructno()!=null)
            example.createCriteria().andCondition("lower(commstructno)=",commStruct.getCommstructno());;
        example.setOrderByClause("chnlno,commstructno");
            List<CommStruct> list=this.selectByExample(example);
        return list;
    }

    @Override
    public int deleteCommStruct(CommStruct commStruct) {
        //先查看 报文格式表(T_SYS_PKG)中是否使用通讯结构
        Example example=new Example(SPkg.class);
        Example.Criteria criteria=example.createCriteria();
        if(StringUtils.hasValue(commStruct.getChnlno())&&!"".equals(commStruct.getChnlno()))
            criteria.andCondition("chnlno=",commStruct.getChnlno());
        if(StringUtils.hasValue(commStruct.getCommstructno())&&!"".equals(commStruct.getCommstructno()))
            criteria.andCondition("commstructno=",commStruct.getCommstructno());
        List list=this.sPkgService.selectByExample(example);
        if(list.size()>0) return -1;
        return this.mapper.deleteByExample(example);
    }

    @Override
    public int saveCommStruct(CommStruct commStruct) {
        commStruct.setCname("mrbird");
        commStruct.setCtime(DateFormatUtils.format(new Date(), "yyyyMMdd HHmmss"));
        if(!StringUtils.hasValue(commStruct.getIfspace()))commStruct.setIfspace("0");
        int ret=this.save(commStruct);
        return ret;
    }

    @Override
    public int updateCommStruct(CommStruct commStruct) {
        Example example=new Example(CommStruct.class);
        Example.Criteria criteria=example.createCriteria();
        if(!StringUtils.hasValue(commStruct.getIfspace())) commStruct.setIfspace("0");
        criteria.andCondition("chnlno=",commStruct.getChnlno());
        criteria.andCondition("commstructno=",commStruct.getCommstructno());
        int ret=this.mapper.updateByExampleSelective(commStruct,example);
        return ret;
    }
}
