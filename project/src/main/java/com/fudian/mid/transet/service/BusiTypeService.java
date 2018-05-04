package com.fudian.mid.transet.service;

import com.fudian.mid.common.service.IService;
import com.fudian.mid.transet.domain.BusiType;

import java.util.List;
import java.util.Map;

public interface BusiTypeService  extends IService<BusiType>{
    List<Map> findBusiType(BusiType busiType);
    void deleteBusiType(String ids);
    int addBusiType(BusiType busiType);
    int updateBusiType(BusiType busiType);

    BusiType findByPrimary(String busitype);
}
