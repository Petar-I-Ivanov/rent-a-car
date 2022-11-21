$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Vehicle Model');
        $('#form').prop('action', '/vehicleModels/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Vehicle Model');
        $('#form').prop('action', '/vehicleModels/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Vehicle Model Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});