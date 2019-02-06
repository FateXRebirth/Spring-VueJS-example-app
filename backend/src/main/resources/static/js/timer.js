document.addEventListener("DOMContentLoaded", function(event) {
    var seconds = 3;
    setInterval(function() {
        document.getElementById("seconds").innerHTML = seconds
        seconds = seconds - 1;
    }, 1000)
    setTimeout(function() {
        window.location.replace("/")
    }, 3000 );
});