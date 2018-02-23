package com.mrsaber.yswx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageAdminController {

    @RequestMapping("page_admin_main.html")
    public String page_admin_main()
    {
        return "page_admin_main";
    }

    @RequestMapping("page_admin_part_builder.html")
    public String page_admin_part_builder()
    {
        return "page_admin_part_builder";
    }
    @RequestMapping("page_admin_part_builder_flows.html")
    public String page_admin_part_builder_flow()
    {
        return "page_admin_part_builder_flows";
    }
    @RequestMapping("page_admin_part_cafeteria.html")
    public String page_admin_part_cafeteria()
    {
        return "page_admin_part_cafeteria";
    }
    @RequestMapping("page_admin_part_cafeteria_admin.html")
    public String page_admin_part_cafeteria_admin()
    {
        return "page_admin_part_cafeteria_admin";
    }
    @RequestMapping("page_admin_part_cafeteria_user.html")
    public String page_admin_part_cafeteria_user()
    {
        return "page_admin_part_cafeteria_user";
    }
}
