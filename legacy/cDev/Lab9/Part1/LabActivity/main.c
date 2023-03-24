/*
* CS-253 Lab09 Pt I - Playlist
*
* author: James "JCIII" Crowell
*
* date: 10/18/2021
*
* description: Program to take in a name for and elements of a playlist in the form of linked list nodes, as well as a
*   menu and supported functions for editing said playlist
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "PlaylistNode.h"

// I think we've actually found a working newline killer here. I say "found" because I actually put this together after a little
// research after BufferPurge() was really fouling the bed during lab 08. Still, nice to have this provided, even if it is late
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

// I don't think I need to explain what this does. It's the fourth time we've had to do this, and I think there's SOME ontological inertia for a grader
// from assignment to assignment, assuming these are looked at in any detail, and I think they are
void PrintMenu(char playlistName[])
{
    printf("%s Playlist Menu\n", playlistName);
    printf("a - Add song\n");
    printf("r - Remove song\n");
    printf("c - Change position of song\n");
    printf("s - Output songs by specific artist\n");
    printf("t - Output total time of playlist (in seconds)\n");
    printf("o - Output full playlist\n");
    printf("q - Quit\n");
    printf("Please make a selection:\n\n");
}

int main(void)
{

    // Instance variables, LL
    PlaylistNode *head = NULL;
    PlaylistNode *tail = NULL;
    PlaylistNode *current = NULL;

    // Instance variables, user stuff
    // Add
    char newSongID[50];
    char newSongTitle[50];
    char newSongArtist[50];
    int newSongLength;

    // Remove
    char removeSongID[50];
    int gotIt = 0;

    // Multi-use
    int maxPos = 0;

    char playlistName[50];
    printf("Please enter a playlist name:\n");
    fgets(playlistName, 49, stdin);
    NewLineTrim(playlistName);

    // Trying out NLT to see what it does.
    // int j = strlen(playlistName);
    // for (int i = 0; i < j; i++) {
    //     printf("%c\n", playlistName[i]);
    // }

    // Engage menu
    int onScreen = 1;
    // Still experimenting with ways to make this as unannoying as possible
    char useChoice;

    printf("\n");
    PrintMenu(playlistName);

    while (onScreen)
    {

        scanf(" %c", &useChoice);
        // NewLineTrim(useChoice);

        switch (useChoice)
        {

        // ADD SONG //
        case 'a':

            while (getchar() != '\n')
                ;
            // Grab info
            printf("ADD SONG\n");
            printf("Enter song's unique ID:\n");
            fgets(newSongID, 49, stdin);
            NewLineTrim(newSongID);

            printf("Enter song's title:\n");
            fgets(newSongTitle, 49, stdin);
            NewLineTrim(newSongTitle);

            printf("Enter song's artist:\n");
            fgets(newSongArtist, 49, stdin);
            NewLineTrim(newSongArtist);

            printf("Enter song's length in seconds:\n");
            scanf("%d", &newSongLength);

            // Create the new node
            current = CreatePlaylistNode(newSongID, newSongTitle, newSongArtist, newSongLength);

            if (current == NULL)
            {
                printf("Error: Unable to create Playlist Node.\n");
                break;
            }

            // New songs are set to be added to the end of the list. Assuming I can properly keep track of my blasted TAIL node, I can just set
            // TAIL's next to be the new node, and then make the new node the new TAIL

            if (head == NULL)
            {
                head = current;
                tail = current;

                printf("First song added!\n");
            }
            else
            {
                int rc = InsertPlaylistNodeAfter(tail, current);

                if (rc < 0)
                {
                    printf("Error: Unable to insert playlist node.\n");
                }

                else
                {
                    tail->nextNodePtr = current;
                    tail = current;
                    printf("Song added!\n");
                }
            }
            break;

        // REMOVE SONG //
        case 'r':

            while (getchar() != '\n')
                ;

            // Get ID
            printf("REMOVE SONG\n");
            printf("Provide Unique ID of song to remove:\n");
            fgets(removeSongID, 49, stdin);
            NewLineTrim(removeSongID);

            // Find ID
            current = head;

            // The loop logic I built means we need a special check for removing the first node in the list
            if (strcmp(head->uniqueID, removeSongID) == 0)
            {
                gotIt = 1;
                printf("Target was current head node. Song \"%s\" removed!\n", head->songName);

                // A little switcharoo here should -SHOULD- allow us to mark the node after head so we can make it our new head, but not before
                // using the CURRENT head to do some memory freeing. I don't actually know if we need to do this or I can just walk the whole
                // list at the end before the program closes and kill 'em all then, but I'm not chancing it
                current = GetNextPlaylistNode(head);
                DestroyPlaylistNode(head);
                head = current;
            }

            else
            {

                while (current != NULL)
                {
                    if (strcmp(current->nextNodePtr->uniqueID, removeSongID) == 0)
                    {
                        gotIt = 1;

                        printf("\"%s\" removed!\n", current->nextNodePtr->songName);

                        // If the tail node is what's holding it, we have to do some special stuff
                        if (tail == current->nextNodePtr)
                        {
                            current->nextNodePtr = NULL;
                            DestroyPlaylistNode(tail);
                            tail = current;
                        }

                        // Otherwise, it's somewhere in the middle
                        else
                        {
                            PlaylistNode *deathMark = GetNextPlaylistNode(current);
                            current->nextNodePtr = current->nextNodePtr->nextNodePtr;
                            DestroyPlaylistNode(deathMark);
                        }

                        // If we find it, no need to continue
                        break;
                    }
                    else
                    {
                        current = GetNextPlaylistNode(current);
                    }
                }
            }

            if (gotIt == 0)
            {
                printf("There is no song by that ID in this playlist.\n");
            }

            break;

        // CHANGE TRACK POSITION //
        case 'c':

            while (getchar() != '\n')
                ;
            printf("CHANGE TRACK POSITION\n");
            int oldPos = 0;
            int newPos = 0;
            maxPos = 0;

            if (head == NULL || head == tail)
            {
                printf("You don't have enough tracks in your playlist to move any around!\n");
                break;
            }

            // Find limit of list
            current = head;
            while (current != NULL)
            {
                maxPos++;
                current = GetNextPlaylistNode(current);
            }

            // Calculation check
            // printf("%d\n", maxPos);
            // Looks good

            printf("Which track would you like to move? Note: Track 1 is the at the front of the list.\n");
            scanf(" %d", &oldPos);
            printf("Change to what position number in list?\n");
            scanf(" %d", &newPos);

            // Reset to head, if applicable
            if (oldPos < 1)
            {
                oldPos = 1;
            }
            if (newPos < 1)
            {
                newPos = 1;
            }

            // Reset to tail, if applicable
            if (oldPos > maxPos)
            {
                oldPos = maxPos;
            }
            if (newPos > maxPos)
            {
                newPos = maxPos;
            }

            // Guarantee there's a sleeker way to do this, but this'll work for a first pass
            PlaylistNode *oldGuy = head;
            PlaylistNode *beforeOld = NULL;
            PlaylistNode *afterOld = head->nextNodePtr;

            PlaylistNode *newGuy = head;
            PlaylistNode *beforeNew = NULL;
            PlaylistNode *afterNew = head->nextNodePtr;

            // int testVal1, testVal2 = 1;

            // Setting i to 1 to start with should guarantee that if the user requests to move the head node, the placeholder won't advance
            // because 1 !< 1
            for (int i = 1; i < oldPos; i++)
            {
                beforeOld = oldGuy;
                oldGuy = GetNextPlaylistNode(oldGuy);
                afterOld = oldGuy->nextNodePtr;
                // testVal1 = i;
            }

            // Roll up to requested new position
            for (int i = 1; i < newPos; i++)
            {
                beforeNew = newGuy;
                newGuy = GetNextPlaylistNode(newGuy);
                afterNew = newGuy->nextNodePtr;
                // testVal2 = i;
            }

            // If those loops are calculating correctly, test outputs should be the same as values entered to the two questions above
            // printf("%d, %d", testVal1, testVal2);

            // Now, actually swap the tracks

            // SPECIAL CASE 1: Swapping head and tail
            if (oldPos == 1 && newPos == maxPos)
            {

                head = afterOld;
                newGuy->nextNodePtr = oldGuy;
                oldGuy->nextNodePtr = NULL;
                tail = oldGuy;
                printf("\"%s\" (former head) moved to bottom of playlist.\n", oldGuy->songName);
            }

            // SPECIAL CASE 2: Swapping tail and head
            else if (oldPos == maxPos && newPos == 1)
            {
                tail = beforeOld;
                beforeOld->nextNodePtr = NULL;
                oldGuy->nextNodePtr = newGuy;
                head = oldGuy;
                printf("\"%s\" (former tail) moved to top of playlist.\n", oldGuy->songName);
            }

            // SPECIAL CASE 3: Moving the head node, no tail involvement
            else if (oldPos == 1)
            {

                // Change head

                head = afterOld;
                newGuy->nextNodePtr = oldGuy;
                oldGuy->nextNodePtr = afterNew;
                printf("\"%s\" (former head) moved to position %d\n", oldGuy->songName, newPos);
            }

            // SPECIAL CASE 4: Moving the tail node, no head involvement
            else if (oldPos == maxPos)
            {

                // oldGuy should already be at the tail
                tail = beforeOld;
                beforeOld->nextNodePtr = NULL;
                beforeNew->nextNodePtr = oldGuy;
                oldGuy->nextNodePtr = newGuy;
                printf("\"%s\" (former tail) moved to position %d\n", oldGuy->songName, newPos);
            }

            // SPECIAL CASE 5: Moving TO the head node, no tail involvement
            else if (newPos == 1)
            {

                head = oldGuy;
                oldGuy->nextNodePtr = newGuy;
                beforeOld->nextNodePtr = afterOld;
                printf("\"%s\" moved to top of playlist.\n", oldGuy->songName);
            }

            // SPECIAL CASE 6: Moving TO the tail node, no head involvement
            else if (newPos == maxPos)
            {

                tail = oldGuy;
                newGuy->nextNodePtr = oldGuy;
                beforeOld->nextNodePtr = afterOld;
                oldGuy->nextNodePtr = NULL;
                printf("\"%s\" moved to bottom of playlist.\n", oldGuy->songName);
            }

            // Generic case
            else
            {
                beforeOld->nextNodePtr = afterOld;
                oldGuy->nextNodePtr = newGuy;
                beforeNew->nextNodePtr = oldGuy;
                printf("\"%s\" moved to position %d\n", oldGuy->songName, newPos);
            }

            break;

        // OUTPUT BY ARTIST //
        case 's':

            // Usual header, recon, etc
            while (getchar() != '\n')
                ;
            printf("OUTPUT SONGS BY SPECIFIC ARTIST\n");
            char artistChoice[50];
            printf("Please specify an artist:\n");
            fgets(artistChoice, 49, stdin);
            NewLineTrim(artistChoice);

            // Despite scope differences, I cannot declare maxPos again to find the list limit. I also don't know for certain which  method will be called first,
            // so I can't just set maxPos to 0 here and rerun it. So, instead:
            maxPos = 0;
            current = head;
            while (current != NULL)
            {
                maxPos++;
                current = GetNextPlaylistNode(current);
            }

            // Guarantee a space
            printf("\n");

            // Reset counter pointer
            current = head;

            // Walk through list, print anything what has that artist listed
            for (int i = 0; i < maxPos; i++)
            {
                if (strcmp(current->artistName, artistChoice) == 0)
                {
                    PrintPlaylistNode(current);
                    printf("\n");
                }

                current = GetNextPlaylistNode(current);
            }

            // One extra line for good measure
            printf("\n");

            break;

        // OUTPUT PLAYLIST TIME //
        case 't':

            while (getchar() != '\n')
                ;
            printf("OUTPUT TOTAL PLAYLIST TIME\n");

            current = head;
            int totalPlayTime = 0;

            while (current != NULL)
            {
                totalPlayTime += current->songLength;
                current = GetNextPlaylistNode(current);
            }

            printf("The total playtime of this playlist is %d seconds.\n", totalPlayTime);

            break;

        // OUTPUT FULL PLAYLIST //
        case 'o':

            while (getchar() != '\n');
            printf("OUTPUT FULL PLAYLIST\n");

            if (head == NULL)
            {
                printf("Playlist is empty!\n");
            }

            else
            {

                current = head;

                while (current != NULL)
                {
                    PrintPlaylistNode(current);
                    printf("\n");
                    current = GetNextPlaylistNode(current);
                }
            }

            break;

        // QUIT //
        case 'q':

            // Also have to walk through the list and dismantle all allocated memory

            current = head;
            PlaylistNode *nextNode;

            while (current != NULL)
            {
                nextNode = GetNextPlaylistNode(current);
                DestroyPlaylistNode(current);
                current = nextNode;
            }
            onScreen = 0;
            break;

        default:
            PrintMenu(playlistName);
        }
    }

    return 0;
}