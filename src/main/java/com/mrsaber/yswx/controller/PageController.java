package com.mrsaber.yswx.controller;

import com.mrsaber.yswx.mapper.*;
import com.mrsaber.yswx.model.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.rmi.rmic.iiop.StubGenerator;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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

    private User cUser=null;

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

    @RequestMapping("page_flow_approval_wxdw.html")
    public String page_flow_approval_wxdw(Model model,String id)
    {
        Bid bid = bidMapper.getSelectBidByFlowId(id);
        bid.setBid_total(bidMapper.getBidTotal(bid.getBid_id()));
        model.addAttribute("bid",bid);
        model.addAttribute("tasks",taskMapper.get(id));
        model.addAttribute("flow",flowMapper.get(id));
        return "page_flow_approval_wxdw";
    }

    @RequestMapping("page_flow_approval_ld.html")
    public String page_approval_ld(Model model,String id)
    {
        Bid bid = bidMapper.getSelectBidByFlowId(id);
        bid.setBid_total(bidMapper.getBidTotal(bid.getBid_id()));
        model.addAttribute("bid",bid);
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

    // XXX 管理员列表
    @RequestMapping("page_flowlist_check_sg.html")
    public String page_list_check_sg(Model model)
    {
        model.addAttribute("flows",flowMapper.getListByStatusAndOf(ProgressType.PROGRESS_WaitForSG,cUser.getUser_office()));
        return "page_flowlist_check_sg";
    }

    //任务验收
    @RequestMapping("page_flowlist_raty_sg.html")
    public String page_list_raty_sg(Model model)
    {
        model.addAttribute("flows",flowMapper.getListByStatus(13));
        return "page_flowlist_raty_sg";
    }

    //任务验收
    @RequestMapping("page_flowlist_check_wxdw.html")
    public String page_flowlist_check_wxdw(Model model)
    {
        model.addAttribute("flows",flowMapper.getCurListByWXDWId(cUser.getUser_office()));
        return "page_flowlist_check_wxdw";
    }
    //分管领导
    @RequestMapping("page_flowlist_check_fgld.html")
    public String page_flowlist_check_fgld(Model model)
    {
        model.addAttribute("flows",flowMapper.getListByStatusAndOf(ProgressType.PROGRESS_WaitForFGLD,cUser.getUser_office()));
        return "page_flowlist_check_fgld";
    }

    //主任
    @RequestMapping("page_flowlist_check_zr.html")
    public String page_flowlist_check_zr(Model model)
    {
        model.addAttribute("flows",flowMapper.getListByStatus(ProgressType.PROGRESS_WaitForZR));
        return "page_flowlist_check_zr";
    }

    //处长
    @RequestMapping("page_flowlist_check_cz.html")
    public String page_flowlist_check_cz(Model model)
    {
        model.addAttribute("flows",flowMapper.getListByStatus(ProgressType.PROGRESS_WaitForCZ));
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
        bid.setBid_total(bidMapper.getBidTotal(bid.getBid_id()));
        model.addAttribute("bid",bid);
        if(bid==null)
        {
            bid = new Bid();
            bid.setUser_office("未分配单位");
            bid.setBid_flow_id("123456");
        }
        Flow flow = flowMapper.get(id);
        if(flow.getFlow_status()!=14)
        {
            flow.setFlow_score(0);
            flow.setFlow_remark("任务进行中，尚未验收！");
        }
        model.addAttribute("bid",bid);
        model.addAttribute("tasks",taskMapper.get(id));
        model.addAttribute("flow",flow);
        return "page_flow_details";
    }

    @RequestMapping("page_flow_raty.html")
    public String page_flow_raty(Model model,String id)
    {
        Bid bid = bidMapper.getSelectBidByFlowId(id);
        bid.setBid_total(bidMapper.getBidTotal(bid.getBid_id()));
        model.addAttribute("bid",bid);
        if(bid==null)
        {
            bid = new Bid();
            bid.setUser_office("未分配单位");
            bid.setBid_flow_id("123456");
        }
        model.addAttribute("bid",bid);
        model.addAttribute("tasks",taskMapper.get(id));
        model.addAttribute("flow",flowMapper.get(id));
        return "page_flow_raty";
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
        return "page_bid_addStuff";
    }

    /**
     * 【历史记录——领导】
     * @param model
     * @return
     */
    @RequestMapping("page_flowlist_history_ld.html")
    public String page_history_LD(Model model)
    {
        model.addAttribute("flows",flowMapper.getAll());
        return "page_flowlist_history_ld";
    }
    /**
     * 【历史记录——食堂】
     * @param model
     * @return
     */
    @RequestMapping("page_flowlist_history.html")
    public String page_history(Model model)
    {
        model.addAttribute("flows",flowMapper.getListByOfId(cUser.getUser_office()));
        return "page_flowlist_history";
    }


    @RequestMapping("page_flowlist_bid_sg.html")
    public String page_flowlist_bid_sg(Model model)
    {
        model.addAttribute("flows",flowMapper.getListByStatus(3));
        return "page_flowlist_bid_sg";
    }

    /**
     * 显示【竞标单位】
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("page_bidlist_showBid.html")
    public String page_bidlist_showBid(Model model,String id)
    {
        System.out.println("执行此页面");
        model.addAttribute("tasks",taskMapper.get(id));
        model.addAttribute("flow",flowMapper.get(id));

        Bid bid = bidMapper.getSelectBidByFlowId(id);
        bid.setBid_total(bidMapper.getBidTotal(bid.getBid_id()));
        model.addAttribute("bid",bid);
        List<Bid> bids = bidMapper.getListByFlowId(id);
        for (Bid aBid:bids
             ) {
            aBid.setBid_total(bidMapper.getBidTotal(aBid.getBid_id()));
        }
        model.addAttribute("bids",bids);
        return "page_bidlist_showBid";
    }

    /**
     * 【登录检测】
     * @param username
     * @param password
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping("page_logincheck.html")
    public String page_logincheck(String username, String password,Model model) throws IOException {
        User user= userMapper.getUser(username,password);
        if(user==null)
        {
            model.addAttribute("error_text","请检查账户密码是否正确！");
            return "page_error";
        }
        session.setAttribute("cUser",user);
        cUser = user;
        switch (user.getUser_type())
        {
            case 10:return "page_index_wxdw";
            case 1:return "page_index";
            case 3:return "page_index_sg";
            case 5:return "page_index_fgld";
            case 7:return "page_index_zr";
            case 9:return "page_index_cz";
        }
        return null;
    }

    /**
     * 【显示报价】
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("page_bid_showStuff.html")
    public String page_bid_showStuff(String id, Model model)
    {
        model.addAttribute("stuffs",bidMapper.getStuffsByBidId(id));
        Bid bid = bidMapper.getBidByBidId(id);
        bid.setBid_total(bidMapper.getBidTotal(bid.getBid_id()));
        model.addAttribute("bid",bid);
        return "page_bid_showStuff";
    }

    private String appId = "wx0535470be54c79fd";
    private String appSecert = "28052f7415f95cf94f1b13062cef81ac";

    @Autowired
    private BuilderMapper builderMapper;

    /**
     * 【食堂人员】【绑定信息】
     * @param code
     * @param state
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping("code.do")
    public String BindWeChat_Ca(String code,String state,Model model) throws IOException {

        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+appSecert+"&code="+code+"&grant_type=authorization_code";
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        JacksonJsonParser jacksonJsonParser = new JacksonJsonParser();
        Map<String,Object> map = jacksonJsonParser.parseMap(response.body().string());
        request = new Request.Builder().url("https://api.weixin.qq.com/cgi-bin/user/info?access_token="+ToolHelper.get_ACCESS_TOKEN()+"&openid="+map.get("openid")+"&lang=zh_CN").build();
        Response user_info = okHttpClient.newCall(request).execute();
        Map<String,Object> info_map = jacksonJsonParser.parseMap(user_info.body().string());
        User user = userMapper.getUserByWe((String) map.get("openid"));
        //XXX:【2018-1-26】 用户已经绑定，拒绝跳转！
        if(user!=null)
        {
            model.addAttribute("error_text","微信已经绑定，请直接登录或者请求管理员解绑重新绑定！");
            return "page_error";
        }
        model.addAttribute("we_info",info_map);
        model.addAttribute("cafeterias",builderMapper.getAllCa());
        return "page_BindWeChat";
    }

    /**
     * 【维修单位】【绑定信息】
     * @param code
     * @param state
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping("code_wxdw.do")
    public String BindWeChat_WXDW(String code,String state,Model model) throws IOException {

        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+appSecert+"&code="+code+"&grant_type=authorization_code";
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        JacksonJsonParser jacksonJsonParser = new JacksonJsonParser();
        Map<String,Object> map = jacksonJsonParser.parseMap(response.body().string());
        request = new Request.Builder().url("https://api.weixin.qq.com/cgi-bin/user/info?access_token="+ToolHelper.get_ACCESS_TOKEN()+"&openid="+map.get("openid")+"&lang=zh_CN").build();
        Response user_info = okHttpClient.newCall(request).execute();
        Map<String,Object> info_map = jacksonJsonParser.parseMap(user_info.body().string());
        User user = userMapper.getUserByWe((String) map.get("openid"));
        //XXX:【2018-1-26】 用户已经绑定，拒绝跳转！
        if(user!=null)
        {
            model.addAttribute("error_text","微信已经绑定，请直接登录或者请求管理员解绑重新绑定！");
            return "page_error";
        }
        model.addAttribute("we_info",info_map);
        model.addAttribute("builders",builderMapper.getAllBuilder());
        return "page_BindWeChat_wxdw";
    }
    /**
     *
     * 【微信】【登录逻辑】
     * @param code
     * @param state
     * @return
     * @throws IOException
     */
    @RequestMapping("we_login.html")
    public String page_we_login(String code,String state,Model model) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+appSecert+"&code="+code+"&grant_type=authorization_code";
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        JacksonJsonParser jacksonJsonParser = new JacksonJsonParser();
        Map<String,Object> map = jacksonJsonParser.parseMap(response.body().string());
        User user = userMapper.getUserByWe((String) map.get("openid"));
        //XXX:【2018-1-26】 微信一键登录失败处理
        if(user==null)
        {
            model.addAttribute("error_text","微信登录失败，使用此功能前请确定您已绑定微信！");
            return "page_error";
        }
        session.setAttribute("cUser",user);
        cUser = user;
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

    /**
     * 【用户信息】
     * @return
     */
    @RequestMapping("page_userInfo.html")
    public String page_user_info()
    {
       User user= (User) session.getAttribute("cUser");
       user =userMapper.getUser(user.getUser_name(),user.getUser_password());
       session.setAttribute("cUser",user);
        return  "page_userInfo";
    }

    /**
     * 自动【跳转首页】
     * @param model
     * @return
     */
    @RequestMapping("indexs.html")
    public String index_html(Model model)
    {
        User user = (User) session.getAttribute("cUser");
        switch (user.getUser_type())
        {
            case 1:return "page_index";
            case 10:return "page_index_wxdw";
            case 3:return "page_index_sg";
            case 5:return "page_index_fgld";
            case 7:return "page_index_zr";
            case 9:return "page_index_cz";
        }
        model.addAttribute("error_text","用户信息验证失败");
        return "page_error";
    }

    /**
     * 【退出登录】
     * @param model
     * @return
     */
    @RequestMapping("logout.html")
    public String logout_html(Model model)
    {
        session.setAttribute("cUser",null);
        model.addAttribute("error_text","已经退出，请重新登录");
        return "page_error";
    }

    /**
     * 【错误页面】
     * @param model
     * @return
     */
    @RequestMapping("page_error.html")
    public String page_error(Model model)
    {
        return "page_error";
    }

    /**
     * 【用户详情】
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("page_user_details.html")
    public String page_user_details(Model model,Integer id)
    {
        User user=userMapper.getUserById(id);
        model.addAttribute("user",user);
        return "page_user_details";
    }

    /**
     * 【打印报表】
     */
    @RequestMapping("page_doc.html")
    public String page_doc(String id,Model model)
    {
        //XXX 需要FLOW 相关信息
        Flow thisFlow = flowMapper.get(id);
        model.addAttribute("flow",thisFlow);
        //XXX 需要发起单位的相关信息
        User thisUser=userMapper.getUserById(thisFlow.getFlow_userId());
        model.addAttribute("user",thisUser);
        //XXX 需要故障的相关信息
        List<TaskForm> theseTasks = taskMapper.get(thisFlow.getFlow_id());
        for(TaskForm task : theseTasks)
        {
            String image_path = "tool/getImage.do?p="+imageMapper.getPath(task.getFault_id()).get(0);
            task.setFault_image(image_path);
        }
        model.addAttribute("tasks",theseTasks);
        //XXX 需要维修单位的相关信息
        Builder builder = builderMapper.getById(thisFlow.getFlow_builderId());
        model.addAttribute("builder",builder);
        //XXX 获得报价信息
        Bid thisBid = bidMapper.getSelectBidByFlowId(thisFlow.getFlow_id());
        thisBid.setBid_total(bidMapper.getBidTotal(thisBid.getBid_id()));
        model.addAttribute("bid",thisBid);
        //XXX 需要材料的信息
        List<Stuff> theseStuffs = bidMapper.getStuffsByBidId(thisBid.getBid_id());
        model.addAttribute("stuffs",theseStuffs);
        return "page_doc";
    }
}