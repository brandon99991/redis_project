<%@page import="java.util.Enumeration"%>
<%@ page language="java" import="java.util.*" %> 
<%@ page import = "java.util.ResourceBundle" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
session.invalidate();

response.sendRedirect("./index.jsp");

%>    

