$('document').ready(function() {

    $('table #editButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(contact, status) {
            $('#idEdit').val(contact.id);
            $('#firstNameEdit').val(contact.firstname);
            $('#lastNameEdit').val(contact.lastname);
            $('#phoneEdit').val(contact.phone);
            $('#mobileEdit').val(contact.mobile);
            $('#emailEdit').val(contact.email);
            $('#remarksEdit').val(contact.remarks);
        });
        
        $('#editModal').modal('show');
    });

    $('table #detailsButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(contact, status) {
            $('#idDetails').val(contact.id);
            $('#firstNameDetails').val(contact.firstname);
            $('#lastNameDetails').val(contact.lastname);
            $('#phoneDetails').val(contact.phone);
            $('#mobileDetails').val(contact.mobile);
            $('#emailDetails').val(contact.email);
            $('#remarksDetails').val(contact.remarks);
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