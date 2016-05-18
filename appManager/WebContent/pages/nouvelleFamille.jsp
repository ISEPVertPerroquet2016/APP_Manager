<%@include file="headerResp.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                       <input name="family"  class="form-control">
                 </div> 
                 <div class="form-group col-sm-6">
					   <label>Description</label>
                       <textarea class="form-control" rows="3"></textarea>
                 </div>    
                
                
             </div>    
            </div>
      
            
            
            <input type="submit" value="valider" class="btn btn-primary"/>
         </form>
         <p>${param.family}</p>
        
         </div> 

             
    </div>


    <!-- jQuery -->
    <script src="./../bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="./../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="./../bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="./../bower_components/raphael/raphael-min.js"></script>
    <script src="./../bower_components/morrisjs/morris.min.js"></script>
    <script src="./../js/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="./../dist/js/sb-admin-2.js"></script>

</body>

</html>
