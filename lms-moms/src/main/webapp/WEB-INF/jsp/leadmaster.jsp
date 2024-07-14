<%@ page import="com.example.maxcrm.MaxCrm.Utility.UtilityClass" %>
<%@ page import="static com.example.maxcrm.MaxCrm.Utility.UtilityClass.propertyService" %><%--
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

    <link href="${pageContext.request.contextPath}/assets/plugins/quilleditor/quill.snow.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/assets/plugins/quilleditor/quill.min.js"></script>
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
    <link href="${pageContext.request.contextPath}/assets/plugins/pace/flash.css" rel="stylesheet" type="text/css"/>
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
    <link href="${pageContext.request.contextPath}/assets/plugins/emojionearea/emojionearea.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- Monthly css -->

    <!-- End page Label Plugins
       =====================================================================-->
    <!-- Start Theme Layout Style
       =====================================================================-->
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath}/assets/dist/css/stylecrm.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/plugins/datetimepicker/jquery.datetimepicker.min.css"
          rel="stylesheet" type="text/css"/>

    <!-- Theme style rtl -->
    <!--<link href="assets/dist/css/stylecrm-rtl.css" rel="stylesheet" type="text/css"/>-->
    <!-- End Theme Layout Style
       =====================================================================-->
    <style>
        .validationFail {
            background-color: #ff00005e !important;
        }

        .downloadfilecls {
            cursor: pointer;
        }

        .fa {
            font-size: 20px;
        }

        .fa-volume-control-phone {
            color: green;

        }
        .loadFeatureListForRole{
            margin-left: 3px;
        }
        .fa-envelope {
            color: green;
        }
        .mandatory{
            font-size: 11px;
            margin-left: 10px;
            color: red;
        }

        .fa-info-circle {
            color: red;
        }

        .customsidedivmenu {
            max-height: 300px;

            overflow: scroll;
        }

        .statuscontrol {
            border: 1px solid black;
            padding: 6px;
            text-align: center;
            width: 81px;
            font-weight: 800;
            color: black;
            float: left;
            border-radius: 6px;
        }
        .cstmbtnfilter{
            margin: 7px; border-radius: 5px; padding: 0px;background: #ccd0d2;
        }

        .leadstatuscls {
            color: black;
            font-weight: bolder;
            padding: 2px;
            border-radius: 6px;
            margin-left: 6px;


        }


        .selectedtrue {

            -webkit-border-radius: 10px;
            border-radius: 10px;
            border: none;
            color: black;
            cursor: pointer;
            display: inline-block;
            font-family: Arial;
            font-size: 20px;
            padding: 5px 10px;
            text-align: center;
            text-decoration: none;
            -webkit-animation: glowing 1500ms infinite;
            -moz-animation: glowing 1500ms infinite;
            -o-animation: glowing 1500ms infinite;
            animation: glowing 1500ms infinite;
        }

        @-webkit-keyframes glowing {
            0% {
                -webkit-box-shadow: 0 0 3px #B20000;
            }
            50% {
                -webkit-box-shadow: 0 0 40px #FF0000;
            }
            100% {
                -webkit-box-shadow: 0 0 3px #B20000;
            }
        }

        @-moz-keyframes glowing {
            0% {
                -moz-box-shadow: 0 0 3px #B20000;
            }
            50% {
                -moz-box-shadow: 0 0 40px #FF0000;
            }
            100% {
                -moz-box-shadow: 0 0 3px #B20000;
            }
        }

        @-o-keyframes glowing {
            0% {
                box-shadow: 0 0 3px #B20000;
            }
            50% {
                box-shadow: 0 0 40px #FF0000;
            }
            100% {
                box-shadow: 0 0 3px #B20000;
            }
        }

        @keyframes glowing {
            0% {
                box-shadow: 0 0 3px #B20000;
            }
            50% {
                box-shadow: 0 0 40px #FF0000;
            }
            100% {
                box-shadow: 0 0 3px #B20000;
            }
        }

        .menuouter {
            position: absolute;
            background-color: white;
            display: none;
            border: 1px solid;

            box-shadow: 5px 10px #888888;
        }

        .menuinnermode {

            cursor: pointer;
            padding-left: 10px;
            padding-right: 10px;
            font-weight: 700;
        }

        .menuinnermode:hover {
            background-color: darkgrey;
            color: white;
        }

        .custom-menu {
            display: none;
            z-index: 1000;
            position: absolute;
            overflow: hidden;
            border: 1px solid #CCC;
            white-space: nowrap;
            font-family: sans-serif;
            background: #FFF;
            color: #333;
            border-radius: 5px;
            padding: 0;
            /*margin-bottom: 40px;*/
        }

        /* Each of the items in the list */
        .custom-menu li {
            padding: 8px 12px;
            cursor: pointer;
            list-style-type: none;
            transition: all .3s ease;
            user-select: none;

        }

        .custom-menu li:hover {
            background-color: #DEF;
        }

        ul {
            list-style-type: none;
        }

        headertag {
            font-weight: bolder;
        }

        .customheaderbtn {

            margin-left: 10px;
            margin-bottom: 5px;
            min-width: 100px;
        }

        .editfield {
            display: none;
        }

        .displaynone {
            display: none;
        }

    </style>
</head>
<body class="sidebar-mini  pace-done">
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
        <section class="content-header" id="addUserHeader">
            <div class="header-icon">
                <i style="font-size: 1em;" class="fa fa-users"></i>
            </div>
            <div class="header-title">
                <h1>Lead</h1>
                <button id="addnewuser" disabled class="btn btn-add " title="Add new lead">Add Lead <i
                        class="hvr-buzz-out fa fa-plus"></i></button>

                <button id="addbulklead" disabled class="btn btn-add " title="Bulk Upload Lead(Maximum 1000)">Bulk Add
                    Lead <i
                            class="hvr-buzz-out fa fa-plus"></i>
                </button>
            </div>
        </section>
        <!-- Main content -->
        <section class="content">
            <button style="display:none" type="button" id="hisiframebtn" class="btn btn-info btn-lg" data-toggle="modal" data-target="#iframeloadhis">Open Modal</button>

            <div class="modal fade" id="iframeloadhis" role="dialog">
                <div class="modal-dialog modal-lg">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>

                        </div>
                        <div style="height: 500px" class="modal-body" id="iframebody">
                           </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>
            <div class="row">


                <div class="col-sm-12 col-md-12">
                    <div class="panel panel-bd lobidrag">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <a href="#">
                                    <h4>Lead Details</h4>
                                </a>
                            </div>
                        </div>
                        <div class="panel-body">


                            <div style="display: none" id="addnewdiv">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
                                        <div id="addnewformdiv" class="panel panel-bd lobidrag">
                                            <div class="panel-heading">
                                                <div class="panel-title">
                                                    <h4></h4>
                                                </div>
                                            </div>
                                            <div class="panel-body">
                                                <form id="createnewleadform">

                                                    <div class="col-md-12 form-group editfield">
                                                        <label class="control-label"><h3 id="leadstageval"></h3></label>
                                                        <br>

                                                    </div>
                                                    <br>
                                                    <div class="form-group editfield">
                                                        <label></label>
                                                        <div id="leadstatus">
                                                        </div>
                                                        <input type="hidden" id="id">
                                                        <input type="hidden" id="leadstatushidden">
                                                        <input type="hidden" id="tempCentreJson">
                                                    </div>

                                                    <br>
                                                    <div id="dynamicformfields"></div>
                                                    <div class="reset-button">
                                                        <button id="createbtn" type="submit" class="btn btn-success">
                                                            Create
                                                        </button>
                                                        <button id="cancelbtn" type="button" class="btn btn-warning">
                                                            Close
                                                        </button>

                                                    </div>
                                                    <input type="hidden" id="hidjsonleadeatils">
                                                </form>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="lobidragdiv" class="col-xs-12 col-sm-4 col-md-4 col-lg-4">


                                        <div class="custompanel panel-bd lobidrag11 ">

                                            <div class="panel-heading">
                                                <div class="panel-title">
                                                    <h4><span>Log Event</span> <i title="Click Here Add A Log Event"
                                                                                  class="fa fa-plus-circle addnewevent"></i>
                                                        <i title="Click Here To Refresh"
                                                           class="fa fa-refresh refreshbtnaddnew" hidden></i></h4>
                                                </div>
                                            </div>
                                            <div class="panel-body">
                                                <div class="table-responsive customsidedivmenu">
                                                    <table class="table table-hover">

                                                        <tbody id="leadeventtbody">


                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="panel panel-bd lobidrag11 ">
                                            <div class="panel-heading">
                                                <div class="panel-title">
                                                    <h4><span>Attachments</span> <i title="Click Here Add Attachments"
                                                                                    class="fa fa-plus-circle addnewevent"></i>
                                                        <i title="Click Here To Refresh"
                                                           class="fa fa-refresh refreshbtnaddnew" hidden></i></h4>
                                                </div>
                                            </div>
                                            <div class="panel-body">
                                                <div class="table-responsive customsidedivmenu">
                                                    <table class="table table-hover">

                                                        <tbody id="attachmenttbody">


                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel panel-bd lobidrag11 ">
                                            <div class="panel-heading">
                                                <div class="panel-title">

                                                    <h4><span>Status Transfer</span> <i title="Click Here To Refresh"
                                                                                        class="fa fa-refresh refreshbtnaddnew"></i>
                                                    </h4>
                                                </div>
                                            </div>
                                            <div style="" class="panel-body">
                                                <div class="table-responsive customsidedivmenu">
                                                    <table class="table table-hover">

                                                        <tbody id="leadstatustranfertbody">


                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel panel-bd lobidrag11 ">
                                            <div class="panel-heading">
                                                <div class="panel-title">

                                                    <h4><span>Task</span> <i title="Click Here Add Task"
                                                                             class="fa fa-plus-circle addnewevent"></i>
                                                        <i title="Click Here To Refresh"
                                                           class="fa fa-refresh refreshbtnaddnew" hidden></i></h4>

                                                </div>
                                            </div>
                                            <div style="" class="panel-body">
                                                <div class="table-responsive customsidedivmenu">
                                                    <table class="table table-hover">

                                                        <tbody id="leadtasktbody">


                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel panel-bd lobidrag11 ">
                                            <div class="panel-heading">
                                                <div class="panel-title">

                                                    <h4><span>Ticket</span> <i title="Click Here Add Ticket"
                                                                               class="fa fa-plus-circle addnewevent"></i>
                                                        <i title="Click Here To Refresh"
                                                           class="fa fa-refresh refreshbtnaddnew" hidden></i></h4>

                                                </div>
                                            </div>
                                            <div style="" class="panel-body">
                                                <div class="table-responsive customsidedivmenu">
                                                    <input type="hidden" id="offsetleadticket" value="0">
                                                    <table class="table table-hover">


                                                        <tbody id="leadtickettbody">

                                                        <tr>
                                                            <td style="    text-align: center;
    font-weight: 700;
    cursor: pointer;" id="loadmoreleadticket">Load More
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel panel-bd lobidrag11 ">
                                            <div class="panel-heading">
                                                <div class="panel-title">

                                                    <h4><span>Transfer History</span>
                                                        <%if(UtilityClass.getCurrentUser().getFeatures().containsKey("Transfer_By_Role")){%>
                                                        <i title="Click Here Transfer This Lead" class="fa fa-plus-circle addnewevent"></i>
                                                        <%}%>

                                                        <i title="Click Here To Refresh" class="fa fa-refresh refreshbtnaddnew" hidden></i>
                                                    </h4>

                                                </div>
                                            </div>
                                            <div style="" class="panel-body">
                                                <div class="table-responsive customsidedivmenu">

                                                    <table class="table table-hover">


                                                        <tbody id="leadtransfertbody">


                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>


                            </div>

<%--====================================================== ASSESSMENT FORM FOR MAINSTREAM SCHOOL CHILDREN --%>
                            <div style="display: none" id="assessmentdiv_mainstream">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div id="assessmentformdiv_mainstream" class="panel panel-bd lobidrag">
                                            <div class="panel-heading">
                                                <div class="panel-title">
                                                    <h4>Mainstream School Assessement</h4>
                                                </div>
                                            </div>
                                            <div class="panel-body">
                                                <form id="assessmentform_mainstream">

                                                    <input type="hidden" id="leadid_mainstream">

                                                    <h4 style="text-align: center;font-weight: bold">Listening</h4>
                                                    <div id="mainlistening">

                                                    </div>
                                                    <h4 style="text-align: center;font-weight: bold">Oral Expression Speaking</h4>
                                                    <div id="mainloralexpression">

                                                    </div>
                                                    <h4 style="text-align: center;font-weight: bold">Basic Reading</h4>
                                                    <div id="mainbasicreading">

                                                    </div>
                                                    <h4 style="text-align: center;font-weight: bold">Reading Comprehension</h4>
                                                    <div id="mainreadingcomprehension">

                                                    </div>
                                                    <h4 style="text-align: center;font-weight: bold">Math Calculations</h4>
                                                    <div id="mainmathcalculations">

                                                    </div>
                                                    <h4 style="text-align: center;font-weight: bold">Math Reasoning</h4>
                                                    <div id="mainmathreasoning">

                                                    </div>
                                                    <h4 style="text-align: center;font-weight: bold">Written Expression</h4>
                                                    <div id="mainwrittenexpression">

                                                    </div>
                                                    <h4 style="text-align: center;font-weight: bold">Behavior</h4>
                                                    <div id="mainbehavior">

                                                    </div>


                                                <div class="reset-button">
                                                        <button id="createbtn_mainstream" type="submit" class="btn btn-success">
                                                            Update
                                                        </button>
                                                        <button id="cancelbtn_mainstream" type="button" class="btn btn-warning">
                                                            Close
                                                        </button>

                                                    </div>
                                                    <input type="hidden" id="hidassessmentjsonleadeatils_mainstream">
                                                </form>
                                            </div>
                                        </div>
                                    </div>


                                </div>


                            </div>
<%--====================================================== ASSESSMENT FORM FOR PRE SCHOOL CHILDREN--%>
                            <div style="display: none" id="assessmentdiv_preschool">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
                                        <div id="assessmentformdiv_preschool" class="panel panel-bd lobidrag">
                                            <div class="panel-heading">
                                                <div class="panel-title">
                                                    <h4>Pre School Assessement</h4>
                                                </div>
                                            </div>
                                            <div class="panel-body">
                                                <form id="assessmentform_preschool">

                                                    <input type="hidden" id="leadid_preschool">

                                                    <h4 style="text-align: center;font-weight: bold">Gross Motor Skills</h4>
                                                    <div id="pregrossmotorskills">

                                                    </div>
                                                    <h4 style="text-align: center;font-weight: bold">Fine Motor Skills</h4>
                                                    <div id="prefinemotorskills">

                                                    </div>
                                                    <h4 style="text-align: center;font-weight: bold">Self Help</h4>
                                                    <div id="preselfhelp">

                                                    </div>
                                                    <h4 style="text-align: center;font-weight: bold">Social Emotional</h4>
                                                    <div id="presocialemotional">

                                                    </div>
                                                    <h4 style="text-align: center;font-weight: bold">Cognitive</h4>
                                                    <div id="precognitive">

                                                    </div>
                                                    <h4 style="text-align: center;font-weight: bold">Communitcation</h4>
                                                    <div id="precommunication">

                                                    </div>
                                                    <h4 style="text-align: center;font-weight: bold">Behaviours</h4>
                                                    <div id="prebehaviors">
                                                    </div>
                                                    <div class="reset-button">
                                                        <button id="createbtn_preschool" type="submit" class="btn btn-success">
                                                            Update
                                                        </button>
                                                        <button id="cancelbtn_preschool" type="button" class="btn btn-warning">
                                                            Close
                                                        </button>

                                                    </div>
                                                    <input type="hidden" id="hidassessmentjsonleadeatils_preschool">
                                                </form>
                                            </div>
                                        </div>
                                    </div>


                                </div>


                            </div>
                            <!--======================================================ADD NEW Lead Type=================================================================== -->
                            <div class="modal fade" id="adFormModal" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header modal-header-primary">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                            <h3><i class="fa fa-user-circle m-r-5"></i>  Details</h3>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">

                                                <div class="col-xs-12 col-sm-12 col-md-12 m-b-20">
                                                    <!-- Nav tabs -->
                                                    <ul class="nav nav-tabs">
                                                        <li class="active"><a href="#detailsTab" id="detailsTabBtn" data-toggle="tab" aria-expanded="true" style="">Info</a></li>
                                                        <li class=""><a href="#questionsTab" data-toggle="tab" aria-expanded="false" style="">Questions</a></li>
                                                    </ul>
                                                    <!-- Tab panels -->
                                                    <div class="tab-content">
                                                        <div class="tab-pane fade active in" id="detailsTab">
                                                            <div class="panel-body">

                                                                    <div class="table-responsive">
                                                                        <table style="display: block;max-height: 300px;overflow-y: scroll;" class="table table-bordered table-striped table-hover"
                                                                               width="100%">
                                                                            <thead>
                                                                            <tr class="info">
                                                                                <th width="10%">Name</th>
                                                                                <th width="100%">Value</th>
                                                                            </tr>
                                                                            </thead>
                                                                            <tbody id="adFormTableBody" >

                                                                            </tbody>
                                                                        </table>
                                                                    </div>

                                                            </div>
                                                        </div>
                                                        <div class="tab-pane fade" id="questionsTab">
                                                            <div class="panel-body">

                                                                <div class="table-responsive">
                                                                    <table style="display: block;max-height: 300px;overflow-y: scroll;" class="table table-bordered table-striped table-hover"
                                                                           width="100%">
                                                                        <thead>
                                                                        <tr class="info">
                                                                            <th width="10%">Type</th>
                                                                            <th width="100%">Value</th>
                                                                        </tr>
                                                                        </thead>
                                                                        <tbody id="questionTableBody" >

                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>


                                    </div>
                                    <!-- /.modal-content -->
                                    <%--=========================================================================================================================================--%>
                                </div>
                                <!-- /.modal-dialog -->Lead
                            </div>
<!--======================================================ADD NEW Lead Type=================================================================== -->
                            <div class="modal fade" id="centrehelpmodal" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header modal-header-primary">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                            <h3><i class="fa fa-times-circle m-r-5"></i> Centre Timings</h3>
                                           </div>
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="col-md-5 form-group">
                                                        <input type="text" min=""  maxlength="100" placeholder="Centre's Pincode"  id="temppincode" class="form-control" required>
                                                    </div>
                                                    <div class="col-md-5 form-group">

                                                        <button class="btn btn-primary" id="searchcentrebtn">Search</button>
                                                    </div>
                                                    <hr>

                                                   </div>
                                                <div class="col-md-12" id="contentdiv">
                                                    <div class="table-responsive">
                                                        <table id="centretable" style="display: block;max-height: 300px;overflow-y: scroll;" class="table table-bordered table-striped table-hover"
                                                               width="100%">
                                                            <thead>
                                                            <tr class="info">
                                                                <th width="10%">Select</th>
                                                                <th width="100%">Timings</th>
                                                                <th width="100%">Name</th>
                                                                <th width="100%">City</th>
                                                                <th width="100%">State</th>
                                                                <th width="100%">Contact</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody id="timingtbody" >

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-primary pull-left" id="selectcentrebtn" >Save</button>
                                            <button type="button" class="btn btn-danger pull-right" id="formCloseBtn2" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                    <%--=========================================================================================================================================--%>
                                </div>
                                <!-- /.modal-dialog -->Lead
                            </div>
<!-- /.modal -->

                            <!--======================================================ADD NEW Lead Type=================================================================== -->
                            <div class="modal fade" id="bulksourcechangemodal" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header modal-header-primary">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                            <h3><i class="fa fa-times-circle m-r-5"></i> Change Lead Source</h3>
                                        </div>
                                        <div class="modal-body form-group">
                                            <div class="row">

                                                <div class="col-md-12" >
                                                    <select class="form-control" id="leadSourceSelect" required>

                                                    </select>
                                                </div>

                                            </div>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-primary pull-left" id="updatesourcebtn" >Update</button>
                                            <button type="button" class="btn btn-danger pull-right" id="bulkSourceChangeCloseBtn" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                    <%--=========================================================================================================================================--%>
                                </div>
                                <!-- /.modal-dialog -->Lead
                            </div>
                            <div class="modal fade" id="bulkprogramchangemodal" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header modal-header-primary">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                            <h3><i class="fa fa-times-circle m-r-5"></i> Change Lead Program</h3>
                                        </div>
                                        <div class="modal-body form-group">
                                            <div class="row">

                                                <div class="col-md-12" >
                                                    <select class="form-control" id="leadProgramSelect" required>

                                                    </select>
                                                </div>

                                            </div>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-primary pull-left" id="updateprogrambtn" >Update</button>
                                            <button type="button" class="btn btn-danger pull-right" id="bulkProgramChangeCloseBtn" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                    <%--=========================================================================================================================================--%>
                                </div>
                                <!-- /.modal-dialog -->Lead
                            </div>
                            <!-- /.modal -->


                            <div class="modal fade" id="viewTiming" tabindex="-1" role="dialog" aria-hidden="true">
                                <div style="width: 100%" class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header modal-header-primary">
                                            <button type="button" class="close" onclick="clearData()" data-dismiss="modal"
                                                    aria-hidden="true">×
                                            </button>
                                            <h3><i class="fa fa-clock-o m-r-5"></i>Center Timing</h3>
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
                                                                            <tbody id="centretimingtbody">

                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                        </fieldset>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-danger pull-right" id="formCloseBtn3"
                                                    data-dismiss="modal">Close
                                            </button>
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                    <%--=========================================================================================================================================--%>
                                </div>
                                <!-- /.modal-dialog -->
                            </div>

                            <div class="modal fade" id="reviewmodal" role="dialog">
                                <div class="modal-dialog modal-lg">

                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>

                                        </div>
                                        <div style="height: 800px;" class="modal-body">
                                            <form class="form-horizontal" id="reviewform">
                                                <fieldset>

                                                    <input id="hidreviewjson" type="hidden">
                                                    <input id="hidreviewleadid" type="hidden">
                                                    <div class="col-md-12 form-group">
                                                        <label class="control-label">How is your child doing, is he enjoying the program and strategies given by the therapist?</label>
                                                        <textarea type="text" maxlength="255" placeholder="Please Provide Parent Input"  id="howsChildDoing" class="form-control" ></textarea>
                                                    </div>

                                                    <div class="col-md-12 form-group">
                                                        <label class="control-label">How about you, are you facing any challenges in terms practising the activities or skills.</label>
                                                        <textarea type="text" maxlength="255" placeholder="Please Provide Parent Input"  id="facingAnyChallenges" class="form-control" ></textarea>
                                                    </div>

                                                    <div class="col-md-8 form-group">
                                                        <label class="control-label">Are you interested in an in-house counsellor who can help you?</label>
                                                        <div class="form-check-inline">
                                                            <label class="form-check-label">
                                                                <input type="radio" class="form-check-input" name="interestedInCounsellor" value="1" >Yes
                                                            </label>
                                                        </div>
                                                        <div class="form-check-inline">
                                                            <label class="form-check-label">
                                                                <input type="radio" class="form-check-input" name="interestedInCounsellor" value="0">No
                                                            </label>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-12 form-group">
                                                        <label class="control-label">what 2- 3 value add or service that we should start to help you more in your day-to-day life.</label>
                                                        <input type="text" maxlength="255" placeholder="Please Provide Input"  id="suggestedService1" class="form-control" >
                                                        <br>
                                                        <input type="text" maxlength="255" placeholder="Please Provide Input"  id="suggestedService2" class="form-control" >
                                                        <br>
                                                        <input type="text" maxlength="255" placeholder="Please Provide Input"  id="suggestedService3" class="form-control" >
                                                    </div>

                                                    <div class="form-group col-md-12">
                                                        <label>Will you recommend us to anyone? (Rate us on the scale of 1 to 10, 1 being lowest and 10 being highest)</label>
                                                        <select id="recommendationScore" name="recommendationScore" class="form-control">
                                                            <option value="0">--- Select Value ---</option>
                                                            <option value="1">1</option>
                                                            <option value="2">2</option>
                                                            <option value="3">3</option>
                                                            <option value="4">4</option>
                                                            <option value="5">5</option>
                                                            <option value="6">6</option>
                                                            <option value="7">7</option>
                                                            <option value="8">8</option>
                                                            <option value="9">9</option>
                                                            <option value="10">10</option>
                                                          </select>
                                                    </div>

                                                    <div class="form-group col-md-12">
                                                        <label>How do you see the impact on child with the intervention provided? (Rate us on the scale of 1 to 10, 1 being lowest and 10 being highest)</label>
                                                        <select id="impactScore" name="impactScore" class="form-control">
                                                            <option value="0">--- Select Value ---</option>
                                                            <option value="1">1</option>
                                                            <option value="2">2</option>
                                                            <option value="3">3</option>
                                                            <option value="4">4</option>
                                                            <option value="5">5</option>
                                                            <option value="6">6</option>
                                                            <option value="7">7</option>
                                                            <option value="8">8</option>
                                                            <option value="9">9</option>
                                                            <option value="10">10</option>
                                                        </select>
                                                    </div>

                                                    <div class="form-group col-md-12">
                                                        <label>How would you rate our therapist? (Rate us on the scale of 1 to 10, 1 being lowest and 10 being highest)</label>
                                                        <select id="therapistScore" name="therapistScore" class="form-control">
                                                            <option value="0">--- Select Value ---</option>
                                                            <option value="1">1</option>
                                                            <option value="2">2</option>
                                                            <option value="3">3</option>
                                                            <option value="4">4</option>
                                                            <option value="5">5</option>
                                                            <option value="6">6</option>
                                                            <option value="7">7</option>
                                                            <option value="8">8</option>
                                                            <option value="9">9</option>
                                                            <option value="10">10</option>
                                                        </select>
                                                    </div>

                                                    <div class="col-md-12 form-group user-form-group">
                                                        <div class="pull-right">
                                                            <button type="submit" class="btn btn-add pull-right" >
                                                                Save
                                                            </button>

                                                            <button style="margin-right: 10px;" type="button" onclick="hideReviewModal()" class="btn btn-danger pull-right" >
                                                                Close
                                                            </button>
                                                        </div>
                                                    </div>


                                                </fieldset>
                                            </form>
                                        </div>

                                    </div>

                                </div>
                            </div>

