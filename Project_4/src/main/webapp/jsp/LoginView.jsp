<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="in.co.rays.Project_4.Controller.ORSView"%>
<%@page import="in.co.rays.Project_4.Controller.LoginCtl"%>
<%@page import="in.co.rays.Project_4.Utility.DataUtility"%>
<%@page import="in.co.rays.Project_4.Utility.ServletUtility"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<form action="<%=ORSView.LOGIN_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.Project_4.Bean.UserBean"
			scope="request"></jsp:useBean>

		<%
			String URI = (String) request.getAttribute("uri");
		%>

		<input type="hidden" name="URI" value="<%=URI%>">

		<center>
			<h1>Login</h1>

			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>
			<h2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
			</h2>

			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDateTime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDateTime())%>">

			<table>
				<tr>
					<th>LoginId <font color="red">*</font></th>
					<td><input type="text" name="login" size=30
						placeholder="Enter valid Email ID"
						value="<%=DataUtility.getStringData(bean.getLogin())%>">&nbsp;<font
						style="position: fixed" color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>
				<tr>
					<th>Password<font color="red">*</font></th>
					<td><input type="password" name="password" size=30
						placeholder="Enter Password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>">&nbsp;<font
						style="position: fixed" color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>
				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=LoginCtl.OP_SIGN_IN%>"> &nbsp; <input
						type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_UP%>">
						&nbsp;</td>
				</tr>
				<tr>
					<th></th>
					<td><a href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b>Forget
								my password</b></a>&nbsp;</td>
				</tr>
			</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>


</body>
</html>