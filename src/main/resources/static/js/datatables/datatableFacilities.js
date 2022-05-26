function getDatatableFacilities() {

	let table = $('#facilitiesDatatable').DataTable({
		"sAjaxSource": "/management/getFacilitiesData",
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
				"mData": "idFacility",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true,
			},

			{
				"mData": "nameFacility",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true,
			},
			{
				"mData": "idTechCat",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false,
			},
			{
				"mData": "photoFacility",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "featuresFacility",
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
				"bSortable": true,
			},
			{
				"mData": "date",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true,
			},
			{
				"mData": "active",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true,
			},

			{
				"mData": null,
				"bSortable": false
			}
		],
		"fnCreatedRow": function(row, data, index) {


			// Categorias
			var nameTechCat = $('<p/>', {
				text: data.nameTechCat
			});
			$('td:eq(2)', row).html(nameTechCat);

			// Imagen 					  	
			var verFoto = $('<a/>', {
				text: 'Foto',
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalFacilitiesPhoto(data.photoFacility);
				}
			});
			$('#photoFacilitiesActual').append(verFoto);

			$('td:eq(3)', row).html(verFoto);

			// Características
			var verCaracterísticas = $('<a/>', {
				text: "Características",
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalFeaturesFacility(data.nameFacility, data.featuresFacility);

				}
			});
			$('td:eq(4)', row).html(verCaracterísticas);

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
					mostrarModalUpdateFacilities(data.idFacility, data.nameFacility, data.idTechCat, data.activeTechCat, data.featuresFacility, data.active);
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
					mostrarModalUpdatePhotoFacilities(data.idFacility);
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
					mostrarModalDeleteFacilities(data.idFacility);
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


	$('#facilitiesDatatable thead tr').clone(true).addClass('filters').appendTo('#facilitiesDatatable thead');

	$('#facilitiesDatatable thead tr.filters th').each(function() {
		$(this).off();
		$(this).removeClass('sorting_desc');
		$(this).removeClass('sorting');

	});

	$('#facilitiesDatatable thead tr:eq(1) th.atri').each(function(i) {
		//let title = $(this).text();
		$(this).html('<input style="max-width: 100px;" type="text" />');

		$('input', this).on('keyup change', function() {

			if (table.column(i).search() != this.value) {

				table.column(i).search(this.value).draw();
			}
		});
	});


	$('#facilitiesDatatable thead tr:eq(1) th.selectCat').each(function(i) {
		//let title = $(this).text();
		let listaTechCat = $('#idTechCatSelect');

		$(this).html(listaTechCat);


		$('select', this).on('keyup change', function() {
			if (table.column(2).search() != this.value) {
				//debugger;
				table.column(2).search(this.value).draw();
			}
		});
	});

	$('#facilitiesDatatable thead tr:eq(1) th.selectActive').each(function(i) {

		$(this).html('<select class="form-select"><option value=""><option value="true">Si</option><option value="false">No</option></select>');

		$('select', this).on('keyup change', function() {
			if (table.column(7).search() !== this.value) {

				table.column(7).search(this.value).draw();
			}
		});
	});



	$(document).on("click", "#btnAceptarAddFacilities", function(e) {
		$('#modalAddFacilities').modal('show');

		var form = $("#formAddFacilities");

		if (form[0].checkValidity() === false) {
			e.preventDefault()
			e.stopPropagation()

		} else {

			anyadirNuevoEquipo();
			$('#modalAddFacilities').modal('hide');

		}
		form.addClass('was-validated')


	});

	$(document).on("click", "#btnCancelarAddFacilities", function() {
		$("#formAddFacilities").removeClass('was-validated');
		$("#nameFacility").val('');
		$("#photoFacility").val('');
		$("#idTechCatActiveSelect").val('');
		$("#featuresFacility").val('');
		$("#active").val('1');
	});

	$(document).on("click", "#btnCloseAddFacilities", function() {
		$("#formAddFacilities").removeClass('was-validated');
		$("#nameFacility").val('');
		$("#photoFacility").val('');
		$("#idTechCatActiveSelect").val('');
		$("#featuresFacility").val('');
		$("#active").val('1');
	});

	$(document).on("click", "#btnAceptarUpdateFacilities", function(e) {
		$('#modalUpdateFacilities').modal('show');

		var form = $("#formUpdateFacilities");

		if (form[0].checkValidity() === false) {
			e.preventDefault()
			e.stopPropagation()

		} else {

			editarEquipo();
			$('#modalUpdateFacilities').modal('hide');

		}
		form.addClass('was-validated')


	});

	$(document).on("click", "#btnCancelarUpdateFacilities", function(e) {
		$("#formUpdateFacilities").removeClass('was-validated');

	});

	$(document).on("click", "#btnCloseUpdateFacilities", function(e) {
		$("#formUpdateFacilities").removeClass('was-validated');

	});

	$(document).on("click", "#btnAceptarUpdatePhotoFacilities", function(e) {
		$('#modalUpdatePhotoFacilities').modal('show');

		var form = $("#formUpdatePhotoFacilities");

		if (form[0].checkValidity() === false) {
			e.preventDefault()
			e.stopPropagation()

		} else {

			editarFotoEquipo();
			$('#modalUpdatePhotoFacilities').modal('hide');

		}
		form.addClass('was-validated')


	});

	$(document).on("click", "#btnCancelarUpdatePhotoFacilities", function(e) {
		$("#formUpdatePhotoFacilities").removeClass('was-validated');
		$("#photoFacilityEdit").val('');

	});

	$(document).on("click", "#btnCloseUpdatePhotoFacilities", function(e) {
		$("#formUpdatePhotoFacilities").removeClass('was-validated');
		$("#photoFacilityEdit").val('');

	});


	$(document).on("click", "#btnAceptarDeleteFacilities", function(e) {
		e.preventDefault();
		eliminarEquipo();

	});




}


