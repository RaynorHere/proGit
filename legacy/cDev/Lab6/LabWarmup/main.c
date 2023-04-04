#include <stdio.h>
#include <ctype.h>

int main(void) {

   char userInput[100];
   double teamWeights[5];

   // Grab 'em
   for (int i = 0; i < 5; i++) {
      // Input checker
      int inputOkay = 0;
    
         // This took longer than I'd have liked. Heh
         while (inputOkay == 0 || teamWeights[i] < 0) {
         printf("Enter weight %d\n", i + 1);
         inputOkay = scanf("%lf", &teamWeights[i]);
         while (getchar() != '\n');
      }

   }

   // Print 'em
   printf("You entered: ");
   for (int i = 0; i < 5; i++) {
      printf("%0.2lf ", teamWeights[i]);
   }
   printf("\n");

   // Summation
   double weightSum = 0.0;
   for (int i = 0; i < 5; i++) {
      weightSum += teamWeights[i];
   }

   // Average
   double weightAvg = weightSum / 5.0;

   

   // Find max value in array
   double weightMax = 0.0;
   for (int i = 0; i < 5; i++) {
      if (teamWeights[i] > weightMax) {
         weightMax = teamWeights[i];
      }
   }

   // Output metastats
   printf("Total weight: %0.2lf\n" , weightSum);
   printf("Average weight: %0.2lf\n", weightAvg);
   printf("Max weight: %0.2lf\n", weightMax);

   return 0;
}