<%--====================================================== LEAD TABLE ======================== --%>

                            <div id="tablediv">

                                <div class="row">
                                    <button class="btn btn-add customheaderbtn" type="button" id="filtersearch"
                                            title="Search lead using custom filter">
                                        Filter/Search <i class="hvr-buzz-out fa fa-search"></i></button>
                                    <button style="" id="removefilter" class="btn btn-add customheaderbtn"
                                            title="Clear custom filter setting"><i
                                            class="hvr-buzz-out fa fa-ban"></i> Clear Filter
                                    </button>

                                    <%if(UtilityClass.getCurrentUser().getFeatures().containsKey("Transfer_By_Role")){%>
                                    <span class="bulkTransferDiv" hidden>
                                      <button style="" id="bulkTransfer" class="btn btn-add customheaderbtn"
                                              title="Transfer selected leads" ><i
                                              class="hvr-buzz-out fa fa-exchange"></i> Transfer Lead
                                      </button>
                                      </span>

                                    <%}%>


                                    <%if(UtilityClass.getCurrentUser().getFeatures().containsKey("SourceChange_By_Role")){%>
                                    <span class="bulkTransferDiv" hidden>
                                      <button style="" id="bulkSourceChange" class="btn btn-add customheaderbtn" data-toggle='modal' data-target='#bulksourcechangemodal'
                                              title="Change Source of selected leads" ><i
                                              class="hvr-buzz-out fa fa-exchange"></i> Change Source
                                      </button>
                                      </span>

                                    <%}%>

                                    <%if(UtilityClass.getCurrentUser().getFeatures().containsKey("ProgramChange_By_Role")){%>
                                    <span class="bulkTransferDiv" hidden>
                                      <button style="" id="bulkProgramChange" class="btn btn-add customheaderbtn" data-toggle='modal' data-target='#bulkprogramchangemodal'
                                              title="Change Program of selected leads" ><i
                                              class="hvr-buzz-out fa fa-exchange"></i> Change Program
                                      </button>
                                      </span>

                                    <%}%>

                                    <span class="bulkTransferDiv" hidden>
                                     <button style="" id="bulkTrash" class="btn btn-danger customheaderbtn"
                                              title="Transfer selected leads to trash" ><i
                                              class="hvr-buzz-out fa fa-trash"></i> Move To Trash
                                      </button>
                                      </span>

                                    <%if(UtilityClass.getCurrentUser().getFeatures().containsKey("Download_By_Role")){%>
                                    <button style="" id="downloadreport" class="btn btn-add customheaderbtn"
                                            title="Download Lead Report" hidden><i
                                            class="hvr-buzz-out fa fa-file-excel-o"></i> Download Report
                                    </button>

                                    <%}%>


                                    <%if(UtilityClass.getCurrentUser().getFeatures().containsKey("Download_By_Role")){%>
                                    <button style="" id="downloadassessmentreport" class="btn btn-add customheaderbtn"
                                            title="Download Assessment Report" hidden><i
                                            class="hvr-buzz-out fa fa-file-excel-o"></i> Download Assessment Report
                                    </button>

                                    <%}%>

                                    <%if(UtilityClass.getCurrentUser().getFeatures().containsKey("Download_Review_By_Role")){%>
                                    <button style="" id="downloadreviewreport" class="btn btn-add customheaderbtn"
                                            title="Download Review Report" hidden><i
                                            class="hvr-buzz-out fa fa-file-excel-o"></i> Download Review Report
                                    </button>

                                    <%}%>


                                </div>

                                <div>
                                    <div class="table-responsive">
                                        <table id="table" class="table table-bordered table-striped table-hover"
                                               width="100%">
                                            <thead>
                                            <tr class="info">
                                                <th><input title="Select all leads" type="checkbox" id="selectAllLead"></th>
                                                <th>Lead Id</th>
                                                <th>Parent name</th>
                                                <th>Child name</th>
                                                <th>Lead Type</th>
                                                <th>Lead Owner</th>
                                                <th>Phone Number</th>
                                                <th>Email</th>
                                                <th>Program</th>
                                                <th>Lead Stage</th>
                                                <th>Lead Status</th>
                                                <th>Lead Source</th>
                                                <th>Call Status</th>
                                                <th>Convert</th>
                                                <th>Lead Date</th>
                                                <th>Create Date</th>
                                                <th>Create By</th>
                                                <th>Update Date</th>
                                                <th>Update By</th>
                                                <th>Count</th>
                                                <th>C2C Attempts</th>
                                                <th>Lead Score</th>
                                                <th>Campaign Name</th>
                                                <th>Keyword</th>
                                                <th>PaymentStatus</th>
                                                <th>Otp Status</th>
                                                <th>Action</th>
                                            </tr>
                                            </thead>
                                            <tbody id="tabletbody">

                                            </tbody>
                                        </table>
                                    </div>
                                </div>


                            </div>
                            <!--======================================================ADD NEW ROLE=================================================================== -->
                            <div class="modal fade" id="clinicalCallTimeModal" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header modal-header-primary">
                                            <h3><i class="fa fa-plus m-r-5"></i> Book Appointment</h3>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <form class="form-horizontal" id="clinicalCallTimeForm">
                                                        <fieldset>

                                                            <div class="col-md-8 form-group">
                                                                <label class="control-label">Date</label>
                                                                <input type="date" min=""  maxlength="100" placeholder="Feature Name"  id="appointmentDate" class="form-control" required>
                                                            </div>
                                                            <!-- Text input-->
                                                            <div class="col-md-8 form-group">
                                                                <label class="control-label">Time Slot</label>
                                                                <select class="form-control" id="appointmendSlot" required>
                                                                    <option value="">Select Time Slot</option>
                                                                    <option value="09 AM to 12 PM"> 09 AM to 12 PM </option>
                                                                    <option value="12 PM to 03 PM"> 12 PM to 03 PM </option>
                                                                    <option value="03 PM to 06 PM"> 03 PM to 06 PM </option>
                                                                </select>
                                                            </div>
                                                            <div class="col-md-12 form-group user-form-group">
                                                                <div class="pull-left">
                                                                    <%--                                                    <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal">Cancel</button>--%>
                                                                    <button type="submit" class="btn btn-add btn-sm" id="createBtn0">Set</button>
                                                                </div>
                                                            </div>
                                                        </fieldset>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-danger pull-right" id="formCloseBtn" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                    <%--=========================================================================================================================================--%>
                                </div>
                                <!-- /.modal-dialog -->
                            </div>
                            <!-- /.modal -->

                        </div>
                        <div class="panel-footer">

                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" value="0" id="hidjsonstageandstatus">
            <select style="display: none" id="userlist">

            </select>
            <ul class='custom-menu'>
                <input type="hidden" id="hidjson">
                <li data-action="edit">Update Lead <i class="hvr-buzz-out fa fa-pencil-square"></i></li>
                <li data-action="addnotes">Add Notes<i class="hvr-buzz-out fa fa-sticky-note"></i></li>
                <li data-action="assess">Do Assessment <i class="hvr-buzz-out fa fa-stethoscope"></i></li>
                <li data-action="review">Review  <i class="hvr-buzz-out fa fa-star"></i></li>
                <li data-action="quicktask">Quick Task <i class="hvr-buzz-out fa fa-tasks"></i></li>
                <li data-action="addattachement">Add Attachment <i class="hvr-buzz-out fa fa-file-archive-o"></i></li>
                <li data-action="logevent">Log Event <i class="hvr-buzz-out fa fa-sticky-note-o"></i></li>
                <%if(UtilityClass.getCurrentUser().getFeatures().containsKey("Transfer_By_Role")){%>
                <li data-action="leadtransfer">Lead Transfer<i class="hvr-buzz-out fa fa-exchange"></i></li>
                <%}%>
                <li data-action="leadticket">Generate Ticket <i class="hvr-buzz-out fa fa-ticket"></i></li>
                <li data-action="clicktocall">Click To Call <i class="hvr-buzz-out fa fa-phone"></i></li>
                <li data-action="quickcampaign">Quick Campaign<i class="hvr-buzz-out fa fa-bullhorn"></i></li>

            </ul>
        </section>
        <select style="display: none" id="leadstagehidden"></select>

        <!-- /.content -->
    </div>
    <select style="display: none" id="ticketdept"></select>
    <select style="display: none" id="type"></select>
    <select style="display: none" id="type"></select>
    <select style="display: none" id="priority"></select>

    <!-- /.content-wrapper -->
    <!-- /.content-wrapper -->
    <footer class="main-footer">
        <jsp:include page="common/footer.jsp"></jsp:include>
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

<script src="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.min.js"
        type="text/javascript"></script>

<%--<script src="https://cdn.jsdelivr.net/npm/file-saver@2.0.2/dist/FileSaver.min.js"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/jszip.js"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/xlsx.js"></script>--%>
<script src="${pageContext.request.contextPath}/js/xlsx.mini.js"></script>
<script src="${pageContext.request.contextPath}/js/jszip.js"></script>
<script src="${pageContext.request.contextPath}/js/FileSaver.min.js"></script>


<!-- End Core Plugins
   =====================================================================-->
<!-- Start Theme label Script
   =====================================================================-->
<!-- Dashboard js -->
<script src="${pageContext.request.contextPath}/assets/dist/js/dashboard.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/datetimepicker/jquery.datetimepicker.full.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/externalform.js"
        type="text/javascript"></script>
<!-- End Theme label Script
   =====================================================================-->
<script>

//===== STORING LOGIN URL FOR EXTERNALFORM.JS (JS FILES CANT ACCESS PAGECONTEXT)
var tempElem;
function storeLoginUrl(){
    tempElem = document.createElement("textarea");
    tempElem.value = "${pageContext.request.contextPath}/login";
}
//=============
storeLoginUrl();
    var quill = null;

    $(document).on("click","#checkmailquick",function (){


            $('.whatsappdiv').css("display","none")
            $('.msgshow').css("display","none")
            $('.subjectdiv').fadeIn();
            $("#quillmsgdiv").css("display","block")
            $("#plainmsgdiv").css("display","none")

    })
    $(document).on("click","#checkmsgquick",function (){


        $('.whatsappdiv').css("display","none")
        $('.subjectdiv').css("display","none")
        $('.msgshow').fadeIn();
        $("#quillmsgdiv").css("display","none")
        $("#plainmsgdiv").css("display","block")

    })
    $(document).on("click","#whatsappquick",function (){


        $('.msgshow').css("display","none")
        $('.subjectdiv').fadeIn();
        $('.whatsappdiv').fadeIn();
        $("#quillmsgdiv").css("display","none")
        $("#plainmsgdiv").css("display","block")

    })

    //================================================================== DATA TABLE ===========================================
    var existingData={};
    var promise = new Promise(function (resolve, reject) {

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url : "${pageContext.request.contextPath}/json/field.json?test=false",
            success: function (json) {
                existingData=json;
                resolve(json)
            },
            error: function (err) {
                console.log(err)
            }
        });


    }).then(function (mainJson) {
        var arrProduct=[];
        arrProduct.push("<option value=''>---Select Program---</option>");
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/product/getallavailable",
            success: function (json) {
                var html = "";

                for (var i = 0; i < json.length; i++) {

                    arrProduct.push("<option unitprice='"+json[i].price+"' title='" + json[i].description + "' value='" + json[i].name + "'>" + json[i].name + "  (" + json[i].price + ")</option> ")
                    html = html + "<option unitprice='"+json[i].price+"' title='" + json[i].description + "' value='" + json[i].name + "'>" + json[i].name + "  (" + json[i].price + ")</option> ";

                }
                $('#product').html(html)
                console.log("loaded program")
                mainJson["interestedProduct"]["options"]=arrProduct;
              //  console.log(mainJson);

                var arrLeadsource=[];
                arrLeadsource.push("<option value=''>---Select Source---</option>");
                $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "${pageContext.servletContext.contextPath}/api/leadsource/getactive",
                    success: function (json) {
                        var html = "";
                        for (var i = 0; i < json.length; i++) {

                            arrLeadsource.push("<option hidstage='"+json[i].initialStage+"' hidstatus='"+json[i].initialStatus+"' title='" + json[i].description + "' value='" + json[i].name + "'>" + json[i].name + "</option> ")
                            html = html + "<option hidstage='"+json[i].initialStage+"' hidstatus='"+json[i].initialStatus+"' title='" + json[i].description + "' value='" + json[i].name + "'>" + json[i].name + "</option> ";

                        }
                        $('#leadsource').html(html)

                        mainJson["leadSource"]["options"]=arrLeadsource;
                        var arrLeadType=[];
                        arrLeadType.push("<option value=''>---Select Type---</option>");
                        $.ajax({
                            type: "GET",
                            contentType: "application/json",
                            url: "${pageContext.servletContext.contextPath}/api/leadtype/getactive",
                            success: function (json) {
                                var html = "";
                                for (var i = 0; i < json.length; i++) {

                                    arrLeadType.push("<option title='" + json[i].description + "' value='" + json[i].name + "'>" + json[i].name + "</option> ")
                                    html = html + "<option title='" + json[i].description + "' value='" + json[i].name + "'>" + json[i].name + "</option> ";

                                }
                                $('#leadtype').html(html);

                                mainJson["clientType"]["options"]=arrLeadType;
                                let arrUserTeam = [];
                                arrUserTeam.push("<option value=''>---Select---</option>");// THIS IS WORKING ACTUALLY
                                $.ajax({
                                    type: "GET",
                                    contentType: "application/json",
                                    //url: "${pageContext.servletContext.contextPath}/api/user/getusermyteam",
                                    url: "${pageContext.servletContext.contextPath}/api/user/getallactive",
                                    success: function (json) {
                                        var html = "";
                                        for (var i = 0; i < json.length; i++) {

                                            arrUserTeam.push("<option title='" + json[i].firstName + "' value='" + json[i].username + "'>" + json[i].firstName + " " + json[i].lastName +" ("+ json[i].username + ")</option> ")
                                            html = html + "<option title='" + json[i].firstName + "' value='" + json[i].username + "'>" + json[i].firstName + " " + json[i].lastName +" ("+ json[i].username + ")</option> ";

                                        }
                                        $('#pgBdName').html(html);
                                        $('#professionFilled').html(html);
                                        $('#childPlayPattern').html(html);
                                        $('#pgBdManager').html(html);

                                        mainJson["pgBdName"]["options"]=arrUserTeam;
                                        mainJson["professionFilled"]["options"]=arrUserTeam;
                                        mainJson["childPlayPattern"]["options"]=arrUserTeam;
                                        mainJson["pgBdManager"]["options"]=arrUserTeam;

                                        //======================================= FETCHING STATES ===========
                                        let arrCountries = [];
                                        arrCountries.push("<option value=''>---Select---</option>");// THIS IS WORKING ACTUALLY
                                        $.ajax({
                                            type: "GET",
                                            contentType: "application/json",
                                            //url: "${pageContext.servletContext.contextPath}/api/user/getusermyteam",
                                            url: "${pageContext.servletContext.contextPath}/api/mbops/countries/getall",
                                            success: function (json) {
                                                var html = "";
                                                for (var i = 0; i < json.length; i++) {

                                                    arrCountries.push("<option title='" + json[i].name + "' value='" + json[i].id + "'>" + json[i].name +  "</option> ")
                                                    html = html + "<option title='" + json[i].name + "' value='" + json[i].id + "'>" + json[i].name +  "</option> ";

                                                }
                                                $('#country').html(html);


                                                mainJson["country"]["options"]=arrCountries;

                                                initLeadForm(mainJson)
                                                existingData=mainJson;




                                            },
                                            error: function (err) {

                                            }

                                        });

                                        initLeadForm(mainJson)
                                        existingData=mainJson;




                                    },
                                    error: function (err) {

                                    }

                                });





                            },
                            error: function (err) {

                            }

                        });



                    },
                    error: function (err) {

                    }

                });





            },
            error: function (err) {

            }

        });

    }, function (errorMessage) {
        console.log(errorMessage);
    })
// DOT ALLOW ANGULAR BRACKETS IN TEXTAREA, IT BREAKS HTML
$(document).on("keyup","textarea",function (){
    let ths = $(this)
    let value = ths.val();
    value = value.replace("'","");
    value = value.replace("<",",");
    value = value.replace(">",",");
    ths.val(value);
})

