package com.fudian.mid.transet.service;

import com.fudian.mid.common.domain.ResponseBo;
import com.fudian.mid.common.service.IService;
import com.fudian.mid.transet.domain.CommFld;

import java.util.List;
import java.util.Map;

public interface CommFldService extends IService<CommFld> {
    int save(CommFld commFld);

    List<CommFld> findCommFld(CommFld commFld);

    CommFld findById(Short commFld);

    CommFld findByCommName(String commName);

    int updateCommFld(CommFld commFld);

    /*
     *@param ids 格式为chnlno|commstructno|commstructfldno,chnlno|commstructno|commstructfldno
     * 删除通讯字段前,先检查 报文格式明细表(T_SYS_PKG_FLD)中该字段是否仍在使用
     */
    ResponseBo deleteCommFld(String ids);
}
