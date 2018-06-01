// navbar-burger
document.addEventListener('DOMContentLoaded', function () {

  // Get all "navbar-burger" elements
  var $navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0);

  // Check if there are any navbar burgers
  if ($navbarBurgers.length > 0) {

    // Add a click event on each of them
    $navbarBurgers.forEach(function ($el) {
      $el.addEventListener('click', function () {

        // Get the target from the "data-target" attribute
        var target = $el.dataset.target;
        var $target = document.getElementById(target);

        // Toggle the class on both the "navbar-burger" and the "navbar-menu"
        $el.classList.toggle('is-active');
        $target.classList.toggle('is-active');

      });
    });
  }

});

// Regular Expression
var Pattern = {
  email: /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/,
}

var message = function(message) {
  var element = document.createElement("p");
  element.className = "help is-danger ";
  element.innerText = message;
  return element;
}

var validateLogin = function() {

    return false;
}

var validateRegister = function() {

    return false;
}

var check = function(event, type) {

    var element = event.target;
    var error = $(element).parent().next();
    error.empty();

    switch(type) {

        case "username":
            if(element.value.length == 0 ) {
                error.append(message("Username Required"));
            } else if (element.value.length < 4 || element.value.length > 20 ) {
                error.append(message("Length of username should between 4 and 20"));
            } else {
                error.empty()
            }
        break;

        case "password":
            if(element.value.length == 0 ) {
                error.append(message("Password Required"));
            } else if (element.value.length < 4 || element.value.length > 20 ) {
                error.append(message("Length of password should between 4 and 20"));
            } else {
                error.empty()
            }
        break;

        case "email":
            if(element.value.length == 0 ) {
              error.append(message("Email Required"));
            } else if (!Pattern.email.test(element.value)) {
              error.append(message("Email Invalid"));
            } else {
                error.empty()
            }
        break;

        case "confirmation":
            if(element.value.length == 0 ) {
                error.append(message("Confirmation Required"));
            } else if (element.value.length < 4 || element.value.length > 20 ) {
                error.append(message("Length of confirmation should between 4 and 20"));
            } else {
                error.empty()
            }
        ;
        break;
    }

}