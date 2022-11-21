$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Invoice Status');
        $('#form').prop('action', '/invoiceStatuses/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Invoice Status');
        $('#form').prop('action', '/invoiceStatuses/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Invoice Status Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});