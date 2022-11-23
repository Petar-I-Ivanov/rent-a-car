$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Vehicle Movement');
        $('#form').prop('action', '/vehicleMovements/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Vehicle Movement');
        $('#form').prop('action', '/vehicleMovements/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Vehicle Movement Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});