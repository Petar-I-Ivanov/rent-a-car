$('document').ready(function() {

    $('#addButton').on('click', function() {

        resetModal();

        $('#contactModalLabel').text("Add Contact");

        $('#form').attr("action", "/contacts/addNew");
        $('#form').attr("method", "post");

        $('#id').prop('disabled', true);
        $('#submitButton').text("Create");

        $('#contactModal').modal('show');
    });

    $('table #editButton').on('click', function(event) {
        
        event.preventDefault();
        resetModal();
        setInputFields($(this).attr('href'));
        
        $('#contactModalLabel').text("Edit Contact");

        $('#form').attr("action", "/contacts/update");
        $('#form').attr("method", "put");

        $('#id').prop('readonly', true);
        $('#submitButton').text("Update");

        $('#contactModal').modal('show');
    });

    $('table #detailsButton').on('click', function(event) {
        
        event.preventDefault();
        resetModal();
        setInputFields($(this).attr('href'));
        
        $('#id').prop('readonly', true);
        $('#firstName').prop('readonly', true);
        $('#lastName').prop('readonly', true);
        $('#phone').prop('readonly', true);
        $('#mobile').prop('readonly', true);
        $('#email').prop('readonly', true);
        $('#remarks').prop('readonly', true);

        $('#contactModalLabel').text("Contact Details");

        $('#submitButton').prop('disabled', true);
        $('#submitButton').prop('hidden', true);

        $('#contactModal').modal('show');
    });

});

function resetModal() {

    //empty fields and remove readonly
    $('#id').val('').prop('readonly', false);
    $('#firstName').val('').prop('readonly', false);
    $('#lastName').val('').prop('readonly', false);
    $('#phone').val('').prop('readonly', false);
    $('#mobile').val('').prop('readonly', false);
    $('#email').val('').prop('readonly', false);
    $('#remarks').val('').prop('readonly', false);

    //remove label
    $('#contactModalLabel').text("");

    //remove form action
    $('#form').attr("action", "/contacts");
    $('#form').attr("method", "");

    //remove disabled ID and reset SUBMIT_BUTTON
    $('#id').prop('disabled', false);
    $('#submitButton').prop('disabled', false);
    $('#submitButton').prop('hidden', false);
    $('#submitButton').text("");
}

function setInputFields(href) {

    $.get(href, function(contact, status) {

        $('#id').val(contact.id);
        $('#firstName').val(contact.firstName);
        $('#lastName').val(contact.lastName)
        $('#phone').val(contact.phone);
        $('#mobile').val(contact.mobile);
        $('#email').val(contact.email);
        $('#remarks').val(contact.remarks);
    });
}