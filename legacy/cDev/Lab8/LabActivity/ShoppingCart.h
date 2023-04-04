#ifndef SHOPPING_CART_H
#define SHOPPING_CART_H

#include "ItemToPurchase.h"

#define MAX_CUSTOMER_NAME_SIZE 50
#define MAX_CURRENT_DATE_SIZE 50
#define MAX_CART_SIZE 10


typedef struct ShoppingCart_struct {
   char customerName [MAX_CUSTOMER_NAME_SIZE];
   char currentDate [MAX_CURRENT_DATE_SIZE];
   ItemToPurchase cartItems [MAX_CART_SIZE];
   int cartSize;
} ShoppingCart;

/* AddItem: Add an item to the next available location in the cartItems array. 
 *   If there are no available positions remaining in the cartItems array, make no
 *   changes to the ShoppingCart. Display a message in the terminal stating that
 *   the item was not added because the ShoppingCart is full.
 *   Return the ShoppingCart object when finished.
 * 
 * item - ItemToPurchase object to be added to the ShoppingCart
 * cart - ShoppingCart object to update
 * 
 * returns - Updated ShoppingCart object
 */
   ShoppingCart AddItem(ItemToPurchase item, ShoppingCart cart);

/* RemoveItem: Remove the first occurrance of an item (starting with position 0) from the 
 *   ShoppingCart that matches the specified name. To remove the item, shift all of the 
 *   items after it in the cartItems array down by one position. Finally, decrement cartSize.
 * 
 *   If there are no matching items in the ShoppingCart or if the ShoppingCart is empty, display a message in the terminal
 *   stating that no matching items were found and make no changes to the ShoppingCart.  
 * 
 *   Return the ShoppingCart object when finished.
 * 
 * name - NULL teminitate string containing item name
 * cart - ShoppingCart object to update
 * 
 * returns - Updated ShoppingCart object
 */
ShoppingCart RemoveItem(char name[], ShoppingCart cart);

/* GetNumItemsInCart:  Calculate the total number of items in the shopping cart,
 *    not simply the number of ItemToPurchase objects.  This calculation should take
 *    into account the quanity of each items as well.
 *
 * cart - ShoppingCart object to process
 * 
 * returns - total number of items in the cart
 */
int GetNumItemsInCart(ShoppingCart cart);

/* GetCostOfCart:  Calculate the total cost of all items in the specified 
 *   ShoppingCart based upon each item's price and quanity.  Return the
 *   total cost as an int.
 *
 * cart - ShoppingCart object to process
 * 
 * returns - total cost of all items in cart
 */
int GetCostOfCart(ShoppingCart cart);

/* PrintInvoice:  Generate an invoice for the ShoppingCart.  The header should include
 *   the customerName and currentDate as well as the total number of items in the cart.
 *   For each ItemToPurchase object in the cartItems array, call the PrintItemCost() 
 *   function to display the unit price, quanity and subtotal cost for that ItemToPurchase 
 *   object. Finally, display the total cost using the GetCostOfCart() function to 
 *   calculate the total.
 * 
 * Example output is provided in the LabGuide
 * 
 * cart - ShoppingCart object to process
 */
void PrintInvoice(ShoppingCart cart);

/* PrintDescriptions:  Generate an summary for the ShoppingCart.  The header should include
 *   the customerName and currentDate. For each ItemToPurchase object in the cartItems array,
 *   call the PrintItemDescription() function to display the item name and description.
 * 
 *  Example output is provided in the LabGuide
 * 
 * cart - ShoppingCart object to process
 */
void PrintDescriptions(ShoppingCart cart);

#endif
