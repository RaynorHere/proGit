#include <stdio.h>
#include <string.h>
#include "ShoppingCart.h"

ShoppingCart AddItem(ItemToPurchase item, ShoppingCart cart)
{

    // I THINK 10 is intended to be the max number of items? And that cartSize is the variable we track number of items with?
    // I say that because I don't know how to access the size of the cartItems array, like we would in Java or Python, and
    // that the given constant for maximum cart size is 10. So this is pretty thoroughly a stab in the dark
    if (cart.cartSize == MAX_CART_SIZE)
    {
        printf("Item not added; cart is already full.\n");
        return cart;
    }

    // I can NOT stress enough how ABSOLUTELY NO IDEA I have what I'm doing, here. This is all gut- and fear-instinct
    else
    {
        // int nextOpen = 0;

        // It's technically possible to get in to this section of the loop and still be trying to add too many items.
        // That is, if "cartSize" is equal to 8, we have space, true, but if someone is trying to add more than 2 items
        // to their cart, we will go over the max size, which is not cool. SO:
        if (cart.cartSize + item.itemQuantity > MAX_CART_SIZE)
        {
            printf("Item not added; requested quantity would exceed maximum cart limit of 10.\n");
            return cart;
        }

        else
        {
            MakeItemBlank(&cart.cartItems[cart.cartSize]);
            cart.cartItems[cart.cartSize] = item;
            cart.cartSize += item.itemQuantity;
        }
        
            printf("Item added!\n");
            printf("%d\n", cart.cartSize);

            // Spit cart back out, hopefully modified
            return cart;
    }
}

ShoppingCart RemoveItem(char name[], ShoppingCart cart)
{

    int removeIndex = -1;

    // First order of bidness is to see if we have a match anywhere in the array for the requested item
    for (int i = 0; i < 10; i++)
    {

        // Match
        if (strcmp(cart.cartItems[i].itemName, name) == 0)
        {

            // Put in the array the item is at
            removeIndex = i;

            // And we therefore don't have to keep iterating through the array, so for brevity's sake:
            break;
        }
    }

    // On the outside of the loop, if the marker index is still -1, the item was never found
    if (removeIndex == -1)
    {
        printf("No item matching that name was found in shopping cart.\n");
        return cart;
    }

    // Now, assuming we're in a position where we've found a valid item to remove, reduce the cartSize variable by the quantity
    // of the item we're deleting. We need to do this before we overwrite the item here. Could do this in one line, but I'm choosing not to
    int deduction = cart.cartItems[removeIndex].itemQuantity;
    cart.cartSize -= deduction;

    // Okay, uh, now, loop through the array and move everything down one index, starting at the one after the removed item?
    // I can't believe how sure I am that this is going to completely not work, but what it SHOULD do is "delete" the item
    // by basically overwriting it with whatever was in front of it, and continue doing so down the cart
    for (int i = removeIndex; i < MAX_CART_SIZE; i++)
    {

        // In the event of a full cart, trying to grab cart[10] for moving over would cause an error. I think. So, to that end,
        // I will write in this little fix.
        if (i == 9)
        {
            strcpy(cart.cartItems[i].itemName, "none");
            strcpy(cart.cartItems[i].itemDescription, "none");
            cart.cartItems[i].itemPrice = 0;
            cart.cartItems[i].itemQuantity = 0;
            break;
        }

        // The last iteration of i on a non-full cart should be one where the "following item" is nothing. To avoid an NPE (or
        // whatever C has in place of them), we will manually switch the "last" item into nothing, and then spit the cart out
        if (strcmp(cart.cartItems[i + 1].itemName, "none") == 0)
        {
            strcpy(cart.cartItems[i].itemName, "none");
            strcpy(cart.cartItems[i].itemDescription, "none");
            cart.cartItems[i].itemPrice = 0;
            cart.cartItems[i].itemQuantity = 0;
            break;
        }

        // Regular execution of loop
        strcpy(cart.cartItems[i].itemName, cart.cartItems[i + 1].itemName);
        strcpy(cart.cartItems[i].itemDescription, cart.cartItems[i + 1].itemDescription);
        cart.cartItems[i].itemPrice = cart.cartItems[i + 1].itemPrice;
        cart.cartItems[i].itemQuantity = cart.cartItems[i + 1].itemQuantity;
    }

    printf("Item removed!\n");
    return cart;
}

// Well, after that monstrosity, this should be blessedly easier
int GetNumItemsInCart(ShoppingCart cart)
{
    int grandTotal = 0;

    // Safety: If someone runs this command on an empty cart
    if (cart.cartSize == 0)
    {
        return 0;
    }

    // Ugh, I wish I was as sure doing this in C as I am in Java
    for (int i = 0; i < MAX_CART_SIZE; i++)
    {

        // So! Start at 0, grab that quantity and add it to the running total
        grandTotal += cart.cartItems[i].itemQuantity;

        // Safety: similar to REMOVE, if the item at i+1 is nothing, there's nothing left to tally, so break the loop
        if (strcmp(cart.cartItems[i + 1].itemName, "none") == 0)
        {
            continue;
        }
    }

    return grandTotal;
}

// This should basically work exactly like the thing above, we just have one more step (multiplying cost by quantity)
int GetCostOfCart(ShoppingCart cart)
{

    int totalCost = 0;

    // Safety: empty cart
    if (cart.cartSize == 0)
    {
        return 0;
    }

    for (int i = 0; i < MAX_CART_SIZE; i++)
    {
        totalCost += (cart.cartItems[i].itemPrice * cart.cartItems[i].itemQuantity);

        // The usual escape clause
        if (strcmp(cart.cartItems[i].itemName, "none") == 0)
        {
            continue;
        }
    }
    return totalCost;
}

// Exactly what it sounds like
void PrintInvoice(ShoppingCart cart)
{
    printf("%s's shopping cart - %s:\n", cart.customerName, cart.currentDate);
    printf("Total number of items: %d\n", GetNumItemsInCart(cart));

    // Loop through, blah blah blah, print desc's and costs, the uszh
    for (int i = 0; i < MAX_CART_SIZE; i++)
    {

        // Safety; gotta put this in the loop this time because the function is VOID
        if (cart.cartSize == 0)
        {
            printf("Your cart is empty; there is no invoice.\n");
            break;
        }

        // Skip empty entries?
        if (strcmp(cart.cartItems[i].itemName, "none") == 0)
        {
            continue;
        }

        printf("\n");
        PrintItemCost(cart.cartItems[i]);

        
    }

    // Offload accumulation to function
    printf("\nTotal cost: $%d\n", GetCostOfCart(cart));
}

// Basically the old zrp-and-flrp
void PrintDescriptions(ShoppingCart cart)
{

    // Formatting, or what passes for it
    printf("%s's shopping cart, %s:\n\n", cart.customerName, cart.currentDate);
    printf("Item Descriptions:\n");

    // Again
    for (int i = 0; i < MAX_CART_SIZE; i++)
    {
        if (strcmp(cart.cartItems[i].itemName, "none") == 0) {
            continue;
        }

        // See above
        if (cart.cartSize == 0)
        {
            printf("Your cart is empty; there are no descriptions to print.\n");
            break;
        }

        PrintItemDescription(cart.cartItems[i]);
        printf("\n");
    }
}