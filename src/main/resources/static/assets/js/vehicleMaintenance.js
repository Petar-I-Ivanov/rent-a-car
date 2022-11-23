$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Vehicle Maintenance');
        $('#form').prop('action', '/vehicleMaintenances/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Vehicle Maintenance');
        $('#form').prop('action', '/vehicleMaintenances/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Vehicle Maintenance Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});