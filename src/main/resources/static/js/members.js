//$(document).on("click", "#filtrarBtn", function(e) {
//	e.preventDefault();
//	filterMembers();
//});
//
//function filterMembers() {
//
//	$.ajax({
//		type: "post",
//		url: "/filterMembers",
//		data: {
//			idProCat: $('#idProCat').val()
//		},
//		success: function(html) {
//			$("#listMembersContainer").html(html);
//		},
//		error: function(e) {
//			console.log(e);
//		},
//	});
//	return false;
//}