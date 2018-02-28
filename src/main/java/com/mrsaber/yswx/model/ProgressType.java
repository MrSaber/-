package com.mrsaber.yswx.model;

public interface ProgressType {
    static final Integer PROGRESS_WaitForCZ = 16; //等待处长主任
    static final Integer PROGRESS_WaitForZR = 5; //等待主任审核
    static final Integer PROGRESS_WaitForFGLD = 3; //等待主任审核
    static final Integer PROGRESS_WaitForSG = 1; //等待主任审核
    static final Integer PROGRESS_FLOW_SUCCESS = 15; // 任务成功
    static final Integer PROGRESS_FLOW_FAILURE = 14; // 任务失败
}
