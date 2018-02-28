package com.mrsaber.yswx.mapper;

import com.mrsaber.yswx.model.TaskForm;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TaskMapper {
    @Insert("INSERT INTO `yswxpg`.`wx_fault` (`fault_id`, `fault_flow_id`, `fault_info`, `fault_context`) VALUES (#{fault_id}, #{fault_flow_id}, #{fault_info}, #{fault_context});")
    void addForm(TaskForm form);

    @Select("SELECT * FROM wx_fault WHERE fault_flow_id=#{id}")
    List<TaskForm> get(String id);

    @Select("SELECT * FROM wx_fault WHERE fault_id=#{fault_id}" )
    TaskForm getForm(String fault_id);
}
