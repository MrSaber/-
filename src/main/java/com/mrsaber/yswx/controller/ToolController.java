package com.mrsaber.yswx.controller;

import com.mrsaber.yswx.model.Image;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@CrossOrigin
@RequestMapping("tool")
public class ToolController {
    /**
     * 【处理微信图片上传】
     * @param image
     * @param session
     * @return
     */
    @RequestMapping("/upload.do")
    public Boolean upload(Image image, HttpSession session)
    {
        String tmp_prefix="WX20180127000001";
        System.out.println(image.getName());
        try {
            image.getFile().transferTo(new File("/home/mrsaber/tmp/"+tmp_prefix+".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @RequestMapping(value = "/getImage.do")
    public String getImage(HttpServletResponse response,String p) throws Exception
    {
        System.out.println("获得图片");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/*");
        File file=new File(p);//获取图片这个文件
        InputStream is=new FileInputStream(file);
        BufferedImage bi=ImageIO.read(is);
        ImageIO.write(bi, "PNG", response.getOutputStream());
        response.flushBuffer();
        return null;
    }
}
