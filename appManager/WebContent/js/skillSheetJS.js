
function sendFamily(nameFamily){
	
	var familySelected = document.getElementById('familySelected');

	familySelected.value = nameFamily;
	
	document.getElementById('skillSheetForm').submit();		
}