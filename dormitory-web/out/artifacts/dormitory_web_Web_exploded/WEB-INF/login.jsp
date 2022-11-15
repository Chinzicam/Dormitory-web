<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>管理登录</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bs4.5.0/css/bootstrap.min.css">
  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/common.js"></script>
  <style>
    .loginbg {
      height: 623px;
      background: url("<%=request.getContextPath()%>/resources/imgs/bg.jpg") no-repeat;
      background-size:100% 100%;
      margin-top: 5px;
      padding-top: 50px;
      padding-bottom: 50px;
    }

    .rightpart {
      margin-top: 90px;
      position: relative;
      padding-right: 0;
    }

    .errinfo {
      color: red;
      display: block;
      margin-top: 5px;
    }

    .errinfo img {
      width: 14px;
      margin-right: 5px;
    }

    .hid {
      visibility: hidden;
    }
  </style>
</head>
<body>
<section class="container">
  <div class="row">
    <div class="col-md-12 loginbg">
      <div class="row">
        <section class="col-sm-4 offset-sm-8 rightpart">
          <div class="panel panel-default">
            <div class="panel-body">
              <form class="form-horizontal" action="<%=request.getContextPath() %>/loginServlet?type=trueLogin">
                <div class="form-group">
                  <div class="col-sm-10 col-sm-offset-1">
                    <span style="color: #ffffff">Hi~</span><br>
                    <span style="color: #ffffff">欢迎使用java宿舍管理系统</span>
                    <span class="errinfo hid">
											<img src="<%=request.getContextPath() %>/resources/imgs/login_error.svg">
					      			<span id="tiptxt"></span>
					      		</span>
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-10 col-sm-offset-1">
                    <div class="input-group">
                      <input type="text" class="form-control" id="uname" name="uname" autocomplete="off"
                             placeholder="请输入用户名">
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-10 col-sm-offset-1">
                    <div class="input-group">
                      <input type="password" class="form-control" id="pwd" name="pwd" autocomplete="off"
                             placeholder="请输入密码">
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-offset-1 col-sm-10">
                    <input id="truelogin" type="button" class="btn btn-primary btn-block" value="登录">
                  </div>
                </div>
              </form>
            </div>
          </div>
        </section>
      </div>
    </div>
  </div>
</section>
<script>
  window.onload = function () {
    //提交处理
    $("#truelogin").click(function () {
      var obj = str2Json("form");
      $.ajax({
        type: "post",
        data: obj,
        url: $($("form")[0]).attr("action"),
        success: function (data) {
          if (data.success) {
            location.href = "<%=request.getContextPath() %>/building?type=toManage";
          } else{
            $("#tiptxt").text(data.errMsg);
            $(".errinfo").removeClass("hid");
          }
        }
      });
    });
  };
</script>
</body>
</html>