package com.fudian.mid.transet.dao;

import com.fudian.mid.common.config.MyMapper;
import com.fudian.mid.transet.domain.CommFld;
import com.fudian.mid.transet.domain.SysPkgFld;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPkgFldMapper extends MyMapper<SysPkgFld> {
    List<SysPkgFld> findSPkgFld(SysPkgFld sysPkgFld);

    void addSPkgFld(List list);

    Short getFldNo(@Param("chnlno") String chnlno,@Param("datapkgno") String datapkgno);

    int updateFld(SysPkgFld commFld);

    void deleteSPkgFld(List datapkgfld);
}