# Lab02 Guide
## Getting Started
[Lab Introduction Video](https://youtu.be/k9AdWMrGkf0)  

## Lab Warmup - Basic output with variables
[Walkthrough Video](https://www.youtube.com/playlist?list=PLvnIObHoMl8fMTds723zaB5jxuh9SXdaK)  

### Problem Description

A variable like userNum can store a value like an integer. Extend the given program as indicated. 

1. Output the user's input.  
2. Output the input squared and cubed. _Hint: Compute squared as userNum * userNum._  
3. Get a second user input into userNum2, and output the sum and product.  

*Note: In the lab activities this semester, the output from your program should exactly match the example output for a given input (if provided).  For this warmup, your solution should exactly duplicate the output below.*  

*Note: This Lab outputs a newline after each user-input prompt. For convenience in the examples below, the user's input value is shown on the next line, but such values don't actually appear as output when the program runs.*  
```
Enter integer: 
4
You entered: 4
4 squared is 16 
And 4 cubed is 64!!
Enter another integer:
5
4 + 5 is 9
4 * 5 is 20
```


### Implementation Guide
1. Expand the folder named LabWarmup and open the file named main.c
2. Enter the program code to create an application as described in the Problem Description.
3. Test the program using the example above to ensure it functions as expected.
4. Commit the changes to your local repository with a message stating that LabWarmup is completed.
5. Push the changes from your local repository to the github classroom repository.
6. Update the Coding Journal with an entry describing your experience using the steps outlined below.


## Lab Activity
### Problem Description
This Lab activity is the traditional programming assignment, typically requiring a few hours over a week. 


1. Output this tree.  
```
   *
  ***
 *****
*******
  ***
```

2. Below the tree (with two blank lines), output this cat.
```
/\   /\
  o o
 =   =
  ---
```
*Hint: A backslash `\` in a string acts as an escape character, such as with a newline* `\n`. *So, to print an actual backslash, escape that backslash by prepending another backslash. Ex: The following prints a single backslash:* `printf("\\");`

*Note: In the lab activities this semester, the output from your program should exactly match the example output for a given input (if provided).  For this activity, your solution should exactly duplicate the output above.*

### Implementation Guide
1. Expand the folder named LabActivity and open the file named main.c
2. Enter the program code to create an application as described in the Problem Description.
3. Test the program using the provided [Lab Activity Test Plan](LabActivityTestPlan.md) to ensure it functions as expected.
4. Commit the changes to your local repository with a message stating that LabActivity is completed.
5. Push the changes from your local repository to the github classroom repository.
6. Update the Coding Journal with an entry describing your experience using the steps outlined below.

## Coding Journal (Optional)
Keep a journal of your activities as you work on this lab. Many of the best engineers that I have worked with professionally have kept some sort of engineering journal. I personally packed notebooks around with me for nearly 8 years before I began keeping my notes electronically.   

Your journal can track ideas, bugs, cool links, code snippets, shell commands, rants, or simply a reflection on what worked well or not-so-well with this lab activity. I will not be grading the content of your journal, but I will expect at least two timestamped journal entries of at least a 75 to 150 words each added to the provided Journal.md file.  The purpose of this component is to help develop the habit of taking notes and creating documentation while you code. The more detail you provide the better as that will help you if you ever need to refer back to this project in the future.

## Markdown Resources
Markdown is a notation that is used to format text documents.  It is widely used in Software Development shops around the world, which is why we're asking you to use it in your lab documentation.  

Github provides a guide for getting started:  [Mastering Markdown](https://guides.github.com/features/mastering-markdown/)
