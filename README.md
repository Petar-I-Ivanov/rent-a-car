# Features included in the MVC project:
  - Bootstrap 'Nice Admin' template
  - Models with CRUD controlls
  - JPA, Lombok
  - H2 database connection
  - Thymeleaf
  - Auditable Aware
  - SuperClass for models with common fields
  - OneToMany and ManyToMany relationships ([click for diagram](https://github.com/Petar-I-Ivanov/rent-a-car/blob/main/diagrams/ERDiagram.jpg))
  - Security
    1. Login, Registration, Logout
    2. Roles Verification (so far only SuperAdmin can manage roles)
    3. Password encoding
  - Header based on User (username, photo link)
  - Data Validation

# TODO:
  - Data Validation
	  -Validate @MappedSuperClass so childs get validated too
  - Verification
	  1. Users only can read ?
	  2. Clients can only Hire Vehicles, Invoice and above ^
	  3. Employees can Maintance, Movement Vehicles and above ^
	  4. Managers can manage Types, Statuses, Locations, Suppliers, Employees and above ^
	  5. Admin can do everything exept Security
	  6. SuperAdmin can do everything
  - Buisness logic
	  1. Count profit from Hire
	  2. Count loss from Maintenance (Movement ?)
	  3. Make statistics
  - Chats / Notifications
  - Everyone can see and edit its own Profile
  - JUnit, Selenium, REST Assured tests
  - UseCase, Class, Sequence, Activity, State-Chart and so on.. diagrams
  - Documentation
