<%@ page import="com.example.maxcrm.MaxCrm.Utility.UtilityClass" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: sujeet
  Date: 07/12/21
  Time: 3:09 PM
  To change this template use File | Settings | File Templates.
--%>
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
        <h1>My Operator Lead Report</h1>
            </div>
    </section>
    <!-- Main content -->
    <div class="content">
      <div class="row">
        <div class="col-sm-12 col-md-12">
          <div class="panel panel-bd lobidrag">
            <div style="display: none" id="leadstatus">



            </div>

            <div class="panel-heading">

            </div>
            <div class="panel-body">

              <div id="tablediv">
                <div>
                  <div class="table-responsive">
                    <table id="table" class="table table-bordered table-striped table-hover">
                      <thead>
                      <tr class="info">
<%--                        <th><input title="Select all leads" type="checkbox" id="selectAllLead"></th>--%>
<%--                        <th>Active</th>--%>
                        <th>Lead ID</th>
                        <th>Number</th>
                        <th>Call Start Time</th>
                        <th>Call End Time</th>
                        <th>Call Status</th>
                        <th>Call Duration</th>
                        <th>Call Type</th>
                        <th>Location</th>
                        <th>Recording</th>
<%--                        <th>Action</th>--%>
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
  <%--urltable="${pageContext.servletContext.contextPath}/api/logevent/getbyleadid";--%>
  urltable="${pageContext.servletContext.contextPath}/api/logevent/getlogbyeventanduserid"
  paramtable='<%=UtilityClass.getCurrentUser().getId()%>'

  paramtable2="MyOperator"

  var table = $('#table').DataTable({
    ajax: {
      url: urltable,
      dataSrc: '',
      "data": {
          "eventType":paramtable2,
        "userId":paramtable,
      }
    },
    "columns": [
         // {"data": "leadId",
         //   "mRender": function (data, type, row) {
         //        return "<a hidatr='" + JSON.stringify(row) + "' style='display: none'></a><span title='Click Here To View Lead' class='clickablelink'>"+applicationPrefix+{ "data": "leadId"}+"</span>";
         //    }
         // },
       {
         "mRender": function (data, type, row) {
             return "<a hidatr='" + JSON.stringify(row) + "' style='display: none '></a><span title='Click Here To View Lead' class='clickablelink' style='color: red;font-weight:bold ;border: 1px solid  red'>"+applicationPrefix+row.leadId+" </span>";

         }
    }
        ,
      { "data": "phonenumber"},
      { "data": "callStartTime"},
      { "data": "callEndTime"},
      { "data": "call_status"},
      { "data": "duration"},
      { "data": "call_type"},
      { "data": "location"},
      // { "data": "recordingUrl"},
      //   {"data": "recordingUrl",
      //       "render": function(data, type){
      //           if(type === 'display'){
      //               data = '<a href="' + data + ' "   class="fa fa-download downloading" title="Download Recording"> </a>';
      //           }
      //
      //           return data;}
      //   },

        {"data": "recordingUrl",
            "render": function(data, type){
                if(type === 'display'){
                    data = '<audio controls style="width: 170px"><source src="'+data+'" type="audio/mpeg"></audio>';
                }

                return data;}
        }
        // { "data": "leadId",
        //     "render": function (data, type) {
        //
        //         return "  <button title='Click Here To View Campaign' type=\"button\" class=\"previewcampaign btn btn-add btn-sm\" ><i class=\"hvr-buzz-out fa fa-eye\"></i></button>";
        //
        //
        //
        //     }
        // }



    ]
    , "initComplete": function (settings, json) {
      $('#preloader').fadeOut();
    },
    "ordering": false
  });

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