// DOT ALLOW ANGULAR BRACKETS IN TEXTAREA, IT BREAKS HTML
$(document).on("keyup","#city",function (){
    let ths = $(this)
    let value = ths.val();
    value = value.replace(/[0-9]/g, "");//replace all numeric values
    value = value.replace(/[&\/\\#,!@^\-_=`+()$~%.'":*?<>{}]/g,'');//replace all numeric and specials characters

    ths.val(value);
})

    function initLeadForm(leadOtherFormJson) {
        //console.log(leadOtherFormJson)
        console.log("loading form")
        var leadformhtml="";

        for (var key in leadOtherFormJson) {

            var keyObject=leadOtherFormJson[key];

            if(keyObject["visible"]) {  // checking if visible

                var sizeClass = "col-md-12";
                var required="required";
                var disabled="";
                var requiredsymbole="<i class='mandatory fa fa-asterisk'></i>"

                if(keyObject["fieldName"] == "Pincode"){
                    requiredsymbole="<i class='mandatory fa fa-asterisk'></i>   <i id='centrehelp' data-toggle='modal' data-target='#centrehelpmodal' class='hvr-buzz-out fa fa-info-circle'></i>"
                }

                if (keyObject["size"] == "half") {
                    sizeClass = "col-md-6";
                }
                if (!keyObject["required"]) {
                    required = "";
                    requiredsymbole="";
                }

                if (keyObject["disabled"] == true) {
                    disabled = "disabled";
                }

            }
            if(keyObject["visible"]) {
                switch (keyObject["type"]) {
                    case "text":
                        leadformhtml = leadformhtml + "<div class=\"" + sizeClass + " form-group\">\n" +
                            "                                                        <label class=\"control-label\">" + keyObject["fieldName"] + "</label>" + requiredsymbole + "<input type='text' placeholder='" + keyObject["placeholder"] + "' class=\"form-control\" id='" + key + "' " + required + " "+disabled+"> </div>";

                        break;
                    case "textarea":
                        leadformhtml = leadformhtml + "<div class=\"" + sizeClass + " form-group\">\n" +
                            "                                                        <label class=\"control-label\">" + keyObject["fieldName"] + "</label>" + requiredsymbole + "<textarea type='text' rows='6' maxlength='999' placeholder='" + keyObject["placeholder"] + "' class=\"form-control\" id='" + key + "' " + required + " "+disabled+ "> </textarea></div>";

                        break;
                    case "select":
                        var optionList = "";
                        for (var i = 0; i < keyObject["options"].length; i++) {
                            optionList = optionList + keyObject["options"][i];
                        }
                        leadformhtml = leadformhtml + "<div class=\"" + sizeClass + " form-group\">\n" +
                            "                                                        <label class=\"control-label\">" + keyObject["fieldName"] + "</label>" + requiredsymbole + "<select type='text' placeholder='" + keyObject["placeholder"] + "' class=\"form-control\" id='" + key + "' " + required + " "+disabled+ "> " + optionList + "</select></div>";

                        break;
                    case "multi-select":
                        var optionList = "";
                        for (var i = 0; i < keyObject["options"].length; i++) {
                            optionList = optionList + keyObject["options"][i];
                        }
                        leadformhtml = leadformhtml + "<div class=\"" + sizeClass + " form-group\">\n" +
                            "                                                        <label class=\"control-label\">" + keyObject["fieldName"] + "</label>" + requiredsymbole + "<select multiple type='text' placeholder='" + keyObject["placeholder"] + "' class=\"form-control\" id='" + key + "' " + required + " "+disabled+ "> " + optionList + "</select></div>";

                        break;
                    case "checkbox":
                        var optionList = "";
                        for (var i = 0; i < keyObject["options"].length; i++) {
                            optionList = optionList + keyObject["options"][i];
                        }
                        leadformhtml = leadformhtml + "<div class=\"" + sizeClass + " form-group\">\n" +
                            "                                                        <label class=\"control-label\">" + keyObject["fieldName"] + "</label>" + requiredsymbole + "<br>" + optionList + "</div>";
                        break;
                    case "radio":
                        var optionList = "";
                        for (var i = 0; i < keyObject["options"].length; i++) {
                            optionList = optionList + keyObject["options"][i];
                        }
                        leadformhtml = leadformhtml + "<div class=\"" + sizeClass + " form-group\">\n" +
                            "                                                        <label class=\"control-label\">" + keyObject["fieldName"] + "</label>" + requiredsymbole + "<br>" + optionList + "</div>";
                        break;
                    case "date":
                        leadformhtml = leadformhtml + "<div class=\"" + sizeClass + " form-group\">\n" +
                            "                                                        <label class=\"control-label\">" + keyObject["fieldName"] + "</label>" + requiredsymbole + "<input type='date' placeholder='" + keyObject["placeholder"] + "' class=\"form-control\" id='" + key + "' " + required + " "+disabled+ "> </div>";

                        break;
                    case "datetime-local":
                        leadformhtml = leadformhtml + "<div class=\"" + sizeClass + " form-group\">\n" +
                            "                                                        <label class=\"control-label\">" + keyObject["fieldName"] + "</label>" + requiredsymbole + "<input type='datetime-local' placeholder='" + keyObject["placeholder"] + "' class=\"form-control\" id='" + key + "' " + required + " "+disabled+ "> </div>";

                        break;
                    case "email":
                        leadformhtml = leadformhtml + "<div class=\"" + sizeClass + " form-group\">\n" +
                            "                                                        <label class=\"control-label\">" + keyObject["fieldName"] + "</label>" + requiredsymbole + "<input type='email' placeholder='" + keyObject["placeholder"] + "' class=\"form-control\" id='" + key + "' " + required + " "+disabled+ "> </div>";

                        break;

                    case "url":
                        leadformhtml = leadformhtml + "<div class=\"" + sizeClass + " form-group\">\n" +
                            "                                                        <label class=\"control-label\">" + keyObject["fieldName"] + "</label>" + requiredsymbole + "<input type='url' placeholder='" + keyObject["placeholder"] + "' class=\"form-control\" id='" + key + "' " + required + " "+disabled+ "> </div>";

                        break;

                    case "number":
                        leadformhtml = leadformhtml + "<div class=\"" + sizeClass + " form-group\">\n" +
                            "                                                        <label class=\"control-label\">" + keyObject["fieldName"] + "</label>" + requiredsymbole + "<input type='number' min='0' value='0' placeholder='" + keyObject["placeholder"] + "' class=\"form-control\" id='" + key + "' " + required + " "+disabled+ "> </div>";

                        break;

                    default:
                        //console.log("Cannot be Found " + key)
                        break;


                }
            }
            else {
                switch (keyObject["type"]) {
                    case "text":
                        leadformhtml = leadformhtml + "<input type='hidden' id='" + key + "' >";

                        break;
                        default:
                       // console.log("Cannot be Found " + key)
                        break;


                }
            }
        }

        $('#dynamicformfields').html(leadformhtml)
    }


  //  initLeadForm()
    const applicationPrefix = '<%=UtilityClass.ApplicationPrefix%>';
    var table = null;

    function loaddataintotable() {
        var searchParams = new URLSearchParams(window.location.search);
        //var leadstatus = searchParams.get('statusValue') || 0;
        var leadstatus = searchParams.get('statusValue') || 0;
        var leadsource = searchParams.get('leadSource') || 0;
        var product = searchParams.get('product') || 0;
        var leadtype = searchParams.get('leadType') || 0;
        var userfilter = searchParams.get('userFilter') || 0;
        var datefilter = searchParams.get('datefilter') || false;
        var customdate = searchParams.get('datevalue') || 0;
        var searchstring = searchParams.get('searchvalue') || 0;
        var fieldArray = searchParams.get('fieldArray') || 0;
        var leadstage = searchParams.get('leadstage') || 0;
        var innersource = searchParams.get('innersource') || 0;
        var leadpriority = searchParams.get('leadpriority') || 0;
        var leadids = searchParams.get('leadids') || 0;
        let dateTypeFlag=searchParams.get("dateTypeFlag")||4;//4 means LMS entry date
        let userflag=searchParams.get("userflag")||0;//0 means for selected user only,1 means for selected user's team members

        var id = searchParams.get('id') || 0;
        var datefiltertype = searchParams.get('datefiltertype') || "createdate";
        if(dateTypeFlag == 1){
            datefiltertype = "leaddate"
        }else if(dateTypeFlag == 2){
            datefiltertype = "convertdate"
        }else if(dateTypeFlag == 3){
            datefiltertype = "updatedate"
        }else if(dateTypeFlag == 4){
            datefiltertype = "createdate"
        }else if(dateTypeFlag == 5){
            datefiltertype = "profilingdate"
        }
        // THIS LOGIC BELOW IS FOR LEAD STATS SAME DATE
        //REQUIREMENT IS - ALL RESULTS, ASSIGNED,PROFILED,CONVERTED,REJECTCED AND PENDING
        //HAS TO BE CALCULATED BASED ON  dateFilter (date on which lead was entered in the LMS) AND profilingAgentId
        //
        else if(dateTypeFlag == 6){
            datefiltertype = "profilingdatecustom"
        }
        else if(dateTypeFlag == 7){
            datefiltertype = "appointmentdate"
        }
        //DATE ON WHICH ASSESSMENT FOR WAS SUBMITTED
        else if(dateTypeFlag == 8){
            datefiltertype = "assessmentdate"
        }


        var url = "datefiltertype=" + datefiltertype + "&innersource=" + innersource + "&statusValue=" + leadstatus + "&leadSource=" + leadsource + "&product=" + product + "&leadType=" + leadtype + "&userFilter=" + userfilter + "&datefilter=" + datefilter + "&datevalue=" + customdate + "&searchvalue=" + searchstring + "&leadstage=" + leadstage + "&id=" + id +"&leadpriority=" + leadpriority +"&fieldArray="+fieldArray+"&leadids="+leadids+"&userflag="+userflag;


        table = $('#table').DataTable({
            ajax: {
                url: '${pageContext.servletContext.contextPath}/api/lead/getlead?' + url,
                dataSrc: 'data',
                deferRender: true,

                "data": {
                    "flag": 1,


                }
            },
            "columns": [

                {
                    "mRender": function (data, type, row) {


                        //return "<a hidatr='" + JSON.stringify(row) + "' style='display: none'></a><span></span>";
                        return "<a class='leadData' style='display: none'>" + JSON.stringify(row) + "</a>"+"<input class='leadSelect' type='checkbox'>";


                    }
                },
                {
                    "mRender": function (data, type, row) {


                        return applicationPrefix + row.id+"\n"+"<i title='View Lead Menu' class=\"hvr-buzz-out fa fa-info-circle\" data-toggle='modal' data-target='#adFormModal' onclick='showAdForm("+row.adFormData+")'></i>";


                    }
                },
                {"data": "parentName"},
                {"data": "childName"},
                {"data": "clientType"},
                {"data": "username"},
                {"data": "phonenumber"},
                {"data": "email"},
                {"data": "interestedProduct"},
                {"data": "leadStage"},
                {"data": "leadStatus"},
                {"data": "leadSource"},
                {"data": "callStatus"},

                {
                    "mRender": function (data, type, row) {


                        if (row.leadConvert == true) {
                            return "<i style='color:green' class=\"hvr-buzz-out fa fa-check\"></i> @" + row.convertDate + ""

                        } else {
                            return "<i style='color:red' class=\"hvr-buzz-out fa fa-close\"></i>"
                        }
                        

                    }
                },

                {"data": "leadDate"},
                {"data": "createDate"},
               {
                    "mRender": function (data, type, row) {

                       // let fullName = row.creatorName;
                       // let username = " ("+row.createBy+")";
                        return row.createBy;
                        return row.createBy;


                    }
                },
                {"data": "updateDate"},
                {"data": "updateBy"},
                {
                    "mRender": function (data, type, row) {


                        return parseInt(row.counter);


                    }
                },
                {"data": "c2cAttempts"},
                {"data": "leadScore"},
                {"data": "leadPriority"},
                {"data": "keyword"},
                {"data": "paymentStatus",
                    "mRender": function (data, type, row) {
                                if(data==true)
                                {
                                    return "paid";
                                }
                               return "not paid";
                    }},

                {"data": "otpStatus"},
                {
                    "mRender": function (data, type, row) {

                        return "<i title='View Lead Menu' class=\"hvr-buzz-out fa fa-pencil editcustom\"></i>";

                    }
                }

            ]
            , "initComplete": function (settings, json) {
               // var id = '<%=request.getParameter("id")%>';

               
            },
            "processing": true,
            "serverSide": true,
            "ordering": false,
            "searching": false,
            "bDestroy": true,
            "sScrollX": true,
            "pageLength": localStorage.getItem("table_length")||25,
            "lengthMenu": [ 10, 25, 50, 100,200,500,1000]



        });
    }

    //DISPLAY ADFORMDATA ON CLICK
//apple
    function showAdForm(data){

        $("#adFormTableBody").html("")
        $("#questionTableBody").html("")
        $("#detailsTabBtn").trigger("click");
        if(data === undefined){
            return;
        }
        let isArray = Array.isArray(data);

        if(data.is_questions == undefined && !isArray){
            showForm1(data)
        }
        if(data.is_questions !== undefined && !isArray){
            showForm2(data)
        }

        if(isArray){
            showForm3(data)
        }
}

function showForm1(data){
//  BASIC KEY-VALUE PAIRS
    let html="";
    for (var key in data) {
        //console.log("User " + data[key] + " is #" + key); // "User john is #234"
        html += "<tr>"
        html += "<td>"+key.toUpperCase()+"</td>"
        html += "<td>"+data[key]+"</td>"
        html += "</tr>"
    }
    $("#adFormTableBody").html(html)
}

function showForm2(data){
//KEY-VALUES PAIRS, ARRAY OF QUESTION
    let html="";
    let questionsHtml = "";

    delete data["is_questions"]
    let questionsArr = data["ans_detail_array"];
    delete data["ans_detail_array"]

    for (var key in data) {
        //console.log("User " + data[key] + " is #" + key); // "User john is #234"
        html += "<tr>"
        html += "<td>"+key.toUpperCase()+"</td>"
        html += "<td>"+data[key]+"</td>"
        html += "</tr>"
    }


    questionsArr.forEach(question =>{
        questionsHtml += "<tr>"
        questionsHtml += "<td>Domain</td>"+"<td>"+question["Domain"]+"</td>"
        questionsHtml += "</tr>"
        questionsHtml += "<tr>"
        questionsHtml += "<td>Ques</td>"+"<td>"+question["Ques"]+"</td>"
        questionsHtml += "</tr>"
        questionsHtml += "<tr>"
        questionsHtml += "<td>Ans</td>"+"<td>"+question["Ans"]+"</td>"
        questionsHtml += "</tr>"
        questionsHtml += "<tr><td></td></tr>"
    })

    $("#adFormTableBody").html(html)
    $("#questionTableBody").html(questionsHtml)
}

function showForm3(dataArr){
//ARRAY OF KEY-VALUES PAIRS WHERE VALUES ARE ARRAYS
    let html = "";
    dataArr.forEach(data=>{

            html += "<tr>"
            html += "<td>"+data["name"].toUpperCase()+"</td>"
            html += "<td>"+data["values"]+"</td>"
            html += "</tr>"

    })
    $("#adFormTableBody").html(html)

}

//STORE LEAD TO BE TRANSFERED
let transferList= new Map();
//SELECT/UN-SELECT ALL LEADS AT ONCE
$(document).on("change","#selectAllLead",function (){

    if($(this).prop("checked") == true){

        $(".leadSelect").prop("checked",true);
        $(".bulkTransferDiv").fadeIn();
        transferList.clear();
        $(".leadData").each(function() {
            let tempJson = JSON.parse($(this).html());
            transferList.set(tempJson.id,tempJson);
        });

    }//end of if
    else if($(this).prop("checked") == false){
        $(".leadSelect").prop("checked",false);
        $(".bulkTransferDiv").fadeOut();
        transferList.clear();
    }//end of else if

})



//SELECT/UN-SELECT ONE BY ONE
$(document).on("change",".leadSelect",function(){

        let json = $(this).closest("tr").find("td").eq(0).find("a").html();
        json = JSON.parse(json);

        if($(this).prop("checked") == true){
           transferList.set(json.id,json);
           $(".bulkTransferDiv").fadeIn();

        }
        else if($(this).prop("checked") == false){
             transferList.delete(json.id);
            if (transferList.size == 0) {
                $(".bulkTransferDiv").fadeOut();
                $("#selectAllLead").prop("checked",false);
            }
        }


    })

$("#bulkTransfer").on("click",function(){


       let values = Array.from(transferList.values());
       $('#hidjson').val(JSON.stringify(values[0]));
        loadhtmlintotransfer("${pageContext.servletContext.contextPath}/html/leadtransfer.html", "Transfer Lead <i class=\"hvr-buzz-out fa fa-exchange\"></i>", "${pageContext.servletContext.contextPath}/api/leadtransfer/insert", values,true);

})

$("#bulkTrash").on("click",function(){

    let arr = [];
    let json = Array.from(transferList.values())

     for (var i = 0; i < json.length; i++) {
        var map = {};
        map["leadId"] = json[i].id;
        map["toId"] = "";
        map["tousername"] = "";
        map["fromusername"] = "";
        arr.push(map)

    }

    if (arr.length == 0) {
        return;
    }

    let leadstage = "Junkbox";
    let leadstatus = "Dont call again";

    $.ajax({
        type: 'POST',
        data: JSON.stringify(arr),
        contentType: 'application/json',
        url: "${pageContext.servletContext.contextPath}/api/lead/bulkupdateleadstatusandstage?leadstage="+leadstage+"&leadstatus="+leadstatus,
        success: function (data) {

            swal({
                title: "Success!",
                text: "Moved " + data + " Leads to trash",
                icon: "success",
                button: "Ok!",
            });

          reloadTable();

            //IF THIS WAS A BULK TRANSFER
            transferList.clear();
            $(".bulkTransferDiv").fadeOut();
            $(".leadSelect").prop("checked",false);
            $("#selectAllLead").prop("checked",false);


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


$("#bulkSourceChange").on("click",function(){
    let values = Array.from(transferList.values());
    $('#hidjson').val(JSON.stringify(values[0]));
    loadLeadSource(()=>{
        console.log("loaded sources")
    })
    //loadhtmlintotransfer("${pageContext.servletContext.contextPath}/html/leadtransfer.html", "Transfer Lead <i class=\"hvr-buzz-out fa fa-exchange\"></i>", "${pageContext.servletContext.contextPath}/api/leadtransfer/insert", values,true);

})

$("#bulkProgramChange").on("click",function(){
    let values = Array.from(transferList.values());
    $('#hidjson').val(JSON.stringify(values[0]));
    loadLeadProgram("#leadProgramSelect",()=>{
        console.log("loaded Programs")
    })

})

$("#updatesourcebtn").on("click",function (){
    bulkChange("Source","#leadSourceSelect",'#bulksourcechangemodal')
})

$("#updateprogrambtn").on("click",function (){
    bulkChange("Program","#leadProgramSelect",'#bulkprogramchangemodal')
})


function bulkChange(flag,selectId,modalId){
    swal({
        title: "Are you sure?",
        text: "You Want To Change "+flag+" Of Selected Leads ("+transferList.size+") To " + $(selectId).val(),
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((change) => {
            if (change) {
                let leadIdList = [];
                transferList.forEach((value,key)=>{
                    leadIdList.push(key)
                })

                $('#preloader').css("display", "block");
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/api/lead/bulkchange?newvalue="+$(selectId).val()+"&flag="+flag,
                    data: JSON.stringify(leadIdList),
                    processData: false,
                    contentType: 'application/json',
                    cache: false,
                    timeout: 600000,
                    success: function (data) {
                        $(modalId).modal("hide");
                        $('#preloader').fadeOut();
                        loaddataintotable();
                        swal({
                            title: "Success!",
                            text: flag+" changed for selected leads",
                            icon: "success",
                            button: "Ok!",
                        });

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
        });
}

    loaddataintotable();

//================================== DOWNLOAD FILTERED LEADS =======
    $("#downloadreport").click(function () {
        let ths = $(this);

        ths.prop('disabled', true);
        ths.html("Processing...")

        var searchParams = new URLSearchParams(window.location.search);
        var leadstatus = searchParams.get('statusValue') || 0;
        var leadsource = searchParams.get('leadSource') || 0;
        var product = searchParams.get('product') || 0;
        var leadtype = searchParams.get('leadType') || 0;
        var userfilter = searchParams.get('userFilter') || 0;
        var datefilter = searchParams.get('datefilter') || false;
        var customdate = searchParams.get('datevalue') || 0;
        var searchstring = searchParams.get('searchvalue') || 0;
        var fieldArray = searchParams.get('fieldArray') || 0;
        var leadstage = searchParams.get('leadstage') || 0;
        var innersource = searchParams.get('innersource') || 0;
        var leadpriority = searchParams.get('leadpriority') || 0;
        var leadids = searchParams.get('leadids') || 0;

       var id = searchParams.get('id') || 0;

       let dateTypeFlag=searchParams.get("dateTypeFlag")||4;//4 means LMS entry date

        var datefiltertype = searchParams.get('datefiltertype') || "createdate";
        if(dateTypeFlag == 1){
            datefiltertype = "leaddate"
        }else if(dateTypeFlag == 2){
            datefiltertype = "convertdate"
        }else if(dateTypeFlag == 3){
            datefiltertype = "updatedate"
        }else if(dateTypeFlag == 4){
            datefiltertype = "createdate"
        }else if(dateTypeFlag == 5){
            datefiltertype = "profilingdate"
        }
            // THIS LOGIC BELOW IS FOR LEAD STATS SAME DATE
            //REQUIREMENT IS - ALL RESULTS, ASSIGNED,PROFILED,CONVERTED,REJECTCED AND PENDING
            //HAS TO BE CALCULATED BASED ON  dateFilter (date on which lead was entered in the LMS) AND profilingAgentId
        //
        else if(dateTypeFlag == 6){
            datefiltertype = "profilingdatecustom"
        }
        else if(dateTypeFlag == 7){
            datefiltertype = "appointmentdate"
        }
        //DATE ON WHICH ASSESSMENT FOR WAS SUBMITTED
        else if(dateTypeFlag == 8){
            datefiltertype = "assessmentdate"
        }



        var url = "datefiltertype=" + datefiltertype + "&innersource=" + innersource + "&statusValue=" + leadstatus + "&leadSource=" + leadsource + "&product=" + product + "&leadType=" + leadtype + "&userFilter=" + userfilter + "&datefilter=" + datefilter + "&datevalue=" + customdate + "&searchvalue=" + searchstring + "&leadstage=" + leadstage + "&id=" + id +"&start=0&length=100000" + "&leadpriority=" + leadpriority + "&fieldArray="+fieldArray+"&leadids="+leadids;



        var promise = new Promise(function (resolve, reject) {
            window.open("${pageContext.servletContext.contextPath}/api/lead/downloadreport?"+url)
            resolve();
        }).then(function (successMessage) {

                    ths.prop('disabled', false);
                    ths.html("Download Report")

        }, function (errorMessage) {

            console.log(errorMessage);
            ths.prop('disabled', false);
            ths.html("Download Report")

        })
        //downloadLeadReport();
    });
//================================= DOWNLOAD ASSESSMENT REPORT =====
$("#downloadassessmentreport").click(function () {
    let ths = $(this);

    ths.prop('disabled', true);
    ths.html("Processing...")

    var searchParams = new URLSearchParams(window.location.search);
    var leadstatus = searchParams.get('statusValue') || 0;
    var leadsource = searchParams.get('leadSource') || 0;
    var product = searchParams.get('product') || 0;
    var leadtype = searchParams.get('leadType') || 0;
    var userfilter = searchParams.get('userFilter') || 0;
    var datefilter = searchParams.get('datefilter') || false;
    var customdate = searchParams.get('datevalue') || 0;
    var searchstring = searchParams.get('searchvalue') || 0;
    var fieldArray = searchParams.get('fieldArray') || 0;
    var leadstage = searchParams.get('leadstage') || 0;
    var innersource = searchParams.get('innersource') || 0;
    var leadpriority = searchParams.get('leadpriority') || 0;
    var leadids = searchParams.get('leadids') || 0;

    var id = searchParams.get('id') || 0;

    let dateTypeFlag=searchParams.get("dateTypeFlag")||4;//4 means LMS entry date

    var datefiltertype = searchParams.get('datefiltertype') || "createdate";
    if(dateTypeFlag == 1){
        datefiltertype = "leaddate"
    }else if(dateTypeFlag == 2){
        datefiltertype = "convertdate"
    }else if(dateTypeFlag == 3){
        datefiltertype = "updatedate"
    }else if(dateTypeFlag == 4){
        datefiltertype = "createdate"
    }else if(dateTypeFlag == 5){
        datefiltertype = "profilingdate"
    }
        // THIS LOGIC BELOW IS FOR LEAD STATS SAME DATE
        //REQUIREMENT IS - ALL RESULTS, ASSIGNED,PROFILED,CONVERTED,REJECTCED AND PENDING
        //HAS TO BE CALCULATED BASED ON  dateFilter (date on which lead was entered in the LMS) AND profilingAgentId
    //
    else if(dateTypeFlag == 6){
        datefiltertype = "profilingdatecustom"
    }
    else if(dateTypeFlag == 7){
        datefiltertype = "appointmentdate"
    }//DATE ON WHICH ASSESSMENT FOR WAS SUBMITTED
    else if(dateTypeFlag == 8){
        datefiltertype = "assessmentdate"
    }



    var url = "datefiltertype=" + datefiltertype + "&innersource=" + innersource + "&statusValue=" + leadstatus + "&leadSource=" + leadsource + "&product=" + product + "&leadType=" + leadtype + "&userFilter=" + userfilter + "&datefilter=" + datefilter + "&datevalue=" + customdate + "&searchvalue=" + searchstring + "&leadstage=" + leadstage + "&id=" + id +"&start=0&length=100000" + "&leadpriority=" + leadpriority + "&fieldArray="+fieldArray+"&leadids="+leadids;



    var promise = new Promise(function (resolve, reject) {
        window.open("${pageContext.servletContext.contextPath}/api/lead/downloadpreschoolreport?"+url)
        resolve();
    }).then(function (successMessage) {

        ths.prop('disabled', false);
        ths.html("Download Assessment Report")

    }, function (errorMessage) {

        console.log(errorMessage);
        ths.prop('disabled', false);
        ths.html("Download Report")

    })
    //downloadLeadReport();
});








//================================= DOWNLOAD ASSESSMENT REPORT =====
$("#downloadreviewreport").click(function () {
    let ths = $(this);

    ths.prop('disabled', true);
    ths.html("Processing...")

    var searchParams = new URLSearchParams(window.location.search);
    var leadstatus = searchParams.get('statusValue') || 0;
    var leadsource = searchParams.get('leadSource') || 0;
    var product = searchParams.get('product') || 0;
    var leadtype = searchParams.get('leadType') || 0;
    var userfilter = searchParams.get('userFilter') || 0;
    var datefilter = searchParams.get('datefilter') || false;
    var customdate = searchParams.get('datevalue') || 0;
    var searchstring = searchParams.get('searchvalue') || 0;
    var fieldArray = searchParams.get('fieldArray') || 0;
    var leadstage = searchParams.get('leadstage') || 0;
    var innersource = searchParams.get('innersource') || 0;
    var leadpriority = searchParams.get('leadpriority') || 0;
    var leadids = searchParams.get('leadids') || 0;

    var id = searchParams.get('id') || 0;

    let dateTypeFlag=searchParams.get("dateTypeFlag")||4;//4 means LMS entry date

    var datefiltertype = searchParams.get('datefiltertype') || "createdate";
    if(dateTypeFlag == 1){
        datefiltertype = "leaddate"
    }else if(dateTypeFlag == 2){
        datefiltertype = "convertdate"
    }else if(dateTypeFlag == 3){
        datefiltertype = "updatedate"
    }else if(dateTypeFlag == 4){
        datefiltertype = "createdate"
    }else if(dateTypeFlag == 5){
        datefiltertype = "profilingdate"
    }
        // THIS LOGIC BELOW IS FOR LEAD STATS SAME DATE
        //REQUIREMENT IS - ALL RESULTS, ASSIGNED,PROFILED,CONVERTED,REJECTCED AND PENDING
        //HAS TO BE CALCULATED BASED ON  dateFilter (date on which lead was entered in the LMS) AND profilingAgentId
    //
    else if(dateTypeFlag == 6){
        datefiltertype = "profilingdatecustom"
    }
    else if(dateTypeFlag == 7){
        datefiltertype = "appointmentdate"
    }//DATE ON WHICH ASSESSMENT FOR WAS SUBMITTED
    else if(dateTypeFlag == 8){
        datefiltertype = "assessmentdate"
    }



    var url = "datefiltertype=" + datefiltertype + "&innersource=" + innersource + "&statusValue=" + leadstatus + "&leadSource=" + leadsource + "&product=" + product + "&leadType=" + leadtype + "&userFilter=" + userfilter + "&datefilter=" + datefilter + "&datevalue=" + customdate + "&searchvalue=" + searchstring + "&leadstage=" + leadstage + "&id=" + id +"&start=0&length=100000" + "&leadpriority=" + leadpriority + "&fieldArray="+fieldArray+"&leadids="+leadids;



    var promise = new Promise(function (resolve, reject) {
        window.open("${pageContext.servletContext.contextPath}/api/lead/downloadreviewreport?"+url)
        resolve();
    }).then(function (successMessage) {

        ths.prop('disabled', false);
        ths.html("Download Review Report")

    }, function (errorMessage) {

        console.log(errorMessage);
        ths.prop('disabled', false);
        ths.html("Download Review Report")

    })
    //downloadLeadReport();
});
//==================================================================
    function downloadLeadReport() {

        var searchParams = new URLSearchParams(window.location.search);
        var leadstatus = searchParams.get('statusValue') || 0;
        var leadsource = searchParams.get('leadSource') || 0;
        var product = searchParams.get('product') || 0;
        var leadtype = searchParams.get('leadType') || 0;
        var userfilter = searchParams.get('userFilter') || 0;
        var datefilter = searchParams.get('datefilter') || false;
        var customdate = searchParams.get('datevalue') || 0;
        var searchstring = searchParams.get('searchvalue') || 0;
        var fieldArray = searchParams.get('fieldArray') || 0;
        var leadstage = searchParams.get('leadstage') || 0;
        var innersource = searchParams.get('innersource') || 0;
        var leadpriority = searchParams.get('leadpriority') || 0;
        var leadids = searchParams.get('leadids') || 0;

        var id = searchParams.get('id') || 0;
        var datefiltertype = searchParams.get('datefiltertype') || "createdate";

        var url = "datefiltertype=" + datefiltertype + "&innersource=" + innersource + "&statusValue=" + leadstatus + "&leadSource=" + leadsource + "&product=" + product + "&leadType=" + leadtype + "&userFilter=" + userfilter + "&datefilter=" + datefilter + "&datevalue=" + customdate + "&searchvalue=" + searchstring + "&leadstage=" + leadstage + "&id=" + id +"&start=0&length=100000" + "&leadpriority=" + leadpriority + "&fieldArray="+fieldArray+"&leadids="+leadids;

        var wb = XLSX.utils.book_new();
        wb.Props = {
            Title: "Lead Report",
            Subject: "Leads",
            Author: "Mom's Belief Lead Management",
            CreatedDate: new Date()
        };

        wb.SheetNames.push("Report");
        let ws_data = [];


        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/lead/getlead?"+url,
            success: function (json) {
                ws_data.push(["LeadID","Lead Owner","Created By","Campaign","Keyword","Lead Date","Duplicate-Count","Created On","Updated By","Updated On","Product","Client Type","Source","Inner Source","Lead Stage","Lead Status","Converted?","Convert Date","Time Taken To Convert","Parent_Name","Child_Name","Relation_to_the_child","primary_language_of_the_child","primary_language_of_parrent","Gender_of_the_child","Date_of_birth","Contact_Number","Email_ID","City","Address","Pincode","Basic_concerns_of_the_child","Does_Your_Child_Have_Development_Delays","We_can_support_you_in_diferent_ways_You_suggest_where_you_want_to_start_with","Does_your_child_have_any_academic_or_learning_concerns?","Does_your_child_have_difficulty_maintaining_friendships","Payment","preferred_time_of_clinical_call","PG_DB_Name","Enabler","BPO","PG_BD_Manager"]);

                for(let i=0;i<json.data.length;i++){

                    var arr = [];

                    for(let i=0;i<json.data.length;i++){

                        var arr = [];
                        arr.push(applicationPrefix+json.data[i].id);
                        arr.push(json.data[i].username);
                        arr.push(json.data[i].createBy);
                        arr.push(json.data[i].leadPriority);//CAMPAIGN
                        arr.push(json.data[i].keyword);//FB CAMPAIGN
                        arr.push(json.data[i].leadDate);//  DATE ON WHICH
                        arr.push(json.data[i].counter);
                        arr.push(json.data[i].createDate);
                        arr.push(json.data[i].updateBy);
                        arr.push(json.data[i].updateDate);
                        arr.push(json.data[i].interestedProduct);
                        arr.push(json.data[i].clientType);
                        arr.push(json.data[i].leadSource);
                        arr.push(json.data[i].leadsourceinner);
                        arr.push(json.data[i].leadStage);
                        arr.push(json.data[i].leadStatus);
                        arr.push((json.data[i].leadConvert == 1)?"TRUE":"FALSE");
                        //arr.push(json.data[i].urnNumber);
                        arr.push(json.data[i].convertDate);
                        arr.push(json.data[i].timeTakenToConvert);

                        arr.push(json.data[i].parentName);
                        arr.push(json.data[i].childName);
                        arr.push(json.data[i].relation);
                        arr.push(json.data[i].childLanguage);
                        arr.push(json.data[i].parentLanguage);
                        arr.push(json.data[i].gender);
                        arr.push(json.data[i].dob);
                        arr.push(json.data[i].phonenumber);
                        arr.push(json.data[i].email);
                        arr.push(json.data[i].city);
                        arr.push(json.data[i].address);
                        arr.push(json.data[i].pincode);
                        arr.push(json.data[i].descrip);
                        arr.push(json.data[i].childDevDelay);
                        arr.push(json.data[i].supportOption);
                        arr.push(json.data[i].learningConcern);
                        arr.push(json.data[i].difficultyInFriendship);
                        arr.push(json.data[i].payment);
                        arr.push(json.data[i].clinicalCallTime);
                        arr.push(json.data[i].pgBdName);
                        arr.push(json.data[i].professionFilled);//enabler
                        arr.push(json.data[i].childPlayPattern);//bpo
                        arr.push(json.data[i].pgBdManager);

                   ws_data.push(arr);
                }

                var ws = XLSX.utils.aoa_to_sheet(ws_data);
                wb.Sheets["Report"] = ws;
                var wbout = XLSX.write(wb, {bookType:'xlsx',  type: 'binary'});
                saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream"}), 'LeadReport.xlsx');


            }},
            error: function (err) {

                console.log(err)
            }

        });
    }

    function s2ab(s) {

        var buf = new ArrayBuffer(s.length);
        var view = new Uint8Array(buf);
        for (var i=0; i<s.length; i++) view[i] = s.charCodeAt(i) & 0xFF;
        return buf;

    }

    function reloadTable() {
        table.ajax.reload(null,false);
    }

    function clearData() {
        for (var key in existingData) {
            switch (existingData[key].type) {
                case "select":
                    $("#"+key).val($("#"+key+" option:first").val());
                    break;
                default :
                    $('#'+key).val("");
                    break
            }
        }

        $("#tempCentreJson").val("")
        $("customfield").html("")
        $('#id').val(0);
        $('#salutaion').val("Mr");
        $('#firstname').val("");
        $('#lastname').val("");
        $('#email').val("");
        $('#phonenumber').val("");
        $('#product').val("");
        $('#leadsource').val("");
        $('#leadtype').val("");
        $('#propbuisness').val(0);
        $('#company').val("");
        $('#description').val("");
        $('#country').val("");
        $('#state').val("");
        $('#city').val("");
        $('#pincode').val("");
        $('#address').val("");
        $('#leadsource').prop("disabled", false);
        $('#leadstatushidden').val("")
        $('#leadpriority').val("")
        $('#urnnumber').val("")
        $('#urnnumberdiv').hide()
    }


    //=======================================HIDE DATA TABLE==================================

    $('#addnewuser').click(function () {
        clearData();

        $('#tablediv').css("display", "none");
        $('#addnewdiv').fadeIn();
        $('#addUserHeader').fadeOut();
        $('#lobidragdiv').css("display", "none");

        //loadBtnAddLogiPanel()
    });
    //=====================================HIDE FORM AND BRING DATA TABLE BACK =====================================
    $('#cancelbtn').click(function () {
        $('#leadstatus').html("");
        clearData();
        table.ajax.reload(null,false);
        $('#addnewdiv').css("display", "none");
        $('#addUserHeader').css("display", "block");
        $('#tablediv').fadeIn();


        $('#createbtn').html("Create")
    });


    //======================================= DATE PICKER =====================================
    $('#dob').datepicker({
        changeMonth: true,
        changeYear: true,
        yearRange: "1930:2025"
    });


    $('#preloader').css("display", "none");

    $('#addnewrow').click(function () {
        addNewRow();
    });



    $('#addnewrowreportto').click(function () {
        addNewRowReportTo();
    });
    //====================================================== UPLOAD FILES ====================================
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


    //================================================= EDIT USER ===========================================

    //===================================================== DELETE REPORT TO FILED FROM FORM =============================================


    // ===================================================== DELETE DOCUMENT FIELD FROM FORM ============================================
    $(document).on("click", ".deletefile", function () {
        var hidid = $(this).closest("tr").find("td").eq(0).attr("attrhid");
        var ths = $(this);
        if (hidid == 0) {
            $(this).closest("tr").remove();

        } else {
            swal({
                    title: "Are you sure?",
                    text: "Delete the selected file?",
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


    $.ajax({
        type: "GET",
        contentType: "application/json",
        //url: "${pageContext.servletContext.contextPath}/api/user/getusermyteam",
        url: "${pageContext.servletContext.contextPath}/api/user/getallactive",
        success: function (json) {
            var html = "<option value=''>---Select User---</option>";
            for (var i = 0; i < json.length; i++) {
                html = html + "<option  value='" + json[i].id + "'>" + json[i].firstName + " " + json[i].lastName +" ("+ json[i].username + ")</option> ";

            }
            $('#userlist').html(html)


        },
        error: function (err) {

        }

    });







    //======================================= LOAD LEAD PRIORITY IN FORM


    $(document).on("click", ".downloadfilecls", function () {
        var id = $(this).attr("attrhid");
        window.open("${pageContext.servletContext.contextPath}/api/file/downloadfile?id=" + id)

    });
    $(document).on("click", ".leadstatuscls", function () {
        var ths = $(this);
        var json = JSON.parse(ths.attr("namestatus"));
        try {
            var prevjson = JSON.parse($('.selectedtrue').attr("namestatus"));

            if (!prevjson.canBeManuallyChanged) {
                swal({
                    title: "Error",
                    text: "This Lead Status Cannot Be Changed Manually",
                    icon: "error",

                    dangerMode: true,
                });
                return;
            }
        } catch (e) {

        }

        var name = ths.text().trim();
        swal({
            title: "Are you sure?",
            text: "You Want To Change Status Of Lead To " + name,
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    var jsonhid = json;
                    setActionOnBasicOfJson(jsonhid, ths)
                    //UPDATE IN MEMORY LEAD JSON DATA AFTER CHANGING STATUS OF LEAD

                    let leadJson = JSON.parse($('#hidjson').val());
                    leadJson.leadStatus = name;
                    $('#hidjson').val(JSON.stringify(leadJson));


                }
            });


    });

    function loadhtmldynmiac(url, subject, callback) {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (html) {
                $('#hididdynmixform').val($('#id').val());
                $('#dynamicformbodymodel').html(html);
                $('#dunamicmodelheader').html(subject);
                $('#dynamicformmodel').modal("show");
                callback("success")


            },
            error: function (err) {

            }

        })
    }

    function loadpopup(action, inneraction) {

        changeStageandStatusOfLead($('#id').val(), action, inneraction, function (data) {
            if (data == "success") {
                loadLeadStatusByLeadStage(action, inneraction, function (data) {

                })
            }
        })


    }

    function ChangeStageAndStatusByJson(json) {


    }

    //FOR CHANGE STAGE/STATUS AND TRANSFER LEAD BACK TO AGENT
    function  getLeadTransferDaoArr(leadJson,hasUserName,username)
    {

        return new Promise((resolve, reject) => {

            //get user details with username
            if(hasUserName){
                $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "${pageContext.request.contextPath}/api/user/getuserbyusername?username="+username,
                    success: function (userMasterDao) {

                        //array of leadTransferDao
                        let leadTransferDaoArr = [];
                        //LEADTRANSFERDAO
                        var map = {};
                        map["leadId"] = leadJson.id;
                        map["toId"] = userMasterDao.id;
                        map["tousername"] = userMasterDao.username;
                        map["fromusername"] = leadJson.username;

                        leadTransferDaoArr.push(map);
                        resolve(leadTransferDaoArr);

                    },
                    error: function (err) {
                        reject("error")
                        swal({
                            title: 'Error',
                            text: err.responseJSON.message,
                            icon: 'error',
                            button: 'Ok'
                        });
                    }

                })
            }

            //get user details from lead.profilling agent
            else{
                $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "${pageContext.request.contextPath}/api/user/getuserById?id="+leadJson.profilingAgentId,
                    success: function (userMasterDao) {

                        //array of leadTransferDao
                        let leadTransferDaoArr = [];
                        //LEADTRANSFERDAO
                        var map = {};
                        map["leadId"] = leadJson.id;
                        map["toId"] = userMasterDao.id;
                        map["tousername"] = userMasterDao.username;
                        map["fromusername"] = leadJson.username;

                        leadTransferDaoArr.push(map);


                        resolve(leadTransferDaoArr);


                    },
                    error: function (err) {
                        reject("error")
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
    }

    function checktypeaction(val, inneraction,clickedStatus) {

        var inneractionval = [];
        inneractionval = inneraction.toString().split("|");
        var optionlist=val.split("|");
        val=optionlist[0];


        if (val == "Call Task") {


            loadhtmlintotask("${pageContext.servletContext.contextPath}/html/quicktask.html", inneractionval[2], "${pageContext.servletContext.contextPath}/api/logtask/insert?increasetime=true", $('#hidjsonleadeatils').val(), "Create",optionlist[1]);

        }
        if (val == "Log Event") {

            loadhtmlintoevent("${pageContext.servletContext.contextPath}/html/quicklogevent.html", inneractionval[2], "${pageContext.servletContext.contextPath}/api/logevent/insert", $('#hidjsonleadeatils').val(), "Create",optionlist[1]);

        }
        if (val == "Call Task Automatic") {
            setTaskAfterTime("${pageContext.servletContext.contextPath}/api/logtask/insertautomactic?minstoincreate=", inneractionval[2], JSON.parse($('#hidjsonleadeatils').val()).id)

        }
        if (val == "Transfer") {
            transferLeadToDepartment(inneraction,function (){});

            changeStageandStatusOfLead($('#id').val(),"Profiling Done","Pending",function (data) {
                table.ajax.reload(null,false);
                $('#addnewdiv').css("display", "none");
                $('#tablediv').fadeIn();
            })


        }
        if (val == "Ticket") {

            loadhtmlintogenerateticket("${pageContext.servletContext.contextPath}/html/quickticket.html", inneractionval[2], "${pageContext.servletContext.contextPath}/api/ticket/insert?sendtextmessage=", $('#hidjson').val(), "Create");

        }
        if (val == "Reject") {




            //GET LEAD DATA
            let leadJson = JSON.parse($('#hidjson').val());
            let username = "<%=UtilityClass.getCurrentUser().getUsername()%>"
            leadJson["username"] = username;

            //CHANGE THE STAGE AND STATUS OF THIS LEAD inneractionval[0] is stage and inneractionval[1] is status
            changeStageandStatusOfLead(leadJson.id,inneractionval[0],inneractionval[1],function (data) {
                loadLeadStatusByLeadStage(inneractionval[0],inneractionval[1], function () {

                    //MEANS THAT LEAD WAS SENT TO PROFILING DONE STAGE BEFORE IMPLEMENTATION OF THIS CODE
                    //HENCE AGENT HAS TO MANUALLY TRANSFER BACK THIS LEAD
                    if(leadJson.profilingAgentId == 0){
                        swal({
                            title: 'Info',
                            text: "Lead couldn't be transferred. Transfer this lead manually!!",
                            icon: 'warning',
                            button: 'Ok'
                        });

                        return false
                    }else{
                       getLeadTransferDaoArr(leadJson,false,null).then(arr => {
                           $.ajax({
                               type: 'POST',
                               data: JSON.stringify(arr),
                               contentType: 'application/json',
                               url: "${pageContext.servletContext.contextPath}/api/leadtransfer/insert",
                               success: function (data) {
                                   swal({
                                       title: 'Success',
                                       text: "Lead Transfered Back To "+leadJson.profilingAgent,
                                       icon: 'success',
                                       button: 'Ok'
                                   });
                                   $("#cancelbtn").trigger("click");
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
                        })




                    }

                });
            })






        }

        if(val == "CRMTransfer"){

            //GET LEAD DATA
            let leadJson = JSON.parse($('#hidjson').val());

            if(leadJson.clientType == "International"){
                swal({
                    title: 'Error',
                    text: "Lead with type 'International' can not be transfered to Center CRM!",
                    icon: 'error',
                    button: 'Ok'
                });
                return;
            }

            // RECEIVING LMS' API endpoint
            //let url = "http://localhost:8082/allow/lead/crmleadtransfer";
            let url = "http://crm.momsbelief.com/miblms/allow/lead/crmleadtransfer";
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "${pageContext.request.contextPath}/api/lead/transferleadtocrm?url=" + url + "&leadId=" + $('#id').val(),
                success: function (html) {

                    swal({
                        title: 'Success',
                        text: "Lead Transferred To Centre Appointment",
                        icon: 'success',
                        button: 'Ok'
                    }).then((ok)=>{
                        changeStatusOnly($('#id').val(),clickedStatus, function (data) {

                        })
                    });

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

    }

    function transferLeadToDepartment(dep,callback) {

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "${pageContext.request.contextPath}/api/lead/transferleadtodep?department=" + dep + "&leadId=" + $('#id').val(),
            success: function (html) {

                swal({
                    title: 'Success',
                    text: html,
                    icon: 'success',
                    button: 'Ok'
                }).then((ok)=>{
                    callback("success")
                });

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

    function setActionOnBasicOfJson(json, ths) {
        $('#hidjsonstageandstatus').val(JSON.stringify(json));

        var promise = new Promise(function (resolve, reject) {
            var changests = false;
            if (json.type == "Action") // will check for actions
            {
                checktypeaction(json.action, json.innerAction,ths.text().trim());
                changests = false;
            } else if (json.type == "ChangeStage")  // will check for status chage
            {
                loadpopup(json.action, json.innerAction);
                changests = false;
            } else if (json.type == "ActionandChangeStage") {
                checktypeaction(json.action, json.innerAction,ths.text().trim());
                changests = false;
            } else if (json.type == "Lead Conversion") // will convert lead
            {
                changests = false;
                changeStatusOnly($('#id').val(), ths.text().trim(), function (data) {
                    var createdate = JSON.parse($('#hidjsonleadeatils').val()).createDate;
                    convertLead($('#id').val(), createdate, function () {
                        swal({
                            title: "Success!",
                            text: "Lead Has Been Successfully Converted",
                            icon: "success",
                            button: "Ok!",
                        });
                        $('#cancelbtn').trigger("click")

                    })
                })


            }
            // CLIENT REQUIREMENT IS :
                //IF LEAD HAS STATE (E.G. PUNJAB) SET ==> THEN CONVERT LEAD, REGISTER TO MBOPS AND TRANSFER IT TO SOME USER(BASED ON DEPT), SAY JACK
                // IF LEAD STATE IS NOT SET ==> THEN CONVERT LEAD, DO NOT REGISTER TO MBOPS ,TRANSFER IT TO SOME OTHER USER(BASED ON DEPT), SAY JILL
                // AND TELL USER THAT LEAD HAS BEEN CONVERTED BUT NOT REGISTERED TO MBOPS
            else if (json.type == "Register" || json.type == "Register1") // will convert lead
            {

             //JSON WITH ALL LEAD DATA
                let leadJson = JSON.parse($('#hidjsonleadeatils').val());
                let state = leadJson.state
                let stateLen = 0;
                let isStateSet = false;//lead has state(location) set
                let isTransfer = false;//want to transfer lead
                let isDataOk = false;//all required fields have values
                if(state != null){
                    state = state.trim();
                    stateLen = state.length
                }

                //transfer lead : to 0 on registration success, to 1 on registration failure
                let departments
                if(json.action == "Transfer"){
                    departments = json.innerAction.split("|");
                    isTransfer = true;
                }
                //lead has state set (e.g. Punjab)
                if(stateLen > 0){
                   isStateSet = true;
                }

                //check if leads has all required values
                if(leadJson.gender && leadJson.parentName && leadJson.phonenumber && leadJson.email && leadJson.address && leadJson.pincode && leadJson.city && leadJson.state && leadJson.interestedProduct){
                       isDataOk = true;
                }

                //change status and convert lead
                changests = false;
                changeStatusOnly($('#id').val(), ths.text().trim(), function (data) {
                    var createdate = JSON.parse($('#hidjsonleadeatils').val()).createDate;
                    convertLead($('#id').val(), createdate, function () {
                        swal({
                            title: "Success!",
                            text: "Lead Has Been Successfully Converted",
                            icon: "success",
                            button: "Ok!",
                        }).then((ok)=>{

                            //if lead has all required data
                            //if state is 0 it means International lead, do not register it automatically
                            if(isDataOk && leadJson.state != "MomsBelief International"){
                                //console.log("Registering to MBOPS",isDataOk)
                                let useCustomIds = false;
                                let flag = "Register";
                                if(json.type == "Register1"){
                                    useCustomIds = true;
                                    flag = "Register1";
                                }
                                registerToMBOPS(leadJson.id,departments,isStateSet,isTransfer,useCustomIds,json,flag);
                            }else{
                                swal({
                                    title: "MBOPS Registration Failed!",
                                    text: "This is an international lead! Can't be registered automatically!",
                                    icon: "warning",
                                    button: "Ok!",
                                })
                            }


                            //if lead's missing required data
                            //but lead has to be transfered
                            if(!isDataOk && isTransfer){kill

                                swal({
                                    title: "MBOPS Registration Failed!",
                                    text: "Make sure all required fields have values, Transferring lead to "+departments[1]+" department",
                                    icon: "warning",
                                    button: "Ok!",
                                }).then((ok)=>{
                                    //TRANSFER LEAD TO A DEPARTMENT
                                    transferLeadToDepartment(departments[1],function (){
                                        $('#cancelbtn').trigger("click")
                                    });
                                })

                            }

                        })

                        // $('#cancelbtn').trigger("click")
                    })
                })



            }

            else if (json.type == "Record UrnNumber"){
                var jsondetails={};
                var jsonhiddetails=JSON.parse($('#hidjsonleadeatils').val());
                jsondetails["id"]=jsonhiddetails.id;
                jsondetails["nextstage"]=json.action;
                jsondetails["nextstatus"]=json.innerAction;


                loadhtmlintogenerateurnnumber("${pageContext.request.contextPath}/html/recordurnnumber.html","Record UrnNumber","${pageContext.request.contextPath}/api/lead/updateurngenerated",JSON.stringify(jsondetails))
            }
            else if(json.type == "OpenForm"){
                doReview($('#hidjson').val());
                return false;

            }
            else {
                changests = true;
            }

            resolve(changests);
        }).then(function (successMessage) {

            if (successMessage) {
                changeStatusOnly($('#id').val(), ths.text().trim(), function (data) {

                });


            }
        }, function (errorMessage) {
            console.log(errorMessage);
        })

        //    ---------------------------------------
        //     SEND NOTIFICATION TO LEAD IF LeadStageObject HAS TEMPLATE-ID
        //IGNORE THESE STATUSES BEFORE ,THESE STATUS ALREADY SEND NOTIFICATION ON MBOPS-REGISTRATION
        if(json.type != "Register" && json.type != "Register1"){
            //SENDING EMPTY HASHMAP AS THIS NOT BEING USED ANYWHERE
            let hashMap = {}
            hashMap["notifycentre"] = "false";
            triggerLeadNotification($('#id').val(),hashMap,json)
        }

    }

    function triggerLeadNotification(leadId,hashMap,json) {

    var templateIdArr=[];
        templateIdArr=json["templateId"].split("|");

        for(let i=0;i<templateIdArr.length;i++){
            if(templateIdArr[i].toString().length>5)
            {
                var map={};
                map["leadId"]=leadId;
                map["templateId"]=templateIdArr[i];
                map["templateName"]=json.templateName;
                map["hashMap"]=hashMap;
                $.ajax({
                    type: "POST",
                    data: JSON.stringify(map),
                    contentType: "application/json",
                    url: "${pageContext.servletContext.contextPath}/api/lead/quickleadnotification?id="+leadId+"&templateId="+templateIdArr[i],
                    success: function (json) {
                    },
                    error: function (err) {
                    }

                })
            }
        }


}

    function loadhtmlintogenerateurnnumber(url,subject,formsubmiturl,json) {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (html) {
                $('#dynamicformbodymodel').html(html);
                $('#dunamicmodelheader').html(subject);
                $('#dynamicformmodel').modal("show");
                $('#hidjsonrecordurn').val(json);
                $('#recordurnforurl').val(formsubmiturl);



            },
            error: function (err) {

            }

        })


    }


    $('#product').change(function () {
        var prosBuisness=parseFloat($('#product :selected').attr("unitprice"));
        $('#propbuisness').val(prosBuisness)
    })


//=========================Add notes fuction by Rahul and Sujeet===================================
$(document).on("submit", "#dynamicaddnoteformdirect", function (e,callback) {
        e.preventDefault();
     var json=JSON.parse($('#hidjson').val())
      var leadid = json.id;
       // After Submit notes show already store in db
     //  var assnotes= json.assessmentNotes;
     // document.getElementById("contentaddnote").value=assnotes;
     //  alert("notes is::  "+assnotes)
        var notess = document.getElementById("contentaddnote").value;

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/lead/updateaccessmentnotes?id=" + leadid + "&notes=" + notess,
            success: function (json) {
                //callback("success")
                swal({
                    title: 'Success',
                    text: 'Note Added',
                    icon: 'Success',
                    button: 'Ok'
                });
                loaddataintotable();
            },
            error: function (err) {

            }

        })
    }
)



    $(document).on("submit","#formrecordurn",function (e) {
        e.preventDefault();
        var json=JSON.parse($('#hidjsonrecordurn').val());

        var url=$('#recordurnforurl').val()
        $.ajax({
            type: 'POST',

            contentType: 'application/json',
            url: url+"?leadId="+json.id+"&urnnumber="+$('#urnnumberurnnumber').val(),
            success: function (data) {


                changeStageandStatusOfLead(json.id,json.nextstage,json.nextstatus,function (data) {

                    loadLeadStatusByLeadStage(json.nextstage,json.nextstatus, function () {
                        $('#cancelbtnnurnnumberform').trigger("click")
                    });

                })

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
    $(document).on("click",".showorhideother",function () {
        var ths=$(this);
        var val=$(this).val();
        var name=$(this).attr("name");

        var required=existingData[name]["required"];
        let customOptions = existingData[name]["customOptions"]||false;

        if(val=="NA" && customOptions == false){

            if(required) {
                $(ths).closest("label").find("customfield").html("<input required class='form-control' id='" + name + "' type='text' >");
            }else{
                $(ths).closest("label").find("customfield").html("<input class='form-control' id='" + name + "' type='text' >");
            }
            }
        //Any previous assessment and Has your child received any therapies before
        else if(val=="NA" && customOptions != false){

            if(required) {
                $(ths).closest("label").find("customfield").html("<select  required class='form-control' id='" + name + "' >"+customOptions+"</select>");
            }else{
                $(ths).closest("label").find("customfield").html("<select class='form-control' id='" + name + "' >"+customOptions+"</select>");
            }
        }
        else{
            $('#'+name).remove()
        }


    })
    //=================================================== FORM SUBMISSION ===========================================
    $('#createnewleadform').submit(function (e) {
        e.preventDefault();

        //verify phone number

        let tempPhone = $("#phonenumber").val();
        if(tempPhone.length > 15 || tempPhone.length < 10 && !tempPhone.includes("xxxx")){


            swal({
                title: "Invalid Phone Number!",
                text: "Enter a valid mobile number!!",
                icon: "error",
                button: "Ok!",
            });
            return false;
        }else if(!tempPhone.includes("xxxx")){

                let reg = /^-?\d*\.?\d*$/ //pattern to check if string has only numbers
                let isNumericOnly = reg.test(tempPhone);
                if(!isNumericOnly){
                    swal({
                        title: "Invalid Phone Number!",
                        text: "Enter a valid mobile number!!",
                        icon: "error",
                        button: "Ok!",
                    });
                    return false;
                }

        }




        var url = "";
        var map = {};


        if ($('#createbtn').html().trim() == 'Create') {

            url = "${pageContext.request.contextPath}/api/lead/insertlead";

            for (var key in existingData) {
                if(existingData[key]["type"]=="radio")
                {
                    var radioval=$("input[name='"+key+"']:checked").val();
                    if(radioval=="NA")
                    {
                        radioval="|"+$('#' + key).val();
                    }
                    map[key]=radioval;
                }
                else if(existingData[key]["type"]=="checkbox")
                {
                    map[key]=$.map($('input[name="'+key+'"]:checked'), function(c){return c.value; }).join(",")
                }
                else if(existingData[key]["type"]=="multi-select"){
                    let value = $('#' + key).val();
                    let finalValue = "";
                    for(let i=0;i<value.length;i++){
                        finalValue += value[i];
                        if(i<value.length-1){
                            finalValue += ",";
                        }
                    }
                    map[key] = finalValue;
                }
                else {
                    map[key] = $('#' + key).val();
                }

            }



        } else {
            url = "${pageContext.request.contextPath}/api/lead/updatelead";
            for (var key in existingData) {
                if(existingData[key]["type"]=="radio")
                {
                  var radioval=$("input[name='"+key+"']:checked").val();
                    if(radioval=="NA" )
                    {
                        radioval="|"+$('#' + key).val();
                    }

                    map[key]=radioval;
                }
                else if(existingData[key]["type"]=="checkbox")
                {
                    map[key]=$.map($('input[name="'+key+'"]:checked'), function(c){return c.value; }).join(",")
                }
                else if(existingData[key]["type"]=="multi-select"){
                    let value = $('#' + key).val();
                    let finalValue = "";
                    for(let i=0;i<value.length;i++){
                        finalValue += value[i];
                        if(i<value.length-1){
                            finalValue += ",";
                        }
                    }
                    map[key] = finalValue;
                }
                else {
                    map[key] = $('#' + key).val();
                }

            }
            map["id"]=$('#id').val();
            //UPDATE IN MEMORY LEADJSON
            //console.log("updating lead > ",map)
            //$('#hidjson').val(JSON.stringify(map));



        }


        $.ajax({
            type: 'POST',
            data: JSON.stringify(map),
            contentType: 'application/json',
            url: url,
            success: function (data) {


                if ($('#createbtn').html().trim() == 'Create') {

                    $('#id').val(data.id);
                    $('#lobidragdiv').css("display", "block");
                    swal({
                        title: "Success!",
                        text: "Successfully created Lead",
                        icon: "success",
                        button: "Ok!",
                    });
                    loadLeadStatusByLeadStage(data.leadStage, data.leadStatus, function () {
                        $('.editfield').fadeIn();
                    });


                    $('#hidjsonleadeatils').val(JSON.stringify(data));
                    $('#hidjson').val(JSON.stringify(data));
                    $('#createbtn').html("Update");
                    $('.editfield').css("display", "block");
                } else {


                    //REFRESH HIDDEN LEAD DATA
                    $('#hidjsonleadeatils').val(JSON.stringify(data));
                    $('#hidjson').val(JSON.stringify(data));
                    swal({
                        title: "Success!",
                        text: "Successfully Updated Lead",
                        icon: "success",
                        button: "Ok!",
                    });
                    //notify centere head and BD that a lead has made an appointment to their center
                    notifyCentre();

                }


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

    //=========================================================================================
    function addNewRow() {
        $('#attachmenttbody').append("<tr ><td attrhid='0' style='width: 80%'><form><input name='file' type='file' class='fileuploadinput form-control'></form> </td><td style='width: 20%'><i title='Click Here To Delete This Document' class='deletefile hvr-buzz-out fa fa-trash'></i></td></tr>")

    }

    //=========================================================================================
    function addNewRowReportTo() {
        $('#reportstotbody').append("<tr><td attrhid='0' style='width: 80%'><select class='departmentreportto form-control'>" + $('#department').html() + "</select> </td><td attrhid='0' style='width: 80%'><select class='form-control'><option value='-1'>----Select Department First-----</option></select> </td><td style='width: 20%'><i title='Click Here To Delete This Document' class='deletereportto hvr-buzz-out fa fa-trash'></i></td></tr>")

    }

    //=========================================================================================
    function loaddepartment(url, id) {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (json) {
                var html = "<option value='0'>---Select Department---</option>";
                for (var i = 0; i < json.length; i++) {
                    html = html + "<option value='" + json[i].roleName + "'>" + json[i].roleName + "</option>"

                }
                $(id).html(html)

            },
            error: function (err) {

            }

        })
    }

    $(document).on("click", ".editcustom", function (event) {

        var pos = $(this).offset();

        //var json = $(this).closest("tr").find("td").eq(0).find("a").attr("hidatr");
        var json = $(this).closest("tr").find("td").eq(0).find("a").html();

        //==== coverts special html characters to plain text (&amp; to & and many more)
        let tempElem = document.createElement("textarea");
        tempElem.innerHTML = json;
        let obj = JSON.parse(tempElem.value);
        json = JSON.stringify(obj);
        //==== ends here

        $('#hidjson').val(json);

        $(".custom-menu").finish().toggle(100).

            // In the right position (the mouse)
            css({
                top: pos.top + "px",
                left: (pos.left - 30) + "px"
            });

    });

    $(document).bind("contextmenu", function (event) {
        // Avoid the real one
        event.preventDefault();
        var targ = $(event.target);
        //var json = targ.closest("tr").find("td").eq(0).find("a").attr("hidatr");
        var json = targ.closest("tr").find("td").eq(0).find("a").html();

        //==== coverts specails html characters to plain text (&amp; to & and many more)
        let tempElem = document.createElement("textarea");
        tempElem.innerHTML = json;
        let obj = JSON.parse(tempElem.value);
        json = JSON.stringify(obj);
        //==== ends here

        $('#hidjson').val(json);
        if ($(event.target).is('td')) {

            var height = event.pageY
            var pageHeight = document.body.scrollHeight
            var customMenuHeight = $(".custom-menu").height()
            var positionTop;

            //if context menu goes below the page length
            if((height + customMenuHeight)> pageHeight){
                positionTop = pageHeight - customMenuHeight - 50;
            }else{
                positionTop = height;
            }


            $(".custom-menu").finish().toggle(100).

                // In the right position (the mouse)
                css({
                    top: positionTop + "px",
                    left: event.pageX + "px"
                });
        }


    });


    // If the document is clicked somewhere
    $(document).bind("mousedown", function (e) {


        if (!$(e.target).parents(".custom-menu").length > 0) {

            // Hide it
            $(".custom-menu").hide(100);
        }
    });

function findAge(dob) {
    var months;
    let d1 = new Date(dob);
    let d2 = new Date();
    months = (d2.getFullYear() - d1.getFullYear()) * 12;
    months -= d1.getMonth();
    months += d2.getMonth();
    return months <= 0 ? 0 : months;
}

//===============================================

    function editUser(json) {

    console.log("json :: ",json)


        //---------------------------------------------------
        // THIS CODE DISABLES THE 'PREFERRED CLINICAL CALL TIME' FIELD
        // IF STAGE OF LEAD IS SAME AS SET FOR appointmentDenyStage PROPERTY
        let appointmentDenyStage = "<%=propertyService.findProperty("Lead","appointmentDenyStage")%>";
        $('#preloader').css("display", "block");
        json = JSON.parse(json);

        if(json.leadStage == appointmentDenyStage){
            $("#clinicalCallTime").prop("disabled",true);
        }

        //___________________________________________________


        $('#id').val(json.id);
        $('#hidjsonleadeatils').val(JSON.stringify(json));
        loadLeadStatusByLeadStage(json.leadStage, json.leadStatus, function () {
            $('.editfield').fadeIn();
        });
        $("#tempCentreJson").val("")
        $('#salutaion').val(json.salutation);
        $('#firstname').val(json.firstName);
        $('#lastname').val(json.lastName);
        $('#email').val(json.email);
        $('#phonenumber').val(json.phonenumber);
        $('#product').val(json.interestedProduct);
        $('#addUserHeader').fadeOut();
        $('#leadsource').val(json.leadSource);
        $('#lobidragdiv').css("display", "block");
        if (json.leadsourceinner != "Application") {
            $('#leadsource').prop("disabled", true);

        } else {
            $('#leadsource').prop("disabled", false);
        }
        $('#leadtype').val(json.clientType);
        $('#leadpriority').val(json.leadPriority);
        $('#propbuisness').val(json.prospectiveBuissness);
        $('#company').val(json.company);
        $('#description').val(json.descrip);
        $('#country').val(json.country);
        $('#state').val(json.state);
        loadStates(json.country,function(){
                    $('#stateId').val(json.stateId);
        });
        $('#city').val(json.city);
        $('#leadstatushidden').val(json.leadStatus);
        $('#leadstageval').html(json.leadStage);
        $('#pincode').val(json.pincode);
        $('#address').val(json.address);
        $('#leadstatus').find("[namestatus='" + json.leadStatus + "']").addClass("selectedtrue");

        $('#offsetleadlog').val(0);

        if(json.leadConvert)
        {
            $('#urnnumberdiv').css("display","block")
        }
        else{
            $('#urnnumberdiv').css("display","none")
        }
        $('#urnnumber').val(json.urnNumber)

        loadleadevent(json.id);
        loadleadstatustranfer(json.id);
        loadleadfileattchment(applicationPrefix + json.id);
        loadleadlogtask(json.id);
        loadleadtransferhistory(json.id);
        loadleadticket(json.id, 10, 0);
        $('#tablediv').css("display", "none");
        $('#addnewdiv').fadeIn();
        $('#createbtn').html("Update");
        $('#preloader').fadeOut("slow")
        for (var key in existingData) {
            var fieldValue=json[key];

            if(fieldValue != null) {
                if (existingData[key]["type"] == "radio") {

                    let customOptions = existingData[key]["customOptions"]||false;

                    if (fieldValue.includes("|")) {
                        $("input[name=" + key + "][value='NA']").prop("checked", true);
                        fieldValue = fieldValue.replace("|", "")

                        if(customOptions == false){
                            if (existingData[key]["required"]) {
                                $('#' + key + '_field').html("<input required class='form-control' id='" + key + "' value='" + fieldValue + "' type='text' >");
                            } else {
                                $('#' + key + '_field').html("<input class='form-control' id='" + key + "' value='" + fieldValue + "' type='text' >");
                            }
                        }else{
                            if (existingData[key]["required"]) {
                                $('#' + key + '_field').html('<select  class="form-control" id="' + key + '" >'+customOptions+'</select>');
                            } else {
                                $('#' + key + '_field').html('<select  class="form-control" id="' + key + '" >'+customOptions+'</select>');
                            }

                            $("#"+key).val(fieldValue);
                        }

                    }
                    else {
                        $("input[name=" + key + "][value='" + fieldValue + "']").prop("checked", true);
                    }
                } else if (existingData[key]["type"] == "checkbox") {
                    var checkboxarr = fieldValue.split(",");
                    for (var i = 0; i < checkboxarr.length; i++) {
                        $("input[name=" + key + "][value='" + checkboxarr[i] + "']").prop("checked", true);
                    }
               }
                else if(existingData[key]["type"]=="multi-select"){
                  let selectValue = fieldValue.split(",");
                      $('#' + key).val(selectValue);
                }

                else {
                    $('#' + key).val(fieldValue);
                }
            }
        }


    }


    // If the menu element is clicked
    $(".custom-menu li").click(function () {

        // This is the triggered action name
        switch ($(this).attr("data-action")) {
            case "edit":
                editUser($('#hidjson').val());
                break;
            case "assess":
                doAssessment($('#hidjson').val());
                break;
            case "review":
                doReview($('#hidjson').val());
                break;
            case "quicktask":
                var selecthtml="Call,Email";
                loadhtmlintotask("${pageContext.servletContext.contextPath}/html/quicktask.html", "Quick Task <i class=\"hvr-buzz-out fa fa-tasks\"></i>", "${pageContext.servletContext.contextPath}/api/logtask/insert?increasetime=false", $('#hidjson').val(), "Create",selecthtml);
                break;
            case "addattachement":
                if(isFeatureAvailable("addattachement") == true){
                loadhtmlintoquickfileupload("${pageContext.servletContext.contextPath}/html/quickdocumentupload.html", "Quick File Upload <i class=\"hvr-buzz-out fa fa-file-archive-o\"></i>", $('#hidjson').val());
                }

                break;
            case "logevent":
                var selecthtml="Call,Email,Not Satisfied,Not Wanted"
                loadhtmlintoevent("${pageContext.servletContext.contextPath}/html/quicklogevent.html", "Quick Log Event <i class=\"hvr-buzz-out fa fa-pencil\"></i>", "${pageContext.servletContext.contextPath}/api/logevent/insert", $('#hidjson').val(), "Create",selecthtml);
                break;
            case "leadtransfer":

                loadhtmlintotransfer("${pageContext.servletContext.contextPath}/html/leadtransfer.html", "Transfer Lead <i class=\"hvr-buzz-out fa fa-exchange\"></i>", "${pageContext.servletContext.contextPath}/api/leadtransfer/insert", $('#hidjson').val(),false);

                break;

            case "leadticket":

                loadhtmlintogenerateticket("${pageContext.servletContext.contextPath}/html/quickticket.html", "Quick Ticket <i class=\"hvr-buzz-out fa fa-ticket\"></i>", "${pageContext.servletContext.contextPath}/api/ticket/insert?sendtextmessage=", $('#hidjson').val(), "Create");

                break;

            case "clicktocall":
                var json=JSON.parse($('#hidjson').val())

                if(json.leadStage=="Junkbox")
                {
                    swal({
                        title: 'Error',
                        text: "Call Cannot Be Initiated While lead is in JunkBox!!",
                        type: 'error',
                        confirmButtonText: 'Ok'
                    })
                    return;
                }

                loadhtmlintoclicktocall("${pageContext.servletContext.contextPath}/html/ClickToCall.html", "Click To Call <i class=\"hvr-buzz-out fa fa-phone\"></i>", "${pageContext.servletContext.contextPath}/api/clicktocall/placeacall?tophonenumber=", $('#hidjson').val());

                break;

            case "quickcampaign":
                loadhtmlintoquickcampaign("${pageContext.servletContext.contextPath}/html/leadcampaign.html","Quick Campaign","CRM")
                  break;
            case "addnotes":
                loadhtmlintoaddnotes("${pageContext.servletContext.contextPath}/html/addnotes.html","Add Notes")


        }

        // Hide it AFTER the action was triggered
        $(".custom-menu").hide(100);
    });

    function isFeatureAvailable(menu){
        let menuAccess;
       switch (menu) {
           case "addattachement":
               menuAccess = "<%=propertyService.findProperty("CRMDemo","allowaddattachement")%>"
               break;
           case "leadtransfer":
               menuAccess = "<%=propertyService.findProperty("CRMDemo","allowleadtransfer")%>"
               break;
           case "leadticket":
               menuAccess = "<%=propertyService.findProperty("CRMDemo","allowleadticket")%>"
               break;
           case "clicktocall":
               menuAccess = "<%=propertyService.findProperty("CRMDemo","allowclicktocall")%>"
               break;
           case "quickcampaign":
               menuAccess = "<%=propertyService.findProperty("CRMDemo","allowquickcampaign")%>"
               default:
                   console.log("NO MATCH")

       }
        let demoUserRole = "<%=propertyService.findProperty("CRMDemo","demoUserRole")%>"

        let currentUserRole = "<%=UtilityClass.getCurrentUser().getDepartment()%>"
        if(currentUserRole == demoUserRole && menuAccess == "false"){
            swal({
                title: 'Feature Not Available',
                text: "Sorry! This feature is not available with demo account!",
                type: 'warning',
                confirmButtonText: 'Ok'
            })

            return false;
        }
        else return true;
    }
//========================Add Notes html function call========================
    function loadhtmlintoaddnotes(url, subject)
    {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (html) {
                $('#dynamicformbodymodel').html(html);
                $('#dunamicmodelheader').html(subject);
                $('#dynamicformmodel').modal("show");
                var json=JSON.parse($('#hidjson').val())
                var notes= json.assessmentNotes;

                $('#contentaddnote').val(notes)
            },
            error: function (err) {
            }

        })
    }



    function loadhtmlintotransfer(url, subject, formsubmiturl, json, isBulkTransfer) {
        if(isBulkTransfer){
            json = JSON.stringify(json);
        }else{
            var arr = [];
            arr.push(JSON.parse(json));
            json = JSON.stringify(arr);
        }

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (html) {
                $('#dynamicformbodymodel').html(html);
                $('#dunamicmodelheader').html(subject);
                $('#dynamicformmodel').modal("show");
                $('#jsonhiddenttransferform').val(json);
                $('#usertransferform').html($('#userlist').html());
                $('#urlhiddentransferform').val(formsubmiturl)
                if(isBulkTransfer){
                    $("#clientTypeSelectDiv").css("display","block")
                    $("#clientTypeSelect").html($("#clientType").html());
                }


            },
            error: function (err) {

            }

        })
    }

    function loadhtmlintotask(url, subject, formsubmiturl, json, typ,optionlist) {

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (html) {

                $('#subjectlisttaskform').html(selecthtml)
                $('#dynamicformbodymodel').html(html);
                $('#dunamicmodelheader').html(subject);
                $('#dynamicformmodel').modal("show");
                $('#taskformjsonhidden').val(json);
                $('#taskformurl').val(formsubmiturl);
                jQuery('#datetimetaskform').datetimepicker({
                    format: 'Y-m-d H:i',
                    lang: 'ru',
                    minDate:'<%=UtilityClass.getDataOnly()%>',
                    minTime:false,
                        step : 15
                });
                if (typ == "Update") {
                    json = JSON.parse(json);
                    $('#createbtntaskform').html("Update");
                    $('#messagetaskform').val(json.message);
                    $('#subjecttaskform').val(json.subject);
                    if (json.txtMsgNotification == true) {
                        $('#txtmessagenotificatontaskform').prop("checked", true);
                    } else {
                        $('#txtmessagenotificatontaskform').prop("checked", false);
                    }



                    $('#datetimetaskform').val(json.dateTimeTask)
                }

                optionlist=optionlist.split(",");
                var selecthtml='';
                for(var i=0;i<optionlist.length;i++)
                {
                    selecthtml=selecthtml+"<option val='"+optionlist[i]+"'>"+optionlist[i]+"</option>"
                }

                $('#subjecttaskform').html(selecthtml)
            },
            error: function (err) {

            }

        })


    }

    function loadhtmlintoquickfileupload(url, subject, json) {

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (html) {
                $('#dynamicformbodymodel').html(html);
                $('#dunamicmodelheader').html(subject);
                $('#dynamicformmodel').modal("show");
                json = JSON.parse(json);
                var leadId = json.id;
                $('#hiddenurlquickfile').val("${pageContext.request.contextPath}/api/file/insertfilelead?leadId=" + leadId);
                $('#hiddenidquickfile').val(leadId);
            },
            error: function (err) {

            }

        })


    }

    function loadhtmlintoclicktocall(url, subject, formsubmiturl, json) {

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (html) {
                $('#dynamicformbodymodel').html(html);
                $('#dunamicmodelheader').html(subject);
                $('#dynamicformmodel').modal("show");
                $('#hiddenjsondynamicform').val(json);
                $('#dynamicformurl').val(formsubmiturl);
                $('#ticketformtype').html($('#type').html());
                $('#phonenumberdynamicform').val(JSON.parse(json).phonenumber)


            },
            error: function (err) {

            }

        })


    }

$(document).on("change", ".templateSourceRadio", function () {
    console.log("Radio button working");
    templateSource = $(this).val();
    console.log("template source is "+ templateSource);
    loadhtmlintoquickcampaign("${pageContext.servletContext.contextPath}/html/leadcampaign.html","Quick Campaign",templateSource);
})


    function loadhtmlintoquickcampaign(url, subject,templateSource) {

        console.log("Template Source is"+templateSource);

        console.log("loadhtmlintoquickcampaign method is called");
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (html) {
                $('#dynamicformbodymodel').html(html);
                $('#dunamicmodelheader').html(subject);
                $('#dynamicformmodel').modal("show");

                $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "${pageContext.servletContext.contextPath}/api/template/getalltemplateapproved?templateSource="+templateSource,
                    success: function (json) {
                        var html="<option value=''>---Select Template---</option>";
                        for(var i=0;i<json.length;i++) {
                            //json[i].templateContent = encodeURIComponent(JSON.stringify(json[i].templateContent)).replace(/'/g, "%27");
                            //html = html+"<option   value='"+JSON.stringify(json[i])+"'>"+json[i].templateName+"</option> ";
                            console.log(json[i]);

                            let rowJson = json[i];
                            rowJson.template = encodeURI(rowJson.template);
                            rowJson.template = btoa(rowJson.template);
                            html = html+"<option value='"+JSON.stringify(rowJson)+"'>"+rowJson.templateName+"</option> ";


                        }
                        $('#templateleadcampaign').html(html)

                        quill = new Quill('.editor', {
                            theme: 'snow'
                        });


                    },
                    error: function (err) {

                    }

                })


            },
            error: function (err) {

            }

        })


    }
    $(document).on("change","#templateleadcampaign",function () {
        var json=JSON.parse($(this).val());
        json.templateContent=decodeURI(json.templateContent);

        if(json.hasUrl)
        {


            $('#urldivquickcampaign').fadeIn();
            $('#urldivquickcampaign').find("input").attr("required",true)

        }
        else{
            $('#urldivquickcampaign').css("display","none")
            $('#urldivquickcampaign').find("input").attr("required",false)

        }
    })



    function loadhtmlintogenerateticket(url, subject, formsubmiturl, json, typ) {

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (html) {
                $('#dynamicformbodymodel').html(html);
                $('#dunamicmodelheader').html(subject);
                $('#dynamicformmodel').modal("show");
                $('#ticketformjsonhidden').val(json);
                $('#ticketformurl').val(formsubmiturl);
                $('#ticketformtype').html($('#type').html());
                $('#departmentform').html($('#ticketdept').html())


            },
            error: function (err) {

            }

        })


    }

    function loadhtmlintoevent(url, subject, formsubmiturl, json, typ,optionlist) {

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (html) {
                $('#dynamicformbodymodel').html(html);
                $('#dunamicmodelheader').html(subject);
                $('#dynamicformmodel').modal("show");
                $('#logeventformjsonhidden').val(json);
                $('#logeventformurl').val(formsubmiturl);


                if (typ == "Update") {


                    json = JSON.parse(json);
                    $('#createbtnlogeventform').html("Update");
                    $('#subjectlogeventform').val(json.eventType);
                    $('#messagelogeventform').val(json.message)
                }


                optionlist=optionlist.split(",");
                var selecthtml='';
                for(var i=0;i<optionlist.length;i++)
                {
                    selecthtml=selecthtml+"<option val='"+optionlist[i]+"'>"+optionlist[i]+"</option>"
                }
                $('#subjectlogeventform').html(selecthtml)


            },
            error: function (err) {

            }

        })


    }


    $('#loadmoreleadticket').click(function () {
        loadleadticket($('#id').val(), 10, $('#offsetleadticket').val())
    });
    var jsonlead = JSON.parse(localStorage.getItem("rolelistlocal"));

    if (jsonlead.ROLE_BULKUPLOADLEAD) {
        $('#addbulklead').removeAttr("disabled")

    }
    if (jsonlead.ROLE_SINGLEADDLEAD) {
        $('#addnewuser').removeAttr("disabled")

    }
    $(document).on("click",".downloadrecording",function (){

        //recorddiv
        var url=$(this).attr("hidatr")

        if(url==null)
        {
            return ;
        }
        let recordingLocation= "https://prpmobility.com/voice/"
        <%--let recordingLocation = <%=propertyService.findProperty("IVR","recordingLocation")%>--%>
        url= url.replace("/VOICEPROMPTS/",recordingLocation)

        console.log("recodingUrl "+url);
        let div = $(this).closest("li").find(".recorddiv");
        div.html('<audio controls style="width: 170px"><source src="'+url+'" type="audio/wav"></audio><button class="btn btn-sm closeaudio">x</button>')

        // window.open(url)
    })

$(document).on("click",".closeaudio",function(){
    let div = $(this).closest("li").find(".recorddiv");
    div.html("");
})

    function loadleadevent(leadId) {

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/logevent/getbyleadid?id=" + leadId,
            success: function (json) {

                var html = "";
                for (var i = 0; i < json.length; i++) {
                    var recordingUrl="";
                    var uri="";
                    let recordingLocation= "https://prpmobility.com/voice/";
                    uri=json[i].recordingUrl;

                    if(json[i].eventType=="Click To Call")
                    {
                     recordingUrl="<li><headertag>Recording</headertag>: <i title='Click Here To Download Recording' hidatr='"+json[i].recordingUrl+"' class='fa fa-cloud-download downloadrecording'></i><div class='recorddiv'></div></li>";

                     }
                    // cosole.log("recordingUrl:: "+recordingUrl)
                    html = html + " <tr hidatr='" + JSON.stringify(json[i]) + "'>\n" +
                        "                                                            <td>\n" +
                        "                                                                <ul>\n" +
                        "                                                                    <li><headertag>Event Type</headertag>: " + json[i].eventType + " </li>\n" +
                        "                                                                    <li><headertag>Call Start Date</headertag>: " + json[i].callStartTime + " </li>\n" +
                        "                                                                    <li><headertag>call End Date</headertag>: " + json[i].callEndTime + " </li>\n" +
                        "                                                                    <li><headertag>Call Status</headertag>: " + json[i].call_status + " </li>\n" +
                        "                                                                    <li><headertag>Location</headertag>: " + json[i].location + " </li>\n" +
                        "                                                                    <li><headertag>Call Duration</headertag>: " + json[i].duration + " </li>\n" +
                        "                                                                    <li><headertag>Message</headertag>:" + json[i].message + "</li>\n" +
                        // "                                                                    <li><headertag>By</headertag>:" + json[i].createBy + "</li>\n" +
                        "                                                                    <li><headertag>Timing</headertag>:" + json[i].createAt + "</li>\n" +
 
                        // "                                                                    <li><headertag>Recording URL</headertag>: " + "<audio controls style='width: 170px'><source src=' " +json[i].recordingUrl+" ' type='audio/wav'> </audio> " + '</li>\n' +
                        "     "+recordingUrl+"                                                               \n" +

                        "                                                                </ul>\n" +
                        "                                                            </td>\n" +
                        "\n" ;
                    if(json[i].eventType != "Bulk Update"){
                        html = html + "<td><i class=\"hvr-buzz-out fa fa-trash deletelogeventbtn\"></i> <i class=\"hvr-buzz-out fa fa-pencil-square editlogeventbtn \"></i></td>";
                    }
                    html = html + "</tr>";
                }


                $('#leadeventtbody').html(html)


            },
            error: function (err) {

            }

        })
    }

    function loadleadtransferhistory(leadId) {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/leadtransfer/getbyid?id=" + leadId,
            success: function (json) {

                var html = "";
                for (var i = 0; i < json.length; i++) {
                    html = html + " <tr>\n" +
                        "                                                            <td>\n" +
                        "                                                                <ul>\n" +
                        "                                                                    <li><headertag>To</headertag>: " + json[i].tousername + " </li>\n" +
                        "                                                                    <li><headertag>By</headertag>:" + json[i].createBy + "</li>\n" +
                        "                                                                    <li><headertag>Timing</headertag>:" + json[i].datetiming + "</li>\n" +
                        "                                                                </ul>\n" +
                        "                                                            </td>\n" +
                        "\n" +
                        "                                                       </tr>";
                }


                $('#leadtransfertbody').html(html)


            },
            error: function (err) {

            }

        })
    }

    function loadleadstatustranfer(leadId) {

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/statustranfer/get?id=" + leadId,
            success: function (json) {

                var html = "";
                for (var i = 0; i < json.length; i++) {
                    html = html + " <tr>\n" +
                        "                                                            <td>\n" +
                        "                                                                <ul>\n" +
                        "                                                                    <li><headertag>From</headertag>: " + json[i].preStage +" ("+ json[i].preStatus +") </li>\n" +
                        "                                                                    <li><headertag>To</headertag>: " + json[i].stage +" ("+ json[i].status +") </li>\n" +
                        "                                                                    <li><headertag>By</headertag>:" + json[i].createBy + "</li>\n" +
                        "                                                                    <li><headertag>Timing</headertag>:" + json[i].readableDate + "</li>\n" +
                        "                                                                </ul>\n" +
                        "                                                            </td>\n" +
                        "\n" +
                        "                                                       </tr>";
                }


                $('#leadstatustranfertbody').html(html)


            },
            error: function (err) {

            }

        })
    }

    function loadleadfileattchment(leadId) {

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/file/getfilesbycreatebylead?createby=" + leadId,
            success: function (json) {

                var html = "";
                for (var i = 0; i < json.length; i++) {
                    html = html + " <tr hidatr='" + JSON.stringify(json[i]) + "'>\n" +
                        "                                                            <td>\n" +
                        "                                                                <ul>\n" +
                        "                                                                    <li><headertag>Filename</headertag>: " + json[i].name + " </li>\n" +
                        "                                                                    <li><headertag>Timing</headertag>:" + json[i].createAt + "</li>\n" +
                        "                                                                </ul>\n" +
                        "                                                            </td>\n" +
                        "\n" +
                        "                    <td><i  class=\"hvr-buzz-out fa fa-cloud-download downloadattachmentfile\"></i><i class=\"hvr-buzz-out fa fa-trash deleteattachmentbtn\"></i> </td>                                             </tr>";
                }


                $('#attachmenttbody').html(html)


            },
            error: function (err) {

            }

        })
    }

    function loadleadlogtask(leadId) {

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/logtask/getlogtask?id=" + leadId,
            success: function (json) {

                var html = "";
                for (var i = 0; i < json.length; i++) {
                    var notification = "<i style='color: red' title='Text Notification Is Off' class=\"hvr-buzz-out fa fa-bell-slash\"></i>";

                    if (json[i].txtMsgNotification == true) {
                        notification = "<i style='color: green' title='Text Notification Is On' class=\"hvr-buzz-out fa fa-bell\"></i>"
                    }
                    html = html + " <tr hidatr='" + JSON.stringify(json[i]) + "'>\n" +
                        "                                                            <td>\n" +
                        "                                                                <ul>\n" +
                        "                                                                    <li>Subject: " + json[i].subject + " </li>\n" +
                        "                                                                    <li>Message:" + json[i].message + "</li>\n" +
                        "                                                                    <li>By:" + json[i].createBy + "</li>\n" +
                        "                                                                    <li>Timing:" + json[i].dateTimeTask + "</li>\n" +
                        "                                                                    <li>" + notification + "</li>\n" +
                        "                                                                </ul>\n" +
                        "                                                            </td>\n" +
                        "\n" +
                        "             <td><i class=\"hvr-buzz-out fa fa-trash deletelogtaskbtn\"></i> <i class=\"hvr-buzz-out fa fa-pencil-square editlogtaskbtn \"></i></td>                                            </tr>";
                }


                $('#leadtasktbody').html(html)


            },
            error: function (err) {

            }

        })
    }

    function loadleadticket(leadid, limit, offset) {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/ticket/getticketbyleadid?id=" + leadid + "&limit=" + limit + "&offset=" + offset,
            success: function (json) {

                json = json.data;

                var html = "";
                for (var i = 0; i < json.length; i++) {
                    html = html + " <tr>\n" +
                        "                                                            <td>\n" +
                        "                                                                <ul>\n" +
                        "                                                                    <li>TicketId:  " + json[i].id + "</li>\n" +
                        "                                                                    <li>Subject: " + json[i].subject + "</li>\n" +
                        "                                                                    <li>Ticket Status: " + json[i].ticketstatus + "</li>\n" +
                        "                                                                    <li>Ticket Priority: " + json[i].priority + "</li>\n" +
                        "                                                                    <li>User: " + json[i].username + "</li>\n" +
                        "                                                                    <li>Last Update: " + json[i].updatedate + "</li>\n" +
                        "                                                                    <li>View Ticket: <i hidid='" + json[i].id + "' title='Click Here To View Ticket' class='hvr-buzz-out fa fa-mail-forward viewticket'></i></li>\n" +
                        "                                                                </ul>\n" +
                        "                                                            </td>\n" +
                        "\n" +
                        "                                                        </tr>";
                }


                if (json.length > 0) {
                    var offset = $('#offsetleadticket').val() || 0;
                    $('#leadtickettbody').find("tr:last").before(html);

                    offset = parseInt(offset) + 1;
                    $('#offsetleadticket').val(offset)
                }


            },
            error: function (err) {

            }

        })
    }

    $(document).on("click", ".viewticket", function () {
        var id = $(this).attr("hidid");
        window.open("${pageContext.request.contextPath}/ticketportal?id=" + id)
    });
    $(document).on("click", ".deletelogtaskbtn", function () {
        var json = $(this).closest("tr").attr("hidatr");
        var leadJson = JSON.parse($("#hidjson").val());
        var leadId = leadJson.id;

        var ths = $(this);
        json = JSON.parse(json);
        swal({
            title: "Are you sure?",
            text: "You Want To Delete This Task ?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/api/logtask/delete?id=" + json.id+"&leadId="+leadId,
                        type: 'DELETE',
                        success: function (result) {
                            swal("Task deleted!", {
                                icon: "success",
                            }),
                                ths.closest("tr").remove()

                        },
                        error: function (err) {

                            swal({
                                title: "Error!",
                                text: "Could Not Delete Log Task",
                                icon: "error",
                                button: "Back!",
                            });
                        }
                    });
                }
            });

    });

    $(document).on("click", ".editlogtaskbtn", function () {
        var json = $(this).closest("tr").attr("hidatr");
        var selecthtml="Call,Email";
        loadhtmlintotask("${pageContext.servletContext.contextPath}/html/quicktask.html", "Quick Task", "${pageContext.servletContext.contextPath}/api/logtask/update", json, "Update",selecthtml);

    });

    $(document).on("click", ".deletelogeventbtn", function () {
        var json = $(this).closest("tr").attr("hidatr");
        var leadJson = JSON.parse($("#hidjson").val());
        var leadId = leadJson.id;
        var ths = $(this);
        json = JSON.parse(json);
        swal({
            title: "Are you sure?",
            text: "You Want To Delete This Event ?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/api/logevent/delete?id=" + json.id+"&leadId="+leadId,
                        type: 'DELETE',
                        success: function (result) {
                            swal("Event deleted!", {
                                icon: "success",
                            }),
                                ths.closest("tr").remove()

                        },
                        error: function (err) {

                            swal({
                                title: "Error!",
                                text: "Could Not Delete Log Event",
                                icon: "error",
                                button: "Back!",
                            });
                        }
                    });
                }
            });

    });
    $(document).on("click", ".editlogeventbtn", function () {
        var json = $(this).closest("tr").attr("hidatr");
        var selecthtml="Call,Email,Not Satisfied,Not Wanted"
        loadhtmlintoevent("${pageContext.servletContext.contextPath}/html/quicklogevent.html", "Quick Event", "${pageContext.servletContext.contextPath}/api/logevent/update", json, "Update",selecthtml);

    });

    $(document).on("click", ".deleteattachmentbtn", function () {
        var json = $(this).closest("tr").attr("hidatr");
        var ths = $(this);
        json = JSON.parse(json);
        swal({
            title: "Are you sure?",
            text: "You Want To Delete This Attachment ?",
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
                            swal("Attachment deleted!", {
                                icon: "success",
                            }),
                                ths.closest("tr").remove()

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

    });
    $('#filtersearch').click(function () {

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "${pageContext.request.contextPath}/html/leadfilter.html",
            success: function (html) {
                $('#dynamicformbodymodel').html(html);
                $('#dunamicmodelheader').html("Lead Filter  <i class=\"hvr-buzz-out fa fa-filter\"></i>");
                $('#dynamicformmodel').modal("show");
                $('#logeventformjsonhidden').val(json);

                $('#leadstagefilterform').html($('#leadstagehidden').html());
                $('#leadstatusfilterform').html($('#leadstatus').html());
                $('#leadsourcefilterform').append($('#leadSource').html());
                $('#leadproductfilterform').append($('#interestedProduct').html());
                $('#leadtypefilterform').append($('#clientType').html());
                $('#leaduserfilterform').html($('#userlist').html())
                $('#leadpriorityfilterform').html($('#leadpriority').html())
                populateCheckboxDiv();


            },
            error: function (err) {

            }

        })


    });

    $(document).on("change", "#leadstagefilterform", function () {
        var ths = $(this);
        var value = ths.val() || 0;
        if (value == 0) {
            return;
        }
        var json = JSON.parse(ths.find('option:selected').attr("hidatr"));

        var option = json.option;
        var html = "<option value=''>-----Select Lead Status----</option>";
        for (var i = 0; i < option.length; i++) {
            html = html + "<option value='" + option[i].value + "'> " + option[i].value + "</option>"
        }
        $('#leadstatusfilterform').html(html)


    });

    function loadhtmlintoleadbulkupload(url, subject, formsubmiturl) {

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (html) {
                $('#dynamicformbodymodel').html(html);
                $('#dunamicmodelheader').html(subject);
                $('#dynamicformmodel').modal("show");
                $('#leaduserbulktoform').html($('#userlist').html());
                $('#leadfilebulkuploadurl').val(formsubmiturl)


            },
            error: function (err) {

            }

        })


    }

    $('#addbulklead').click(function () {
        loadhtmlintoleadbulkupload("${pageContext.servletContext.contextPath}/html/bulkuploadlead.html", "Lead Bulk Upload", "${pageContext.servletContext.contextPath}/api/lead/bulkuploadlead");


    });



    $(document).on("click", "#downloadsamplefile", function () {

        //window.open("${pageContext.servletContext.contextPath}/api/files/downloadsamplefile?filename=bulkleadupload.xlsx")

        // console.log("Downloading Sample File")


        var wb = XLSX.utils.book_new();
        wb.Props = {
            Title: "Lead Bulk Upload Sample",
            Subject: "Sample",
            Author: "PRP Lead Management System",
            CreatedDate: new Date()
        };

        wb.SheetNames.push("Sample");
        let ws_data = [];
        //ws_data.push(["Salutation","Firstname","Lastname","Phonenumber","Email","City","State","Pincode","Country","Address","Company","Type","Source","Description","Product","ProspectiveBuissness","Priority"]);
        ws_data.push(["Parent_Name","Relation_to_the_child","primary_language_of_the_child","preferred_language_of_parrent","Child_Name","Gender_of_the_child","Date_of_birth","Contact_Number","Email_ID","City","State","Country","Address","Pincode","Basic_concerns_of_the_child","Does_Your_Child_Have_Development_Delays","We_can_support_you_in_different_ways_You_suggest_where_you_want_to_start_with","Does_your_child_have_any_academic_or_learning_concerns","Does_your_child_have_difficulty_maintaining_friendships","Payment","preferred_time_of_clinical_call","PG_DB_Name","Enabler","BPO","PG_BD_Manager","Program","Lead_Type","Source","Campaign","Keyword","Lead_Date","PageName","Lead_Score"]);
        var arr = [];

        arr.push("John Doe")
        arr.push("Father");
        arr.push("English");
        arr.push("Alex Doe")
        arr.push("English");
        arr.push("Male")
        arr.push("2020-08-01")
        arr.push("9811927272")
        arr.push("johndoe@gmail.com")
        arr.push("Jersy")
        arr.push("Oklahoma")
        arr.push("America")
        arr.push("Q-51")
        arr.push("120092")
        arr.push("no basic concerns")
        arr.push("no")
        arr.push("Support for learning ability");
        arr.push("Learning process is comparitvely slow");
        arr.push("Yes");
        arr.push("2400");
        arr.push("2020-08-05T12:58");
        arr.push("PG BD Name");
        arr.push("Enabler");
        arr.push("BPO");
        arr.push("PG BD Manager");
        arr.push("Speech Therapy")
        arr.push("Individual")
        arr.push("Test")
        arr.push("FB_Campaign_June")
        arr.push("Doctor")
        arr.push("2020-08-01")
        arr.push("Moms Belief FB Page")
        arr.push("10")


        ws_data.push(arr);


        var ws = XLSX.utils.aoa_to_sheet(ws_data);
        wb.Sheets["Sample"] = ws;
        var wbout = XLSX.write(wb, {bookType:'xlsx',  type: 'binary'});
        saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream"}), 'bulk_upload_Sample.xlsx');

    });



    $(document).on("click", ".downloadattachmentfile", function () {
        var json = JSON.parse($(this).closest("tr").attr("hidatr"));
        window.open("${pageContext.servletContext.contextPath}/api/file/downloadfile?id=" + json.id)
    });


    function loadLeadStatusByLeadStage(stage, statusvalue, callback) {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/leadstage/getbyname?name=" + stage,
            success: function (json) {

                var html = "";
                json = json.option;
                for (var i = 0; i < json.length; i++) {


                    if (json[i].value == statusvalue) {

                        html = html + "<button  namestatus='" + JSON.stringify(json[i]) + "' style=\"background-color: " + json[i].color + "\" type=\"button\" class=\"btn btn-labeled btn-success m-b-5 leadstatuscls selectedtrue\">" +
                            "   " + json[i].value + "     " +
                            "        </button>\n";

                    } else {
                        html = html + "<button  namestatus='" + JSON.stringify(json[i]) + "' style=\"background-color: " + json[i].color + "\" type=\"button\" class=\"btn btn-labeled btn-success m-b-5 leadstatuscls\">" +
                            "   " + json[i].value + "     " +
                            "        </button>\n";
                    }


                }
                $('#leadstageval').html(stage);
                $('#leadstatus').html(html);
                callback("success")


            },
            error: function (err) {

            }

        })
    }

    $(document).on("submit", "#leaddynamicforms", function (e) {
        e.preventDefault();
        swal({
            title: "Are you sure?",
            text: $('#hiddendynamicmessage').val(),
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Success!", {
                        icon: "success",
                    });
                    $('#cancelbtndynmaicform').trigger("click");
                    changeStageandStatusOfLead($('#id').val(), $('#nextstagehidden').val(), $('#nextstatushidden').val(), function (data) {
                        if (data == "success") {
                            loadLeadStatusByLeadStage($('#nextstagehidden').val(), $('#nextstatushidden').val(), function () {
                                $('.editfield').fadeIn();
                            });
                        }
                    })

                }
            });
    });

    function changeStageandStatusOfLead(leadid, stage, status, callback) {

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/lead/updateleadstatusandstage?leadstatus=" + status + "&leadstage=" + stage + "&id=" + leadid,
            success: function (json) {

                console.log("Json Object Response is "+ JSON.stringify(json));

                if(json.msg == "Disposition Missing on Moms CRM"){
                    swal({
                        title: json.msg,
                        text: "",
                        icon: "warning",
                        button: "Ok!",
                    })
                }

                callback("success")

            },
                error: function (err) {
                    swal({
                        title: "Registration To MBOPS Failed!",
                        text: err.responseJSON.message,
                        icon: "error",
                        button: "Ok!",
                    }).then((ok)=>{
                        if(isTransfer){
                            //TRANSFER LEAD TO A DEPARTMENT
                            transferLeadToDepartment(departments[1],function (){
                                $('#cancelbtn').trigger("click")
                            });
                        }
                    })
                }


            // },
            // error: function (err) {
            //
            // }

        })
    }

    function changeStatusOnly(leadid, status, callback) {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/lead/updateleadstatus?leadstatus=" + status + "&id=" + leadid,
            success: function (json) {
                $(".glyphicon-ok").removeClass("glyphicon-ok");
                $(".selectedtrue").removeClass("selectedtrue");
                $('#leadstatushidden').val(status);

                var btn = $('#leadstatus').find("button");
                for (var m = 0; m < btn.length; m++) {

                    if (status.toString().trim() == btn.eq(m).text().trim()) {
                        btn.eq(m).addClass("selectedtrue");
                    }
                }

                console.log("Json Object Response is "+ json.msg);
                if(json.msg.toString() == "Disposition Missing on Center CRM"){
                    swal({
                        title: json.msg,
                        text: "",
                        icon: "warning",
                        button: "Ok!",
                    })
                }
                callback("success")

                callback("success")


            },
            error: function (err) {

            }

        })
    }


    function registerToMBOPS(id,departments,isStateSet,isTransfer,useCustomIds,leadJson,flag){
        //flag : Register\Register1
        //transfer lead : to departments[0] on registration success, to departments[1] on registration failure

        //LEAD HAS STATE(E.G. PUNJAB) SET
        if(isStateSet){
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "${pageContext.servletContext.contextPath}/api/mbops/register?leadId=" + id + "&useCustomIds="+useCustomIds+"&flag="+flag,
                success: function (json) {
                    swal({
                        title: "Registration Success!",
                        text: "Lead Has Been Successfully Registered To MBOPS",
                        icon: "success",
                        button: "Ok!",
                    }).then(()=>{
                      //  if(type == "Register1"){
                          //  changeStatusOnly($('#id').val(), "Center Enrolled", function (data) {

                          //  });
                      //  }else{

                            // changeStatusOnly($('#id').val(), "Registered To MBOPS", function (data) {
                            //
                            // });
                        changeStatusOnly($('#id').val(), "Enrolled", function (data) {

                        });
                      //  }

                    }).then((ok)=>{

                        //SENDING EMPTY HASHMAP AS THIS NOT BEING USED ANYWHERE
                        let hashMap = {}
                        hashMap["notifycentre"] = "false";
                        triggerLeadNotification($('#id').val(),hashMap,leadJson)

                       if(isTransfer){
                           //TRANSFER LEAD TO A DEPARTMENT
                           transferLeadToDepartment(departments[0],function (){
                               $('#cancelbtn').trigger("click")
                           });
                       }
                    });
                },
                error: function (err) {
                    swal({
                        title: "Registration To MBOPS Failed!",
                        text: err.responseJSON.message,
                        icon: "error",
                        button: "Ok!",
                    }).then((ok)=>{
                        if(isTransfer){
                            //TRANSFER LEAD TO A DEPARTMENT
                            transferLeadToDepartment(departments[1],function (){
                                $('#cancelbtn').trigger("click")
                            });
                        }
                    })
                }

            })
        }else{

            swal({
                title: "Registration To MBOPS Failed!",
                text: "Lead's State(Location) not set!",
                icon: "error",
                button: "Ok!",
            }).then((ok)=>{
                if(isTransfer){
                    //TRANSFER LEAD TO A DEPARTMENT
                    transferLeadToDepartment(departments[1],function (){
                        $('#cancelbtn').trigger("click")
                    });
                }
            })
           }

    }

    function convertLead(id, createdate, callback) {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/lead/convertlead?createdate=" + createdate + "&id=" + id,
            success: function (json) {
                callback("success")


            },
            error: function (err) {

            }

        })
    }

    $.ajax({
        url: "${pageContext.request.contextPath}/api/leadstage/getall",
        type: 'GET',
        success: function (json) {
            var html = "<option value=''>---Select Lead Stage----</option>";
            for (var i = 0; i < json.length; i++) {


                html = html + "<option hidatr='" + JSON.stringify(json[i]) + "' value='" + json[i].stage + "'>" + json[i].stage + "</option>"
            }
            $('#leadstagehidden').html(html)
        },
        error: function (err) {


        }
    });


    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '${pageContext.servletContext.contextPath}/api/roles/getroles',
        success: function (json) {
            var html = "<option value=''>---Select Department---</option>";
            for (var i = 0; i < json.length; i++) {
                html = html + "<option value='" + json[i].roleName + "'>" + json[i].roleName + "</option>"

            }
            $(id).html(html);
            $("#ticketdept").html(html)

        },
        error: function (err) {

        }

    });


    //================================LOAD Ticket Type


    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '${pageContext.servletContext.contextPath}/api/tickettype/getallactive',
        success: function (json) {
            var html = "<option value=''>---Select Ticket Type---</option>";
            for (var i = 0; i < json.length; i++) {
                html = html + "<option value='" + json[i].name + "'>" + json[i].name + "</option>"

            }
            $('#type').html(html)

        },
        error: function (err) {

        }

    });


    //================================LOAD Priority


    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '${pageContext.servletContext.contextPath}/api/priority/getallactive',
        success: function (json) {
            var html = "<option value='0'>---Select Priority---</option>";
            for (var i = 0; i < json.length; i++) {
                html = html + "<option value='" + json[i].name + "'>" + json[i].name + "</option>"

            }
            $('#priority').html(html)

        },
        error: function (err) {

        }


    });
    $(document).on("change", "#departmentform", function () {

        var dept = $("#departmentform").val();
        var agent = '0';
        var id = "#userform";
        loadagents(id, dept, agent);
    });

    function loadagents(id, dept, value) {
        var url = '${pageContext.servletContext.contextPath}/api/user/getuserbydepartment?department=' + dept;
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (json) {
                var html = "<option value='0'>---Select Agent---</option>";
                for (var i = 0; i < json.length; i++) {
                    if (json[i].id == value) {
                        html = html + "<option selected value='" + json[i].id + "'>" + json[i].username + "</option>"
                    } else {
                        html = html + "<option value='" + json[i].id + "'>" + json[i].username + "</option>"
                    }
                }

                $(id).html(html);


            },
            error: function (err) {

            }

        })
    }

    $(document).on("click", "#addnewrowticketing", function () {
        $('#tbodyattachmentticket').append("<tr><td ><form><input name='file' type='file' class='form-control attachmentaddticket'></form></td><td atrhid='0'><i class='fa fa-trash deleteattachmentticket'></i></td></tr>")
    });
    $(document).on("click", ".deleteattachmentticket", function () {
        var id = $(this).closest("td").attr("atrhid");

        var ths = $(this);
        if (id == 0) {
            ths.closest("tr").remove();
        } else {
            $.ajax({
                type: "DELETE",
                contentType: "application/json",
                url: "${pageContext.servletContext.contextPath}/api/file/delete?id=" + id,
                success: function (json) {

                    ths.closest("tr").remove();
                },
                error: function (err) {

                }

            });
        }
    });
    $(document).on("change", ".attachmentaddticket", function () {
        var ths = $(this);
        var form = $(this).closest("form")[0];
        var data = new FormData(form);
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
                ths.closest("tr").html("<td   title='" + data.createAt + "'>" + data.name + "</td><td atrhid='" + data.id + "'><i title='Click Here To Delete This Document' class='deleteattachmentticket hvr-buzz-out fa fa-trash'></i></td>")

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

    function loadUserBased(assignby, callback) {


        var url = "${pageContext.request.contextPath}/api/lead/getuserbytype?type=" + assignby;
        $.ajax({
            type: 'GET',

            contentType: 'application/json',
            url: url,
            success: function (data) {
                callback(data)


            },
            error: function (err) {


            }
        });

    }

    $(document).on("submit", "#leadbulkuploadform", function (e) {
        e.preventDefault();


        var type=0;
        var filterValue=$('#leaduserbulktoform').val();
        var assignby = $("input[name='radioassignby']:checked").val();
        if(assignby=="Source")
        {
            type=1;
        }
        if(assignby=="Product")
        {
            type=2;
        }
        if(assignby=="Type")
        {
            type=3;
        }
        if(assignby=="User")
        {
            type=4;
        }



        var leadUploadLimit = <%=propertyService.findProperty("Lead","maxBulkUploadSize")%>;


                    var fileToLoad = document.getElementById("leadbulkuploadfile").files[0];

                    var fileReader = new FileReader();

                    fileReader.onload = function (fileLoadedEvent) {

                        var data = fileLoadedEvent.target.result;
                        var workbook = XLSX.read(data, {
                            type: 'binary'
                        });

                        workbook.SheetNames.forEach(function (sheetName) {

                            // Here is your object {raw: false}
                            //var XL_row_object = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[sheetName]);
                            var XL_row_object = XLSX.utils.sheet_to_json(workbook.Sheets[sheetName],{raw: false});

                            var json = XL_row_object;
                            var arrlist = [];
                            var map = {};
                            var userfiltertypecount = {};
                            if (json.length > leadUploadLimit) {
                                showError("Maximum Bulk Upload Limit is "+leadUploadLimit )
                                return;
                            }

                            console.log("json.length",json.length)

                            for (var i = 0; i < json.length; i++) {

                                console.log("Parent_name",json[i].Parent_Name)
                                console.log("json.length",json.length)

                                if (json[i].Parent_Name == null) {
                                    showError("Parent Name cannot be null at row " + (parseInt(i) + 1));
                                    return;

                                }
                                if (json[i].Email_ID == null) {
                                    showError("Email cannot be null at row " + (parseInt(i) + 1));
                                    return;

                                }
                                if (json[i].Contact_Number == null) {
                                    showError("Phonenumber cannot be null at row " + (parseInt(i) + 1));
                                    return;

                                }

                                if (json[i].Contact_Number.includes("@")) {
                                    showError("Invalid Phonenumber at row " + (parseInt(i) + 1));
                                    return;
                                }

                                map = {};

                                let leadSource = json[i].Source || "";
                                let program = json[i].Program || "";
                                let leadType = json[i].Lead_Type || "";

                                leadSource = leadSource.trim();
                                program = program.trim();
                                leadType = leadType.trim();

                                map["gender"] = json[i].Gender_of_the_child || "";
                                map["parentName"] = json[i].Parent_Name || "";
                                map["childName"] = json[i].Child_Name || "";
                                map["phonenumber"] = json[i].Contact_Number || "";

                                map["leadSource"] = leadSource;
                                map["interestedProduct"] = program;
                                map["clientType"] = leadType;


                                map["descrip"] = json[i].Basic_concerns_of_the_child || "";
                                map["city"] = json[i].City || "";
                                map["state"] = json[i].State || "";
                                map["country"] = json[i].Country || "0";
                                map["country"] = map["country"].trim();
                                map["country"] = map["country"].toLowerCase();
                                map["email"] = json[i].Email_ID || "";
                                map["pincode"] = json[i].Pincode || "";
                                map["address"] = json[i].Address || "";
                                map["leadsourceinner"] = "Bulk Upload";
                                map["dob"] = json[i].Date_of_birth || "";
                                map["childDevDelay"] = json[i].Does_Your_Child_Have_Development_Delays || "";
                                map["relation"] = json[i].Relation_to_the_child || "";
                                map["childLanguage"] = json[i].primary_language_of_the_child || "";
                                map["parentLanguage"] = json[i].preferred_language_of_parrent || "";
                                map["supportOption"] = json[i].We_can_support_you_in_diferent_ways_You_suggest_where_you_want_to_start_with || "";
                                map["learningConcern"] = json[i].Does_your_child_have_any_academic_or_learning_concerns || "";
                                map["difficultyInFriendship"] = json[i].Does_your_child_have_difficulty_maintaining_friendships || "";
                                map["payment"] = json[i].Payment || "";
                                map["clinicalCallTime"] = json[i].preferred_time_of_clinical_call || "";
                                map["pgBdName"] = json[i].PG_DB_Name || "";
                                map["professionFilled"] = json[i].Enabler || "";
                                map["childPlayPattern"] = json[i].BPO || "";
                                map["pgBdManager"] = json[i].PG_BD_Manager || "";
                                map["pageName"] = json[i].PageName || "";

                                map["leadPriority"] = json[i].Campaign || "";
                                map["keyword"] = json[i].Keyword || "";
                                map["leadDate"] = json[i].Lead_Date || "";//DATE ON WHICH LEASD WAS RECEIVED (NOT UPLOADED INTO LMS)
                                map["leadScore"] = json[i].Lead_Score || 0;

                                arrlist.push(map)

                            }

                            swal({
                                title: "Are you sure?",
                                text: "You Want To Upload " + arrlist.length + " Leads Based on " + assignby + "? ",
                                icon: "warning",
                                buttons: true,
                                dangerMode: true,
                            })
                                .then((willDelete) => {
                                    if (willDelete) {
                                        $('#preloader').css("display", "block");
                                        $.ajax({
                                            type: 'POST',
                                            data: JSON.stringify(arrlist),
                                            contentType: 'application/json',
                                            url: "${pageContext.servletContext.contextPath}/api/lead/bulkuploadlead?filtertype="+type+"&filterValue="+filterValue,
                                            success: function (data) {
                                                table.ajax.reload();

                                                var wb = XLSX.utils.book_new();
                                                wb.Props = {
                                                    Title: "Post Upload Stats",
                                                    Subject: "Stats",
                                                    Author: "PRP Lead Management System",
                                                    CreatedDate: new Date()
                                                };

                                                wb.SheetNames.push("Status");
                                                let ws_data = [];
                                                //ws_data.push(["Salutation","Firstname","Lastname","Phonenumber","Email","City","State","Pincode","Country","Address","Company","Type","Source","Description","Product","ProspectiveBuissness","Priority"]);
                                                ws_data.push(["PhoneNumber","Status"]);
                                                let phoneArr = Object.keys(data);
                                                let uploadedLeadCount = 0;
                                                for (let phone of phoneArr) {
                                                    console.log("phone :",phone)
                                                    console.log("data[phone] :",data[phone])
                                                    let arr = [];
                                                    arr.push(phone)
                                                    arr.push(data[phone]);

                                                    if(data[phone] == "Uploaded"){
                                                        uploadedLeadCount++;
                                                    }
                                                    ws_data.push(arr);
                                                }

                                                console.log("ws_data",ws_data)
                                                var ws = XLSX.utils.aoa_to_sheet(ws_data);
                                                wb.Sheets["Status"] = ws;
                                                var wbout = XLSX.write(wb, {bookType:'xlsx',  type: 'binary'});

                                                swal({
                                                    title: "Success!",
                                                    text: "Successfully Uploaded "+uploadedLeadCount+"Lead(s)",
                                                    icon: "success",
                                                    button: "Ok!",
                                                }).then((willDelete) => {
                                                  saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream"}), 'bulk_upload_Status.xlsx');
                                                });
                                                $('#preloader').css("display", "none");

                                                $('#cancelbtnbulkuploadform').trigger("click")

                                            },
                                            error: function (err) {

                                                swal({
                                                    title: 'Error',
                                                    text: err.responseJSON.message,
                                                    icon: 'error',
                                                    button: 'Ok'
                                                });
                                                $('#preloader').css("display", "none")

                                            }
                                        });

                                    }
                                });


                        })


                    };
                    fileReader.readAsBinaryString(fileToLoad);




    });
    $(document).on("click", ".refreshbtnaddnew", function () {

        var json = JSON.parse($('#hidjson').val());
        var type = $(this).closest("h4").find("span").html();

        switch (type) {
            case "Log Event":
                loadleadevent(json.id);
                break;
            case "Task":
                loadleadlogtask(json.id);style=' '
                break;
            case "Attachments":
                loadleadfileattchment(applicationPrefix + json.id);
                break;style=' '
            case "Ticket":
                loadleadticket(json.id, 10, 0);
                break;
            case "Status Transfer":

                loadleadstatustranfer(json.id);
                break;
            case "Transfer History":
                loadleadtransferhistory(json.id);
                break;
        }


    });

    $(document).on("click", ".addnewevent", function () {


        var type = $(this).closest("h4").find("span").html();

        switch (type) {
            case "Log Event":
                var selecthtml="Call,Email,Not Satisfied,Not Wanted"

                loadhtmlintoevent("${pageContext.servletContext.contextPath}/html/quicklogevent.html", "Quick Log Event <i class=\"hvr-buzz-out fa fa-pencil\"></i>", "${pageContext.servletContext.contextPath}/api/logevent/insert", $('#hidjson').val(), "Create",selecthtml);

                break;
            case "Task":
                var selecthtml="Call,Email"
                loadhtmlintotask("${pageContext.servletContext.contextPath}/html/quicktask.html", "Quick Task <i class=\"hvr-buzz-out fa fa-tasks\"></i>", "${pageContext.servletContext.contextPath}/api/logtask/insert?increasetime=false", $('#hidjson').val(), "Create",selecthtml);

                break;
            case "Attachments":
                if(isFeatureAvailable("addattachement") == true){
                loadhtmlintoquickfileupload("${pageContext.servletContext.contextPath}/html/quickdocumentupload.html", "Quick File Upload <i class=\"hvr-buzz-out fa fa-file-archive-o\"></i>", $('#hidjson').val());
                }
                break;
            case "Ticket":
                if(isFeatureAvailable("leadticket") == true){
                loadhtmlintogenerateticket("${pageContext.servletContext.contextPath}/html/quickticket.html", "Quick Ticket <i class=\"hvr-buzz-out fa fa-ticket\"></i>", "${pageContext.servletContext.contextPath}/api/ticket/insert?sendtextmessage=", $('#hidjson').val(), "Create");
                }
                break;
            case "Transfer History":
                if(isFeatureAvailable("leadtransfer") == true){
                loadhtmlintotransfer("${pageContext.servletContext.contextPath}/html/leadtransfer.html", "Transfer Lead <i class=\"hvr-buzz-out fa fa-exchange\"></i>", "${pageContext.servletContext.contextPath}/api/leadtransfer/insert", $('#hidjson').val(),false);
                }
                break;

        }

    });

    function showError(msg) {
        swal({
            title: 'Error',
            text: msg,
            type: 'error',
            confirmButtonText: 'Ok'
        })
    }

    loadCitiesAndStates()
    function loadCitiesAndStates(){
        var cities = ["Delhi","Bangalore","Hyderabad","Ahmedabad","Chennai","Kolkata","Surat","Pune","Jaipur","Lucknow","Kanpur","Nagpur","Indore","Thane","Bhopal","Visakhapatnam","Pimpri & Chinchwad","Patna","Vadodara","Ghaziabad","Ludhiana","Agra","Nashik","Faridabad","Meerut","Rajkot","Kalyan & Dombivali","Vasai Virar","Varanasi","Srinagar","Aurangabad","Dhanbad","Amritsar","Navi Mumbai","Allahabad","Ranchi","Haora","Coimbatore","Jabalpur","Gwalior","Vijayawada","Jodhpur","Madurai","Raipur","Kota","Guwahati","Chandigarh","Solapur","Hubli and Dharwad","Bareilly","Moradabad","Mysore","Gurgaon","Aligarh","Jalandhar","Tiruchirappalli","Bhubaneswar","Salem","Mira and Bhayander","Thiruvananthapuram","Bhiwandi","Saharanpur","Gorakhpur","Guntur","Bikaner","Amravati","Noida","Jamshedpur","Bhilai Nagar","Warangal","Cuttack","Firozabad","Kochi","Bhavnagar","Dehradun","Durgapur","Asansol","Nanded Waghala","Kolapur","Ajmer","Gulbarga","Jamnagar","Ujjain","Loni","Siliguri","Jhansi","Ulhasnagar","Nellore","Jammu","Sangli Miraj Kupwad","Belgaum","Mangalore","Ambattur","Tirunelveli","Malegoan","Gaya","Jalgaon","Udaipur","Maheshtala"]
        var states = ["Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal","Andaman and Nicobar Islands","Chandigarh","Dadra and Nagar Haveli and Daman and Diu","Delhi","Jammu and Kashmir","Ladakh","Lakshadweep","Puducherry"];

        var cityhtml="";
        for(var i=0;i<cities.length;i++){
            cityhtml += '<option value="'+cities[i]+'">'
         }
        $("#citylist").html(cityhtml);

        var statehtml="";
        for(var i=0;i<states.length;i++){
            statehtml += '<option value="'+states[i]+'">'
        }
        $("#statelist").html(statehtml);


    }


    //in filter form, check if user is searching a string, when typing unhide checkboxes div
    function showCheckBoxes(){
        var value = $("#leadsearchfilterform").val();
        if(value.trim() == ""){
            $("#fieldCheckboxDiv").hide();
        }else{
            $("#fieldCheckboxDiv").show();
        }

    }

    //populate checboxes

    function populateCheckboxDiv(){
        var html="";
        $.ajax({
            type: "GET",
            contentType: "application/json",
            //url: "http://164.52.197.69:8080/MomBelief/field.json",
            url : "${pageContext.request.contextPath}/json/field.json?test=false",
            success: function (json) {
                for(var key in json){
                    html += "    <span class='cstmbtnfilter'><input hidname='"+key+"' type='checkbox'>  "+json[key]["fieldName"]+"</input></span> ";
                }

                $("#checkboxDiv").html(html);
            },
            error: function (err) {

                console.log(err)
            }

        });
    }

    $(document).on("change","#contentTypeleadcampaign",function(e){

    })

//SET HIDDEN STATES VALUES
    $(document).on("change","#stateId",function(){
        let state = $("#stateId option:selected").text();
        $("#state").val(state);
    })

//
function loadStates(countryid,callback){
    let arrStates = [];
    arrStates.push("<option value=''>---Select---</option>");// THIS IS WORKING ACTUALLY
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "${pageContext.servletContext.contextPath}/api/mbops/states/getallbycountryid?countryid="+countryid,
        success: function (json) {
            var html = "<option value=''>---Select---</option>";
            for (var i = 0; i < json.length; i++) {
                arrStates.push("<option title='" + json[i].name + "' value='" + json[i].id + "'>" + json[i].name +  "</option> ")
                html = html + "<option title='" + json[i].name + "' value='" + json[i].id + "'>" + json[i].name +  "</option> ";
            }
            $('#stateId').html(html);
            return callback();
        },
        error: function (err) {

        }

    });
}
// LEAD LEAD SOURCES

