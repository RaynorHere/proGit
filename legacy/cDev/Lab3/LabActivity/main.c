#include <stdio.h>
#include <string.h>

int main(void) {
 printf("Raynor's Garage List of Services: \n\n");
   printf("Oil Change: $35\n");
   printf("Tire Rotation: $19\n");
   printf("Car Wash: $7\n");
   printf("Car Wax: $12\n\n");

   char userResponse1[200];
   char userResponse2[200];
   
   printf("Please choose a service \n");
   printf("(Note: Entry is case-sensitive and spaces matter!):\n");
   // I find the regular way C does strings reprehensibly annoying
   // when using scan-derived commands, so I've gotten cozy with the
   // fgets() function lately. As far as I can tell, this fixes the
   // "if the user exceeds max character input" problem. You can enter 
   // many characters as you like into this entry; it only takes 40
   fgets(userResponse1, 40, stdin);

   // I have no idea what this does or how it does it
   // This is the final attempt I'm making at making
   // strcmp work with input strings. What a nightmare
   userResponse1[strcspn(userResponse1, "\n")] = 0;
   

   printf("Understood. And your second service?\n");
   fgets(userResponse2, 40, stdin);
   userResponse2[strcspn(userResponse2, "\n")] = 0;
   
   char option1[] = "Oil Change";
   char option2[] = "Tire Rotation";
   char option3[] = "Car Wash";
   char option4[] = "Car Wax";
   char option5[] = "-";

   int oilCost = 35;
   int rotateCost = 19;
   int washCost = 7;
   int waxCost = 12;

   int cost1 = 0;
   int cost2 = 0;

   // Wow, I can't believe how annoying this is. No boolean
   // variables, the comparison capabilities (such as, in a 
   // switch setup) are pathetic; setting up branching paths,
   // particularly predicated on STRINGS in this language
   // COMPLETELY sucks
if (strcmp(userResponse1, option1) == 0) {
      cost1 = oilCost;
   }
   else if (strcmp(userResponse1, option2) == 0) {
      cost1 = rotateCost;
   }
   else if (strcmp(userResponse1, option3) == 0) {
      cost1 = washCost;
   }
   else if (strcmp(userResponse1, option4) == 0) {
      cost1 = waxCost;
   }

   else if(strcmp(userResponse1, option5) == 0) {
      cost1 = 0;
   }
   else {
      printf("Error: Requested service not recognized");
      return 1;
   }

if (strcmp(userResponse2, option1) == 0) {
      cost2 = oilCost;
   }
   else if (strcmp(userResponse2, option2) == 0) {
      cost2 = rotateCost;
   }
   else if (strcmp(userResponse2, option3) == 0) {
      cost2 = washCost;
   }
   else if (strcmp(userResponse2, option4) == 0) {
      cost2 = waxCost;
   }

   else if(strcmp(userResponse2, option5) == 0) {
      cost2 = 0;
   }
   else {
      printf("Error: Requested service not recognized");
      return 1;
   }

   printf("\n\n Raynor's Garage \n\n");
   if (strcmp(userResponse1, option5) == 0) {
      printf("Service 1: No Service\n");
   }
   else {
      printf("Service 1: %s, $%d\n", userResponse1, cost1);
   }

   if (strcmp(userResponse2, option5) == 0) {
      printf("Service 2: No Service\n");
   }
   else {
      printf("Service 2: %s, $%d\n", userResponse2, cost2);
   }
   int total = cost1 + cost2;
   printf("Total cost: $%d", total);
   

   return 0;
}