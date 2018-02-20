package com.mrsaber.yswx.model;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * 这个类专门用于发送模板消息
 */
public class SecheduleHelper {
    private  String templet;
    private  HashMap<String,String> pattern;
    public SecheduleHelper(String templet, HashMap<String,String> pattern)
    {
        this.templet = templet;
        this.pattern = pattern;
        for(String key:pattern.keySet())
        {
            this.templet.replace(key,pattern.get(key));
        }
    }
    private void send() throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON,templet);
        Request request = new Request.Builder()
                .url("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+ToolHelper.get_ACCESS_TOKEN())
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
