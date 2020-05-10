# shoppingList
Java 2 project

Usage:

Root request path - http://hostname:port/api/v1

GET

/products - returns all products.
<br>
Optional parameters:
name - to find products by name.
page, size - for a paginated request.

/products/{id} - returns a product with a specified id.

POST

/products - save a product to a database, accepts JSON as a request body,

PUT

/products - update an existing product.

DELETE

/products - delete a product from a database.