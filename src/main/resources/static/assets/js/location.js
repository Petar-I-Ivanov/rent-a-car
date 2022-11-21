$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Location');
        $('#form').prop('action', '/locations/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Location');
        $('#form').prop('action', '/locations/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Location Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});