function loadLeadSource(callback){


    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "${pageContext.servletContext.contextPath}/api/leadsource/getactive",
        success: function (json) {
            var html = "<option value=''>---Select---</option>";
            for (var i = 0; i < json.length; i++) {
             html = html + "<option title='" + json[i].name + "' value='" + json[i].name + "'>" + json[i].name +  "</option> ";
            }
            $('#leadSourceSelect').html(html);
            return callback();
        },
        error: function (err) {

        }

    });
}

function loadLeadProgram(id,callback){
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "${pageContext.servletContext.contextPath}/api/product/getallavailable",
        success: function (json) {
            var html = "<option value=''>---Select---</option>";
            for (var i = 0; i < json.length; i++) {
                html = html + "<option title='" + json[i].description + "' value='" + json[i].name + "'>" + json[i].name + "</option> ";
            }
            console.log("id :: ",id)
            console.log("html :: ",html)
            $(id).html(html);
            return callback();
        },
        error: function (err) {
            console.log("err :: ",err)

        }

    });
}

// LOAD STATES FOR GIVEN COUNTRY
$(document).on("change","#country",function(e){
    let countryid = $(this).val();
    let country = $("#country option:selected").text();
    $("#country").val(countryid);
    loadStates(countryid,function(){

    });


})


$(document).on("submit", "#dynamicleadcampaignform", function (e) {
    console.log("button Working");
    e.preventDefault();

    var templatejson=JSON.parse($('#templateleadcampaign').val());
    console.log("templatejson is "+templatejson);
    var json={};
    console.log("templatejson is "+templatejson.id);
    json["templateId"]=templatejson.id;
    json["templateName"]=templatejson.templateName;
    let leadJson=JSON.parse($('#hidjson').val());

    console.log("templatejson :: ",templatejson)


    swal({
        title: "Are you sure?",
        text: "You Want To Create Quick Campaign To This Lead?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((willDelete)=>{

        if (willDelete) {
            $('#preloader').css("display","block")
            let promise = new Promise((resolve,reject)=>{
                let hashMap = {}
                hashMap["notifycentre"] = "false"
                triggerLeadNotification(leadJson.id, hashMap, json)
                resolve()
            }).then(()=>{
                $('#cancelcampaignquick').trigger("click")
                $('#preloader').fadeOut();
            }).then(()=>{
                swal({
                    title: "Success!",
                    text: "Successfully Created Campaign",
                    icon: "success",
                    button: "OK",
                });
            });



        }else{
            $('#preloader').css("display","none")
        }
        swal.close()

    });
    <%--e.preventDefault();--%>
    <%--var searchParams = new URLSearchParams(window.location.search);--%>
    <%--var leadstatus = searchParams.get('statusValue') || 0;--%>
    <%--var leadsource = searchParams.get('leadSource') || 0;--%>
    <%--var product = searchParams.get('product') || 0;--%>
    <%--var leadtype = searchParams.get('leadType') || 0;--%>
    <%--var userfilter = searchParams.get('userFilter') || 0;--%>
    <%--var datefilter = searchParams.get('datefilter') || false;--%>
    <%--var customdate = searchParams.get('datevalue') || 0;--%>
    <%--var searchstring = searchParams.get('searchvalue') || 0;--%>
    <%--var leadstage = searchParams.get('leadstage') || 0;--%>
    <%--var innersource = searchParams.get('innersource') || 0;--%>
    <%--var id = searchParams.get('id') || 0;--%>
    <%--var datefiltertype = searchParams.get('datefiltertype') || "createdate";--%>
    <%--var maxLimitSms=<%=UtilityClass.propertyService.findProperty("Campaign","maximumonecampaignlimitMSG")%>--%>
    <%--var maxLimitMail=<%=UtilityClass.propertyService.findProperty("Campaign","maximumonecampaignlimitMAIL")%>--%>
    <%--var urlinner = "?datefiltertype=" + datefiltertype + "&innersource=" + innersource + "&statusValue=" + leadstatus + "&leadSource=" + leadsource + "&product=" + product + "&leadType=" + leadtype + "&userFilter=" + userfilter + "&datefilter=" + datefilter + "&datevalue=" + customdate + "&searchvalue=" + searchstring + "&leadstage=" + leadstage + "&id=" + id;--%>
    <%--var urlinner2 = "?id="+id--%>
    <%--var url="${pageContext.request.contextPath}/api/campaign/quickcampaignbyidsingledirect"+urlinner2--%>
    <%--msg="Are you sure you want create Campaign ?";--%>
    <%--let exclusionList= new Map();--%>
    <%--var map={};--%>
    <%--var templatejson=JSON.parse($('#templateleadcampaign').val())--%>
    <%--console.log("templatejson is "+templatejson);--%>
    <%--exclusionList.set("621869beafa926253e527320",templatejson);--%>
    <%--// exclusionList.set(templatejson.id,templatejson);--%>
    <%--map["templateId"]=templatejson.id;--%>
    <%--map["name"]= "Quick Campaign";--%>
    <%--map["templateName"]=templatejson.templateName--%>
    <%--map["url"]=$('#urlembed').val();--%>
    <%--map["totalReciept"]= 1;--%>
    <%--map["excludedLeadIdList"]=exclusionList;--%>
    <%--if(map["totalReciept"]==0)--%>
    <%--{--%>
    <%--    swal({--%>
    <%--        title: 'Error',--%>
    <%--        text: "Cannot Create Campaign with 0 Reciept",--%>
    <%--        type: 'error',--%>
    <%--        confirmButtonText: 'Ok'--%>
    <%--    })--%>
    <%--    return;--%>
    <%--}--%>

    <%--if(templatejson.templateType=="Mail")--%>
    <%--{--%>
    <%--    if(map["totalReciept"]>maxLimitMail)--%>
    <%--    {--%>
    <%--        swal({--%>
    <%--            title: 'Error',--%>
    <%--            text: "Maximum  Campaign Limit Exceed. Maximum is "+maxLimitMail,--%>
    <%--            type: 'error',--%>
    <%--            confirmButtonText: 'Ok'--%>
    <%--        })--%>
    <%--        return;--%>
    <%--    }--%>
    <%--}else{--%>
    <%--    if(map["totalReciept"]>maxLimitSms)--%>
    <%--    {--%>
    <%--        swal({--%>
    <%--            title: 'Error',--%>
    <%--            text: "Maximum  Campaign Limit Exceed. Maximum is "+maxLimitSms,--%>
    <%--            type: 'error',--%>
    <%--            confirmButtonText: 'Ok'--%>
    <%--        })--%>
    <%--        return;--%>
    <%--    }--%>
    <%--}--%>



    <%--swal({--%>
    <%--    // THIS SHOWS PROMPT WITHOUT ANIMATION--%>

    <%--    // title: "Are you sure?",--%>
    <%--    // text: msg,--%>
    <%--    // type: "warning",--%>
    <%--    // showCancelButton: true,--%>
    <%--    // confirmButtonColor: '#DD6B55',--%>
    <%--    // confirmButtonText: 'Yes, I am sure!',--%>
    <%--    // cancelButtonText: "No, cancel it!",--%>
    <%--    // closeOnConfirm: false,--%>
    <%--    // closeOnCancel: false--%>

    <%--    title: "Are you sure?",--%>
    <%--    text: msg,--%>
    <%--    icon: "warning",--%>
    <%--    buttons: true,--%>
    <%--    dangerMode: false,--%>

    <%--}).then((willDelete)=>{--%>

    <%--    if (willDelete) {--%>
    <%--        $('#preloader').css("display","block")--%>

    <%--        $.ajax({--%>
    <%--            type: "POST",--%>
    <%--            data: JSON.stringify(map),--%>
    <%--            contentType: "application/json",--%>
    <%--            url: url,--%>
    <%--            success: function (data) {--%>
    <%--                if ($('#createbtn').html().trim() == "Create") {--%>


    <%--                    swal({--%>
    <%--                        title: "Success!",--%>
    <%--                        text: "Successfully Created Campaign",--%>
    <%--                        icon: "success",--%>
    <%--                        button: "OK",--%>
    <%--                    });--%>
    <%--                }--%>
    <%--                clearData();--%>
    <%--                table.ajax.reload();--%>
    <%--                $('#addnewdiv').css("display","none")--%>
    <%--                $('#tablediv').fadeIn();--%>
    <%--                $('#preloader').fadeOut();--%>
    <%--                $('#addnewuser').css("display","block")--%>
    <%--                $('#toTop').trigger("click")--%>
    <%--                $('#preloader').css("display","none")--%>
    <%--            },--%>
    <%--            error: function (err) {--%>

    <%--                $('#preloader').fadeOut();--%>

    <%--                swal({--%>
    <%--                    title: 'Error',--%>
    <%--                    text: err.responseJSON.message,--%>
    <%--                    type: 'error',--%>
    <%--                    confirmButtonText: 'Ok'--%>
    <%--                })--%>
    <%--            }--%>

    <%--        });--%>

    <%--    }else{--%>
    <%--        $('#preloader').css("display","none")--%>
    <%--    }--%>
    <%--    swal.close()--%>

    <%--});--%>
})

    <%--$(document).on("submit", "#dynamicleadcampaignform", function (e) {--%>
    <%--    e.preventDefault();--%>
    

    <%--    var templatejson=JSON.parse($('#templateleadcampaign').val());--%>
    <%--    var map={};--%>
    <%--    map["templateId"]=templatejson.id;--%>
    <%--    map["name"]="Quick Campaign";--%>
    <%--    map["templateName"]=templatejson.templateName--%>
    <%--    map["url"]=$('#embedurlquickcampaign').val();--%>
    <%--    map["totalReciept"]=1;--%>
    <%--    var leadJson=JSON.parse($('#hidjson').val());--%>

    <%--    console.log("templatejson :: ",templatejson)--%>


    <%--    swal({--%>
    <%--        title: "Are you sure?",--%>
    <%--        text: "You Want To Create Quick Campaign To This Lead?",--%>
    <%--        icon: "warning",--%>
    <%--        buttons: true,--%>
    <%--        dangerMode: true,--%>
    <%--    }).then((willDelete)=>{--%>

    <%--        if (willDelete) {--%>
    <%--            $('#preloader').css("display","block")--%>

    <%--            $.ajax({--%>
    <%--                type: "POST",--%>
    <%--                data: JSON.stringify(map),--%>
    <%--                contentType: "application/json",--%>
    <%--                url: "${pageContext.request.contextPath}/api/campaign/quickcampaignbyidsingle?id="+leadJson.id,--%>
    <%--                success: function (data) {--%>

    <%--                    swal({--%>
    <%--                            title: "Success!",--%>
    <%--                            text: "Successfully Created Campaign",--%>
    <%--                            icon: "success",--%>
    <%--                            button: "OK",--%>
    <%--                        });--%>
    <%--                    $('#cancelcampaignquick').trigger("click")--%>
    <%--                    $('#preloader').fadeOut();--%>

    <%--                },--%>
    <%--                error: function (err) {--%>

    <%--                    $('#preloader').fadeOut();--%>

    <%--                    swal({--%>
    <%--                        title: 'Error',--%>
    <%--                        text: err.responseJSON.message,--%>
    <%--                        type: 'error',--%>
    <%--                        confirmButtonText: 'Ok'--%>
    <%--                    })--%>
    <%--                }--%>

    <%--            });--%>

    <%--        }else{--%>
    <%--            $('#preloader').css("display","none")--%>
    <%--        }--%>
    <%--        swal.close()--%>

    <%--    });--%>
    <%--})--%>
    $(document).on("submit", "#dynamicleadcampaignformdirect", function (e) {
        e.preventDefault();

        var map={};
        map["templateType"]=$("input[name='templateType']:checked").val();

        map["variable"]="";

        map["templateName"]="Custom";
        map["templateSubject"]=$("#templateSubjectquickcampaign").val()

        map["contentType"]=$('#contentTypeleadcampaign').val()

        if(map["templateType"] == "Mail"){
            map["template"] = quill.root.innerHTML;
        }
        else{
            map["template"]=$('#contentquickcampaignquick').val()
        }

        map["params"]=[];
        var leadJson=JSON.parse($('#hidjson').val());
        swal({
            title: "Are you sure?",
            text: "You Want To Create Quick Campaign To This Lead?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }).then((willDelete)=>{

            if (willDelete) {
                $('#preloader').css("display","block")

                $.ajax({
                    type: "POST",
                    data: JSON.stringify(map),
                    contentType: "application/json",
                    url: "${pageContext.request.contextPath}/api/campaign/quickcampaignbyidsingledirect?id="+leadJson.id,
                    success: function (data) {

                        swal({
                            title: "Success!",
                            text: "Successfully Created Campaign",
                            icon: "success",
                            button: "OK",
                        });
                        $('#cancelcampaignquick').trigger("click")
                        $('#preloader').fadeOut();

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

            }else{
                $('#preloader').css("display","none")
            }
            swal.close()

        });
    })


//----------------------------------------------

$(document).on("click","#clinicalCallTime",function(){


    //IN ABSENSE OF #hidjson I.E WHEN CREATING NEW LEAD WE WILL GET EXCEPTION
    try {
    let leadJson = JSON.parse($('#hidjson').val());
    let appointmentDenyStage = "<%=propertyService.findProperty("Lead","appointmentDenyStage")%>";
    if(leadJson.leadStage == appointmentDenyStage){
        return false;
    }
    }catch (e) {

    }

   let appointmentSlot = $(this).val();
   let date = appointmentSlot.split(" ")[0]
    let slot = appointmentSlot.split(" ")[1]

    $("#appointmentDate").val(date);
   $("#appointmendSlot").val(slot);

    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd
    }
    if(mm<10){
        mm='0'+mm
    }

    today = yyyy+'-'+mm+'-'+dd;
    document.getElementById("appointmentDate").setAttribute("min", today);

    $("#clinicalCallTimeModal").modal("show");
})

$(document).on("submit","#clinicalCallTimeForm",function(e){
e.preventDefault();
 let date = $("#appointmentDate").val();
 let slot = $("#appointmendSlot").val();
 let appointmentSlot = date.trim() +" "+ slot.trim()

    $.ajax({
        type: 'GET',
        url: "${pageContext.servletContext.contextPath}/api/lead/gettimeslotcount?timeSlot="+appointmentSlot,
        success: function (count) {

            let limit = "<%=propertyService.findProperty("Lead","appointmentLimit")%>";
            if(count >= limit){
                swal({
                    title: 'Error',
                    text: "Sorry!! This slot is already full!!",
                    icon: 'error',
                    button: 'Ok'
                });
            }else{
                $("#clinicalCallTime").val(appointmentSlot);
                $('#formCloseBtn').trigger("click");
            }

        },
        error: function (err) {

            swal({
                title: 'Error',
                text: err,
                icon: 'error',
                button: 'Ok'
            });

        }
    });

})
//====================================================================== DO ASSESSMENT ======
function doAssessment(json) {
    json = JSON.parse(json);
    if(json.dob == null){
        swal({
            title: 'Error',
            text: 'Date Of Birth Not Set!',
            icon: 'error',
            button: 'Ok'
        });
        return false;
    }
   // $('#id').val(json.id);
    $('#lobidragdiv').css("display", "block");
    $('#tablediv').css("display", "none");

    //CHECK IF CHILD IS PRESCHOOL OR MAINSTREAM GOER
    let age = findAge(json.dob)/12;

    if(json.doesChildGoToSchool == "NO" || age < 5 && json.doesChildGoToSchool == "YES"){
        //OPEN PRESCHOOL FORM
        console.log("opening preschool form");
        loadPreschoolAssessmentForm(json);
        $("#leadid_preschool").val(json.id);
        $('#assessmentdiv_preschool').fadeIn();

    }
    //age > 5 and doesChildGoToSchool == "YES"
    else{
        //OPEN MAINSTREAM SCHOOL FORM
        console.log("opening mainstream form");
        loadMainstreamAssessmentForm(json);
        $("#leadid_mainstream").val(json.id);
        $('#assessmentdiv_mainstream').fadeIn();
    }


    $('#assessmentdiv').fadeIn();
   // $('#createbtn2').html("Update");
    $('#preloader').fadeOut("slow")



}

//=====================================HIDE MAINSTREAM ASSESSMENT FORM AND BRING DATA TABLE BACK =====================================
$('#cancelbtn_mainstream').click(function () {
   // clearData();
    table.ajax.reload(null,false);
    $('#assessmentdiv_mainstream').css("display", "none");
    $('#tablediv').fadeIn();
    $("#assessmentform_mainstream")[0].reset();


    $("#mainlistening").html("")
    $("#mainloralexpression").html("")
    $("#mainbasicreading").html("")
    $("#mainreadingcomprehension").html("")
    $("#mainmathcalculations").html("")
    $("#mainmathreasoning").html("")
    $("#mainwrittenexpression").html("")
    $("#mainbehavior").html("")
   //$('#createbtn_mainstream').html("Update")
});
//=====================================HIDE PRESCHOOL ASSESSMENT FORM AND BRING DATA TABLE BACK =====================================
$('#cancelbtn_preschool').click(function () {
   // clearData();
    table.ajax.reload(null,false);
    $('#assessmentdiv_preschool').css("display", "none");
    $('#tablediv').fadeIn();
    $("#assessmentform_preschool")[0].reset();

    $("#pregrossmotorskills").html("")
    $("#prefinemotorskills").html("")
    $("#preselfhelp").html("")
    $("#presocialemotional").html("")
    $("#precognitive").html("")
    $("#precommunication").html("")
    $("#prebehaviors").html("")
    //$('#createbtn_preschool').html("Update")
});
// ================================================== LOAD MAINSTREAM SCHOOL ASSESSMENT FORM LEADS ===
var mainstreamKeys = []
function loadMainstreamAssessmentForm(leadJson){
    //var mainstreamFormData={};
    mainstreamKeys = [];
    var promise = new Promise(function (resolve, reject) {

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url : "${pageContext.request.contextPath}/json/mainstreamschoolform.json",
            success: function (fieldJson) {
               //mainstreamFormData=fieldJson;
                let jsonObj = {"leadjson" : leadJson,"fieldjson" : fieldJson}
                resolve(jsonObj)
            },
            error: function (err) {
                console.log(err)
            }
        });


    }).then(function (jsonObj) {



            let leadJson = jsonObj["leadjson"];
            let fieldJson =  jsonObj["fieldjson"];
        for (let key in fieldJson) {
             mainstreamKeys.push(key);
            let keyObject=fieldJson[key];

            let html = '';
            html += '<div class="col-md-12 form-group">'
            html += '<label class="control-label">'+keyObject["fieldName"]+'</label>'
            html += '<div class="form-check-inline">'
            html += '<label class="form-check-label"><input type="radio" score="9" block="'+keyObject.block+'" class="form-check-input" name="'+key+'" value="Present" required>Present </label>'
            html += '<label class="form-check-label"><input type="radio" score="5" block="'+keyObject.block+'" class="form-check-input"  name="'+key+'" value="Emerging">Emerging </label>'
            html += '<label class="form-check-label"> <input type="radio" score="1" block="'+keyObject.block+'" class="form-check-input" name="'+key+'" value="Absent">Absent </label>'
            html += '</div>'
            html += '</div>'

            $("#"+keyObject["block"]).append(html);
            $("input[name="+key+"][value=" + leadJson[key] + "]").prop('checked', true);

        }


    });
}

