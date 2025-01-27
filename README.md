# User Browsing pattern access application
Application that tracks key product browsing activity of users to enable personalized product recommendations.

## Application Features
  * Enables a registered/authenticated user to browse products and place order.
  * Application track products viewed by the user and send relevant activity details to Apache Kafka for further processing by the downstream systems.

## Tech Stack
  * Java, Spring Boot, Apache Kafka, Spring security, JWT, Spring data JPA, MYSQL.

## Getting Started

  * Download the repository on local machine.
  * Import the downloaded project in your editor, ex- Intellij, Eclipse, or VSCode.
  * The project uses MySQL as the database, so make sure to have a running instance on MySQL on your localhost or have access to cloud instance.
  * The project uses Apache Kafka as the messaging platform, so make sure to have a running instance of Kafka cluster on your localhost or have access to cloud instance.
  * Make changes to the application.properties file to add your database credentials.
  * The Project has embedded Tomcat container so the application can be executed directly through the editor (Using Run button/icon).
  * Currently the project is under development and test cases are being written so it is not a complete final version. However, endpoints mentioned below are functional and can be used using REST client like Postman.

## API Endpoints

  * User Registration - `POST /auth/register`.
    
    *  Request Body
    
       ```
         firstName:"",
         lastName:"",
         email:"",
         password:""
       ```
  * User Login - `POST /auth/login`. 

    *  Request Body
      
       ```
         email:"",
         password:""
       ```

    *  Response Body

       ```
         token:""
       ```

  * Add Product - `POST /api/v1/products`. This URI is secure so please make sure to add token in the authorization header. Currently any registered user is authorized to make this request, however in future only users with ADMIN role will be authorized. 

    *  Request Body
   
       ```
        productName:"",
        productDescription:"",
        productUrl:"",
        productPrice:<Enter value of type double>,
        productQuantity:<Enter value of type int>
       ```

    *   Response Body

         ```
           productId:""
         ```

   * Place Order - `/api/v1/users/{userId}/orders`. This URI is secure so please make sure to add token in the authorization header. This request enables a user with userId to place order of products.

     *  Request Body
    
        ```
          productIdList:<Array of product id>
        ```

     *  Response Body
    
        ```
          orderId:""
        ```
  
         
