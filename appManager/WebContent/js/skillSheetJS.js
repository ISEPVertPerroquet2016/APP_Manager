
function sendFamily(nameFamily){
	
	var familySelected = document.getElementById('familySelected');

	familySelected.value = nameFamily;
	
	document.getElementById('skillSheetForm').submit();	
}

function switchFiche(){
	
	var toggle = document.getElementById('toggleFiche');
	var fiche = document.getElementById('fiche');
	var edit = document.getElementById('editFiche');
	
	if(toggle.checked){
		fiche.style.display = "none";
		edit.style.display = "block";
	}else{
		fiche.style.display = "block";
		edit.style.display = "none";
	}	
}

function changeGroup(){
	
	document.getElementById('selectEleve').value = "0";
	document.getElementById('skillSheetForm').submit();
	
}
	
function selectNiveau(){

	var count = 1;
	
	do{
		
		var niveau = document.getElementById('niveauRadio' + count).value;
		
		if(niveau == "NA" || niveau == "B-" || niveau == "B+" || niveau == "I-" || niveau == "I+")
		{
				document.getElementById(niveau + count).checked = true;

				var niveauButton = document.getElementById('niveauButton' + count);

				/*if(niveau == "NA"){
					niveauButton.className = "btn btn-danger btn-circle";
				} 
				if(niveau ="B-"){
					niveauButton.className = "btn btn-warning btn-circle";
				}
				if(niveau ="B+"){
					niveauButton.className = "btn btn-info btn-circle";
				}
				if(niveau ="I-"){
					niveauButton.className = "btn btn-primary btn-circle";
				}
				if(niveau ="I+"){
					niveauButton.className = "btn btn-success btn-circle";
				}*/
				
		}
		
		count++;
		
	}while(niveau != null);
}

		