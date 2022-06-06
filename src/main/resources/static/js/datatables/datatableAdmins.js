function getDatatableAdmins() {

	let table = $('#adminsDatatable').DataTable({
		"sAjaxSource": "/management/getAdminsData",
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
				"mData": "idAdmin",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "nameAdmin",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": false
			},
			{
				"mData": "emailAdmin",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true
			},
			{
				"mData": "usernameAdmin",
				"mRender": function(data, type, row) {
					return data;
				},
				"bSortable": true
			}
		],
		"fnCreatedRow": function(row, data, index) {

			$('td:eq(0)', row).attr("data-label", "ID");
			$('td:eq(1)', row).attr("data-label", "Nombre");
			$('td:eq(2)', row).attr("data-label", "Email");
			$('td:eq(3)', row).attr("data-label", "Nombre de usuario");


		}

	});

$('#inputDeleteAdmins').val($('#inputUser').val());



	// BOTONES

	$(document).on("click", "#btnAceptarAddAdmins", function(e) {

		$('#modalAddAdmins').modal('show');
		var form = $("#formAddAdmins");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			anyadirNuevoAdmin();
		}
		form.addClass('was-validated');

	});

	$(document).on("click", "#btnCancelarAddAdmins", function() {
		$("#formAddAdmins").removeClass('was-validated');
		$(".mensajeError").hide();
		$(".mensajeError").text('');
		$("#nameAdmin").val('');
		$("#emailAdmin").val('');
		$("#usernameAdmin").val('');
		$("#pwdAdmin").val('');
		$("#pwdAdminAddConfirm").val('');
	});

	$(document).on("click", "#btnCloseAddAdmins", function() {
		$("#formAddAdmins").removeClass('was-validated');
		$(".mensajeError").hide();
		$(".mensajeError").text('');
		$("#nameAdmin").val('');
		$("#emailAdmin").val('');
		$("#usernameAdmin").val('');
		$("#pwdAdmin").val('');
		$("#pwdAdminAddConfirm").val('');
	});

	$("#modalAddAdmins").on('hide.bs.modal', function() {
		$("#formAddAdmins").removeClass('was-validated');
		$(".mensajeError").hide();
		$(".mensajeError").text('');
	});


	$(document).on("click", "#btnAceptarUpdateDataAdmins", function(e) {

		$('#modalUpdateDataAdmins').modal('show');
		var form = $("#formUpdateDataAdmins");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			actualizarDataAdmin();
		}
		form.addClass('was-validated');

	});

	$(document).on("click", "#btnCancelarUpdateDataAdmins", function() {
		$("#formUpdateDataAdmins").removeClass('was-validated');
		$(".mensajeError").hide();
		$(".mensajeError").text('');

	});

	$(document).on("click", "#btnCloseUpdateDataAdmins", function() {
		$("#formUpdateDataAdmins").removeClass('was-validated');
		$(".mensajeError").hide();
		$(".mensajeError").text('');

	});

	$("#modalUpdateDataAdmins").on('hide.bs.modal', function() {
		$("#formUpdateDataAdmins").removeClass('was-validated');
		$(".mensajeError").hide();
		$(".mensajeError").text('');
	});


	$(document).on("click", "#btnAceptarPwdAdminActualAdmins", function(e) {

		$('#modalChangePwdAdmins').modal('show');
		var form = $("#formChangePwdAdmins");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			cambiarPwdAdmin();

		}
		form.addClass('was-validated');

	});

	$(document).on("click", "#btnCancelarChangePwdAdmins", function() {
		$("#formChangePwdAdmins").removeClass('was-validated');
		$(".mensajeError").hide();
		$(".mensajeError").text('');
		$("#pwdAdminActual").val('');
		$("#pwdAdminNueva").val('');
		$("#pwdAdminConfirm").val('');


	});

	$(document).on("click", "#btnCloseChangePwdAdmins", function() {
		$("#formChangePwdAdmins").removeClass('was-validated');
		$(".mensajeError").hide();
		$(".mensajeError").text('');
		$("#pwdAdminActual").val('');
		$("#pwdAdminNueva").val('');
		$("#pwdAdminConfirm").val('');
	});

	$("#modalChangePwdAdmins").on('hide.bs.modal', function() {
		$("#formChangePwdAdmins").removeClass('was-validated');
		$(".mensajeError").hide();
		$(".mensajeError").text('');
	});

}


function mostrarModalAddAdmins() {
	$(".mensajeError").hide();
	$(".mensajeError").text('');
	$("#modalAddAdmins").modal('toggle');
}


function mostrarModalUpdateDataAdmins() {
	$(".mensajeError").hide();
	$(".mensajeError").text('');
	$('#nameAdminEdit').val($('#nameAdminInput').val());
	$('#emailAdminEdit').val($('#emailAdminInput').val());
	$("#modalUpdateDataAdmins").modal('toggle');
}

function mostrarModalChangePwdAdmins() {
	$(".mensajeError").hide();
	$(".mensajeError").text('');
	$("#modalChangePwdAdmins").modal('toggle');
}


// FUNCIONES CRUD

