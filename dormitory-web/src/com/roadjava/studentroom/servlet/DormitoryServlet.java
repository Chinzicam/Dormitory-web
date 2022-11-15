package com.roadjava.studentroom.servlet;


import com.roadjava.studentroom.bean.entity.BuildingDO;
import com.roadjava.studentroom.bean.entity.DormitoryDO;
import com.roadjava.studentroom.service.DormitoryService;
import com.roadjava.studentroom.req.DormitoryBuildingRequest;
import com.roadjava.studentroom.req.DormitoryRequest;
import com.roadjava.studentroom.res.ResultDTO;
import com.roadjava.studentroom.res.TableResult;
import com.roadjava.studentroom.service.BuildingService;
import com.roadjava.studentroom.service.impl.BuildingServiceImpl;
import com.roadjava.studentroom.service.impl.DormitoryServiceImpl;
import com.roadjava.studentroom.util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;
//宿舍楼相关方法
@WebServlet(name = "dormitoryServlet",urlPatterns = "/dormitory")
public class DormitoryServlet extends HttpServlet {
    private DormitoryService service = new DormitoryServiceImpl();
    private BuildingService buildingService = new BuildingServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置导航
        request.setAttribute("dormitoryActive","active");
        String type = request.getParameter("type");
        if ("toManage".equals(type)) {
            String no = request.getParameter("no");
            String pageNowStr = request.getParameter("pageNow");//当前第几页
            DormitoryRequest searchRequest = new DormitoryRequest();
            int pageNow = 1; // 默认查第一页
            if (pageNowStr != null && !"".equals(pageNowStr.trim())) {
                pageNow = Integer.parseInt(pageNowStr);
            }
            searchRequest.setPageNow(pageNow);
            searchRequest.setNo(no);
            TableResult<DormitoryDO> tableResult = service.retrieveList(searchRequest);
            tableResult.setPageNow(pageNow);
            tableResult.setSearchWord(no == null ? "" : no);
            // 放到request请求域中，并在studentManage.jsp中使用
            request.setAttribute("tableResult",tableResult);
            request.getRequestDispatcher("/WEB-INF/dormitory/manage.jsp").forward(request,response);
        }else if("toAdd".equals(type)) {
            DormitoryBuildingRequest dormitoryBuildingRequest = new DormitoryBuildingRequest();
            dormitoryBuildingRequest.setPageSize(-1);
            Vector<BuildingDO> vector = buildingService.getVector(dormitoryBuildingRequest);
            request.setAttribute("buildings",vector);
            request.getRequestDispatcher("/WEB-INF/dormitory/add.jsp").forward(request,response);
        }else if("add".equals(type)) {
            // 执行学生的添加
            String no = request.getParameter("no");
            String buildingId = request.getParameter("buildingId");
            DormitoryDO dormitoryDO = new DormitoryDO();
            dormitoryDO.setNo(no);
            dormitoryDO.setDormitoryBuildingId(Long.parseLong(buildingId));
            service.add(dormitoryDO);
            response.sendRedirect(request.getContextPath()+"/dormitory?type=toManage");
        }else if ("toUpdate".equals(type)) {
            String id = request.getParameter("id");
            String pageNow = request.getParameter("pageNow");
            DormitoryDO dormitoryDO = service.retrieveOneById(Long.parseLong(id));
            request.setAttribute("oneDO", dormitoryDO);
            request.setAttribute("pageNow",Integer.parseInt(pageNow));
            // 查询出所有的宿舍楼
            DormitoryBuildingRequest dormitoryBuildingRequest = new DormitoryBuildingRequest();
            dormitoryBuildingRequest.setPageSize(-1);
            Vector<BuildingDO> vector = buildingService.getVector(dormitoryBuildingRequest);
            request.setAttribute("buildings",vector);
            request.getRequestDispatcher("/WEB-INF/dormitory/update.jsp").forward(request,response);
        }else if ("update".equals(type)) {
            String id = request.getParameter("id");
            String no = request.getParameter("no");
            String buildingId = request.getParameter("buildingId");
            String pageNow = request.getParameter("pageNow");
            // 把参数封装为对象
            DormitoryDO dormitoryDO = new DormitoryDO();
            dormitoryDO.setId(Long.parseLong(id));
            dormitoryDO.setNo(no);
            dormitoryDO.setDormitoryBuildingId(Long.parseLong(buildingId));
            service.update(dormitoryDO);
            response.sendRedirect(request.getContextPath()+"/dormitory?type=toManage&pageNow="+pageNow);
        }else if ("delete".equals(type)) {
            String id = request.getParameter("id");
            service.delete(new Long[]{Long.parseLong(id)});
            response.sendRedirect(request.getContextPath()+"/dormitory?type=toManage");
        }else if ("getDormitoriesByBuildingId".equals(type)){
            String buildingId = request.getParameter("buildingId");
            DormitoryRequest dormitoryRequest = new DormitoryRequest();
            dormitoryRequest.setPageSize(-1);
            dormitoryRequest.setDormitoryBuildingId(Long.parseLong(buildingId));
            Vector<DormitoryDO> dormitoryDOS = service.getVector(dormitoryRequest);
            ResponseUtil.respAppJson(response, ResultDTO.buildSuccess(dormitoryDOS));
        }
    }
}
