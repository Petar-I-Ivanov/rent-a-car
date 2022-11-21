$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Vehicle Make');
        $('#form').prop('action', '/vehicleMakes/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Vehicle Make');
        $('#form').prop('action', '/vehicleMakes/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Vehicle Make Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});