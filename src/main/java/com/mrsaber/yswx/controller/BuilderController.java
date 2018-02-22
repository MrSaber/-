package com.mrsaber.yswx.controller;

import com.mrsaber.yswx.mapper.UserMapper;
import com.mrsaber.yswx.model.Builder;
import com.mrsaber.yswx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("builder")
public class BuilderController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/getAllByOfId.do")
    public List<User> getAllInfo(Integer ofId)
    {
       return userMapper.getAllByOf(ofId);
    }

    @RequestMapping("/getAllOf.do")
    public List<Builder> getAllOf()
    {
        return userMapper.getAllOf(2);
    }

}
