<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		
		LDAPObject user = (LDAPObject)request.getAttribute( "utilisateur" );
	%>
	<h1>Vous �tes connect�s: bienvenu <%=nom%></h1>
</body>
</html>