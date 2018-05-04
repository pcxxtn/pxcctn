package com.fudian.mid.transet.service;

import com.fudian.mid.common.service.IService;
import com.fudian.mid.transet.domain.CommStruct;

import java.util.List;

public interface CommStructService extends IService<CommStruct> {

    /*
     *根据渠道号chnlno和通讯结构号commstructno来查询通讯结构表
     */
    List findCommStr(CommStruct commStruct);

    /*
     * 根据渠道号chnlno和通讯结构号commstructno删除通讯结构
     * 删除前应该先检查该通讯结构是否仍在使用
     * 检查T_SYS_PKG报文格式表
     */
    int deleteCommStruct(CommStruct commStruct);

    /*
     *新增通讯结构
     */
    int saveCommStruct(CommStruct commStruct);

    /*
     *根据渠道号和通讯结构号修改通讯结构
     */
    int updateCommStruct(CommStruct commStruct);
}
