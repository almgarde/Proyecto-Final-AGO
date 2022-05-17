$(document).ready(function() {

	$(".authors").each(function(index) {
		if ($(this).attr("data-is-member")) {
			$(this).attr("href", "/memberComplete/" + $(this).attr("data-id-member"));
		}
	});

	$(document).on("click", "#filtrarBtn", function(e) {
		e.preventDefault();
		orderedPublications();
	});

});

function orderedPublications() {

	$.ajax({
		type: "post",
		url: "/orderedPublications",
		data: {
			ascendente: $('#ascendente').val()
		},
		success: function(html) {
			$("#listPublicationsContainer").html(html);
		},
		error: function(e) {
			console.log(e);
		},
	});
	return false;
}