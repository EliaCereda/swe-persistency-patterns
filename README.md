# SWE Persistency Patterns
The goal of this assignment is to design the architecture and provide a simple implementation for a web application that
supports the following functionality:
* Create, Read, Update and Delete (CRUD) operations for an entity that represents a user.
* A login operation.
* Search operations with three different search criteria: 
  - by name
  - by address
  - by name of the best friend
  
## Architecture
The first step in defining the architecture of the application has been to design its database schema. I tried to keep 
the complexity of the schema as low as possible, while providing all the necessary to demonstrate the required 
operations. The result can be seen in the figure below:
![ER diagram of the database schema](docs/images/database.png)

The technologies I adopted for this application are Java EE with Servlet and Java Server Pages. In addition, I used the 
JSTL library to implement the template views and the SQLite JDBC driver to connect to the database. I chose to use 
IntelliJ as my Integrated Development Environment. 

The application follows the Model View Controller (MVC) architecture, which divides the code in three major components.

### Model 
The Model component is responsible for storing the application data in domain objects and persisting them to the 
database. It is composed of three parts:
* the `Database` class, which initialises the database and manages the database connections.
* the `User` and `Address` classes, which correspond to the Gateway instances. They are Plain Old Java Objects (POJOs), 
without any knowledge of the database and don't implement any business logic.

![Class diagram of the Model component](docs/images/model.png)

### Controller

![Class diagram of the Controller component](docs/images/controller.png)