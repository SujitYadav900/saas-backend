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
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/<%=UtilityClass.minLogoUrl%>" type="image/x-icon">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath}/assets/dist/css/stylecrm.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="middle-box">
    <div class="row">
        <div class="col-sm-12">
            <div class="error-text">
                <h1>404</h1>
                <h3><span>Page</span><br class="hidden-xs"> Not Found</h3>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="error-desc">
                <p>Sorry, but the page you are looking for has note been found. Try checking the URL for error, then hit the
                    refresh button on your browser or try found something else in our app.</p>
                <a href="/" class="btn btn-add">Dashboard</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>