#include<stdio.h>
#include <string.h>
#include <ctype.h>

char PrintMenu(const char usrStr[]) {

   char selection;
   int onScreen = 1;
   printf("MENU\n");
   printf("c - Number of Non-Whitespace Characters\n");
   printf("w - Number of Words\n");
   printf("f - Fix Capitalization\n");
   printf("r - Replace all !'s\n");
   printf("s - Shorten Spaces\n");
   printf("q - Quit\n");

   
   scanf(" %c", &selection);
   
   return selection;

}

int GetNumOfNonWSCharacters(const char usrStr[]) {
   int nWSChars = 0;
         for (int i = 0; i < strlen(usrStr); i++) {
            if (usrStr[i] != ' ' && usrStr[i] != '\t' && usrStr[i] != '\n') {
               nWSChars++;
            }
         }
   return nWSChars;
}

int GetNumOfWords(const char usrStr[]) {
   int wordCount = 0;
   char initialCharacter = usrStr[0];
   if (initialCharacter != '\n') {
      wordCount = 1;
   }
   for (int i = 0; i < strlen(usrStr); i++) {
      if (usrStr[i] == ' ' || usrStr[i] == '.' || usrStr[i] == '?' || usrStr[i] == '!') {
         while (usrStr[i+1] == ' ' || usrStr[i+1] == '.' || usrStr[i] == '?' || usrStr[i] == '!') {
            i++;
         }
         wordCount++;
      }
   }
   return wordCount;
}

void FixCapitalization(char usrStr[]) {
   // Initial characters are easy to check
   for (int i = 0; i < strlen(usrStr); i++) {
      if (i == 0 && usrStr[i] >= 'a' && usrStr[i] <= 'z') {
         usrStr[i] = usrStr[i] - 32;         
      }

      // Stab: if the character two places back is a punctuation (because the character
      // ONE place back should be a space), then this character should be uppercase
      if (usrStr[i] == '.' || usrStr[i] == '?' || usrStr[i] == '!') {
         
         // Disregard spaces, no matter the number
         while (usrStr[i + 1] == ' ') {
            i++;
         }
         if (usrStr[i+1] >= 'a' && usrStr[i+1] <= 'z') {
            usrStr[i+1] -= 32;
         }

         // That actually worked. I'll be damned.
      }
   }
}

void ReplaceExclamation(char usrStr[]) {
   for (int i = 0; i < strlen(usrStr); i++) {
      if (usrStr[i] == '!') {
         usrStr[i] = '.';
      }
   }
}

// This was inexcusably annoying. Why do so many of these assignments center around using C for things
// that C explicitly sucks at? No one uses C for string manipulation, which is supported by the fact that
// every time I go looking on the net for solutions to problems I'm having relating to challenges in this
// class, the fixes are exclusively for other languages; most often C++. I'm really tired of learning to use
// this language for something which no one who uses it, uses it FOR
void ShortenSpace(char usrStr[]) {

   int i = 0;
   int j = 0;
   while (usrStr[i] != '\0') {
      if (usrStr[i] == ' ') {
         j = i;
         while (usrStr[j+1] == ' ') {
            j++;
         }
         strcpy(&usrStr[i], &usrStr[j]);
      }
      i++;
   }


   //  char* d = s;
   // //  if (*d == ' ') {
   //  do {
   //      while (*d == ' ') {
   //          ++d;
   //      }
        
   //  } while (*s++ = *d++);
// }





// int i = 0;
//    int j = 0;
//    char edited[strlen(usrStr)];

//    while (usrStr[i] != '\0') {
//       if (usrStr[i] != ' ') {
//          edited[j] = usrStr[i];
//          j++;
//       }
//       else {
//          if (edited[j-1] != ' ') {
//             edited[j] = ' ';
//             j++;
//          }
//       }
//    }
//    i++;

//    memset(usrStr, 0, strlen(usrStr));
//    strcpy(usrStr, edited);
}



int main(void) {

   const int MAXLENGTH = 256;
   char userString[MAXLENGTH];

   printf("Enter a sample text:\n");
   fgets(userString, MAXLENGTH, stdin);

   printf("\nYou entered: %s\n", userString);

   int onScreen = 1;

   while (onScreen) {
   char choice = PrintMenu(userString);

   // Let's do it again
   switch (choice) {

      case 'c' :
         printf("Number of Non-Whitespace Characters chosen\n");         
         // Ooh, if this works, we're getting good and concise, aren't we?
         printf("Your sentence contains %d non-whitespace characters\n", GetNumOfNonWSCharacters(userString));
         break;

      
      case 'w' :
         printf("Number of Words chosen\n");
         printf("Your sentence contains %d words.\n", GetNumOfWords(userString));
         break;

      case 'f' :
         printf("Fix Capitalization chosen\n");
         FixCapitalization(userString);
         printf("Edited string: %s\n", userString);
         break;

      case 'r' :
         printf("Replace All !\'s chosen\n");
         ReplaceExclamation(userString);
         printf("Edited string: %s\n", userString);
         break;

      case 's' :
         printf("Shorten Spaces chosen\n");
         ShortenSpace(userString);
         printf("Edited string: %s\n", userString);
         break;      

      case 'q' :
         onScreen = 0;         
         break;

      default : 
         printf("\nError: Unknown option selected\n");
         break;


   }

   }

   return 0;
}