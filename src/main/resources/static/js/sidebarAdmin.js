$(document).ready(function() {

	// CATEGORÍAS 
	$(document).on("click", ".catSidebar", function(e) {
		e.preventDefault();
		$(".catSidebar").removeClass("active");
		$(this).addClass("active");
		let numCat = $(this).attr("data-num-cat");
		getDataTableCategory(numCat);
	});


	// Data Picker 

	if ($('#inputCatPublication').val() == "true") {
		$('#sidebarPublication').click();
		$('#publicationsDatatable').DataTable().ajax.reload();
	}







});

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


			}



		},
		error: function(e) {
			console.log(e);
		},
	});
	return false;
}

function activateDatePicker() {
	$('.input-group.date').datepicker({
		format: "dd/mm/yyyy",
		language: "es"
	});
}

function okMessage() {

	$("#response").animate({
		height: '+=72px'
	}, 300);

	$('<div class="alert alert-success">' +
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


