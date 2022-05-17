//$(document).on("click", "#filtrarBtn", function(e) {
//	e.preventDefault();
//	filterFacilities();
//});
//
//function filterFacilities() {
//
//	$.ajax({
//		type: "post",
//		url: "/filterFacilities",
//		data: {
//			idTechCat: $('#idTechCat').val()
//		},
//		success: function(html) {
//			$("#listFacilitiesContainer").html(html);
//		},
//		error: function(e) {
//			console.log(e);
//		},
//	});
//	return false;
//}