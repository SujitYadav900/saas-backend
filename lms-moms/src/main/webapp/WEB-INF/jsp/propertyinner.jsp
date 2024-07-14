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
                <i class="fa fa-gears"></i>
            </div>


            <div class="header-title">
                <h1 id="pageHeading"></h1>
                <a href="#" class="btn btn-add" onclick="clearData()" data-toggle="modal"
                   data-target="#addpropertyinner" title="Add new inner property" id="addBtn"></a>
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
                                    <h4 id="tableHeading"></h4>
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
                                            <th >Property Value</th>
                                            <th>Property Type</th>
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
            <div class="modal fade" id="addpropertyinner" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header modal-header-primary">
                            <button type="button" class="close" onclick="clearData()" data-dismiss="modal"
                                    aria-hidden="true">×
                            </button>
                            <h3 id="formHeading"></h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <form class="form-horizontal" id="propertyinnerForm">
                                        <fieldset>
                                            <div class="col-md-6 form-group">
                                                <label class="control-label">Name</label>
                                                <input type="text" id="name" placeholder="Enter Property Name" required
                                                       class="form-control">
                                            </div>

                                            <div class="col-md-6 form-group">
                                                <label class="control-label">Value</label>
                                                <input type="text" id="value" placeholder="Enter Property Value" required
                                                       class="form-control">
                                            </div>

                                            <div class="col-md-6 form-group">
                                                <label class="control-label">Type</label>
                                                <select id="propertyid" required class="form-control">

                                                </select>
                                            </div>

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
// function clearData(){
// $("#fileinput").val("");
// }
var searchParams = new URLSearchParams(window.location.search);

var value = searchParams.get("property");
$("#pageHeading").html(value+" Property");
$("#tableHeading").html(value+" Property Details")
$("#formHeading").html("<i class='fa fa-plus m-r-5'></i> New "+value+" Property")

    //=======================================UPLOAD FILE=============================================
    $("#propertyinnerForm").on("submit", function (e) {

        e.preventDefault();
        var url = ""

        if($("#createBtn").html() == "Create"){

            var map={
                name : $("#name").val(),
                value : $("#value").val(),
                propertyId : $("#propertyid").val()
            }
            url = "${pageContext.request.contextPath}/api/propertyinner/insert"
        }
        else{

            var map={
                id : $("#id").val(),
                name : $("#name").val(),
                value : $("#value").val(),
                propertyId : $("#propertyid").val()
            }
            url = "${pageContext.request.contextPath}/api/propertyinner/update"
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
                       text: "Successfully Created "+value+" Property",
                       icon: "success",
                       button: "Ok!",
                   });
               }
               else{
                   swal({
                       title: "Success!",
                       text: "Successfully Updated "+value+" Property",
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

$("#addBtn").html("<i class='fa fa-plus' ></i> Add "+value+" Property")

    var table = $('#table').DataTable({
        ajax: {
            url: '${pageContext.servletContext.contextPath}/api/propertyinner/getall?property='+searchParams.get("property"),
            dataSrc: '',
            "data": {
                "flag": 1,
            },

        },
        
        "columns": [

            {

                "mRender": function (data, type, row) {
                    return "<a hidatr='" + JSON.stringify(row) + "' style='display: none'></a><span>"+row.name+"</span>";
                }
            },
            { "mRender": function (data, type, row) {
                    return '<span style="word-break:break-all;">'+row.value+' </span> ' ;
                }},
            {"data" : "propertyType"},
            {
                "mRender": function (data, type, row) {
                    return '<button type="button" title="Edit this Property" class="btn btn-add btn-sm editBtn" data-toggle="modal" data-target="#addpropertyinner"><i class="fa fa-pencil"></i> </button> ' ;
                }
            },
        ]
        , "initComplete": function (settings, json) {
            $('#tablediv').fadeIn();
            $('#loading').fadeOut();
        },
        "ordering": false,


    });

    //Refresh table
    function reloadTable() {
        table.ajax.reload();
    }
    //      EDIT PROPERTY

    $(document).on("click",".editBtn", function(){

        var json = JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));
        $("#id").val(json.id);
        $("#name").val(json.name);
        $("#value").val(json.value);
        $("#propertyid").val(json.propertyId);
        $("#createBtn").html("Update")
        $("#propertyid").prop("disabled",false);


    });

    //clear form
    function clearData(){
        $("#id").val("");
        $("#name").val("");
        $("#value").val("");
        $("#propertyid").prop("disabled","true");
        $("#createBtn").html("Create")
    }

    // DELETE Property
    $(document).on("click", ".deleteBtn", function () {
        var json = JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));

        swal({
            title: "Are you sure?",
            text: "Delete " + json.name + " ?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/api/propertyinner/delete?id=" + json.id,
                        type: 'DELETE',
                        success: function (result) {
                            swal("Property deleted!", {
                                icon: "success",
                            }),
                                reloadTable();
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

    // $(document).on("click",".editBtn",function () {
    //
    //     var json = JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));
    //
    // })

    // LOAD PROPERTY
    loadproperty();
    function loadproperty(){
        var url = '${pageContext.servletContext.contextPath}/api/property/getall';
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (json) {
                var html = "<option value=''>---Select Type---</option>";
                for (var i = 0; i < json.length; i++) {
                    if(json[i].type == value){
                        html = html + "<option value='" + json[i].id + "' selected>" + json[i].type + "</option>"
                    }
                    else{
                        html = html + "<option value='" + json[i].id + "'>" + json[i].type + "</option>"
                    }

                }
                $("#propertyid").html(html)

            },
            error: function (err) {

            }

        })
    }

</script>
<%--============================ DATA TABLE ENDS HERE =====================================--%>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>