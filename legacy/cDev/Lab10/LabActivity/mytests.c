/* 
 * File: mytests.c
 * Author: Luke Hindman
 * Date: Tue 30 Mar 2021 09:51:04 AM MDT
 * Description: Unit tests for Student object in playlist activity
 */
#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <string.h>
#include <stdlib.h>

#include "Student.h"

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
   char testName[] = "Unit Test - Create/Destroy Student:";
   Student *testNode = CreateStudent("Mosby", "Ted", 6003, 103);
   if (testNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }
   DestroyStudent(testNode);
   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

/* 
 * 
 * Write Additional Unit Tests Here
 * 
 */

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

   if (argc != 3)
   {
      printf("usage: %s [-t <test num>]\n", argv[0]);
      exit(1);
   }
   else
   {
      int test_num = atoi(argv[2]);

      setup_signal_handling();

      switch (test_num)
      {
      case 1:
         /* Safe Path */
         status = testCreateDestroy();
         break;

         /* 
      * 
      * Call Additional Unit Tests Here
      * 
      */

      default:
         /* Unknown test selection */
         printf("Invalid test specified\n");
         printf("usage: %s [-t <test num>]\n", argv[0]);
         exit(1);
         break;
      }
   }

   return status;
}
