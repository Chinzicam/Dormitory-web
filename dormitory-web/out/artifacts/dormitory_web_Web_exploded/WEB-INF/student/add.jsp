<%@ page import="java.util.List" %>
<%@ page import="com.roadjava.studentroom.bean.entity.BuildingDO" %>
<%@ page import="com.roadjava.studentroom.bean.entity.DormitoryDO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>添加学生信息</title>
  <style>


  </style>
</head>
<body>
<div class="container">
  <jsp:include page="../top.jsp"/>
  <div id="cont" class="row">
    <section class="col-sm-4 offset-sm-4">
      <form action="<%= request.getContextPath()%>/student?type=add" method="post" id="addForm">
        <div class="form-group">
          <label>
            学号:
          </label>
          <input type="text" class="form-control" name="no"/>

        </div>
        <div class="form-group">
          <label>
            姓名:
          </label>
          <input type="text" class="form-control" name="name"/>
        </div>
        <div class="form-group">
          <label>
            性别:
          </label>
          <div class="form-check form-check-inline">
            <input class="form-check-input" checked type="radio" name="gender" id="maleRadio" value="M">
            <label class="form-check-label" for="maleRadio">男</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="gender" id="femaleRadio" value="F">
            <label class="form-check-label" for="femaleRadio">女</label>
          </div>
        </div>
        <div class="form-group">
          <label>
            所在院系:
          </label>
          <input type="text" class="form-control" name="dept"/>

        </div>
        <div class="form-group">
          <label>
            班级:
          </label>
          <input type="text" class="form-control" name="grade"/>

        </div>
        <div class="form-group">
          <label>
            宿舍楼:
          </label>
          <select name="buildingId" class="form-control">
            <%
              List<BuildingDO> data = (List<BuildingDO>) request.getAttribute("buildings");
              for (BuildingDO oneBuilding : data) {
            %>
              <option value="<%= oneBuilding.getId()%>" data-type="<%= oneBuilding.getType()%>">
                <%= oneBuilding.getUniqueNo() %>
              </option>
            <%
              }
            %>>
          </select>
        </div>
        <div class="form-group">
          <label>
            宿舍:
          </label>
          <select name="dormitoryId" class="form-control">
          </select>
        </div>
        <div class="d-flex justify-content-around">
          <input type="button" id="trueAdd" class="btn btn-primary" value="新增"/>
          <a class="btn btn-primary" href="#" onclick="history.go(-1)">返回</a>
        </div>
      </form>
    </section>
  </div>
</div>
<script>
  $(function () {
    var buildingId = $("select[name=buildingId]").val();
    reloadDormitories(buildingId);
  });
  $("select[name=buildingId]").change(function () {
    var buildingId = $(this).val();
    reloadDormitories(buildingId);
  });

  function reloadDormitories(buildingId) {
    $.ajax({
      type: "post",
      url: "<%=request.getContextPath()%>/dormitory",
      data: {"buildingId": buildingId, "type": "getDormitoriesByBuildingId"},
      success: function (result) {
        if (result.success) {
          var dormitoryArr = result.data;
          var len = dormitoryArr.length;
          var optionHtml = '';
          for (var i = 0; i < len; i++) {
            var oneDormitory = dormitoryArr[i];
            optionHtml += '<option value="' + oneDormitory["id"] + '">' + oneDormitory["no"] + '</option>';
          }
          $("select[name=dormitoryId]").html(optionHtml);
        }
      }
    });
  }
  // 添加学生
  $("#trueAdd").click(function () {
    var obj = str2Json("#addForm");
    // 校验男生应该添加到男生宿舍，女生应该添加到女生宿舍
    var buildingType = $("select[name=buildingId] :selected").attr("data-type");
    if (buildingType !==obj["gender"]) {
      $("#tipCont").text("请选择对应的宿舍楼类型");
      $("#tipModal").modal("show");
      return;
    }
    $.ajax({
      type: "post",
      data: obj,
      url: $("#addForm").attr("action"),
      success: function (data) {
        if (data.success) {
          location.href = "<%=request.getContextPath() %>/student?type=toManage";
        } else{
          $("#tipCont").text(data.errMsg);
          $("#tipModal").modal("show");
        }
      }
    });
  });
</script>
</body>
</html>
