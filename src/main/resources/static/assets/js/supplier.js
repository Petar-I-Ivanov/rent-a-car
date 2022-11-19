$('document').ready(function() {

    $('table #editButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(supplier, status) {
            $('#idEdit').val(supplier.id);
            $('#nameEdit').val(supplier.name);
            $('#addressEdit').val(supplier.address);
            $('#cityEdit').val(supplier.city);
            $('#phoneEdit').val(supplier.phone);
            $('#mobileEdit').val(supplier.mobile);
            $('#websiteEdit').val(supplier.website);
            $('#emailEdit').val(supplier.email);
            $('#ddlCountryEdit').val(supplier.countryid).change();
            $('#ddlStateEdit').val(supplier.stateid).change();
            $('#detailsEdit').val(supplier.details);
        });
        
        $('#editModal').modal('show');
    });

    $('table #detailsButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(supplier, status) {
            $('#idDetails').val(supplier.id);
            $('#nameDetails').val(supplier.name);
            $('#addressDetails').val(supplier.address);
            $('#cityDetails').val(supplier.city);
            $('#phoneDetails').val(supplier.phone);
            $('#mobileDetails').val(supplier.mobile);
            $('#websiteDetails').val(supplier.website);
            $('#emailDetails').val(supplier.email);
            $('#countryDetails').val(supplier.country.description);
            $('#stateDetails').val(supplier.state.name);
            $('#detailsDetails').val(supplier.details);
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