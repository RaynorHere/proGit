/* CS-253 Lab 09 Activity
*
* Author: James "JCIII" Crowell
*
* Date: 10/17/2021
*
* Description: An introductory assignment to pointers and linked lists in C
*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "PlaylistNode.h"

// Constructor
PlaylistNode* CreatePlaylistNode(char id[], char songName[], char artistName[], int songLength) {

    PlaylistNode *newNode = (PlaylistNode *) malloc(sizeof(PlaylistNode));

    if (newNode == NULL) {
        return NULL;
    }

    // Assign values as necessary; do not assign next node yet, but intialize the pointer, I think, with the NULL? Whatever; keep it in mind, anyway
    strncpy(newNode->uniqueID, id, 50);
    strncpy(newNode->songName, songName, 50);
    strncpy(newNode->artistName, artistName, 50);
    newNode->songLength = songLength;
    newNode->nextNodePtr = NULL;
}

// Insert a node to a specific point in the list, rather than necessarily the tail
int InsertPlaylistNodeAfter(PlaylistNode* nodeInList, PlaylistNode* newNode) {

    // Placeholder for value swapping in insertion algorithm
    PlaylistNode* placeHolder = NULL;

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

// Set the nextNode pointer of a node in the list to a new node. I'm not entirely certain how this differs from the one above.
// Maybe it isn't supposed to; maybe I could have offloaded a few lines above to this function, though I didn't
int SetNextPlaylistNode(PlaylistNode* nodeInList, PlaylistNode* newNode) {

    // newNode can apparently be null, but not nIL (heheheheh, that's almost a pun when you read it out loud)
    if (nodeInList == NULL) {
        return -1;
    }

    // Are we meant to worry about the songs that newNode might already be pointing to? Are we worrying about displacing the order of the playlist?
    // I am extremely unclear on these things

    nodeInList->nextNodePtr = newNode;

    return 0;
}

// Request the nextNode pointer of a node in the list
PlaylistNode* GetNextPlaylistNode(PlaylistNode* nodeInList) {

    // Non-extant node
    if (nodeInList == NULL) {
        return NULL;
    }

    // Tail of list; I'm not sure this is necessary; it's next pointer would already BE null, so I don't think I have to specify, but I'm being thorough
    if (nodeInList->nextNodePtr == NULL) {
        return NULL;
    }

    PlaylistNode* nextOut = nodeInList->nextNodePtr;

    return nextOut;
}

// Basically a toString; print out each instance variable of the node, and print each on its own line
void PrintPlaylistNode(PlaylistNode* thisNode) {

    // Ensure a fresh line
    printf("\n");
    printf("Unique ID: %s\n", thisNode->uniqueID);
    printf("Song Name: %s\n", thisNode->songName);
    printf("Artist Name: %s\n", thisNode->artistName);
    printf("Song Length (Seconds): %d\n", thisNode->songLength);

}

// Garbage collection; de-allocate any memory used to make nodes
void DestroyPlaylistNode(PlaylistNode* thisNode) {

    // Can't do it on a nothing node, obviously
    if (thisNode == NULL) {
        return;
    }

    // This is weirdly, deceptively easy
    free(thisNode);
}