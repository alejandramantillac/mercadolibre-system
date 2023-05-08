# mercadolibre-system
Algoritmos y Programación II - Tarea Integradora II

Maria Alejandra Mantilla C. - A00395792</br>
Pablo Fernando Pineda P. - A00395831

# **MercadoLibreSystem Project**
MercadoLibre has hired our services to develop an application that allows the online sale of their virtual store.

This app should allow users to enter products into the system with their respective information and create an inventory of products.

It should also allow users to search for products according to the search criteria they choose, such as name, price, price range, number of times purchased, among others. After performing this search, the products should be displayed on the screen in an orderly manner (ascending or descending).

This app also allows users to make purchases or orders, and each time one of these is made, the product inventory decreases and the number of times purchased increases.

## **Quality indicators**</br>
**Error density** = total errors / total tests</br>
**realiability** = 1 - error density</br>
**Completeness** = tests / total functionalities</br>


### **Commit 5 - Order test implementation - df3433af4be8b7ce83e3b0637946c788d3f7491f**

**Error density** = 97/(3 InventoryTest)+(8 productSearcherTest )+(12 ProductTest)+(8 OrderTest) = 97/31 = 3.129</br>
**realiability** = 1-3.129 = -2.128</br>
**Completeness** = 31/9 = 3.4</br>

### **Commit 9 - More tests added in Product and ProductSearcher - 64bc0e37d8c3976966c117a9052daeb1d073f14b**

**Error density** = 243/(9 InventoryTest)+(18 productSearcherTest )+(16 ProductTest)+(8 Order Test)+( 5 OrderSearcherTest) = 243/56 = 4.339</br>
**reliability** = 1-4.339 = -3.339</br>
**Completeness** = 56/9 = 6.22</br>

### **Commit 11 - Exceptions implementation - 0e286e7ac84c5f383c4a2e180295194301bec47c**

**Error density** =128/56 = 2.28</br>
**reliability** = 1-2.28 = -1.28</br>
**Completeness** = 56/9 = 6.22</br>

### **Commit 13 - ProductBinarySearcher class implementation - 3c6d953192d1394fc987a5fd628d275b13ad2efe**

**Error density** =105/56 = 1.875</br>
**reliability** = 1-1.875 = -0.875</br>
**Completeness** = 56/9 = 6.22</br>

### **Commit 17 - Search products by name and price methods implementation - dccc75b40f92dcf34f9c980355b97d5702ec49ad**

**Error density** =35/56 =0.625 </br>
**reliability** = 1-0.625 =0.375 </br>
**Completeness** = 56/9 = 6.22</br>

### **Commit 18 - Search products by category and times purchased methods implemented in ProductSearcher and Controller class - ae6347b5c9de2389653fbc4fc6c10c196ba1c9b9**
**Error density** = 32/56 = 0.571 </br>
**reliability** = 1-0.571 = 0.429 </br>
**Completeness** = 56/9 = 6.22 </br>

### **Commit 20 - Search products by keyword and quantity methods implemented in ProductSearcher and Controller class - d5016a30042b2e46e554bb9e42a5a6a468301dda**
**Error density** = 23/56 = 0.410 </br>
**reliability** = 1-0.410 = 0.59 </br>
**Completeness** = 56/9 = 6.22 </br>

### **Commit 22 - Search products by times purchased and price with range implemented in ProductSearcher class - 20d18f8fe8808f52825ea0a3063aaca9c9645f46**
**Error density** = 14/56 = 0.25 </br>
**reliability** = 1-0.25 = 0.75 </br>
**Completeness** = 56/9 = 6.22 </br>

### **Commit 24 - Search orders by Customer name and total implemented in OrderSearcher class**
**Error density** = 1/56 = 0.0178 </br>
**reliability** = 1-0.0178 = 0.9822 </br>
**Completeness** = 56/9 = 6.22 </br>