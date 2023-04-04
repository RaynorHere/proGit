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

int noSort(const struct dirent **entryA, const struct dirent **entryB) {

    // LITERALLY CAST THEM TO VOID, which is the most metal goddamn thing any coding class has ever said or written
    UNUSED(entryA);
    UNUSED(entryB);

    return 0;

}

static int defaultFilter (const struct dirent *current) {
    UNUSED(current);
    return 1;
}


int main (int argc, char * argv[]) {
    struct dirent **eps;
    int n;
    int opt;

    // Declare filterFunction pointer
    int (*filterFunction) (const struct dirent *);
    filterFunction = defaultFilter;

    // Declare sortFunction pointer
    int (*sortFunction)(const struct dirent **, const struct dirent **);
    sortFunction = noSort;


    // Declaration, sets default to "this directory"
    char dirPath[4096];
    strcpy(dirPath, "./");

    

    // Use getopt() to process command line arguments
    while ((opt = getopt(argc, argv, "d:s")) != -1) {
        switch (opt) {
            case 'd' :
                strncpy(dirPath, optarg, 4096);
                break;

            case 's' :
                sortFunction = alphasort;
                break;

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
