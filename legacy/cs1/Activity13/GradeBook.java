import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Lesson 13: Activity - GradeBook
 *
 * @author CS121 Instructors
 * @version [semester]
 * @author [your name]
 */
public class GradeBook {

	public static void main(String[] args) {
		
		/* TODO: 1. Create a new ArrayList of Student objects called gradebook. */
		ArrayList <Student> gradeBook = new ArrayList<Student>();
		
		
		
		
		/* TODO: 2. Create a new File object for gradebook.csv and 
		 * a new Scanner object to parse it. Catch any required 
		 * exceptions and display a useful message to the user.
		 */
		
		File gradesIn = new File("gradebook.csv");
		final String SEPERATOR = ",";
		String lastName = "", firstName = "";
		int studentID = 0, studentScore;
		//I'm calling the last one "score" because the word "grade" and its declensions are used a lot 
		//as it is.
		try 
		{
			Scanner gradesReader = new Scanner(gradesIn);
			while (gradesReader.hasNextLine())
			{
				
				String thisGrade = gradesReader.nextLine();
				Scanner gradesParser = new Scanner(thisGrade);
				gradesParser.useDelimiter(SEPERATOR);
				while (gradesParser.hasNext())
				{
					lastName = gradesParser.next();
					firstName = gradesParser.next();
					studentID = gradesParser.nextInt();
					studentScore = gradesParser.nextInt();
					Student student = new Student(firstName, lastName, studentID);
					student.setGrade(studentScore);
					gradeBook.add(student);
					
				}
				
				gradesParser.close();
			}
			
			gradesReader.close();
		
			
		for (Student gradeOut : gradeBook)
			{
				System.out.println(gradeOut);
			}
			
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("The required file, \"gradebook.csv\" was not found; please place in program directory and run again.");
			
			//We'll comment out the stack trace because it's largely unhelpful, unless you know how 
			//to read it, and I'm not entirely too proud to admit that I don't, completely.
			//e.printStackTrace();
		}
		
		
				
			/* TODO: 3. Use a while loop and the Scanner created above to iterate 
			 * through the lines in the gradebook.csv file.
			 */
		

				/* TODO: 4. For each line (student record), use a second 
				 * Scanner object to tokenize the line using a comma (',')
				 * as the delimiter, extract values for lastName, firstName,
				 * id and grade and store them to local variables.
			 	 */


			 	/* TODO: 5. Create a new Student object using the local variables
			 	 * create above, set the student's grade, and finally add the 
			 	 * new Student object to the gradebook ArrayList. 
				 */

		
		
		/* TODO: 6. Use a foreach loop to print out contents of the gradebook */

		

	}

}
