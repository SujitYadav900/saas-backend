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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.css"/>
    <script src="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.js"></script>
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
                <h1>User</h1>
                <button id="addnewuser" class="btn btn-add">Add User <i class="hvr-buzz-out fa fa-plus"></i></button>
            </div>
        </section>
        <!-- Main content -->
        <div class="content">
            <div class="row">
                <div class="col-sm-12 col-md-12">
                    <div class="panel panel-bd lobidrag">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>This is page content</h4>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div style="display: none" id="addnewdiv">

                                <form id="createnewuserform" method="post" class="col-sm-12">

                                    <div class="form-check">
                                        <label>Status</label><br>
                                        <label class="radio-inline">
                                            <input type="radio" id="checkactive"  name="status" value="1"
                                                   checked="checked">Active</label>
                                        <label class="radio-inline"><input  id="checkinactive" type="radio" name="status" value="0">Inctive</label>
                                    </div>
                                    <br>



                                    <div class="form-group">
                                        <label>Template Type</label>
                                        <input id="templatetype" type="text" name="templateType" class="form-control"
                                               placeholder="Template Type" required>
                                    </div>



                                    <div class=" form-group">
                                        <label>Variable</label>
                                        <div class="table-responsive">
                                            <table class="table table-bordered table-striped table-hover">
                                                <thead>
                                                <tr>
                                                    <th>Variable</th>
                                                    <th></th>
                                                </tr>
                                                </thead>
                                                <tbody id="variabletbody"></tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class=" form-group">
                                        <label>Source</label>
                                        <div class="table-responsive">
                                            <table class="table table-bordered table-striped table-hover">
                                                <thead>
                                                <tr>
                                                    <th>Sources</th>
                                                    <th></th>
                                                </tr>
                                                </thead>
                                                <tbody id="sourcetbody"></tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <input type="hidden" name="id">
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

                                                <th>Name</th>
                                                <th>Info</th>
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
                            This is standard panel footer
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs"><b>Version</b> 1.0</div>
        <strong>Copyright &copy; 2016-2017 <a href="#">thememinister</a>.</strong> All rights reserved.
    </footer>
</div>
<!-- ./wrapper -->
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
<script src="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.min.js"
        type="text/javascript"></script>
<!-- Dashboard js -->
<script src="${pageContext.request.contextPath}/assets/dist/js/dashboard.js" type="text/javascript"></script>
<!-- End Theme label Script
   =====================================================================-->
