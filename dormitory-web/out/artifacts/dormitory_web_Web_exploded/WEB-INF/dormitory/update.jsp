<%@ page import="java.util.List" %>
<%@ page import="com.roadjava.studentroom.bean.entity.BuildingDO" %>
<%@ page import="com.roadjava.studentroom.bean.entity.DormitoryDO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新宿舍信息</title>
    <style>

    </style>
</head>
<body>
<div class="container">
    <jsp:include page="../top.jsp"/>
    <div id="cont" class="row">
        <section class="col-sm-4 offset-sm-4">
            <form action="<%= request.getContextPath()%>/dormitory?type=update&pageNow=${requestScope.pageNow}" method="post">
                <%
                    DormitoryDO oneDO = (DormitoryDO) request.getAttribute("oneDO");
                %>
                <div class="form-group">
                    <label>
                        系统标识:
                    </label>
                        <!--disabled的不能提交，如果用了disabled又想提交，就要再加个hidden的input来提交
                        disabled的input里面的内容-->
                    <input type="text"  class="form-control"  readonly name="id" value="<%= oneDO.getId()%>"/>
                </div>
                <div class="form-group">
                    <label>
                        宿舍编号:
                    </label>
                        <!--pagescope req session application-->
                    <input type="text"   class="form-control" name="no" value="${oneDO.no}"/>
                </div>
                <div class="form-group">
                    <label>
                        宿舍楼:
                    </label>
                    <select  class="form-control"  name="buildingId">
                        <%
                            List<BuildingDO> data = (List<BuildingDO>) request.getAttribute("buildings");
                            for (int i =0; i< data.size();i++) {
                                BuildingDO buildingDO = data.get(i);
                                if (buildingDO.getId().equals(oneDO.getDormitoryBuildingId())) {
                        %>

                        <option selected value="<%= buildingDO.getId()%>"><%= buildingDO.getUniqueNo() %></option>
                        <%
                        } else {
                        %>
                        <option value="<%= buildingDO.getId()%>"><%= buildingDO.getUniqueNo() %></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <div class="d-flex justify-content-around">
                    <input type="submit" class="btn btn-primary" value="更新"/>
                    <a class="btn btn-primary" href="#" onclick="history.go(-1)">返回</a>
                </div>
            </form>
        </section>
    </div>
</div>
</body>
</html>
