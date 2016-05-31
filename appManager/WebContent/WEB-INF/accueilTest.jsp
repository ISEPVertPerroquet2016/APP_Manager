<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% 
		
		
	%>
	<h1>Vous êtes connectés: bienvenue ${sessionScope.user.surname}</h1>
	<p> le type est ${sessionScope.user.type}</p>
	<p> le numéro est ${sessionScope.user.userID}</p>
</body>
</html>
