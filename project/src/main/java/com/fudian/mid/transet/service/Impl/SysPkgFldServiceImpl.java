package com.fudian.mid.transet.service.Impl;

import com.fudian.mid.common.service.impl.BaseService;
import com.fudian.mid.transet.dao.CommFldMapper;
import com.fudian.mid.transet.dao.SysPkgFldMapper;
import com.fudian.mid.transet.domain.SysPkgFld;
import com.fudian.mid.transet.service.SysPkgFldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("spkgFldService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysPkgFldServiceImpl extends BaseService<SysPkgFld> implements SysPkgFldService{

    @Autowired
    SysPkgFldMapper sysPkgFldMapper;
    @Autowired
    CommFldMapper commFldMapper;

    @Override
    @Transactional(readOnly = false)
    public List<Map> spkgrFldList(SysPkgFld sysPkgFld) {
        String chnlno = sysPkgFld.getChnlno();
        String datapkgno = sysPkgFld.getDatapkgno();
        String commstructno = sysPkgFld.getCommstructno();
        List<Map> list = this.commFldMapper.findComFld(chnlno,datapkgno,commstructno);
        return list;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteSPkgFld(List datapkgfld) {
        this.sysPkgFldMapper.deleteSPkgFld(datapkgfld);
    }

    @Override
    public List<SysPkgFld> spkgFldList(SysPkgFld sysPkgFld) {
        return this.sysPkgFldMapper.findSPkgFld(sysPkgFld);
    }

    @Override
    public void addSPkgFld(List list) {
        this.sysPkgFldMapper.addSPkgFld(list);
    }

    @Override
    public Short getFldNo(String chnlno, String datapkgno) {
        return this.sysPkgFldMapper.getFldNo(chnlno,datapkgno);
    }

    @Override
    public int updateFld(SysPkgFld commFld) {
        return this.sysPkgFldMapper.updateFld(commFld);
    }


}
