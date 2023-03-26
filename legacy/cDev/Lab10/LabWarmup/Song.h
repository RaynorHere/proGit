/* File: Song.h
 * Author: Luke Hindman
 * Date: Sun 01 Nov 2020 09:16:37 AM PST
 * Description: Stuct and functions for working with Song data
 */
#ifndef __SONG_H
#define __SONG_H

#define SONG_FIELD_SIZE 40

struct song_struct {    
    char artist[SONG_FIELD_SIZE];
    char album[SONG_FIELD_SIZE];
    char title[SONG_FIELD_SIZE];
    int duration;
};
typedef struct song_struct Song;

/* CreateSong: Allocate a Song object in the heap using malloc
 *    the object will be initialized with the specified artist, 
 *    album, title and duration.  Return NULL if any parameters are null
 *    or if duration is less than 0.
 * artist - NULL terminated string containing artist
 * album - NULL terminated string containing album
 * title - NULL terminated string containing song title
 * duration - interger value with duration of song in seconds
 * 
 * returns - Pointer to Song object allocated on the heap
 */
Song * CreateSong(const char artist[], const char album[], const char title[], int duration);

/* CompareSongs: Compare two Song objects. The parameters must be of 
 *    type void* for compatibility with qsort().  The parameters must
 *    be cast to Song before dereferencing internal fields.  Returns
 *    negative number if the duration of song1 is less than song2, a 
 *    positive number of the duration of song1 is greather than song2, 
 *    and zero if the songs have equal duration. Undefined behavior if
 *    either song1 or song2 is NULL
 * song1PtrPtr - Pointer to a Pointer to a Song
 * song2PtrPtr - Pointer to a Pointer to a Song
 * 
 * returns negative number if song1 < song2
 *         positive number if song1 > song2
 *         zero if song1 == song2
 */
int CompareSongs(const void * song1PtrPtr, const void * song2PtrPtr);

/* PrintSong: Write the fields of the Song, nicely formatted,
 *     to stdout (console) using printf. 
 * thisSong - Pointer to Song object to be displayed */
void PrintSong(Song * thisSong);

/* DestroySong: Release memory allocated by malloc in the
 *    CreateSong function.  Does nothing if thisSong is NULL
 * thisSong - Pointer to Song object to be freed.
 */
void DestroySong(Song * thisSong);

#endif /* __SONG_H */