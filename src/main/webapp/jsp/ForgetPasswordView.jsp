<%@page import="in.co.insurance.mgt.ctl.ForgetPasswordCtl"%>
<%@page import="in.co.insurance.mgt.ctl.CategoryCtl"%>
<%@page import="in.co.insurance.mgt.ctl.UserRegistrationCtl"%>
<%@page import="in.co.insurance.mgt.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.insurance.mgt.ctl.LoginCtl"%>
<%@page import="in.co.insurance.mgt.util.DataUtility"%>
<%@page import="in.co.insurance.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forget Password</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div class="container">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="<%=IMSView.WELCOME_CTL%>">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">Forget Password</li>
			</ol>
		</nav>

		<div class="card shadow  mb-5 bg-body rounded" style="width: 800px">
			<h5 class="card-header"
				style="height: 70px; font-size: 25px; background-color: #18334f; color: white;">Forget Password</h5>
			<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
			</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
			</font></b>
			<div class="card-body">
				<form role="form" action="<%=IMSView.FORGET_PASSWORD_CTL%>"
					method="post" >

					<jsp:useBean id="bean" class="in.co.insurance.mgt.bean.UserBean"
						scope="request"></jsp:useBean>

					 <input
						type="hidden" name="id" value="<%=bean.getId()%>">

					<div class="mb-3">
						<label class="form-label">Login Id</label> <input type="text"
							class="form-control" name="login" placeholder="Enter Login Id"
							value="<%=ServletUtility.getParameter("login", request)%>">
						<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("login", request)%></div>
					</div>

					<input type="submit" name="operation"
						class="btn btn-primary pull-right"
						value="<%=ForgetPasswordCtl.OP_GO%>">
				</form>
			</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>