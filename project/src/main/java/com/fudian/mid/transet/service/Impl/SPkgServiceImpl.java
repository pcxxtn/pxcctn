package com.fudian.mid.transet.service.Impl;

import com.fudian.mid.common.service.impl.BaseService;
import com.fudian.mid.common.util.DateUtil;
import com.fudian.mid.transet.dao.SPkgMapper;
import com.fudian.mid.transet.domain.SPkg;
import com.fudian.mid.transet.service.SPkgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@Service("spkgService")
public class SPkgServiceImpl extends BaseService<SPkg> implements SPkgService{

    @Autowired
    SPkgMapper sPkgMapper;

    @Override
    public List<SPkg> findSPkg(SPkg sPkg) {
        return this.sPkgMapper.findSPkg(sPkg);
    }

    @Override
    public void deleteSPkgs(String datapkgnos) {
        List<String> list = Arrays.asList(datapkgnos.split(","));
        this.batchDelete(list, "datapkgno", SPkg.class);
    }

    @Override
    public int updateSPkg(SPkg sPkg) {
        return this.sPkgMapper.updateSPkg(sPkg);
    }

    @Override
    public int getMaxKey(String chnlno) {
        return this.sPkgMapper.getMaxKey(chnlno);
    }

}
