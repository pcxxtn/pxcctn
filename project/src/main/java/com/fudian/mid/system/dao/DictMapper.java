package com.fudian.mid.system.dao;

import com.fudian.mid.common.config.MyMapper;
import com.fudian.mid.system.domain.Dict;

import java.util.List;

public interface DictMapper extends MyMapper<Dict> {

    List findDictBytbname(Dict dict);
}