function unon() {
    document.getElementById('association').style.display = "none";
}
function uoui() {
    document.getElementById('association').style.display = "block";
}
function addListItem() {
	$("#listView1").append('<tr> <td><input type="text" class="form-control"'+
			'th:value="+"${student.trainings[__${stat.index}__].date}"'+
				'th:field="${student.trainings[__${stat.index}__].date}"></td>'+
			'<td><input type="text" class="form-control"'+
				'th:value="${student.trainings[__${stat.index}__].name}"'+
				'th:field="${student.trainings[__${stat.index}__].name}"></td>'+
			'<td><input type="text" class="form-control"'+
				'th:value="${student.trainings[__${stat.index}__].place}"'+
				'th:field="${student.trainings[__${stat.index}__].place}"></td></tr>').listview("refresh");
} 
function removeListItem() {
	$("#listView1").children().last().remove().listview("refresh");
} 