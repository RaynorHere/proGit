#ifndef ITEM_TO_PURCHASE_H
#define ITEM_TO_PURCHASE_H

#define MAXNAMELENGTH 80

struct ItemToPurchase {
    char itemName[MAXNAMELENGTH];
    int itemPrice;
    int itemQuantity;
};

/* Definition of new type */
typedef struct ItemToPurchase ItemToPurchase;

/*  Initializer/data clear function
*   item - Pointer to ITP obj
*   return 0 (good) or -1 (bad)
*/
int MakeItemBlank(ItemToPurchase * itemPointer);

/* Display cost function; prints to stdout 
*   item - ITP object (NOT pointer) to display
*/
void PrintItemCost(ItemToPurchase item);

#endif