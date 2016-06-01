<%@include file="headerResp.jsp" %>
<div id="page-wrapper">
            <div class="row">
         <nav class="navbar navbar-default">
        <div class="container-fluid">
          
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href=""></a></li><li><a href=""></a></li><li><a href=""></a></li>    
              <li><a href="affichercomm.jsp">Communication</a></li>
              <li><a href="afficherTE.jsp">Travail en équipe</a></li>
              <li><a href="affichercp.jsp">Conduite de projet</a></li>
              <li class="active"><a href="affichercr.jsp">Conception, réalisation</a></li>
              <li><a href="afficherpr.jsp">Professionnel responsable</a></li>
             
            </ul>
         
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
      <div class="col-lg-12">
                    <h3 class="page-header"> Concevoir et réaliser une application informatique (site web)</h3>
                </div>
                <div class="col-sm-4">
                    <form role="form" >
                                <div class="form-group col-sm-6">
                                            
                                            <select placeholder="Equipe" class="form-control">
                                              <option>Equipe 1</option>
                                               <option>Equipe 2</option>
                                            </select>
                                  
                                     </div> 
                                            <div class="form-group col-sm-6"> 
                                            <select placeholder="Equipe" class="form-control">
                                              <option>Jean DUPONT</option>
                                               <option></option>
                                            </select>
                                          </div> 
                                    <div><a href="evaluercr.jsp">Editer</a></div>
    
                               </form>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="panel-body">
          
          <div class="row">
                       <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>Observation de l'équipe</th>
                                            <th>Observation individuelle</th>
                                            <th>Remarques</th>
                                            <th>Niveau</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>Spécification des besoins (MOA)</td>
                                            <td>Observation</td>
                                            <td>Observation</td>
                                            <td>Remarque</td>
                                            <td><button type="button" class="btn btn-danger btn-circle">B
                            </button></td>
                                        </tr>
                                        <tr>
                                            <td>Conception visuelle (MOE)</td>
                                            <td>Observation</td>
                                            <td>Observation</td>
                                            <td>Remarque</td>
                                            <td><button type="button" class="btn btn-warning btn-circle">B
                            </button></td>
                                        </tr>
                                        <tr>
                                            <td>Conception des données (MOE) </td>
                                            <td>Observation</td>
                                            <td>Observation</td>
                                            <td>Remarque</td>
                                            <td><button type="button" class="btn btn-success btn-circle">I
                            </button></td>
                                        </tr>
                                        <tr>
                                            <td> Développement de l'application (MOE) : HTML et CSS</td>
                                            <td>Observation</td>
                                            <td>Observation</td>
                                            <td>Remarque</td>
                                            <td><button type="button" class="btn btn-success btn-circle">I
                            </button></td>
                                      </tr>
                                      <tr>
                                            <td> Développement de l'application (MOE) : PHP</td>
                                            <td>Observation</td>
                                            <td>Observation</td>
                                            <td>Remarque</td>
                                            <td><button type="button" class="btn btn-success btn-circle">I
                            </button></td>
                                      </tr>
                                      <tr>
                                            <td>  Développement de l'application (MOE) : SQL</td>
                                            <td>Observation</td>
                                            <td>Observation</td>
                                            <td>Remarque</td>
                                            <td><button type="button" class="btn btn-success btn-circle">I
                            </button></td>
                                      </tr>
                                      <tr>
                                            <td>Développement de l'application (MOE) : JavaScript</td>
                                            <td>Observation</td>
                                            <td>Observation</td>
                                            <td>Remarque</td>
                                            <td><button type="button" class="btn btn-success btn-circle">I
                            </button></td>
                                      </tr>
                                      <tr>
                                            <td>Organisation du code (MOE)</td>
                                            <td>Observation</td>
                                            <td>Observation</td>
                                            <td>Remarque</td>
                                            <td><button type="button" class="btn btn-success btn-circle">I
                            </button></td>
                                      </tr>
                                      <tr>
                                            <td> Test et validation (MOE)</td>
                                            <td>Observation</td>
                                            <td>Observation</td>
                                            <td>Remarque</td>
                                            <td><button type="button" class="btn btn-success btn-circle">I
                            </button></td>
                                      </tr>
                                       <tr>
                                            <td> Déploiement (MOE)</td>
                                            <td>Observation</td>
                                            <td>Observation</td>
                                            <td>Remarque</td>
                                            <td><button type="button" class="btn btn-success btn-circle">I
                            </button></td>
                                      </tr>
                                    </tbody>
                                </table>
                            </div>       
                     </div>
                     </div>
            <!-- /.row -->
            
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
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

</body>

</html>
