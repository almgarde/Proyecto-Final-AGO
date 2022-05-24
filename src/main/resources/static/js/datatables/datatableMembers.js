function getDatatableMembers() {
	let table = $('#membersDatatable').DataTable({
		"sAjaxSource": "/management/getMembersData",
		"sAjaxDataProp": "",
		"orderCellsTop": true,
		"fixedHeader": false,
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
				"mData": "dniMember",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false,
			},
			{
				//"mData": ["shortNameMember", "emailMember", "phoneMember", "reseachIdMember", "scopusIdMember", "orcIdMember"]
				"mRender": function(data, type, row) {
					return row.shortNameMember + row.emailMember + row.phoneMember + row.reseachIdMember + row.scopusIdMember + row.orcIdMember;
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
				text: 'Datos Personales',
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalMembersData(data.nameMember, data.shortNameMember, data.emailMember, data.phoneMember, data.reseachIdMember, data.scopusIdMember, data.orcIdMember);
				}
			});
			$('td:eq(3)', row).html(verDatos);

			// Categorias
			var nameProCat = $('<p/>', {
				text: data.nameProCat
			});
			$('td:eq(4)', row).html(nameProCat);

			// Imagen 					  	
			var verFoto = $('<a/>', {
				text: 'Foto',
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalMembersPhoto(data.photoMember);
				}
			});
			$('td:eq(5)', row).html(verFoto);

			// Trayectoria
			var verTrayectoria = $('<a/>', {
				text: "Trayectoria",
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalMemberTrajectory(data.nameMember, data.trajectoryMember);

				}
			});
			$('td:eq(6)', row).html(verTrayectoria);

			// Activo 
			if (data.active=='true') {
				var activo = $('<a/>', {
					text: "Si"
				});
			} else {
				var activo = $('<a/>', {
					text: "No"
				});
			}
			$('td:eq(9)', row).html(activo);
			
			// Acciones 
			var liAccion1 = $('<li/>');

			var accion1 = $('<a/>', {
				text: 'Editar',
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
				text: 'Cambiar foto',
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
				text: 'Eliminar',
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
			var aPrincipal = $('<a/>').addClass('nav-link').addClass('dropdown-toggle').attr('href', '#').attr('id', 'listaAcciones').attr('role', 'button').attr('data-bs-toggle', 'dropdown').attr('aria-expanded', 'false').text('Acciones');
			var uPrincipal = $('<u/>').addClass('dropdown-menu').attr('aria-labelledby', 'listaAcciones').css('text-decoration', 'none');

			liPrincipal.append(aPrincipal);
			aPrincipal.append(uPrincipal);
			uPrincipal.append(liAccion1);
			uPrincipal.append(liAccion2);
			uPrincipal.append(liAccion3);

			divAcciones.append(liPrincipal);
			$('td:eq(10)', row).html(divAcciones);		
			
		}
	});


	$('#membersDatatable thead tr').clone(true).addClass('filters').appendTo('#membersDatatable thead');
	
	$('#membersDatatable thead tr.filters th').each(function() {
		$(this).off();
		$(this).removeClass('sorting_desc');
		$(this).removeClass('sorting');

	});

	$('#membersDatatable thead tr:eq(1) th.atri').each(function(i) {
		//let title = $(this).text();
		$(this).html('<input style="max-width: 100px;" type="text" />');

		$('input', this).on('keyup change', function() {

			if (table.column(i).search() != this.value) {

				table.column(i).search(this.value).draw();
			}
		});
	});


	$('#membersDatatable thead tr:eq(1) th.selectCat').each(function(i) {
		//let title = $(this).text();
		let listaProCat = $('#idProCatSelect');

		$(this).html(listaProCat);

		//$(this).html('<select class="form-select"><option value="0">Seleccione una categoría</option><option th:each="proCatDto : ${listaProCatDto}" th:text="${proCatDto.nameProCat}" th:value="${proCatDto.idProCat}"></select>');

		$('select', this).on('keyup change', function() {
			if (table.column(4).search() != this.value) {
				//debugger;
				table.column(4).search(this.value).draw();
			}
		});
	});

	$('#membersDatatable thead tr:eq(1) th.selectActive').each(function(i) {

		$(this).html('<select class="form-select"><option value=""><option value="true">Si</option><option value="false">No</option></select>');

		$('select', this).on('keyup change', function() {
			if (table.column(9).search() !== this.value) {

				table.column(9).search(this.value).draw();
			}
		});
	});



	$(document).on("click", "#btnAceptarAddMembers", function(e) {
		e.preventDefault();
		anyadirNuevoMiembro();

	});


$(document).on("click", "#btnAceptarUpdateMembers", function(e) {
		e.preventDefault();
		editarMiembro();

	});
	
	$(document).on("click", "#btnAceptarUpdatePhotoMembers", function(e) {
		e.preventDefault();
		editarFotoMiembro();

	});

	$(document).on("click", "#btnAceptarDeleteMembers", function(e) {
		e.preventDefault();
		eliminarMiembro();

	});


}


function mostrarModalMembersData(name, shortName, email, phone, researchId, scopusId, orcId) {

	$("#headerMembersData").text(name);
	$("#modalShortNameMember").text(shortName);
	$("#modalEmailMember").text(email);
	$("#modalPhoneMember").text(phone);
	$("#modalReseachIdMember").text(researchId);
	$("#modalScopusIdMember").text(scopusId);
	$("#modalOrcIdMember").text(orcId);
	$("#modalMembersData").modal('toggle');
}

function mostrarModalMembersPhoto(imagen) {
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

function mostrarModalUpdateMembers(idMember, nameMember, shortNameMember, dniMember, idProCat, activeProCat,  emailMember, phoneMember, reseachIdMember, scopusIdMember, orcIdMember, trajectoryMember, active) {

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
		$('#membersDatatable').DataTable().ajax.reload();
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
		$('#membersDatatable').DataTable().ajax.reload();
$('#imageMembersEdit').val('');
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
		$('#membersDatatable').DataTable().ajax.reload();

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
		$('#membersDatatable').DataTable().ajax.reload();

	});
}
