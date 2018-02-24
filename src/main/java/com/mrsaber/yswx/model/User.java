package com.mrsaber.yswx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private Integer user_id;
    private String user_name;
    private String user_password;
    private Integer user_type;
    private String user_office;
    private String user_realname;
    //
    private String user_openId;
    private Integer user_status;
    private String user_tel;
    //
    private String of_name;

    public String getOf_name() {
        return of_name;
    }

    public void setOf_name(String of_name) {
        this.of_name = of_name;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getUser_openId() {
        return user_openId;
    }

    public void setUser_openId(String user_openId) {
        this.user_openId = user_openId;
    }

    public Integer getUser_status() {
        return user_status;
    }

    public void setUser_status(Integer user_status) {
        this.user_status = user_status;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public Integer getUser_type() {
        return user_type;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    public String getUser_office() {
        return user_office;
    }

    public void setUser_office(String user_office) {
        this.user_office = user_office;
    }

    public String getUser_realname() {
        return user_realname;
    }

    public void setUser_realname(String user_realname) {
        this.user_realname = user_realname;
    }


}
