<%@ page import="java.util.List" %>
<%@ page import="com.roadjava.studentroom.bean.entity.BuildingDO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新宿舍楼页面</title>
    <link href="https://cdn.bootcss.com/gijgo/1.9.13/combined/css/gijgo.min.css" rel="stylesheet">
    <style>
        .gj-datepicker-md [role=right-icon] {
            top: 8px;
        }
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="../top.jsp"/>
    <div id="cont" class="row">
        <section class="col-sm-4 offset-sm-4">
            <form action="<%= request.getContextPath()%>/building?type=update&pageNow=${requestScope.pageNow}"
                  method="post">
                <%
                    BuildingDO oneDO = (BuildingDO) request.getAttribute("oneDO");
                %>
                <div class="form-group">
                    <label>
                        系统标识:
                    </label>
                    <!--disabled的不能提交，如果用了disabled又想提交，就要再加个hidden的input来提交
                    disabled的input里面的内容-->
                    <input type="text" class="form-control" readonly name="id" value="<%= oneDO.getId()%>"/>
                </div>
                <div class="form-group">
                    <label>
                        宿舍编号:
                    </label>
                    <!--pagescope req session application-->
                    <input type="text" class="form-control" name="no" value="${oneDO.no}"/>
                </div>
                <div class="form-group">
                    <label>
                        落成时间:
                    </label>
                    <input type="text" class="form-control" readonly value="${oneDO.completedDate}"
                           name="completedDate" id="completedDate"/>
                </div>
                <div class="form-group">
                    <label class="d-block">
                        宿舍楼类型:
                    </label>
                    <div class="form-check form-check-inline">
                        <%
                            String buildingType = oneDO.getType();
                            if ("M".equals(buildingType)) {
                        %>
                        <input class="form-check-input" checked type="radio" name="buildingType" id="maleRadio"
                               value="M">
                        <%
                        } else {
                        %>
                        <input class="form-check-input" type="radio" name="buildingType" id="maleRadio" value="M">
                        <%
                            }
                        %>
                        <label class="form-check-label" for="maleRadio">男生宿舍</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <%
                            if ("F".equals(buildingType)) {
                        %>
                        <input class="form-check-input" checked type="radio" name="buildingType" id="femaleRadio"
                               value="F">
                        <%
                        } else {
                        %>
                        <input class="form-check-input" type="radio" name="buildingType" id="femaleRadio" value="F">
                        <%
                            }
                        %>
                        <label class="form-check-label" for="femaleRadio">女生宿舍</label>
                    </div>
                </div>
                <div class="d-flex justify-content-around">
                    <input type="submit" class="btn btn-primary" value="更新"/>
                    <a class="btn btn-primary" href="#" onclick="history.go(-1)">返回</a>
                </div>
            </form>
        </section>
    </div>
</div>
<script src="https://cdn.bootcss.com/gijgo/1.9.13/combined/js/gijgo.min.js"></script>
<script src="https://cdn.bootcss.com/gijgo/1.9.13/combined/js/messages/messages.zh-cn.min.js"></script>
<script>
    $("#completedDate").datepicker({
        locale: 'zh-cn',
        format: 'yyyy-mm-dd', // 2021-07-08
        weekStartDay: 1 // 周一到周日:1-7
    });
</script>
</body>
</html>
