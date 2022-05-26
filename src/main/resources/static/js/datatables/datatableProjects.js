function getDatatableProjects() {
	let table = $('#projectsDatatable').DataTable({
		"sAjaxSource": "/management/getProjectsData",
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
				text: 'Imagen',
				href: '',
				click: function(e) {
					e.preventDefault();
					//$('#newsDatatable').DataTable().ajax.reload();
					mostrarModalProjectsImage(data.imageProject);
				}
			});
			$('td:eq(2)', row).html(verImage);

			// Abstract
			var verAbstract = $('<a/>', {
				text: "Descripción",
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalProjectsDescription(data.titleProject, data.descriptionProject);

				}
			});
			$('td:eq(3)', row).html(verAbstract);

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
					mostrarModalUpdateProjects(data.idProject, data.titleProject, data.descriptionProject, data.active);
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
					mostrarModalUpdateImageProjects(data.idProject);
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
					mostrarModalDeleteProjects(data.idProject);
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


	$('#projectDatatable thead tr').clone(true).addClass('filters').appendTo('#projectDatatable thead');
	
	$('#projectDatatable thead tr.filters th').each(function() {
		$(this).off();
		$(this).removeClass('sorting_desc');
		$(this).removeClass('sorting');

	});

	$('#projectDatatable thead tr:eq(1) th.atri').each(function(i) {
		//let title = $(this).text();
		$(this).html('<input style="max-width: 100px;" type="text" />');

		$('input', this).on('keyup change', function() {

			if (table.column(i).search() != this.value) {

				table.column(i).search(this.value).draw();
			}
		});
	});

	$('#projectDatatable thead tr:eq(1) th.selectActive').each(function(i) {

		$(this).html('<select class="form-select"><option value=""><option value="true">Si</option><option value="false">No</option></select>');

		$('select', this).on('keyup change', function() {
			if (table.column(6).search() !== this.value) {

				table.column(6).search(this.value).draw();
			}
		});
	});

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


function mostrarModalProjectsImage(imagen) {
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