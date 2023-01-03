$('document').ready(function () {

    // setting new methods for the validator

    // NOTE: methods where're used [[${objects}]] some of the 'object' are returning ID only
    // all of the times 'United States' is the root Country

    // checks if field value's length is between 2 numbers (using between parameter as borders where [0] is lower and [1] is upper borders)
    jQuery.validator.addMethod('lengthRange', function (value, element, between) {
        return this.optional(element) || (value.length >= between[0] && value.length <= between[1]);
    }, 'Field length should be between {0} and {1}.');

    // checks if field's value is capitalized every word (Like That) and if it's used only letters and space
    jQuery.validator.addMethod('pascalCaseWithSpace', function (value, element) {
        return this.optional(element) || /^[A-Z][a-z]+(?: [A-Z][a-z]+)*$/gm.test(value);
    }, 'Field should contains only letters with capitalized first and after space.');

    // checks if field's value is only digits with 10-20 length
    jQuery.validator.addMethod('phoneNumber', function (value, element) {
        return this.optional(element) || /^[0-9]{10,20}$/gm.test(value);
    }, 'Field should contains only digits (10 to 20 length).');

    // checks if field's value is upper case
    jQuery.validator.addMethod('upperCase', function (value, element) {
        return this.optional(element) || /^[A-Z]+$/gm.test(value);
    }, 'Field should contains only upper case letters.');

    // checks if field's value is 4 digits
    jQuery.validator.addMethod('fourDigitsOnly', function (value, element) {
        return this.optional(element) || /^[0-9]{4}$/i.test(value);
    }, 'Field should be 4 digits.');

    // checks if field's value is inside given array (given in 'list' parameter)
    jQuery.validator.addMethod('partOf', function (value, element, list) {

        //  check it with both ways because sometimes 'United States' country is shown with id only
        //  also for when there's array with values ['Like', 'That']
        for (var i = 0; i < list.length; i++) {
            if (list[i] == value || list[i]['id'] == value) {
                return true;
            }
        }

        return false;
    }, 'Pick from the shown options.');

    // checks if the selected State's country is the selected Country
    // @param states are declared in the <head> of the model's pages where it is used
    jQuery.validator.addMethod('stateInCountry', function (value, element, pickedCountry) {

        for (var i = 0; i < states.length; i++) {

            if (states[i]['id'] == value) {

                return states[i]['countryId'] == $(pickedCountry).find(':selected').val();
            }
        }

    }, 'State and country doesn\'t match.');

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
    });

});

function setUnique(fieldNames) {

    $('#addButton, table #editButton, table #detailsButton').on('click', function () {

        //      sets timeout so the form can be filled with values of that object
        //      it's done in form.js file
        setTimeout(function () {
            //  sets new unique rule with new @param except value
            for (var i = 0; i < fieldNames.length; i++) {
                $('#form #' + fieldNames[i]).rules('add', { unique: $('#form #' + fieldNames[i]).val() });
            }
        }, 250);
    });
}

function clearValidation() {

    var v = $('#form').validate();

    $('[name]', '#form').each(function () {
        v.successList.push(this);
        v.showErrors();
    });

    v.resetForm();
    v.reset();

    $('.error').removeClass('error');
}