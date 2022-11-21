$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Employee Type');
        $('#form').prop('action', '/employeeTypes/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Employee Type');
        $('#form').prop('action', '/employeeTypes/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Employee Type Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});