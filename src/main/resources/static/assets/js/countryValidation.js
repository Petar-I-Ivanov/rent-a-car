$('document').ready(function() {

    jQuery.validator.addMethod("range105", function(value, element) {
        return this.optional(element) || (value.length >= 4 && value.length <= 105);
    }, "Field size must be between 4 and 105.");

    jQuery.validator.addMethod("range60", function(value, element) {
        return this.optional(element) || (value.length >= 4 && value.length <= 60);
    }, "Field size must be between 4 and 60.");

    jQuery.validator.addMethod("range15", function(value, element) {
        return this.optional(element) || (value.length >= 4 && value.length <= 15);
    }, "Field size must be between 4 and 15.");

    jQuery.validator.addMethod("pascalCaseWithSpace", function(value, element) {
        return this.optional(element) || /^[A-Z][a-z]+(?: [A-Z][a-z]+)*$/gm.test(value);
    }, "Field should contains only letters with capitalized first and after space.");

    jQuery.validator.addMethod("snakeCaseWithCapitalized", function(value, element) {
        return this.optional(element) || /^[A-Z]+(?:_[A-Z]+)*$/gm.test(value);
    }, "Field should contains only capitalized letters with underscore between.");

    jQuery.validator.addMethod("fourDigitsOnly", function(value, element) {
        return this.optional(element) || /^[0-9]{4}$/i.test(value);
    }, "Field must be 4 digits.");

    $('#form').validate({
        rules: {
            description: { pascalCaseWithSpace: true, range60: true },
            capital: { pascalCaseWithSpace: true, range105: true },
            code: { fourDigitsOnly: true },
            continent: { snakeCaseWithCapitalized: true, range15: true },
            nationality: { pascalCaseWithSpace: true, range60: true }
        }
    });
});