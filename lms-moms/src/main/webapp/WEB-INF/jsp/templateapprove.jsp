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
    <!-- Pace css -->
    <link href="${pageContext.request.contextPath}/assets/plugins/pace/flash.css" rel="stylesheet" type="text/css"/>
    <!-- Pe-icon -->
    <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/helper.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.css" rel="stylesheet"
          type="text/css"/>
    <!-- Themify icons -->
    <link href="${pageContext.request.contextPath}/assets/themify-icons/themify-icons.css" rel="stylesheet"
          type="text/css"/>
    <!-- Emojionearea -->
    <link href="${pageContext.request.contextPath}/assets/plugins/emojionearea/emojionearea.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- Monthly css -->
    <link href="${pageContext.request.contextPath}/assets/plugins/monthly/monthly.css" rel="stylesheet"
          type="text/css"/>
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath}/assets/dist/css/stylecrm.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/plugins/datetimepicker/jquery.datetimepicker.min.css" rel="stylesheet"   type="text/css"/>
    <style>
        .validationFail {
            background-color: #ff00005e !important;
        }
        .downloadfilecls{
            cursor: pointer;
        }
        .mailshow{
            display: none;
        }
        .customclass{
            display: none;
        }
    </style>
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
                <i class="fa fa-file-text"></i>
            </div>
            <div class="header-title">
                <h1>Template Approval</h1>
                   </div>
        </section>
        <!-- Main content -->
        <div class="content">
            <div class="row">
                <div class="col-sm-12 col-md-12">
                    <div class="panel panel-bd lobidrag">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Template Details</h4>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div style="display: none" id="addnewdiv">

                                <form id="createnewuserform" method="post" class="col-sm-12">
                                <input type="hidden" id="id">
                                    <input type="hidden" id="templateType">
                                    <br>
                                    <div class="form-check">
                                        <label>Template Status</label><br>
                                        <label class="radio-inline">
                                            <input type="radio"  name="templatests" value="true" >Approved</label>
                                        <label class="radio-inline"><input type="radio" name="templatests" value="false">Not Apporved</label>
                                    </div>
                                    <br>
                                    <div class="form-check maildiv" >
                                        <label>Template TemplateSubject</label>
                                        <br>
                                        <label id="templatesubject" class="radio-inline">
                                        </label>

                                    </div>

                                    <br>
                                    <div class="form-check">
                                        <label>Template Message</label>
                                        <br>
                                        <label id="templatemessage" class="radio-inline" style="overflow: scroll;max-height: 200px;">
                                           </label>

                                    </div>
                                    <br>
                                    <div class="messagediv form-group">
                                        <label>Template Hidden Id</label>
                                        <input id="templateidhiddent"  type="text" name="templateSubject" class=" form-control"
                                               placeholder="Template Hidden Id">
                                    </div>    <br>
                                    <div class="reset-button">
                                        <button id="createbtn" type="button" class="btn btn-success">Update</button>
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

                                                <th>Template Name</th>
                                                <th>Template Type</th>
                                                <th>Template Subject</th>
                                                <th>Approved</th>
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
                        <div class="panel-footer">

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
<!-- ./wrapper -->
<!-- Start Core Plugins
   =====================================================================-->
<!-- jQuery -->

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
<script src="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.min.js"
        type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/assets/plugins/datetimepicker/jquery.datetimepicker.full.min.js"
        type="text/javascript"></script>
<!-- Dashboard js -->
<script src="${pageContext.request.contextPath}/assets/dist/js/dashboard.js" type="text/javascript"></script>
<!-- End Theme label Script
   =====================================================================-->
<script>


    var table = $('#table').DataTable({
        ajax: {
            url: '${pageContext.servletContext.contextPath}/api/template/getalltemplateapproval',
            dataSrc: '',
            "data": {
                "flag": 1,

            }
        },
        "columns": [

            {
                "mRender": function (data, type, row) {
                    let rowJson = row;
                    rowJson.template = encodeURI(rowJson.template);
                    rowJson.template = btoa(rowJson.template);


                    return "<a hidatr='" +JSON.stringify(rowJson) + "' style='display: none'></a><span>" + row.templateName + "</span>";


                }
            },

            {"data": "templateType"},

            {"data": "templateSubject"},

            {
                "mRender": function (data, type, row) {
                    if(row.approved)
                    {
                        return "<i title='This template has been approved' style='color: green' class='fa fa-check'></i>";
                    }
                    else{
                        return "<i title='This template Has yet to be approved' style='color: red' class='fa fa-hand-stop-o'></i>";
                    }


                }
            },


            {
                "mRender": function (data, type, row) {
                    if(row.templateStatus==0)
                    {
                        return " <button title='Click Here To Approve Or Reject Template' type=\"button\" class=\"edituser btn btn-danger btn-sm \" ><i class=\"fa fa-unlock\"></i></button>";
                    }


                }
            }

        ]
        , "initComplete": function (settings, json) {
            $('#tablediv').fadeIn();
            $('#loading').fadeOut();
        },
        "ordering": false
    });
    jQuery('#schduleOn').datetimepicker({
        format:'Y/m/d H:i',
        lang:'ru'
    });
    $('#addnewuser').click(function () {

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

        var json=JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));
        json.template = atob(json.template);
        json.template = decodeURI(json.template);

        clearData()
       $('#addnewdiv').fadeIn();
       $('#tablediv').css("display","none")
        if(json.templateType=="Mail")
        {
            $('.messagediv').css("display","none")
            $('.maildiv').css("display","block")

        }else{
            $('.maildiv').css("display","none")
            $('.messagediv').css("display","block")
        }

        $("input[name=templatests][value=" + json.approved + "]").prop('checked', true);
        $('#templateType').val(json.templateType)
        $('#templatemessage').html(json.template)
        $('#templatesubject').html(json.templateSubject)
        $('#id').val(json.id)




    })



    $('#createbtn').click(function () {

        var sts=$("input[name='templatests']:checked").val();
        if(sts=="true")
        {
            sts=true;
        }else{
            sts=false;
        }
        var message="You want to Approve Template?"
        if(!sts)
        {
            message="You want to Reject Template?"
        }
        var type=$('#templateType').val();
        var id=$('#id').val();
        var value=$('#templateidhiddent').val();
        var url="${pageContext.servletContext.contextPath}/api/template/approveorejecttemplate?id="+id+"&value="+value+"&type="+type+"&sts="+sts;
        swal({
                title: "Are you sure?",
                text: message,
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
                        type: "POST",

                        contentType: "application/json",
                        url: url,
                        success: function (data) {



                            table.ajax.reload();
                            $('#addnewdiv').css("display","none")
                            $('#tablediv').fadeIn();

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

                }
                swal.close()

            });


    })

    function clearData() {

     $('#templatesubject').html("")
        $('#templatemessage').html("");
        $('#templateidhiddent').val("");
    }






</script>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>

