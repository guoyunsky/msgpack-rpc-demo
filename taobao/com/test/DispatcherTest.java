package com.test;

import com.taobao.top.xbox.threadpool.JobDispatcher;
import com.taobao.top.xbox.threadpool.JobThreadWeightModel;

public class DispatcherTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        JobDispatcher jobDispatcher = new JobDispatcher();
        JobThreadWeightModel[] jobThreadWeightModels = new JobThreadWeightModel[3];
        
        try {
            for(int i= 0 ; i < jobThreadWeightModels.length ; i++)
            {
                jobThreadWeightModels[i] = new JobThreadWeightModel();
                jobThreadWeightModels[i].setKey("queue:" + i);
            }

            //模型中tag:0保留5个资源，tag:1保留5个，tag:2最大5个资源。
            jobThreadWeightModels[0].setType(JobThreadWeightModel.WEIGHT_MODEL_LEAVE);
            jobThreadWeightModels[0].setValue(5);
            
            jobThreadWeightModels[1].setType(JobThreadWeightModel.WEIGHT_MODEL_LEAVE);
            jobThreadWeightModels[1].setValue(7);
            
            jobThreadWeightModels[2].setType(JobThreadWeightModel.WEIGHT_MODEL_LIMIT);
            jobThreadWeightModels[2].setValue(5);
            
            jobDispatcher.setJobThreadWeightModel(jobThreadWeightModels);
            jobDispatcher.setCorePoolSize(50);
            jobDispatcher.setMaximumPoolSize(100);
            jobDispatcher.setMaximumQueueSize(500);
            jobDispatcher.init();
            
            for(int i = 1 ; i <= 10; i++)
            {
                MyJob defaultjob = new MyJob(null, i*10, 1000);
                MyJob leavejob = new MyJob("queue:1", i*11, 1000);
                MyJob limitjob = new MyJob("queue:2", i*12, 1000);
                jobDispatcher.execute(leavejob);
                jobDispatcher.execute(limitjob);
                jobDispatcher.execute(defaultjob);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            jobDispatcher.stopDispatcher();
        }

    }

}
