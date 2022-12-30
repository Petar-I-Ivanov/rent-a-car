$('document').ready(function () {

    $('#addButton').on('click', function () {

        resetModal();

//      set the form and show modal
        $('#id').prop('disabled', true);
        $('#submitButton').text("Create");
        $('#modal').modal('show');
    });

    $('table #editButton').on('click', function (event) {

//      prevent from opening the link following to the JSON
        event.preventDefault();
        resetModal();
        setInputFields($(this).attr('href'));

//      set the form and show modal
        $('#id').prop('readonly', true);
        $('#submitButton').text("Update");
        $('#modal').modal('show');
    });

    $('table #detailsButton').on('click', function (event) {

//      prevent from opening the link following to the JSON
        event.preventDefault();
        resetModal();
        setInputFields($(this).attr('href'));

//      loop through all the inputs and selects inside the form with id="form"
        $('#form input, #form select').each(function () {

//          get the <input> element inside this variable
            var input = $(this);

//          check if variable is <select>, disable it
//          else it is <input>, make it readonly
            if (input.is("select")) {
                input.prop('disabled', true);
            }
            else {
                input.prop('readonly', true);
            }
        });

//      set the form and show modal
        $('#submitButton').prop('disabled', true);
        $('#submitButton').prop('hidden', true);
        $('#modal').modal('show');
    });

    $('table #deleteButton').on('click', function(event) {

//      prevent from opening the link following to the JSON
		event.preventDefault();

//      saves prevented link in this variable
		var href = $(this).attr("href");

//      sets confirmation delete button in delete modal to the saved link
		$('#confirmDeleteButton').attr('href', href);
		$('#deleteModal').modal('show');
	});

    $('#cancelButton').on('click', function() {
        $('#modal').modal('hide');
	});
});

function resetModal() {

//  loop through all the inputs and selects inside the form with id="form"
    $('#form input, #form select').each(function () {

//      get the <input> element inside this variable
        var input = $(this);

//      check if the field is from Auditable class, resets its value and don't remove readonly
//      return == continue        
        if (input.attr('id') == 'createdBy' || input.attr('id') == 'createdDate' ||
            input.attr('id') == 'lastModifiedBy' || input.attr('id') == 'lastModifiedDate') {
                input.val('');
                return;
            }

//      check if variable is <select>, reset it to the first option (SELECT) and enable it
//      else it is <input>, reset its value and remove 'readonly'
        if (input.is("select")) {
            input.val('0').prop('disabled', false);
        }
        else {
            input.val('').prop('readonly', false);
        }
    });

//  reset id and submitButton
    $('#id').prop('disabled', false);
    $('#submitButton').prop('disabled', false);
    $('#submitButton').prop('hidden', false);
    $('#submitButton').text("");
}

function setInputFields(href) {

//  get the JSON object from the href, into response
    $.get(href, function (response) {

//      loop through all the inputs and selects inside the form with id="form"
        $('#form input, #form select').each(function () {

//          asset the <input> element and its id
            var input = $(this);
            var id = input.attr('id');

//          check if element is <select>, get the element from JSON object based on its id and change it on the specific value
//          else it's <input> and we get the element's value from JSON based on its id
            if (input.is("select")) {
                input.val(response[id]).change();
            }
            else {
//              check if input is date, gets the first 10 chars of the string
                if(id.includes('Date') || id.includes('date')) {
                    input.val(response[id].substr(0, 10));
                }
                else {
                    input.val(response[id]);
                }
            }
        });
    });
}