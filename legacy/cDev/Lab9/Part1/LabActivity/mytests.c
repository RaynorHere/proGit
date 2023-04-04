/* 
 * Author: Luke Hindman
 * Date: Mon 22 Mar 2021 04:47:36 PM MDT
 * Description:  Unit test harness for PlaylistNode API
 */
#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <string.h>
#include <stdlib.h>

#include "PlaylistNode.h"

#define UNUSED(x) (void)(x)
#define DISP_MSG(MSG) if(write(STDOUT_FILENO,MSG,strlen(MSG)) == -1) perror("write");

/* Define error handler */
static void handler(int sig, siginfo_t *si, void *unused)
{
	UNUSED(sig);
	UNUSED(unused);
    if (si->si_signo == SIGSEGV) {
		DISP_MSG("failed (segfault)\n")
        exit(1);
    } 
}

/* Safe Path - Create and Destroy Node */
int testCreateDestroy(void) {
   char testName[] = "Unit Test - Create/Destroy PlaylistNode:";
   PlaylistNode * testNode = CreatePlaylistNode("SD123", "Peg", "Steely Dan", 237);
   if (testNode == NULL) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   } 
   DestroyPlaylistNode(testNode);
   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

int testInsertAfterNode(void) {
   char testName[] = "Unit Test - Insert PlaylistNode After (tail):";
   PlaylistNode * listHead = CreatePlaylistNode("SD123", "Peg", "Steely Dan", 237);
   if (listHead == NULL) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   } 

   PlaylistNode * newNode = CreatePlaylistNode("JJ234", "All For You", "Janet Jackson", 391);
   if (newNode == NULL) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   } 

   int rc = InsertPlaylistNodeAfter(listHead,newNode);
   if (rc < 0) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   }

   /* Double check to make sure the values were set properly */
   if (listHead->nextNodePtr != newNode || newNode->nextNodePtr != NULL) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   }

   DestroyPlaylistNode(listHead);
   DestroyPlaylistNode(newNode);

   fprintf(stderr, "%s passed\n", testName);
   return 0;
}

int testInsertAfterNodeMiddle(void) {
   char testName[] = "Unit Test - Insert PlaylistNode After (middle):";
   PlaylistNode * listHead = CreatePlaylistNode("SD123", "Peg", "Steely Dan", 237);
   if (listHead == NULL) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   } 

   PlaylistNode * tailNode = CreatePlaylistNode("JJ234", "All For You", "Janet Jackson", 391);
   if (tailNode == NULL) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   } 

   int rc = InsertPlaylistNodeAfter(listHead,tailNode);
   if (rc < 0) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   }

   PlaylistNode * middleNode = CreatePlaylistNode("J345", "Canned Head", "Jamiroquai", 330);
   if (middleNode == NULL) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   } 

   rc = InsertPlaylistNodeAfter(listHead,middleNode);
   if (rc < 0) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   }

   /* Double check to make sure the values were set properly */
   if (listHead->nextNodePtr != middleNode || middleNode->nextNodePtr != tailNode) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   }

   DestroyPlaylistNode(listHead);
   DestroyPlaylistNode(middleNode);
   DestroyPlaylistNode(tailNode);

   fprintf(stderr, "%s passed\n", testName);
   return 0;
}


int testSetNextNode(void) {
   char testName[] = "Unit Test - Set Next Node:";
   PlaylistNode * listHead = CreatePlaylistNode("SD123", "Peg", "Steely Dan", 237);
   if (listHead == NULL) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   } 

   PlaylistNode * newNode = CreatePlaylistNode("JJ234", "All For You", "Janet Jackson", 391);
   if (newNode == NULL) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   } 

   int rc = SetNextPlaylistNode(listHead,newNode);
   if (rc < 0) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   }

   /* test to confirm the values were set properly */
   if(listHead->nextNodePtr != newNode) {
            fprintf(stderr,"%s failed\n", testName);
      return 1;
   }

   fprintf(stderr,"%s passed\n", testName);
   DestroyPlaylistNode(listHead);
   DestroyPlaylistNode(newNode);
   return 0;
}

