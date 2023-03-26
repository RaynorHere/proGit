#include <stdio.h>
#include <string.h>

int main(void) {

   // Getcher eggs and your flour and milk
   const int TOTALSIZE = 5;
   int jerseyArray[TOTALSIZE];
   int ratingArray[TOTALSIZE];
   int gateKeeper;



   for (int i = 0; i < TOTALSIZE; i++) {
      
      gateKeeper = 0;

      // Jersey add
      while (gateKeeper == 0 || jerseyArray[i] < 0 || jerseyArray[i] > 99) {

         printf("Enter player %d's jersey number:\n", i + 1);

         gateKeeper = scanf("%d", &jerseyArray[i]);

         while (getchar() != '\n');
      }

      // Reset
      gateKeeper = 0;

      // Rate add
      while (gateKeeper == 0 || ratingArray[i] < 1 || ratingArray[i] > 9) {
         
         printf("Enter player %d's rating:\n", i + 1);

         gateKeeper = scanf("%d", &ratingArray[i]);

         while (getchar() != '\n');
      }
   }

   // Spit back
   printf("ROSTER\n");
   for (int i = 0; i < TOTALSIZE; i++) {

      printf("Player %d --- Jersey number: %d, Rating: %d\n", i + 1, jerseyArray[i], ratingArray[i]);

   }

   // Space between
   printf("\n\n");

   // Ingredients
   char input;
   int onScreen = 1;
   // Menu section
   printf("MANAGEMENT MENU\n");
   printf("u - Update Player Rating\n");
   printf("a - Output Players Above a Rating\n");
   printf("r - Replace Player\n");
   printf("o - Output Roster\n");
   printf("q - Quit\n");
   printf("\n\nChoose an option (Note: menu *IS* case-sensitive)\n");

   while (onScreen) {
   scanf(" %c", &input);

   // Welcome to my world of fun!
   switch(input) {

      case 'u' :
         printf("\nUpdate Player Rating chosen.\n");
         
         gateKeeper = 0;
         int jerseyRequest;
         while (gateKeeper == 0 || jerseyRequest < 0 || jerseyRequest > 99) {
            printf("Enter a jersey number between 0 and 99:\n");
            gateKeeper = scanf("%d", &jerseyRequest);
            while (getchar() != '\n');
         }

         gateKeeper = 0;
         int newRate;
         while (gateKeeper == 0 || newRate < 1 || newRate > 9) {
            printf("Enter new rating for this player.\n");
            gateKeeper = scanf("%d", &newRate);
            while (getchar() != '\n');
         }

         // Ensures jersey passed is valid
         int existenceCheck = -1;

         for (int i = 0; i < TOTALSIZE; i++) {
            if (jerseyArray[i] == jerseyRequest) {
               // Unlocks "valid" check
               existenceCheck = i;
               // Update value
               ratingArray[i] = newRate;
               printf("Player rating successfully changed.\n");
            }
         }

         // If jersey number not in array
         if (existenceCheck == -1) {
            printf("Error: Nonexistent jersey entered. Check roster and try again.\n");
         }
         break;

      // Players above certain rank
      case 'a' :
         printf("Output Players Above a Rating chosen.\n");

         // Ingredients
         gateKeeper = 0;
         int threshold;
         
         // Accept a rating minimum
         while (gateKeeper == 0 || threshold < 0 || threshold > 9) {
            printf("Enter a threshold value between 0 and 9.\n");
            gateKeeper = scanf("%d", &threshold);
            while (getchar() != '\n');
         }

         gateKeeper = 0;

         printf("Players above rating level %d:\n", threshold);

         for (int i = 0; i < TOTALSIZE; i++) {
            if (ratingArray[i] > threshold) {
               printf("Player %d --- Jersey number: %d; Rating: %d\n", i+1, jerseyArray[i], ratingArray[i]);
            }
         }

         break;

      // Replace player
      case 'r' :
         printf("Replace Player chosen.\n");

         gateKeeper = 0;
         int oldJersey;
         int newJersey;
         int newRating;
         int existenceCheck2 = -1;
      
         while (gateKeeper == 0 || oldJersey < 0 || oldJersey > 99) {
            printf("Enter current jersey number:\n");
            gateKeeper = scanf("%d", &oldJersey);
            while(getchar() != '\n');
         }

         gateKeeper = 0;

         while (gateKeeper == 0 || newJersey < 0 || newJersey > 99) {
            printf("Enter new jersey number:\n");
            gateKeeper = scanf("%d", &newJersey);
            while (getchar() != '\n');
         }

         gateKeeper = 0;

         while (gateKeeper == 0 || newRating < 1 || newRating > 9) {
            printf("Enter a rating for the new player:\n");
            gateKeeper = scanf("%d", &newRating);
            while (getchar() != '\n');
         }

         gateKeeper = 0;
      
         for (int i = 0; i < TOTALSIZE; i++) {
            if (jerseyArray[i] == oldJersey) {
               existenceCheck2 = i;
               jerseyArray[i] = newJersey;
               ratingArray[i] = newRating;
            }
         }

         if (existenceCheck2 == -1) {
            printf("Error: nonexistent jersey passed. Check roster and try again.\n");
            break;
         }
         else {
            printf("Player replaced.\n");
            break;
         }

      // Roster out
      case 'o' :
         printf("Output roster selected.\n");
         for (int i = 0; i < TOTALSIZE; i++) {

            printf("Player %d --- Jersey number: %d, Rating: %d\n", i + 1, jerseyArray[i], ratingArray[i]);

         }

         break;

      // Quit
      case 'q' :
         onScreen = 0;
         break;

      default :
         printf("Error: Unknown option selected.\n\n");
         printf("MANAGEMENT MENU\n");
         printf("u - Update Player Rating\n");
         printf("a - Output Players Above a Rating\n");
         printf("r - Replace Player\n");
         printf("o - Output Roster\n");
         printf("q - Quit\n");
         printf("\n\nChoose an option (Note: menu *IS* case-sensitive)");
         break;


   }

}

   return 0;
}