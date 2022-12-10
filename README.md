Features included in the MVC project:
  - Bootstrap 'Nice Admin' template
  - Models with CRUD controlls
  - JPA, Lombok
  - H2 database connection
  - Thymeleaf
    1. objects passing through Model
    2. listing each record with th:each
    3. common header, footer, etc. with th:fragment
  - Auditable Aware
  - SuperClass for models with common fields
  - OneToMany and ManyToMany relationships ([click for diagram](https://github.com/Petar-I-Ivanov/rent-a-car/blob/main/diagrams/ERDiagram.jpg))
  - Security
    1. Login, Registration, Logout
    2. Roles Verification (so far only SuperAdmin can manage roles)
    3. Password encoding
  - Header based on User (username, photo link)

TODO:
  - Data Validation
	  1. Validate with annotations in the models
	  2. Validate at the Views
	  3. If Country is selected (at ddl), the state have to be from that country
  - Verification
	  1. Users only can read ?
	  2. Clients can only Hire Vehicles, Invoice and above ^
	  3. Employees can Maintance, Movement Vehicles and above ^
	  4. Managers can manage Types, Statuses, Locations, Suppliers, Employees and above ^
	  5 Admin can do everything exept Security
	  6 SuperAdmin can do everything
  - Buisness logic
	  1. Count profit from Hire
	  2. Count loss from Maintenance (Movement ?)
	  3. Make statistics
  - Chats / Notifications
  - Everyone can see and edit its own Profile
  - JUnit, Selenium, REST Assured tests
  - UseCase, Class, Sequence, Activity, State-Chart and so on.. diagrams
  - Documentation
