<%@ page import="com.example.maxcrm.MaxCrm.Utility.UtilityClass" %>
<style>
    #dunamicmodelheader {
        text-align: center;
        font-weight: 800;
        font-size: 30px;
    }
    #status{
        display: block!important;
    }

    .smalltag{

        margin-left: 10px!important;
        cursor: pointer;
    }
    .badge-massege {
        margin-left: 0px!important;
    }

    .popup {
        height: 150px;
        width: 250px;
        background: #009688;
        bottom: -170px;
        right: 10px;
        position: absolute;
        transition: all 0.8s;
        padding-top: 20px;
        border-radius: 10px;
        vertical-align: top;
        display: none;
        box-shadow: 2px 3px 4px 6px grey;
        color: white;
    }
    .open {
        bottom: 0;
    }

    .band {
        position: absolute;
        top: 0;
        width: 100%;
        height: 20px;

        text-align: right;
        border-top-left-radius: 10px;
        border-top-right-radius: 10px;
        transition: all 0.8s;
    }



</style>

    <script src="${pageContext.request.contextPath}/assets/plugins/jQuery/jquery-1.12.4.min.js"
    type="text/javascript"></script>

<header id="headerdiv" style="position: fixed;width: 100%" class="main-header">
    <a href="<%=UtilityClass.propertyService.findProperty("Application","siteUrl")%>" class="logo">
        <!-- Logo -->
        <span class="logo-mini">
               <img src="<%=UtilityClass.minLogoUrl%>" alt="">
               </span>
        <span class="logo-lg">
               <img src="<%=UtilityClass.logoUrl%>" alt="">
               </span>
    </a>
    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top">
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <!-- Sidebar toggle button-->
            <span class="sr-only">Toggle navigation</span>
            <span class="pe-7s-angle-left-circle"></span>
        </a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <!-- Orders -->

                <li id="counterLi">

                </li>
                <!-- Messages -->
                <li class="dropdown tasks-menu" title="Click Notifications">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="hvr-buzz-out fa fa-hand-pointer-o"></i>
                        <span class="label label-success" id="clickunreadcount"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <ul id="clickul" class="menu">


                            </ul>
                        </li>
                    </ul>
                </li>
                <!-- Notifications -->
                <li class="dropdown tasks-menu" title="General Notifications">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="pe-7s-bell"></i>
                        <span class="label label-warning" id="notiunreadcount"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <ul id="notifyul" class="menu">

                            </ul>
                        </li>
                    </ul>
                </li>
                <!-- Tasks -->
                <li class="dropdown tasks-menu" title="Task Notifications">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="pe-7s-note2"></i>
                        <span class="label label-danger" id="taskunreadcount"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <ul id="taskul" class="menu">
                                 <!-- end task item -->
                            </ul>
                        </li>
                    </ul>
                </li>
                <!-- Help -->

                <!-- user -->
                <li class="dropdown dropdown-user">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span id="profilePicSpan"></span>
                    <ul class="dropdown-menu" >
                        <li>
                            <a title="Current User">
                                <i class="fa fa-user"></i> <%=UtilityClass.getCurrentUser().getUsername()%></a>
                        </li>
                        <li>
                            <a onclick="ProfilePageOpen()" title="View Profile" style="cursor: pointer;">
                                <i class="fa fa-address-card"></i> User Profile</a>
                        </li>

                        <li>
                            <a onclick="logout()" style="cursor: pointer;">
                            <i class="fa fa-sign-out"></i> Signout</a>
                        </li>
                    </ul>

                </li>


            </ul>
        </div>
        <div class="popup">
            <div class="band"><i style="margin-right: 10px;" onclick="visibleoffnotification()"  class="fa fa-remove "></i></div>
            <div><h4 style="text-align: center;font-weight: bolder" id="headingnotification"></h4></div>
            <br>
            <div style="text-align: center"><span style="padding: 10px" id="messagenotification"></span></div>
        </div>
    </nav>
</header>
<div class="modal fade" id="dynamicformmodel" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div id="dunamicmodelheader" class="modal-header modal-header-primary">



            </div>
            <div id="dynamicformbodymodel" class="modal-body">

            </div>

            <div class="modal-footer">

            </div>
        </div>
        <!-- /.modal-content -->
        <%--=========================================================================================================================================--%>
    </div>
    <!-- /.modal-dialog -->
</div>
<script>
    const  userId=<%=UtilityClass.getCurrentUser().getId()%>



    function  visibleoffnotification() {
        $('.popup').fadeOut('slow');
    }
    function showNotificationwithMessageAndHeader(msg,header)
    {
        $('#headingnotification').html(header)
        $('#messagenotification').html(msg)
        $('.popup').fadeIn('slow');
    }
    function logout()
    {
        localStorage.removeItem("rolelistlocal")
        localStorage.removeItem("Menuapp");
        location.href="${pageContext.request.contextPath}/logout";
    }
    function ProfilePageOpen() {
        location.href="${pageContext.request.contextPath}/profile?userid="+userId;
    }
    const snoozenotificationurl="${pageContext.request.contextPath}/api/notification/snoozenotification";

    const socketiourl ='<%=UtilityClass.propertyService.findProperty("Application","nodejsrealtimedeployurl") %>notifications';
    const  notificationurlget="${pageContext.request.contextPath}/api/notification/getnotification"
    const urlprefixapplication="${pageContext.request.contextPath}"
    const usernamecuruser="<%=UtilityClass.getCurrentUser().getUsername()%>";
    const irratenotificationclick="<%=UtilityClass.propertyService.findProperty("Application","IrritateNotificationClick")%>"
    const irratenotificationtask="<%=UtilityClass.propertyService.findProperty("Application","IrritateNotificationTask")%>"
    const irritateNotificationNoti	="<%=UtilityClass.propertyService.findProperty("Application","IrritateNotificationNoti")%>"

    const  gender="<%=UtilityClass.getCurrentUser().getGender()%>"

    var malePic = '<img src="${pageContext.request.contextPath}/assets/dist/img/avatar5.png" class="img-circle" width="45" height="45" alt="user"></a>';
    var femalePic = '<img src="${pageContext.request.contextPath}/assets/dist/img/avatar2.png" class="img-circle" width="45" height="45" alt="user"></a>';

    if(gender == "male"){
        $("#profilePicSpan").html(malePic)
    }else{
        $("#profilePicSpan").html(femalePic)
    }

    var isDemo = "<%=UtilityClass.getCurrentUser().isDemo()%>"
    console.log("isDemo ",isDemo)
    if(isDemo == "true"){

        console.log("showing remaining days")
        var remainingDays = "<%=UtilityClass.getRemainingDemoPeriod()%>"
        var btn = '<button class="btn btn-warning" style="margin-top: 15px;margin-right: 15px;" hidden><i class="fa fa-hourglass"></i> <strong>Days Left : '+remainingDays+'</strong></button>'
        $("#counterLi").html(btn);

    }

    var contextPath = null;
    function setContextPath(){
        contextPath = document.createElement("textarea");
        contextPath.value = "${pageContext.request.contextPath}";
    }
    setContextPath();

</script>





