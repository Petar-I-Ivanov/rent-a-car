$('document').ready(function() {

    $('table #editButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(vehicleHire, status) {

            var dateOut = vehicleHire.dateOut.substr(0,10);
            var dateIn = vehicleHire.dateIn.substr(0,10);

            $('#idEdit').val(vehicleHire.id);
            $('#ddlVehicleEdit').val(vehicleHire.vehicleid).change();
            $('#dateOutEdit').val(dateOut);
            $('#timeOutEdit').val(vehicleHire.timeOut);
            $('#dateInEdit').val(dateIn);
            $('#timeInEdit').val(vehicleHire.timeIn);
            $('#ddlClientEdit').val(vehicleHire.clientid).change();
            $('#ddlLocationEdit').val(vehicleHire.locationid).change();
            $('#priceEdit').val(vehicleHire.price);
            $('#remakrsEdit').val(vehicleHire.remarks);
        });
        
        $('#editModal').modal('show');
    });

    $('table #detailsButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(vehicleHire, status) {

            var dateOut = vehicleHire.dateOut.substr(0,10);
            var dateIn = vehicleHire.dateIn.substr(0,10);

            $('#idDetails').val(vehicleHire.id);
            $('#vehicleDetails').val(vehicleHire.vehicle.name);
            $('#dateOutDetails').val(dateOut);
            $('#timeOutDetails').val(vehicleHire.timeOut);
            $('#dateInDetails').val(dateIn);
            $('#timeInDetails').val(vehicleHire.timeIn);
            $('#clientDetails').val(vehicleHire.client.name);
            $('#locationDetails').val(vehicleHire.location.description);
            $('#priceDetails').val(vehicleHire.price);
            $('#remakrsDetails').val(vehicleHire.remarks);
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