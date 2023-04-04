/* 
 * Author: Luke Hindman
 * Date: Mon 22 Mar 2021 04:47:36 PM MDT
 * Description:  Unit test harness for ContactNode API
 */
#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <string.h>
#include <stdlib.h>

#include "ContactNode.h"

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

/* Safe Path - Create and Destroy Node */
int testCreateDestroy(void)
{
   char testName[] = "Unit Test - Create/Destroy ContactNode:";
   ContactNode *testNode = CreateContactNode("Roxanne Hughes", "443-555-2864");
   if (testNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }
   DestroyContactNode(testNode);
   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

int testInsertAfterNode(void)
{
   char testName[] = "Unit Test - Insert ContactNode After (tail):";
   ContactNode *listHead = CreateContactNode("Roxanne Hughes", "443-555-2864");
   if (listHead == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   ContactNode *newNode = CreateContactNode("Juan Alberto Jr.", "410-555-9385");
   if (newNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   int rc = InsertContactAfter(listHead, newNode);
   if (rc < 0)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   /* Double check to make sure the values were set properly */
   if (listHead->nextNodePtr != newNode || newNode->nextNodePtr != NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   DestroyContactNode(listHead);
   DestroyContactNode(newNode);

   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

int testInsertAfterNodeMiddle(void)
{
   char testName[] = "Unit Test - Insert ContactNode After (middle):";
   ContactNode *listHead = CreateContactNode("Roxanne Hughes", "443-555-2864");
   if (listHead == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   ContactNode *tailNode = CreateContactNode("Juan Alberto Jr.", "410-555-9385");
   if (tailNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   int rc = InsertContactAfter(listHead, tailNode);
   if (rc < 0)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   ContactNode *middleNode = CreateContactNode("Rachel Phillips", "310-555-6610");
   if (middleNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   rc = InsertContactAfter(listHead, middleNode);
   if (rc < 0)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   /* Double check to make sure the values were set properly */
   if (listHead->nextNodePtr != middleNode || middleNode->nextNodePtr != tailNode)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   DestroyContactNode(listHead);
   DestroyContactNode(middleNode);
   DestroyContactNode(tailNode);

   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

int testGetNextAfterInsert(void)
{

   char testName[] = "Unit Test - Get Next After Insert:";
   ContactNode *listHead = CreateContactNode("Roxanne Hughes", "443-555-2864");
   if (listHead == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   ContactNode *tailNode = CreateContactNode("Juan Alberto Jr.", "410-555-9385");
   if (tailNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   int rc = InsertContactAfter(listHead, tailNode);
   if (rc < 0)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   ContactNode *middleNode = CreateContactNode("Rachel Phillips", "310-555-6610");
   if (middleNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   rc = InsertContactAfter(listHead, middleNode);
   if (rc < 0)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   /* Double check to make sure the values were set properly */
   if (GetNextContact(listHead) != middleNode ||
       GetNextContact(middleNode) != tailNode ||
       GetNextContact(tailNode) != NULL)
   {
      fprintf(stderr, "%s failed\n", testName);

      return 1;
   }

   DestroyContactNode(listHead);
   DestroyContactNode(middleNode);
   DestroyContactNode(tailNode);

   fprintf(stderr, "%s passed\n", testName);
   return 0;
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

   if (argc != 3)
   {
      printf("usage: %s -t <test num>\n", argv[0]);
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
      case 2:
         /* Safe Path */
         status = testInsertAfterNode();
         break;
      case 3:
         /* Safe Path */
         status = testInsertAfterNodeMiddle();
         break;
      case 4:
         /* Safe Path */
         status = testGetNextAfterInsert();
         break;
      default:
         /* Unknown test selection */
         printf("Invalid test specified\n");
         break;
      }
   }

   return status;
}
