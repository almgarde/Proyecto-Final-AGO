function getDatatableProjects() {

	let table = $('#projectsDatatable').DataTable({
		"sAjaxSource": "/management/getProjectsData",
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
				"mData": "idProject",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true
			},
			{
				"mData": "titleProject",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},

			{
				"mData": "imageProject",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "descriptionProject",
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
				text: $('#imageProjectLabel').val(),
				href: '',
				click: function(e) {
					e.preventDefault();
					//$('#newsDatatable').DataTable().ajax.reload();
					mostrarModalProjectsImage(data.titleProject, data.imageProject);
				}
			});

			$('td:eq(2)', row).html(verImage);


			// Abstract
			var verAbstract = $('<a/>', {
				text: $('#descriptionProjectLabel').val(),
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalProjectsDescription(data.titleProject, data.descriptionProject);
				}
			});

			$('td:eq(3)', row).html(verAbstract);


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
					mostrarModalUpdateProjects(data.idProject, data.titleProject, data.descriptionProject, data.active);
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
					mostrarModalUpdateImageProjects(data.idProject);
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
					mostrarModalDeleteProjects(data.idProject);
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
			$('td:eq(1)', row).attr("data-label", "Titulo");
			$('td:eq(2)', row).attr("data-label", "Imagen");
			$('td:eq(3)', row).attr("data-label", "Descripción");
			$('td:eq(4)', row).attr("data-label", "Admin");
			$('td:eq(5)', row).attr("data-label", "Fecha");
			$('td:eq(6)', row).attr("data-label", "Activo");
			$('td:eq(7)', row).attr("data-label", "");

		}
	});



	// FILTROS

	$('#projectsDatatable thead tr').clone(true).addClass('filters').appendTo('#projectsDatatable thead');

	$('#projectsDatatable thead tr.filters th').each(function() {
		$(this).off();
		$(this).removeClass('sorting_desc');
		$(this).removeClass('sorting');
	});

	$('#projectsDatatable thead tr:eq(1) th.atri').each(function(i) {

		$(this).html('<input type="text" />');

		$('input', this).on('keyup change', function() {
			if (table.column(i).search() != this.value) {
				table.column(i).search(this.value).draw();
			}
		});
	});

	$('#projectsDatatable thead tr:eq(1) th.selectActive').each(function(i) {

		$(this).html('<select class="form-select"><option value=""><option value="true">' + $('#activoSi').val() + '</option><option value="false">' + $('#activoNo').val() + '</option></select>');

		$('select', this).on('keyup change', function() {
			if (table.column(6).search() !== this.value) {
				table.column(6).search(this.value).draw();
			}
		});
	});


	// BOTONES 

	$(document).on("click", "#btnAceptarAddProjects", function(e) {

		$('#modalAddProjects').modal('show');
		var form = $("#formAddProjects");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			anyadirNuevoProyecto();
			$('#modalAddProjects').modal('hide');
		}
		form.addClass('was-validated');

	});

	$(document).on("click", "#btnCancelarAddProjects", function() {
		$("#formAddProjects").removeClass('was-validated');
		$("#titleProjects").val('');
		$("#imageProjects").val('');
		$("#descProjects").val('');
		$("#active").val('1');
	});

	$(document).on("click", "#btnCloseAddProjects", function() {
		$("#formAddProjects").removeClass('was-validated');
		$("#titleProjects").val('');
		$("#imageProjects").val('');
		$("#descProjects").val('');
		$("#active").val('1');
	});

	$(document).on("click", "#btnAceptarUpdateProjects", function(e) {

		$('#modalUpdateProjects').modal('show');
		var form = $("#formUpdateProjects");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			editarProyecto();
			$('#modalUpdateProjects').modal('hide');
		}
		form.addClass('was-validated');

	});

	$(document).on("click", "#btnCancelarUpdateProjects", function(e) {
		$("#formUpdateProjects").removeClass('was-validated');
	});

	$(document).on("click", "#btnCloseUpdateProjects", function(e) {
		$("#formUpdateProjects").removeClass('was-validated');
	});

	$(document).on("click", "#btnAceptarUpdateImageProjects", function(e) {

		$('#modalUpdateImageProjects').modal('show');
		var form = $("#formUpdateImageProjects");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			editarImagenProyecto();
			$('#modalUpdateImageProjects').modal('hide');
		}
		form.addClass('was-validated');

	});

	$(document).on("click", "#btnCancelarUpdateImageProjects", function(e) {
		$("#formUpdateImageProjects").removeClass('was-validated');
	});

	$(document).on("click", "#btnCloseUpdateImageProjects", function(e) {
		$("#formUpdateImageProjects").removeClass('was-validated');
	});

	$(document).on("click", "#btnAceptarDeleteProjects", function(e) {
		e.preventDefault();
		eliminarProyecto();
	});

}


