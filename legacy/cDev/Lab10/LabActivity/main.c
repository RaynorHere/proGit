#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <errno.h>
#include "Student.h"


// We passed a size argument last time, here we don't, and it looks
// like the base value is 8, judging by sample output
const int BASEARRAYSIZE = 8; 

// Gonna need this to be universal, too


// I was gonna make an expand function, but it seems with realloc(), we might not need it?
// We'll make our expand function up top
// Student ** expandArray(Student **arrayToUse) {
   
//    // Create larger array
//    Student** newBook = (Student **) malloc(sizeof(Student *) * BASEARRAYSIZE * multiplier);

//    // Increment multipleir
//    multiplier++;

//    // Walk through old array, copying values in
//    int arrayPlace = 0;

//    while (arrayToUse[arrayPlace] != NULL) {

//       // Have to declare these for ripping?
//       char oldFirst[STUDENT_FIELD_SIZE];
//       char oldLast[STUDENT_FIELD_SIZE];

//       // Rip` old data
//       strcpy(oldFirst, arrayToUse[arrayPlace]->firstname);
//       strcpy(oldLast, arrayToUse[arrayPlace]->lastname);
//       int oldID = arrayToUse[arrayPlace]->id;
//       int oldScore = arrayToUse[arrayPlace]->score;
//       Student *transferStudent = CreateStudent(oldLast, oldFirst, oldID, oldScore);

//       // Have to create new student?
//       newBook[arrayPlace] = transferStudent;
      
//       // Free old value
//       DestroyStudent(arrayToUse[arrayPlace]);

//       // Advance to next place
//       arrayPlace++;      
//    }

//    return newBook;   
// }

int main(int argc, char *argv[])
{

   Student **gradeBook;

   if (argc != 2)
   {
      fprintf(stderr, "Usage: %s, <catalog.csv>\n", argv[0]);
      exit(1);
   }

   char *fileNameIn = argv[1];

   FILE *fileIn = fopen(fileNameIn, "r");

   // Straight out of the warmup, but I don't recall it working properly there
   // errno = 0;
   // if (fileIn == NULL) {
   //    perror("fopen");
   //    exit(1);
   // }

   // First array, size
   gradeBook = ((Student **)malloc(sizeof(Student *) * 8));

   // I feel like this is largely useless, but it's about consistency to learn
   if (gradeBook == NULL) {
      fprintf(stderr, "Memory allocation error, but that's all I can tell you.\n");
      exit(1);
   }

   // Node elements
   char lastNameIn[STUDENT_FIELD_SIZE];
   char firstNameIn[STUDENT_FIELD_SIZE];
   int id;
   int score;  

   // Scanning elements
   int studentInfos = 0;
   int studentPopulation = 0;
   int currentListMax = 8;

   // Read the CSV
   while (!feof(fileIn)) {

      // Every student should have four attributes
      studentInfos = fscanf(fileIn, "%39[^,],%39[^,],%d,%d\n", lastNameIn, firstNameIn, &id, &score);

      // All four attributes means a new node and it goes in the list
      if (studentInfos == 4) {
         gradeBook[studentPopulation] = CreateStudent(lastNameIn, firstNameIn, id, score);
      }

      // If someone's missing something, we can't create a node and we have an error
      else {
         fprintf(stderr, "Error, student #%d is missing attributes.\n", studentPopulation);
      }

      // Array expansion, I hope. Should trip when we've filled out the last spot that the
      // array in its current form has open
      if (studentPopulation+1 == currentListMax) {

         // CurrentListPop should be the old array size because we haven't changed it
         // First execution: 8 & 2^1 =16; second execution: 8 * 2^2 = 32; etc
         fprintf(stdout, "Growing array from size %d to %d.\n", currentListMax, currentListMax*2);

         // I'm TOLD this will double the size of the array, copy contents over, and free the old array
         // I'm REALLY hoping it works that way
         gradeBook = ((Student**) realloc(gradeBook, (currentListMax * 2) * sizeof(Student *)));

         // Now, move the goalposts, so to speak. This should be all we need to change
         currentListMax *= 2;
      }
      studentPopulation++;
   }

   // File's job is done
   fclose(fileIn);

   // Output results
   fprintf(stdout, "Successfully loaded %d entries.\n", studentPopulation); 

   // Now we gotta traverse and sort. You know what would be REALLY sick is if I could
   // finagle it to do a radixSort, but I only mostly know what that would look like
   // and I've another test next week; time is a luxury I do not have. Might come back
   // to this, though. Friggin' love programming

   // This is disgustingly automated. I'm offended I'm so surprised at it
   qsort(gradeBook, studentPopulation, sizeof(Student*), CompareStudents);
   
   // Print out array, hopefully sorted?
   // Appears to work. Now destroy
   for (int i = 0; i <studentPopulation; i++) {
      PrintStudent(gradeBook[i]);
      DestroyStudent(gradeBook[i]);
   }

   // The last hoarcrux
   free(gradeBook);

   // We are showing 0 compilation errors, 0 valgrind errors, and 0 memory leaks. I believe we are finished.

   return 0;
}
