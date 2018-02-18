package com.mrsaber.yswx.controller;

import com.mrsaber.yswx.mapper.UserMapper;
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

    @RequestMapping("/addNoCheckUser.do")
    public String addNoCheckUser(User user)
    {
      return null;
    }
}
