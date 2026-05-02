<h1 align="center">🛡️ Account Registration - Unit Testing Project</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/JUnit_5-25A162?style=for-the-badge&logo=junit5&logoColor=white" alt="JUnit5"/>
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven"/>
  <img src="https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white" alt="Actions"/>
</p>

> *This project implements automated unit tests for an "Account Registration" module. It demonstrates the practical application of software verification and validation techniques[cite: 1].*

## 👥 Team Members
* **Mehmet Sablak**
* **Melike Miray Sarıkçıoğlu**

---

## 🎯 Testing Techniques Applied
To ensure robust validation, the following test design techniques were strictly applied[cite: 1]:

* 🔀 **Equivalence Partitioning (EP):** Used to test domains like valid/invalid email formats and name character rules.
* 🚧 **Boundary Value Analysis (BVA):** Applied to critical edge cases such as the 18-year age limit and the minimum 8-character password requirement.

---

## 🧪 Test Scenarios Overview

| ID | Scenario Description | Technique Used | Expected Result |
| :---: | :--- | :---: | :---: |
| **01** | Valid Submission | 🌟 *Happy Path* | ✅ `Pass` |
| **02** | Empty First Name | 🔀 *EP (Invalid)* | 🛑 `Fail (Exception)` |
| **03** | First Name with Numbers | 🔀 *EP (Invalid)* | 🛑 `Fail (Exception)` |
| **04** | First Name with Spaces Only | 🔀 *EP (Invalid)* | 🛑 `Fail (Exception)` |
| **05** | Email without '@' symbol | 🔀 *EP (Invalid)* | 🛑 `Fail (Exception)` |
| **06** | Email without domain | 🔀 *EP (Invalid)* | 🛑 `Fail (Exception)` |
| **07** | Password < 8 characters | 🚧 *BVA (Below)* | 🛑 `Fail (Exception)` |
| **08** | Password Exactly 8 characters | 🚧 *BVA (Boundary)* | ✅ `Pass` |
| **09** | Empty Password | 🔀 *EP (Invalid)* | 🛑 `Fail (Exception)` |
| **10** | Empty Confirm Password | 🔀 *EP (Invalid)* | 🛑 `Fail (Exception)` |
| **11** | Passwords Do Not Match | 🔀 *EP (Invalid)* | 🛑 `Fail (Exception)` |
| **12** | Future Date of Birth | 🔀 *EP (Invalid)* | 🛑 `Fail (Exception)` |
| **13** | Age Under 18 | 🚧 *BVA (Below)* | 🛑 `Fail (Exception)` |
| **14** | Age Exactly 18 | 🚧 *BVA (Boundary)* | ✅ `Pass` |
| **15** | Invalid Date Format | 🔀 *EP (Invalid)* | 🛑 `Fail (Exception)` |

---

## ⚙️ Automated Test Execution
This project is fully integrated with **GitHub Actions**[cite: 1]. All 15 unit tests are automatically executed and validated upon every `push` or `pull_request` to the main branch.