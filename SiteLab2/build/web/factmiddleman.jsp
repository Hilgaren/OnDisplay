<%-- 
    Document   : factmiddleman
    Created on : Oct 14, 2015, 2:00:02 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="fact" class="userinput.Factorizer" scope="session"/>
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
