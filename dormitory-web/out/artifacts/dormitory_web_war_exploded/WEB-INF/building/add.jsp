<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加宿舍楼页面</title>
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
            <form action="<%= request.getContextPath()%>/building?type=add" method="post">
                <div class="form-group">
                    <label>
                        宿舍楼编号:
                    </label>
                    <input type="text" class="form-control" name="no"/>
                </div>
                <div class="form-group">
                    <label>
                        落成时间:
                    </label>
                    <input type="text" class="form-control" readonly name="completedDate" id="completedDate"/>
                </div>
                <div class="form-group">
                    <label class="d-block">
                        宿舍楼类型:
                    </label>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" checked type="radio" name="buildingType" id="maleRadio"
                               value="M">
                        <label class="form-check-label" for="maleRadio">男生宿舍</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="buildingType" id="femaleRadio" value="F">
                        <label class="form-check-label" for="femaleRadio">女生宿舍</label>
                    </div>
                </div>
                <div class="d-flex justify-content-around">
                    <input type="submit" class="btn btn-primary" value="新增"/>
                    <a class="btn btn-primary" href="#" onclick="history.go(-1)">返回</a>
                </div>
            </form>
        </section>
    </div>
</div>
<!--bootstrap4使用gijgo日期控件-->
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
