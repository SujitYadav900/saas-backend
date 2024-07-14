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

   <!-- =============================FOR CUSTOM ALERTS ============================================ -->
   <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.css"/>
   <script src="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.js"></script>

   <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css">
   <!-- Favicon and touch icons -->
   <link rel="shortcut icon" href="${pageContext.request.contextPath}<%=UtilityClass.minLogoUrl%>" type="image/x-icon">

   <!-- Start Global Mandatory Style
      =====================================================================-->
   <!-- jquery-ui css -->
   <link href="${pageContext.request.contextPath}/assets/plugins/jquery-ui-1.12.1/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
   <!-- Bootstrap -->
   <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
   <!-- Bootstrap rtl -->
   <!--<link href="assets/bootstrap-rtl/bootstrap-rtl.min.css" rel="stylesheet" type="text/css"/>-->
   <!-- Lobipanel css -->
   <link href="${pageContext.request.contextPath}/assets/plugins/lobipanel/lobipanel.min.css" rel="stylesheet" type="text/css"/>
   <!-- Pace css -->
   <link href="${pageContext.request.contextPath}/assets/plugins/pace/flash.css" rel="stylesheet" type="text/css"/>
   <!-- Font Awesome -->
   <!-- Pe-icon -->
   <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet" type="text/css"/>
   <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/helper.css" rel="stylesheet" type="text/css"/>

   <!-- Themify icons -->
   <link href="${pageContext.request.contextPath}/assets/themify-icons/themify-icons.css" rel="stylesheet" type="text/css"/>
   <!-- End Global Mandatory Style
      =====================================================================-->
   <!-- Start page Label Plugins
      =====================================================================-->
   <!-- Emojionearea -->
   <link href="${pageContext.request.contextPath}/assets/plugins/emojionearea/emojionearea.min.css" rel="stylesheet" type="text/css"/>
   <!-- Monthly css -->
   <link href="${pageContext.request.contextPath}/assets/plugins/monthly/monthly.css" rel="stylesheet" type="text/css"/>
   <!-- End page Label Plugins
      =====================================================================-->
   <!-- Start Theme Layout Style
      =====================================================================-->
   <!-- Theme style -->
   <link href="${pageContext.request.contextPath}/assets/dist/css/stylecrm.css" rel="stylesheet" type="text/css"/>
   <!-- Theme style rtl -->
   <!-- End Theme Layout Style
      =====================================================================-->
   <style>
      .takemetotable{
         margin-left: 10px;
      }
   </style>
   <script>
      var contextPathUrl = "${pageContext.request.contextPath}";
   </script>
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
         <div class="header-icon">
            <i class="fa fa-bar-chart-o"></i>
         </div>
         <div class="header-title">
            <h1>Mail Dashboard</h1>
            <small>Quick Overview</small>
         </div>
      </section>
      <!-- Main content -->
      <section class="content">
         <div class="row">
            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
               <div id="cardbox1">
                  <div class="statistic-box">
                     <i class="fa fa-envelope fa-3x"></i>
                     <div class="counter-number pull-right">
                        <span class="count-number" id="totalmail"></span>
                        <span class="slight"><i class="fa fa-play fa-rotate-270"> </i>
                              </span>
                     </div>
                     <h3>Total Mails</h3>
                  </div>
               </div>
            </div>

            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
               <div id="cardbox2">
                  <div class="statistic-box">
                     <i class="fa fa-envelope-open fa-3x"></i>
                     <div class="counter-number pull-right">
                        <span class="count-number" id="totalsent"></span>
                        <span class="slight"><i class="fa fa-play fa-rotate-270"> </i>
                              </span>
                     </div>
                     <h3>Total Sent</h3>
                  </div>
               </div>
            </div>

            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
               <div id="cardbox3">
                  <div class="statistic-box">
                     <i class="hvr-buzz-out fa fa-exclamation-triangle fa-3x"></i>

                     <div class="counter-number pull-right">
                        <span class="count-number" id="totalfailed"></span>
                        <span class="slight">
                              </span>
                     </div>
                     <h3>Total Failed</h3>
                  </div>
               </div>
            </div>
         </div>





         <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs">
               <li id="charttab" class="active"><a href="#tab3" title="Click to view Graphical Report" data-toggle="tab">Graphical Report <i class="fa fa-bar-chart"></i></a></li>
               <li> <a href="#tab4" title="Click to view Tabular Report" data-toggle="tab">Tabular Report <i class="fa fa-table"></i></a></li>
               <li><a href="#tab5" title="Click to view filter Report" data-toggle="tab">Filter <i class="fa fa-filter"></i></a></li>
               <li><a href="#" onclick="onSettingFromLoad()" data-toggle="modal" data-target="#graphSettings" title="Click to filter Report">Settings <i class="fa fa-gear" ></i></a></li>
            </ul>
            <!-- Tab panels -->
            <div class="tab-content">
               <div class="tab-pane fade in active" id="tab3">
                  <div class="panel-body">
                     <%--=============================== GRAPHS ==============================--%>
                     <div class="row">

                        <div class="col-sm-6 col-md-12 chartPanelClass">
                           <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Mail Status Graph</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <canvas id="statuschart" height="100"></canvas>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="tab-pane fade" id="tab4">
                  <div class="panel-body">

                     <div class="row">

                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                           <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Mail Report</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <div class="table-responsive">
                                    <table class="table table-bordered table-striped table-hover">

                                       <thead><tr><th>Status</th><th>Count</th><th>Percentage <span style="color: green">(%)</span></th><th>Action</th></tr></thead>
                                       <tbody id="tbody">

                                       </tbody>

                                    </table>
                                 </div>
                              </div>
                           </div>
                        </div>



                     </div>

                  </div>
               </div>
               <div class="tab-pane fade" id="tab5">
                  <div class="panel-body">

                     <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                           <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Filter</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <form class="form-horizontal" id="filterform">
                                    <fieldset>

                                       <div class="col-md-10 form-group" >
                                          <label>From</label>
                                          <input type="date" id="fromdate" class="form-control" />
                                       </div>

                                       <div class="col-md-10 form-group" >
                                          <label>To</label>
                                          <input type="date" id="todate" class="form-control"  />
                                       </div>

                                       <div class="col-md-10 form-group user-form-group">
                                          <div class="pull-left">
                                             <button type="submit" class="btn btn-add btn-sm" id="filterBtn">
                                                Filter
                                             </button>
                                          </div>
                                       </div>

                                    </fieldset>
                                 </form>
                              </div>
                           </div>

                        </div>
                     </div>
                  </div>
               </div>
               <div id="graphSettingDiv"></div>
            </div>




