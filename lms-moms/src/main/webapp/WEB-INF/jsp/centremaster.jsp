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
                <i class="fa fa-cubes"></i>
            </div>

                <div class="header-title">
                    <h1>Centre</h1>
                    <a href="#" class="btn btn-add" data-toggle="modal" data-target="#addProduct" title="Add new program"><i class="fa fa-plus"></i> Add Centre</a>
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
                                    <h4>Centre Details</h4>
                                </a>
                            </div>
                        </div>
                        <div class="panel-body">

                            <div class="table-responsive">
                                <div class="table-responsive card" style="padding: 20px">
                                    <table class="table table-striped table-bordered first" id="table">
                                        <thead>
                                        <tr>
                                            <th>Centre Name</th>
                                            <th>Pincode</th>
                                            <th>Centre Address</th>
                                            <th>Contact Person</th>
                                            <th>Contact Number</th>
                                            <th>Status</th>
                                            <th>Timings</th>
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

            <!--======================================================ADD NEW ROLE=================================================================== -->
            <div class="modal fade" id="addProduct" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header modal-header-primary">
                            <button type="button" onclick="clearData()" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h3><i class="fa fa-plus m-r-5"></i> Add New Centre</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <form class="form-horizontal" id="createForm">
                                        <fieldset>

                                            <!-- Not to be manually inserted-->
                                                 <input type="hidden" id="id" class="form-control" >
                                            <input type="hidden" id="centreTimingSun" class="form-control" >
                                            <input type="hidden" id="centreTimingMon" class="form-control" >
                                            <input type="hidden" id="centreTimingTue" class="form-control" >
                                            <input type="hidden" id="centreTimingWed" class="form-control" >
                                            <input type="hidden" id="centreTimingThu" class="form-control" >
                                            <input type="hidden" id="centreTimingFri" class="form-control" >
                                            <input type="hidden" id="centreTimingSat" class="form-control" >

                                            <!-- Text input-->
                                            <div class="col-md-8 form-group">
                                                <label class="control-label">Status</label>
                                                <div class="form-check-inline">
                                                    <label class="form-check-label">
                                                        <input type="radio" class="form-check-input" name="optradionew" value="1" checked>Available
                                                    </label>
                                                </div>
                                                <div class="form-check-inline">
                                                    <label class="form-check-label">
                                                        <input type="radio" class="form-check-input" name="optradionew" value="0">Unavailable
                                                    </label>
                                                </div>
                                            </div>

                                            <!-- Text input-->
                                            <div class="col-md-6 form-group">
                                                <label class="control-label">Centre Name</label>
                                                <input type="text"  maxlength="100" placeholder="Centre Name"  id="centreName" class="form-control" required>
                                            </div>

                                            <!-- Text input-->
                                            <div class="col-md-6 form-group">
                                                <label class="control-label">Centre Pincode</label>
                                                <input type="text"  maxlength="100" placeholder="Centre Pincode"  id="pincode" class="form-control" required>
                                            </div>
                                            <!-- Text input-->
                                            <div class="col-md-6 form-group">
                                                <label class="control-label">Centre Address</label>
                                                <input type="text" maxlength="256" placeholder="Centre Address"  id="centreAddress" class="form-control" >
                                            </div>
                                            <!-- Text input-->
                                            <div class="col-md-6 form-group">
                                                <label class="control-label">Centre City</label>
                                                <input type="text" maxlength="100" placeholder="Centre City"  id="centreCity" class="form-control" >
                                            </div>
                                            <!-- Text input-->
                                            <div class="col-md-6 form-group">
                                                <label class="control-label">Centre State</label>
                                                <input type="text" maxlength="100" placeholder="Centre State"  id="centreState" class="form-control" >
                                            </div>
                                            <!-- Text input-->
                                            <div class="col-md-6 form-group">
                                                <label class="control-label">Centre Country</label>
                                                <input type="text" maxlength="100" placeholder="Centre Country"  id="centreCountry" class="form-control" >
                                            </div>



                                            <!-- Text input-->
                                            <div class="col-md-6 form-group">
                                                <label class="control-label">Centre Primary Contact Person</label>
                                                <input type="text"  maxlength="100" placeholder="Centre Contact Person"  id="centreContactPerson" class="form-control" required>
                                            </div>

                                            <!-- Text input-->
                                            <div class="col-md-6 form-group">
                                                <label class="control-label">Centre Primary Contact Number</label>
                                                <input type="text"  maxlength="100" placeholder="Centre Contact Number"  id="centreContactNumber" class="form-control" required>
                                            </div>


                                            <!-- Text input-->
                                            <div class="col-md-6 form-group">
                                                <label class="control-label">Centre Secondary Contact Person</label>
                                                <input type="text"  maxlength="100" placeholder="Centre Secondary Contact Person"  id="centreContactPerson2" class="form-control" required>
                                            </div>

                                            <!-- Text input-->
                                            <div class="col-md-6 form-group">
                                                <label class="control-label">Centre Secondary Contact Number</label>
                                                <input type="text"  maxlength="100" placeholder="Centre Secondary Contact Number"  id="centreContactNumber2" class="form-control" required>
                                            </div>


                                            <!-- Text input-->
                                            <div class="col-md-12 form-group">
                                                <label class="control-label">Centre Contact Email</label>
                                                <input type="text"  maxlength="100" placeholder="Centre Contact Email"  id="centreContactEmail" class="form-control" required>
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
            <div class="modal fade" id="setTiming" tabindex="-1" role="dialog" aria-hidden="true">
                <div style="width: 100%" class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header modal-header-primary">
                            <button type="button" class="close" onclick="clearData()" data-dismiss="modal"
                                    aria-hidden="true">×
                            </button>
                            <h3><i class="fa fa-plus m-r-5"></i>Set Center Timing</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <form class="form-horizontal" id="timingform">

                                        <input type="hidden" id="tempJson">
                                        <fieldset>

                                            <div class="tab-pane fade in active" id="tab1">
                                                <div class="panel-body">
                                                    <div class="table-responsive">
                                                        <table class="table table-bordered table-striped table-hover">
                                                            <thead>
                                                            <tr class="info">
                                                                <th>Mon</th>
                                                                <th>Tue</th>
                                                                <th>Wed</th>
                                                                <th>Thu</th>
                                                                <th>Fri</th>
                                                                <th>Sat</th>
                                                                <th>Sun</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <tr>
                                                                <td><input type="text"  maxlength="100" placeholder="E.g. 9AM-12PM"  id="centreTimingMonTemp" class="form-control" required></td>
                                                                <td><input type="text"  maxlength="100" placeholder="E.g. 9AM-12PM"  id="centreTimingTueTemp" class="form-control" required></td>
                                                                <td><input type="text"  maxlength="100" placeholder="E.g. 9AM-12PM"  id="centreTimingWedTemp" class="form-control" required></td>
                                                                <td><input type="text"  maxlength="100" placeholder="E.g. 9AM-12PM"  id="centreTimingThuTemp" class="form-control" required></td>
                                                                <td><input type="text"  maxlength="100" placeholder="E.g. 9AM-12PM"  id="centreTimingFriTemp" class="form-control" required></td>
                                                                <td><input type="text"  maxlength="100" placeholder="E.g. 9AM-12PM"  id="centreTimingSatTemp" class="form-control" required></td>
                                                                <td><input type="text"  maxlength="100" placeholder="E.g. 9AM-12PM"  id="centreTimingSunTemp" class="form-control" required></td>

                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>


                                            <div class="col-md-12 form-group user-form-group">
                                                <div class="pull-left">
                                                    <%--                                                    <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal">Cancel</button>--%>
                                                    <button type="submit" class="btn btn-add btn-sm" >Save</button>
                                                </div>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger pull-right" id="formCloseBtn2"
                                    data-dismiss="modal">Close
                            </button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                    <%--=========================================================================================================================================--%>
                </div>
                <!-- /.modal-dialog -->
            </div>

            <%-- ================================================== ADD Product TO AGENT  ============================================================--%>


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
        $('#productAgentBody').html("");
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
                $('#productSelect').html(html)

            },
            error: function (err) {

            }

        })
    }

      //============================ INSERT USER ON SELECT ==========================
      //============================ INSERT USER ON SELECT ==========================
    $('#productSelect').on('change',function () {
        var userId = $(this).val();
        var username = $("#productSelect option:selected").text();
        var productid =$(this).attr('productid');

        if (userId == 0) {

        } else {

            var map={
                product_id : productid,
                user_id : userId
            }

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "${pageContext.servletContext.contextPath}/api/productuser/insert",
                data: JSON.stringify(map),
                success: function (data) {

                    var html = $('#productAgentBody').html();
                    html = html + ("<tr><td attrhid='"+data.id+"'>"+username+"</td><td><button type='button' class='btn btn-danger btn-sm deletUser' title='Delete User from List'  ><i class='fa fa-trash'></i></button></td></tr>");
                    $('#productAgentBody').html(html);

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
                        url: "${pageContext.request.contextPath}/api/productuser/delete?id=" + id,
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

    //=================================== CREATE PRODUCT

    $('#createForm').submit(function (e) {
        e.preventDefault();


        var url = "";
        if($('#createBtn').html() == 'Create'){

            var url = "${pageContext.request.contextPath}/api/mbcentre/insert";
            let centreName = $('#centreName').val().trim();
            centreName = centreName.trim();

            var map = {

                centreName: centreName,
                pincode : $('#pincode').val(),
                centreAddress: $('#centreAddress').val(),
                centreContactPerson: $('#centreContactPerson').val(),
                centreContactNumber: $('#centreContactNumber').val(),
                status: $("input[name='optradionew']:checked").val()
            };
        }

        else{

            var url = "${pageContext.request.contextPath}/api/mbcentre/update";
            let centreName = $('#centreName').val().trim();
            centreName = centreName.trim();

            var map = {
                id:$('#id').val(),
                centreName: centreName,
                pincode : $('#pincode').val(),
                centreAddress: $('#centreAddress').val(),
                centreContactPerson: $('#centreContactPerson').val(),
                centreContactNumber: $('#centreContactNumber').val(),
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
                    text: "Successfully Created Centre",
                    icon: "success",
                    button: "Ok!",
                });
            }

            else{

                swal({
                    title: "Success!",
                    text: "Successfully Updated Centre",
                    icon: "success",
                    button: "Ok!",
                });

            }
               // $('.modal').modal('toggle')
                $('#formCloseBtn').trigger("click");
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
            url: '${pageContext.servletContext.contextPath}/api/mbcentre/getall',
            dataSrc: '',
            "data": {
                "flag": 1,

            }
        },
        "columns": [

            { "mRender": function (data, type, row) {


                    return "<a style='display: none' hidatr='"+JSON.stringify(row)+"'></a><span>"+row.centreName+"</span>";


                }
            },
            {"data":"pincode"},
            {"data":"centreAddress"},
            {"data":"centreContactPerson"},
            {"data":"centreContactNumber"},

            {
                "mRender": function (data, type, row) {
                    var html = "";

                    if (row.status == 1) {
                        html = html + "<i style=' margin-left: 5px; font-size: 18px;color: greenyellow' title='Available' class=\"hvr-buzz-out fa fa-check\"></i>";
                    } else {

                        html = html + "<i style=' margin-left: 5px; font-size: 18px;color: red' title='Not Available' class=\"hvr-buzz-out fa fa-ban\"></i>";
                    }

                    return html;
                }


            },
            { "mRender": function (data, type, row) {


                    return '<button type="button" class="btn btn-add btn-sm settimingcls" data-toggle="modal" data-target="#setTiming" title="Set Timing"><i class="fa fa-clock-o" ></i></button>'
                }

            },
            { "mRender": function (data, type, row) {


                return '<button type="button" class="btn btn-add btn-sm editfield" data-toggle="modal" data-target="#addProduct" title="Update"><i class="fa fa-pencil" ></i></button>'
            }

            },

        ]


        , "initComplete": function(settings, json) {
            $('#tablediv').fadeIn();
            $('#loading').fadeOut();
        },
        "ordering" : false
    } );

    $(document).on("click",".settimingcls",function (){
    let jsonStr = $(this).closest("tr").find("td").eq(0).find("a").attr("hidatr");
    $("#tempJson").val(jsonStr);
    })

    $("#timingform").submit(function(e){
        e.preventDefault();
        let tempJson = JSON.parse($("#tempJson").val());

        tempJson["centreTimingSun"] = $("#centreTimingSunTemp").val();
        tempJson["centreTimingMon"] = $("#centreTimingMonTemp").val();
        tempJson["centreTimingTue"] = $("#centreTimingTueTemp").val();
        tempJson["centreTimingWed"] = $("#centreTimingWedTemp").val();
        tempJson["centreTimingThu"] = $("#centreTimingThuTemp").val();
        tempJson["centreTimingFri"] = $("#centreTimingFriTemp").val();
        tempJson["centreTimingSat"] = $("#centreTimingSatTemp").val();

        console.table(tempJson)
    });

    //RESETTING VALUES AFTER UPDATE OPERATION, ELSE VALUES REMAINS SAME AS AFTER AN 'UPDATE' OPERATION
   function clearData()
   {
       $('#id').val("");
       centreName,
       $('#centreName').val("")
       $('#pincode').val("")
       $('#centreAddress').val("")
       $('#centreContactPerson').val("")
       $('#centreContactNumber').val("")
       $("input[name=optradionew][value='1']").prop('checked', true);
       $('#createBtn').html("Create")
   }

    $(document).on("click",".editfield",function () {
        var json=JSON.parse($(this).closest("tr").find("td").eq(0).find("a").attr("hidatr"));
        $('#id').val(json.id);
        $('#centreName').val(json.centreName)
        $('#pincode').val(json.pincode)
        $('#centreAddress').val(json.centreAddress)
        $('#centreContactPerson').val(json.centreContactPerson)
        $('#centreContactNumber').val(json.centreContactNumber)
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

