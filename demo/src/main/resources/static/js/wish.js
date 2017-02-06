function countChars(val) {
	document.getElementById("charsCount").innerHTML = val.length;

	var checkCount = document.getElementById("checkCount");
	if (val.length >= 450) {
		checkCount.innerHTML = "&#x2714;";
		checkCount.style.color = "green";
	} else {
		checkCount.innerHTML = "&#x2718;";
		checkCount.style.color = "red";
	}
}

function initializeMyMission(){
	MyMissions = document.getElementById("myMissions");
    AllMissions = document.getElementById("allMissions");
	if (MyMissions.length > 0) {
		for(var i = 0; i< MyMissions.options.length-1 ; i++ ){
			for(var j = 0; j < AllMissions.options.length-1 ; j++ ){
				if(MyMissions.options[i].text == AllMissions.options[j].text){
					AllMissions.remove(j);
				}
			}
		}
		sortlist("allMissions");
	}
}

function addMission() {
	selected = document.getElementById("myMissions");
    selectMission = document.getElementById("allMissions");
	if (selected.length < 4 && selectMission.selectedIndex!=-1) {
		var option = selectMission.options[selectMission.selectedIndex];
		selected.add(option);
		sortlist("myMissions");
	}
}

function removeMission() {
	selected = document.getElementById("myMissions");
	selectMission = document.getElementById("allMissions");
	if (selected.selectedIndex!=-1) {
	var option = selected.options[selected.selectedIndex];
	selectMission.add(option);
	sortlist("allMissions");
	}
}

function selectMission(){
	selected = document.getElementById("myMissions");
	for (var i=0; i<selected.options.length; i++) {
		selected.options[i].selected = true;
	}
}

function sortlist(list) {

	var cl = document.getElementById(list);
	var clTexts = new Array();

	for (i = 0; i < cl.length; i++) {
		clTexts[i] = cl.options[i].text.toUpperCase() + ","
				+ cl.options[i].text + "," + cl.options[i].value;
	}

	clTexts.sort();

	for (i = 0; i < cl.length; i++) {
		var parts = clTexts[i].split(',');

		cl.options[i].text = parts[1];
		cl.options[i].value = parts[2];
	}
}
