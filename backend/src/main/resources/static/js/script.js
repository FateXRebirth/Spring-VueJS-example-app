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

$('.delete').click(function(event) {
    $(this).closest('.message').fadeOut();
})

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

// Validate login form
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
        $.ajax("http://localhost:8081/api/person/GetAllPerson", { async: false })
        .done(function(data) {
            data.forEach( function(person) {
                if(person.username == username.val()) {
                    exist = true;
                    if(person.password == password.val()) {
                        $('#success-message .message-body').append(message("Successfully!"));
                        $('#success-message').fadeIn();
                        setTimeout(function() {
                           $('#success-message').fadeOut();
                        }, 1500)
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
        $('#failure-message').fadeIn();
        setTimeout(function() {
            $('#failure-message').fadeOut();
        }, 1500)
        return false;
    }
})

// Validate register form
$('#registerForm').submit(function(event) {

    event.preventDefault();

    var exist = false;

    $('.message-body').empty();

    var username = $('input[name=username]');
    var email = $('input[name=email]');
    var password = $('input[name=password]');
    var confirmation = $('input[name=confirmation]');

    if($('.help.is-danger').length > 0) return false;

    if(username.val() == "" || password.val() == "") {
        if(username.val() == "") username.parent().next().append(message("Username Required"));
        if(email.val() == "") email.parent().next().append(message("E-mail Required"));
        if(password.val() == "") password.parent().next().append(message("Password Required"));
        if(confirmation.val() == "") confirmation.parent().next().append(message("Confirmation Required"));
        return false;
    } else {
        $.ajax("http://localhost:8081/api/person/GetAllPersonEmail", { async: false })
        .done(function(data) {
            data.forEach( function(Email) {
                if(Email == email.val()) {
                    exist = true;
                    $('#failure-message .message-body').append(message("This email has been used"));
                }
            })
        })
        if(!exist) {
            if(password.val() == confirmation.val()) {
                var data = {
                    username: username.val(),
                    email: email.val(),
                    password: password.val(),
                    type: 'user'
                }
                $.ajax({
                    async: false,
                    type: "POST",
                    contentType: "application/json",
                    url: "http://localhost:8081/api/person/Create",
                    data: JSON.stringify(data),
                    dataType: 'json',
                    success: function (data) {
                        console.log("success")
                    },
                    error: function (e) {
                        console.log("failure")
                    }
                });
                $('#success-message .message-body').append(message("Successfully! You can login now"));
                $('#success-message').fadeIn();
                setTimeout(function() {
                   $('#success-message').fadeOut();
                   window.location.replace("/login")
                }, 1500)
                return false;
            } else {
                $('#failure-message .message-body').append(message("Password should be same"));
            }
        }
    }

    if($('#failure-message .message-body').children().length > 0) {
        $('#failure-message').fadeIn();
        setTimeout(function() {
            $('#failure-message').fadeOut();
        }, 1500)
        return false;
    }
})

function BrandCreate() {
    var form = $('form[name=brand]');
    const Name = form.find($('input[name=brandName]')).val();
    const chineseName = form.find($('input[name=brandChineseName]')).val()
    if(Name != "") {
        var data = {

        }
        $.ajax({
            async: false,
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8081/api/post/person",
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (data) {
                console.log("success")
            },
            error: function (e) {
                console.log("failure")
            }
        });
    }
//    $('#success-message-right .message-body').append(message("Successfully! You can login now"));
//    $('#success-message-right').fadeIn();
//    setTimeout(function() {
//       $('#success-message-right').fadeOut();
//       window.location.replace("/login")
//    }, 1500)
//    $('#failure-message-left .message-body').append(message("Password should be same"));
//    $('#failure-message-left').fadeIn();
//    setTimeout(function() {
//        $('#failure-message-left').fadeOut();
//    }, 1500)
}
function BrandReset() {
    $('form[name=brand]')[0].reset();
}