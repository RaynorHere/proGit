/*
 * imageCompare in C
 * Terms, for newcomers:
 *   "Compare" item is the item pulled from the list and compared against every other
 *   "Candidate" item is the other item, which the compare item is begin compared against
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/time.h>

int main()
{
    // POINTERS
    FILE *manPtr;   // Manifest of files to compare
    FILE *repPtr;   // Report program will output
    FILE *compItem; // Pointer for item to compare
    FILE *candItem; // Pointer for items to compare against

    // STRUCTS
    struct timeval iterTop, iterBottom; // Timestamps just to see long iters take

    // OTHER VARS
    char item[255];      // Var to hold URL of item being compared
    char candidate[255]; // Var to hold URL of items being compared against
    int lCnt = 0;        // Lines in file
    int maxEnt = 0;      // Longest string in manifest
    long compSize = 0;   // Filesize of compare item
    long candSize = 0;   // Filesize of candidate item
    int compHead;        // The 'scanner' of the compare item's data
    int candHead;        // The 'scanner' of the candidate item's data

    ////                INITIAL PREP                ////
    // Open manifest, report
    manPtr = fopen("manifest", "r"); // File is open (manPtr points to beginning of it contents)
    repPtr = fopen("report", "w");

    // Get a line count
    while (fgets(item, 255, manPtr) != NULL)
    {
        lCnt++; // Line counts
    }
    char *manifest[lCnt]; // Actual array

    // Run again?
    rewind(manPtr);
    int i = 0;
    while (fgets(item, 255, manPtr) != NULL)
    {
        item[strcspn(item, "\n")] = 0; // Strips newline
        manifest[i] = strdup(item);    // Populate
        i++;
    }
    ////                /////////////                ////

    // Report
    // printf("There are %d lines in the file/entries in the manifest.\n", lCnt);
    // printf("=========================ENTRIES=========================\n");
    // i = 0;
    // while (i < lCnt)
    // {
    //     printf("Entry %d: %s\n", (i+1), manifest[i]);
    //     i++;
    // }
    // printf("========================END PREP=========================\n\n");

    // Everything is accurate up to this point as of 03/28/23
    // Whatever the problem is, it's not the files or the manifest

    ////                MAIN FLOW                ////
    i = 0;
    int j;
    while (i < lCnt)
    {
        // Begin timestamp
        gettimeofday(&iterTop, NULL);

        // Open item from manifest to compare
        compItem = fopen(manifest[i], "rb");
        if (compItem == NULL)
        {
            printf("READ ERROR: COMPARE\n");
            exit(1);
        }

        // Get filesize         Confirmed working
        fseek(compItem, 0L, SEEK_END); // Jump to end of file
        compSize = ftell(compItem);    // Report position
        rewind(compItem);              // Return to initial
        compHead = getc(compItem);     // Set scanner head compare

        // Read fail?
        if (compHead == EOF)
        {
            printf("INITIAL COMPARE HEAD COULD NOT BE SET ON ITER %d\n\n", i);
            exit(1);
        }
        else
        {
            printf("COMPARE READ HEAD SET\n\n");
        }

        // Only work down list
        j = i;

        while (j < lCnt)
        {
            // First iteration always skips
            // Do not compare to self
            if (manifest[i] == manifest[j])
            {
                printf("SKIPPING SELFSAME FILE AT ITER %d-%d\n\n", i, j);
                j++; // Continue jumps entirely out of loop; j doesn't inc below
                continue;
            }
            candItem = fopen(manifest[j], "rb"); // Open candidate
            if (candItem == NULL)
            {
                printf("READ ERROR: CANDIDATE");
                exit(1);
            }

            fseek(candItem, 0L, SEEK_END);
            candSize = ftell(candItem); // Report filesize
            rewind(candItem);

            // Only bother scanning bytes if sizes are equal
            if (compSize == candSize)
            {
                printf("IDENTICAL FILESIZES FOUND. COMPARING CONTENTS.\n");
                candHead = getc(candItem); // Set scanner head candidate
                if (candHead == EOF)
                {
                    printf("INITIAL CANDIDATE HEAD COULD NOT BE SET ON ITER %d-%d\n\n", i, j);
                    exit(1);
                }
                printf("CANDIDATE READ HEAD SET.\n");

                // Heads move in sync, should only have to see one hit EOF
                while (compHead == candHead && compHead != EOF)
                {
                    compHead = getc(compItem);
                    candHead = getc(candItem);
                    // printf("%d and %d\n", compHead, candHead);
                }

                // Should only need to check one head
                if (compHead == EOF)
                {
                    fprintf(repPtr, "Files %s and %s are the same\n", manifest[i], manifest[j]);
                }

                // Rewind compare item for next candidate
                printf("COMPARISONS COMPLETE; REWINDING\n\n");
                rewind(compItem);
            }

            // Close stream
            fclose(candItem);
            j++;
        }
        fclose(compItem);

        gettimeofday(&iterBottom, NULL);
        int iterElapse = iterBottom.tv_sec - iterTop.tv_sec;
        i++;
        printf("Iter %d done, after %d seconds\n", i, iterElapse);
    }

    fclose(manPtr);
    // Terminate
    return 0;
}