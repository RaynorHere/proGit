/* File: Student.c
 * Author: Luke Hindman
 * Date: Sun 01 Nov 2020 09:16:37 AM PST
 * Description: Stuct and functions for working with Student data
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Student.h"

Student * CreateStudent(const char lastname[], const char firstname[], int id, int score) {
    /* Validate Parameters */
    if (lastname == NULL || firstname == NULL) {
        return NULL;
    }
    if(id < 0 || score < 0) {
        return NULL;
    }

    /* Allocate space for Student in heap and validate */
    Student * newStudent = (Student *) malloc(sizeof(Student));
    if (newStudent == NULL) {
        return NULL;
    }

    /* Initialize the Student with the specified values */
    strncpy(newStudent->lastname,lastname,STUDENT_FIELD_SIZE);
    strncpy(newStudent->firstname,firstname,STUDENT_FIELD_SIZE);
    newStudent->id = id;
    newStudent->score = score;

    return newStudent;
}

int CompareStudents(const void * Student1PtrPtr, const void * Student2PtrPtr) {
    /* Dereference the parameters to access the Student pointers */
    Student * Student1 = * (Student **)Student1PtrPtr; 
    Student * Student2 = * (Student **)Student2PtrPtr;
    return ((Student *)Student1)->score - ((Student *)Student2)->score;
}



void PrintStudent(Student * thisStudent) {
    /* Validate Parameters */
    if(thisStudent == NULL) {
        return;
    }
    int score = thisStudent->score;
    char * grade = "F";
    if(score >= 97) {
        grade = "A+";
    } else if (score >= 94) {
        grade = "A";
    } else if (score >= 90) {
        grade = "A-";
    } else if (score >= 87) {
        grade = "B+";
    } else if (score >= 84) {
        grade = "B";
    } else if (score >= 80) {
        grade = "B-";
    } else if (score >= 77) {
        grade = "C+";
    } else if (score >= 74) {
        grade = "C";
    } else if (score >= 70) {
        grade = "C-";
    } else if (score >= 67) {
        grade = "D+";
    } else if (score >= 64) {
        grade = "D";
    } else if (score >= 60) {
        grade = "D-";
    }

    fprintf(stdout,"%s %s (%d) has a %s (%d)\n", thisStudent->firstname, thisStudent->lastname, thisStudent->id, grade, score);

}

void DestroyStudent(Student * thisStudent) {
    /* Validate Parameters */
    if (thisStudent == NULL) {
        return;
    }

    free(thisStudent);
}

