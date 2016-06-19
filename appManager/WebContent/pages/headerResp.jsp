<!DOCTYPE html>
<%@page import="dao.DAOUtilitaire"%>
<%@page import="entities.Utilisateur"%>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>APP Manager</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body id="body">

    <div id="wrapper">

        <!-- Navigation -->
        
        <nav class="navbar navbar-inverse navbar-static-top"style="margin-bottom: 0">
        	<img src="images/isep-issy.jpg" style="width:100%;" >
        </nav>
        
        
        <nav class="navbar navbar-inverse navbar-static-top" role="navigation" style="margin-bottom: 0">
                 
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
               	<a class="navbar-brand">APP Manager</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
        
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="">${sessionScope.user.firstname} ${sessionScope.user.surname}
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
					</a>
                    
                    <ul class="dropdown-menu dropdown-user">
                        <li class="divider"></li>
                        <li><a href="Deconnexion"><i class="fa fa-sign-out fa-fw"></i> Log out</a>
                        </li>
                    </ul>
                </li>
               
            </ul>
            
           
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                     
                         <% 
                         	Utilisateur util = (Utilisateur)session.getAttribute( "user" );
                         	String typeProf = util.getType(  );	
                         %>    
                         <% if( DAOUtilitaire.RESPONSABLE.equals( typeProf ) || DAOUtilitaire.PROFESSEUR.equals( typeProf ) ) { %>        	                       
                        	<li>
                            	<a href="Accueil"><i class="fa fa-dashboard fa-fw"></i> Accueil</a>
                        	</li>                                                    
                         <% } %>
                        <li>
                            <a href="SkillsSheet">Fiche de competences</a>
                        </li>
                        <li>
                            <a href="notes.jsp">Notes</a>
                        </li>
                           
                         <% if(DAOUtilitaire.RESPONSABLE.equals( typeProf )) { %>
                        	
                        	<li><a href="SkillManagement"><i class=""></i> Gestion de compétences</a></li>
                        	<li><a href="Group"><i class=""></i> Gestion de Groupes</a></li>
                        <% } %> 
                                                
                        <li><a href=""><i class="glyphicon glyphicon-file"></i> Documents</a></li>
                   </ul>
                </div>
               
            </div>
          
        </nav>

        