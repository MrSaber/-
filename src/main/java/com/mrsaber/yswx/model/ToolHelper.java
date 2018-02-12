package com.mrsaber.yswx.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolHelper {
    public static String getWX_ID()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String context = sdf.format(new Date());
        String suffix = String.valueOf(System.currentTimeMillis()/1000);
        return "TASK"+context+suffix;
    }

    public static String getFLOW_ID()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String context = sdf.format(new Date());
        String suffix = String.valueOf(System.currentTimeMillis()/1000);
        return "WX"+context+suffix;
    }

    public static String getBID_ID()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String context = sdf.format(new Date());
        String suffix = String.valueOf(System.currentTimeMillis()/1000);
        return "BID"+context+suffix;
    }
}
