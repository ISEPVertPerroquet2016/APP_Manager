<%@include file="headerResp.jsp" %>

   <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h3 class="page-header">Nouveau groupe</h3>
               	</div>
			</div>
        
        <div class="panel-body col-sm-12 text-center">
        <form action="Group" role="form" method="POST">
           <div class="panel  panel-primary ">
               <div class="panel-heading"></div>
               <div class="panel-body">
                  <div class="form-group col-sm-6">
                       <label>Groupe:</label>
                       <input name="groupe"  class="form-control"><p>${requestScope.formFamily.erreurs.nameFamily}</p>
                 </div> 
                 <div class="form-group col-sm-6">
					   <label>Tuteur</label>
                       <select class="form-control" name="tuteur">
                       <option>Prof</option>
                       
                       </select>
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
