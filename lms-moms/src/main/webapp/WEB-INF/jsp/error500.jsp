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
<%--    <link rel="shortcut icon" href="<%=UtilityClass.minLogoUrl%>" type="image/x-icon">--%>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- Bootstrap rtl -->
    <!--<link href="assets/bootstrap-rtl/bootstrap-rtl.min.css" rel="stylesheet" type="text/css"/>-->
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath}/assets/dist/css/stylecrm.css" rel="stylesheet" type="text/css"/>
    <!-- Theme style rtl -->
    <!--<link href="assets/dist/css/stylecrm-rtl.css" rel="stylesheet" type="text/css"/>-->
</head>
<body>
<div class="middle-box2 text-center">
    <div class="row">
        <div class="col-sm-12">
            <div class="error-text2"><h1>505</h1></div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="error-desc2">
                <p>The server encountered something unexpected that didn't allow it to complete the request.
                    We apologize. You can go back to main page: </p>
                <a href="/" class="btn btn-add">Dashboard</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>