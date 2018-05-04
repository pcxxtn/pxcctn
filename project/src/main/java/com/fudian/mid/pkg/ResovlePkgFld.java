package com.fudian.mid.pkg;

import java.util.List;

public interface ResovlePkgFld {

    /**
     *
     * @param Chnlno 渠道号
     * @param param 由flag判断param为报文格式号datapkgno或外部交易码fTranCode
     * @param flag 值为0-根据渠道号ChnlNo和外部交易码fTranCode获取解包报文格式字段
     *                  1-根据渠道号ChnlNo和外部交易码fTranCode获取打包报文格式字段
     *                  2-格局渠道号chnlno和报文格式号datapkgno获取报文格式字段
     * @return List
     */
    List<FldBean> GetPkgFld(String Chnlno,String param,String flag);
}
