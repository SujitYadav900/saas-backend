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
    <link rel="stylesheet"href="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.min.css"/>
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
<%--    <link href="${pageContext.request.contextPath}/assets/plugins/pace/flash.css" rel="stylesheet" type="text/css"/>--%>
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
<body class="hold-transition sidebar-mini" >
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
                <i class="fa fa-sitemap"></i>
            </div>

            <div class="header-title" >
                <h1>Lead Source</h1>
                    <a href="#" class="btn btn-add" data-toggle="modal" onclick="clearData()" data-target="#addLeadSource" title="Add new lead source"><i class="fa fa-plus"></i> Add Lead Source</a>
                </div>

        </section>
        <!-- Main content -->
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-bd lobidrag">
                        <div class="panel-heading">
                            <div class="btn-group" id="buttonexport">
                                <a href="#">
                                    <h4>Lead Source Details</h4>
                                </a>
                            </div>
                        </div>
                        <div class="panel-body">

                            <div class="table-responsive">
                                <div class="table-responsive card" style="padding: 20px">
                                    <table class="table table-striped table-bordered first" id="table">
                                        <thead>
                                        <tr>
                                            <th>Name</th><th>Access Token</th><th>Description</th><th>Status</th><th>Initial Stage</th><th>Initial Status</th><th>Initial Type</th><th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!--======================================================ADD NEW LEAD STATUS=================================================================== -->
            <div class="modal fade" id="addLeadSource" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header modal-header-primary">
                            <button type="button" onclick="clearData()" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h3><i class="fa fa-plus m-r-5"></i> Add New Lead Source</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <form class="form-horizontal" id="createForm">
                                        <input type="hidden" id="accesstoken">
                                        <fieldset>

                                            <!-- Not to be manually inserted-->

                                                <input type="hidden" id="id" class="form-control" >

                                            <div class="col-md-10 form-group">
                                                <label class="control-label">Status</label>
                                                <div class="form-check-inline">
                                                    <label class="form-check-label">
                                                        <input type="radio" class="form-check-input" name="optradionew" value="1" checked>Enabled
                                                    </label>
                                                </div>
                                                <div class="form-check-inline">
                                                    <label class="form-check-label">
                                                        <input type="radio" class="form-check-input" name="optradionew" value="0">Disabled
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="col-md-10 form-group">
                                                <label class="control-label">Lead Assign By</label>
                                                <div class="form-check-inline">
                                                    <label class="form-check-label">
                                                        <input type="radio" class="form-check-input" name="leadssignby" value="Source" checked>Source
                                                    </label>
                                                </div>
                                                <div class="form-check-inline">
                                                    <label class="form-check-label">
                                                        <input type="radio" class="form-check-input" name="leadssignby" value="Type">Type
                                                    </label>
                                                </div>
                                                <div class="form-check-inline">
                                                    <label class="form-check-label">
                                                        <input type="radio" class="form-check-input" name="leadssignby" value="Product">Product
                                                    </label>
                                                </div>

                                            </div>

                                            <!-- Text input-->
                                            <div class="col-md-10 form-group">
                                                <label class="control-label">Name</label>
                                                <input type="text"  maxlength="100" placeholder="Lead Source Name"  id="name" class="form-control"  required>
                                            </div>

                                            <div class="col-md-10 form-group">
                                                <label class="control-label">Intital Stage</label>
                                                <select required  class="form-control" id="initialstage"></select>
                                            </div>
                                            <div class="col-md-10 form-group">
                                                <label class="control-label">Intital Status</label>
                                                <select required  class="form-control" id="initialstatus"></select>
                                            </div>
                                            <div class="col-md-10 form-group">
                                                <label class="control-label">Intital Type</label>
                                                <select required  class="form-control" id="initialtype"></select>
                                            </div>
                                            <!-- Text input-->
                                            <div class="col-md-10 form-group">
                                                <label class="control-label">Description</label>
                                                <input type="text" maxlength="256"placeholder="Lead Source Description"  id="description" class="form-control" >
                                            </div>

                                            <!-- Text input-->
                                            <div class="col-md-10 form-group">
                                                   <input type="checkbox" id="sendnotification"  name="checkbox" /> Send Text Message To Lead

                                            </div>
                                            <!-- Text input-->
                                            <div class="col-md-10 form-group" id="templatediv">
                                                <div class="form-check">
                                                    <label class="control-label">Template Source</label>
                                                    <br>
                                                    <label class="form-check-label">
                                                        <input type="radio" class="form-check-input templateSourceRadio" name="templateSource" value="CRM" checked> CRM
                                                    </label>
                                                    <label class="form-check-label">
                                                        <input type="radio" class="form-check-input templateSourceRadio" name="templateSource" value="WATI"> WATI
                                                    </label>
                                                </div>
                                                <label>Template</label>
                                                <select id="template" name="template" class="form-control">
                                                </select>
                                            </div>

                                            <!-- Text input-->
                                            <div class="col-md-10 form-group">
                                                <input type="checkbox" id="sendnotificationothers"  name="checkbox" /> Send Text Message To Others

                                            </div>
                                            <!-- Text input-->
                                            <div class="col-md-10 form-group" id="numberfielddiv">
                                                <label>Template</label>
                                                <select id="templateothers" name="templateothers"  class="form-control">
                                                </select>

                                                <label>Receivers</label>
                                                <textarea id="numberfield" name="numberfield" placeholder="Enter comma separated phone numbers" class="form-control"></textarea>
                                            </div>


                                            <div class="col-md-12 form-group user-form-group">
                                                <div class="pull-left">
