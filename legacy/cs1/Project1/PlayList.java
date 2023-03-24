import java.text.DecimalFormat;
import java.util.Scanner;
import java.text.DecimalFormat;

/*
 *The PlayList program accepts several inputs from the user; a song title, artist, album, and length,
 *for three songs. It then groups those three songs into a list, calculates the average run time of
 *each, declares which song is the closest in time to four minutes, and then prints a sorted list of
 *those songs, shortest to longest.
 *
 * @author James "JCIII" Crowell
 * 
 * @version 1.0
 * 
 * @est 2020-10-05
*/

public class PlayList 


{

	public static void main(String[] args) 
	
	{
	//PHASE 1
		
	//These first three variables speak for themselves
	String songTitle;
	String songArtist;
	String songAlbum;
	
	//We're gonna have to do some wonky stuff with the length, so it gets several variables.
	//This is what the user inputs.
	String songLengthRaw;
	
	//These will come in handy for pulling data out into a separate spot so we can operate on it.
	int colonCounter;
	int songMinutes;
	int songSeconds;
	
	//This is what it'll look like when it's finished.
	int songLengthFinished;
	
	
		
	Scanner input =  new Scanner(System.in);
			
	System.out.println("Please enter the title of a song:");
	songTitle = input.nextLine();
	
	System.out.println("Please enter the artist of that song:");
	songArtist = input.nextLine();
	
	System.out.println("Please enter the album containing that song:");
	songAlbum = input.nextLine();
	
	//First, we grab the raw user input, including the colon.
	System.out.println("How long is that song (mm:ss format)?");
	songLengthRaw = input.nextLine();
	
	//Now we have a string. First step, find out where the colon separating minutes and seconds is,
	//so we know which number we have to multiply.
	colonCounter = songLengthRaw.indexOf(":");
	
	//Now that we know where the colon IS, everything BEFORE it is minutes. That information is still in
	//a string form, but in one sweep, we can take just the chunk of the user's data that was minutes,
	//separate it into its own string, then parse it into an integer.
	songMinutes = Integer.parseInt(songLengthRaw.substring(0, colonCounter));
	
	//This should grab the number of seconds, less than a full minute, that the user entered; AFTER the
	//colon.
	songSeconds = Integer.parseInt(songLengthRaw.substring(colonCounter+1));
	
	//Finally, total song time in seconds is equal to:
	songLengthFinished = (songMinutes * 60 + songSeconds);
	
	Song song1 = new Song(songTitle, songArtist, songAlbum, songLengthFinished);
	
	//Now, we should pretty much able to run that two more times, and that will give us our three songs.
	
	//Song 2
	System.out.println("Please enter the title of a song:");
	songTitle = input.nextLine();	
	System.out.println("Please enter the artist of that song:");
	songArtist = input.nextLine();	
	System.out.println("Please enter the album containing that song:");
	songAlbum = input.nextLine();	
	System.out.println("How long is that song (mm:ss format)?");
	songLengthRaw = input.nextLine();
	
	colonCounter = songLengthRaw.indexOf(":");
	songMinutes = Integer.parseInt(songLengthRaw.substring(0, colonCounter));
	songSeconds = Integer.parseInt(songLengthRaw.substring(colonCounter+1));
	songLengthFinished = (songMinutes * 60 + songSeconds);
	
	Song song2 = new Song(songTitle, songArtist, songAlbum, songLengthFinished);
	
	//Song 3	
	System.out.println("Please enter the title of a song:");
	songTitle = input.nextLine();	
	System.out.println("Please enter the artist of that song:");
	songArtist = input.nextLine();	
	System.out.println("Please enter the album containing that song:");
	songAlbum = input.nextLine();	
	System.out.println("How long is that song (mm:ss format)?");
	songLengthRaw = input.nextLine();
	
	input.close();
		
	colonCounter = songLengthRaw.indexOf(":");
	songMinutes = Integer.parseInt(songLengthRaw.substring(0, colonCounter));
	songSeconds = Integer.parseInt(songLengthRaw.substring(colonCounter+1));
	songLengthFinished = (songMinutes * 60 + songSeconds);
	
	Song song3 = new Song(songTitle, songArtist, songAlbum, songLengthFinished);
		
	//TEST PHASE 1
//	System.out.println(song1.toString());
//	System.out.println(song2.toString());
//	System.out.println(song3.toString());
	
	//Okay. Our base program is written and appears to be behaving. Now let's move on to more advanced
	//features.
	
	
	//PHASE 2
	
	//First up, average length calculation. We need to cast them as doubles so they aren't trunc'd;
	//that way we can calculate an average that has decimals to it.
	double averagePlay;
	averagePlay = ((double)song1.getPlayTime() + (double)song2.getPlayTime() + (double)song3.getPlayTime()) / 3;
	
	//Format to show two decimal places. Even if the average is a round number, I want to include the
	//decimal places, so we'll put "0" instead of "##" after the decimal point.
	DecimalFormat df = new DecimalFormat("##.00");
	String formattedAveragePlay = df.format(averagePlay);
	
	//TEST PHASE 2
	System.out.println("The average play time per song on this list is " + formattedAveragePlay);
	//This is part of expected program output, so it isn't commented out, unlike phase 1's test.
	
	//All righty; average calculator looks to be good. Moving on.
		
	
	//PHASE 3
		
	//Now we pull which is closest to four minutes. This should be as simple as subtracting the total
	//time of each song from 240 seconds, taking an absolute value, and then stating that the smallest
	//value is that closest to four minutes.
	
	//Make it a double, in case two songs are close, and decision comes to fractions of a second.
	double comparVal = 240.0;
	double song1Range, song2Range, song3Range;
	String closestSong1, closestSong2;
	
	song1Range = Math.abs(comparVal - song1.getPlayTime());
	song2Range = Math.abs(comparVal - song2.getPlayTime());
	song3Range = Math.abs(comparVal - song3.getPlayTime());
	
	if (song1Range < song2Range && song1Range < song3Range)
		{
			closestSong1 = song1.getTitle();
			System.out.println("The song with play time closest to 240 seconds is " + closestSong1);
		}
	
	else if (song2Range < song1Range && song2Range < song3Range)
		{
			closestSong1 = song2.getTitle();
			System.out.println("The song with play time closest to 240 seconds is " + closestSong1);
		}
	
	else if (song3Range < song1Range && song3Range < song2Range)
		{
			closestSong1 = song3.getTitle();
			System.out.println("The song with play time closest to 240 seconds is " + closestSong1);
		}
	
	//Here, we'll start writing in catches for two songs being equally close to 4 minutes
	else if (song1Range < song2Range && song1Range == song3Range)
		{
			closestSong1 = song1.getTitle();
			closestSong2 = song3.getTitle();
			System.out.println("The songs with play time closest to 240 seconds are " + closestSong1 + " and " + closestSong2);
		}
	
	else if (song1Range < song3Range && song1Range == song2Range)
		{
			closestSong1 = song1.getTitle();
			closestSong2 = song2.getTitle();
			System.out.println("The songs with play time closest to 240 seconds are " + closestSong1 + " and " + closestSong2);
		}
	
	else if (song2Range < song1Range && song2Range == song3Range)
		{
			closestSong1 = song2.getTitle();
			closestSong2 = song3.getTitle();
			System.out.println("The songs with play time closest to 240 seconds are " + closestSong1 + " and " + closestSong2);
		}
	
	//Finally, if we have all three songs the same length, this will catch that condition, too
	else
		{
			System.out.println("All three songs are equally close to four minutes in play time.");
		}

	/*
	 * I wasn't going to write in the catches for multiple songs being equally close to 4 minutes, 
	 * because the project specifications don't ask for it. But as soon as I realized it was a 
	 * possibility, I couldn't leave it alone. 
	*/
	
	
	//PHASE 4
	
	//Now we just need to order them from shortest to longest. 
	
	//Heading for end print
	String border = "==========================================================================================================";
	String heading = "Title			      Artist		   Album			Playtime (Seconds)";
	
	//Variables for length comparison. Redundant, I know, but it'll shorten future statements.
	int lengA = song1.getPlayTime();
	int lengB = song2.getPlayTime();
	int lengC = song3.getPlayTime();
	
	if (lengA <= lengB && lengA <= lengC && lengB <= lengC)
		{
			System.out.println("Your ordered playlist is: ");
			System.out.println(border);
			System.out.println(heading);
			System.out.println(border);
			System.out.println(song1.toString());
			System.out.println(song2.toString());
			System.out.println(song3.toString());
			System.out.println(border);
		}
	
	else if (lengA <= lengB && lengA <= lengC && lengC <= lengB) 
		{
			System.out.println("Your ordered playlist is: ");
			System.out.println(border);
			System.out.println(heading);
			System.out.println(border);
			System.out.println(song1.toString());
			System.out.println(song3.toString());
			System.out.println(song2.toString());
			System.out.println(border);
		}
	else if (lengB <= lengA && lengB <= lengC && lengA <= lengC)
		{
			System.out.println("Your ordered playlist is: ");
			System.out.println(border);
			System.out.println(heading);
			System.out.println(border);
			System.out.println(song2.toString());
			System.out.println(song1.toString());
			System.out.println(song3.toString());
			System.out.println(border);
		}
	else if (lengB <= lengA && lengB <= lengC && lengC <= lengA)
		{
			System.out.println("Your ordered playlist is: ");
			System.out.println(border);
			System.out.println(heading);
			System.out.println(border);
			System.out.println(song2.toString());
			System.out.println(song3.toString());
			System.out.println(song1.toString());
			System.out.println(border);
		}
	else if (lengC <= lengA && lengC <= lengB && lengA <= lengB)
		{
			System.out.println("Your ordered playlist is: ");
			System.out.println(border);
			System.out.println(heading);
			System.out.println(border);
			System.out.println(song3.toString());
			System.out.println(song1.toString());
			System.out.println(song2.toString());
			System.out.println(border);
		}
	else
		{
			System.out.println("Your ordered playlist is: ");
			System.out.println(border);
			System.out.println(heading);
			System.out.println(border);
			System.out.println(song3.toString());
			System.out.println(song2.toString());
			System.out.println(song1.toString());
			System.out.println(border);
		}
		
	}
	
}
