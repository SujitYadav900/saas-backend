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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.css"/>
    <script src="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.js"></script>


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
                <i class="fa fa-check-square-o"></i>
            </div>

            <div class="header-title" >
                <h1>Lead Stage</h1>

            <div class="buttonexport">
                <a onclick="  clearData();" href="#" title="Add new lead stage" class="btn btn-add" data-toggle="modal"
                   data-target="#uploadDocument"><i class="fa fa-plus"></i>Add New Stage</a>
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
                                    <h4>Lead Stage Details</h4>
                                </a>
                            </div>
                        </div>
                        <div class="panel-body">

                            <div class="table-responsive">
                                <div class="table-responsive card" style="padding: 20px">
                                    <table class="table table-striped table-bordered first" id="table">
                                        <thead>
                                        <tr>

                                            <th>Lead Stage</th>
                                            <th>Action</th>

                                        </tr>
                                        </thead>
                                        <tbody id="tbodytable">

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
                <div style="width: 100%" class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header modal-header-primary">
                            <button type="button" class="close" onclick="clearData()" data-dismiss="modal"
                                    aria-hidden="true">×
                            </button>
                            <h3><i class="fa fa-plus m-r-5"></i>Add Stage</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <form class="form-horizontal" id="uploadForm">
                                        <fieldset>
                                            <div class="col-md-12 form-group">
                                                <label class="control-label">Enter Stage Name</label>
                                                <input required type="text" name="stagename" id="statename" placeholder="Enter Stage"
                                                       class="form-control">
                                            </div>
                                            <div class="col-md-12 form-group">
                                                <label class="control-label">Stage Option</label>
                                                <table class="table table-bordered table-striped table-hover">
                                                    <thead><tr><th>Option Name</th><th>Type</th><th>Action</th><th>Inner Action</th><th>TemplateId</th><th>Color</th><th>Manual Change</th><th>Action  <i id="addnewrow" class="hvr-buzz-out fa fa-plus-circle"></i></th></tr></thead>
                                                    <tbody id="tbodyatr"></tbody>

                                                </table>



                                            </div>
                                            <!-- Text input-->

                                            <input type="hidden" id="id" class="form-control">
                                            <div class="col-md-12 form-group user-form-group">
                                                <div class="pull-left">
                                                    <button type="submit" class="btn btn-add btn-sm" id="createbtn">
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

<!-- ==============FORM SUBMISSION SCRIPT | CREATING A NEW ROLE=================== -->
<script>


    //=======================================UPLOAD FILE=============================================


    // ============================================== TABLE ============================


    //Refresh table


    // DELETE File



function loadleadstage()
{
    $.ajax({
        url: "${pageContext.request.contextPath}/api/leadstage/getall",
        type: 'GET',
        success: function (json) {
            var html=""
            for(var i=0;i<json.length;i++)
            {

                //var innertdhtml="<i class=\"hvr-buzz-out fa fa-pencil-square editfield\"></i>  <i class=\"hvr-buzz-out fa fa-trash deletestage\"></i>";
                var innertdhtml = '<button type="button" class="btn btn-add btn-sm editfield" title="Update" ><i class="fa fa-pencil"></i></button> <button type="button" class="btn btn-danger btn-sm deletestage" title="Delete" ><i class="fa fa-trash"></i></button>'
                html=html+"<tr> <td hidden><a >"+JSON.stringify(json[i])+"</a></tdhidden> <td title='Click Here To View' style='cursor: pointer'>"+json[i].stage+"</td><td>"+innertdhtml+"</td></tr>"
                //html=html+"<tr hidatr='"+JSON.stringify(json[i])+"'><td title='Click Here To View' style='cursor: pointer'>"+json[i].stage+"</td><td>"+innertdhtml+"</td></tr>"
            }
            $('#tbodytable').html(html)
        },
        error: function (err) {


        }
    });
}
    loadleadstage();
    function clearData()
    {
        $('#tbodyatr').html("")
        $('#statename').val("")
        $('#createbtn').html("Create")
        $('#id').val("")
    }
