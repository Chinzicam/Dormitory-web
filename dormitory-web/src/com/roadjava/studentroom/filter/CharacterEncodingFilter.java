package com.roadjava.studentroom.filter;


import com.roadjava.studentroom.bean.entity.ManagerDO;
import com.roadjava.studentroom.util.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//字符过滤器
@WebFilter(filterName = "characterEncodingFilter"
        ,urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "encoding",value = "UTF-8")
        }
)
public class CharacterEncodingFilter implements Filter {
    private String encoding = null;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        // 设置字符编码
        httpServletRequest.setCharacterEncoding(encoding);
        httpServletResponse.setCharacterEncoding(encoding);
        chain.doFilter(req,resp);
    }

    /**
     * 初始化filter时会回调
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        this.encoding = config.getInitParameter("encoding");
    }

}
