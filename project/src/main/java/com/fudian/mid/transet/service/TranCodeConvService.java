package com.fudian.mid.transet.service;

import com.fudian.mid.common.service.IService;
import com.fudian.mid.transet.domain.TranCodeConv;

public interface TranCodeConvService extends IService<TranCodeConv> {
    TranCodeConv findByPrimary(String chnlNo,String fTranCode);

    void deleteTranCodeConv(String chnlNo,String fTranCode);

    int addTranCodeConv(TranCodeConv tranCodeConv);

    void updateTranCodeConv(TranCodeConv tranCodeConv);
}
