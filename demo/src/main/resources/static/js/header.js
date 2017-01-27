function activeNav(id) {
	if(id=="offers"){
		$('#offers').addClass("active");
		$('#candidatures').removeClass("active");
	}
	else if(id=='candidatures'){
		$('#candidatures').addClass("active");
		$('#offers').removeClass("active");
	}
}