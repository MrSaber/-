package com.mrsaber.yswx.model;

import java.util.Date;

public class Flow {
    private String flow_id;
    private Date flow_date;
    private Integer flow_status;
    private String flow_whyfail;

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
