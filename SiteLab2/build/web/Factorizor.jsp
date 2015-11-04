<%-- 
    Document   : Factorizor
    Created on : Oct 13, 2015, 8:10:55 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Factorizor</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </head>
    <body>
         <jsp:include page="header.jsp"/>
        <br>
        <div class="text-center" style="max-width:1080px; border:2px solid #ccc; margin-left: auto;margin-right: auto;">
            <form action="factmiddleman.jsp" method="method" role="form">
                <div class="form-group">
                    <label>Enter Number: </label>
                    <input type="number" min="0" name="moneyString" value="" />
                    <input type="submit">
                </div>
            </form>

        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
