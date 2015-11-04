<%-- 
    Document   : home
    Created on : Oct 16, 2015, 10:18:51 AM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%--   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
        <title>Item Library</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon"
              href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>

        <div class="container">
            <h1>Items For Sale</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active">
                        <a
                            href="${pageContext.request.contextPath}/user">User</a>
                    </li>
                    <li role="presentation">
                        <a
                            href="${pageContext.request.contextPath}/admin">Admin</a>
                    </li>

                </ul>
            </div>

            <div class="row" style="max-width:1080px; border:2px solid #ccc; margin-left: auto;margin-right: auto;">
                <div class="col-md-6">
                    <h2>My Items</h2>
                    <table id="itemTable" class="table table-hover" style="max-width:1080px; border:2px solid #ccc; margin-left: auto;margin-right: auto;">
                        <tr>
                            <th width="15%">Item Name</th>
                            <th width="15%">Quantity</th>
                            <th width="15%">Cost</th>
                            <th width="15%">Buy</th>

                        </tr>
                        <tbody id="contentRows"></tbody>
                    </table>
                </div> 
                <div class="col-md-6" style="max-width:1080px; border:2px solid #ccc; margin-left: auto;margin-right: auto;">
                    <h2 class="text-right">Insert Money Here</h2>

                    <form class="form-horizontal" role="form">
                        <div class="form-group">

                            <label for="add-moneh" class="col-md-4 control-label">
                                Insert Here:
                            </label>
                            <div class="col-md-8">
                                <input type="number" step=".01" max="5" min="0"

                                       class="form-control"
                                       id="add-moneh"
                                       placeholder="Name"/>
                            </div>
                        </div>
                        <div class="row" style="max-width:1080px; border:2px solid #ccc; margin-left: auto;margin-right: auto;">
                            <div class="col-md-4">Current Funds: $</div>
                            <div class="col-md-1" id="deposit"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-12" id="validationErrors" style="color: red"></div>
                            <div class="col-md-12" id="change" style="color: green">&nbsp;</div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-8">
                                <button type="submit"
                                        id="add-moneh-button"
                                        class="btn btn-default">Insert Money

                                </button>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-8">
                                <button type="submit"
                                        id="dispense"
                                        class="btn btn-default">Dispense Change

                                </button>
                            </div>
                        </div>
                    </form>

                </div> 

            </div>

        </div> 

        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/ItemList.js"></script>
    </body>
</html>
