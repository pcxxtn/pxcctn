package com.fudian.mid.transet.dao;

import com.fudian.mid.common.config.MyMapper;
import com.fudian.mid.transet.domain.SPkg;

import java.util.List;

public interface SPkgMapper extends MyMapper<SPkg> {
    List<SPkg> findSPkg(SPkg sPkg);

    int updateSPkg(SPkg sPkg);

    void insertsPkg(SPkg sPkg);

    int getMaxKey();
}