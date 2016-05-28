package com.hhit.util;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;








import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import com.google.gson.Gson;

public class JsonUtil {
	//使用Gson--->xx转化成json
    public static void toJson(HttpServletResponse response, Object data) 
        throws IOException {
        Gson gson = new Gson();
        String result = gson.toJson(data);
        response.setContentType("text/json; charset=utf-8");
        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
        PrintWriter out = response.getWriter();
        System.out.println(result);
        out.print(result);
        out.flush();
        out.close();
    }
    //过滤掉对象关联的外键
    public static JSONObject jsonFilter(Object obj, String[] filterNames){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setIgnoreDefaultExcludes(false);    
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);    //防止自包含
         
        if(filterNames != null){
            //这里是核心，过滤掉不想使用的属性
            jsonConfig .setExcludes(filterNames) ;
        }
        JSONObject jsonObj = JSONObject.fromObject(obj, jsonConfig);
        return jsonObj;
         
    }
    //过滤掉对象List关联的外键
    public static JSONArray jsonListFilter(List objList, String[] filterNames){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setIgnoreDefaultExcludes(false);    
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);    //防止自包含
         
        if(filterNames != null){
            //这里是核心，过滤掉不想使用的属性
            jsonConfig .setExcludes(filterNames) ;
        }
        JSONArray jsonArray = JSONArray.fromObject(objList, jsonConfig);
        return jsonArray;
    }
  
}
