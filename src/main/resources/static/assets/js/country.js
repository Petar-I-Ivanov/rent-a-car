$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Country');
        $('#form').prop('action', '/countries/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Country');
        $('#form').prop('action', '/countries/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Country Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});
