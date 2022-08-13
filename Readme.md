# CRUD SYSTEM PRODUCTS ⭐📝
### Members: **Edison Rivera** - **Estela Chipantasi**

# **User Guide** 📑

1. **You have to log in** to go to the main window. 🔐
   
![Window Login](assets/window_login.png) 

2. Next, the main window will be displayed where **you must choose a section to work with, otherwise the buttons will remain locked.**

Examples:

![Section to choose](assets/example_one.png)

![Unlocked buttons](assets/example_two.png)

3. Now you can **CREATE, UPDATE, SEARCH/READ OR DELETE** the data of each of the sections.

## CREATE
* The window will be displayed where you can add the requested information depending on the section you have chosen. 
  
![CREATE P](assets/createP.png)

![CREATE C](assets/createC.png)

![CREATE PR](assets/createPR.png)

## UPDATE

* When you need to update some data, you need to enter the primary key according to the section to update. 
Key for the section:

**Products = Product code**

**Providers = Providers RUC**

**Categories = Categories code**

![UPDATE P](assets/updateP.png)

![UPDATE C](assets/updateC.png)

![UPDATE PR](assets/updatePR.png)

After pressing the consult button, all the data will be shown and you will be able to update the field you want.

## SEARCH/READ

* If you want to search for any product, category or supplier. First select the corresponding section and then choose the way to search, it can be by name or code.
  
**For section Providers the code = RUC**

![SEARCH DATA](assets/read.png)

## DELETE

* In the case of delete. First you must select the section where you want to delete the information, then search for the section key:
  
**Products = Product Code**

**Suppliers = RUC Suppliers**

**Categories = Category Code**

And from there delete.

![DELETE P](assets/deleteP.png)

![DELETE C](assets/deleteC.png)

![DELETE PR](assets/deletePR.png)

## **TYPE OF ERRORS 💀:**
![ERROR 1062](assets/error62.png)
   
**[-] A record with the same PK already exists.** 🔑
   
![ERROR 1452](assets/error52.png)

**[-] There is no record to reference (FK)** 🗝
   
![ERROR 1406](assets/error06.png)

**[-] The field exceeds the limit.**