$('#uploadForm').submit(function (e) {
    e.preventDefault();
    var btntext=$('#createbtn').html().trim();
    var tr=$('#tbodyatr').find("tr");
    if(tr.length<=0)
    {


        swal({
            title: "Error!",
            text: "Cannot Create State Without Options",
            icon: "error",
            button: "Back!",
        });
    }
    var optionarr=[];
    for(var i=0;i<tr.length;i++)
    {
        var optionmap={};
        optionmap["value"]=tr.eq(i).find("td").eq(0).find("input").val().trim();
        optionmap["type"]=tr.eq(i).find("td").eq(1).find("input").val().trim();
        optionmap["action"]=tr.eq(i).find("td").eq(2).find("input").val().trim();
        optionmap["innerAction"]=tr.eq(i).find("td").eq(3).find("input").val().trim();
        optionmap["innerAction"]=tr.eq(i).find("td").eq(3).find("input").val().trim();
        optionmap["templateId"]=tr.eq(i).find("td").eq(4).find("input").val().trim();
        optionmap["color"]=tr.eq(i).find("td").eq(5).find("input").val().trim();

        optionmap["canBeManuallyChanged"]=tr.eq(i).find("td").eq(6).find("input").is(":checked");
        optionarr.push(optionmap);
    }
    var map={};
    map["stage"]=$('#statename').val().trim();
    map["option"]=optionarr;
    var url="${pageContext.request.contextPath}/api/leadstage/insert"
    if(btntext=="Update")
    {
        map["id"]=$('#id').val();
        url="${pageContext.request.contextPath}/api/leadstage/update"
    }

    $.ajax({
        type: 'POST',
        data: JSON.stringify(map),
        contentType: 'application/json',
        url: url,
        success: function (data) {




            if (btntext == 'Create') {


                swal({
                    title: "Success!",
                    text: "Successfully created Stage",
                    icon: "success",
                    button: "Ok!",
                });

            } else {

                swal({
                    title: "Success!",
                    text: "Successfully Updated Stage",
                    icon: "success",
                    button: "Ok!",
                });

            }
            loadleadstage()

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


})
    $(document).on("click",".deletestage",function () {
        var ths=$(this);

        //var json=JSON.parse(ths.closest("tr").attr("hidatr"));
        var json=JSON.parse(ths.closest("tr").find("td").eq(0).find("a").html());
        console.log(json)
        swal({
            title: "Are you sure?",
            text: "You Want To Delete This Stage ?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/api/leadstage/delete?id="+json.id,
                        type: 'DELETE',
                        success: function (json) {
                            ths.closest("tr").remove();
                        },
                        error: function (err) {


                        }
                    });


                }
            });
    })
    $(document).on("click",".editfield",function () {
        var ths=$(this);
        console.log(ths)

        //var json=JSON.parse(ths.closest("tr").attr("hidatr"));
        var json=JSON.parse(ths.closest("tr").find("td").eq(0).find("a").html());

        $('#statename').val(json.stage)
        $('#id').val(json.id);
        json=json.option;
        var html=""


        for(var i=0;i<json.length;i++)
        {
            var htmlmanualcheck="<input type='checkbox' checked/>";
            if(!json[i].canBeManuallyChanged)
            {
                htmlmanualcheck="<input type='checkbox'/>"
            }
             html=html+"<tr> <td><input value='"+json[i].value+"' class='form-control' type='text' placeholder='Enter Option Name'></td> <td><input class='form-control' value='"+json[i].type+"' type='text' placeholder='Enter Type Name'></td> <td><input class='form-control' value='"+json[i].action+"' type='text' placeholder='Enter Action'></td>  <td><input class='form-control' type='text' value='"+json[i].innerAction+"' placeholder='Enter Inner Action'></td> <td><input class='form-control' type='text' value='"+json[i].templateId+"' placeholder='Enter TemplateId'></td> <td><input class='form-control' value='"+json[i].color+"' type='color' placeholder='Enter Color'></td><td>"+htmlmanualcheck+"</td> <td><button type='button' class='btn btn-danger btn-sm deleterow' title='Click Here To Delete this option'><i class='fa fa-trash'></i></button></td> </tr>"


        }



        $('#createbtn').html("Update")
        $('#uploadDocument').modal("show");
        $('#tbodyatr').html(html)

    })



        $('#addnewrow').click(function () {

            var html="<tr> <td><input class='form-control' type='text' placeholder='Enter Option Name'></td> <td><input class='form-control' type='text' placeholder='Enter Type Name'></td> <td><input class='form-control' type='text' placeholder='Enter Action'></td>  <td><input class='form-control' type='text' placeholder='Enter Inner Action'></td> <td><input class='form-control' type='text' placeholder='Enter TemplateId'></td><td><input class='form-control' type='color' placeholder='Enter Color'></td><td><input type='checkbox' checked></td><td><i title='Click Here To Delete this option' class='deleterow hvr-buzz-out fa fa-trash'></i></td> </tr>"
            $('#tbodyatr').append(html);
        })
    $(document).on("click",".deleterow",function () {

        swal({
            title: "Are you sure?",
            text: "You Want To Delete This Option ?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    $(this).closest("tr").remove();
                }
            });

    })

    $(document).on("keyup","input",function (){
        let ths = $(this)
       let value = ths.val();
        value = value.replace("'","");
        ths.val(value);
    })

</script>
<%--============================ DATA TABLE ENDS HERE =====================================--%>
</body>


<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>

