<%--
  Created by IntelliJ IDEA.
  User: ajay
  Date: 15/04/22
  Time: 10:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.maxcrm.MaxCrm.Utility.UtilityClass" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="common/title.jsp"></jsp:include>
    <!-- =============================FOR DATATABLES ============================================ -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.min.css"/>
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
    <link href="${pageContext.request.contextPath}/assets/plugins/lobipanel/lobipanel.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- <link href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/> -->
    <!-- Pe-icon -->
    <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/helper.css" rel="stylesheet"
          type="text/css"/>
    <!-- Themify icons -->
    <link href="${pageContext.request.contextPath}/assets/themify-icons/themify-icons.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/dist/css/stylecrm.css" rel="stylesheet" type="text/css"/>
    <!-- Theme style rtl -->
    <!--<link href="assets/dist/css/stylecrm-rtl.css" rel="stylesheet" type="text/css"/>-->
    <!-- End Theme Layout Style
       =====================================================================-->
    <style>
        .cstmbtnfilter{
            margin: 7px;
            border-radius: 5px;
            padding: 0px;
            background: #ccd0d2;
            /*white-space: nowrap;*/
        }
    </style>
</head>

<body class="hold-transition sidebar-mini">
<!--preloader-->
<!-- Site wrapper -->
<div class="wrapper">
    <jsp:include page="common/header.jsp"></jsp:include>
    <!-- =============================================== -->
    <!-- Left side column. contains the sidebar -->
    <jsp:include page="common/sidebar.jsp"></jsp:include>
    <!-- =============================================== -->
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="header-icon">
                <i class="fa fa-bar-chart-o"></i>
            </div>
            <div class="header-title">
                <h1>Log Task Report </h1>
            </div>
        </section>
        <!-- Main content -->
        <div class="content">
            <div class="row">
                <div class="col-sm-12 col-md-12">
                    <div class="panel panel-bd lobidrag">

                        <div class="panel-heading">

                        </div>
                        <div class="panel-body">
                            <div class="form-group">

                                <label>User</label>
                                <select class="form-control"  id="userdetails" required>

                                    <option value="Own">Own</option>
                                    <option value="Team">Team</option>
                                </select>
                                <br>
                                <div>
                                <button id="searchbtn" type="button" class="btn btn-success">Search</button>
                                    <button id="downloadButton" type="button" class="btn btn-success">Download Report</button>
                                </div>
                            </div>
                            <div id="tablediv">
                                <div>
                                    <div class="table-responsive">
                                        <table id="table" class="table table-bordered table-striped table-hover">
                                            <thead>
                                            <tr class="info">
                                                <th>Lead ID</th>
                                                <th>Task ID</th>
                                                    <th>Create Date</th>
                                                    <th>User Name</th>
                                                    <th>Subject</th>
                                                <th>Log Message</th>
                                                <th>Date and Time Task</th>
                                            </tr>
                                            </thead>
                                            <tbody>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                            </div>

                            <select style="display: none" id="leadstagehidden"></select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer">

        <jsp:include page="common/footer.jsp"></jsp:include>
    </footer>
</div>

<script src="${pageContext.request.contextPath}/assets/plugins/jQuery/jquery-1.12.4.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/jquery-ui-1.12.1/jquery-ui.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/lobipanel/lobipanel.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/pace/pace.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/dist/js/custom.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/dist/js/dashboard.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.min.js"></script>
<!-- =============================FOR CUSTOM ALERTS ============================================ -->
<script src="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.css"/>

<script>
    var urltable='';
    var paramtable='';
    urltable="${pageContext.servletContext.contextPath}/api/logtask/getdashboardlogs"
    urlDownload="${pageContext.servletContext.contextPath}/api/logtask/getlogdownloadreport"

    // BY default-----Table data is team------
    paramtable2="own";
    var table = $('#table').DataTable({

        ajax: {
            url: urltable,
            dataSrc: '',
            "data": {
                "flag":paramtable2,
            }
        },
        "columns": [
            {
                "mRender": function (data, type, row) {
                    return "<a hidatr='" + JSON.stringify(row) + "' style='display: none '></a><span title='Click Here To View Lead' class='clickablelink' style='color: red;font-weight:bold ;border: 1px solid  red'>"+applicationPrefix+row.leadId+" </span>";

                }
            },
            // { "data": "leadId"},
            { "data": "id"},
            { "data": "createDate"},
            { "data": "firstname"},
            { "data": "subject"},
            { "data": "message"},
            { "data": "dateTimeTask"},
        ]
        , "initComplete": function (settings, json) {
            $('#preloader').fadeOut();
        },
        "ordering": false
    });
    // paramtable2=$('#userdetails').val();
    // console.log("ye raha param"+paramtable2);

    $(document).on("click","#searchbtn",function () {

        paramtable2=$('#userdetails').val();
        console.log("ye raha param"+paramtable2);
        if (table != undefined && table != null)
        {
            table.destroy();
            table = null;
        }

        table = $('#table').DataTable({

            ajax: {
                url: urltable,
                dataSrc: '',
                "data": {
                    "flag":paramtable2,
                }
            },
            "columns": [
                {
                    "mRender": function (data, type, row) {
                        return "<a hidatr='" + JSON.stringify(row) + "' style='display: none '></a><span title='Click Here To View Lead' class='clickablelink' style='color: red;font-weight:bold ;border: 1px solid  red'>"+applicationPrefix+row.leadId+" </span>";

                    }
                },
                // { "data": "leadId"},
                { "data": "id"},
                { "data": "createDate"},
                { "data": "firstname"},
                { "data": "subject"},
                { "data": "message"},
                { "data": "dateTimeTask"},
            ]
            , "initComplete": function (settings, json) {
                $('#preloader').fadeOut();
            },
            "ordering": false
        });

    })

    $(document).on("click","#downloadButton",function () {

        paramtable2=$('#userdetails').val();
        console.log("This is Parameter "+paramtable2);

            $.ajax ({
                url: urlDownload + "?flag=" + paramtable2,
                data: "",
                type: 'GET',

                success: function test(data,textStatus,jqXHR) {

                    console.log(data);

                }
            })


    })


    const applicationPrefix = '<%=UtilityClass.ApplicationPrefix%>';
    $(document).on("click",".clickablelink",function () {
        var id=JSON.parse($(this).closest("tr").find("a").attr("hidatr")).leadId;
        window.open("${pageContext.request.contextPath}/lead?id="+id)

    })

    $(document).on("click",".previewcampaign",function () {
        var id=JSON.parse($(this).closest("tr").find("a").attr("hidatr")).leadId;
        window.open("${pageContext.request.contextPath}/lead?id="+id)

    })
</script>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>
