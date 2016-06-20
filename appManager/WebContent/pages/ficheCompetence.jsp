<%@page import="dao.DAOUtilitaire"%>
<%@page import="entities.GroupObject"%>
<%@page import="java.util.List"%>
<%@include file="header.jsp" %>
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
     	 
	     	  <div class="lg-12">
	            <h3 class="page-header"> ${requestScope.familySelected.description}</h3>              
	         </div>
	         
   	 	 <form id ="skillSheetForm" role="form" action="SkillsSheet" method="post">
   	 	 
   	 	 <input type="hidden" id="familySelected" name="familySelected" value="${requestScope.familySelected.nameFamily}" />
     	 <div class="lg-12">
         	
                <div class="form-group col-sm-4">
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
                       <select class="form-control" name="sheetGroup" onchange="changeGroup()"> 
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
                <div class="form-group col-sm-4"> 
                    <select id="selectEleve" class="form-control" name="eleveSelected" onchange="document.getElementById('skillSheetForm').submit()">
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
              </div>
                <%
                	String userType = ((Utilisateur)session.getAttribute( DAOUtilitaire.USER )).getType(); 
                	if(DAOUtilitaire.PROFESSEUR.equals( userType ) || DAOUtilitaire.RESPONSABLE.equals( userType ) ){
                %>
               	   <div class="form-group col-sm-4"> 
	               	   <label class="switch">
	    					<input id="toggleFiche" class="switch-input" type="checkbox" onclick="switchFiche()"/>
	    					<span class="switch-label" data-on="Fiche" data-off="edit"></span> 
	    					<span class="switch-handle"></span> 
	    				</label>
    				</div>
                                              	
               	
               	 <div class="col-lg-12" id="editFiche" style="display:none">
               		<div  class="panel-body" >
           	<% 
           		List<Utilisateur> eleves = null;
           		if(request.getAttribute( DAOUtilitaire.SHEET_ELEVES) != null)
           		{
           			eleves = (List<Utilisateur>) request.getAttribute( DAOUtilitaire.SHEET_ELEVES);
           		}
           		
	       		if(request.getAttribute( DAOUtilitaire.FAMILY_SELECTED ) != null 
	       		&& ( (Integer) request.getAttribute( DAOUtilitaire.ELEVE_SELECTED ) > 0) || ( eleves != null && !eleves.isEmpty(  ) && eleves.get(0).getIdGroup(  ) > 0  )){
            %>
           		<c:forEach items="${requestScope.familySelected.skills}" var="skill" varStatus="boucle">
           			<c:set var="skillName" value="${skill.nameSkill}" scope="page" />
	               <div class="panel-body col-sm-6">
        
            			<div class="panel  panel-primary ">
                        	<div class="panel-heading">${skill.nameSkill}</div>
                          	<div class="panel-body"> 
                          		<%
                          			if( (Integer) request.getAttribute( DAOUtilitaire.ELEVE_SELECTED ) > 0 ){
                          		%>
                           		<div class="form-group col-sm-6">
                             		<label>Observation de l'élève</label>
                              		<textarea class="form-control" rows="3" name="observationEleveUpdated<c:out value="${skill.nameSkill}"></c:out>" placeholder="observation" style="resize:none;">${requestScope.ficheSelected[skillName].observation}</textarea>
                              		
                              		<input id="observationEleve" type="hidden" name="observationEleve<c:out value="${skill.nameSkill}"></c:out>" value="${requestScope.ficheSelected[skillName].observation}">
                            	</div>                           	
                       			<div class="form-group">
                                    <label>Niveau de l'élève</label>
                                                                     
	                                 <div class="radio">
	                                 
	                                 	  <label>
		                                    <input type="radio" name="niveauRadioUpdated<c:out value="${skill.nameSkill}"></c:out>" value="NN" checked>NN
		                                  </label>
	                                 
		                                  <label>
		                                    <input type="radio" name="niveauRadioUpdated<c:out value="${skill.nameSkill}"></c:out>" id="NA<c:out value="${boucle.count}"></c:out>" value="NA">NA
		                                  </label>
		                                          
		                                  <label>
		                                   	<input type="radio" name="niveauRadioUpdated<c:out value="${skill.nameSkill}"></c:out>" id="B-<c:out value="${boucle.count}"></c:out>" value="B-">B-
		                                   </label>
		                                          
		                                    
		                                   <label>
		                                     <input type="radio" name="niveauRadioUpdated<c:out value="${skill.nameSkill}"></c:out>" id="B+<c:out value="${boucle.count}"></c:out>" value="B+">B+
		                                   </label>
		                                                 
		                                   <label>
		                                      <input type="radio" name="niveauRadioUpdated<c:out value="${skill.nameSkill}"></c:out>" id="I-<c:out value="${boucle.count}"></c:out>" value="I-">I-
		                                   </label>
		                                            
		                                   <label>
		                                      <input type="radio" name="niveauRadioUpdated<c:out value="${skill.nameSkill}"></c:out>" id="I+<c:out value="${boucle.count}"></c:out>" value="I+">I+
		                                   </label>
		                                   
		                                    <input id="niveauRadio<c:out value="${boucle.count}"></c:out>" type="hidden" name="niveauRadio<c:out value="${skill.nameSkill}"></c:out>" value="${requestScope.ficheSelected[skillName].niveau}">
		                                    
	                                  </div>
                                                                    	   
                           		</div>
                           		<%
	       							}else{
	       							    
                          		%>
                          		
                          		<div class="form-group col-sm-6">
                             		<label>Observation de l'équipe</label>
                              		<textarea class="form-control" rows="3" name="observationCollectiveUpdated<c:out value="${skill.nameSkill}"></c:out>" placeholder="observation" style="resize:none;">${requestScope.ficheCollectiveSelected[skillName]}</textarea>
                              		
                              		<input id="observationCollective" type="hidden" name="observationCollective<c:out value="${skill.nameSkill}"></c:out>" value="${requestScope.ficheCollectiveSelected[skillName]}">
                            	</div>
                          		
                          		<%
	       							}       							    
                         		%>

                          </div>
                </div>
                   
         
                </div>
                </c:forEach>               
            
            	</div>
            	<div class="panel-body" align="center">
            		<input name="validerFiche" value="Valider" type="submit" class="btn btn-primary btn-lg">
            	</div>
            	<%
	       		}  
           		%>
            
               	</div>
               <%
	       		}  
            	%>	              	
       		</form>
         
                
        
            </div>
                      
            <!-- /.row -->
            
            <div id="fiche" class="panel-body">
          
          <div class="row">
                       <div class="table-responsive">
                  <% 
                  	List<Utilisateur> eleves = null;
             		if(request.getAttribute( DAOUtilitaire.SHEET_ELEVES) != null)
             		{
             			eleves = (List<Utilisateur>) request.getAttribute( DAOUtilitaire.SHEET_ELEVES);
             		}
                  
                  		if(request.getAttribute( DAOUtilitaire.FAMILY_SELECTED ) != null 
                  		&& ( (Integer) request.getAttribute( DAOUtilitaire.ELEVE_SELECTED ) > 0 || ( eleves != null && !eleves.isEmpty(  ) && eleves.get(0).getIdGroup(  ) > 0  ) )){
                  %>     
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>Nom de la compétence</th>
                                            <th>Observation de l'équipe</th>
                                       <%
                                       		if( request.getAttribute( DAOUtilitaire.ELEVE_SELECTED )!= null && (Integer) request.getAttribute( DAOUtilitaire.ELEVE_SELECTED ) > 0){
                                       %>
                                            <th>Observation individuelle</th>                                                                                                                             
                                            <th>Niveau</th>
                                       <%
                                       		}
                                       %>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.familySelected.skills}" var="skill" >
                                        	<c:set var="skillName" value="${skill.nameSkill}" scope="page" />
                                        	
	                                        <tr>
	                                            <td>${skill.nameSkill}</td>
	                                            <td>${requestScope.ficheCollectiveSelected[skillName]}</td>
                                       <%
                                       		if( request.getAttribute( DAOUtilitaire.ELEVE_SELECTED )!= null && (Integer) request.getAttribute( DAOUtilitaire.ELEVE_SELECTED ) > 0){
                                       %>
	                                            <td>${requestScope.ficheSelected[skillName].observation}</td> 
	                                            <td>
		                                            <button type="button" class="btn btn-danger btn-circle">
		                                            <c:choose>
		                                            	<c:when test="${requestScope.ficheSelected[skillName].niveau == null}">
		                                            	NN
		                                            	</c:when>
		                                            	<c:otherwise>
		                                            	${requestScope.ficheSelected[skillName].niveau }
		                                            	</c:otherwise>
		                                            
		                                            </c:choose>
		                                            
		                                           	 	
		                                            </button>
	                                            </td>
                                      <%
                                      		}
                                       %>
	                                        </tr>
                                        </c:forEach>
                                    
                                  
                                     
                                    </tbody>
                                </table>
                               <%} %>
                            </div>       
                     </div>
                  </div>
           
</div>
      

    <!-- jQuery -->
    <script src="js/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/scriptApp.js"></script>


	
	<script>document.getElementById("body").onload = function() {selectNiveau()};</script>
	<script src="js/skillSheetJS.js"></script>
	
	
	
</body>

</html>
