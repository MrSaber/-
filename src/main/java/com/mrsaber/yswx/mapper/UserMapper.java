package com.mrsaber.yswx.mapper;


import com.mrsaber.yswx.model.Builder;
import com.mrsaber.yswx.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    //允许用户绑定
    @Update("UPDATE wx_user SET user_status = 1 WHERE user_id=#{id}")
    void allowBind(Integer id);

    //删除未绑定用户
    @Delete("DELETE FROM wx_user WHERE user_status =0 AND user_id=#{id}")
    void rejectBind(Integer id);

    //更新用户基本信息
    @Update("UPDATE `yswxpg`.`wx_user` SET  `user_name`=#{user_name}, `user_password`=#{user_password},`user_realname`=#{user_realname}, `user_tel`=#{user_tel} WHERE (`user_id`=#{user_id});")
    void updateById(User user);

    //删除用户
    @Delete("DELETE FROM wx_user WHERE user_id=#{id}")
    void deleteById(Integer id);

    //根据ID确定用户
    @Select("SELECT * FROM wx_user,wx_office WHERE user_office=of_id AND user_id = #{id}")
    User getUserById(Integer id);

    //获得未审核用户信息
    @Select("SELECT * FROM wx_user,wx_office WHERE user_office=of_id AND user_status =0")
    List<User> getNoCheckUser();

    //根据账户密码确定用户
    @Select("SELECT * FROM wx_user WHERE user_name=#{username} and user_password=#{password} and user_status=1;")
    User getUser(@Param("username")String username,@Param("password")String password);

    //根据OPENID确定用户
    @Select("SELECT * FROM wx_user WHERE user_openId=#{openId} and user_status=1;")
    User getUserByWe(String openId);

    //添加未审核用户
    @Insert("INSERT INTO `yswxpg`.`wx_user` (`user_name`, `user_password`, `user_type`, `user_realname`, `user_office`, `user_openId`, `user_status`) VALUES (#{user_name}, #{user_password}, #{user_type}, #{user_realname}, #{user_office}, #{user_openId}, '0');")
    void addNoCheckUser(User user);

    //更新账户的联系方式
    @Update("UPDATE `yswxpg`.`wx_user` SET `user_tel`=#{tel} WHERE (`user_id`=#{id});")
    void updateUserTel(@Param("tel")String tel,@Param("id")Integer id);

    //根据类型获得各种用户信息
    @Select("SELECT * FROM wx_user WHERE user_office = #{office} and user_status=1")
    List<User> getAllByOf(Integer office);

    //根据类型和单位获得各种用户信息
    @Select("SELECT * FROM wx_user WHERE user_office =#{office} AND user_type=#{type} and user_status=1")
    List<User> getAllByOfAndType(@Param("office")Integer office,@Param("type")Integer type);

    //获得所有的维修单位
    @Select("SELECT * FROM wx_office WHERE of_type=#{type}")
    List<Builder> getAllOf(Integer type);

}
