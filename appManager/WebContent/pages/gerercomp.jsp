<%@include file="headerResp.jsp" %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="page-header"> familles de compétences  <a href="Family"><p class="fa fa-plus-circle"></p></a></h2>
                </div>
               
            </div>
            <!-- Famille -->
            <c:forEach items="${ requestScope.families }" var="family" varStatus="status">
            <div class="row">
          <div class="col-lg-4 col-md-6">
                   <h3><p class="fa fa-chevron-circle-down" >${ family.nameFamily }</p>                   		
                   <a href="Skill?nameFamily=${ family.nameFamily }"><p class="fa fa-plus-circle"></p></a></h3>
                   <p>Description famille: ${ family.description } </p>
                </div></div>
                
                <c:forEach items="${ family.skills }" var="skill" varStatus="boucle">
                
                <div class="row">
                <div class="col-lg-4 col-md-6">
                
                   <h5><p  id="fleche<c:out value="${status.count} "></c:out><c:out value="${boucle.count} "></c:out>" class="fa fa-chevron-down" onclick="test('block<c:out value="${status.count} "></c:out><c:out value="${boucle.count} "></c:out>','fleche<c:out value="${status.count} "></c:out><c:out value="${boucle.count} "></c:out>');"></p>${skill.nameSkill}<a href=""><p class="fa fa-trash-o"></p></a> <a href=""><p class="fa fa-edit"></p></a></h5>
                </div>
              </div>
             <div id="block<c:out value="${status.count} "></c:out><c:out value="${boucle.count} "></c:out>" style="display:none;"><b>Critère d'observation: </b>${skill.observationTest}<br>
                  <b> Coefficient:</b>${skill.coefficient}<br>
            <b>  B:</b> ${skill.basicSkill} <br><b>I</b>: ${skill.mediumSkill}
             </div>
             </c:forEach>
             
             </c:forEach>
            
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
	<script type="text/javascript">
function test(block,fleche){
	if(document.getElementById(block).style.display=="none"){
	document.getElementById(block).style.display="block";
	document.getElementById(fleche).className="fa fa-chevron-up";}
	else {
		document.getElementById(block).style.display="none";
		document.getElementById(fleche).className="fa fa-chevron-down";
	}
	
	}

</script>
		
        
</body>

</html>