function anyadirNuevoAdmin() {

	let form = new FormData();
	form.append("nameAdmin", $("#nameAdmin").val());
	form.append("emailAdmin", $("#emailAdmin").val());
	form.append("usernameAdmin", $("#usernameAdmin").val());
	form.append("pwdAdmin", $("#pwdAdmin").val());
	form.append("pwdAdminConfirm", $("#pwdAdminAddConfirm").val());

	$.ajax({
		url: '/management/addAdminsData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			$('#adminsDatatable').DataTable().ajax.reload();
			okMessage();
			$(".alert").text($('#addOkMensaje').val());
		},
		error: function(e) {
			$('#modalAddAdmins').modal('show');
			$("#formAddAdmins").removeClass('was-validated');
			$(".mensajeError").show();
			var mensajeError ="";

			if (e.responseText == "usernameAdminUnique") {
				mensajeError = $('#usernameAdminUnique').val();
			}
			if (e.responseText == "emailAdminUnique") {
				mensajeError = $('#emailAdminUnique').val();
			}
			if (e.responseText == "pswConfirmNoCoincide") {
				mensajeError = $('#pswConfirmNoCoincide').val();
			}

			$(".mensajeError").text(mensajeError);
		}
	}).done(function() {
		$("#formAddAdmins").removeClass('was-validated');
		$('#modalAddAdmins').modal('hide');
		$(".mensajeError").hide();
		$(".mensajeError").text('');
		$("#nameAdmin").val('');
		$("#emailAdmin").val('');
		$("#usernameAdmin").val('');
		$("#pwdAdmin").val('');
		$("#pwdAdminAddConfirm").val('');

	});
}

function actualizarDataAdmin() {

	let form = new FormData();

	form.append("nameAdmin", $("#nameAdminEdit").val());
	form.append("emailAdmin", $("#emailAdminEdit").val());
	form.append("usernameAdmin", $("#inputUser").val());

	$.ajax({
		url: '/management/updateDataAdmins',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {

			$('#adminsDatatable').DataTable().ajax.reload();
			okMessage();
			$(".alert").text($('#updateOkMensaje').val());
			$("#nameAdminInput").val(form.get("nameAdmin"));
			$("#emailAdminInput").val(form.get("emailAdmin"));

		},
		error: function(e) {
			$('#modalUpdateDataAdmins').modal('show');
			$("#formUpdateDataAdmins").removeClass('was-validated');
			$(".mensajeError").show();

			var mensajeError ="";

			if (e.responseText == "emailAdminUnique") {
				mensajeError = $('#emailAdminUnique').val();
			}
			$(".mensajeError").text(mensajeError);

		}
	}).done(function() {
		$("#formUpdateDataAdmins").removeClass('was-validated');
		$('#modalUpdateDataAdmins').modal('hide');
		$(".mensajeError").hide();
		$(".mensajeError").text('');
	});



}


function cambiarPwdAdmin() {
	debugger;
	let form = new FormData();
	form.append("pwdAdminActual", $("#pwdAdminActual").val());
	form.append("pwdAdminNueva", $("#pwdAdminNueva").val());
	form.append("pwdAdminConfirm", $("#pwdAdminConfirm").val());
	form.append("usernameAdmin", $("#inputUser").val());

	$.ajax({
		url: '/management/changePwdAdmins',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {

			$('#adminsDatatable').DataTable().ajax.reload();
			okMessage();
			$(".alert").text($('#updateOkMensaje').val());

		},
		error: function(e) {
			$('#modalChangePwdAdmins').modal('show');
			$("#formChangePwdAdmins").removeClass('was-validated');
			$(".mensajeError").show();


			var mensajeError;
			if (e.responseText == "pswActualMal") {
				mensajeError = $('#pswActualMal').val();
			}
			if (e.responseText == "pswNuevaViejaIgual") {
				mensajeError = $('#pswNuevaViejaIgual').val();
			}
			if (e.responseText == "pswConfirmNoCoincide") {
				mensajeError = $('#pswConfirmNoCoincide').val();
			}
			$(".mensajeError").text(mensajeError);

		}
	}).done(function() {
		$("#formChangePwdAdmins").removeClass('was-validated');
		$('#modalChangePwdAdmins').modal('hide');
		$(".mensajeError").hide();
		$(".mensajeError").text('');
		$("#pwdAdminActual").val('');
		$("#pwdAdminNueva").val('');
		$("#pwdAdminConfirm").val('');

	});
}

function eliminarAdmin() {

	let form = new FormData();

	form.append("usernameAdmin", $("#inputUser").val());

	$.ajax({
		url: '/management/deleteAdminsData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			$("#inputUser").val('');
//			if (data == '') {
//				$('#techCatDatatable').DataTable().ajax.reload();
//				okMessage();
//				$(".alert").text($('#deleteOkMensaje').val());
//			} else {
//				errorMessage();
//				$(".alert").text($('#deleteErrorMensaje').val());
//			}
		},
		error: function(data) {
//			errorMessage();
//			$(".alert").text($('#deleteErrorMensaje').val());
		}
	});
}