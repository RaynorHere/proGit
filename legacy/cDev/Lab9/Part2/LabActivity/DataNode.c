/*
*   DataNode.c - Code definitions for the DataNode struct as defined by DataNode.h
*   Author: James "JCIII" Crowell
*   Date: 10/25/2021
*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include "DataNode.h"

DataNode* CreateDataNode(const char data[]) {

    // Ensure data passed is non-null
    if (data == NULL) {
        printf("No data in passed String.\n");
        return NULL;
    }

    // Allocate the pointer
    DataNode *newNode = (DataNode *) malloc(sizeof(DataNode));

    // Error check
    if (newNode == NULL) {
        return NULL;
    }

    // Allocate memory for data in node, including terminator character
    newNode->dataSize = sizeof(char) * strlen(data) + 1;
    newNode->dataValue = (char *) malloc(newNode->dataSize);

    // Error check
    if (newNode->dataValue == NULL) {
        return NULL;
    }

    // Actually copy in data
    strcpy(newNode->dataValue, data);

    // And next node pointer is bugger all
    newNode->nextNodePtr = NULL;
    return newNode;
}

// Insert a new node into a particular point in the list, specifically relative to a node given
int InsertDataNodeAfter(DataNode* nodeInList, DataNode* newNode) {

   // Placeholder for value swapping in insertion algorithm
    DataNode* placeHolder = NULL;

    // Ensure both nodes are valid
    if (nodeInList == NULL || newNode == NULL) {
        return -1;
    }

    // Copy old next node into placeholder, make new node the new next node, set new node's next to the old next node
    placeHolder = nodeInList->nextNodePtr;
    nodeInList->nextNodePtr = newNode;
    newNode->nextNodePtr = placeHolder;

    return 0;

}

// Set what node is next
int SetNextDataNode(DataNode* nodeInList, DataNode* newNode) {

    // nodeInList NULL check
    if (nodeInList == NULL) {
        return -1;
    }

    // Set the extant node's next pointer to the passed new guy
    nodeInList->nextNodePtr = newNode;

    return 0;
}

// Return what node is next
DataNode* GetNextDataNode(DataNode* nodeInList) {

    // Non-extant node
    if (nodeInList == NULL) {
        return NULL;
    }

    // Tail of list; I'm not sure this is necessary; it's next pointer would already BE null, so I don't think I have to specify
    // Let's try leaving it out this time
    // if (nodeInList->nextNodePtr == NULL) {
    //     return NULL;
    // }

    DataNode* nextOut = nodeInList->nextNodePtr;

    return nextOut;
}

// Display data in node
void PrintDataNode(DataNode* thisNode) {

    // This is eerily barren
    printf("%s", thisNode->dataValue);
}


// What "Law of Conservation of Data"?
void DestroyDataNode(DataNode* thisNode) {

    // Can't do it on a nothing node, obviously
    if (thisNode == NULL) {
        return;
    }

    // This is where it gets tricky; my CREATE method MALLOC's twice. Do I FREE twice, therefore? I *THINK* so, and I *THINK* this is where
    free(thisNode->dataValue);
    free(thisNode);
}


// Given an array of strings, build a linked list of nodes, each one containing one string
DataNode* BuildDataList(char * data[], int numElements) {

    // Fingers
    DataNode* head = NULL;
    DataNode* newNode = NULL;
    DataNode* current = NULL;

    // Create a node for each string in the array
    for (int i = 0; i < numElements; i++) {
        newNode = CreateDataNode(data[i]);

        // Ensure all nodes create successfully
        if (newNode == NULL) {
            DestroyDataList(head);
            return NULL;
        }

        // If it's our first node; i.e., the process is just beginning
        if (i == 0) {
            head = newNode;
            current = newNode;
            continue;
        }

        // Else, just throw it on the pile
        SetNextDataNode(current, newNode);
        current = newNode;
    }

    // Spit out pointer to head
    return head;
    }

    // Get current population of linked list
    int GetDataListSize(DataNode* listHead) {
        int totalSize = 0;
        DataNode* current = listHead;

        if (listHead == NULL) {
            return -1;
        }

        while (current != NULL) {
            totalSize++;
            current = GetNextDataNode(current);
        }

        return totalSize;
    }


    // Iterate through and print out contents of entire linked list
    void PrintDataList(DataNode *listHead) {

        DataNode* current = listHead;

        // Until we hit nothingness
        while (current != NULL) {
            
            // Print out each itemized node
            PrintDataNode(current);

            // Set to next node
            current = GetNextDataNode(current);

            // Whereupon we will check BEFORE the conditional to break the loop above if it's null
            // if it is, give it a newline instead of a comma, because our last element isn't supposed
            // to have a comma after it
            if (current == NULL) {
                printf("\n");
            }

            // Otherwise
            else {
                printf(", ");
            }
        }
    }

    // The name's pretty self-explanatory, I think
    DataNode* GetRandomDataNode(DataNode *listHead) {

        // Initial value, which we will later supply with a valid index
        int rouletteWheel = -1;
        int listSize = GetDataListSize(listHead);

        // The usual substance check
        if (listHead == NULL) {
            return NULL;
        }

        // Obtain number of a random node; mod guarantees a value between 0 and listSize, so it
        // refers to a valid node in the list
        rouletteWheel = rand() % listSize;

        // Index double-check
        if (rouletteWheel < 0 || rouletteWheel > listSize) {
            return NULL;
        }

        if (rouletteWheel == 0) {
            return listHead;
        }

        // This will actually roll us up to the node indexed by the randomly-selected integer
        DataNode* current = listHead;
        for (int i = 0; i < rouletteWheel; i++) {
            current = GetNextDataNode(current);
        }

        // Spit out the found node
        return current;
    }

    // Total memory deallocation method
    void DestroyDataList(DataNode* listHead) {

        DataNode* current = listHead;

        while (listHead != NULL) {
            listHead = GetNextDataNode(listHead);
            DestroyDataNode(current);
            current = listHead;
        }

    }
