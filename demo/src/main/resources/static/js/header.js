function activeNav(id) {
	if(id=="offers"){
		$('#offers').addClass("active");
		$('#candidatures').removeClass("active");
	}
	else if(id=='candidatures'){
		$('#candidatures').addClass("active");
		$('#offers').removeClass("active");
	}
	else if(id=='consult'){
		$('#consult').addClass("active");
		$('#offers').removeClass("active");
		$('#candidatures').removeClass("active");
	}
	else if(id=='gestionModerator'){
		$('#gestionModerator').addClass("active");
		$('#consult').removeClass("active");
		$('#offers').removeClass("active");
		$('#candidatures').removeClass("active");
	}
	else if(id=='gestionReferent'){
		$('#gestionReferent').addClass("active");
		$('#gestionModerator').removeClass("active");
		$('#consult').removeClass("active");
		$('#offers').removeClass("active");
		$('#candidatures').removeClass("active");	
	}
	else if(id=='consult_offers'){
		$('#consult_offers').addClass("active");
		$('#offers').removeClass("active");
		$('#candidatures').removeClass("active");
		$('#gestionReferent').removeClass("active");
		$('#gestionModerator').removeClass("active");
		$('#consult').removeClass("active");
	}
	else if(id=='gestionOffers'){
		$('#gestionOffers').addClass("active");
		$('#consult_offers').removeClass("active");
		$('#offers').removeClass("active");
		$('#candidatures').removeClass("active");
		$('#gestionReferent').removeClass("active");
		$('#gestionModerator').removeClass("active");
		$('#consult').removeClass("active");
	}
	
	else if(id =='nextYear'){
		$('#nextYear').addClass("active");
		$('#gestionOffers').removeClass("active");
		$('#consult_offers').removeClass("active");
		$('#offers').removeClass("active");
		$('#candidatures').removeClass("active");
		$('#gestionReferent').removeClass("active");
		$('#gestionModerator').removeClass("active");
		$('#consult').removeClass("active");
	}
	
	else if(id=='contract'){
		$('#contract').addClass("active");
	}
	else if(id=='accueil'){
		$('#accueil').addClass("active");
	}
	else if(id=='stats'){
		$('#stats').addClass("active");
		$('#consult_offers').removeClass("active");
		$('#offers').removeClass("active");
		$('#candidatures').removeClass("active");
		$('#gestionReferent').removeClass("active");
		$('#gestionModerator').removeClass("active");
		$('#consult').removeClass("active");
	}
}