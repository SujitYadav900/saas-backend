<%--<%@ page import="UtilityClass" %>&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: gurpreet--%>
<%--  Date: 19/12/19--%>
<%--  Time: 11:00 AM--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="utf-8">--%>
<%--    <meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>
<%--    <jsp:include page="common/title.jsp"></jsp:include>--%>

<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.min.css"/>--%>

<%--    <!-- =============================FOR CUSTOM ALERTS ============================================ -->--%>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.css"/>--%>
<%--    <script src="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.js"></script>--%>

<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css">--%>
<%--    <!-- Favicon and touch icons -->--%>
<%--    <link rel="shortcut icon" href="${pageContext.request.contextPath}<%=UtilityClass.minLogoUrl%>" type="image/x-icon">--%>
<%--    <!-- Start Global Mandatory Style--%>
<%--       =====================================================================-->--%>
<%--    <!-- jquery-ui css -->--%>
<%--    <link href="${pageContext.request.contextPath}/assets/plugins/jquery-ui-1.12.1/jquery-ui.min.css" rel="stylesheet"--%>
<%--          type="text/css"/>--%>
<%--    <!-- Bootstrap -->--%>
<%--    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet"--%>
<%--          type="text/css"/>--%>
<%--    <!-- Bootstrap rtl -->--%>
<%--    <!--<link href="assets/bootstrap-rtl/bootstrap-rtl.min.css" rel="stylesheet" type="text/css"/>-->--%>
<%--    <!-- Lobipanel css -->--%>
<%--    <link href="${pageContext.request.contextPath}/assets/plugins/lobipanel/lobipanel.min.css" rel="stylesheet"--%>
<%--          type="text/css"/>--%>
<%--    <!-- Pace css -->--%>
<%--    <link href="${pageContext.request.contextPath}/assets/plugins/pace/flash.css" rel="stylesheet" type="text/css"/>--%>
<%--    <!-- Font Awesome -->--%>
<%--    <!-- <link href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/> -->--%>
<%--    <!-- Pe-icon -->--%>
<%--    <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet"--%>
<%--          type="text/css"/>--%>
<%--    <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/helper.css" rel="stylesheet"--%>
<%--          type="text/css"/>--%>
<%--    <link href="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.css" rel="stylesheet"--%>
<%--          type="text/css"/>--%>
<%--    <!-- Themify icons -->--%>
<%--    <link href="${pageContext.request.contextPath}/assets/themify-icons/themify-icons.css" rel="stylesheet"--%>
<%--          type="text/css"/>--%>
<%--    <!-- End Global Mandatory Style--%>
<%--       =====================================================================-->--%>
<%--    <!-- Start page Label Plugins--%>
<%--       =====================================================================-->--%>
<%--    <!-- Emojionearea -->--%>
<%--    <link href="${pageContext.request.contextPath}/assets/plugins/emojionearea/emojionearea.min.css" rel="stylesheet"--%>
<%--          type="text/css"/>--%>
<%--    <!-- Monthly css -->--%>
<%--    <link href="${pageContext.request.contextPath}/assets/plugins/monthly/monthly.css" rel="stylesheet"--%>
<%--          type="text/css"/>--%>
<%--    <!-- End page Label Plugins--%>
<%--       =====================================================================-->--%>
<%--    <!-- Start Theme Layout Style--%>
<%--       =====================================================================-->--%>
<%--    <!-- Theme style -->--%>
<%--    <link href="${pageContext.request.contextPath}/assets/dist/css/stylecrm.css" rel="stylesheet" type="text/css"/>--%>

