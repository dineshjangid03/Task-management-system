document.getElementById("loginForm").addEventListener("submit", function(event){
    fun(event)
})

async function fun(event) {
    event.preventDefault();

    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var login = {
        username: username,
        password: password
    };

    let res = await fetch("http://localhost:8080/api/users/login", {
        method: "POST",
        headers: {
        "Content-Type": "application/json"
        },
        body: JSON.stringify(login)
    })
    .then(async function(response) {
        return response.json() 
    })
    .catch(function(error) {
        alert("An error occurred during login");
        console.error(error);
    });
    if(res.message!=null){
        alert(res.message)
    }
    else{
        localStorage.setItem("user", JSON.stringify(res))
        window.location.href="index.html"
    }
    
}