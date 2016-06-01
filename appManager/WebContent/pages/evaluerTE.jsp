<%@include file="headerResp.jsp" %>

        <div id="page-wrapper">
            <div class="row">
            
         <nav class="navbar navbar-default">
        <div class="container-fluid">
          
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href=""></a></li><li><a href=""></a></li><li><a href=""></a></li>    
              <li class=""><a href="evaluercomm.jsp">Communication</a></li>
              <li class="active"><a href="evaluerTE.jsp">Travail en équipe</a></li>
              <li><a href="evaluercp.jsp">Conduite de projet</a></li>
              <li><a href="evaluercr.jsp">Conception, réalisation</a></li>
              <li><a href="evaluerpr.jsp">Professionnel responsable</a></li>
             
            </ul>
         
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
<div class="col-lg-12">
                    <h3 class="page-header">Agir en acteur dynamique et efficace dans une équipe</h3>
                </div>
                <div class="col-sm-6">
                    <form role="form" >
                                <div class="form-group col-sm-6">
                                            
                                            <select placeholder="Equipe" class="form-control">
                                              <option>Equipe 1</option>
                                               <option>Equipe 2</option>
                                            </select>
                                  
                                     </div> 
                                            <div class="form-group col-sm-6"> 
                                          <select id="select" onchange="view()" placeholder="Equipe" class="form-control">
                                              <option>Aucun</option>
                                              <option>Jean DUPONT</option>
                                               <option></option>
                                            </select>
                                          </div> 
                                   <div><a href="afficherTE.jsp">Afficher</a></div>
    
                               </form>
                </div>
                <!-- /.col-lg-12 -->
            </div>
      <div id="eleve">      <!-- /.row -->
        <div class="panel-body col-sm-6">
          <form role="form">
            <div class="panel  panel-primary ">
                        <div class="panel-heading">
                            Participer à la vie de l'équipe
                        </div>
                          <div class="panel-body">
                           <div class="form-group col-sm-6">
                             <label>Observation de l'élève</label>
                              <textarea class="form-control" rows="3" placeholder="observation"></textarea>
                            </div>
                            
                            <!-- ///////////////////////-->
                             <div class="form-group">
                                    <label>Niveau de l'élève</label>
                                    <div class="radio">
                                        <label>
                                           <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>NA
                                        </label>
                                          
                                        <label>
                                            <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">B-
                                        </label>
                                          
                                    
                                        <label>
                                          <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">B+
                                        </label>
                                                 
                                        <label>
                                           <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">I-
                                        </label>
                                                 
                                        <label>
                                           <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">I+
                                        </label>
                                            </div>
                               </div>

                          </div>
                </div>
                   
         
                </div>
                  <div class="panel-body col-sm-6">
        
            <div class="panel  panel-primary ">
                        <div class="panel-heading">
                            Animer une équipe et la motiver
                        </div>
                          <div class="panel-body">
                           <div class="form-group col-sm-6">
                             <label>Observation de l'élève</label>
                              <textarea class="form-control" rows="3" placeholder="observation"></textarea>
                            </div>

                             <div class="form-group">
                                    <label>Niveau de l'élève</label>
                                    <div class="radio">
                                        <label>
                                           <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>NA
                                        </label>
                                          
                                        <label>
                                            <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">B-
                                        </label>
                                          
                                    
                                        <label>
                                          <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">B+
                                        </label>
                                                 
                                        <label>
                                           <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">I-
                                        </label>
                                                 
                                        <label>
                                           <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">I+
                                        </label>
                                            </div>
                               </div>
                          </div>
                </div>
                   
          
                </div>
          <div class="panel-body col-sm-6">
         
            <div class="panel  panel-primary ">
                        <div class="panel-heading">
                            Gérer les conflits, la diversité et les différences
                        </div>
                          <div class="panel-body">
                           <div class="form-group col-sm-6">
                             <label>Observation de l'élève</label>
                              <textarea class="form-control" rows="3" placeholder="observation"></textarea>
                            </div>

                             <div class="form-group">
                                    <label>Niveau de l'élève</label>
                                    <div class="radio">
                                        <label>
                                           <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>NA
                                        </label>
                                          
                                        <label>
                                            <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">B-
                                        </label>
                                          
                                    
                                        <label>
                                          <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">B+
                                        </label>
                                                 
                                        <label>
                                           <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">I-
                                        </label>
                                                 
                                        <label>
                                           <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">I+
                                        </label>
                                            </div>
                               </div>
                          </div>
                </div>
                   
        
                </div>  
                <div class="panel-body col-sm-6">
        
            <div class="panel  panel-primary ">
                        <div class="panel-heading">
                            être force de proposition
                        </div>
                          <div class="panel-body">
                           <div class="form-group col-sm-6">
                             <label>Observation de l'élève</label>
                              <textarea class="form-control" rows="3" placeholder="observation"></textarea>
                            </div>

                             <div class="form-group">
                                    <label>Niveau de l'élève</label>
                                    <div class="radio">
                                        <label>
                                           <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>NA
                                        </label>
                                          
                                        <label>
                                            <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">B-
                                        </label>
                                          
                                    
                                        <label>
                                          <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">B+
                                        </label>
                                                 
                                        <label>
                                           <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">I-
                                        </label>
                                                 
                                        <label>
                                           <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">I+
                                        </label>
                                            </div>
                               </div>
                          </div>
                </div>
                   <button type="button" class="btn btn-primary">Valider</button>
               
          </form>
                </div> 

              </div>
              <div  id="equipe"></div>
              </div></div>


              <!-- -->
                     
            <!-- /.row -->
            
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="../bower_components/raphael/raphael-min.js"></script>
    <script src="../bower_components/morrisjs/morris.min.js"></script>
    <script src="js/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>

</body>

</html>
  <script type="text/javascript"> 

 function view() { 
   if (document.getElementById("select").value=="Jean DUPONT") { 
     /*document.getElementById("equipe").st€‹yle.display="block"; */
  document.getElementById("eleve").st€‹yle.display= "none";
    
      
    }  /*else { 
      document.getElementById("eleve").s€‹yle.display="block";
     document.getElementById("equipe").stâ€‹yle.display="none";
       
   } */} 

 </script> 