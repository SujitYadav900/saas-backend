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
                <i class="fa fa-th-large"></i>
            </div>

                <div class="header-title">
                    <h1>Campaign
                    </h1>
                    <a href="#" class="btn btn-add" data-toggle="modal" data-target="#addLeadPriority" title="Add new Campaign"><i class="fa fa-plus"></i> Add Campaign</a>
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
                                    <h4>Campaign Details</h4>
                                </a>
                            </div>
                        </div>
                        <div class="panel-body">

                            <div class="table-responsive">
                                <div class="table-responsive card" style="padding: 20px">
                                    <table class="table table-striped table-bordered first" id="table">
                                        <thead>
                                        <tr>
                                            <th>Name</th><th>Description</th><th>Status</th></th><th>Action</th>
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

            <!--======================================================ADD NEW Lead Type=================================================================== -->
            <div class="modal fade" id="addLeadPriority" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header modal-header-primary">
                            <button type="button" onclick="clearData()" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h3><i class="fa fa-plus m-r-5"></i> Add New Campaign</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <form class="form-horizontal" id="createForm">
                                        <fieldset>

                                            <!-- Not to be manually inserted-->
                                                <input type="hidden" id="id" class="form-control" >
                                            <!-- Text input-->
                                            <div class="col-md-8 form-group">
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


                                            <!-- Text input-->
                                            <div class="col-md-8 form-group">
                                                <label class="control-label">Name</label>
                                                <input type="text" maxlength="100" placeholder="Lead Priority Name"  id="name" class="form-control" required>
                                            </div>
