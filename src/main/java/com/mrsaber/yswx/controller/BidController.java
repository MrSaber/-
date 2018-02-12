package com.mrsaber.yswx.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrsaber.yswx.mapper.BidMapper;
import com.mrsaber.yswx.mapper.FlowMapper;
import com.mrsaber.yswx.model.Bid;
import com.mrsaber.yswx.model.Stuff;
import com.mrsaber.yswx.model.Test;
import com.mrsaber.yswx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("bid")
public class BidController {
    @Autowired
    private HttpSession session;
    @Autowired
    private BidMapper bidMapper;
    @Autowired
    private FlowMapper flowMapper;

    @RequestMapping("/addStuff.do")
    public String addStuff(String data,String id) throws IOException {
/*        String bidId = (String) session.getAttribute("bidId");
        if(bidId==null)
            return "会话失效，请刷新页面重新进入";*/

        ObjectMapper objectMapper = new ObjectMapper();
        Stuff[] stuffs =objectMapper.readValue(data,Stuff[].class);
        for (Stuff stuff:stuffs
             ) {
            stuff.setStuff_bid_id(id);
            bidMapper.addStuff(stuff);
        }
        return "OK";
    }

    @RequestMapping("/addBid.do")
    public String addBid(Bid bid)
    {
        User cUser = (User) session.getAttribute("cUser");
        bid.setBid_user_id(cUser.getUser_id());
        bid.setBid_status(0);
        bidMapper.addBid(bid);
        return "OK";
    }

    @RequestMapping("/selectBid.do")
    public String selectBid(String id)
    {
        //1.将该报价单状态变为激活
        bidMapper.selectBidTo(id,1);
        //2.将Flow变为管理员选定单位状态，即11
        Bid bid = bidMapper.getBidByBidId(id);
        flowMapper.selectFlowTo(bid.getBid_flow_id(),11);
        return "OK";
    }

}
