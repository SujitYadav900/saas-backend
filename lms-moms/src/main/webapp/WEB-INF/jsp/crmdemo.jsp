<%@ page import="com.example.maxcrm.MaxCrm.Utility.UtilityClass" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="common/title.jsp"></jsp:include>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.min.css"/>

    <!-- =============================FOR CUSTOM ALERTS ============================================ -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.css"/>
    <script src="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.js"></script>
    <script src="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css">
    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}<%=UtilityClass.minLogoUrl%>" type="image/x-icon">
    <!-- Start Global Mandatory Style
       =====================================================================-->
    <!-- jquery-ui css -->
    <link href="${pageContext.request.contextPath}/assets/plugins/jquery-ui-1.12.1/jquery-ui.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- Lobipanel css -->
    <link href="${pageContext.request.contextPath}/assets/plugins/lobipanel/lobipanel.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- Pace css -->
    <link href="${pageContext.request.contextPath}/assets/plugins/pace/flash.css" rel="stylesheet" type="text/css"/>
    <!-- Pe-icon -->
    <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/helper.css" rel="stylesheet"
          type="text/css"/>

    <!-- Themify icons -->
    <link href="${pageContext.request.contextPath}/assets/themify-icons/themify-icons.css" rel="stylesheet"
          type="text/css"/>
    <!-- Emojionearea -->
    <link href="${pageContext.request.contextPath}/assets/plugins/emojionearea/emojionearea.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- Monthly css -->
    <link href="${pageContext.request.contextPath}/assets/plugins/monthly/monthly.css" rel="stylesheet"
          type="text/css"/>
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath}/assets/dist/css/stylecrm.css" rel="stylesheet" type="text/css"/>

    <style>
        .validationFail {
            background-color: #ff00005e !important;
        }
        .downloadfilecls{
            cursor: pointer;
        }

        body  {
            background-image: url("${pageContext.request.contextPath}/images/prp_logo.png");
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-position: center;
            background-size: 800px 700px;

        }
    </style>

</head>


<body>
<div class="container">
    <div style="margin: 5%; opacity: 0.95">
        <div id="formheading" style="margin: auto 0%; width: 100%; background: white; padding-left: 35%; padding-top: 5px;padding-bottom: 5px; " >
            <h2><strong>Register For LMS Demo</strong></h2>
        </div>
        <div class="panel panel-bd lobidrag" id="registerdiv">

            <div class="panel-body">
                <div id="addnewdiv">

                    <form id="createnewuserform" method="post" class="col-sm-12">

                        <!-- Radio Buttons for status-->
                        <div class="col-md-12 form-group">
                            <label class="control-label">Gender</label>
                            <br>
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" name="gender" value="male" checked> <i class="fa fa-male"></i> Male
                            </label>

                            <br>
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" name="gender" value="female"> <i class="fa fa-female"></i> Female
                            </label>
                        </div>
                        <br>



                        <div class="col-md-6 form-group">
                            <label>First Name*</label>
                            <input type="text" minlength="4" maxlength="100" name="firstName" id="firstName" class="form-control"
                                   placeholder="Enter First Name" required>
                        </div>

                        <div class="col-md-6 form-group">
                            <label>Last Name</label>
                            <input name="lastName" id="lastName" maxlength="100" type="text" class="form-control"
                                   placeholder="Enter last Name">
                        </div>


                        <div class="col-md-6 form-group">
                            <label>Email/username*</label>
                            <input name="email" type="email" id="email" class="form-control" placeholder="Enter Email"
                                   required>
                        </div>
                        <div class="col-md-6 form-group">
                            <label>Mobile*</label>
                            <input name="mobile" pattern="[0-9]{10}"  id="mobile" type="tel" class="form-control"
                                   placeholder="Enter Mobile"
                                   required>
                        </div>

