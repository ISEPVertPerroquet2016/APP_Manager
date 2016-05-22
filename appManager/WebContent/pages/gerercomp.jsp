<%@include file="headerResp.jsp" %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="page-header">comp�tences  <a href="nouvelleFamille.jsp"><p class="fa fa-plus-circle"></p></a></h2>
                </div>
               
            </div>
            <!-- Famille 1 -->
            <div class="row">
          <div class="col-lg-4 col-md-6">
                   <h3><p class="fa fa-chevron-circle-down" ></p> Travail en �quipe <a href="nouvelleComp.jsp"><p class="fa fa-plus-circle"></p></a></h3>
                </div></div>
                <div class="row">
                <div class="col-lg-4 col-md-6">
                   <h5><p  id="fleche1" class="fa fa-chevron-down" onclick="test('block1','fleche1');"></p> Participer � la vie de l'�quipe <a href=""><p class="fa fa-trash-o"></p></a> <a href=""><p class="fa fa-edit"></p></a></h5>
                </div>
              </div>
             <div id="block1" style="display:none;">Rep�rage au quotidien et lors des diverses pr�sentations<br>
                  <b> Coefficient:</b> 4<br>
            <b>  B:</b> se montrer ouvert, collaboratif et participatif &nbsp <b>I</b>: agir avec coordination et entre-aide
             </div>
                
            <!-- Famille 2 -->
            
                <div class="row">
                <div class="col-lg-4 col-md-6">
                   <h5><p id="fleche2" class="fa fa-chevron-down" onclick="test('block2','fleche2');"></p>Aimer une �quipe et la motiver <a href=""><p class="fa fa-trash-o"></p></a> <a href=""><p class="fa fa-edit"></p></a></h5>
                </div>
              </div>
             <div id="block2" style="display:none;">Rep�rage au quotidien et lors des diverses pr�sentations<br>
                  <b> Coefficient:</b> 4<br>
            <b>  B:</b>animer en maintenant la coh�sion de l'�quipe et un minimum d'int�r�t&nbsp <b>I:</b> motiver les membres de l'�quipe
             </div>
            
            <!-- Famille 3 -->
            
            <div class="row">
                <div class="col-lg-6 col-md-6">
                   <h5><p id="fleche3" class="fa fa-chevron-down" onclick="test('block3','fleche3');"></p>G�rer les conflits, la diversit� et les diff�rences<a href=""><p class="fa fa-trash-o"></p></a> <a href=""><p class="fa fa-edit"></p></a></h5>
                </div>
              </div>
             <div id="block3" style="display:none;">observation au quotidien avec le cas �ch�ant un suivi rapproch� des conflits et leurs �volutions<br>
                  <b> Coefficient:</b> 3<br>
            <b>  B:</b> d�tecter les conflits et accepter la diversit� et les diff�rences&nbsp <b>I:</b> apporter des solutions aux conflits et s'ouvrir aux diff�rences 
             </div>
             
             <!-- Famille 3 -->
            
            <div class="row">
                <div class="col-lg-6 col-md-6">
                   <h5><p id="fleche4" class="fa fa-chevron-down" onclick="test('block4','fleche4');"></p>�tre force de proposition<a href=""><p class="fa fa-trash-o"></p></a> <a href=""><p class="fa fa-edit"></p></a></h5>
                </div>
              </div>
             <div id="block4" style="display:none;">observation lors des discussions sur les livrables et 
             											�ventuellement lors des suivis au quotidien<br>
                  <b> Coefficient:</b> 3<br>
            <b>  B:</b> �mettre une id�e pertinente &nbsp <b>I:</b> la justifier et la d�fendre  
             </div>
             
             <!-- Famille 4 -->
        <div class="row">
          <div class="col-lg-4 col-md-6">
            <h3><p class="fa fa-chevron-circle-down" ></p> Communication <a href=""><p class="fa fa-plus-circle"></p></a></h3>
          </div>
        </div>
        
        <div class="row">
          <div class="col-lg-4 col-md-6">
             <h5><p  id="fleche21" class="fa fa-chevron-down" onclick="test('block21','fleche21');"></p>�couter et se faire �couter<a href=""><p class="fa fa-trash-o"></p></a> <a href=""><p class="fa fa-edit"></p></a></h5>
          </div>
        </div>
        <div id="block21" style="display:none;">le tuteur peut �couter une discussion entre les apprenants portant sur les travaux en groupe, 
             										les difficult�s ou les divergences rencontr�es<br>
                  <b> Coefficient:</b> 4<br>
            <b>  B:</b>�tre dispos� � l'�coute et �tre capable d'exposer son point de vue&nbsp <b>I</b>: admettre  que l'autre peut avoir raison et maintenir l'int�r�t de son auditoire
        </div>
        
        
        <!-- Famille 4 -->
        
        <div class="row">
          <div class="col-lg-4 col-md-6">
             <h5><p  id="fleche22" class="fa fa-chevron-down" onclick="test('block22','fleche22');"></p>Dialoguer, argumenter et convaincre<a href=""><p class="fa fa-trash-o"></p></a> <a href=""><p class="fa fa-edit"></p></a></h5>
          </div>
        </div>
        <div id="block22" style="display:none;">le tuteur peut �couter un dialogue entre les apprenants 
       											 analysant une situation technique complexe donnant lieu � des choix vari�s<br>
                  <b> Coefficient:</b> 4<br>
            <b>  B:</b>savoir r�unir les conditions d'un dialogue et l'engager <b>I</b>:avancer des arguments convaincants qui font �voluer les positions des diff�rents interlocuteurs
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
