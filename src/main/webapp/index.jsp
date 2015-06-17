<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored ="false" %>

<html>
<head>
<style type="text/css">
td {
	border-style: solid;
	border-width: 0.2em;
	border-color: green;
	color: purple;
}
thead {
	background-color: aqua;
}
table {
	background-color: yellow;
	
}
.numberInput{
	width: 25px;
}

</style>

</head>
<body>

 <form action="ActionRecherche" method="post">
 	<label>search by name : <input type="text" name="searchByName" value="${searchByName}"></label><br><br>
 	<label>Vitesse max entre : <input class="numberInput" type="text" name="minVitesse" value="${minVitesse}">
 					   <input class="numberInput" type="text" name="maxVitesse" value="${maxVitesse}">
 	</label><br>
 	<label>pagination : <input class="numberInput" type="text" name="size" value="${size}"></label><br><br>
	<c:forEach items="${facet}" var="term">
			<c:out value="${term.term}(${term.count})"></c:out><input type="checkbox" name="${term.term}" checked="${term.isCheked}"><br>
	</c:forEach>
	
	<button type="submit" name="search">Search</button><br><br><br>

<table><thead>
		<tr><td>Id</td>
		<td>Voiture</td>
		<td>MPG</td>
		<td>cylendres</td>
		<td>vitesseMax</td>
		<td>nbrChauveaux</td>
		<td>poid</td>
		<td>acceleration</td>
		<td>model</td>
		<td>origine</td>
		</tr>
		</thead>
<c:forEach items="${voitures}" var="v">
	<tr>
		<td><c:out value="${v.id}"></c:out></td> 
		<td><c:out value="${v.voiture}"></c:out></td>
		<td><c:out value="${v.MPG}"></c:out></td>
		<td><c:out value="${v.cylendres}"></c:out></td>
		<td><c:out value="${v.vitesseMax}"></c:out></td>
		<td><c:out value="${v.nbrChauveaux}"></c:out></td>
		<td><c:out value="${v.poid}"></c:out></td>
		<td><c:out value="${v.acceleration}"></c:out></td>
		<td><c:out value="${v.model}"></c:out></td>
		<td><c:out value="${v.origine}"></c:out></td>
	</tr>
</c:forEach>
 </table><br>

	<button type="submit" name="last">Last</button>
	<button type="submit" name="next">Next</button>
	
</form>

</body>
</html>
