package com.fudian.mid.job.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.fudian.mid.common.annotation.Log;
import com.fudian.mid.common.controller.BaseController;
import com.fudian.mid.common.domain.QueryRequest;
import com.fudian.mid.common.domain.ResponseBo;
import com.fudian.mid.common.util.FileUtils;
import com.fudian.mid.job.domain.JobLog;
import com.fudian.mid.job.service.JobLogService;

@Controller
public class JobLogController extends BaseController {

	@Autowired
	private JobLogService jobLogService;

	@RequestMapping("jobLog")
	public String index() {
		return "job/log/log";
	}

	@RequestMapping("jobLog/list")
	@ResponseBody
	public Map<String, Object> jobLogList(QueryRequest request, JobLog log) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		List<JobLog> list = this.jobLogService.findAllJobLogs(log);
		PageInfo<JobLog> pageInfo = new PageInfo<JobLog>(list);
		return getDataTable(pageInfo);
	}

	@Log("删除调度日志")
	@RequiresPermissions("jobLog:delete")
	@RequestMapping("jobLog/delete")
	@ResponseBody
	public ResponseBo deleteJobLog(String ids) {
		try {
			this.jobLogService.deleteBatch(ids);
			return ResponseBo.ok("删除调度日志成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("删除调度日志失败，请联系网站管理员！");
		}
	}

	@RequestMapping("jobLog/excel")
	@ResponseBody
	public ResponseBo jobLogExcel(JobLog jobLog) {
		try {
			List<JobLog> list = this.jobLogService.findAllJobLogs(jobLog);
			return FileUtils.createExcelByPOIKit("调度日志表", list, JobLog.class);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("导出Excel失败，请联系网站管理员！");
		}
	}

	@RequestMapping("jobLog/csv")
	@ResponseBody
	public ResponseBo jobLogCsv(JobLog jobLog) {
		try {
			List<JobLog> list = this.jobLogService.findAllJobLogs(jobLog);
			return FileUtils.createCsv("调度日志表", list, JobLog.class);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("导出Csv失败，请联系网站管理员！");
		}
	}
}
