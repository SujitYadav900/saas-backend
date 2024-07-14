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
                <i class="fa fa-cloud-upload"></i>
            </div>

            <div class="buttonexport">
                <a href="#" class="btn btn-add" data-toggle="modal" title="Click to upload documents"
                   data-target="#uploadDocument"><i class="fa fa-plus"></i> Upload Documents</a>
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
                                    <h4>Document Details</h4>
                                </a>
                            </div>
                        </div>
                        <div class="panel-body">

                            <div class="table-responsive">
                                <div class="table-responsive card" style="padding: 20px">
                                    <table class="table table-striped table-bordered first" id="table">
                                        <thead>
                                        <tr>
                                            <th>File Name</th>
                                            <th>Created By</th>
                                            <th>Created At</th>
                                            <th>Get Shareable link</th>
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
            <div class="modal fade" id="uploadDocument" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header modal-header-primary">
                            <button type="button" class="close" onclick="clearData()" data-dismiss="modal"
                                    aria-hidden="true">×
                            </button>
                            <h3 ><i class="fa fa-plus m-r-5"></i>Upload New Document</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <form class="form-horizontal" id="uploadForm">
                                        <fieldset>
                                            <div class="col-md-6 form-group">
                                                <label class="control-label">Enter File</label>
                                                <input type="file" name="file" id="fileinput" placeholder="Enter File"
                                                       class="form-control" required>
                                            </div>
                                            <!-- Text input-->

                                            <input type="hidden" placeholder="File ID" id="fileid" class="form-control">
                                            <div class="col-md-12 form-group user-form-group">
                                                <div class="pull-left">
                                                    <button type="submit" class="btn btn-add btn-sm" id="fileUploadBtn">
                                                        Upload
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
clearData();
function clearData(){
$("#fileinput").val("");
}

    //=======================================UPLOAD FILE=============================================
    $("#uploadForm").on("submit", function (e) {

        e.preventDefault();

        let demoUserRole = "<%=UtilityClass.propertyService.findProperty("CRMDemo","demoUserRole")%>"
        let currentUserRole = "<%=UtilityClass.getCurrentUser().getDepartment()%>"
        if(currentUserRole == demoUserRole){

            swal({
                title: 'Feature Not Available',
                text: "Sorry! This feature is not available with demo account!",
                type: 'warning',
                confirmButtonText: 'Ok'
            })

            return;
        }
        var ths = $(this);
        var form = ths[0];
        var data = new FormData(form);

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "${pageContext.request.contextPath}/api/file/insertfile",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {

                swal({
                    title: "Success!",
                    text: "Successfully uploaded file",
                    icon: "success",
                    button: "Ok!",
                });
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
            url: '${pageContext.servletContext.contextPath}/api/file/getfilebycreateby',
            dataSrc: '',
            "data": {
                "flag": 1,
            }
        },
        "columns": [
            {
                "mRender": function (data, type, row) {
                    return "<a hidatr='" + JSON.stringify(row) + "' style='display: none'></a><span>"+row.name+"</span>";
                }
            },

            {"data": "createBy"},
            {"data": "createAt"},
            {
                "mRender": function (data, type, row) {
                    var html = "<i style=' margin-left: 5px; font-size: 25px;color: deepskyblue' title='Click to copy shareable link' class=\"hvr-buzz-out fa fa-external-link-square copylink\"></i>";
                    return html;
                }
            },
            {
                "mRender": function (data, type, row) {
                    return '<button type="button" title="Delete this document" class="btn btn-danger btn-sm deleteFile" ><i class="fa fa-trash-o"></i> </button>';
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

    // DELETE File
    $(document).on("click", ".deleteFile", function () {
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
                        url: "${pageContext.request.contextPath}/api/file/delete?id=" + json.id,
                        type: 'DELETE',
                        success: function (result) {
                            swal("File deleted!", {
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

    $(document).on("click",".copylink",function () {
        var json = JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));
        var text = '<%=UtilityClass.propertyService.findProperty("Application","applicationUrl")%>api/file/downloadfile?id='+json.id;
        var copyFrom = $('<textarea/>');
        copyFrom.css({
            position: "absolute",
            left: "-1000px",
            top: "-1000px",
        });
        copyFrom.text(text);
        $('body').append(copyFrom);
        copyFrom.select();
        document.execCommand('copy');

        swal({
            title: 'Copied',
            text: 'Link Copied To The Clipboard',
            icon: 'success',
            timer: 1000,
            buttons: false,
        })
    })

</script>

<script>

    var chatFrameCss= '<style> .chat-frame {\n' +
        '        /* border-radius: 50px; */\n' +
        '        position: fixed;\n' +
        '        right: 90px;\n' +
        '        bottom: 90px;\n' +
        '      \n' +
        '       \n' +
        '        text-align: center;\n' +
        '        height: 400px;\n' +
        '        width: 380px; z-index: 100; background:none transparent;\n' +
        '      } </style>'

    //$("head").append(chatFrameCss)

    //$("body").append(' <iframe class="chat-frame " allowtransparency="true" frameborder="0" src="http://127.0.0.1:5500/ChatUI1-edge-grey/test/test.html"></iframe>')
</script>

<%--============================ DATA TABLE ENDS HERE =====================================--%>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>