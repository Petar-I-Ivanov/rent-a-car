$('document').ready(function() {

    $('table #editButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(vehicleType, status) {
            $('#idEdit').val(vehicleType.id);
            $('#descriptionEdit').val(vehicleType.description);
            $('#detailsEdit').val(vehicleType.details);
        });
        
        $('#editModal').modal('show');
    });

    $('table #detailsButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(vehicleType, status) {
            $('#idDetails').val(vehicleType.id);
            $('#descriptionDetails').val(vehicleType.description);
            $('#detailsDetails').val(vehicleType.details);
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