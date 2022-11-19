$('document').ready(function() {

    $('table #editButton').on('click', function(event) {
        
        event.preventDefault();
        
        // /states/findById/?id=1
        var href = $(this).attr('href');

        $.get(href, function(state, status) {
            $('#idEdit').val(state.id);
            $('#ddlCountryEdit').val(state.countryid).change();
            $('#nameEdit').val(state.name);
            $('#capitalEdit').val(state.capital);
            $('#codeEdit').val(state.code);
            $('#detailsEdit').val(state.details);
        });
        
        $('#editModal').modal('show');
    });

    $('table #detailsButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(state, status) {
            $('#idDetails').val(state.id);
            $('#ddlCountryDetails').val(state.countryid).change();
            $('#nameDetails').val(state.name);
            $('#capitalDetails').val(state.capital);
            $('#codeDetails').val(state.code);
            $('#detailsDetails').val(state.details);
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