<%--    <!-- Theme style rtl -->--%>
<%--    <!--<link href="assets/dist/css/stylecrm-rtl.css" rel="stylesheet" type="text/css"/>-->--%>
<%--    <!-- End Theme Layout Style--%>
<%--       =====================================================================-->--%>
<%--    <style>--%>
<%--        .validationFail {--%>
<%--            background-color: #ff00005e !important;--%>
<%--        }--%>
<%--        .downloadfilecls{--%>
<%--            cursor: pointer;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body class="hold-transition sidebar-mini">--%>
<%--<!--preloader-->--%>
<%--<div id="preloader">--%>
<%--    <div id="status"></div>--%>
<%--</div>--%>
<%--<!-- Site wrapper -->--%>
<%--<div class="wrapper">--%>
<%--    <jsp:include page="common/header.jsp"></jsp:include>--%>
<%--    <!-- =============================================== -->--%>
<%--    <!-- Left side column. contains the sidebar -->--%>
<%--    <jsp:include page="common/sidebar.jsp"></jsp:include>--%>
<%--    <!-- =============================================== -->--%>
<%--    <!-- Content Wrapper. Contains page content -->--%>
<%--    <div class="content-wrapper">--%>
<%--        <!-- Content Header (Page header) -->--%>
<%--        <section class="content-header" id="addUserHeader">--%>
<%--            <div class="header-icon">--%>
<%--                <i class="fa fa-file-text"></i>--%>
<%--            </div>--%>
<%--            <div class="header-title" >--%>
<%--                <h1>User</h1>--%>
<%--                <button id="addnewuser" class="btn btn-add">Add User <i class="hvr-buzz-out fa fa-plus"></i></button>--%>
<%--            </div>--%>
<%--        </section>--%>
<%--        <!-- Main content -->--%>
<%--        <section class="content">--%>

<%--            <div class="row">--%>
<%--                <div class="col-sm-12 col-md-12">--%>
<%--                    <div class="panel panel-bd lobidrag">--%>
<%--                        <div class="panel-heading">--%>
<%--                            <div class="panel-title">--%>
<%--                                <h4>This is page content</h4>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="panel-body">--%>


<%--                            <div style="display: none" id="addnewdiv">--%>

<%--                                <form id="createnewuserform" method="post" class="col-sm-12">--%>
<%--                                    &lt;%&ndash;                                    <div class="form-group">&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                        <label>Sex</label><br>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                        <label class="radio-inline"><input name="gender" value="Male" checked="checked"&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                                                           type="radio">Male</label>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                        <label class="radio-inline"><input name="gender" value="Female" type="radio">Female</label>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                    </div>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                    <div class="form-check">&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                        <label>Status</label><br>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                        <label class="radio-inline">&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                            <input type="radio" name="status" value="Active"&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                                   checked="checked">Active</label>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                        <label class="radio-inline"><input  type="radio" name="status" value="Inactive">Inactive</label>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                    </div>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                    <br>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                    <div class="form-check">&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                        <label>Two Step Authentication</label><br>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                        <label class="radio-inline">&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                            <input type="radio" name="twoStepAuthentication" value="true"&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                                   checked="checked">Yes</label>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                        <label class="radio-inline"><input type="radio" name="twoStepAuthentication"&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                                                           value="false">No</label>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                    </div>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                    <br>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                    <div class="form-check">&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                        <label>Send Email On Creation</label><br>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                        <label class="radio-inline">&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                            <input type="radio" name="sendMailOnCreation" value="true"&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                                   checked="checked">Yes</label>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                        <label class="radio-inline"><input type="radio" name="sendMailOnCreation"&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                                                           value="false">No</label>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                    </div>&ndash;%&gt;--%>

<%--                                    <!-- Radio Buttons for status-->--%>
<%--                                    <div class="form-check">--%>
<%--                                        <label class="control-label">Sex</label>--%>
<%--                                        <br>--%>
<%--                                        <label class="form-check-label">--%>
<%--                                            <input type="radio" class="form-check-input" name="gender" value="male" checked>Male--%>
<%--                                        </label>--%>
<%--                                        <label class="form-check-label">--%>
<%--                                            <input type="radio" class="form-check-input" name="gender" value="female">Female--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                    <br>--%>
<%--                                    <!--  status-->--%>
<%--                                    <div class="form-check">--%>
<%--                                        <label class="control-label">Status</label>--%>
<%--                                        <br>--%>
<%--                                        <label class="form-check-label">--%>
<%--                                            <input type="radio" class="form-check-input" name="status" value="active" checked>Active--%>
<%--                                        </label>--%>
<%--                                        <label class="form-check-label">--%>
<%--                                            <input type="radio" class="form-check-input" name="status" value="inactive">Inactive--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                    <br>--%>
<%--                                    <!-- twoStepAuthentication-->--%>
<%--                                    <div class="form-check">--%>
<%--                                        <label class="control-label">Two Step Authentication</label>--%>
<%--                                        <br>--%>
<%--                                        <label class="form-check-label">--%>
<%--                                            <input type="radio" class="form-check-input" name="twoStepAuthentication" value="true" checked>Yes--%>
<%--                                        </label>--%>
<%--                                        <label class="form-check-label">--%>
<%--                                            <input type="radio" class="form-check-input" name="twoStepAuthentication" value="false">No--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                    <br>--%>
<%--                                    <!-- sendMailOnCreation -->--%>
<%--                                    <div class="form-check">--%>
<%--                                        <label class="control-label">Send Email On Creation</label>--%>
<%--                                        <br>--%>
<%--                                        <label class="form-check-label">--%>
<%--                                            <input type="radio" class="form-check-input" name="sendMailOnCreation" value="true" checked>Yes--%>
<%--                                        </label>--%>
<%--                                        <label class="form-check-label">--%>
<%--                                            <input type="radio" class="form-check-input" name="sendMailOnCreation" value="false">No--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                    <br>--%>
<%--                                    <div class="form-group">--%>
<%--                                        <label>First Name</label>--%>
<%--                                        <input type="text" minlength="4" maxlength="100" name="firstName" id="firstName" class="form-control"--%>
<%--                                               placeholder="Enter First Name" required>--%>
<%--                                    </div>--%>
<%--                                    <div class="form-group">--%>
<%--                                        <label>Last Name</label>--%>
<%--                                        <input name="lastName" id="lastName" minlength="4" maxlength="100" type="text" class="form-control"--%>
<%--                                               placeholder="Enter last Name">--%>
<%--                                    </div>--%>
<%--                                    <div class="form-group">--%>
<%--                                        <label>User Name</label>--%>
<%--                                        <input type="text" name="username" id="username" class="form-control"--%>
<%--                                               placeholder="Enter Username"--%>
<%--                                               required>--%>
<%--                                    </div>--%>
<%--                                    <div class="form-group">--%>
<%--                                        <label>Password</label>--%>
<%--                                        <input type="text" name="password" id="password" class="form-control"--%>
<%--                                               placeholder="Enter Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"--%>
<%--                                               required>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group">--%>
<%--                                        <label>Email</label>--%>
<%--                                        <input name="email" type="email" id="email" class="form-control" placeholder="Enter Email"--%>
<%--                                               required>--%>
<%--                                    </div>--%>
<%--                                    <div class="form-group">--%>
<%--                                        <label>Mobile</label>--%>
<%--                                        <input name="mobile" pattern="[0-9]{10}"  id="mobile" type="tel" class="form-control"--%>
<%--                                               placeholder="Enter Mobile"--%>
<%--                                               required>--%>
<%--                                    </div>--%>


<%--                                    <div class="form-group">--%>
<%--                                        <label>Facebook Id</label>--%>
<%--                                        <input name="faceBookId" id="facebookId" type="text" class="form-control"--%>
<%--                                               placeholder="Enter Facebook details">--%>
<%--                                    </div>--%>
<%--                                    <div class="form-group">--%>
<%--                                        <label>Date of Birth</label>--%>
<%--                                        <input name="dob" autocomplete="false" id="dob" type="text" autocomplete="off" class="form-control"--%>
<%--                                               placeholder="Enter Date...">--%>
<%--                                    </div>--%>
<%--                                    <div class="form-group">--%>
<%--                                        <label>Address</label>--%>
<%--                                        <textarea name="address" id="address" class="form-control" rows="3" ></textarea>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group">--%>
<%--                                        <label>Department</label>--%>
<%--                                        <select id="department" name="department" required class="form-control">--%>

<%--                                        </select>--%>
<%--                                    </div>--%>
<%--                                    <div class="form-group">--%>
<%--                                        <label>Documents upload</label>--%>
<%--                                        <div class="table-responsive">--%>
<%--                                            <table class="table table-bordered table-striped table-hover">--%>
<%--                                                <thead>--%>
<%--                                                <tr>--%>
<%--                                                    <th>File</th>--%>
<%--                                                    <th><i id="addnewrow" title="Click Here To Add Document"--%>
<%--                                                           class="fa fa-plus-circle "></i></th>--%>
<%--                                                </tr>--%>
<%--                                                </thead>--%>
<%--                                                <tbody id="attachmenttbody"></tbody>--%>
<%--                                            </table>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                    <div class="form-group">--%>
<%--                                        <label>Reports To</label>--%>
<%--                                        <div class="table-responsive">--%>
<%--                                            <table class="table table-bordered table-striped table-hover">--%>
<%--                                                <thead>--%>
<%--                                                <tr>--%>
<%--                                                    <th>Department</th>--%>
<%--                                                    <th>User</th>--%>
<%--                                                    <th><i id="addnewrowreportto"--%>
<%--                                                           title="Click Here To add new Report To"--%>
<%--                                                           class="fa fa-plus-circle "></i></th>--%>
<%--                                                </tr>--%>
<%--                                                </thead>--%>
<%--                                                <tbody id="reportstotbody"></tbody>--%>
<%--                                            </table>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>

<%--                                    <input type="hidden" name="id" id="id">--%>

<%--                                    <div class="reset-button">--%>
<%--                                        <button id="createbtn" type="submit" class="btn btn-success">Create</button>--%>
<%--                                        <button id="cancelbtn" type="button" class="btn btn-warning">Cancel</button>--%>

<%--                                    </div>--%>
<%--                                </form>--%>
<%--                            </div>--%>
<%--                            <div id="tablediv">--%>
<%--                                <div>--%>
<%--                                    <div class="table-responsive">--%>
<%--                                        <table id="table" class="table table-bordered table-striped table-hover">--%>
<%--                                            <thead>--%>
<%--                                            <tr class="info">--%>

<%--                                                <th>Full name</th>--%>
<%--                                                <th>Username</th>--%>
<%--                                                <th>Email</th>--%>
<%--                                                <th>Mobile</th>--%>
<%--                                                <th>Facebook</th>--%>
<%--                                                <th>Dob</th>--%>
<%--                                                <th>Department</th>--%>
<%--                                                <th>Info</th>--%>
<%--                                                <th>Action</th>--%>
<%--                                            </tr>--%>
<%--                                            </thead>--%>
<%--                                            <tbody>--%>

<%--                                            </tbody>--%>
<%--                                        </table>--%>
<%--                                    </div>--%>
<%--                                </div>--%>

<%--                            </div>--%>


<%--                        </div>--%>
<%--                        <div class="panel-footer">--%>
<%--                            This is standard panel footer--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </section>--%>
<%--        <!-- /.content -->--%>
<%--    </div>--%>
<%--    <!-- /.content-wrapper -->--%>
<%--    <footer class="main-footer">--%>
<%--        <div class="pull-right hidden-xs"><b>Version</b> 1.0</div>--%>
<%--        <strong>Copyright &copy; 2016-2017 <a href="#">thememinister</a>.</strong> All rights reserved.--%>
<%--    </footer>--%>
<%--</div>--%>
<%--<!-- ./wrapper -->--%>
<%--<!-- Start Core Plugins--%>
<%--   =====================================================================-->--%>
<%--<!-- jQuery -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/jQuery/jquery-1.12.4.min.js"--%>
<%--        type="text/javascript"></script>--%>
<%--<!-- jquery-ui -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/jquery-ui-1.12.1/jquery-ui.min.js"--%>
<%--        type="text/javascript"></script>--%>
<%--<!-- Bootstrap -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>--%>
<%--<!-- lobipanel -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/lobipanel/lobipanel.min.js"--%>
<%--        type="text/javascript"></script>--%>
<%--<!-- Pace js -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/pace/pace.min.js" type="text/javascript"></script>--%>
<%--<!-- SlimScroll -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/slimScroll/jquery.slimscroll.min.js"--%>
<%--        type="text/javascript"></script>--%>
<%--<!-- FastClick -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/fastclick/fastclick.min.js"--%>
<%--        type="text/javascript"></script>--%>
<%--<!-- CRMadmin frame -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/dist/js/custom.js" type="text/javascript"></script>--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.min.js"--%>
<%--        type="text/javascript"></script>--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.min.js"--%>
<%--        type="text/javascript"></script>--%>
<%--<!-- End Core Plugins--%>
<%--   =====================================================================-->--%>
<%--<!-- Start Theme label Script--%>
<%--   =====================================================================-->--%>
<%--<!-- Dashboard js -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/dist/js/dashboard.js" type="text/javascript"></script>--%>
<%--<!-- End Theme label Script--%>
<%--   =====================================================================-->--%>
<%--<script>--%>
<%--    //================================================================== DATA TABLE ===========================================--%>
<%--    var table = $('#table').DataTable({--%>
<%--        ajax: {--%>
<%--            url: '${pageContext.servletContext.contextPath}/api/user/getalluser',--%>
<%--            dataSrc: '',--%>
<%--            "data": {--%>
<%--                "flag": 1,--%>

<%--            }--%>
<%--        },--%>
<%--        "columns": [--%>

<%--            {--%>
<%--                "mRender": function (data, type, row) {--%>


<%--                    return "<a hidatr='" + JSON.stringify(row) + "' style='display: none'></a><span>" + row.firstName + " " + row.lastName + "</span>";--%>


<%--                }--%>
<%--            },--%>

<%--            {"data": "username"},--%>
<%--            {"data": "email"},--%>
<%--            {"data": "mobile"},--%>
<%--            {"data": "faceBookId"},--%>
<%--            {"data": "dob"},--%>
<%--            {"data": "department"},--%>
<%--            {--%>
<%--                "mRender": function (data, type, row) {--%>
<%--                    var html = "";--%>
<%--                    if (row.gender == "male") {--%>
<%--                        html = html + "<i style='    margin-left: 5px;  font-size: 18px;color: black' title='Male User' class=\"hvr-buzz-out fa fa-male\"></i>";--%>
<%--                    } else {--%>
<%--                        html = html + "<i style='    margin-left: 5px;  font-size: 18px;color: deeppink'  title='Female User' class=\"hvr-buzz-out fa fa-female\"></i>";--%>
<%--                    }--%>
<%--                    if (row.twoStepAuthentication == true) {--%>
<%--                        html = html + "<i style='    margin-left: 5px;  font-size: 18px;color: greenyellow'  title='Two Step Authentication Enabled' class=\"hvr-buzz-out fa fa-lock\"></i>";--%>
<%--                    } else {--%>

<%--                        html = html + "<i style='    margin-left: 5px;  font-size: 18px;color: red' title='Two Step Authentication Disabled' class=\"hvr-buzz-out fa fa-unlock\"></i>";--%>
<%--                    }--%>
<%--                    if (row.status == "active") {--%>
<%--                        html = html + "<i style='    margin-left: 5px;  font-size: 18px;color: greenyellow'  title='Active' class=\"hvr-buzz-out fa fa-check\"></i>";--%>
<%--                    } else {--%>

<%--                        html = html + "<i style='    margin-left: 5px;  font-size: 18px;color: red' title='Not Active' class=\"hvr-buzz-out fa fa-ban\"></i>";--%>
<%--                    }--%>

<%--                    return html;--%>
<%--                }--%>
<%--            },--%>

<%--            {--%>
<%--                "mRender": function (data, type, row) {--%>

<%--                    return "<button title='Click Here To Edit User' type=\"button\" class=\"edituser btn btn-add btn-sm\" ><i class=\"fa fa-pencil\"></i>";--%>
<%--                }--%>
<%--            }--%>

<%--        ]--%>
<%--        , "initComplete": function (settings, json) {--%>
<%--            $('#tablediv').fadeIn();--%>
<%--            $('#loading').fadeOut();--%>
<%--        },--%>
<%--        "ordering": false--%>
<%--    });--%>

<%--    function reloadTable(){--%>
<%--        table.ajax.reload();--%>
<%--    }--%>


<%--    //=======================================HIDE DATA TABLE==================================--%>

<%--    $('#addnewuser').click(function () {--%>
<%--        //clearData();--%>
<%--        $('#tablediv').css("display", "none");--%>
<%--        $('#addnewdiv').fadeIn();--%>
<%--        $('#addUserHeader').fadeOut();--%>
<%--    });--%>

<%--    //=====================================HIDE FORM AND BRING DATA TABLE BACK =====================================--%>
<%--    $('#cancelbtn').click(function () {--%>
<%--        clearData();--%>
<%--        $('#addnewuser').css("display", "block");--%>
<%--        $('#addnewdiv').css("display", "none");--%>
<%--        $('#toTop').trigger("click")--%>
<%--        $('#tablediv').fadeIn();--%>
<%--        $('#addUserHeader').fadeIn();--%>
<%--        $('#createbtn').html("Create")--%>
<%--    });--%>
<%--    //======================================= DATE PICKER =====================================--%>
<%--    $('#dob').datepicker({--%>
<%--        changeMonth: true,--%>
<%--        changeYear: true,--%>
<%--        yearRange: "1930:2025"--%>
<%--    });--%>

<%--    loaddepartment("${pageContext.request.contextPath}/api/roles/getroles", '#department');--%>
<%--    $('#preloader').css("display", "none");--%>

<%--    $('#addnewrow').click(function () {--%>
<%--        addNewRow();--%>
<%--    });--%>

<%--    $('#addnewrowreportto').click(function () {--%>
<%--        addNewRowReportTo();--%>
<%--    });--%>
<%--    //====================================================== UPLOAD FILES ====================================--%>
<%--    $(document).on("change", ".fileuploadinput", function () {--%>
<%--        var ths = $(this);--%>
<%--        var form = $(this).closest("form")[0];--%>

<%--        var data = new FormData(form);--%>
<%--        data.append("CustomField", "This is some extra data, testing");--%>
<%--        $('#preloader').css("display", "block");--%>
<%--        $.ajax({--%>
<%--            type: "POST",--%>
<%--            enctype: 'multipart/form-data',--%>
<%--            url: "${pageContext.request.contextPath}/api/file/insertfile",--%>
<%--            data: data,--%>
<%--            processData: false,--%>
<%--            contentType: false,--%>
<%--            cache: false,--%>
<%--            timeout: 600000,--%>
<%--            success: function (data) {--%>

<%--                $('#preloader').fadeOut();--%>
<%--                ths.closest("tr").html("<td class='downloadfilecls' attrhid='" + data.id + "' title='" + data.createAt + "'>" + data.name + "</td><td><i title='Click Here To Delete This Document' class='deletefile hvr-buzz-out fa fa-trash'></i></td>")--%>

<%--            },--%>
<%--            error: function (err) {--%>
<%--                $('#preloader').fadeOut();--%>
<%--                swal({--%>
<%--                    title: 'Error',--%>
<%--                    text: err.responseJSON.message,--%>
<%--                    type: 'error',--%>
<%--                    confirmButtonText: 'Ok'--%>
<%--                })--%>

<%--            }--%>
<%--        });--%>
<%--    });--%>
<%--    function populate(frm, data) {--%>
<%--        $.each(data, function(key, value){--%>
<%--            $('[name='+key+']', frm).val(value);--%>
<%--        });--%>
<%--    }--%>
<%--    //================================================= EDIT USER ===========================================--%>
<%--    $(document).on("click",".edituser",function () {--%>

<%--        $('#createbtn').html("Update")--%>
<%--        var json=JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));--%>


<%--        $('#id').val(json.id);--%>
<%--        $("input[name='gender'][value="+json.gender+"]").prop("checked",true);--%>
<%--        $("input[name='status'][value="+json.status+"]").prop("checked",true);--%>
<%--        $("input[name='twoStepAuthentication'][value="+json.twoStepAuthentication+"]").prop("checked",true);--%>
<%--        $("input[name='sendMailOnCreation'][value="+json.sendMailOnCreation+"]").prop("checked",true);--%>
<%--        $('#firstName').val(json.firstName);--%>
<%--        $('#lastName').val(json.lastName);--%>
<%--        $('#username').val(json.username);--%>
<%--        $('#password').val(json.password);--%>
<%--        $('#email').val(json.email);--%>
<%--        $('#mobile').val(json.mobile);--%>
<%--        $('#facebookId').val(json.faceBookId);--%>
<%--        $('#dob').val(json.dob);--%>
<%--        $('#address').val(json.address);--%>
<%--        $('#department').val(json.department);--%>

<%--        $('#addnewuser').trigger("click")--%>
<%--        var attachmentlist=JSON.parse(json.attachementList);--%>
<%--        var reportto=JSON.parse(json.reportTo);--%>
<%--        var html="";--%>
<%--        for(var i=0;i<attachmentlist.length;i++)--%>
<%--        {--%>
<%--            html=html+"<tr ><td class='downloadfilecls' attrhid='" + attachmentlist[i].id + "' title='" + json.createAt + "'>" + attachmentlist[i].name + "</td><td><i title='Click Here To Delete This Document' class='deletefile hvr-buzz-out fa fa-trash'></i></td></tr>"--%>
<%--        }--%>
<%--        $('#attachmenttbody').html(html)--%>

<%--        html="";--%>
<%--        for(var i=0;i<reportto.length;i++)--%>
<%--        {--%>
<%--            html=html+"<tr editcls='1'><td attrhid='0' style='width: 80%'>"+reportto[i].department+" </td><td attrhid='"+reportto[i].userid+"' style='width: 80%'>"+reportto[i].username+"</td><td style='width: 20%'><i title='Click Here To Delete This Document' class='deletereportto hvr-buzz-out fa fa-trash'></i></td></tr>"--%>
<%--        }--%>
<%--        $('#reportstotbody').html(html)--%>


<%--        $('#createbtn').html("Update")--%>
<%--        $('#addUserHeader').fadeOut();--%>

<%--    })--%>
<%--    //===================================================== DELETE REPORT TO FILED FROM FORM =============================================--%>
<%--    $(document).on("click", ".deletereportto", function () {--%>
<%--        $(this).closest("tr").remove();--%>
<%--    });--%>

