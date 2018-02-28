package com.mrsaber.yswx.controller;

import com.mrsaber.yswx.mapper.BidMapper;
import com.mrsaber.yswx.mapper.FlowMapper;
import com.mrsaber.yswx.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 维修任务操作
 */
@RestController
@CrossOrigin
@RequestMapping("flow")
public class FlowController {
    @Autowired
    private FlowMapper flowMapper;
    @Autowired
    private BidMapper bidMapper;
    @Autowired
    private HttpSession session;

    /**
     * 设置任务进度
     * @param flowId
     * @param status
     * @return
     */
    @RequestMapping(value = "/setProcess.do")
    public Message setProcess(String flowId,Integer status)
    {

        Message re = new Message();
        try {
            flowMapper.selectFlowTo(flowId,status);
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
     * 食堂工作人员发起维修任务
     * @param response
     * @return
     */
    @RequestMapping(value = "/add.do")
    public Message add(HttpServletResponse response)
    {
        Message message = new Message();

        Flow flow = new Flow();
        flow.setFlow_id((String) session.getAttribute("flowId"));
        User user= (User) session.getAttribute("cUser");
        // XXX:设置Flow的相关信息！
        flow.setFlow_userId(user.getUser_id());
        flow.setFlow_date(new Date());
        flow.setFlow_ofId(user.getUser_office());

        session.setAttribute("flow_id",flow.getFlow_id());
        try {
            flowMapper.add(flow);
            session.setAttribute("flowId",null);//生成Flow以后清除Session值
        } catch (Exception e) {
            e.printStackTrace();
            message.setCode(0);
            message.setMsg(e.getMessage());
            return message;
        }
        message.setCode(1);
        message.setMsg("发布成功,等待管理员审核！");
        return message;
    }
    /**
     * 维修单位发起验收
     * @param id

     * @return
     */
    @RequestMapping(value = "/wxdwSubmit.do")
    public Message wxdwSubmit(String id)
    {
        Message message = new Message();
        //假定用户已登录
        try {
            flowMapper.approval(id,13);
        } catch (Exception e) {
            e.printStackTrace();
            message.setCode(0);
            message.setMsg(e.getMessage());
            return message;
        }
        message.setCode(1);
        message.setMsg("发起成功！");
        return message;
    }

    /**
     * 任务评分
     * @param id 任务ID
     * @param remark    评语
     * @param score     评分
     * @return  完成情况
     */
    @RequestMapping(value = "/raty.do")
    public String raty(String id,String remark,Integer score)
    {
        try {
            Flow flow = new Flow();
            flow.setFlow_id(id);
            flow.setFlow_remark(remark);
            flow.setFlow_score(score);
            flowMapper.raty(flow);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "评价成功！";
    }
    /**
     * 审批通过
     * @return
     */
    @RequestMapping(value = "/approval.do")
    public Message  approval(String id)
    {
        Message message = new Message();
        User user= (User) session.getAttribute("cUser");
        //假定用户已登录
        Integer type = user.getUser_type();
        //处理报价2万元以上的项目
        System.out.println("type为"+type);
        if(type==UserType.USER_ZR)
        {
            System.out.println("开始进行比较！");
            Double bidPrice = bidMapper.getBidTotal(bidMapper.getSelectBidByFlowId(id).getBid_id());;
            System.out.println(bidPrice);
            if(bidPrice >=20000.0)
            {
                //状态16 为等待处长进行决策！
                flowMapper.approval(id, ProgressType.PROGRESS_WaitForCZ);
                message.setCode(3);
                message.setMsg("由于报价高于标定值，需要等待处长进行决定！");
                return message;
            }
        }
        try {
            flowMapper.approval(id,type);
        } catch (Exception e) {
            e.printStackTrace();
            message.setCode(0);
            message.setMsg(e.getMessage());
            return message;
        }
        message.setCode(1);
        message.setMsg("操作成功");
        return message;
    }
    /**
     * 不同意审批
     * @param id 原因
     * @param why
     * @return
     */
    @RequestMapping(value = "/noApproval.do")
    public Boolean  noApproval(String id,String why)
    {
        Message message = new Message();
        User user= (User) session.getAttribute("cUser");
        Integer type = user.getUser_type();
        Integer value = type+1;
        try {
            flowMapper.noApproval(id,value,why);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * 获得维修单位的所有维修任务
     */
    @RequestMapping(value = "/getAllByBuilderId.do")
    public List<Flow> getAllByBuilderId(Integer ofId)
    {
       return flowMapper.getAllByBuilderId(ofId);
    }
}
