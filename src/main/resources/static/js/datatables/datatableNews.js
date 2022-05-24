function getDatatableNews() {
	let table = $('#newsDatatable').DataTable({
		"sAjaxSource": "/management/getNewsData",
		"sAjaxDataProp": "",
		"orderCellsTop": true,
		"fixedHeader": false,
		"order": [[0, "desc"]],
		"aoColumns": [
			{
				"mData": "idNews",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true,
			},
			{
				"mData": "titleNews",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "imageNews",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "abstractNews",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
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
				"bSortable": true
			},
			{
				"mData": "date",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true
			},
			{
				"mData": "active",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true
			},

			{
				"mData": null,
				"bSortable": false
			}
		],
		"fnCreatedRow": function(row, data, index) {

			// Imagen 		

			var verImage = $('<a/>', {
				text: 'Imagen',
				href: '',
				click: function(e) {
					e.preventDefault();
					//$('#newsDatatable').DataTable().ajax.reload();
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
					mostrarModalNewsAbstract(data.titleNews, data.abstractNews);

				}
			});
			$('td:eq(3)', row).html(verAbstract);

			// Contenido
			var verContent = $('<a/>', {
				text: "Contenido",
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalNewsContent(data.titleNews, data.contentNews);

				}
			});
			$('td:eq(4)', row).html(verContent);

			// Activo 
			if (data.active == 'true') {
				var activo = $('<a/>', {
					text: "Si"
				});
			} else {
				var activo = $('<a/>', {
					text: "No"
				});
			}
			$('td:eq(7)', row).html(activo);
			
			// Acciones 	 	
			var liAccion1 = $('<li/>');
			var accion1 = $('<a/>', {
				text: 'Editar',
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalUpdateNews(data.idNews, data.titleNews, data.abstractNews, data.contentNews, data.active);
				}
			});

			accion1.addClass('dropdown-item').attr('href', '#');
			liAccion1.append(accion1);

			var liAccion2 = $('<li/>');
			var accion2 = $('<a/>', {
				text: 'Cambiar foto',
				href: '',
				click: function(e) {

					e.preventDefault();
					mostrarModalUpdatePhotoNews(data.idNews);
				}
			});

			accion2.addClass('dropdown-item').attr('href', '#');
			liAccion2.append(accion2);

			var liAccion3 = $('<li/>');
			var accion3 = $('<a/>', {
				text: 'Eliminar',
				href: '',
				click: function(e) {

					e.preventDefault();
					mostrarModalDeleteNews(data.idNews);
				}
			});

			accion3.addClass('dropdown-item').attr('href', '#');
			liAccion3.append(accion3);

			var divAcciones = $('<div/>');
			divAcciones.addClass("text-end");

			var liPrincipal = $('<li/>').css('list-style-type', 'none').addClass('nav-item').addClass('dropdown');
			var aPrincipal = $('<a/>').addClass('nav-link').addClass('dropdown-toggle').attr('href', '#').attr('id', 'listaAcciones').attr('role', 'button').attr('data-bs-toggle', 'dropdown').attr('aria-expanded', 'false').text('Acciones');
			var uPrincipal = $('<u/>').addClass('dropdown-menu').attr('aria-labelledby', 'listaAcciones').css('text-decoration', 'none');

			liPrincipal.append(aPrincipal);
			aPrincipal.append(uPrincipal);
			uPrincipal.append(liAccion1);
			uPrincipal.append(liAccion2);
			uPrincipal.append(liAccion3);

			divAcciones.append(liPrincipal);

			$('td:eq(8)', row).html(divAcciones);
		}
	});

	$(document).on("click", "#btnAceptarAddNews", function(e) {
		e.preventDefault();
		anyadirNuevaNoticia();

	});

$(document).on("click", "#btnAceptarUpdateNews", function(e) {
		e.preventDefault();
		editarNews();

	});
	
	$(document).on("click", "#btnAceptarUpdatePhotoNews", function(e) {
		e.preventDefault();
		editarImagenNews();

	});

	$(document).on("click", "#btnAceptarDeleteNews", function(e) {
		e.preventDefault();
		eliminarNews();

	});

	$('#newsDatatable thead tr').clone(true).addClass('filters').appendTo('#newsDatatable thead');

	$('#newsDatatable thead tr.filters th').each(function() {
		$(this).off();
		$(this).removeClass('sorting_desc');
		$(this).removeClass('sorting');

	});



	$('#newsDatatable thead tr:eq(1) th.atri').each(function(i) {
		//let title = $(this).text();
		$(this).html('<input style="max-width: 100px;" type="text" />');

		$('input', this).off('keyup change').on('keyup change', function() {

			if (table.column(i).search() != this.value) {

				table.column(i).search(this.value).draw();
			}
		});
	});

	$('#newsDatatable thead tr:eq(1) th.selectActive').each(function(i) {

		$(this).html('<select class="form-select"><option value=""><option value="true">Si</option><option value="false">No</option></select>');

		$('select', this).on('keyup change', function() {
			if (table.column(7).search() !== this.value) {

				table.column(7).search(this.value).draw();

			}
		});
	});



}

