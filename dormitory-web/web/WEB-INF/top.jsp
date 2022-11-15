<%@ page import="com.roadjava.studentroom.bean.entity.ManagerDO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bs4.5.0/css/bootstrap.min.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/resources/bs4.5.0/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/common.js"></script>
</head>
<body>
<div class="row mb-2">
    <div class="col-sm-12">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav mr-auto w-100 justify-content-around">
                    <li class="nav-item ${buildingActive}">
                        <a class="nav-link" href="<%=request.getContextPath() %>/building?type=toManage">宿舍楼管理</a>
                    </li>
                    <li class="nav-item ${dormitoryActive}">
                        <a class="nav-link" href="<%=request.getContextPath() %>/dormitory?type=toManage">宿舍管理</a>
                    </li>
                    <li class="nav-item ${studentActive}">
                        <a class="nav-link" href="<%=request.getContextPath() %>/student?type=toManage">学生管理</a>
                    </li>
                    <li class="nav-item ${managerActive}">
                        <a class="nav-link" href="<%=request.getContextPath() %>/manager?type=logout">退出</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
    <!--消息提示开始-->
    <div class="modal fade" data-backdrop="static"
         tabindex="-1"
         id="tipModal">
        <div class="modal-dialog modal-dialog-centered modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">提示</h5>
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="tipCont"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
    <!--消息提示结束-->
</div>
</body>
</html>
