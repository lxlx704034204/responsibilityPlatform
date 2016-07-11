package com.orbit.etl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * etl job封装成Quartz Job供调度进行数据同步
 */
public class EtlJob implements Job {
  private IEtlService etlService;

  public EtlJob(IEtlService etlService) {
    this.etlService = etlService;
  }

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {


    etlService.execute();

  }
}
