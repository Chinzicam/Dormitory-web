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
//登录
@WebServlet(name = "loginServlet",urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    ManagerService managerService = new ManagerServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if ("trueLogin".equals(type)) { // 执行登录的
            String uname = request.getParameter("uname");
            String pwd = request.getParameter("pwd");
            if (uname == null || "".equals(uname.trim())
                || pwd == null || "".equals(pwd.trim())) {
                ResultDTO<Object> dto = ResultDTO.buildFailure("用户名或密码不能为空");
                ResponseUtil.respAppJson(response,dto);
                return;
            }
            ManagerDO managerDO = new ManagerDO();
            managerDO.setUserName(uname);
            managerDO.setPwd(pwd);
            boolean b = managerService.validateManagerInfo(managerDO);
            if (b) {
                request.getSession().setAttribute("manager",managerDO);
                ResponseUtil.respAppJson(response,ResultDTO.buildEmptySuccess());
            }else {
                ResponseUtil.respAppJson(response,ResultDTO.buildFailure("用户名或密码不正确"));
            }
        }else {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        }
    }
}
