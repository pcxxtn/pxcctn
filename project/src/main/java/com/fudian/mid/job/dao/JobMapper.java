package com.fudian.mid.job.dao;

import java.util.List;

import com.fudian.mid.common.config.MyMapper;
import com.fudian.mid.job.domain.Job;

public interface JobMapper extends MyMapper<Job> {
	
	List<Job> queryList();
}