/* 
 * File: mytests.c
 * Author: Luke Hindman
 * Date: Mon 19 Oct 2020 02:05:20 PM PDT
 * Description: Unit tests for DataNode component of M.A.S.H. activity
 */
#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <string.h>
#include <stdlib.h>

#include "DataNode.h"

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
   char testName[] = "Unit Test - Create/Destroy DataNode:";
   DataNode *testNode = CreateDataNode("Hello World!");
   if (testNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }
   DestroyDataNode(testNode);
   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

int testCreateNull(void)
{
   char testName[] = "Unit Test - Create DataNode (NULL):";
   DataNode *testNode = CreateDataNode(NULL);
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

int testLargeString(void)
{
   char testName[] = "Unit Test - Create DataNode (Large String):";

   /* Dynamically allocate memory for string in heap */
   char *largeString = (char *)malloc(sizeof(char) * 1024 * 1024);

   /* load the string with dots */
   for (int i = 0; i < 1024 * 1024; i++)
   {
      largeString[i] = '.';
   }
   /* Null terminate the string */
   largeString[(1024 * 1024) - 1] = '\0';

   DataNode *testNode = CreateDataNode(largeString);
   if (testNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   free(largeString);
   DestroyDataNode(testNode);
   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

int testInsertAfterNode(void)
{
   char testName[] = "Unit Test - Insert PlaylistNode After (head):";
   DataNode *listHead = CreateDataNode("List Head");
   if (listHead == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   DataNode *newNode = CreateDataNode("New Node");
   if (newNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   int rc = InsertDataNodeAfter(listHead, newNode);
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

   DestroyDataNode(listHead);
   DestroyDataNode(newNode);

   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

int testInsertAfterNodeMiddle(void)
{
   char testName[] = "Unit Test - Insert PlaylistNode After (middle):";
   DataNode *listHead = CreateDataNode("List Head");
   if (listHead == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   DataNode *tailNode = CreateDataNode("Tail Node");
   if (tailNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   int rc = InsertDataNodeAfter(listHead, tailNode);
   if (rc < 0)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   DataNode *middleNode = CreateDataNode("Middle Node");
   if (middleNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   rc = InsertDataNodeAfter(listHead, middleNode);
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

   DestroyDataNode(listHead);
   DestroyDataNode(middleNode);
   DestroyDataNode(tailNode);

   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

int testInsertAfterNodeNullListNode(void)
{
   char testName[] = "Unit Test - Insert PlaylistNode After (NULL list):";
   DataNode *listHead = NULL;

   DataNode *newNode = CreateDataNode("New Node");
   if (newNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   int rc = InsertDataNodeAfter(listHead, newNode);
   if (rc < 0)
   {
      fprintf(stderr, "%s passed\n", testName);
      DestroyDataNode(newNode);
      return 0;
   }

   fprintf(stderr, "%s failed\n", testName);
   return 1;
}

int testInsertAfterNodeNullNewNode(void)
{
   char testName[] = "Unit Test - Insert DataNode After (NULL newNode):";
   DataNode *newNode = NULL;

   DataNode *listHead = CreateDataNode("List Head");
   if (listHead == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   int rc = InsertDataNodeAfter(listHead, newNode);
   if (rc < 0)
   {
      fprintf(stderr, "%s passed\n", testName);
      DestroyDataNode(listHead);
      return 0;
   }

   fprintf(stderr, "%s failed\n", testName);
   return 1;
}

int testSetNextNode(void)
{
   char testName[] = "Unit Test - Set Next Node:";
   DataNode *listHead = CreateDataNode("List Head");
   if (listHead == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   DataNode *newNode = CreateDataNode("New Node");
   if (newNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   int rc = SetNextDataNode(listHead, newNode);
   if (rc < 0)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   /* test to confirm the values were set properly */
   if (listHead->nextNodePtr != newNode)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   fprintf(stderr, "%s passed\n", testName);
   DestroyDataNode(listHead);
   DestroyDataNode(newNode);
   return 0;
}

int testSetNextNodeThisNull(void)
{
   char testName[] = "Unit Test - Set Next Node (NULL nodeInList):";
   DataNode *listHead = NULL;

   DataNode *newNode = CreateDataNode("New Node");
   if (newNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   int rc = SetNextDataNode(listHead, newNode);
   if (rc < 0)
   {
      fprintf(stderr, "%s passed\n", testName);
      DestroyDataNode(newNode);
      return 0;
   }

   fprintf(stderr, "%s failed\n", testName);
   DestroyDataNode(newNode);
   return 1;
}

int testSetNextNodeNewNull(void)
{
   char testName[] = "Unit Test - Set Next Node (NULL newNode):";
   DataNode *listHead = CreateDataNode("List Head");
   if (listHead == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   DataNode *newNode = NULL;

   int rc = SetNextDataNode(listHead, newNode);
   if (rc < 0)
   {
      fprintf(stderr, "%s failed\n", testName);
      DestroyDataNode(listHead);
      return 1;
   }

   fprintf(stderr, "%s passed\n", testName);
   DestroyDataNode(listHead);
   return 0;
}

int testGetNextAfterInsert(void)
{

   char testName[] = "Unit Test - Get Next After Insert:";
   DataNode *listHead = CreateDataNode("List Head");
   if (listHead == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   DataNode *tailNode = CreateDataNode("Tail Node");
   if (tailNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   int rc = InsertDataNodeAfter(listHead, tailNode);
   if (rc < 0)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   DataNode *middleNode = CreateDataNode("Middle Node");
   if (middleNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   rc = InsertDataNodeAfter(listHead, middleNode);
   if (rc < 0)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   /* Double check to make sure the values were set properly */
   if (GetNextDataNode(listHead) != middleNode ||
       GetNextDataNode(middleNode) != tailNode ||
       GetNextDataNode(tailNode) != NULL)
   {
      fprintf(stderr, "%s failed\n", testName);

      return 1;
   }

   DestroyDataNode(listHead);
   DestroyDataNode(middleNode);
   DestroyDataNode(tailNode);

   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

int testGetNextNull(void) {

   char testName[] = "Unit Test - Get Next NULL:";
   if (GetNextDataNode(NULL) != NULL) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   }
   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

int testPrintDataNode(void)
{

   char testName[] = "Unit Test - Print DataNode:";

   DataNode *newNode = CreateDataNode("Red Sector A");
   if (newNode == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }
   printf("%s - Test Output: ", testName);
   PrintDataNode(newNode);
   // printf("\n%s - Expected Output: Red Sector A\n", testName);

   DestroyDataNode(newNode);
   return 0;
}

int testBuildDestroyDataList(void)
{

   char testName[] = "Unit Test - Build/Destroy Data List:";
   char *mydata[] = {"mansion", "apartment", "shack", "house"};
   DataNode *listHead = BuildDataList(mydata, 4);

   if (listHead == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   /* Cleanup the List */
   DestroyDataList(listHead);
   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

int testGetDataListSize(void)
{

   char testName[] = "Unit Test - Get Data List Size:";
   char *mydata[] = {"mansion", "apartment", "shack", "house"};
   DataNode *listHead = BuildDataList(mydata, 4);

   if (listHead == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   if (GetDataListSize(listHead) != 4)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   /* Cleanup the List */
   DestroyDataList(listHead);
   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

int testVerifyListContents(void)
{

   char testName[] = "Unit Test - Verify Data List Contents:";
   char *mydata[] = {"mansion", "apartment", "shack", "house"};
   DataNode *listHead = BuildDataList(mydata, 4);

   if (listHead == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   /* Walk through each node in the list and compare it to 
    *   the corresponding element in the array */
   int index = 0;
   DataNode *current = listHead;
   while (current != NULL)
   {
      if (strcmp(current->dataValue, mydata[index]) != 0)
      {
         fprintf(stderr, "%s failed\n", testName);
         fprintf(stderr, "\texpected: %s\n", mydata[index]);
         fprintf(stderr, "\t   found: %s\n", current->dataValue);
         return 1;
      }

      /* Move to the next node / index */
      index++;
      current = current->nextNodePtr;
   }

   /* Cleanup the List */
   DestroyDataList(listHead);
   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

int testPrintDataList(void)
{

   char testName[] = "PrintDataList Test";
   char *mydata[] = {"mansion", "apartment", "shack", "house"};
   DataNode *listHead = BuildDataList(mydata, 4);

   if (listHead == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   printf("%s - Test Output: ", testName);
   PrintDataList(listHead);

   /* Disabled because output is checked in test harness */
   // printf("\n%s - Expected Output: ", testName);

   // for (int i = 0; i < 4; i++)
   // {
   //    printf("%s%s", mydata[i], (i == 3) ? "" : ", ");
   // }
   // printf("\n");

   /* Cleanup the List */
   DestroyDataList(listHead);

   fprintf(stderr, "%s passed (pending manual review)\n", testName);
   return 0;
}

int testGetRandomDataNode(void)
{

   char testName[] = "Unit Test - Get Random DataNode:";
   char *mydata[] = {"mansion", "apartment", "shack", "house"};
   DataNode *listHead = BuildDataList(mydata, 4);

   if (listHead == NULL)
   {
      fprintf(stderr, "%s failed\n", testName);
      return 1;
   }

   printf("%s - Test Output: ", testName);

   int numTests = 100;
   DataNode *current = NULL;
   for (int i = 0; i < numTests; i++)
   {
      current = GetRandomDataNode(listHead);
      if (current == NULL)
      {
         fprintf(stderr, "%s failed\n", testName);
         return 1;
      }
      else
      {
         printf("%s%s", current->dataValue, (i == numTests - 1) ? "" : ", ");
      }
   }
   printf("\n");

   /* Cleanup the List */
   DestroyDataList(listHead);
   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

int runall(void)
{

   int status = 0;
   status += testCreateDestroy();
   status += testLargeString();
   status += testCreateNull();
   status += testInsertAfterNode();
   status += testInsertAfterNodeMiddle();
   status += testInsertAfterNodeNullListNode();
   status += testInsertAfterNodeNullNewNode();
   status += testSetNextNode();
   status += testSetNextNodeThisNull();
   status += testSetNextNodeNewNull();
   status += testGetNextAfterInsert();
   status += testGetNextNull();
   status += testPrintDataNode();
   status += testBuildDestroyDataList();
   status += testGetDataListSize();
   status += testVerifyListContents();
   status += testPrintDataList();
   status += testGetRandomDataNode();

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
         status = testLargeString();
         break;
      case 3:
         /* Safe Path */
         status = testCreateNull();
         break;
      case 4:
         /* Safe Path */
         status = testInsertAfterNode();
         break;
      case 5:
         /* Safe Path */
         status = testInsertAfterNodeMiddle();
         break;
      case 6:
         /* Invalid Test*/
         status = testInsertAfterNodeNullListNode();
         break;
      case 7:
         /* Safe Path */
         status = testInsertAfterNodeNullNewNode();
         break;
      case 8:
         /* Safe Path */
         status += testSetNextNode();
         break;
      case 9:
         /* Invalid Test*/
         status += testSetNextNodeThisNull();
         break;
      case 10:
         /* Safe Path */
         status += testSetNextNodeNewNull();
         break;
      case 11:
         /* Safe Path */
         status += testGetNextAfterInsert();
         break;
      case 12:
         status += testGetNextNull();
         break;
      case 13:
         status += testPrintDataNode();
         break;
      case 14:
         status += testBuildDestroyDataList();
         break;
      case 15:
         status += testGetDataListSize();
         break;
      case 16:
         status += testVerifyListContents();
         break;
      case 17:
         status += testPrintDataList();
         break;
      case 18:
         status += testGetRandomDataNode();
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

