package com.mrsaber.yswx.controller;

import com.mrsaber.yswx.mapper.BuilderMapper;
import com.mrsaber.yswx.mapper.UserMapper;
import com.mrsaber.yswx.model.Builder;
import com.mrsaber.yswx.model.Message;
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
    @Autowired
    private BuilderMapper builderMapper;

    @RequestMapping("/getAllByOfId.do")
    public List<User> getAllInfo(Integer ofId)
    {
        return userMapper.getAllByOf(ofId);
    }
    @RequestMapping("/getAllByOfIdAndType.do")
    public List<User> getAllInfoAndType(Integer ofId,Integer type)
    {
        return userMapper.getAllByOfAndType(ofId,type);
    }
    @RequestMapping("/getAllOf.do")
    public List<Builder> getAllOf()
    {
        return userMapper.getAllOf(2);
    }

    @RequestMapping("/gerAllOfCa.do")
    public List<Builder> getAllOfCa()
    {
        return userMapper.getAllOf(1);
    }


    /**
     * 添加食堂
     * @param builder
     * @return
     */
    @RequestMapping("/addCa.do")
    public Message addCa(Builder builder)
    {
        Message message = new Message();
        try {
            builderMapper.addCa(builder);
        } catch (Exception e) {
            e.printStackTrace();
            message.setCode(0);
            message.setMsg(e.getMessage());
            return message;
        }
        message.setMsg("操作成功！");
        message.setCode(1);
        return message;
    }

}
