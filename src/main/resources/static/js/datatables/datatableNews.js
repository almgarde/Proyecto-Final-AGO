function getDatatableNews() {

	let table = $('#newsDatatable').DataTable({
		"sAjaxSource": "/management/getNewsData",
		"sAjaxDataProp": "",
		"orderCellsTop": true,
		"fixedHeader": false,
		"bAutoWidth": false,
		"language": {
			"lengthMenu": $('#labelMostrando').val() + ' _MENU_ ' + $('#labelEntradas').val(),
			"emptyTable": $('#labelTablaVacia').val(),
			"search": $('#labelBuscador').val(),
			"zeroRecords": $('#labelNoCoincidencias').val(),
			"info": $('#labelMostrando').val() + ' _START_ ' + $('#labelA').val() + ' _END_ ' + $('#labelDe').val() + ' _TOTAL_ ' + $('#labelEntradas').val(),
			"infoEmpty": $('#labelMostrando').val() + " 0 " + $('#labelA').val() + ' 0 ' + $('#labelDe').val() + ' 0 ' + $('#labelEntradas').val(),
			"infoFiltered": '(' + $('#labelFiltrado').val() + ' _MAX_ ' + $('#labelEntradas').val() + ')',
			"loadingRecords": $('#labelCargando').val(),
			"paginate": {
				"next": $('#labelSiguiente').val(),
				"previous": $('#labelAnterior').val()
			},
		},
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
				text: $('#imageNewsLabel').val(),
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalNewsImage(data.titleNews, data.imageNews);
				}
			});

			$('td:eq(2)', row).html(verImage);


			// Abstract
			var verAbstract = $('<a/>', {
				text: $('#abstractNewsLabel').val(),
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalNewsAbstract(data.titleNews, data.abstractNews);

				}
			});

			$('td:eq(3)', row).html(verAbstract);


			// Contenido
			var verContent = $('<a/>', {
				text: $('#contentNewsLabel').val(),
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalNewsContent(data.titleNews, data.contentNews);
				}
			});

			$('td:eq(4)', row).html(verContent);


			// Activo 
			var activo;
			if (data.active == 'true') {
				activo = $('<a/>', {
					text: $('#activoSi').val()
				});
			} else {
				activo = $('<a/>', {
					text: $('#activoNo').val()
				});
			}

			$('td:eq(7)', row).html(activo);


			// Acciones 	 	

			var liAccion1 = $('<li/>');
			var accion1 = $('<a/>', {
				text: $('#linkEditar').val(),
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
				text: $('#linkCambiarFoto').val(),
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalUpdateImageNews(data.idNews);
				}
			});

			accion2.addClass('dropdown-item').attr('href', '#');
			liAccion2.append(accion2);


			var liAccion3 = $('<li/>');
			var accion3 = $('<a/>', {
				text: $('#linkEliminar').val(),
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
			var aPrincipal = $('<a/>').addClass('nav-link').addClass('dropdown-toggle').attr('href', '#').attr('id', 'listaAcciones').attr('role', 'button').attr('data-bs-toggle', 'dropdown').attr('aria-expanded', 'false').text($('#linkAcciones').val());
			var uPrincipal = $('<u/>').addClass('dropdown-menu').attr('aria-labelledby', 'listaAcciones').css('text-decoration', 'none');

			liPrincipal.append(aPrincipal);
			aPrincipal.append(uPrincipal);
			uPrincipal.append(liAccion1);
			uPrincipal.append(liAccion2);
			uPrincipal.append(liAccion3);

			divAcciones.append(liPrincipal);

			$('td:eq(8)', row).html(divAcciones);


			$('td:eq(0)', row).attr("data-label", "ID");
			$('td:eq(1)', row).attr("data-label", "Titulo");
			$('td:eq(2)', row).attr("data-label", "Imagen");
			$('td:eq(3)', row).attr("data-label", "Abstract");
			$('td:eq(4)', row).attr("data-label", "Contenido");
			$('td:eq(5)', row).attr("data-label", "Admin");
			$('td:eq(6)', row).attr("data-label", "Fecha");
			$('td:eq(7)', row).attr("data-label", "Activo");
			$('td:eq(8)', row).attr("data-label", "");

		}
	});



	// FILTROS

	$('#newsDatatable thead tr').clone(true).addClass('filters').appendTo('#newsDatatable thead');

	$('#newsDatatable thead tr.filters th').each(function() {
		$(this).off();
		$(this).removeClass('sorting_desc');
		$(this).removeClass('sorting');
	});



	$('#newsDatatable thead tr:eq(1) th.atri').each(function(i) {

		$(this).html('<input type="text" />');

		$('input', this).off('keyup change').on('keyup change', function() {
			if (table.column(i).search() != this.value) {
				table.column(i).search(this.value).draw();
			}
		});
	});

	$('#newsDatatable thead tr:eq(1) th.selectActive').each(function(i) {

		$(this).html('<select class="form-select"><option value=""><option value="true">' + $('#activoSi').val() + '</option><option value="false">' + $('#activoNo').val() + '</option></select>');

		$('select', this).on('keyup change', function() {
			if (table.column(7).search() !== this.value) {
				table.column(7).search(this.value).draw();
			}
		});
	});


	// BOTONES

	$(document).on("click", "#btnAceptarAddNews", function(e) {

		$('#modalAddNews').modal('show');
		var form = $("#formAddNews");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			anyadirNuevaNoticia();
			$('#modalAddNews').modal('hide');
		}
		form.addClass('was-validated');

	});

	$(document).on("click", "#btnCancelarAddNews", function() {
		$("#formAddNews").removeClass('was-validated');
		$("#titleNews").val('');
		$("#abstractNews").val('');
		$("#contentNews").val('');
		$("#active").val('1');
		$('#imageNews').val('');
	});

	$(document).on("click", "#btnCloseAddNews", function() {
		$("#formAddNews").removeClass('was-validated');
		$("#titleNews").val('');
		$("#abstractNews").val('');
		$("#contentNews").val('');
		$("#active").val('1');
		$('#imageNews').val('');
	});

	$(document).on("click", "#btnAceptarUpdateNews", function(e) {

		$('#modalUpdateNews').modal('show');
		var form = $("#formUpdateNews");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			editarNews();
			$('#modalUpdateNews').modal('hide');
		}
		form.addClass('was-validated');

	});

	$(document).on("click", "#btnCancelarUpdateNews", function(e) {
		$("#formUpdateNews").removeClass('was-validated');
	});

	$(document).on("click", "#btnCloseUpdateNews", function(e) {
		$("#formUpdateNews").removeClass('was-validated');
	});

	$(document).on("click", "#btnAceptarUpdateImageNews", function(e) {

		$('#modalUpdateImageNews').modal('show');
		var form = $("#formUpdateImageNews");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			editarImagenNews();
			$('#modalUpdateImageNews').modal('hide');
		}
		form.addClass('was-validated');

	});

	$(document).on("click", "#btnCancelarUpdateImageNews", function(e) {
		$("#formUpdateImageNews").removeClass('was-validated');
	});

	$(document).on("click", "#btnCloseUpdatePhotoNews", function(e) {
		$("#formUpdateImageNews").removeClass('was-validated');
	});

	$(document).on("click", "#btnAceptarDeleteNews", function(e) {
		e.preventDefault();
		eliminarNews();
	});

}


