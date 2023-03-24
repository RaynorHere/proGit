import java.util.Scanner;

/**
 * Lesson 9: Activity - Data Comparisons and Switch Statements
 * 
 * @author CS121 Instructors
 * @author <you>
 */
public class Verses
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Which day is it (1 - 12)? ");
		int day = scan.nextInt();
		
		// TODO: Use a switch statement to set the appropriate suffix for the day
		String daySuffix;
		switch (day)
		{ 
		case 1:
			daySuffix = "st";
			break;
		case 2:
			daySuffix = "nd";
			break;
		case 3:
			daySuffix = "rd";
			break;
		default:
			daySuffix = "th";
		}
		
		
		System.out.println("On the " + day + daySuffix + " day of Christmas my true love gave to me");
	
		// TODO: Use a switch statement to control which lines get printed depending on the day
		switch (day)
		{
		case 12:		
		System.out.println("Twelve drummers drumming,");
		
		case 11:
		System.out.println("Eleven pipers piping,");
		
		case 10:
		System.out.println("Ten lords a leaping,");
		
		case 9:
		System.out.println("Nine ladies dancing,");
		
		case 8:
		System.out.println("Eight maids a milking,");
		
		case 7:
		System.out.println("Seven swans a swimming,");
		
		case 6:
		System.out.println("Six geese a laying,");
		
		case 5: 
		System.out.println("Five golden rings,");
		
		case 4: 
		System.out.println("Four calling birds,");
		
		case 3: 
		System.out.println("Three french hens,");
		
		case 2: 
		System.out.println("Two turtle doves, and");
		
		case 1:
		System.out.println("A partridge in a pear tree.");
		break;
		}
		
		scan.close();
	}
}
