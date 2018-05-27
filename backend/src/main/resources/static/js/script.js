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
  element.className = "help is-danger";
  element.innerText = message;
  return element;
}

var validateLoginForm = function() {

    const username = document.forms["loginForm"]["username"];
    if(username.value == '') {
      username.parentElement.parentElement.appendChild(message("Username required"));
    }
    if(username.value.length < 4 || username.value.length > 20) {
      username.parentElement.parentElement.appendChild(message("Length of username should between 4 and 20"));
    }

    const password = document.forms["loginForm"]["password"];
    if(password.value == '') {
      password.parentElement.parentElement.appendChild(message("Password required"));
    }
    if(password.value.length < 4 || password.value.length > 20) {
      password.parentElement.parentElement.appendChild(message("Length of password should between 4 and 20"));
    }
    return false;
}

var validateRegisterForm = function() {

    const username = document.forms["registerForm"]["username"];
    if(username.value == '') {
      username.parentElement.parentElement.appendChild(message("Username required"));
    }
    if(username.value.length < 4 || username.value.length > 20) {
      username.parentElement.parentElement.appendChild(message("Length of username should between 4 and 20"));
    }

    const email = document.forms["registerForm"]["email"];
    if(email.value == '') {
      email.parentElement.parentElement.appendChild(message("Email required"));
    }
    if(!Pattern.email.test(email.value)) {
      email.parentElement.parentElement.appendChild(message("Email invalid"));
    }

    const password = document.forms["registerForm"]["password"];
    if(password.value == '') {
      password.parentElement.parentElement.appendChild(message("Username required"));
    }
    if(password.value.length < 4 || password.value.length > 20) {
      password.parentElement.parentElement.appendChild(message("Length of password should between 4 and 20"));
    }

    const confirmation = document.forms["registerForm"]["confirmation"];   
    if(confirmation.value == '') {
      confirmation.parentElement.parentElement.appendChild(message("Username required"));
    }
    if(confirmation.value.length < 4 || confirmation.value.length > 20) {
      confirmation.parentElement.parentElement.appendChild(message("Length of confirmation should between 4 and 20"));
    } 
    if(confirmation.value != password.value) {
      confirmation.parentElement.parentElement.appendChild(message("Password should same"));
    }
    return false;
}