<%--    // ===================================================== DELETE DOCUMENT FIELD FROM FORM ============================================--%>
<%--    $(document).on("click", ".deletefile", function () {--%>
<%--        var hidid = $(this).closest("tr").find("td").eq(0).attr("attrhid");--%>
<%--        var ths = $(this);--%>
<%--        if (hidid == 0) {--%>
<%--            $(this).closest("tr").remove();--%>

<%--        } else {--%>
<%--            swal({--%>
<%--                    title: "Are you sure?",--%>
<%--                    text: "Delete the selected file?",--%>
<%--                    type: "warning",--%>
<%--                    showCancelButton: true,--%>
<%--                    confirmButtonColor: '#DD6B55',--%>
<%--                    confirmButtonText: 'Yes, I am sure!',--%>
<%--                    cancelButtonText: "No, cancel it!",--%>
<%--                    closeOnConfirm: false,--%>
<%--                    closeOnCancel: false--%>
<%--                },--%>
<%--                function (isConfirm) {--%>

<%--                    if (isConfirm) {--%>

<%--                        $.ajax({--%>
<%--                            type: "DELETE",--%>
<%--                            contentType: "application/json",--%>
<%--                            url: "${pageContext.servletContext.contextPath}/api/file/delete?id=" + hidid,--%>
<%--                            success: function (json) {--%>


<%--                            },--%>
<%--                            error: function (err) {--%>

<%--                            }--%>

<%--                        });--%>

<%--                        ths.closest("tr").remove();--%>

<%--                    }--%>
<%--                    swal.close()--%>

<%--                });--%>

<%--        }--%>
<%--    });--%>
<%--    $(document).on("change", ".departmentreportto", function () {--%>
<%--        var dep = $(this).val();--%>
<%--        var ths = $(this);--%>
<%--        if (dep == 0) {--%>

<%--        } else {--%>
<%--            $.ajax({--%>
<%--                type: "GET",--%>
<%--                contentType: "application/json",--%>
<%--                url: "${pageContext.servletContext.contextPath}/api/user/getuserbydepartment?department=" + dep,--%>
<%--                success: function (json) {--%>
<%--                    var html = "<option value='0'>---Select All---</option>";--%>
<%--                    for (var i = 0; i < json.length; i++) {--%>
<%--                        html = html + "<option value='" + json[i].id + "'>" + json[i].username + "</option>"--%>

<%--                    }--%>
<%--                    ths.closest("tr").find("td").eq(1).find("select").html(html)--%>


<%--                },--%>
<%--                error: function (err) {--%>

<%--                }--%>

<%--            })--%>
<%--        }--%>
<%--    });--%>
<%--    $(document).on("click",".downloadfilecls",function () {--%>
<%--        var id=$(this).attr("attrhid");--%>
<%--        window.open("${pageContext.servletContext.contextPath}/api/file/downloadfile?id="+id)--%>

<%--    })--%>
<%--    function clearData() {--%>

<%--        $('#attachmenttbody').html("");--%>
<%--        $('#reportstotbody').html("");--%>
<%--        $('#createnewuserform').trigger("reset");--%>
<%--        $("input[name='gender']:checked").val("male");--%>
<%--        $("input[name='status']:checked").val("active");--%>
<%--        $("input[name='twoStepAuthentication']:checked").val("true")--%>
<%--        $("input[name='sendMailOnCreation']:checked").val("true")--%>
<%--        $("#createbtn").html("Create")--%>

<%--    }--%>

