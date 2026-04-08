<!-- <h2> Step 1</h2>
<p>Download Git from:
https://git-scm.com/download/win </p>
<p>Run the installer.</p>
<p>Click Next through the setup (default settings are fine).
<p>Click Install and finish.</p>
<h2> Verify Installation</h2> 
<p>Press Windows + R</p>
<p>Type cmd and press Enter</p>
<p> Type:</p>
<b> git --versio </b>
<p> If installed correctly, it will show the Git version.</p>
<h2>Step 3</h2>
<p>Go to the Folder Where You Want to Save the Project </p>
<p>Clone the Repository </p>
    <b> git clone https://github.com/gitdeme/webservice-2018.git</b>
<p>Git will download the project files to your computer.</p>







<h2>To access the generated WSDL, access the webservice by wrting as follow</h2>
http://localhost:8080/ws/calculator.wsdl -->



# ShapeTool SOAP Web Service
- **Name:** Bilal Ebrahim
- **Id:** 145647
- **University:** Woldia University  
- **Department:** Software Engineering (5th Year)

## 1. Design Decisions
- **Contract-First Approach:** We designed the XSD first to ensure a strict data contract between client and server.
- **Java Spring Boot:** Used for its robust Spring-WS support and embedded Tomcat server.

## 2. Schema (XSD) Structure
- **Target Namespace:** `http://wuniversity.edu/se/shapetool`
- **Data Types:** Used `xs:double` for precision in area calculations.
- **Restrictions:** Implemented `minInclusive="0.0"` for all dimensions to prevent negative inputs.

## 3. Service Operations
- `circleArea`: Calculates πr².
- `squareArea`: Calculates side².
- `rectangleArea`: Calculates L × W.
- `parallelogramArea`: Calculates B × H.
- `triangleArea`: Calculates 0.5 × B × H.

## 4. Validation & Error Handling
- **Interceptor:** A `PayloadValidatingInterceptor` was configured in `WebServiceConfig`.
- **Fault Handling:** When XSD validation fails (e.g., negative radius), the service automatically returns a `SOAP-ENV:Client` Fault with a descriptive error message.

## 5. How to Run & Test
1. Run `./mvnw spring-boot:run`
2. Access WSDL: `http://localhost:8080/ws/shapetool.wsdl`
3. Use Postman/SoapUI to send POST requests to `http://localhost:8080/ws/shapetool`
