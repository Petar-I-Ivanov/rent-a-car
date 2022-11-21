$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Vehicle');
        $('#form').prop('action', '/vehicles/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Vehicle');
        $('#form').prop('action', '/vehicles/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Vehicle Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});