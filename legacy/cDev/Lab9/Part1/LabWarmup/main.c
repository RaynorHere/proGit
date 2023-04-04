#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "ContactNode.h"

void NewLineTrim(char *line);

int main(void)
{

   char fullName[50] = "";
   char phoneNum[50] = "";
   int rc;
   const int MAX_CONTACTS = 4;

   ContactNode *headNode = NULL;
   ContactNode *tailNode = NULL;
   ContactNode *currentNode = NULL;

   for (int i = 1; i < MAX_CONTACTS; i++)
   {

      printf("Person %d\n", i);
      printf("Enter name:\n");
      fgets(fullName, 50, stdin);
      NewLineTrim(fullName);

      printf("Enter phone number:\n");
      fgets(phoneNum, 50, stdin);
      NewLineTrim(phoneNum);

      printf("You entered: %s, %s\n\n", fullName, phoneNum);

      currentNode = CreateContactNode(fullName, phoneNum);
      if (currentNode == NULL)
      {
         printf("Error: Unable to create node.\n");
         printf("Name: %s\n", fullName);
         printf("Phone number: %s\n", phoneNum);
         exit(1);
      }

      if (headNode == NULL)
      {
         headNode = currentNode;
         tailNode = currentNode;
      }
      else
      {
         rc = InsertContactAfter(tailNode, currentNode);

         if (rc < 0)
         {
            printf("Error: Unable to insert node into list.\n");
            PrintContactNode(currentNode);
            exit(1);
         }

         tailNode = currentNode;
      }
   }

   printf("CONTACT LIST\n");
   currentNode = headNode;
   while (currentNode != NULL)
   {
      PrintContactNode(currentNode);
      currentNode = GetNextContact(currentNode);
      printf("\n");
   }

   currentNode = headNode;
   ContactNode *nextNode;
   while (currentNode != NULL)
   {
      nextNode = GetNextContact(currentNode);
      DestroyContactNode(currentNode);
      currentNode = nextNode;
   }

   return 0;
}

void NewLineTrim(char *line)
{
   size_t lineLength;

   if (line == NULL)
   {
      return;
   }

   lineLength = strlen(line);
   if (line[lineLength - 1] == '\n')
   {
      line[lineLength - 1] = '\0';
   }
}