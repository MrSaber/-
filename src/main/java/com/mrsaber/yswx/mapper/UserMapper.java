package com.mrsaber.yswx.mapper;


import com.mrsaber.yswx.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM wx_user WHERE user_name=#{username} and user_password=#{password};")
    User getUser(@Param("username")String username,@Param("password")String password);

    @Select("SELECT * FROM wx_user WHERE user_openId=#{openId};")
    User getUserByWe(String openId);

    @Insert("INSERT INTO `yswxpg`.`wx_user` (`user_name`, `user_password`, `user_type`, `user_realname`, `user_office`, `user_openId`, `user_status`) VALUES (#{user_name}, #{user_password}, #{user_type}, #{user_realname}, #{user_office}, #{user_openId}, '0');")
    void addNoCheckUser(User user);

    @Update("UPDATE `yswxpg`.`wx_user` SET `user_tel`=#{tel} WHERE (`user_id`=#{id});")
    void updateUserTel(@Param("tel")String tel,@Param("id")Integer id);

    @Select("SELECT * FROM shzu.shzu_user,shzu_office WHERE shzu_user.user_office=of_id AND user_role =#{type};")
    List<User> getALL(Integer type);

    @Insert("INSERT INTO `shzu`.`shzu_user` (`user_name`, `user_password`, `user_role`, `user_other`, `user_office`, `user_permit`) VALUES (#{user_name}, #{user_password}, '1', '1', #{user_office}, '1');")
    void addU(User user);

    @Insert("INSERT INTO `shzu`.`shzu_user` (`user_name`, `user_password`, `user_role`, `user_other`, `user_office`, `user_permit`) VALUES (#{user_name}, #{user_password}, '3', #{user_other}, #{user_office}, '1');")
    void addBM(User user);


    @Update("UPDATE `shzu`.`shzu_user` SET `user_name`=#{user_name}, `user_password`=#{user_password} WHERE `user_id`=#{user_id};")
    void update(User user);

    @Delete("DELETE FROM `shzu`.`shzu_user` WHERE `user_id`=#{id};")
    void del(Integer id);

    @Select("SELECT of_permit FROM academy WHERE of_id=#{id}")
    Integer isAllow(Integer id);
}
