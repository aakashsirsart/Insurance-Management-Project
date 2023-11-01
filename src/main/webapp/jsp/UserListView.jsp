<%@page import="in.co.insurance.mgt.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.insurance.mgt.bean.UserBean"%>
<%@page import="in.co.insurance.mgt.ctl.UserListCtl"%>
<%@page import="in.co.insurance.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User List</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<nav aria-label="breadcrumb" style="margin-left: 10px;">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="<%=IMSView.WELCOME_CTL%>">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">User List</li>
		</ol>
	</nav>
	<form action="<%=IMSView.USER_LIST_CTL%>" method="post">
		<div class="card" style="margin: 10px;">
			<h5 class="card-header"
				style="height: 50px; font-size: 25px; background-color: #18334f; color: white;">User
				List</h5>
			<div class="card-body">
				<div class="row">
					<div class="col">
						<input type="text" name="firstName" class="form-control"
							placeholder=" Search By First Name" aria-label="Search By Name"
							value="<%=ServletUtility.getParameter("firstName", request)%>">
					</div>
					<div class="col">
						<input type="text" name="email" class="form-control"
							placeholder=" Search By Email Id" aria-label="Search By Email"
							value="<%=ServletUtility.getParameter("email", request)%>">
					</div>
					<div class="col">
						<input type="submit" class="btn btn-primary btn btn-info"
							name="operation" value="<%=UserListCtl.OP_SEARCH%>">or<input
							type="submit" class="btn btn-primary btn btn-info"
							name="operation" value="<%=UserListCtl.OP_RESET%>">
					</div>
				</div>
				<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
				<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
				<br>

				<table class="table table-sm table-bordered">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">First Name</th>
							<th scope="col">Last Name</th>
							<th scope="col">Email</th>
							<th scope="col">Contact No</th>
							<th scope="col">Gender</th>
						</tr>
					</thead>
					<tbody>
						<%
						int pageNo = ServletUtility.getPageNo(request);
						int pageSize = ServletUtility.getPageSize(request);
						int index = ((pageNo - 1) * pageSize) + 1;
						int size = ServletUtility.getSize(request);
						UserBean bean = null;
						List list = ServletUtility.getList(request);
						Iterator<UserBean> it = list.iterator();
						while (it.hasNext()) {
							bean = it.next();
						%>
						<tr>
							<td scope="row"><%=index++%></td>
							<td scope="row"><%=bean.getFirstName()%></td>
							<td scope="row"><%=bean.getLastName()%></td>
							<td scope="row"><%=bean.getEmail()%></td>
							<td scope="row"><%=bean.getContactNo()%></td>
							<td scope="row"><%=bean.getGender()%></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
				<div class="clearfix">

					<nav aria-label="Page navigation example">
						<ul class="pagination justify-content-end">
							<li class="page-item disabled"><input type="submit"
								name="operation" class="page-link"
								value="<%=UserListCtl.OP_PREVIOUS%>"
								<%=(pageNo == 1) ? "disabled" : ""%>></li>
							<%
							int count = DataUtility.getInt(String.valueOf(size / 10));
							%>
							<%
							for (int i = 1; i <= count + 1; i++) {
							%>
							<%
							if (i == pageNo) {
							%>
							<li class="page-item active"><a class="page-link activate"
								href="<%=IMSView.USER_LIST_CTL%>?pageNo=<%=i%>"><%=i%></a></li>
							<%
							} else {
							%>
							<li class="page-item"><a class="page-link"
								href="<%=IMSView.USER_LIST_CTL%>?pageNo=<%=i%>"><%=i%></a></li>
							<%
							}
							%>
							<%
							}
							%>
							<li class="page-item"><input type="submit" name="operation"
								class="page-link" value="<%=UserListCtl.OP_NEXT%>"
								<%=((list.size() < pageSize) || size == pageNo * pageSize) ? "disabled" : ""%>></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>