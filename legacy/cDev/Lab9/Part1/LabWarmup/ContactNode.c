/* CS-253 Lab 09 Warmup
* Author: James Crowell
* Date: 10/17/2021
* Description: An introductory assignment to pointers and linked lists in C
*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "ContactNode.h"

ContactNode* CreateContactNode(char name[], char phoneNum[]) {

   ContactNode * thisNode = (ContactNode *) malloc(sizeof(ContactNode));

   if (thisNode == NULL) {
      return NULL;
   }

   strncpy (thisNode->contactName, name, CONTACT_FIELD_SIZE);
   strncpy (thisNode->contactPhoneNum, phoneNum, CONTACT_FIELD_SIZE);
   thisNode->nextNodePtr = NULL;

   return thisNode;
}

int InsertContactAfter(ContactNode* nodeInList, ContactNode* newNode) {
   ContactNode* tmpNext = NULL;

   if (nodeInList == NULL || newNode == NULL) {
      return -1;
   }

   tmpNext = nodeInList->nextNodePtr;
   nodeInList->nextNodePtr = newNode;
   newNode->nextNodePtr = tmpNext;

   return 0;
}

ContactNode* GetNextContact (ContactNode* nodeInList) {
   if (nodeInList == NULL) {
      return NULL;
   }

   return nodeInList->nextNodePtr;
}

void PrintContactNode (ContactNode* thisNode) {
   if (thisNode == NULL) {
      return;
   }

   printf("Name: %s\n", thisNode->contactName);
   printf("Phone number: %s\n", thisNode->contactPhoneNum);

   return;
}

void DestroyContactNode (ContactNode* thisNode) {
   if (thisNode == NULL) {
      return;
   }
   free(thisNode);
}

