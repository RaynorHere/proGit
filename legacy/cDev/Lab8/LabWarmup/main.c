#include<stdio.h>
#include<string.h>
#include<stdlib.h>

#include "ItemToPurchase.h"


void BufferPurge (void) {
   char c = getchar();
   while (c != '\n' && c != EOF) {
      c = getchar();
   }
}

int main(void) {
 
   int rc = 0;
   ItemToPurchase itemA, itemB;

   rc = MakeItemBlank(&itemA);
   if (rc < 0) {
      printf("Error: Initialization failure.\n");
      exit(1);
   }

   rc = MakeItemBlank(&itemB);
   if (rc < 0) {
      printf("Error: Initialization failure.\n");
      exit(1);
   }


   // Set up item 1
   printf("Please enter item 1's name:\n");
   scanf("%79[^\n]s", itemA.itemName);
   BufferPurge();

   printf("Enter the item's price\n");
   scanf("%d", &itemA.itemPrice);
   printf("Enter the item quantity.\n");
   scanf("%d", &itemA.itemQuantity);

   printf("\n");
   PrintItemCost(itemA);

   // Clean out
   BufferPurge();


   // Item 2
   printf("\nPlease enter item 2's name:\n");
   scanf("%79[^\n]s", itemB.itemName);
   BufferPurge();

   printf("Enter the item's price\n");
   scanf("%d", &itemB.itemPrice);
   printf("Enter the item quantity.\n");
   scanf("%dd", &itemB.itemQuantity);

   printf("\n");
   PrintItemCost(itemB);

   int totalCost = (itemA.itemQuantity * itemA.itemPrice) + (itemB.itemQuantity * itemB.itemPrice);

   printf("\n\nTOTAL COST\n");
   PrintItemCost(itemA); printf(" + "); PrintItemCost(itemB); printf("\n");
   printf("Total: $%d\n", totalCost);

   
   return 0;
}
