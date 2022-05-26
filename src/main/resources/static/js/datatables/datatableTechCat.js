function getDatatableTechCat() {
	let table = $('#techCatDatatable').DataTable({
		"sAjaxSource": "/management/getTechCatData",
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
				"mData": "idTechCat",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true
			},
			{
				"mData": "nameTechCat",
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
			$('td:eq(4)', row).html(activo);
			
			// Acciones 	 	
			var liAccion1 = $('<li/>');
			var accion1 = $('<a/>', {
				text: 'Editar',
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalUpdateTechCat(data.idTechCat, data.nameTechCat, data.active);
				}
			});

			accion1.addClass('dropdown-item').attr('href', '#');
			liAccion1.append(accion1);

			var liAccion2 = $('<li/>');
			var accion2 = $('<a/>', {
				text: 'Eliminar',
				href: '',
				click: function(e) {

					e.preventDefault();
					mostrarModalDeleteTechCat(data.idTechCat);
				}
			});


			accion2.addClass('dropdown-item').attr('href', '#');
			liAccion2.append(accion2);

			var divAcciones = $('<div/>');
			divAcciones.addClass("text-end");

			var liPrincipal = $('<li/>').css('list-style-type', 'none').addClass('nav-item').addClass('dropdown');
			var aPrincipal = $('<a/>').addClass('nav-link').addClass('dropdown-toggle').attr('href', '#').attr('id', 'listaAcciones').attr('role', 'button').attr('data-bs-toggle', 'dropdown').attr('aria-expanded', 'false').text('Acciones');
			var uPrincipal = $('<u/>').addClass('dropdown-menu').attr('aria-labelledby', 'listaAcciones').css('text-decoration', 'none');

			liPrincipal.append(aPrincipal);
			aPrincipal.append(uPrincipal);
			uPrincipal.append(liAccion1);
			uPrincipal.append(liAccion2);


			divAcciones.append(liPrincipal);

			$('td:eq(5)', row).html(divAcciones);
		}
	});


	$('#techCatDatatable thead tr').clone(true).addClass('filters').appendTo('#techCatDatatable thead');
	
	$('#techCatDatatable thead tr.filters th').each(function() {
		$(this).off();
		$(this).removeClass('sorting_desc');
		$(this).removeClass('sorting');

	});

	$('#techCatDatatable thead tr:eq(1) th.atri').each(function(i) {
		//let title = $(this).text();
		$(this).html('<input style="max-width: 100px;" type="text" />');

		$('input', this).on('keyup change', function() {

			if (table.column(i).search() != this.value) {

				table.column(i).search(this.value).draw();
			}
		});
	});

	$('#techCatDatatable thead tr:eq(1) th.selectActive').each(function(i) {

		$(this).html('<select class="form-select"><option value=""><option value="true">Si</option><option value="false">No</option></select>');

		$('select', this).on('keyup change', function() {
			if (table.column(4).search() !== this.value) {

				table.column(4).search(this.value).draw();
			}
		});
	});

	$(document).on("click", "#btnAceptarAddTechCat", function(e) {
	
	$('#modalAddTechCat').modal('show');

		var form = $("#formAddTechCat");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();

		} else {

			anyadirNuevaTechCat();
			$('#modalAddTechCat').modal('hide');

		}
		form.addClass('was-validated');
		

	});
	
	$(document).on("click", "#btnCancelarAddTechCat", function() {
$("#formAddTechCat").removeClass('was-validated');
});

		$(document).on("click", "#btnCloseAddTechCat", function() {
$("#formAddTechCat").removeClass('was-validated');

	});
	
	$(document).on("click", "#btnAceptarUpdateTechCat", function(e) {
	
		$('#modalUpdateTechCat').modal('show');

		var form = $("#formUpdateTechCat");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();

		} else {

			editarTechCat();
			$('#modalUpdateTechCat').modal('hide');

		}
		form.addClass('was-validated');
		

	});
	
	$(document).on("click", "#btnCancelarUpdateTechCat", function(e) {
$("#formUpdateTechCat").removeClass('was-validated');

	});
	
		$(document).on("click", "#btnCloseUpdateTechCat", function(e) {
$("#formUpdateTechCat").removeClass('was-validated');

	});

	$(document).on("click", "#btnAceptarDeleteTechCat", function(e) {
		e.preventDefault();
		eliminarTechCat();

	});

}

function mostrarModalAddTechCat() {
	$("#modalAddTechCat").modal('toggle');
}

function mostrarModalUpdateTechCat(idTechCat, nameTechCat, active) {

	$('#nameTechCatEdit').val(nameTechCat);

	$('#idTechCatUpdate').val(idTechCat);
	if (active == 'true') {
		$('#activeEdit').val('1');
	} else {
		$('#activeEdit').val('0');
		
	}
	
	$("#modalUpdateTechCat").modal('toggle');
}


function mostrarModalDeleteTechCat(idTechCat) {
		$('#idTechCatDelete').val(idTechCat);
	$("#modalDeleteTechCat").modal('toggle');
}

function anyadirNuevaTechCat() {

	let form = new FormData();


	form.append("nameTechCat", $("#nameTechCat").val());
	form.append("active", $("#active").val());


	$.ajax({
		url: '/management/addTechCatData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,

		success: function(data) {
			if (data == '') {
				$('#techCatDatatable').DataTable().ajax.reload();
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
		
		$("#formAddTechCat").removeClass('was-validated');
		$("#nameTechCat").val('');
		$("#active").val('1');

	});
}


function editarTechCat() {

	let form = new FormData();

form.append("idTechCat", $("#idTechCatUpdate").val());
	form.append("nameTechCat", $("#nameTechCatEdit").val());
	form.append("active", $("#activeEdit").val());

	$.ajax({
		url: '/management/updateTechCatData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#techCatDatatable').DataTable().ajax.reload();
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
		
$("#formUpdateTechCat").removeClass('was-validated');

	});
}

function eliminarTechCat() {

	let form = new FormData();

form.append("idTechCat", $("#idTechCatDelete").val());


	$.ajax({
		url: '/management/deleteTechCatData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#techCatDatatable').DataTable().ajax.reload();
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