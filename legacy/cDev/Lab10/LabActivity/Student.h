/* File: Student.h
 * Author: Luke Hindman
 * Date: Sun 01 Nov 2020 10:58:39 AM PST
 * Description: Stuct and functions for working with Student data
 */
#ifndef __STUDENT_H
#define __STUDENT_H

#define STUDENT_FIELD_SIZE 40

struct student_grade_struct {    
    char lastname[STUDENT_FIELD_SIZE];
    char firstname[STUDENT_FIELD_SIZE];
    int id;
    int score;
};
typedef struct student_grade_struct Student;

/* CreateStudent: Allocate a Student object in the heap using malloc
 *    the object will be initialized with the specified lastname, firstname, 
 *    id and score.  Return NULL if any parameters are null or less than zero.
 * lastname - NULL terminated string containing lastname
 * firstname - NULL terminated string containing firstname
 * id - integer value representing student id number
 * score - integer value representing student score
 * 
 * returns - Pointer to Student object allocated on the heap
 */
Student * CreateStudent(const char lastname[], const char firstname[], int id, int score);

/* CompareStudents: Compare two Student objects. The parameters must be of 
 *    type void* for compatibility with qsort().  The parameters must
 *    be cast to Student before dereferencing internal fields.  Returns
 *    negative number if the score of Student1 is less than Student2, a 
 *    positive number of the score of Student1 is greather than Student2, 
 *    and zero if the Students have equal score. Undefined behavior if
 *    either Student1 or Student2 is NULL
 * Student1PtrPtr - Pointer to a Pointer to a Student
 * Student2PtrPtr - Pointer to a Pointer to a Student
 * 
 * returns negative number if Student1 < Student2
 *         positive number if Student1 > Student2
 *         zero if Student1 == Student2
 */
int CompareStudents(const void * Student1PtrPtr, const void * Student2PtrPtr);

/* PrintStudent: Write the fields of the Student, nicely formatted,
 *     to stdout (console) using printf. 
 * thisStudent - Pointer to Student object to be displayed */
void PrintStudent(Student * thisStudent);

/* DestroyStudent: Release memory allocated by malloc in the
 *    CreateStudent function.  Does nothing if thisStudent is NULL
 * thisStudent - Pointer to Student object to be freed.
 */
void DestroyStudent(Student * thisStudent);

#endif /* __STUDENT_H */
