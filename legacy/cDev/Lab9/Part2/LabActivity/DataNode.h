/* 
 * File: DataNode.h
 * Author: Luke Hindman & zyBooks
 * Date: Mon 19 Oct 2020 09:56:11 AM PDT
 * Description: This is a generic list node that is
 *    used to store string data
 */

#ifndef __DATA_NODE_H
#define __DATA_NODE_H

#include <stdio.h>

struct DataNode_struct {
   char * dataValue;
   size_t dataSize;
   struct DataNode_struct* nextNodePtr;
};
typedef struct DataNode_struct DataNode;

/* CreateDataNode: Allocate a DataNode in the heap using malloc
 *    the dataValue will be allocated on the heap using malloc with sufficient
 *    size to store the string data passed in as a parameter. 
 *    dataValue will be initialized with a copy of the string data parameter
 *    dataSize will be set to the number of bytes allocated by malloc for dataValue.
 * data - Pointer to null terminated string of characters
 * 
 * returns - Pointer to DataNode allocated on the heap, NULL on error
 */
DataNode* CreateDataNode(const char data[]);

/* InsertDataNodeAfter: Insert a new DataNode into the linked list
 *    immediately after the specified node. 
 * nodeInList - Pointer to the DataNode that the new DataNode 
 *                  should be inserted after in the list
 * newNode - Pointer to the new DataNode to be added
 * returns - 0 on success, -1 on error
 */
int InsertDataNodeAfter(DataNode* nodeInList, DataNode* newNode);

/* SetNextDataNode: Update the nextNodePtr field of the specified nodeInList 
 *     to point to newNode.  It is valid for newNode to be NULL, but not nodeInList.
 * nodeInList - Pointer to DataNode whose nextNodePtr field will be updated
 * newNode - Pointer DataNode to be set in the nextNodePtr
 * returns - 0 on success, -1 on error
 */
int SetNextDataNode(DataNode* nodeInList, DataNode* newNode);

/* GetNextDataNode: Return a pointer to the DataNode that immediately
 *     follows the specified node in the list
 * nodeInList - Pointer to DataNode that we want to get the next node of
 * returns - Pointer to next DataNode, NULL on error or end of list
 */
DataNode* GetNextDataNode(DataNode* nodeInList);

/* PrintDataNode: Write the dataValue of the DataNode, 
 *     to stdout (console) using printf. 
 * thisNode - Pointer to DataNode object to be displayed */
void PrintDataNode(DataNode* thisNode);

/* DestroyDataNode: Release memory allocated by malloc in the
 *    CreateDataNode function.  Does nothing if thisNode is NULL
 * thisNode - Pointer to DataNode object to be freed.
 */
void DestroyDataNode(DataNode* thisNode);

/* BuildDataList: Construct a Linked List of DataNodes from
 *    an array of strings. This function should call the CreateDataNode()
 *    function to allocate a DataNode for each string in the
 *    data array. It should hold a pointer to the listHead and listTail 
 *    and use the InsertDataNodeAfter funtion to build the list
 *    itself.  
 * 
 *  The order of the items in the list should correspond to the
 *    order of the elements in the array.  For example, the listHead
 *    should contain the dataValue that corresponds to data[0] and 
 *    listTail should contain the dataValue that cooresponds
 *    to data[numElements-1].
 * 
 * Once the list has been constructed, the listHead pointer should be 
 *    returned to the caller, NULL should be returned on an error.
 * data - Array of NULL terminiated strings
 * numElements - The number of string elements in data
 * returns - DataNode pointer to listHead, NULL if error occurred
 */
DataNode* BuildDataList(char * data[], int numElements);

/* GetDataListSize: Iterate through the list and count the
 *   number of DataNodes in the list. Remember that we know
 *   we've reached the last node of the list when nextNodePtr
 *   is NULL. Return this count to the caller or -1 if listHead
 *   is NULL.
 * listHead - DataNode pointer at the head of the list
 * returns - The number of DataNodes in the list, -1 on error
 */
int GetDataListSize(DataNode* listHead);

/* PrintDataList: Iterate through the list and print the
 *   dataValue of each node to stdout in a comma separate list.
 *   The last element of the list should not have a trailing
 *   comma.  For Example: 
 * 
 *        mansion, apartment, shack, house
 * 
 *   Function should silently return if listHead is NULL
 * listHead - DataNode pointer at the head of the list
 */
void PrintDataList(DataNode *listHead);

/* GetRandomDataNode: Use the GetDataListSize function in combination
 *   with the rand() function to randomly select a DataNode
 *   from the list. Return a pointer to the DataNode to the caller, NULL on an error.
 *
 * listHead - DataNode pointer at the head of the list
 * returns - DataNode pointer to randomly selected node, NULL if error occurred
 */
DataNode* GetRandomDataNode(DataNode *listHead);

/* DestroyDataList: Iterate through the list, beginning
 *    with listHead, calling DestroyDataNode on each
 *    node to release the memory allocated with malloc().
 *    It is important to store the value of nextNodePtr
 *    to a local variable before destroying the current
 *    DataNode. The last node of the list has been reached
 *    when nextNodePtr is NULL. If listHead is NULL, return.
 * listHead - DataNode pointer at the head of the list
 */
void DestroyDataList(DataNode* listHead);

#endif /* __DATA_NODE_H */
