<%@ page import="com.example.maxcrm.MaxCrm.Utility.UtilityClass" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <jsp:include page="common/title.jsp"></jsp:include>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css">
    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}<%=UtilityClass.minLogoUrl%>" type="image/x-icon">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- Bootstrap rtl -->
    <!-- Pe-icon-7-stroke -->
    <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet" type="text/css"/>
    <!-- style css -->
    <link href="${pageContext.request.contextPath}/assets/dist/css/stylecrm.css" rel="stylesheet" type="text/css"/>
    <!-- Theme style rtl -->
    <style>
        .calculatediv{
            font-weight: 700;
            width: 14%;
            text-align: center;
        }
    </style>
</head>
<body>
<!-- Content Wrapper -->
<div class="login-wrapper">

    <div class="container-center">
        <div class="login-area">
            <div class="panel panel-bd panel-custom">
                <div class="panel-heading">
                    <div class="view-header">
                        <div class="header-icon">
                            <i class="pe-7s-unlock"></i>
                        </div>
                        <div class="header-title">
                            <h3>Login</h3>
                            <small><strong>Please enter your credentials to login.</strong></small>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <form style="display: ${login}" name='f' action='${pageContext.request.contextPath}/login' method='POST'>
                        <div class="form-group">
                            <label class="control-label" for="username">Username</label>
                            <input type="text" placeholder="example@gmail.com" title="Please enter you username" required="" value="" name="username" id="username" class="form-control">

                        </div>
                        <div class="form-group">
                            <label class="control-label" for="password">Password</label>
                            <input type="password" title="Please enter your password" placeholder="******" required="" value="" name="password" id="password" class="form-control">

                        </div>
                        <div class="form-group">
                            <label class="control-label" for="password">Enter Total Of Following</label>
                            <input tabIndex="-1" id="number1" name="number1" class="calculatediv" type="number" readonly="readonly"  value="">
                            <span class="calculatediv">+</span> <input tabIndex="-1" id="number2" name="number2" class="calculatediv" readonly="readonly" type="number" value="">

                        </div>
                        <div class="form-group">
                            <label class="control-label" for="password">Enter Total</label>
                            <input  type="number"  placeholder="Enter Total" required="" value="" name="total"  class="form-control">

                        </div>
                        <div>
                            <p style="color: red;">${errorMsg}${msg}</p>

                            <br>
                            <button class="btn btn-add"><span class="fa-stack"><i class="hvr-buzz-out fa fa-unlock-alt fa-stack-2x"></i></span> Login</button>
                            <button type="button" class="btn btn-add pull-right" id="resetbtn">
                                <span class="fa-stack">
                                <i class="fa fa-undo fa-stack-2x"></i>
                                <i class="fa fa-lock fa-stack-4x"></i></span> Reset Password</button>

                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
<!-- /.content-wrapper -->


<!-- jQuery -->
<script src="${pageContext.request.contextPath}/assets/plugins/jQuery/jquery-1.12.4.min.js" type="text/javascript"></script>
<!-- bootstrap js -->
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script>

    $("#resetbtn").on("click",function () {

        window.location = "${pageContext.request.contextPath}/resetpassword";

    })

    function getRandomInt(min, max) {
        min = Math.ceil(min);
        max = Math.floor(max);
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }
    $('#number1').val(getRandomInt(0,10))
    $('#number2').val(getRandomInt(0,10))
    localStorage.removeItem("rolelistlocal")
    localStorage.removeItem("Menuapp");
</script>
</body>
</html>
