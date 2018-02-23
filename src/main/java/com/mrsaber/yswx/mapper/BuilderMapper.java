package com.mrsaber.yswx.mapper;

import com.mrsaber.yswx.model.Builder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BuilderMapper {
    @Insert("INSERT INTO `yswxpg`.`wx_office` (`of_name`, `of_type`, `of_other`, `of_address`, `of_tel`) VALUES (#{of_name}, '1', #{of_other}, #{of_address}, #{of_tel});")
    void addCa(Builder builder);
}
