# bullish_v3

Run Application: mvn spring-boot:run

Test Application: mvn test


## Admin Operations
Get All Admins

    Request:
        Method: GET
        Endpoint: /admin_access/admins
    Response:
        Status: 200 OK
        Body: List of Admin objects

Get Admin by Username

    Request:
        Method: GET
        Endpoint: /admin_access/admins/{username}
        Path Variable: username (String)
    Response:
        Status: 200 OK if admin found, 404 NOT FOUND otherwise
        Body: Admin object

Create Admin

    Request:
        Method: POST
        Endpoint: /admin_access/admins/{admin}
        Body: Admin object
    Response:
        Status: 201 CREATED
        Body: Created Admin object

Update Admin

    Request:
        Method: PUT
        Endpoint: /admin_access/admins/{admin}
        Body: Updated Admin object
    Response:
        Status: 200 OK if admin updated, 404 NOT FOUND otherwise
        Body: Updated Admin object

Remove Admin

    Request:
        Method: DELETE
        Endpoint: /admin_access/admins/{admin}
        Body: Admin object to be removed
    Response:
        Status: 200 OK if admin removed, 404 NOT FOUND otherwise
        Body: Removed Admin object

Client Operations
Get All Clients

    Request:
        Method: GET
        Endpoint: /admin_access/clients
    Response:
        Status: 200 OK
        Body: List of Client objects

Get Client by Username

    Request:
        Method: GET
        Endpoint: /admin_access/clients/{name}
        Path Variable: name (String)
    Response:
        Status: 200 OK if client found, 400 BAD REQUEST otherwise
        Body: Client object

Product Operations
Get All Products

    Request:
        Method: GET
        Endpoint: /admin_access/products
    Response:
        Status: 200 OK
        Body: List of Product objects

Get Product by Name

    Request:
        Method: GET
        Endpoint: /admin_access/products/{name}
        Path Variable: name (String)
    Response:
        Status: 200 OK if product found, 404 NOT FOUND otherwise
        Body: Product object

Create Product

    Request:
        Method: POST
        Endpoint: /admin_access/products/{product}
        Body: Product object
    Response:
        Status: 201 CREATED
        Body: Created Product object

Update Product

    Request:
        Method: PUT
        Endpoint: /admin_access/products/{product}
        Body: Updated Product object
    Response:
        Status: 200 OK if product updated, 404 NOT FOUND otherwise
        Body: Updated Product object

Remove Product

    Request:
        Method: DELETE
        Endpoint: /admin_access/products/{product}
        Body: Product object to be removed
    Response:
        Status: 200 OK if product removed, 404 NOT FOUND otherwise
        Body: Removed Product object