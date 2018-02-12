package com.mrsaber.yswx.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.validation.constraints.Max;
import java.util.List;

@Mapper
public interface ImageMapper {
    @Insert("INSERT INTO `yswxpg`.`wx_image` (`image_path`, `image_fault_id`) VALUES (#{image_path}, #{image_fault_id});")
    void add(@Param("image_path")String image_path,@Param("image_fault_id") String image_fault_id);

    @Select("SELECT image_path FROM wx_image WHERE image_fault_id=#{fault_id}" )
    List<String> getPath(String fault_id);
}