<%--                                                    <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal">Cancel</button>--%>
                                                    <button type="submit" class="btn btn-add btn-sm" id="createBtn">Create</button>
                                                </div>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger pull-right" id="formCloseBtn" onclick="clearData()" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>

                    <!-- /.modal-content -->
                    <%--=========================================================================================================================================--%>
                    <%-- ================================================== ADD LEAD SOURCE TO AGENT  ============================================================--%>
                    <div class="modal" id="addLeadAgent" tabindex="-1" role="dialog" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header modal-header-primary">
                                    <button type="button" onclick="clearUserTableData()" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <h3><i class="fa fa-plus m-r-5"></i>Assign Agent</h3>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <form class="form-horizontal" id="addAgentForm">
                                                <fieldset>
                                                    <div class="form-group col-md-12">
                                                        <label>User </label>
                                                        <select id="leadSourceSelect" name="leadSource" required class="form-control">

                                                        </select>
                                                    </div>

                                                    <div class="form-group col-md-12">
                                                        <div class="table-responsive">
                                                            <table class="table table-bordered table-striped table-hover" id="usertable">
                                                                <tbody id="leadSourceAgentBody"></tbody>
                                                            </table>
                                                        </div>
                                                    </div>

                                                </fieldset>
                                            </form>


                                        </div>
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger pull-right" id="agentFormCloseBtn" onclick="clearUserTableData()" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                    <%-- =========================================================================================================================================--%>
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-
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
<script src="${pageContext.request.contextPath}/assets/plugins/jQuery/jquery-1.12.4.min.js" type="text/javascript"></script>
<!-- jquery-ui -->
<script src="${pageContext.request.contextPath}/assets/plugins/jquery-ui-1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<!-- lobipanel -->
<script src="${pageContext.request.contextPath}/assets/plugins/lobipanel/lobipanel.min.js" type="text/javascript"></script>
<!-- Pace js -->
<script src="${pageContext.request.contextPath}/assets/plugins/pace/pace.min.js" type="text/javascript"></script>
<%--<!-- SlimScroll -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript">    </script>--%>
<%--<!-- FastClick -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/fastclick/fastclick.min.js" type="text/javascript"></script>--%>
<!-- CRMadmin frame -->
<script src="${pageContext.request.contextPath}/assets/dist/js/custom.js" type="text/javascript"></script>
<!-- End Core Plugins
   =====================================================================-->
<!-- Start Page Lavel Plugins
   =====================================================================-->
<!-- ChartJs JavaScript -->
<%--<script src="${pageContext.request.contextPath}/assets/plugins/chartJs/Chart.min.js" type="text/javascript"></script>--%>
<!-- Counter js -->
<%--<script src="${pageContext.request.contextPath}/assets/plugins/counterup/waypoints.js" type="text/javascript"></script>--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/counterup/jquery.counterup.min.js" type="text/javascript"></script>--%>
<!-- Monthly js -->
<%--<script src="${pageContext.request.contextPath}/assets/plugins/monthly/monthly.js" type="text/javascript"></script>--%>
<!-- End Page Lavel Plugins
   =====================================================================-->
