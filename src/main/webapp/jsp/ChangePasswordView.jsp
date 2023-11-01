<%@page import="in.co.insurance.mgt.ctl.ChangePasswordCtl"%>
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
<title>Change Password</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div class="container">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="<%=IMSView.WELCOME_CTL%>">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">Change
					Password</li>
			</ol>
		</nav>

		<div class="card shadow  mb-5 bg-body rounded" style="width: 800px">
			<h5 class="card-header"
				style="height: 70px; font-size: 25px; background-color: #18334f; color: white;">Change
				Password</h5>
			<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
			</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
			</font></b>
			<div class="card-body">
				<form role="form" action="<%=IMSView.CHANGE_PASSWORD_CTL%>"
					method="post">

					<jsp:useBean id="bean" class="in.co.insurance.mgt.bean.UserBean"
						scope="request"></jsp:useBean>
					<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
						type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
					<input type="hidden" name="modifiedBy"
						value="<%=bean.getModifiedBy()%>"> <input type="hidden"
						name="createdDatetime"
						value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
					<input type="hidden" name="modifiedDatetime"
						value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">



					<div class="mb-3">
						<label class="form-label">Old Password</label> <input type="password"
							class="form-control" name="oldPassword"
							placeholder="Enter Old Password"
							value="<%=DataUtility.getString(
		request.getParameter("oldPassword") == null ? "" : DataUtility.getString(request.getParameter("oldPassword")))%>">
						<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("oldPassword", request)%></div>
					</div>

					<div class="row">
						<div class="col">
							<label class="form-label">New Password</label> <input
								type="password" class="form-control" name="newPassword"
								placeholder="Enter New Password"
								value="<%=DataUtility.getString(
		request.getParameter("newPassword") == null ? "" : DataUtility.getString(request.getParameter("newPassword")))%>">
							<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("newPassword", request)%></div>
						</div>
						<div class="col">
							<label class="form-label">Confirm Password</label> <input
								type="password" class="form-control" name="confirmPassword"
								placeholder="Confirm Password"
								value="<%=DataUtility.getString(request.getParameter("confirmPassword") == null ? ""
		: DataUtility.getString(request.getParameter("confirmPassword")))%>">
							<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("confirmPassword", request)%></div>
						</div>
					</div>

					<input type="submit" name="operation"
						class="btn btn-primary pull-right"
						value="<%=ChangePasswordCtl.OP_SAVE%>"> <input
						type="submit" name="operation" class="btn btn-primary pull-right"
						value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>">
				</form>
			</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>