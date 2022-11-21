$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Invoice');
        $('#form').prop('action', '/invoices/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Invoice');
        $('#form').prop('action', '/invoices/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Invoice Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});