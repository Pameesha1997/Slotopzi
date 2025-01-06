# API for Slotopzi


1. Clone the repository and cd into it

- `git clone https://github.com/dumidu1998/slotopzi-API.git`

- `cd slotopzi-API`


2. Open the project folder with IntelliJ 





3. Click on Add Configuration and Add new Spring Boot project configuration by selecting the java version



Then 

4. Go to application.properties file and change the URL for the MySQL 

- `spring.datasource.url = jdbc:mysql://localhost:3306/slotopzi`



5. Run the Application with Run button



Make sure to run the MySQL server in the development environment before starting the application. 
###### Import the SQL file provided to the database. (slotopzi.sql)
# 
If you need to change the port address of the backend, edit the application.properties file `server.port = 9000`


Once All done user can access the backend with [http://localhost:9000 ](http://localhost:9000) 

