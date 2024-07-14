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
                <h1>Day Wise Campaign</h1>
                <button id="addnewuser" class="btn btn-add" title="Add new campaign">Add Day Wise Campaign <i class="hvr-buzz-out fa fa-plus"></i></button>
            </div>
        </section>
        <!-- Main content -->
        <div class="content">
            <div class="row">
                <div class="col-sm-12 col-md-12">
                    <div class="panel panel-bd lobidrag">

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
                            <div style="display: none" id="addnewdiv">

                                <form id="createnewuserform" method="post" class="col-sm-12">


                                    <br>
                                    <div class="form-check">
                                        <label>Status</label><br>
                                        <label class="radio-inline">
                                            <input type="radio" id="checkenabled" name="status" value="true" checked="checked">Enabled</label>
                                        <label class="radio-inline"><input id="checkdisabled" type="radio" name="status" value="false">Disabled</label>
                                    </div>
                                    <div class="form-check">
                                        <label>Campaign Type</label><br>
                                        <label class="radio-inline">
                                            <input type="radio" id="checkstagewise" name="campaigntype" value="StageWise" checked="checked">Stage Wise</label>
                                        <label class="radio-inline"><input id="checkcreatedate" type="radio" name="campaigntype" value="CreateDate">Create Date</label>
                                    </div>

                                    <div class="form-check">
                                        <label>Generate Url By</label><br>
                                        <label class="radio-inline">
                                            <input type="radio" id="checkproductwiseurl" name="campaigntype" value="ProductWise" checked="checked">Product Wise</label>
                                        <label class="radio-inline"><input id="checksourcewiseurl" type="radio" name="campaigntype" value="SourceWise">Source Wise</label>
                                        <label class="radio-inline"><input id="checktypewiseurl" type="radio" name="campaigntype" value="TypeWise">Type Wise</label>
                                        <label class="radio-inline"><input id="checkstagewiseurl" type="radio" name="campaigntype" value="StageWise">Stage Wise</label>

                                    </div>
                                    <input type="hidden" id="leadstatushidden">
                                    <div class="form-group">
                                        <label>Campaign Name</label>
                                        <input type="text" class="form-control" id="campaignname" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Template Name</label>
                                        <select class="form-control"  id="templateid" required>

                                        </select>
                                    </div>
                                    <div style="display: none" id="urldiv" class="form-group">
                                        <label>Enter Url</label>
                                        <input type="url" class="form-control" id="urlembed">
                                    </div>
                                    <div class="form-group">
                                        <label>Stage Name</label>
                                        <select class="form-control"  id="stagename" required>

                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Day</label>
                                        <select class="form-control"  id="day" required>


                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <table class="table table-bordered table-striped table-hover">
                                            <thead>
                                            <tr>
                                                <th>Name</th>
                                                <th>Url</th>
                                            </tr>
                                            </thead>
                                        </table>
                                    </div>



