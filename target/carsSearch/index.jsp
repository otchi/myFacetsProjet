<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List,org.amine.index.Voiture"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<body>
<form action="ActionRecherche" method="post">
	<button type="submit">rechercher tout</button>
</form>

<% int i=0; %>
<c:out value="${i}"></c:out>
</body>
</html>
