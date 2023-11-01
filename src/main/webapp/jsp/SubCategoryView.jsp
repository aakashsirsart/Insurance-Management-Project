<%@page import="in.co.insurance.mgt.ctl.SubCategoryCtl"%>
<%@page import="java.util.List"%>
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
<title>Sub Category</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div class="container">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="<%=IMSView.WELCOME_CTL%>">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">Sub Category</li>
			</ol>
		</nav>

		<div class="card shadow  mb-5 bg-body rounded" style="width: 800px">
			<h5 class="card-header"
				style="height: 70px; font-size: 25px; background-color: #18334f; color: white;">Sub Category</h5>
			<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
			</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
			</font></b>
			<div class="card-body">
				<form role="form" action="<%=IMSView.SUB_CATEGORY_CTL%>"
					method="post" enctype="multipart/form-data">

					<jsp:useBean id="bean" class="in.co.insurance.mgt.bean.SubCategoryBean"
						scope="request"></jsp:useBean>

					 <input
						type="hidden" name="id" value="<%=bean.getId()%>"> <input
						type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
					<input type="hidden" name="modifiedBy"
						value="<%=bean.getModifiedBy()%>"> <input type="hidden"
						name="createdDatetime"
						value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
					<input type="hidden" name="modifiedDatetime"
						value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
					
					<% List list=(List)request.getAttribute("categoryList");%>
					
					<div class="mb-3">
						<label class="form-label">Name</label> 
						<%=HTMLUtility.getList("category",String.valueOf(bean.getCategoryId()),list)%>
						<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("category", request)%></div>
					</div>

					<div class="mb-3">
						<label class="form-label">Name</label> <input type="text"
							class="form-control" name="name" placeholder="Enter Name..."
							value="<%=DataUtility.getStringData(bean.getName())%>">
						<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("name", request)%></div>
					</div>
					

					<div class="mb-3">
						<label class="form-label">Description</label> <textarea rows="4" cols="4" 
							class="form-control" name="description" placeholder="Enter Email Id"><%=DataUtility.getStringData(bean.getDescription())%></textarea>
						<div class="form-text" style="color: red;"><%=ServletUtility.getErrorMessage("description", request)%></div>
					</div>

					<div class="mb-3">
						<label class="form-label">Image</label> <input type="file"
							class="form-control" name="image"
							placeholder="Enter Image"
							value="">
					</div>

					<input type="submit" name="operation"
						class="btn btn-primary pull-right"
						value="<%=SubCategoryCtl.OP_SAVE%>"> <input
						type="submit" name="operation" class="btn btn-primary pull-right"
						value="<%=SubCategoryCtl.OP_RESET%>">
				</form>
			</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>