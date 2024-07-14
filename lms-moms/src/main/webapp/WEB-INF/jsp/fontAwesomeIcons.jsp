<%@ page import="com.example.maxcrm.MaxCrm.Utility.UtilityClass" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="common/title.jsp"></jsp:include>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.min.css"/>

    <!-- =============================FOR CUSTOM ALERTS ============================================ -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.css"/>
    <script src="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.js"></script>
    <script src="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.min.js" type="text/javascript"></script>
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



</head>


<body>
<div class="container">
    <div style="margin: 5%; opacity: 0.95">

    </div>

    <div class="col-sm-12 lobipanel-parent-sortable ui-sortable" data-lobipanel-child-inner-id="mREvOYcPeh">
        <div class="panel panel-bd lobidrag lobipanel lobipanel-sortable" data-inner-id="mREvOYcPeh" data-index="0">
            <div class="panel-heading ui-sortable-handle">
                <div class="panel-title" style="max-width: calc(100% - 180px);">
                    <h4>Icons</h4>
                </div>
                <div class="dropdown"><ul class="dropdown-menu dropdown-menu-right"><li><a data-func="editTitle" data-tooltip="Edit title" data-toggle="tooltip" data-title="Edit title" data-placement="bottom" data-original-title="" title=""><i class="panel-control-icon ti-pencil"></i><span class="control-title">Edit title</span></a></li><li><a data-func="unpin" data-tooltip="Unpin" data-toggle="tooltip" data-title="Unpin" data-placement="bottom" data-original-title="" title=""><i class="panel-control-icon ti-move"></i><span class="control-title">Unpin</span></a></li><li><a data-func="reload" data-tooltip="Reload" data-toggle="tooltip" data-title="Reload" data-placement="bottom" data-original-title="" title=""><i class="panel-control-icon ti-reload"></i><span class="control-title">Reload</span></a></li><li><a data-func="minimize" data-tooltip="Minimize" data-toggle="tooltip" data-title="Minimize" data-placement="bottom" data-original-title="" title=""><i class="panel-control-icon ti-minus"></i><span class="control-title">Minimize</span></a></li><li><a data-func="expand" data-tooltip="Fullscreen" data-toggle="tooltip" data-title="Fullscreen" data-placement="bottom" data-original-title="" title=""><i class="panel-control-icon ti-fullscreen"></i><span class="control-title">Fullscreen</span></a></li><li><a data-func="close" data-tooltip="Close" data-toggle="tooltip" data-title="Close" data-placement="bottom" data-original-title="" title=""><i class="panel-control-icon ti-close"></i><span class="control-title">Close</span></a></li></ul><div class="dropdown-toggle" data-toggle="dropdown"><span class="panel-control-icon glyphicon glyphicon-cog"></span></div></div></div>

            <div class="panel-body">
                <div class="row">

                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-address-book"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-address-book</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-address-book-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-address-book-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-address-card"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-address-card</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-address-card-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-address-card-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bandcamp"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bandcamp</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bath"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bath</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bathtub"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bathtub <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-drivers-license"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-drivers-license  <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-drivers-license-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-drivers-license-o <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-eercast"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-eercast</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-envelope-open"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-envelope-open</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-envelope-open-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-envelope-open-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-etsy"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-etsy</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-free-code-camp"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-free-code-camp</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-grav"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-grav</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-handshake-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-handshake-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-id-badge"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-id-badge</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-id-card"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-id-card</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-id-card-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-id-card-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-imdb"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-imdb</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-linode"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-linode</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-meetup"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-meetup</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-microchip"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-microchip</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-podcast"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-podcast</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-quora"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-quora</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-ravelry"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-ravelry</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-s15"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-s15 <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-shower"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-shower</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-snowflake-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-snowflake-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-superpowers"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-superpowers</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-telegram"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-telegram</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thermometer"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thermometer <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thermometer-0"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thermometer-0 <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thermometer-1"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thermometer-1 <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thermometer-2"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thermometer-2  <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thermometer-3"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thermometer-3  <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thermometer-4"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thermometer-4 <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thermometer-empty"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thermometer-empty</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thermometer-full"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thermometer-full</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thermometer-half"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thermometer-half</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thermometer-quarter"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thermometer-quarter</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thermometer-three-quarters"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thermometer-three-quarters</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-times-rectangle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-times-rectangle <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-times-rectangle-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-times-rectangle-o  <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-user-circle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-user-circle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-user-circle-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-user-circle-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-user-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-user-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-vcard"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-vcard <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-vcard-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-vcard-o  <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-window-close"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-window-close</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-window-close-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-window-close-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-window-maximize"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-window-maximize</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-window-minimize"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-window-minimize</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-window-restore"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-window-restore</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-wpexplorer"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-wpexplorer</span>
                        </div>
                    </div>
                </div>
                <div class="row fontawesome-icon-list">
                    <div class="col-sm-12">
                        <h3>Web Application Icons</h3>
                        <hr>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-address-book"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-address-book</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-address-book-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-address-book-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-address-card"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-address-card</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-address-card-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-address-card-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-adjust"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-adjust</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-american-sign-language-interpreting"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-american-sign ..</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-anchor"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-anchor</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-archive"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-archive</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-area-chart"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-area-chart</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrows"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrows</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrows-h"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrows-h</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrows-v"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrows-v</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-asl-interpreting"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-asl-interpreting <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-assistive-listening-systems"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-assistive ..</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-asterisk"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-asterisk</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-at"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-at</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-audio-description"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-audio-description</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-automobile"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-automobile <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-balance-scale"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-balance-scale</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-ban"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-ban</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bank"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bank <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bar-chart"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bar-chart</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bar-chart-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bar-chart-o <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-barcode"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-barcode</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bars"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bars</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bath"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bath</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bathtub"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bathtub <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-battery"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-battery <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-battery-0"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-battery-0 <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-battery-1"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-battery-1 </span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-battery-2"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-battery-2   <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-battery-3"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-battery-3   <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-battery-4"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-battery-4   <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-battery-empty"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-battery-empty</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-battery-full"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-battery-full</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-battery-half"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-battery-half</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-battery-quarter"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-battery-quarter</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-battery-three-quarters"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-battery-three-quarters</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bed"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bed</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-beer"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-beer</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bell"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bell</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bell-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bell-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bell-slash"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bell-slash</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bell-slash-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bell-slash-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bicycle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bicycle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-binoculars"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-binoculars</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-birthday-cake"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-birthday-cake</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-blind"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-blind</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bluetooth"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bluetooth</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bluetooth-b"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bluetooth-b</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bolt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bolt</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bomb"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bomb</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-book"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-book</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bookmark"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bookmark</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bookmark-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bookmark-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-braille"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-braille</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-briefcase"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-briefcase</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bug"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bug</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-building"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-building</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-building-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-building-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bullhorn"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bullhorn</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bullseye"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bullseye</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bus"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bus</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cab"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cab <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-calculator"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-calculator</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-calendar"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-calendar</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-calendar-check-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-calendar-check-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-calendar-minus-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-calendar-minus-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-calendar-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-calendar-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-calendar-plus-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-calendar-plus-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-calendar-times-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-calendar-times-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-camera"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-camera</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-camera-retro"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-camera-retro</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-car"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-car</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-caret-square-o-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-caret-square-o-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-caret-square-o-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-caret-square-o-left</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-caret-square-o-right"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-caret-square-o-right</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-caret-square-o-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-caret-square-o-up</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cart-arrow-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cart-arrow-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cart-plus"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cart-plus</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cc"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cc</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-certificate"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-certificate</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-check"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-check</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-check-circle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-check-circle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-check-circle-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-check-circle-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-check-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-check-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-check-square-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-check-square-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-child"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-child</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-circle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-circle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-circle-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-circle-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-circle-o-notch"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-circle-o-notch</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-circle-thin"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-circle-thin</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-clock-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-clock-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-clone"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-clone</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-close"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-close <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cloud"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cloud</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cloud-download"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cloud-download</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cloud-upload"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cloud-upload</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-code"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-code</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-code-fork"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-code-fork</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-coffee"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-coffee</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cog"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cog</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cogs"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cogs</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-comment"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-comment</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-comment-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-comment-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-commenting"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-commenting</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-commenting-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-commenting-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-comments"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-comments</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-comments-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-comments-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-compass"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-compass</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-copyright"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-copyright</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-creative-commons"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-creative-commons</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-credit-card"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-credit-card</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-credit-card-alt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-credit-card-alt</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-crop"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-crop</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-crosshairs"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-crosshairs</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cube"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cube</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cubes"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cubes</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cutlery"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cutlery</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-dashboard"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-dashboard <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-database"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-database</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-deaf"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-deaf</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-deafness"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-deafness <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-desktop"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-desktop</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-diamond"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-diamond</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-dot-circle-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-dot-circle-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-download"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-download</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-drivers-license"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-drivers-license <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-drivers-license-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-drivers-license-o <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-edit"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-edit <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-ellipsis-h"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-ellipsis-h</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-ellipsis-v"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-ellipsis-v</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-envelope"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-envelope</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-envelope-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-envelope-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-envelope-open"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-envelope-open</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-envelope-open-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-envelope-open-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-envelope-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-envelope-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-eraser"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-eraser</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-exchange"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-exchange</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-exclamation"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-exclamation</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-exclamation-circle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-exclamation-circle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-exclamation-triangle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-exclamation-triangle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-external-link"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-external-link</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-external-link-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-external-link-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-eye"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-eye</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-eye-slash"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-eye-slash</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-eyedropper"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-eyedropper</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-fax"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-fax</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-feed"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-feed <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-female"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-female</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-fighter-jet"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-fighter-jet</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-archive-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-archive-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-audio-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-audio-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-code-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-code-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-excel-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-excel-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-image-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-image-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-movie-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-fa fa-file-movie-o <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-pdf-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-pdf-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-photo-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-photo-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-picture-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-picture-o <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-powerpoint-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-powerpoint-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-sound-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-sound-o <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-video-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-video-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-word-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-word-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-zip-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-zip-o <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-film"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-film</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-filter"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-filter</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-fire"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-fire</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-fire-extinguisher"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-fire-extinguisher</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-flag"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-flag</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-flag-checkered"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-flag-checkered</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-flag-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-flag-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-flash"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-flash <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-flask"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-flask</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-folder"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-folder</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-folder-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-folder-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-folder-open"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-folder-open</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-folder-open-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-folder-open-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-frown-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-frown-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-futbol-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-futbol-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-gamepad"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-gamepad</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-gavel"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-gavel</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-gear"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-gear <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-gears"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-gears <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-gift"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-gift</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-glass"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-glass</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-globe"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-globe</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-graduation-cap"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-graduation-cap</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-group"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-group <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-grab-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-grab-o <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-lizard-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-lizard-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-paper-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-paper-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-peace-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-peace-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-pointer-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-pointer-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-rock-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-rock-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-scissors-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-scissors-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-spock-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-spock-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-stop-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-stop-o <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-handshake-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-handshake-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hard-of-hearing"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hard-of-hearing <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hashtag"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hashtag</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hdd-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hdd-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-headphones"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-headphones</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-heart"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-heart</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-heart-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-heart-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-heartbeat"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-heartbeat</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-history"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-history</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-home"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-home</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hotel"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hotel <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hourglass"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hourglass</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hourglass-1"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hourglass-1 <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hourglass-2"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hourglass-2 <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hourglass-3"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hourglass-3 <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hourglass-end"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hourglass-end</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hourglass-half"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hourglass-half</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hourglass-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hourglass-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hourglass-start"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hourglass-start</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-i-cursor"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-i-cursor</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-id-badge"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-id-badge</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-id-card"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-id-card</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-id-card-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-id-card-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-image"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-image</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-inbox"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-inbox</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-industry"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-industry</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-info"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-info</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-info-circle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-info-circle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-institution"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-institution <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-key"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-key</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-keyboard-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-keyboard-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-language"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-language</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-laptop"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-laptop</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-leaf"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-leaf</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-legal"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-legal <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-lemon-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-lemon-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-level-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-level-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-level-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-level-up</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-life-bouy"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-life-bouy <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-life-buoy"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-life-buoy <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-life-ring"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-life-ring</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-life-saver"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-life-saver <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-lightbulb-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-lightbulb-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-line-chart"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-line-chart</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-location-arrow"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-location-arrow</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-lock"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-lock</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-low-vision"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-low-vision</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-magic"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-magic</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-magnet"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-magnet</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-mail-forward"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-mail-forward <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-mail-reply"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-mail-reply <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-mail-reply-all"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-mail-reply-all <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-male"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-male</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-map"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-map</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-map-marker"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-map-marker</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-map-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-map-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-map-pin"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-map-pin</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-map-signs"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-map-signs</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-meh-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-meh-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-microchip"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-microchip</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-microphone"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-microphone</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-microphone-slash"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-microphone-slash</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-minus"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-minus</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-minus-circle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-minus-circle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-minus-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-minus-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-minus-square-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-minus-square-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-mobile"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-mobile</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-mobile-phone"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-mobile-phone</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-money"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-money</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-moon-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-moon-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-mortar-board"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-mortar-board <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-motorcycle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-motorcycle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-mouse-pointer"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-mouse-pointer</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-music"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-music</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-navicon"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-navicon <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-newspaper-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-newspaper-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-object-group"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-object-group</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-object-ungroup"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-object-ungroup</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-paint-brush"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-paint-brush</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-paper-plane"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-paper-plane</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-paper-plane-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-paper-plane-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-paw"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-paw</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-pencil"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-pencil</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-pencil-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-pencil-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-pencil-square-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-pencil-square-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-percent"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-percent</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-phone"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-phone</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-phone-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-phone-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-photo"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-photo <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-picture-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-picture-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-pie-chart"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-pie-chart</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-plane"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-plane</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-plug"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-plug</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-plus"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-plus</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-plus-circle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-plus-circle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-plus-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-plus-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-plus-square-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-plus-square-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-podcast"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-podcast</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-power-off"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-power-off</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-print"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-print</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-puzzle-piece"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-puzzle-piece</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-qrcode"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-qrcode</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-question"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-question</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-question-circle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-question-circle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-question-circle-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-question-circle-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-quote-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-quote-left</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-quote-right"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-quote-right</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-random"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-random</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-recycle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-recycle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-refresh"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-refresh</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-registered"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-registered</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-remove"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-remove <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-reorder"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-reorder <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-reply"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-reply</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-reply-all"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-reply-all</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-retweet"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-retweet</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-road"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-road</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-rocket"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-rocket</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-rss"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-rss</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-rss-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-rss-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-s15"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-s15 <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-search"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-search</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-search-minus"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-search-minus</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-search-plus"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-search-plus</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-send"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-send <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-send-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-send-o <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-server"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-server</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-share"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-share</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-share-alt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-share-alt</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-share-alt-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-share-alt-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-share-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-share-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-share-square-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-share-square-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-shield"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-shield</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-ship"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-ship</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-shopping-bag"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-shopping-bag</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-shopping-basket"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-shopping-basket</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-shopping-cart"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-shopping-cart</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-shower"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-shower</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sign-in"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sign-in</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sign-language"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sign-language</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sign-out"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sign-out</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-signal"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-signal</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-signing"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-signing <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sitemap"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sitemap</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sliders"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sliders</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-smile-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-smile-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-snowflake-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-snowflake-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-soccer-ball-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-soccer-ball-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sort"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sort</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sort-alpha-asc"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sort-alpha-asc</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sort-alpha-desc"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sort-alpha-desc</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sort-amount-asc"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sort-amount-asc</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sort-amount-desc"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sort-amount-desc</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sort-asc"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sort-asc</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sort-desc"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sort-desc</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sort-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sort-down <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sort-numeric-asc"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sort-numeric-asc</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sort-numeric-desc"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sort-numeric-desc</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sort-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sort-up <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-space-shuttle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-space-shuttle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-spinner"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-spinner</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-spoon"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-spoon</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-square-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-square-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-star"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-star</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-star-half"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-star-half</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-star-half-empty"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-star-half-empty <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-star-half-full"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-star-half-full <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-star-half-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-star-half-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-star-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-star-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sticky-note"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sticky-note</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sticky-note-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sticky-note-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-street-view"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-street-view</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-suitcase"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-suitcase</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sun-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sun-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-support"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-support</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-tablet"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-tablet</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-tachometer"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-tachometer</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-tag"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-tag</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-tags"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-tags</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-tasks"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-tasks</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-taxi"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-taxi</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-television"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-television</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-terminal"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-terminal</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thumb-tack"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thumb-tack</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thumbs-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thumbs-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thumbs-o-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thumbs-o-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thumbs-o-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thumbs-o-up</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thumbs-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thumbs-up</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-ticket"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-ticket</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-times"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-times</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-times-circle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-times-circle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-times-circle-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-times-circle-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-times-rectangle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-times-rectangle <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out times-rectangle-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-fa fa-times-rectangle-o <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-tint"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-tint</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-toggle-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-toggle-down <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-toggle-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-toggle-left <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-toggle-off"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-toggle-off</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-toggle-on"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-toggle-on</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-toggle-right"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-toggle-right <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-toggle-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-toggle-up <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-trademark"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-trademark</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-trash"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-trash</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-trash-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-trash-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-tree"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-tree</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-trophy"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-trophy</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-truck"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-truck</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-tty"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-tty</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-tv"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-tv <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-umbrella"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-umbrella</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-universal-access"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-universal-access</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-university"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-university</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-unlock"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-unlock</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-unlock-alt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-unlock-alt</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-unsorted"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-unsorted <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-upload"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-upload</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-user"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-user</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-user-circle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-user-circle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-user-circle-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-user-circle-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-user-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-user-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-user-plus"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-user-plus</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-user-secret"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-user-secret</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-user-times"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-user-times</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-users"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-users</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-vcard"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-vcard <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-vcard-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-vcard-o <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-video-camera"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-video-camera</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-volume-control-phone"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-volume-control-phone</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-volume-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-volume-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-volume-off"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-volume-off</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-volume-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-volume-up</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-warning"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-warning <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-wheelchair"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-wheelchair</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-wheelchair-alt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-wheelchair-alt</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-wifi"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-wifi</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-window-close"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-window-close</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-window-close-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-window-close-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-window-maximize"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-window-maximize</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-window-minimize"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-window-minimize</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-window-restore"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-window-restore</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-wrench"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-wrench</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <h3>Accessibility Icons</h3>
                        <hr>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-american-sign-language-interpreting"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-american-sign-language-interpreting
                                    </span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-asl-interpreting"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-asl-interpreting
                                    <span class="text-muted">(alias)</span>
                                    </span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-assistive-listening-systems"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-assistive-listening-systems</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-audio-description"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-audio-description</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-blind"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-blind</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-braille"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-braille</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cc"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cc</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-deaf"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-deaf</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-deafness"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-deafness <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hard-of-hearing"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hard-of-hearing
                                    <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-low-vision"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-low-vision</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-question-circle-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-question-circle-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sign-language"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sign-language</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-signing"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-signing<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-tty"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-tty</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-universal-access"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-universal-access</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-volume-control-phone"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-volume-control-phone</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-wheelchair"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-wheelchair</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-wheelchair-alt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa- wheelchair-alt</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <h3>Hand Icons</h3>
                        <hr>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-grab-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa- hand-grab-o<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-lizard-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-lizard-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-o-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-o-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-o-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-o-left</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-o-right"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-o-right</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-o-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-o-up</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-paper-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-paper-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-peace-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-peace-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-pointer-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-pointer-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-rock-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-rock-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-scissors-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-scissors-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-spock-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-spock-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-stop-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-stop-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thumbs-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thumbs-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thumbs-o-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-humbs-o-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thumbs-o-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thumbs-o-up</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-thumbs-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-thumbs-up</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <h3>Transportation Icons</h3>
                        <hr>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-ambulance"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-ambulance</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-automobile"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-automobile<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bicycle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bicycle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bus"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bus</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cab"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cab<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-car"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-car</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-fighter-jet"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-fighter-jet</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-plane"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-plane</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-rocket"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-rocket</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-ship"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-ship</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-space-shuttle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-space-shuttle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-subway"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-subway</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-taxi"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-taxi</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-train"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-train</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-truck"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-truck</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-wheelchair"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-wheelchair</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-wheelchair-alt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-wheelchair-alt</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <h3>Gender Icons</h3>
                        <hr>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-genderless"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-genderless</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-intersex"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-intersex<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-mars"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-mars</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-mars-double"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-mars-double</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-mars-stroke"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-mars-stroke</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-mars-stroke-h"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-mars-stroke-h</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-mars-stroke-v"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-mars-stroke-v</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-mercury"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-mercury</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-neuter"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-neuter</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-transgender"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-transgender</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-transgender"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-transgender</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-transgender-alt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-transgender-alt</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-venus"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-venus</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-venus-double"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-venus-double</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-venus-mars"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-venus-mars</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <h3>File Type Icons</h3>
                        <hr>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-archive-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-archive-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-audio-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-audio-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-code-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-code-o
                                    </span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-excel-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-excel-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-image-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-image-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-movie-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-movie-o <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-pdf-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-pdf-o
                                    </span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-photo-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-photo-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-picture-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-picture-o<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-powerpoint-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-powerpoint-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-sound-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-sound-o<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-text"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-text</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-text-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa- file-text-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-video-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-video-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-word-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-word-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-zip-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-
                                    file-zip-o<span class="text-muted">(alias)</span>
                                    </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <h3>Spinner Icons</h3>
                        <hr>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-circle-o-notch"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-circle-o-notch</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cog"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cog</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-gear"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-gear</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-refresh"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-refresh</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-spinner"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-spinner</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <h3>Form Control Icons</h3>
                        <hr>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-check-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-check-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-check-square-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-check-square-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-circle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-circle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-circle-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-circle-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-dot-circle-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-dot-circle-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-minus-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-minus-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-minus-square-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-minus-square-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-plus-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-plus-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-plus-square-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-plus-square-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-square-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-square-o</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <h3>Payment Icons</h3>
                        <hr>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cc-amex"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cc-amex</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cc-diners-club"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cc-diners-club</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cc-discover"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cc-discover</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cc-jcb"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cc-jcb</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cc-mastercard"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cc-mastercard</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cc-paypal"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cc-paypal</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cc-stripe"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cc-stripe</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cc-visa"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cc-visa</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-credit-card"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-credit-card</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-credit-card-alt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-credit-card-alt</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-google-wallet"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-google-wallet</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-paypal"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-paypal</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <h3>Chart Icons</h3>
                        <hr>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-area-chart"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-area-chart</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bar-chart"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bar-chart</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bar-chart-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bar-chart-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-line-chart"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-line-chart</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-pie-chart"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-pie-chart</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <h3>Currency Icons</h3>
                        <hr>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bitcoin"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bitcoin<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-btc"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-btc</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cny"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cny</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-dollar"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-dollar<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-eur"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-eur</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-euro"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-euro</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-gbp"></i>
                            <span class="icon-name ">gbp</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-gg"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-gg</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-gg-circle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-gg-circle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-ils"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-fa-ils</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-inr"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-inr</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-jpy"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-jpy</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-krw"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-krw</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-money"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-money</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-rmb"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-rmb<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-rouble"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-rouble<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-rub"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-rub
                                    </span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-ruble"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-ruble<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-rupee"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-rupee<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-shekel"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-shekel<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sheqel"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sheqel<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-try"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-try</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-turkish-lira"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-turkish-lira</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-usd"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-usd</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-won"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-won
                                    <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-yen"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-yen<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <h3>Text Editor Icons</h3>
                        <hr>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa fa-align-center"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-align-center</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-align-justify"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-align-justify</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-align-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-align-left</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-align-right"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-align-right</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bold"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bold</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-chain"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-chain<span class="text-muted">(alias)</span>
                                    </span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-chain-broken"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-chain-broken</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-clipboard"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-clipboard</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-columns"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-columns</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-copy"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-copy<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cut"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cut<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-dedent"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-dedent<span class="text-muted">(alias)</span>
                                    </span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-eraser"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-eraser</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-text"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-file-text</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-file-text-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-text-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-files-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-files-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-floppy-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-floppy-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-font"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-font</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-header"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-header</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-indent"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-indent</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-italic"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-italic</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-link"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-link</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-list"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-list</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-list-alt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-list-alt</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-list-ol"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-list-ol</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-list-ul"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-list-ul</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-outdent"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-outdent</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-paperclip"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-paperclip</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-paragraph"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-paragraph</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-paste"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-paste<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-rotate-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-rotate-left<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-rotate-right"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-rotate-right<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-save"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-save</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-scissors"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-scissors</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-strikethrough"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-strikethrough</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-subscript"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-subscript</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-superscript"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-superscript</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-table"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-table</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-text-height"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-text-height</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-text-width"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-text-width</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-th"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-th</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-th-large"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-th-large</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-th-list"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-th-list</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-th-large"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-th-large</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-underline"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-underline</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-th-large"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-th-large</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-th-large"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-th-large</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-undo"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-undo</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-unlink"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-unlink<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <h3>Directional Icons</h3>
                        <hr>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-angle-double-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-angle-double-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-angle-double-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-angle-double-left</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-angle-double-right"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-angle-double-right</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-angle-double-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-angle-double-up</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-angle-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-angle-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-angle-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-angle-left</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-angle-right"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-angle-right</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-angle-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-angle-up</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrow-circle-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrow-circle-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrow-circle-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrow-circle-left</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrow-circle-o-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrow-circle-o-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrow-circle-o-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrow-circle-o-left</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrow-circle-o-right"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrow-circle-o-right</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrow-circle-o-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrow-circle-o-up</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrow-circle-right"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrow-circle-right</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrow-circle-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrow-circle-up</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrow-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrow-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrow-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrow-left</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrow-right"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrow-right</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrow-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrow-up</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrows"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrows</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrows-alt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrows-alt</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrows-h"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrows-h</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrows-v"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrows-v</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-caret-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-caret-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-caret-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-caret-left</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-caret-right"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-caret-right</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-caret-square-o-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-caret-square-o-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-caret-square-o-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-caret-square-o-left</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-caret-square-o-right"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-caret-square-o-right</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-caret-square-o-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-caret-square-o-up</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-caret-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-caret-up</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-chevron-circle-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-chevron-circle-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-chevron-circle-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-chevron-circle-left</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-chevron-circle-right"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-chevron-circle-right</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-chevron-circle-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-chevron-circle-up</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-chevron-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-chevron-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-chevron-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-chevron-left</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-chevron-right"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-chevron-right</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-chevron-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-chevron-up</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-exchange"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-exchange</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-o-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-o-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-o-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-o-left</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-o-right"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-o-right</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-o-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-o-left</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-o-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-o-left</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hand-o-right"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hand-o-right</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-long-arrow-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-long-arrow-down</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-long-arrow-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-long-arrow-left</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-long-arrow-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-long-arrow-up</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-toggle-down"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-toggle-down<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-toggle-left"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-toggle-left<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-toggle-right"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-toggle-right<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-toggle-up"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-toggle-up
                                    <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <h3>Video Player Icon</h3>
                        <hr>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrows-alt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrows-alt</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-backward"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-backward</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-compress"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-compress</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-eject"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-eject</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-expand"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-expand</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-fast-backward"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-fast-backward</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-fast-forward"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-fast-forward</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-forward"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-forward</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-pause"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-pause</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-pause-circle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-pause-circle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-pause-circle-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrows-alt</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-arrows-alt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-arrows-alt</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-play"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-play</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-play-circle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-play-circle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-play-circle-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-play-circle-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-random"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-random</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-step-backward"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-step-backward</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-step-forward"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-step-forward</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-stop"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-stop</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-stop-circle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-stop-circle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-stop-circle-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-stop-circle-o</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <h3>Brand Icons</h3>
                        <hr>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-500px"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-500px</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-500px"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-500px</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-adn"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-adn</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-amazon"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-amzon</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-android"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-android</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-angellist"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-angellist</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-apple"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-apple</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bandcamp"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bandcamp</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-behance"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-behance</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-behance-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-behance-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bitbucket"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bitbucket</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bitbucket-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bitbucket-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bitcoin"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bitcoin
                                    <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-black-tie"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-black-tie</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bluetooth"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bluetooth</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-bluetooth-b"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-bluetooth-b</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-btc"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-btc</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-buysellads"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-buysellads</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cc-amex"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-amex</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cc-diners-club"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cc-diners-club</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cc-jcb"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cc-jcb</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cc-mastercard"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cc-mastercard</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cc-paypal"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cc-paypal</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cc-stripe"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cc-stripe</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-cc-visa"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-cc-visa</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-chrome"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-chrome</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-codepen"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-codepen</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-codiepie"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-codiepie</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-connectdevelop"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-connectdevelop</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-contao"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-contao</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-css3"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-fa fa-css3</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-dashcube"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-dashcube</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-delicious"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-delicious</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-deviantart"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-deviantart</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-digg"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-digg</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-dribbble"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-dribbble</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-dropbox"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-dropbox</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-drupal"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-drupal</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-edge"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-edge</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-eercast"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-eercast</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-empire"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-empire</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-envira"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-envira</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-etsy"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-etsy</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-expeditedssl"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-expeditedssl</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-fa"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-fa<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-facebook"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-facebook</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-facebook-f"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-facebook-f<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-facebook-official"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-facebook-official</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-facebook-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-facebook-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-firefox"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-firefox</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-first-order"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-first-order</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-flickr"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-flickr</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-font-awesome"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-font-awesome</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-fonticons"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-fonticons</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-fort-awesome"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-fort-awesome</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-forumbee"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-forumbee</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-foursquare"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-foursquare</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-ge"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-ge<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-get-pocket"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-get-pocket</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-gg"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-gg</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-gg-circle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-gg-circle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-git"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-git</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-git-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-git-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-github"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-github</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-github-alt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-github-alt</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-github-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-github-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-gitlab"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-fa fa-gitlab</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-gittip"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-gittip<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-glide"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-glide</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-github"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-github</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-glide-g"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-glide-g</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-google"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-google</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-google-plus"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-google-plus</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-google-plus-circle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-google-plus-circle
                                    <span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-google-plus-official"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-google-plus-official</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-google-plus-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-google-plus-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-google-wallet"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-google-wallet</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-gratipay"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-gratipay</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-grav"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-grav</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hacker-news"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hacker-news</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-houzz"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-houzz</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-html5"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-html5</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-imdb"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-imdb</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-instagram"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-instagram</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-internet-explorer"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-internet-explorer</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-ioxhost"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-ioxhost</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-joomla"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-joomla</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-jsfiddle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-jsfiddle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-lastfm"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-lastfm</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-lastfm-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-lastfm-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-leanpub"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-leanpub</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-linkedin"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-linkedin</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-linkedin-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-linkedin-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-linode"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-linode</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-linux"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-linux</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-maxcdn"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-maxcdn</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-meanpath"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-meanpath</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-medium"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-medium</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-meetup"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-meetup</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-mixcloud"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-mixcloud</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-modx"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-modx</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-odnoklassniki"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-odnoklassniki</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-odnoklassniki-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-odnoklassniki-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-opencart"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-opencart</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-openid"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-openid</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-opera"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-fa fa-opera</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-optin-monster"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-optin-monster</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-pagelines"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-pagelines</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-paypal"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-paypal</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-pied-piper"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-pied-piper</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-pied-piper-alt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-pied-piper-alt</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-pied-piper-pp"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-pied-piper-pp</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-pinterest"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-pinterest</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-pinterest-p"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-pinterest-p</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-pinterest-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-pinterest-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-product-hunt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-product-hunt</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-qq"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-qq</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-quora"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-quora</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-ra"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-ra<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-ravelry"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-ravelry</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-rebel"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-rebel</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-reddit"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-reddit</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-reddit-alien"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-reddit-alien</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-reddit-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-reddit-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-renren"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-renren</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-resistance"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-resistance<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-safari"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-safari</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-scribd"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-scribd</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-sellsy"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-sellsy</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-share-alt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-share-alt</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-share-alt-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-share-alt-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-shirtsinbulk"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-shirtsinbulk</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-simplybuilt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-simplybuilt</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-skyatlas"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-skyatlas</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-skype"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-skype</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-slack"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-slack</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-slideshare"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-slideshare</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-snapchat"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-snapchat</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-snapchat-ghost"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-snapchat-ghost</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-snapchat-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-snapchat-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-soundcloud"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-soundcloud</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-spotify"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-spotify</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-stack-exchange"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-stack-exchange</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-steam"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-steam</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-steam-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-steam-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-stumbleupon"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-stumbleupon</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-stumbleupon-circle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-stumbleupon-circle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-superpowers"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-superpowers</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-telegram"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-telegram</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-tencent-weibo"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-tencent-weibo</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-themeisle"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-themeisle</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-trello"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-trello</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-tripadvisor"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-tripadvisor</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-tumblr"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-tumblr</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-tumblr-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-tumblr-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-twitch"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-twitch</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-twitter"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-twitter</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-twitter-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-twitter-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-usb"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-usb</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-viacoin"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-viacoin</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-viadeo"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-viadeo</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-viadeo-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-viadeo-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-vimeo"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-vimeo</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-vimeo-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-vimeo-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-vine"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-vine</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-vk"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-vk</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-wechat"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-wechat<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-weibo"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-weibo</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-weixin"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-weixin</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-whatsapp"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-whatsapp</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-wikipedia-w"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-wikipedia-w</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-windows"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-windows</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-wordpress"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-wordpress</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-wpbeginner"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-wpbeginner</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-wpexplorer"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-wpexplorer</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-wpforms"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-wpforms</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-xing"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-xing</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-xing-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-xing-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-y-combinator"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-y-combinator<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-y-combinator-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-combinator-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-yahoo"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-yahoo</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-yc"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-yc<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-yc-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-yc-square<span class="text-muted">(alias)</span></span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-yelp"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-yelp</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-yoast"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-yoast</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-youtube"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-youtube</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-youtube-play"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-youtube-play</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-youtube-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-youtube-square</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <h3>Medical Icons</h3>
                        <hr>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-ambulance"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-ambulance</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-h-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-h-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-heart"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-heart</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-heart-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-heart-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-heartbeat"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-heartbeat</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-hospital-o"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-hospital-o</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-medkit"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-medkit</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-plus-square"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-plus-square</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-stethoscope"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-stethoscope</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-user-md"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-user-md</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-wheelchair"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-wheelchair</span>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2 icon_box_width">
                        <div class="icon_box">
                            <i class="hvr-buzz-out fa fa-wheelchair-alt"></i>
                            <span class="icon-name">hvr-buzz-out fa fa-wheelchair-alt</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer class="main-footer" style="margin-left:0;position: fixed;left: 0;bottom: 0;width: 100%;">
        <strong ><%=UtilityClass.copyRightMsg%><span><%=UtilityClass.startYear%></span> - <span><%=UtilityClass.endYear%></span> <a href="<%=UtilityClass.siteUrl%>"><%=UtilityClass.ApplicationTitle%></a>.</strong> <%=UtilityClass.footerMsg%>
    </footer>
</div>

</body>

</html>