</div>
      </section>

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
<script src="${pageContext.request.contextPath}/assets/plugins/jQuery/jquery-1.12.4.min.js" type="text/javascript"></script>
<!-- jquery-ui -->
<script src="${pageContext.request.contextPath}/assets/plugins/jquery-ui-1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<!-- lobipanel -->
<script src="${pageContext.request.contextPath}/assets/plugins/lobipanel/lobipanel.min.js" type="text/javascript"></script>
<!-- Pace js -->
<script src="${pageContext.request.contextPath}/assets/plugins/pace/pace.min.js" type="text/javascript"></script>
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath}/assets/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript">    </script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/assets/plugins/fastclick/fastclick.min.js" type="text/javascript"></script>
<!-- CRMadmin frame -->
<script src="${pageContext.request.contextPath}/assets/dist/js/custom.js" type="text/javascript"></script>
<!-- End Core Plugins
   =====================================================================-->
<!-- Start Page Lavel Plugins
   =====================================================================-->
<!-- ChartJs JavaScript -->
<script src="${pageContext.request.contextPath}/assets/plugins/chartJs/Chart.min.js" type="text/javascript"></script>
<!-- Counter js -->
<script src="${pageContext.request.contextPath}/assets/plugins/counterup/waypoints.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/counterup/jquery.counterup.min.js" type="text/javascript"></script>
<!-- Monthly js -->
<script src="${pageContext.request.contextPath}/assets/plugins/monthly/monthly.js" type="text/javascript"></script>
<!-- End Page Lavel Plugins
   =====================================================================-->
<!-- Start Theme label Script
   =====================================================================-->
<!-- Dashboard js -->
<script src="${pageContext.request.contextPath}/assets/dist/js/dashboard.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/chartSettings.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/chartjs-plugin-labels.js"></script>
<!-- End Theme label Script
   =====================================================================-->
