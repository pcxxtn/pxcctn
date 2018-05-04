package com.fudian.mid.transet.service.Impl;

import com.fudian.mid.common.domain.ResponseBo;
import com.fudian.mid.common.service.impl.BaseService;
import com.fudian.mid.transet.dao.DictFldMapper;
import com.fudian.mid.transet.domain.DictFld;
import com.fudian.mid.transet.service.DictFldService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("dictFldService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictFldServiceImpl extends BaseService<DictFld> implements DictFldService{

    @Autowired
    DictFldMapper dictFldMapper;

    @Override
    public List<Map> findDictFld(Map map) {
        return null;
    }

    @Override
    public int save(DictFld dictFld) {
        dictFld.setDictNo(Short.parseShort(this.getSequence(DictFld.SEQ)+""));
        this.dictFldMapper.insert(dictFld);
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int updateAll(DictFld dictFld) {
        int ret=this.updateNotNull(dictFld);
        return ret;
    }

    @Override
    public int updateNotNull(DictFld entity) {
        entity.setDictChgTime(DateFormatUtils.format(new Date(), "yyyyMMdd HHmmss"));
        int ret=this.mapper.updateByPrimaryKeySelective(entity);
        return ret;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteDictFld(String ids){
        List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list,"dictNo",DictFld.class);
    }

    @Override
    public DictFld findById(Short dictFld) {
        return this.selectByKey(dictFld);
    }

    @Override
    public DictFld findByDictName(String dictName) {
        Example example=new Example(DictFld.class);
        example.createCriteria().andCondition("lower(dict_name)=", dictName);
        List<DictFld> dictFlds=this.selectByExample(example);
        if (dictFlds.size() == 0) {
            return null;
        } else {
            return dictFlds.get(0);
        }
    }
}