<%--                                            <div class="col-md-8 form-group">--%>
<%--                                                <label class="control-label">Colour</label>--%>
<%--                                                <input type="color" minlength="3" maxlength="100"   title="Choose a color for this priority" id="color" class="form-control" required>--%>
<%--                                            </div>--%>
                                            <!-- Text input-->
                                            <div class="col-md-8 form-group">
                                                <label class="control-label">Description</label>
                                                <input type="text"  maxlength="256" placeholder="Lead Priority Description"  id="description" class="form-control">
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
                            <button type="button" class="btn btn-danger pull-right" onclick="clearData()" id="formCloseBtn" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                    <%--=========================================================================================================================================--%>
                </div>
                <!-- /.modal-dialog -->Lead
            </div>
            <!-- /.modal -->

            <%-- ================================================== ADD LEAD PRIORITY TO AGENT  ============================================================--%>
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
                                                <select id="leadPrioritySelect" name="leadpriority" required class="form-control">

                                                </select>
                                            </div>

                                            <div class="form-group col-md-12">
                                                <div class="table-responsive">
                                                    <table class="table table-bordered table-striped table-hover" id="usertable">
                                                        <tbody id="leadPriorityAgentBody"></tbody>
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
<!-- ==============FORM SUBMISSION SCRIPT | CREATING A NEW ROLE=================== -->
<script>

    function clearUserTableData(){
        $('#leadPriorityAgentBody').html("");
    }

    function loadAgent(){
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: '${pageContext.servletContext.contextPath}/api/user/getalluser',
            success: function (json) {
                var html = "<option value='0'>---Select User---</option>";
                for (var i = 0; i < json.length; i++) {
                    html = html + "<option value='" + json[i].id + "'>" + json[i].username + "</option>"

                }
                $('#leadPrioritySelect').html(html)

            },
            error: function (err) {

            }

        })
    }

    $(document).on('click','.addLeadAgentBtn',function () {


        var json = JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));

        var tempId = json.id;
        $('#leadPrioritySelect').attr('leadpriorityid',json.id);


        //======================== table =======================

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: '${pageContext.servletContext.contextPath}/api/leadpriorityuser/getallbypriorityid?lead_priority_id='+tempId,
            success: function (json) {
                var html = "";
                for (var i = 0; i < json.length; i++) {
                    var html = $('#leadPriorityAgentBody').html();
                    html = html + ("<tr><td attrhid='"+json[i].id+"'>"+json[i].name+"</td><td><button type='button' class='btn btn-danger btn-sm deletUser' title='Delete User from List'  ><i class='fa fa-trash'></i></button></td></tr>");
                    $('#leadPriorityAgentBody').html(html);

                }
                //$('#leadPrioritySelect').html(html)

            },
            error: function (err) {

            }

        })
        loadAgent();
        //======================================================
    });

    //============================ INSERT USER ON SELECT ==========================
    $('#leadPrioritySelect').on('change',function () {
        var userId = $(this).val();
        var username = $("#leadPrioritySelect option:selected").text();
        var leadpriorityid =$(this).attr('leadpriorityid');

        if (userId == 0) {

        } else {

            var map={
                lead_priority_id : leadpriorityid,
                user_id : userId
            }

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "${pageContext.servletContext.contextPath}/api/leadpriorityuser/insert",
                data: JSON.stringify(map),
                success: function (data) {

                    var html = $('#leadPriorityAgentBody').html();
                    html = html + ("<tr><td attrhid='"+data.id+"'>"+username+"</td><td><button type='button' class='btn btn-danger btn-sm deletUser' title='Delete User from List'  ><i class='fa fa-trash'></i></button></td></tr>");
                    $('#leadPriorityAgentBody').html(html);

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
                        url: "${pageContext.request.contextPath}/api/leadpriorityuser/delete?id=" + id,
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

//=================================== CREATE LEAD PRIORITY
    $('#createForm').submit(function (e) {
        e.preventDefault();


        var url = "";
        if($('#createBtn').html() == 'Create'){

            var url = "${pageContext.request.contextPath}/api/leadpriority/insert";
            var map = {

                name: $('#name').val(),
                desc: $('#description').val(),
                // color: $('#color').val(),
                status: $("input[name='optradionew']:checked").val()
            };
        }

        else{

            var url = "${pageContext.request.contextPath}/api/leadpriority/update";
            var map = {
                id:$('#id').val(),
                name: $('#name').val(),
                desc: $('#description').val(),
                // color: $('#color').val(),
                status: $("input[name='optradionew']:checked").val()
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
                    text: "Successfully created Campaign",
                    icon: "success",
                    button: "Ok!",
                });


            }

            else{

                swal({
                    title: "Success!",
                    text: "Successfully Updated Lead Campaign",
                    icon: "success",
                    button: "Ok!",
                });

            }
                $('#formCloseBtn').trigger('click')
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


    });
    // ============================================== TABLE ============================
    var table=  $('#table').DataTable( {
        ajax: {
            url: '${pageContext.servletContext.contextPath}/api/leadpriority/getall',
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

            { "data": "desc"},
            // {
            //     "mRender": function (data, type, row) {
            //         return "<span style='padding: 5px 25px; border-radius: 5px; background-color: "+row.color+"' ></span>";
            //
            //
            //
            //     }
            // },
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
            { "mRender": function (data, type, row) {


                return '<button type="button" class="btn btn-add btn-sm editfield" title="Update" data-toggle="modal" data-target="#addLeadPriority" ><i class="fa fa-pencil"></i></button>'
                    // '<button type="button" style="padding-left: 10px;" class="btn btn-add btn-sm addLeadAgentBtn"  title="Assign agent" data-toggle="modal" data-target="#addLeadAgent"><i class="hvr-buzz-out fa fa-user-circle-o"></i></button>';

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
       // $('#color').val("");
       $("input[name=optradionew][value='1']").prop('checked', true);
       $('#createBtn').html("Create")
   }

    $(document).on("click",".editfield",function () {
        var json=JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));
        $('#id').val(json.id);
        $('#name').val(json.name);
        $('#description').val(json.desc)
        // $('#color').val(json.color);
        $("input[name=optradionew][value=" + json.status + "]").prop('checked', true);
        $('#createBtn').html("Update")

    })

    //Refresh table
    function reloadTable(){
        table.ajax.reload();
    }




</script>
<%--============================ DATA TABLE ENDS HERE =====================================--%>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>

