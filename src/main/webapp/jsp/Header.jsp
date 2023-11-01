<%@page import="in.co.insurance.mgt.ctl.IMSView"%>
<%@page import="in.co.insurance.mgt.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
	integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
	integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	$(function() {
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			defaultDate : '01/01/1995'
		});
	});

	$(function() {
		$("#datepicker1").datepicker({
			changeMonth : true,
			changeYear : true
		});
	});
</script>

 <script language="javascript">
$(function(){
	$("#selectall").click(function () {
		  $('.case').attr('checked', this.checked);
	});
	$(".case").click(function(){

		if($(".case").length == $(".case:checked").length) {
			$("#selectall").attr("checked", "checked");
		} else {
			$("#selectall").removeAttr("checked");
		}

	});
});

</script>
</head>
<body>
	<%
	UserBean userBean = (UserBean) session.getAttribute("user");

	boolean userLoggedIn = userBean != null;

	String welcomeMsg = "Hi, ";

	if (userLoggedIn) {
		welcomeMsg += userBean.getFirstName();
	} else {
		welcomeMsg += "Guest";
	}
	%>
	<nav class="navbar navbar-expand-lg"
		style="height: 71px; border: groove; border-color: burlywood; background-color: aliceblue;">
		<div class="container-fluid">
			<a class="navbar-brand " href="#"
				style="font-size: 32px; font-family: serif; color: black;">Insurance
				Management</a>

			<div class="collapse navbar-collapse" id="navbarText">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">

				</ul>
				<span class="navbar-text"> <%=welcomeMsg%>
				</span>
			</div>
		</div>
	</nav>
	<div class="shadow  mb-5 bg-body rounded">
		<nav class="navbar navbar-expand-lg "
			style="height: 45px; background-color: #18334f;">
			<div class="container-fluid">
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link active link-light"
							aria-current="page" href="<%=IMSView.WELCOME_CTL%>">Home</a></li>
						<%
						if (userLoggedIn) {
						%>
						<%-- <li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle link-light" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">
								Category </a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="<%=IMSView.CATEGORY_CTL%>">Add Category</a></li>
								<li><a class="dropdown-item" href="<%=IMSView.CATEGORY_LIST_CTL%>">Category List</a></li>
							</ul></li> --%>
						<%
						if (userBean.getRoleId() == 1) {
						%>
						<li class="nav-item"><a class="nav-link link-light" href="<%=IMSView.USER_LIST_CTL%>">User List</a></li>
						<li class="nav-item"><a class="nav-link link-light" href="<%=IMSView.CATEGORY_CTL%>">Add Category</a>
							<li class="nav-item"><a class="nav-link link-light" href="<%=IMSView.CATEGORY_LIST_CTL%>">Category List</a>
							
							<li class="nav-item"><a class="nav-link link-light" href="<%=IMSView.SUB_CATEGORY_CTL%>">Add SubCategory</a>
							<li class="nav-item"><a class="nav-link link-light" href="<%=IMSView.SUB_CATEGORY_LIST_CTL%>">SubCategory List</a>
							
							<li class="nav-item"><a class="nav-link link-light" href="<%=IMSView.POLICY_CTL%>">Add Policy</a>
							<li class="nav-item"><a class="nav-link link-light" href="<%=IMSView.POLICY_LIST_CTL%>">Policy List</a>
							
							<li class="nav-item"><a class="nav-link active link-light"
							aria-current="page" href="<%=IMSView.BUY_POLICY_LIST_CTL%>">Buy Policy</a></li>	
							
						</li>
						<%
						} else if (userBean.getRoleId() == 2) {
						%>
						<li class="nav-item"><a class="nav-link active link-light"
							aria-current="page" href="<%=IMSView.CATEGORIES_CTL%>">Categories</a></li>
						<li class="nav-item"><a class="nav-link active link-light"
							aria-current="page" href="<%=IMSView.SUB_CATEGORIES_CTL%>">SubCategories</a></li>
						<li class="nav-item"><a class="nav-link active link-light"
							aria-current="page" href="<%=IMSView.POLICYIES_CTL%>">Policy</a></li>	
						<li class="nav-item"><a class="nav-link active link-light"
							aria-current="page" href="<%=IMSView.BUY_POLICY_LIST_CTL%>">My Policy</a></li>	
						<%
						}
						%>
						<%
						}else{
						%>
						<li class="nav-item"><a class="nav-link active link-light"
							aria-current="page" href="<%=IMSView.CATEGORIES_CTL%>">Categories</a></li>
							<li class="nav-item"><a class="nav-link active link-light"
							aria-current="page" href="<%=IMSView.SUB_CATEGORIES_CTL%>">Sub Categories</a></li>
						<li class="nav-item"><a class="nav-link active link-light"
							aria-current="page" href="<%=IMSView.POLICYIES_CTL%>">Policy</a></li>
						<%} %>
					</ul>


				</div>
				<ul class="nav justify-content-end">
					<%
					if (userLoggedIn) {
					%>
					<li class="nav-item"><a class="nav-link link-light"
						href="<%=IMSView.MY_PROFILE_CTL%>">My Profile</a></li>
					<li class="nav-item"><a class="nav-link link-light"
						href="<%=IMSView.LOGIN_CTL%>?operation=logout">Logout</a></li>
					<%
					} else {
					%>
					<li class="nav-item"><a class="nav-link link-light"
						href="<%=IMSView.LOGIN_CTL%>">SignIn</a></li>
					<li class="nav-item"><a class="nav-link link-light"
						href="<%=IMSView.USER_REGISTRATION_CTL%>">SignUp</a></li>
					<%
					}
					%>
				</ul>
			</div>
		</nav>
	</div>
</body>
</html>