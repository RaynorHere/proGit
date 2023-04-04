#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "ShoppingCart.h"
#include "ItemToPurchase.h"

/*
*   Online Shopping Cart Program
*   author: James Crowell
*   date: 10/13/2021
*/

// Sweet black flaming Sabbath, this one just keeps going

char customerNameIn[50];
char customerDateIn[50];

void BufferPurge(void)
{
    char c = getchar();
    while (c != '\n' && c != EOF)
    {
        c = getchar();
    }
}

    


int main(void)
{
    printf("What is your name?\n");
    fgets(customerNameIn, 49, stdin);
    int newLineMarker = strlen(customerNameIn);

    // This is the only reliable function I could find to write to make sure the newline character isn't being included as
    // part of the customer's name. I'm super impressed this worked
    if (newLineMarker > 0 && customerNameIn[newLineMarker -1] == '\n') {
        customerNameIn[newLineMarker - 1] = '\0';
    }
    // BufferPurge();
    
    printf("What is today's date?\n");
    printf("Note: expected format is [month full name] [day], [year]\n");    
    fgets(customerDateIn, 49, stdin);

    // I'm gonna do that newline killer again. I suspect I will need it for later, similar functions, too
    newLineMarker = strlen(customerDateIn);
    if (newLineMarker > 0 && customerDateIn[newLineMarker -1] == '\n') {
        customerDateIn[newLineMarker - 1] = '\0';
    }
    
    // BufferPurge();

    // Set up cart? This is bizarre without constructors, which I'm sure is the point
    ShoppingCart userCart;
    strcpy(userCart.customerName, customerNameIn);
    strcpy(userCart.currentDate, customerDateIn);
    userCart.cartSize = 0;

    // I dunno if this'll work, and I'm sure it's wasteful of memory, but I can't think of any other way to initialize the cart, really,
    // and if I made all my escape clauses correctly, the front end should never show blank items
    for (int i = 0; i < MAX_CART_SIZE; i++)
    {

        // Had to take several stabs at this and it only worked when I added the &. Chuffing buggercripes knows if it's even going to work
        MakeItemBlank(&userCart.cartItems[i]);
    }

    // Now we can move on to user input? I think?
    int onScreen = 1;
    char userChoiceIn[5];
    char userChoice;
    ItemToPurchase newItem;
    char newItemName[MAXNAMELENGTH];
    char newItemDescription[MAXNAMELENGTH];
    char itemToPull[MAXNAMELENGTH];
    int initCheck = 0;

    
    

    while (onScreen)
    {
    printf("\nPROGRAM MENU\n");
    printf("a - Add Item to Cart\n");
    printf("r - Remove Item From Cart\n");
    printf("i - Output Item Descriptions\n");
    printf("o - Output Shopping Cart\n");
    printf("q - Quit\n");
    printf("Choose an option:\n");
    

    // I am having a HELL of a time with having to hit enter twice or make a selection twice, or having the next prompt get skipped and it's
    // SO EFFING ANNOYING; I'm going to try this as a workaround. Why does C suck so much at this?
    fgets(userChoiceIn, 4, stdin);
    userChoice = userChoiceIn[0];

        switch (userChoice)
        {
            case 'a' :
                // Ingredients
                
                
                // I do not expect to ever run into this
                initCheck = MakeItemBlank(&newItem);
                if (initCheck < 0) {
                    printf("No dice.");
                    exit(1);
               }
           
                printf("Add Item to Cart chosen.\n");
                printf("Enter item name:\n");
                fgets(newItemName, 79, stdin);
                newLineMarker = strlen(newItemName);
                if (newLineMarker > 0 && newItemName[newLineMarker -1] == '\n') {
                newItemName[newLineMarker - 1] = '\0';
            }
                printf("Enter item description:\n");
                fgets(newItemDescription, 79, stdin);
                newLineMarker = strlen(newItemDescription);
                if (newLineMarker > 0 && newItemDescription[newLineMarker -1] == '\n') {
                newItemDescription[newLineMarker - 1] = '\0';
            }
                printf("Enter the item price:\n");
                scanf("%d", &newItem.itemPrice);
                // I can't remember if scanf on digits needs a purge but I'd prefer not to risk it
                // BufferPurge();

                printf("Enter item quantity:\n");
                scanf("%d", &newItem.itemQuantity);
                // BufferPurge();

                // Insert values
                strcpy(newItem.itemName, newItemName);
                strcpy(newItem.itemDescription, newItemDescription);
                // newItem.itemPrice = newItemPrice;
                // newItem.itemQuantity = newItemQuantity;                

                // Add item
                userCart = AddItem(newItem, userCart);

                break;

            case 'r' :

                
                printf("\nRemove Item From Cart chosen.\n");
                printf("Enter name of item to remove:\n");
                fgets(itemToPull, 79, stdin);
                newLineMarker = strlen(itemToPull);
                if (newLineMarker > 0 && itemToPull[newLineMarker -1] == '\n') {
                itemToPull[newLineMarker - 1] = '\0';
            }

                userCart = RemoveItem(itemToPull, userCart);

                break;

            case 'i' :

                printf("\nOutput Item Descriptions chosen.\n");
                PrintDescriptions(userCart);
                break;

            case 'o' :

                printf("\nOutput Shopping Cart chosen.\n");
                PrintInvoice(userCart);
                break;

            case 'q' :
                onScreen = 0;
                break;
            
            
                
        }
    }
}
