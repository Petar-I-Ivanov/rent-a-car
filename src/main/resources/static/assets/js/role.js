$('document').ready(function() {

    $('table #editButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(role, status) {
            $('#idEdit').val(role.id);
            $('#descriptionEdit').val(role.description);
            $('#detailsEdit').val(role.details);
        });
        
        $('#editModal').modal('show');
    });

    $('table #detailsButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(role, status) {

            var createdDate = role.createdDate.substr(0,10);
            var lastModifiedDate = role.lastModifiedDate.substr(0,10);

            $('#idDetails').val(role.id);
            $('#descriptionDetails').val(role.description);
            $('#detailsDetails').val(role.details);
            $('#createdByDetails').val(role.createdBy);
            $('#createdOnDetails').val(createdDate);
            $('#modifiedByDetails').val(role.lastModifiedBy);
            $('#modifiedOnDetails').val(lastModifiedDate);
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