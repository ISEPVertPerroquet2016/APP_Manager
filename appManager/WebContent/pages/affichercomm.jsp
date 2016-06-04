<%@include file="headerResp.jsp" %>
<div id="page-wrapper">
            <div class="row">
         <nav class="navbar navbar-default">
        <div class="container-fluid">
          
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
               <c:forEach items="${ requestScope.families }" var="family" >
               		<li><a href="">${ family.nameFamily }</a></li>
               </c:forEach>     
              
            </ul>
         
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
      <div class="col-lg-12">
                    <h3 class="page-header"> Agir en bon communicant dans un environnement scientifique et technique</h3>
                </div>
                <div class="col-sm-4">
                    <form role="form" >
                                <div class="form-group col-sm-6">
                                            
                                            <select placeholder="Equipe" class="form-control">
	                                            <c:forEach items="${ requestScope.groups }" var="group">
	                                            	<option>${group.groupID}</option>
	                                            </c:forEach>                                                                                           
                                            </select>
                                  
                                     </div> 
                                            <div class="form-group col-sm-6"> 
                                            <select placeholder="Equipe" class="form-control">
                                              <option>Jean DUPONT</option>
                                               <option></option>
                                            </select>
                                          </div> 
                                    <div><a href="evaluercomm.jsp">Editer</a></div>
    
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
                                            <td>Écouter et se faire écouter</td>
                                            <td>Observation</td>
                                            <td>Observation</td>
                                            <td>Remarque</td>
                                            <td><button type="button" class="btn btn-danger btn-circle">B
                            </button></td>
                                        </tr>
                                        <tr>
                                            <td>Dialoguer, argumenter et convaincre</td>
                                            <td>Observation</td>
                                            <td>Observation</td>
                                            <td>Remarque</td>
                                            <td><button type="button" class="btn btn-warning btn-circle">B
                            </button></td>
                                        </tr>
                                        <tr>
                                            <td>Communiquer à l'oral </td>
                                            <td>Observation</td>
                                            <td>Observation</td>
                                            <td>Remarque</td>
                                            <td><button type="button" class="btn btn-success btn-circle">I
                            </button></td>
                                        </tr>
                                        <tr>
                                            <td>Communiquer à l'écrit</td>
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
