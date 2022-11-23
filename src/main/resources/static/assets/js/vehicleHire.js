$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Vehicle Hire');
        $('#form').prop('action', '/vehicleHires/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Vehicle Hire');
        $('#form').prop('action', '/vehicleHires/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Vehicle Hire Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});
