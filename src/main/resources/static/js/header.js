$(document).ready(function() {

	$(".headerLang").removeClass("active");

	var lang = $("#lang").val();

	if (lang == "es") {
		$('#espLang').addClass("active");
	} else if (lang == "en") {
		$('#engLang').addClass("active");
	} else if (lang == "fr") {
		$('#frLang').addClass("active");
	} else {
		$('#espLang').addClass("active");
	}

});
