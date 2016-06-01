<%@include file="headerResp.jsp" %>

   <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h3 class="page-header">Nouvelle compétence dans la famille "${familyName}" </h3>
               	</div>
			</div>
        
        <div class="panel-body col-sm-12 text-center">
        <form action="Skill" role="form" method="post">
           <div class="panel  panel-primary ">
               <div class="panel-heading"></div>
               <div class="panel-body">
                  <div class="form-group col-sm-4">
                       <label>Compétence</label>
                       <input class="form-control" name="name_skill">
                       <p>${requestScope.formSkill.erreurs.nameSkill}</p>
                 </div> 
                 <div class="form-group col-sm-4">
					   <label>Critères d'observation</label>
                       <input class="form-control" name="observation_test">
                 </div>    
                 <div class="form-group col-sm-4">
                  	   <label>Coefficient</label>
                       <input type="number" class="form-control" name="coefficient">
                  
               	</div>
                
             </div>    
            </div>
            <div class="panel  panel-danger ">
               <div class="panel-heading">Niveau basique</div>
               <div class="panel-body">
                  <div class="form-group col-sm-4">
                       <label>B:</label>
                       <input class="form-control" name="basic_skill">
                  </div>
                  <div class="form-group col-sm-4">
					   <label>Acquis si</label>
                       <textarea class="form-control" rows="3" name="basic_required"></textarea>
                   </div>
                   <div class="form-group col-sm-4">    
                 
                  	   <label>Non acquis si</label>
                       <textarea class="form-control" rows="3" name="basic_failed"></textarea>
                  
               </div>
                
             </div>    
            </div>
            
            <div class="panel  panel-danger ">
               <div class="panel-heading">Niveau intermédiaire</div>
               <div class="panel-body">
                  <div class="form-group col-sm-4">
                       <label>I:</label>
                       <input class="form-control" name="medium_skill">
                  </div>
                  <div class="form-group col-sm-4">
					   <label>Acquis si</label>
                       <textarea class="form-control" rows="3" name="medium_required"></textarea>
                   </div>
                   <div class="form-group col-sm-4">    
                 
                  	   <label>Non acquis si</label>
                       <textarea class="form-control" rows="3" name="medium_failed"></textarea>
                  
               </div>
                
             </div>    
            </div>
                 <input type="hidden" name="familyName" value='<c:out value="${familyName}"></c:out>'>       
            <input type="submit" value="valider" class="btn btn-primary"/>
         </form>
         <p>${requestScope.formSkill.resultat}</p>

         </div> 

             
    </div>


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

</body>

</html>
