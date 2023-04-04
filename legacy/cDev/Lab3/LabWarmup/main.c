#include <stdio.h>
#include <string.h>

int main(void) {

   /* So, funny story: the "Activity" header on the readme.me was small
    * enough that I didn't even see it as I was working on the warmup, so
    * I accidentally did the entire lab activity IN the warmup. but I cut
    * that code and pasted it over, so I'm going to just rebuild the warmup
    * like, halfway, because the warmup was basically half the activity
   */

  char userIn[100];
  printf("Garage Services: \n\n");
  printf("Oil Change: $35\n");
  printf("Tire Rotation: $19\n");
  printf("Car Wash: $7\n\n");

  printf("Please state your requested auto service:\n");
  printf("Note: Match input with menu exactly; case AND space\n");
  fgets(userIn, 100, stdin);
  userIn[strcspn(userIn, "\n")] = 0;

  if (strcmp(userIn, "Oil Change") != 0 && strcmp(userIn, "Tire Rotation") != 0 
  && strcmp(userIn, "Car Wash") != 0) {
      printf("Error: Requested service is not recognized\n");
      return 1;
  }
  else {
     printf("You entered: %s\n", userIn);
     
     if (strcmp(userIn, "Oil Change") == 0) {
        printf("Cost of oil change: $35");
     }
     else if (strcmp(userIn, "Tire Rotation") == 0) {
        printf("Cost of tire rotation: $19");
     }
     else if (strcmp(userIn, "Car Wash") == 0) {
        printf("Cost of car wash: $7");
     }
  }


   return 0;
}