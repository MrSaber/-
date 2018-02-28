package com.mrsaber.yswx.mapper;

import com.mrsaber.yswx.model.Bid;
import com.mrsaber.yswx.model.Stuff;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BidMapper {
    @Insert("INSERT INTO `yswxpg`.`wx_stuff` (`stuff_bid_id`, `stuff_name`, `stuff_number`, `stuff_price`) VALUES (#{stuff_bid_id}, #{stuff_name}, #{stuff_number}, #{stuff_price});")
     Integer addStuff(Stuff stuff);

    @Insert("INSERT INTO `yswxpg`.`wx_bid` (`bid_id`,`bid_flow_id`, `bid_user_id`, `bid_timeprice`, `bid_total`, `bid_status`) VALUES (#{bid_id},#{bid_flow_id}, #{bid_user_id}, #{bid_timeprice}, #{bid_total}, #{bid_status});")
     Integer addBid(Bid bid);

    @Select("SELECT wx_bid.*,wx_user.user_office,wx_office.of_name FROM wx_office,wx_bid,wx_user WHERE bid_user_id = wx_user.user_id AND user_type= 10 AND user_office = of_id AND bid_flow_id=#{flowId}")
    List<Bid> getListByFlowId(String flowId);

    @Select("SELECT * FROM wx_bid WHERE bid_id =#{id}")
    Bid getBidByBidId(String id);

    @Select("SELECT * FROM wx_stuff WHERE stuff_bid_id=#{id}")
    List<Stuff> getStuffsByBidId(String id);

    @Update("UPDATE wx_bid SET bid_status = #{status} WHERE bid_id = #{id}")
    Integer selectBidTo(@Param("id") String id,@Param("status") Integer status);

    @Select("SELECT wx_bid.*,wx_user.user_office,wx_office.of_name FROM wx_office, wx_bid,wx_user WHERE bid_user_id = wx_user.user_id  AND of_id = user_office AND bid_flow_id = #{id} AND bid_status = 1")
    Bid getSelectBidByFlowId(String id);

    @Select("SELECT wx_bid.bid_timeprice+SUM(wx_stuff.stuff_price) AS bid_total FROM `wx_bid`,`wx_stuff` WHERE wx_stuff.stuff_bid_id = wx_bid.bid_id AND wx_bid.bid_id=#{id};")
    Double getBidTotal(String id);

}
