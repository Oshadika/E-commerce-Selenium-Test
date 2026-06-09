# 🧪 SauceDemo Selenium Automation Framework

### 📌 Project Overview
This is a UI Automation Testing Framework built for the SauceDemo application using Selenium WebDriver, Java, TestNG, and Maven.  
It automates end-to-end user flows like login, product management, cart operations, checkout process, and logout functionality.

---

### 🌐 Application Under Test
https://www.saucedemo.com/

---

### 🏗️ Framework Structure

src/test/java  
├── pages → Page classes (locators + actions)  
├── tests → TestNG test classes  
├── utils → Utility classes (driver setup, waits, screenshots)  
└── base → BaseTest (setup and teardown)

---

### ⚙️ Technologies Used
- Java  
- Selenium WebDriver  
- TestNG  
- Maven  
- IntelliJ IDEA  
- Git & GitHub  

---

### 🧪 Test Scenarios Covered

#### LOGIN MODULE
- Valid login  
- Invalid login  
- Empty field validation  

#### PRODUCT MODULE
- Verify product list  
- Verify product details  
- Add product to cart  
- Remove product from cart  
- Sorting functionality  

#### CART MODULE
- Add items to cart  
- Remove items  
- Continue shopping  
- Cart persistence  

#### CHECKOUT MODULE
- Checkout form validation  
- Required field validation  
- Complete purchase flow  
- Cancel checkout  

#### LOGOUT MODULE
- Verify user logout functionality  

---

### ▶️ How to Run Project

#### 1. Clone Repository
```bash
git clone https://github.com/your-username/your-repo-name.git
