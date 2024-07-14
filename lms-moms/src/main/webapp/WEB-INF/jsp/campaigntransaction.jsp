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
    <!-- Bootstrap rtl -->
    <!--<link href="assets/bootstrap-rtl/bootstrap-rtl.min.css" rel="stylesheet" type="text/css"/>-->
    <!-- Lobipanel css -->
    <link href="${pageContext.request.contextPath}/assets/plugins/lobipanel/lobipanel.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- Pace css -->
    <%--    <link href="${pageContext.request.contextPath}/assets/plugins/pace/flash.css" rel="stylesheet" type="text/css"/>--%>
    <!-- Font Awesome -->
    <!-- <link href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/> -->
    <!-- Pe-icon -->
    <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/helper.css" rel="stylesheet"
          type="text/css"/>

    <!-- Themify icons -->
    <link href="${pageContext.request.contextPath}/assets/themify-icons/themify-icons.css" rel="stylesheet"
          type="text/css"/>
    <!-- End Global Mandatory Style
       =====================================================================-->
    <!-- Start page Label Plugins
       =====================================================================-->
    <!-- Emojionearea -->
    <%--    <link href="${pageContext.request.contextPath}/assets/plugins/emojionearea/emojionearea.min.css" rel="stylesheet" type="text/css"/>--%>
    <!-- Monthly css -->
    <%--    <link href="${pageContext.request.contextPath}/assets/plugins/monthly/monthly.css" rel="stylesheet" type="text/css"/>--%>
    <!-- End page Label Plugins
       =====================================================================-->
    <!-- Start Theme Layout Style
       =====================================================================-->
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath}/assets/dist/css/stylecrm.css" rel="stylesheet" type="text/css"/>
    <!-- Theme style rtl -->
    <!--<link href="assets/dist/css/stylecrm-rtl.css" rel="stylesheet" type="text/css"/>-->
    <!-- End Theme Layout Style
       =====================================================================-->
    <style>
        .clickablelink{
            cursor: pointer;
            color: red;
            font-weight: bolder;
            border: 1px solid;
            padding: 2px;

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
                <i class="fa fa-bullhorn"></i>
            </div>
            <div class="header-title">
                <h1>Campaign Details</h1>
                  </div>
        </section>
        <!-- Main content -->
        <div class="content">
            <div class="row">
                <div class="col-sm-12 col-md-12">
                    <div class="panel panel-bd lobidrag">
                        <div style="display: none" id="leadstatus">



                        </div>
                        <select style="display: none" id="leadsource"></select>
                        <select style="display: none" id="product"></select>
                        <select style="display: none" id="leadtype"></select>
                        <select style="display: none" id="userlist"></select>
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Campaign Details</h4>
                            </div>
                        </div>
                        <div class="panel-body">

                            <div id="tablediv">
                                <div>
                                    <div class="table-responsive">
                                        <table id="table" class="table table-bordered table-striped table-hover">
                                            <thead>
                                            <tr class="info">
                                                <th>Lead Id</th>
                                                <th>Opened</th>
                                                <th>Status</th>
                                                <th>Open Count</th>
                                                <th>Ip</th>
                                                <th>Open Timing</th>
                                                <th>User Agent</th>
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
<script>

    const applicationPrefix = '<%=UtilityClass.ApplicationPrefix%>';
    $(document).on("click",".clickablelink",function () {
        var id=JSON.parse($(this).closest("tr").find("a").attr("hidatr")).leadId;
        window.open("${pageContext.request.contextPath}/lead?id="+id)

    })
    var table = $('#table').DataTable({
        ajax: {

            url: "${pageContext.request.contextPath}/api/campaigntrans/getbycampaignid",
            dataSrc: 'data',
            "data": {
                "id":"<%=request.getParameter("id")%>",
                "count":"<%=request.getParameter("count")%>",

            }
        },
        "columns": [

            {
                "mRender": function (data, type, row) {


                    return "<a hidatr='" + JSON.stringify(row) + "' style='display: none'></a><span title='Click Here To View Lead' class='clickablelink'>"+applicationPrefix+row.leadId+"</span>";


                }
            },
            {
                "mRender": function (data, type, row) {

                    if(row.openCount>0)
                    {
                       return "<i class='fa fa-check checked'></i>"
                    }
                    else{
                        return "<i class='fa fa-remove notcheck'></i>"
                    }


                }
            },
            { "data": "dlrStatus"},
            { "data": "openCount"},
            { "data": "ip"},

            { "data": "timing"},
            { "data": "userAgent"}



        ]
        , "initComplete": function (settings, json) {
            $('#preloader').fadeOut();
        },
        "processing": true,
        "serverSide": true,
        "ordering": false,
        "searching": false,
        "bDestroy": true,

    });


</script>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>

