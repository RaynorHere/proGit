#include <stdio.h>
#include <math.h>

int main(void) {
   int userNum;
   
   printf("Enter integer:\n");
   scanf("%d", &userNum);
   
   // Confirmation
   printf("The number entered is %d \n", userNum);

   // Square it
   int squareUserNum = userNum * userNum;

   // Cube it
   int cubeUserNum = userNum * userNum * userNum;

   // Experiment: can carat numbers for arithmetic?
   // int cubeUserNumExp = userNum^3;

   // Apparently not; C has a dedicated function
   // int cubeUserNumExp = pow(userNum, 3); 
   // Returns 124. Works like random in java (you punch in nextInt(125) 
   // and it gives you a random value between 0 & 124)?

   // int cubeUserNumExp = pow(userNum, 3) +1;

   // 5 -> 125; 16 -> 4097 (Exp: 4096); 8 -> 513 (Exp: 512); 
   // What the Hell? .....Odds? Odds are low? Evens are where they should
   // be, and the +1 throws them off?

   // 7 -> 344 (Exp: 343); 11 -> 1332 (Exp: 1331) .....5's? 5's are the problem?

   // 10 -> 1001 (Exp: 1000); 15 -> 3376 (Exp: 3375); This is profoundly weird 

   /* Okay, this has to be some floating-point math nonsense; I know computers
   *  calculate math differently than we do, and 5 specifically is probably
   *  returning some 124.999999 nonsense which is flooring to 124. I don't 
   *  understand it well enough to figure out a foolproof way to fix it, and 
   *  this is turning the warm-up into a much longer assignment. I'm going to 
   *  skip it for now, but might come back to this later; it's very interesting
   */

   // Output square
   printf("%d squared is %d \n", userNum, squareUserNum);

   // Output cube
   printf("%d cubed is %d \n", userNum, cubeUserNum);

   printf("Input a new integer: \n");

   int userDigit2;
   scanf("%d", &userDigit2);

   // Find sum
   int summation = userDigit2 + userNum;

   // Find product
   int product = userDigit2 * userNum;

   // Output again
   printf("%d + %d is %d\n", userNum, userDigit2, summation);
   printf("%d * %d is %d", userNum, userDigit2, product);

   return 0;
}