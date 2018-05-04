package com.fudian.mid.transet.service.Impl;

import com.fudian.mid.common.service.impl.BaseService;
import com.fudian.mid.transet.dao.TranPkgMapper;
import com.fudian.mid.transet.domain.TranPkg;
import com.fudian.mid.transet.service.TranPkgService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.nio.file.attribute.FileTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("TranPkgService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TranPkgServiceImpl extends BaseService<TranPkg> implements TranPkgService{

@Autowired
    TranPkgMapper tranPkgMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteTranPkg(String chnlNo,String fTranCode){
        TranPkg tranPkg = new TranPkg();
        tranPkg.setChnlno(chnlNo);
        tranPkg.setFtrancode(fTranCode);
        this.tranPkgMapper.deleteTranPkg(tranPkg);
    }

    @Override
    public List<Map> findTranPkg(TranPkg tranPkg){
        return  this.tranPkgMapper.findTranPkg(tranPkg);
    }

    @Override
    public int addTranPkg(TranPkg tranPkg) {
//        tranPkg.setCreateTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") );
        tranPkg.setCtime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        this.tranPkgMapper.insert(tranPkg);
        return 0;
    }


    @Override
    public TranPkg findByPrimary(String chnlNo,String fTranCode) {

        Example example = new Example(TranPkg.class);
        example.createCriteria().andCondition("lower(chnlno)=", chnlNo.toLowerCase()).andCondition("lower(ftrancode)=", fTranCode.toLowerCase());
        List<TranPkg> list = this.selectByExample(example);
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateTranPkg(TranPkg tranPkg) {
        tranPkg.setMtime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//        this.updateNotNull(tranPkg);
        this.tranPkgMapper.updateTranPkg(tranPkg);
    }
}
