# Personal Management System (JavaFX + Maven)

A simple **JavaFX-based personnel (student) management system**
demonstrating clean modular architecture with multiple Maven modules.

## Project Structure

    lab1/
    ├── lanceur/                  # Launcher module (entry point)
    ├── personnel-entities/       # Entity classes (e.g., Student)
    ├── gestionnaire-du-personnel/ # Logic and persistence layer
    └── user-interface/           # JavaFX GUI module

##  Features

-   Manage students: add, list, and persist data.
-   Data is serialized into `students.ser`.
-   Simple GUI built with **JavaFX**.
-   Modular design using **Maven multi-module structure**.
-   Fully functional with Java 17+.

##  Technologies

-   **Java 17**
-   **JavaFX**
-   **Maven**
-   **JUnit 5** (for tests)

##  Run Instructions

### 1. Compile all modules

``` bash
mvn clean install
```

### 2. Run the UI module

``` bash
cd user-interface
mvn javafx:run
```

##  Maven Setup

Each module is managed in the root `pom.xml` with the following key
modules:

-   `personnel-entities` → defines core domain models (like `Student`).
-   `gestionnaire-du-personnel` → handles business logic and file
    persistence.
-   `user-interface` → JavaFX GUI for interacting with the system.

##  Example Class: Student

``` java
public class Student implements Serializable {
    private String name;
    private int id;
    private String major;
    private int year;
}
```

##  Data Persistence

Data is stored in `students.ser` using Java's built-in serialization:

``` java
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE));
oos.writeObject(students);
```

##  Development Notes

-   Ensure `Student` implements `Serializable`.
-   Run from `user-interface` to see the GUI.
-   Each new student is automatically persisted.

##  GUI Preview (Example)

A minimal UI with a list view and "Add Student" dialog:

-   Displays all students.
-   Allows adding new ones via form fields.
-   Automatically updates the list after saving.

##  Future Improvements

-   Edit & delete student functionality.
-   Validation and error handling.
-   Switch to JSON or database persistence.
-   Unit tests for all modules.

------------------------------------------------------------------------

**Author:** [Akram Fadel](https://akramfadel.netlify.app)
**Version:** 1.0\
**License:** Apache-2.0 
