function getDatatablePublications() {

	let table = $('#publicationsDatatable').DataTable({
		"sAjaxSource": "/management/getPublicationsData",
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
			"infoEmpty": $('#labelMostrando').val() + ' 0 ' + $('#labelA').val() + ' 0 ' + $('#labelDe').val() + ' 0 ' + $('#labelEntradas').val(),
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
				text: $('#authorsPublicationLabel').val(),
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalAuthorsPublication(data.titlePublication, data.authorsPublication);
				}
			});

			$('td:eq(2)', row).html(verAutores);


			// Doi 	
			var irDoi = $('<a/>', {
				text: $('#doiPublicationtLabel').val(),
				href: data.doiPublication,
				click: function(e) {
					e.preventDefault();
					window.open(this, '_blank');
					return false;
				}
			});

			$('td:eq(4)', row).html(irDoi);


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

			$('td:eq(8)', row).html(activo);


			// Acciones 	

			var liAccion1 = $('<li/>');
			var accion1 = $('<a/>', {
				text: $('#linkEditar').val(),
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
				text: $('#linkEliminar').val(),
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
			var aPrincipal = $('<a/>').addClass('nav-link').addClass('dropdown-toggle').attr('href', '#').attr('id', 'listaAcciones').attr('role', 'button').attr('data-bs-toggle', 'dropdown').attr('aria-expanded', 'false').text($('#linkAcciones').val());
			var uPrincipal = $('<u/>').addClass('dropdown-menu').attr('aria-labelledby', 'listaAcciones').css('text-decoration', 'none');

			liPrincipal.append(aPrincipal);
			aPrincipal.append(uPrincipal);
			uPrincipal.append(liAccion1);
			uPrincipal.append(liAccion2);

			divAcciones.append(liPrincipal);


			$('td:eq(9)', row).html(divAcciones);


			$('td:eq(0)', row).attr("data-label", "ID");
			$('td:eq(1)', row).attr("data-label", "Titulo");
			$('td:eq(2)', row).attr("data-label", "Autores");
			$('td:eq(3)', row).attr("data-label", "Revista");
			$('td:eq(4)', row).attr("data-label", "DOI");
			$('td:eq(5)', row).attr("data-label", "Año de publicación");
			$('td:eq(6)', row).attr("data-label", "Admin");
			$('td:eq(7)', row).attr("data-label", "Fecha");
			$('td:eq(8)', row).attr("data-label", "Activo");
			$('td:eq(9)', row).attr("data-label", "");

		}
	});


	// FILTROS

	$('#publicationsDatatable thead tr').clone(true).addClass('filters').appendTo('#publicationsDatatable thead');

	$('#publicationsDatatable thead tr.filters th').each(function() {
		$(this).off();
		$(this).removeClass('sorting_desc');
		$(this).removeClass('sorting');
	});

	$('#publicationsDatatable thead tr:eq(1) th.atri').each(function(i) {

		$(this).html('<input type="text" />');

		$('input', this).off('keyup change').on('keyup change', function() {
			if (table.column(i).search() != this.value) {
				table.column(i).search(this.value).draw();
			}
		});
	});

	$('#publicationsDatatable thead tr:eq(1) th.selectActive').each(function(i) {

		$(this).html('<select class="form-select"><option value=""><option value="true">' + $('#activoSi').val() + '</option><option value="false">' + $('#activoNo').val() + '</option></select>');

		$('select', this).on('keyup change', function() {
			if (table.column(8).search() !== this.value) {
				table.column(8).search(this.value).draw();
			}
		});
	});


	// BOTONES

	$(document).on("click", "#btnAceptarAddPublication", function(e) {

		$('#modalAddPublications').modal('show');
		var form = $("#formAddPublication");

		if (i >= 2) {
			$('#inputNameAutor').removeAttr('required');
			$('#inputShortNameAutor').removeAttr('required');
			$('#inputNameAutor').val('');
			$('#inputShortNameAutor').val('');
			$('#tdInputNameAutor').hide();
			$('#tdInputShortNameAutor').hide();
			$('#inputNameAutor').hide();
			$('#inputShortNameAutor').hide();
			$('#authorSelect').val('');
			$('#btnNuevoAutor').show();
			$('#headerSelectAuthors').text('Escoja un miembro o añada un nuevo autor');
		} else {
			$('#inputNameAutor').val('');
			$('#inputShortNameAutor').val('');
			$('#btnNuevoAutor').hide();
			$('#tdInputNameAutor').show();
			$('#tdInputShortNameAutor').show();
			$('#inputNameAutor').show();
			$('#inputShortNameAutor').show();
			$('#authorSelect').val('');
			$('#headerSelectAuthors').text('Nuevo autor');
			$('#inputNameAutor').prop("required", true);
			$('#inputShortNameAutor').prop("required", true);
		}

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			$('#inputUserPublication').val($('#inputUser').val());
			form.submit();
			$('#modalAddPublications').modal('hide');
		}
		form.addClass('was-validated');
	});

	$(document).on("click", "#btnCancelarAddPublication", function() {
		$("#formAddPublication").removeClass('was-validated');
		$('#titlePublicationMaxContador').html(max_chars_content);
				$(".mensajeError").hide();
		$(".mensajeError").text('');
		$('#inputNameAutor').val('');
		$('#inputShortNameAutor').val('');
		$('#titlePublication').val('');
		$('#journalPublication').val('');
		$('#doiPublication').val('');
		$('#yearPublication').val('');
	});

	$(document).on("click", "#btnCloseAddPublication", function() {
		$("#formAddPublication").removeClass('was-validated');
		$('#titlePublicationMaxContador').html(max_chars_content);
				$(".mensajeError").hide();
		$(".mensajeError").text('');
		$('#inputNameAutor').val('');
		$('#inputShortNameAutor').val('');
		$('#titlePublication').val('');
		$('#journalPublication').val('');
		$('#doiPublication').val('');
		$('#yearPublication').val('');
	});

	$("#modalAddPublications").on('hide.bs.modal', function() {
		$("#formAddPublication").removeClass('was-validated');
		$(".mensajeError").hide();
		$(".mensajeError").text('');
	});


	$(document).on("click", "#btnAceptarUpdatePublication", function(e) {

		$('#modalUpdatePublications').modal('show');
		var form = $("#formUpdatePublication");
		j = $('#numRowsAuthors').val();

		if (j > 0) {
			$('#inputNameAutorEdit').removeAttr('required');
			$('#inputShortNameAutorEdit').removeAttr('required');
			$('#inputNameAutorEdit').val('');
			$('#inputShortNameAutorEdit').val('');
			$('#tdInputNameAutorEdit').hide();
			$('#tdInputShortNameAutorEdit').hide();
			$('#inputNameAutorEdit').hide();
			$('#inputShortNameAutorEdit').hide();
			$('#authorSelectEdit').val('');
			$('#btnNuevoAutorEdit').show();
			$('#headerSelectAuthorsEdit').text('Escoja un miembro o añada un nuevo autor');
		} else {
			$('#inputNameAutorEdit').val('');
			$('#inputShortNameAutorEdit').val('');
			$('#btnNuevoAutorEdit').hide();
			$('#tdInputNameAutorEdit').show();
			$('#tdInputShortNameAutorEdit').show();
			$('#inputNameAutorEdit').show();
			$('#inputShortNameAutorEdit').show();
			$('#authorSelectEdit').val('');
			$('#headerSelectAuthorsEdit').text('Nuevo autor');
			$('#inputNameAutorEdit').prop("required", true);
			$('#inputShortNameAutorEdit').prop("required", true);
		}

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			form.submit();
			$('#modalUpdatePublications').modal('hide');
		}
		form.addClass('was-validated');
	});

	$(document).on("click", "#btnCancelarUpdatePublication", function(e) {
		$("#formUpdatePublication").removeClass('was-validated');		
				$(".mensajeError").hide();
		$(".mensajeError").text('');
		$('#inputNameAutorEdit').val('');
		$('#inputShortNameAutorEdit').val('');
		$('.authorsList').remove();
	});

	$(document).on("click", "#btnCloseUpdatePublications", function(e) {
		$("#formUpdatePublication").removeClass('was-validated');
				$(".mensajeError").hide();
		$(".mensajeError").text('');
		$('#inputNameAutorEdit').val('');
		$('#inputShortNameAutorEdit').val('');
		$('.authorsList').remove();
	});

	$("#modalUpdatePublications").on('hide.bs.modal', function() {
		$("#formUpdatePublication").removeClass('was-validated');
		$(".mensajeError").hide();
		$(".mensajeError").text('');
	});

	$(document).on("click", "#btnAceptarDeletePublication", function(e) {
		e.preventDefault();
		eliminarPublicacion();
	});


	// FORMULARIO AÑADIR AUTORES

	var i = 1;

	$('#add').click(function() {

		let nameAuthor;
		let shortNameAuthor;
		let idMember;

		if ($('#authorSelect').val() != '') {
			idMember = $('#authorSelect').val();
			nameAuthor = $('#authorSelect option:selected').attr('data-name-member');
			shortNameAuthor = $('#authorSelect option:selected').attr('data-short-name-member');
		} else {
			nameAuthor = $('#inputNameAutor').val();
			shortNameAuthor = $('#inputShortNameAutor').val();
		}

		if ($('#authorSelect').val() != '') {
			i++;
			$('#dynamic_field').append('<tr id="row' + i + '"><td>' + (i - 1) + '</td><td><input value="' + nameAuthor + '" id="inputAddNameAuthor' + i + '" type="text" name="authorsPublication[' + (i - 2) + '].nameAuthor" class="form-control" /></td><td><input value="' + shortNameAuthor + '" id="inputAddShortNameAuthor' + i + '" type="text" name="authorsPublication[' + (i - 2) + '].shortNameAuthor" class="form-control " /></td><td><button type="button" name="remove" id="' + i + '" class="btn btn-danger btn_remove" data-is-member="true">X</button></td><input value="' + idMember + '" id="inputAddIdMember' + i + '" type="hidden" name="authorsPublication[' + (i - 2) + '].idMember" class="form-control" /></tr>');
			$('#authorSelect option:selected').remove();
		} else {
			if ($('#inputNameAutor').val() != '' && $('#inputShortNameAutor').val() != '') {
				i++;
				$('#dynamic_field').append('<tr id="row' + i + '"><td>' + (i - 1) + '</td><td><input value="' + nameAuthor + '" id="inputAddNameAuthor' + i + '" type="text" name="authorsPublication[' + (i - 2) + '].nameAuthor" class="form-control "/></td><td><input value="' + shortNameAuthor + '" id="inputAddShortNameAuthor' + i + '" type="text" name="authorsPublication[' + (i - 2) + '].shortNameAuthor" class="form-control " /></td><td><button type="button" name="remove" id="' + i + '" class="btn btn-danger btn_remove" data-is-member="false">X</button></td></tr>');
			}
		}

		$('#btnNuevoAutor').show();
		$('#headerSelectAuthors').text('Escoja un miembro o añada un nuevo autor');
		$('#tdInputNameAutor').hide();
		$('#tdInputShortNameAutor').hide();
		$('#inputNameAutor').hide();
		$('#inputShortNameAutor').hide();
		$('#inputNameAutor').val('');
		$('#inputShortNameAutor').val('');
		$('#authorSelect').val('');

	});

	$(document).on('click', '.btn_remove', function() {

		i--;
		var button_id = $(this).attr("id");

		if ($(this).attr("data-is-member") == "true") {

			var nameMember = $('#inputAddNameAuthor' + button_id).val();
			var shortNameMember = $('#inputAddShortNameAuthor' + button_id).val();
			var idMember = $('#inputAddIdMember' + button_id).val();

			$('#authorSelect').append('<option id="optionAdded' + idMember + '" value="' + idMember + '" data-name-member="' + nameMember + '" data-short-name-member="' + shortNameMember + '"/></option>');
			$('#optionAdded' + idMember).text(nameMember + ',' + shortNameMember);
		}
		$('#row' + button_id + '').remove();
	});

	$(document).on("click", "#newAuthor", function(e) {

		if (i > 1) {
			$('#inputNameAutor').removeAttr('required');
			$('#inputShortNameAutor').removeAttr('required');
		} else {
			$('#inputNameAutor').prop("required", true);
			$('#inputShortNameAutor').prop("required", true);
		}

		e.preventDefault();
		$('#btnNuevoAutor').hide();
		$('#tdInputNameAutor').show();
		$('#tdInputShortNameAutor').show();
		$('#inputNameAutor').show();
		$('#inputShortNameAutor').show();
		$('#authorSelect').val('');
		$('#headerSelectAuthors').text('Nuevo autor');

	});

	$(document).on("change", "#authorSelect", function(e) {

		e.preventDefault();
		$('#headerSelectAuthors').text('Escoja un miembro o añada un nuevo autor');

		$('#tdInputNameAutor').hide();
		$('#tdInputShortNameAutor').hide();
		$('#inputNameAutor').hide();
		$('#inputShortNameAutor').hide();
		$('#inputNameAutor').val('');
		$('#inputShortNameAutor').val('');
		$('#btnNuevoAutor').show();

	});



	// FORMULARIO EDITAR	

	var j;

	$('#addEdit').click(function() {

		j = $('#numRowsAuthors').val();
		let idMember;
		let nameAuthor;
		let shortNameAuthor

		if ($('#authorSelectEdit').val() != '') {
			idMember = $('#authorSelectEdit').val();
			nameAuthor = $('#authorSelectEdit option:selected').attr('data-name-member');
			shortNameAuthor = $('#authorSelectEdit option:selected').attr('data-short-name-member');
		} else {
			nameAuthor = $('#inputNameAutorEdit').val();
			shortNameAuthor = $('#inputShortNameAutorEdit').val();
		}

		if ($('#authorSelectEdit').val() != '') {
			j++;
			$('#dynamic_fieldEdit').append('<tr id="row' + j + '"><td>' + j + '</td><td><input value="' + nameAuthor + '" id="inputUpdateNameAuthor' + j + '" type="text" name="authorsPublication[' + (j - 1) + '].nameAuthor" class="form-control name_list" /></td><td><input value="' + shortNameAuthor + '" id="inputUpdateShortNameAuthor' + j + '" type="text" name="authorsPublication[' + (j - 1) + '].shortNameAuthor" class="form-control name_list" /></td><td><button type="button" name="remove" id="' + j + '" class="btn btn-danger btn_removeEdit" data-is-member="true">X</button></td><input value="' + idMember + '" id="inputUpdateIdMember' + j + '" type="hidden" name="authorsPublication[' + (j - 1) + '].idMember" class="form-control name_list" /></tr>');
			$('#numRowsAuthors').val(j);
			$('#authorSelectEdit option:selected').remove();
		} else {
			if ($('#inputNameAutorEdit').val() != '' && $('#inputShortNameAutorEdit').val() != '') {
				j++;
				$('#dynamic_fieldEdit').append('<tr id="row' + j + '"><td>' + j + '</td><td><input value="' + nameAuthor + '" id="inputUpdateNameAuthor' + j + '" type="text" name="authorsPublication[' + (j - 1) + '].nameAuthor" class="form-control name_list" /></td><td><input value="' + shortNameAuthor + '" id="inputUpdateShortNameAuthor' + j + '" type="text" name="authorsPublication[' + (j - 1) + '].shortNameAuthor" class="form-control name_list" /></td><td><button type="button" name="remove" id="' + j + '" class="btn btn-danger btn_removeEdit" data-is-member="false">X</button></td></tr>');
				$('#numRowsAuthors').val(j);
			}
		}

		$('#btnNuevoAutorEdit').show();
		$('#headerSelectAuthorsEdit').text('Escoja un miembro o añada un nuevo autor');
		$('#tdInputNameAutorEdit').hide();
		$('#tdInputShortNameAutorEdit').hide();
		$('#inputNameAutorEdit').hide();
		$('#inputShortNameAutorEdit').hide();
		$('#inputNameAutorEdit').val('');
		$('#inputShortNameAutorEdit').val('');
		$('#authorSelectEdit').val('');

	});

	$(document).on('click', '.btn_removeEdit', function() {

		j = $('#numRowsAuthors').val();

		var button_id = $(this).attr("id");

		j--;
		$('#numRowsAuthors').val(j);

		if ($(this).attr("data-is-member") == "true") {

			var nameMember = $('#inputUpdateNameAuthor' + button_id).val();
			var shortNameMember = $('#inputUpdateShortNameAuthor' + button_id).val();
			var idMember = $('#inputUpdateIdMember' + button_id).val();

			$('#authorSelectEdit').append('<option id="optionAdded' + idMember + '" value="' + idMember + '" data-name-member="' + nameMember + '" data-short-name-member="' + shortNameMember + '"/></option>');

			$('#optionAdded' + idMember).text(nameMember + ',' + shortNameMember);

		}
		$('#row' + button_id + '').remove();

	});


	$(document).on("click", "#newAuthorEdit", function(e) {

		e.preventDefault();

		if (j > 0) {
			$('#inputNameAutorEdit').removeAttr('required');
			$('#inputShortNameAutorEdit').removeAttr('required');
		} else {
			$('#inputNameAutorEdit').prop("required", true);
			$('#inputShortNameAutorEdit').prop("required", true);
		}

		$('#btnNuevoAutorEdit').hide();
		$('#tdInputNameAutorEdit').show();
		$('#tdInputShortNameAutorEdit').show();
		$('#inputNameAutorEdit').show();
		$('#inputShortNameAutorEdit').show();
		$('#authorSelectEdit').val('');
		$('#headerSelectAuthorsEdit').text('Nuevo autor');

	});


	$(document).on("change", "#authorSelectEdit", function(e) {
		e.preventDefault();
		$('#headerSelectAuthorsEdit').text('Escoja un miembro o añada un nuevo autor');
		$('#tdInputNameAutorEdit').hide();
		$('#tdInputShortNameAutorEdit').hide();
		$('#inputNameAutorEdit').hide();
		$('#inputShortNameAutorEdit').hide();
		$('#btnNuevoAutorEdit').show();
	});
	
	// MÁXIMO CARACTERES TEXTAREA

	var max_chars_content = 3500;
	

