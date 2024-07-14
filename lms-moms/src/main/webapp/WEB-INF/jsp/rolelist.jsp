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
    <style>
        .modal-header-primary{
            text-align: center!important;
            font-weight: 900!important;
        }
    </style>
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
    <!-- jquery-ui css -->
    <link href="${pageContext.request.contextPath}/assets/plugins/jquery-ui-1.12.1/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- Lobipanel css -->
    <link href="${pageContext.request.contextPath}/assets/plugins/lobipanel/lobipanel.min.css" rel="stylesheet" type="text/css"/>
    <!-- Pe-icon -->
    <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/pe-icon-7-stroke/css/helper.css" rel="stylesheet" type="text/css"/>

    <!-- Themify icons -->
    <link href="${pageContext.request.contextPath}/assets/themify-icons/themify-icons.css" rel="stylesheet" type="text/css"/>
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
                <i class="fa fa-black-tie"></i>
            </div>
            <div class="header-title">
                <h1>Roles</h1>

            </div>
                <div class="buttonexport">
                    <a href="#" class="btn btn-add" data-toggle="modal" data-target="#addRole" title="Add new role"><i class="fa fa-plus"></i> Add Roles</a>
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
                                    <h4>Role Details</h4>
                                </a>
                            </div>
                        </div>
                        <div class="panel-body">

                            <div class="table-responsive">
                                <div class="table-responsive card" style="padding: 20px">
                                    <table class="table table-striped table-bordered first" id="table">
                                        <thead>
                                        <tr>
                                            <th>Role Name</th><th>Role Description</th><th>Status</th><th>Action</th>
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

            <%--=================== GIVE DASHBOARD SEQUENCE ===========--%>



<%--=================== GIVE DASHBOARD PERSMISSIONS ===========--%>
            <div class="modal fade" id="dashboardpermission" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <div class="modal-header modal-header-primary">
                            <button type="button" class="close" data-dismiss="modal" onclick="clearData()" aria-hidden="true">×</button>
                            <h3><i class="fa fa-plus m-r-5"></i> View Permission</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div  style="max-height: 400px;min-height: 400px;overflow: scroll" class="table-responsive" id="dashboardpermissiondiv">
                                        <input type="hidden" id="dashboardpermisionhidid">

                                        <table class="table table-bordered table-striped table-hover" id="dashboardpermissiontable">
                                            <thead>
                                            <tr><td>Dashboard Item</td><td>Allow/Disallow</td><td>Sequence</td></tr>
                                            </thead>
                                            <tbody id="dashboardtbody">

                                            </tbody>

                                        </table>

                                    </div>

                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger pull-right" onclick="clearData()" id="formCloseBtn3" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                    <%--=========================================================================================================================================--%>
                </div>
                <!-- /.modal-dialog -->
            </div>
