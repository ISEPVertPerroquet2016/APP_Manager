<%@page import="dao.DAOUtilitaire"%>
<%@page import="entities.GroupObject"%>
<%@page import="java.util.List"%>
<%@include file="headerResp.jsp" %>
 <link href="css/toggle.css" rel="stylesheet">
<div id="page-wrapper">
     <div class="row">
         <nav class="navbar navbar-default">
       		 <div class="container-fluid">
          
		         <div id="navbar" class="navbar-collapse collapse">
		          <ul class="nav navbar-nav">
		             <c:forEach items="${ sessionScope.families }" var="family" >
		             		<li><a href="" onclick="sendFamily('${ family.nameFamily }'); return false;">${ family.nameFamily }</a></li>
		             </c:forEach>                  
		          </ul>         
		         </div><!--/.nav-collapse -->
          
   			 </div><!--/.container-fluid -->
     	 </nav>
     	 	
     	 <div class="col-lg-12">
         	<form id ="skillSheetForm" role="form" action="SkillsSheet" method="post">
                <div class="form-group col-sm-6">
           			<% 
           				Utilisateur user = (Utilisateur)session.getAttribute( "user" );
            			List<Utilisateur> elevesSelected = null;
            			int groupSelected = 0;
                		if(request.getAttribute( "sheetEleves" ) != null)
                		{
                		    elevesSelected = (List<Utilisateur>) request.getAttribute( "sheetEleves" ); 
                		    groupSelected = elevesSelected.get( 0 ).getIdGroup(  ) ;
                		}
               		%>
                	<%	
           				if(!(DAOUtilitaire.ELEVE).equals( user.getType(  ) )){
   				 	%>
                       <select class="form-control" name="sheetGroup" onchange="document.getElementById('skillSheetForm').submit()"> 
                           		<option default disabled selected>Selectionner un groupe</option>
                           	<%                		
                           		List<GroupObject> groups = (List<GroupObject>) session.getAttribute( "sheetGroups" );                                             		
                           	 	for(GroupObject group: groups)
                           	 	{	 	    
                           	 	    if( group.getGroupID(  ) == groupSelected)
                           	 	    {
                  	 	     %>
          	 	     			<option selected><%=group.getGroupID(  )%></option>
                   	 	     <%    
                           	 	    }
                           	 	    else
                           	 	    {
                       	 	 %>
               	 	 			<option><%=group.getGroupID(  )%></option>
                            	 <%   
                           	 	    }
                           	 	}
                           	 %>                                                                                          
                         </select>
                           <% } %>
                                  
                </div> 
                <div class="form-group col-sm-6"> 
                    <select class="form-control" name="eleveSelected" onchange="document.getElementById('skillSheetForm').submit()">
                        <option default value="0">Selectionner un eleve</option>
                  	<%
                  		int userID = user.getUserID(  );
                  		int eleveSelectedID = -1;
                  		if(request.getAttribute( DAOUtilitaire.ELEVE_SELECTED ) != null)
                  		{
                  		    eleveSelectedID = (Integer) request.getAttribute( DAOUtilitaire.ELEVE_SELECTED );
                  		}
                  		
                  		if(elevesSelected != null)
                  		{
                  		    for(Utilisateur eleve: elevesSelected)
                      		{
                      			if( eleveSelectedID == eleve.getUserID(  )) 
                      			{
                      	  	%>
                 	 	     		<option selected value="<%=eleve.getUserID(  )%>"><%=eleveSelectedID + " - " + eleve.getFirstname(  ) + " " + eleve.getSurname(  )%></option>
                 	 	     <%
                      			}else
                      			{
                      			%>
                 	 	     		<option value="<%=eleve.getUserID(  )%>"><%=eleve.getUserID(  ) + " - " + eleve.getFirstname(  ) + " " + eleve.getSurname(  )%></option>
                 	 	     	<% 
                      			}
                      		} 
                  		}                              	
                  	%>
                   
                	</select>
                </div> 
                
               	   <div class="form-group col-sm-6"> <label class="switch">
    					<input class="switch-input" type="checkbox" />
    					<span class="switch-label" data-on="On" data-off="Off"></span> 
    					<span class="switch-handle"></span> 
    				</label>
    				</div>
                                   
               	<input type="hidden" id="familySelected" name="familySelected" value="${requestScope.familySelected.nameFamily}" />
       		</form>
         </div>
                
         <div class="col-lg-12">
            <h3 class="page-header"> ${requestScope.familySelected.description}</h3>              
         </div>
                
                <!-- /.col-lg-12 -->
                
            </div>
                      
            <!-- /.row -->
            
            <div class="panel-body">
          
          <div class="row">
                       <div class="table-responsive">
                  <% 
                  		if(request.getAttribute( DAOUtilitaire.FAMILY_SELECTED ) != null 
                  		&& (Integer) request.getAttribute( DAOUtilitaire.ELEVE_SELECTED ) > 0){
                  %>     
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>Nom de la comp�tence</th>
                                            <th>Observation de l'�quipe</th>
                                            <th>Observation individuelle</th>
                                      <%if(!DAOUtilitaire.ELEVE.equals( user.getType(  ) )){ %>  
                                            <th>Remarques</th>
                                      <%} %>  
                                            <th>Niveau</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.familySelected.skills}" var="skill" >
                                        	<c:set var="skillName" value="${skill.nameSkill}" scope="page" />
                                        	
	                                        <tr>
	                                            <td>${skill.nameSkill}</td>
	                                            <td>${requestScope.ficheCollectiveSelected[skillName]}</td>
	                                            <td>${requestScope.ficheSelected[skillName].observation}</td>
                                            <%if(!DAOUtilitaire.ELEVE.equals( user.getType(  ) )){ %>
	                                            <td></td>
                                            <%} %> 
	                                            <td>
		                                            <button type="button" class="btn btn-danger btn-circle">
		                                           	 	${requestScope.ficheSelected[skillName].niveau}
		                                            </button>
	                                            </td>
	                                        </tr>
                                        </c:forEach>
                                    
                                  
                                     
                                    </tbody>
                                </table>
                               <%} %>
                            </div>       
                     </div>
                  </div>
            <!-- /.row -->
            
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->
   
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="js/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="js/raphael-min.js"></script>
    <script src="js/morris.min.js"></script>
    <script src="js/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/sb-admin-2.js"></script>

	
<!--

//-->
	<script src="js/skillSheetJS.js"></script>
	
</body>

</html>
