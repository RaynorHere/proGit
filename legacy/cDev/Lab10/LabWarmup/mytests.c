/* 
 * File: mytests.c
 * Author: Luke Hindman
 * Date: Tue 30 Mar 2021 09:51:04 AM MDT
 * Description: Unit tests for Song object in playlist activity
 */
#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <string.h>
#include <stdlib.h>

#include "Song.h"

#define UNUSED(x) (void)(x)
#define DISP_MSG(MSG)                                \
   if (write(STDOUT_FILENO, MSG, strlen(MSG)) == -1) \
      perror("write");

/* Define error handler */
static void handler(int sig, siginfo_t *si, void *unused)
{
   UNUSED(sig);
   UNUSED(unused);
   if (si->si_signo == SIGSEGV)
   {
      DISP_MSG("failed (segfault)\n")
      exit(1);
   }
}

int testCreateDestroy(void)
{
   char testName[] = "Unit Test - Create/Destroy Song:";
   Song *testNode = CreateSong("Rush","Roll the Bones", "Dreamline", 277);
   if (testNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }
   DestroySong(testNode);
   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

int testCreateNullArtist(void)
{
   char testName[] = "Unit Test - Create Song (NULL Artist):";
   Song *testNode = CreateSong(NULL,"Roll the Bones", "Dreamline", 277);
   if (testNode == NULL)
   {
      fprintf(stderr, "%s passed\n", testName);
      return 0;
   }
   else
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }
}

int testCreateNullAlbum(void)
{
   char testName[] = "Unit Test - Create Song (NULL Album):";
   Song *testNode = CreateSong("Rush",NULL, "Dreamline", 277);
   if (testNode == NULL)
   {
      fprintf(stderr, "%s passed\n", testName);
      return 0;
   }
   else
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }
}

int testCreateNullTitle(void)
{
   char testName[] = "Unit Test - Create Song (NULL Title):";
   Song *testNode = CreateSong("Rush","Roll the Bones", NULL, 277);
   if (testNode == NULL)
   {
      fprintf(stderr, "%s passed\n", testName);
      return 0;
   }
   else
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }
}

int testCreateNegDuration(void)
{
   char testName[] = "Unit Test - Create Song (Negative Duration):";
   Song *testNode = CreateSong("Rush","Roll the Bones", "Dreamline", -1);
   if (testNode == NULL)
   {
      fprintf(stderr, "%s passed\n", testName);
      return 0;
   }
   else
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }
}


int testCompareSongsShorter(void)
{
   char testName[] = "Unit Test - Compare Songs (Shorter First):";
   Song *song1 = CreateSong("Rush","Roll the Bones", "Dreamline", 277);
   if (song1 == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   Song *song2 = CreateSong("Rush","Roll the Bones", "The Big Wheel", 312);
   if (song2 == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   int rc = CompareSongs(&song1, &song2);
   if (rc >= 0)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   DestroySong(song1);
   DestroySong(song2);

   fprintf(stderr, "%s passed\n", testName);
   return 0;
}


int testCompareSongsLonger(void)
{
   char testName[] = "Unit Test - Compare Songs (Longer First):";
   Song *song1 = CreateSong("Rush","Roll the Bones", "Dreamline", 277);
   if (song1 == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   Song *song2 = CreateSong("Rush","Roll the Bones", "The Big Wheel", 312);
   if (song2 == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   int rc = CompareSongs(&song2, &song1);
   if (rc <= 0)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   DestroySong(song1);
   DestroySong(song2);

   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

int testCompareSongsEqual(void)
{
   char testName[] = "Unit Test - Compare Songs (Equal Length):";
   Song *song1 = CreateSong("Rush","Roll the Bones", "Dreamline", 277);
   if (song1 == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   int rc = CompareSongs(&song1, &song1);
   if (rc != 0)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   DestroySong(song1);

   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

int testPrintSong(void)
{

   char testName[] = "Unit Test - Print Song:";

   Song *song1 = CreateSong("Rush","Roll the Bones", "Dreamline", 277);
   if (song1 == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }
   printf("%s - Test Output:\n", testName);
   PrintSong(song1);

   DestroySong(song1);
   return 0;
}

int runall(void)
{

   int status = 0;
   status += testCreateDestroy();
   status += testCreateNullArtist();
   status += testCreateNullAlbum();
   status += testCreateNullTitle();
   status += testCreateNegDuration();
   status += testCompareSongsShorter();
   status += testCompareSongsLonger();
   status += testCompareSongsEqual();
   status += testPrintSong();

   return status;
}

void setup_signal_handling(void)
{
   /* Setup signal handling to catch segfault */
   struct sigaction sa;
   sa.sa_flags = SA_SIGINFO;
   sigemptyset(&sa.sa_mask);
   sa.sa_sigaction = handler;
   if (sigaction(SIGSEGV, &sa, NULL) == -1)
      perror("sigaction");
}

int main(int argc, char *argv[])
{
   int status = 0;

   if (argc == 1)
   {
      status = runall();
   }
   else if (argc == 3)
   {
      int test_num = atoi(argv[2]);

      setup_signal_handling();

      switch (test_num)
      {
      case 1:
         /* Safe Path */
         status = testCreateDestroy();
         break;
      case 2:
         /* Safe Path */
         status = testCreateNullArtist();
         break;
      case 3:
         /* Safe Path */
         status = testCreateNullAlbum();
         break;
      case 4:
         /* Safe Path */
         status = testCreateNullTitle();
         break;
      case 5:
         /* Safe Path */
         status = testCreateNegDuration();
         break;
      case 6:
         /* Safe Path */
         status = testCompareSongsShorter();
         break;
      case 7:
         /* Invalid Test*/
         status = testCompareSongsLonger();
         break;
      case 8:
         /* Safe Path */
         status = testCompareSongsEqual();
         break;
      case 9:
         /* Safe Path */
         status += testPrintSong();
         break;
      default:
         /* Unknown test selection */
         printf("Invalid test specified\n");
         printf("usage: %s [-t <test num>]\n", argv[0]);
         exit(1);
         break;
      }
   } 
   else
   {
      printf("usage: %s [-t <test num>]\n", argv[0]);
      exit(1);
   }
   

   return status;
}

