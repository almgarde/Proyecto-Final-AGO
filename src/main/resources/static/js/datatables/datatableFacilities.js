function getDatatableFacilities() {

	let table = $('#facilitiesDatatable').DataTable({
		"sAjaxSource": "/management/getFacilitiesData",
		"sAjaxDataProp": "",
		"orderCellsTop": true,
		"fixedHeader": false,
		"order": [[0, "desc"]],
		"aoColumns": [
			{
				"mData": "idFacility",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true,
			},

			{
				"mData": "nameFacility",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true,
			},
			{
				"mData": "idTechCat",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false,
			},
			{
				"mData": "photoFacility",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "featuresFacility",
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


			// Categorias
			var nameTechCat = $('<p/>', {
				text: data.nameTechCat
			});
			$('td:eq(2)', row).html(nameTechCat);

			// Imagen 					  	
			var verFoto = $('<a/>', {
				text: 'Foto',
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalFacilitiesPhoto(data.photoFacility);
				}
			});
			$('#photoFacilitiesActual').append(verFoto);
			
			$('td:eq(3)', row).html(verFoto);

			// Características
			var verCaracterísticas = $('<a/>', {
				text: "Características",
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalFeaturesFacility(data.nameFacility, data.featuresFacility);

				}
			});
			$('td:eq(4)', row).html(verCaracterísticas);

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
			$('td:eq(7)', row).html(activo);

			// Acciones 	
			
			var liAccion1 = $('<li/>');
			var accion1 = $('<a/>', {
				text: 'Editar',
				href: '',
				click: function(e) {
					e.preventDefault();
					mostrarModalUpdateFacilities(data.idFacility, data.nameFacility, data.idTechCat, data.activeTechCat, data.featuresFacility, data.active);
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
					mostrarModalUpdatePhotoFacilities(data.idFacility);
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
					mostrarModalDeleteFacilities(data.idFacility);
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

			$('td:eq(8)', row).html(divAcciones);
		}
	});


	$('#facilitiesDatatable thead tr').clone(true).addClass('filters').appendTo('#facilitiesDatatable thead');

	$('#facilitiesDatatable thead tr.filters th').each(function() {
		$(this).off();
		$(this).removeClass('sorting_desc');
		$(this).removeClass('sorting');

	});

	$('#facilitiesDatatable thead tr:eq(1) th.atri').each(function(i) {
		//let title = $(this).text();
		$(this).html('<input style="max-width: 100px;" type="text" />');

		$('input', this).on('keyup change', function() {

			if (table.column(i).search() != this.value) {

				table.column(i).search(this.value).draw();
			}
		});
	});


	$('#facilitiesDatatable thead tr:eq(1) th.selectCat').each(function(i) {
		//let title = $(this).text();
		let listaTechCat = $('#idTechCatSelect');

		$(this).html(listaTechCat);


		$('select', this).on('keyup change', function() {
			if (table.column(2).search() != this.value) {
				//debugger;
				table.column(2).search(this.value).draw();
			}
		});
	});

	$('#facilitiesDatatable thead tr:eq(1) th.selectActive').each(function(i) {

		$(this).html('<select class="form-select"><option value=""><option value="true">Si</option><option value="false">No</option></select>');

		$('select', this).on('keyup change', function() {
			if (table.column(7).search() !== this.value) {

				table.column(7).search(this.value).draw();
			}
		});
	});



	$(document).on("click", "#btnAceptarAddFacilities", function(e) {
		e.preventDefault();
		anyadirNuevoEquipo();

	});

	$(document).on("click", "#btnAceptarUpdateFacilities", function(e) {
		e.preventDefault();
		editarEquipo();

	});
	
	$(document).on("click", "#btnAceptarUpdatePhotoFacilities", function(e) {
		e.preventDefault();
		editarFotoEquipo();

	});

	$(document).on("click", "#btnAceptarDeleteFacilities", function(e) {
		e.preventDefault();
		eliminarEquipo();

	});




}


function mostrarModalFacilitiesPhoto(foto) {
	$('#bodyModalPhotoFacilities').attr('src', 'images/' + foto);
	$('#modalPhotoFacilities').modal('toggle');
}

function mostrarModalFeaturesFacility(nameFacility, featuresFacility) {
	$("#modalFeaturesFacility").modal('toggle');
	$("#headerFeaturesFacility").text(nameFacility);
	$("#bodyFeaturesFacility").text(featuresFacility);
}


function mostrarModalAddFacilities() {
	$("#modalAddFacilities").modal('toggle');
}

function mostrarModalUpdateFacilities(idFacility, nameFacility, idTechcat, activeTechCat, featuresFacility, active) {

	$('#nameFacilityEdit').val(nameFacility);
	if (activeTechCat == "true") {
		$('#idTechCatActiveSelectEdit').val(idTechcat);
	} else {
		$('#idTechCatActiveSelectEdit').val('');
	}


	$('#featuresFacilityEdit').val(featuresFacility);
	$('#idFacilityUpdate').val(idFacility);
	if (active == 'true') {
		$('#activeEdit').val('1');
	} else {
		$('#activeEdit').val('0');
		
	}
	
	$("#modalUpdateFacilities").modal('toggle');
}

function mostrarModalUpdatePhotoFacilities(idFacility) {
	$('#idFacilityPhotoUpdate').val(idFacility);
	$("#modalUpdatePhotoFacilities").modal('toggle');
}

function mostrarModalDeleteFacilities(idFacility) {
		$('#idFacilitiesDelete').val(idFacility);
	$("#modalDeleteFacilities").modal('toggle');
}

function anyadirNuevoEquipo() {

	let form = new FormData();

	form.append("file", $('#photoFacility')[0].files[0]);
	form.append("nameFacility", $("#nameFacility").val());
	form.append("idTechCat", $("#idTechCatActiveSelect").val());
	form.append("featuresFacility", $("#featuresFacility").val());

	form.append("active", $("#active").val());

	$.ajax({
		url: '/management/addFacilitiesData',
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
		$('#facilitiesDatatable').DataTable().ajax.reload();
		$("#nameFacility").val('');
		$("#photoFacility").val('');
		$("#idTechCat").val('');
		$("#featuresFacility").val('');
		$("#active").val('1');
	});
}
	function editarEquipo() {

		let form = new FormData();

		form.append("nameFacility", $("#nameFacilityEdit").val());
		form.append("idTechCat", $("#idTechCatActiveSelectEdit").val());
		form.append("featuresFacility", $("#featuresFacilityEdit").val());
		form.append("idFacility", $("#idFacilityUpdate").val());
		form.append("active", $("#activeEdit").val());

		$.ajax({
			url: '/management/updateFacilitiesData',
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
			$('#facilitiesDatatable').DataTable().ajax.reload();
			$("#nameFacilityEdit").val('');
			$("#idTechCatEdit").val('');
			$("#featuresFacilityEdit").val('');
			$("#idFacilityUpdate").val('');
			$("#activeEdit").val('1');
		});
	}
	
	function editarFotoEquipo() {

		let form = new FormData();

		form.append("file", $('#photoFacilityEdit')[0].files[0]);
		form.append("idFacility", $("#idFacilityPhotoUpdate").val());

		$.ajax({
			url: '/management/updatePhotoFacilitiesData',
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
			$('#facilitiesDatatable').DataTable().ajax.reload();
$('#photoFacilityEdit').val('');
		});
	}

function eliminarEquipo() {

		let form = new FormData();

		form.append("idFacility", $("#idFacilitiesDelete").val());

		$.ajax({
			url: '/management/deleteFacilitiesData',
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
			$('#facilitiesDatatable').DataTable().ajax.reload();
		});
	}