function mostrarModalNewsAbstract(titleNews, absctractNews) {
	$("#modalNewsAbstract").modal('toggle');
	$("#headerModalAbstract").text(titleNews + ' - Abstract');
	$("#bodyModalAbstract").text(absctractNews);
}

function mostrarModalNewsContent(titleNews, contentNews) {
	$("#modalNewsAbstract").modal('toggle');
	$("#headerModalAbstract").text(titleNews + ' - Contenido');
	$("#bodyModalAbstract").text(contentNews);
}

function mostrarModalAddNews() {
	$("#modalAddNews").modal('toggle');
}

function mostrarModalNewsImage(imagen) {
	$('#bodyModalImageNews').attr('src', 'images/' + imagen);
	$('#modalNewsImage').modal('toggle');
}

function mostrarModalUpdateNews(idNews, titleNews, abstractNews, contentNews, active) {
debugger;
	$('#titleNewsEdit').val(titleNews);



	$('#abstractNewsEdit').val(abstractNews);
	$('#contentNewsEdit').val(contentNews);
		$('#idNewsUpdate').val(idNews);
	if (active == 'true') {
		$('#activeEdit').val('1');
	} else {
		$('#activeEdit').val('0');
		
	}
	
	$("#modalUpdateNews").modal('toggle');
}

function mostrarModalUpdatePhotoNews(idNews) {
	$('#idNewsUpdatePhoto').val(idNews);
	$("#modalUpdatePhotoNews").modal('toggle');
}

function mostrarModalDeleteNews(idNews) {
		$('#idNewsDelete').val(idNews);
	$("#modalDeleteNews").modal('toggle');
}

function anyadirNuevaNoticia() {


	//	let form = $('#addNewsForm');
	//	form.submit();
	//	
	//	getDataTableCategory(1);
	//	

	let form = new FormData();

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
		data: form,
		//
		//		{
		//			"file": $('#imageNews')[0].files[0],
		//			"titleNews": $("#titleNews").val(),
		//			"abstractNews": $("#abstractNews").val(),
		//			"contentNews": $("#contentNews").val(),
		//			"active": $("#active").val()
		//		},
		success: function(data) {

			//			$('#newsDatatable').DataTable().ajax.reload();
			//			$("#titleNews").val('');
			//			$("#abstractNews").val('');
			//			$("#contentNews").val('');
			//			$("#active").val('1');
			//			$('#imageNews').val('');



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
	}).done(function() {
		$('#newsDatatable').DataTable().ajax.reload();
		$("#titleNews").val('');
		$("#abstractNews").val('');
		$("#contentNews").val('');
		$("#active").val('1');
		$('#imageNews').val('');
	});
}

function editarNews() {

		let form = new FormData();

		form.append("titleNews", $("#titleNewsEdit").val());
		form.append("abstractNews", $("#abstractNewsEdit").val());
		form.append("contentNews", $("#contentNewsEdit").val());
		form.append("idNews", $("#idNewsUpdate").val());
		form.append("active", $("#activeEdit").val());

		$.ajax({
			url: '/management/updateNewsData',
			type: "POST",
			processData: false,
			contentType: false,
			data: form,
			success: function(data) {




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
		}).done(function() {
			$('#newsDatatable').DataTable().ajax.reload();

		});
	}


function editarImagenNews() {

		let form = new FormData();

form.append("file", $('#imageNewsEdit')[0].files[0]);
		form.append("idNews", $("#idNewsUpdatePhoto").val());

		$.ajax({
			url: '/management/updateImageNewsData',
			type: "POST",
			processData: false,
			contentType: false,
			data: form,
			success: function(data) {




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
		}).done(function() {
			$('#newsDatatable').DataTable().ajax.reload();

		});
	}
	
	function eliminarNews() {

		let form = new FormData();


		form.append("idNews", $("#idNewsDelete").val());

		$.ajax({
			url: '/management/deleteNewsData',
			type: "POST",
			processData: false,
			contentType: false,
			data: form,
			success: function(data) {




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
		}).done(function() {
			$('#newsDatatable').DataTable().ajax.reload();

		});
	}

