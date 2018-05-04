package com.fudian.mid.pkg;

import com.fudian.mid.common.service.impl.BaseService;
import com.fudian.mid.pkg.dao.DataPkgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("ResovlePkgFld")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true,rollbackFor = Exception.class)
public class ResolvePkgFldImpl extends BaseService<FldBean> implements ResovlePkgFld {
    @Autowired
    DataPkgMapper dataPkgMapper;

    @Override
    public List<FldBean> GetPkgFld(String chnlno, String ftrancode, String flag) {
        List list=new ArrayList();
        if("0".equals(flag))
            list=this.dataPkgMapper.findUnPackFld(chnlno,ftrancode); //渠道号和外部交易码
        else if("1".equals(flag))
            list=this.dataPkgMapper.findPackFld(chnlno,ftrancode); //渠道号和外部交易码
        else if("2".equals(flag))
            list=this.dataPkgMapper.findCommNameByPkgNo(chnlno,ftrancode); //渠道号和报文格式号
        return list;
    }
}
