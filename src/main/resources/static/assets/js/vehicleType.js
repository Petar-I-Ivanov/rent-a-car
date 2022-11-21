$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Vehicle Type');
        $('#form').prop('action', '/vehicleTypes/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Vehicle Type');
        $('#form').prop('action', '/vehicleTypes/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Vehicle Type Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});