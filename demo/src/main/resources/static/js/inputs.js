function cancelEvent(event) {
	event.preventDefault();
	event.stopImmediatePropagation();
}

function getValueAfterKeypress($input, keycode) {
	var caretPosition = $input[0].selectionStart;
  
  var result = $input.val().substring(0, caretPosition) +
    				   String.fromCharCode(keycode) +
    				   $input.val().substring(caretPosition);
  
  return result;
}
function validateFormGeneric() {
    var x = document.forms["formgeneric"]["validityDate"].value;
    if (x == "") {
        alert("Le champ Date de validité doit être rempli");
        return false;
    }
}

function validateFormStandard(){
    var x = document.forms["formstandard"]["validityDate"].value;
    if (x == "") {
        alert("Le champ Date de validité doit être rempli");
        return false;
    }
}

function isValidInteger(string) {
	var regex = /^\d*$/;
  
  return regex.test(string);
}

function isValidPhoneNumber(string) {
	var regex = /^\+?\d*$/;
  
  return regex.test(string);
}

//When the document is ready
$(document).ready(function () {

$(".input-integer").bind({
  paste: function() {
    if (!isValidInteger(event.clipboardData.getData('text/plain'))) {
      cancelEvent(event);
    }
  },
  keypress: function() {
  	if (!isValidInteger(getValueAfterKeypress($(this), event.which))) {
      cancelEvent(event);
    }
  }
});

$(".input-phonenumber").bind({
  paste: function() {
    if (!isValidPhoneNumber(event.clipboardData.getData('text/plain'))) {
      cancelEvent(event);
    }
  },
  keypress: function() {
  	if (!isValidPhoneNumber(getValueAfterKeypress($(this), event.which))) {
      cancelEvent(event);
    }
  }
});

$('.input-date, .input-daterange').datepicker({
	language: 'fr',
	autoclose: true,
	clearBtn: true,
	keyboardNavigation: false
});

});
