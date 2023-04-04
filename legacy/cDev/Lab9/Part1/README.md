# Lab09-1 Guide
## Getting Started
[Lab Introduction Video](https://youtu.be/flEZpDLgFyM)


### Code Style Requirements
Please review the [CS253 Style Guide](https://docs.google.com/document/d/1zKIpNfkiPpDHEvbx8XSkZbUEUlpt8rnZjkhCSvM-_3A/edit?usp=sharing) and apply it in all lab warmups, lab activities and projects this semester. Coding Style will assessed as part of your lab and project grades.

### Code Quality Requirements
- Code must compile without warnings using the provided Makefile
- Programs must handle unexpected user input and either reprompt (loops) or gracefully exit with a non-zero exit status.
- Programs must handle error conditions gracefully, without crashing, ideally by checking function returns codes (if available) and returning a non-zero exit status.
- Programs should be free of memory related errors, buffer overflows, stack smashing, leaks, etc... Whether the program crashes or not. This will be validated using valgrind.

## Lab Warmup - Contacts
[Walkthrough Video](https://youtube.com/playlist?list=PLvnIObHoMl8erHXUNk9tIJCR_UrD3uplq)  

### Problem Description

*You will be building a linked list. Make sure to keep track of both the head and tail nodes.*

<br /> 
1. Create three files to submit.

- ContactNode.h - Struct definition, including the data members and related function declarations
- ContactNode.c - Related function definitions
- main.c - main() function

<br /> 
2. The ContactNode struct as well as function declarations for the related functions described below have been provided in ContactNode.h.  Please do not modify the provided ContactNode.h file. Details regarding each function as well as expected return values are included in the comments associated with each function declaration in ContactNode.h.  The following is a summary of this content:

- Data members
   - char contactName[50]
   - char contactPhoneNum[50]
   - struct ContactNode* nextNodePtr
- Related functions
   - ContactNode* CreateContactNode(char nameInit[], char phoneNumInit[]) 
      - Call malloc to allocate space for a ContactNode and return a pointer to the initialized object
   - void InsertContactAfter(ContactNode* thisNode, ContactNode* newNode) 
      - Insert a new node after node
   - ContactNode* GetNextContact(ContactNode* thisNode) 
      - Return location pointed by nextNodePtr
   - void PrintContactNode(ContactNode* thisNode)
      - Display the contents of this PlaylistNode on stdout using printf
   - void DestroyContactNode(ContactNode* thisNode)
      - Call free to release all memory dynamically allocated by malloc for the specified node


<br />  

Ex. of PrintContactNode() output:
```
Name: Roxanne Hughes
Phone number: 443-555-2864
```
<br />
3. In main(), prompt the user for three contacts and output the user's input. Create three ContactNodes and use the nodes to build a linked list. 
<br /><br />
Ex:  

```
Person 1
Enter name:
Roxanne Hughes
Enter phone number:
443-555-2864
You entered: Roxanne Hughes, 443-555-2864

Person 2
Enter name:
Juan Alberto Jr.
Enter phone number:
410-555-9385
You entered: Juan Alberto Jr., 410-555-9385

Person 3
Enter name:
Rachel Phillips
Enter phone number:
310-555-6610
You entered: Rachel Phillips, 310-555-6610
```
<br />
4. Output the linked list. 
<br /><br />
Ex:

```
CONTACT LIST
Name: Roxanne Hughes
Phone number: 443-555-2864

Name: Juan Alberto Jr.
Phone number: 410-555-9385

Name: Rachel Phillips
Phone number: 310-555-6610
```

<br />
5. Run valgrind to check for memory leaks or errors
<br /><br />

```
valgrind --tool=memcheck --leak-check=yes --show-reachable=yes ./myprog
valgrind --tool=memcheck --leak-check=yes --show-reachable=yes ./mytests -t <test_num>
```

### Error Handling
For the LabWarmup, please do not worry about user input errors in the UI. The purpose of this program is to test the ContactNode struct and supporting functions.  Please focus your attention on implementing the expected behaviour for each function declared in the ContactNode.h header file.

### Implementation Guide
1. Expand the folder named LabWarmup and open ContactNode.c, ContactNode.h and main.c
2. Enter the program code to create an application as described in the Problem Description.
3. Test the program using the above example to ensure it functions as expected.
4. Run each test case above with valgrind to catch any memory leaks or errors
5. Commit the changes to your local repository with a message stating that LabWarmup is completed.
6. Push the changes from your local repository to the github classroom repository.
7. Update the Coding Journal with an entry describing your experience using the steps outlined below.


## Lab Activity
### Problem Description

*You will be building a linked list. Make sure to keep track of both the head and tail nodes.*

<br />
1. Implement the functions declared in the PlaylistNode.h in PlaylistNode.c 

- PlaylistNode.h - Struct definition and related function declarations
- PlaylistNode.c - Related function definitions
- main.c - main() function

The PlaylistNode struct as well as function declarations for the related functions described below have been provided in PlaylistNode.h.  Please do not modify the provided PlaylistNode.h file. Details regarding each function as well as expected return values are included in the comments associated with each function declaration in PlaylistNode.h.  The following is a summary of this content:  

Note: Some functions can initially be function stubs (empty functions), to be completed in later steps. 

- Private data members
   - char uniqueID[50] 
   - char songName[50] 
   - char artistName[50] 
   - int songLength 
   - PlaylistNode* nextNodePtr 
- Related functions
   - CreatePlaylistNode() 
   - InsertPlaylistNodeAfter() 
   - SetNextPlaylistNode() 
   - GetNextPlaylistNode()
   - PrintPlaylistNode()
   - DestroyPlaylistNode()

Ex. of PrintPlaylistNode output:
```
Unique ID: S123
Song Name: Peg
Artist Name: Steely Dan
Song Length (in seconds): 237
```

<br />
2. In main(), prompt the user for the title of the playlist. 
<br /><br />
Ex:

```
Enter playlist's title:
JAMZ 
```
<br />
3. Implement the PrintMenu() function. PrintMenu() takes the playlist title as a parameter and outputs a menu of options to manipulate the playlist. Each option is represented by a single character. Build and output the menu within the function.

If an invalid character is entered, continue to prompt for a valid choice. Hint: Implement Quit before implementing other options. Call PrintMenu() in the main() function. Continue to execute the menu until the user enters q to Quit. 
<br /><br />
Ex:
```
JAMZ PLAYLIST MENU
a - Add song
r - Remove song
c - Change position of song
s - Output songs by specific artist
t - Output total time of playlist (in seconds)
o - Output full playlist
q - Quit

Choose an option:
```
<br />
4. Implement "Output full playlist" menu option. If the list is empty, output: `Playlist is empty` 
<br /><br />
Ex:

```
JAMZ - OUTPUT FULL PLAYLIST
1.
Unique ID: SD123
Song Name: Peg
Artist Name: Steely Dan
Song Length (in seconds): 237

2.
Unique ID: JJ234
Song Name: All For You
Artist Name: Janet Jackson
Song Length (in seconds): 391

3.
Unique ID: J345
Song Name: Canned Heat
Artist Name: Jamiroquai
Song Length (in seconds): 330

4.
Unique ID: JJ456
Song Name: Black Eagle
Artist Name: Janet Jackson
Song Length (in seconds): 197

5. 
Unique ID: SD567
Song Name: I Got The News
Artist Name: Steely Dan
Song Length (in seconds): 306
```
<br />
5. Implement the "Add song" menu item. New additions are added to the end of the list. 
<br /><br />
Ex:

```
ADD SONG
Enter song's unique ID:
SD123
Enter song's name:
Peg
Enter artist's name:
Steely Dan
Enter song's length (in seconds):
237
```
<br />
6. Implement the "Remove song" function. Prompt the user for the unique ID of the song to be removed.
<br /><br />
Ex:

```
REMOVE SONG
Enter song's unique ID:
JJ234
"All For You" removed
```
<br />
7. Implement the "Change position of song"  menu option. Prompt the user for the current position of the song and the desired new position. Valid new positions are 1 - *n* (the number of nodes). If the user enters a new position that is less than 1, move the node to the position 1 (the head). If the user enters a new position greater than *n*, move the node to position *n* (the tail). 6 cases will be tested:

- Moving the head node 
- Moving the tail node 
- Moving a node to the head 
- Moving a node to the tail 
- Moving a node up the list 
- Moving a node down the list 
<br />

Ex:
```
CHANGE POSITION OF SONG
Enter song's current position:
3
Enter new position for song:
2
"Canned Heat" moved to position 2
```
<br />
8. Implement the "Output songs by specific artist" menu option. Prompt the user for the artist's name, and output the node's information, starting with the node's current position. 
<br /><br />

Ex:
```
OUTPUT SONGS BY SPECIFIC ARTIST
Enter artist's name:
Janet Jackson

2.
Unique ID: JJ234
Song Name: All For You
Artist Name: Janet Jackson
Song Length (in seconds): 391

4.
Unique ID: JJ456
Song Name: Black Eagle
Artist Name: Janet Jackson
Song Length (in seconds): 197
```
<br />
9. Implement the "Output total time of playlist" menu option. Output the sum of the time of the playlist's songs (in seconds). 
<br /><br />

Ex:
```
OUTPUT TOTAL TIME OF PLAYLIST (IN SECONDS)
Total time: 1461 seconds
```

<br />
10. Run valgrind to check for memory leaks or errors
<br /><br />

```
valgrind --tool=memcheck --leak-check=yes --show-reachable=yes ./myprog
valgrind --tool=memcheck --leak-check=yes --show-reachable=yes ./mytests -t <test_num>
```


### Error Handling
The following describes the expected behavior when functions return errors unless the error is an expected behavior and part of the core application logic.  For example, GetNextPlaylistNode() returns NULL when it reaches the end of the list.
- if a call to CreatePlaylistNode() returns NULL, display the following message, display the menu and prompt for a selection
```
Error: Unable to create PlaylistNode
```
- if a call to InsertPlaylistNOdeAfter() returns -1, display the following message, display the menu and prompt for a selection
```
Error: Unable to insert PlaylistNode
```
- if a call to SetNextPlaylistNode() returns -1, display the following message, display the menu and prompt for a selection
```
Error: Unable to set next PlaylistNode
```

### Implementation Guide
1. Expand the folder named LabActivity and open PlaylistNode.c, PlaylistNode.h, main.c
2. Enter the program code to create an application as described in the Problem Description.
3. Test the program using the provided [Lab Activity Test Plan](LabActivityTestPlan.md) to ensure it functions as expected.
4. Run the program with valgrind to catch any memory leaks or errors
5. Commit the changes to your local repository with a message stating that LabActivity is completed.
6. Push the changes from your local repository to the github classroom repository.
7. Update the Coding Journal with an entry describing your experience using the steps outlined below.

## Coding Journal (Optional)
Keep a journal of your activities as you work on this lab. Many of the best engineers that I have worked with professionally have kept some sort of engineering journal. I personally packed notebooks around with me for nearly 8 years before I began keeping my notes electronically.   

Your journal can track ideas, bugs, cool links, code snippets, shell commands, rants, or simply a reflection on what worked well or not-so-well with this lab activity. I will not be grading the content of your journal, but I will expect at least two timestamped journal entries of at least a 75 to 150 words each added to the provided Journal.md file.  The purpose of this component is to help develop the habit of taking notes and creating documentation while you code. The more detail you provide the better as that will help you if you ever need to refer back to this project in the future.

## Markdown Resources
Markdown is a notation that is used to format text documents.  It is widely used in Software Development shops around the world, which is why we're asking you to use it in your lab documentation.  

Github provides a guide for getting started:  [Mastering Markdown](https://guides.github.com/features/mastering-markdown/)
