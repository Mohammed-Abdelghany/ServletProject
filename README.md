# Servlet CRUD Project 🚀

A **simple CRUD application** built with **Java Servlets**, **JSP**, and **JDBC**, demonstrating basic web application architecture with a **Service Layer**.

---

## 🔹 Overview
This project allows users to manage **Items** with fields:
- **Name**
- **Price**
- **Total Number**

It implements full CRUD operations:
1. **Create** new items
2. **Read** items list or single item
3. **Update** existing items
4. **Delete** items

All operations are handled via a **Servlet controller** and **Service layer**, connecting to a relational database (MySQL/Oracle).

---

## 🛠 Technologies
- Java Servlets & JSP
- JDBC (Database connectivity)
- Eclipse IDE
- Tomcat Server
- MySQL / Oracle Database

---

## 📂 Project Structure
```
src/
 └── com.item.controller   -> Servlet controllers
 └── com.item.model        -> Item entity class
 └── com.item.service      -> Service layer for CRUD operations
WebContent/
 └── index.jsp             -> Show all items
 └── show-item.jsp         -> Show single item
 └── update-item.jsp       -> Edit item form
```

---

## 🔐 Authentication (Login & Sign Up)
The project is ready to be extended with **user authentication** features:

- **Sign Up**: Users can register with a username, email, and password. Passwords will be securely hashed before storing in the database.
- **Login**: Registered users can log in to access and manage their items.
- **Logout**: Users can securely end their session.

> Future updates will include secure session management and role-based access control.

---

## ⚡ How to Run
1. Clone this repository:
```bash
git clone https://github.com/Mohammed-Abdelghany/ServletProject.git
```
2. Import project into **Eclipse** as **Dynamic Web Project**.  
3. Configure **database connection** in `DataSource`.  
4. Deploy the project on **Tomcat Server**.  
5. Access in browser:
```
ServletProject/itemservlet
```

---

## 🎯 Features
- Full CRUD operations for `Item`
- Uses Service Layer for clean architecture
- JSP pages for frontend display
- Database-driven operations
- Ready for Login & Sign Up authentication

---

## 👨‍💻 Author
**Mohammed Abdelghany**
Laravel Developer and Full-Stack Enthusiast, currently learning Java, Servlets, and planning to explore Spring Framework.
---

## ✅ License
This project is **open source**. Feel free to explore, modify, and reuse for learning purposes.
