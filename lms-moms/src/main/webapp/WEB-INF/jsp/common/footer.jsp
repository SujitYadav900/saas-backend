<%@ page import="com.example.maxcrm.MaxCrm.Utility.UtilityClass" %>
<strong style=""><%=UtilityClass.copyRightMsg%><span><%=UtilityClass.startYear%></span> - <span><%=UtilityClass.endYear%></span> <a href="<%=UtilityClass.siteUrl%>"><%=UtilityClass.ApplicationTitle%></a>.</strong> <%=UtilityClass.footerMsg%>
<script>

   var rolelist=localStorage.getItem("rolelistlocal")
    if(rolelist==null)
    {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "${pageContext.servletContext.contextPath}/api/roles/getroleafterlogin",
            success: function (json) {
               localStorage.setItem("rolelistlocal",JSON.stringify(json))


            },
            error: function (err) {

            }

        });

    }
    $('.content-wrapper').css("margin-top",$('#headerdiv').height())


</script>

