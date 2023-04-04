/* 
 * Author: Luke Hindman
 * Date: Thu 05 Nov 2020 08:10:44 AM PST
 * Description:  Adapted from the Simple Directory Lister Mark II example
 *    provided in the libc manual.
 * https://www.gnu.org/software/libc/manual/html_node/Simple-Directory-Lister-Mark-II.html
 */
#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <dirent.h>
#include <errno.h>
#include <string.h>
#include <unistd.h>

#define UNUSED(x) (void)x


////            SORTING ALGORITHMS          ////

//  No sort. Default. Self-explanatory
int noSort(const struct dirent **entryA, const struct dirent **entryB) {

    // LITERALLY CAST THEM TO VOID, which is the most metal goddamn thing any coding class has ever said or written
    UNUSED(entryA);
    UNUSED(entryB);

    return 0;

}

// Reverse sort. Inverse of Alphasort (which isn't defined here because it's a native C algorithm)
int reverseSort (const struct dirent **entryA, const struct dirent **entryB) {

    // Designate the pieces
    const char *firstOne = (*entryA)->d_name;
    const char *secondOne = (*entryB)->d_name;

    // Exchange positions. Apparently you can also negate this with a "-" in front of it,
    // but this seems simpler  
    return strcmp (secondOne, firstOne);
}


////            FILTER ALGORITHMS           ////

//  Exactly what it says on the box
static int defaultFilter (const struct dirent *current) {
    
    //  Modify default filter function to exclude hidden files
    //  Note to future self possibly reviwing this: comparing chars REQUIRES apostrophes, not quotes!
    //  Originally had == ".", and it don't work
    if (current->d_name[0] == '.') {
        return 0;
    }
    else {

    // This cast should no longer be necessary, because now we've used CURRENT to perform some function
    // With the above, everything else is fine, so just return 1 across the board
    // UNUSED(current);
    return 1;
    }
}

//  Show hidden files and folders
static int showAll (const struct dirent *current) {
    
    //  Luckily, basically already done for us, because our Warmup's default search function showed everything
    //  Therefore, simply set it to return 1, no matter the value it finds
    UNUSED(current);
    return 1;
}

//  Show only files, not folders/directories
static int showFilesOnly (const struct dirent *current) {
    
        if (current->d_type == DT_DIR) {
            return 0;
        }
        else {
            return 1;
        }
}


////        PRIMARY PROGRAM         ////    

int main (int argc, char * argv[]) {
    struct dirent **eps;
    int n;
    int opt;

    // Declare filterFunction pointer; default - Don't show hiddens
    int (*filterFunction) (const struct dirent *);
    filterFunction = defaultFilter;

    // Declare sortFunction pointer; default - No sorting
    int (*sortFunction)(const struct dirent **, const struct dirent **);
    sortFunction = noSort;


    // Declaration, sets default to "this directory"
    char dirPath[4096];
    strcpy(dirPath, "./");

    

    // Use getopt() to process command line arguments
    while ((opt = getopt(argc, argv, "d:safrh")) != -1) {
        switch (opt) {

            //  Specify new path to search
            case 'd' :
                strncpy(dirPath, optarg, 4096);
                break;

            //  Disable filtering out of hidden files and folders
            case 'a' :
                filterFunction = showAll;
                break;

            // Sort in reverse of alphabetical order
            case 'r' :
                sortFunction = reverseSort;
                break;
            
            case 'f' :
                filterFunction = showFilesOnly;
                break;
            
            // Enable sorting by alphabetical order
            case 's' :
                sortFunction = alphasort;
                break;

            // Help function/output of abbreviated usage manual
            case 'h' :
                printf("Usage: ./myls [-d <directory>] [-s] [-a] [-r] [-f] [-h]\n");
                printf("-d allows a user to specify a different directory than the current one to scan.\n");
                printf("-a displays all files, including hidden ones (vanilla search will not show hidden files)\n");
                printf("-f displays only files, not folders/directories contained in location\n");
                printf("-s displays output in alphabetical order, ascending from A\n");
                printf("-r displays output in alphabetical order, descending from Z\n");
                printf("-h displays this help menu.\n");
                exit(0);

            default :
                break;

            fprintf(stderr, "Naw. Option's wrong.\n");
            fprintf(stdout, "Intended Usage: %s [-d <path>] \n", argv[0]);

        }
    }

    // Actual scanning of the directory
    errno = 0;
    n = scandir (dirPath, &eps, filterFunction, sortFunction);
    

    /* validate directory was opened successfully */
    if (n < 0) {
        perror("scandir: ");
        exit(1);
    }

    int cnt;
    for (cnt = 0; cnt < n; ++cnt) {
        fprintf(stdout,"%s\n", eps[cnt]->d_name);
    }

    for (int i = 0; i < n; i++) {
        free(eps[i]);
    }

    free (eps);

    return 0;
}
