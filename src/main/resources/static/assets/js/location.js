$('document').ready(function() {

    $('table #editButton').on('click', function(event) {
        
        event.preventDefault();
        
        // /locations/findById/?id=1
        var href = $(this).attr('href');

        $.get(href, function(location, status) {
            $('#idEdit').val(location.id);
            $('#ddlCountryEdit').val(location.countryid).change();
            $('#ddlStateEdit').val(location.stateid).change();
            $('#descriptionEdit').val(location.description);
            $('#detailsEdit').val(location.details);
            $('#cityEdit').val(location.city);
            $('#addressEdit').val(location.address);

        });
        
        $('#editModal').modal('show');
    });

    $('table #detailsButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(location, status) {
            $('#idDetails').val(location.id);
            $('#ddlCountryDetails').val(location.countryid).change();
            $('#ddlStateDetails').val(location.stateid).change();
            $('#descriptionDetails').val(location.description);
            $('#detailsDetails').val(location.details);
            $('#cityDetails').val(location.city);
            $('#addressDetails').val(location.address);
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