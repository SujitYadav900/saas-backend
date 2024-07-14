<%@ page import="com.example.maxcrm.MaxCrm.Utility.UtilityClass" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <jsp:include page="common/title.jsp"></jsp:include>

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
                        <div style="display: none" class="form-group">
                            <label class="control-label" for="username">Username</label>
                            <input type="text" placeholder="example@gmail.com" title="Please enter you username" required="" value="${sessionScope.get("username")}" name="username" id="username" class="form-control">

                        </div>
                        <div style="display:none;" class="form-group">
                            <label class="control-label" for="password">Password</label>
                            <input type="password" title="Please enter your password" placeholder="******" required="" value="${sessionScope.get("password")}" name="password" id="password" class="form-control">

                        </div>
                        <div style="display: none" class="form-group">
                            <label class="control-label" for="password">Enter Total Of Following</label>
                            <input id="number1" name="number1" class="calculatediv" type="number" readonly="readonly"  value="1"><span class="calculatediv">+</span> <input id="number2" name="number2" class="calculatediv" readonly="readonly" type="number" value="1">

                        </div>
                        <div style="display: none" class="form-group">
                            <label class="control-label" for="password">Enter Total</label>
                            <input  type="number"  placeholder="Enter Total" required="" value="2" name="total"  class="form-control">

                        </div>
                        <div style="display: block" class="form-group">
                            <label class="control-label" for="password">Enter Otp</label>
                            <input  type="number"  placeholder="Enter Otp" required="" value="" name="otp"  class="form-control">

                        </div>
                        <div>

                            ${errorMsg}${msg}
                            <br>
                            <button class="btn btn-add">Login</button>

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

</script>
</body>
</html>