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
<title>User Registration</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div class="container">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="<%=IMSView.WELCOME_CTL%>">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">User
					Registration</li>
			</ol>
		</nav>

		<div class="card shadow  mb-5 bg-body rounded" style="width: 800px">
			<h5 class="card-header"
				style="height: 70px; font-size: 25px; background-color: #18334f; color: white;">User
				Registration</h5>
			<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
			</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
			</font></b>
			<div class="card-body">
				<form role="form" action="<%=IMSView.USER_REGISTRATION_CTL%>"
					method="post">

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

					<div class="row">
						<div class="col">
							<label class="form-label">First Name</label> <input type="text"
								class="form-control" name="firstName"
								placeholder="Enter First Name"
								value="<%=DataUtility.getStringData(bean.getFirstName())%>">
							<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("firstName", request)%></div>
						</div>
						<div class="col">
							<label class="form-label">Last Name</label> <input type="text"
								class="form-control" name="lastName"
								placeholder="Enter Last Name"
								value="<%=DataUtility.getStringData(bean.getLastName())%>">
							<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("lastName", request)%></div>
						</div>
					</div>

					<div class="mb-3">
						<label class="form-label">Login Id</label> <input type="text"
							class="form-control" name="login" placeholder="Login Id"
							value="<%=DataUtility.getStringData(bean.getLogin())%>">
						<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("login", request)%></div>
					</div>

					<div class="row">
						<div class="col">
							<label class="form-label">Password</label> <input type="password"
								class="form-control" name="password" placeholder="Password"
								value="<%=DataUtility.getStringData(bean.getPassword())%>">
							<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("password", request)%></div>
						</div>
						<div class="col">
							<label class="form-label">Confirm Password</label> <input
								type="password" class="form-control" name="confirmPassword"
								placeholder="Password"
								value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>">
							<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("confimPassword", request)%></div>
						</div>
					</div>

					<div class="mb-3">
						<label class="form-label">Email Id</label> <input type="text"
							class="form-control" name="email" placeholder="Enter Email Id"
							value="<%=DataUtility.getStringData(bean.getEmail())%>">
						<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("email", request)%></div>
					</div>

					<div class="mb-3">
						<label class="form-label">Contact No.</label> <input type="text"
							class="form-control" name="contactNo"
							placeholder="Enter Contact No."
							value="<%=DataUtility.getStringData(bean.getContactNo())%>">
						<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("contactNo", request)%></div>
					</div>
					<div class="mb-3">
						<label class="form-label">Aadhaar Card No.</label> <input type="text"
							class="form-control" name="aadhaarNo"
							placeholder="Enter Aadhaar No."
							value = "<%=DataUtility.getStringData(bean.getAadhaarNo()) %>"
							>
						<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("aadhaarNo", request)%></div>
					</div>
					<div class="mb-3">
						<label class="form-label">PAN Card No.</label> <input type="text"
							class="form-control" name="panNo"
							placeholder="Enter PAN No."
							value="<%=DataUtility.getStringData(bean.getPanNo())%>">
						<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("panNo", request)%></div>
					</div>

					<%
					HashMap map = new HashMap();
					map.put("Male", "Male");
					map.put("Female", "Female");
					%>

					<div class="mb-3">
						<label class="form-label">Gender</label><%=HTMLUtility.getList("gender", String.valueOf(bean.getGender()), map)%>
						<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("gender", request)%></div>
					</div>



					<input type="submit" name="operation"
						class="btn btn-primary pull-right"
						value="<%=UserRegistrationCtl.OP_SIGN_UP%>"> <input
						type="submit" name="operation" class="btn btn-primary pull-right"
						value="<%=UserRegistrationCtl.OP_RESET%>">
				</form>
			</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>