$('document').ready(function() {

    var password = document.getElementById("yourPassword");
    var confirmPassword = document.getElementById("confirmPassword");

    function validatePassword() {

        if (password.value != confirmPassword.value) {
            confirmPassword.setCustomValidity("Passwords don't match!");
        
        } else {
            confirmPassword.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirmPassword.onkeyup = validatePassword;
});