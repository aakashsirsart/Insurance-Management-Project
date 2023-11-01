<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
		<div class="row">
			<div class="col">
				<div id="carouselExampleControls" class="carousel slide"
					data-bs-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="/Insurance-Management/image/new-bg-2.jpg" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="/Insurance-Management/image/insurance-1.jpg" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="/Insurance-Management/image/4.jpg" class="d-block w-100" alt="...">
						</div>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselExampleControls" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselExampleControls" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</div>
<hr>

			<div class="row">
				<div class="col">
				<div class="container">
					<br>
					<div class="about-section paddingTB60 gray-bg">
						<div class="row">
							<div class="col-md-7 col-sm-6">
								<div class="about-title clearfix">
									<h1>About Us</h1>
									<h3>Lorem ipsum dolor sit amet</h3>
									<p class="about-paddingB">Lorem ipsum dolor sit amet,
										consectetur adipiscing elit. Donec aliquet dolor libero, eget
										venenatis mauris finibus dictum. Vestibulum quis elit eget
										neque porttitor congue non sit amet dolor. Proin pretium purus
										a lorem ornare</p>
									<p>sed lobortis pulvinar. Integer laoreet mi id eros porta
										euismod. Suspendisse potenti. Nulla eros mauris, convallis et
										sem tempus, viverra hendrerit sapien</p>

								</div>
							</div>
							<div class="col-md-5 col-sm-6">
								<div class="about-img">
									<img src="https://devitems.com/preview/appmom/img/mobile/2.png"
										alt="">
								</div>
							</div>
						</div>
					</div>
				</div>
				</div>

			</div>


		</div>
	<hr>
		<div class="row">
			<div class="col">
				<br>
				<div class="jumbotron jumbotron-sm">
					<div class="container">
						<div class="row">
							<div class="col-sm-12 col-lg-12">
								<h1 class="h1">
									Contact us <small>Feel free to contact us</small>
								</h1>
							</div>
						</div>
					</div>
				</div>
				<br>
				<div class="container">
					<div class="row">
						<div class="col-md-8">
							<div class="well well-sm">
								<form>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label for="name"> Name</label> <input type="text"
													class="form-control" id="name" placeholder="Enter name"
													required="required" />
											</div>
											<div class="form-group">
												<label for="email"> Email Address</label>
												<div class="input-group">
													<span class="input-group-addon"><span
														class="glyphicon glyphicon-envelope"></span> </span> <input
														type="email" class="form-control" id="email"
														placeholder="Enter email" required="required" />
												</div>
											</div>
											<div class="form-group">
												<label for="subject"> Subject</label> <select id="subject"
													name="subject" class="form-control" required="required">
													<option value="na" selected="">Choose One:</option>
													<option value="service">General Customer Service</option>
													<option value="suggestions">Suggestions</option>
													<option value="product">Product Support</option>
												</select>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label for="name"> Message</label>
												<textarea name="message" id="message" class="form-control"
													rows="9" cols="25" required="required"
													placeholder="Message"></textarea>
											</div>
										</div>
										<div class="col-md-12">
											<button type="submit" class="btn btn-primary pull-right"
												id="btnContactUs">Send Message</button>
										</div>
									</div>
								</form>
							</div>
						</div>
						<div class="col-md-4">
							<form>
								<legend>
									<span class="glyphicon glyphicon-globe"></span> Our office
								</legend>
								<address>
									<strong>Twitter, Inc.</strong><br> 795 Folsom Ave, Suite
									600<br> San Francisco, CA 94107<br> <abbr
										title="Phone"> P:</abbr> (123) 456-7890
								</address>
								<address>
									<strong>Full Name</strong><br> <a href="mailto:#">first.last@example.com</a>
								</address>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>


<br>

	<%@ include file="Footer.jsp"%>
</body>
</html>