package com.mrsaber.yswx.mapper;

import com.mrsaber.yswx.model.Builder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BuilderMapper {
    /**
     * 添加食堂信息
     * @param builder
     */
    @Insert("INSERT INTO `yswxpg`.`wx_office` (`of_name`, `of_type`, `of_other`, `of_address`, `of_tel`) VALUES (#{of_name}, '1', #{of_other}, #{of_address}, #{of_tel});")
    void addCa(Builder builder);
    /**
     * 获得食堂、主任、处长的信息！
     * @return
     */
    @Select("SELECT * FROM wx_office WHERE of_type=1 OR of_type=6 OR of_type=4;")
    List<Builder> getAllCa();

    /**
     * 获得所有维修单位的信息！
     * @return
     */
    @Select("SELECT * FROM wx_office WHERE of_type=2;")
    List<Builder> getAllBuilder();
    /**
     * 获得指定Builder
     */
    @Select("SELECT * FROM wx_office WHERE of_id = #{ofId}")
    Builder getById(Integer id);

}
