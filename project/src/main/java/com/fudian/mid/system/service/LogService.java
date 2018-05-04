package com.fudian.mid.system.service;

import java.util.List;

import com.fudian.mid.common.service.IService;
import com.fudian.mid.system.domain.SysLog;

public interface LogService extends IService<SysLog> {
	
	List<SysLog> findAllLogs(SysLog log);
	
	void deleteLogs(String logIds);
}
