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


var inputPrueba = $('#inputPrueba').val();
$('#prueba').text(inputPrueba);


	// BOTONES

	$(document).on("click", "#btnAceptarAddAdmins", function(e) {

		$('#modalAddAdmins').modal('show');
		var form = $("#formAddAdmins");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			anyadirNuevoAdmin();
			$('#modalAddAdmins').modal('hide');
		}
		form.addClass('was-validated');

	});

	$(document).on("click", "#btnCancelarAddAdmins", function() {
		$("#formAddAdmins").removeClass('was-validated');
		$("#nameAdmin").val('');
		$("#emailAdmin").val('');
		$("#usernameAdmin").val('');
		$("#pwdAdmin").val('');
	});

	$(document).on("click", "#btnCloseAddAdmins", function() {
		$("#formAddAdmins").removeClass('was-validated');
		$("#nameAdmin").val('');
		$("#emailAdmin").val('');
		$("#usernameAdmin").val('');
		$("#pwdAdmin").val('');
	});





}


function mostrarModalAddAdmins() {
	$("#modalAddAdmins").modal('toggle');
}


// FUNCIONES CRUD

function anyadirNuevoAdmin() {

	let form = new FormData();
	form.append("nameAdmin", $("#nameAdmin").val());
	form.append("emailAdmin", $("#emailAdmin").val());
	form.append("usernameAdmin", $("#usernameAdmin").val());
	form.append("pwdAdmin", $("#pwdAdmin").val());

	$.ajax({
		url: '/management/addAdminsData',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		success: function(data) {
			if (data == '') {
				$('#adminsDatatable').DataTable().ajax.reload();
				okMessage();
				$(".alert").text($('#addOkMensaje').val());
			} else {
				errorMessage();
				$(".alert").text($('#addErrorMensaje').val());
			}
		},
		error: function(data) {
			errorMessage();
			$(".alert").text($('#addErrorMensaje').val());
		}
	}).done(function() {
		$("#formAddAdmins").removeClass('was-validated');
		$("#nameAdmin").val('');
		$("#emailAdmin").val('');
		$("#usernameAdmin").val('');
		$("#pwdAdmin").val('');

	});
}