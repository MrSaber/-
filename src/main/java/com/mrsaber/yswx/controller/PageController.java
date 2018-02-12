package com.mrsaber.yswx.controller;

import com.mrsaber.yswx.mapper.*;
import com.mrsaber.yswx.model.Bid;
import com.mrsaber.yswx.model.Flow;
import com.mrsaber.yswx.model.ToolHelper;
import com.mrsaber.yswx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class PageController {
    @Autowired
    private HttpSession session;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private FlowMapper flowMapper;
    @Autowired
    private BidMapper bidMapper;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("page_task_addWX.html")
    public String page_addWX()
    {
        String taskId = ToolHelper.getWX_ID();
        session.setAttribute("TASK_ID", taskId);
        System.out.println(taskId);
        return "page_task_addWX";
    }

    @RequestMapping("page_flow_addFlow.html")
    public String page_addFlow(Model model)
    {
        String flowId =null;
        if((flowId= (String) session.getAttribute("flowId"))==null)
        {
            flowId= ToolHelper.getFLOW_ID();
            session.setAttribute("flowId",flowId);
            System.out.println("已设置Flow");
        }
        model.addAttribute("flow",flowId);
        model.addAttribute("tasks",taskMapper.get(flowId));
        return "page_flow_addFlow";
    }

    @RequestMapping("page_task_details.html")
    public String page_showTask(String id,Model model)
    {
        //获取表单内容
        model.addAttribute("form",taskMapper.getForm(id));
        //获取图片地址
        model.addAttribute("images",imageMapper.getPath(id));
        return "page_task_details";
    }

    @RequestMapping("page_flow_approval.html")
    public String page_approval(Model model,String id)
    {
        model.addAttribute("tasks",taskMapper.get(id));
        model.addAttribute("flow",flowMapper.get(id));
        return "page_flow_approval";
    }
    @RequestMapping("page_flow_approval_ld.html")
    public String page_approval_ld(Model model,String id)
    {
        model.addAttribute("bid",bidMapper.getSelectBidByFlowId(id));
        model.addAttribute("tasks",taskMapper.get(id));
        model.addAttribute("flow",flowMapper.get(id));
        return "page_flow_approval_ld";
    }

    @RequestMapping("page_flow_bid.html")
    public String page_flow_bid(Model model,String id)
    {
        model.addAttribute("tasks",taskMapper.get(id));
        model.addAttribute("flow",flowMapper.get(id));
        return "page_flow_bid";
    }


    @RequestMapping("page_flowlist_check_sg.html")
    public String page_list_check_sg(Model model)
    {
        model.addAttribute("flows",flowMapper.getListByStatus(1));
        return "page_flowlist_check_sg";
    }
    //分管领导
    @RequestMapping("page_flowlist_check_fgld.html")
    public String page_flowlist_check_fgld(Model model)
    {
        model.addAttribute("flows",flowMapper.getListByStatus(11));
        return "page_flowlist_check_fgld";
    }
    //主任
    @RequestMapping("page_flowlist_check_zr.html")
    public String page_flowlist_check_zr(Model model)
    {
        model.addAttribute("flows",flowMapper.getListByStatus(5));
        return "page_flowlist_check_zr";
    }
    //处长
    @RequestMapping("page_flowlist_check_cz.html")
    public String page_flowlist_check_cz(Model model)
    {
        model.addAttribute("flows",flowMapper.getListByStatus(7));
        return "page_flowlist_check_cz";
    }


    @RequestMapping("page_flowlist_mail.html")
    public String page_list_mail(Model model)
    {
        model.addAttribute("flows",flowMapper.getListByStatus(3));
        return "page_flowlist_mail";
    }

    @RequestMapping("page_flow_details.html")
    public String page_list_task(Model model,String id)
    {
        Bid bid = bidMapper.getSelectBidByFlowId(id);
        if(bid==null)
        {
            bid = new Bid();
            bid.setUser_office("未分配单位");
            bid.setBid_flow_id("123456");
        }
        model.addAttribute("bid",bid);
        model.addAttribute("tasks",taskMapper.get(id));
        model.addAttribute("flow",flowMapper.get(id));
        return "page_flow_details";
    }

    //审批进度页面
    @RequestMapping("page_flow_progress.html")
    public String page_progress(String id,Model model)
    {
        String[] args ={
                "食堂工作人员发布维修任务", //0
                "食堂管理员已同意维修任务", //1
                "食堂管理员已拒绝维修任务", //2
                "维修单位报价",          //3
                "食堂管理员选定维修单位",  //4
                "分管领导同意维修方案",   //5
                "分管领导不同意维修方案",  //6
                "主任同意维修方案",       //7
                "主任不同意维修方案",        //8
                "处长同意维修方案",         //9
                "处长不同意维修方案",        //10
                "维修单位施工",            //11
                "维修单位发起验收",         //12
                "食堂管理员验收",          //13
                "任务完成",                //14
                "任务失败"                 //15
                };
        ArrayList<String> current_progress = new ArrayList<>();
        Flow flow = flowMapper.get(id);
        Integer status = flow.getFlow_status();
        switch (status)
        {
            case 1: //任务发布
                current_progress.add(args[0]);

                break;
            case 3: //管理员同意维修任务
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                break;
            case 4: //管理员不同意维修任务
                current_progress.add(args[0]);
                current_progress.add(args[2]);
                current_progress.add(args[15]);
                break;
            case 11: //管理员选定单位
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                break;
            case 5://分管领导同意
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[5]);
                break;
            case 6://分管领导拒绝
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[6]);
                current_progress.add(args[15]);
                break;
            case 7://主任同意
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[5]);
                current_progress.add(args[7]);
                break;
            case 8://主任拒绝
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[5]);
                current_progress.add(args[8]);
                current_progress.add(args[15]);
                break;
            case 9://处长同意
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[5]);
                current_progress.add(args[7]);
                current_progress.add(args[9]);
                break;
            case 10://处长拒绝
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[5]);
                current_progress.add(args[7]);
                current_progress.add(args[10]);
                current_progress.add(args[15]);
                break;
            case 12://维修单位施工
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[5]);
                current_progress.add(args[7]);
                current_progress.add(args[9]);
                current_progress.add(args[11]);
                break;
            case 13://维修单位发起验收
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[5]);
                current_progress.add(args[7]);
                current_progress.add(args[9]);
                current_progress.add(args[11]);
                current_progress.add(args[12]);
                break;
            case 14://管理员验收
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[5]);
                current_progress.add(args[7]);
                current_progress.add(args[9]);
                current_progress.add(args[11]);
                current_progress.add(args[12]);
                current_progress.add(args[13]);
                break;
            case 15://任务完成
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[5]);
                current_progress.add(args[7]);
                current_progress.add(args[9]);
                current_progress.add(args[11]);
                current_progress.add(args[12]);
                current_progress.add(args[13]);
                current_progress.add(args[14]);
                break;
        }
        model.addAttribute("progress",current_progress);
        return "page_flow_progress";
    }


    //报价页面
    @RequestMapping("page_bid_addStuff.html")
    public String page_bid_list(String id,Model model)
    {
        model.addAttribute("flowId",id);
        model.addAttribute("bidId",ToolHelper.getBID_ID());
        return "page_bid_list";
    }

    //历史记录
    @RequestMapping("page_flowlist_history.html")
    public String page_history(Model model)
    {
        model.addAttribute("flows",flowMapper.getAll());
        return "page_flowlist_history";
    }

    //
    @RequestMapping("page_flowlist_bid_sg.html")
    public String page_flowlist_bid_sg(Model model)
    {
        model.addAttribute("flows",flowMapper.getListByStatus(3));
        return "page_flowlist_bid_sg";
    }

    @RequestMapping("page_bidlist_showBids.html")
    public String page_bidlist_bids(Model model,String id)
    {
        model.addAttribute("tasks",taskMapper.get(id));
        model.addAttribute("flow",flowMapper.get(id));
        model.addAttribute("bids",bidMapper.getListByFlowId(id));
        return "page_bidlist_showBids";
    }

    @RequestMapping("page_logincheck.html")
    public String page_logincheck(String username, String password, HttpServletResponse response) throws IOException {
        User user= userMapper.getUser(username,password);
        session.setAttribute("cUser",user);
        switch (user.getUser_type())
        {
            case 1:return "page_index";
            case 3:return "page_index_sg";
            case 5:return "page_index_fgld";
            case 7:return "page_index_zr";
            case 9:return "page_index_cz";
            case 10:return "page_index_wxdw";
        }
        return null;
    }

    @RequestMapping("page_bid_showStuff.html")
    public String page_bid_showStuff(String id, Model model)
    {
        model.addAttribute("stuffs",bidMapper.getStuffsByBidId(id));
        model.addAttribute("bid",bidMapper.getBidByBidId(id));
        return "page_bid_showStuff";
    }

}