function getDatatableThesis() {
	let table = $('#thesisDatatable').DataTable({
		"sAjaxSource": "/management/getThesisData",
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
				"mData": "idThesis",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true
			},
			{
				"mData": "doctorThesis",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true
			},

			{
				"mData": "titleThesis",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "coverPageThesis",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "dateDefenseThesis",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true
			},
			{
				"mData": "directorThesis",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "coDirectorThesis",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "urlThesis",
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

			// Portada 		

			var verPortada = $('<a/>', {
				text: 'Portada',
				href: '',
				click: function(e) {
					e.preventDefault();
					//$('#newsDatatable').DataTable().ajax.reload();
					mostrarModalCoverPageThesis(data.coverPageThesis);
				}
			});
			$('td:eq(3)', row).html(verPortada);

			// Enlace 		

			var irEnlace = $('<a/>', {
				text: 'Enlace',
				href: data.urlThesis,
				click: function(e) {
					e.preventDefault();
					//					$(this).attr('href', data.urlThesis);
					window.open(this, '_blank');
					return false;
				}
			});
			$('td:eq(7)', row).html(irEnlace);

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
			$('td:eq(10)', row).html(activo);
			
			// Acciones 	
			
			var liAccion1 = $('<li/>');
			var accion1 = $('<a/>', {
				text: 'Editar',
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalUpdateThesis(data.idThesis, data.doctorThesis, data.titleThesis, data.dateDefenseThesis, data.directorThesis, data.coDirectorThesis, data.urlThesis, data.active);
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
					mostrarModalUpdateCoverPageThesis(data.idThesis);
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
					mostrarModalDeleteThesis(data.idThesis);
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

			$('td:eq(11)', row).html(divAcciones);
		}
	});
	
	

	

	$('#thesisDatatable thead tr').clone(true).addClass('filters').appendTo('#thesisDatatable thead');

	$('#thesisDatatable thead tr.filters th').each(function() {
		$(this).off();
		$(this).removeClass('sorting_desc');
		$(this).removeClass('sorting');

	});

	$('#thesisDatatable thead tr:eq(1) th.atri').each(function(i) {
		//let title = $(this).text();
		$(this).html('<input style="max-width: 100px;" type="text" />');

		$('input', this).on('keyup change', function() {

			if (table.column(i).search() != this.value) {

				table.column(i).search(this.value).draw();
			}
		});
	});
	

	$('#thesisDatatable thead tr:eq(1) th.selectActive').each(function(i) {

		$(this).html('<select class="form-select"><option value=""><option value="true">Si</option><option value="false">No</option></select>');

		$('select', this).on('keyup change', function() {
			if (table.column(10).search() !== this.value) {

				table.column(10).search(this.value).draw();
			}
		});
	});

activateDatePicker();

	$(document).on("click", "#btnAceptarAddThesis", function(e) {
				$('#modalAddThesis').modal('show');
		var form = $("#formAddThesis");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();

		} else {

			anyadirNuevaTesis();
			$('#modalAddThesis').modal('hide');

		}
		form.addClass('was-validated');
		

	});
	
							$(document).on("click", "#btnCancelarAddThesis", function() {
$("#formAddThesis").removeClass('was-validated');
		$("#doctorThesis").val('');
		$("#titleThesis").val('');
		$("#coverPageThesis").val('');
		$("#dateDefenseThesis").val('');
		$("#directorThesis").val('');
		$("#coDirectorThesis").val('');
		$("#urlThesis").val('');
		$("#active").val('1');
});

		$(document).on("click", "#btnCloseAddThesis", function() {
$("#formAddThesis").removeClass('was-validated');
		$("#doctorThesis").val('');
		$("#titleThesis").val('');
		$("#coverPageThesis").val('');
		$("#dateDefenseThesis").val('');
		$("#directorThesis").val('');
		$("#coDirectorThesis").val('');
		$("#urlThesis").val('');
		$("#active").val('1');

	});
	
	$(document).on("click", "#btnAceptarUpdateThesis", function(e) {
						$('#modalUpdateThesis').modal('show');
		var form = $("#formUpdateThesis");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();

		} else {

			editarTesis();
			$('#modalUpdateThesis').modal('hide');

		}
		form.addClass('was-validated');
		

	});
	
	$(document).on("click", "#btnCancelarUpdateThesis", function(e) {
$("#formUpdateThesis").removeClass('was-validated');

	});
	
		$(document).on("click", "#btnCloseUpdateThesis", function(e) {
$("#formUpdateThesis").removeClass('was-validated');

	});

	
	$(document).on("click", "#btnAceptarUpdateCoverPageThesis", function(e) {
						$('#modalUpdateCoverPageThesis').modal('show');
		var form = $("#formUpdateCoverPageThesis");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();

		} else {

			editarPortadaTesis();
			$('#modalUpdateCoverPageThesis').modal('hide');

		}
		form.addClass('was-validated');
		
		

	});
	
	$(document).on("click", "#btnCancelarUpdateCoverPageThesis", function(e) {
$("#formUpdateCoverPageThesis").removeClass('was-validated');
$("#coverPageThesisEdit").val('');

	});
	
		$(document).on("click", "#btnCloseUpdateCoverPageThesis", function(e) {
$("#formUpdateCoverPageThesis").removeClass('was-validated');
$("#coverPageThesisEdit").val('');

	});

	$(document).on("click", "#btnAceptarDeleteThesis", function(e) {
		e.preventDefault();
		eliminarTesis();

	});

}

