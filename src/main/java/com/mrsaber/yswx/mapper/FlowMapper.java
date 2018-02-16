package com.mrsaber.yswx.mapper;

import com.mrsaber.yswx.model.Flow;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FlowMapper {
    @Insert("INSERT INTO `yswxpg`.`wx_flow` (`flow_id`, `flow_date`, `flow_status`) VALUES (#{flow_id}, #{flow_date}, 1)")
    void add(Flow flow);

    @Select("SELECT * FROM wx_flow WHERE flow_id =#{id}")
    Flow get(String id);

    @Select("SELECT * FROM wx_flow ")
    List<Flow> getAll();

    @Select("SELECT * FROM wx_flow WHERE flow_status =#{type}")
    List<Flow> getListByStatus(Integer type);

    @Update("UPDATE wx_flow SET flow_status = #{value} WHERE flow_id = #{key}")
    void approval(@Param("key")String key,@Param("value")Integer value);

    @Update("UPDATE `yswxpg`.`wx_flow` SET `flow_status`=#{value}, `flow_whyfail`=#{why} WHERE `flow_id`=#{key};")
    void noApproval(@Param("key")String key,@Param("value")Integer value,@Param("why")String why);

    @Update("UPDATE wx_flow SET flow_status = #{status} WHERE flow_id = #{id}")
    Integer selectFlowTo(@Param("id") String id,@Param("status") Integer status);

    @Update("UPDATE `yswxpg`.`wx_flow` SET `flow_remark`=#{flow_remark}, `flow_score`=#{flow_score} WHERE `flow_id`=#{flow_id};")
    void raty(Flow flow);
}