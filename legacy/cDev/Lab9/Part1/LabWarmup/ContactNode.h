/* 
 * File: ContactNode.h
 * Author: Luke Hindman & zyBooks
 * Date: Thu 15 Oct 2020 03:40:47 PM PDT
 * Description: This is a modified version of the ContactNode lab activity
 *    provided by zyBooks.
 */
#ifndef __CONTACT_NODE_H
#define __CONTACT_NODE_H

#define CONTACT_FIELD_SIZE 50

struct ContactNode_struct {
   char contactName[CONTACT_FIELD_SIZE];
   char contactPhoneNum[CONTACT_FIELD_SIZE];
   struct ContactNode_struct* nextNodePtr;
};
typedef struct ContactNode_struct ContactNode;

/* CreateContactNode: Allocate a ContactNode object in the heap using malloc
 *    the object will be initialized with the specified name and phone number
 *    and the nextNodePtr field will be set to NULL;
 * name - NULL terminated string containing contact name
 * phoneNum - NULL terminated string containing contact phone number
 * returns - Pointer to ContactNode object allocated on the heap
 */
ContactNode* CreateContactNode(char name[], char phoneNumInit[]);

/* InsertContactAfter: Insert a new ConactNode into the linked list
 *    immediately after the specified node. 
 * nodeInList - Pointer to the ContactNode that the new ContactNode 
 *                  should be inserted after in the list
 * newNode - Pointer to the new ContactNode to be added
 * returns - 0 on success, -1 on error
 */
int InsertContactAfter(ContactNode* nodeInList, ContactNode* newNode);

/* GetNextContact: Return a pointer to the ContactNode that immediately
 *     follows the specified node in the list
 * nodeInList - Pointer to ContactNode that we want to get the next node of
 * returns - Pointer to next ContactNode, NULL on error or end of list
 */
ContactNode* GetNextContact(ContactNode* nodeInList);

/* PrintContactNode: Write the fields of the ContactNode, nicely formatted,
 *     to stdout (console) using printf. 
 * thisNode - Pointer to ContactNode object to be displayed */
void PrintContactNode(ContactNode* thisNode);

/* DestroyContactNode: Release memory allocated by malloc in the
 *    CreateContactNode function.  Does nothing if thisNode is NULL
 * thisNode - Pointer to ContactNode object to be freed.
 */
void DestroyContactNode(ContactNode* thisNode);

#endif /* __CONTACT_NODE_H */