package com.mrsaber.yswx.model;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.json.JacksonJsonParser;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ToolHelper {
    private static String access_token = null;
    private static String appId = "wx0535470be54c79fd";
    private static String appSecert = "28052f7415f95cf94f1b13062cef81ac";
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

    public static String get_ACCESS_TOKEN() throws IOException {
        if(access_token ==null)
        {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecert).build();
            Response response = okHttpClient.newCall(request).execute();
            JacksonJsonParser jacksonJsonParser = new JacksonJsonParser();
            Map<String, Object> map =jacksonJsonParser.parseMap(response.body().string());
            access_token= (String) map.get("access_token");
        }
        return access_token;
    }
}
