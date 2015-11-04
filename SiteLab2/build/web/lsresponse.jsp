<%-- 
    Document   : lsresponse
    Created on : Oct 14, 2015, 10:09:00 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <jsp:useBean id="luckysevenstuff" class="userinput.LuckySevensServlet" scope="session"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="text-center" style="max-width:1080px; border:2px solid #ccc; margin-left: auto;margin-right: auto;">
        <%= luckysevenstuff.method() %>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
