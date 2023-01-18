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

    // checks if field's value is phone number
    jQuery.validator.addMethod('phoneNumber', function (value, element) {
        return this.optional(element) || /^[0-9]{10,20}$/gm.test(value);
    }, 'Field should be phone number.');

    // checks if field's value is phone number
    jQuery.validator.addMethod('samePassword', function (value, element, newPassword) {
        return value == $(newPassword).val();
    }, 'Confirm password doesn\'t match with new password.');

    // checks if field's value is upper case
    jQuery.validator.addMethod('upperCase', function (value, element) {
        return this.optional(element) || /^[A-Z]+$/gm.test(value);
    }, 'Field should contains only upper case letters.');

    // checks if field's value is upper case and digits
    jQuery.validator.addMethod('upperCaseAndDigits', function (value, element) {
        return this.optional(element) || /^[A-Z0-9]+$/gm.test(value);
    }, 'Field should contains only upper case letters and digits.');
    
    // checks if field's value is 4 digits
    jQuery.validator.addMethod('fourDigitsOnly', function (value, element) {
        return this.optional(element) || /^[0-9]{4}$/i.test(value);
    }, 'Field should be 4 digits.');

    // checks if field's value is double number
    jQuery.validator.addMethod('doubleNumber', function (value, element) {
        return this.optional(element) || /^[0-9]+.[0-9]{1,2}$/i.test(value);
    }, 'Field should be double number.');

    // checks if field's value is time
    jQuery.validator.addMethod('time', function (value, element) {
        return this.optional(element) || /^[0-9]{2}:[0-9]{2}$/i.test(value);
    }, 'Field should be time (like 08:59).');

    //checks if field's date value is greater than params
    jQuery.validator.addMethod("greaterDate", function(value, element, params) {
            return new Date(value) > new Date($(params).val());
    }, 'Date must be greater.');

    //checks if different locations are picked
    jQuery.validator.addMethod("differentLocations", function(value, element, fromLocation) {
            return value != $(fromLocation).val();
    }, 'Locations must be different.');

    //checks if the picked date is today or in the future
    jQuery.validator.addMethod("presentOrFuture", function (value, element) {
        var now = new Date();
        var myDate = new Date(value);
        return this.optional(element) || myDate >= now;
    }, 'Date should be today or future.');

    //checks if the picked date is today or in the past
    jQuery.validator.addMethod("presentOrPast", function (value, element) {
        var now = new Date();
        var myDate = new Date(value);
        return this.optional(element) || myDate <= now;
    }, 'Date should be today or past.');

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