<%--                        <div class="col-md-6 form-group">--%>
<%--                            <label>User Name*</label>--%>
<%--                            <input type="text" name="username" id="username" class="form-control"--%>
<%--                                   placeholder="Enter Username"--%>
<%--                                   required>--%>
<%--                        </div>--%>

                        <div class="col-md-6 form-group passworddiv" hidden>
                            <label>Password*</label>
                            <input type="text" name="password" id="password" class="form-control" title="Must contain at least one upper case character,one lower case character,one digit,one special character and must be at least 8 characters long"
                                   placeholder="Enter Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
                        </div>
                        <div class="col-md-6 passworddiv" hidden>

                        </div>


                        <div class="col-md-6 form-group">
                            <label>Organisation/Company Name*</label>
                            <input name="companyName" id="companyName" type="text" class="form-control"
                                   placeholder="Your Company or Organisation Name" required>
                        </div>

                        <div class="col-md-6 form-group">
                            <label>Number of Employees in Organisation/Company*</label>
                            <input name="companySize" id="companySize" type="number" min="1" class="form-control"
                                   placeholder="Number of Employees in Your Company or Organisation" required>
                        </div>

                        <div class="col-md-6 form-group" id="industrydiv">
                            <label>Industry*</label>
                            <input name="industry" id="industry" type="text" class="form-control"
                                   placeholder="Your industry" list="industrylist" required>
                            <datalist id="industrylist">

                            </datalist>
                        </div>


                        <div class="col-md-12 form-group">
                            <label>Address</label>
                            <textarea name="address" id="address" class="form-control" rows="3" ></textarea>
                        </div>

                        <input type="hidden" name="id" id="id">

                        <div class="reset-button">
                            <button id="createbtn" type="submit" class="btn btn-success">Register</button>
                        </div>
                    </form>
                </div>
                <p style="text-align: center">Already registered? <a href="${pageContext.request.contextPath}/login">Sign in</a> | Forgot login details?<a href="${pageContext.request.contextPath}/resetpassword"> Reset</a></p>
            </div>


        </div>

        <div id="success-notice" hidden>

            <div class="jumbotron mt-3">
                <h1 id="noticeheading"></h1>
                <p class="lead" id="noticemsg"></p>
                <a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/login" role="button">Go to Login Page</a>
                <a class="btn btn-lg btn-primary" href="#" role="button">Explore our other services</a>
            </div>

        </div>
    </div>
    <footer class="main-footer" style="margin-left:0;position: fixed;left: 0;bottom: 0;width: 100%;">
        <strong ><%=UtilityClass.copyRightMsg%><span><%=UtilityClass.startYear%></span> - <span><%=UtilityClass.endYear%></span> <a href="<%=UtilityClass.siteUrl%>"><%=UtilityClass.ApplicationTitle%></a>.</strong> <%=UtilityClass.footerMsg%>
    </footer>
</div>

</body>
<script src="${pageContext.request.contextPath}/assets/plugins/jQuery/jquery-1.12.4.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/jquery-ui-1.12.1/jquery-ui.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/lobipanel/lobipanel.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/pace/pace.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/slimScroll/jquery.slimscroll.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/fastclick/fastclick.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/dist/js/custom.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/dist/js/dashboard.js" type="text/javascript"></script>
<script>

    $('#createnewuserform').submit(function (e) {
        e.preventDefault();
        var url = "${pageContext.request.contextPath}/api/user/insertdemouser";
        var map = {
                gender: $("input[name='gender']:checked").val(),
                status: $("input[name='status']:checked").val(),
                firstName: $('#firstName').val(),
                lastName: $('#lastName').val(),
                username: $('#email').val(),
                password: $('#password').val(),
                email: $('#email').val(),
                mobile: $('#mobile').val(),
                faceBookId: $('#facebookId').val(),
                address: $('#address').val(),
                companyName:$('#companyName').val(),
                companySize:$('#companySize').val(),

            };



        $.ajax({
            type: 'POST',
            data: JSON.stringify(map),
            contentType: 'application/json',
            url: url,
            success: function (data) {


                $("#noticeheading").html("Thank You "+$("#firstName").val())
                $("#noticemsg").html("We appreciate your decision. Your login credentials have been mailed to you on "+$("#email").val()+".")
                $("#registerdiv").fadeOut();
                $("#formheading").fadeOut();
                $("#success-notice").fadeIn();

                    // swal({
                    //     title: "Success!",
                    //     text: "Your login credentials have been sent to your registered E-Mail ID",
                    //     icon: "success",
                    //     button: "Ok!",
                    // });


               // clearData();

            },
            error: function (err) {

                swal({
                    title: 'Error',
                    text: err.responseJSON.message,
                    icon: 'error',
                    button: 'Ok'
                });

            }
        });


    });

    //check if guest is allowed to set password
    let demoUserRole = "<%=UtilityClass.propertyService.findProperty("CRMDemo","letUserCreatePassword")%>"

    if(demoUserRole == "true"){
        $("#password").prop("required",true);
        $(".passworddiv").show();
    }
    else{
        $("#industrydiv").removeClass("col-md-6");
        $("#industrydiv").addClass("col-md-12");
    }
    loadIndustries()
    function loadIndustries(){
        var industries = ["AGRICULTURE AND ALLIED INDUSTRIES","AUTOMOBILES","AUTO COMPONENTS","AVIATION","BANKING","CEMENT","CONSUMER DURABLES","ECOMMERCE","EDUCATION AND TRAINING","ENGINEERING AND CAPITAL GOODS","FINANCIAL SERVICES","FMCG","GEMS AND JEWELLERY","HEALTHCARE","INFRASTRUCTURE","INSURANCE","IT & ITES","MANUFACTURING","MEDIA AND ENTERTAINMENT","METALS AND MINING","OIL AND GAS","PHARMACEUTICALS","PORTS","POWER","RAILWAYS","REAL ESTATE","RENEWABLE ENERGY","RETAIL","ROADS","SCIENCE AND TECHNOLOGY","SERVICES","STEEL","TELECOMMUNICATIONS","TEXTILES","TOURISM AND HOSPITALITY"];


        var html="";
        for(var i=0;i<industries.length;i++){
            html += '<option value="'+industries[i]+'">'
        }
        $("#industrylist").html(html);

    }

</script>

</html>