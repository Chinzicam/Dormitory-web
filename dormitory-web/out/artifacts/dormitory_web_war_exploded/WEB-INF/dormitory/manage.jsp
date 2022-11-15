<%@ page import="com.roadjava.studentroom.bean.entity.BuildingDO" %>
<%@ page import="com.roadjava.studentroom.res.TableResult" %>
<%@ page import="java.util.List" %>
<%@ page import="com.roadjava.studentroom.bean.entity.DormitoryDO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>宿舍管理页面</title>
</head>
<body>
<div class="container">
    <jsp:include page="../top.jsp"/>
    <div class="row">
        <section class="search col-sm-12 mb-2">
            <%
                TableResult<DormitoryDO> tableResult = (TableResult) request.getAttribute("tableResult");
            %>
            <div class="d-flex justify-content-start align-items-center">
                <a class="btn btn-primary mr-3" href="<%=request.getContextPath()%>/dormitory?type=toAdd">新增</a>
                <form method="post" class="form-inline mb-0"
                      action="<%=request.getContextPath()%>/dormitory?type=toManage">
                    <input class="form-control" type="text" name="no" placeholder="按宿舍编号模糊搜索"
                           value="<%= tableResult.getSearchWord()%>"/>
                    <!--value在没有被别人修改的情况下就是1,
                        由于不是ajax局部刷新，页面是整体刷新的，所以即便pageNow被修改了，查询结果
                        出来的页面中的pageNow仍然还是1
                    -->
                    <input type="hidden" name="pageNow" id="pageNow" value="1"/>
                    <input type="submit" class="ml-3 btn btn-primary" value="查询">
                </form>
            </div>
        </section>
        <section class="col-sm-12">
            <table class="table table-bordered table-hover table-striped">
                <thead>
                <tr>
                    <th>系统标识</th>
                    <th>宿舍编号</th>
                    <th>所在宿舍楼编号</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<DormitoryDO> data = tableResult.getData();
                    for (DormitoryDO oneDO : data) {
                %>
                <tr>
                    <td><%= oneDO.getId()%>
                    </td>
                    <td><%= oneDO.getNo()%>
                    </td>
                    <td><%= oneDO.getBuildingUniqueNo()%>
                    </td>
                    <td>
                        <a href="<%=request.getContextPath()%>/dormitory?type=delete&id=<%= oneDO.getId()%>">删除</a>
                        <a href="<%=request.getContextPath()%>/dormitory?type=toUpdate&id=<%= oneDO.getId()%>&pageNow=<%=tableResult.getPageNow()%>">更新</a>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
            <div class="page">
                <ul class='pagination'>
                    <%
                        // 只要不是第一页就显示
                        if (tableResult.getPageNow() != 1) {
                    %>
                    <li class="page-item"><a class="page-link" href="#" onclick="goFirst()">首页</a></li>
                    <li class="page-item"><a class="page-link" href="#" onclick="goPre()">上一页</a></li>
                    <%
                        }
                    %>
                    <%
                        // 只要不是最后一页就显示
                        if (tableResult.getPageNow() != tableResult.getPageCount()) {
                    %>
                    <li class="page-item"><a class="page-link" href="#" onclick="goNext()">下一页</a></li>
                    <li class="page-item"><a class="page-link" href="#" onclick="goLast()">尾页</a></li>
                    <%
                        }
                    %>
                    <li class="page-item"><span class="page-link">共<%=tableResult.getPageCount()%>页</span></li>
                    <li class="page-item"><span class="page-link">共<%=tableResult.getTotalCount()%>条</span></li>
                    <li class="page-item"><span class="page-link">当前是第<%=tableResult.getPageNow()%>页</span></li>
                </ul>
            </div>
        </section>
    </div>
</div>

<script>
    // 首页
    function goFirst() {
        document.forms[0].submit();
    }

    // 上一页
    function goPre() {
        // 1.拿到当前页
        var currentPageStr = "<%=tableResult.getPageNow()%>";
        var prePage = parseInt(currentPageStr) - 1;
        // 2.修改搜索里面提交的pageNow
        document.getElementById("pageNow").value = prePage;
        document.forms[0].submit();
    }

    // 下一页
    function goNext() {
        var currentPageStr = "<%=tableResult.getPageNow()%>";
        var nextPage = parseInt(currentPageStr) + 1;
        document.getElementById("pageNow").value = nextPage;
        document.forms[0].submit();
    }

    // 尾页
    function goLast() {
        var pageCountStr = "<%=tableResult.getPageCount()%>";
        document.getElementById("pageNow").value = parseInt(pageCountStr);
        document.forms[0].submit();
    }
</script>
</body>
</html>
