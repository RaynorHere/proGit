#include<stdio.h>
#include<string.h>

#include "ItemToPurchase.h"

int MakeItemBlank (ItemToPurchase * itemPointer) {
    if (itemPointer == NULL) {
        return -1;
    }

    strcpy(itemPointer->itemName, "none");
    strcpy(itemPointer->itemDescription, "none");
    itemPointer->itemPrice = 0;
    itemPointer->itemQuantity = 0;

    return 0;
}

void PrintItemCost (ItemToPurchase item) {
    printf("%s %d @ $%d = $%d", item.itemName, item.itemQuantity, item.itemPrice,
                                (item.itemPrice * item.itemQuantity));
}

void PrintItemDescription(ItemToPurchase item) {
    printf("%s: %s.", item.itemName, item.itemDescription);
}