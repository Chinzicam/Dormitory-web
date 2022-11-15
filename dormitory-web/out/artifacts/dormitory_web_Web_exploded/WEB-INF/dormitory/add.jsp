<%@ page import="java.util.List" %>
<%@ page import="com.roadjava.studentroom.bean.entity.BuildingDO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加宿舍信息</title>
</head>
<body>
<div class="container">
    <jsp:include page="../top.jsp"/>
    <div id="cont" class="row">
        <section class="col-sm-4 offset-sm-4">
            <form action="<%= request.getContextPath()%>/dormitory?type=add" method="post">
                <div class="form-group">
                    <label>
                        宿舍编号:
                    </label>
                        <input type="text"  class="form-control" name="no"/>

                </div>
                <div class="form-group">
                    <label>
                        宿舍楼:
                    </label>
                    <select name="buildingId" class="form-control">
                        <%
                            List<BuildingDO> data = (List<BuildingDO>) request.getAttribute("buildings");
                            for (int i =0; i< data.size();i++) {
                                BuildingDO oneDO = data.get(i);
                        %>
                        <option value="<%= oneDO.getId()%>"><%= oneDO.getUniqueNo() %></option>
                        <%
                            }
                        %>>
                    </select>
                </div>
                <div class="d-flex justify-content-around">
                    <input type="submit" class="btn btn-primary" value="新增"/>
                    <a class="btn btn-primary" href="#" onclick="history.go(-1)">返回</a>
                </div>
            </form>
        </section>
    </div>
</div>
</body>
</html>
