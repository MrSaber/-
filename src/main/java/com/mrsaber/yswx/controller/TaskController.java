package com.mrsaber.yswx.controller;

import com.mrsaber.yswx.mapper.ImageMapper;
import com.mrsaber.yswx.mapper.TaskMapper;
import com.mrsaber.yswx.model.Image;
import com.mrsaber.yswx.model.TaskForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping(value = "task")
public class TaskController {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private HttpSession session;

    /**
     * 添加故障表单
     * @param form
     * @return
     */
    @RequestMapping("/addForm.do")
    public String addForm(TaskForm form)
    {

        String flowId = (String) session.getAttribute("flowId");
        form.setFlow_id(flowId);
        try {
            form.setFault_id((String) session.getAttribute("TASK_ID"));
            taskMapper.addForm(form);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "OK";
    }

    /**
     * 添加故障照片
     * @param image
     * @return
     */
    @RequestMapping("/addImg.do")
    public Boolean addImg(Image image)
    {
        String img_prefix= (String) session.getAttribute("TASK_ID");
        String img_suffix = String.valueOf(System.currentTimeMillis());
        img_suffix = img_suffix.substring(img_suffix.length()-6);
        String img_path = "E:\\tmp\\"+img_prefix+img_suffix+".jpg";
        try {
            imageMapper.add(img_path,img_prefix);
            image.getFile().transferTo(new File(img_path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
