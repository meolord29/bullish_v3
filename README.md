# bullish_v3

Run Application: mvn spring-boot:run

Test Application: mvn test

# __ADMIN REST API Contracts__

## Admin Operations
### Get All Admins

    Request:
        Method: GET
        Endpoint: /admin_access/admins
    Response:
        Status: 200 OK
        Body: List of Admin objects

### Get Admin by Username

    Request:
        Method: GET
        Endpoint: /admin_access/admins/{username}
        Path Variable: username (String)
    Response:
        Status: 200 OK if admin found, 404 NOT FOUND otherwise
        Body: Admin object

### Create Admin

    Request:
        Method: POST
        Endpoint: /admin_access/admins/{admin}
        Body: Admin object
    Response:
        Status: 201 CREATED
        Body: Created Admin object

### Update Admin

    Request:
        Method: PUT
        Endpoint: /admin_access/admins/{admin}
        Body: Updated Admin object
    Response:
        Status: 200 OK if admin updated, 404 NOT FOUND otherwise
        Body: Updated Admin object

### Remove Admin

    Request:
        Method: DELETE
        Endpoint: /admin_access/admins/{admin}
        Body: Admin object to be removed
    Response:
        Status: 200 OK if admin removed, 404 NOT FOUND otherwise
        Body: Removed Admin object

## Client Operations
### Get All Clients

    Request:
        Method: GET
        Endpoint: /admin_access/clients
    Response:
        Status: 200 OK
        Body: List of Client objects

### Get Client by Username

    Request:
        Method: GET
        Endpoint: /admin_access/clients/{name}
        Path Variable: name (String)
    Response:
        Status: 200 OK if client found, 400 BAD REQUEST otherwise
        Body: Client object

## Product Operations
### Get All Products

    Request:
        Method: GET
        Endpoint: /admin_access/products
    Response:
        Status: 200 OK
        Body: List of Product objects

### Get Product by Name

    Request:
        Method: GET
        Endpoint: /admin_access/products/{name}
        Path Variable: name (String)
    Response:
        Status: 200 OK if product found, 404 NOT FOUND otherwise
        Body: Product object

### Create Product

    Request:
        Method: POST
        Endpoint: /admin_access/products/{product}
        Body: Product object
    Response:
        Status: 201 CREATED
        Body: Created Product object

### Update Product

    Request:
        Method: PUT
        Endpoint: /admin_access/products/{product}
        Body: Updated Product object
    Response:
        Status: 200 OK if product updated, 404 NOT FOUND otherwise
        Body: Updated Product object

### Remove Product

    Request:
        Method: DELETE
        Endpoint: /admin_access/products/{product}
        Body: Product object to be removed
    Response:
        Status: 200 OK if product removed, 404 NOT FOUND otherwise
        Body: Removed Product object

# __CLIENT REST API Contracts__

## Client Operations
### Get Client by Username

    Request:
        Method: GET
        Endpoint: /client_access/clients/{username}
        Path Variable: username (String)
    Response:
        Status: 200 OK if client found, 404 NOT FOUND otherwise
        Body: Client object

### Create Client

    Request:
        Method: POST
        Endpoint: /client_access/clients/{client}
        Body: Client object
    Response:
        Status: 201 CREATED
        Body: Created Client object

### Update Client

    Request:
        Method: PUT
        Endpoint: /client_access/clients/{client}
        Body: Updated Client object
    Response:
        Status: 200 OK if client updated, 404 NOT FOUND otherwise
        Body: Updated Client object

### Remove Client

    Request:
        Method: DELETE
        Endpoint: /client_access/clients/{client}
        Body: Client object to be removed
    Response:
        Status: 200 OK if client removed, 404 NOT FOUND otherwise
        Body: Removed Client object

## Product Operations
### Get All Products

    Request:
        Method: GET
        Endpoint: /client_access/products
    Response:
        Status: 200 OK
        Body: List of Product objects

### Get Product by Name

    Request:
        Method: GET
        Endpoint: /client_access/products/{name}
        Path Variable: name (String)
    Response:
        Status: 200 OK if product found, 404 NOT FOUND otherwise
        Body: Product object

## Basket Operations
### Get All Baskets for a Specific Client

    Request:
        Method: GET
        Endpoint: /client_access/basket/{username}/all
        Path Variable: username (String)
    Response:
        Status: 200 OK
        Body: List of Basket objects

### Add Item to Basket

    Request:
        Method: POST
        Endpoint: /client_access/basket
        Body: Basket object
    Response:
        Status: 201 CREATED
        Body: Created Basket object

### Update Basket

    Request:
        Method: PUT
        Endpoint: /client_access/basket
        Body: Updated Basket object
    Response:
        Status: 200 OK if basket updated, 404 NOT FOUND otherwise
        Body: Updated Basket object

### Remove Item from Basket

    Request:
        Method: DELETE
        Endpoint: /client_access/basket
        Body: Basket object to be removed
    Response:
        Status: 200 OK if item removed from basket, 404 NOT FOUND otherwise
        Body: Removed Basket object

### Get Total Price of the Basket

    Request:
        Method: GET
        Endpoint: /client_access/basket/{username}/priceTotal
        Path Variable: username (String)
    Response:
        Status: 200 OK
        Body: PriceOutput object representing the total price

## ConfirmedPurchase Operations
### Add Confirmed Purchase

    Request:
        Method: POST
        Endpoint: /client_access/confirmedPurchase/{username}
        Body: ConfirmedPurchase object
    Response:
        Status: 201 CREATED
        Body: Created ConfirmedPurchase object

### Get All Confirmed Purchases for a Specific Client

    Request:
        Method: GET
        Endpoint: /client_access/confirmedPurchase/{username}/all
        Path Variable: username (String)
    Response:
        Status: 200 OK
        Body: List of ConfirmedPurchase objects