document.getElementById("registrationForm").addEventListener("submit", function(event) {
    event.preventDefault(); 

    var name = document.getElementById("name").value;
    var mobile = document.getElementById("mobile").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var user = {
        name: name,
        mobile: mobile,
        username: username,
        password: password
    };
    console.log(user)

    fetch("http://localhost:8080/api/users", {
        method: "POST",
        headers: {
        "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    })
    .then(function(response) {
        if (response.ok) {
        alert("Registration successful");
        window.location.href = "login.html";
        } else {
        alert("Registration failed");
        }
    })
    .catch(function(error) {
        alert("An error occurred during registration");
        console.error(error);
    });
});