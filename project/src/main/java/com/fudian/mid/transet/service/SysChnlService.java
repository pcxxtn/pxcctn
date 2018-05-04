package com.fudian.mid.transet.service;

import com.fudian.mid.common.service.IService;
import com.fudian.mid.system.domain.Dict;
import com.fudian.mid.transet.domain.DictFld;
import com.fudian.mid.transet.domain.SysChnl;

import java.util.List;
import java.util.Map;


public interface SysChnlService extends IService<SysChnl> {

    SysChnl findById(String  chnlno);

    void addChnl(SysChnl sysChnl);

    void deleteChnl(String chnlIds);

    void updateChnl(SysChnl sysChnl);

    List getChnlOption();

}
