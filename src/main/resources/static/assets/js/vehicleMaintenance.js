$('document').ready(function() {

    $('table #editButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(vehicleMaintenance, status) {

            var startDate = vehicleMaintenance.startDate.substr(0,10);
            var endDate = vehicleMaintenance.endDate.substr(0,10);

            $('#idEdit').val(vehicleMaintenance.id);
            $('#ddlVehicleEdit').val(vehicleMaintenance.vehicleid).change();
            $('#startDateEdit').val(startDate);
            $('#endDateEdit').val(endDate);
            $('#priceEdit').val(vehicleMaintenance.price);
            $('#ddlSupplierEdit').val(vehicleMaintenance.supplierid).change();
            $('#remakrsEdit').val(vehicleMaintenance.remarks);
        });
        
        $('#editModal').modal('show');
    });

    $('table #detailsButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(vehicleMaintenance, status) {

            var startDate = vehicleMaintenance.startDate.substr(0,10);
            var endDate = vehicleMaintenance.endDate.substr(0,10);

            $('#idDetails').val(vehicleMaintenance.id);
            $('#vehicleDetails').val(vehicleMaintenance.vehicle.name);
            $('#startDateDetails').val(startDate);
            $('#endDateDetails').val(endDate);
            $('#priceDetails').val(vehicleMaintenance.price);
            $('#supplierDetails').val(vehicleMaintenance.supplier.name);
            $('#remakrsDetails').val(vehicleMaintenance.remarks);
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