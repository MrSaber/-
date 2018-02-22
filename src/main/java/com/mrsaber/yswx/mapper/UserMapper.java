package com.mrsaber.yswx.mapper;


import com.mrsaber.yswx.model.Builder;
import com.mrsaber.yswx.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    //更新用户基本信息
    @Update("UPDATE `yswxpg`.`wx_user` SET  `user_name`=#{user_name}, `user_password`=#{user_password},`user_realname`=#{user_realname}, `user_tel`=#{user_tel} WHERE (`user_id`=#{user_id});")
    void updateById(User user);

    //删除用户
    @Delete("DELETE FROM wx_user WHERE user_id=#{id}")
    void deleteById(Integer id);

    //根据ID确定用户
    @Select("SELECT * FROM wx_user WHERE user_id = #{id}")
    User getUserById(Integer id);

    //根据账户密码确定用户
    @Select("SELECT * FROM wx_user WHERE user_name=#{username} and user_password=#{password};")
    User getUser(@Param("username")String username,@Param("password")String password);

    //根据OPENID确定用户
    @Select("SELECT * FROM wx_user WHERE user_openId=#{openId};")
    User getUserByWe(String openId);

    //添加未审核用户
    @Insert("INSERT INTO `yswxpg`.`wx_user` (`user_name`, `user_password`, `user_type`, `user_realname`, `user_office`, `user_openId`, `user_status`) VALUES (#{user_name}, #{user_password}, #{user_type}, #{user_realname}, #{user_office}, #{user_openId}, '0');")
    void addNoCheckUser(User user);

    //更新账户的联系方式
    @Update("UPDATE `yswxpg`.`wx_user` SET `user_tel`=#{tel} WHERE (`user_id`=#{id});")
    void updateUserTel(@Param("tel")String tel,@Param("id")Integer id);

    //根据类型获得各种用户信息
    @Select("SELECT * FROM wx_user WHERE user_office = #{type}")
    List<User> getAllByOf(Integer type);

    //获得所有的维修单位
    @Select("SELECT * FROM wx_office WHERE of_type=#{type}")
    List<Builder> getAllOf(Integer type);

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
