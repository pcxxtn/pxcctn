package com.fudian.mid.transet.dao;

import com.fudian.mid.common.config.MyMapper;
import com.fudian.mid.transet.domain.TranDef;

import java.util.List;
import java.util.Map;

public interface TranDefMapper extends MyMapper<TranDef> {
    List<Map> findTran(TranDef tranDef);
}