<%--=================== GIVE PAGE PERMISSIONS =================--%>
            <div class="modal fade" id="featurespermission" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <div class="modal-header modal-header-primary">
                            <button type="button" class="close" data-dismiss="modal" onclick="clearData()" aria-hidden="true">×</button>
                            <h3> Feature Permission</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div  style="max-height: 400px;min-height: 400px;overflow: scroll" class="table-responsive" >
                                        <input type="hidden" id="hidroleId">

                                        <table class="table table-bordered table-striped table-hover">
                                            <thead>
                                            <tr><td>Feature</td><td>Status</td></tr>
                                            </thead>
                                            <tbody id="featuretbody">

                                            </tbody>

                                        </table>

                                    </div>

                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger pull-right" onclick="clearData()" id="formCloseBtn3" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                    <%--=========================================================================================================================================--%>
                </div>
                <!-- /.modal-dialog -->
            </div>


            <div class="modal fade" id="addpermission" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header modal-header-primary">
                            <button type="button" class="close" data-dismiss="modal" onclick="clearData()" aria-hidden="true">×</button>
                            <h3><i class="fa fa-plus m-r-5"></i> View Permission</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div  style="max-height: 400px;min-height: 400px;overflow: scroll" class="table-responsive" id="permission">
                                        <input type="hidden" id="permisionhidid">

                                        <table class="table table-bordered table-striped table-hover" id="permissiontable">
                                            <thead>
                                            <tr><td>Page</td><td><i id="addnewrowpermission" title="Click Here To Add new Role" class="hvr-buzz-out fa fa-plus-circle"></i></td></tr>
                                            </thead>
                                            <tbody id="permissiontbody">

                                            </tbody>

                                        </table>

                                    </div>

                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger pull-right" onclick="clearData()" id="formCloseBtn4" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                    <%--=========================================================================================================================================--%>
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!--======================================================ADD NEW ROLE=================================================================== -->
            <div class="modal fade" id="addRole" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header modal-header-primary">
                            <button type="button" class="close" data-dismiss="modal" onclick="clearData()" aria-hidden="true">×</button>
                            <h3><i class="fa fa-plus m-r-5"></i> Add new Role</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <form class="form-horizontal" id="createForm">
                                        <fieldset>

                                            <!-- Not to be manually inserted-->
                                                <input type="hidden" placeholder="Role Name" id="id" class="form-control" >

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
                                                <label class="control-label">Role Name</label>
                                                <input type="text" minlength="4" maxlength="100" placeholder="Role Name"  id="name" class="form-control" required>
                                            </div>
                                            <!-- Text input-->
                                            <div class="col-md-8 form-group">
                                                <label class="control-label">Role Description</label>
                                                <input type="text" minlength="4" maxlength="256" placeholder="Role Description"  id="description" class="form-control" required>
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
<select style="display: none" id="pagelist"></select>
        </section>

<%--========DASHBOARD LIST========--%>
        <select style="display: none" id="dashboardlist"></select>
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
<!-- CRMadmin frame -->
<script src="${pageContext.request.contextPath}/assets/dist/js/custom.js" type="text/javascript"></script>
<!-- Dashboard js -->
<script src="${pageContext.request.contextPath}/assets/dist/js/dashboard.js" type="text/javascript"></script>
<!-- End Theme label Script=====================================================================-->

