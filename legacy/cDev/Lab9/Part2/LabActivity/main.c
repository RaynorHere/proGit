/*
*   DataNode.c - Code definitions for the DataNode struct as defined by DataNode.h
*   Author: James "JCIII" Crowell
*   Date: 10/25/2021
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "DataNode.h"

// Gonna keep this fraggin' thing with me for the rest of my *life*
   void NewLineTrim(char *line) {
    size_t lineLength;

    if (line == NULL)
    {
        return;
    }

    lineLength = strlen(line);
    if (line[lineLength - 1] == '\n')
    {
        line[lineLength - 1] = '\0';
    }
}

int main(void) {   

   // Enumerations
   enum LIST_NAMES {HOME_LIST, FEMALE_SPOUSES, MALE_SPOUSES, TRANSPORTATIONS, CAREERS, TERRITORIES};

   // Array of pointers required for buliding data list
   DataNode* database[5];

   // Create the lists, because we can't send these arguments as part of BuildDataList. Apparently
   // Also, if you're actually reading this, yes, I AM a raging nerd, and have also been playing through the entire Castlevania/Bloodstained series
   char *homeList[] = {"Mansion","Apartment","Shack","House"};
   char *femSpouse[] = {"Victoria", "Emily", "Elizabeth", "Jennifer", "Ada"};
   char *malSpouse[] = {"Zangetsu", "Gebel", "Alfred", "Johannes", "Garrett"};
   char *transport[] = {"walk", "drive a Ranger", "fly a Cessna", "sail a Whaler", "teleport"};
   char *careers[] = {"Vampire Hunter", "CybSec Specialist", "Monk", "Demon Slayer", "Sorceror"};
   char *territories[] = {"Portland", "Guangzhou", "Haneda", "Ha'erbin", "Seattle"};

   // Populate the 2D array that I think this is
   database[HOME_LIST] = BuildDataList(homeList, 4);
   database[FEMALE_SPOUSES] = BuildDataList(femSpouse, 5);
   database[MALE_SPOUSES] = BuildDataList(malSpouse, 5);
   database[TRANSPORTATIONS] = BuildDataList(transport, 5);
   database[CAREERS] = BuildDataList(careers, 5);
   database[TERRITORIES] = BuildDataList(territories, 5);

   // Now, print out all elements of all lists
   printf("\nTHE DATABASE O' FUTURES:\n");
   printf("Home List: ");
   PrintDataList(database[HOME_LIST]);
   printf("Female Spouses List: ");
   PrintDataList(database[FEMALE_SPOUSES]);
   printf("Male Spouses: ");
   PrintDataList(database[MALE_SPOUSES]);
   printf("Transportations: ");
   PrintDataList(database[TRANSPORTATIONS]);
   printf("Careers: ");
   PrintDataList(database[CAREERS]);

  
   // Let's get down to it
   printf("\nWelcome, player! Do tell me your name, if you please:\n");
   char playerName[50];
   fgets(playerName, 49, stdin);
   NewLineTrim(playerName);

    // Random seed
    srand(time(0));

   int orientation = rand() % 2;

   printf("Welcome, %s; BEHOLD YOUR FATE!\n", playerName);
   printf("Y'all gonna marry %s and live in a %s.\n", 
            (orientation == 1) ? GetRandomDataNode(database[FEMALE_SPOUSES])->dataValue : GetRandomDataNode(database[MALE_SPOUSES])->dataValue, GetRandomDataNode(database[HOME_LIST])->dataValue);
   printf("After %d years of marriage, you will manage to obtain your dream job, being a %s.\n", rand() % 10, GetRandomDataNode(database[CAREERS])->dataValue);
   printf("Your family will move to a %s in %s where you will %s to work, four days a week. Because we will have implemented the four-day work week by this point.\n", 
                                            GetRandomDataNode(database[HOME_LIST])->dataValue, GetRandomDataNode(database[TERRITORIES])->dataValue, GetRandomDataNode(database[TRANSPORTATIONS])->dataValue);


    // And finally, shut it all down
    DestroyDataList(database[HOME_LIST]);
    DestroyDataList(database[FEMALE_SPOUSES]);
    DestroyDataList(database[MALE_SPOUSES]);
    DestroyDataList(database[TRANSPORTATIONS]);
    DestroyDataList(database[CAREERS]);
    DestroyDataList(database[TERRITORIES]);

   return 0;
}