$('document').ready(function () {

    // setting new methods for the validator

    // checks if field value's length is between 2 numbers (using between parameter as borders where [0] is lower and [1] is upper borders)
    jQuery.validator.addMethod('lengthRange', function (value, element, between) {
        return this.optional(element) || (value.length >= between[0] && value.length <= between[1]);
    }, 'Field length should be between {0} and {1}.');

    // checks if field's value is capitalized every word (Like That) and if it's used only letters and space
    jQuery.validator.addMethod('pascalCaseWithSpace', function (value, element) {
        return this.optional(element) || /^[A-Z][a-z]+(?: [A-Z][a-z]+)*$/gm.test(value);
    }, 'Field should contains only letters with capitalized first and after space.');

    // checks if field's value is 4 digits
    jQuery.validator.addMethod('fourDigitsOnly', function (value, element) {
        return this.optional(element) || /^[0-9]{4}$/i.test(value);
    }, 'Field should be 4 digits.');

    // checks if field's value is inside given array (given in 'list' parameter)
    jQuery.validator.addMethod('partOf', function (value, element, list) {

        // if first list index doesn't have 'id' property neither the rest have so list must be with enum values ['Like', 'That']
        // else the list is from objects with 'id' property and compare it by 'id' property and value
        if (list[0]['id'] == undefined) {
            return this.optional(element) || list.includes(value);
        }

        else {

            for (var i = 0; i < list.length; i++) {
                if (list[i]['id'] == value) {
                    return true;
                }
            }

            return false;
        }
    }, 'Pick from the shown options.');

    // there might be error in this function
    // before dropping the database id=2 (list[1]) returned id only

    //  checks if field's value is unique based on
    //  'objects' (declared in the <head> in each model's page, represends actual list of that model)
    //  @param except is given field's text when calling this rule
    //  used when updating to exclude model's property value
    jQuery.validator.addMethod('unique', function (value, element, except) {

        // gets the element's value of 'name' attribute as string
        var field = element['name'];

        for (var i = 0; i < objects.length; i++) {

            if (objects[i][field] == value && value != except) {
                return false;
            }
        }

        return true;
    }, 'Field must be unique.');

    $('#addButton, table #editButton, table #detailsButton').on('click', function () {

        clearValidation();

//      sets timeout so the form can be filled with values of that object
//      it's done in form.js file
        setTimeout(function () {
            setUnique(['description', 'capital', 'code', 'nationality']);
        }, 250);
    });

});

function setUnique(fieldNames) {

//  removes the old unique rule with the old @param except value 
    $('#form input, #form select').each(function () {
        $(this).rules('remove', 'unique');
    });

//  sets new unique rule with new @param except value
    for (var i = 0; i < fieldNames.length; i++) {
        $('#form #' + fieldNames[i]).rules('add', {unique : $('#form #' + fieldNames[i]).val() });
    }
}

function clearValidation() {

    var v = $('#form').validate();
    
    $('[name]', '#form').each( function() {
        v.successList.push(this);
        v.showErrors();
    });

    v.resetForm();
    v.reset();

    $('.error').removeClass('error');
}