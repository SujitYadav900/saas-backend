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
        .whatsappdiv{
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
                <h1>Template</h1>
                <button id="addnewuser" class="btn btn-add" title="Add new template">Add Template <i class="hvr-buzz-out fa fa-plus"></i></button>
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




                                    <br>
                                    <div class="form-check">
                                        <label>Template Type</label><br>
                                        <label class="radio-inline">
                                            <input type="radio" id="checkmsg"  name="templateType" value="Message"
                                                   checked="checked">Message</label>
                                        <label class="radio-inline"><input  id="checkmail" type="radio" name="templateType" value="Mail">Mail</label><label class="radio-inline"><input  id="whatsapp" type="radio" name="templateType" value="Whatsapp">Whatsapp</label>
                                    </div>
                                    <br>
                                    <div class="form-check">
                                        <label>Variable</label><br>
                                        <select name="variable" class="form-control" id="variable"></select>
                                    </div>
                                    <div class="form-check whatsappdiv">
                                        <label>Content Type</label><br>
                                        <select name="variable" class="form-control" id="contentType"><option value="1">Text</option><option value="2">File</option><option value="3">Link</option></select>
                                    </div>
                                    <div class="form-group ">
                                        <label>Template Name</label>
                                        <input minlength="4" maxlength="100" id="templateName" type="text" name="templateName" class="form-control"
                                               placeholder="Template Name" required>
                                    </div>

                                    <div class="mailshow form-group">
                                        <label>Template Subject</label>
                                        <input id="templateSubject"  type="text" name="templateSubject" class=" form-control"
                                               placeholder="Template Subject">
                                    </div>


                                    <div class=" form-group">
                                        <label>Message<span style="color: red" id="variablespan"></span></label>
                                            <div style="height: 100px" class="editor"></div>

                                    </div>
                                    <div style="display: none" class=" form-group">
                                        <label>Variable Embeed</label>
                                        <div class="table-responsive">
                                            <table class="table table-bordered table-striped table-hover">
                                                <thead>
                                                <tr>
                                                    <th>Variable Name</th>

                                                </tr>
                                                </thead>
                                                <tbody id="variabletbody"></tbody>
                                            </table>
                                        </div>
                                    </div>





                                    <input id="id" type="hidden" name="id">
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
                                                <th>Template Id</th>
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
<link href="${pageContext.request.contextPath}/assets/plugins/quilleditor/quill.snow.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/plugins/quilleditor/quill.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/datetimepicker/jquery.datetimepicker.full.min.js"
        type="text/javascript"></script>
<!-- Dashboard js -->
<script src="${pageContext.request.contextPath}/assets/dist/js/dashboard.js" type="text/javascript"></script>
<!-- End Theme label Script
   =====================================================================-->
<script>
    var quill = new Quill('.editor', {
        theme: 'snow'
    });

    var table = $('#table').DataTable({
        ajax: {
            url: '${pageContext.servletContext.contextPath}/api/template/getalltemplate',
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

                    return "<a hidatr='" + JSON.stringify(rowJson) + "' style='display: none'></a><span>" + row.id + "</span>";


                }
            },
            {"data": "templateName"},


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
                        return "<button title='Click Here To Edit Template' type=\"button\" class=\"edituser btn btn-add btn-sm\" ><i class=\"fa fa-pencil\"></i></button> <button title='Click Here To Delete Template' type=\"button\" class=\"deletebtn btn btn-danger btn-sm \" ><i class=\"fa fa-trash\"></i></button>";
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
    $(document).on("click",".deletebtn",function () {
        var json=JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));
        var ths=$(this);
        swal({
                title: "Are you sure?",
                text: "You will not be able to recover this Template!",
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
                        url: "${pageContext.servletContext.contextPath}/api/template/delete?id=" + json.id,
                        success: function (json) {


                        },
                        error: function (err) {

                        }

                    });

                    ths.closest("tr").remove();

                }
                swal.close()

            });
    })
    $('#checkmail').click(function () {
        $('.whatsappdiv').css("display","none")
        $('.msgshow').css("display","none")
        $('.mailshow').fadeIn();
    })
    $('#checkmsg').click(function () {
        $('.whatsappdiv').css("display","none")
        $('.mailshow').css("display","none")
        $('.msgshow').fadeIn();
    })
    $('#whatsapp').click(function () {
        $('.msgshow').css("display","none")
        $('.mailshow').fadeIn();
        $('.whatsappdiv').fadeIn();

        })
    $('#preloader').css("display", "none");
    $('#addnewrow').click(function () {
        addNewRow();
    });
    function addNewRow() {
        $('#attachmenttbody').append("<tr ><td attrhid='0' style='width: 80%'><form><input name='file' type='file' class='fileuploadinput form-control'></form> </td><td style='width: 20%'><i title='Click Here To Delete This Document' class='deletefile hvr-buzz-out fa fa-trash'></i></td></tr>")

    }

    $(document).on("change", ".fileuploadinput", function () {
        var ths = $(this);
        var form = $(this).closest("form")[0];

        var data = new FormData(form);
        data.append("CustomField", "This is some extra data, testing");
        $('#preloader').css("display", "block");
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

                $('#preloader').fadeOut();
                ths.closest("tr").html("<td class='downloadfilecls' attrhid='" + data.id + "' title='" + data.createAt + "'>" + data.name + "</td><td><i title='Click Here To Delete This Document' class='deletefile hvr-buzz-out fa fa-trash'></i></td>")

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

    $(document).on("click",".edituser",function () {

        var json=JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));
        json.template = atob(json.template);
        json.template = decodeURI(json.template);


        $('#addnewuser').trigger("click")


        quill.root.innerHTML=json.template
        $('#templateName').val(json.templateName)
        $('#templateSubject').val(json.templateSubject)
        $('#id').val(json.id)
        $("input[name=templateType][value=" + json.templateType + "]").prop('checked', true);
        if(json.templateType=="Mail")
        {

            $('.mailshow').css("display","block")
        }
        else
        {
            $('.mailshow').css("display","none")
        }

        for(var i=0;i<$('#variable').find("option").length;i++)
        {
            var option=$('#variable').find("option").eq(i).text().trim();
            if(option==json.variable)
            {
                $('#variable').val(option);
                var json=JSON.parse($('#variable').find("option").eq(i).attr("atrhid"));
                var html=""
                var tempjson=json.variable;
                var viewhtml="<sup>"
                // html=html+"<tr><td>@childName</td></tr>"
                // html=html+"<tr><td>@parentName</td></tr>"
                for(var i=0;i<tempjson.length;i++)
                {
                    html=html+"<tr><td>@"+tempjson[i].name+"</td></tr>"
                    viewhtml=viewhtml+"  @"+tempjson[i].name;
                }
                viewhtml=viewhtml+"</sup>"

                $('#variablespan').html(viewhtml)
                $('#variabletbody').html(html)
            }
        }

        $('#createbtn').html("Update")

    })


    $(document).on("click", ".deletereportto", function () {

        $(this).closest("tr").remove();


    });
    $(document).on("click", ".deletefile", function () {
        var hidid = $(this).closest("tr").find("td").eq(0).attr("attrhid");
        var ths = $(this);
        if (hidid == 0) {
            $(this).closest("tr").remove();

        } else {
            swal({
                    title: "Are you sure?",
                    text: "You will not be able to recover this imaginary file!",
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
                            url: "${pageContext.servletContext.contextPath}/api/file/delete?id=" + hidid,
                            success: function (json) {


                            },
                            error: function (err) {

                            }

                        });

                        ths.closest("tr").remove();

                    }
                    swal.close()

                });

        }
    });
    $(document).on("change", ".departmentreportto", function () {
        var dep = $(this).val();
        var ths = $(this);
        if (dep == 0) {

        } else {
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "${pageContext.servletContext.contextPath}/api/user/getuserbydepartment?department=" + dep,
                success: function (json) {
                    var html = "<option value='0'>---Select All---</option>";
                    for (var i = 0; i < json.length; i++) {
                        html = html + "<option value='" + json[i].id + "'>" + json[i].username + "</option>"

                    }
                    ths.closest("tr").find("td").eq(1).find("select").html(html)


                },
                error: function (err) {

                }

            })
        }
    });

    function clearData() {

        $('#checkmsg').trigger("click")
        $('#templateName').val("")
        $('#variable').val(0)
        $('#templateSubject').val("")
        quill.root.innerHTML="";
        $('#id').val(0);
        $('#createBtn').html("Create");



    }

    $('#createnewuserform').submit(function (e) {
        e.preventDefault();
        var text =    quill.getText();
        //replace all special characteds except @ so that we have clean param array in Template dao
        let tempText =  text.replace(/[&\/\\#,+()$~%.'":*?<>{}]/g,' ');
        var arr=tempText.match(/@\S+/g);//create array of words start with @
        var hasUrl=false;
        var params=[];

        // //no variable selected
        // if(arr == null){
        //     swal({
        //         title: 'Error',
        //         text: 'Please choose at least one variable',
        //         type: 'error',
        //         confirmButtonText: 'Ok'
        //     })
        // }

        if(arr != null){
        for(var i=0;i<arr.length;i++)
        {
            var find=arr[i].trim();
            if(find=="@url")
            {
                hasUrl=true;
            }
            params.push(find.replace("@",""))

        }
        }

        var form = $(this).serializeArray();
        //$('#preloader').css("display", "block");
        var map = {};



        map["hasUrl"]=hasUrl;


        var innermap={};
        var innerarr=[];
        innermap["status"]="Sent"
        innermap["count"]="0"
        innerarr.push(innermap);
        if(hasUrl==true) {
            innermap["status"] = "Clicked"
            innermap["count"] = "0"

            innerarr.push(innermap)
        }
        map["templateDetails"]=innerarr;

        var url = "";
        var msg = "You Want To Create A New Template ";
        if ($('#createbtn').html().trim() == "Create") {
            url = "${pageContext.servletContext.contextPath}/api/template/insert";
            map["templateType"]=$("input[name='templateType']:checked").val();
            map["variable"]=$("#variable").val();
            map["templateName"]=$("#templateName").val();
            map["templateSubject"]=$("#templateSubject").val()
            map["templateSubject"]=$("#templateSubject").val()
            map["contentType"]=$('#contentType').val()
            map["templateIdHidden"] = $("#templateName").val();
        } else {
            msg = "You Want To Update Template ";
            url = "${pageContext.servletContext.contextPath}/api/template/update";
            map["templateType"]=$("input[name='templateType']:checked").val();
            map["variable"]=$("#variable").val();
            map["templateName"]=$("#templateName").val();
            map["templateSubject"]=$("#templateSubject").val()
            map["templateSubject"]=$("#templateSubject").val()
            map["id"]=$("#id").val()
            map["contentType"]=$('#contentType').val()
            map["templateIdHidden"] = $("#templateName").val();
        }


        map["params"]=params;


        if(map["templateType"]=="Mail")
        {
            map["template"]=quill.root.innerHTML
            //map["template"]=JSON.stringify(quill.root.innerHTML);
        }
        else{
            map["template"]=quill.getText().trim();
            //map["template"]=JSON.stringify(quill.getText().trim());
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
                                    text: "Successfully Created Template",
                                    icon: "success",
                                    button: "Yes",
                                });
                            } else {
                                swal({
                                    title: "Success!",
                                    text: "Successfully Updated Template",
                                    icon: "success",
                                    button: "Yes!",
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


    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "${pageContext.servletContext.contextPath}/json/templatetype.json",
        success: function (json) {
            var html = "<option atrhid='0' value='0'>---Select Source---</option>";

            for (var i = 0; i < json.length; i++) {
                console.log("json> ",json[i])
                html = html + "<option atrhid='"+ JSON.stringify(json[i])+"'  value='" + json[i].templateType + "'>" + json[i].templateType + "</option>"

            }


            $('#variable').html(html)


        },
        error: function (err) {

        }

    })
    $('#variable').change(function () {


        var json=$(this).find('option:selected').attr("atrhid");


        if(json==0)
        {
            return;
        }

        json=JSON.parse(json);
        var html=""
        var tempjson=json.variable;
        var viewhtml="<sup>"
        for(var i=0;i<tempjson.length;i++)
        {
            html=html+"<tr><td>@"+tempjson[i].name+"</td></tr>"
            viewhtml=viewhtml+"  @"+tempjson[i].name;
        }
        viewhtml=viewhtml+"</sup>"
        $('#variablespan').html(viewhtml)
        $('#variabletbody').html(html)


    })

    $('.messagetemplate',function () {

    })

</script>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>

