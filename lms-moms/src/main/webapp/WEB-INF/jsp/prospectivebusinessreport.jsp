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
   <!-- <link href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/> -->
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
   <!--<link href="assets/dist/css/stylecrm-rtl.css" rel="stylesheet" type="text/css"/>-->
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
            <h1>Business Dashboard</h1>
            <small>Quick Overview</small>
         </div>
      </section>
      <!-- Main content -->
      <section class="content">

         <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs">
               <li id="charttab" class="active"><a href="#tab3" data-toggle="tab" title="Click to view Graphical Report">Graphical Report <i class="fa fa-bar-chart"></i></a></li>
               <li> <a href="#tab4" data-toggle="tab" title="Click to view Tabular Report">Tabular Report <i class="fa fa-table"></i></a></li>
               <li><a href="#tab5" data-toggle="tab" title="Click to filter Report">Filter <i class="fa fa-filter"></i></a></li>
               <li><a href="#" onclick="onSettingFromLoad()" data-toggle="modal" data-target="#graphSettings" title="Click to filter Report">Settings <i class="fa fa-gear"></i></a></li>
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
                                    <h4>Lead Stage Graph</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <canvas id="stagechart" class="chartCanvasClass" ></canvas>
                              </div>
                           </div>

                        </div>

                        <div class="col-sm-6 col-md-12 chartPanelClass">
                           <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Lead Status Graph</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <canvas id="statuschart" class="chartCanvasClass"></canvas>
                              </div>
                           </div>
                        </div>

                        <div class="col-sm-6 col-md-12 chartPanelClass">
                           <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Lead Type Graph</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <canvas id="typechart" class="chartCanvasClass"></canvas>
                              </div>
                           </div>
                        </div>

                        <div class="col-sm-6 col-md-12 chartPanelClass">
                           <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Lead Product Graph</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <canvas id="productchart" class="chartCanvasClass"></canvas>
                              </div>
                           </div>
                        </div>

                        <div class="col-sm-6 col-md-12 chartPanelClass">
                           <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Lead Source Graph</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <canvas id="sourcechart" class="chartCanvasClass"></canvas>
                              </div>
                           </div>
                        </div>

                        <div class="col-sm-6 col-md-12 chartPanelClass">
                           <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Lead Priority Graph</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <canvas id="leadprioritychart" class="chartCanvasClass"></canvas>
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
                                    <h4>Lead Stage</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <div class="table-responsive">
                                    <table class="table table-bordered table-striped table-hover">

                                       <thead><tr><th>Name</th><th>Amount</th><th>Percentage <span style="color: green">(%)</span></th><th>Action</th></tr></thead>
                                       <tbody id="tbodystagechart">

                                       </tbody>

                                    </table>
                                 </div>
                              </div>
                           </div>
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                           <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Lead Status</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <div class="table-responsive">
                                    <table class="table table-bordered table-striped table-hover">

                                       <thead><tr><th>Name</th><th>Amount</th><th>Percentage <span style="color: green">(%)</span></th><th>Action</th></tr></thead>

                                       <tbody id="tbodystatuschart">

                                       </tbody>

                                    </table>
                                 </div>
                              </div>
                           </div>
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                           <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Lead Type</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <div class="table-responsive">
                                    <table class="table table-bordered table-striped table-hover">

                                       <thead><tr><th>Name</th><th>Amount</th><th>Percentage <span style="color: green">(%)</span></th><th>Action</th></tr></thead>

                                       <tbody id="tbodytypechart">

                                       </tbody>

                                    </table>
                                 </div>
                              </div>
                           </div>
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                           <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Lead Product</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <div class="table-responsive">
                                    <table class="table table-bordered table-striped table-hover">

                                       <thead><tr><th>Name</th><th>Amount</th><th>Percentage <span style="color: green">(%)</span></th><th>Action</th></tr></thead>

                                       <tbody id="tbodyproductchart">

                                       </tbody>

                                    </table>
                                 </div>
                              </div>
                           </div>
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                           <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Lead Source</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <div class="table-responsive">
                                    <table class="table table-bordered table-striped table-hover">

                                       <thead><tr><th>Name</th><th>Amount</th><th>Percentage <span style="color: green">(%)</span></th><th>Action</th></tr></thead>

                                       <tbody id="tbodysourcechart">

                                       </tbody>

                                    </table>
                                 </div>
                              </div>
                           </div>
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                           <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Lead Priority</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <div class="table-responsive">
                                    <table class="table table-bordered table-striped table-hover">

                                       <thead><tr><th>Name</th><th>Amount</th><th>Percentage <span style="color: green">(%)</span></th><th>Action</th></tr></thead>

                                       <tbody id="tbodyleadprioritychart">

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
                                          <label>Select User</label>
                                          <select id="user" name="user" class="form-control">

                                          </select>
                                       </div>

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
      var result = (((parseInt(got)*100)/parseInt(total))).toFixed(2)
      if(isNaN(result)){
         return "0.00%"
      }
      else return result +"%"

   }


   var statuschart=null;
   var stagechart=null;
   var typechart=null;
   var productchart=null;
   var sourcechart=null;
   var innersorucechart=null;
   var leadprioritychart=null;

   function loaddefaultparams(){
      var searchParams = new URLSearchParams(window.location.search);
      var temp_startdate = "<%=UtilityClass.getFirstDateAndEndDatePlain()[0]%>"
      var temp_enddate = "<%=UtilityClass.getFirstDateAndEndDatePlain()[1]%>"
      var userid = searchParams.get("userid") || "0";
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

      var  url = "userid="+userid+"&startdate="+startdate+"&enddate="+enddate;
      $.ajax({
         url: "${pageContext.request.contextPath}/api/leadreportcard/getbusinessreport?"+url,
         type: 'GET',
         success: function (json) {

            var totalBusiness = 0;
            var lableforstage=[];
            var dataforstage=[];
            var lableforstatus=[];
            var dataforstatus=[];
            var lablefortype=[];
            var datafortype=[];
            var lableforproduct=[];
            var dataforproduct=[];
            var lableforsource=[];
            var dataforsource=[];
            var lableforleadpriority=[];
            var dataforleadpriority=[];

            var stagehtml = "";
            var statushtml = "";
            var typehtml = "";
            var producthtml = "";
            var sourcehtml = "";
            var leadpriorityhtml = "";

            for(var i=0;i<json.length;i++)
            {

               if(json[i].seq==1)
               {
                  totalBusiness = json[i].count;

               }

               if(json[i].seq==2)
               {

                  lableforstage.push(json[i].description);
                  dataforstage.push(json[i].count)

                  stagehtml = stagehtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td><td>"+calculatePercentage(totalBusiness,json[i].count)+"</td><td><i hidtype='stage' hidvalue='"+json[i].description+"' title='Click Here To View Report' class=\"hvr-buzz-out fa fa-bar-chart-o takemetoreport\"></i><i title='Click Here To View Data' class=\"hvr-buzz-out fa fa-table takemetotable\"></i></td></tr>"

               }
               if(json[i].seq==3)
               {
                  lableforstatus.push(json[i].description);
                  dataforstatus.push(json[i].count);
                  statushtml = statushtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td><td>"+calculatePercentage(totalBusiness,json[i].count)+"</td><td><i hidtype='status' hidvalue='"+json[i].description+"' title='Click Here To View Report' class=\"hvr-buzz-out fa fa-bar-chart-o takemetoreport\"></i><i title='Click Here To View Data' class=\"hvr-buzz-out fa fa-table takemetotable\"></i></td></tr>"

               }
               if(json[i].seq==4)
               {
                  lablefortype.push(json[i].description);
                  datafortype.push(json[i].count);
                  typehtml = typehtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td><td>"+calculatePercentage(totalBusiness,json[i].count)+"</td><td><i hidtype='type' hidvalue='"+json[i].description+"' title='Click Here To View Report' class=\"hvr-buzz-out fa fa-bar-chart-o takemetoreport\"></i><i title='Click Here To View Data' class=\"hvr-buzz-out fa fa-table takemetotable\"></i></td></tr>"

               }
               if(json[i].seq==5)
               {
                  lableforproduct.push(json[i].description);
                  dataforproduct.push(json[i].count);
                  producthtml = producthtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td><td>"+calculatePercentage(totalBusiness,json[i].count)+"</td><td><i hidtype='product' hidvalue='"+json[i].description+"' title='Click Here To View Report' class=\"hvr-buzz-out fa fa-bar-chart-o takemetoreport\"></i><i title='Click Here To View Data' class=\"hvr-buzz-out fa fa-table takemetotable\"></i></td></tr>"

               }
               if(json[i].seq==6)
               {
                  lableforsource.push(json[i].description);
                  dataforsource.push(json[i].count);
                  sourcehtml = sourcehtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td><td>"+calculatePercentage(totalBusiness,json[i].count)+"</td><td><i hidtype='source' hidvalue='"+json[i].description+"' title='Click Here To View Report' class=\"hvr-buzz-out fa fa-bar-chart-o takemetoreport\"></i><i title='Click Here To View Data' class=\"hvr-buzz-out fa fa-table takemetotable\"></i></td></tr>"

               }
               if(json[i].seq==7)
               {
                  lableforleadpriority.push(json[i].description);
                  dataforleadpriority.push(json[i].count);
                 leadpriorityhtml = leadpriorityhtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td><td>"+calculatePercentage(totalBusiness,json[i].count)+"</td><td><i hidtype='leadpriority' hidvalue='"+json[i].description+"' title='Click Here To View Report' class=\"hvr-buzz-out fa fa-bar-chart-o takemetoreport\"></i><i title='Click Here To View Data' class=\"hvr-buzz-out fa fa-table takemetotable\"></i></td></tr>"

               }


            }
            $("#tbodystagechart").html(stagehtml);
            $("#tbodystatuschart").html(statushtml);
            $("#tbodytypechart").html(typehtml);
            $("#tbodyproductchart").html(producthtml);
            $("#tbodysourcechart").html(sourcehtml);
            $("#tbodyleadprioritychart").html(leadpriorityhtml);


            var chartType = localStorage.getItem("chartType");
            if(chartType == undefined || chartType == "undefined"){
               chartType = "bar";
            }

            var dynamicColors = function() {
               var r = Math.floor(Math.random() * 240);
               var g = Math.floor(Math.random() * 240);
               var b = Math.floor(Math.random() * 240);
               var a = "0.5";
               return "rgba(" + r + "," + g + "," + b + ","+a+")";
            };

            function getColorArray(labelArray){
               var colors = [];
               for (var i =0;i<labelArray.length;i++) {
                  colors.push(dynamicColors());
               }
               return colors;
            }

            var options = JSON.parse(localStorage.getItem("chartOptions"));


            if(stagechart!=null)
            {
               stagechart.destroy();
            }
            var ctx = document.getElementById("stagechart");
            stagechart = new Chart(ctx, {
               type: chartType,
               data: {
                  labels: lableforstage,
                  datasets: [
                     {
                        label: "Lead Stage",
                        data: dataforstage,
                        //borderColor: getColorArray(lableforstage),
                        backgroundColor: getColorArray(lableforstage)
                     }
                  ]
               },
               options: options
            });

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
                        label: "Lead Status",
                        data: dataforstatus,
                       // borderColor: getColorArray(lableforstatus),
                      //  width: "0.2",
                      //  borderWidth: "0",
                        backgroundColor: getColorArray(lableforstatus)
                     }
                  ]
               },
               options: options
            });


            if(typechart!=null)
            {
               typechart.destroy();
            }
            var ctxType = document.getElementById("typechart");
            typechart = new Chart(ctxType, {
               type: chartType,
               data: {
                  labels: lablefortype,
                  datasets: [
                     {
                        label: "Lead Type",
                        data: datafortype,
                        //borderColor: getColorArray(lablefortype),

                        backgroundColor: getColorArray(lablefortype)
                     }
                  ]
               },
               options: options
            });

            if(productchart!=null)
            {
               productchart.destroy();
            }
            var ctxProduct = document.getElementById("productchart");
            productchart = new Chart(ctxProduct, {
               type: chartType,
               data: {
                  labels: lableforproduct,
                  datasets: [
                     {
                        label: "Lead Product",
                        data: dataforproduct,
                       // borderColor: getColorArray(lableforproduct),
                       // width: "0.2",
                        //borderWidth: "0",
                        backgroundColor: getColorArray(lableforproduct)
                     }
                  ]
               },
               options: options
            });

            if(sourcechart!=null)
            {
               sourcechart.destroy();
            }
            var ctxSource = document.getElementById("sourcechart");
            sourcechart = new Chart(ctxSource, {
               type: chartType,
               data: {
                  labels: lableforsource,
                  datasets: [
                     {
                        label: "Lead Source",
                        data: dataforsource,
                       // borderColor: getColorArray(lableforsource),
                      //  width: "0.2",
                       // borderWidth: "0",
                        backgroundColor: getColorArray(lableforsource)
                     }
                  ]
               },
               options: options
            });



            if(leadprioritychart!=null)
            {
               leadprioritychart.destroy();
            }
            var ctxLeadPriority = document.getElementById("leadprioritychart");
            sourcechart = new Chart(ctxLeadPriority, {
               type: chartType,
               data: {
                  labels: lableforleadpriority,
                  datasets: [
                     {
                        label: "Lead Priority",
                        data: dataforleadpriority,
                       // borderColor: getColorArray(lableforleadpriority),
                       // width: "0.2",
                       // borderWidth: "0",
                        backgroundColor: getColorArray(lableforleadpriority)
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
      var id = $("#user").val()||0;
      if(fromdate!=0) {
         fromdate = fromdate.replace(/-/g, '');
      }
      if(todate!=0) {
         todate = todate.replace(/-/g, '');
      }
      var urlparams=   "userid="+id+"&startdate="+fromdate+"&enddate="+todate;
      var url=window.location.toString().split("?")[0];
      url = url+"?"+urlparams;
      window.history.pushState({}, null, url);

      loaddefaultparams();
      $('#charttab').find("a").trigger("click")
      //========================= SETTING DEFAULT PARAMS
   })
   //============================ LOAD USERS
   loaduser();

   function loaduser() {
      $.ajax({
         type: "GET",
         contentType: "application/json",
         url: "${pageContext.servletContext.contextPath}/api/user/getusermyteam",
         success: function (json) {
            var html = "<option value='0'>---All User---</option>";
            for (var i = 0; i < json.length; i++) {
               html = html + "<option  value='" + json[i].id + "'>" + json[i].username + "</option> ";

            }
            $('#user').html(html)


         },
         error: function (err) {

         }

      });
   }
   $(document).on("click",".takemetoreport",function () {
       var searchParams = new URLSearchParams(window.location.search);
      var hidtype=$(this).attr("hidtype");
      var hidvalue=$(this).attr("hidvalue");
      var startdate=searchParams.get("startdate")||0
      var enddate=searchParams.get("enddate")||0


      var userfilter = searchParams.get("userid")||0
      var url="?statussearch="+hidvalue+"&startdate="+startdate+"&enddate="+enddate+"&userid="+userfilter
      switch (hidtype) {
         case "stage":
            window.open("${pageContext.request.contextPath}/getreportbyleadstage"+url)
            break;
         case "status":
            window.open("${pageContext.request.contextPath}/getreportbyleadstatus"+url)
            break;
         case "type":
            window.open("${pageContext.request.contextPath}/getreportbyleadtype"+url)
            break;
         case "product":
            window.open("${pageContext.request.contextPath}/getreportbyleadproduct"+url)
            break;
         case "source":
            window.open("${pageContext.request.contextPath}/getreportbyleadsource"+url)
            break;
         case "leadpriority":
            window.open("${pageContext.request.contextPath}/getreportbyleadpriority"+url)
            break;


      }

   })

   $(document).on("click",".takemetotable",function () {
       var searchParams = new URLSearchParams(window.location.search);
      var hidtype=$(this).closest("td").find("i").eq(0).attr("hidtype");
      var hidvalue=$(this).closest("td").find("i").eq(0).attr("hidvalue");
      var startdate=searchParams.get("startdate")||0
      var enddate=searchParams.get("enddate")||0

      var leadstatus = 0;
      var leadsource =  0;
      var product =  0;
      var leadtype =  0;
      var userfilter = searchParams.get("userid")||0
      var datefilter = false;
      var customdate =  0;
      var searchstring = 0;
      var leadstage=   0;
      var leadpriority=   0;

      var id=    0;
      if(startdate!=0&&enddate!=0)
      {
         datefilter=true;
         customdate=startdate+"@"+enddate;
      }


      switch (hidtype) {
         case "stage":
            leadstage=hidvalue;
            var urlparams="statusValue="+leadstatus+"&leadSource="+leadsource+"&product="+product+"&leadType="+leadtype+"&userFilter="+userfilter+"&datefilter="+datefilter+"&datevalue="+customdate+"&searchvalue="+searchstring+"&leadstage="+leadstage+"&leadpriority="+leadpriority;

            window.open("${pageContext.request.contextPath}/lead?"+urlparams)

            break;
         case "status":
            leadstatus=hidvalue;
            var urlparams="statusValue="+leadstatus+"&leadSource="+leadsource+"&product="+product+"&leadType="+leadtype+"&userFilter="+userfilter+"&datefilter="+datefilter+"&datevalue="+customdate+"&searchvalue="+searchstring+"&leadstage="+leadstage+"&leadpriority="+leadpriority;

            window.open("${pageContext.request.contextPath}/lead?"+urlparams)
            break;
         case "type":
            leadtype=hidvalue;
            var urlparams="statusValue="+leadstatus+"&leadSource="+leadsource+"&product="+product+"&leadType="+leadtype+"&userFilter="+userfilter+"&datefilter="+datefilter+"&datevalue="+customdate+"&searchvalue="+searchstring+"&leadstage="+leadstage+"&leadpriority="+leadpriority;

            window.open("${pageContext.request.contextPath}/lead?"+urlparams)
            break;
         case "product":
            product=hidvalue;
            var urlparams="statusValue="+leadstatus+"&leadSource="+leadsource+"&product="+product+"&leadType="+leadtype+"&userFilter="+userfilter+"&datefilter="+datefilter+"&datevalue="+customdate+"&searchvalue="+searchstring+"&leadstage="+leadstage+"&leadpriority="+leadpriority;

            window.open("${pageContext.request.contextPath}/lead?"+urlparams)
            break;
         case "source":
            leadsource=hidvalue;
            var urlparams="statusValue="+leadstatus+"&leadSource="+leadsource+"&product="+product+"&leadType="+leadtype+"&userFilter="+userfilter+"&datefilter="+datefilter+"&datevalue="+customdate+"&searchvalue="+searchstring+"&leadstage="+leadstage+"&leadpriority="+leadpriority;

            window.open("${pageContext.request.contextPath}/lead?"+urlparams)
            break;
         case "leadpriority":
            leadpriority=hidvalue;
            var urlparams="statusValue="+leadstatus+"&leadSource="+leadsource+"&product="+product+"&leadType="+leadtype+"&userFilter="+userfilter+"&datefilter="+datefilter+"&datevalue="+customdate+"&searchvalue="+searchstring+"&leadstage="+leadstage+"&leadpriority="+leadpriority;

            window.open("${pageContext.request.contextPath}/lead?"+urlparams)
            break;



      }
   })
</script>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>

