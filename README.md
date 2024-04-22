
<div align="center">
    <img src="images\logo2.png" alt="Logo">
    <h2 align="center">DivineDepartures</h2>
</div>

## About The Project
<p>A WebApp project that allows user to craft personal resting spaces for their departed loved ones</p>
<p>This is created as part of the final presentation for the Professional Diploma for Software Development course</p>

## Stacks Used
- Angular
 - Material theme
- Spring Boot
  - Spring Security
  - Spring Mail
  - Spring JWT
  - CSRF Token
- MySQL

## Getting started
Ensure you have Git, MySQL Workbench or SQL client, JDK17, Node.js installed in your machine

To test the WebApp locally:
1. **Clone the repo.** In your terminal, use the git clone command. This command creates a local copy of the project on your machine.
      ```sh
      git clone https://github.com/ridzuanazmi/DivineDepartures.git
      ```
2.  **Run the SQL script.** in your SQL client, run the schema1.sql to create the tables locally
3. **Start the server app.** Navigate to the server folder in terminal and run:
      ```sh
      mvn clean spring-boot:run
      ```
4. **Open the app in your browser**. enter http://localhost:8080/
5. **Create and account** in the register page
