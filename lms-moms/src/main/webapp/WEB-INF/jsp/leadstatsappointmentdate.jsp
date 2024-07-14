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
                <h1>Appointment Date Wise Dashboard</h1>
                <small>Quick Overview</small>
            </div>
        </section>
        <!-- Main content -->
        <section class="content">

            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs">
                    <li> <a href="#tab4" data-toggle="tab" title="Click to view Tabular Report">Tabular Report <i class="fa fa-table"></i></a></li>
                    <li><a href="#tab5" data-toggle="tab" title="Click to filter Report">Filter <i class="fa fa-filter"></i></a></li>
                    <li><a href="#" onclick="onSettingFromLoad()" data-toggle="modal" data-target="#graphSettings" title="Click to filter Report">Settings <i class="fa fa-gear" ></i></a></li>

                </ul>
                <!-- Tab panels -->
                <div class="tab-content">

                    <div class="tab-pane fade in active" id="tab4">
                        <div class="panel-body">

                            <div class="row">

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

                                                    <thead><tr><th>Date</th><th>Leads</th><th>Action</th></tr></thead>

                                                    <tbody id="tbodytypechart">

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
        var result = (((parseInt(got)*100)/parseInt(total))).toFixed(2)
        if(isNaN(result)){
            return "0.00%"
        }
        else return result +"%"

    }
    //loaddefaultparams()
    function loaddefaultparams(){

        console.log("loading defaults ")
        var searchParams = new URLSearchParams(window.location.search);
        var temp_startdate = "<%=UtilityClass.getFirstDateAndEndDatePlain()[0]%>"
        var temp_enddate = "<%=UtilityClass.getFirstDateAndEndDatePlain()[1]%>"

        var  startdate = searchParams.get("startdate") || temp_startdate;
        // let dateTypeFlag=searchParams.get("dateTypeFlag")||4;//4 means LMS entry date
        let isFilter = searchParams.get("isFilter")||false;//do not search for clietType
        let clientType = searchParams.get("clientType")||0;
        if(clientType != 0){
            isFilter = true
        }
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
            url: "${pageContext.request.contextPath}/api/leadreportcard/getleadstatsappointmentdate?"+url,
            type: 'GET',
            success: function (json) {

                var html = "";


                //TOTAL COUNT OF EACH TABLE
                let count = 0;



                for(var i=0;i<json.length;i++)
                {
                    count += parseInt(json[i].count)
                    html += "<tr>";
                    html += "<td>"+json[i].description+"</td>";
                    html += "<td>"+json[i].count+"</td>";
                    html += "<td><i hidvalue='"+json[i].description+"' title='Click Here To View Leads'  class='hvr-buzz-out fa fa-table takemetotable'></i></td>";
                    html += "</tr>"
                }

                html += "<tr>";
                html += "<td class='boldCell' > Total </td>";
                html += "<td class='boldCell' >"+count+"</td>";
                html += "<td class='boldCell' ></td>";
                html += "</tr>"

                $("#tbodytypechart").html(html);
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

        // let dateTypeFlag = $("input[name='dateType']:checked").val();

        if(fromdate!=0) {
            fromdate = fromdate.replace(/-/g, '');
        }
        if(todate!=0) {
            todate = todate.replace(/-/g, '');
        }

        var urlparams=   "startdate="+fromdate+"&enddate="+todate;
console.log(urlparams)
        var url=window.location.toString().split("?")[0];
        url = url+"?"+urlparams;
        window.history.pushState({}, null, url);

        loaddefaultparams();
        window.location.reload();

        //========================= SETTING DEFAULT PARAMS
    })

    $(document).on("click",".takemetotable",function () {
        let searchParams = new URLSearchParams(window.location.search);

        //1=leaddate,2=covertdate,3=updatedate,4=createdate,5=profillingdate,6=profilingdatecustome,7=appointmentdate
        let dateTypeFlag = 7;

        var firstDateOfMonth=<%=UtilityClass.getFirstDateAndEndDateInt()[0]%>;
        var lastDateOfMonth=<%=UtilityClass.getFirstDateAndEndDateInt()[1]%>;

        //var startdate=searchParams.get("startdate")||firstDateOfMonth
        //var enddate=searchParams.get("enddate")||lastDateOfMonth
        let hidvalue=$(this).closest("td").find("i").eq(0).attr("hidvalue");
        let startdate = hidvalue.replaceAll("-","");
        let enddate = hidvalue.replaceAll("-","");
        if(startdate==0)
        {
            startdate=firstDateOfMonth;
        }  if(enddate==0)
        {
            enddate=lastDateOfMonth;
        }


        var datefilter = false;
        var customdate =  0;
        if(startdate!=0&&enddate!=0)
        {
            datefilter=true;
            customdate=startdate+"@"+enddate;
        }

        //PASSING CUSTOM VALUE SO THAT ONLY LEADS THAT HAVE MBOPSCHILDID ARE PICKED
        let searchvalue = "appointment";

        var urlparams="statusValue="+0+"&leadSource="+0+"&product="+0+"&leadType="+0+"&userFilter="+0+"&datefilter="+datefilter+"&datevalue="+customdate+"&searchvalue="+searchvalue+"&leadstage="+0+"&leadpriority="+0+"&keyword="+0+"&dateTypeFlag="+dateTypeFlag;
        window.open("${pageContext.request.contextPath}/lead?"+urlparams)

    })
</script>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>