<!-- =============================DATA TABLES======================================================-->
<script src="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.min.js"></script>
<!-- ==============FORM SUBMISSION SCRIPT | CREATING A NEW ROLE=================== -->
<script>

    $.ajax({
        type: 'GET',

        contentType: 'application/json',
        url: "${pageContext.request.contextPath}/api/urlaccess/getallactive",
        success: function (data) {
            var html="<option value='0'>Select Page</option>";
            for(var i=0;i<data.length;i++)
            {
                html=html+"<option value='"+data[i].id+"'>"+data[i].pageName+"</option>"
            }
            $('#pagelist').html(html)

        }

    });

    $('#createForm').submit(function (e) {
        e.preventDefault();


        var url = "";
        if($('#createBtn').html() == 'Create'){

            var url = "${pageContext.request.contextPath}/api/roles/insert";
            let roleName = $('#name').val().trim();
            roleName = roleName.trim();

            var map = {

                roleName: roleName,
                roleDesc: $('#description').val(),
                status: $("input[name='optradionew']:checked").val()
            };
        }

        else{

            var url = "${pageContext.request.contextPath}/api/roles/update";
            let roleName = $('#name').val().trim();
            roleName = roleName.trim();

            var map = {
                id:$('#id').val().trim(),
                roleName: roleName,
                roleDesc: $('#description').val(),
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
                    text: "Successfully created role",
                    icon: "success",
                    button: "Ok!",
                });

            }

            else{

                swal({
                    title: "Success!",
                    text: "Successfully Updated Role",
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
            url: '${pageContext.servletContext.contextPath}/api/roles/getroles',
            dataSrc: '',
            "data": {
                "flag": 1,

            }
        },
        "columns": [

            { "mRender": function (data, type, row) {


                    return "<a style='display: none'>"+JSON.stringify(row)+"</a><span>"+row.roleName+"</span>";


                }
            },

            { "data": "roleDesc" },
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


                return '<button type="button" class="btn btn-add btn-sm editfield" data-toggle="modal" data-target="#addRole" title="Update"><i class="fa fa-pencil"></i></button>  <button title="Click Here To Give Permission" type="button" data-toggle="modal" data-target="#addpermission" class="btn btn-danger btn-sm givepermissions" data-toggle="modal" ><i class="hvr-buzz-out fa fa-key"></i> </button> ' +
                    '<button title="Click Here To Give Dashboard Permission" type="button" data-toggle="modal" data-target="#dashboardpermission" class="btn btn-success btn-sm givedashboardpermissions" data-toggle="modal" ><i class="hvr-buzz-out fa fa-object-ungroup"></i> </button>' +
                    '<button  title="Click Here To Give  Access Features" type="button" data-toggle="modal" data-target="#featurespermission" class="btn btn-success btn-sm loadFeatureListForRole" data-toggle="modal" ><i class="hvr-buzz-out fa fa-bars"></i> </button>';

            }

            },

        ]


        , "initComplete": function(settings, json) {
            $('#tablediv').fadeIn();
            $('#loading').fadeOut();
        },
        "ordering" : false
    } );

    //RESETTING VALUES AFTER UPDATE OPERATION, ELSE VALUES REMAINS SAME AS AFTER LAsT 'UPDATE' OPERATION
   function clearData()
   {
       $('#id').val("");
       $('#name').val("");
       $('#description').val("")
       $("input[name=optradionew][value='1']").prop('checked', true);
       $('#createBtn').html("Create")



   }
   $(document).on("click",".loadFeatureListForRole",function () {
       var id=  JSON.parse($(this).closest("tr").find("td").eq(0).find("a").html()).id;
       $.ajax({
           type: 'GET',

           contentType: 'application/json',
           url: "${pageContext.request.contextPath}/api/features/getByRoleId?roleId="+id,
           success: function (json) {
               var html="";
               for(var i=0;i<json.length;i++)
               {
                   if(json[i].refId>0) {
                       html = html + "<tr roleId='"+id+"' refId='"+json[i].refId+"'  title='"+json[i].descri+"' feid='"+json[i].id+"'><td>"+json[i].name+"</td><td><input checked type='checkbox' class='giveaccesstofeature'></td></tr>";
                   }
                   else{
                       html = html + "<tr roleId='"+id+"' refId='"+json[i].refId+"'  title='"+json[i].descri+"' feid='"+json[i].id+"'><td>"+json[i].name+"</td><td><input  type='checkbox' class='giveaccesstofeature'></td></tr>";

                   }
               }
               $('#featuretbody').html(html)

           }

       });
   })

    $(document).on("click",".giveaccesstofeature",function () {
        var ths=$(this);
        var tr=ths.closest("tr");
        var checkSts=ths.prop("checked");
        console.log(checkSts)
        if(checkSts)
        {
            var map={};
            map["roleId"]=tr.attr("roleid");
            map["featureId"]=tr.attr("feid");
            giveAccessToFeature(map,function (data) {
            console.log(data)
                tr.attr("refid",data.id)
            })
        }
        else{
            deleteFeatureFromRole(tr.attr("refid"),function (data) {

            })
        }
    })
    function deleteFeatureFromRole(id,callback)
    {
        console.log(id)
        $.ajax({
            type: 'DELETE',

            contentType: 'application/json',
            url: "${pageContext.request.contextPath}/api/featureassign/delete?id="+id,
            success: function (json) {
                callback("200")

            }

        });

    }
    function giveAccessToFeature(map,callback)
    {

        $.ajax({
            type: 'POST',
            data: JSON.stringify(map),
            contentType: 'application/json',
            url: '${pageContext.servletContext.contextPath}/api/featureassign/insert',
            success: function (data) {
                callback(data.id)
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

    }
   //=========== ADD URL ACCESS PERMISSON ROW
   $('#addnewrowpermission').click(function () {
    $('#permissiontbody').prepend("<tr hidid='0'><td><select class='form-control urlchange'>"+$('#pagelist').html()+"</select></td><td><i class=\"deletepermission fa fa-trash-o\"></i></td></tr>")
   })

   $(document).on("change",".urlchange",function () {
        var ths=$(this).closest("tr");
        var roleid=$('#permisionhidid').val();
        var urlid=$(this).val();
        if(urlid==0)
        {
            return;
        }
        var html=$(this).find('option:selected').text()
        var map={};
        map["urlId"]=urlid;
        map["roleId"]=roleid;
        $.ajax({
            type: 'POST',
            data: JSON.stringify(map),
            contentType: 'application/json',
            url: '${pageContext.servletContext.contextPath}/api/rolepage/insert',
            success: function (data) {
                ths.prop("hidid",data.id)
                ths.html("<td>"+html+"</td><td><i class='deletepermission fa fa-trash-o'></i></td>")
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

   $(document).on("click",".givepermissions",function () {

       var json=JSON.parse($(this).closest("tr").find("td").eq(0).find("a").html());
       $('#permissiontbody').html("")
       $('#permisionhidid').val(json.id);
       var map={};
       for(var i=0;i<$('#pagelist').find("option").length;i++)
       {
           map[$('#pagelist').find("option").eq(i).val()]=$('#pagelist').find("option").eq(i).text();
       }

       $.ajax({
           type: 'GET',

           contentType: 'application/json',
           url: "${pageContext.request.contextPath}/api/rolepage/getallbyrole?role="+json.id,
           success: function (data) {
               var html="";
               for(var i=0;i<data.length;i++)
               {
                   html=html+"<tr hidid='"+data[i].id+"'><td>"+ map[data[i].urlId]+"</td><td><i title='Click Here To Delete' class=\"deletepermission fa fa-trash-o\"></i></td></tr>"
               }
               $('#permissiontbody').html(html)

           }

       });

   })
    //========================== LOADING DASHBOARD LIST ===

    $.ajax({
        type: 'GET',

        contentType: 'application/json',
        url: "${pageContext.request.contextPath}/json/dashboard.json",
        success: function (dashboard_json) {
            var html = "";
            for(var i=0;i<dashboard_json.length;i++)
            {
                html=html+"<tr><td title='"+dashboard_json[i].description+"'>"+dashboard_json[i].dashboardName+"</td><td><input  hidatr='"+JSON.stringify(dashboard_json[i])+"' type='checkbox' hiddenvalue='0' title='Click Here To Add Permission' class='permissioncheck' data-toggle='modal' data-target='#itemseqdiv' /></td><td></td></tr>"
            }
            $('#dashboardtbody').html(html)

        }

    });
//============== DASHBOARD BUTTON CLICK
    $(document).on("click",".givedashboardpermissions",function () {
        clearLastColumn();
        $('.permissioncheck').prop("checked",false)

        var json=JSON.parse($(this).closest("tr").find("td").eq(0).find("a").html());
        $('#dashboardpermisionhidid').val(json.id)
        var roleId = json.id;
        $.ajax({

            type: 'GET',
            contentType: 'application/json',
            url: "${pageContext.request.contextPath}/api/dashboardpermission/getpermissionsbyrolename?roleName="+json.roleName,
            success: function(result) {
                for(var i=0;i<result.length;i++){
                    var name= result[i].dashboardName;
                    var tr=$("#dashboardtbody").find("tr");

                    for(var m=0;m< tr.length; m++){

                        if(name == tr.eq(m).find("td").eq(0).text() && roleId ==  result[i].roleId){
                            tr.eq(m).find("td").eq(1).find("input").attr("hiddenvalue", result[i].id)
                            tr.eq(m).find("td").eq(1).find("input").prop("checked",true)
                            tr.eq(m).find("td").eq(2).html(result[i].seq)
                        }
                    }
                }

            },
            error: function(err){

                swal({
                    title: "Error!",
                    text: err.responseJSON,
                    icon: "error",
                    button: "Back!",
                });

            }
        });

    });
    function clearLastColumn(){
        var trlen= $("#dashboardtbody tr").length;
        for(var i=0;i<trlen;i++){
            $("#dashboardtbody").find("tr").eq(i).find("td").eq(2).html("")
        }

    }
//====== CHECKBOX EVENT
   $(document).on("click",".permissioncheck",function (e) {



       var ths=$(this);


       var json = JSON.parse($(this).attr("hidatr"));
       var roleid =  $('#dashboardpermisionhidid').val();
       var id = ths.attr("hiddenvalue")

       if ($(this).is(':checked')) {
           var html = '<input type="number" placeholder="Enter Sequence" id="itemseqselect" class="form-control" required />'
           var elem = document.createElement("div");
           elem.innerHTML = html;
           swal("Dashboard Item Location", {
               content: elem,
               buttons: [
                   'Cancel',
                   'Save'
               ],
               allowOutsideClick: false
           }).then(function(isConfirm) {
               if (isConfirm) {
                   var seq = $("#itemseqselect").val();
                   ths.closest("tr").find("td").eq(2).html(seq);
                   addDashboardPermission(roleid,json,ths,seq)
               } else {
                   ths.prop("checked",false);
               }
           })



       }

       else{
        deleteDashboardPermission(id);
           ths.closest("tr").find("td").eq(2).html("");
       }

   })



    //====== ADDING DESTKOP PERMISSION TO THE ROLE

    function  addDashboardPermission(roleid,json,ths,seq){

       var map={

           roleId : roleid,
           dashboardName :json.dashboardName,
           url : json.url,
           description : json.description,
           seq : seq
       }
        $.ajax({

            type: 'POST',
            contentType: 'application/json',
            url: "${pageContext.request.contextPath}/api/dashboardpermission/insert",
            data: JSON.stringify(map),
            success: function(result) {
                    ths.attr("hiddenvalue",result.id)

            },
            error: function(err){

                swal({
                    title: "Error!",
                    text: err.responseJSON,
                    icon: "error",
                    button: "Back!",
                });

            }
        });
    }

    //===============  DELETE DASHBOARD PERMISSION=======================
    function deleteDashboardPermission(id){
        $.ajax({

            type: 'DELETE',
            contentType: 'application/json',
            url: "${pageContext.request.contextPath}/api/dashboardpermission/delete?id="+id,
            success: function(result) {

            },
            error: function(err){

                swal({
                    title: "Error!",
                    text: err.responseJSON,
                    icon: "error",
                    button: "Back!",
                });

            }
        });
    }

    $(document).on("click",".deletepermission",function () {
        var id=$(this).closest("tr").attr("hidid");
        var ths=$(this).closest("tr");


        swal({
            title: "Are you sure?",
            text: "Delete Permission ?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    if(id==0)
                    {
                        $(this).closest("tr").remove();
                    }
                    else{
                        $.ajax({
                            type: 'DELETE',

                            contentType: 'application/json',
                            url: "${pageContext.request.contextPath}/api/rolepage/delete?id="+id,
                            success: function (data) {
                                ths.remove();

                            }

                        });
                    }
                }
            });
    })
    $(document).on("click",".editfield",function () {
        var json=JSON.parse($(this).closest("tr").find("td").eq(0).find("a").html());
        $('#id').val(json.id);
        $('#name').val(json.roleName);
        $('#description').val(json.roleDesc)
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

