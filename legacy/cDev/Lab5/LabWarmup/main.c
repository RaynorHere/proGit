/* 
*  Author: James "JCIII" Crowell
*  Date: 9/24/21
*  Description: Accept a char and number value, ensure integer, draw triangle
*/
#include <stdio.h>

int main(void) {
   char triangleChar;
   int triangleHeight;
   int outerCount, innerCount;
   int heightTest = 0;

   printf("Enter a character:\n");
   scanf("%c", &triangleChar);
   
   printf("Enter triangle height:\n");
   heightTest = scanf("%d", &triangleHeight);
   printf("\n");

   // Height check 1: Ensure numerical input
   if (heightTest == 0) {
      printf("Error: Unrecognized input, please enter only integer values.\n");
      return 1;
   }

   // Type check 2 (non-integer numericals) appears unnecessary
   // %d input appears to just make input integer
       
   for (outerCount = 0; outerCount < triangleHeight; outerCount++) {
      
      for (innerCount = 0; innerCount <= outerCount; innerCount++) {
         printf("%c ", triangleChar);
      }
      printf("\n");
   }
   
   return 0;
}