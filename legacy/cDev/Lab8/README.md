# Lab08 Guide
## Getting Started
[Lab Introduction Video](https://youtu.be/z6-XkpvbhOk)  

### Code Style Requirements
Please review the [CS253 Style Guide](https://docs.google.com/document/d/1zKIpNfkiPpDHEvbx8XSkZbUEUlpt8rnZjkhCSvM-_3A/edit?usp=sharing) and apply it in all lab warmups, lab activities and projects this semester. Coding Style will assessed as part of your lab and project grades.

### Code Quality Requirements
- Code must compile without warnings using the provided Makefile
- Programs must handle unexpected user input and either reprompt (loops) or gracefully exit with a non-zero exit status.
- Programs must handle error conditions gracefully, without crashing, ideally by checking function returns codes (if available) and returning a non-zero exit status.
- Programs should be free of memory related errors, buffer overflows, stack smashing, etc... Whether the program crashes or not.

## Lab Warmup - Online shopping cart (Part 1)
[Walkthrough Video](https://www.youtube.com/playlist?list=PLvnIObHoMl8cYVKwOXlAgRFquwLgPpYAH)  

### Problem Description  
1. Create three files to submit:

- ItemToPurchase.h - Struct definition and related function declarations
- ItemToPurchase.c - Related function definitions
- main.c - main() function

Build the ItemToPurchase struct with the following specifications: 

- Data members 
    - char itemName [ ]
    - int itemPrice
    - int itemQuantity
- Related functions
    - MakeItemBlank() 
        - Has a pointer to an ItemToPurchase parameter. 
        - Sets item's name = "none", item's price = 0, item's quantity = 0
    - PrintItemCost()
        - Has an ItemToPurchase parameter.

<br />
Ex. of PrintItemCost() output:

```
Bottled Water 10 @ $1 = $10
```
<br />
2. In main(), prompt the user for two items and create two objects of the ItemToPurchase struct.  Before prompting for the second item, enter the following code to allow the user to input a new string. `c` is declared as a char.  

```
c = getchar();
while(c != '\n' && c != EOF) {
   c = getchar();
}
```
<br />
Ex:

```
Item 1
Enter the item name:
Chocolate Chips
Enter the item price:
3
Enter the item quantity:
1

Item 2
Enter the item name:
Bottled Water
Enter the item price:
1
Enter the item quantity:
10
```
<br />
3. Add the costs of the two items together and output the total cost. 
<br /><br />
Ex:

```
TOTAL COST
Chocolate Chips 1 @ $3 = $3
Bottled Water 10 @ $1 = $10

Total: $13
```

### Error Handling
For the LabWarmup, please do not worry about user input errors in the UI. The purpose of this program is to test the ItemToPurchase struct and supporting functions.  Please focus your attention on implementing the expected behaviour for each function declared in the ItemToPurchase.h header file.

### Implementation Guide
1. Expand the folder named LabWarmup and open the files named ItemToPurchase.c, ItemToPurchase.h and main.c
2. Enter the program code to create an application as described in the Problem Description.
3. Test the program using the above example to ensure it functions as expected.
4. Commit the changes to your local repository with a message stating that LabActivity is completed.
5. Push the changes from your local repository to the github classroom repository.
6. Update the Coding Journal with an entry describing your experience using the steps outlined below.

## Lab Activity - Online shopping cart (Part 2)
### Problem Description  
This program extends the earlier "Online shopping cart" program. Please copy ItemToPurchase.c and ItemToPurchase.h from the LabWarmup folder into the LabActivity folder.  

<br />
1. Updates to ItemToPurchase.c and ItemToPurchase.h

- Extend the ItemToPurchase struct to contain a new data member called itemDescription. It should hold a maximum of 80 characters, including the '\0' terminator.   

```
char itemDescription[] 
```
- Extend the MakeItemBlack() function in ItemToPurchase.c so that it sets the itemDescription member to "none"

- Add a function declaration for PrintItemDescription() to ItemToPurchase.h and implement it in ItemToPurchase.c. This function takes an ItemToPurchase struct as a parameter and outputs the itemName and itemDescription as shown in the output below:
   - itemName: Bottled Water
   - itemDescription: Deer Park, 12 oz.

<br />
Ex. of PrintItemDescription() output:

```
Bottled Water: Deer Park, 12 oz.
```
<br />
2. Define each of the functions declared in the ShoppingCart.h header file by implementing them in ShoppingCart.c

The ShoppingCart struct as well as function declarations for the related functions described below have been provided in ShoppingCart.h. Details regarding each function as well as expected return values are included in the comments associated with each function declaration in ShoppingCart.h.  Please study those comments carefully before beginning implementation.  

**DO NOT MODIFY THE PROVIDED ShoppingCart.h HEADER FILE**

**Hint:** Some functions can initially be function stubs (empty functions), to be completed 
in later steps. 

The following is a summary of ShoppingCart.h header  
- ShoppingCart Structure
   - char customerName [ ]
   - char currentDate [ ]
   - ItemToPurchase cartItems [ ] - has a maximum of 10 slots (can hold up to 10 items of any quantity)
   - int cartSize - the number of filled slots in array cartItems [ ] (number of items in cart of any quantity)

- ShoppingCart Functions
   - AddItem()
   - RemoveItem()
   - GetNumItemsInCart() 
   - GetCostOfCart() 
   - PrintInvoice()

```
John Doe's Shopping Cart - February 1, 2016
Number of Items: 8

Nike Romaleos 2 @ $189 = $378
Chocolate Chips 5 @ $3 = $15
Powerbeats 2 Headphones 1 @ $128 = $128

Total: $521
```
   - PrintDescriptions()

```
John Doe's Shopping Cart - February 1, 2016

Item Descriptions
Nike Romaleos: Volt color, Weightlifting shoes
Chocolate Chips: Semi-sweet
Powerbeats Headphones: Bluetooth headphones
```
<br />
3. In main(), prompt the user for a customer's name and today's date. Output the name and date. Create an object of type ShoppingCart. 
<br /><br />

```
Enter Customer's Name:
John Doe
Enter Today's Date:
February 1, 2016

Customer Name: John Doe
Today's Date: February 1, 2016
```
<br />
4. Implement the program menu in the provided main.c source file.  You may implement this directly in main() or by creating a PrintMenu() function that takes a ShoppingCart as a parameter. Each option is represented by a single character as shown in the example output below. If the an invalid character is entered, continue to prompt for a valid choice. Continue to execute the menu until the user enters q to Quit.  

*Hint: Implement Quit before implementing other options.*

```
PROGRAM MENU
a - Add item to cart
r - Remove item from cart
i - Output item descriptions
o - Output shopping cart
q - Quit

Choose an option:
```
<br />
5. Implement "Add item to cart" menu option. 
<br /><br />

```
ADD ITEM TO CART
Enter the item name:
Nike Romaleos
Enter the item description:
Volt color, Weightlifting shoes
Enter the item price:
189
Enter the item quantity:
2
```

<br />
6. Implement the "Output shopping cart" menu option.
<br /><br />

```
John Doe's Shopping Cart - February 1, 2016
Number of Items: 8

Nike Romaleos 2 @ $189 = $378
Chocolate Chips 5 @ $3 = $15
Powerbeats Headphones 1 @ $128 = $128

Total: $521
```
<br />
7. Implement the "Output item descriptions" menu option. 
<br /><br />

```
John Doe's Shopping Cart - February 1, 2016

Item Descriptions
Nike Romaleos: Volt color, Weightlifting shoes
Chocolate Chips: Semi-sweet
Powerbeats Headphones: Bluetooth headphones
```

<br />
8. Implement the "Remove item from cart" menu option. 
<br /><br />

```
Enter name of item to remove:
Chocolate Chips
```
<br />

### Error Handling
For the LabActivity, please do not worry about user input errors in the UI beyond what is described above in the the Program Description. Please focus your attention on implementing the expected behaviour for each function declared in the ShoppingCart.h header file. The UI code needs to function correctly, but you may assume for this LabActivity that as long as you follow the exact order of inputs and data types show in the examples above, the user will enter correct data.

### Implementation Guide
1. Expand the folder named LabActivity and open each of the source (.c) and header (.h) files
2. Enter the program code to create an application as described in the Problem Description.
3. Test the program using the provided [Lab Activity Test Plan](LabActivityTestPlan.md) to ensure it functions as expected.
4. Commit the changes to your local repository with a message stating that LabActivity is completed.
5. Push the changes from your local repository to the github classroom repository.
6. Update the Coding Journal with an entry describing your experience using the steps outlined below.

## Coding Journal (Optional)
Keep a journal of your activities as you work on this lab. Many of the best engineers that I have worked with professionally have kept some sort of engineering journal. I personally packed notebooks around with me for nearly 8 years before I began keeping my notes electronically.   

Your journal can track ideas, bugs, cool links, code snippets, shell commands, rants, or simply a reflection on what worked well or not-so-well with this lab activity. I will not be grading the content of your journal, but I will expect at least two timestamped journal entries of at least a 75 to 150 words each added to the provided Journal.md file.  The purpose of this component is to help develop the habit of taking notes and creating documentation while you code. The more detail you provide the better as that will help you if you ever need to refer back to this project in the future.

## Markdown Resources
Markdown is a notation that is used to format text documents.  It is widely used in Software Development shops around the world, which is why we're asking you to use it in your lab documentation.  

Github provides a guide for getting started:  [Mastering Markdown](https://guides.github.com/features/mastering-markdown/)
