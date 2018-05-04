package com.fudian.mid.transet.service.Impl;

import com.fudian.mid.common.service.impl.BaseService;
import com.fudian.mid.system.domain.User;
import com.fudian.mid.transet.dao.TranDefMapper;
import com.fudian.mid.transet.domain.DictFld;
import com.fudian.mid.transet.domain.TranDef;
import com.fudian.mid.transet.service.TranDefService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("tranDefService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TranDefServiceImpl extends BaseService<TranDef> implements TranDefService{

    @Autowired
    TranDefMapper tranDefMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteTran(String ids){
        List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list,"tranCode",TranDef.class);
    }

    @Override
    public int addTran(TranDef tranDef) {
        tranDef.setCreateTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") );

        this.tranDefMapper.insert(tranDef);
        return 0;
    }
    @Override
    public TranDef findById(Short tranCode) {
        return this.selectByKey(tranCode);
    }

    @Override
    public TranDef findByTranCode(String tranCode) {
        Example example = new Example(TranDef.class);
        example.createCriteria().andCondition("lower(tran_code)=", tranCode.toLowerCase());
        List<TranDef> list = this.selectByExample(example);
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateTran(TranDef tranDef) {
        tranDef.setModifyTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        this.updateNotNull(tranDef);
    }
}
