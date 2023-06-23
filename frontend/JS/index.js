
var user = JSON.parse(localStorage.getItem("user"))
var userId = user.userId;
let token = user.uuid;





// get tasks of user
var url = "http://localhost:8080/api/search/user/" + userId;

fetch(url)
.then(function(response) {
    if (response.ok) {
    return response.json();
    } else {
    throw new Error("Failed to fetch task list");
    }
})
.then(function(data) {
    display(data)
})
.catch(function(error) {
    console.error("Error:", error);
});





// logout from database
async function logout(){
    var url = "http://localhost:8080/api/users/logout/" + token;

    let response=await fetch(url,{
        method:"POST",
        headers:{
            'Content-Type':'application/json'
        }
    })
    
    localStorage.removeItem("user")
    window.location.href="login.html"
}





// display tasks of user
function display(data){

    var taskListBody = document.getElementById("taskListBody");

    taskListBody.innerHTML = '';
    console.log(data)
    data.forEach(function(task) {
        
        var row = document.createElement("tr");

        var  col1 = document.createElement("td")
        var  col2 = document.createElement("td")
        var  col3 = document.createElement("td")
        var  col4 = document.createElement("td")
        var  col5 = document.createElement("td")
        var  col6 = document.createElement("td")
        var  col7 = document.createElement("td")
        var  col8 = document.createElement("td")
        var  col9 = document.createElement("td")
        var  col10 = document.createElement("td")
        
        col1.innerHTML = task.id

        col2.innerHTML = task.title

        col3.innerHTML = task.description

        col4.innerHTML = task.dueDate

        var  p = document.createElement("span")
        p.innerHTML = task.completed ? "Yes  " : "No  "
        var  markBtn = document.createElement("button")
        markBtn.setAttribute("class","btn btn-info btn-sm ml-2 w-75")
        markBtn.innerHTML = task.completed ? "Mark Pending" : "Mark Completed"
        col5.append(p,markBtn)

        col6.innerHTML = task.userId

        col7.innerHTML = task.name

        var  btn = document.createElement("button")
        btn.innerHTML = `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3" viewBox="0 0 16 16"><path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"/></svg> Delete`
        btn.setAttribute("class","btn btn-danger")
        col8.append(btn)

        var  inp = document.createElement("input")
        inp.setAttribute("placeholder", "User Id")
        inp.setAttribute("id", "userId")
        var  assign = document.createElement("button")
        assign.innerHTML = "Assign"
        assign.setAttribute("class","btn btn-success btn-sm ml-1")
        col9.append(inp,assign)

        var  editBtn = document.createElement("button")
        editBtn.setAttribute("class","border-0 bg-transparent")
        editBtn.innerHTML=`<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16"><path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/></svg>`
        col10.append(editBtn)

        row.append(col1,col2,col3,col4,col5,col6,col7,col8,col9,col10)

        btn.addEventListener("click", function(){
            deleteTask(task)
        })
        markBtn.addEventListener("click", function(){
            markTask(task)
        })
        assign.addEventListener("click", function(){
            assignTask(task)
        })
        editBtn.addEventListener("click", function(){
            editTask(task)
        })

        taskListBody.appendChild(row);

    });


}





// to delete task
async function deleteTask(task){

    let taskId=task.id;

    let api_link=`http://localhost:8080/api/tasks/${token}/${taskId}`
    let response=await fetch(api_link,{
        method:"DELETE",
        headers:{
            'Content-Type':'application/json'
        }
    })
    let data=await response.json()

    if(data.message!=null){
        alert(data.message);
    }
    else{
        location.reload();
    }
}





// to mark task as complete or pending
async function markTask(task){

    let taskId=task.id;
    console.log(token)
    let api_link=`http://localhost:8080/api/tasks/complete/${token}/${taskId}`
    let response=await fetch(api_link,{
        method:"PUT",
        headers:{
            'Content-Type':'application/json'
        }
    })
    let data=await response.json()

    if(data.message!=null){
        alert(data.message);
    }
    else{
        location.reload();
    }
}





// to assign task to another user
async function assignTask(task){

    let taskId=task.id;
    let userId=document.getElementById("userId").value;

    if(userId==''){
        alert("please enter user Id")
        return
    }

    let api_link=`http://localhost:8080/api/tasks/${token}/${taskId}/${userId}`
    let response=await fetch(api_link,{
        method:"PUT",
        headers:{
            'Content-Type':'application/json'
        }
    })
    let data=await response.json()

    if(data.message!=null){
        alert(data.message);
    }
    else{
        location.reload();
    }
}





