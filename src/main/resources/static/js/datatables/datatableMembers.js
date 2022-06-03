function getDatatableMembers() {

	let table = $('#membersDatatable').DataTable({
		"sAjaxSource": "/management/getMembersData",
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
				"mData": "idMember",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true,
			},

			{
				"mData": "nameMember",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true,
			},
			{
				"mRender": function(data, type, row) {
					return row.dniMember + row.shortNameMember + row.emailMember + row.phoneMember + row.reseachIdMember + row.scopusIdMember + row.orcIdMember;
				},
				"bSortable": false
			},
			{
				"mData": "idProCat",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "photoMember",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "trajectoryMember",
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

			// Datos personales 
			var verDatos = $('<a/>', {
				text: $('#dataMemberLabel').val(),
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalMembersData(data.nameMember, data.dniMember, data.shortNameMember, data.emailMember, data.phoneMember, data.reseachIdMember, data.scopusIdMember, data.orcIdMember);
				}
			});

			$('td:eq(2)', row).html(verDatos);


			// Categorias
			var nameProCat = $('<p/>', {
				text: data.nameProCat
			});

			$('td:eq(3)', row).html(nameProCat);


			// Imagen 					  	
			var verFoto = $('<a/>', {
				text: $('#photoMemberLabel').val(),
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalMembersPhoto(data.nameMember, data.photoMember);
				}
			});

			$('td:eq(4)', row).html(verFoto);


			// Trayectoria
			var verTrayectoria = $('<a/>', {
				text: $('#trajectoryMemberLabel').val(),
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalMemberTrajectory(data.nameMember, data.trajectoryMember);

				}
			});

			$('td:eq(5)', row).html(verTrayectoria);


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
					mostrarModalUpdateMembers(data.idMember, data.nameMember, data.shortNameMember, data.dniMember, data.idProCat, data.activeProCat, data.emailMember, data.phoneMember, data.reseachIdMember, data.scopusIdMember, data.orcIdMember, data.trajectoryMember, data.active);
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
					mostrarModalUpdatePhotoMembers(data.idMember);
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
					mostrarModalDeleteMembers(data.idMember);
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
			$('td:eq(9)', row).html(divAcciones);


			$('td:eq(0)', row).attr("data-label", "ID");
			$('td:eq(1)', row).attr("data-label", "Nombre");
			$('td:eq(2)', row).attr("data-label", "Datos personales");
			$('td:eq(3)', row).attr("data-label", "Categoría");
			$('td:eq(4)', row).attr("data-label", "Foto");
			$('td:eq(5)', row).attr("data-label", "Trayectoria");
			$('td:eq(6)', row).attr("data-label", "Admin");
			$('td:eq(7)', row).attr("data-label", "Fecha");
			$('td:eq(8)', row).attr("data-label", "Activo");
			$('td:eq(9)', row).attr("data-label", "");

		}
	});


	// FILTROS

	$('#membersDatatable thead tr').clone(true).addClass('filters').appendTo('#membersDatatable thead');

	$('#membersDatatable thead tr.filters th').each(function() {
		$(this).off();
		$(this).removeClass('sorting_desc');
		$(this).removeClass('sorting');
	});

	$('#membersDatatable thead tr:eq(1) th.atri').each(function(i) {

		$(this).html('<input type="text" />');

		$('input', this).on('keyup change', function() {
			if (table.column(i).search() != this.value) {
				table.column(i).search(this.value).draw();
			}
		});
	});


	$('#membersDatatable thead tr:eq(1) th.selectCat').each(function(i) {

		let listaProCat = $('#idProCatSelect');
		$(this).html(listaProCat);

		$('select', this).on('keyup change', function() {
			if (table.column(3).search() != this.value) {
				table.column(3).search(this.value).draw();
			}
		});
	});

	$('#membersDatatable thead tr:eq(1) th.selectActive').each(function(i) {

		$(this).html('<select class="form-select"><option value=""><option value="true">' + $('#activoSi').val() + '</option><option value="false">' + $('#activoNo').val() + '</option></select>');

		$('select', this).on('keyup change', function() {
			if (table.column(8).search() !== this.value) {
				table.column(8).search(this.value).draw();
			}
		});
	});


	// BOTONES

	$(document).on("click", "#btnAceptarAddMembers", function(e) {

		$('#modalAddMembers').modal('show');
		var form = $("#formAddMembers");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			anyadirNuevoMiembro();
			$('#modalAddMembers').modal('hide');
		}
		form.addClass('was-validated');

	});

	$(document).on("click", "#btnCancelarAddMembers", function() {
		$("#formAddMembers").removeClass('was-validated');
		$("#nameMember").val('');
		$("#shortNameMember").val('');
		$("#dniMember").val('');
		$("#emailMember").val('');
		$("#phoneMember").val('');
		$("#idProCat").val('');
		$("#reseachIdMember").val('');
		$("#shortNameMember").val('');
		$("#scopusIdMember").val('');
		$("#orcIdMember").val('');
		$("#trajectoryMember").val('');
		$("#active").val('1');
		$('#imageMembers').val('');
	});

	$(document).on("click", "#btnCloseAddMembers", function() {
		$("#formAddMembers").removeClass('was-validated');
		$("#nameMember").val('');
		$("#shortNameMember").val('');
		$("#dniMember").val('');
		$("#emailMember").val('');
		$("#phoneMember").val('');
		$("#idProCat").val('');
		$("#reseachIdMember").val('');
		$("#imageMembers").val('');
		$("#scopusIdMember").val('');
		$("#orcIdMember").val('');
		$("#trajectoryMember").val('');
		$("#active").val('1');
		$('#imageMembers').val('');
	});


	$(document).on("click", "#btnAceptarUpdateMembers", function(e) {

		$('#modalUpdateMembers').modal('show');
		var form = $("#formUpdateMember");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			editarMiembro();
			$('#modalUpdateMembers').modal('hide');
		}
		form.addClass('was-validated');

	});

	$(document).on("click", "#btnCancelarUpdateMembers", function() {
		$("#formUpdateMembers").removeClass('was-validated');

	});

	$(document).on("click", "#btnCloseUpdateMembers", function() {
		$("#formUpdateMembers").removeClass('was-validated');

	});


	$(document).on("click", "#btnAceptarUpdatePhotoMembers", function(e) {

		$('#modalUpdatePhotoMembers').modal('show');
		var form = $("#formUpdatePhotoMembers");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			editarFotoMiembro();
			$('#modalUpdatePhotoMembers').modal('hide');
		}
		form.addClass('was-validated');

	});

	$(document).on("click", "#btnCancelarUpdatePhotoMembers", function() {
		$("#formUpdatePhotoMembers").removeClass('was-validated');
		$("#imageMembersEdit").val('');
	});

	$(document).on("click", "#btnCloseUpdatePhotoMembers", function() {
		$("#formUpdatePhotoMembers").removeClass('was-validated');
		$("#imageMembersEdit").val('');
	});

	$(document).on("click", "#btnAceptarDeleteMembers", function(e) {
		e.preventDefault();
		eliminarMiembro();
	});

}


