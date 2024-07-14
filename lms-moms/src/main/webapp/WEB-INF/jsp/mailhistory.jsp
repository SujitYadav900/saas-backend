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

    <!-- chat windows -->
    <link href="${pageContext.request.contextPath}/css/chat.css" rel="stylesheet"
          type="text/css"/>

    <!-- Bootstrap rtl -->
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
        .customheaderbtn {

            margin-left: 10px;
            margin-bottom: 5px;
            min-width: 100px;
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
        <section class="content-header" style="height: 70px">
            <div class="header-icon" >
                <i style="font-size: 1em; " class="fa fa-envelope"></i>

            </div>

            <div class="header-title">
                <h1>Mail History</h1>
            </div>

        </section>
        <!-- Main content -->
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-bd lobidrag" id="tablediv">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <a href="#">
                                    <h4></h4>
                                </a>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <button class="btn btn-add customheaderbtn" type="button" id="filtersearch" title="Search using custom filter">
                                    Filter/Search <i class="hvr-buzz-out fa fa-search"></i></button>
                                <button style="" id="removefilter" class="btn btn-add customheaderbtn" title="Clear custom filter settings"><i
                                        class="hvr-buzz-out fa fa-ban"></i> Clear Filter
                                </button>
                            </div>
                            <div class="table-responsive">
                                <div class="table-responsive card" style="padding: 20px">
                                    <table class="table table-striped table-bordered first" id="table">
                                        <thead>
                                        <tr>
                                            <th width="0px"></th>
                                            <th>Subject</th>
                                            <th>To</th>
                                            <th>Time</th>
                                            <th>Status</th>
                                            <th width="10%">Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%--==================================TICKET FOLLOW UP WINDOW=================================--%>

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

<!-- =============================DATA TABLES======================================================-->
<script src="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.min.js"></script>

<script>
    var table = null;

    function loaddataintotable() {
        var searchParams = new URLSearchParams(window.location.search);

        var datevalue = searchParams.get('datevalue') || 0;
        var status = searchParams.get('status') || 0;
        var url = "datevalue=" + datevalue + "&status=" + status;

        table = $('#table').DataTable({
            ajax: {
                url: '${pageContext.servletContext.contextPath}/api/mail/getmailhistory?' + url,
                dataSrc: 'data',
                deferRender: true,

                "data": {
                    "flag": 1,


                }
            },
            "columns": [

                {
                    "mRender": function (data, type, row) {


                        return "<a hidatr='" + JSON.stringify(row) + "' style='display: none'></a><span></span>";


                    }
                },

                {
                    "mRender": function (data, type, row) {


                        return row.subject


                    }
                },

                {"data": "mailto"},
                {"data": "actiondate"},
                {
                    "mRender": function (data, type, row) {


                        if (row.status == 1) {
                            return "<span style='color: green'>Sent</span>"
                        } else {
                            return "<span style='color: red'>Failed</span>"
                        }

                    }
                },


                {
                    "mRender": function (data, type, row) {


                        return "<button title=\"Click Here To View Full Content of mail\" type=\"button\" class=\"viewmailmessage btn btn-add btn-sm\"><i class=\"hvr-buzz-out fa fa-eye\"></i></button>";


                    }
                }

            ]
            , "initComplete": function (settings, json) {


            },
            "processing": true,
            "serverSide": true,
            "ordering": false,
            "searching": false,
            "bDestroy": true

        });
    }

    loaddataintotable();
    //================================================================= SHOWING TICKET STATUS  FOR CREATING NEW TICKET=======

    $(document).on("submit", "#dynamicfilterform", function (e) {
        e.preventDefault();
        var status = $('#mailstatus').val();
        var startdate = $('#startdatedynamicfilter').val();
        var enddate = $('#enddatedynamicfilter').val();
        startdate = startdate.replace(/-/g, '');
        enddate = enddate.replace(/-/g, '');
        var datevalue = startdate + "@" + enddate;

        var urlparams = "datevalue=" + datevalue + "&status=" + status;
        var url = window.location.toString().split("?")[0];
        url = url + "?" + urlparams;
        window.history.pushState({}, null, url);
        loaddataintotable();

        $("#cancelbtnfilterform").trigger("click");

    });
    $(document).on("click", ".viewmailmessage", function () {
        var json = JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));
        $.ajax({
            url: "${pageContext.request.contextPath}/api/mail/getmailcontent?id=" + json.id,
            type: 'GET',
            success: function (html) {

                $('#dynamicformbodymodel').html(html);
                $('#dunamicmodelheader').html("Mail Content <i class='fa fa-pencil-square'></i>");
                $('#dynamicformmodel').modal("show");

            },
            error: function (err) {

                swal({
                    title: "Error!",
                    text: err.status,
                    icon: "error",
                    button: "Back!",
                });
            }
        });

    });

    $('#filtersearch').click(function () {
        $.ajax({
            type: 'GET',

            contentType: 'application/json',
            url: "${pageContext.servletContext.contextPath}/html/mailfilter.html",
            success: function (html) {
                $('#dynamicformbodymodel').html(html);
                $('#dunamicmodelheader').html("Mail Filter <i class='fa fa-filter'></i>");
                $('#dynamicformmodel').modal("show");


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
    $('#removefilter').click(function () {
        var url = window.location.toString().split("?")[0];
        window.history.pushState({}, null, url);
        loaddataintotable()
    })
</script>
<%--============================ DATA TABLE ENDS HERE =====================================--%>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>

