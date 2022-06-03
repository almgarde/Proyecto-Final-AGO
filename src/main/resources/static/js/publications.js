$(document).ready(function() {

	generarAutores();

	$('#selectOrdenPublication').val("false");

	$(document).on("change", "#selectOrdenPublication", function(e) {
		e.preventDefault();
		orderedPublications();
	});

});


function generarAutores() {

	var i = 0;

	$(".authors").each(function(index) {

		var numAuthors = $(this).attr("data-num-autor");
		var autor;

		if ($(this).attr("data-is-member") == "true") {
			autor = $('<a/>', {
				text: $(this).attr("data-text"),
				href: "/memberComplete/" + $(this).attr("data-id-member"),
			});
		} else {
			autor = $('<p/>', {
				text: $(this).attr("data-text")
			});
		}

		if (i < numAuthors - 1) {
			autor.text(autor.text().concat(", "));
			++i;
		} else {
			i = 0;
		}

		$(this).append(autor);
	});
}

function orderedPublications() {
	
	$.ajax({
		type: "post",
		url: "/orderedPublications",
		data: {
			ascendente: $('#selectOrdenPublication').val()
		},
		success: function(html) {
			$("#listPublicationsContainer").html(html);
			generarAutores();
		},
		error: function(e) {
			console.log(e);
		},
	});
	return false;
}