// MODALES

function mostrarModalNewsAbstract(titleNews, absctractNews) {
	$("#modalNewsAbstract").modal('toggle');
	$("#headerModalAbstract").text(titleNews);
	$("#bodyModalAbstract").text(absctractNews);
}

function mostrarModalNewsContent(titleNews, contentNews) {
	$("#modalNewsContent").modal('toggle');
	$("#headerModalContent").text(titleNews);
	$("#bodyModalContent").text(contentNews);
}

function mostrarModalAddNews() {
	$("#modalAddNews").modal('toggle');
}

function mostrarModalNewsImage(titleNews, imagen) {
	$("#headerModalImage").text(titleNews);
	$('#bodyModalImageNews').attr('src', 'images/' + imagen);
	$('#modalNewsImage').modal('toggle');
}

function mostrarModalUpdateNews(idNews, titleNews, abstractNews, contentNews, active) {

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

function mostrarModalUpdateImageNews(idNews) {
	$('#idNewsUpdateImage').val(idNews);
	$("#modalUpdateImageNews").modal('toggle');
}

function mostrarModalDeleteNews(idNews) {
	$('#idNewsDelete').val(idNews);
	$("#modalDeleteNews").modal('toggle');
}


// FUNCIONES CRUD

function anyadirNuevaNoticia() {

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
		success: function(data) {
			if (data == '') {
				$('#newsDatatable').DataTable().ajax.reload();
				okMessage();
				$(".alert").text($("#addOkMensaje").val());
			} else {
				errorMessage();
				$(".alert").text($("#addErrorMensaje").val());
			}
		},
		error: function(data) {
			errorMessage();
			$(".alert").text($("#addErrorMensaje").val());
		}
	}).done(function() {

		$("#formAddNews").removeClass('was-validated');
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
			if (data == '') {
				$('#newsDatatable').DataTable().ajax.reload();
				okMessage();
				$(".alert").text($("#updateOkMensaje").val());
			} else {
				errorMessage();
				$(".alert").text($("#updateErrorMensaje").val());
			}
		},
		error: function(data) {
			errorMessage();
			$(".alert").text($("#updateErrorMensaje").val());
		}
	}).done(function() {

		$("#formUpdateNews").removeClass('was-validated');
	});
}


function editarImagenNews() {

	let form = new FormData();

	form.append("file", $('#imageNewsEdit')[0].files[0]);
	form.append("idNews", $("#idNewsUpdateImage").val());

	$.ajax({
		url: '/management/updateImageNewsData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#newsDatatable').DataTable().ajax.reload();
				okMessage();
				$(".alert").text($("#updateOkMensaje").val());
			} else {
				errorMessage();
				$(".alert").text($("#updateErrorMensaje").val());
			}
		},
		error: function(data) {
			errorMessage();
			$(".alert").text($("#updateErrorMensaje").val());
		}
	}).done(function() {
		$("#formUpdateImageNews").removeClass('was-validated');
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
			if (data == '') {
				$('#newsDatatable').DataTable().ajax.reload();
				okMessage();
				$(".alert").text($("#deleteOkMensaje").val());
			} else {
				errorMessage();
				$(".alert").text($("#deleteErrorMensaje").val());
			}
		},
		error: function(data) {
			errorMessage();
			$(".alert").text($("#deleteErrorMensaje").val());
		}
	});
}



