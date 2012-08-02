package com.test;

import com.taobao.top.xbox.threadpool.Job;

public class MyJob implements Job {
    String key;
    int id;
    int timeOut;
    
    public MyJob(String key, int id, int timeOut) {
        super();
        this.key = key;
        this.id = id;
        this.timeOut = timeOut;
    }


    @Override
    public void run() {
        System.out.println("job id: " + id);

    }

    @Override
    public String getKey() {
        return key;
    }


    @Override
    public long getTimeOut() {
       return timeOut;
    }


    @Override
    public void setTimeOut(long timeout) {
        this.timeOut = timeOut;

    }
    

}
