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

<title>Personeel - Werknemershiërarchy</title>

<!-- Bootstrap core CSS -->
<link href="${contextPath}/stylesheets/bootstrap.min.css"
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
					<li class="active"><a href="werknemershierarchy.htm"
						title="Werknemershiërarchie">Werknemershiërarchie</a></li>
					<li><a href="jobtitels.htm" title="Jobtitels">Jobtitels</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>

	<div class="container">

		<div class="starter-template">
			<h1>Werknemer ${werknemer.naam}</h1>
			<p>
				Voornaam<br /> <span class="bold">${werknemer.voornaam}</span>
			</p>
			<p>
				Familienaam<br /> <span class="bold">${werknemer.familienaam}</span>
			</p>
			<p>
				Email adres<br /> <span class="bold"><a href="mailto:${werknemer.email}">${werknemer.email}</a></span>
			</p>
			<p>
				Salaris<br /> <span class="bold"><fmt:formatNumber
						value="${werknemer.salaris}" /></span>
			</p>
			<p>
				Jobtitel<br /> <span class="bold">${werknemer.jobtitel.naam}</span>
			</p>
			<c:if test="${not empty werknemer.chef}">
				<p>
					Chef<br /> <span class="bold"> <c:url
							value="/werknemershierarchy.htm" var="id">
							<c:param name="werknemerID" value="${werknemer.chef.id}" />
						</c:url><a href="${id}"
						title="${werknemer.chef.voornaam}&nbsp;${werknemer.chef.familienaam}">${werknemer.chef.voornaam}&nbsp;${werknemer.chef.familienaam}</a></span>

				</p>
			</c:if>
			<c:if test="${not empty werknemer.werknemers}">
				<p>
					Ondergeschikten<br />
					<c:forEach var="werknemer" items="${werknemer.werknemers}">
						<c:url value="/werknemershierarchy.htm" var="id">
							<c:param name="werknemerID" value="${werknemer.id}" />
						</c:url>
						<span class="bold"><a href="${id}"
							title="${werknemer.voornaam}&nbsp;${werknemer.familienaam}">${werknemer.voornaam}&nbsp;${werknemer.familienaam}</a></span>
						<br />
					</c:forEach>
				</p>
			</c:if>
			<p>
				Foto<br /> <img src="${contextPath}/images/${werknemer.id}.jpg"
					alt="${werknemer.voornaam}&nbsp;${werknemer.familienaam}" />
			</p>
			<p>
				<c:url value="/opslag.htm" var="id">
					<c:param name="werknemerID" value="${werknemer.id}" />
				</c:url>
				<a href="${id}" title="Opslag">Opslag</a>
			</p>
		</div>

	</div>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="${contextPath}/js/bootstrap.min.js"></script>
</body>
</html>