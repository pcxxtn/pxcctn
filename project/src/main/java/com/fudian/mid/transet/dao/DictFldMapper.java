package com.fudian.mid.transet.dao;

import com.fudian.mid.common.config.MyMapper;
import com.fudian.mid.transet.domain.DictFld;

import java.util.List;
import java.util.Map;

public interface DictFldMapper extends MyMapper<DictFld> {
    List<Map> findDictFld(DictFld dictFld);
}