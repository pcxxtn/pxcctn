package com.fudian.mid.transet.service.Impl;

import com.fudian.mid.common.service.impl.BaseService;
import com.fudian.mid.transet.dao.TranCodeConvMapper;
import com.fudian.mid.transet.domain.TranCodeConv;
import com.fudian.mid.transet.service.TranCodeConvService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service("TranCodeConvService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TranCodeConvServiceImpl extends BaseService<TranCodeConv> implements TranCodeConvService{
    @Autowired
    TranCodeConvMapper tranCodeConvMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteTranCodeConv(String chnlNo,String fTranCode){
        TranCodeConv tranCodeConv = new TranCodeConv();
        tranCodeConv.setChnlno(chnlNo);
        tranCodeConv.setFtrancode(fTranCode);
        this.tranCodeConvMapper.deleteTranCodeConv(tranCodeConv);
    }

    @Override
    public int addTranCodeConv(TranCodeConv tranPkg) {
//        tranPkg.setCreateTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") );
        tranPkg.setCtime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        this.tranCodeConvMapper.insert(tranPkg);
        return 0;
    }


    @Override
    public TranCodeConv findByPrimary(String chnlNo,String fTranCode) {

        Example example = new Example(TranCodeConv.class);
        example.createCriteria().andCondition("lower(chnlno)=", chnlNo.toLowerCase()).andCondition("lower(ftrancode)=", fTranCode.toLowerCase());
//        example.createCriteria().andCondition("lower(ftrancode)=", fTranCode.toLowerCase());
        List<TranCodeConv> list = this.selectByExample(example);
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateTranCodeConv(TranCodeConv tranCodeConv) {
        tranCodeConv.setMtime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//        this.updateNotNull(tranCodeConv);
        this.tranCodeConvMapper.updateTranCodeConv(tranCodeConv);
    }

}
