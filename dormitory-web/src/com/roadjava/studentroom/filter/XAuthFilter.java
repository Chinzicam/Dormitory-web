package com.roadjava.studentroom.filter;


import com.roadjava.studentroom.bean.entity.ManagerDO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 登录权限检测
 */
@WebFilter(filterName = "characterEncodingFilter"
        ,urlPatterns = {"/building","/dormitory","/student","/manager"}
)
public class XAuthFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        // 未登录则跳转到登录页
        ManagerDO manager = (ManagerDO) httpServletRequest.getSession().getAttribute("manager");
        if (manager == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/index.jsp");
            return;
        }
        chain.doFilter(req,resp);
    }

    /**
     * 初始化filter时会回调
     */
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