function mostrarModalAddThesis() {
	$("#modalAddThesis").modal('toggle');
}

function mostrarModalCoverPageThesis(portada) {
	$('#bodyModalCoverPageThesis').attr('src', 'images/' + portada);
	$('#modalCoverPageThesis').modal('toggle');
}

function mostrarModalUpdateThesis(idThesis, doctorThesis, titleThesis, dateDefenseThesis, directorThesis, coDirectorThesis, urlThesis, active) {
$('#idThesisUpdate').val(idThesis);
	$('#doctorThesisEdit').val(doctorThesis);
	$('#titleThesisEdit').val(titleThesis);
	$('#dateDefenseThesisEdit').val(dateDefenseThesis);
	$('#directorThesisEdit').val(directorThesis);
	$('#coDirectorThesisEdit').val(coDirectorThesis);
	$('#urlThesisEdit').val(urlThesis);
	
	
	if (active == 'true') {
		$('#activeEdit').val('1');
	} else {
		$('#activeEdit').val('0');
		
	}
	
	$("#modalUpdateThesis").modal('toggle');
}

function mostrarModalUpdateCoverPageThesis(idThesis) {
	$('#idThesisUpdateCoverPage').val(idThesis);
	$("#modalUpdateCoverPageThesis").modal('toggle');
}

function mostrarModalDeleteThesis(idThesis) {
		$('#idThesisDelete').val(idThesis);
	$("#modalDeleteThesis").modal('toggle');
}


function anyadirNuevaTesis() {

	let form = new FormData();

	form.append("file", $('#coverPageThesis')[0].files[0]);
	form.append("doctorThesis", $("#doctorThesis").val());
	form.append("titleThesis", $("#titleThesis").val());
	form.append("dateDefenseThesis", $("#dateDefenseThesis").val());
	form.append("directorThesis", $("#directorThesis").val());
	form.append("coDirectorThesis", $("#coDirectorThesis").val());
	form.append("urlThesis", $("#urlThesis").val());
	form.append("active", $("#active").val());


	$.ajax({
		url: '/management/addThesisData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,

		success: function(data) {
			if (data == '') {
				$('#thesisDatatable').DataTable().ajax.reload();
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
		
		$("#formAddThesis").removeClass('was-validated');
		$("#doctorThesis").val('');
		$("#titleThesis").val('');
		$("#coverPageThesis").val('');
		$("#dateDefenseThesis").val('');
		$("#directorThesis").val('');
		$("#coDirectorThesis").val('');
		$("#urlThesis").val('');
		$("#active").val('1');

	});
}


function editarTesis() {

	let form = new FormData();

	form.append("idThesis", $("#idThesisUpdate").val());
	form.append("doctorThesis", $("#doctorThesisEdit").val());
	form.append("titleThesis", $("#titleThesisEdit").val());
	form.append("dateDefenseThesis", $("#dateDefenseThesisEdit").val());
	form.append("directorThesis", $("#directorThesisEdit").val());
	form.append("coDirectorThesis", $("#coDirectorThesisEdit").val());
	form.append("urlThesis", $("#urlThesisEdit").val());
	form.append("active", $("#activeEdit").val());


	$.ajax({
		url: '/management/updateThesisData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,

		success: function(data) {
			if (data == '') {
				$('#thesisDatatable').DataTable().ajax.reload();
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
		
$("#formUpdateThesis").removeClass('was-validated');

	});
}

function editarPortadaTesis() {

	let form = new FormData();

	form.append("idThesis", $("#idThesisUpdateCoverPage").val());
form.append("file", $('#coverPageThesisEdit')[0].files[0]);

	$.ajax({
		url: '/management/updateCoverPageThesisData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,

		success: function(data) {
			if (data == '') {
				$('#thesisDatatable').DataTable().ajax.reload();
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
		
$("#formUpdateCoverPageThesis").removeClass('was-validated');
$("#coverPageThesisEdit").val('');
	});
}

function eliminarTesis() {

	let form = new FormData();

	form.append("idThesis", $("#idThesisDelete").val());


	$.ajax({
		url: '/management/deleteThesisData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,

		success: function(data) {
			if (data == '') {
				$('#thesisDatatable').DataTable().ajax.reload();
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