$('#titlePublicationMaxContador').html(max_chars_content);

	$('#titlePublication').keyup(function() {
		var chars = $(this).val().length;
		var diff = max_chars_content - chars;
		$('#titlePublicationMaxContador').html(diff);

	});
	

	$('#titlePublicationEdit').keyup(function() {
		var chars = $(this).val().length;
		var diff = max_chars_content - chars;
		$('#titlePublicationMaxContadorEdit').html(diff);

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
			$(".mensajeError").hide();
		$(".mensajeError").text('');
	$('#tdInputNameAutor').hide();
	$('#tdInputShortNameAutor').hide();
	$('#inputNameAutor').hide();
	$('#inputShortNameAutor').hide();
}

function mostrarModalUpdatePublication(idPublication, titlePublication, authorsPublication, journalPublication, doiPublication, yearPublication, active) {
		$(".mensajeError").hide();
		$(".mensajeError").text('');
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

	var j = 0;

	authorsPublication.forEach(function(valor, indice, array) {

		j++;

		if (valor.idMember != '') {
			$('#authorSelectEdit option[value="' + valor.idMember + '"]').remove();
			$('#dynamic_fieldEdit').append('<tr class="authorsList" id="row' + j + '"><td>' + j + '</td><td><input id="inputUpdateNameAuthor' + j + '" value="' + valor.nameAuthor + '" type="text" name="authorsPublication[' + (j - 1) + '].nameAuthor" class="form-control name_list"></td><td><input id="inputUpdateShortNameAuthor' + j + '" value="' + valor.shortNameAuthor + '" type="text" name="authorsPublication[' + (j - 1) + '].shortNameAuthor" class="form-control name_list"></td><td><button type="button" name="remove" id="' + j + '" class="btn btn-danger btn_removeEdit" data-is-member="true">X</button></td><input id="inputUpdateIdMember' + j + '" value="' + valor.idMember + '" type="hidden" name="authorsPublication[' + (j - 1) + '].idMember" class="form-control name_list"></tr>');
		} else {
			$('#dynamic_fieldEdit').append('<tr class="authorsList" id="row' + j + '"><td>' + j + '</td><td><input id="inputUpdateNameAuthor' + j + '" value="' + valor.nameAuthor + '" type="text" name="authorsPublication[' + (j - 1) + '].nameAuthor" class="form-control name_list" /></td><td><input id="inputUpdateShortNameAuthor' + j + '" value="' + valor.shortNameAuthor + '" type="text" name="authorsPublication[' + (j - 1) + '].shortNameAuthor" class="form-control name_list" /></td><td><button type="button" name="remove" id="' + j + '" class="btn btn-danger btn_removeEdit" data-is-member="false">X</button></td><input id="inputUpdateIdMember' + j + '" value="' + null + '" type="hidden" name="authorsPublication[' + (j - 1) + '].idMember" class="form-control name_list"></tr>');
		}
		$('#numRowsAuthors').val(j);

	})

	$('#tdInputNameAutorEdit').hide();
	$('#tdInputShortNameAutorEdit').hide();
	$('#inputNameAutorEdit').hide();
	$('#inputShortNameAutorEdit').hide();
	
	var charsInit = titlePublication.length;
	var diffInit = 3500 - charsInit;
	$('#titlePublicationMaxContadorEdit').html(diffInit);
	
	$("#modalUpdatePublications").modal('toggle');
}

function mostrarModalDeletePublication(idPublication) {
	$('#idPublicationDelete').val(idPublication);
	$("#modalDeletePublication").modal('toggle');
}


// FUNCIONES CRUD

function eliminarPublicacion() {

	let form = new FormData();
	form.append("idPublication", $("#idPublicationDelete").val());

	$.ajax({
		url: '/management/deletePublicationsData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#publicationsDatatable').DataTable().ajax.reload();
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


