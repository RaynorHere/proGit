public class MyTempConverter 
{
/* We know the formula for finding Celsius temperature "y" from Fahrenheit temperature "x" is 
 y = (5/9)*(x-32). From there, it's a simple matter to pass a given value through the operators and return our desired result. For now, we're 
 starting with a hardcoded input, so it's even simpler.
 */

	public static void main(String[] args)
	{
		//Initialize our variables for calculation and printing. Will be using floating points, because 7 sig figs is enough for temperature.
		float fahrTemp;
		float celsTemp;
		
		//I would like to use a specific value to ensure the program is not only functioning, but the formulae are accurate, so I'll be using
		//A specific value with a specific result in mind
		
		//Set up the elements to our formula that we'll pass input through to get output
		int step1 = (32);
		//I was getting a "convert float to double" error, for some reason, and it seemed to be related to writing the numbers as "5.0" and "9.0"
		//I'm not sure what the correct way to write the numbers would be, since they're both decimals (one is just to 7 sig figs; the other 15)
		//So, I'm resorting to casting the numbers. I know I could use double variables, and it would be fine; we don't have to scrounge for
		//Memory conservation, but I chose float and I want float.
		float step2 = ((float)(5.0/9.0));
		
		//We know the boiling point of water is 212 Fahrenheit, or 100 Celcius, so, in order to make sure our formula is correct:
		fahrTemp = 212;
		
		celsTemp = ((fahrTemp - step1)*step2);
		
		System.out.println("Given the Fahrenheit temperature " + fahrTemp);
		System.out.println("The equivalent Celsius temperature will be " + celsTemp);
		
		//It's pretty obvious this will require very, VERY little tweaking in order to accept user input, and honestly, I want to submit it with
		//User input already coded, but these things are done in steps.
				
	}
		
}
