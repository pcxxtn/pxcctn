package com.fudian.mid.transet.dao;

import com.fudian.mid.common.config.MyMapper;
import com.fudian.mid.transet.domain.TranCodeConv;
import com.fudian.mid.transet.domain.TranPkg;

public interface TranCodeConvMapper extends MyMapper<TranCodeConv> {
    void deleteTranCodeConv(TranCodeConv tranCodeConv);

    void updateTranCodeConv(TranCodeConv tranCodeConv);
}