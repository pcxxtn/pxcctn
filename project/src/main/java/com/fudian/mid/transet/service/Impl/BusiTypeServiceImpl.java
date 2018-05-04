package com.fudian.mid.transet.service.Impl;

import com.fudian.mid.common.service.impl.BaseService;
import com.fudian.mid.transet.dao.BusiTypeMapper;
import com.fudian.mid.transet.domain.BusiType;
import com.fudian.mid.transet.service.BusiTypeService;
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

@Service("BusiTypeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BusiTypeServiceImpl extends BaseService<BusiType> implements BusiTypeService {
    @Autowired
    BusiTypeMapper busiTypeMapper;

    @Override
    public List<Map> findBusiType(BusiType busiType) {
        return this.busiTypeMapper.findBusiType(busiType);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteBusiType(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list, "busitype", BusiType.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int addBusiType(BusiType busiType) {
        busiType.setCtime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        busiType.setMtime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        busiType.setCurdate(DateFormatUtils.format(new Date(), "yyyyMMdd"));
        busiType.setLastdate(DateFormatUtils.format(new Date(), "yyyyMMdd"));
        busiType.setLastbatchdate(DateFormatUtils.format(new Date(), "yyyyMMdd"));
        busiType.setTranbegintime(DateFormatUtils.format(new Date(), "yyyyMMdd"));
        busiType.setTranendtime(DateFormatUtils.format(new Date(), "yyyyMMdd"));
        this.busiTypeMapper.insert(busiType);
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int updateBusiType(BusiType busiType) {
        busiType.setMtime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        this.updateNotNull(busiType);
        return 0;
    }

    @Override
    public BusiType findByPrimary(String busitype) {
        Example example = new Example(BusiType.class);
        example.createCriteria().andCondition("lower(busitype)=", busitype.toLowerCase());
        List<BusiType> list = this.selectByExample(example);
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
