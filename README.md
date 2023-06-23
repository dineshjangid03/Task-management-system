# Task-Management-System

This is a Task Management System application. It provides the functionality to register users, create tasks, update task details, assign tasks to users, mark tasks as complete, delete tasks, and perform various search and filter operations on tasks.

# Features
  - User Management:

    - User registration: Users can register an account by providing their details.
    - User login: Users can log into their account using their credentials.
    - User logout: Users can log out from their account.

  - Task Management:

    - Create task: Users can create tasks by providing the task details.
    - Update task: Users can update the details of an existing task.
    - Assign task to another user: Users can assign a task to another user.
    - Mark task as complete: Users can mark a task as complete.
    - Delete task: Users can delete a task.

  - Search and Filter:

    - Search task by title: Users can search for tasks based on the task title.
    - Search task by description: Users can search for tasks based on the task description.
    - Search tasks of a user: Users can retrieve all the tasks associated with a specific user.
    - Filter tasks by completion status: Users can filter tasks based on their completion status.
    - Filter tasks by due date: Users can filter tasks based on their due date.
    - Filter tasks by completion status and due date: Users can filter tasks based on their completion status and due date.


# Technologies Used
  - Java
  - Spring Boot
  - Hibernate
  - Maven
  - MySQL
  - HTML
  - CSS
  - JavaScript
  - Bootstrap

# API Endpoints

  - User Endpoints:

    - POST /api/users: Register a new user.
    - POST /api/users/login: Log in to a user account.
    - POST /api/users/logout/{token}: Log out from a user account.

  - Task Endpoints:

    - POST /api/tasks/{token}: Create a new task.
    - PUT /api/tasks/{token}: Update an existing task.
    - PUT /api/tasks/{token}/{taskId}/{userId}: Assign a task to another user.
    - PUT /api/tasks/complete/{token}/{taskId}: Mark a task as complete.
    - DELETE /api/tasks/{token}/{taskId}: Delete a task.

  - Search and Filter Endpoints:

    - GET /api/search/title/{token}/{title}: Search for tasks by title.
    - GET /api/search/desc/{token}/{desc}: Search for tasks by description.
    - GET /api/search/user/{userId}: Retrieve tasks of a specific user.
    - GET /api/filter/completionstatus/{token}/{completedStatus}: Filter tasks by completion status.
    - GET /api/filter/duedate/{token}/{dueDate}: Filter tasks by due date.
    - GET /api/filter/{token}/{completedStatus}/{dueDate}: Filter tasks by completion status and due date.

# Usage Examples

  - User Endpoints:

    - Register a new user:
      - Request: POST /api/users
        ```html
        http://localhost:8080/api/users
        ```
      - Body:
        ```json
        {
            "name":"Dinesh",
            "mobile":"8696700323",
            "username":"dinesh1",
            "password":"1234"
        }
        ```
        
    -  Log in to a user account:
      

        - Request: POST /api/users/login
          ```html
          http://localhost:8080/api/users/login
          ```
        - Body:
          ```json
          {
              "username":"dinesh1",
              "password":"1234"
          }
          ```
    -  Log out from a user account:
        - Request: POST /api/users/logout/{token}
          ```html
          http://localhost:8080/users/logout/{token}
          ```
     
          
      
  - Task Endpoints:

    - Create a new task:
        - Request: POST /api/tasks/{token}
          ```html
          http://localhost:8080/api/tasks/{token}
          ```
        - Body:
          ```json
          {
              "title":"title",
              "description":"description",
              "dueDate":"2023-07-10",
              "completed":"false"
          }
          ```
    - Update an existing task:
        - Request: PUT /api/tasks/{token}
          ```html
          http://localhost:8080/api/tasks/{token}
          ```
        - Body:
          ```json
          {
              "title":"title",
              "description":"description",
              "dueDate":"2023-07-10",
          }
          ```
    - Assign a task to another user:
        - Request: PUT /api/tasks/{token}/{taskId}/{userId}
          ```html
          http://localhost:8080/api/tasks/${token}/${taskId}/${userId}
          ```
    - Mark a task as complete:
        - Request: PUT /api/tasks/complete/{token}/{taskId}
          ```html
          http://localhost:8080/api/tasks/complete/${token}/${taskId}
          ```
    - Delete a task:
        - Request: DELETE /api/tasks/{token}/{taskId}
          ```html
          http://localhost:8080/api/tasks/${token}/${taskId}
          ```

  - Search and Filter Endpoints:

    - Search for tasks by title:
      - Request: GET /api/search/title/{token}/{title}
          ```html
          http://localhost:8080/api/search/title/${token}/${title}
          ```
    - Search for tasks by description:
      - Request: GET /api/search/desc/{token}/{desc}
          ```html
          http://localhost:8080/api/search/desc/${token}/${description}
          ```
    - Filter tasks by completion status:
      - Request: GET /api/filter/completionstatus/{token}/{completedStatus}
          ```html
          http://localhost:8080/api/filter/completionstatus/${token}/${completedStatus}
          ```
    - Filter tasks by due date:
      - Request: GET /api/filter/duedate/{token}/{dueDate}
          ```html
          http://localhost:8080/api/filter/duedate/${token}/${dueDate}
          ```
