$(document).ready(function() {

	// CATEGORÍAS 
	$(document).on("click", ".catSidebar", function(e) {
		e.preventDefault();
		$(".catSidebar").removeClass("active");
		$(this).addClass("active");
		let numCat = $(this).attr("data-num-cat");
		getDataTableCategory(numCat);
	});





});

function getDataTableCategory(numCat) {
	$.ajax({
		type: "post",
		url: "/management/getDataTableCategory",
		data: {
			numCat: numCat
		},
		success: function(html) {
			$("#containerDataTables").html(html);
			let selectedCat = $('.selectedCat').val();

			switch (selectedCat) {
				case "0":

					break;
				case "1":
					getDatatableNews();
					
					break;

				case "2":
					getDatatableProCat();
					break;

				case "3":
					getDatatableMembers();
					break;

				case "4":
					getDatatableProjects();
					break;

				case "5":
					getDatatableTechCat();
					break;

				case "6":
					getDatatableFacilities();
					break;

				case "7":
					getDatatablePublications();
					break;

				case "8":
					getDatatableThesis();
					break;

				case "9":
					getDatatableLinks();
					break;


			}



		},
		error: function(e) {
			console.log(e);
		},
	});
	return false;
}

//function getNewsData() {
//
//	$.ajax({
//		type: "post",
//		url: "/management/getNewsData",
//		data: {
//
//		},
//		success: function(html) {
//			$("#containerDataTables").html(html);
//			$('#scripDatatable').attr('src', "/js/facilities.js");
//		},
//		error: function(e) {
//			console.log(e);
//		},
//	});
//	return false;
//}


// DATATABLES
//
//function getDatatableNews() {
//	$('#newsDatatable').dataTable({
//		"sAjaxSource": "/management/getNewsData",
//		"sAjaxDataProp": "",
//		"order": [[0, "asc"]],
//		"aoColumns": [
//			{
//				"mData": "idNews",
//				"mRender": function(data, type, row) {
//					return data;
//				},
//				"bSortable": false
//			},
//			{
//				"mData": "titleNews",
//				"mRender": function(data, type, row) {
//					return data;
//				},
//				"bSortable": false
//			},
//			{
//				"mData": "imageNews",
//				"mRender": function(data, type, row) {
//					return data;
//				},
//				"bSortable": false
//			},
//			{
//				"mData": null
//			},
//			{
//				"mData": "contentNews",
//				"mRender": function(data, type, row) {
//					return data;
//				},
//				"bSortable": false
//			},
//			{
//				"mData": "active",
//				"mRender": function(data, type, row) {
//					return data;
//				},
//				"bSortable": false
//			},
//
//			{
//				"mData": null
//			}
//		],
//		"fnCreatedRow": function(row, data, index) {
//
//			var verAbstract = $('<a/>', {
//				text: "Abstract",
//				href: '',
//				click: function() {
//					event.preventDefault();
//					mostrarModalNewsAbstract(data.idNews, data.abstractNews);
//					
//				}
//			});
//			$('td:eq(3)', row).html(verAbstract);
//			}
//		});
//}
//
//function mostrarModalNewsAbstract(idNews, absctractNews) {
//	$("#modalNewsAbstract").modal('toggle');
//	$("#headerModalAbstract").text('Abstract Noticia ' + idNews);
//	$("#bodyModalAbstract").text(absctractNews);
//}
//
//

