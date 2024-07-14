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
    <!-- pe-icon-7-stroke -->
    <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet" type="text/css"/>
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath}/assets/dist/css/stylecrm.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.css"/>
    <script src="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css">
    <!-- Favicon and touch icons -->
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
                            <i class="pe-7s-refresh-2"></i>
                        </div>
                        <div class="header-title">
                            <h3>Password Reset</h3>
                            <small><strong>Please fill the form to reset your password</strong></small>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <form id="passwordresetform">
                               <!-- Text input-->
                            <div class="col-md-12 form-group">
                                <div class="form-check-inline">
                                    <input type="text" placeholder="Email, Phone or username" title="Please enter you email address ,phone number or username" required="" value="" name="username" id="username" class="form-control">
                                    <span class="help-block small" id="error">Your registered Email, Username or Phone</span>

                                </div>
                            </div>


                            <!-- Text input-->
                            <div class="col-md-12 form-group">
                                <label class="control-label">Receive Password via</label>
                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" name="optradionew" value="email" checked> <i class="fa fa-envelope"></i> Email
                                    </label>
                                </div>
                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" name="optradionew" value="text">  <i style="font-size: 1.5em;" class="fa fa-mobile-phone"></i> Text Message
                                    </label>
                                </div>
                            </div>
                        <div class="col-md-12 form-group">
                            <div class="form-check-inline">
                                <button type="submit" style="width: 100%;" class="btn btn-add btn-block" id="createBtn">Reset Password</button>
                                <a href="${pageContext.request.contextPath}/" class="btn btn-add btn-block" >Login Page</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /.content-wrapper -->
<!-- jQuery -->
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/assets/plugins/jQuery/jquery-1.12.4.min.js" type="text/javascript"></script>
<script>

    //====form submit

    $("#passwordresetform").on("submit",function (e) {

        e.preventDefault();

        var temp_username = $("#username").val();
        var temp_mode =   $("input[name='optradionew']:checked").val();
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "${pageContext.request.contextPath}/allow/api/passwordreset/reset?username="+temp_username+"&mode="+temp_mode,
            success: function (json) {

                swal({
                    title: "Success!",
                    text: JSON.stringify(json),
                    icon: "success",
                    button: "Ok!",
                });
                $("#username").val("");
                $("#error").html( "Your registered Email, Username or Phone");
                console.log("OTP Sent!!")

            },
            error: function (err) {


                $("#error").html( "<strong><span style='color: red' >"+err.responseJSON.message+"</span></strong>");
            }

        })


    })

</script>
</body>
</html>