// MODALES

function mostrarModalMembersData(name, dni, shortName, email, phone, researchId, scopusId, orcId) {
	$("#headerMembersData").text(name);
	$("#modalDniMember").text(dni);
	$("#modalShortNameMember").text(shortName);
	$("#modalEmailMember").text(email);
	$("#modalPhoneMember").text(phone);
	$("#modalReseachIdMember").text(researchId);
	$("#modalScopusIdMember").text(scopusId);
	$("#modalOrcIdMember").text(orcId);
	$("#modalMembersData").modal('toggle');
}

function mostrarModalMembersPhoto(nameMember, imagen) {
	$("#headerMembersPhoto").text(nameMember);
	$('#bodyModalMembersPhoto').attr('src', 'images/' + imagen);
	$('#modalMembersPhoto').modal('toggle');
}

function mostrarModalMemberTrajectory(nameMember, trajectoryMember) {
	$("#modalMembersTrajectory").modal('toggle');
	$("#headerMembersTrajectory").text(nameMember);
	$("#bodyMembersTrajectory").text(trajectoryMember);
}


function mostrarModalAddMembers() {
	$("#modalAddMembers").modal('toggle');
}

function mostrarModalUpdateMembers(idMember, nameMember, shortNameMember, dniMember, idProCat, activeProCat, emailMember, phoneMember, reseachIdMember, scopusIdMember, orcIdMember, trajectoryMember, active) {

	$('#nameMemberEdit').val(nameMember);
	$('#shortNameMemberEdit').val(shortNameMember);
	$('#dniMemberEdit').val(dniMember);

	if (activeProCat == "true") {
		$('#idProCatActiveSelectEdit').val(idProCat);
	} else {
		$('#idProCatActiveSelectEdit').val('');
	}

	$('#emailMemberEdit').val(emailMember);
	$('#phoneMemberEdit').val(phoneMember);
	$('#reseachIdMemberEdit').val(reseachIdMember);
	$('#scopusIdMemberEdit').val(scopusIdMember);
	$('#orcIdMemberEdit').val(orcIdMember);
	$('#trajectoryMemberEdit').val(trajectoryMember);
	$('#idMembersUpdate').val(idMember);

	if (active == 'true') {
		$('#activeEdit').val('1');
	} else {
		$('#activeEdit').val('0');
	}
	$("#modalUpdateMembers").modal('toggle');

}