// ================================================== LOAD PRESCHOOL ASSESSMENT FORM LEADS ===
var preschoolKeys = [];
function loadPreschoolAssessmentForm(leadJson){
    //var mainstreamFormData={};
    preschoolKeys = [];
    var promise = new Promise(function (resolve, reject) {

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url : "${pageContext.request.contextPath}/json/preschoolform.json",
            success: function (fieldJson) {
                //mainstreamFormData=fieldJson;
                let jsonObj = {"leadjson" : leadJson,"fieldjson" : fieldJson}
                resolve(jsonObj)
            },
            error: function (err) {
                console.log(err)
            }
        });


    }).then(function (jsonObj) {

        let leadJson = jsonObj["leadjson"];
        let fieldJson =  jsonObj["fieldjson"];
        for (let key in fieldJson) {
            preschoolKeys.push(key);

            let keyObject=fieldJson[key];

            let html = '';
            html += '<div class="col-md-12 form-group">'
            html += '<label class="control-label">'+keyObject["fieldName"]+'</label>'
            html += '<div class="form-check-inline">'
            html += '<label class="form-check-label"><input type="radio" score="1" block="'+keyObject.block+'" class="form-check-input" name="'+key+'" value="Present" required>Present </label>'
            html += '<label class="form-check-label"><input type="radio" score="5" block="'+keyObject.block+'" class="form-check-input"  name="'+key+'" value="Emerging" >Emerging </label>'
            html += '<label class="form-check-label"> <input type="radio" score="9" block="'+keyObject.block+'" class="form-check-input" name="'+key+'" value="Absent">Absent </label>'
            html += '</div>'
            html += '</div>'

            $("#"+keyObject["block"]).append(html);
            $("input[name="+key+"][value=" + leadJson[key] + "]").prop('checked', true);

        }


    });
}

