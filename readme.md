- Was it easy to complete the task using AI?  
  Yes, It was easy.

- How long did task take you to complete?
  (Please be honest, we need it to gather anonymized statistics)
  It takes about two hours (not considering that I had to wait for the free GPT-4o version. Including this, it take about 8 hours)

- Was the code ready to run after generation? What did you have to change to make it usable?
  No, code wasn`t ready after generation. I had to change name of folder, add relevant dependance version, add user credential to hibernate.cfg.xml ect.

- Which challenges did you face during completion of the task?
  I had no challanges during completion of the task. It was easy because i used GPT.

- Which specific prompts you learned as a good practice to complete the task?
  My first prompt.  I got a complete answer. After that, I needed to write some specific clues to finish the assignment.
  """
  Create a RESTful API to manage a simple todo list application using Spring Boot, Hibernate, and MySQL.
  The application should allow users to create, read, update, and delete todo items.
  Each item should have a title and a description.
  Use Hibernate to persist the items in the database.
  Entity User (id, name) and Todo (id, name, description, user_id).
  Also write dependancys  (pom.xml maven). """

* Project Description:
This is a simple RESTful API for managing a Todo List application.
It is built with Spring Boot, Hibernate, and MySQL, and allows users to create, read, update, and delete both Users and Todos.

* Features:
- User Management: Create and list users.
- Todo Management: Create, update, delete, and list todos associated with users.
- Data Persistence: Uses Hibernate to persist data in a MySQL database.
- REST Endpoints: Offers endpoints for managing users and todos.

* Instructions to Run:
1. Clone the repository to your local machine.
2. Ensure you have Java (17 or above), Maven, and MySQL installed.
3. Update the `application.properties` file in `src/main/resources` with your MySQL database credentials.

*    Example:
spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>

* 4. Build the project using Maven:
```
*    mvn clean install
    ```
* 5. Run the application:
```
*    mvn spring-boot:run
```
* 6. Use a tool like Postman or cURL to interact with the API at `http://localhost:8080`.
    - User Endpoints:
      - `GET /api/users` - Retrieve all users.
      - `POST /api/users` - Create a new user.
    - Todo Endpoints:
      - `GET /api/todos` - Retrieve all todos.
      - `POST /api/todos` - Create a new todo.
      - `PUT /api/todos/{id}` - Update a todo by ID.
      - `DELETE /api/todos/{id}` - Delete a todo by ID.
