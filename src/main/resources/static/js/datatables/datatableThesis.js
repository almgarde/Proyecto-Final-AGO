function getDatatableThesis() {

	let table = $('#thesisDatatable').DataTable({
		"sAjaxSource": "/management/getThesisData",
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

				"mRender": function(data, type, row) {
					return row.directorThesis + row.coDirectorThesis;
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
				text: $('#coverPageThesisLabel').val(),
				href: '',
				click: function(e) {
					e.preventDefault();
					//$('#newsDatatable').DataTable().ajax.reload();
					mostrarModalCoverPageThesis(data.titleThesis, data.coverPageThesis);
				}
			});

			$('td:eq(3)', row).html(verPortada);


			// Directores 			
			var verDir = $('<a/>', {
				text: $('#directorsThesisLabel').val(),
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalDirectoresData(data.titleThesis, data.directorThesis, data.coDirectorThesis);
				}
			});

			$('td:eq(5)', row).html(verDir);


			// Enlace 
			var irEnlace = $('<a/>', {
				text: $('#urlThesisLabel').val(),
				href: data.urlThesis,
				click: function(e) {
					e.preventDefault();
					window.open(this, '_blank');
					return false;
				}
			});

			$('td:eq(6)', row).html(irEnlace);

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
			$('td:eq(9)', row).html(activo);


			// Acciones 	
			var liAccion1 = $('<li/>');
			var accion1 = $('<a/>', {
				text: $('#linkEditar').val(),
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
				text: $('#linkCambiarFoto').val(),
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
				text: $('#linkEliminar').val(),
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
			var aPrincipal = $('<a/>').addClass('nav-link').addClass('dropdown-toggle').attr('href', '#').attr('id', 'listaAcciones').attr('role', 'button').attr('data-bs-toggle', 'dropdown').attr('aria-expanded', 'false').text($('#linkAcciones').val());
			var uPrincipal = $('<u/>').addClass('dropdown-menu').attr('aria-labelledby', 'listaAcciones').css('text-decoration', 'none');

			liPrincipal.append(aPrincipal);
			aPrincipal.append(uPrincipal);
			uPrincipal.append(liAccion1);
			uPrincipal.append(liAccion2);
			uPrincipal.append(liAccion3);

			divAcciones.append(liPrincipal);

			$('td:eq(10)', row).html(divAcciones);


			$('td:eq(0)', row).attr("data-label", "ID");
			$('td:eq(1)', row).attr("data-label", "Doctor");
			$('td:eq(2)', row).attr("data-label", "Título");
			$('td:eq(3)', row).attr("data-label", "Portada");
			$('td:eq(4)', row).attr("data-label", "Fecha de defensa");
			$('td:eq(5)', row).attr("data-label", "Directores");
			$('td:eq(6)', row).attr("data-label", "URL");
			$('td:eq(7)', row).attr("data-label", "Admin");
			$('td:eq(8)', row).attr("data-label", "Fecha");
			$('td:eq(9)', row).attr("data-label", "Activo");
			$('td:eq(10)', row).attr("data-label", "");

		}
	});


	// DATEPICKER

	activateDatePicker();


	// FILTROS

	$('#thesisDatatable thead tr').clone(true).addClass('filters').appendTo('#thesisDatatable thead');

	$('#thesisDatatable thead tr.filters th').each(function() {
		$(this).off();
		$(this).removeClass('sorting_desc');
		$(this).removeClass('sorting');
	});

	$('#thesisDatatable thead tr:eq(1) th.atri').each(function(i) {

		$(this).html('<input type="text" />');

		$('input', this).on('keyup change', function() {
			if (table.column(i).search() != this.value) {
				table.column(i).search(this.value).draw();
			}
		});
	});


	$('#thesisDatatable thead tr:eq(1) th.selectActive').each(function(i) {

		$(this).html('<select class="form-select"><option value=""><option value="true">' + $('#activoSi').val() + '</option><option value="false">' + $('#activoNo').val() + '</option></select>');

		$('select', this).on('keyup change', function() {
			if (table.column(10).search() !== this.value) {
				table.column(10).search(this.value).draw();
			}
		});
	});


	// BOTONES

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
		$('#titleThesisMaxContador').html(max_chars_content);	
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
		$('#titleThesisMaxContador').html(max_chars_content);
				$("#doctorThesis").val('');
		$("#titleThesis").val('');
		$("#coverPageThesis").val('');
		$("#dateDefenseThesis").val('');
		$("#directorThesis").val('');
		$("#coDirectorThesis").val('');
		$("#urlThesis").val('');
		$("#active").val('1');
	});
	
		$("#modalAddThesis").on('hide.bs.modal', function() {
		$("#formAddThesis").removeClass('was-validated');

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

	$("#modalUpdateThesis").on('hide.bs.modal', function() {
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


	$("#modalUpdateCoverPageThesis").on('hide.bs.modal', function() {
		$("#formUpdateCoverPageThesis").removeClass('was-validated');
});


	$(document).on("click", "#btnAceptarDeleteThesis", function(e) {
		e.preventDefault();
		eliminarTesis();
	});


// MÁXIMO CARACTERES TEXTAREA

	var max_chars_content = 3500;
	

$('#titleThesisMaxContador').html(max_chars_content);

	$('#titleThesis').keyup(function() {
		var chars = $(this).val().length;
		var diff = max_chars_content - chars;
		$('#titleThesisMaxContador').html(diff);

	});
	

	$('#titleThesisEdit').keyup(function() {
		var chars = $(this).val().length;
		var diff = max_chars_content - chars;
		$('#titleThesisMaxContadorEdit').html(diff);

	});
}


// MODALES

function mostrarModalDirectoresData(titulo, director, coDirector) {
	$("#headerDirectorsThesisData").text(titulo);
	$("#modalDirectorThesis").text(director);
	$("#modalCoDirectorThesis").text(coDirector);
	$("#modalDirectorsThesisData").modal('toggle');
}


function mostrarModalAddThesis() {
	$("#modalAddThesis").modal('toggle');

}

function mostrarModalCoverPageThesis(titleThesis, portada) {
	$('#headerCoverPageThesis').text(titleThesis);
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

var charsInit = titleThesis.length;
	var diffInit = 3500 - charsInit;
	$('#titleThesisMaxContadorEdit').html(diffInit);
	
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


// FUNCIONES CRUD

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
	form.append("inputUser", $("#inputUser").val());

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
	form.append("inputUser", $("#inputUser").val());

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
				$(".alert").text($('#updateOkMensaje').val());
			} else {
				errorMessage();
				$(".alert").text($('#updateErrorMensaje').val());
			}
		},
		error: function(data) {
			errorMessage();
			$(".alert").text($('#updateErrorMensaje').val());
		}
	}).done(function() {
		$("#formUpdateThesis").removeClass('was-validated');


	});
}

function editarPortadaTesis() {

	let form = new FormData();

	form.append("idThesis", $("#idThesisUpdateCoverPage").val());
	form.append("inputUser", $("#inputUser").val());
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
				$(".alert").text($('#updateOkMensaje').val());
			} else {
				errorMessage();
				$(".alert").text($('#updateErrorMensaje').val());
			}
		},
		error: function(data) {
			errorMessage();
			$(".alert").text($('#updateErrorMensaje').val());
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
				$(".alert").text($('#deleteOkMensaje').val());
			} else {
				errorMessage();
				$(".alert").text($('#deleteErrorMensaje').val());
			}
		},
		error: function(data) {
			errorMessage();
			$(".alert").text($('#deleteErrorMensaje').val());
		}
	});
}