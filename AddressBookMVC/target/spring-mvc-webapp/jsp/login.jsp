<%-- 
    Document   : login
    Created on : Oct 30, 2015, 9:42:47 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon"
              href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div>
            <div class="container">
                <h2>Sign in to the Address Book</h2>
                <div class="navbar navbar-default">
                    <div class="navbar-header"><a class="navbar-brand active"  href="${pageContext.request.contextPath}/home">Address Book</a></div>
                    <ul class="nav navbar-nav">
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/home">Home</a>
                        </li>
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/search">Search</a>
                        </li>
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/stats">Stats</a>
                        </li>
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/j_spring_security_logout">Log
                                Out</a>
                        </li>
                    </ul>
                </div>

                <c:if test="${param.login_error == 1}">
                    <h3>Wrong id or password!</h3>
                </c:if>
                <!-- #2 - Post to Spring security to check our authentication -->
                <form method="post" class="signin"
                      action="j_spring_security_check">
                    <fieldset>
                        <table cellspacing="0">
                            <tr>
                                <th>
                                    <label for="username">Username
                                    </label>
                                </th>

                                <td><input id="username_or_email"
                                           name="j_username"
                                           type="text" />
                                </td>
                            </tr>
                            <tr>
                                <th><label for="password">Password</label></th>
                                <!-- #2b - must be j_password for Spring -->
                                <td><input id="password"
                                           name="j_password"
                                           type="password" />
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td><input name="commit" type="submit"
                                           value="Sign In" /></td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
                
            </div>
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/addressBook.js"></script>
    </body>
</html>
