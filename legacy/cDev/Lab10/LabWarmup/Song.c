/* File: Song.c
 * Author: Luke Hindman
 * Date: Sun 01 Nov 2020 09:16:37 AM PST
 * Description: Stuct and functions for working with Song data
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Song.h"


Song * CreateSong(const char artist[], const char album[], const char title[], int duration) {
    /* Validate Parameters */
    if (artist == NULL || album == NULL || title == NULL) {
        return NULL;
    }
    if(duration < 0) {
        return NULL;
    }

    /* Allocate space for Song in heap and validate */
    Song * newSong = (Song *) malloc(sizeof(Song));
    if (newSong == NULL) {
        return NULL;
    }

    /* Initialize the Song with the specified values */
    strncpy(newSong->artist,artist,SONG_FIELD_SIZE);
    strncpy(newSong->album,album,SONG_FIELD_SIZE);
    strncpy(newSong->title,title,SONG_FIELD_SIZE);
    newSong->duration = duration;

    return newSong;
}

int CompareSongs(const void * song1PtrPtr, const void * song2PtrPtr) {
    /* Dereference the parameters to access the song pointers */
    Song * song1 = * (Song **)song1PtrPtr; 
    Song * song2 = * (Song **)song2PtrPtr;
    return ((Song *)song1)->duration - ((Song *)song2)->duration;
}

void PrintSong(Song * thisSong) {
    /* Validate Parameters */
    if(thisSong == NULL) {
        return;
    }

    fprintf(stdout,"------------------------------------------------\n");
    fprintf(stdout,"Artist: %s\n", thisSong->artist);
    fprintf(stdout,"Album: %s\n", thisSong->album);
    fprintf(stdout,"Title: %s\n", thisSong->title);
    fprintf(stdout,"Duration: %d\n", thisSong->duration);
    fprintf(stdout,"------------------------------------------------\n");
}

void DestroySong(Song * thisSong) {
    /* Validate Parameters */
    if (thisSong == NULL) {
        return;
    }

    free(thisSong);
}

