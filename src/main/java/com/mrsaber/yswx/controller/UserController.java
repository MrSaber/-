package com.mrsaber.yswx.controller;

import com.mrsaber.yswx.mapper.UserMapper;
import com.mrsaber.yswx.model.Message;
import com.mrsaber.yswx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    /**
     * 同意用户申请
     * @param id
     * @return
     */
    @RequestMapping("/allowBind.do")
    public Message allowBind(Integer id)
    {
        Message re = new Message();
        try {
            userMapper.allowBind(id);
        } catch (Exception e) {
            e.printStackTrace();
            re.setMsg(e.getMessage());
            re.setCode(0);
            return re;
        }
        re.setMsg("操作成功");
        re.setCode(1);
        return  re;
    }

    /**
     * 拒绝用户申请
     * @param id
     * @return
     */
    @RequestMapping("/rejectBind.do")
    public Message rejectBind(Integer id)
    {
        Message re = new Message();
        try {
            userMapper.rejectBind(id);
        } catch (Exception e) {
            e.printStackTrace();
            re.setMsg(e.getMessage());
            re.setCode(0);
            return re;
        }
        re.setMsg("操作成功");
        re.setCode(1);
        return  re;
    }

    @RequestMapping("/getNoCheckUser.do")
    public List<User> getNoCheckUser()
    {
        return userMapper.getNoCheckUser();
    }

    @RequestMapping("/getById.do")
    public User getById(Integer id)
    {
        return userMapper.getUserById(id);
    }

    @RequestMapping("/updateById.do")
    public Message updateById(User user)
    {
        Message re = new Message();
        try {
            userMapper.updateById(user);
        } catch (Exception e) {
            e.printStackTrace();
            re.setMsg(e.getMessage());
            re.setCode(0);
            return re;
        }
        re.setMsg("操作成功");
        re.setCode(1);
        return  re;
    }

    @RequestMapping("/delById.do")
    public Message delById(Integer id)
    {
        Message re = new Message();
        try {
            userMapper.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            re.setMsg(e.getMessage());
            re.setCode(0);
            return re;
        }
        re.setMsg("操作成功");
        re.setCode(1);
        return  re;
    }
}
