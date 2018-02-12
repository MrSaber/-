package com.mrsaber.yswx.model;

public class TaskForm {
    private String fault_id;
    private String fault_flow_id;
    private String fault_info;
    private String fault_context;

    public String getFault_id() {
        return fault_id;
    }

    public void setFault_id(String fault_id) {
        this.fault_id = fault_id;
    }

    public String getFlow_id() {
        return fault_flow_id;
    }

    public void setFlow_id(String flow_id) {
        this.fault_flow_id = flow_id;
    }

    public String getFault_info() {
        return fault_info;
    }

    public void setFault_info(String fault_info) {
        this.fault_info = fault_info;
    }

    public String getFault_context() {
        return fault_context;
    }

    public void setFault_context(String fault_context) {
        this.fault_context = fault_context;
    }

    @Override
    public String toString() {
        return "TaskForm{" +
                "fault_id='" + fault_id + '\'' +
                ", flow_id='" + fault_flow_id + '\'' +
                ", fault_info='" + fault_info + '\'' +
                ", fault_context='" + fault_context + '\'' +
                '}';
    }
}