int testSetNextNodeThisNull(void) {
   char testName[] = "Unit Test - Set Next Node (NULL nodeInList):";
   PlaylistNode * listHead = NULL;

   PlaylistNode * newNode = CreatePlaylistNode("JJ234", "All For You", "Janet Jackson", 391);
   if (newNode == NULL) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   } 

   int rc = SetNextPlaylistNode(listHead,newNode);
   if (rc < 0) {
      fprintf(stderr,"%s passed\n", testName);
      DestroyPlaylistNode(newNode);
      return 0;
   }

   fprintf(stderr,"%s failed\n", testName);
   DestroyPlaylistNode(newNode);
   return 1;
}

int testSetNextNodeNewNull(void) {
   char testName[] = "Unit Test - Set Next Node (NULL newNode):";
   PlaylistNode * listHead = CreatePlaylistNode("SD123", "Peg", "Steely Dan", 237);
   if (listHead == NULL) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   } 

   PlaylistNode * newNode = NULL;

   int rc = SetNextPlaylistNode(listHead,newNode);
   if (rc < 0) {
      fprintf(stderr,"%s failed\n", testName);
      DestroyPlaylistNode(listHead);
      return 1;
   }

   fprintf(stderr,"%s passed\n", testName);
   DestroyPlaylistNode(listHead);
   return 0;
}


int testGetNextAfterInsert(void) {

   char testName[] = "Unit Test - Get Next After Insert:";
   PlaylistNode * listHead = CreatePlaylistNode("SD123", "Peg", "Steely Dan", 237);
   if (listHead == NULL) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   } 

   PlaylistNode * tailNode = CreatePlaylistNode("JJ234", "All For You", "Janet Jackson", 391);
   if (tailNode == NULL) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   } 

   int rc = InsertPlaylistNodeAfter(listHead,tailNode);
   if (rc < 0) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   }

   PlaylistNode * middleNode = CreatePlaylistNode("J345", "Canned Head", "Jamiroquai", 330);
   if (middleNode == NULL) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   } 

   rc = InsertPlaylistNodeAfter(listHead,middleNode);
   if (rc < 0) {
      fprintf(stderr,"%s failed\n", testName);
      return 1;
   }

   /* Double check to make sure the values were set properly */
   if (GetNextPlaylistNode(listHead) != middleNode || 
       GetNextPlaylistNode(middleNode) != tailNode ||
       GetNextPlaylistNode(tailNode) != NULL) {
      fprintf(stderr,"%s failed\n", testName);

      return 1;
   }

   DestroyPlaylistNode(listHead);
   DestroyPlaylistNode(middleNode);
   DestroyPlaylistNode(tailNode);

   fprintf(stderr, "%s passed\n", testName);
   return 0;

}

void setup_signal_handling(void) {
    /* Setup signal handling to catch segfault */
	struct sigaction sa;
    sa.sa_flags = SA_SIGINFO;
    sigemptyset(&sa.sa_mask);
    sa.sa_sigaction = handler;
    if (sigaction(SIGSEGV, &sa, NULL) == -1)
        perror("sigaction");
}

int main(int argc, char* argv[])
{
    int status = 0;

    if (argc != 3) {
		printf("usage: %s -t <test num>\n",argv[0]);
		exit(1);
	} else {
		int test_num = atoi(argv[2]);

        setup_signal_handling();

		switch(test_num) {

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
        case 5:
            /* Safe Path */
            status = testSetNextNode();
            break;
        case 6:
            /* Safe Path */
            status = testSetNextNodeNewNull();
            break;
        case 7:
            /* Invalid Case */
            status = testSetNextNodeThisNull();
            break;         

        default:
            /* Unknown test selection */
			printf("Invalid test specified\n");
            break;
        }
    }

    return status;
}
