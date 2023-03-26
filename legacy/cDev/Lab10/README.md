# Lab10 Guide
## Getting Started
[Lab Introduction Video](https://youtu.be/d5nxNjAw9qI)

### Code Style Requirements
Please review the [CS253 Style Guide](https://docs.google.com/document/d/1zKIpNfkiPpDHEvbx8XSkZbUEUlpt8rnZjkhCSvM-_3A/edit?usp=sharing) and apply it in all lab warmups, lab activities and projects this semester. Coding Style will assessed as part of your lab and project grades.

### Code Quality Requirements
- Code must compile without warnings using the provided Makefile
- Programs must handle unexpected user input and either reprompt (loops) or gracefully exit with a non-zero exit status.
- Programs must handle error conditions gracefully, without crashing, ideally by checking function returns codes (if available) and returning a non-zero exit status.
- Programs should be free of memory related errors, buffer overflows, stack smashing, leaks, etc... Whether the program crashes or not. This will be validated using valgrind.

## Lab Warmup - Playlist Builder
[Walkthrough Video](https://www.youtube.com/playlist?list=PLvnIObHoMl8cLxjfgsSf5C8T4gcaArdBL)  

### Problem Description
Playlist Builder is an application that allows the user to specify a CSV file containing song data and a number of songs on the commandline.  The application will open the CSV, load the specified number of songs from the CSV file into a dynamically allocated array of the specified size, sort the songs in the array based upon their duration, then display the list of songs in the console.

<br />
1. Carefully study the provided Song header file (Song.h) and function definitions (Song.c)
<br /><br />
The Song struct as well as function declarations for the related functions described below have been provided in Song.h. Please do not modify the provided Song.h or Song.c files. Details regarding each function as well as expected return values are included in the comments associated with each function declaration in Song.h. The following is a summary of this content:

- Data members
  - char artist[40];
  - char album[40];
  - char title[40];
  - int duration;
- Related functions
  - Song * CreateSong(const char artist[], const char album[], const char title[], int duration)  
  - int CompareSongs(const void * song1PtrPtr, const void * song2PtrPtr) 
  - void PrintSong(Song * thisSong)
  - void DestroySong(Song * thisSong)
  
<br />
2. Process the command-line arguments passed into main(), validate the correct number of values are passed in, dynamically create a songlist array of the specified size and finally, open the specified file.  Handle any errors that occur by displaying a helpful error message and then exit with a non-zero exit status. 

<br /><br />
Ex: Incorrect number of command-line arguments
```
./myprog 
Usage: ./myprog <catalog.csv> <size>
```

<br /><br />
Ex: Missing catalog file
```
./myprog  rush-collection.csv 15
fopen: No such file or directory
```

<br /><br />
Ex: Size not an integer value
```
./myprog music-collection.csv twelve
Error: Invalid size specied: twelve
```

<br />
3. Iterate through each line in the specified input CSV file, extracting song fields from each line as shown in the CSVParser example, adding each song to the songlist. If the songlist fills up before reaching the of the CSV file, stop processing the CSV file and display a message specifying the number of songs that were successfully loaded. If the end of the CSV file is reached before loading the requested number of songs, display a message specifying the number of songs that were successfully loaded.
<br /><br />

Ex: Successfully parsed and loaded three songs from CSV file
```
./myprog music-collection.csv 12
Successfully loaded 12 songs!
```

<br />
4. Iterate through the songlist and display each song in the console using the PrintSong() function.
<br /><br />

Ex:
```
./myprog music-collection.csv 2
Successfully loaded 2 songs!
------------------------------------------------
Artist: Aerosmith
Album: A Little South Of Sanity Disc 1
Title: Falling In Love (Is Hard On The Knees)
Duration: 209
------------------------------------------------
------------------------------------------------
Artist: Aerosmith
Album: A Little South Of Sanity Disc 1
Title: Hole In My Soul
Duration: 340
------------------------------------------------
```


<br />
5. Release allocated memory. Iterate through the songlist, freeing each Song by calling the DestroySong() function. Then free the songlist.  Also make certain to close any files that were opened.
<br /><br />

Ex: Run program in valgrind to verify no memory errors exist
```
valgrind --tool=memcheck --leak-check=yes --show-reachable=yes ./myprog  music-collection.csv 915 
==89619== Memcheck, a memory error detector
==89619== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==89619== Using Valgrind-3.15.0 and LibVEX; rerun with -h for copyright info
==89619== Command: ./myprog music-collection.csv 915

<<<<<<<< PROGRAM OUTPUT REMOVED >>>>>>>>

==89614== 
==89614== HEAP SUMMARY:
==89614==     in use at exit: 0 bytes in 0 blocks
==89614==   total heap usage: 919 allocs, 919 frees, 126,372 bytes allocated
==89614== 
==89614== All heap blocks were freed -- no leaks are possible
==89614== 
==89614== For lists of detected and suppressed errors, rerun with: -s
==89614== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 0 from 0)
```


### Implementation Guide
1. Expand the folder named LabWarmup and open the files named Song.h, Song.c and main.c
2. Enter the program code to create an application as described in the Problem Description.
3. Test the program using the examples above to ensure it functions as expected.
4. Run the program with valgrind to catch any memory leaks or errors
5. Commit the changes to your local repository with a message stating that LabWarmup is completed.
6. Push the changes from your local repository to the github classroom repository.
7. Update the Coding Journal with an entry describing your experience using the steps outlined below.


## Lab Activity - Gradebook
### Problem Description
Gradebook is an adaptation of a CS121 project used to demonstrate processing CSV files as well as working with ArrayLists and is revisited when we discuss Arrays.  This version of the Gradebook application allows the user to specify a CSV file containing student grade data command-line.  The application will open the CSV, load the grades from the CSV file into a dynamically allocated array. The array will initially be allocated to hold a max of 8 student grades, but will grow as needed by doubling its size each time. Once the grade data has been loaded from the CSV file, the grades array will be sorted based upon score, then displayed in the console. 
<br />
1. Carefully study the provided Student header file (Student.h) and function definitions (Student.c)
<br /><br />
The Student struct as well as function declarations for the related functions described below have been provided in Student.h. Please do not modify the provided Student.h or Student.c files. Details regarding each function as well as expected return values are included in the comments associated with each function declaration in Student.h. The following is a summary of this content:

- Data members
  - char lastname[20];
  - char firstname[20];
  - int id;
  - int score;
- Related functions
  - Student * CreateStudent(const char lastname[], const char firstname[], int id, int score)  
  - int CompareStudents(const void * Student1PtrPtr, const void * Student2PtrPtr) 
  - void PrintStudent(Student * thisSG)
  - void DestroyStudent(Student * thisSG)


<br />
2. Process the command-line arguments passed into main(), validate the correct number of values are passed in, dynamically create a gradebook array to hold 8 Student pointers and finally, open the specified file.  Handle any errors that occur by displaying a helpful error message and then exit with a non-zero exit status. 
<br /><br />

Ex: Incorrect number of command-line arguments
```
./myprog 
Usage: ./myprog <gradebook.csv>
```

Ex: Missing CSV data file
```
./myprog grades.txt
fopen: No such file or directory
```

<br />
3. Iterate through each line in the specified input CSV file, extracting song fields from each line as shown in the CSVParser example, adding each Student to the gradebook. If the gradebook fills up before reaching the of the CSV file, grow the gradebook by doubling its size as shown in the SampleDataProcessing example. When the end of the CSV file is reached display a message specifying the number of Students that were successfully loaded.
<br /><br />

Ex: Run gradebook application on XL CSV dataset
```
./myprog gradebook-xl.csv 
Growing Array: 8 -> 16
Growing Array: 16 -> 32
Growing Array: 32 -> 64
Growing Array: 64 -> 128
Growing Array: 128 -> 256
Growing Array: 256 -> 512
Growing Array: 512 -> 1024
Successfully loaded 1000 Students!
```
<br />
4. Use qsort() and the CompareStudents() function to sort the gradebook array.  

For more details on how qsort() works, please review the [Week 9 Deeper Look](https://www.youtube.com/playlist?list=PLvnIObHoMl8dxXVqbIki-altY22BE2vTU) discussion of function pointers. The PointersAndFunctions example from [cs253-week9-examples](https://github.com/lhindman/cs253-week9-examples) may also be helpful.
<br /><br />


<br />
5. Iterate through the sorted gradebook and display each Student in the console using the PrintStudent() function. Verify that the Students are displayed in ascending order
<br /><br />

Ex: Show sorted gradebook using small CSV dataset
```
./myprog gradebook.csv
Successfully loaded 7 Students!
Stella Zinman (6326) has a F (21)
Barney Stinson (1) has a D- (63)
Gary Blauman (7123) has a C (76)
Lily Aldrin (1413) has a B- (82)
Marshall Eriksen (1423) has a B+ (87)
Robin Scherbatsky (3002) has a A- (91)
Ted Mosby (6003) has a A+ (103)
```

<br />
6. Release allocated memory. Iterate through the gradebook, freeing each Student by calling the DestroyStudent() function. Then free the gradebook.  Also make certain to close any files that were opened.
<br /><br />


Ex:
```
valgrind --tool=memcheck --leak-check=yes --show-reachable=yes ./myprog  gradebook-xl.csv > /dev/null
==90009== Memcheck, a memory error detector
==90009== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==90009== Using Valgrind-3.15.0 and LibVEX; rerun with -h for copyright info
==90009== Command: ./myprog gradebook-xl.csv
==90009== 

<<<<<<<< PROGRAM OUTPUT REMOVED >>>>>>>>

==90009== 
==90009== HEAP SUMMARY:
==90009==     in use at exit: 0 bytes in 0 blocks
==90009==   total heap usage: 1,012 allocs, 1,012 frees, 120,984 bytes allocated
==90009== 
==90009== All heap blocks were freed -- no leaks are possible
==90009== 
==90009== For lists of detected and suppressed errors, rerun with: -s
==90009== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 0 from 0)
```

### Implementation Guide
1. Expand the folder named LabActivity and open the files named Student.h, Student.c and main.c
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
