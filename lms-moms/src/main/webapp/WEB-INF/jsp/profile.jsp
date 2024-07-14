<%@ page import="com.example.maxcrm.MaxCrm.Utility.UtilityClass" %><%--
  Created by IntelliJ IDEA.
  User: gurpreet
  Date: 19/12/19
  Time: 11:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="common/title.jsp"></jsp:include>


    <!-- =============================FOR DATATABLES ============================================ -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.min.css"/>

    <!-- =============================FOR CUSTOM ALERTS ============================================ -->
    <script src="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.css"/>
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
    <!-- Pe-icon -->
    <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/helper.css" rel="stylesheet"
          type="text/css"/>

    <!-- Themify icons -->
    <link href="${pageContext.request.contextPath}/assets/themify-icons/themify-icons.css" rel="stylesheet"
          type="text/css"/>
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath}/assets/dist/css/stylecrm.css" rel="stylesheet" type="text/css"/>

    <style>
        .selectrow{
            background-color: #ececec;
            padding: 10px;
            cursor: pointer;
        }
    </style>
</head>
<body class="hold-transition sidebar-mini">
<!--preloader-->
<div id="preloader">
    <div id="status"></div>
</div>
<!-- Site wrapper -->
<div class="wrapper">

    <%-- ===============================================--%>
    <%--    HEADER     --%>
    <jsp:include page="common/header.jsp"></jsp:include>
    <!-- =============================================== -->
    <!-- Left side column. contains the sidebar -->
    <jsp:include page="common/sidebar.jsp"></jsp:include>
    <!-- =============================================== -->
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">


            <div class="buttonexport">

            </div>

        </section>
        <!-- Main content -->
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div  class="col-sm-12 col-md-4">
                    <div id="infodiv" class="card">
                        <div class="card-header">
                            <div class="card-header-headshot"></div>
                        </div>
                        <div class="card-content">
                            <div class="card-content-member text-center">
                                <h4 class="m-t-0" id="fullname"></h4>

                            </div>
                            <div class="card-content-languages">
                                <div class="card-content-languages-group">
                                    <div>
                                        <h4>Gender:</h4>
                                    </div>
                                    <div>
                                        <ul>
                                            <li id="gender">

                                                <div class="fluency fluency-4"></div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="card-content-languages-group">
                                    <div>
                                        <h4>Username:</h4>
                                    </div>
                                    <div>
                                        <ul>
                                            <li id="username"></li>

                                        </ul>
                                    </div>
                                </div>
                                <div class="card-content-languages-group">
                                    <div>
                                        <h4>Email:</h4>
                                    </div>
                                    <div>
                                        <ul>
                                            <li id="email"></li>

                                        </ul>
                                    </div>
                                </div>
                                <div class="card-content-languages-group" style="display: none;">
                                    <div>
                                        <h4>Facebook:</h4>
                                    </div>
                                    <div>
                                        <ul>
                                            <li id="facebook"></li>

                                        </ul>
                                    </div>
                                </div>
                                <div class="card-content-languages-group" style="display: none;">
                                    <div>
                                        <h4>Dob:</h4>
                                    </div>
                                    <div>
                                        <ul>
                                            <li id="dob"></li>

                                        </ul>
                                    </div>
                                </div>
                                <div class="card-content-languages-group">
                                    <div>
                                        <h4>Address:</h4>
                                    </div>
                                    <div>
                                        <ul>
                                            <li id="address"></li>

                                        </ul>
                                    </div>
                                </div>
                                <div class="card-content-languages-group">
                                    <div>
                                        <h4>Department:</h4>
                                    </div>
                                    <div>
                                        <ul>
                                            <li id="department"></li>

                                        </ul>
                                    </div>
                                </div>

                            </div>

                        </div>
                        <div class="card-footer">
                            <div class="card-footer-stats">
                                <div>
                                    <p>Last Login:</p>
                                  <span id="lastlogin"></span>
                                </div>
                                <div>
                                    <p>Status:</p>
                                    <span id="userstatus">350</span>
                                </div>

                            </div>
                            <div class="card-footer-message" id="editBtn">
                                <h4><i class="fa fa-edit"></i> <a href="${pageContext.request.contextPath}/userupdate" style="color: white">Edit Profile</a></h4>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12 col-md-8">

                    <div id="reviewblock" class="review-block">

                        <div class="panel-body">
                            <h3 >
                                <i class="hvr-buzz-out fa fa-history"></i>
                                Login History</h3>
                            <div class="Workslist">
                                <div class="worklistdate">
                                    <table class="table table-hover">
                                        <thead style="background: #afded5">
                                        <tr>
                                            <th>IP Address</th>
                                            <th>Time</th>
                                        </tr>
                                        </thead>
                                     <tbody id="historytbody" style="background: #daf5ed;">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- /.content -->
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer">
        <jsp:include page="common/footer.jsp"></jsp:include>
    </footer>
