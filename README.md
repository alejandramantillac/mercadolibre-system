# mercadolibre-system
Algoritmos y Programación II - Tarea Integradora II

Maria Alejandra Mantilla C. - A00395831</br>
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


### **Commit 5 - Order test implementation**
**Error density** = 97/(3 InventoryTest)+(8 productSearcherTest )+(12 ProductTest)+(8 OrderTest) = 97/31 = 3.129</br>
**realiability** = 1-3.129 = -2.128</br>
**Completeness** = 31/9 = 3.4</br>

### **Commit 9 - More tests added in Product and ProductSearcher**

**Error density** = 243/(9 InventoryTest)+(18 productSearcherTest )+(16 ProductTest)+(8 Order Test)+( 5 OrderSearcherTest) = 243/56 = 4.339</br>
**reliability** = 1-4.339 = -3.339</br>
**Completeness** = 56/9 = 6.22</br>

### **Commit 11 - Exceptions implementation**

**Error density** =128/56 = 2.28</br>
**reliability** = 1-2.28 = -1.28</br>
**Completeness** = 56/9 = 6.22</br>

### **Commit 13 - ProductBinarySearcher class implementation**

**Error density** =105/56 = 1.875</br>
**reliability** = 1-1.875 = -0.875</br>
**Completeness** = 56/9 = 6.22</br>

### **Commit 17 - Search products by name and price methods implementation**

**Error density** =35/56 =0.625 </br>
**reliability** = 1-0.625 =0.375 </br>
**Completeness** = 56/9 = 6.22</br>