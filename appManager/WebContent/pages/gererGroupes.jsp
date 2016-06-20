<%@include file="header.jsp" %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="page-header">Groupes  <a href=""><p class="fa fa-plus-circle"></p></a></h2>
                </div>
               
            </div>
            <!-- Famille -->
            <c:forEach items="${ requestScope.families }" var="family" varStatus="status">
            <div class="row">
          <div class="col-lg-4 col-md-6">
                   <h3><p class="fa fa-chevron-circle-down" ></p> ${ family.nameFamily } 
                   <a href="Skill?nameFamily=${ family.nameFamily }"><p class="fa fa-plus-circle"></p></a></h3>
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
    

    <!-- jQuery -->
    <script src="js/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/scriptApp.js"></script>
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
