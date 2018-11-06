Description
    The PropertyManager application uses Open JDK 11, SpringBoot, H2 database and Swagger for documentation. The entire
     application was developed using IntelliJ. It uses maven for dependency management. The entire property details are
     stored in the in-memory H2 database.
     The main class is com.corelogic.propertymanager.PropertymanagerApplication. You can run this class as a standalone
     application.

     Note: Since H2 is a in-memory db, every time you re-start the application, the data is lost.

EndPoints
     The following end points have been exposed

     Description :- Adds a new property
     Method :- POST
     http://localhost:8080/newProperty

     Description :- Updates a specified property ID
     Method :- PUT
     http://localhost:8080/updateProperty/{property id to be updated}

     Description :- returns all the added properties based on the pagination size specified in the application.properties
     Pagination size property name :- propertymanager.pagination.size
     Parameter :- pageNum is the page number of the pagination logic
     Pagination Description :- Based on the pagination size property, only those many records will be returned in one page,
                Each page starts at pageNum zero.
     Method :- GET
     http://localhost:8080/showProperties?pageNum=0

Documentation
     The documentation can be accessed at the following URL http://localhost:8080/swagger-ui.html

Enhancements
     If I had some more time, I would want to introduce Authentication service to only allow valid users to access the
     end points. I would be using JWT (JSON Web Token) as the token passing mechanism.
     Also I would want to implement logging mechanism.