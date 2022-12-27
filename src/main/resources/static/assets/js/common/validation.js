$('document').ready(function () {

    // clear validation messages and 'error' class
    $.fn.clearValidation = function() {
    
        var v = $(this).validate();
        
        $('[name]', this).each( function() {
            v.successList.push(this);
            v.showErrors();
        });
    
        v.resetForm();
        v.reset();
    
        $('.error').removeClass('error');
    };
   
//  clear validation on Button Click
    $('table #editButton, table #detailsButton, #addButton').on('click', function() {
        $('#form').clearValidation();
    });
});

function setEmptyUnique(fieldNames) {

    $('#form input, #form select').each(function() {
        $(this).rules('remove', 'unique');
    });

    for (var i = 0; i < fieldNames.length; i++) {
        $('#form #' + fieldNames[i]).rules('add', 'unique');
    }
}

function setUniqueWithExeptions(fieldNames) {

    $('#form input, #form select').each(function() {
        $(this).rules('remove', 'unique');
    });

    setTimeout(function() {
        for (var i = 0; i < fieldNames.length; i++) {
            $('#form #' + fieldNames[i]).rules('add', {unique : [$('#form #' + fieldNames[i]).val()] });
        }
    }, 500);
}