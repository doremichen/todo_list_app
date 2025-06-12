# Architecture Overview

## Project: Java MVVM Todo List App

This document provides an overview of the architecture and structure used in the Java MVVM-based Todo List Android application. The design follows clean architecture principles to ensure separation of concerns, testability, and maintainability.

---

## Architecture Layers

Presentation Layer
│
├── View (Activity / Fragment)
│ - Displays data
│ - Handles user interaction
│ - Observes ViewModel
│
├── ViewModel
│ - Exposes LiveData for UI
│ - Transforms and prepares data
│ - Calls UseCases (or Repository)
│
├── Use Cases (Optional Layer)
│ - Contains business rules
│ - Orchestrates operations between domain and data layers
│
├── Repository (Interface)
│ - Abstracts data access
│
├── Data Layer
│ - Implements Repository
│ - Data sources (Room DAO / Remote API)
│
└── Domain Layer
- Contains Entity & Value Objects
- Pure Kotlin/Java objects

---

## Data Flow

User Action →
View →
ViewModel →
Repository →
Local (Room) / Remote Data Source →
ViewModel (LiveData) →
UI Update

---

## 🧩 Key Components

| Component        | Description |
|------------------|-------------|
| `MainActivity` / `TaskListFragment` | View Layer – Displays list, filter, and navigation |
| `TaskViewModel`  | ViewModel – Handles UI logic, exposes LiveData |
| `TaskRepository` | Abstract interface for accessing task data |
| `TaskRepositoryImpl` | Implementation of repository using Room |
| `TaskDao`        | Data Access Object for SQLite database via Room |
| `TaskEntity`     | Data representation in Room |
| `Task`           | Domain Model entity (ID, title, description, isCompleted...) |
| `TaskFilter`     | Value Object – Filtering criteria |
| `AppDatabase`    | Room database configuration |
| `UseCase` (Optional) | For large projects: encapsulate logic like AddTask, DeleteTask |

---

## Libraries and Tools

- **Room** – SQLite ORM
- **LiveData** – Reactive data for ViewModel
- **ViewModel** – Android Architecture Component
- **DataBinding** (optional) – Bind ViewModel to layout
- **JUnit / Espresso** – Unit & UI Testing
- **PlantUML** – For domain and class diagrams

---

## Folder Structure

com.example.todoapp
├── data
│ ├── local (Room DB, DAO, Entity)
│ ├── repository
├── domain
│ ├── model (Task, FilterType)
│ ├── usecase (optional)
├── ui
│ ├── tasklist (View, ViewModel)
│ ├── addtask
├── utils
├── AppDatabase.java

---

## Architecture Benefits

- **Separation of Concerns**: UI, business logic, and data layers are cleanly decoupled.
- **Testability**: ViewModel and UseCases can be unit tested easily.
- **Scalability**: Easy to extend with filters, tags, due dates, etc.
- **Maintainability**: Modular structure helps future developers understand and modify the code.

---
