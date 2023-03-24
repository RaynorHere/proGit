import java.util.Scanner;
public class ConvertToHMS
{	
	
	public static void main(String[] args)
	{
		//Seconds in an hour
		final int hoursPer = 3600;
		//Minutes in an hour
		final int minPer = 60;
		
		//Values we will spit out to the user after calculation
		int hoursOut;
		int minutesOut;
		int secondsOut;
						
		System.out.println("This program will convert elapsed seconds into a total number of hours, minutes, and seconds.");
		System.out.print("Please enter number of elapsed seconds (note: please enter a positive integer)");
		
		//Create our scanner variable, fill it, and pass its value to the placeholder variable we will be using for calculations
		Scanner Input = new Scanner(System.in);
		int input = Input.nextInt();
		//Prevent memory leaks
		Input.close();
		
		hoursOut = input / hoursPer;
		input %= hoursPer;
		minutesOut = input / minPer;
		input %= minPer;
		//This next one is redundant, because you could just return the remaining seconds in timeIn, but I like the consistency of all variables
		//Being returned having the suffix "Out"
		secondsOut = input;
	
		System.out.println("Total elapsed time (H:M:S): " + hoursOut + ":" + minutesOut + ":" + secondsOut);
				
	}

}
