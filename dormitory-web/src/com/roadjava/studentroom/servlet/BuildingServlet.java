package com.roadjava.studentroom.servlet;


import com.roadjava.studentroom.bean.entity.BuildingDO;
import com.roadjava.studentroom.req.DormitoryBuildingRequest;
import com.roadjava.studentroom.res.TableResult;
import com.roadjava.studentroom.service.BuildingService;
import com.roadjava.studentroom.service.impl.BuildingServiceImpl;
import com.roadjava.studentroom.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//宿舍楼相关方法
@WebServlet(name = "buildingServlet",urlPatterns = "/building")
public class BuildingServlet extends HttpServlet {
    BuildingService service = new BuildingServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置导航
        request.setAttribute("buildingActive","active");
        String type = request.getParameter("type");
        if ("toManage".equals(type)) {
            String no = request.getParameter("no");
            String pageNowStr = request.getParameter("pageNow");//当前第几页
            DormitoryBuildingRequest searchRequest = new DormitoryBuildingRequest();
            int pageNow = 1; // 默认查第一页
            if (pageNowStr != null && !"".equals(pageNowStr.trim())) {
                pageNow = Integer.parseInt(pageNowStr);
            }
            searchRequest.setPageNow(pageNow);
            searchRequest.setNo(no);
            TableResult<BuildingDO> tableResult = service.retrieveList(searchRequest);
            tableResult.setPageNow(pageNow);
            tableResult.setSearchWord(no == null ? "" : no);
            // 放到request请求域中，并在studentManage.jsp中使用
            request.setAttribute("tableResult",tableResult);
            request.getRequestDispatcher("/WEB-INF/building/manage.jsp").forward(request,response);
        }else if("toAdd".equals(type)) {
            request.getRequestDispatcher("/WEB-INF/building/add.jsp").forward(request,response);
        }else if("add".equals(type)) {
            // 执行学生的添加
            String no = request.getParameter("no");
            String completedDate = request.getParameter("completedDate");
            String buildingType = request.getParameter("buildingType");
            BuildingDO buildingDO = new BuildingDO();
            buildingDO.setNo(no);
            buildingDO.setType(buildingType);
            buildingDO.setCompletedDate(completedDate);
            service.add(buildingDO);
            response.sendRedirect(request.getContextPath()+"/building?type=toManage");
        }else if ("toUpdate".equals(type)) {//更新
            String id = request.getParameter("id");
            String pageNow = request.getParameter("pageNow");
            BuildingDO buildingDO = service.retrieveOneById(Long.parseLong(id));
            request.setAttribute("oneDO", buildingDO);
            request.setAttribute("pageNow",Integer.parseInt(pageNow));
            request.getRequestDispatcher("/WEB-INF/building/update.jsp").forward(request,response);
        }else if ("update".equals(type)) {
            String buildingId = request.getParameter("id");
            String no = request.getParameter("no");
            String completedDate = request.getParameter("completedDate");
            String buildingType = request.getParameter("buildingType");
            String pageNow = request.getParameter("pageNow");
            // 把参数封装为对象
            BuildingDO buildingDO = new BuildingDO();
            buildingDO.setId(Long.parseLong(buildingId));
            buildingDO.setNo(no);
            buildingDO.setType(buildingType);
            buildingDO.setCompletedDate(completedDate);
            service.update(buildingDO);
            response.sendRedirect(request.getContextPath()+"/building?type=toManage&pageNow="+pageNow);
        }else if ("delete".equals(type)) {
            String buildingId = request.getParameter("id");
            service.delete(new Long[]{Long.parseLong(buildingId)});
            response.sendRedirect(request.getContextPath()+"/building?type=toManage");
        }
    }
}