<%--    //=================================================== FORM SUBMISSION ===========================================--%>
<%--    $('#createnewuserform').submit(function (e) {--%>
<%--        e.preventDefault();--%>
<%--        var url = "";--%>
<%--        if($('#createbtn').html() == 'Create'){--%>

<%--            var url = "${pageContext.request.contextPath}/api/user/insertuser";--%>

<%--            //===================== EXTRACT ATTACHED DOCUMENT DETAILS ====================================--%>
<%--            var tr = null;--%>
<%--            var hidid = null;--%>
<%--            var documentarr = [];--%>
<%--            var documentmap = {};--%>
<%--            for (var i = 0; i < $('#attachmenttbody').find("tr").length; i++) {--%>
<%--                documentmap = {};--%>
<%--                tr = $('#attachmenttbody').find("tr").eq(i);--%>
<%--                var hidid = tr.find("td").eq(0).attr("attrhid");--%>

<%--                if (hidid == 0) {--%>
<%--                    tr.addClass("validationFail");--%>
<%--                    return;--%>
<%--                } else {--%>
<%--                    tr.removeClass("validationFail")--%>
<%--                }--%>
<%--                documentmap["id"] = hidid;--%>
<%--                documentmap["name"] = tr.find("td").eq(0).html();--%>
<%--                documentarr.push(documentmap);--%>
<%--            }--%>

<%--            //================================================== EXTRACT REPORTS TO FEILD FATA =========================--%>

<%--            var arrreporttp = [];--%>
<%--            var mapreport = {};--%>
<%--            var department = null;--%>
<%--            var userid = null;--%>
<%--            var username = null;--%>

<%--            for (var i = 0; i < $('#reportstotbody').find("tr").length; i++) {--%>

<%--                tr = $('#reportstotbody').find("tr").eq(i);--%>
<%--                if(tr.attr("editcls")==1)--%>
<%--                {--%>

<%--                    department = tr.find("td").eq(0).text();--%>
<%--                    userid = tr.find("td").eq(1).attr("attrhid")--%>
<%--                    username = tr.find("td").eq(1).text();--%>
<%--                }--%>
<%--                else{--%>
<%--                    department = tr.find("td").eq(0).find("select").val();--%>
<%--                    userid = tr.find("td").eq(1).find("select").val();--%>
<%--                    username = tr.find("td").eq(1).find("select").find('option:selected').text()--%>
<%--                }--%>

<%--                if (department == 0 || userid == -1) {--%>
<%--                    tr.addClass("validationFail");--%>
<%--                    return;--%>
<%--                } else {--%>
<%--                    tr.removeClass("validationFail")--%>
<%--                }--%>
<%--                if (userid == 0) {--%>
<%--                    var len = tr.find("td").eq(1).find("select").find("option").length;--%>
<%--                    for (var m = 0; m < len; m++) {--%>
<%--                        mapreport = {};--%>
<%--                        mapreport["department"] = department;--%>
<%--                        mapreport["userid"] = tr.find("td").eq(1).find("select").find("option").eq(m).val();--%>
<%--                        mapreport["username"] = tr.find("td").eq(1).find("select").find("option").eq(m).text();--%>
<%--                        if (mapreport["userid"] != 0) {--%>
<%--                            arrreporttp.push(mapreport);--%>
<%--                        }--%>
<%--                    }--%>
<%--                } else {--%>
<%--                    mapreport = {};--%>
<%--                    mapreport["department"] = department;--%>
<%--                    mapreport["userid"] = userid;--%>
<%--                    mapreport["username"] = username;--%>
<%--                    arrreporttp.push(mapreport);--%>
<%--                }--%>
<%--            }--%>

<%--            var map = {--%>
<%--                gender: $("input[name='gender']:checked").val(),--%>
<%--                status: $("input[name='status']:checked").val(),--%>
<%--                twoStepAuthentication: $("input[name='twoStepAuthentication']:checked").val(),--%>
<%--                sendMailOnCreation: $("input[name='sendMailOnCreation']:checked").val(),--%>

<%--                firstName: $('#firstName').val(),--%>
<%--                lastName: $('#lastName').val(),--%>
<%--                username: $('#username').val(),--%>
<%--                password: $('#password').val(),--%>
<%--                email: $('#email').val(),--%>
<%--                mobile: $('#mobile').val(),--%>
<%--                faceBookId: $('#facebookId').val(),--%>
<%--                dob: $('#dob').val(),--%>
<%--                address: $('#address').val(),--%>
<%--                department: $('#department').val(),--%>
<%--                attachementList : JSON.stringify(documentarr),--%>
<%--                reportTo: JSON.stringify(arrreporttp)--%>
<%--            };--%>
<%--        }--%>

<%--        //================================================ UPDATE USER ======================================--%>
<%--        else{--%>
<%--            var url = "${pageContext.request.contextPath}/api/user/updateuser";--%>
<%--            //===================== EXTRACT ATTACHED DOCUMENT DETAILS ====================================--%>
<%--            var tr = null;--%>
<%--            var hidid = null;--%>
<%--            var documentarr = [];--%>
<%--            var documentmap = {};--%>
<%--            for (var i = 0; i < $('#attachmenttbody').find("tr").length; i++) {--%>
<%--                documentmap = {};--%>
<%--                tr = $('#attachmenttbody').find("tr").eq(i);--%>
<%--                var hidid = tr.find("td").eq(0).attr("attrhid");--%>

<%--                if (hidid == 0) {--%>
<%--                    tr.addClass("validationFail");--%>
<%--                    return;--%>
<%--                } else {--%>
<%--                    tr.removeClass("validationFail")--%>
<%--                }--%>
<%--                documentmap["id"] = hidid;--%>
<%--                documentmap["name"] = tr.find("td").eq(0).html();--%>
<%--                documentarr.push(documentmap);--%>
<%--            }--%>

<%--            //================================================== EXTRACT REPORTS TO FEILD FATA =========================--%>

<%--            var arrreporttp = [];--%>
<%--            var mapreport = {};--%>
<%--            var department = null;--%>
<%--            var userid = null;--%>
<%--            var username = null;--%>

<%--            for (var i = 0; i < $('#reportstotbody').find("tr").length; i++) {--%>

<%--                tr = $('#reportstotbody').find("tr").eq(i);--%>
<%--                if(tr.attr("editcls")==1)--%>
<%--                {--%>

<%--                    department = tr.find("td").eq(0).text();--%>
<%--                    userid = tr.find("td").eq(1).attr("attrhid")--%>
<%--                    username = tr.find("td").eq(1).text();--%>
<%--                }--%>
<%--                else{--%>
<%--                    department = tr.find("td").eq(0).find("select").val();--%>
<%--                    userid = tr.find("td").eq(1).find("select").val();--%>
<%--                    username = tr.find("td").eq(1).find("select").find('option:selected').text()--%>
<%--                }--%>

<%--                if (department == 0 || userid == -1) {--%>
<%--                    tr.addClass("validationFail");--%>
<%--                    return;--%>
<%--                } else {--%>
<%--                    tr.removeClass("validationFail")--%>
<%--                }--%>
<%--                if (userid == 0) {--%>
<%--                    var len = tr.find("td").eq(1).find("select").find("option").length;--%>
<%--                    for (var m = 0; m < len; m++) {--%>
<%--                        mapreport = {};--%>
<%--                        mapreport["department"] = department;--%>
<%--                        mapreport["userid"] = tr.find("td").eq(1).find("select").find("option").eq(m).val();--%>
<%--                        mapreport["username"] = tr.find("td").eq(1).find("select").find("option").eq(m).text();--%>
<%--                        if (mapreport["userid"] != 0) {--%>
<%--                            arrreporttp.push(mapreport);--%>
<%--                        }--%>
<%--                    }--%>
<%--                } else {--%>
<%--                    mapreport = {};--%>
<%--                    mapreport["department"] = department;--%>
<%--                    mapreport["userid"] = userid;--%>
<%--                    mapreport["username"] = username;--%>
<%--                    arrreporttp.push(mapreport);--%>
<%--                }--%>
<%--            }--%>

<%--            var map = {--%>
<%--                id:$('#id').val(),--%>
<%--                gender: $("input[name='gender']:checked").val(),--%>
<%--                status: $("input[name='status']:checked").val(),--%>
<%--                twoStepAuthentication: $("input[name='twoStepAuthentication']:checked").val(),--%>
<%--                sendMailOnCreation: $("input[name='sendMailOnCreation']:checked").val(),--%>
<%--                firstName: $('#firstName').val(),--%>
<%--                lastName: $('#lastName').val(),--%>
<%--                username: $('#username').val(),--%>
<%--                password: $('#password').val(),--%>
<%--                email: $('#email').val(),--%>
<%--                mobile: $('#mobile').val(),--%>
<%--                faceBookId: $('#facebookId').val(),--%>
<%--                dob: $('#dob').val(),--%>
<%--                address: $('#address').val(),--%>
<%--                department: $('#department').val(),--%>
<%--                attachementList: JSON.stringify(documentarr),--%>
<%--                reportTo: JSON.stringify(arrreporttp)--%>

<%--            };--%>

<%--        }--%>

<%--        $.ajax({--%>
<%--            type: 'POST',--%>
<%--            data: JSON.stringify(map),--%>
<%--            contentType: 'application/json',--%>
<%--            url: url,--%>
<%--            success: function (data) {--%>

<%--                if($('#createBtn').html() == 'Create'){--%>

<%--                    swal({--%>
<%--                        title: "Success!",--%>
<%--                        text: "Successfully created User",--%>
<%--                        icon: "success",--%>
<%--                        button: "Ok!",--%>
<%--                    });--%>
<%--                }--%>

<%--                else{--%>

<%--                    swal({--%>
<%--                        title: "Success!",--%>
<%--                        text: "Successfully Updated User",--%>
<%--                        icon: "success",--%>
<%--                        button: "Ok!",--%>
<%--                    });--%>

<%--                }--%>

<%--                reloadTable();--%>


<%--                $('#addnewdiv').css("display", "none");--%>
<%--                $('#toTop').trigger("click")--%>
<%--                $('#tablediv').fadeIn();--%>
<%--                $('#addUserHeader').fadeIn();--%>
<%--                clearData();--%>

<%--            },--%>
<%--            error: function (err) {--%>

<%--                swal({--%>
<%--                    title: 'Error',--%>
<%--                    text: err.responseJSON.message,--%>
<%--                    icon: 'error',--%>
<%--                    button: 'Ok'--%>
<%--                });--%>

<%--            }--%>
<%--        });--%>


<%--    });--%>

