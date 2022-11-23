$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Role');
        $('#form').prop('action', '/roles/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Role');
        $('#form').prop('action', '/roles/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Role Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});