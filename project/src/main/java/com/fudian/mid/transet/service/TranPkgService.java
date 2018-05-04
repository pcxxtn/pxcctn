package com.fudian.mid.transet.service;

import com.fudian.mid.common.service.IService;
import com.fudian.mid.transet.domain.TranPkg;

import java.util.List;
import java.util.Map;

public interface TranPkgService extends IService<TranPkg> {

    void deleteTranPkg(String chnlNo,String fTranCode);

    int addTranPkg(TranPkg tranPkg);

    void updateTranPkg(TranPkg tranPkg);

    TranPkg findByPrimary(String chnlNo,String fTranCode);

    List<Map> findTranPkg(TranPkg tranPkg);

}
