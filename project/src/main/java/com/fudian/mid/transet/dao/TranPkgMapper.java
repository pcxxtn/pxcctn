package com.fudian.mid.transet.dao;

import com.fudian.mid.common.config.MyMapper;
import com.fudian.mid.transet.domain.TranPkg;

import java.util.List;
import java.util.Map;

public interface TranPkgMapper extends MyMapper<TranPkg> {
    List<Map> findTranPkg(TranPkg tranPkg);
    void deleteTranPkg(TranPkg tranPkg);
    void updateTranPkg(TranPkg tranPkg);
}