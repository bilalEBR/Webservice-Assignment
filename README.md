# ShapeTool SOAP Web Service Implementation

| Student Information | Details |
| :--- | :--- |
| **Name** | Bilal Ebrahim |
| **ID** | 145647 |
| **University** | Woldia University |
| **Department** | Software Engineering (5th Year) |
| **Project** | Assignment 1 - ShapeTool Web Service |
| **Presentation Date** | April 8, 2026 |

---
## 1. Project Overview
The **ShapeTool** is a professional SOAP-based web service developed to perform geometric area calculations. This project adheres to the principles of **Service-Oriented Architecture (SOA)** and utilizes a **Contract-First development approach**. The service exposes multiple operations to calculate the area of circles, squares, rectangles, parallelograms, and triangles, ensuring data integrity through strict XML validation.

## 2. Design Decisions
- **Contract-First Approach:** The XML Schema (XSD) was designed before any Java code. This ensures that the service contract is independent of the implementation and provides a clear definition for clients.
- **Spring Boot Framework:** Chosen for its robust support for Spring-WS, allowing for easy integration of WSDL generation and SOAP message handling.
- **Data Precision:** All calculations utilize `xs:double` to ensure high precision for geometric results.

## 3. Schema (XSD) Structure
- **Target Namespace:** `http://wuniversity.edu/se/shapetool`
- **Data Types:** Used `xs:double` for precision in area calculations.
- **Restrictions:** Implemented `minInclusive="0.0"` for all dimensions to prevent negative inputs.

## 4. Service Operations
- The service provides the following operations via the ShapeToolPort:
- **Operation** | **Input Parameters** | **Mathematical Formula**
- **circleArea** | radius | Area = π × r²
- **squareArea** | side | Area = side²
- **rectangleArea** | length, width | Area = L × W
- **parallelogramArea** | base, height | Area = base × height
- **triangleArea** | base, height | Area = 0.5 × base × height

## 5. Validation & Error Handling
- **Interceptor:** A `PayloadValidatingInterceptor` was configured in `WebServiceConfig`.
- **Fault Handling:** When XSD validation fails (e.g., negative radius), the service automatically returns a `SOAP-ENV:Client` Fault with a descriptive error message.
  
## 6. SOAP Request & Response Samples
- For ease of testing and evidence of functionality, a dedicated directory named `soap-samples/` is located in the project root. 
- This folder contains complete XML message examples for every operation:
- **Circle**: `circle-request.xml` / `circle-response.xml`
- **Square**: `square-request.xml` / `square-response.xml`
- **Rectangle**: `rectangle-request.xml` / `rectangle-response.xml`
- **Parallelogram**: `parallelogram-request.xml` / `parallelogram-response.xml`
- **Triangle**: `triangle-request.xml` / `triangle-response.xml`
- Each request file demonstrates the correct namespace usage (`http://webservice.wdu/shapetool`) and the structured format required by the service.

## 7. How to Run & Test
1. Run `./mvnw spring-boot:run`
2. Access WSDL: `http://localhost:8080/ws/shapetool.wsdl`
3. Use Postman/SoapUI to send POST requests to `http://localhost:8080/ws/shapetool`
