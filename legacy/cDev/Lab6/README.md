# Lab06 Guide
## Getting Started
[Lab Introduction Video](https://youtu.be/Ar-woGf71tQ)  

### Code Style Requirements
Please review the [CS253 Style Guide](https://docs.google.com/document/d/1zKIpNfkiPpDHEvbx8XSkZbUEUlpt8rnZjkhCSvM-_3A/edit?usp=sharing) and apply it in all lab warmups, lab activities and projects this semester. Coding Style will assessed as part of your lab and project grades.

### Code Quality Requirements
- Code must compile without warnings using the provided Makefile
- Programs must handle unexpected user input and either reprompt (loops) or gracefully exit with a non-zero exit status.
- Programs must handle error conditions gracefully, without crashing, ideally by checking the returns codes (if available) and returning a non-zero exit status.
- Programs should be free of memory related errors, buffer overflows, stack smashing, etc... Whether the program crashes or not.

## Lab Warmup - Team Weight Tracker
[Walkthrough Video](https://www.youtube.com/playlist?list=PLvnIObHoMl8eEBDgbh2r7NUh3ujzNqxzV)  

### Problem Description

Output each floating-point value with two digits after the decimal point, which can be achieved as follows:<br />`printf("%0.2lf", yourValue);`

<br />
1. Prompt the user to enter five numbers, being five people's weights. Store the numbers in an array of doubles. Output the array's numbers on one line, each number followed by one space.  
<br /><br />

```
Enter weight 1:
236.0
Enter weight 2:
89.5
Enter weight 3:
142.0
Enter weight 4:
166.3
Enter weight 5:
93.0
You entered: 236.00 89.50 142.00 166.30 93.00

```
<br /> 
2. Also output the total weight, by summing the array's elements. 

<br /> 
3.  Also output the average of the array's elements.  

<br /> 
4.  Also output the max array element.  
<br /><br />

```
Enter weight 1:
236.0
Enter weight 2:
89.5
Enter weight 3:
142.0
Enter weight 4:
166.3
Enter weight 5:
93.0
You entered: 236.00 89.50 142.00 166.30 93.00 

Total weight: 726.80
Average weight: 145.36
Max weight: 236.00

```

Output each floating-point value with two digits after the decimal point, which can be achieved as follows:<br />`printf("%0.2lf", yourValue);`


### Error Handling
The following describes the expected behavior in the event of unexpected user input
- If the user inputs a non-numerical weight value, simply prompt for the weight value again.
- Something to think about... Does it make sense to allow the user to enter negative values for weight in this situation? 


### Implementation Guide
1. Expand the folder named LabWarmup and open the file named main.c
2. Enter the program code to create an application as described in the Problem Description.
3. Test the program using the example above to ensure it functions as expected.
4. Commit the changes to your local repository with a message stating that LabWarmup is completed.
5. Push the changes from your local repository to the github classroom repository.
6. Update the Coding Journal with an entry that includes the code segment where each variable is allocated, including the array.


## Lab Activity - Team Roster
### Problem Description
This program will store roster and rating information for a soccer team. Coaches rate players during tryouts to ensure a balanced team.

<br />
1.  Prompt the user to input five pairs of numbers: A player's jersey number (0 - 99) and the player's rating (1 - 9). Store the jersey numbers in one int array and the ratings in another int array. Output these arrays (i.e., output the roster).  
<br /><br />

```
Enter player 1's jersey number:
84
Enter player 1's rating:
7

Enter player 2's jersey number:
23
Enter player 2's rating:
4

Enter player 3's jersey number:
4
Enter player 3's rating:
5

Enter player 4's jersey number:
30
Enter player 4's rating:
2

Enter player 5's jersey number:
66
Enter player 5's rating:
9

ROSTER
Player 1 -- Jersey number: 84, Rating: 7
Player 2 -- Jersey number: 23, Rating: 4
...
```

<br />
2. Implement a menu of options for a user to modify the roster. Each option is represented by a single character. The program initially outputs the menu, and outputs the menu after a user chooses an option.  The program ends when the user chooses the option to Quit. For this step, the other options do nothing. The menu IS case sensitive and should NOT accept upper case values.
<br /><br />

```
MENU
u - Update player rating
a - Output players above a rating
r - Replace player
o - Output roster
q - Quit

Choose an option:
```
<br />
3.  Implement the "Output roster" menu option. 
<br /><br />

```
ROSTER
Player 1 -- Jersey number: 84, Rating: 7
Player 2 -- Jersey number: 23, Rating: 4
...
```
<br />
4. Implement the "Update player rating" menu option. Prompt the user for a player's jersey number. Prompt again for a new rating for the player, and then change that player's rating. 
<br /><br />

```
Enter a jersey number:
23
Enter a new rating for player:
6
```
<br />
5. Implement the "Output players above a rating" menu option. Prompt the user for a rating. Print the jersey number and rating for all players  with ratings above the entered value. 
<br /><br />

```
Enter a rating:
5

ABOVE 5
Player 1 -- Jersey number: 84, Rating: 7
...
```

<br />
6. Implement the "Replace player" menu option. Prompt the user for the jersey number of the player to replace. If the player is in the roster, then prompt again for a new jersey number and rating. Update the replaced player's jersey number and rating. 
<br /><br />

```
Enter a jersey number:
4
Enter a new jersey number:
12
Enter a rating for the new player:
8
```

### Error Handling
The following describes the expected behavior in the event of unexpected user input
- During the initial load of player information (jersey and rating), reprompt the user if a non-integer value is entered
- If the user selects an invalid menu option, display the following then re-display the menu and selection prompt
```
Error: Unknown option selected
```
- If the user enters a non-existent jersey number during update or replace operation, display the following then re-display the menu and selection prompt
```
Error: Non-existent jersey number selected, please check your data and try again
```
- If the user enters a non integer value within the update rating, output ratings above, or replace player menu options, abandon that option, display the following then re-display the menu and selection prompt
```
Error: Non-integer value detected, please check your data and try again
```

### Implementation Guide
1. Expand the folder named LabActivity and open the file named main.c
2. Enter the program code to create an application as described in the Problem Description.
3. Test the program using the provided [Lab Activity Test Plan](LabActivityTestPlan.md) to ensure it functions as expected.
4. Commit the changes to your local repository with a message stating that LabActivity is completed.
5. Push the changes from your local repository to the github classroom repository.
6. Update the Coding Journal with an entry that includes the code segment where each variable is allocated, including the array.

## Coding Journal (Optional)
Keep a journal of your activities as you work on this lab. Many of the best engineers that I have worked with professionally have kept some sort of engineering journal. I personally packed notebooks around with me for nearly 8 years before I began keeping my notes electronically.   

Your journal can track ideas, bugs, cool links, code snippets, shell commands, rants, or simply a reflection on what worked well or not-so-well with this lab activity. I will not be grading the content of your journal, but I will expect at least two timestamped journal entries of at least a 75 to 150 words each added to the provided Journal.md file.  The purpose of this component is to help develop the habit of taking notes and creating documentation while you code. The more detail you provide the better as that will help you if you ever need to refer back to this project in the future.

## Markdown Resources
Markdown is a notation that is used to format text documents.  It is widely used in Software Development shops around the world, which is why we're asking you to use it in your lab documentation.  

Github provides a guide for getting started:  [Mastering Markdown](https://guides.github.com/features/mastering-markdown/)