<!-- Start Theme label Script
   =====================================================================-->
<!-- Dashboard js -->
<script src="${pageContext.request.contextPath}/assets/dist/js/dashboard.js" type="text/javascript"></script>
<!-- End Theme label Script=====================================================================-->

<!-- =============================DATA TABLES======================================================-->
<script src="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.min.js"></script>


<script>


    <%--==================================== ASSIGNING LEAD SOURCE TO AN AGENT ========================--%>

<%--    ==========================POPULATE SELECT FIELD  =============================================--%>

    function clearUserTableData(){
        $('#leadSourceAgentBody').html("");
    }

    function loadAgent(){
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: '${pageContext.servletContext.contextPath}/api/user/getalluser',
            success: function (json) {
                var html = "<option value='0'>---Select User---</option>";
                for (var i = 0; i < json.length; i++) {
                    html = html + "<option value='" + json[i].id + "'>" + json[i].firstName + " " + json[i].lastName +" ("+ json[i].username + ")</option>"

                }
                $('#leadSourceSelect').html(html)

            },
            error: function (err) {

            }

        })
    }
    $(document).on('click','.addLeadAgentBtn',function () {


       var json = JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));

        var tempId = json.id;
      $('#leadSourceSelect').attr('leadsourceid',json.id);


        //======================== table =======================

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: '${pageContext.servletContext.contextPath}/api/leadsourceuser/getallbysourceid?lead_Source_id='+tempId,
            success: function (json) {
                var html = "";
                for (var i = 0; i < json.length; i++) {
                    var html = $('#leadSourceAgentBody').html();
                    html = html + ("<tr><td attrhid='"+json[i].id+"'>"+json[i].name+"</td><td><button type='button' class='btn btn-danger btn-sm deletUser' title='Delete User from List'  ><i class='fa fa-trash'></i></button></td></tr>");
                    $('#leadSourceAgentBody').html(html);

                }
                //$('#leadSourceSelect').html(html)

            },
            error: function (err) {

            }

        })
        loadAgent();
        //======================================================
    });


    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '${pageContext.servletContext.contextPath}/api/leadtype/getactive',
        success: function (json) {
            var html = "";
            for (var i = 0; i < json.length; i++) {
                html=html+"<option value='"+json[i].name+"'>"+json[i].name+"</option>"


            }
            $('#initialtype').html(html)

        },
        error: function (err) {

        }

    })

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '${pageContext.servletContext.contextPath}/api/leadstage/getall',
        success: function (json) {
            var html = "";
            for (var i = 0; i < json.length; i++) {
                html=html+"<option hidjson='"+JSON.stringify(json[i].option)+"' value='"+json[i].stage+"'>"+json[i].stage+"</option>"


            }
            $('#initialstage').html(html)

        },
        error: function (err) {

        }

    })
    function changeStatusByStage(value)
    {

    var json= JSON.parse($("#initialstage option:selected").attr("hidjson"));
    var html=""
        for (var i = 0; i < json.length; i++) {
            html=html+"<option  value='"+json[i].value+"'>"+json[i].value+"</option>"


        }

        $('#initialstatus').html(html)
        if(value!=0)
        {
            $('#initialstatus').val(value)
        }

    }

    $('#initialstage').change(function () {

        changeStatusByStage(0)


    })
    //============================ INSERT USER ON SELECT ==========================
    $('#leadSourceSelect').on('change',function () {
        var userId = $(this).val();
        var username = $("#leadSourceSelect option:selected").text();
        var leadsourceid =$(this).attr('leadsourceid');


        if (userId == 0) {

        } else {

            var map={
                lead_source_id : leadsourceid,
                user_id : userId
            }
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "${pageContext.servletContext.contextPath}/api/leadsourceuser/insert",
                data: JSON.stringify(map),
                success: function (data) {

                    var html = $('#leadSourceAgentBody').html();
                    html = html + ("<tr><td attrhid='"+data.id+"'>"+username+"</td><td><button type='button' class='btn btn-danger btn-sm deletUser' title='Delete User from List'  ><i class='fa fa-trash'></i></button></td></tr>");
                    $('#leadSourceAgentBody').html(html);

                },
                error: function (err) {
                    swal({
                        title: 'Error',
                        text: err.responseJSON.message,
                        icon: 'error',
                        button: 'Ok'
                    });
                }

            })
        }

    })

