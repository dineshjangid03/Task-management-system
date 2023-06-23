document.getElementById("createTaskForm").addEventListener("submit", function(event) {
    event.preventDefault();

    var title = document.getElementById("title").value;
    var description = document.getElementById("description").value;
    var dueDate = document.getElementById("dueDate").value;
    var completed = document.getElementById("completed").checked;

    var task = {
        title: title,
        description: description,
        dueDate: dueDate,
        completed: completed
    };

    var user = JSON.parse(localStorage.getItem("user"))
    var token =user.uuid;
    var url = "http://localhost:8080/api/tasks/" + token;

    fetch(url, {
        method: "POST",
        headers: {
        "Content-Type": "application/json"
        },
        body: JSON.stringify(task)
    })
    .then(function(response) {
        if (response.ok) {
        alert("Task created successfully");
        window.location.href = "index.html";
        } else {
        alert("Failed to create task");
        }
    })
    .catch(function(error) {
        console.error("Error:", error);
    });
});