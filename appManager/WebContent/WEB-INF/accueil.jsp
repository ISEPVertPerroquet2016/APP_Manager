<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% String nom = (String)session.getAttribute( "name" ); 
		if(nom == null) 
		{
		    nom = "echec";
		}
		
		
	%>
	<h1>Vous êtes connectés: bienvenue ${ utilisateur.nom }</h1>
</body>
</html>
