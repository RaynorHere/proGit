import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class AdGenerator 

{
	
	public static void main(String[] args) 
	{
		//Set up our program to accept input from users
		Scanner Input = new Scanner(System.in);
		
		System.out.println("This program will generate an ad for services based on user input");
		
		//Grab all pertinent information. We use "nextLine" because it allows answers to be phrases; else having 
		//spaces between elements of input (that is, if your job title is two words) causes each element after each
		//space to apply to the NEXT question in the list, which can easily cause formatting errors and program crashes.
		System.out.println("Enter your first name:");				
		String nameF = Input.nextLine().trim();
		
		System.out.println("Enter your middle name:");
		String nameM = Input.nextLine().trim();
		
		System.out.println("Enter your last name:");
		String nameL = Input.nextLine().trim();
		
		System.out.println("What do you do for a living? (Answer as you would, 'I am a...')");
		String jobClass = Input.nextLine().trim();
		
		System.out.println("What phone number is good for reaching you (##########)?");
		String phoneNo = Input.nextLine().trim();
		
		System.out.println("What do you charge, by the hour ($$$.CC)?");
		float wages = Input.nextFloat();
		
		Input.close();
		
		//Begin formatting information
		String areaCode = phoneNo.substring(0, 3);
		String phone1 = phoneNo.substring(3, 6);
		String phone2 = phoneNo.substring(6, 10);
		
		String midInitial = nameM.charAt(0) + ".";
		
		NumberFormat payment = NumberFormat.getCurrencyInstance();
				
		//Actual program output
		System.out.println("--------------------------------------------------------");
		System.out.println("Looking for a " + jobClass + "?");
		System.out.println("Give us a call at (" + areaCode + ") " + phone1 + "-" + phone2 + "!");
		System.out.println("As for " + nameF + " " + midInitial + " " + nameL);
		System.out.println("(Rates as low as " + payment.format(wages) + ")");
		System.out.println("--------------------------------------------------------");
						

	}

}
