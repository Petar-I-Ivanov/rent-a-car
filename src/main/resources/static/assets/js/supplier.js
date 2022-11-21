$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Supplier');
        $('#form').prop('action', '/suppliers/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Supplier');
        $('#form').prop('action', '/suppliers/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Supplier Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});