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

    <link href="${pageContext.request.contextPath}/assets/plugins/quilleditor/quill.snow.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/assets/plugins/quilleditor/quill.min.js"></script>

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
        .avtar-img {

            color: white;
            height: 40px;
            width: 40px;
            line-height: 40px;
            text-align: center;
            vertical-align: middle;
            border-radius: 50%;
            font-size: 1em;
            margin: 10px;
        }

        .validationFail {
            background-color: #ff00005e !important;
        }

        .downloadfilecls {
            cursor: pointer;
        }

        .fa {
            font-size: 20px;
        }

        .fa-volume-control-phone {
            color: green;

        }
        .followTicketDiv{
            display: none;
        }

        .fa-envelope {
            color: green;
        }

        .fa-info-circle {
            color: red;
        }

        .customsidedivmenu {
            height: 180px;
            overflow-y: scroll;
        }

        .statuscontrol {
            border: 1px solid black;
            padding: 6px;
            text-align: center;
            width: 81px;
            font-weight: 800;
            color: black;
            float: left;
            border-radius: 6px;
        }

        .leadstatuscls {
            color: black;
            font-weight: bolder;
            padding: 2px;
            border-radius: 6px;
            margin-left: 6px;
        }

        .selectedtruepriority {

            -webkit-border-radius: 10px;
            border-radius: 10px;
            border: none;
            color: green;
            cursor: pointer;
            display: inline-block;
            font-family: Arial;
            font-size: 20px;
            padding: 5px 10px;
            text-align: center;
            text-decoration: none;
            -webkit-animation: glowing 1500ms infinite;
            -moz-animation: glowing 1500ms infinite;
            -o-animation: glowing 1500ms infinite;
            animation: glowing 1500ms infinite;
        }

        .selectedtrue {

            -webkit-border-radius: 10px;
            border-radius: 10px;
            border: none;
            color: green;
            cursor: pointer;
            display: inline-block;
            font-family: Arial;
            font-size: 20px;
            padding: 5px 10px;
            text-align: center;
            text-decoration: none;
            -webkit-animation: glowing 1500ms infinite;
            -moz-animation: glowing 1500ms infinite;
            -o-animation: glowing 1500ms infinite;
            animation: glowing 1500ms infinite;
        }


        @-webkit-keyframes glowing {
            0% {
                -webkit-box-shadow: 0 0 3px #B20000;
            }
            50% {
                -webkit-box-shadow: 0 0 40px #FF0000;
            }
            100% {
                -webkit-box-shadow: 0 0 3px #B20000;
            }
        }

        @-moz-keyframes glowing {
            0% {
                -moz-box-shadow: 0 0 3px #B20000;
            }
            50% {
                -moz-box-shadow: 0 0 40px #FF0000;
            }
            100% {
                -moz-box-shadow: 0 0 3px #B20000;
            }
        }

        @-o-keyframes glowing {
            0% {
                box-shadow: 0 0 3px #B20000;
            }
            50% {
                box-shadow: 0 0 40px #FF0000;
            }
            100% {
                box-shadow: 0 0 3px #B20000;
            }
        }

        @keyframes glowing {
            0% {
                box-shadow: 0 0 3px #B20000;
            }
            50% {
                box-shadow: 0 0 40px #FF0000;
            }
            100% {
                box-shadow: 0 0 3px #B20000;
            }
        }

        .menuouter {
            position: absolute;
            background-color: white;
            display: none;
            border: 1px solid;

            box-shadow: 5px 10px #888888;
        }

        .menuinnermode {

            cursor: pointer;
            padding-left: 10px;
            padding-right: 10px;
            font-weight: 700;
        }

        .menuinnermode:hover {
            background-color: darkgrey;
            color: white;
        }

        .custom-menu {
            display: none;
            z-index: 1000;
            position: absolute;
            overflow: hidden;
            border: 1px solid #CCC;
            white-space: nowrap;
            font-family: sans-serif;
            background: #FFF;
            color: #333;
            border-radius: 5px;
            padding: 0;
        }
