package com.fudian.mid.transet.dao;

import com.fudian.mid.common.config.MyMapper;
import com.fudian.mid.transet.domain.DictFld;
import com.fudian.mid.transet.domain.SysChnl;

import java.util.List;
import java.util.Map;

public interface SysChnlMapper extends MyMapper<SysChnl> {

    List<Map> findSysChnl(SysChnl sysChnl);
}