<%@page import="in.co.insurance.mgt.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.insurance.mgt.bean.CategoryBean"%>
<%@page import="in.co.insurance.mgt.ctl.CategoryListCtl"%>
<%@page import="in.co.insurance.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Category List</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<nav aria-label="breadcrumb" style="margin-left: 10px;">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="<%=IMSView.WELCOME_CTL%>">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Category
				List</li>
		</ol>
	</nav>
<form action="<%=IMSView.CATEGORY_LIST_CTL%>" method="post">
	<div class="card" style="margin: 10px;">
		<h5 class="card-header"
			style="height: 50px; font-size: 25px; background-color: #18334f; color: white;">Category
			List</h5>
		<div class="card-body">
			<div class="row">
				<div class="col">
					<input type="text" name="name" class="form-control"
						placeholder=" Search By Name" aria-label="Search By Name"
						value="<%=ServletUtility.getParameter("name", request)%>">
				</div>
				<div class="col">
					<input type="submit" class="btn btn-primary btn btn-info"
						name="operation" value="<%=CategoryListCtl.OP_SEARCH%>">or<input
						type="submit" class="btn btn-primary btn btn-info"
						name="operation" value="<%=CategoryListCtl.OP_RESET%>">
				</div>
			</div>
			<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
			<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
			<br>

			<table class="table table-sm table-bordered">
				<thead>
					<tr>
						<th scope="col"><input type="checkbox" id="selectall">Select
							All</th>
						<th scope="col">#</th>
						<th scope="col">Image</th>
						<th scope="col">Name</th>
						<th scope="col">Description</th>
						<th scope="col">Edit</th>
					</tr>
				</thead>
				<tbody>
					<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					int size = ServletUtility.getSize(request);
					CategoryBean bean = null;
					List list = ServletUtility.getList(request);
					Iterator<CategoryBean> it = list.iterator();
					while (it.hasNext()) {
						bean = it.next();
					%>
					<tr>
						<td scope="row"><input type="checkbox" class="case"
							name="ids" value="<%=bean.getId()%>"></td>
						<td scope="row"><%=index++%></td>
						<td scope="row"><img
							src="<%=IMSView.APP_CONTEXT + IMSView.GET_IMAGE_VIEW%>?id=<%=bean.getId()%>&table=category"
							width="100px" height="100px"></td>
						<td scope="row"><%=bean.getName()%></td>
						<td scope="row"><%=bean.getDescription()%></td>
						<td><a href="<%=IMSView.CATEGORY_CTL%>?id=<%=bean.getId()%>"
										class="btn btn-primary btn btn-info">Edit</a></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
			<div class="clearfix">
			<input type="submit" name="operation"
								class="btn btn-primary btn btn-info float-start"
								value="<%=CategoryListCtl.OP_DELETE%>"
								<%=(list.size() == 0) ? "disabled" : ""%>>
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-end">
					<li class="page-item"><input type="submit" name="operation"
								class="page-link"
								value="<%=CategoryListCtl.OP_PREVIOUS%>"
								<%=(pageNo == 1) ? "disabled" : ""%>></li>
								
								<% int count=DataUtility.getInt(String.valueOf(size/10));%>
								<% for(int i=1;i<=count+1;i++){%>
								<% if(i==pageNo){%>	
								<li class="page-item active"><a class="page-link activate" href="<%=IMSView.CATEGORY_LIST_CTL%>?pageNo=<%=i%>"><%=i%></a></li>
								<%}else{%>
								<li class="page-item"><a class="page-link" href="<%=IMSView.CATEGORY_LIST_CTL%>?pageNo=<%=i%>"><%=i%></a></li>
								<%} %>
								<%}%>
								
					<li class="page-item"><input type="submit" name="operation"
								class="page-link"
								value="<%=CategoryListCtl.OP_NEXT%>"
								<%=((list.size() < pageSize) || size==pageNo*pageSize) ? "disabled" : ""%>></li>
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