//  changes modalLabel, form's action link and form's method based on what button is clicked and which model page

// _objectName is entity's name capitalized (Like That)
// objectName is enetity's name like how it's added in the model, but singular (likeThat)
// objectsName is entity's name like in the model, but plural (likeThats)

// these variables are declared in the <head> tag of each model page with needed values

$('document').ready(function () {

    $('#addButton').on('click', function () {

        $('#modalLabel').text('Add ' + _objectName);
        $('#form').prop('action', '/' + objectsName + '/addNew');
        $('#form').prop('method', 'post');
    });

    $('table #editButton').on('click', function () {

        $('#modalLabel').text('Edit ' + _objectName);
        $('#form').prop('action', '/' + objectsName + '/update');
        $('#form').prop('method', 'put');
    });

    $('table #detailsButton').on('click', function () {

        $('#modalLabel').text(_objectName + ' Details');
        $('#form').prop('action', '');
        $('#form').prop('method', '');
    });
});