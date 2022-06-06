function getDatatableFacilities() {

	let table = $('#facilitiesDatatable').DataTable({
		"sAjaxSource": "/management/getFacilitiesData",
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
				text: $('#photoFacilityLabel').val(),
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalFacilitiesPhoto(data.nameFacility, data.photoFacility);
				}
			});
			
			$('td:eq(3)', row).html(verFoto);


			// Características
			var verCaracterísticas = $('<a/>', {
				text: $('#featuresFacilityLabel').val(),
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalFeaturesFacility(data.nameFacility, data.featuresFacility);
				}
			});

			$('td:eq(4)', row).html(verCaracterísticas);


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
					mostrarModalUpdateFacilities(data.idFacility, data.nameFacility, data.idTechCat, data.activeTechCat, data.featuresFacility, data.active);
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
					mostrarModalUpdatePhotoFacilities(data.idFacility);
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
					mostrarModalDeleteFacilities(data.idFacility);
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
			$('td:eq(1)', row).attr("data-label", "Nombre");
			$('td:eq(2)', row).attr("data-label", "Categoría");
			$('td:eq(3)', row).attr("data-label", "Foto");
			$('td:eq(4)', row).attr("data-label", "Características");
			$('td:eq(5)', row).attr("data-label", "Admin");
			$('td:eq(6)', row).attr("data-label", "Fecha");
			$('td:eq(7)', row).attr("data-label", "Activo");
			$('td:eq(8)', row).attr("data-label", "");
		}
	});


	// FILTROS

	$('#facilitiesDatatable thead tr').clone(true).addClass('filters').appendTo('#facilitiesDatatable thead');

	$('#facilitiesDatatable thead tr.filters th').each(function() {
		$(this).off();
		$(this).removeClass('sorting_desc');
		$(this).removeClass('sorting');
	});

	$('#facilitiesDatatable thead tr:eq(1) th.atri').each(function(i) {

		$(this).html('<input type="text" />');

		$('input', this).on('keyup change', function() {
			if (table.column(i).search() != this.value) {
				table.column(i).search(this.value).draw();
			}
		});
	});


	$('#facilitiesDatatable thead tr:eq(1) th.selectCat').each(function(i) {

		let listaTechCat = $('#idTechCatSelect');
		$(this).html(listaTechCat);

		$('select', this).on('keyup change', function() {
			if (table.column(2).search() != this.value) {
				table.column(2).search(this.value).draw();
			}
		});
	});

	$('#facilitiesDatatable thead tr:eq(1) th.selectActive').each(function(i) {

		$(this).html('<select class="form-select"><option value=""><option value="true">' + $('#activoSi').val() + '</option><option value="false">' + $('#activoNo').val() + '</option></select>');

		$('select', this).on('keyup change', function() {
			if (table.column(7).search() !== this.value) {
				table.column(7).search(this.value).draw();
			}
		});
	});


	// BOTONES

	$(document).on("click", "#btnAceptarAddFacilities", function(e) {

		$('#modalAddFacilities').modal('show');
		var form = $("#formAddFacilities");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			anyadirNuevoEquipo();
			$('#modalAddFacilities').modal('hide');			
		}
		form.addClass('was-validated');

	});

	$(document).on("click", "#btnCancelarAddFacilities", function() {
		$("#formAddFacilities").removeClass('was-validated');
		$('#featuresFacilitiesMaxContador').html(max_chars_content);
		$("#nameFacility").val('');
		$("#photoFacility").val('');
		$("#idTechCatActiveSelect").val('');
		$("#featuresFacility").val('');
		$("#active").val('1');
	});

	$(document).on("click", "#btnCloseAddFacilities", function() {
		$("#formAddFacilities").removeClass('was-validated');
		$('#featuresFacilitiesMaxContador').html(max_chars_content);
				$(".mensajeError").hide();
		$(".mensajeError").text('');
		$("#nameFacility").val('');
		$("#photoFacility").val('');
		$("#idTechCatActiveSelect").val('');
		$("#featuresFacility").val('');
		$("#active").val('1');
	});
	
	$("#modalAddFacilities").on('hide.bs.modal', function() {
		$("#formAddFacilities").removeClass('was-validated');

	});

	$(document).on("click", "#btnAceptarUpdateFacilities", function(e) {

		$('#modalUpdateFacilities').modal('show');
		var form = $("#formUpdateFacilities");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
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
	
		$("#modalUpdateFacilities").on('hide.bs.modal', function() {
		$("#formUpdateFacilities").removeClass('was-validated');

	});

	$(document).on("click", "#btnAceptarUpdatePhotoFacilities", function(e) {

		$('#modalUpdatePhotoFacilities').modal('show');
		var form = $("#formUpdatePhotoFacilities");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			editarFotoEquipo();
			$('#modalUpdatePhotoFacilities').modal('hide');
		}
		form.addClass('was-validated');

	});

	$(document).on("click", "#btnCancelarUpdatePhotoFacilities", function(e) {
		$("#formUpdatePhotoFacilities").removeClass('was-validated');
		$("#photoFacilityEdit").val('');
	});

	$(document).on("click", "#btnCloseUpdatePhotoFacilities", function(e) {
		$("#formUpdatePhotoFacilities").removeClass('was-validated');
		$("#photoFacilityEdit").val('');
	});
	
		$("#modalUpdatePhotoFacilities").on('hide.bs.modal', function() {
		$("#formUpdateFacilities").removeClass('was-validated');
	});

	$(document).on("click", "#btnAceptarDeleteFacilities", function(e) {
		e.preventDefault();
		eliminarEquipo();
	});
	
	
	// MÁXIMO CARACTERES TEXTAREA

	var max_chars_content = 3500;
	

$('#featuresFacilitiesMaxContador').html(max_chars_content);

	$('#featuresFacility').keyup(function() {
		var chars = $(this).val().length;
		var diff = max_chars_content - chars;
		$('#featuresFacilitiesMaxContador').html(diff);

	});
	

	$('#featuresFacilityEdit').keyup(function() {
		var chars = $(this).val().length;
		var diff = max_chars_content - chars;
		$('#featuresFacilitiesMaxContadorEdit').html(diff);

	});

}


// MODALES

function mostrarModalFacilitiesPhoto(nameFacility, foto) {
	$("#headerImageFacility").text(nameFacility);
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
	
	var charsInit = featuresFacility.length;
	var diffInit = 3500 - charsInit;
	$('#featuresFacilitiesMaxContadorEdit').html(diffInit);
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


// FUNCIONES CRUD

function anyadirNuevoEquipo() {

	let form = new FormData();

	form.append("file", $('#photoFacility')[0].files[0]);
	form.append("nameFacility", $("#nameFacility").val());
	form.append("idTechCat", $("#idTechCatActiveSelect").val());
	form.append("featuresFacility", $("#featuresFacility").val());
	form.append("active", $("#active").val());
	form.append("inputUser", $("#inputUser").val());

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
				$(".alert").text($('#addOkMensaje').val());
			} else {
				errorMessage();
				$(".alert").text($('#addErrorMensaje').val());
			}
		},
		error: function(data) {
			errorMessage();
			$(".alert").text($('#addErrorMensaje').val());
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
	form.append("inputUser", $("#inputUser").val());
	

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
	form.append("inputUser", $("#inputUser").val());

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
