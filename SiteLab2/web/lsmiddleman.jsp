<%-- 
    Document   : lsmiddleman
    Created on : Oct 14, 2015, 10:34:27 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="luckysevenstuff" class="userinput.LuckySevensServlet" scope="session"/>
<jsp:setProperty name="luckysevenstuff" property="moneyString"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        response.sendRedirect("lsresponse.jsp");
        %>
    </body>
</html>
