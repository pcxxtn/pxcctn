package com.fudian.mid.transet.dao;

import com.fudian.mid.common.config.MyMapper;
import com.fudian.mid.transet.domain.BusiType;

import java.util.List;
import java.util.Map;

public interface BusiTypeMapper extends MyMapper<BusiType> {
    List<Map> findBusiType(BusiType busiType);

}