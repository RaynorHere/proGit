#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>
#include "Song.h"

int main(int argc, char * argv[]) {

   if (argc != 3) {
      fprintf(stderr, "Usage: %s, <catalog.csv> <size>\n", argv[0]);
      exit(1);
   }

   char * fileName = argv[1];
   char * useSize = argv[2];


   // Declares file type (a pointer, natch), makes an object out of it, sets it to read permission/functionality
   FILE * fileIn = fopen(fileName, "r");

   // Validate open process(?)
   errno = 0;
   if (fileIn == NULL) {
      perror("fopen");
      exit(1);
   }

   // Validating list size
   int listSize = 0;
   int rc = sscanf(useSize, "%d", &listSize);

   if (rc != 1) {
      fprintf(stderr, "Non-integer value passed for size.");
      exit(1);
   }

   if (listSize <= 0) {
      fprintf(stderr, "Error: must specify list size of 1 or greater.\n");
      exit(1);
   }

   Song ** songList = (Song **) malloc(sizeof(Song *) * listSize);

   if (songList == NULL) {
      fprintf(stderr, "Error: Cannot allocate memory for song list.\n");
      exit(1);
   }

   const int MAXFIELDSIZE = 256;
   char artistField[MAXFIELDSIZE];
   char album[MAXFIELDSIZE];
   char title[MAXFIELDSIZE];
   int duration;

   int songPop = 0;
   int readPop;

   while (!feof(fileIn) && songPop < listSize) {
      readPop = fscanf(fileIn, "%255[^,],%255[^,],%255[^,],%d\n", artistField, album, title, &duration);

      if (readPop == 4) {
         songList[songPop++] = CreateSong(artistField, album, title, duration);
      }
      else {
         fprintf(stderr, "Error: read %d of 4 while processing CSV.\n", readPop);
      }
   }

   fclose(fileIn);

   fprintf(stdout, "Successfully loaded %d songs.\n", songPop);

   // Dump out list
   for (int i = 0; i < songPop; i++) {
      PrintSong(songList[i]);
   }

   // Annihilate
   for (int i = 0; i < songPop; i++) {
      DestroySong(songList[i]);
   }

   // Abolish
   free(songList);


   return 0;
}