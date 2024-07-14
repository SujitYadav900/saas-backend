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
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.css"/>
    <script src="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.js"></script>

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
    <link href="${pageContext.request.contextPath}/assets/plugins/pace/flash.css" rel="stylesheet" type="text/css"/>
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
    <link href="${pageContext.request.contextPath}/assets/plugins/emojionearea/emojionearea.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- Monthly css -->
    <link href="${pageContext.request.contextPath}/assets/plugins/monthly/monthly.css" rel="stylesheet"
          type="text/css"/>
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
        .custom-container {
            display: flex;
            flex-wrap: wrap;
            flex-direction: column;
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
                <i class="fa fa-dashboard"></i>
            </div>
            <div class="header-title">
                <h1>Dashboard</h1>
                <small>Quick Overview</small>
                <a href="#" class="pull-right" data-toggle="modal" data-target="#graphSettings" title="Click to filter Report"><i style="font-size: 1.5em" class="fa fa-gear" onclick="onSettingFromLoad()"></i></a>
            </div>

            <div id="graphSettingDiv"></div>
        </section>
        <!-- Main content -->

        <section class="content custom-container">
            <div class="row"  id="drawcounterdiv"></div>
            <div class="row"  id="taskdiv"></div>
            <div class="row"  id="drawgraphsdiv"></div>
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
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath}/assets/plugins/slimScroll/jquery.slimscroll.min.js"
        type="text/javascript"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/assets/plugins/fastclick/fastclick.min.js"
        type="text/javascript"></script>
<!-- CRMadmin frame -->
<script src="${pageContext.request.contextPath}/assets/dist/js/custom.js" type="text/javascript"></script>
<!-- End Core Plugins
   =====================================================================-->
<!-- Start Page Lavel Plugins
   =====================================================================-->
<!-- ChartJs JavaScript -->
<script src="${pageContext.request.contextPath}/assets/plugins/chartJs/Chart.min.js" type="text/javascript"></script>
<!-- Monthly js -->
<script src="${pageContext.request.contextPath}/assets/plugins/monthly/monthly.js" type="text/javascript"></script>
<!-- End Page Lavel Plugins
   =====================================================================-->
<!-- Start Theme label Script
   =====================================================================-->
<!-- Counter js -->
<script src="${pageContext.request.contextPath}/assets/plugins/counterup/waypoints.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/counterup/jquery.counterup.min.js" type="text/javascript"></script>
<!-- Dashboard js -->
<script src="${pageContext.request.contextPath}/assets/dist/js/dashboard.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/chartjs-plugin-labels.js"></script>
<!-- End Theme label Script
   =====================================================================-->
