package com.roadjava.studentroom.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 *服务器回复工具类
 */
public class ResponseUtil {

    public static void respTxtHtml(HttpServletResponse resp, String result) {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        write(resp, result);
    }

    private static void write(HttpServletResponse resp, String result) {
        try (PrintWriter writer = resp.getWriter()) {
            writer.print(result);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void respTxtPlain(HttpServletResponse resp, String result) {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain;charset=utf-8");//设置返回的内容类型,不加就没有
        write(resp,result);
    }

    public static void respAppJson(HttpServletResponse resp, Object obj) {
        resp.setCharacterEncoding("utf-8");
        /*
         告诉浏览器返回的结果类型,会在响应头添加:Content-Type: application/json;charset=utf-8
         如果不加这句则响应头就没ContentType,
         此时你返回的又是json类型的，则表现为json字符串,你就需要通过eval JSON.parse,如果不想
         使用eval JSON.parse,则可以加上dataType:"json"
         */
        resp.setContentType("application/json;charset=utf-8");
        write(resp, JSON.toJSONString(obj));
    }

}
