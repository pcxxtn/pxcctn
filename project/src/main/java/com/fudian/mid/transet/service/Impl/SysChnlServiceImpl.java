package com.fudian.mid.transet.service.Impl;

import com.fudian.mid.common.service.impl.BaseService;
import com.fudian.mid.system.dao.DictMapper;
import com.fudian.mid.system.domain.Dict;
import com.fudian.mid.transet.dao.DictFldMapper;
import com.fudian.mid.transet.dao.SysChnlMapper;
import com.fudian.mid.transet.domain.DictFld;
import com.fudian.mid.transet.domain.SysChnl;
import com.fudian.mid.transet.service.DictFldService;
import com.fudian.mid.transet.service.SysChnlService;
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

@Service("sysChnlService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysChnlServiceImpl extends BaseService<SysChnl> implements SysChnlService {

    @Autowired
    private SysChnlMapper sysChnlMapper;


    @Override
    public SysChnl findById(String chnlno) {
            return this.selectByKey(chnlno);
        }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addChnl(SysChnl sysChnl) {


        this.sysChnlMapper.insert(sysChnl);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteChnl(String chnlIds) {
        List<String> list = Arrays.asList(chnlIds.split(","));
        this.batchDelete(list, "chnlno", SysChnl.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateChnl(SysChnl sysChnl) {
        {
            this.updateNotNull(sysChnl);
        }
    }

    @Override
    public List getChnlOption() {
        Example example=new Example(SysChnl.class);
        Example.Criteria criteria= example.createCriteria();
        example.selectProperties("chnlno","chnlname");
        List list=this.mapper.selectByExample(example);
        return list;
    }
}
