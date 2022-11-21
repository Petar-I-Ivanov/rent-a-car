$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Employee');
        $('#form').prop('action', '/employees/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Employee');
        $('#form').prop('action', '/employees/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Employee Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});