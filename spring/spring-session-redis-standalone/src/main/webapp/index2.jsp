<%@page import="java.util.Enumeration"%>
<%@ page language="java" import="java.util.*" %> 
<%@ page import = "java.util.ResourceBundle" %>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>RedisSession</title>
    <style type="text/css">
        body {
            padding: 1em;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>K8S Session Store With Redis</h1>
        <p>This application demonstrates how to use a Redis instance to back your session. 
        Notice that there is no JSESSIONID cookie. 
        We are also able to customize the way of identifying what the requested session id is.</p>

        <h1>Try it</h1>

        <form id="attr" class="form-inline" role="form" action="./session" method="post">
            <label for="attributeName">Attribute Name</label>
            <input id="attributeName" type="text" name="attributeName"/>
            <label for="attributeValue">Attribute Value</label>
            <input id="attributeValue" type="text" name="attributeValue"/>
            <input type="submit" value="Set Attribute"/>
        </form>

        <hr/>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>Attribute Name</th>
                <th>Attribute Value</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope}" var="attr">
                <tr>
                    <td><c:out value="${attr.key}"/></td>
                    <td><c:out value="${attr.value}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <br>

</body>
</html>