//=============================== DELETE USER ==============================================
    $(document).on('click','.deletUser',function(){
        var id = $(this).closest("tr").find("td").eq(0).attr("attrhid");
        var ths = $(this).closest('tr');

        swal({
            title: "Are you sure?",
            text: "Delete User?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if(willDelete){
                $.ajax({
                    url: "${pageContext.request.contextPath}/api/leadsourceuser/delete?id=" + id,
                    type: 'DELETE',
                    success: function () {
                        ths.remove();
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
            }
            });

    })


    <%--===============================================================================================--%>
    $('#templatediv').hide();
    $("#sendnotification").on('change',function () {
        $('#templatediv').toggle();
    })

    $('#numberfielddiv').hide();
    $("#sendnotificationothers").on('change',function () {
        $('#numberfielddiv').toggle();
    })

    //  ==============FORM SUBMISSION SCRIPT | CREATING A NEW Lead Source===================

    $('#createForm').submit(function (e) {
        e.preventDefault();
        //==================== FOR SENDING TEXT MESSAGE TO LEADS
        var sendnotication_status = $("#sendnotification").is(':checked');
        var sendnotification = ''
        var template_val = '';
        var validation = true;
        if(sendnotication_status && $('#template').val() != 0){
            sendnotification = 1;
            template_val = $('#template').val();
        }

        else if(sendnotication_status && $('#template').val() == 0){
            validation = false;
        }
        else {
            sendnotification = 0;
        }
        //========================= FOR SENDING TEXT MESSAGE TO OTHERS
        var sendnotication_others_status = $("#sendnotificationothers").is(':checked');
        var sendnotification_others = ''
        var numberfield_val = '';
        var template_others_val = '';
        var validation_others_2 = true;

        if(sendnotication_others_status && $('#numberfield').val() != '' && $('#templateothers').val() != 0){
            sendnotification_others = 1;
            numberfield_val = $('#numberfield').val();
            template_others_val = $('#templateothers').val();
        }

        else if(sendnotication_others_status && $('#numberfield').val() == '' && $('#templateothers').val() == 0){
            validation = false;
        }
        else if(sendnotication_others_status && $('#templateothers').val() == 0){
            validation = false;
        }

        else if(sendnotication_others_status && $('#numberfield').val() == '' ){
            validation_others_2 = false;
        }

        else {
            sendnotification_others = 0;
        }

        if(validation && validation_others_2 ){
            var url = "";
            if($('#createBtn').html() == 'Create'){

                let name= $('#name').val()
                name = name.trim();


                var url = "${pageContext.request.contextPath}/api/leadsource/insert";
                var map = {

                    name: name,
                    desc: $('#description').val(),
                    status: $("input[name='optradionew']:checked").val(),
                    sendnotification : sendnotification,
                    template : template_val,
                    sendnotificationothers : sendnotification_others,
                    templateothers : template_others_val,
                    phonenumbers: numberfield_val,
                    assignBy:$("input[name='leadssignby']:checked").val(),
                    initialStage:$('#initialstage').val(),
                    initialStatus:$('#initialstatus').val(),
                    initialType:$('#initialtype').val()


                };
            }

            else{

                var url = "${pageContext.request.contextPath}/api/leadsource/update";
                let name= $('#name').val()
                name = name.trim();

                var map = {
                    id:$('#id').val(),
                    name: name,
                    desc: $('#description').val(),
                    status: $("input[name='optradionew']:checked").val(),
                    sendnotification : sendnotification,
                    template : template_val,
                    sendnotificationothers : sendnotification_others,
                    templateothers : template_others_val,
                    phonenumbers: numberfield_val,
                    assignBy:$("input[name='leadssignby']:checked").val(),
                    initialStage:$('#initialstage').val(),
                    initialStatus:$('#initialstatus').val(),
                    initialType:$('#initialtype').val(),
                    accessToken:$('#accesstoken').val()

                };


            }

            $.ajax({
                type: 'POST',
                data: JSON.stringify(map),
                contentType: 'application/json',
                url: url,
                success: function (data) {

                    if($('#createBtn').html() == 'Create'){

                        swal({
                            title: "Success!",
                            text: "Successfully created Lead Source",
                            icon: "success",
                            button: "Ok!",
                        });
                        // $('.modal').modal('toggle')
                    }

                    else{

                        swal({
                            title: "Success!",
                            text: "Successfully Updated Lead Source",
                            icon: "success",
                            button: "Ok!",
                        });


                    }
                    $('#formCloseBtn').trigger('click');
                    //$('.modal').modal('toggle')
                    // $('.modal').css('display','none');

                    clearData()
                    reloadTable();

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
        else if(!validation_others_2){
                swal({
                    title: 'Error',
                    text: "Please Provide At Least One Phone Number!!",
                    icon: 'error',
                    button: 'Ok'
                });
        }

        else{

            swal({
                title: 'Error',
                text: "Please Select one Template!!",
                icon: 'error',
                button: 'Ok'
            });
        }


    });
    // ============================================== TABLE ============================
    var table=  $('#table').DataTable( {
        ajax: {
            url: '${pageContext.servletContext.contextPath}/api/leadsource/getall',
            dataSrc: '',
            "data": {
                "flag": 1,

            }
        },
        "columns": [

            { "mRender": function (data, type, row) {


                    return "<a hidatr='" + JSON.stringify(row) + "' style='display: none'></a><span>"+row.name+"</span>";


                }
            },
            { "data": "accessToken"},
            { "data": "desc"},
            {
                "mRender": function (data, type, row) {
                    var html = "";

                    if (row.status == 1) {
                        html = html + "<i style=' margin-left: 5px; font-size: 18px;color: greenyellow' title='Active' class=\"hvr-buzz-out fa fa-check\"></i>";
                    } else {

                        html = html + "<i style=' margin-left: 5px; font-size: 18px;color: red' title='Not Active' class=\"hvr-buzz-out fa fa-ban\"></i>";
                    }

                    return html;
                }


            },
            { "data": "initialStage"},
            { "data": "initialStatus"},
            { "data": "initialType"},

            { "mRender": function (data, type, row) {


                return '<button style="margin: 5px;" type="button" class="btn btn-add btn-sm editfield" title="Update Lead Source" data-toggle="modal" data-target="#addLeadSource" ><i class="fa fa-pencil"></i> </button>' +
                    '<button style="margin: 5px;" type="button" style="padding-left: 10px;" class="btn btn-add btn-sm addLeadAgentBtn"  title="Assign agent" data-toggle="modal" data-target="#addLeadAgent"><i class="hvr-buzz-out fa fa-user-circle-o"></i></button>';

            }

            },

        ]


        , "initComplete": function(settings, json) {
            $('#tablediv').fadeIn();
            $('#loading').fadeOut();
        },
        "ordering" : false
    } );

    //RESETTING VALUES AFTER UPDATE OPERATION, ELSE VALUES REMAINS SAME AS AFTER AN 'UPDATE' OPERATION
   function clearData()
   {
       $('#id').val("");
       $('#name').val("");
       $('#description').val("")
       $("input[name=optradionew][value='1']").prop('checked', true);
       $("#sendnotification").prop("checked",false);
       $("#templatediv").hide();
       $("#template").val(0);
       $("#sendnotificationothers").prop("checked",false);
       $("#numberfielddiv").hide();
       $("#numberfield").val("");
       $("#templateothers").val(0);
       $('#createBtn').html("Create")

       changeStatusByStage("Pending")
   }

    $(document).on("click",".editfield",function () {
        //var json=JSON.parse($(this).closest("tr").find("td").eq(0).find("a").html());
        var json = JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));
        var test = $(this);
        $('#id').val(json.id);
        $('#name').val(json.name);
        $('#description').val(json.desc)
        $("input[name=optradionew][value=" + json.status + "]").prop('checked', true);
        $("input[name=leadssignby][value=" + json.assignBy + "]").prop('checked', true);
        $('#accesstoken').val(json.accessToken)
        $('#initialstage').val(json.initialStage)
        $('#createBtn').html("Update")
        changeStatusByStage(json.initialStatus)
        $('#initialtype').val(json.initialType)

        if(json.sendnotification == '1'){
            $('#sendnotification').prop('checked', true);
            $('#template').val(json.template);
            $('#templatediv').show();
        }

        if(json.sendnotificationothers == '1'){
            $('#sendnotificationothers').prop('checked', true);
            $('#numberfield').val(json.phonenumbers);
            $('#templateothers').val(json.templateothers);
            $('#numberfielddiv').show();
        }


    })

    //Refresh table
    function reloadTable(){
        table.ajax.reload();
    }

//======================== POPULATE TEMPLATE ========================

    //================================TEMPLATE FOR LEADS
    loadtemplate();
    function loadtemplate() {
       var url = '${pageContext.servletContext.contextPath}/api/template/getalltemplateapproved?flag=1';
       var id  = '#template';
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (json) {
                var html = "<option value='0'>---Select Template---</option>";
                for (var i = 0; i < json.length; i++) {
                    html = html + "<option value='" + json[i].id + "'>" + json[i].templateName + "</option>"

                }
                $(id).html(html)

            },
            error: function (err) {

            }

        })
    }

    //========================= TEMPLATE FOR OTHERS
    loadtemplateforOthers();
    function loadtemplateforOthers() {
        var url = '${pageContext.servletContext.contextPath}/api/template/getalltemplate';
        var id  = '#templateothers';
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (json) {
                var html = "<option value='0'>---Select Template---</option>";
                for (var i = 0; i < json.length; i++) {
                    html = html + "<option value='" + json[i].id + "'>" + json[i].templateName + "</option>"

                }
                $(id).html(html)

            },
            error: function (err) {

            }

        })
    }

    //LOAD CRM TEMPLATES BY DEFAULT
    loadTemplates("CRM");

    $('.templateSourceRadio').click(function () {
        let templateSource = $(this).val().toString();
        loadTemplates(templateSource);
    })
    function loadTemplates(templateSource){
        if(templateSource != "CRM"){
            console.log("I am the Lead Source ",templateSource );
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "${pageContext.servletContext.contextPath}/api/template/getalltemplateapproved?templateSource="+templateSource,
                success: function (json) {
                    var html="<option value=''>---Select Template---</option>";
                    for(var i=0;i<json.length;i++) {
                        //let templateContent = json[i].templateContent
                        json[i].templateContent = encodeURIComponent(JSON.stringify(json[i].templateContent)).replace(/'/g, "%27");
                        console.log(json[i].templateContent);
                        html = html+"<option   value='"+json[i].templateName+"'>"+json[i].templateName+"</option> ";
                    }
                    $('#template').val("");
                    $('#template').html(html)

                },
                error: function (err) {
                    console.log(err)
                    swal({
                        title: 'Error',
                        text: JSON.stringify(err.responseJSON.message),
                        type: 'error',
                        confirmButtonText: 'Ok'
                    })
                }
            })
        }else{
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "${pageContext.servletContext.contextPath}/api/template/getalltemplateapproved?templateSource="+templateSource,
                success: function (json) {
                    var html="<option value=''>---Select Template---</option>";
                    for(var i=0;i<json.length;i++) {
                        //let templateContent = json[i].templateContent
                        json[i].templateContent = encodeURIComponent(JSON.stringify(json[i].templateContent)).replace(/'/g, "%27");
                        console.log(json[i].templateContent);
                        html = html+"<option   value='"+JSON.stringify(json[i])+"'>"+json[i].templateName+"</option> ";
                    }
                    $('#template').val("");
                    $('#template').html(html)

                },
                error: function (err) {
                    console.log(err)
                    swal({
                        title: 'Error',
                        text: JSON.stringify(err.responseJSON.message),
                        type: 'error',
                        confirmButtonText: 'Ok'
                    })
                }
            })
        }

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/template/getalltemplateapproved?templateSource="+templateSource,
            success: function (json) {
                var html="<option value=''>---Select Template---</option>";
                for(var i=0;i<json.length;i++) {
                    //let templateContent = json[i].templateContent
                    json[i].templateContent = encodeURIComponent(JSON.stringify(json[i].templateContent)).replace(/'/g, "%27");
                    console.log(json[i].templateContent);
                    html = html+"<option   value='"+JSON.stringify(json[i])+"'>"+json[i].templateName+"</option> ";
                }
                $('#template').val("");
                $('#template').html(html)

            },
            error: function (err) {
                console.log(err)
                swal({
                    title: 'Error',
                    text: JSON.stringify(err.responseJSON.message),
                    type: 'error',
                    confirmButtonText: 'Ok'
                })
            }
        })
    }


</script>
<%--============================ DATA TABLE ENDS HERE =====================================--%>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>

