package com.mrsaber.yswx.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Flow {
    private String flow_id="";

    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date flow_date;

    private Integer flow_status;
    private String flow_whyfail;
    private String flow_remark;
    private Integer flow_score;

    private Integer flow_userId;
    private Integer flow_ofId;
    private Integer flow_builderId;


    public Integer getFlow_userId() {
        return flow_userId;
    }

    public void setFlow_userId(Integer flow_userId) {
        this.flow_userId = flow_userId;
    }

    public Integer getFlow_ofId() {
        return flow_ofId;
    }

    public void setFlow_ofId(Integer flow_ofId) {
        this.flow_ofId = flow_ofId;
    }

    public Integer getFlow_builderId() {
        return flow_builderId;
    }

    public void setFlow_builderId(Integer flow_builderId) {
        this.flow_builderId = flow_builderId;
    }

    public String getFlow_remark() {
        return flow_remark;
    }

    public void setFlow_remark(String flow_remark) {
        this.flow_remark = flow_remark;
    }

    public Integer getFlow_score() {
        return flow_score;
    }

    public void setFlow_score(Integer flow_score) {
        this.flow_score = flow_score;
    }

    public String getFlow_id() {
        return flow_id;
    }

    public void setFlow_id(String flow_id) {
        this.flow_id = flow_id;
    }

    public Date getFlow_date() {
        return flow_date;
    }

    public void setFlow_date(Date flow_date) {
        this.flow_date = flow_date;
    }

    public Integer getFlow_status() {
        return flow_status;
    }

    public void setFlow_status(Integer flow_status) {
        this.flow_status = flow_status;
    }

    public String getFlow_whyfail() {
        return flow_whyfail;
    }

    public void setFlow_whyfail(String flow_whyfail) {
        this.flow_whyfail = flow_whyfail;
    }
}
