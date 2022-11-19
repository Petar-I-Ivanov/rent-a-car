$('document').ready(function() {

    $('table #editButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(vehicleMovement, status) {

            var dateOne = vehicleMovement.date1.substr(0,10);
            var dateTwo = vehicleMovement.date2.substr(0,10);

            $('#idEdit').val(vehicleMovement.id);
            $('#ddlVehicleEdit').val(vehicleMovement.vehicleid).change();
            $('#ddlLocationOneEdit').val(vehicleMovement.locationid1).change();
            $('#dateOneEdit').val(vehicleMovement.dateOne);
            $('#ddlLocationTwoEdit').val(vehicleMovement.locationid2).change();
            $('#dateTwoEdit').val(vehicleMovement.dateTwo);
            $('#remarksEdit').val(vehicleMovement.remarks);
        });
        
        $('#editModal').modal('show');
    });

    $('table #detailsButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(vehicleMovement, status) {

            var dateOne = vehicleMovement.date1.substr(0,10);
            var dateTwo = vehicleMovement.date2.substr(0,10);

            $('#idDetails').val(vehicleMovement.id);
            $('#vehicleDetails').val(vehicleMovement.vehicle.name);
            $('#locationOneDetails').val(vehicleMovement.location1.description);
            $('#dateOneDetails').val(vehicleMovement.dateOne);
            $('#locationTwoDetails').val(vehicleMovement.location2.description);
            $('#dateTwoDetails').val(vehicleMovement.dateTwo);
            $('#remarksDetails').val(vehicleMovement.remarks);
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