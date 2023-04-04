import java.util.Random;
import java.util.Scanner;

/**
 * Lesson 10: Activity - while Loops and Iterators 
 * 
 * @author Java Foundations
 * @author CS121 Instructors
 * @version Fall 2018
 */
public class HigherLower
{
	public static void main(String[] args)
	{
		final int MAX = 10;
		int answer;
		int guess;
		boolean repeat = true;
		String replay;
		
		Scanner scan = new Scanner(System.in);
		Random random = new Random();
		
		while (repeat)
	{
		answer = random.nextInt(MAX) + 1;
		
		System.out.print("I'm thinking of a number between 1 and " + MAX + ". ");
		System.out.print("Guess what it is: ");
		
		guess = scan.nextInt();
		
		// Here, we trap the program in a never-ending state until the correct answer is given.
		// We will break this state down into what KIND of wrong the answer is.
		while (guess != answer)
		{
			if (guess <= 0 || guess >= 11)
			{
				System.out.println("ERROR: Your guess needs to be between 1 and 10");
				guess = scan.nextInt();
			}
			else if (guess > answer)
			{
				//The "and" statement here guarantees that if you start too high but end up guessing 
				//too MUCH lower, it lets you out of this loop and shunts you to the next loop
				while (guess != answer && guess > answer)
				{
					System.out.println("Too high; go lower.");
					guess = scan.nextInt();
					if (guess <= 0 || guess >= 11)
					{
						System.out.println("ERROR: Your guess needs to be between 1 and 10)");
					}
				}
			}
			else if (guess < answer)
			{
				//Reverse of above; we only want the program here as long as you're wrong AND too low.
				//Go too much higher, and it'll kick you back to the previous block and tell you to go
				//lower.
				while (guess != answer && guess < answer)
				{
					System.out.println("Too low; go higher.");
					guess = scan.nextInt();
					if (guess <= 0 || guess >= 11)
					{
						System.out.println("ERROR: Your guess needs to be between 1 and 10)");
					}
				}
			}
			
		}
		
		System.out.println("You got it! Good guessing!");
		System.out.println("Play again? (Y/N)");
		//This is probably just being pedantic, but I wanted to make sure there were catches 
		//programmed for if the user is entering any options other than specifically what is 
		//given. I'm trying to prevent unhandled's in all my programs, where possible.
		replay = scan.next();
		while (!replay.equalsIgnoreCase("Y") && !replay.equalsIgnoreCase("N"))
		{
			System.out.println("Invalid entry; please enter Y or N.");
			replay = scan.next();
		}
		if (replay.equalsIgnoreCase("Y"))
			{
				repeat = true;
			}
		else 
			{
				repeat = false;
			}
		
	}
				
		System.out.println("Game over. Goodbye!");
		
		scan.close();
	}
}

