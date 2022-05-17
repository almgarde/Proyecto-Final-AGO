function getDatatableNews() {
	$('#newsDatatable').dataTable({
		"sAjaxSource": "/management/getNewsData",
		"sAjaxDataProp": "",
		"order": [[0, "desc"]],
		"aoColumns": [
			{
				"mData": "idNews",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "titleNews",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": null
			},
			{
				"mData": null
			},
			{
				"mData": "contentNews",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "admin",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "date",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "active",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},

			{
				"mData": null
			}
		],
		"fnCreatedRow": function(row, data, index) {
			
			// Imagen 		
			  	
			var verImage = $('<a/>', {
				text: 'Imagen',
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalNewsImage(data.imageNews);
				}
			});
	    	$('td:eq(2)', row).html(verImage);

			// Abstract
			var verAbstract = $('<a/>', {
				text: "Abstract",
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalNewsAbstract(data.idNews, data.abstractNews);

				}
			});
			$('td:eq(3)', row).html(verAbstract);
			
			// Contenido
			var verContent = $('<a/>', {
				text: "Contenido",
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalNewsContent(data.idNews, data.contentNews);

				}
			});
			$('td:eq(4)', row).html(verContent);
			
			// Activo 
			if(data.active) {
				var activo = $('<a/>', {
				text: "Si"
				});
			} else {
				var activo = $('<a/>', {
				text: "No"
				});
			}
			$('td:eq(7)', row).html(activo);
		}
	});
	
	$(document).on("click", "#btnAceptarAddNews", function(e) {
		e.preventDefault();
		anyadirNuevaNoticia();
		
	});
	

	
	
	
	
}

function mostrarModalNewsAbstract(idNews, absctractNews) {
	$("#modalNewsAbstract").modal('toggle');
	$("#headerModalAbstract").text('Noticia ' + idNews + ' - Abstract');
	$("#bodyModalAbstract").text(absctractNews);
}

function mostrarModalNewsContent(idNews, contentNews) {
	$("#modalNewsAbstract").modal('toggle');
	$("#headerModalAbstract").text('Noticia ' + idNews + ' - Contenido');
	$("#bodyModalAbstract").text(contentNews);
}

function mostrarModalAddNews() {
$("#modalAddNews").modal('toggle');
}

function mostrarModalNewsImage (imagen) {
	$('#bodyModalImageNews').attr('src', 'images/'+imagen);
	$('#modalNewsImage').modal('toggle');
}

function anyadirNuevaNoticia() {
	
	
//	let form = $('#addNewsForm');
//	form.submit();
//	
//	getDataTableCategory(1);
//	

	var form = new FormData();

	form.append("file", $('#imageNews')[0].files[0]);
	form.append("titleNews", $("#titleNews").val());
	form.append("abstractNews", $("#abstractNews").val());
	form.append("contentNews", $("#contentNews").val());
	form.append("active", $("#active").val());

	$.ajax({
		url: '/management/addNewsData',
		type: "POST",
		processData: false,
        contentType: false,
		data:form,
//
//		{
//			"file": $('#imageNews')[0].files[0],
//			"titleNews": $("#titleNews").val(),
//			"abstractNews": $("#abstractNews").val(),
//			"contentNews": $("#contentNews").val(),
//			"active": $("#active").val()
//		},
		success: function(data) {

			$('#newsDatatable').DataTable().ajax.reload();
			$("#titleNews").val('');
			$("#abstractNews").val('');
			$("#contentNews").val('');
			$("#active").val('1');
			$('#imageNews').val('');



			//			removePantallaLoader();
			//			debugger;
			//			if (!data || data == '') {
			//				
			//				refreshDataTable();
			//				mostrarNotificacion('missatge-feedback-positiu', Globalize.localize('label.listaUsuarios.modal.anyadir.confirmacion'));
			//			} else {
			//				mostrarNotificacion('missatge-error', Globalize.localize('label.listaUsuarios.modal.anyadir.error'));
			//			}

		}
		//error: function(data) {
		//			removePantallaLoader();
		//			mostrarNotificacion('missatge-error', Globalize.localize('label.listaUsuarios.modal.anyadir.error'));
		//		}
	});
}

