$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Contact');
        $('#form').prop('action', '/contacts/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Contact');
        $('#form').prop('action', '/contacts/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Contact Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});