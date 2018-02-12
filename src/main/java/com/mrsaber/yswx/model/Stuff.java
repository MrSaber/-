package com.mrsaber.yswx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stuff {
    private String stuff_name;
    private Integer stuff_number;
    private Double stuff_price;
    private String stuff_bid_id;

    public String getStuff_name() {
        return stuff_name;
    }

    public void setStuff_name(String stuff_name) {
        this.stuff_name = stuff_name;
    }

    public Integer getStuff_number() {
        return stuff_number;
    }

    public void setStuff_number(Integer stuff_number) {
        this.stuff_number = stuff_number;
    }

    public Double getStuff_price() {
        return stuff_price;
    }

    public void setStuff_price(Double stuff_price) {
        this.stuff_price = stuff_price;
    }

    public String getStuff_bid_id() {
        return stuff_bid_id;
    }

    public void setStuff_bid_id(String stuff_bid_id) {
        this.stuff_bid_id = stuff_bid_id;
    }
}
