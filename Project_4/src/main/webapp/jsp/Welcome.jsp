<%@page import="in.co.rays.Project_4.Bean.RoleBean"%>
<%@page import="in.co.rays.Project_4.Bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="in.co.rays.Project_4.Controller.ORSView"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

</head>
<body>
	<form action="<%=ORSView.WELCOME_CTL%>">
		<%@ include file="Header.jsp"%>
		<h1 align="Center">
			<font size="10px" color="red">Welcome to ORS </font>
		</h1>

		<%
			UserBean beanUserBean = (UserBean) session.getAttribute("user");
			if (beanUserBean != null) {
				if (beanUserBean.getRoleId() == RoleBean.STUDENT) {
		%>

		<h2 align="Center">
			<a href="<%=ORSView.GET_MARKSHEET_CTL%>">Click here to see your Marksheet </a>
		</h2>

		<%
			}
			}
		%>

	</form>

	<%@ include file="Footer.jsp"%>

</body>
</html>