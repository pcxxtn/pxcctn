package com.fudian.mid.transet.dao;

import com.fudian.mid.common.config.MyMapper;
import com.fudian.mid.transet.domain.CommFld;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommFldMapper extends MyMapper<CommFld> {
    int deleteByKey(CommFld commFld);

    List<Map> findComFld(@Param("chnlno") String chnlno,@Param("datapkgno") String datapkgno,@Param("commstructno") String commstructno);

    int insertCommFld(CommFld commFld);
}