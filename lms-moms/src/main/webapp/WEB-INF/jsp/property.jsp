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
    <!-- Lobipanel css -->
    <link href="${pageContext.request.contextPath}/assets/plugins/lobipanel/lobipanel.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- Pe-icon -->
    <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/helper.css" rel="stylesheet"
          type="text/css"/>

    <!-- Themify icons -->
    <link href="${pageContext.request.contextPath}/assets/themify-icons/themify-icons.css" rel="stylesheet"
          type="text/css"/>
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
                <i class="fa fa-gear"></i>
            </div>

            <div class="header-title">
                <h1>Property Type</h1>
                <a href="#" class="btn btn-add" onclick="clearData()" data-toggle="modal"
                   data-target="#addproperty" title="Add new property type"><i class="fa fa-plus"></i> Add Property Type</a>
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
                                    <h4>Property Details</h4>
                                </a>
                            </div>
                        </div>
                        <div class="panel-body">

                            <div class="table-responsive">
                                <div class="table-responsive card" style="padding: 20px">
                                    <table class="table table-striped table-bordered first" id="table">
                                        <thead>
                                        <tr>
                                            <th>Property Name</th>
                                            <th>Action</th>
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

            <!--======================================================ADD NEW Document=================================================================== -->
            <div class="modal fade" id="addproperty" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header modal-header-primary">
                            <button type="button" class="close" onclick="clearData()" data-dismiss="modal"
                                    aria-hidden="true">×
                            </button>
                            <h3><i class="fa fa-plus m-r-5"></i>Add New Property Type</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <form class="form-horizontal" id="propertyForm">
                                        <fieldset>
                                            <div class="col-md-6 form-group">
                                                <label class="control-label">Property Type Name</label>
                                                <input type="text" id="type" placeholder="Enter Property Type Name" required
                                                       class="form-control">
                                            </div>
                                            <!-- Text input-->

                                            <input type="hidden" id="id">
                                            <div class="col-md-12 form-group user-form-group">
                                                <div class="pull-left">
                                                    <button type="submit" class="btn btn-add btn-sm" id="createBtn">
                                                        Create
                                                    </button>
                                                </div>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger pull-right" id="formCloseBtn"
                                    data-dismiss="modal">Close
                            </button>
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
<!-- CRMadmin frame -->
<script src="${pageContext.request.contextPath}/assets/dist/js/custom.js" type="text/javascript"></script>
<!-- Dashboard js -->
<script src="${pageContext.request.contextPath}/assets/dist/js/dashboard.js" type="text/javascript"></script>
<!-- End Theme label Script=====================================================================-->

<!-- =============================DATA TABLES======================================================-->
<script src="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.min.js"></script>
<!-- ==============FORM SUBMISSION SCRIPT | CREATING A NEW ROLE=================== -->
<script>
clearData();
function clearData(){
$("#fileinput").val("");
}

    //=======================================UPLOAD FILE=============================================
    $("#propertyForm").on("submit", function (e) {

        e.preventDefault();
        var url = ""

        if($("#createBtn").html() == "Create"){
            var map={
                type : $("#type").val()
            }
            url = "${pageContext.request.contextPath}/api/property/insert"
        }
        else{

            var map={
                id : $("#id").val(),
                type : $("#type").val()
            }
            url = "${pageContext.request.contextPath}/api/property/update"
        }

        $.ajax({
            type: "POST",
            contentType: 'application/json',
            url: url,
            data: JSON.stringify(map),
            success: function (data) {

               if($("#createBtn").html() == "Create"){
                   swal({
                       title: "Success!",
                       text: "Successfully Created Property",
                       icon: "success",
                       button: "Ok!",
                   });
               }
               else{
                   swal({
                       title: "Success!",
                       text: "Successfully Updated Property",
                       icon: "success",
                       button: "Ok!",
                   });
               }
                $('#formCloseBtn').trigger('click');
                clearData();
                reloadTable();
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
    });

    // ============================================== TABLE ============================
    var table = $('#table').DataTable({
        ajax: {
            url: '${pageContext.servletContext.contextPath}/api/property/getall',
            dataSrc: '',
            "data": {
                "flag": 1,
            }
        },
        "columns": [
            {
                "mRender": function (data, type, row) {
                    return "<a hidatr='" + JSON.stringify(row) + "' style='display: none'></a><span>"+row.type+"</span>";
                }
            },

            {
                "mRender": function (data, type, row) {
                    return '<button type="button" title="Edit this Property Type" class="btn btn-add btn-sm editBtn" data-toggle="modal" data-target="#addproperty"><i class="fa fa-pencil"></i> </button> ';
                }
            },
        ]
        , "initComplete": function (settings, json) {
            $('#tablediv').fadeIn();
            $('#loading').fadeOut();
        },
        "ordering": false
    });

    //Refresh table
    function reloadTable() {
        table.ajax.reload();
    }
    //      EDIT PROPERTY

    $(document).on("click",".editBtn", function(){
        var json = JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));
        $("#id").val(json.id);
        $("#type").val(json.type);
        $("#createBtn").html("Update")


    });

    //clear form
    function clearData(){
        $("#id").val("");
        $("#type").val("");
        $("#createBtn").html("Create")
    }


    $(document).on("click",".editBtn",function () {
        var json = JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));

    })

</script>
<%--============================ DATA TABLE ENDS HERE =====================================--%>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>