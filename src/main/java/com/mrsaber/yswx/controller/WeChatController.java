package com.mrsaber.yswx.controller;

import com.mrsaber.yswx.mapper.UserMapper;
import com.mrsaber.yswx.model.Message;
import com.mrsaber.yswx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("we")
public class WeChatController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/toBind.do")
    public String toBind(String openId)
    {
        return null;
    }

    @RequestMapping("/updateTel.do")
    public Message updateTel(Integer id,String tel)
    {
        Message message = new Message();
        try {
            userMapper.updateUserTel(tel,id);
        } catch (Exception e) {
            message.setCode(0);
            message.setMsg(e.getMessage());
            e.printStackTrace();
        }
        message.setCode(0);
        message.setMsg("更新成功！");
        return message;
    }

    @RequestMapping("/addNoCheckUser.do")
    public Message addNoCheckUser(User user)
    {
        Message message = new Message();
        try {
            userMapper.addNoCheckUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            message.setCode(0);
            message.setMsg(e.getMessage());
            return message;
        }
        message.setCode(1);
        message.setMsg("已提交申请");
        return message;
    }
}

/**
 * 今天计划：
 * 1.完善登录逻辑！
 */