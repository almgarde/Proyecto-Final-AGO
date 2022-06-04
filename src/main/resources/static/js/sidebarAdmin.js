$(document).ready(function() {

	// CATEGORÍAS SIDEBAR 

	$(document).on("click", ".catSidebar", function(e) {
		e.preventDefault();
		$(".catSidebar").removeClass("active");
		$(this).addClass("active");
		let numCat = $(this).attr("data-num-cat");
		getDataTableCategory(numCat);
	});

	if ($('#inputCatPublication').val() == "true") {
		$('#sidebarPublication').click();
		$('#publicationsDatatable').DataTable().ajax.reload();
	}

	// CAROUSEL 

	$(document).on("click", ".imgCarousel", function(e) {

		e.preventDefault;

		if ($(this).attr("id") == "image1Carousel") {
			$('#headerModalImageCarousel').text($('#imagen1').val());
			$('#bodyModalImageCarousel').attr('src', 'images/image1Carousel.png');
		} else if ($(this).attr("id") == "image2Carousel") {
			$('#headerModalImageCarousel').text($('#imagen2').val());
			$('#bodyModalImageCarousel').attr('src', 'images/image2Carousel.jpg');
		} else if ($(this).attr("id") == "image3Carousel") {
			$('#headerModalImageCarousel').text($('#imagen3').val());
			$('#bodyModalImageCarousel').attr('src', 'images/image3Carousel.jpg');
		}
		$('#modalImageCarousel').modal('toggle');

	});

	$(document).on("click", "#btnActualizarCarouselImageHome", function(e) {

		var form = $("#formUpdateImageCarousel");

		if (form[0].checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		} else {
			actualizarCarouselImages();
		}
		form.addClass('was-validated');

	});

});


// CATEGORÍAS 

function getDataTableCategory(numCat) {

	$.ajax({
		type: "post",
		url: "/management/getDatatableCategory/",
		data: {
			numCat: numCat
		},
		success: function(html) {

			$("#containerDataTables").html(html);

			let selectedCat = $('.selectedCat').val();

			switch (selectedCat) {

				case "0":
					break;

				case "1":
					getDatatableNews();
					break;

				case "2":
					getDatatableProCat();
					break;

				case "3":
					getDatatableMembers();
					break;

				case "4":
					getDatatableProjects();
					break;

				case "5":
					getDatatableTechCat();
					break;

				case "6":
					getDatatableFacilities();
					break;

				case "7":
					getDatatablePublications();
					break;

				case "8":
					getDatatableThesis();
					break;

				case "9":
					getDatatableLinks();
					break;

				case "10":
					getDatatableAdmins();
					break;

			}
		},
		error: function(e) {
			console.log(e);
		}
	});

}


// DATEPICKER

function activateDatePicker() {

	const valores = window.location.search;
	const urlParams = new URLSearchParams(valores);

	var lang = urlParams.get('lang');
	$('.input-group.date').datepicker({
		format: "dd/mm/yyyy",
		language: lang

	});
}


// MENSAJES 

function okMessage() {

	$("#response").animate({
		height: '+=72px'
	}, 300);

	$('<div style="width:100%" class="alert alert-success">' +
		'<button type="button" class="close" data-dismiss="alert">' +
		'&times;</button>success</div>').hide().appendTo('#response').fadeIn(1000);

	$(".alert").delay(3000).fadeOut(
		function() {
			$(this).remove();
		});

	$("#response").delay(4000).animate({
		height: '-=72px'
	}, 300);

};

function errorMessage() {

	$("#response").animate({
		height: '+=72px'
	}, 300);

	$('<div class="alert alert-danger">' +
		'<button type="button" class="close" data-dismiss="alert">' +
		'&times;</button>Failed</div>').hide().appendTo('#response').fadeIn(1000);

	$(".alert").delay(3000).fadeOut(
		function() {
			$(this).remove();
		});

	$("#response").delay(4000).animate({
		height: '-=72px'
	}, 300);
};


function actualizarCarouselImages() {

	let form = new FormData();

	form.append("file", $('#imageEditCarousel')[0].files[0]);
	form.append("numImageCarousel", $("#numImageCarousel").val());

	$.ajax({
		url: '/management/updateCarouselImages',
		type: "POST",
		processData: false,
		contentType: false,
		data: form,
		timeout: 5000,
		success: function(html) {
			if (html != '') {
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
		$("#formUpdateImageCarousel").removeClass('was-validated');
		$("#numImageCarousel").val('1');
		$('#imageEditCarousel').val('');
	});

}


