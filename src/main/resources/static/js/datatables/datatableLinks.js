function getDatatableLinks() {

	let table = $('#linksDatatable').DataTable({
		"sAjaxSource": "/management/getLinksData",
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
				"mData": "idLink",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true
			},
			{
				"mData": "titleLink",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},

			{
				"mData": "imageLink",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "urlLink",
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
			var verImagen = $('<a/>', {
				text: $('#imageLinkLabel').val(),
				href: '',
				click: function(e) {
					e.preventDefault();
					//$('#newsDatatable').DataTable().ajax.reload();
					mostrarModalImagenLink(data.titleLink, data.imageLink);
				}
			});

			$('td:eq(2)', row).html(verImagen);


			// Enlace 	
			var irEnlace = $('<a/>', {
				text: $('#urllinkLabel').val(),
				href: data.urlLink,
				click: function(e) {
					e.preventDefault();
					window.open(this, '_blank');
					return false;
				}
			});

			$('td:eq(3)', row).html(irEnlace);


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

			$('td:eq(6)', row).html(activo);


			// Acciones 

			var liAccion1 = $('<li/>');
			var accion1 = $('<a/>', {
				text: $('#linkEditar').val(),
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalUpdateLinks(data.idLink, data.titleLink, data.urlLink, data.active);
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
					mostrarModalUpdatePhotoLinks(data.idLink);
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
					mostrarModalDeleteLinks(data.idLink);
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

			$('td:eq(7)', row).html(divAcciones);


			$('td:eq(0)', row).attr("data-label", "ID");
			$('td:eq(1)', row).attr("data-label", "Título");
			$('td:eq(2)', row).attr("data-label", "Imagen");
			$('td:eq(3)', row).attr("data-label", "URL");
			$('td:eq(4)', row).attr("data-label", "Admin");
			$('td:eq(5)', row).attr("data-label", "Fecha");
			$('td:eq(6)', row).attr("data-label", "Fecha");
			$('td:eq(7)', row).attr("data-label", "");

		}
	});


	// FILTROS

	$('#linksDatatable thead tr').clone(true).addClass('filters').appendTo('#linksDatatable thead');

	$('#linksDatatable thead tr.filters th').each(function() {
		$(this).off();
		$(this).removeClass('sorting_desc');
		$(this).removeClass('sorting');
	});

	$('#linksDatatable thead tr:eq(1) th.atri').each(function(i) {

		$(this).html('<input type="text" />');

		$('input', this).on('keyup change', function() {
			if (table.column(i).search() != this.value) {
				table.column(i).search(this.value).draw();
			}
		});
	});

	$('#linksDatatable thead tr:eq(1) th.selectActive').each(function(i) {

		$(this).html('<select class="form-select"><option value=""><option value="true">' + $('#activoSi').val() + '</option><option value="false">' + $('#activoNo').val() + '</option></select>');

		$('select', this).on('keyup change', function() {
			if (table.column(5).search() !== this.value) {
				table.column(5).search(this.value).draw();
			}
		});
	});


	// BOTONES

	$(document).on("click", "#btnAceptarAddLinks", function(e) {

		$('#modalAddLinks').modal('show');
		var form = $("#formAddLinks");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			anyadirNuevoLinks();
			
		}
		form.addClass('was-validated')

	});

	$(document).on("click", "#btnCancelarAddLinks", function() {
		$("#formAddLinks").removeClass('was-validated');
				$(".mensajeError").hide();
		$(".mensajeError").text('');
		$("#titleLinks").val('');
		$("#imageLinks").val('');
		$("#urlLinks").val('');
		$("#active").val('1');
	});

	$(document).on("click", "#btnCloseAddLinks", function() {
		$("#formAddLinks").removeClass('was-validated');
				$(".mensajeError").hide();
		$(".mensajeError").text('');
		$("#titleLinks").val('');
		$("#imageLinks").val('');
		$("#urlLinks").val('');
		$("#active").val('1');

	});
	
			$("#modalAddLinks").on('hide.bs.modal', function() {
		$("#formAddLinks").removeClass('was-validated');
		$(".mensajeError").hide();
		$(".mensajeError").text('');
	});
	

	$(document).on("click", "#btnAceptarUpdateLinks", function(e) {

		$('#modalUpdateLinks').modal('show');
		var form = $("#formUpdateLinks");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			editarLinks();
			
		}
		form.addClass('was-validated');

	});

	$(document).on("click", "#btnCancelarUpdateLinks", function(e) {
		$("#formUpdateLinks").removeClass('was-validated');
				$(".mensajeError").hide();
		$(".mensajeError").text('');
	});

	$(document).on("click", "#btnCloseUpdateLinks", function(e) {
		$("#formUpdateLinks").removeClass('was-validated');
				$(".mensajeError").hide();
		$(".mensajeError").text('');
	});
	
	
			$("#modalUpdateLinks").on('hide.bs.modal', function() {
		$("#formUpdateLinks").removeClass('was-validated');
		$(".mensajeError").hide();
		$(".mensajeError").text('');
	});


	$(document).on("click", "#btnAceptarUpdateImageLinks", function(e) {

		$('#modalUpdateImageLinks').modal('show');
		var form = $("#formUpdateImageLinks");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			editarImagenLinks();
			$('#modalUpdateImageLinks').modal('hide');
		}
		form.addClass('was-validated');

	});

	$(document).on("click", "#btnCancelarUpdateImageLinks", function(e) {
		$("#formUpdateImageLinks").removeClass('was-validated');
		$("#imageLinksEdit").val('');
	});

	$(document).on("click", "#btnCloseUpdateImageLinks", function(e) {
		$("#formUpdateImageLinks").removeClass('was-validated');
		$("#imageLinksEdit").val('');
	});
	
			$("#modalUpdateImageLinks").on('hide.bs.modal', function() {
		$("#formUpdateImageLinks").removeClass('was-validated');
	});

	$(document).on("click", "#btnAceptarDeleteLinks", function(e) {
		e.preventDefault();
		eliminarLinks();
	});

}


