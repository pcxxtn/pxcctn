package com.fudian.mid.transet.service;

import com.fudian.mid.common.service.IService;
import com.fudian.mid.transet.domain.DictFld;

import java.util.List;
import java.util.Map;


public interface DictFldService extends IService<DictFld> {

    List<Map> findDictFld(Map map);

    int save(DictFld dictFld);

    void deleteDictFld(String ids);

    DictFld findById(Short dictFld);

    DictFld findByDictName(String dictName);
}