.clickablelink{
    cursor: pointer;
    color: red;
    font-weight: bolder;
    border: 1px solid;
    padding: 2px;

}
        /* Each of the items in the list */
        .custom-menu li {
            padding: 8px 12px;
            cursor: pointer;
            list-style-type: none;
            transition: all .3s ease;
            user-select: none;
        }

        .custom-menu li:hover {
            background-color: #DEF;
        }

        ul {
            list-style-type: none;
        }

        headertag {
            font-weight: bolder;
        }

        .customheaderbtn {

            margin-left: 10px;
            margin-bottom: 5px;
            min-width: 100px;
        }

        .filediv {
            border: 1px solid #337ab7;
            /* width: 100%; */
            width: fit-content;
            padding: 3px;
            cursor: pointer;
            text-align: center;
            float: left;
            margin-left: 10px;
            border-radius: 6px;
        }
        #loadmoreoption{
            text-align: center;
            font-size: 20px;
            cursor: pointer;
            border: 1px solid lightgray;
        }
        #chatwindowdiv{
            max-height: 400px;

            overflow: scroll;
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
        <section class="content-header" style="height: 70px;">
            <div class="header-icon">
                <i style="font-size: 1em" class="fa fa-ticket"></i>
            </div>

            <div class="header-title">
               <h1>Tickets</h1>
            </div>

        </section>
        <!-- Main content -->
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-bd lobidrag" id="tablediv">
                        <div class="panel-heading">
                            <div class="btn-group" id="buttonexport">
                                <a href="#">
                                    <h4>Ticket Details</h4>
                                </a>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <button class="btn btn-add customheaderbtn" type="button" id="filtersearch" title="Search tickets using custom filter">
                                    Filter/Search <i class="hvr-buzz-out fa fa-search"></i></button>
                                <button style="" id="removefilter" class="btn btn-add customheaderbtn" title="Clear custom filter setting"><i class="hvr-buzz-out fa fa-ban"></i> Clear Filter
                                </button>
                            </div>
                            <div class="table-responsive">
                                <div class="table-responsive card" style="padding: 20px">
                                    <table class="table table-striped table-bordered first" id="table">
                                        <thead>
                                        <tr>
                                            <th>Ticket ID</th>
                                            <th>Lead ID</th>
                                            <th>Subject</th>
                                            <th>Type</th>
                                            <th>Department</th>
                                            <th>Agent</th>
                                            <th>Priority</th>
                                            <th>Status</th>
                                            <th>Created On</th>
                                            <th>Created By</th>

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
            <div class="row followTicketDiv">
                <div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
                    <div class="panel panel-bd lobidrag " >
                        <div class="panel-heading">
                            <div class="btn-group">
                                <a href="#">
                                    <h4></h4>
                                </a>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="form-group">
                                <label>Ticket Status</label>
                                <div id="ticketstatusdiv">

                                </div>
                            </div>
                            <div class="form-group">
                                <label>Ticket Priority</label>
                                <div id="ticketpriotitydiv">


                                </div>
                            </div>
                            <div class="form-group">
                                <label>Ticket Forward</label>
                                <br>
                                <button  data-toggle="modal" id="ticketForwardBtn" title="Forward ticket to another Department/User" type="button" class="btn btn-labeled btn-success m-b-5">
                                    <span class="btn-label"><i class="hvr-buzz-out fa fa-mail-forward"></i></span>Forward
                                </button>
                            </div>
                            <select style="display: none" id="userlist"></select>
                            <div id="chatwindowdiv" class="row chatwindom">
                                <input type="hidden" id="offsetchatwindow">
                                <input type="hidden" id="loadmoreboolean">


                            </div>

                            <div class="detailswork">

                                <h1 id="this_subject"></h1>
                                <span style="display: inline"><h4 id="this_createby_createdate"></h4></span>

                            </div>
                            <%--====================================FORM FOR TICKET REPSONSE MESSAGE ===========================--%>
                            <form id="messageForm">

                                <input type="hidden" id="receiver_hidden_msg">
                                <input type="hidden" id="department_hidden_msg">
                                <input type="hidden" id="ticketid_hidden_msg">
                                <div style="height: 150px; width: 100%" id="editor">


                                </div>

                                <div class="table-responsive">
                                    <table class="table table-bordered table-striped table-hover">
                                        <thead>
                                        <tr>
                                            <th>Attachment</th>
                                            <th><i id="addnewrow2" style="font-size: 1.5em;"
                                                   title="Click Here To Add Attachment"
                                                   class="hvr-buzz-out fa fa-plus-circle "></i></th>
                                        </tr>
                                        </thead>
                                        <tbody id="attachmenttbody2"></tbody>
                                    </table>
                                </div>

                                <div class="pull-right">
                                    <button type="button" class="btn btn-add btn-sm" id="sendBtn">
                                        Post <i class="hvr-buzz-out fa fa-send-o"></i>
                                    </button>
                                    <button type="button" class="btn btn-add btn-sm" id="cncelbtn">
                                        Back
                                    </button>
                                </div>
                            </form>
                            <%--=====================================================--%>

                            <table id="convotable" style="width: 100%">
                                <thead>

                                </thead>
                                <tbody>

                                </tbody>
                            </table>



                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                    <div class="panel panel-bd lobidrag11 ">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Status Transfer</h4>
                            </div>
                        </div>
                        <div class="panel-body" style="">
                            <div class="table-responsive customsidedivmenu">
                                <table class="table table-hover">
                                    <thead><tr><th>Name</th><th>Time</th></tr></thead>

                                    <tbody id="statustransfertbody">



                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-bd lobidrag11 ">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Forward History</h4>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive customsidedivmenu">
                                <table class="table table-hover">

                                    <tbody id="forwardhistorytbody">


                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%--======================================= UPDATE TICKET STATUS=======================================--%>
            <div class="modal fade" id="changeTicketStatus" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header modal-header-primary">
                            <button type="button" onclick="clearData()" class="close" data-dismiss="modal"
                                    aria-hidden="true">×
                            </button>
                            <h3><i class="fa fa-plus m-r-5"></i> Update Ticket Status</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <form class="form-horizontal" id="statusChangeForm">
                                        <fieldset>
                                            <input type="hidden" id="ticketid2">
                                            <input type="hidden" id="ticketstatus_old">
                                            <input type="hidden" id="ticketstatus2">


                                            <%--                                            <div class="col-md-10 form-group">--%>
                                            <%--                                                <label>Status</label>--%>
                                            <%--                                                <select id="ticketstatus2" name="ticketstatus" required--%>
                                            <%--                                                        class="form-control">--%>

                                            <%--                                                </select>--%>
                                            <%--                                            </div>--%>

                                            <div class="col-md-10 form-group user-form-group">
                                                <div class="pull-left">
                                                    <button type="submit" class="btn btn-add btn-sm" id="changeBtn">
                                                        Create
                                                    </button>
                                                </div>
                                            </div>

                                        </fieldset>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger pull-right" onclick="clearData()"
                                    id="closebtn2" data-dismiss="modal">Close
                            </button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>

            <%--======================================= TICKET FORWARD=======================================--%>
            <div class="modal fade" id="ticketForward" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header modal-header-primary">
                            <button type="button" onclick="clearData()" class="close" data-dismiss="modal"
                                    aria-hidden="true">×
                            </button>
                            <h3 style="text-align: center"><i class="hvr-buzz-out fa fa-mail-forward"></i> Forward Ticket</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <form class="form-horizontal" id="ticketForwardForm">
                                        <fieldset>
                                            <input type="hidden" id="ticketid3">
                                            <input type="hidden" id="currentdept">
                                            <input type="hidden" id="currentagent">

                                            <div class="col-md-10 form-group">
                                                <label>Department</label>
                                                <select id="ticketdept" name="ticketdept" required
                                                        class="form-control">

                                                </select>
                                            </div>

                                            <div class="col-md-10 form-group" id="agentselectdiv2">
                                                <label>Agent</label>
                                                <select id="agent2" name="agent2" required class="form-control">

                                                </select>
                                            </div>

                                            <div class="col-md-10 form-group user-form-group">
                                                <div class="pull-left">
                                                    <button type="submit" class="btn btn-add btn-sm" id="forwardBtn">
                                                        <i class="hvr-buzz-out fa fa-mail-forward"></i> Forward
                                                    </button>
                                                </div>
                                            </div>

                                        </fieldset>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <select style="display: none" id="tickettype"></select>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger pull-right" onclick="clearData()"
                                    id="closebtn3" data-dismiss="modal">Close
                            </button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!--======================================================ADD NEW Ticket=================================================================== -->

            <!-- /.modal -->
            <input type="hidden" id="hidjson">
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
        var ticketstatus = searchParams.get('ticketstatus') || 0;
        var ticketType = searchParams.get('ticketType') || 0;
        var ticketPriority = searchParams.get('ticketPriority') || 0;
        var datefilter = searchParams.get('datefilter') || false;
        var checkClosedTickets = searchParams.get('checkClosedTickets') || false;
        var datevalue = searchParams.get('datevalue') || 0;
        var userList = searchParams.get('userList') || 0;

        var leadid = searchParams.get('leadid') || 0;
        var id = searchParams.get('id') || 0;
        var searchValue = searchParams.get('searchValue') || 0;
        var datefiltertype=searchParams.get("datefiltertype")||0;
        var url = "datefiltertype="+datefiltertype+"&ticketstatus=" + ticketstatus + "&ticketType=" + ticketType + "&ticketPriority=" + ticketPriority + "&datefilter=" + datefilter + "&checkClosedTickets=" + checkClosedTickets + "&datevalue=" + datevalue + "&userList=" + userList + "&searchValue=" + searchValue + "&leadid=" + leadid + "&id=" + id;


        table = $('#table').DataTable({
            ajax: {
                url: '${pageContext.servletContext.contextPath}/api/ticket/getall?' + url,
                dataSrc: 'data',
                deferRender: true,

                "data": {
                    "flag": 1,


                }
            },
            "columns": [


                {
                    "mRender": function (data, type, row) {

                        return "<a hidatr='" + JSON.stringify(row) + "' >"+" <%=UtilityClass.ApplicationPrefix%>" + row.id+"</a>";



                    }
                },
                {
                    "mRender": function (data, type, row) {


                        return "<a class='clickablelink' hidid='"+row.leadid+"' title='Click Here To View Lead'>"+" <%=UtilityClass.ApplicationPrefix%>" + row.leadid+"</a>";


                    }
                },
                {"data": "subject"},
                {"data": "type"},
                {"data": "department"},
                {"data": "username"},
                {"data": "priority"},
                {"data": "ticketstatus"},
                {"data": "createdate"},
                {"data": "createdby"},



                {
                    "mRender": function (data, type, row) {


                        return "<button title=\"Click Here To View\" type=\"button\" class=\"editcustom btn btn-add btn-sm\"><i class=\"hvr-buzz-out fa fa-eye\"></i></button>";


                    }
                }

            ]
            , "initComplete": function (settings, json) {


            },
            "processing": true,
            "serverSide": true,
            "ordering": false,
            "searching": false,
            "bDestroy": true,
            "sScrollX": true,

        });
    }

    loaddataintotable();
    //================================================================= SHOWING TICKET STATUS  FOR CREATING NEW TICKET=======


    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "${pageContext.servletContext.contextPath}/api/leadticketstatus/getallactive",
        success: function (json) {
            var html = "";
            for (var i = 0; i < json.length; i++) {

                html = html + "   <button color='" + json[i].color + "' namestatus='" + json[i].name + "' style='background-color: " + json[i].color + "' title='" + json[i].description + "' type=\"button\" class=\"btn btn-labeled btn-success m-b-5 ticketstatuscls\">\n" +
                    "        " + json[i].name + "\n" +
                    "        </button>";


            }
            $('#ticketstatusdiv').html(html)


        },
        error: function (err) {

        }

    });

    $(document).on("click", ".ticketstatuscls", function () {
        var ths = $(this);


        var name = ths.text().trim();
        var json;
        try {
             json = JSON.parse($('#hidjson').val());
        }catch (e) {
            $(".selectedtrue").removeClass("selectedtrue");
            ths.addClass("selectedtrue")
            return;
        }
        swal({
            title: "Are you sure?",
            text: "You Want To Change Status Of Ticket To " + name,
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {


                if (willDelete) {
                    $(".selectedtrue").removeClass("selectedtrue");
                    changeStatus(json, name, function (data) {
                        if (data == "success") {

                            ths.addClass("selectedtrue")
                            loadticketstatustranfer(json.id)

                        }
                    })


                }
            });

    });
    $(document).on("click", ".changepriority", function () {
        var ths = $(this);


        var name = ths.text().trim();
        var json;
        try {
             json = JSON.parse($('#hidjson').val());
        }catch (e) {
            $(".selectedtruepriority").removeClass("selectedtruepriority");
            ths.addClass("selectedtruepriority")
            return;
        }
        swal({
            title: "Are you sure?",
            text: "You Want To Change Priority Of Ticket To " + name,
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    $(".selectedtruepriority").removeClass("selectedtruepriority");
                    changePriority(json, name, function (data) {
                        if (data == "success") {
                            ths.addClass("selectedtruepriority")
                            loadticketstatustranfer(json.id)


                        }
                    })


                }
            });

    });

    function changePriority(json, tostatus, callback) {
        $.ajax({
            type: 'POST',

            contentType: 'application/json',
            url: "${pageContext.request.contextPath}/api/ticket/updatepriority?priority=" + tostatus + "&id=" + json.id,
            success: function (data) {
                callback("success")

            },
            error: function (err) {
                callback("error");
                swal({
                    title: 'Error',
                    text: err.responseJSON.message,
                    icon: 'error',
                    button: 'Ok'
                });

            }
        });
    }

    function changeStatus(json, tostatus, callback) {
        $.ajax({
            type: 'POST',

            contentType: 'application/json',
            url: "${pageContext.request.contextPath}/api/ticket/updatestatus?ticketstatus=" + tostatus + "&id=" + json.id + "&createdate=" + json.createdate,
            success: function (data) {
                if(tostatus=="Closed")
                {
                    $('#cncelbtn').trigger("click")
                    swal({
                        title: 'Success',
                        text: "Ticket Has been successfully Closed!",
                        icon: 'success',
                        button: 'Ok'
                    });
                }
                callback("success")

            },
            error: function (err) {
                callback("error");
                swal({
                    title: 'Error',
                    text: err.responseJSON.message,
                    icon: 'error',
                    button: 'Ok'
                });

            }
        });
    }

    //=============================================================================================

    var convotable;

    var quill = new Quill('#editor', {
        theme: 'snow'

    });



    //==================================== UPDATING TICKET STATUS =========================


    //==================================== FORWARD TICKET  =========================

    $("#ticketForwardBtn").click(function(){

    if(isFeatureAvailable() == true) {
        $("#ticketForward").modal("show")
    }
    });

    function isFeatureAvailable(){
        let currentUserRole = "<%=UtilityClass.getCurrentUser().getDepartment()%>"
        let demoUserRole = "<%=UtilityClass.propertyService.findProperty("CRMDemo","demoUserRole")%>"
        let menuAccess = "<%=UtilityClass.propertyService.findProperty("CRMDemo","allowleadticket")%>"
        if(currentUserRole == demoUserRole && menuAccess == "false") {
            swal({
                title: 'Feature Not Available',
                text: "Sorry! This feature is not available with demo account!",
                type: 'warning',
                confirmButtonText: 'Ok'
            })
            return false;
        }
        else return true;

        }

    $("#ticketForwardForm").on("submit", function (e) {
        e.preventDefault();


        var ticketid = JSON.parse($('#hidjson').val()).id;
        var todept = $("#ticketdept").val();
        var toagent = $("#agent2").val();
        var toagentstring=$("#agent2 option:selected").text();
        var msg="You Want To Forward This Ticket To "+toagentstring+" ?";
        if(toagent==0)
        {
            msg="You Want To Forward This Ticket To "+todept+"  Department ?";
        }
        swal({
            title: "Are you sure?",
            text:msg,
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })  .then((willDelete) => {


            if (willDelete) {






        var url = "${pageContext.request.contextPath}/api/ticket/forwardticket?id=" + ticketid + "&lastforwardname=" + toagent + "&department="+todept+"&toagent=" + toagentstring;

        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: url,
            success: function (data) {


                swal({
                    title: 'Success',
                    text: 'Successfully Transfer Ticket To '+toagentstring,
                    icon: 'success',
                    button: 'Ok'
                });
                $(".followTicketDiv").hide();
                reloadTable();
                $('#tablediv').removeClass("hidden");

                $("#closebtn3").trigger("click");

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
            }
        });
    });

    //==================================== RESPONDING TO TICKET ===========================


    //==================================== SHOW TICKET LIST AND HIDE FOLLOWUP WINDOW ===

    function showTickets() {
        reloadTable();
        convotable.destroy();
        $('#tablediv').removeClass("hidden");
        $(".followTicketDiv").hide();

    }

    $('#cncelbtn').on("click",function () {
        reloadTable();
        $('#chatwindowdiv').html("");
        $('#tablediv').removeClass("hidden");
        $(".followTicketDiv").hide();
    })

    var colorMap = {
        A: "0DAAF5",
        B: "2603DB",
        C: "541F4E",
        D: "7458C1",
        E: "094874",
        F: "E82921",
        G: "E57F95",
        H: "AEABEE",
        I: "A620EE",
        J: "1E766F",
        K: "B57DF9",
        L: "EDE6C7",
        M: "C66625",
        N: "4CB6CA",
        O: "5E8D5F",
        P: "C153E4",
        Q: "CD2B4E",
        R: "315A78",
        S: "FC5A21",
        T: "480D56",
        U: "D17246",
        V: "7139D8",
        W: "809EB1",
        X: "6ADB5F",
        Y: "DA8D7D",
        Z: "45FB74"
    };
    function clearData()

    {
        quill.root.innerHTML="";
        $('#attachmenttbody2').html("")
    }
    <%--==================================ticket follow up=================================--%>
    $(document).on("click", ".editcustom", function () {

        //reset status and proproty buttons
        $("#ticketstatusdiv>button.selectedtrue").removeClass("selectedtrue");
        $("#ticketpriotitydiv>button.selectedtruepriority").removeClass("selectedtruepriority");

        $('#preloader').css("display","block")
        var json = JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));
        $('#hidjson').val(JSON.stringify(json));

        $('#ticketstatusdiv').find("[namestatus='" + json.ticketstatus + "']").addClass("selectedtrue");
        $('#ticketpriotitydiv').find("[namepriority='" + json.priority + "']").addClass("selectedtruepriority");
        $('#tablediv').addClass("hidden");
        $(".followTicketDiv").show();
        if(json.close)
        {
            $('.ticketstatuscls').prop("disabled",true)
            $('.changepriority').prop("disabled",true)
        }
        else{
            $('.ticketstatuscls').prop("disabled",false)
            $('.changepriority').prop("disabled",false)
        }
        loadTicketMessage(json.id, 10,function (data) {
            if(data=="success")
            {
                $('#preloader').fadeOut("slow")
            }
        });
        loadticketforward(json.id)
        loadticketstatustranfer(json.id)


    });
    function loadticketstatustranfer(id) {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/ticketststransfer/getall?id=" + id ,
            success: function (json) {
                var html = "";
                var forwardicon="<i class=\"hvr-buzz-out fa fa-arrow-circle-up\"> </i>"
                for(var i=0;i<json.length;i++)
                {
                    if(i==0)
                    {
                        forwardicon="";
                    }
                    html=html+"  <tr><td>"+forwardicon+" "+json[i].tostatus+"</td><td>"+json[i].timestamp+"</td></tr>\n"
                }
                $('#statustransfertbody').html(html)




            },
            error: function (err) {

            }

        });
    }
    function loadticketforward(id) {
       $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/ticketforwardlog/getall?id=" + id ,
            success: function (json) {
                var html = "";
                var forwardicon="<i class=\"hvr-buzz-out fa fa-arrow-circle-up\"></i>"
                for(var i=0;i<json.length;i++)
                {
                    if(i==json.length-1)
                    {
                        forwardicon="";
                    }
                    html=html+"  <tr><td>"+forwardicon+" "+json[i].toagent+"</td><td>"+json[i].timestamp+"</td></tr>\n"
                }
                $('#forwardhistorytbody').html(html)
              



            },
            error: function (err) {

            }

        });
    }



    function loadTicketMessage(id, limit,callback) {
      var   offset=$('#offsetchatwindow').val()||0;
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/ticketmessage/getallbyticket?id=" + id + "&offset=" + offset + "&limit=" + limit + "",
            success: function (json) {
                var html = "";
                loadmessageintodiv(json,function (data) {
                    if(data=="success")
                    {
                        callback("success")
                    }
                })



            },
            error: function (err) {
                callback("success")
            }

        });
    }

    function loadmessageintodiv(json,callback) {

        var html="";
        for (var i = 0; i < json.length; i++) {

            var innerattachment = "";
            var deletebtn = "";
            var innerjson = JSON.parse(json[i].attachmentlist);
            for (var m = 0; m < innerjson.length; m++) {
                innerattachment = innerattachment + "   <div class='filediv downloadfileattachment' hidid=" + innerjson[m].id + " title=\"Click Here To Download File\" ><i class=\"hvr-buzz-out fa fa-paperclip \"></i>" + innerjson[m].name + "</div>";
            }
            if (json[i].sender == usernamecuruser) {
                deletebtn = "<i hidid='" + json[i].id + "'  class='hvr-buzz-out fa fa-trash deleteticketmessage'></i>";

            }
            html = html + "<a  class=\"inbox_item unread\">\n" +
                "                                    <div class=\"inbox-avatar\">\n" +
                "\n" +
                "                                        <div class=\"inbox-avatar-text\">\n" +
                "                                            <div class=\"avatar-name\">" + json[i].sender + " " + deletebtn + "</div>\n" +
                "                                            <div><small>" + json[i].message.trim() + "  " + innerattachment + " </span></span></small></div>\n" +
                "                                        </div>\n" +
                "\n" +
                "                                        <div class=\"inbox-date hidden-sm hidden-xs hidden-md\">\n" +
                "                                            <div class=\"date\">" + json[i].senddate + "</div>\n" +
                "                                            <div></div>\n" +
                "                                        </div>\n" +
                "\n" +
                "                                    </div>      </a>"
        }


        $('#chatwindowdiv').append(html)
        $("#chatwindowdiv").scrollTop($("#chatwindowdiv")[0].scrollHeight);
        callback("success")
    }

    function clearmessageform() {
        quill.setText("");
        $('#attachmenttbody2').html("");
    }

    //================================================================== EDIT TICKET ========

    $(document).on("click",".downloadfileattachment",function () {
        var id=$(this).attr("hidid");
        window.open("${pageContext.servletContext.contextPath}/api/file/downloadfile?id=" + id)
    })

    //Refresh table
    function reloadTable() {
        table.ajax.reload();
    }

    //======================================  FILE DOWNLOAD ========================

    //====================================== ADD NEW ROW FOR DOCUMENT UPLOAD =======

    $('#addnewrow').click(function () {
        addNewRow();
    });

    function addNewRow() {
        $('#attachmenttbody').append("<tr ><td attrhid='0' style='width: 80%'><form><input name='file' type='file' class='fileuploadinput form-control'></form> </td><td style='width: 20%'><i title='Click Here To Delete This Document' class='deletefileattachment hvr-buzz-out fa fa-trash'></i></td></tr>")
    }

    //======================================= DOCUMENT UPLOAD FIELDS FOR TIKCET RESPONSE ===
    $('#addnewrow2').click(function () {
        addNewRow2();
    });

    function addNewRow2() {
        $('#attachmenttbody2').append("<tr ><td attrhid='0' style='width: 80%'><form><input name='file' type='file' class='fileuploadinput form-control'></form> </td><td style='width: 20%'><i title='Click Here To Delete This Document' class='deletefileattachment hvr-buzz-out fa fa-trash'></i></td></tr>")
    }

    //======================================= DOCUMENT UPLOAD =======================
    $(document).on("change", ".fileuploadinput", function () {
        var ths = $(this);
        var form = $(this).closest("form")[0];

        var data = new FormData(form);

        $('#preloader').css("display", "block");
        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "${pageContext.request.contextPath}/api/file/insertfile",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {

                $('#preloader').fadeOut();
                ths.closest("tr").html("<td class='downloadfilecls' attrhid='" + data.id + "' title='" + data.createAt + "'>" + data.name + "</td><td><i title='Click Here To Delete This Document' class='deletefileattachment hvr-buzz-out fa fa-trash'></i></td>")

            },
            error: function (err) {
                $('#preloader').fadeOut();

                swal({
                    title: 'Error',
                    text: err.responseJSON.message,
                    type: 'error',
                    confirmButtonText: 'Ok'
                })

            }
        });
    });
    // ===================================================== DELETE DOCUMENT and FIELD FROM FORM ============================================
    $(document).on("click", ".deletefileattachment", function () {
        var hidid = $(this).closest("tr").find("td").eq(0).attr("attrhid");
        var ths = $(this);
        if (hidid == 0) {
            ths.closest("tr").remove();

        } else {
            swal({
                title: "Are you sure?",
                text: "You Want To Delete Attachment?",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })  .then((willDelete) => {


                if (willDelete) {

                    $.ajax({
                        type: "DELETE",
                        contentType: "application/json",
                        url: "${pageContext.servletContext.contextPath}/api/file/delete?id=" + hidid,
                        success: function (json) {
                            ths.closest("tr").remove();


                        },
                        error: function (err) {

                        }

                    });


                }
            });


        }
    });

    //================================LOAD ROLES
    loadderoles();

    function loadderoles() {
        var url = '${pageContext.servletContext.contextPath}/api/roles/getroles';
        var id = '#department';
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (json) {
                var html = "<option value='0'>---Select Department---</option>";
                for (var i = 0; i < json.length; i++) {
                    html = html + "<option value='" + json[i].roleName + "'>" + json[i].roleName + "</option>"

                }
                $(id).html(html);
                $("#ticketdept").html(html)

            },
            error: function (err) {

            }

        })
    }


    //================================LOAD Ticket Type
    loadticketype();

    function loadticketype() {
        var url = '${pageContext.servletContext.contextPath}/api/tickettype/getallactive';

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (json) {
                var html = "<option value='0'>---Select Ticket Type---</option>";
                for (var i = 0; i < json.length; i++) {
                    html = html + "<option value='" + json[i].name + "'>" + json[i].name + "</option>"

                }
                $('#tickettype').html(html)

            },
            error: function (err) {

            }

        })
    }


    //================================LOAD Priority
    loadpriority();

    function loadpriority() {


        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: '${pageContext.servletContext.contextPath}/api/priority/getallactive',
            success: function (json) {
                var html = "";
                for (var i = 0; i < json.length; i++) {
                    html = html + "<button title='"+json[i].description+"' style='background-color: " + json[i].color + "' namepriority='" + json[i].name + "' type=\"button\" class=\"btn btn-labeled btn-success m-b-5 changepriority\">" + json[i].name + "</button>"

                }
                $('#ticketpriotitydiv').html(html)

            },
            error: function (err) {

            }

        })
    }


    //================================LOAD AGENTS WHEN FORWARDING TICKET
    $("#ticketForwardBtn").on("click", function () {

        var dept = $("#ticketdept").val();
        var agent = $("#currentagent").val();
        var id = "#agent2";
        loadagents(id, dept, agent);
    });

    $("#ticketdept").on("change", function () {

        var dept = $("#ticketdept").val();
        var agent = '0';
        var id = "#agent2";
        loadagents(id, dept, agent);
    });

    //================================LOAD AGENTS
    $("#agentselectdiv").hide();//keep it hidden by default
    $("#department").on("change", function () {

        var dept = $("#department").val();
        var agent = '0';
        var id = '#agent';
        loadagents(id, dept, agent);
        $("#agentselectdiv").show();
    });

    //================================LOAD AGENTS
    function loadagents(id, dept, value) {
        var url = '${pageContext.servletContext.contextPath}/api/user/getuserbydepartment?department=' + dept;
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (json) {
                var html = "<option value='0'>---Select Agent---</option>";
                for (var i = 0; i < json.length; i++) {
                    if (json[i].id == value) {
                        html = html + "<option selected value='" + json[i].id + "'>" + json[i].username + "</option>"
                    } else {
                        html = html + "<option value='" + json[i].id + "'>"+ json[i].firstName + " " + json[i].lastName +" ("+ json[i].username + ")</option>"
                    }
                }

                $(id).html(html);


            },
            error: function (err) {

            }

        })
    }

    $('#sendBtn').click(function () {
        var map = {};
        var trarr=[];
        var tr=$('#attachmenttbody2').find("tr");
        for(var i=0;i<tr.length;i++)
        {
            var trmap={};
            trmap["name"]=tr.eq(i).find("td").eq(0).text()||0;
            trmap["id"]=tr.eq(i).find("td").eq(0).attr("attrhid")||0;
            if(trmap["id"]!=0)   trarr.push(trmap);


        }
        var json = JSON.parse($('#hidjson').val());
        map["ticketid"] = json.id;
        map["message"]=quill.root.innerHTML;
        map["attachmentlist"]=JSON.stringify(trarr);

     //check if quill editor is empty. BY default quill editor has <p><br></p>
     if(quill.root.innerHTML.trim() != "<p><br></p>" && quill.root.innerHTML.replace(/<p>/g, '').trim() != "</p>"){
         swal({
             title: "Are you sure?",
             text: "You Want To Add Reponse To Ticket?",

             icon: "warning",
             buttons: true,
             dangerMode: true,
         })
             .then((willDelete) => {


                 if (willDelete) {
                     var url = "${pageContext.request.contextPath}/api/ticketmessage/insert";
                     $.ajax({
                         type: 'POST',
                         data: JSON.stringify(map),
                         contentType: 'application/json',
                         url: url,
                         success: function (data) {
                             clearData();
                             var json=[];
                             json.push(data);
                             loadmessageintodiv(json,function (data) {
                                 if(data=="success")
                                 {

                                 }
                             })




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


                 }
             });
     }else{

         swal({
             title: 'Error',
             text: "Can't Send Empty Message!",
             icon: 'error',
             button: 'Ok'
         });
     }


    })
    $(document).on("click",".deleteticketmessage",function () {
        var id=$(this).attr("hidid");
        var ths=$(this);
        swal({
                title: "Are you sure?",
                text: "You Want To Delete Reponse?",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })  .then((willDelete) => {


            if (willDelete) {
                var url = "${pageContext.request.contextPath}/api/ticketmessage/delete?id="+id;
                $.ajax({
                    type: 'DELETE',

                    contentType: 'application/json',
                    url: url,
                    success: function (data) {

                        ths.closest("a").remove();



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


            }
        });
    })

    $('#filtersearch').click(function () {
        $.ajax({
            type: 'GET',

            contentType: 'application/json',
            url: "${pageContext.servletContext.contextPath}/html/ticketfilter.html",
            success: function (html) {
                $('#dynamicformbodymodel').html(html);
                $('#dunamicmodelheader').html("Ticket Filter <i class='fa fa-filter'></i>");
                $('#dynamicformmodel').modal("show");
                $('#ticketstatusfilterdiv').html($('#ticketstatusdiv').html())
                $('#ticketpriorityfilterdiv').html($('#ticketpriotitydiv').html())
                $('#ticketTypefilterform').html($('#tickettype').html())
                $('#ticketuserfilterform').html($('#userlist').html())


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
    })
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "${pageContext.servletContext.contextPath}/api/user/getusermyteam",
        success: function (json) {
            var html = "<option value=''>---Select User---</option>";
            for (var i = 0; i < json.length; i++) {
                html = html + "<option  value='" + json[i].id + "'>" + json[i].username + "</option> ";

            }
            $('#userlist').html(html)


        },
        error: function (err) {

        }

    });
    $(document).on("submit","#dynamicfilterform",function (e) {
        e.preventDefault();
        var startdate=$('#ticketstartdatefilterform').val()||0;
        var enddate=$('#ticketendfilterform').val()||0;
        var ticketstatus = $('#ticketstatusfilterdiv').find(".selectedtrue").text().trim()||0;
        var ticketType = $('#ticketTypefilterform').val()||0;
        var ticketPriority =$('#ticketpriorityfilterdiv').find(".selectedtruepriority").text().trim()||0;
        var datefilter = false;
        var datevalue =0;
        var userList =$('#ticketuserfilterform').val()||0;
        if(startdate!=0&&enddate!=0)
        {
            datefilter=true;
            datevalue=startdate+"@"+enddate;
        }
        var searchValue = $('#ticketfilterformsearch').val() || 0;
        var checkClosedTickets = false;
        var datefiltertype = $("input[name='datefiltertype']:checked").val();
        var urlparams = "datefiltertype="+datefiltertype+"&ticketstatus=" + ticketstatus + "&ticketType=" + ticketType + "&ticketPriority=" + ticketPriority + "&datefilter=" + datefilter + "&checkClosedTickets=" + checkClosedTickets + "&datevalue=" + datevalue + "&userList=" + userList + "&searchValue=" + searchValue + "&leadid=0&id=0";
        var url=window.location.toString().split("?")[0];
        url = url+"?"+urlparams;
        window.history.pushState({}, null, url);
        loaddataintotable();


    })
    $('#removefilter').click(function () {
        var url=window.location.toString().split("?")[0];
        window.history.pushState({}, null, url);
        loaddataintotable()
    })
    $(document).on("click",".clickablelink",function () {
        var id=$(this).attr("hidid")
        window.open("${pageContext.request.contextPath}/lead?id="+id)

    })
</script>
<%--============================ DATA TABLE ENDS HERE =====================================--%>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>

