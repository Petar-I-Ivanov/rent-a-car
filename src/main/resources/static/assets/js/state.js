$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add State');
        $('#form').prop('action', '/states/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit State');
        $('#form').prop('action', '/states/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('State Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});