// MODALES

function mostrarModalProjectsImage(titleProject, imagen) {
	$("#headerModalImageProject").text(titleProject);
	$('#bodyModalImageProject').attr('src', 'images/' + imagen);
	$('#modalImageProject').modal('toggle');
}

function mostrarModalProjectsDescription(titleProject, descriptionProject) {
	$("#modalDescriptionProject").modal('toggle');
	$("#headerModalDescriptionProject").text(titleProject);
	$("#bodyModalDescriptionProject").text(descriptionProject);
}


function mostrarModalAddProjects() {
	$("#modalAddProjects").modal('toggle');
}

function mostrarModalUpdateProjects(idProject, titleProject, descriptionProject, active) {

	$('#titleProjectsEdit').val(titleProject);
	$('#descProjectsEdit').val(descriptionProject);
	$('#idProjectUpdate').val(idProject);

	if (active == 'true') {
		$('#activeEdit').val('1');
	} else {
		$('#activeEdit').val('0');
	}

	$("#modalUpdateProjects").modal('toggle');
}

function mostrarModalUpdateImageProjects(idProject) {
	$('#idProjectUpdateImage').val(idProject);
	$("#modalUpdateImageProjects").modal('toggle');
}

function mostrarModalDeleteProjects(idProject) {
	$('#idProjectsDelete').val(idProject);
	$("#modalDeleteProjects").modal('toggle');
}


// FUNCIONES CRUD

function anyadirNuevoProyecto() {

	let form = new FormData();

	form.append("file", $('#imageProjects')[0].files[0]);
	form.append("titleProjects", $("#titleProjects").val());
	form.append("descProjects", $("#descProjects").val());
	form.append("active", $("#active").val());

	$.ajax({
		url: '/management/addProjectsData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#projectsDatatable').DataTable().ajax.reload();
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
		$("#formAddProjects").removeClass('was-validated');
		$("#titleProjects").val('');
		$("#imageProjects").val('');
		$("#descProjects").val('');
		$("#active").val('1');
	});
}

function editarProyecto() {

	let form = new FormData();

	form.append("idProject", $("#idProjectUpdate").val());
	form.append("titleProject", $("#titleProjectsEdit").val());
	form.append("descProject", $("#descProjectsEdit").val());
	form.append("active", $("#activeEdit").val());

	$.ajax({
		url: '/management/updateProjectsData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#projectsDatatable').DataTable().ajax.reload();
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
		$("#formUpdateProjects").removeClass('was-validated');
	});
}

function editarImagenProyecto() {

	let form = new FormData();

	form.append("file", $('#imageProjectsEdit')[0].files[0]);
	form.append("idProject", $("#idProjectUpdateImage").val());

	$.ajax({
		url: '/management/updateImageProjectsData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#projectsDatatable').DataTable().ajax.reload();
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
		$("#formUpdateImageProjects").removeClass('was-validated');
		$("#imageProjectsEdit").val('');
	});
}

function eliminarProyecto() {

	let form = new FormData();

	form.append("idProject", $("#idProjectsDelete").val());

	$.ajax({
		url: '/management/deleteProjectsData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#projectsDatatable').DataTable().ajax.reload();
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