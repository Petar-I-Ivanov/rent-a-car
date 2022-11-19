$('document').ready(function() {

    $('table #editButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(vehicleModel, status) {
            $('#idEdit').val(vehicleModel.id);
            $('#descriptionEdit').val(vehicleModel.description);
            $('#detailsEdit').val(vehicleModel.details);
        });
        
        $('#editModal').modal('show');
    });

    $('table #detailsButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(vehicleModel, status) {
            $('#idDetails').val(vehicleModel.id);
            $('#descriptionDetails').val(vehicleModel.description);
            $('#detailsDetails').val(vehicleModel.details);
            $('#createdByDetails').val(vehicleModel.createdBy);
            $('#createdOnDetails').val(vehicleModel.createdDate.substr(0,19).replace("T", " "));
            $('#lastUpdatedByDetails').val(vehicleModel.lastModifiedBy);
            $('#lastUpdatedOnDetails').val(vehicleModel.lastModifiedDate.substr(0,19).replace("T", " "));
        });
        
        $('#detailsModal').modal('show');
    });

	$('table #deleteButton').on('click', function(event) {

		event.preventDefault();

		var href = $(this).attr("href");

		$('#confirmDeleteButton').attr('href', href);
		
		$('#deleteModal').modal('show');
	});
});