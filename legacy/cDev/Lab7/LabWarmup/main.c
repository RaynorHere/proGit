#include<stdio.h>
#include <string.h>

//Returns the number of characters in usrStr
int GetNumOfCharacters(const char usrStr[]) {

   // This is confirmed to work; every string I put in is coming out as long as I expect it to.
   int theDeets = 0;

   int i = 0;
   while (usrStr[i] != '\0') {
      theDeets++;
      i++;
   }
   return theDeets;   
}

void OutputWithoutWhitespace(const char usrStr[]) {
   for (int i = 0; i < strlen(usrStr); i++) {
      if (usrStr[i] != ' ' && usrStr[i] != '\t') {
         printf("%c", usrStr[i]);
      }
   }

}

int main(void) {

   const int MAXLENGTH = 50;
   char userString[MAXLENGTH];

   printf("Please enter a sentence or phrase\n");

   fgets(userString, MAXLENGTH, stdin);

   // Far as I can tell, this wipes the newline character out of the passed string
   // char *newlinePosition;
   // if ((newlinePosition = strchr(userString, '\n')) != NULL) {
   //    *newlinePosition = '\0';
   // }   

   printf("\nYou entered: %s\n", userString);

   int strPop = GetNumOfCharacters(userString);

   printf("The number of characters in the string you passed is %d\n", strPop);
   printf("Your string printed without any spaces looks like: ");
   OutputWithoutWhitespace(userString);

   return 0;
}