package com.fudian.mid.transet.service;

import com.fudian.mid.common.service.IService;
import com.fudian.mid.transet.domain.SPkg;

import java.util.List;


public interface SPkgService extends IService<SPkg> {
    List<SPkg> findSPkg(SPkg sPkg);

    void deleteSPkgs(String datapkgnos);

    int updateSPkg(SPkg sPkg);

    int getMaxKey();
}