function mostrarModalUpdatePhotoMembers(idMember) {
	$('#idMembersPhotoUpdate').val(idMember);
	$("#modalUpdatePhotoMembers").modal('toggle');
}

function mostrarModalDeleteMembers(idMember) {
	$('#idMembersDelete').val(idMember);
	$("#modalDeleteMembers").modal('toggle');
}


// FUNCIONES CRUD

function anyadirNuevoMiembro() {

	let form = new FormData();

	form.append("file", $('#imageMembers')[0].files[0]);
	form.append("nameMember", $("#nameMember").val());
	form.append("shortNameMember", $("#shortNameMember").val());
	form.append("dniMember", $("#dniMember").val());
	form.append("emailMember", $("#emailMember").val());
	form.append("phoneMember", $("#phoneMember").val());
	form.append("idProCat", $("#idProCatActiveSelect").val());
	form.append("reseachIdMember", $("#reseachIdMember").val());
	form.append("scopusIdMember", $("#scopusIdMember").val());
	form.append("orcIdMember", $("#orcIdMember").val());
	form.append("trajectoryMember", $("#trajectoryMember").val());
	form.append("active", $("#active").val());

	$.ajax({
		url: '/management/addMembersData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#membersDatatable').DataTable().ajax.reload();
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
		$("#formAddMembers").removeClass('was-validated');
		$("#nameMember").val('');
		$("#shortNameMember").val('');
		$("#dniMember").val('');
		$("#emailMember").val('');
		$("#phoneMember").val('');
		$("#idProCat").val('');
		$("#reseachIdMember").val('');
		$("#shortNameMember").val('');
		$("#scopusIdMember").val('');
		$("#orcIdMember").val('');
		$("#trajectoryMember").val('');
		$("#active").val('1');
		$('#imageMembers').val('');
	});
}


function editarMiembro() {

	let form = new FormData();

	form.append("nameMember", $("#nameMemberEdit").val());
	form.append("shortNameMember", $("#shortNameMemberEdit").val());
	form.append("dniMember", $("#dniMemberEdit").val());
	form.append("emailMember", $("#emailMemberEdit").val());
	form.append("phoneMember", $("#phoneMemberEdit").val());
	form.append("idProCat", $("#idProCatActiveSelectEdit").val());
	form.append("reseachIdMember", $("#reseachIdMemberEdit").val());
	form.append("scopusIdMember", $("#scopusIdMemberEdit").val());
	form.append("orcIdMember", $("#orcIdMemberEdit").val());
	form.append("trajectoryMember", $("#trajectoryMemberEdit").val());
	form.append("active", $("#activeEdit").val());
	form.append("idMembers", $("#idMembersUpdate").val());

	$.ajax({
		url: '/management/updateMembersData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#membersDatatable').DataTable().ajax.reload();
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
		$('#imageMembersEdit').val('');
		$("#formUpdateMembers").removeClass('was-validated');
	});
}

function editarFotoMiembro() {

	let form = new FormData();

	form.append("file", $('#imageMembersEdit')[0].files[0]);
	form.append("idMembers", $("#idMembersPhotoUpdate").val());

	$.ajax({
		url: '/management/updatePhotoMembersData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#membersDatatable').DataTable().ajax.reload();
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
		$("#formUpdatePhotoMembers").removeClass('was-validated');
		$("#imageMembersEdit").val('');
	});
}


function eliminarMiembro() {

	let form = new FormData();

	form.append("idMembers", $("#idMembersDelete").val());

	$.ajax({
		url: '/management/deleteMembersData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#membersDatatable').DataTable().ajax.reload();
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

