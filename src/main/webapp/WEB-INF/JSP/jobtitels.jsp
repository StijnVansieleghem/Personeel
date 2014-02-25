<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'
	trimDirectiveWhitespaces="true"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<c:set var='contextPath'
	value='${pageContext.servletContext.contextPath}' />
<c:url value="index.htm" var="unshippedOrdersURL" />
<!doctype html>
<html lang='nl'>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Stijn Vansieleghem">

<title>Personeel - Jobtitels</title>

<!-- Bootstrap core CSS -->
<link href="${contextPath}/stylesheets//bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${contextPath}/stylesheets/starter-template.css"
	rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Personeel</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="index.jsp">Startpagina</a></li>
					<li><a href="werknemershierarchy.htm"
						title="Werknemershiërarchie">Werknemershiërarchie</a></li>
					<li class="active"><a href="jobtitels.htm" title="Jobtitels">Jobtitels</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>

	<div class="container">

		<div class="starter-template">
			<h1>Jobtitels</h1>
			<nav>
				<ul>
					<c:forEach var="jobtitel" items="${jobtitels}">
						<c:url value="/jobtitels.htm" var="id">
							<c:param name="jobtitelID" value="${jobtitel.id}" />
						</c:url>
						<li><a href="${id}" title="${jobtitel.naam}">${jobtitel.naam}</a></li>
					</c:forEach>
				</ul>
			</nav>
			<section>
				<c:if test="${not empty jobtitel}">
					<h2>
						<c:out value="${jobtitel.naam}" />
					</h2>
					<c:forEach var="werknemer" items="${jobtitel.werknemers}">
						<ul>
							<c:url value="/werknemershierarchy.htm" var="id">
								<c:param name="werknemerID" value="${werknemer.id}" />
							</c:url>
							<li><a href="${id}" title="${werknemer.naam}">${werknemer.naam}</a></li>
						</ul>
					</c:forEach>
				</c:if>
			</section>
		</div>

	</div>
	<!-- /.container -->

	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script>
		$('section').hide();
		$(document).ready(function() {
			$('section').fadeIn(1000);
		});
	</script>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="${contextPath}/js/bootstrap.min.js"></script>
</body>
</html>