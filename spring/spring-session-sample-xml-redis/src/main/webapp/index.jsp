<%@page import="java.util.Enumeration"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Session Attributes</title>
    <style type="text/css">
        body {
            padding: 1em;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Description</h1>
        <p>This application demonstrates how to use a Redis instance to back your session. Notice that there is no JSESSIONID cookie. We are also able to customize the way of identifying what the requested session id is.</p>

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
    <br>
    <br>
    <%
    String sessionID = session.getId();
    out.println("sessionID : " + sessionID + "<br>");
    
    int s_interval = session.getMaxInactiveInterval();    // session 최대 유효시간 => 톰캣 xml파일에 기본값 60분
    out.println("sessionInterval : " + s_interval + "<br>");
    %>
    
    <br>    
    <br>
    <br>
    
    
    <%
    String sessionName;
    String sessionValue;
    Enumeration enumeration = session.getAttributeNames(); // 세션객체를 직렬화해서 받음
    while(enumeration.hasMoreElements()){
        sessionName = enumeration.nextElement().toString();
        sessionValue = session.getAttribute(sessionName).toString();
        out.println("sessionName : " + sessionName + "<br>");
        out.println("sessionValue : " + sessionValue + "<br>");
    }
    %>
    
</body>
</html>
