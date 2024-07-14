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
   <jsp:include page="title.jsp"></jsp:include>

   <!-- =============================FOR CUSTOM ALERTS ============================================ -->
   <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.css"/>
   <script src="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.js"></script>

   <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css">
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

      .takemetoreport{
         display: none;
      }

      /*FOR TOTALS UNDER EACH TABLE*/
      .boldCell{
         font-weight: 900 !important;
      }

      #cardbox1, #cardbox2, #cardbox3 {
         cursor:default !important;
      }

      #cardbox1:focus,#cardbox1:hover,#cardbox2:focus,#cardbox2:hover,#cardbox3:focus,#cardbox3:hover {
         color:#fff;
         cursor:default;
         background:#009688;
         box-shadow:0 10px 20px rgba(0,0,0,.19),0 6px 6px rgba(0,0,0,.23)
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
   <jsp:include page="header.jsp"></jsp:include>
   <!-- =============================================== -->
   <!-- Left side column. contains the sidebar -->
   <jsp:include page="sidebar.jsp"></jsp:include>
   <!-- =============================================== -->
   <!-- Content Wrapper. Contains page content -->
   <div class="content-wrapper">
      <!-- Content Header (Page header) -->
      <section class="content-header">
         <div class="header-icon">
            <i class="fa fa-bar-chart-o"></i>
         </div>
         <div class="header-title">
            <h1 id="heading"></h1>
            <small>Quick Overview</small>
         </div>
      </section>
      <!-- Main content -->
      <section class="content">
         <div class="row">
            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
               <div id="cardbox1">
                  <div class="statistic-box">
                     <i class="fa fa-users fa-3x"></i>
                     <div class="counter-number pull-right">
                        <span class="count-number" id="totallead"></span>
                        <span class="slight"><i class="fa fa-play fa-rotate-270"> </i>
                              </span>
                     </div>
                     <h3> Total Leads</h3>
                  </div>
               </div>
            </div>
            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
               <div id="cardbox2">
                  <div class="statistic-box">
                     <i class="fa fa-refresh fa-3x"></i>
                     <div class="counter-number pull-right">
                        <span class="count-number" id="totalconvertlead"></span>
                        <span class="slight"><i class="fa fa-play fa-rotate-270"> </i>
                              </span>
                     </div>
                     <h3>Total Convert</h3>
                  </div>
               </div>
            </div>

            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
               <div id="cardbox3">
                  <div class="statistic-box">
                     <i class="hvr-buzz-out fa fa-percent fa-3x"></i>

                     <div class="counter-number pull-right">
                        <span class="count-number" id="conversionpercentage"></span>
                        <span class="slight">%
                              </span>
                     </div>
                     <h3>Conversion Percentage</h3>
                  </div>
               </div>
            </div>
         </div>





         <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs">
               <li class="active"><a  id="charttab" href="#tab3" data-toggle="tab" title="Click to view Graphical Report">Graphical Report <i class="fa fa-bar-chart"></i></a></li>
               <li><a href="#tab4" data-toggle="tab" title="Click to view Tabular Report">Tabular Report <i class="fa fa-table"></i></a></li>
               <li><a href="#tab5" id="filtertab" data-toggle="tab" title="Click to view Filter Report">Filter <i class="fa fa-filter"></i></a></li>
               <li><a href="#" onclick="onSettingFromLoad()" data-toggle="modal" data-target="#graphSettings" title="Click to filter Report">Settings <i class="fa fa-gear" ></i></a></li>
            </ul>
            <!-- Tab panels -->
            <div class="tab-content">
               <div class="tab-pane fade in active" id="tab3">
                  <div class="panel-body">
                     <%--=============================== GRAPHS ==============================--%>
                     <div class="row">
                        <div class="col-sm-6 col-md-12 chartPanelClass customsidedivmenu">
                           <div class="panel panel-bd lobidragdashboard">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Lead Stage Graph</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <canvas id="stagechart" ></canvas>
                              </div>
                           </div>

                        </div>

                        <div class="col-sm-6 col-md-12 chartPanelClass customsidedivmenu">
                           <div class="panel panel-bd lobidragdashboard">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Lead Status Graph</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <canvas id="statuschart" ></canvas>
                              </div>
                           </div>
                        </div>

                        <div class="col-sm-6 col-md-12 chartPanelClass customsidedivmenu">
                           <div class="panel panel-bd lobidragdashboard">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Lead Type Graph</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <canvas id="typechart" ></canvas>
                              </div>
                           </div>
                        </div>

                        <div class="col-sm-6 col-md-12 chartPanelClass customsidedivmenu">
                           <div class="panel panel-bd lobidragdashboard">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Lead Program Graph</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <canvas id="productchart" ></canvas>
                              </div>
                           </div>
                        </div>

                        <div class="col-sm-6 col-md-12 chartPanelClass customsidedivmenu">
                           <div class="panel panel-bd lobidragdashboard">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Lead Source Graph</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <canvas id="sourcechart" ></canvas>
                              </div>
                           </div>
                        </div>

                        <div class="col-sm-6 col-md-12 chartPanelClass customsidedivmenu">
                           <div class="panel panel-bd lobidragdashboard">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Lead Inner Source Graph</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <canvas id="innersourcechart" ></canvas>
                              </div>
                           </div>
                        </div>


                        <div class="col-sm-6 col-md-12 chartPanelClass customsidedivmenu">
                           <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Campaign Graph</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <canvas id="leadprioritychart" ></canvas>
                              </div>
                           </div>
                        </div>

                        <div class="col-sm-6 col-md-12 chartPanelClass customsidedivmenu">
                           <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                              <div class="panel-heading">
                                 <div class="panel-title">
                                    <h4>Keyword Graph</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <canvas id="keywordchart" ></canvas>
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

                                       <thead><tr><th>Name</th><th>Count</th><th>Percentage <span style="color: green">(%)</span></th><th>Action</th></tr></thead>
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

                                       <thead><tr><th>Name</th><th>Count</th><th>Percentage <span style="color: green">(%)</span></th><th>Action</th></tr></thead>
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

                                       <thead><tr><th>Name</th><th>Count</th><th>Percentage <span style="color: green">(%)</span></th><th>Action</th></tr></thead>
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
                                    <h4>Lead Program</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <div class="table-responsive">
                                    <table class="table table-bordered table-striped table-hover">

                                       <thead><tr><th>Name</th><th>Count</th><th>Percentage <span style="color: green">(%)</span></th><th>Action</th></tr></thead>
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

                                       <thead><tr><th>Name</th><th>Count</th><th>Percentage <span style="color: green">(%)</span></th><th>Action</th></tr></thead>
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
                                    <h4>Lead Source Inner</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <div class="table-responsive">
                                    <table class="table table-bordered table-striped table-hover">
                                       <thead><tr><th>Name</th><th>Count</th><th>Percentage <span style="color: green">(%)</span></th><th>Action</th></tr></thead>
                                       <tbody id="tbodysourceinnerchart">

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
                                    <h4>Campaign</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <div class="table-responsive">
                                    <table class="table table-bordered table-striped table-hover">
                                       <thead><tr><th>Name</th><th>Count</th><th>Percentage <span style="color: green">(%)</span></th><th>Action</th></tr></thead>
                                       <tbody id="tbodyleadprioritychart">

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
                                    <h4>Keyword</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <div class="table-responsive">
                                    <table class="table table-bordered table-striped table-hover">
                                       <thead><tr><th>Name</th><th>Count</th><th>Percentage <span style="color: green">(%)</span></th><th>Action</th></tr></thead>
                                       <tbody id="tbodykeywordchart">

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
                                    <h4>Filter Report</h4>
                                 </div>
                              </div>
                              <div style="" class="panel-body">
                                 <form class="form-horizontal" id="filterform">
                                    <fieldset>

                                       <div class="col-md-10 form-check">
                                         <br>
                                          <label class="form-check-label">
                                             <input type="radio" class="form-check-input" name="userListType" value="0" checked> By User
                                          </label>
                                          <label class="form-check-label">

                                          </label>
                                          <label class="form-check-label">

                                          </label>
                                          <label class="form-check-label" >
                                             <input type="radio" class="form-check-input" name="userListType" value="1" > By Team
                                          </label>
                                       </div>


                                       <div class="col-md-10 form-group" >
                                          <label>Select User</label>
                                          <select id="user" name="user" class="form-control">

                                          </select>
                                       </div>

                                       <div class="col-md-10 form-group" >
                                          <label id="selectlabel"></label>
                                          <select required id="sharedselect" class="form-control">

                                          </select>
                                       </div>

                                       <div class="col-md-10 form-group hidden" id="statusselectdiv" >
                                          <label>Select Status</label>
                                          <select id="statusselect" class="form-control">

                                          </select>
                                       </div>

                                       <div class="col-md-10 form-group hidden" id="clientypeselectdiv" >
                                          <label>Select Lead Type (Team)</label>
                                          <select id="clienttypeselect" class="form-control">

                                          </select>
                                       </div>

                                       <div class="col-md-10 form-check">
                                          <label class="control-label">Date Type</label>
                                          <br>
                                          <label class="form-check-label">
                                             <input type="radio" class="form-check-input" name="dateType" value="4" checked> Create Date
                                          </label>
                                          <label class="form-check-label" hidden>
                                             <input type="radio" class="form-check-input" name="dateType" value="1" > Lead Date
                                          </label>
                                          <label class="form-check-label">
                                             <input type="radio" class="form-check-input" name="dateType" value="2"> Convert Date
                                          </label>
                                          <label class="form-check-label">
                                             <input type="radio" class="form-check-input" name="dateType" value="3" > Update Date
                                          </label>
                                          <label class="form-check-label">
                                             <input type="radio" class="form-check-input" name="dateType" value="5"> Profiling Date
                                          </label>
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
      <jsp:include page="footer.jsp"></jsp:include>
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
   var keywordchart=null;

   function loaddefaultparams(){

      var temp_startdate = "<%=UtilityClass.getFirstDateAndEndDatePlain()[0]%>"
      var temp_enddate = "<%=UtilityClass.getFirstDateAndEndDatePlain()[1]%>"
       var searchParams = new URLSearchParams(window.location.search);
       var statussearch=searchParams.get("statussearch")||0;
      var clienttypevalue=searchParams.get("clienttypevalue")||0;
      let dateTypeFlag=searchParams.get("dateTypeFlag")||4;//4 means LMS entry date
      let userflag=searchParams.get("userflag")||0;//0 means for selected user only,1 means for selected user's team members

      var userlist = searchParams.get("userid") || "0";
      var flag=$('#flaghidden').val();

      var startdate = searchParams.get("startdate") || temp_startdate;
      if(startdate==0)
      {
         startdate=temp_startdate;
      }
      var enddate = searchParams.get("enddate") || temp_enddate;
      if(enddate==0)
      {
         enddate=temp_enddate;
      }
      var  url = "userlist="+userlist+"&startdate="+startdate+"&enddate="+enddate+"&flag="+flag+"&searchvalue="+statussearch+"&clienttypevalue="+clienttypevalue+"&dateTypeFlag="+dateTypeFlag+"&userflag="+userflag;

      $.ajax({
         url: "${pageContext.request.contextPath}/api/reportalllead/getreport?"+url,
         type: 'GET',
         success: function (json) {
            var totalconvertlead=0;
            var totallead=0;
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
            var lableforinnersource=[];
            var dataforinnersource=[];
            var lableforleadpriority=[];
            var dataforleadpriority=[];
            var lableforkeyword=[];
            var dataforkeyword=[];

            var stagehtml = "";
            var statushtml = "";
            var typehtml = "";
            var producthtml = "";
            var sourcehtml = "";
            var sourceinnerhtml = "";
            var leadpriorityhtml = "";
            var keywordhtml = "";


            //TOTAL COUNT OF EACH TABLE
            let stageCount = 0;
            let statusCount = 0;
            let typeCount = 0;
            let productCount = 0;
            let sourceCount = 0;
            let sourceInnerCount = 0;
            let leadpriorityCount = 0;
            let keywordCount = 0;

            $('#charttab').trigger("click")
            for(var i=0;i<json.length;i++)
            {
               if(json[i].seq==1)
               {
                  // $("#tabular").html(html);
                  totallead=json[i].count;
                  $('#totallead').html(json[i].count)

               }
               if(json[i].seq==2)
               {

                  totalconvertlead=json[i].count;
                  $('#totalconvertlead').html(totalconvertlead)
                  var percentage=calculatePercentage(totallead,totalconvertlead)
                  $('#conversionpercentage').html(percentage)
               }

               if(json[i].seq==3)
               {
                  lableforstage.push(json[i].description);
                  dataforstage.push(json[i].count)
                  stageCount += parseInt(json[i].count)
                  stagehtml = stagehtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td><td>"+calculatePercentage(totallead,json[i].count)+"</td><td><i hidtype='stage' hidvalue='"+json[i].description+"' title='Click Here To View Report' class=\"hvr-buzz-out fa fa-bar-chart-o takemetoreport\"></i><i title='Click Here To View Data' class=\"hvr-buzz-out fa fa-table takemetotable\"></i></td></tr>"

               }
               if(json[i].seq==4)
               {
                  lableforstatus.push(json[i].description);
                  dataforstatus.push(json[i].count);
                  statusCount += parseInt(json[i].count)

                  statushtml = statushtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td><td>"+calculatePercentage(totallead,json[i].count)+"</td><td><i hidtype='status' hidvalue='"+json[i].description+"' title='Click Here To View Report' class=\"hvr-buzz-out fa fa-bar-chart-o takemetoreport\"></i><i title='Click Here To View Data' class=\"hvr-buzz-out fa fa-table takemetotable\"></i></td></tr>"

               }
               if(json[i].seq==5)
               {
                  lablefortype.push(json[i].description);
                  datafortype.push(json[i].count);
                  typeCount += parseInt(json[i].count)

                  typehtml = typehtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td><td>"+calculatePercentage(totallead,json[i].count)+"</td><td><i hidtype='type' hidvalue='"+json[i].description+"' title='Click Here To View Report' class=\"hvr-buzz-out fa fa-bar-chart-o takemetoreport\"></i><i title='Click Here To View Data' class=\"hvr-buzz-out fa fa-table takemetotable\"></i></td></tr>"


               }
               if(json[i].seq==6)
               {
                  lableforproduct.push(json[i].description);
                  dataforproduct.push(json[i].count);
                  productCount += parseInt(json[i].count)

                  producthtml = producthtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td><td>"+calculatePercentage(totallead,json[i].count)+"</td><td><i hidtype='product' hidvalue='"+json[i].description+"' title='Click Here To View Report' class=\"hvr-buzz-out fa fa-bar-chart-o takemetoreport\"></i><i title='Click Here To View Data' class=\"hvr-buzz-out fa fa-table takemetotable\"></i></td></tr>"
               }
               if(json[i].seq==7)
               {
                  lableforsource.push(json[i].description);
                  dataforsource.push(json[i].count);
                  sourceCount += parseInt(json[i].count)

                  sourcehtml = sourcehtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td><td>"+calculatePercentage(totallead,json[i].count)+"</td><td><i hidtype='source' hidvalue='"+json[i].description+"' title='Click Here To View Report' class=\"hvr-buzz-out fa fa-bar-chart-o takemetoreport\"></i><i title='Click Here To View Data' class=\"hvr-buzz-out fa fa-table takemetotable\"></i></td></tr>"

               }
               if(json[i].seq==8)
               {
                  lableforinnersource.push(json[i].description);
                  dataforinnersource.push(json[i].count);
                  sourceInnerCount += parseInt(json[i].count)

                  sourceinnerhtml = sourceinnerhtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td><td>"+calculatePercentage(totallead,json[i].count)+"</td><td><i hidtype='innersource' hidvalue='"+json[i].description+"' title='Click Here To View Report' class=\"hvr-buzz-out fa fa-bar-chart-o takemetoreport takemetoreport\"></i><i title='Click Here To View Data' class=\"hvr-buzz-out fa fa-table takemetotable\"></i></td></tr>"
               }
               //CAMPAIGN
               if(json[i].seq==9)
               {
                  lableforleadpriority.push(json[i].description);
                 dataforleadpriority.push(json[i].count);
                  leadpriorityCount += parseInt(json[i].count)

                 leadpriorityhtml = leadpriorityhtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td><td>"+calculatePercentage(totallead,json[i].count)+"</td><td><i hidtype='leadpriority' hidvalue='"+json[i].description+"' title='Click Here To View Report' class=\"hvr-buzz-out fa fa-bar-chart-o takemetoreport takemetoreport\"></i><i title='Click Here To View Data' class=\"hvr-buzz-out fa fa-table takemetotable\"></i></td></tr>"
               }
               if(json[i].seq==10)
               {
                  lableforkeyword.push(json[i].description);
                  dataforkeyword.push(json[i].count);
                  keywordCount += parseInt(json[i].count)

                  keywordhtml = keywordhtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td><td>"+calculatePercentage(totallead,json[i].count)+"</td><td><i hidtype='keyword' hidvalue='"+json[i].description+"' title='Click Here To View Report' class=\"hvr-buzz-out fa fa-bar-chart-o takemetoreport takemetoreport\"></i><i title='Click Here To View Data' class=\"hvr-buzz-out fa fa-table takemetotable\"></i></td></tr>"
               }

            }

            stagehtml = stagehtml+"<tr ><td class='boldCell' > Total </td><td class='boldCell'>"+stageCount+"</td><td> </td><td> </td></tr>"
            statushtml = statushtml+"<tr><td class='boldCell' > Total </td><td class='boldCell' >"+statusCount+"</td><td> </td><td> </td></tr>"
            typehtml = typehtml+"<tr><td class='boldCell' > Total </td><td class='boldCell' >"+typeCount+"</td><td> </td><td> </td></tr>"
            producthtml = producthtml+"<tr><td class='boldCell' > Total </td><td class='boldCell' >"+productCount+"</td><td> </td><td> </td></tr>"
            sourcehtml = sourcehtml+"<tr><td class='boldCell' > Total </td><td class='boldCell' >"+sourceCount+"</td><td> </td><td> </tr>"
            sourceinnerhtml = sourceinnerhtml+"<tr><td class='boldCell' > Total </td><td class='boldCell' >"+sourceInnerCount+"</td><td> </td><td> </td></tr>"
            leadpriorityhtml = leadpriorityhtml+"<tr><td class='boldCell' > Total </td><td class='boldCell' >"+leadpriorityCount+"</td><td> </td><td> </td></tr>"
            keywordhtml = keywordhtml+"<tr><td class='boldCell' > Total </td><td class='boldCell' >"+keywordCount+"</td><td> </td><td> </td></tr>"

            $("#tbodystagechart").html(stagehtml);
            $("#tbodystatuschart").html(statushtml);
            $("#tbodytypechart").html(typehtml);
            $("#tbodyproductchart").html(producthtml);
            $("#tbodysourcechart").html(sourcehtml);
            $("#tbodysourceinnerchart").html(sourceinnerhtml);
            $("#tbodyleadprioritychart").html(leadpriorityhtml);
            $("#tbodykeywordchart").html(keywordhtml);


            var chartType = localStorage.getItem("chartType");
            if(chartType == undefined || chartType == "undefined"){
               chartType = "bar";
            }
            // var colors = ['rgba(255, 99, 132, 0.5)',
            //    'rgba(54, 162, 235, 0.5)',
            //    'rgba(255, 206, 86, 0.5)',
            //    'rgba(75, 192, 192, 0.5)',
            //    'rgba(153, 102, 255, 0.5)',
            //    'rgba(255, 159, 64, 0.5)'
            // ];

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
                       // borderColor: getColorArray(lableforstage),
                        backgroundColor: getColorArray(lableforstage)
                     }
                  ]
               },
               options: options
            });
            var opt = {
               events: false,
               tooltips: {
                  enabled: false
               },
               hover: {
                  animationDuration: 0
               },
               animation: {
                  duration: 1,
                  onComplete: function () {
                     var chartInstance = this.chart,
                             ctx = chartInstance.ctx;
                     ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
                     ctx.textAlign = 'center';
                     ctx.textBaseline = 'bottom';

                     this.data.datasets.forEach(function (dataset, i) {
                        var meta = chartInstance.controller.getDatasetMeta(i);
                        meta.data.forEach(function (bar, index) {
                           var data = dataset.data[index];
                           ctx.fillText(data, bar._model.x, bar._model.y - 5);
                        });
                     });
                  }
               }
            };

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
                       // width: "0.2",
                        //borderWidth: "0",
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
                       // borderColor: getColorArray(lablefortype),
                       // width: "0.2",
                       // borderWidth: "0",
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
                        //borderColor: getColorArray(lableforproduct),
                        //width: "0.2",
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
                       // width: "0.2",
                        //borderWidth: "0",
                        backgroundColor: getColorArray(lableforsource)
                     }
                  ]
               },
               options: options
            });

            if(innersorucechart!=null)
            {
               innersorucechart.destroy();
            }
            var ctxInnerSource = document.getElementById("innersourcechart");
            innersorucechart = new Chart(ctxInnerSource, {
               type: chartType,
               data: {
                  labels: lableforinnersource,
                  datasets: [
                     {
                        label: "Lead Inner Source",
                        data: dataforinnersource,
                        //borderColor: getColorArray(lableforinnersource),
                        //width: "0.2",
                       // borderWidth: "0",
                        backgroundColor: getColorArray(lableforinnersource)
                     }
                  ]
               },
               options:options
            });



            if(leadprioritychart!=null)
            {
               leadprioritychart.destroy();
            }
            var ctxLeadPriority = document.getElementById("leadprioritychart");
            leadprioritychart = new Chart(ctxLeadPriority, {
               type: chartType,
               data: {
                  labels: lableforleadpriority,
                  datasets: [
                     {
                        label: "Campaigns",
                        data: dataforleadpriority,
                        //borderColor: getColorArray(lableforleadpriority),
                       // width: "0.2",
                       // borderWidth: "0",
                        backgroundColor: getColorArray(lableforleadpriority)
                     }
                  ]
               },
               options: options
            });

            if(keywordchart!=null)
            {
               keywordchart.destroy();
            }
            var ctxKeyword = document.getElementById("keywordchart");
            keywordchart = new Chart(ctxKeyword, {
               type: chartType,
               data: {
                  labels: lableforkeyword,
                  datasets: [
                     {
                        label: "Keywords",
                        data: dataforkeyword,
                        //borderColor: getColorArray(lableforleadpriority),
                        // width: "0.2",
                        // borderWidth: "0",
                        backgroundColor: getColorArray(lableforkeyword)
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
   var searchParams = new URLSearchParams(window.location.search);
   var statussearch=searchParams.get("statussearch")||0;

   if(statussearch==0)
   {
      $('#filtertab').trigger("click");
   }
   else{
      loaddefaultparams();

   }




   //============================ RUNFILTER
   $("#filterform").on("submit", function (e) {
      e.preventDefault();
      var startdate = $("#fromdate").val()||0;
      var enddate = $("#todate").val()||0;
      var userlist = $("#user").val()||0;
      var flag = $("#flaghidden").val();
      let dateTypeFlag = $("input[name='dateType']:checked").val();
      let userflag = $("input[name='userListType']:checked").val();
      var statussearch;
      let clienttypevalue=0;

      if($("#flaghidden").val() == 5){
         clienttypevalue = $("#clienttypeselect").val() || 0;
      }

      if($("#flaghidden").val() == 2){
         statussearch = $("#statusselect").val();
      }
      else{
         statussearch = $("#sharedselect").val();
      }
      if(startdate!=0) {
         startdate = startdate.replace(/-/g, '');
      }
      if(enddate!=0) {
         enddate = enddate.replace(/-/g, '');
      }

      var urlparams= "userid="+userlist+"&startdate="+startdate+"&enddate="+enddate+"&flag="+flag+"&statussearch="+statussearch+"&dateTypeFlag="+dateTypeFlag+"&userflag="+userflag+"&clienttypevalue="+clienttypevalue;

      var url=window.location.toString().split("?")[0];
      url = url+"?"+urlparams;
      window.history.pushState({}, null, url);
      loaddefaultparams();
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
            var html = "<option value='0'>All Users</option>";
            for (var i = 0; i < json.length; i++) {
               html = html + "<option  value='" + json[i].id + "'>" + json[i].firstName + " " + json[i].lastName +" ("+ json[i].username + ")</option> ";

            }
            $('#user').html(html)


         },
         error: function (err) {

         }

      });
   }

   //============================ LOAD LEAD SOURCE INNER
   function leadleadsourceinnner(url,temp_html) {

      $.ajax({
         type: "GET",
         contentType: "application/json",
         url: url,
         success: function (json) {
            var html = temp_html;
            for (var i = 0; i < json.length; i++) {
               html = html + "<option  value='" + json[i] + "'>" + json[i] + "</option> ";

            }
            $('#sharedselect').html(html)


         },
         error: function (err) {

         }

      });
   }

   //============================ LOAD status
    function fetchselect(url,temp_html,selectId) {

      console.log("loading selection for :: ",selectId)

      $.ajax({
         type: "GET",
         contentType: "application/json",
         url: url,
         success: function (json) {
            var html = temp_html;
            for (var i = 0; i < json.length; i++) {
               html = html + "<option  value='" + json[i].name + "'>" + json[i].name + "</option> ";
            }
            $(selectId).html(html)
         },
         error: function (err) {
            console.log("Error while loading select for :: ",selectId)

         }

      });
   }



   loadselect();

   function loadstage() {

      $.ajax({
         type: "GET",
         contentType: "application/json",
         url: "${pageContext.servletContext.contextPath}/api/leadstage/getall",
         success: function (json) {
            var html = "<option value=''>---Select Lead Stage---</option>";
            for (var i = 0; i < json.length; i++) {
               html = html + "<option hidatr='"+JSON.stringify(json[i].option)+"' value='" + json[i].stage + "'>" + json[i].stage + "</option> ";

            }
            $('#sharedselect').html(html)


         },
         error: function (err) {

         }

      });

   }

   $("#sharedselect").change(function () {

      if($('#flaghidden').val() == 2){
        if($("#sharedselect").val() != ""){
           $("#statusselectdiv").removeClass("hidden");
           var ths = $(this);
           var json = JSON.parse(ths.find("option:selected").attr("hidatr"));

           var html = "<option value=''>---Select Lead status---</option>";
           for (var i = 0; i < json.length; i++) {
              html = html + "<option value='" + json[i].value+ "'>" + json[i].value+ "</option> ";

           }
           $('#statusselect').html(html)
           $('#statusselect').prop("required","true");
        }
        else{
           $("#statusselectdiv").addClass("hidden");
           $('#statusselect').html("")
           $('#statusselect').removeAttr("required");
        }
      }
   })


   function loadselect() {
      var temp_flag = $('#flaghidden').val();
      var temp_url = "";
      var temp_html="";

      //======================Stage
      if(temp_flag == 1){

         $("#heading").html("Lead Reports By Stage");
         $("#selectlabel").html("Select Stage");
         loadstage();

      }
      else if(temp_flag == 2){
       $("#heading").html("Lead Reports By Status");
       $("#selectlabel").html("Select Stage");
      loadstage();
      }

      else if(temp_flag == 3){

         $("#heading").html("Lead Reports By Type");
         $("#selectlabel").html("Select Type");
         temp_url = "${pageContext.servletContext.contextPath}/api/leadtype/getactive"
         temp_html = "<option value=''>---Select Lead Type---</option>";
         fetchselect(temp_url,temp_html,'#sharedselect');
      }

      else if(temp_flag == 4){

         $("#heading").html("Lead Reports By Program");
         $("#selectlabel").html("Select Program");
         temp_url = "${pageContext.servletContext.contextPath}/api/product/getallavailable"
         temp_html = "<option value=''>---Select Lead Program---</option>";
         fetchselect(temp_url,temp_html,'#sharedselect');
      }

      else if(temp_flag == 5){

         $("#heading").html("Lead Reports By Source");
         $("#selectlabel").html("Select Source");
         temp_url = "${pageContext.servletContext.contextPath}/api/leadsource/getactive"
         temp_html = "<option value=''>---Select Lead Source---</option>";
         fetchselect(temp_url,temp_html,'#sharedselect');

         let temp_url1 = "${pageContext.servletContext.contextPath}/api/leadtype/getactive"
         let temp_html1 = "<option value=''>---Select Lead Type---</option>";
         console.log("temp_url1 ",temp_url1)
         console.log("temp_html1 ",temp_html1)
         fetchselect(temp_url1,temp_html1,'#clienttypeselect');
         $("#clientypeselectdiv").removeClass("hidden");


      }
      else if(temp_flag == 6){

         $("#heading").html("Lead Reports By Source Inner");
         $("#selectlabel").html("Select Source Inner");
         temp_url = "${pageContext.servletContext.contextPath}/api/lead/getleadsourceinner"
         temp_html = "<option value=''>---Select Lead Source Inner---</option>";
         leadleadsourceinnner(temp_url,temp_html);
      }
      else if(temp_flag == 7){

         $("#heading").html("Lead Reports By Facebook Campaign");
         $("#selectlabel").html("Select Facebook Campaign");
         temp_url = "${pageContext.servletContext.contextPath}/api/leadpriority/getactive"
         temp_html = "<option value=''>---Select Facebook Campaign---</option>";
         fetchselect(temp_url,temp_html,'#sharedselect');
      }


   }


   var indexdiv=$('#displaynone').val();
   indexdiv = parseInt(indexdiv)-1
   $('#tab3').find(".customsidedivmenu").eq(parseInt(indexdiv)).css("display","none")
   $('#tab4').find(".customsidedivmenu").eq(parseInt(indexdiv)).css("display","none")


   $(document).on("click",".takemetotable",function () {
       var searchParams = new URLSearchParams(window.location.search);
      var hidtype=$(this).closest("td").find("i").eq(0).attr("hidtype");
      var hidvalue=$(this).closest("td").find("i").eq(0).attr("hidvalue");
      let statussearch = searchParams.get("statussearch")||0;
      let clienttypevalue = searchParams.get("clienttypevalue")||0;
      let dateTypeFlag=searchParams.get("dateTypeFlag")||4;//4 means LMS entry date
      let userflag=searchParams.get("userflag")||0;//0 means for selected user only,1 means for selected user's team members

      var firstDateOfMonth=<%=UtilityClass.getFirstDateAndEndDateInt()[0]%>;
      var lastDateOfMonth=<%=UtilityClass.getFirstDateAndEndDateInt()[1]%>;
      var startdate=searchParams.get("startdate")||firstDateOfMonth
      var enddate=searchParams.get("enddate")||lastDateOfMonth
      if(startdate==0)
      {
         startdate=firstDateOfMonth;
      }  if(enddate==0)
      {
         enddate=lastDateOfMonth;
      }

      var leadstatus = 0;
      var leadsource =  0;
      var product =  0;
      var leadtype =  0;
      var userfilter = searchParams.get("userid")||0
      var datefilter = false;
      var customdate =  0;
      var searchstring = 0;
      var leadstage=   0;
      var innersource=0;
      var leadpriority=0;
      var keyword=0;

      var id=    0;
      if(startdate!=0&&enddate!=0)
      {
         datefilter=true;
         customdate=startdate+"@"+enddate;
      }
      var flag=$('#flaghidden').val();
      switch (flag) {
         case "1":
            leadstage=statussearch;
            break;
         case "2":
            leadstatus=statussearch;
            break;
         case "3":
            leadtype=statussearch;
            break;
         case "4":
            product=statussearch;
            break;
         case "5":
            leadsource=statussearch;
            leadtype=clienttypevalue;
            break;
         case "6":
            innersource=statussearch;
            break;
         case "7":
           leadpriority=statussearch;
            break;
         case "8":
            keyword=statussearch;
            break;

      }


      switch (hidtype) {
         case "stage":
            leadstage=hidvalue;
            var urlparams="innersource="+innersource+"&statusValue="+leadstatus+"&leadSource="+leadsource+"&product="+product+"&leadType="+leadtype+"&userFilter="+userfilter+"&datefilter="+datefilter+"&datevalue="+customdate+"&searchvalue="+searchstring+"&leadstage="+leadstage+"&leadpriority="+leadpriority+"&keyword="+keyword+"&dateTypeFlag="+dateTypeFlag+"&userflag="+userflag;

            window.open("${pageContext.request.contextPath}/lead?"+urlparams)

            break;
         case "status":
            leadstatus=hidvalue;
            var urlparams="innersource="+innersource+"&statusValue="+leadstatus+"&leadSource="+leadsource+"&product="+product+"&leadType="+leadtype+"&userFilter="+userfilter+"&datefilter="+datefilter+"&datevalue="+customdate+"&searchvalue="+searchstring+"&leadstage="+leadstage+"&leadpriority="+leadpriority+"&keyword="+keyword+"&dateTypeFlag="+dateTypeFlag+"&userflag="+userflag;

            window.open("${pageContext.request.contextPath}/lead?"+urlparams)
            break;
         case "type":
            leadtype=hidvalue;
            var urlparams="innersource="+innersource+"&statusValue="+leadstatus+"&leadSource="+leadsource+"&product="+product+"&leadType="+leadtype+"&userFilter="+userfilter+"&datefilter="+datefilter+"&datevalue="+customdate+"&searchvalue="+searchstring+"&leadstage="+leadstage+"&leadpriority="+leadpriority+"&keyword="+keyword+"&dateTypeFlag="+dateTypeFlag+"&userflag="+userflag;

            window.open("${pageContext.request.contextPath}/lead?"+urlparams)
            break;
         case "product":
            product=hidvalue;
            var urlparams="innersource="+innersource+"&statusValue="+leadstatus+"&leadSource="+leadsource+"&product="+product+"&leadType="+leadtype+"&userFilter="+userfilter+"&datefilter="+datefilter+"&datevalue="+customdate+"&searchvalue="+searchstring+"&leadstage="+leadstage+"&leadpriority="+leadpriority+"&keyword="+keyword+"&dateTypeFlag="+dateTypeFlag+"&userflag="+userflag;

            window.open("${pageContext.request.contextPath}/lead?"+urlparams)
            break;
         case "source":
            leadsource=hidvalue;
            var urlparams="innersource="+innersource+"&statusValue="+leadstatus+"&leadSource="+leadsource+"&product="+product+"&leadType="+leadtype+"&userFilter="+userfilter+"&datefilter="+datefilter+"&datevalue="+customdate+"&searchvalue="+searchstring+"&leadstage="+leadstage+"&leadpriority="+leadpriority+"&keyword="+keyword+"&dateTypeFlag="+dateTypeFlag+"&userflag="+userflag;

            window.open("${pageContext.request.contextPath}/lead?"+urlparams)
            break;
         case "innersource":
            innersource=hidvalue;
            var urlparams="innersource="+innersource+"&statusValue="+leadstatus+"&leadSource="+leadsource+"&product="+product+"&leadType="+leadtype+"&userFilter="+userfilter+"&datefilter="+datefilter+"&datevalue="+customdate+"&searchvalue="+searchstring+"&leadstage="+leadstage+"&leadpriority="+leadpriority+"&keyword="+keyword+"&dateTypeFlag="+dateTypeFlag+"&userflag="+userflag;

            window.open("${pageContext.request.contextPath}/lead?"+urlparams)
            break;
         case "leadpriority":
            leadpriority=hidvalue;
            var urlparams="innersource="+innersource+"&statusValue="+leadstatus+"&leadSource="+leadsource+"&product="+product+"&leadType="+leadtype+"&userFilter="+userfilter+"&datefilter="+datefilter+"&datevalue="+customdate+"&searchvalue="+searchstring+"&leadstage="+leadstage+"&leadpriority="+leadpriority+"&keyword="+keyword+"&dateTypeFlag="+dateTypeFlag+"&userflag="+userflag;

            window.open("${pageContext.request.contextPath}/lead?"+urlparams)
            break;

         case "keyword":
            keyword=hidvalue;
            var urlparams="innersource="+innersource+"&statusValue="+leadstatus+"&leadSource="+leadsource+"&product="+product+"&leadType="+leadtype+"&userFilter="+userfilter+"&datefilter="+datefilter+"&datevalue="+customdate+"&searchvalue="+searchstring+"&leadstage="+leadstage+"&leadpriority="+leadpriority+"&keyword="+keyword+"&dateTypeFlag="+dateTypeFlag+"&userflag="+userflag;

            window.open("${pageContext.request.contextPath}/lead?"+urlparams)
            break;

      }
   })

</script>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>

