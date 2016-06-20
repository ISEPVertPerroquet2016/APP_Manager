<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Styles css -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/indexConnexion.css" rel="stylesheet">
    
<div class="container">    
    <div id="loginbox" class="mainbox col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3"> 
    		
        <h1>APP Manager</h1>
       	<img alt="" src="images/Isep-Logo.png">  
       	
        <div class="panel panel-default" >
       		<div class="panel-body" >

                <form name="Connexion" id="form" class="form-horizontal" method="POST">
                   
                   <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input id="login" type="text" class="form-control" name="login" value="" placeholder="Login">                                        
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input id="password" type="password" class="form-control" name="password" placeholder="Password">
                    </div>                                                                  

                    <div class="form-group">
                    	<div class="col-sm-12 controls">
                            <button type="submit" class="btn btn-primary pull-right"><i class="glyphicon glyphicon-log-in"></i> Log in</button>                          
                        </div>
                    </div>

                </form>
                <p>${requestScope.formConnexion.erreurConnexion }</p>     
			</div>                     
        </div>  
    </div>
</div>

	<!------------ Script------------>
	
    <script src="js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>