<script>

    var table = $('#table').DataTable({
        ajax: {
            url: '${pageContext.servletContext.contextPath}/api/templatetype/getall',
            dataSrc: '',
            "data": {
                "flag": 1,

            }
        },
        "columns": [

            {
                "mRender": function (data, type, row) {


                    return "<a hidatr='" + JSON.stringify(row) + "' style='display: none'></a><span>"+row.templateType+"</span>";


                }
            },


            {
                "mRender": function (data, type, row) {
                    var html = "";

                    if (row.status == "1") {
                        html = html + "<i style='    margin-left: 5px;  font-size: 18px;color: greenyellow'  title='Active' class=\"hvr-buzz-out fa fa-check\"></i>";
                    } else {

                        html = html + "<i style='    margin-left: 5px;  font-size: 18px;color: red' title='Not Active' class=\"hvr-buzz-out fa fa-ban\"></i>";
                    }

                    return html;
                }
            },

            {
                "mRender": function (data, type, row) {

                    return "<button title='Click Here To Edit User' type=\"button\" class=\"edituser btn btn-add btn-sm\" ><i class=\"fa fa-pencil\"></i>";
                }
            }

        ]
        , "initComplete": function (settings, json) {
            $('#tablediv').fadeIn();
            $('#loading').fadeOut();
        },
        "ordering": false
    });
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





    function populate(frm, data) {
        $.each(data, function(key, value){
            $('[name='+key+']', frm).val(value);
        });
    }
    $(document).on("click",".edituser",function () {
        $('#addnewuser').trigger("click")
        var json=JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));
        $('#templatetype').val(json.templateType)

        if(json.status=="1")
        {
            $('#checkactive').trigger("click")
        }
        else{
            $('#checkactive').trigger("click")
        }



        var variablelist=json.variable;
        var html=""
        for(var i=0;i<variablelist.length;i++)
        {
            var checkbox="<input type='checkbox' checked='false'>"
            if(variablelist[i].status==1)
            {
                checkbox="<input type='checkbox' checked='true'>"
            }
            html=html+"<tr><td>"+variablelist[i].name+"</td><td>"+checkbox+"</td></tr>"
        }
        $('#variabletbody').html(html)

        var sourcelist=json.source;
        var html=""
        for(var i=0;i<sourcelist.length;i++)
        {
            var checkbox="<input type='checkbox' checked='false'>"
            if(sourcelist[i].status==1)
            {
                checkbox="<input type='checkbox' checked='true'>"
            }
            html=html+"<tr><td>"+sourcelist[i].name+"</td><td>"+checkbox+"</td></tr>"
        }
        $('#sourcetbody').html(html)

        $('#createbtn').html("Update")

    })





    function clearData() {

        $('#createnewuserform').find("input[type=text], input[type=number], input[type=hidden],input[type=email] , textarea").val("");
        $('#attachmenttbody').html("");
        $('#urltbody').html("");
        $('#department').val(0);
        $('#checkmale').trigger("click")
        $('#checkactive').trigger("click")
        $('#twosteptrue').trigger("click")
        $('#mailtrue').trigger("click")



    }

    $('#createnewuserform').submit(function (e) {
        e.preventDefault();
        var form = $(this).serializeArray();
        $('#preloader').css("display", "block");
        var map = {};
        var arrvariable=[];
        var arrsource=[];
        for(var i=0;i<$('#sourcetbody').find("tr").length;i++)
        {
            var innermap={};
            innermap["name"]=$('#sourcetbody').find("tr").eq(i).find("td").eq(0).html();
            innermap["status"]=0;
            if($('#sourcetbody').find("tr").eq(i).find("td").eq(1).find("input").prop("checked"))
            {
                innermap["status"]=1;
            }


            arrsource.push(innermap);

        }
        for(var i=0;i<$('#variabletbody').find("tr").length;i++)
        {
            var innermap={};
            innermap["name"]=$('#variabletbody').find("tr").eq(i).find("td").eq(0).html();
            innermap["status"]=0;
            if($('#variabletbody').find("tr").eq(i).find("td").eq(1).find("input").prop("checked"))
            {
                innermap["status"]=1;
            }


            arrvariable.push(innermap);

        }
        map["variable"]=arrvariable;
        map["source"]=arrsource;





        var url = "";
        var msg = "You Want To Create A Template Type?";
        if ($('#createbtn').html().trim() == "Create") {

        } else {
            msg = "You Want To Update Template Type?";
            url = "${pageContext.servletContext.contextPath}/api/templatetype/update";
        }
        for (var i = 0; i < form.length; i++) {
            map[form[i].name] = form[i].value

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

                    $.ajax({
                        type: "POST",
                        data: JSON.stringify(map),
                        contentType: "application/json",
                        url: url,
                        success: function (data) {
                            if ($('#createbtn').html().trim() == "Create") {
                                swal({
                                    title: "Success!",
                                    text: "Successfully Created User",
                                    icon: "success",
                                    button: "Aww yiss!",
                                });
                            } else {
                                swal({
                                    title: "Success!",
                                    text: "Successfully Updated Template",
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

    });
    $('#addnewrowvariable').click(function () {
        addNewRow("Variable","#variabletbody")
    })
    $('#addnewrowsource').click(function () {
        addNewRow("Source","#sourcetbody")
    })



    function addNewRow(value,id) {
        $(id).append("<tr><td style='width: 80%'><input placeholder='Enter "+value+"' type='text' class='form-control'> </td><td style='width: 20%'><input  type='checkbox'></td></tr>")

    }
    $(document).on("click",".deletefile",function () {
        $(this).closest("tr").remove();
    })


</script>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>

