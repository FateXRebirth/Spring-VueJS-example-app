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

// Create custom message
var message = function(message) {
    var element = document.createElement("p");
    element.className = "help is-danger ";
    element.innerText = message;
    return element;
}

// Check input dynamically
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

$('#loginForm').submit(function(event) {

    var exist = false;

    $('.message-body').empty();

    var username = $('input[name=username]');
    var password = $('input[name=password]');

    if($('.help.is-danger').length > 0) return false;

    if(username.val() == "" || password.val() == "") {
        if(username.val() == "") username.parent().next().append(message("Username Required"));
        if(password.val() == "") password.parent().next().append(message("Password Required"));
        return false;
    } else {
        $.ajax("http://localhost:8081/api/get/person/all", { async: false })
        .done(function(data) {
            data.forEach( function(person) {
                if(person.username == username.val()) {
                    exist = true;
                    if(person.password == password.val()) {
                        $('#success-message .message-body').append(message("Successfully!"));
                        $('#success-message').fadeIn(1000);
                        setTimeout(function() {
                           $('#success-message').fadeOut(1000);
                        }, 3000)
                        return false;
                    } else {
                        $('#failure-message .message-body').append(message("Password is not correct"));
                    }
                }
            })
            if(!exist) {
                $('#failure-message .message-body').append(message("Username does not exist"));
            }
        })
    }

    if($('#failure-message .message-body').children().length > 0) {
        $('#failure-message').fadeIn(1000);
        setTimeout(function() {
            $('#failure-message').fadeOut(1000);
        }, 3000)
        return false;
    }
})

// Validate login form
var validateLogin = function() {

    $.ajax({
        async: false,
        method: "POST",
        contentType : "application/json",
        url: "http://localhost:8081/login2",
        data: JSON.stringify({ username: "admin", password: "admin" })
    }).done(function( msg ) {
        alert( "Data Saved: " + msg );
    });

    var exist = false;

    $('.message-body').empty();

    var username = $('input[name=username]');
    var password = $('input[name=password]');

    if($('.help.is-danger').length > 0) return false;

    if(username.val() == "" || password.val() == "") {
        if(username.val() == "") username.parent().next().append(message("Username Required"));
        if(password.val() == "") password.parent().next().append(message("Password Required"));
        return false;
    } else {
        $.ajax("http://localhost:8081/api/get/person/all", { async: false })
        .done(function(data) {
            data.forEach( function(person) {
                if(person.username == username.val()) {
                    exist = true;
                    if(person.password == password.val()) {
                        $('#success-message .message-body').append(message("Successfully!"));
                        $('#success-message').fadeIn(1000);
                        setTimeout(function() {
                           $('#success-message').fadeOut(1000);
                        }, 3000)
                        return false;
                    } else {
                        $('#failure-message .message-body').append(message("Password is not correct"));
                    }
                }
            })
            if(!exist) {
                $('#failure-message .message-body').append(message("Username does not exist"));
            }
        })
        $.ajax({
            async: false,
            method: "POST",
            contentType : "application/json",
            url: "http://localhost:8081/login2",
            data: JSON.stringify({ username: "admin", password: "admin" })
        }).done(function( msg ) {
            alert( "Data Saved: " + msg );
        });
    }

    if($('#failure-message .message-body').children().length > 0) {
        $('#failure-message').fadeIn(1000);
        setTimeout(function() {
            $('#failure-message').fadeOut(1000);
        }, 3000)
        return false;
    }
}

// Validate register form
var validateRegister = function() {

    fetch('http://localhost:8081/api/get/person/all').then( (response) => response.json()).then(function(data) {
        console.log(data);
    })

    const username = document.forms["registerForm"]["username"];
    const email = document.forms["registerForm"]["email"];
    const password = document.forms["registerForm"]["password"];
    const confirmation = document.forms["registerForm"]["confirmation"];

    return false;
}