function mostrarModalFacilitiesPhoto(foto) {
	$('#bodyModalPhotoFacilities').attr('src', 'images/' + foto);
	$('#modalPhotoFacilities').modal('toggle');
}

function mostrarModalFeaturesFacility(nameFacility, featuresFacility) {
	$("#modalFeaturesFacility").modal('toggle');
	$("#headerFeaturesFacility").text(nameFacility);
	$("#bodyFeaturesFacility").text(featuresFacility);
}


function mostrarModalAddFacilities() {
	$("#modalAddFacilities").modal('toggle');
}

function mostrarModalUpdateFacilities(idFacility, nameFacility, idTechcat, activeTechCat, featuresFacility, active) {

	$('#nameFacilityEdit').val(nameFacility);
	if (activeTechCat == "true") {
		$('#idTechCatActiveSelectEdit').val(idTechcat);
	} else {
		$('#idTechCatActiveSelectEdit').val('');
	}


	$('#featuresFacilityEdit').val(featuresFacility);
	$('#idFacilityUpdate').val(idFacility);
	if (active == 'true') {
		$('#activeEdit').val('1');
	} else {
		$('#activeEdit').val('0');

	}

	$("#modalUpdateFacilities").modal('toggle');
}

function mostrarModalUpdatePhotoFacilities(idFacility) {
	$('#idFacilityPhotoUpdate').val(idFacility);
	$("#modalUpdatePhotoFacilities").modal('toggle');
}

function mostrarModalDeleteFacilities(idFacility) {
	$('#idFacilitiesDelete').val(idFacility);
	$("#modalDeleteFacilities").modal('toggle');
}

function anyadirNuevoEquipo() {

	let form = new FormData();

	form.append("file", $('#photoFacility')[0].files[0]);
	form.append("nameFacility", $("#nameFacility").val());
	form.append("idTechCat", $("#idTechCatActiveSelect").val());
	form.append("featuresFacility", $("#featuresFacility").val());

	form.append("active", $("#active").val());

	$.ajax({
		url: '/management/addFacilitiesData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#facilitiesDatatable').DataTable().ajax.reload();
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
		$("#formAddFacilities").removeClass('was-validated');
		$("#nameFacility").val('');
		$("#photoFacility").val('');
		$("#idTechCatActiveSelect").val('');
		$("#featuresFacility").val('');
		$("#active").val('1');
	});
}
function editarEquipo() {

	let form = new FormData();

	form.append("nameFacility", $("#nameFacilityEdit").val());
	form.append("idTechCat", $("#idTechCatActiveSelectEdit").val());
	form.append("featuresFacility", $("#featuresFacilityEdit").val());
	form.append("idFacility", $("#idFacilityUpdate").val());
	form.append("active", $("#activeEdit").val());

	$.ajax({
		url: '/management/updateFacilitiesData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#facilitiesDatatable').DataTable().ajax.reload();
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
		
		$("#formUpdateFacilities").removeClass('was-validated');
		$("#nameFacilityEdit").val('');
		$("#idTechCatEdit").val('');
		$("#featuresFacilityEdit").val('');
		$("#idFacilityUpdate").val('');
		$("#activeEdit").val('1');
	});
}

function editarFotoEquipo() {

	let form = new FormData();

	form.append("file", $('#photoFacilityEdit')[0].files[0]);
	form.append("idFacility", $("#idFacilityPhotoUpdate").val());

	$.ajax({
		url: '/management/updatePhotoFacilitiesData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#facilitiesDatatable').DataTable().ajax.reload();
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
		
		$('#photoFacilityEdit').val('');
		$("#formUpdatePhotoFacilities").removeClass('was-validated');
	});
}

function eliminarEquipo() {

	let form = new FormData();

	form.append("idFacility", $("#idFacilitiesDelete").val());

	$.ajax({
		url: '/management/deleteFacilitiesData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#facilitiesDatatable').DataTable().ajax.reload();
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
