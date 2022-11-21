$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Client');
        $('#form').prop('action', '/clients/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Client');
        $('#form').prop('action', '/clients/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Client Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});