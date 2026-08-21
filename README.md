# 🏦 ABI Bank — Online Banking Management System

**ABI Bank (Anshu Bank of India)** is a full-stack web-based banking management system developed as a **BSc Computer Science project**. It simulates essential online banking operations using a modern frontend, Java Spring Boot backend, and MySQL database.

## 📌 Project Overview

The application provides a complete banking workflow, from customer registration and administrator approval to customer authentication, account management, money transfers, transaction tracking, and PDF passbook generation.

The project was developed to gain practical experience in **full-stack development, REST API development, database integration, authentication, and application deployment**.

## 🚀 Features

* 👤 Customer account registration
* 🔐 Customer login and authentication
* 👨‍💼 Admin login and account approval
* 💳 Personalized banking dashboard
* 💰 Account balance management
* 💸 Money transfer between users
* 📜 Transaction history
* 📄 Passbook PDF generation
* 👤 Customer profile management
* 🔑 Forgot password functionality
* 🔒 JWT-based authentication
* 🗄️ MySQL database integration

## 🛠️ Technologies Used

### Frontend

* HTML5
* CSS3
* JavaScript
* Fetch API

### Backend

* Java
* Spring Boot
* Spring Data JPA
* REST APIs
* JWT

### Database

* MySQL
* MySQL Workbench

### Tools

* IntelliJ IDEA
* Maven
* Git
* GitHub

## 🏗️ Architecture

```text
                    ABI Bank
                       │
                       ▼
              ┌─────────────────┐
              │ HTML / CSS / JS │
              │    Frontend     │
              └────────┬────────┘
                       │ REST API
                       ▼
              ┌─────────────────┐
              │   Spring Boot   │
              │     Backend     │
              └────────┬────────┘
                       │
              ┌────────┴────────┐
              ▼                 ▼
       ┌─────────────┐   ┌─────────────┐
       │     JWT     │   │ Spring Data │
       │    Auth     │   │     JPA     │
       └─────────────┘   └──────┬──────┘
                                │
                                ▼
                         ┌─────────────┐
                         │    MySQL    │
                         │   Database  │
                         └─────────────┘
```

## 📂 Project Structure

```text
ABI-Bank/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com.bank.abibank/
│       │       ├── controller/
│       │       ├── model/
│       │       ├── repository/
│       │       └── jwt/
│       │
│       └── resources/
│           ├── static/
│           └── application.properties
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## 🔐 Security

The application includes authentication and account approval checks using JWT and separate administrator authentication.

For deployment, sensitive information should be stored using environment variables instead of committing it to GitHub.

Example:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
ADMIN_USERNAME
ADMIN_PASSWORD
JWT_SECRET
```

> ⚠️ Never commit real database passwords, administrator passwords, JWT secrets, API keys, or other sensitive credentials to a public repository.

## ▶️ Running the Project Locally

### 1. Clone the repository

```bash
git clone https://github.com/Anshuman-Mallick/Anshu_Bank_of_India.git
```

### 2. Open the project

Open the project in **IntelliJ IDEA** or another Java IDE.

### 3. Configure MySQL

Create a MySQL database named:

```text
bankdb
```

Configure the database connection in your application configuration.

### 4. Run Spring Boot

Run the main Spring Boot application.

The application will normally be available at:

```text
http://localhost:8080
```

## 🎥 Project Demonstration

A project demonstration video is available showing the major functionality of the ABI Bank application, including registration, authentication, admin approval, dashboard operations, transactions, and passbook generation.

## 📚 Learning Outcomes

This project provided practical experience with:

* Full-stack web development
* Java and Spring Boot
* REST API development
* MySQL database management
* Spring Data JPA
* JWT authentication
* Frontend-backend integration
* PDF generation
* Git and GitHub
* Environment-based configuration
* Deployment concepts

## 🔮 Future Improvements

* Real OTP verification
* Email notifications
* BCrypt password hashing
* Two-factor authentication
* UPI/payment gateway integration
* Advanced transaction filtering
* Loan and fixed-deposit management
* Improved authorization and security
* Cloud deployment
* Docker support

## 👨‍💻 Developer

**Anshuman Mallick**
BSc Computer Science Student

### ABI Bank — Anshu Bank of India

**Java • Spring Boot • MySQL • HTML • CSS • JavaScript • JPA • JWT • Git • GitHub**

---

⭐ If you find this project useful or interesting, feel free to explore the repository.
