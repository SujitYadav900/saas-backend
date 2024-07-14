<%@ page import="com.example.maxcrm.MaxCrm.Utility.UtilityClass" %>
<aside class="main-sidebar" >
    <!-- sidebar -->
    <div class="sidebar">
        <!-- sidebar menu -->
        <ul class="sidebar-menu" id="sidebarmenu" style="margin-bottom: 100px;">
            <li class="active">
                <a href="${pageContext.request.contextPath}/"><i class="fa fa-tachometer"></i><span>Dashboard</span>
                    <span class="pull-right-container">
                     </span>
                </a>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-users"></i><span>Master</span>
                    <span class="pull-right-container">
                     <i class="fa fa-angle-left pull-right"></i>
                     </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${pageContext.request.contextPath}/user">Usermaster</a></li>
                    <li><a href="${pageContext.request.contextPath}/rolelist">Roles</a></li>
                    <li><a href="${pageContext.request.contextPath}/leadstatus">Lead Status</a></li>
                    <li><a href="${pageContext.request.contextPath}/leadtype">Lead Type</a></li>
                    <li><a href="${pageContext.request.contextPath}/leadsource">Lead Source</a></li>
                    <li><a href="${pageContext.request.contextPath}/leadtype">Lead Type</a></li>
                    <li><a href="${pageContext.request.contextPath}/menu">Menu</a></li>
                    <li><a href="${pageContext.request.contextPath}/urlaccess">Page</a></li>
                    <li><a href="${pageContext.request.contextPath}/product">Product</a></li>

                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="glyphicon glyphicon-bullhorn"></i><span>Campaign</span>
                    <span class="pull-right-container">
                     <i class="fa fa-angle-left pull-right"></i>
                     </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${pageContext.request.contextPath}/template">Template</a></li>


                </ul>
            </li>

        </ul>
    </div>
    <!-- /.sidebar -->
</aside>
<script>
    var menu=localStorage.getItem("Menuapp");

    if(menu==null)
    {
        console.log("Loading From Server")
        var json=JSON.parse('<%=UtilityClass.getCurrentUser().getMenuJson()%>');
        var html="";
        for(var i=0;i<json.length;i++)
        {
            html=html+"<li class=\"treeview\" >"
            html=html+" <a href=\"#\">\n" +
                "                    <i class='"+json[i].menuIcon+"'></i><span>"+json[i].menuName+"</span>\n" +
                "                    <span class=\"pull-right-container\" >\n" +
                "                     <i class=\"fa fa-angle-left pull-right\"></i>\n" +
                "                     </span>\n" +
                "                </a>";
            html=html+" <ul class=\"treeview-menu\" > "
            var listElement=json[i].listment;
            for(var k=0;k<listElement.length;k++) {
                if (listElement[k].pagename != "Home") {
                    html = html + "<li ><a title='" + listElement[k].pagedesc + "' href='" + listElement[k].url + "'>" + listElement[k].pagename + "</a></li> "
                }
                }
            html=html+" </ul> "


            html=html+" </li>"

        }
        localStorage.setItem("Menuapp",html)
        document.getElementById("sidebarmenu").innerHTML=html;

    }
    else{
        console.log("Loading From Localstorage")
        document.getElementById("sidebarmenu").innerHTML=menu;
    }






</script>