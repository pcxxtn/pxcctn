package com.fudian.mid.pkg.dao;

import com.fudian.mid.common.config.MyMapper;
import com.fudian.mid.pkg.FldBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataPkgMapper extends MyMapper<FldBean> {

    List findUnPackFld(@Param("chnlno")String chnlno, @Param("ftrancode")String ftrancode);
    List findPackFld( @Param("chnlno") String chnlno, @Param("ftrancode")String ftrancode);
    List findCommNameByPkgNo(@Param("chnlno") String chnlno,@Param("datapkgno") String datapkgno);
}
