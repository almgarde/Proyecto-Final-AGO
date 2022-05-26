function getDatatableProCat() {
	let table = $('#proCatDatatable').DataTable({
		"sAjaxSource": "/management/getProCatData",
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
				"mData": "idProCat",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "nameProCat",
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
			let activo;
			if (data.active == 'true') {
				activo = $('<a/>', {
					text: "Si"
				});
			} else {
				activo = $('<a/>', {
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
					mostrarModalUpdateProCat(data.idProCat, data.nameProCat, data.active);
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
					mostrarModalDeleteProCat(data.idProCat);
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


	$('#proCatDatatable thead tr').clone(true).addClass('filters').appendTo('#proCatDatatable thead');

	$('#proCatDatatable thead tr.filters th').each(function() {
		$(this).off();
		$(this).removeClass('sorting_desc');
		$(this).removeClass('sorting');

	});

	$('#proCatDatatable thead tr:eq(1) th.atri').each(function(i) {
		//let title = $(this).text();
		$(this).html('<input style="max-width: 100px;" type="text" />');

		$('input', this).on('keyup change', function() {

			if (table.column(i).search() != this.value) {

				table.column(i).search(this.value).draw();
			}
		});
	});

	$('#proCatDatatable thead tr:eq(1) th.selectActive').each(function(i) {

		$(this).html('<select class="form-select"><option value=""><option value="true">Si</option><option value="false">No</option></select>');

		$('select', this).on('keyup change', function() {
			if (table.column(4).search() !== this.value) {

				table.column(4).search(this.value).draw();
			}
		});
	});

	$(document).on("click", "#btnAceptarAddProCat", function(e) {
		
		$('#modalAddProCat').modal('show');

		var form = $("#formAddProCat");

		if (form[0].checkValidity() === false) {
			e.preventDefault()
			e.stopPropagation()

		} else {

			anyadirNuevaProCat();
			$('#modalAddProCat').modal('hide');

		}
		form.addClass('was-validated')

	});
	
	$(document).on("click", "#btnCancelarAddProCat", function() {
$("#formAddProCat").removeClass('was-validated');
		$("#nameProCat").val('');
		$("#active").val('1');
});

		$(document).on("click", "#btnCloseAddProCat", function() {
$("#formAddProCat").removeClass('was-validated');
		$("#nameProCat").val('');
		$("#active").val('1');
});

	$(document).on("click", "#btnAceptarUpdateProCat", function(e) {
		
		$('#modalUpdateProCat').modal('show');

		var form = $("#formUpdateProCat");

		if (form[0].checkValidity() === false) {
			e.preventDefault()
			e.stopPropagation()

		} else {

			editarProCat();
			$('#modalUpdateProCat').modal('hide');

		}
		form.addClass('was-validated')

	});
	
	$(document).on("click", "#btnCancelarUpdateProCat", function(e) {
$("#formUpdateProCat").removeClass('was-validated');

	});
	
		$(document).on("click", "#btnCloseUpdateProCat", function(e) {
$("#formUpdateProCat").removeClass('was-validated');

	});



	$(document).on("click", "#btnAceptarDeleteProCat", function(e) {
		e.preventDefault();
		eliminarProCat();

	});

//	$('#modalAddProCat').on('hidden.bs.modal', function() {
//		$("#nameProCat").val('');
//		$("#active").val('1');
//	})



}







function mostrarModalAddProCat() {
	$("#modalAddProCat").modal('toggle');
}

function mostrarModalUpdateProCat(idProCat, nameProCat, active) {

	$('#nameProCatEdit').val(nameProCat);

	$('#idProCatUpdate').val(idProCat);
	if (active == 'true') {
		$('#activeEdit').val('1');
	} else {
		$('#activeEdit').val('0');

	}

	$("#modalUpdateProCat").modal('toggle');
}


function mostrarModalDeleteProCat(idProCat) {

	$('#idProCatDelete').val(idProCat);
	$("#modalDeleteProCat").modal('toggle');
}


function anyadirNuevaProCat() {

	let form = new FormData();


	form.append("nameProCat", $("#nameProCat").val());
	form.append("active", $("#active").val());

	$.ajax({
		url: '/management/addProCatData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#proCatDatatable').DataTable().ajax.reload();
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
		
		$("#formAddProCat").removeClass('was-validated');
		$("#nameProCat").val('');
		$("#active").val('1');

	});
}

function editarProCat() {

	let form = new FormData();

	form.append("idProCat", $("#idProCatUpdate").val());
	form.append("nameProCat", $("#nameProCatEdit").val());
	form.append("active", $("#activeEdit").val());

	$.ajax({
		url: '/management/updateProCatData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#proCatDatatable').DataTable().ajax.reload();
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
		
$("#formUpdateProCat").removeClass('was-validated');

	});
}

function eliminarProCat() {

	let form = new FormData();

	form.append("idProCat", $("#idProCatDelete").val());


	$.ajax({
		url: '/management/deleteProCatData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#proCatDatatable').DataTable().ajax.reload();
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

