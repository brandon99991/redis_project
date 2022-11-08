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
        <form id="logout" class="form-inline" role="form" action="./logout.jsp" method="post">
            <input type="submit" value="Session Invalidate"/>
        </form>

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
    
<%
///////////////////////////
// sessioID
///////////////////////////

    out.println("<h1>* Session Info</h1>");

    String sessionID = session.getId();
    out.println("<b>session.getId</b> : " + sessionID + "<br>");
    
    int s_interval = session.getMaxInactiveInterval();   
    out.println("<b>session.isNew </b>: " + session.isNew() + "<br>");
    out.println("<b>session.hashCode </b>: " + session.hashCode() + "<br>");

	String pattern = "yyyy-MM-dd HH:mm:ss";
	SimpleDateFormat sdf  = new SimpleDateFormat(pattern);
	Date date = new Date();
	date.setTime(session.getCreationTime());

	
    out.println("<b>세션 생성된 시간</b>: " + session.getCreationTime() + "<br>");
    out.println("<b>세션 생성된 시간 </b>: " + sdf.format(date) + "<br>");
    date.setTime(session.getLastAccessedTime());
    out.println("<b>세션 최근 접근 시간 </b>: " + session.getLastAccessedTime() + "<br>");
    out.println("<b>세션 최근 접근 시간 </b>: " + sdf.format(date) + "<br>");
    out.println("<b>session.getMaxInactiveInterval</b> : " + s_interval + "<br>");

///////////////////////////
//get Cookies
///////////////////////////
out.println("<h1>* Get Cookies (name : value) </h1>");

Cookie cookies[] = request.getCookies();
for (int i=0; i < cookies.length; i++) {
String name = cookies[i].getName();
String value = cookies[i].getValue();

out.println("<b>" + name + "</b> : " + value + "<br>");
}
    
    
/* 
///////////////////////////
// session Attribute
///////////////////////////
    String sessionName;
    String sessionValue;
    Enumeration enumeration = session.getAttributeNames();
    while(enumeration.hasMoreElements()){
        sessionName = enumeration.nextElement().toString();
        sessionValue = session.getAttribute(sessionName).toString();

        out.println("<b>sessionName</b> : " + sessionName + "<br>");
        out.println("<b>sessionValue</b> : " + sessionValue + "<br>");
    }
    
 
///////////////////////////
// HostName, IP, Port ...
///////////////////////////
 out.println("<h1>* Get HostName, IP, Port ... </h1>");

       String svrName=request.getLocalName();
       String svrIp=request.getLocalAddr();
       int svrPort=request.getLocalPort();

        out.println(" HostName : " + svrName + "<br>");
        out.println(" ServerIP : " + svrIp + "<br>");
        out.println(" ServerPort : " + svrPort + "<br>");
        
        
        //String RemoteIP=request.getRemoteAddr();
        //String RemoteHost=request.getRemoteHost();
        //int RemotePort=request.getRemotePort();
        
        //out.println(" RemoteIP : " + RemoteIP + "<br>");
        //out.println(" RemoteHost : " + RemoteHost + "<br>");
        //out.println(" RemotePort : " + RemotePort + "<br>");
        

        String Protocol=request.getProtocol();
        String Scheme=request.getScheme();

        out.println(" Protocol : " + Protocol + "<br>");
        out.println(" Scheme : " + Scheme + "<br>");

        String RequestUri=request.getRequestURI();
        //String RequestUrl=request.getRequestURL();

        out.println(" RequestUri : " + RequestUri + "<br>");
        //out.println(" RequestUrl : " + RequestUrl + "<br>");
        
        //String sesId=request.getRequestedSessionId();
        //String sesCookie=request.isRequestedSessionIdFromCookie();
        //String FromUrl=request.isRequestedSessionIdFromURL();
        //String sesValid=request.isRequestedSessionIdValid();  
        //out.println(" sesId : " + sesId + "<br>");      

///////////////////////////
// get Header
///////////////////////////

 out.println("<h1>* Get Header </h1>");

 Enumeration eHeader = request.getHeaderNames();
 while (eHeader.hasMoreElements()) {
 String hName = (String)eHeader.nextElement();
 String hValue = request.getHeader(hName);

 out.println(hName + " : " + hValue + "<br>");
 }

///////////////////////////
// Get Attribute
///////////////////////////

 out.println("<h1>* Get Attribute </h1>");

 Enumeration eAttr = request.getAttributeNames();
 while (eAttr.hasMoreElements()) {
  String aName = (String)eAttr.nextElement();
  String aValue = request.getHeader(aName);

  out.println(aName + " : " + aValue + "<br>");
 }

///////////////////////////
// Get Parameter
///////////////////////////
 Enumeration eParam = request.getParameterNames();
 while (eParam.hasMoreElements()) {
  String pName = (String)eParam.nextElement();
  String pValue = request.getParameter(pName);

  out.println(pName + " : " + pValue + "<br>");
 }
 
 
 */
%>
    <br>
<%
///////////////////////////
// Redis Cluster Nodes
///////////////////////////
 out.println("<h1>* Get Redis Cluster Nodes </h1>");

	ResourceBundle resource = ResourceBundle.getBundle("redis");
	String RedisMasterNodes = resource.getString("spring.redis.master");
	String RedisSlaveNodes = resource.getString("spring.redis.slave");
	
	out.println(" Redis Master Nodes : " + RedisMasterNodes + "<br>");
	out.println(" Redis Slave Nodes : " + RedisSlaveNodes + "<br>");
%>    


</body>
</html>