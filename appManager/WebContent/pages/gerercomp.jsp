<%@include file="headerResp.jsp" %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="page-header">compétences  <a href="nouvelleFamille.jsp"><p class="fa fa-plus-circle"></p></a></h2>
                </div>
               
            </div>
            <!-- Famille 1 -->
            <div class="row">
          <div class="col-lg-4 col-md-6">
                   <h3><p class="fa fa-chevron-circle-down" ></p> Travail en équipe <a href="nouvelleComp.jsp"><p class="fa fa-plus-circle"></p></a></h3>
                </div></div>
                <div class="row">
                <div class="col-lg-4 col-md-6">
                   <h5><p  id="fleche1" class="fa fa-chevron-down" onclick="test('block1','fleche1');"></p> Participer à la vie de l'équipe <a href=""><p class="fa fa-trash-o"></p></a> <a href=""><p class="fa fa-edit"></p></a></h5>
                </div>
              </div>
             <div id="block1" style="display:none;">Repérage au quotidien et lors des diverses présentations<br>
                  <b> Coefficient:</b> 4<br>
            <b>  B:</b> se montrer ouvert, collaboratif et participatif &nbsp <b>I</b>: agir avec coordination et entre-aide
             </div>
                
            <!-- Famille 2 -->
            
                <div class="row">
                <div class="col-lg-4 col-md-6">
                   <h5><p id="fleche2" class="fa fa-chevron-down" onclick="test('block2','fleche2');"></p>Aimer une équipe et la motiver <a href=""><p class="fa fa-trash-o"></p></a> <a href=""><p class="fa fa-edit"></p></a></h5>
                </div>
              </div>
             <div id="block2" style="display:none;">Repérage au quotidien et lors des diverses présentations<br>
                  <b> Coefficient:</b> 4<br>
            <b>  B:</b>animer en maintenant la cohésion de l'équipe et un minimum d'intérêt&nbsp <b>I:</b> motiver les membres de l'équipe
             </div>
            
            <!-- Famille 3 -->
            
            <div class="row">
                <div class="col-lg-6 col-md-6">
                   <h5><p id="fleche3" class="fa fa-chevron-down" onclick="test('block3','fleche3');"></p>Gérer les conflits, la diversité et les différences<a href=""><p class="fa fa-trash-o"></p></a> <a href=""><p class="fa fa-edit"></p></a></h5>
                </div>
              </div>
             <div id="block3" style="display:none;">observation au quotidien avec le cas échéant un suivi rapproché des conflits et leurs évolutions<br>
                  <b> Coefficient:</b> 3<br>
            <b>  B:</b> détecter les conflits et accepter la diversité et les différences&nbsp <b>I:</b> apporter des solutions aux conflits et s'ouvrir aux différences 
             </div>
             
             <!-- Famille 3 -->
            
            <div class="row">
                <div class="col-lg-6 col-md-6">
                   <h5><p id="fleche4" class="fa fa-chevron-down" onclick="test('block4','fleche4');"></p>Être force de proposition<a href=""><p class="fa fa-trash-o"></p></a> <a href=""><p class="fa fa-edit"></p></a></h5>
                </div>
              </div>
             <div id="block4" style="display:none;">observation lors des discussions sur les livrables et 
             											éventuellement lors des suivis au quotidien<br>
                  <b> Coefficient:</b> 3<br>
            <b>  B:</b> émettre une idée pertinente &nbsp <b>I:</b> la justifier et la défendre  
             </div>
             
             <!-- Famille 4 -->
        <div class="row">
          <div class="col-lg-4 col-md-6">
            <h3><p class="fa fa-chevron-circle-down" ></p> Communication <a href=""><p class="fa fa-plus-circle"></p></a></h3>
          </div>
        </div>
        
        <div class="row">
          <div class="col-lg-4 col-md-6">
             <h5><p  id="fleche21" class="fa fa-chevron-down" onclick="test('block21','fleche21');"></p>Écouter et se faire écouter<a href=""><p class="fa fa-trash-o"></p></a> <a href=""><p class="fa fa-edit"></p></a></h5>
          </div>
        </div>
        <div id="block21" style="display:none;">le tuteur peut écouter une discussion entre les apprenants portant sur les travaux en groupe, 
             										les difficultés ou les divergences rencontrées<br>
                  <b> Coefficient:</b> 4<br>
            <b>  B:</b>être disposé à l'écoute et être capable d'exposer son point de vue&nbsp <b>I</b>: admettre  que l'autre peut avoir raison et maintenir l'intérêt de son auditoire
        </div>
        
        
        <!-- Famille 4 -->
        
        <div class="row">
          <div class="col-lg-4 col-md-6">
             <h5><p  id="fleche22" class="fa fa-chevron-down" onclick="test('block22','fleche22');"></p>Dialoguer, argumenter et convaincre<a href=""><p class="fa fa-trash-o"></p></a> <a href=""><p class="fa fa-edit"></p></a></h5>
          </div>
        </div>
        <div id="block22" style="display:none;">le tuteur peut écouter un dialogue entre les apprenants 
       											 analysant une situation technique complexe donnant lieu à des choix variés<br>
                  <b> Coefficient:</b> 4<br>
            <b>  B:</b>savoir réunir les conditions d'un dialogue et l'engager <b>I</b>:avancer des arguments convaincants qui font évoluer les positions des différents interlocuteurs
        </div>
        </div>
        <!-- /#page-wrapper -->

  
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../js/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../js/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="../js/raphael-min.js"></script>
    <script src="../js/morris.min.js"></script>
    <script src="../js/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../js/sb-admin-2.js"></script>
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
		<c:forEach items="${ families }" var="family" varStatus="boucle">
            <h1>${ family.nameFamily }</h1>
            <c:forEach items="${ family.skills }" var="skill" varStatus="boucle">
            	<p>${skill.nameSkill}</p>
            </c:forEach>
        </c:forEach>
</body>

</html>