<script>
   function calculatePercentage(total,got) {

      return (((parseInt(got)*100)/parseInt(total))).toFixed(2) +"%"

   }


   var statuschart=null;

   function loaddefaultparams(){
      var searchParams = new URLSearchParams(window.location.search);
      var temp_startdate = "<%=UtilityClass.getFirstDateAndEndDatePlain()[0]%>"
      var temp_enddate = "<%=UtilityClass.getFirstDateAndEndDatePlain()[1]%>"
      var  startdate = searchParams.get("startdate") || temp_startdate;
      if(startdate==0)
      {
         startdate=temp_startdate;
      }
      var enddate = searchParams.get("enddate") || temp_enddate;
      if(enddate==0)
      {
         enddate=temp_enddate;
      }

      var  url = "startdate="+startdate+"&enddate="+enddate;
      $.ajax({
         url: "${pageContext.request.contextPath}/api/mailreportcard/getreport?"+url,
         type: 'GET',
         success: function (json) {
            var totalsent=0;
            var totalfailed=0;
            var totalmail=0;
            var mailstatus;
            var percentage=0;

            var lableforstatus=[];
            var dataforstatus=[];

            var statushtml = "";

            for(var i=0;i<json.length;i++)
            {
               totalmail=totalmail+parseInt(json[i].count)
               if(json[i].status==1)
               {
                  mailstatus = 'Sent'
                  totalsent=totalsent+parseInt(json[i].count)


               }
               if(json[i].status== -1)
               {
                  mailstatus = 'Failed'
                  totalfailed=totalfailed+parseInt(json[i].count)

               }

                  lableforstatus.push(mailstatus);
                  dataforstatus.push(json[i].count);


            }

            $("#totalmail").html(totalmail);
            $("#totalsent").html(totalsent);
            $("#totalfailed").html(totalfailed);

            for(var i=0;i<json.length;i++) {
               if(json[i].status==1)
               {
                  mailstatus = 'Sent'


               }
               if(json[i].status== -1)
               {
                  mailstatus = 'Failed'


               }
               statushtml = statushtml + "<tr><td>" + mailstatus + "</td><td>" + json[i].count + "</td><td>" + calculatePercentage(totalmail,json[i].count) + "</td><td><i hidvalue='" + mailstatus + "' title='Click Here To View data' class=\"hvr-buzz-out fa fa-table takemetotable\"></i></td></tr>"
            }

            $("#tbody").html(statushtml);
            $("#totalmail").html(totalmail);

            var chartType = localStorage.getItem("chartType");
            if(chartType == undefined || chartType == "undefined"){
               chartType = "bar";
            }

            var colors = ['rgba(255, 99, 132, 0.5)',
               'rgba(54, 162, 235, 0.5)',
               'rgba(255, 206, 86, 0.5)',
               'rgba(75, 192, 192, 0.5)',
               'rgba(153, 102, 255, 0.5)',
               'rgba(255, 159, 64, 0.5)'
            ];
            var options = JSON.parse(localStorage.getItem("chartOptions"));

            if(statuschart!=null)
            {
               statuschart.destroy();
            }
            var ctx = document.getElementById("statuschart");
            statuschart= new Chart(ctx, {
               type: chartType,
               data: {
                  labels: lableforstatus,
                  datasets: [
                     {
                        label: "Mail Status",
                        data: dataforstatus,
                       // borderColor: colors,
                      //  width: "0.2",
                       // borderWidth: "0",
                        backgroundColor: colors
                     }
                  ]
               },
               options: options
            });




         },
         error: function (err) {


         }
      })
   }
   loaddefaultparams();


   //============================ RUNFILTER
   $("#filterform").on("submit", function (e) {
      e.preventDefault();
      var todate = $("#todate").val()||0;
      var fromdate = $("#fromdate").val()||0;
      if(todate!=0&&fromdate!=0) {
         todate = todate.replace(/-/g, '');
         fromdate = fromdate.replace(/-/g, '');
      }
      var urlparams=   "startdate="+fromdate+"&enddate="+todate;
      var url=window.location.toString().split("?")[0];
      url = url+"?"+urlparams;
      window.history.pushState({}, null, url);

      loaddefaultparams();
      $('#charttab').find("a").trigger("click")
      //========================= SETTING DEFAULT PARAMS
   })
   //============================ LOAD USERS


   $(document).on("click",".takemetotable",function () {
      var searchParams = new URLSearchParams(window.location.search);
      var status=$(this).closest("td").find("i").eq(0).attr("hidvalue");
      var startdate=searchParams.get("startdate")||0
      var enddate=searchParams.get("enddate")||0
      var datevalue=0;
      if(startdate!=0&&enddate!=0) {
         datevalue = startdate.replace(/-/g, '')+'@'+enddate.replace(/-/g, '');
      }


      var urlparams = "datevalue=" + datevalue + "&status=" + status;
      window.open("${pageContext.request.contextPath}/mailhistory?"+urlparams);



   })


</script>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>