<br><br>

                                    <input type="hidden" id="id" name="id">
                                    <div class="reset-button">
                                        <button id="createbtn" type="submit" class="btn btn-success">Create</button>
                                        <button id="cancelbtn" type="button" class="btn btn-warning">Cancel</button>

                                    </div>
                                </form>
                            </div>
                            <div id="tablediv">
                                <div>
                                    <div class="table-responsive">
                                        <table id="table" class="table table-bordered table-striped table-hover">
                                            <thead>
                                            <tr class="info">

                                                <th>Campaign Name</th>
                                                <th>Template Name</th>
                                                <th>Create Date</th>
                                                <th>Total Receipt</th>
                                                <th>Total Click</th>
                                                <th>Status</th>
                                                <th>Action</th>
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

    var urltable='';
    var paramtable='';


    urltable="${pageContext.servletContext.contextPath}/api/campaign/getbycreateby";
    paramtable='<%=UtilityClass.getCurrentUser().getUsername()%>'

    var table = $('#table').DataTable({
        ajax: {

            url: urltable,
            dataSrc: '',
            "data": {
                "id":paramtable,

            }
        },
        "columns": [

            {
                "mRender": function (data, type, row) {


                    return "<a hidatr='" + JSON.stringify(row) + "' style='display: none'></a><span>"+row.name+"</span>";


                }
            },


            { "data": "templateName"},

            { "data": "createDate"},

            { "data": "totalReciept"},
            { "data": "totalClicks"},
            {
                "mRender": function (data, type, row) {
                    if(row.status==0) {
                        return "<button type=\"button\" class=\"btn btn-warning btn-rounded w-md m-b-5\" title='Campaign is Under Process'>Processing</button>";
                    }
                    else if(row.status==1) {
                        return "<button type=\"button\" class=\"btn btn-success w-md m-b-5\" title='Campaign completed'>Complete</button>";
                    }
                    else{
                        return "<button type=\"button\" class=\"btn btn-danger w-md m-b-5\" title='Campaing Failed'>Failed</button>";
                    }
                }
            },
            {
                "mRender": function (data, type, row) {

                    return "<button title='Click Here To View Campaign' type=\"button\" class=\"previewcampaign btn btn-add btn-sm\" ><i class=\"hvr-buzz-out fa fa-eye\"></i> </button>";



                }
            }

        ]
        , "initComplete": function (settings, json) {
            $('#preloader').fadeOut();
        },
        "ordering": false
    });

    $(document).on("click",".leadstatuscls",function () {
        var ths=$(this);
        $(".glyphicon-ok").removeClass("glyphicon-ok")
        $(".selectedtrue").removeClass("selectedtrue")
        var name=ths.text().trim();

        $('#leadstatushidden').val(name)

        ths.prepend("<i class=\"glyphicon glyphicon-ok\"></i>")
        ths.addClass("selectedtrue")

    })
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "${pageContext.servletContext.contextPath}/api/leadstatus/getactive",
        success: function (json) {
            var html="";
            for(var i=0;i<json.length;i++) {
                html = html+"   <button color='"+json[i].color+"' namestatus='"+json[i].name+"' style='background-color: "+json[i].color+"' title='"+json[i].description+"' type=\"button\" class=\"btn btn-labeled btn-success m-b-5 leadstatuscls\">\n" +
                    "        "+json[i].name+"\n" +
                    "        </button>";

            }
            $('#leadstatus').html(html)


        },
        error: function (err) {

        }

    })

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "${pageContext.servletContext.contextPath}/api/user/getusermyteam",
        success: function (json) {
            var html="<option value=''>---Select User---</option>";
            for(var i=0;i<json.length;i++) {
                html = html+"<option  value='"+json[i].id+"'>"+json[i].username+"</option> ";

            }
            $('#userlist').html(html)


        },
        error: function (err) {

        }

    })

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "${pageContext.servletContext.contextPath}/api/template/getalltemplateapproved",
        success: function (json) {
            var html="<option value=''>---Select Template---</option>";
            for(var i=0;i<json.length;i++) {
                html = html+"<option   value='"+JSON.stringify(json[i])+"'>"+json[i].templateName+"</option> ";

            }
            $('#templateid').html(html)


        },
        error: function (err) {

        }

    })

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "${pageContext.servletContext.contextPath}/api/product/getallavailable",
        success: function (json) {
            var html="<option value=''>---Select Product---</option>";
            for(var i=0;i<json.length;i++) {
                html = html+"<option title='"+json[i].description+"' value='"+json[i].name+"'>"+json[i].name+"  ("+json[i].price+")</option> ";

            }
            $('#product').html(html)


        },
        error: function (err) {

        }

    })

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "${pageContext.servletContext.contextPath}/api/leadsource/getactive",
        success: function (json) {
            var html="<option value=''>---Select Source---</option>";
            for(var i=0;i<json.length;i++) {
                html = html+"<option title='"+json[i].description+"' value='"+json[i].name+"'>"+json[i].name+"</option> ";

            }
            $('#leadsource').html(html)


        },
        error: function (err) {

        }

    })

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "${pageContext.servletContext.contextPath}/api/leadtype/getactive",
        success: function (json) {
            var html="<option value=''>---Select Source---</option>";
            for(var i=0;i<json.length;i++) {
                html = html+"<option title='"+json[i].description+"' value='"+json[i].name+"'>"+json[i].name+"</option> ";

            }
            $('#leadtype').html(html)


        },
        error: function (err) {

        }

    })
    $('#addnewuser').click(function () {
        clearData();
        $('#tablediv').css("display", "none");
        $('#addnewdiv').fadeIn();
    });
    $('#cancelbtn').click(function () {
        clearData();
        $('#addnewuser').css("display", "block");
        $('#addnewdiv').css("display", "none");
        $('#toTop').trigger("click")
        $('#tablediv').fadeIn();
    });






    $(document).on("click",".edituser",function () {
        $('#addnewuser').trigger("click")
        var json=JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));


        $('#campaignname').val(json.name)
        $('#schdate').val(json.schduleAt)
        var templatename=json.templateName;
        $('#id').val(json.id)
        $("#templateid option:contains(" + templatename +")").attr("selected", true);
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url:"${pageContext.request.contextPath}/api/campaign/getbycampaignid?id="+json.id,
            success: function (json) {
                json=json.al;
                var html="";
                var prefix='<%=UtilityClass.ApplicationPrefix%>';
                var titlehtml="";
                for(var i=0;i<json.length;i++)
                {
                    titlehtml="Fullname :"+json[i].firstName+" "+json[i].lastName;
                    titlehtml=titlehtml+"\nPhonenumber :"+json[i].phonenumber
                    titlehtml=titlehtml+"\nEmail :"+json[i].email
                    titlehtml=titlehtml+"\nLead Source :"+json[i].leadSource
                    titlehtml=titlehtml+"\nLead Status :"+json[i].leadStatus
                    titlehtml=titlehtml+"\nLead Type :"+json[i].clientType
                    titlehtml=titlehtml+"\nCompany Name :"+json[i].company

                    html=html+"<div title='"+titlehtml+"' hidjson='"+JSON.stringify(json[i])+"' class='customtolist'>"+prefix+json[i].id+"  <i title='Click Here To Delete This Lead From Campaign' class=\"hvr-buzz-out fa fa-trash deletebtn\"></i></div>"
                }
                $('#tolist').html(html)




            },
            error: function (err) {

            }

        })

        $('#createbtn').html("Update")

    })





    function clearData() {
        var urlsecong = window.location.toString().split("?")[0];
        window.history.pushState({}, null, urlsecong);
        $('#totalreciept').html(0)
        $('#tolist').html("");
        $('#urlembed').val("");
        $('#campaignname').val("")
        $('#id').val("");
        $('#templateid').val("");
        $('#schdate').val("")
        localStorage.removeItem("jsonhiddenreciept");
        $('#createbtn').html("Create");



    }


    $('#addnewrowvariable').click(function () {
        addNewRow("Variable","#variabletbody")
    })
    $('#addnewrowsource').click(function () {
        addNewRow("Source","#sourcetbody")
    })



    function addNewRow(value,id) {
        $(id).append("<tr><td style='width: 80%'><input placeholder='Enter "+value+"' type='text' class='form-control'> </td><td style='width: 20%'><input  type='checkbox'></td></tr>")

    }

    $('#addleads').click(function () {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url:"${pageContext.request.contextPath}/html/leadfilter.html",
            success: function (html) {
                $('#dynamicformbodymodel').html(html)
                $('#dunamicmodelheader').html("Lead Filter  <i class=\"hvr-buzz-out fa fa-filter\"></i>")
                $('#dynamicformmodel').modal("show");
                $('#logeventformjsonhidden').val(json)
                $('#leadstatusfilterform').html($('#leadstatus').html())
                $('#leadsourcefilterform').html($('#leadsource').html())
                $('#leadproductfilterform').html($('#product').html())
                $('#leadtypefilterform').html($('#leadtype').html())
                $('#leaduserfilterform').html($('#userlist').html())
                $('#leadstagefilterform').html($('#leadstagehidden').html())


            },
            error: function (err) {

            }

        })
    })

    $(document).on("submit","#dynamicfilterform",function (e) {
        e.preventDefault()
        $('#preloader').css("display","block");
        var leadsource = $('#leadsourcefilterform').val() || 0;
        var leadstatus = $('#leadstatushidden').val() || 0;
        var product = $('#leadproductfilterform').val() || 0;
        var leadtype = $('#leadtypefilterform').val() || 0;
        var userfilter = $('#leaduserfilterform').val() || 0;
        var startdate = $('#leadstartdatefilterform').val() || 0;
        var enddate = $('#leadendfilterform').val() || 0;
        var searchstring = $('#leadsearchfilterform').val() || 0;
        var leadstage = $('#leadstagefilterform').val() || 0;
        var innersource = $('#leadsourceinnerfilterform').val() || 0;
        var datefilter = true;
        var customdate = startdate + "@" + enddate;
        var datefiltertype = $("input[name='datefiltertype']:checked").val();
        if (startdate == 0 || enddate == 0) {
            customdate = 0;
            datefilter = false;
        }

        var urlparams = "datefiltertype=" + datefiltertype + "&innersource=" + innersource + "&statusValue=" + leadstatus + "&leadSource=" + leadsource + "&product=" + product + "&leadType=" + leadtype + "&userFilter=" + userfilter + "&datefilter=" + datefilter + "&datevalue=" + customdate + "&searchvalue=" + searchstring + "&leadstage=" + leadstage;
        var url = window.location.toString().split("?")[0];
        url = url + "?" + urlparams;
        window.history.pushState({}, null, url);
        $('#preloader').css("display","none");
        $('#cancelbtnfilterform').trigger("click");
        loadCountByFilter()

    })
    function loadCountByFilter()
    {
        var searchParams = new URLSearchParams(window.location.search);
        var leadstatus = searchParams.get('statusValue') || 0;
        var leadsource = searchParams.get('leadSource') || 0;
        var product = searchParams.get('product') || 0;
        var leadtype = searchParams.get('leadType') || 0;
        var userfilter = searchParams.get('userFilter') || 0;
        var datefilter = searchParams.get('datefilter') || false;
        var customdate = searchParams.get('datevalue') || 0;
        var searchstring = searchParams.get('searchvalue') || 0;
        var leadstage = searchParams.get('leadstage') || 0;
        var innersource = searchParams.get('innersource') || 0;
        var id = searchParams.get('id') || 0;
        var datefiltertype = searchParams.get('datefiltertype') || "createdate";
        $('#preloader').css("display","block")
        var urlinner = "?datefiltertype=" + datefiltertype + "&innersource=" + innersource + "&statusValue=" + leadstatus + "&leadSource=" + leadsource + "&product=" + product + "&leadType=" + leadtype + "&userFilter=" + userfilter + "&datefilter=" + datefilter + "&datevalue=" + customdate + "&searchvalue=" + searchstring + "&leadstage=" + leadstage + "&id=" + id;

        var url="${pageContext.request.contextPath}/api/campaign/getleadcount"+urlinner
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url:url,
            success: function (count) {
                $('#totalreciept').html(count)

                $('#preloader').css("display","none");


            },
            error: function (err) {

            }

        })

    }

    $(document).on("click",".infolead",function () {
        var json=JSON.parse($(this).closest("div").attr("hidjson"))
        var html="Fullname:"+json.firstName+" "+json.lastName;

        $(this).prop("title",html)

    })
    $(document).on("click",".deletbtntable",function () {

        var json=JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));
        swal({
                title: "Are you sure?",
                text: "Delete this Campaign?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: '#DD6B55',
                confirmButtonText: 'Yes, I am sure!',
                cancelButtonText: "No, cancel it!",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            function (isConfirm) {

                if (isConfirm) {

                    $.ajax({
                        type: "DELETE",
                        contentType: "application/json",
                        url:"${pageContext.request.contextPath}/api/campaign/delete?id="+json.id,
                        success: function (json) {

                            table.ajax.reload();



                        },
                        error: function (err) {

                        }

                    })

                }
                swal.close()

            });


    })
    $(document).on("click",".deletebtn",function () {
        var ths=$(this);
        swal({
                title: "Are you sure?",
                text: "Delete this Reciept?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: '#DD6B55',
                confirmButtonText: 'Yes, I am sure!',
                cancelButtonText: "No, cancel it!",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            function (isConfirm) {

                if (isConfirm) {

                    ths.closest("div").remove();

                }
                swal.close()

            });


    })
    $('#templateid').change(function () {
        var json=JSON.parse($(this).val());

        if(json.hasUrl)
        {


            $('#urldiv').fadeIn();
            $('#urldiv').find("input").attr("required",true)

        }
        else{
            $('#urldiv').css("display","none")
            $('#urldiv').find("input").attr("required",false)

        }

    })

    $('#createnewuserform').submit(function (e) {

        e.preventDefault();
        var searchParams = new URLSearchParams(window.location.search);
        var leadstatus = searchParams.get('statusValue') || 0;
        var leadsource = searchParams.get('leadSource') || 0;
        var product = searchParams.get('product') || 0;
        var leadtype = searchParams.get('leadType') || 0;
        var userfilter = searchParams.get('userFilter') || 0;
        var datefilter = searchParams.get('datefilter') || false;
        var customdate = searchParams.get('datevalue') || 0;
        var searchstring = searchParams.get('searchvalue') || 0;
        var leadstage = searchParams.get('leadstage') || 0;
        var innersource = searchParams.get('innersource') || 0;
        var id = searchParams.get('id') || 0;
        var datefiltertype = searchParams.get('datefiltertype') || "createdate";
        var maxLimitSms=<%=UtilityClass.propertyService.findProperty("Campaign","maximumonecampaignlimitMSG")%>
        var maxLimitMail=<%=UtilityClass.propertyService.findProperty("Campaign","maximumonecampaignlimitMAIL")%>
        var urlinner = "?datefiltertype=" + datefiltertype + "&innersource=" + innersource + "&statusValue=" + leadstatus + "&leadSource=" + leadsource + "&product=" + product + "&leadType=" + leadtype + "&userFilter=" + userfilter + "&datefilter=" + datefilter + "&datevalue=" + customdate + "&searchvalue=" + searchstring + "&leadstage=" + leadstage + "&id=" + id;

        var url="${pageContext.request.contextPath}/api/campaign/insertcampaign"+urlinner
        msg="Are you sure you want create Campaign ?";
        var map={};
        var templatejson=JSON.parse($('#templateid').val())
        map["templateId"]=templatejson.id;
        map["name"]=$('#campaignname').val();
        map["templateName"]=templatejson.templateName
        map["url"]=$('#urlembed').val();
        map["totalReciept"]=$('#totalreciept').html()||0;
        if(map["totalReciept"]==0)
        {
            swal({
                title: 'Error',
                text: "Cannot Create Campaign with 0 Reciept",
                type: 'error',
                confirmButtonText: 'Ok'
            })
            return;
        }

        if(templatejson.templateType=="Mail")
        {
            if(map["totalReciept"]>maxLimitMail)
            {
                swal({
                    title: 'Error',
                    text: "Maximum  Campaign Limit Exceed. Maximum is "+maxLimitMail,
                    type: 'error',
                    confirmButtonText: 'Ok'
                })
                return;
            }
        }else{
            if(map["totalReciept"]>maxLimitSms)
            {
                swal({
                    title: 'Error',
                    text: "Maximum  Campaign Limit Exceed. Maximum is "+maxLimitSms,
                    type: 'error',
                    confirmButtonText: 'Ok'
                })
                return;
            }
        }



        swal({
                title: "Are you sure?",
                text: msg,
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: '#DD6B55',
                confirmButtonText: 'Yes, I am sure!',
                cancelButtonText: "No, cancel it!",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            function (isConfirm) {

                if (isConfirm) {
                    $('#preloader').css("display","block")

                    $.ajax({
                        type: "POST",
                        data: JSON.stringify(map),
                        contentType: "application/json",
                        url: url,
                        success: function (data) {
                            if ($('#createbtn').html().trim() == "Create") {


                                swal({
                                    title: "Success!",
                                    text: "Successfully Created Campaign",
                                    icon: "success",
                                    button: "Aww yiss!",
                                });
                            }
                            clearData();
                            table.ajax.reload();
                            $('#addnewdiv').css("display","none")
                            $('#tablediv').fadeIn();
                            $('#preloader').fadeOut();
                            $('#addnewuser').css("display","block")
                            $('#toTop').trigger("click")
                            $('#preloader').css("display","none")
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

                }else{
                    $('#preloader').css("display","none")
                }
                swal.close()

            });
    })


    var myChart=null;
    $(document).on("click",".previewcampaign",function () {
        var json=JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));
        window.open("${pageContext.request.contextPath}/campaigntransaction?id="+json.id+"&count="+json.totalReciept)

    })
    $(document).on("change","#leadstagefilterform",function () {
        var ths=$(this);
        var value=ths.val()||0;
        if(value==0)
        {
            return;
        }
        var json=JSON.parse(ths.find('option:selected').attr("hidatr"));
        var option=json.option;
        var html="<option value=''>-----Select Lead Status----</option>"
        for(var i=0;i<option.length;i++)
        {
            html=html+"<option value='"+option[i].value+"'> "+option[i].value+"</option>"
        }
        $('#leadstatusfilterform').html(html)


    })
    $(document).on("click","#pieChart",function (evt) {
        $('#preloader').fadeIn();
        var activePoints = myChart.getElementsAtEvent(evt);
        var typ=0;
        if(activePoints[0]._model.label.trim()=="Open") {
            typ = 1;
        }

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url:"${pageContext.request.contextPath}/api/campaign/getclicktrans?id="+$('#hididdrawgraph').val()+"&type="+typ,
            success: function (json) {

                $('#preloader').css("display","none");


            },
            error: function (err) {
                $('#preloader').css("display","none");
            }

        })
    })
    $.ajax({
        url: "${pageContext.request.contextPath}/api/leadstage/getall",
        type: 'GET',
        success: function (json) {
            var html="<option value=''>---Select Lead Stage----</option>"
            for(var i=0;i<json.length;i++)
            {




                html=html+"<option hidatr='"+JSON.stringify(json[i])+"' value='"+json[i].stage+"'>"+json[i].stage+"</option>"
            }
            $('#leadstagehidden').html(html)
        },
        error: function (err) {


        }
    })
</script>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>

