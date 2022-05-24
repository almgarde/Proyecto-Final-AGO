function getDatatablePublications() {
	let table = $('#publicationsDatatable').DataTable({
		"sAjaxSource": "/management/getPublicationsData",
		"sAjaxDataProp": "",
		"orderCellsTop": true,
		"fixedHeader": false,
		"order": [[0, "desc"]],
		"aoColumns": [
			{
				"mData": "idPublication",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true,
			},
			{
				"mData": "titlePublication", "width": "20%",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "authorsPublication",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "journalPublication",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "doiPublication",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "yearPublication",
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

			// Autores
			var verAutores = $('<a/>', {
				text: "Autores",
				href: '',
				click: function(e) {
					e.preventDefault();

					mostrarModalAuthorsPublication(data.titlePublication, data.authorsPublication);

				}
			});

			$('td:eq(2)', row).html(verAutores);


			// Doi 	
			var irDoi = $('<a/>', {
				text: 'Enlace',
				href: data.doiPublication,
				click: function(e) {
					e.preventDefault();
					window.open(this, '_blank');
					return false;
				}
			});
			$('td:eq(4)', row).html(irDoi);


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
			$('td:eq(8)', row).html(activo);


			// Acciones 	

			var liAccion1 = $('<li/>');
			var accion1 = $('<a/>', {
				text: 'Editar',
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalUpdatePublication(data.idPublication, data.titlePublication, data.authorsPublication, data.journalPublication, data.doiPublication, data.yearPublication, data.active);
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
					mostrarModalDeletePublication(data.idPublication);
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


			$('td:eq(9)', row).html(divAcciones);

		}
	});


	//BOTONES

	$(document).on("click", "#btnAceptarAddPublication", function(e) {
		e.preventDefault();
		var form = $("#formAddPublication");
		form.submit();
	});


	$(document).on("click", "#btnAceptarUpdatePublication", function(e) {
		e.preventDefault();
		var form = $("#formUpdatePublication");
		form.submit();
	});


	$(document).on("click", "#btnAceptarDeletePublication", function(e) {
		e.preventDefault();
		eliminarPublicacion();
	});


	//FILTROS

	$('#publicationsDatatable thead tr').clone(true).addClass('filters').appendTo('#publicationsDatatable thead');

	$('#publicationsDatatable thead tr.filters th').each(function() {
		$(this).off();
		$(this).removeClass('sorting_desc');
		$(this).removeClass('sorting');

	});

	$('#publicationsDatatable thead tr:eq(1) th.atri').each(function(i) {
		//let title = $(this).text();
		$(this).html('<input style="max-width: 100px;" type="text" />');

		$('input', this).off('keyup change').on('keyup change', function() {
			if (table.column(i).search() != this.value) {
				table.column(i).search(this.value).draw();
			}
		});
	});

	$('#publicationsDatatable thead tr:eq(1) th.selectActive').each(function(i) {

		$(this).html('<select class="form-select"><option value=""><option value="true">Si</option><option value="false">No</option></select>');

		$('select', this).on('keyup change', function() {
			if (table.column(8).search() !== this.value) {
				table.column(8).search(this.value).draw();

			}
		});
	});
	
	
	// FORMULARIO AÑADIR

	var i = 1;
	$('#add').click(function() {
		i++;
		let nameAuthor;
		let shortNameAuthor
		if ($('#authorSelect').val() != '') {


			nameAuthor = $('#authorSelect option:selected').attr('data-name-member');
			shortNameAuthor = $('#authorSelect option:selected').attr('data-short-name-member');

		} else {
			nameAuthor = $('#inputNameAutor').val();
			shortNameAuthor = $('#inputShortNameAutor').val();
		}

		$('#dynamic_field').append('<tr id="row' + i + '"><td><input value="' + nameAuthor + '" id="inputAnyadirNameAuthor" type="text" name="authorsPublication[' + (i - 2) + '].nameAuthor" class="form-control name_list" /></td><td><input value="' + shortNameAuthor + '" id="inputAnyadirShortNameAuthor" type="text" name="authorsPublication[' + (i - 2) + '].shortNameAuthor" class="form-control name_list" /></td><td><button type="button" name="remove" id="' + i + '" class="btn btn-danger btn_remove">X</button></td></tr>');

		$('#inputNuevoAutor').hide();
		$('#btnNuevoAutor').show();
		$('#headerSelectAuthors').text('Escoja un miembro o añada un nuevo autor');
		$('#inputNameAutor').val('');
		$('#inputShortNameAutor').val('');
		$('#authorSelect').val('');

	});
	
	

	$(document).on('click', '.btn_remove', function() {
		var button_id = $(this).attr("id");
		$('#row' + button_id + '').remove();
	});


	$(document).on("click", "#newAuthor", function(e) {
		e.preventDefault();
		$('#btnNuevoAutor').hide();
		$('#inputNuevoAutor').show();
		$('#authorSelect').val('');
		$('#headerSelectAuthors').text('Nuevo autor');

	});
	

	$(document).on("change", "#authorSelect", function(e) {
		e.preventDefault();
		$('#headerSelectAuthors').text('Escoja un miembro o añada un nuevo autor');
		$('#inputNuevoAutor').hide();
		$('#btnNuevoAutor').show();

	});
	
	
	
	// FORMULARIO EDITAR	

	$('#addEdit').click(function() {
		i++;
		let nameAuthor;
		let shortNameAuthor
		if ($('#authorSelectEdit').val() != '') {


			nameAuthor = $('#authorSelectEdit option:selected').attr('data-name-member');
			shortNameAuthor = $('#authorSelectEdit option:selected').attr('data-short-name-member');

		} else {
			nameAuthor = $('#inputNameAutorEdit').val();
			shortNameAuthor = $('#inputShortNameAutorEdit').val();
		}

		$('#dynamic_fieldEdit').append('<tr id="row' + i + '"><td><input value="' + nameAuthor + '" id="inputAnyadirNameAuthor" type="text" name="authorsPublication[' + (i - 2) + '].nameAuthor" class="form-control name_list" /></td><td><input value="' + shortNameAuthor + '" id="inputAnyadirShortNameAuthor" type="text" name="authorsPublication[' + (i - 2) + '].shortNameAuthor" class="form-control name_list" /></td><td><button type="button" name="remove" id="' + i + '" class="btn btn-danger btn_removeEdit">X</button></td></tr>');

		
		$('#inputNuevoAutorEdit').hide();
		$('#btnNuevoAutorEdit').show();
		$('#headerSelectAuthorsEdit').text('Escoja un miembro o añada un nuevo autor');
		$('#inputNameAutorEdit').val('');
		$('#inputShortNameAutorEdit').val('');
		$('#authorSelectEdit').val('');

	});


	$(document).on('click', '.btn_removeEdit', function() {
		var button_id = $(this).attr("id");
		$('#row' + button_id + '').remove();
	});


	$(document).on("click", "#newAuthorEdit", function(e) {
		e.preventDefault();
		$('#btnNuevoAutorEdit').hide();
		$('#inputNuevoAutorEdit').show();
		$('#authorSelectEdit').val('');
		$('#headerSelectAuthorsEdit').text('Nuevo autor');

	});


	$(document).on("change", "#authorSelectEdit", function(e) {
		e.preventDefault();
		$('#headerSelectAuthorsEdit').text('Escoja un miembro o añada un nuevo autor');
		$('#inputNuevoAutorEdit').hide();
		$('#btnNuevoAutorEdit').show();

	});

}


// MODALES

function mostrarModalAuthorsPublication(titulo, autores) {
	$("#modalAuthorsPublication").modal('toggle');
	$("#headerAuthorsPublication").text(titulo);
	$('#listaAutores').text('');
	autores.forEach(function(valor, indice, array) {
		$('#listaAutores').append('<p>' + valor.nameAuthor + ', ' + valor.shortNameAuthor + '</p>');
	});
}


function mostrarModalAddPublications() {
	$("#modalAddPublications").modal('toggle');
	$('#inputNuevoAutor').hide();
}


function mostrarModalUpdatePublication(idPublication, titlePublication, authorsPublication, journalPublication, doiPublication, yearPublication, active) {
	$('#inputNuevoAutorEdit').hide();
	$('#titlePublicationEdit').val(titlePublication);
	$('#journalPublicationEdit').val(journalPublication);
	$('#doiPublicationEdit').val(doiPublication);
	$('#yearPublicationEdit').val(yearPublication);
	$('#idPublicationEdit').val(idPublication);
	if (active == 'true') {
		$('#activeEdit').val('1');
	} else {
		$('#activeEdit').val('0');
	}

	let i = 1;
	authorsPublication.forEach(function(valor, indice, array) {
		i++;
		$('#dynamic_fieldEdit').append('<tr id="row' + i + '"><td><input value="' + valor.nameAuthor + '" id="inputAnyadirNameAuthor" type="text" name="authorsPublication[' + (i - 2) + '].nameAuthor" class="form-control name_list" /></td><td><input value="' + valor.shortNameAuthor + '" id="inputAnyadirShortNameAuthor" type="text" name="authorsPublication[' + (i - 2) + '].shortNameAuthor" class="form-control name_list" /></td><td><button type="button" name="remove" id="' + i + '" class="btn btn-danger btn_remove">X</button></td></tr>');
	})

	$("#modalEditPublications").modal('toggle');
}

function mostrarModalDeletePublication(idPublication) {
	$('#idPublicationDelete').val(idPublication);
	$("#modalDeletePublication").modal('toggle');
}



// FUNCIONES CRUD

function eliminarPublicacion() {

	let form = new FormData();
	debugger
	form.append("idPublication", $("#idPublicationDelete").val());


	$.ajax({
		url: '/management/deletePublicationsData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,

		success: function(data) {

			//			$('#newsDatatable').DataTable().ajax.reload();
			//			$("#titleNews").val('');
			//			$("#abstractNews").val('');
			//			$("#contentNews").val('');
			//			$("#active").val('1');
			//			$('#imageNews').val('');



			//			removePantallaLoader();
			//			debugger;
			//			if (!data || data == '') {
			//				
			//				refreshDataTable();
			//				mostrarNotificacion('missatge-feedback-positiu', Globalize.localize('label.listaUsuarios.modal.anyadir.confirmacion'));
			//			} else {
			//				mostrarNotificacion('missatge-error', Globalize.localize('label.listaUsuarios.modal.anyadir.error'));
			//			}

		}
		//error: function(data) {
		//			removePantallaLoader();
		//			mostrarNotificacion('missatge-error', Globalize.localize('label.listaUsuarios.modal.anyadir.error'));
		//		}
	}).done(function() {
		$('#publicationsDatatable').DataTable().ajax.reload();


	});

	
}


