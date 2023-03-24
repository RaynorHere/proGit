/*
* Author: James "Crowley" Crowell
* Date: 9/24/21
* Description: Accepts 3 integer values, ensures they are integers, ensures
*              arrow head width is greater than arrow base width, then draws
*              half an hour pointing down using nested loops
*
*/
#include <stdio.h>

int main(void) {
   int arrowBaseHeight;
   int arrowBaseWidth;
   int arrowHeadWidth;
   int inputChecker = 0;
   
   printf("Enter arrow base height:\n");
   inputChecker = scanf("%d", &arrowBaseHeight);

   // Input test one
   if (inputChecker == 0) {
      printf("Error: Unrecognized input. Please only supply integer values.");
      return 1;
   }

   // Reset checker variable
   inputChecker = 0;
   
   printf("Enter arrow base width:\n");
   inputChecker = scanf("%d", &arrowBaseWidth);

   // Input check two
   if (inputChecker == 0) {
      printf("Error: Unrecognized input. Please only supply integer values.");
      return 1;
   }

   inputChecker = 0;
   
   printf("Enter arrow head width:\n");
   inputChecker = scanf("%d", &arrowHeadWidth);
   printf("\n");

   // Input check three
   if (inputChecker == 0) {
      printf("Error: Unrecognized input. Please only supply integer values.");
      return 1;
   }

   inputChecker = 0;
   
   while (arrowHeadWidth <= arrowBaseWidth) {
      printf("Error: Arrow head width must be greater than arrow base width.\n");
      printf("Please enter acceptable arrow head width:\n");
      inputChecker = scanf("%d", &arrowHeadWidth);

      // Final input check
      if (inputChecker == 0) {
         printf("Error: Unrecognized input. Please only supply integer values.");
         return 1;
   }
      printf("\n");
   }

   // Draw arrow base
   for (int i = 0; i < arrowBaseHeight; i++) {

      for (int j = 0; j < arrowBaseWidth; j++) {
         printf("*");
      }

      printf("\n");
   }

   for (int i = arrowHeadWidth; i > 0; i--) {

      for (int j = i; j > 0; j--) {
         printf("*");
      }
      printf("\n");
   }

    
   return 0;
}