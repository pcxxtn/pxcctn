package com.fudian.mid.job.service;

import java.util.List;

import com.fudian.mid.common.service.IService;
import com.fudian.mid.job.domain.JobLog;

public interface JobLogService extends IService<JobLog>{

	List<JobLog> findAllJobLogs(JobLog jobLog);

	void saveJobLog(JobLog log);
	
	void deleteBatch(String jobLogIds);
}
