<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.insurance.mgt.bean.CategoryBean"%>
<%@page import="in.co.insurance.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Categories</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div class="container">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="<%=IMSView.WELCOME_CTL%>">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">Categories</li>
			</ol>
		</nav>
		<form action="<%=IMSView.CATEGORIES_CTL%>" method="post">
			<div class="row row-cols-1 row-cols-md-3 g-4">
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
				<div class="col">
					<div class="card h-100">

						<a href="<%=IMSView.SUB_CATEGORIES_CTL%>?cId=<%=bean.getId()%>"><img
							height="240px" width="50px"
							src="<%=IMSView.APP_CONTEXT + IMSView.GET_IMAGE_VIEW%>?id=<%=bean.getId()%>&table=category"
							class="card-img-top" alt="..."></a>

						<div class="card-body">
							<h5 class="card-title"><%=bean.getName()%></h5>
						</div>
					</div>
				</div>
				<%
				}
				%>
			</div>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>
	</div>

	<br>
	<%@ include file="Footer.jsp"%>
</body>
</html>