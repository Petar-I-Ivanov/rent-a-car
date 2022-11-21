$('document').ready(function() {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add Job Title');
        $('#form').prop('action', '/jobTitles/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit Job Title');
        $('#form').prop('action', '/jobTitles/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text('Job Title Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});