</div>
<!-- /.wrapper -->
<!-- Start Core Plugins
   =====================================================================-->
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/assets/plugins/jQuery/jquery-1.12.4.min.js"
        type="text/javascript"></script>
<!-- jquery-ui -->
<script src="${pageContext.request.contextPath}/assets/plugins/jquery-ui-1.12.1/jquery-ui.min.js"
        type="text/javascript"></script>
<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<!-- lobipanel -->
<script src="${pageContext.request.contextPath}/assets/plugins/lobipanel/lobipanel.min.js"
        type="text/javascript"></script>
<!-- Pace js -->
<script src="${pageContext.request.contextPath}/assets/plugins/pace/pace.min.js" type="text/javascript"></script>
<!-- CRMadmin frame -->
<script src="${pageContext.request.contextPath}/assets/dist/js/custom.js" type="text/javascript"></script>
<!-- Dashboard js -->
<script src="${pageContext.request.contextPath}/assets/dist/js/dashboard.js" type="text/javascript"></script>
<!-- End Theme label Script=====================================================================-->

<!-- =============================DATA TABLES======================================================-->
<script src="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.min.js"></script>

<!-- ==============FORM SUBMISSION SCRIPT | CREATING A NEW ROLE=================== -->
<script>
    checkEditRight()
    function checkEditRight(){
        var userId = '<%=request.getParameter("userid")%>'
        var loggedUser = '<%=UtilityClass.getCurrentUser().getId()%>'

        if(userId != loggedUser){
            $("#editBtn").hide();
        }
    }

    $('#reviewblock').css("min-height",$('#infodiv').height())
    $('#reviewblock').css("max-height",$('#infodiv').height())
    $.ajax({
        url: "${pageContext.request.contextPath}/api/loginhistory/getbyuserid?id=<%=request.getParameter("userid")%>",
        type: 'GET',
        success: function (json) {
            $('#lastlogin').html(json.loginTiming)
        },
        error: function (err) {


        }
    });

    $.ajax({
        url: "${pageContext.request.contextPath}/api/realtime/realtime?id=<%=request.getParameter("userid")%>",
        type: 'GET',
        success: function (json) {
            var html="";

            for(var i=0;i<json.length;i++)
            {
                html=html+"<div title='Click Here To View'  hidurl='"+json[i].url+"' class='row selectrow'><div class=\"col-sm-9\">\n" +
                    "                                <div class=\"review-block-rate\"><i style='font-size: 30px' class='"+json[i].icon+"'></i>\n" +

                    "                                </div>\n" +
                    "                                <div class=\"review-block-title\">"+json[i].subject+"</div>\n" +
                    "                                <div class=\"review-block-description\">"+json[i].message+" </div>\n" +
                    "                            </div></div>"

            }
            

            $('#reviewblock').html(html)
        },
        error: function (err) {


        }
    });
    $(document).on("click",".selectrow",function () {
        var url=$(this).attr("hidurl")
        window.open("${pageContext.request.contextPath}/"+url)

    })

    $.ajax({
        url: "${pageContext.request.contextPath}/api/user/getuserById?id=<%=request.getParameter("userid")%>",
        type: 'GET',
        success: function (json) {
            if(json.gender=="male")
            {

                $('.card-header-headshot').css("background-image","url('${pageContext.request.contextPath}/assets/dist/img/avatar5.png')")

            }else{

                $('.card-header-headshot').css("background-image","url('${pageContext.request.contextPath}/assets/dist/img/avatar2.png')")

            }

            $('#gender').html(json.gender.toUpperCase())
            $('#username').html(json.username)
            $('#email').html(json.email)
            $('#facebook').html(json.faceBookId)
            $('#dob').html(json.dob)
            $('#address').html(json.address)
            $('#department').html(json.department)
            $('#userstatus').html(json.status.toUpperCase())
            loginHistory(json.id);
        },
        error: function (err) {


        }
    });


    function loginHistory(userId){
            $.ajax({
                url: "${pageContext.request.contextPath}/api/loginhistory/getlasttenhistory?userId="+userId,
                type: 'GET',
                success: function (json) {
                    var html = "";
                   for(var i=0;i<json.length;i++){
                       html = html+"<tr><td> <i class='hvr-buzz-out fa fa-laptop'></i>  "+json[i].ip+"</td><td> <i class='hvr-buzz-out fa fa-clock-o'></i>  "+json[i].loginTiming+"</td></tr>"
                   }

                   $("#historytbody").html(html);
                },
                error: function (err) {
                }
            })

        }
    //=======================================UPLOAD FILE=============================================

</script>
<%--============================ DATA TABLE ENDS HERE =====================================--%>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>