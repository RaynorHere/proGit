# Lab09-2 Guide
## Getting Started

[Lab Introduction Video](https://youtu.be/h-jyX6pC11I)

### Code Style Requirements
Please review the [CS253 Style Guide](https://docs.google.com/document/d/1zKIpNfkiPpDHEvbx8XSkZbUEUlpt8rnZjkhCSvM-_3A/edit?usp=sharing) and apply it in all lab warmups, lab activities and projects this semester. Coding Style will assessed as part of your lab and project grades.

### Code Quality Requirements
- Code must compile without warnings using the provided Makefile
- Programs must handle unexpected user input and either reprompt (loops) or gracefully exit with a non-zero exit status.
- Programs must handle error conditions gracefully, without crashing, ideally by checking function returns codes (if available) and returning a non-zero exit status.
- Programs should be free of memory related errors, buffer overflows, stack smashing, leaks, etc... Whether the program crashes or not. This will be validated using valgrind.

## Lab Activity - M.A.S.H.
### Problem Description
M.A.S.H. is a text-based game that will predict your future!  M.A.S.H. is an abbreviation for the potential future places of residence: Mansion, Apartment, Shack, House. :)

This activity provides experience adding items to, selecting items from, and iterating over LinkedLists. It also provides experience with Dynamic Memory allocation, pointers, arrays and structs. 

<br />
1. Carefully study the DataNode header file (DataNode.h)
<br /><br />
The DataNode struct as well as function declarations for the related functions described below have been provided in DataNode.h. Please do not modify the provided DataNode.h file. Details regarding each function as well as expected return values are included in the comments associated with each function declaration in DataNode.h. The following is a summary of this content:

- Data members
  - char * dataValue
  - int dataSize
  - struct DataNode* nextNodePtr
- Related functions
  - DataNode* CreateDataNode(const char data[])  
  - int InsertDataNodeAfter(DataNode* nodeInList, DataNode* newNode)  
  - int SetNextDataNode(DataNode* nodeInList, DataNode* newNode)  
  - DataNode* GetNextDataNode(DataNode* nodeInList)  
  - void PrintDataNode(DataNode* thisNode)
  - void DestroyDataNode(DataNode* thisNode)
  - DataNode* BuildDataList(char * data[])
  - int GetDataListSize(DataNode* listHead)
  - void PrintDataList(DataNode *listHead)
  - DataNode* GetRandomDataNode(DataNode *listHead)
  - void DestroyDataList(DataNode* listHead)


<br />
2. Implement each of the related functions listed above (DataNode.c)  
<br /><br />
Test each function as it is written by developing testcases in mytests.c. Be sure to run the tests through valgrind to catch any memory related errors that might not be immediately visible during normal safepath testing.

```
valgrind --tool=memcheck --leak-check=yes --show-reachable=yes ./mytests -t <test_num>
```

NOTE: If your testing does note seem to produce random results when calling GetRandomDataNode, this is likely because rand() has a default seed value of 1.  To change the seed value to the current time you will need to include <time.h> and then make the following call to srand() in main() before your first call to rand().  It is important that you  call srand() once and only once in your program.

```
srand(time(0));
```

<br />
3. Implement M.A.S.H. Game Database (main.c)
<br /><br />
Use an array of DataNode pointers to store the game data, with an ENUM for the indexes as follows:  


- database[HOME_LIST] ---->>>>>  DataNode* listHead for list of homes  
- database[FEMALE_SPOUSE_LIST] ---->>>>>  DataNode* listHead for list of female spouses  
- database[MALE_SPOUSE_LIST] ---->>>>>  DataNode* listHead for list of male spouses  
- database[OCCUPATION_LIST] ---->>>>> DataNode* listHead for list of occupations  
- database[TRANSPORTATION_LIST] ---->>>>> DataNode* listHead for list of transportion - - methods  
- database[HOMETOWN_LIST] ---->>>>> DataNode* listHead for list of hometowns  

The data values should be specified in an char array of strings by using an initializer list.  This array will be passed to the BuildDataList function as a parameter which will in turn construct the list, allocating memory as required, and return a pointer to the listHead. This pointer should be stored at the cooresponding index in the database.

NOTE:  Each list should contain a minimum of 5 options.

<br />
4. Display M.A.S.H. Game Database (main.c)
<br /><br />
Display the contents of the M.A.S.H. Game Database by calling the PrintDataList function on each list in the database array.


```
--------------------- Future Possibilities Database ------------------------
Home List: mansion, apartment, shack, house 
Female Spouse List: Robin, Lily, Nora, Patrice, Zoey, Quinn
Male Spouse List: Ted, Marshall, Barney, Ranjit, Carl, Linus
Occupation List: teacher, architect, lawyer, newscaster, undercover agent
Transportation List: walk, ride the train, ride a bus, fly an airplane, carpool
Hometown List: Cleveland, Queens, The Bronx, Brooklyn, Manhattan, Staten Island
----------------------------------------------------------------------------

```
<br />
5. Implement M.A.S.H. Game UI (main.c)
<br /><br />
Prompt the user for their name and store to a local variable. Use the GetRandomDataNode() function to make a random selection from each list in the database and store a pointer to the nodes' dataValue in a local variable.  Then these values to construct the user's future and display it in the console.

```
Please enter your name: Luke
 
 
Welcome Luke, this is your future... 
You will marry Barney and live in a shack. 
After 7 years of marriage, you will finally get your dream job of being a teacher. 
Your family will move to a apartment in The Bronx where you will walk to work each day. 

```
<br />
6. Free dynamic memory (main.c)
<br /><br />
Use the DestroyDataList() function to free the memory allocated for each of the lists in the database.  


<br /> <br />
7. Run valgrind to check for memory leaks or errors
<br /><br />

```
valgrind --tool=memcheck --leak-check=yes --show-reachable=yes ./myprog
valgrind --tool=memcheck --leak-check=yes --show-reachable=yes ./mytests -t <test_num>
```

### Implementation Guide
1. Expand the folder named LabActivity and open the files named DataNode.c, DataNode.h, mytests.c and main.c
2. Use the comments in DataNode.h to implement the related functions in DataNode.c
3. Implement test cases in mytests.c to validate each function behaves as expected. This is known as unit testing and you can find a list of suggested tests in the [Lab Activity Test Plan](LabActivityTestPlan.md). Be sure to run these tests with valgrind as indicated in the Problem Description above.
4. Enter the program code to create the M.A.S.H Game as described in the Problem Description.
5. Test the program using the example above to ensure it functions as expected.
6. Run the program with valgrind to catch any memory leaks or errors
7. Commit the changes to your local repository with a message stating that LabActivity is completed.
8. Push the changes from your local repository to the github classroom repository.
9. Update the Coding Journal with an entry describing your experience using the steps outlined below.

## Coding Journal (Optional)
Keep a journal of your activities as you work on this lab. Many of the best engineers that I have worked with professionally have kept some sort of engineering journal. I personally packed notebooks around with me for nearly 8 years before I began keeping my notes electronically.   

Your journal can track ideas, bugs, cool links, code snippets, shell commands, rants, or simply a reflection on what worked well or not-so-well with this lab activity. I will not be grading the content of your journal, but I will expect at least two timestamped journal entries of at least a 75 to 150 words each added to the provided Journal.md file.  The purpose of this component is to help develop the habit of taking notes and creating documentation while you code. The more detail you provide the better as that will help you if you ever need to refer back to this project in the future.

## Markdown Resources
Markdown is a notation that is used to format text documents.  It is widely used in Software Development shops around the world, which is why we're asking you to use it in your lab documentation.  

Github provides a guide for getting started:  [Mastering Markdown](https://guides.github.com/features/mastering-markdown/)
