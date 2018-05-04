package com.fudian.mid.transet.service;

import com.fudian.mid.common.service.IService;
import com.fudian.mid.transet.domain.SysPkgFld;

import java.util.List;
import java.util.Map;


public interface SysPkgFldService extends IService<SysPkgFld> {
    List<Map> spkgrFldList(SysPkgFld sPkg);

    void deleteSPkgFld(List datapkgfldno);

    List<SysPkgFld> spkgFldList(SysPkgFld sysPkgFld);

    void addSPkgFld(List list);

    Short getFldNo(String chnlno, String datapkgno);

    int updateFld(SysPkgFld commFld);
}
