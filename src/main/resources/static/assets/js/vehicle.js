$('document').ready(function() {

    $('table #editButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(vehicle, status) {

            var registrationDate = vehicle.registrationDate.substr(0,10);
            var acquisitionDate = vehicle.acquisitionDate.substr(0,10);

            $('#idEdit').val(vehicle.id);
            $('#nameEdit').val(vehicle.name);

            $('#ddlVehicleTypeEdit').val(vehicle.vehicletypeid).change();
            $('#vehicleNumberEdit').val(vehicle.vehicleNumber);
            $('#registrationDateEdit').val(registrationDate);
            $('#acquisitionDateEdit').val(acquisitionDate);

            $('#ddlVehicleMakeEdit').val(vehicle.vehiclemakeid).change();
            $('#powerEdit').val(vehicle.power);
            $('#fuelCapacityEdit').val(vehicle.fuelCapacity);

            $('#ddlVehicleStatusEdit').val(vehicle.vehiclestatusid).change();
            $('#netWeightEdit').val(vehicle.netWeight);

            $('#ddlVehicleModelEdit').val(vehicle.vehiclemodelid).change();
            $('#ddlEmployeeEdit').val(vehicle.employeeid).change();
            $('#ddlLocationEdit').val(vehicle.locationid).change();

            $('#remarksEdit').val(vehicle.remarks);
        });
        
        $('#editModal').modal('show');
    });

    $('table #detailsButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(vehicle, status) {

            var registrationDate = vehicle.registrationDate.substr(0,10);
            var acquisitionDate = vehicle.acquisitionDate.substr(0,10);

            $('#idDetails').val(vehicle.id);
            $('#nameDetails').val(vehicle.name);

            $('#vehicleTypeDetails').val(vehicle.vehicleType.description);
            $('#vehicleNumberDetails').val(vehicle.vehicleNumber);
            $('#registrationDateDetails').val(registrationDate);
            $('#acquisitionDateDetails').val(acquisitionDate);

            $('#vehicleMakeDetails').val(vehicle.vehicleMake.description);
            $('#powerDetails').val(vehicle.power);
            $('#fuelCapacityDetails').val(vehicle.fuelCapacity);

            $('#vehicleStatusDetails').val(vehicle.vehicleStatus.description);
            $('#netWeightDetails').val(vehicle.netWeight);

            $('#vehicleModelDetails').val(vehicle.vehicleModel.description);
            $('#employeeDetails').val(vehicle.inCharge.username);
            $('#locationDetails').val(vehicle.currentLocation.description);

            $('#remarksDetails').val(vehicle.remarks);
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