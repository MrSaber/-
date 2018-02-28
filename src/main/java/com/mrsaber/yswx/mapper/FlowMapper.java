package com.mrsaber.yswx.mapper;

import com.mrsaber.yswx.model.Flow;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FlowMapper {

    /**
     * 获得维修单位的任务列表
     * @param id
     * @return
     */
    @Select("SELECT * FROM wx_flow WHERE flow_builderId =#{id} AND flow_status<>14 AND flow_status<>15")
    List<Flow> getCurListByWXDWId(Integer id );
    /**
     * 添加流程单
     * @param flow
     */
    @Insert("INSERT INTO `yswxpg`.`wx_flow` (`flow_id`, `flow_date`, `flow_status`,`flow_userId`,`flow_ofId`) VALUES (#{flow_id}, #{flow_date}, 1,#{flow_userId}),#{flow_ofId}")
    void add(Flow flow);
    /**
     * 获得各自食堂各状态的维修任务
     * @param type XXX 类型
     * @param office XXX 食堂编号
     * @return
     */
    @Select("SELECT * FROM wx_flow WHERE flow_status =#{type} and flow_ofId = #{office}")
    List<Flow> getListByStatusAndOf(@Param("type") Integer type,@Param("office") Integer office);

    /**
     * 获得各自食堂各状态的维修任务
     * @param office XXX 食堂编号
     * @return
     */
    @Select("SELECT * FROM wx_flow WHERE flow_ofId = #{office}")
    List<Flow> getListByOfId(@Param("office") Integer office);

    /**
     * 获得具体的FLOW信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM wx_flow WHERE flow_id =#{id}")
    Flow get(String id);

    /**
     * 领导和管理员信息
     * @return
     */
    @Select("SELECT * FROM wx_flow ")
    List<Flow> getAll();


    @Select("SELECT * FROM wx_flow WHERE flow_builderId =#{type}")
    List<Flow> getAllByBuilderId(Integer type);

    @Select("SELECT * FROM wx_flow WHERE flow_status =#{type}")
    List<Flow> getListByStatus(Integer type);

    @Update("UPDATE wx_flow SET flow_status = #{value} WHERE flow_id = #{key}")
    void approval(@Param("key")String key,@Param("value")Integer value);

    @Update("UPDATE `yswxpg`.`wx_flow` SET `flow_status`=#{value}, `flow_whyfail`=#{why} WHERE `flow_id`=#{key};")
    void noApproval(@Param("key")String key,@Param("value")Integer value,@Param("why")String why);

    @Update("UPDATE wx_flow SET flow_status = #{status} WHERE flow_id = #{id}")
    Integer selectFlowTo(@Param("id") String id,@Param("status") Integer status);

    @Update("UPDATE `yswxpg`.`wx_flow` SET `flow_remark`=#{flow_remark},`flow_status`=14, `flow_score`=#{flow_score} WHERE `flow_id`=#{flow_id};")
    void raty(Flow flow);
}