//==================================== MAINSTREAM FORM SUBMISSION ===
$(document).on("submit","#assessmentform_mainstream",function (e) {
    e.preventDefault()
    console.log("submitting mainstream form")
    let map = {};
    map["id"] = $("#leadid_mainstream").val();
    mainstreamKeys.forEach(key =>(
        map[key] = $('input[name="'+key+'"]:checked').val()

    ))
    //============================ CALCUALTING SUM OF SELECTED OPTIONS FOR LEAD SCORE
    let score = 0;
    mainstreamKeys.forEach(key =>(
        score +=  parseInt($('input[name="'+key+'"]:checked').attr("score"))
        ))
    map["leadScore"] = score;
//================================ CALCULATING SUM OF EACH GROUP OF QUESTIONS
    let blockMap = {};
    for(let i=0;i<mainstreamKeys.length;i++){
        let block = $('input[name="'+mainstreamKeys[i]+'"]:checked').attr("block")

        if(map[block] === undefined){
            console.log("creating new key for block >> ",block)
            map[block] = 0;
        }
    }
//=========== CALCULATING THE SUM OF SELECTED OPTIONS FOR EACH GROUP
for (let key in map) {
        console.log("key > ",key)
        $('input[block="'+key+'"]:checked').each(function(){
            console.log($(this).attr("score"))
            map[key] = parseInt(map[key])+parseInt($(this).attr("score"));
        })
    }
console.table(map)
//============================================================================================
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "${pageContext.servletContext.contextPath}/api/lead/updatemainstreamform",
        data: JSON.stringify(map),
        success: function (data) {

            swal({
                title: 'Success',
                text: "Assessment Done",
                icon: 'success',
                button: 'Ok'
            });

            //transferLeadToDepartment("PROFILINGMASTER",function (){
                changeStageandStatusOfLead(data.id,"Profiling Done","Assessment Done",function (data) {

                })
           // });

            table.ajax.reload(null,false);
            $("#cancelbtn_mainstream").trigger("click");
            $("#assessmentform_mainstream")[0].reset();
            $('#assessmentdiv_mainstream').css("display", "none");
            $('#tablediv').fadeIn();

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


})

//==================================== PRESCHOOL FORM SUBMISSION ====

$(document).on("submit","#assessmentform_preschool",function (e) {
    e.preventDefault()
    console.log("submitting preschool form")
    let map = {};
    map["id"] = $("#leadid_preschool").val();
    preschoolKeys.forEach(key =>(
        map[key] = $('input[name="'+key+'"]:checked').val()
    ))

//================================ CALCULATING SUM OF ALL SELECTED OPTIONS
    let score = 0;
    preschoolKeys.forEach(key =>(
        score += parseInt($('input[name="'+key+'"]:checked').attr("score"))
    ))
    map["leadScore"] = score;

    //================================ CALCULATING SUM OF EACH GROUP OF QUESTIONS

    for(let i=0;i<preschoolKeys.length;i++){
        let block = $('input[name="'+preschoolKeys[i]+'"]:checked').attr("block")
        if(map[block] === undefined){
            map[block] = 0;
        }
    }
//=========== CALCULATING THE SUM OF SELECTED OPTIONS FOR EACH GROUP
    for (let key in map) {
        $('input[block="'+key+'"]:checked').each(function(){
            map[key] = parseInt(map[key])+parseInt($(this).attr("score"));
        })
    }
    console.table(map)
//============================================================================================

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "${pageContext.servletContext.contextPath}/api/lead/updatepreschoolform",
        data: JSON.stringify(map),
        success: function (data) {

            swal({
                title: 'Success',
                text: "Assessment Done",
                icon: 'success',
                button: 'Ok'
            });

            //transferLeadToDepartment("PROFILINGMASTER",function (){
                changeStageandStatusOfLead(data.id,"Profiling Done","Assessment Done",function (data) {

                })
            //});


            table.ajax.reload(null,false);
            $("#cancelbtn_preschool").trigger("click");
            $("#assessmentform_preschool")[0].reset();
            $('#assessmentdiv_preschool').css("display", "none");
            $('#tablediv').fadeIn();

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



})

//====================== CENTRE FUNCTIONS =====

$(document).on("click","#centrehelp",function (){

    let pincode = $("#pincode").val();
    $("#temppincode").val(pincode);
    $("#searchcentrebtn").trigger("click");

})



$(document).on("click","#searchcentrebtn",function (){

    let pincode = $("#temppincode").val();
    console.log("Fetching centers for pincode :", pincode)
      if(pincode){
          $.ajax({
              type: "GET",
              contentType: "application/json",
              url: "${pageContext.servletContext.contextPath}/api/external/getcentresbypincode?pincode="+pincode,
              success: function (data) {

                  let len = data.length;
                  let html = "";

                  for(let i=0;i<len;i++){

                      let timingVal = "";
                      if(data[i].centreTimingSun){
                          timingVal +="Sun : "+data[i].centreTimingSun+"\n"
                      }
                      if(data[i].centreTimingMon){
                          timingVal +="Mon : "+data[i].centreTimingMon+"\n"
                      }
                      if(data[i].centreTimingTue){
                          timingVal +="Tue : "+data[i].centreTimingTue+"\n"
                      }
                      if(data[i].centreTimingWed){
                          timingVal +="Wed : "+data[i].centreTimingWed+"\n"
                      }
                      if(data[i].centreTimingThu){
                          timingVal +="Thu : "+data[i].centreTimingThu+"\n"
                      }
                      if(data[i].centreTimingFri){
                          timingVal +="Fri : "+data[i].centreTimingFri+"\n"
                      }
                      if(data[i].centreTimingSat){
                          timingVal +="Sat : "+data[i].centreTimingSat+"\n"
                      }

                      html += "<tr>"
                      html += "<td hidatr='"+JSON.stringify(data[i])+"'><input type='checkbox' class='centrecheck'></td>"
                      html += "<td style='white-space:pre' >"+timingVal+"</td>"
                      html += "<td>"+data[i].name+"</td>"
                      html += "<td>"+data[i].center_city+"</td>"
                      html += "<td>"+data[i].center_state+"</td>"
                      html += "<td>"+data[i].center_mobile+"</td>"
                      html += "</tr>"


                  }
                  $("#timingtbody").html(html)



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

// STORE NUMBER OF LEADS USER WANTS TO SEE
$('select[name="table_length"]').on('change', function(){
    localStorage.setItem("table_length",$(this).val())
});
$('select[name="table_length"]').val(localStorage.getItem("table_length")||25);

$(document).on("change",".centrecheck",function(){

        $('.centrecheck').prop('checked', false);
        $(".centrecheck").removeClass("seletedcentre");

        $(this).prop("checked",true);
        $(this).addClass("seletedcentre");
    });


//var tempCentreAndLeadsJson = [];

$(document).on("click","#selectcentrebtn",function(){
    let centrejson = JSON.parse($(".seletedcentre").closest("tr").find("td").eq(0).attr("hidatr"))
    swal({
        title: "Are you sure?",
        text: "Book Appointment at "+centrejson.name,
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((appoint) => {
        if (appoint) {
            $("#tempCentreJson").val(JSON.stringify(centrejson));

            swal({
                title: "Success!",
                icon: "success",
                button: "Ok!",
            });
        }
    });



})


//notify centere head and BD that a lead has made an appointment to their center
function notifyCentre(){

    let centrejson = JSON.parse($("#tempCentreJson").val());
    //let leadJson = tempCentreAndLeadsJson[1];
    let leadJson = JSON.parse($("#hidjsonleadeatils").val());

    if(!centrejson || !leadJson){
        console.log("leadJson or center json is undefined")
        return false;
    }

    let nameArr = [];
    nameArr.push(centrejson.center_contact_person)
    nameArr.push(centrejson.center_bd)

    let numberArr = [];
    numberArr.push(centrejson.center_mobile)
    numberArr.push(centrejson.center_bd_mobile)

    let templateIDs = "<%=UtilityClass.propertyService.findProperty("Lead", "centreNotificationTemplateID")%>";
    let json = {"templateId" : templateIDs}


    for(let i=0;i<nameArr.length;i++) {
        if(numberArr[i].length > 1){
            let hashMap = {};
            hashMap["notifycentre"] = "true";// so that impl method uses phone numbers from "centrephonenumber" key instad of lead's phone numnber
            hashMap["centreemail"] = centrejson.center_email;
            hashMap["@centrename"] = centrejson.center;
            //hashMap["@centrehead"] = centrejson.center_contact_person;
            //hashMap["centrephonenumber"] = centrejson.center_mobile;
            hashMap["@centrehead"] = nameArr[i];
            hashMap["centrephonenumber"] = numberArr[i];
            hashMap["@centrepin"] = centrejson.center_pin;
           triggerLeadNotification(leadJson.id, hashMap, json)
        }
    }


}
//==========================================================================

//====================================================================== Do REVEIEWS  =======
function doReview(json) {

    json = JSON.parse(json);
    let ReviewStage = "<%=propertyService.findProperty("Lead","ReviewStage")%>";

    if(json.clientType == ReviewStage){
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/reviewservice/getbyleadid?leadid="+json.id,
            success: function (data) {
               if(data){
                   console.log("review found : ",data)
                   $("#hidreviewjson").val(JSON.stringify(data));
                   $("#howsChildDoing").val(data.howsChildDoing);
                   $("#facingAnyChallenges").val(data.facingAnyChallenges);
                   //$("#interestedInCounsellor").val(data.howsChildDoing);
                   $("input[name=interestedInCounsellor][value=" + data.interestedInCounsellor + "]").prop('checked', true);

                   $("#recommendationScore").val(data.recommendationScore);
                   $("#impactScore").val(data.impactScore);
                   $("#suggestedService1").val(data.suggestedService1);
                   $("#suggestedService2").val(data.suggestedService2);
                   $("#suggestedService3").val(data.suggestedService3);
                   $("#therapistScore").val(data.therapistScore);

               }else{
                   $("#reviewform").trigger("reset");
                   $("#hidreviewjson").val(JSON.stringify({}));
                   $("#hidreviewleadid").val(json.id);
                   console.log("review not found")
               }
                $("#reviewmodal").modal("show")
            },
            error: function (err) {
                swal({
                    title: 'Error While Loading Review Data of This Lead!',
                    text: err.responseJSON.message,
                    icon: 'error',
                    button: 'Ok'
                });
            }

        })

    }else{
        swal({
            title: 'Error',
            text: 'Lead Not Eligible For Review!',
            icon: 'warning',
            button: 'Ok'
        });
    }
}

$(document).on("submit","#reviewform",function(e){
    e.preventDefault();
    console.log("reviewing")
    let reviewJson = JSON.parse($("#hidreviewjson").val());
    let leadid = $("#hidreviewleadid").val();
    let url;

    console.log("reviewJson ",reviewJson)
    //INSERT
    if(reviewJson["leadId"] == undefined){
        console.log("inserting")
        url = "${pageContext.servletContext.contextPath}/api/reviewservice/insert";
        reviewJson["howsChildDoing"] = $("#howsChildDoing").val();
        reviewJson["facingAnyChallenges"] = $("#facingAnyChallenges").val();
        reviewJson["interestedInCounsellor"] = $('input[name="interestedInCounsellor"]:checked').val();
        reviewJson["recommendationScore"] = $("#recommendationScore").val();
        reviewJson["impactScore"] = $("#impactScore").val();
        reviewJson["suggestedService1"] = $("#suggestedService1").val();
        reviewJson["suggestedService2"] = $("#suggestedService2").val();
        reviewJson["suggestedService3"] = $("#suggestedService3").val();
        reviewJson["therapistScore"] = $("#therapistScore").val();
        reviewJson["leadId"] = leadid;

    }
    //UPDATE
    else{
        console.log("updating")
        url = "${pageContext.servletContext.contextPath}/api/reviewservice/update";
        reviewJson["howsChildDoing"] = $("#howsChildDoing").val();
        reviewJson["facingAnyChallenges"] = $("#facingAnyChallenges").val();
        reviewJson["interestedInCounsellor"] = $('input[name="interestedInCounsellor"]:checked').val();
        reviewJson["recommendationScore"] = $("#recommendationScore").val();
        reviewJson["impactScore"] = $("#impactScore").val();
        reviewJson["suggestedService1"] = $("#suggestedService1").val();
        reviewJson["suggestedService2"] = $("#suggestedService2").val();
        reviewJson["suggestedService3"] = $("#suggestedService3").val();
        reviewJson["therapistScore"] = $("#therapistScore").val();
    }

    console.log("reviewJson ",reviewJson)

      $.ajax({
        type: "POST",
          data: JSON.stringify(reviewJson),
        contentType: "application/json",
        url: url,
        success: function (data) {
            swal({
                title: 'Success',
                text: "Review Saved",
                icon: 'success',
                button: 'Ok'
            });
            $("#reviewmodal").modal("hide")
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
})

function hideReviewModal(){
    $("#reviewmodal").modal("hide");
}


</script>
</body>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
</html>

