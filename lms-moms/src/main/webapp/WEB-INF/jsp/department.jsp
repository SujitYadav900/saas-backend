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

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
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


                <div class="buttonexport">
                    <a href="#" class="btn btn-add" data-toggle="modal" data-target="#addDepartment"><i class="fa fa-plus"></i> Add Department</a>
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
                                    <h4>Department Details</h4>
                                </a>
                            </div>
                        </div>
                        <div class="panel-body">

                            <div class="table-responsive">
                                <div class="table-responsive card" style="padding: 20px">
                                    <table class="table table-striped table-bordered first" id="table">
                                        <thead>
                                        <tr>
                                            <th>Name</th><th>Description</th><th>Status</th><th>Action</th>
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

            <!--======================================================ADD NEW Department=================================================================== -->
            <div class="modal fade" id="addDepartment" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header modal-header-primary">
                            <button type="button" onclick="clearData()" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h3><i class="fa fa-plus m-r-5"></i> Add New Department</h3>
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
                                                <input type="text" minlength="4" maxlength="100" placeholder="Department Name"  id="name" class="form-control" required>
                                            </div>
                                            <!-- Text input-->
                                            <div class="col-md-8 form-group">
                                                <label class="control-label">Description</label>
                                                <input type="text" minlength="4" maxlength="256" placeholder="Department Description"  id="description" class="form-control" required>
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
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->

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
    $('#createForm').submit(function (e) {
        e.preventDefault();


        var url = "";
        if($('#createBtn').html() == 'Create'){

            var url = "${pageContext.request.contextPath}/api/department/insert";
            var map = {

                name: $('#name').val(),
                description: $('#description').val(),
                status: $("input[name='optradionew']:checked").val()
            };
        }

        else{

            var url = "${pageContext.request.contextPath}/api/department/update";
            var map = {
                id:$('#id').val(),
                name: $('#name').val(),
                description: $('#description').val(),
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
                    text: "Successfully created Department",
                    icon: "success",
                    button: "Ok!",
                });


            }

            else{

                swal({
                    title: "Success!",
                    text: "Successfully Updated Department",
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
            url: '${pageContext.servletContext.contextPath}/api/department/getall',
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

            { "data": "description"},
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


                return '<button type="button" class="btn btn-add btn-sm editfield" title="Update" data-toggle="modal" data-target="#addDepartment" ><i class="fa fa-pencil"></i></button>';

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
       $('#createBtn').html("Create")
   }

    $(document).on("click",".editfield",function () {
        var json=JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));
        $('#id').val(json.id);
        $('#name').val(json.name);
        $('#description').val(json.description)
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

