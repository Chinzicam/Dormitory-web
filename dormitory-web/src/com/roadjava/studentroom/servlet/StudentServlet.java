package com.roadjava.studentroom.servlet;


import com.roadjava.studentroom.bean.entity.BuildingDO;
import com.roadjava.studentroom.bean.entity.StudentDO;
import com.roadjava.studentroom.res.ResultDTO;
import com.roadjava.studentroom.service.DormitoryService;
import com.roadjava.studentroom.req.DormitoryBuildingRequest;
import com.roadjava.studentroom.req.StudentRequest;
import com.roadjava.studentroom.res.TableResult;
import com.roadjava.studentroom.service.BuildingService;
import com.roadjava.studentroom.service.StudentService;
import com.roadjava.studentroom.service.impl.BuildingServiceImpl;
import com.roadjava.studentroom.service.impl.DormitoryServiceImpl;
import com.roadjava.studentroom.service.impl.StudentServiceImpl;
import com.roadjava.studentroom.util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;
//学生增删改查方法
@WebServlet(name = "studentServlet", urlPatterns = "/student")
public class StudentServlet extends HttpServlet {
    private StudentService service = new StudentServiceImpl();
    private DormitoryService dormitoryService = new DormitoryServiceImpl();
    private BuildingService buildingService = new BuildingServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置导航
        request.setAttribute("studentActive", "active");
        String type = request.getParameter("type");
        if ("toManage".equals(type)) {
            String no = request.getParameter("no");
            String pageNowStr = request.getParameter("pageNow");//当前第几页
            StudentRequest searchRequest = new StudentRequest();
            int pageNow = 1; // 默认查第一页
            if (pageNowStr != null && !"".equals(pageNowStr.trim())) {
                pageNow = Integer.parseInt(pageNowStr);
            }
            searchRequest.setPageNow(pageNow);
            searchRequest.setStudentNo(no);
            TableResult<StudentDO> tableResult = service.retrieveList(searchRequest);
            tableResult.setPageNow(pageNow);
            tableResult.setSearchWord(no == null ? "" : no);
            // 放到request请求域中，并在studentManage.jsp中使用
            request.setAttribute("tableResult", tableResult);
            request.getRequestDispatcher("/WEB-INF/student/manage.jsp").forward(request, response);
        } else if ("toAdd".equals(type)) {
            // 查询宿舍楼
            DormitoryBuildingRequest dormitoryBuildingRequest = new DormitoryBuildingRequest();
            dormitoryBuildingRequest.setPageSize(-1);
            Vector<BuildingDO> vector = buildingService.getVector(dormitoryBuildingRequest);
            request.setAttribute("buildings", vector);
            request.getRequestDispatcher("/WEB-INF/student/add.jsp").forward(request, response);
        } else if ("add".equals(type)) {
            try {
                // 执行学生的添加
                StudentDO studentDO = buildBasicStudent(request);
                boolean b = service.add(studentDO);
                if (b) {
                    ResponseUtil.respAppJson(response, ResultDTO.buildSuccess("添加成功"));
                } else {
                    ResponseUtil.respAppJson(response, ResultDTO.buildFailure("添加失败"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                ResponseUtil.respAppJson(response, ResultDTO.buildFailure(e.getMessage()));
            }
        } else if ("toUpdate".equals(type)) {
            String id = request.getParameter("id");
            String pageNow = request.getParameter("pageNow");
            StudentDO dormitoryDO = service.retrieveOneById(Long.parseLong(id));
            request.setAttribute("oneDO", dormitoryDO);
            request.setAttribute("pageNow", Integer.parseInt(pageNow));
            // 查询出所有的宿舍楼
            DormitoryBuildingRequest dormitoryBuildingRequest = new DormitoryBuildingRequest();
            dormitoryBuildingRequest.setPageSize(-1);
            Vector<BuildingDO> vector = buildingService.getVector(dormitoryBuildingRequest);
            request.setAttribute("buildings", vector);
            request.getRequestDispatcher("/WEB-INF/student/update.jsp").forward(request, response);
        } else if ("update".equals(type)) {
            try {
                String id = request.getParameter("id");
                StudentDO studentDO = buildBasicStudent(request);
                studentDO.setId(Long.parseLong(id));
                boolean b = service.update(studentDO);
                if (b) {
                    ResponseUtil.respAppJson(response, ResultDTO.buildSuccess("更新成功"));
                } else {
                    ResponseUtil.respAppJson(response, ResultDTO.buildFailure("更新失败"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                ResponseUtil.respAppJson(response, ResultDTO.buildFailure(e.getMessage()));
            }
        } else if ("delete".equals(type)) {
            String id = request.getParameter("id");
            service.delete(new Long[]{Long.parseLong(id)});
            response.sendRedirect(request.getContextPath() + "/student?type=toManage");
        }
    }

    private StudentDO buildBasicStudent(HttpServletRequest request) {
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String dept = request.getParameter("dept");
        String grade = request.getParameter("grade");
        String dormitoryId = request.getParameter("dormitoryId");
        StudentDO studentDO = new StudentDO();
        studentDO.setNo(no);
        studentDO.setName(name);
        studentDO.setGender(gender);
        studentDO.setDept(dept);
        studentDO.setGrade(grade);
        studentDO.setDormitoryId(Long.parseLong(dormitoryId));
        return studentDO;
    }
}
