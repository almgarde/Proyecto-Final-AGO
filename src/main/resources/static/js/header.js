$(document).ready(function() {

	$(".headerLang").removeClass("active");

	var lang = $("#lang").val();

	if (lang == "es") {
		$('#espLang').addClass("active");
	} else if (lang == "es_ES") {
		$('#espLang').addClass("active");
	} else if (lang == "en") {
		$('#engLang').addClass("active");
	} else {
		$('#frLang').addClass("active");
	} 

	var inputUser = $('#inputUser').val();
	if (inputUser == "") {
		$('#loginOpt').show();
		$('#gestionOpt').hide();
		$('#logoutOpt').hide();
	} else {
		$('#loginOpt').hide();
		$('#gestionOpt').show();
		$('#logoutOpt').show();
	}

});