// to filter tasks according to completion status
var completionStatusSelect = document.getElementById('completionStatus');
var taskListBody = document.getElementById('taskListBody');

completionStatusSelect.addEventListener('change', () => {
    var completedStatus = completionStatusSelect.value;
    var link = `http://localhost:8080/api/filter/completionstatus/${token}/${completedStatus}`
    if(completedStatus=="all"){
        link = `http://localhost:8080/api/search/user/${userId}`
    }
    fetch(link)
    .then(response => response.json())
    .then(tasks => {
        if(tasks.message!=null){
            alert(tasks.message);
        }
        display(tasks)
    })
    .catch(error => {
        console.log('Error:', error);
    });

});





// to filter tasks according to due date
var dueDateInput = document.getElementById('dueDate');
var taskListBody = document.getElementById('taskListBody');

dueDateInput.addEventListener('change', () => {
var dueDate = dueDateInput.value;

fetch(`http://localhost:8080/api/filter/duedate/${token}/${dueDate}`)
    .then(response => response.json())
    .then(tasks => {
        if(tasks.message!=null){
            alert(tasks.message);
        }
        display(tasks)
    })
    .catch(error => {
        console.log('Error:', error);
    });
});





// to search tasks by title
var searchTitleInput = document.getElementById('searchTitle');
var searchButton = document.getElementById('searchButton');

searchButton.addEventListener('click', () => {
var title = searchTitleInput.value;

fetch(`http://localhost:8080/api/search/title/${token}/${title}`)
    .then(response => response.json())
    .then(tasks => {
        display(tasks)
    })
    .catch(error => {
        console.log('Error:', error);
    });
});





// to search tasks by Description
var searchDescriptionInput = document.getElementById('searchDescription');
var searchButton1 = document.getElementById('searchButton1');

searchButton1.addEventListener('click', () => {
var description = searchDescription.value;

fetch(`http://localhost:8080/api/search/desc/${token}/${description}`)
    .then(response => response.json())
    .then(tasks => {
        display(tasks)
    })
    .catch(error => {
        console.log('Error:', error);
    });
});





// to edit task
function editTask(task){
    console.log(task)
    let div=document.getElementById("hidden");
    div.style.visibility="visible"
    div.innerHTML="";
    let p1=document.createElement("input");
    let p2=document.createElement("input");
    let p3=document.createElement("input");
    let btn=document.createElement("button");
    let btn1=document.createElement("button");
    
    btn.innerHTML="Update"
    btn1.innerHTML="Cancel"

    div.setAttribute("class","text-center pb-4")
    p1.setAttribute("id","p1")
    p2.setAttribute("id","p2")
    p3.setAttribute("id","p3")
    p3.setAttribute("type","date")
    p1.setAttribute("class","form-control mt-4 w-75 mx-auto")
    p2.setAttribute("class","form-control mt-4 w-75 mx-auto")
    p3.setAttribute("class","form-control mt-4 w-75 mx-auto")
    btn.setAttribute("class","btn btn-primary mt-4")
    btn1.setAttribute("class","btn btn-danger mt-4 ml-3")

    p1.value=task.title;
    p2.value=task.description;
    p3.value=task.dueDate

    div.append(p1,p2,p3,btn,btn1)

    btn1.addEventListener("click", function(){
        div.style.visibility="hidden"
    })

    btn.addEventListener("click", function(){
        editFun(task)
    })

    function editFun(task){
        var task = {
            id:task.id,
            title: document.getElementById("p1").value,
            description: document.getElementById("p2").value,
            dueDate: document.getElementById("p3").value,
        };
    
        var url = "http://localhost:8080/api/tasks/" + token;
    
        fetch(url, {
            method: "PUT",
            headers: {
            "Content-Type": "application/json"
            },
            body: JSON.stringify(task)
        })
        .then(function(response) {
            if (response.ok) {
                alert("Task updated successfully");
                window.location.href = "index.html";
            } else {
                alert("Failed to update task");
            }
        })
        .catch(function(error) {
            console.error("Error:", error);
        });
    }

}