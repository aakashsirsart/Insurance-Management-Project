<%@page import="in.co.insurance.mgt.ctl.LoginCtl"%>
<%@page import="in.co.insurance.mgt.util.ServletUtility"%>
<%@page import="in.co.insurance.mgt.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div class="container">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="<%=IMSView.WELCOME_CTL%>">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">Login</li>
			</ol>
		</nav>

		<div class="card shadow  mb-5 bg-body rounded" style="width: 700px">
			<h5 class="card-header"
				style="height: 70px; font-size: 25px; background-color: #18334f; color: white;">Login</h5>
			<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
			</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
			</font></b>
			<div class="card-body">
				<form role="form" action="<%=IMSView.LOGIN_CTL%>" method="post">

					<jsp:useBean id="bean" class="in.co.insurance.mgt.bean.UserBean"
						scope="request"></jsp:useBean>
					<%
					String uri = (String) request.getAttribute("uri");
					%>

					<input type="hidden" name="uri" value="<%=uri%>"> <input
						type="hidden" name="id" value="<%=bean.getId()%>"> <input
						type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
					<input type="hidden" name="modifiedBy"
						value="<%=bean.getModifiedBy()%>"> <input type="hidden"
						name="createdDatetime"
						value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
					<input type="hidden" name="modifiedDatetime"
						value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

					<div class="mb-3">
						<label class="form-label">Login Id</label> <input type="text"
							class="form-control" name="login" placeholder="Login Id"
							value="<%=DataUtility.getStringData(bean.getLogin())%>">
						<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("login", request)%></div>
					</div>
					<div class="mb-3">
						<label class="form-label">Password</label> <input type="password"
							class="form-control" name="password" placeholder="Password"
							value="<%=DataUtility.getStringData(bean.getPassword())%>">
						<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("password", request)%></div>
					</div>

					<input type="submit" name="operation"
						class="btn btn-primary pull-right"
						value="<%=LoginCtl.OP_SIGN_IN%>"> <input type="submit"
						name="operation" class="btn btn-primary pull-right"
						value="<%=LoginCtl.OP_SIGN_UP%>">

				</form>
			</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>