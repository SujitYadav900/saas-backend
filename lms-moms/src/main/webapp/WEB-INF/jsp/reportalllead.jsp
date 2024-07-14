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
      <!-- jquery-ui css -->
      <link href="${pageContext.request.contextPath}/assets/plugins/jquery-ui-1.12.1/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
      <!-- Bootstrap -->
      <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
      <!-- Lobipanel css -->
      <link href="${pageContext.request.contextPath}/assets/plugins/lobipanel/lobipanel.min.css" rel="stylesheet" type="text/css"/>
      <!-- Pace css -->
      <link href="${pageContext.request.contextPath}/assets/plugins/pace/flash.css" rel="stylesheet" type="text/css"/>
      <!-- Pe-icon -->
      <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet" type="text/css"/>
      <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/helper.css" rel="stylesheet" type="text/css"/>

      <!-- Themify icons -->
      <link href="${pageContext.request.contextPath}/assets/themify-icons/themify-icons.css" rel="stylesheet" type="text/css"/>
      <!-- Emojionearea -->
      <link href="${pageContext.request.contextPath}/assets/plugins/emojionearea/emojionearea.min.css" rel="stylesheet" type="text/css"/>
      <!-- Monthly css -->
      <link href="${pageContext.request.contextPath}/assets/plugins/monthly/monthly.css" rel="stylesheet" type="text/css"/>
      <!-- Theme style -->
      <link href="${pageContext.request.contextPath}/assets/dist/css/stylecrm.css" rel="stylesheet" type="text/css"/>
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
                  <i class="fa fa-dashboard"></i>
               </div>
               <div class="header-title">
                  <h1>Lead Complete Report Dashboard</h1>
                  <small>Quick Overview</small>
               </div>
            </section>
            <!-- Main content -->
            <section class="content">
               <div class="row">
                  <div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
                     <div id="cardbox1">
                        <div class="statistic-box">
                           <i class="fa fa-user-plus fa-3x"></i>
                           <div class="counter-number pull-right">
                              <span class="count-number" id="totallead"></span>
                              <span class="slight"><i class="fa fa-play fa-rotate-270"> </i>
                              </span>
                           </div>
                           <h3> Total Lead</h3>
                        </div>
                     </div>
                  </div>
                  <div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
                     <div id="cardbox2">
                        <div class="statistic-box">
                           <i class="fa fa-user-secret fa-3x"></i>
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
                           <i class="hvr-buzz-out fa fa-clock-o fa-3x"></i>

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
                     <li id="charttab" class="active"><a href="#tab3" data-toggle="tab"><i class="fa fa-bar-chart"></i></a></li>
                     <li><a href="#tab4" data-toggle="tab"><i class="fa fa-table"></i></a></li>
                     <li><a href="#tab5" data-toggle="tab"><i class="fa fa-filter"></i></a></li>
                  </ul>
                  <!-- Tab panels -->
                  <div class="tab-content">
                     <div class="tab-pane fade in active" id="tab3">
                        <div class="panel-body">
                           <%--=============================== GRAPHS ==============================--%>
                           <div class="row">
                              <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                 <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                                    <div class="panel-heading">
                                       <div class="panel-title">
                                          <h4>Lead Stage Graph</h4>
                                       </div>
                                    </div>
                                    <div style="" class="panel-body">
                                       <canvas id="stagechart" height="100"></canvas>
                                    </div>
                                 </div>

                              </div>

                              <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                 <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                                    <div class="panel-heading">
                                       <div class="panel-title">
                                          <h4>Lead Status Graph</h4>
                                       </div>
                                    </div>
                                    <div style="" class="panel-body">
                                       <canvas id="statuschart" height="100"></canvas>
                                    </div>
                                 </div>
                              </div>

                              <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                 <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                                    <div class="panel-heading">
                                       <div class="panel-title">
                                          <h4>Lead Type Graph</h4>
                                       </div>
                                    </div>
                                    <div style="" class="panel-body">
                                       <canvas id="typechart" height="100"></canvas>
                                    </div>
                                 </div>
                              </div>

                              <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                 <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                                    <div class="panel-heading">
                                       <div class="panel-title">
                                          <h4>Lead Product Graph</h4>
                                       </div>
                                    </div>
                                    <div style="" class="panel-body">
                                       <canvas id="productchart" height="100"></canvas>
                                    </div>
                                 </div>
                              </div>

                              <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                 <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                                    <div class="panel-heading">
                                       <div class="panel-title">
                                          <h4>Lead Source Graph</h4>
                                       </div>
                                    </div>
                                    <div style="" class="panel-body">
                                       <canvas id="sourcechart" height="100"></canvas>
                                    </div>
                                 </div>
                              </div>

                              <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                 <div class="panel panel-bd lobidragdashboard customsidedivmenu">
                                    <div class="panel-heading">
                                       <div class="panel-title">
                                          <h4>Lead Inner Source Graph</h4>
                                       </div>
                                    </div>
                                    <div style="" class="panel-body">
                                       <canvas id="innersourcechart" height="100"></canvas>
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

                                          <thead><tr><th>Name</th><th>Count</th></tr></thead>
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

                                          <thead><tr><th>Name</th><th>Count</th></tr></thead>
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

                                          <thead><tr><th>Name</th><th>Count</th></tr></thead>
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

                                          <thead><tr><th>Name</th><th>Count</th></tr></thead>
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

                                          <thead><tr><th>Name</th><th>Count</th></tr></thead>
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
                                          <thead><tr><th>Name</th><th>Count</th></tr></thead>
                                          <tbody id="tbodysourceinnerchart">

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
                                          <h4>Lead Stage Graph</h4>
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

                                             <div class="col-md-10 form-check">
                                                <label class="control-label">Gender</label>
                                                <br>
                                                <label class="form-check-label">
                                                   <input type="radio" class="form-check-input" name="dateType" value="1" checked> Lead Date
                                                </label>
                                                <label class="form-check-label">
                                                   <input type="radio" class="form-check-input" name="dateType" value="2"> Convert Date
                                                </label>
                                                <label class="form-check-label">
                                                   <input type="radio" class="form-check-input" name="dateType" value="3" > Update Date
                                                </label>
                                                <label class="form-check-label">
                                                   <input type="radio" class="form-check-input" name="dateType" value="4"> Entry Date
                                                </label>
                                             </div>

                                             <div class="col-md-10 form-group" >
                                                <label>From</label>
                                                <input type="date" id="fromdate" class="form-control" required/>
                                             </div>

                                             <div class="col-md-10 form-group" >
                                                <label>To</label>
                                                <input type="date" id="todate" class="form-control"  required/>
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
      <!-- End Theme label Script
         =====================================================================-->
      <script>


         var url = "";
         function loaddefaultparams(){
            var searchParams = new URLSearchParams(window.location.search);
            var temp_startdate = "<%=UtilityClass.getFirstDateAndEndDatePlain()[0]%>"
            var temp_enddate = "<%=UtilityClass.getFirstDateAndEndDatePlain()[1]%>"
            var userid = searchParams.get("userid") || "0";
            var  startdate = searchParams.get("startdate") || temp_startdate;
            var enddate = searchParams.get("enddate") || temp_enddate;
            let dateTypeFlag=searchParams.get("dateTypeFlag")||4;//4 means LMS entry date
            url = "userid="+userid+"&startdate="+startdate+"&enddate="+enddate+"&dateTypeFlag="+dateTypeFlag;
         }


         loaddefaultparams();
         $.ajax({
            url: "${pageContext.request.contextPath}/api/leadreportcard/getreportcard?"+url,
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
               var stagehtml = "";
               var statushtml = "";
               var typehtml = "";
               var producthtml = "";
               var sourcehtml = "";
               var sourceinnerhtml = "";

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

                        //$("#tabular").html(html);
                        totalconvertlead=json[i].count;
                        $('#totalconvertlead').html(totalconvertlead)
                     }
                     if(json[i].seq==2)
                     {

                        var percentage=(((parseInt(totalconvertlead)*100)/parseInt(totallead))).toFixed(2)
                        $('#conversionpercentage').html(percentage)
                     }
                     if(json[i].seq==3)
                     {

                        lableforstage.push(json[i].description);
                        dataforstage.push(json[i].count)

                        stagehtml = stagehtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td></tr>"

                     }
                     if(json[i].seq==4)
                     {
                        lableforstatus.push(json[i].description);
                        dataforstatus.push(json[i].count);
                        statushtml = statushtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td></tr>"

                     }
                     if(json[i].seq==5)
                     {
                        lablefortype.push(json[i].description);
                        datafortype.push(json[i].count);
                        typehtml = typehtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td></tr>"

                     }
                     if(json[i].seq==6)
                     {
                        lableforproduct.push(json[i].description);
                        dataforproduct.push(json[i].count);
                        producthtml = producthtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td></tr>"

                     }
                     if(json[i].seq==7)
                     {
                        lableforsource.push(json[i].description);
                        dataforsource.push(json[i].count);
                        sourcehtml = sourcehtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td></tr>"

                     }
                     if(json[i].seq==8)
                     {
                        lableforinnersource.push(json[i].description);
                        dataforinnersource.push(json[i].count);
                        sourceinnerhtml = sourceinnerhtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td></tr>"

                     }


                  }
               $("#tbodystagechart").html(stagehtml);
               $("#tbodystatuschart").html(statushtml);
               $("#tbodytypechart").html(typehtml);
               $("#tbodyproductchart").html(producthtml);
               $("#tbodysourcechart").html(sourcehtml);
               $("#tbodysourceinnerchart").html(sourceinnerhtml);



               var ctx = document.getElementById("stagechart");
               var myChart = new Chart(ctx, {
                  type: 'bar',
                  data: {
                     labels: lableforstage,
                     datasets: [
                        {
                           label: "Lead Stage",
                           data: dataforstage,
                           borderColor: "rgba(0, 150, 136, 0.8)",

                           backgroundColor: "rgba(0, 150, 136, 0.8)"
                        }
                     ]
                  },
                  options: {
                     scales: {
                        xAxes: [{
                           barThickness: 40,  // number (pixels) or 'flex'
                           maxBarThickness: 40 ,// number (pixels),


                        }],
                        yAxes: [{
                           ticks: {
                              beginAtZero: true,

                           }
                        }]
                     }
                  }
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

               var ctx = document.getElementById("statuschart");
               var myChart = new Chart(ctx, {
                  type: 'bar',
                  data: {
                     labels: lableforstatus,
                     datasets: [
                        {
                           label: "Lead Status",
                           data: dataforstatus,
                           borderColor: "rgba(0, 150, 136, 0.8)",
                           width: "0.2",
                           borderWidth: "0",
                           backgroundColor: "rgba(0, 150, 136, 0.8)"
                        }
                     ]
                  },
                  options: {
                     scales: {
                        xAxes: [{
                           barThickness: 40,  // number (pixels) or 'flex'
                           maxBarThickness: 40 ,// number (pixels),

                        }],
                        yAxes: [{
                           ticks: {
                              beginAtZero: true
                           }
                        }]
                     }


                  }
               });

               var ctxType = document.getElementById("typechart");
               var myTypeChart = new Chart(ctxType, {
                  type: 'bar',
                  data: {
                     labels: lablefortype,
                     datasets: [
                        {
                           label: "Lead Type",
                           data: datafortype,
                           borderColor: "rgba(0, 150, 136, 0.8)",
                           width: "0.2",
                           borderWidth: "0",
                           backgroundColor: "rgba(0, 150, 136, 0.8)"
                        }
                     ]
                  },
                  options: {
                     scales: {
                        xAxes: [{
                           barThickness: 40,  // number (pixels) or 'flex'
                           maxBarThickness: 40 ,// number (pixels),

                        }],
                        yAxes: [{
                           ticks: {
                              beginAtZero: true
                           }
                        }]
                     }


                  }
               });

               var ctxProduct = document.getElementById("productchart");
               var myProductChart = new Chart(ctxProduct, {
                  type: 'bar',
                  data: {
                     labels: lableforproduct,
                     datasets: [
                        {
                           label: "Lead Product",
                           data: dataforproduct,
                           borderColor: "rgba(0, 150, 136, 0.8)",
                           width: "0.2",
                           borderWidth: "0",
                           backgroundColor: "rgba(0, 150, 136, 0.8)"
                        }
                     ]
                  },
                  options: {
                     scales: {
                        xAxes: [{
                           barThickness: 40,  // number (pixels) or 'flex'
                           maxBarThickness: 40 ,// number (pixels),

                        }],
                        yAxes: [{
                           ticks: {
                              beginAtZero: true
                           }
                        }]
                     }


                  }
               });


               var ctxSource = document.getElementById("sourcechart");
               var mySourceChart = new Chart(ctxSource, {
                  type: 'bar',
                  data: {
                     labels: lableforsource,
                     datasets: [
                        {
                           label: "Lead Source",
                           data: dataforsource,
                           borderColor: "rgba(0, 150, 136, 0.8)",
                           width: "0.2",
                           borderWidth: "0",
                           backgroundColor: "rgba(0, 150, 136, 0.8)"
                        }
                     ]
                  },
                  options: {
                     scales: {
                        xAxes: [{
                           barThickness: 40,  // number (pixels) or 'flex'
                           maxBarThickness: 40 ,// number (pixels),

                        }],
                        yAxes: [{
                           ticks: {
                              beginAtZero: true
                           }
                        }]
                     }


                  }
               });

               var ctxInnerSource = document.getElementById("innersourcechart");
               var myInnerSourceChart = new Chart(ctxInnerSource, {
                  type: 'bar',
                  data: {
                     labels: lableforinnersource,
                     datasets: [
                        {
                           label: "Lead Inner Source",
                           data: dataforinnersource,
                           borderColor: "rgba(0, 150, 136, 0.8)",
                           width: "0.2",
                           borderWidth: "0",
                           backgroundColor: "rgba(0, 150, 136, 0.8)"
                        }
                     ]
                  },
                  options: {
                     scales: {
                        xAxes: [{
                           barThickness: 40,  // number (pixels) or 'flex'
                           maxBarThickness: 40 ,// number (pixels),

                        }],
                        yAxes: [{
                           ticks: {
                              beginAtZero: true
                           }
                        }]
                     }


                  }
               });

            },
            error: function (err) {


            }
         })

         //============================ RUNFILTER
         $("#filterform").on("submit", function (e) {
         e.preventDefault();
         var todate = $("#todate").val();
         var fromdate = $("#fromdate").val();
         let dateTypeFlag = $("input[name='dateType']:checked").val();

         var id = $("#user").val();
         todate = todate.replace(/-/g, '');
         fromdate = fromdate.replace(/-/g, '');
         id=1;

         //========================= SETTING DEFAULT PARAMS

                $.ajax({
               type: "GET",
               contentType: "application/json",
               url: "${pageContext.request.contextPath}/api/leadreportcard/getreportcard?id="+id+"&startdate="+fromdate+"&enddate="+todate+"&dateTypeFlag="+dateTypeFlag,
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
                  var stagehtml = "";
                  var statushtml = "";
                  var typehtml = "";
                  var producthtml = "";
                  var sourcehtml = "";
                  var sourceinnerhtml = "";

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

                        //$("#tabular").html(html);
                        totalconvertlead=json[i].count;
                        $('#totalconvertlead').html(totalconvertlead)
                     }
                     if(json[i].seq==2)
                     {

                        var percentage=(((parseInt(totalconvertlead)*100)/parseInt(totallead))).toFixed(2)
                        $('#conversionpercentage').html(percentage)
                     }
                     if(json[i].seq==3)
                     {

                        lableforstage.push(json[i].description);
                        dataforstage.push(json[i].count)

                        stagehtml = stagehtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td></tr>"

                     }
                     if(json[i].seq==4)
                     {
                        lableforstatus.push(json[i].description);
                        dataforstatus.push(json[i].count);
                        statushtml = statushtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td></tr>"

                     }
                     if(json[i].seq==5)
                     {
                        lablefortype.push(json[i].description);
                        datafortype.push(json[i].count);
                        typehtml = typehtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td></tr>"

                     }
                     if(json[i].seq==6)
                     {
                        lableforproduct.push(json[i].description);
                        dataforproduct.push(json[i].count);
                        producthtml = producthtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td></tr>"

                     }
                     if(json[i].seq==7)
                     {
                        lableforsource.push(json[i].description);
                        dataforsource.push(json[i].count);
                        sourcehtml = sourcehtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td></tr>"

                     }
                     if(json[i].seq==8)
                     {
                        lableforinnersource.push(json[i].description);
                        dataforinnersource.push(json[i].count);
                        sourceinnerhtml = sourceinnerhtml+"<tr><td>"+json[i].description+"</td><td>"+json[i].count+"</td></tr>"

                     }


                  }
                  $("#tbodystagechart").html(stagehtml);
                  $("#tbodystatuschart").html(statushtml);
                  $("#tbodytypechart").html(typehtml);
                  $("#tbodyproductchart").html(producthtml);
                  $("#tbodysourcechart").html(sourcehtml);
                  $("#tbodysourceinnerchart").html(sourceinnerhtml);

                  var ctx = document.getElementById("stagechart");
                  var myChart = new Chart(ctx, {
                     type: 'bar',
                     data: {
                        labels: lableforstage,
                        datasets: [
                           {
                              label: "Lead Stage",
                              data: dataforstage,
                              borderColor: "rgba(0, 150, 136, 0.8)",

                              backgroundColor: "rgba(0, 150, 136, 0.8)"
                           }
                        ]
                     },
                     options: {
                        scales: {
                           xAxes: [{
                              barThickness: 40,  // number (pixels) or 'flex'
                              maxBarThickness: 40 ,// number (pixels),


                           }],
                           yAxes: [{
                              ticks: {
                                 beginAtZero: true,

                              }
                           }]
                        }
                     }
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

                  var ctx = document.getElementById("statuschart");
                  var myChart = new Chart(ctx, {
                     type: 'bar',
                     data: {
                        labels: lableforstatus,
                        datasets: [
                           {
                              label: "Lead Status",
                              data: dataforstatus,
                              borderColor: "rgba(0, 150, 136, 0.8)",
                              width: "0.2",
                              borderWidth: "0",
                              backgroundColor: "rgba(0, 150, 136, 0.8)"
                           }
                        ]
                     },
                     options: {
                        scales: {
                           xAxes: [{
                              barThickness: 40,  // number (pixels) or 'flex'
                              maxBarThickness: 40 ,// number (pixels),

                           }],
                           yAxes: [{
                              ticks: {
                                 beginAtZero: true
                              }
                           }]
                        }


                     }
                  });

                  var ctxType = document.getElementById("typechart");
                  var myTypeChart = new Chart(ctxType, {
                     type: 'bar',
                     data: {
                        labels: lablefortype,
                        datasets: [
                           {
                              label: "Lead Type",
                              data: datafortype,
                              borderColor: "rgba(0, 150, 136, 0.8)",
                              width: "0.2",
                              borderWidth: "0",
                              backgroundColor: "rgba(0, 150, 136, 0.8)"
                           }
                        ]
                     },
                     options: {
                        scales: {
                           xAxes: [{
                              barThickness: 40,  // number (pixels) or 'flex'
                              maxBarThickness: 40 ,// number (pixels),

                           }],
                           yAxes: [{
                              ticks: {
                                 beginAtZero: true
                              }
                           }]
                        }


                     }
                  });

                  var ctxProduct = document.getElementById("productchart");
                  var myProductChart = new Chart(ctxProduct, {
                     type: 'bar',
                     data: {
                        labels: lableforproduct,
                        datasets: [
                           {
                              label: "Lead Product",
                              data: dataforproduct,
                              borderColor: "rgba(0, 150, 136, 0.8)",
                              width: "0.2",
                              borderWidth: "0",
                              backgroundColor: "rgba(0, 150, 136, 0.8)"
                           }
                        ]
                     },
                     options: {
                        scales: {
                           xAxes: [{
                              barThickness: 40,  // number (pixels) or 'flex'
                              maxBarThickness: 40 ,// number (pixels),

                           }],
                           yAxes: [{
                              ticks: {
                                 beginAtZero: true
                              }
                           }]
                        }


                     }
                  });


                  var ctxSource = document.getElementById("sourcechart");
                  var mySourceChart = new Chart(ctxSource, {
                     type: 'bar',
                     data: {
                        labels: lableforsource,
                        datasets: [
                           {
                              label: "Lead Source",
                              data: dataforsource,
                              borderColor: "rgba(0, 150, 136, 0.8)",
                              width: "0.2",
                              borderWidth: "0",
                              backgroundColor: "rgba(0, 150, 136, 0.8)"
                           }
                        ]
                     },
                     options: {
                        scales: {
                           xAxes: [{
                              barThickness: 40,  // number (pixels) or 'flex'
                              maxBarThickness: 40 ,// number (pixels),

                           }],
                           yAxes: [{
                              ticks: {
                                 beginAtZero: true
                              }
                           }]
                        }


                     }
                  });

                  var ctxInnerSource = document.getElementById("innersourcechart");
                  var myInnerSourceChart = new Chart(ctxInnerSource, {
                     type: 'bar',
                     data: {
                        labels: lableforinnersource,
                        datasets: [
                           {
                              label: "Lead Inner Source",
                              data: dataforinnersource,
                              borderColor: "rgba(0, 150, 136, 0.8)",
                              width: "0.2",
                              borderWidth: "0",
                              backgroundColor: "rgba(0, 150, 136, 0.8)"
                           }
                        ]
                     },
                     options: {
                        scales: {
                           xAxes: [{
                              barThickness: 40,  // number (pixels) or 'flex'
                              maxBarThickness: 40 ,// number (pixels),

                           }],
                           yAxes: [{
                              ticks: {
                                 beginAtZero: true
                              }
                           }]
                        }


                     }
                  });

                  $("#charttab").trigger("click");
               },
               error: function (err) {

               }

            });


         })
         //============================ LOAD USERS
         loaduser();

         function loaduser() {
            $.ajax({
               type: "GET",
               contentType: "application/json",
               url: "${pageContext.servletContext.contextPath}/api/user/getusermyteam",
               success: function (json) {
                  var html = "<option value='0'>---Current User---</option>";
                  for (var i = 0; i < json.length; i++) {
                     html = html + "<option  value='" + json[i].id + "'>" + json[i].username + "</option> ";

                  }
                  $('#user').html(html)


               },
               error: function (err) {

               }

            });
         }
      </script>
   </body>
   <script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>

