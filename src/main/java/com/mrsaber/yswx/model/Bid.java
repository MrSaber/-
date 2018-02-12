package com.mrsaber.yswx.model;

public class Bid {
    private String bid_id;
    private String bid_flow_id;
    private Integer bid_user_id;
    private Double bid_timeprice;
    private Double bid_total;
    private Integer bid_status;
    //附加属性，方便关联查找
    private String user_office;

    public String getUser_office() {
        return user_office;
    }

    public void setUser_office(String user_office) {
        this.user_office = user_office;
    }

    public String getBid_id() {
        return bid_id;
    }

    public void setBid_id(String bid_id) {
        this.bid_id = bid_id;
    }

    public String getBid_flow_id() {
        return bid_flow_id;
    }

    public void setBid_flow_id(String bid_flow_id) {
        this.bid_flow_id = bid_flow_id;
    }

    public Integer getBid_user_id() {
        return bid_user_id;
    }

    public void setBid_user_id(Integer bid_user_id) {
        this.bid_user_id = bid_user_id;
    }

    public Double getBid_timeprice() {
        return bid_timeprice;
    }

    public void setBid_timeprice(Double bid_timeprice) {
        this.bid_timeprice = bid_timeprice;
    }

    public Double getBid_total() {
        return bid_total;
    }

    public void setBid_total(Double bid_total) {
        this.bid_total = bid_total;
    }

    public Integer getBid_status() {
        return bid_status;
    }

    public void setBid_status(Integer bid_status) {
        this.bid_status = bid_status;
    }


}
