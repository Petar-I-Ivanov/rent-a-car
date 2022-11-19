$('document').ready(function () {

    $('#addButton').on('click', function (event) {

        resetModal();

        //change the form for Adding
        $('#countryModalLabel').text("Add Country");

        $('#form').attr("action", "/countries/addNew");
        $('#form').attr("method", "post");

        $('#id').prop('disabled', true);
        $('#submitButton').text("Create");

        $('#countryModal').modal('show');
    });

    $('table #editButton').on('click', function (event) {

        //prevent from opening JSON link
        event.preventDefault();

        //gets the prevented JSON format
        // and uses it to set the fields
        var href = $(this).attr('href');

        resetModal();
        setInputFields(href);

        //change the form for Editing
        $('#countryModalLabel').text("Edit Country");

        $('#form').attr("action", "/countries/update");
        $('#form').attr("method", "put");

        $('#id').prop('readonly', true);
        $('#submitButton').text("Update");

        $('#countryModal').modal('show');
    });

    $('table #detailsButton').on('click', function (event) {

        event.preventDefault();

        var href = $(this).attr('href');

        resetModal();
        setInputFields(href);

        
        //make input fields readonly
        $('#id').prop('readonly', true);
        $('#description').prop('readonly', true);
        $('#capital').prop('readonly', true);
        $('#code').prop('readonly', true);
        $('#continent').prop('readonly', true);
        $('#nationality').prop('readonly', true);

        //change the form for Details
        $('#countryModalLabel').text("Country Details");

        $('#submitButton').prop('disabled', true);
        $('#submitButton').prop('hidden', true);

        $('#countryModal').modal('show');
    });

});

function resetModal() {

    //empty fields and remove readonly
    $('#id').val('').prop('readonly', false);
    $('#description').val('').prop('readonly', false);
    $('#capital').val('').prop('readonly', false);
    $('#code').val('').prop('readonly', false);
    $('#continent').val('').prop('readonly', false);
    $('#nationality').val('').prop('readonly', false);

    //remove label
    $('#countryModalLabel').text("");

    //remove form action
    $('#form').attr("action", "/countries");
    $('#form').attr("method", "");

    //remove disabled ID and reset SUBMIT_BUTTON
    $('#id').prop('disabled', false);
    $('#submitButton').prop('disabled', false);
    $('#submitButton').prop('hidden', false);
    $('#submitButton').text("");
}

function setInputFields(href) {

    $.get(href, function (country, status) {

        $('#id').val(country.id);
        $('#description').val(country.description);
        $('#capital').val(country.capital);
        $('#code').val(country.code);
        $('#continent').val(country.continent);
        $('#nationality').val(country.nationality);
    });
}
