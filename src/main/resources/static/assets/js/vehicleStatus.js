$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Vehicle Status');
        $('#form').prop('action', '/vehicleStatuses/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Vehicle Status');
        $('#form').prop('action', '/vehicleStatuses/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Vehicle Status Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});