# ToDoApp - Java Android MVVM To-Do List

A simple To-Do list Android application built using **Java** and the **MVVM architecture**. This project is designed to follow a complete software development lifecycle: analysis, design, implementation, and testing.

---

## Project Goals

- Build an Android app using the **MVVM architecture pattern**.
- Implement core to-do list features (CRUD + filtering).
- Store data locally using **Room (SQLite)**.
- Use **ViewModel** and **LiveData** for state management.
- Separate UI, logic, and data layers clearly.
- Practice Android development with a software engineering mindset.

---

## Development Phases

### 1️ Analysis Phase

#### Functional Requirements

- [FR1] Add a new task
- [FR2] Edit an existing task
- [FR3] Delete a task
- [FR4] Toggle task status (complete/incomplete)
- [FR5] View all tasks
- [FR6] Persist task data using Room database
- [FR7] Filter tasks by status (All / Completed / Incomplete)

#### Non-Functional Requirements

- Language: Java
- Android Minimum SDK: 29 (Android 10.0)
- Architecture: MVVM
- Data Binding: ViewBinding only (no DataBinding)
- Local Storage: Room ORM
- Must survive screen rotation without data loss

---

### 2️ Design Phase

#### Architecture - MVVM Structure

View (Activity/Fragment)
↓
ViewModel (LiveData)
↓
Repository
↓
Room (Entity / DAO / DB)

#### Module Responsibilities

- `Task.java` — Task entity (data model)
- `TaskDao.java` — Defines database operations
- `TaskDatabase.java` — Room database configuration
- `TaskRepository.java` — Abstracts data source logic
- `TaskViewModel.java` — Exposes LiveData to UI
- `MainFragment.java` — Displays the task list
- `AddEditFragment.java` — Add/edit task screen

#### UI Wireframe (Screens)

- **MainFragment**: Displays all tasks, filter menu, and add button
- **AddEditFragment**: Input field + Save button

---

### 3️ Testing Phase

#### Unit Tests

- TaskViewModel: Verify business logic
- TaskRepository: Mocked data source tests
- TaskDao: Room in-memory database tests

#### UI Tests

- Espresso: Add/Edit/Delete task flows
- Robolectric: Simulate view behavior

#### Edge Case Tests

- Empty input validation
- Handling deletion of nonexistent tasks

---

## Tech Stack

- **Language**: Java
- **Architecture**: MVVM
- **Database**: Room (SQLite)
- **State Management**: ViewModel + LiveData
- **UI Binding**: ViewBinding
- **UI Layouts**: RecyclerView + ConstraintLayout
- **Testing**: JUnit, Espresso, Robolectric
- **Version Control**: Git + GitHub

---

## Project Structure (Simplified)

app/
├── model/
│ ├── Task.java
| ├── TaskFilter.java
│ ├── TaskDao.java
│ └── TaskDatabase.java
├── repository/
│ └── TaskRepository.java
├── viewmodel/
│ └── TaskViewModel.java
├── ui/
│ ├── MainFragment.java
│ └── AddEditFragment.java
└── MainActivity.java

---

## Getting Started

1. Open the project in **Android Studio**
2. Make sure minimum SDK is set to **API 29**
3. Run on an emulator or real device
4. Build & launch the app

---

## Progress Tracker

- [x] Use Case Definitions
- [x] Initial README
- [x] Task Entity & DAO
- [x] Repository Layer
- [ ] ViewModel Layer
- [ ] UI Fragments
- [ ] Unit Tests
- [ ] UI Testing
- [ ] UI Polish & Validation

---

## Author

- **Developer**: AdamChen
- **Email**: powq2011@gmail.com
- **License**: Abb



