package com.fudian.mid.system.service;

import java.util.List;

import com.fudian.mid.common.service.IService;
import com.fudian.mid.system.domain.Dict;

public interface DictService extends IService<Dict> {

	List<Dict> findAllDicts(Dict dict);

	Dict findById(Long dictId);

	void addDict(Dict dict);

	void deleteDicts(String dictIds);

	void updateDict(Dict dicdt);

	 List findDictBytbname(Dict dict);
}
