function getDatatableLinks() {
	let table = $('#linksDatatable').DataTable({
		"sAjaxSource": "/management/getLinksData",
		"sAjaxDataProp": "",
		"sDom":"ltipr",
		"orderCellsTop": true,
		"fixedHeader": false,
		"language": {
			"lengthMenu": "Mostrando _MENU_ entradas",
			"emptyTable": "Sin datos en esta tabla",
			"zeroRecords": "No se han encontrado coincidencias",
			"info": "Mostrando _START_ a _END_ de _TOTAL_ entradas",
			"infoEmpty": "Mostrando 0 a 0 de 0 entradas",
			"infoFiltered": "(filtrado de _MAX_ entradas)",
			"loadingRecords": "Cargando...",
			"paginate": {
				"first": "Primero",
				"last": "Ultimo",
				"next": "Siguiente",
				"previous": "Anterior"
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
				text: 'Portada',
				href: '',
				click: function(e) {
					e.preventDefault();
					//$('#newsDatatable').DataTable().ajax.reload();
					mostrarModalImagenLink(data.imageLink);
				}
			});
			$('td:eq(2)', row).html(verImagen);

			// Enlace 		

			var irEnlace = $('<a/>', {
				text: 'Enlace',
				href: data.urlLink,
				click: function(e) {
					e.preventDefault();
					//					$(this).attr('href', data.urlThesis);
					window.open(this, '_blank');
					return false;
				}
			});
			$('td:eq(3)', row).html(irEnlace);

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
			$('td:eq(6)', row).html(activo);

			// Acciones 	 	
			var liAccion1 = $('<li/>');
			var accion1 = $('<a/>', {
				text: 'Editar',
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
				text: 'Cambiar foto',
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
				text: 'Eliminar',
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
			var aPrincipal = $('<a/>').addClass('nav-link').addClass('dropdown-toggle').attr('href', '#').attr('id', 'listaAcciones').attr('role', 'button').attr('data-bs-toggle', 'dropdown').attr('aria-expanded', 'false').text('Acciones');
			var uPrincipal = $('<u/>').addClass('dropdown-menu').attr('aria-labelledby', 'listaAcciones').css('text-decoration', 'none');

			liPrincipal.append(aPrincipal);
			aPrincipal.append(uPrincipal);
			uPrincipal.append(liAccion1);
			uPrincipal.append(liAccion2);
			uPrincipal.append(liAccion3);

			divAcciones.append(liPrincipal);

			$('td:eq(7)', row).html(divAcciones);
		}
	});


	$('#linksDatatable thead tr').clone(true).addClass('filters').appendTo('#linksDatatable thead');

	$('#linksDatatable thead tr.filters th').each(function() {
		$(this).off();
		$(this).removeClass('sorting_desc');
		$(this).removeClass('sorting');

	});

	$('#linksDatatable thead tr:eq(1) th.atri').each(function(i) {
		//let title = $(this).text();
		$(this).html('<input style="max-width: 100px;" type="text" />');

		$('input', this).on('keyup change', function() {

			if (table.column(i).search() != this.value) {

				table.column(i).search(this.value).draw();
			}
		});
	});

	$('#linksDatatable thead tr:eq(1) th.selectActive').each(function(i) {

		$(this).html('<select class="form-select"><option value=""><option value="true">Si</option><option value="false">No</option></select>');

		$('select', this).on('keyup change', function() {
			if (table.column(5).search() !== this.value) {

				table.column(5).search(this.value).draw();
			}
		});
	});

	$(document).on("click", "#btnAceptarAddLinks", function(e) {
		$('#modalAddLinks').modal('show');

		var form = $("#formAddLinks");

		if (form[0].checkValidity() === false) {
			e.preventDefault()
			e.stopPropagation()

		} else {

			anyadirNuevoLinks();
			$('#modalAddLinks').modal('hide');

		}
		form.addClass('was-validated')


	});

	$(document).on("click", "#btnCancelarAddLinks", function() {
		$("#formAddLinks").removeClass('was-validated');
		$("#titleLinks").val('');
		$("#imageLinks").val('');
		$("#urlLinks").val('');
		$("#active").val('1');
	});

	$(document).on("click", "#btnCloseAddLinks", function() {
		$("#formAddLinks").removeClass('was-validated');
		$("#titleLinks").val('');
		$("#imageLinks").val('');
		$("#urlLinks").val('');
		$("#active").val('1');

	});

	$(document).on("click", "#btnAceptarUpdateLinks", function(e) {
		$('#modalUpdateLinks').modal('show');

		var form = $("#formUpdateLinks");

		if (form[0].checkValidity() === false) {
			e.preventDefault()
			e.stopPropagation()

		} else {

			editarLinks();
			$('#modalUpdateLinks').modal('hide');

		}
		form.addClass('was-validated')


	});

	$(document).on("click", "#btnCancelarUpdateLinks", function(e) {
		$("#formUpdateLinks").removeClass('was-validated');

	});

	$(document).on("click", "#btnCloseUpdateLinks", function(e) {
		$("#formUpdateLinks").removeClass('was-validated');

	});


	$(document).on("click", "#btnAceptarUpdateImageLinks", function(e) {
		$('#modalUpdateImageLinks').modal('show');

		var form = $("#formUpdateImageLinks");

		if (form[0].checkValidity() === false) {
			e.preventDefault()
			e.stopPropagation()

		} else {

			editarImagenLinks();
			$('#modalUpdateImageLinks').modal('hide');

		}
		form.addClass('was-validated')


	});

	$(document).on("click", "#btnCancelarUpdateImageLinks", function(e) {
		$("#formUpdateImageLinks").removeClass('was-validated');
		$("#imageLinksEdit").val('');

	});

	$(document).on("click", "#btnCloseUpdateImageLinks", function(e) {
		$("#formUpdateImageLinks").removeClass('was-validated');
		$("#imageLinksEdit").val('');

	});

	$(document).on("click", "#btnAceptarDeleteLinks", function(e) {
		e.preventDefault();
		eliminarLinks();

	});

}

function mostrarModalAddLinks() {
	$("#modalAddLinks").modal('toggle');
}

function mostrarModalImagenLink(imagen) {
	$('#bodyModalImageLinks').attr('src', 'images/' + imagen);
	$('#modalImageLinks').modal('toggle');
}

function mostrarModalUpdateLinks(idLink, titleLink, urlLink, active) {

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


function anyadirNuevoLinks() {

	let form = new FormData();

	form.append("file", $('#imageLinks')[0].files[0]);
	form.append("titleLinks", $("#titleLinks").val());
	form.append("urlLinks", $("#urlLinks").val());
	form.append("active", $("#active").val());

	$.ajax({
		url: '/management/addLinksData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#linksDatatable').DataTable().ajax.reload();
				okMessage();
				$(".alert").text('Bieeen');
			} else {
				errorMessage();
				$(".alert").text('Joooo');
			}

		},
		error: function(data) {
			errorMessage();
			$(".alert").text('Joooo');
		}
	}).done(function() {

		$("#formAddLinks").removeClass('was-validated');
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

	$.ajax({
		url: '/management/updateLinksData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#linksDatatable').DataTable().ajax.reload();
				okMessage();
				$(".alert").text('Bieeen');
			} else {
				errorMessage();
				$(".alert").text('Joooo');
			}

		},
		error: function(data) {
			errorMessage();
			$(".alert").text('Joooo');
		}
	}).done(function() {

		$("#formUpdateLinks").removeClass('was-validated');
		$("#titleLinksEdit").val('');
		$("#idLinksUpdate").val('');
		$("#activeEdit").val('1');
	});
}

function editarImagenLinks() {

	let form = new FormData();

	form.append("file", $('#imageLinksEdit')[0].files[0]);
	form.append("idLink", $("#idLinksUpdateImage").val());


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
				$(".alert").text('Bieeen');
			} else {
				errorMessage();
				$(".alert").text('Joooo');
			}

		},
		error: function(data) {
			errorMessage();
			$(".alert").text('Joooo');
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
				$(".alert").text('Bieeen');
			} else {
				errorMessage();
				$(".alert").text('Joooo');
			}

		},
		error: function(data) {
			errorMessage();
			$(".alert").text('Joooo');
		}
	}).done(function() {


	});
}