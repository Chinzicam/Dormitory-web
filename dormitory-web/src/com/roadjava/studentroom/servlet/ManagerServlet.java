package com.roadjava.studentroom.servlet;


import com.roadjava.studentroom.bean.entity.ManagerDO;
import com.roadjava.studentroom.res.ResultDTO;
import com.roadjava.studentroom.service.ManagerService;
import com.roadjava.studentroom.service.impl.ManagerServiceImpl;
import com.roadjava.studentroom.util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//管理员退出系统
@WebServlet(name = "managerServlet",urlPatterns = "/manager")
public class ManagerServlet extends HttpServlet {
    ManagerService managerService = new ManagerServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置导航
        request.setAttribute("managerActive","active");
        String type = request.getParameter("type");
        if ("logout".equals(type)) {
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
    }
}
