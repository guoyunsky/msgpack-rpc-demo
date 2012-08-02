/**
 * 
 */
package com.taobao.top.xbox.threadpool;


import java.util.concurrent.FutureTask;

/**
 * @author fangweng
 * @email fangweng@taobao.com
 * @date 2011-7-14
 *
 */
public class JobFutureTask extends FutureTask<Object> 
		implements java.lang.Comparable<JobFutureTask>,Job{

	Job job;

	public JobFutureTask(Job job, Object result) {
		super(job, result);
		this.job = job;
	}

	@Override
	public int compareTo(JobFutureTask o) {
		
		return (int)(job.getTimeOut() - o.job.getTimeOut());
	}

	@Override
	public String getKey() {
		return job.getKey();
	}

	@Override
	public long getTimeOut() {
		return job.getTimeOut();
	}

	@Override
	public void setTimeOut(long timeout) {
		job.setTimeOut(timeout);
	}

}