// MODALES

function mostrarModalAddLinks() {
	$("#modalAddLinks").modal('toggle');
			$(".mensajeError").hide();
		$(".mensajeError").text('');
}

function mostrarModalImagenLink(titleLink, imagen) {
	$('#headerModalImageLinks').text(titleLink);
	$('#bodyModalImageLinks').attr('src', 'images/' + imagen);
	$('#modalImageLinks').modal('toggle');
}

function mostrarModalUpdateLinks(idLink, titleLink, urlLink, active) {
			$(".mensajeError").hide();
		$(".mensajeError").text('');
	$('#titleLinksEdit').val(titleLink);
	$('#urlLinksEdit').val(urlLink);
	$('#idLinksUpdate').val(idLink);

	if (active == 'true') {
		$('#activeEdit').val('1');
	} else {
		$('#activeEdit').val('0');

	}
	$("#modalUpdateLinks").modal('toggle');
}

function mostrarModalUpdatePhotoLinks(idLink) {
	$('#idLinksUpdateImage').val(idLink);
	$("#modalUpdateImageLinks").modal('toggle');
}

function mostrarModalDeleteLinks(idLink) {
	$('#idLinksDelete').val(idLink);
	$("#modalDeleteLinks").modal('toggle');
}


// FUNCIONES CRUD

function anyadirNuevoLinks() {

	let form = new FormData();

	form.append("file", $('#imageLinks')[0].files[0]);
	form.append("titleLinks", $("#titleLinks").val());
	form.append("urlLinks", $("#urlLinks").val());
	form.append("active", $("#active").val());
	form.append("inputUser", $("#inputUser").val());

	$.ajax({
		url: '/management/addLinksData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
//			if (data == '') {
				$('#linksDatatable').DataTable().ajax.reload();
				okMessage();
				$(".alert").text($("#addOkMensaje").val());
//			} else {
//				errorMessage();
//				$(".alert").text($("#addOkMensaje").val());
//			}
		},
		error: function(e) {
			$('#modalAddLinks').modal('show');
			$("#formAddLinks").removeClass('was-validated');
			$(".mensajeError").show();
			var mensajeError ="";

			if (e.responseText == "titleLinkUnique") {
				mensajeError = $('#titleLinkUnique').val();
			}

			$(".mensajeError").text(mensajeError);
		}
	}).done(function() {
		$("#formAddLinks").removeClass('was-validated');
		$('#modalAddLinks').modal('hide');
				$(".mensajeError").hide();
		$(".mensajeError").text('');
		$("#titleLinks").val('');
		$("#imageLinks").val('');
		$("#urlLinks").val('');
		$("#active").val('1');
	});

}

function editarLinks() {

	let form = new FormData();

	form.append("titleLink", $("#titleLinksEdit").val());
	form.append("urlLink", $("#urlLinksEdit").val());
	form.append("idLink", $("#idLinksUpdate").val());
	form.append("active", $("#activeEdit").val());
	form.append("inputUser", $("#inputUser").val());

	$.ajax({
		url: '/management/updateLinksData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
//			if (data == '') {
				$('#linksDatatable').DataTable().ajax.reload();
				okMessage();
				$(".alert").text($("#updateOkMensaje").val());
//			} else {
//				errorMessage();
//				$(".alert").text($("#updateErrorMensaje").val());
//			}
		},
		error: function(e) {
			$('#modalUpdateLinks').modal('show');
			$("#formUpdateLinks").removeClass('was-validated');
			$(".mensajeError").show();
			var mensajeError ="";

			if (e.responseText == "titleLinkUnique") {
				mensajeError = $('#titleLinkUnique').val();
			}

			$(".mensajeError").text(mensajeError);
		}
	}).done(function() {
		$("#formUpdateLinks").removeClass('was-validated');
		$('#modalUpdateLinks').modal('hide');
				$(".mensajeError").hide();
		$(".mensajeError").text('');
		$("#titleLinksEdit").val('');
		$("#idLinksUpdate").val('');
		$("#activeEdit").val('1');
	});
}

function editarImagenLinks() {

	let form = new FormData();

	form.append("file", $('#imageLinksEdit')[0].files[0]);
	form.append("idLink", $("#idLinksUpdateImage").val());
	form.append("inputUser", $("#inputUser").val());

	$.ajax({
		url: '/management/updateImageLinksData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#linksDatatable').DataTable().ajax.reload();
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
		$("#formUpdateImageLinks").removeClass('was-validated');
		$("#imageLinksEdit").val('');
	});
}

function eliminarLinks() {

	let form = new FormData();

	form.append("idLink", $("#idLinksDelete").val());

	$.ajax({
		url: '/management/deleteLinksData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#linksDatatable').DataTable().ajax.reload();
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