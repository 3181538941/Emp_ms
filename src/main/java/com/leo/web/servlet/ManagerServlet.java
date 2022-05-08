package com.leo.web.servlet;

import com.alibaba.fastjson.JSON;
import com.leo.pojo.Employee;
import com.leo.pojo.Manager;
import com.leo.service.Impl.ManagerServiceImpl;
import com.leo.service.ManagerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/man/*")
public class ManagerServlet extends BaseServlet {
    
    ManagerService managerService = new ManagerServiceImpl();
    
    public void getAllDepInfo(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException {
        //1. 调用service查询
        List<HashMap<String, String>> depInfos = managerService.selectAllDepInfo();
        //2. 转为JSON
        String jsonString = JSON.toJSONString(depInfos);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    
    public void login(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        //1. 接收数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        System.out.println(params);
        //转为Manager对象
        Manager manager = JSON.parseObject(params, Manager.class);
        System.out.println(manager);
//        //2. 转为JSON
//        String jsonString = JSON.toJSONString(manager);
//        //3. 写数据
//        response.setContentType("text/json;charset=utf-8");
//        response.getWriter().write(jsonString);
    }
}
