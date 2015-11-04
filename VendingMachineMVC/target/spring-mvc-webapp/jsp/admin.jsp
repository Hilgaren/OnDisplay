<%-- 
    Document   : admin
    Created on : Oct 29, 2015, 9:01:17 AM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- #1 - Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon"
              href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>Item Library</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/user">User</a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/admin">Admin</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/j_spring_security_logout">Log
                            Out</a>
                    </li>
                </ul>
            </div>
            <div class="col-md-6">
                <h2>My Items</h2>
                <table id="itemTable" class="table table-hover">
                    <tr>
                        <th width="15%">Item Name</th>
                        <th width="15%">Quantity</th>
                        <th width="15%">Cost</th>
                        <th width="15%">Buy</th>
                        <th width="15%">Delete</th>
                    </tr>
                    <tbody id="adminContentRows"></tbody>
                </table>
                <div id="validationErrors" style="color: red"/>
            </div>
        </div>
        <div class="col-md-6">
            <h2>Stock New Item</h2>
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label for="add-item-name" class="col-md-4 control-label">
                        Item Name:
                    </label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-item-name"
                               placeholder="Item Name"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-quantity" class="col-md-4 control-label">
                        Quantity:
                    </label>
                    <div class="col-md-8">
                        <input type="number"
                               max="25"
                               min="1"
                               class="form-control"
                               id="add-quantity"
                               placeholder="Quantity"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-cost" class="col-md-4 control-label">
                        Cost:
                    </label>
                    <div class="col-md-8">
                        <input type="number"
                               min=".25"
                               step=".01"
                               max="5"
                               class="form-control"
                               id="add-cost"
                               placeholder="Cost"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <button type="submit"
                                id="add-button"
                                class="btn btn-default">
                            Create Item
                        </button>
                    </div>
                </div>
            </form>
        </div> 

        <div id="validationErrors" style="color: red"/>
    </div>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/ItemList.js"></script>
</body>
</html>
