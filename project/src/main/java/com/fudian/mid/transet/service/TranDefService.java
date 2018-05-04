package com.fudian.mid.transet.service;

import com.fudian.mid.common.service.IService;
import com.fudian.mid.transet.domain.TranDef;


public interface TranDefService extends IService<TranDef> {
    void deleteTran(String ids);

    int addTran(TranDef tranDef);

    TranDef findById(Short tranCode);

    void updateTran(TranDef tranDef);

    TranDef findByTranCode(String tranCode);

}
