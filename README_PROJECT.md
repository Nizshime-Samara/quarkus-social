
# quarkus-social
This project uses Quarkus version 2.3.0, the Supersonic Subatomic Java Framework.
If you want to learn more about Quarkus visit the website: https://quarkus.io/ .

## Running your application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```
> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Expose API's description through an OpenAPI specification / Swagger UI

- **You will need**:
  - JDK 11+ installed with 'JAVA_HOME' configured .
  - Apache Maven 3.9.6.
  
>  **_NOTE:_** Quarkus now ships with Open API Swagger documentation in dev mode and PRD at http://localhost:8080/q/swagger-ui .

# Project specific settings on Windows.
### MySQL Database Configuration for Local Execution

To run the MySQL database for the Quarkus application locally, follow these steps:

- **Access Services.msc**:
  - Press `Win + R` to open the Run dialog box.
  - Type `services.msc` and press Enter. This will open the Windows Services Manager..

- **Locate MySQL Service**:
  - In the list of services, look for MySQL. The service name may vary depending on the installation (e.g. MySQL57, MySQL80, etc.).

- **Start the Service**:
  - Right click on the MySQL service and choose 'Start'.
  - If the service is already running and you want to restart it, choose 'Restart'.
  - To check if MySQL is running correctly, use MySQL Workbench or any MySQL client (such as DBeaver).

- **Automatic Configuration**:
  - For MySQL to start automatically with Windows, right-click on the service, select 'Properties' and, in the 'Startup Type' tab, choose 'Automatic'.