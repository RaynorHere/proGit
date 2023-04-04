import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * Lesson 12: Activity - ArrayLists
 *
 * @author CS121 Instructors
 * @version [semester]
 * @author [your name]
 */
public class MASHGame {

	public static void main(String[] args) {

		/* Define list of options */
		ArrayList<String> homeList = new ArrayList<String>();
		ArrayList<String> femaleSpouseList = new ArrayList<String>();
		ArrayList<String> maleSpouseList = new ArrayList<String>();
		ArrayList<String> occupationList = new ArrayList<String>();
		ArrayList<String> transportationList = new ArrayList<String>();
		ArrayList<String> hometownList = new ArrayList<String>();

		/* Add items to home list */
		homeList.add("Mansion");
		homeList.add("Apartment");
		homeList.add("Shack");
		homeList.add("House");

		/* TODO: 1. Add items to femaleSpouseList */
		//I would like to make one thing very clear: it is taking everything I have not to re-title
		//this array list as "waifuList", which is EXACTLY what it should be. Just saying.
		femaleSpouseList.add("Juri");
		femaleSpouseList.add("Janine");
		femaleSpouseList.add("Victoria");
		femaleSpouseList.add("Marle");
		femaleSpouseList.add("Faye");
		femaleSpouseList.add("Triss");

		/* TODO: 2. Add items to maleSpouseList */
		//Obviously, you could do a similar thing with "husbandoList", but again, perhaps the... 
		//vagaries of the stultifying culture of the internet are beyond the scope of this assignment.
		//Just saying, you COULD.
		maleSpouseList.add("Garrett");
		maleSpouseList.add("Dismas");
		maleSpouseList.add("Leon");
		maleSpouseList.add("Magus");
		maleSpouseList.add("Raynor");
		maleSpouseList.add("Vergil");
		

		/* TODO: 3. Add items to occupationList */
		occupationList.add("Spiritfarer");
		occupationList.add("Mercenary");
		occupationList.add("Nuclear Physicist");
		occupationList.add("Skynet Programmer");
		occupationList.add("Adventurer");
		occupationList.add("Vampire Killer");
		
		/* TODO: 4. Add items to transportationList */
		transportationList.add("Starfreighter");
		transportationList.add("Semi");
		transportationList.add("Rice Rocket");
		transportationList.add("F-350");
		transportationList.add("Z3");
		transportationList.add("JägerMech");

		/* TODO: 5. Add items to hometownList */
		hometownList.add("Tau-Ceti V");
		hometownList.add("Mar Sara");
		hometownList.add("The Lost Woods");
		hometownList.add("Gloomwood");
		hometownList.add("Raccoon City");
		hometownList.add("Morta");
		
		/* Print the database */
		System.out.println("--------------------- Future Possibilities Database ------------------------");
		System.out.print("Home List: ");
		for (String item: homeList) 
			{
				System.out.print(item + ", ");
			}
		//Going to put in newline spacing just to make the output separate a little more.
		System.out.println("\n");
		
		/* TODO: 6. Print the items in the femaleSpouseList using the ArrayList toString() method */
		System.out.println("Female Spouses: ");
		System.out.print(femaleSpouseList.toString());
		System.out.println("\n");
		
		/* TODO: 7. Print the items in the maleSpouseList using a for loop (IE: use indices to print items ) */
		System.out.println("Male Spouses: ");
		for (int i = 0; i < maleSpouseList.size()-1; i++)
			{
				String x = maleSpouseList.get(i);
				System.out.print(x + ", ");
			}
		System.out.print(maleSpouseList.get(maleSpouseList.size()-1));
		System.out.println("\n");
		
		
		/* TODO: 8. Print the items in the occupationList using the ArrayList iterator and a while loop */
		System.out.println("Occupations:");
		Iterator<String> jorb = occupationList.iterator();
		while(jorb.hasNext())
			{
				Object y = jorb.next();
				System.out.print(y + ", ");
			}
		System.out.println("\n");
		
		
		/* TODO: 9. Print the items in the transportationList using a foreach loop */
		System.out.println("Vehicles:");
		for (String z : transportationList)
			{
				System.out.print(z + ", ");
			}
		System.out.println("\n");
		
		
		/* TODO: 10. Print the items in the hometownList using the method of your choice */
		System.out.println("Hometowns:");
		//Hey, YOU might call it boring; *I* call it EFFICIENCY.
		System.out.println(hometownList.toString());
		System.out.println("\n");
		
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("\n");

		/* Ask the player for their name */
		Scanner kbd = new Scanner(System.in);
		System.out.print("Please enter your name: ");
		String name = kbd.nextLine();
		kbd.close();
		System.out.println("\n\n");
		
		/* Randomly choose items from each List */
		Random rand = new Random();
		String firstHome = homeList.get(rand.nextInt(homeList.size()));	
		
		/* TODO: 11. Use rand object to select each of the following future 
		 * components from above lists. 
		 * */
		homeList.remove(firstHome);
		
		//I feel a little weird that this is the same code over and over again.
		String secondHome = homeList.get(rand.nextInt(homeList.size()));
		String occupation = occupationList.get(rand.nextInt(occupationList.size()));
		String transportation = transportationList.get(rand.nextInt(transportationList.size()));
		String hometown = hometownList.get(rand.nextInt(hometownList.size()));
		
		/* TODO: 12. Use the coin to determine whether player's spouse is male or female, 
		 * then randomly select spouse from the corresponding list.
		 */
		boolean coin = rand.nextBoolean();
		String spouse;
		if (coin)
			{
				spouse = femaleSpouseList.get(rand.nextInt(femaleSpouseList.size()));
			}
		else
			{
				spouse = maleSpouseList.get(rand.nextInt(maleSpouseList.size()));
			}

		/* Randomly choose number of years player will be married */
		int yearsOfMarriage = rand.nextInt(30) + 1;


		/* Print the player's future*/
		System.out.println("Welcome "+ name + ", this is your future...");
		System.out.println("You will marry " + spouse + " and live in a " + firstHome + ".");
		System.out.println("After " + yearsOfMarriage + (yearsOfMarriage == 1?" year":" years") +" of marriage, you will finally get your dream job of being a " + occupation + ".");
		System.out.println("Your family will move to a " + secondHome + " in " + hometown + " where you will drive a " + transportation + " to work each day.");


	}

}