<script>

    //total lead counter
    $(document).on("click","#cardbox1",function () {

        var startdate=<%=UtilityClass.getFirstDateAndEndDateInt()[0]%>;
        var enddate=<%=UtilityClass.getFirstDateAndEndDateInt()[1]%>;

        var leadstatus = 0;
        var leadsource =  0;
        var product =  0;
        var leadtype =  0;
        var userfilter = 0
        var datefilter = true;
        var customdate =  0;
        var searchstring = 0;
        var leadstage=   0;
        var innersource=0;
        var leadpriority=0;
        var keyword=0;
        customdate=startdate+"@"+enddate;

        let urlparams="innersource="+innersource+"&statusValue="+leadstatus+"&leadSource="+leadsource+"&product="+product+"&leadType="+leadtype+"&userFilter="+userfilter+"&datefilter="+datefilter+"&datevalue="+customdate+"&searchvalue="+searchstring+"&leadstage="+leadstage+"&leadpriority="+leadpriority+"&keyword="+keyword;
        window.open("${pageContext.request.contextPath}/lead?"+urlparams)
    })

    $(document).on("click","#cardbox2",function () {

        var startdate=<%=UtilityClass.getFirstDateAndEndDateInt()[0]%>;
        var enddate=<%=UtilityClass.getFirstDateAndEndDateInt()[1]%>;
        customdate=startdate+"@"+enddate;
        var url="${pageContext.request.contextPath}/lead?datefiltertype=convertdate&innersource=0&statusValue=0&leadSource=0&product=0&leadType=0&userFilter=0&datefilter=true&datevalue="+customdate+"&searchvalue=0&leadstage=0&leadpriority=0&fieldArray=&dateTypeFlag=2";
        window.open(url)
    })

    $(document).on("click","#cardbox4",function () {

        var startdate=<%=UtilityClass.getFirstDateAndEndDateInt()[0]%>;
        var enddate=<%=UtilityClass.getFirstDateAndEndDateInt()[1]%>;
        var userfilter=<%=UtilityClass.getCurrentUser().getId()%>;
        customdate=startdate+"@"+enddate;
        //var url="${pageContext.request.contextPath}/lead?datefiltertype=convertdate&innersource=0&statusValue=0&leadSource=0&product=0&leadType=0&userFilter=0&datefilter=true&datevalue="+customdate+"&searchvalue=0&leadstage=0&leadpriority=0&fieldArray=&dateTypeFlag=2";
        let url = "${pageContext.request.contextPath}/lead?datefiltertype=createdate&innersource=0&statusValue=0&leadSource=0&product=0&leadType=0&userFilter="+userfilter+"&datefilter=true&datevalue="+customdate+"&searchvalue=0&leadstage=0&leadpriority=0&fieldArray=&dateTypeFlag=4"
        window.open(url)
    })

    $(document).on("click","#cardbox5",function () {

        var startdate=<%=UtilityClass.getFirstDateAndEndDateInt()[0]%>;
        var enddate=<%=UtilityClass.getFirstDateAndEndDateInt()[1]%>;
        customdate=startdate+"@"+enddate;
        var url="${pageContext.request.contextPath}/lead?datefiltertype=createdate&innersource=0&statusValue=0&leadSource=0&product=0&leadType=0&userFilter=1&datefilter=true&datevalue="+customdate+"&searchvalue=0&leadstage=0&leadpriority=0&fieldArray=&dateTypeFlag=4";
        window.open(url)
    })

    $(document).on("click","#cardbox6",function () {

        var startdate=<%=UtilityClass.getFirstDateAndEndDateInt()[0]%>;
        var enddate=<%=UtilityClass.getFirstDateAndEndDateInt()[1]%>;
        var userfilter=<%=UtilityClass.getCurrentUser().getId()%>;
        customdate=startdate+"@"+enddate;
        //var url="${pageContext.request.contextPath}/lead?datefiltertype=convertdate&innersource=0&statusValue=0&leadSource=0&product=0&leadType=0&userFilter=0&datefilter=true&datevalue="+customdate+"&searchvalue=0&leadstage=0&leadpriority=0&fieldArray=&dateTypeFlag=2";
        let url = "${pageContext.request.contextPath}/lead?datefiltertype=createdate&innersource=0&statusValue=Pending&leadSource=0&product=0&leadType=0&userFilter="+userfilter+"&datefilter=true&datevalue="+customdate+"&searchvalue=0&leadstage=Conversation&leadpriority=0&fieldArray=&dateTypeFlag=4"
        window.open(url)
    })


    function calculatePercentage(total,got) {
        var result = (((parseInt(got)*100)/parseInt(total))).toFixed(2)
        if(isNaN(result)){
            return "0.00%"
        }
        else return result +"%"

    }


    getpermittedUrl();

    function getpermittedUrl() {
        var result;
        $.ajax({
            url: "${pageContext.request.contextPath}/api/dashboardpermission/getpermissionsbyname",
            type: 'GET',
            success: function (json) {
                var count;
                for (count = 0; count < json.length; count++) {

                    //loadHTmlContent(json[count].url)

                       loadHTmlContent(json[count])
                }
            },

            error: function (err) {


            }
        })
    }
    // function loadAllUnreadTask() {
    //     $.ajax({
    //         url: unreadtaskurl,
    //         type: 'GET',
    //         success: function (json) {
    //             var html = "";
    //             for (var i = 0; i < json.length; i++) {
    //                 html = html + "<tr>" +
    //                     "<td urlhid='"+json[i].url+"' idhid='" + json[i].id + "'>" + json[i].subject + "</td>" +
    //                     "<td>" + json[i].dateTimeTask + "</td>" +
    //                     "<td>" + json[i].message + "</td>" +
    //                     "<td>" + json[i].createDate + "</td>" +
    //                     "<td>" + json[i].createBy + "</td>" +
    //                     "<td><button title='Click Here To Complete Task' class='btn btn-danger completetask'><i class='fa fa-check '></i></button><button title='Click here to view lead' class='btn btn-danger taskmetolead'><i class='fa fa-arrow-circle-o-right'></i></button ></td>" +
    //                     "</tr>"
    //             }
    //             $('#tbodyunreadtask').html(html)
    //         },
    //
    //         error: function (err) {
    //
    //
    //         }
    //     })
    // }


    <%--$(document).on("click",".taskmetolead",function () {--%>
    <%--    var urlhid=$(this).closest("tr").find("td").eq(0).attr("urlhid");--%>
    <%--    window.open("${pageContext.request.contextPath}/"+urlhid)--%>
    <%--})--%>
    <%--$(document).on("click",".completetask",function () {--%>
    <%--    var id=$(this).closest("tr").find("td").eq(0).attr("idhid");--%>
    <%--    var  message = "You Have Completed This Task?"--%>
    <%--    swal({--%>
    <%--        title: "Are you sure?",--%>
    <%--        text: message,--%>
    <%--        icon: "warning",--%>
    <%--        buttons: true,--%>
    <%--        dangerMode: true,--%>
    <%--    })--%>
    <%--        .then((willDelete) => {--%>
    <%--            if (willDelete) {--%>
    <%--                $.ajax({--%>
    <%--                    type: "POST",--%>
    <%--                    contentType: "application/json",--%>
    <%--                    url: urlprefixapplication + "/api/notification/updatereadstatus?id=" + id,--%>
    <%--                    success: function (html) {--%>

    <%--                        getNotification()--%>
    <%--                        loadAllUnreadTask()--%>

    <%--                    },--%>
    <%--                    error: function (err) {--%>

    <%--                    }--%>

    <%--                })--%>

    <%--            }--%>
    <%--        });--%>

    <%--})--%>

    function loadgraphfromurl(url) {
        switch (url) {
            case "leadcount":
                loadleadcountgraph(url, "Lead Count");
                break;
            case "agentleadcount":
                loadleadcountgraph(url, "My Lead Count");
                break;
            case "leadstagegraph":
                loadleadcountgraph(url, "Lead Stage");
                break;
            case "leadstatusgraph":
                loadleadcountgraph(url, "Lead Status");
                break;
            case "leadtypegraph":
                loadleadcountgraph(url, "Lead Type");
                break;
            case "leadproductgraph":
                loadleadcountgraph(url, "Lead Product");
                break;
            case "leadsourcegraph":
                loadleadcountgraph(url, "Lead Source");
                break;
            case "leadsourceinnergraph":
                loadleadcountgraph(url, "Lead Source Inner");
                break;
            case "totalticket":
                loadleadcountgraph(url, "Ticket Count");
                break;
            case "ticketstatusgraph":
                loadleadcountgraph(url, "Ticket Status");
                break;
            case "tickettypegraph":
                loadleadcountgraph(url, "Ticket Type");
                break;
            case "ticketprioritygraph":
                loadleadcountgraph(url, "Ticket Priority");
                break;
            case "keywordgraph":
                loadleadcountgraph(url, "Facebook Keyword");
                break;
            case "totalmail":
                loadleadcountgraph(url, "Mail Count");
                break;
            case "mailstatusgraph":
                loadleadcountgraph(url, "Mail Status");
                break;
            case "taskview":
                loadTasks();
                break;
            case "leadprioritygraph":
                loadleadcountgraph(url, "Lead Priority");
                break;

        }

    }

    function loadajaxcall(url, callback) {

        $.ajax({
            url: "${pageContext.request.contextPath}/api/dashboard/loadfirstpage?url=" + url,
            type: 'GET',
            success: function (json) {
                callback(json);

            },
            error: function (err) {
            }
        })
    }


   var chartType = localStorage.getItem("chartType");
    if(chartType == undefined || chartType == "undefined"){
        chartType = "bar";
    }
    // var colors1 = ['rgba(255, 99, 132, 0.5)',
    //     'rgba(54, 162, 235, 0.5)',
    //     'rgba(255, 206, 86, 0.5)',
    //     'rgba(75, 192, 192, 0.5)',
    //     'rgba(153, 102, 255, 0.5)',
    //     'rgba(255, 159, 64, 0.5)'
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

    function loadleadcountgraph(url, label) {

        loadajaxcall(url, function (json) {
            if (json != null) {
                var totalconvertlead = 0;
                var totallead = 0;
                var lableforgraph = [];
                var dataforgraph = [];
                var html = "";
                var length = json.length;
                var total =0;
                for (var i = 0; i < length; i++) {
                    if (json[i].seq == 1) {
                        totallead = json[i].count;
                        $('#totallead').html(json[i].count)
                    } else if (json[i].seq == 2) {
                        totalconvertlead = json[i].count;
                        $('#totalconvertlead').html(totalconvertlead)
                        var percentage = calculatePercentage(totallead,totalconvertlead);
                        $('#conversionpercentage').html(percentage)
                    } else if (json[i].seq == 9) {

                        $('#totaltickets').html(json[i].count)


                    } else if (json[i].seq == 10) {
                        $('#totalclosedticket').html(json[i].count);

                    } else if (json[i].seq == 11) {
                        $('#averagetime').html(json[i].count)
                    }
                      else if (json[i].seq == 15) {

                        total = total+json[i].count;

                            if(json[i].desc == 1){

                                $('#totalsent').html(json[i].count)
                            }
                            else{

                                  $('#totalfailed').html(json[i].count)

                            }

                        $('#totalmails').html(total)
                    }

                    else if(json[i].seq == 16){

                        if(json[i].desc == -1 )lableforgraph.push("Failed");
                        else lableforgraph.push("Sent");
                        dataforgraph.push(json[i].count)

                        html = html + "<tr><td>" + json[i].desc + "</td><td>" + json[i].count + "</td></tr>"
                    }
                    else if (json[i].seq == 19) {
                        $('#agenttotallead').html(json[i].count)
                    }
                    else if (json[i].seq == 20) {
                        $('#agenttotalconvertlead').html(json[i].count)
                    }
                    else if (json[i].seq == 21) {
                        $('#agentpendinglead').html(json[i].count)
                    }
                    else {
                        lableforgraph.push(json[i].desc);
                        dataforgraph.push(json[i].count)
                        html = html + "<tr><td>" + json[i].desc + "</td><td>" + json[i].count + "</td></tr>"
                    }


                }

                $("#" + url).html(html);

                var ctx = document.getElementById(url);
                if (dataforgraph != "") {
                    var myChart = new Chart(ctx, {
                        type: chartType,
                        data: {
                            labels: lableforgraph,
                            datasets: [
                                {
                                    label: label,
                                    data: dataforgraph,
                                   // borderColor: getColorArray(lableforgraph),

                                    backgroundColor: getColorArray(lableforgraph)
                                }
                            ]
                        },
                        options:options
                    });
                }
            }
        })

    }


    function loadHTmlContent(json) {

        $.ajax({
            url: "${pageContext.request.contextPath}/html/dashboardgraphs/" + json.url + ".html",
            type: 'GET',
            success: function (html) {

                if (json.url == "leadcount") {
                    $('#drawcounterdiv').append(html);
                    $("#totallead").closest('div .row').css("order", json.seq);
                    $("#totallead").closest('div .row').css("display", "flex");
                    $("#totallead").closest('div .row').css("flex-direction", "row");

                }
                else if (json.url == "agentleadcount") {
                    $('#drawcounterdiv').append(html);
                    $("#totallead").closest('div .row').css("order", json.seq);
                    $("#totallead").closest('div .row').css("display", "flex");
                    $("#totallead").closest('div .row').css("flex-direction", "row");

                }
                else if (json.url == "totalticket") {
                    $('#drawcounterdiv').append(html);
                    $("#totaltickets").closest('div .row').css("order", json.seq);
                    $("#totaltickets").closest('div .row').css("display", "flex");
                    $("#totaltickets").closest('div .row').css("flex-direction", "row");
                }
                else if (json.url == "totalmail") {
                    $('#drawcounterdiv').append(html);
                   $("#totalmails").closest('div .row').css("order", json.seq);
                   $("#totalmails").closest('div .row').css("display", "flex");
                   $("#totalmails").closest('div .row').css("flex-direction", "row");
                }
                else if (json.url == "taskview") {
                    $('#taskdiv').append(html);
                    $("#taskview").closest('div .row').css("order", json.seq);
                    $("#taskview").closest('div .row').css("display", "flex");
                    $("#taskview").closest('div .row').css("flex-direction", "row");
                }

                else {
                    $('#drawgraphsdiv').append(html);
                    $("#" + json.url).closest('div .graphdiv').css("order", json.seq);
                    //$("#" + json.url).closest('div .graphdiv').css("display", "flex");
                    //$("#" + json.url).closest('div .graphdiv').css("flex-direction", "row");
                    let currentWidthGlobal = localStorage.getItem("chartWidth");
                    if(currentWidthGlobal == "full" ){
                        $(".chartPanelClass").addClass("col-md-12")
                        $(".chartPanelClass").removeClass("col-md-6")
                    }
                    else if(currentWidthGlobal == "half"){
                        $(".chartPanelClass").addClass("col-md-6")
                        $(".chartPanelClass").removeClass("col-md-12")

                    }
                    //$("#" + json.url).closest('div .graphdiv').css("display", "flex");
                    //$("#" + json.url).closest('div .graphdiv').css("flex-direction", "column");


                }
                loadgraphfromurl(json.url);

            },
            error: function (err) {
            }
        })

    }

    var table;
    var userSelection = '';
    userSelection = "Own"
    function loadTasks(){

       userSelection=$('#userDetailsDashboard').val();
        if (table != undefined && table != null)
        {
            table.destroy();
            table = null;
        }
       table=  $('#table').DataTable( {
            ajax: {
                url: '${pageContext.servletContext.contextPath}/api/logtask/getdashboardlogs',
                dataSrc: '',
                "data": {
                    "flag": userSelection,

                },

            },
            "columns": [

                { "data": "id"},
                { "mRender": function (data, type, row) {

                        $(row).css('background-color', row.color);
                        return "<a style='display: none'>"+JSON.stringify(row)+"</a><span style='padding: 5px;border-radius : 3px;background-color : "+row.color+"' >"+row.createDate+"</span>";

                    }
                },

                { "data": "firstname" },
                {   "data": "fullName"},
                {   "data": "phoneNumber"},
                { "mRender": function (data, type, row) {

                    let date = row.dateTimeTask.split(" ")[0];
                    let time = row.dateTimeTask.split(" ")[1];
                   let day = date.split("-")[2];
                   let month = date.split("-")[1];
                   let year = date.split("-")[0];

                   return day+"-"+month+"-"+year+" "+time;
                    }
                },
                {   "data": "subject"},
                {   "data": "message"},
                { "mRender": function (data, type, row) {

                        return "<a title='Go to task'  target='_blank' href='${pageContext.servletContext.contextPath}/"+row.url+"'><i style='font-size:1.5em;' class='fa fa-share-square' ></i></a>";

                    }
                }


            ]


            , "initComplete": function(settings, json) {
                $('#tablediv').fadeIn();
                $('#loading').fadeOut();
            },
           "ordering": false,
           "paging": true,
           "searching": true,
           "info":true,
           "language": {
               "emptyTable":     "No Pending or Upcomming Tasks "
           }

        } );

    }

    function reloadDashboardTaskTable(){
        table.ajax.reload();
    }

    $(document).on("click","#searchBtnDashboard",function () {
        loadTasks();
    })


</script>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/chartSettings.js" type="text/javascript"></script>
</html>

