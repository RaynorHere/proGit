# Project 1: PlayList Generator

*Author: James "JCIII" Crowell
*Class: CS121 Section 6
*Semester: Fall 2020


## Overview
This program's purpose is:
1) To accept input from the user in three "sets", of four items each:
	a) Song title
	b) Song artist
	c) Song album
	d) Song length

2) To construct a playlist of those songs

3) To calculate the average playtime of the songs submitted by the user

4) To tell the user which song of those submitted is closest in length to four minutes

5) To print the playlist, ordered from shortest song to longest song


## Compiling and Using
Navigate to the directory where PlayList.java is saved.
Run command:

javac PlayList.java

Once compiler finishes, run command:

java PlayList

The program should query for the name of a song, the artist of that song, the album the song appears on, and the playtime of that song, in mm:ss format. Supply those answers as the program requests them, and after the third cycle of questions, your results should appear, in line with the Overview above.


## Sources Used

Dr. Amit Jain, for assistance with ParseInt() and IndexOf() functions, to convert submitted play data to correct format


Stackoverflow.com, for assistance with correcting .equals error:

https://stackoverflow.com/questions/46800753/equals-method-and-operator-for-primitive-data-types-and-class-data-types
(I mistakenly believed to compare two DOUBLE variables, I had to use variable.equals; for primitive data types, "==" suffices, and .equals is for objects. This reminded me of that)