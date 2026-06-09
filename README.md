# 🧪 SauceDemo Selenium Automation Framework

### 📌 Project Overview
This is a UI Automation Testing Framework built for the SauceDemo application using Selenium WebDriver, Java, TestNG, and Maven.  
It automates end-to-end user flows including login, product management, cart operations, checkout process, and logout functionality.

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

#### 🔐 LOGIN MODULE
- Valid login  
- Invalid login  
- Empty username and password validation  

#### 📦 PRODUCT MODULE
- Verify product list display  
- Verify product details  
- Add product to cart  
- Remove product from cart  
- Multiple product selection  
- Product sorting functionality  

#### 🛒 CART MODULE
- Verify cart page display  
- Remove items from cart  
- Continue shopping functionality  
- Cart persistence validation  
- Navigate to checkout page  

#### 💳 CHECKOUT MODULE
- Navigate to checkout page  
- Validate required fields  
- Enter valid checkout information  
- Verify order summary  
- Complete checkout process  
- Cancel checkout  
- Negative validation scenarios  

#### 🚪 LOGOUT MODULE
- Verify user logout functionality  

---

### ▶️ How to Run the Project

#### 1️⃣ Clone Repository
```bash
git clone https://github.com/your-username/your-repo-name.git
2️⃣ Import Project
Open IntelliJ IDEA
Click Open
Select the project folder
3️⃣ Install Dependencies

Maven will automatically download all dependencies from pom.xml

4️⃣ Run Tests

You can run tests using:

testng.xml file OR
Right click on test class → Run
📊 Reports

After execution, reports are generated in:

test-output/

📸 Screenshots (Optional Enhancement)

If implemented, failure screenshots are stored in:

screenshots/

🚀 Future Enhancements
Implementation of Page Object Model (POM) design pattern
Extent Reports integration for better reporting
Screenshot capture on test failure
Parallel test execution
CI/CD integration using GitHub Actions
Cross-browser testing support


