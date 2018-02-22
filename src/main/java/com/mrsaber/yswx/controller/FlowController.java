package com.mrsaber.yswx.controller;

import com.mrsaber.yswx.mapper.FlowMapper;
import com.mrsaber.yswx.model.Flow;
import com.mrsaber.yswx.model.User;
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
    private HttpSession session;

    @RequestMapping(value = "/add.do")
    public Integer add(HttpServletResponse response)
    {
        /**
         * 生成任务ID
         */
        Flow flow = new Flow();
        flow.setFlow_id((String) session.getAttribute("flowId"));
        flow.setFlow_date(new Date());
        session.setAttribute("flow_id",flow.getFlow_id());
        try {
            flowMapper.add(flow);
            session.setAttribute("flowId",null);//生成Flow以后清除Session值
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
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
    public Boolean  approval(String id)
    {
        User user= (User) session.getAttribute("cUser");
        //假定用户已登录
        Integer type = user.getUser_type();
        try {
            flowMapper.approval(id,type);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
        //假定用户已登录
        Integer type = 3;
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