<%--    //=========================================================================================--%>
<%--    function addNewRow() {--%>
<%--        $('#attachmenttbody').append("<tr ><td attrhid='0' style='width: 80%'><form><input name='file' type='file' class='fileuploadinput form-control'></form> </td><td style='width: 20%'><i title='Click Here To Delete This Document' class='deletefile hvr-buzz-out fa fa-trash'></i></td></tr>")--%>

<%--    }--%>
<%--    //=========================================================================================--%>
<%--    function addNewRowReportTo() {--%>
<%--        $('#reportstotbody').append("<tr><td attrhid='0' style='width: 80%'><select class='departmentreportto form-control'>" + $('#department').html() + "</select> </td><td attrhid='0' style='width: 80%'><select class='form-control'><option value='-1'>----Select Department First-----</option></select> </td><td style='width: 20%'><i title='Click Here To Delete This Document' class='deletereportto hvr-buzz-out fa fa-trash'></i></td></tr>")--%>

<%--    }--%>
<%--    //=========================================================================================--%>
<%--    function loaddepartment(url, id) {--%>
<%--        $.ajax({--%>
<%--            type: "GET",--%>
<%--            contentType: "application/json",--%>
<%--            url: url,--%>
<%--            success: function (json) {--%>
<%--                var html = "<option value='0'>---Select Department---</option>";--%>
<%--                for (var i = 0; i < json.length; i++) {--%>
<%--                    html = html + "<option value='" + json[i].roleName + "'>" + json[i].roleName + "</option>"--%>

<%--                }--%>
<%--                $(id).html(html)--%>

<%--            },--%>
<%--            error: function (err) {--%>

<%--            }--%>

<%--        })--%>
<%--    }--%>
<%--</script>--%>
<%--</body>--%>
<%--<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>--%>
<%--</html>--%>

