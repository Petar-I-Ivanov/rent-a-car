$('document').ready(function() {

    $('table #editButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(client, status) {
            $('#idEdit').val(client.id);
            $('#nameEdit').val(client.name);
            $('#addressEdit').val(client.address);
            $('#cityEdit').val(client.city);
            $('#phoneEdit').val(client.phone);
            $('#mobileEdit').val(client.mobile);
            $('#websiteEdit').val(client.website);
            $('#emailEdit').val(client.email);
            $('#ddlCountryEdit').val(client.countryid).change();
            $('#ddlStateEdit').val(client.stateid).change();
            $('#detailsEdit').val(client.details);
        });
        
        $('#editModal').modal('show');
    });

    $('table #detailsButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(client, status) {
            $('#idDetails').val(client.id);
            $('#nameDetails').val(client.name);
            $('#addressDetails').val(client.address);
            $('#cityDetails').val(client.city);
            $('#phoneDetails').val(client.phone);
            $('#mobileDetails').val(client.mobile);
            $('#websiteDetails').val(client.website);
            $('#emailDetails').val(client.email);
            $('#ddlCountryDetails').val(client.countryid).change();
            $('#ddlStateDetails').val(client.stateid).change();
            $('#detailsDetails').val(client.details);
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