# mercadolibre-system
Algoritmos y Programación II - Tarea Integradora II

Maria Alejandra Mantilla C. - A00395831
Pablo Fernando Pineda P. - A00395831

#**MercadoLibreSystem Project**
MercadoLibre has hired our services to develop an application that allows the online sale of their virtual store.

This app should allow users to enter products into the system with their respective information and create an inventory of products.

It should also allow users to search for products according to the search criteria they choose, such as name, price, price range, number of times purchased, among others. After performing this search, the products should be displayed on the screen in an orderly manner (ascending or descending).

This app also allows users to make purchases or orders, and each time one of these is made, the product inventory decreases and the number of times purchased increases.

## **Quality indicators**
**Error density** = total errors / total tests
**realiability** = 1 - error density
**Completeness** = tests / total functionalities


### **Commit 5 - Order test implementation**
**Error density** = 97/(3 InventoryTest)+(8 productSearcherTest )+(12 ProductTest)+(8 OrderTest) = 97/31 = 3.129
**realiability** = 1-3.129 = -2.128
**Completeness** = 31/9 = 3.4

### **Commit 9 - More tests added in Product and ProductSearcher**

*Error density* = 243/(9 InventoryTest)+(18 productSearcherTest )+(16 ProductTest)+(8 Order Test)+( 5 OrderSearcherTest) = 243/56 = 4.339
*reliability* = 1-4.339 = -3.339
*Completeness* = 56/9 = 6.22
