<%@include file="header.jsp" %>

   <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h3 class="page-header">Nouvelle famille de compétence </h3>
               	</div>
			</div>
        
        <div class="panel-body col-sm-12 text-center">
        <form action="Family" role="form" method="POST">
           <div class="panel  panel-primary ">
               <div class="panel-heading"></div>
               <div class="panel-body">
                  <div class="form-group col-sm-6">
                       <label>Famille</label>
                       <input name="family"  class="form-control"><p>${requestScope.formFamily.erreurs.nameFamily}</p>
                 </div> 
                 <div class="form-group col-sm-6">
					   <label>Description</label>
                       <textarea class="form-control" name="description" rows="3"></textarea>
                 </div>    
                
                
             </div>    
            </div>
      
            
            
            <input type="submit" value="valider" class="btn btn-primary"/>
         </form>
         <p>${requestScope.formFamily.resultat}</p>
         
        
         </div> 

             
    </div>


    <!-- jQuery -->
    <script src="js/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/scriptApp.js"></script>

</body>

</html>
