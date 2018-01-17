This program is shopping cart web project example.

This project uses:

Servlets for request proccessing,
JSP & JSTL for pages generation,
Dtabase interaction implemented for MySQL db,
HikariCP as pool of connections,
Maven for project build;

With this example you can navigate to product list & cart pages,
add product to cart from products page, remove position from cart, 
clear cart, process order from cart page.

If order successfully created you will see success page with order 
number and cart will be cleared.
 
DataSource.properties contain preferences for database connection. 

Program create 'webcart' database and tables 'products', 'orders', and 'orderscontent'
at launch if they not exist;

If table 'products' is empty, you will see page with "fill products" button, which fills
table with test content;