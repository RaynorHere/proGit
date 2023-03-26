import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * Search for shortest paths between start and end points on a circuit board
 * as read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to
 * a GUI according to options specified via command-line arguments.
 * 
 * @author mvail
 */
public class CircuitTracer {

	/** launch the program
	 * @param args three required arguments:
	 *  first arg: -s for stack or -q for queue
	 *  second arg: -c for console output or -g for GUI output
	 *  third arg: input file name  
	 */
	public static void main(String[] args) {
		new CircuitTracer(args); //create this with args
	}

	/** Print instructions for running CircuitTracer from the command line. */
	private void printUsage() {
		// any command line args
		// See https://en.wikipedia.org/wiki/Usage_message for format and content guidance
		System.out.println("Intended Program Syntax: java CircuitTracer [-s|-q] [-c|-g] [file]");
		System.out.println("-s or -q Execute in stack mode (depth first) or queue mode (breadth first)");
		System.out.println("-c or -g Execute in console (Command Line) or GUI interface");
		System.out.println("file is the intended file for the program to process. Must be files of .dat extension and "
			+ "located in the same folder as CircuitTracer.java");		
	}
	
	/** 
	 * Set up the CircuitBoard and all other components based on command
	 * line arguments.
	 * 
	 * @param args command line arguments passed through from main()
	 */
	public CircuitTracer(String[] args) {
		// GIVEN: if the program's passing more or fewer than 3 arguments, something's wrong
		if (args.length != 3) {
			printUsage();
			return; //exit the constructor immediately
		}
		
		// The first argument passed absolutely MUST be -s or -q, so otherwise, error message
		if (!args[0].equals("-s") && !args[0].equals("-q")) {
			printUsage();
			return;
		}
		
		// Argument two can only be -c or -g, otherwise, error message
		if (!args[1].equals("-c") && !args[1].equals("-g")) {
			printUsage();
			return;
		}
		
		if (args[1].equals("-g")) {
			System.out.println("Terribly sorry, but GUI mode is not yet implemented in this version.");
			return;
		}
		
		// Originally, I had some conditionals here to make sure that the passed filename both exists and
		// can be successfully converted into a file, but as it turns out, I can just include a catch
		// statement for a FileNotFoundException, and handle it that way; I don't need to pre-screen
		// the file passed as the final argument; the program architecture does it for me
		String fileName = args[2];	
		
		// Assuming all these things are correct, we can now begin the process of... Well, processing the data
		

		// Initialize the board
		try {
			CircuitBoard oldSolder = new CircuitBoard(fileName);
			
			// Declare storage object, which we will typify depending on what was passed as arg[0]
			Storage<TraceState> daTank;
			
			// Pick yer storage type
			if (args[0].equals("-s")) {
				daTank = Storage.getStackInstance();
			} else {
				daTank = Storage.getQueueInstance();
			}
				// Here's our list of best paths
				ArrayList<TraceState> pathsOfLeastTravel = new ArrayList<TraceState>();
				
				Point beginPoint = oldSolder.getStartingPoint();
				int startRow = (int) beginPoint.getX();
				int startCol = (int) beginPoint.getY();
				
				// Attempting to set up starting trace position options (directions we can go immediately)
				if (oldSolder.isOpen(startRow -1, startCol)) {
					TraceState startUp = new TraceState(oldSolder, startRow -1, startCol);
					daTank.store(startUp);
				}
				
				// I canNOT stress enough how clumsy and stupid this feels. This feels inelegant. This feels
				// like if code could be rendered ridiculously, apoplectically drunk, this is what it would
				// look like. Like, "your family disowns you for at least a week" drunk
				if (oldSolder.isOpen(startRow +1, startCol)) {
					TraceState startDown = new TraceState(oldSolder, startRow +1, startCol);
					daTank.store(startDown);
				}
				
				if (oldSolder.isOpen(startRow, startCol -1)) {
					TraceState startLeft = new TraceState(oldSolder, startRow, startCol -1);
					daTank.store(startLeft);
				}
				
				if (oldSolder.isOpen(startRow, startCol +1)) {
					TraceState startRight = new TraceState(oldSolder, startRow, startCol +1);
					daTank.store(startRight);
				}
				
				// Now that our state storage is full of every possible opening move, I think we can
				// advance to actually proving/processing potential paths to probable prevailment
				while (!daTank.isEmpty()) {
					
					// Luckily, the Storage class is written in such a way that I can have one single 
					// unifying code for it, no matter the type it was initialized as (stack or queue)
					// and the program will handle it autonomously. That's good foresight
					TraceState currentPosition = daTank.retrieve();
					
					// We'll use these flags to avoid a ConcurrentModException; Improved Path or Same Paths
					boolean imPath = false;
					boolean similPath = false;
					
					// First, see if we're at a finished state yet
					if (currentPosition.isComplete()) {
						
						// If we are, have we found any finished states before? 
						// If not, throw this one into that list
						if (pathsOfLeastTravel.isEmpty()) {
							pathsOfLeastTravel.add(currentPosition);
						}
						
						// If so, comparison time
						else {
							
							// Pull out every traceState in the "best paths" bucket (which will only
							// ever be of a single path length), and compare them to the current one
							for (TraceState defendingChamp : pathsOfLeastTravel) {
								
								// If the current one is shorter
								if (currentPosition.pathLength() < defendingChamp.pathLength()) {									
									imPath = true;
								}
								
								// If the current one is the same length
								else if (currentPosition.pathLength() == defendingChamp.pathLength()) {
									similPath = true;
								}
							}
							
							// If we've found a better path
							if (imPath) {
								pathsOfLeastTravel.clear();
								pathsOfLeastTravel.add(currentPosition);
							}
							
							// If we've found an equivalent path
							else if (similPath) {
								pathsOfLeastTravel.add(currentPosition);
							}
						}
					}
					
					// And here's what we do if it is not yet a complete path
					else {
						
						// First order of bidness is to geolocate
						int hereRow = currentPosition.getRow();
						int hereCol = currentPosition.getCol();
						
						// Then we kinda just repeat what we did to start with: up, down, left right
						
						if (currentPosition.getBoard().isOpen(hereRow -1, hereCol)) {
							TraceState moveUp = new TraceState(currentPosition, hereRow -1, hereCol);
							daTank.store(moveUp);
						}
						
						if (currentPosition.getBoard().isOpen(hereRow +1, hereCol)) {
							TraceState moveDown = new TraceState(currentPosition, hereRow +1, hereCol);
							daTank.store(moveDown);
						}
						
						if (currentPosition.getBoard().isOpen(hereRow, hereCol -1)) {
							TraceState moveLeft = new TraceState(currentPosition, hereRow, hereCol -1);
							daTank.store(moveLeft);
						}
						
						if (currentPosition.getBoard().isOpen(hereRow, hereCol +1)) {
							TraceState moveRight = new TraceState(currentPosition, hereRow, hereCol +1);
							daTank.store(moveRight);
						}
					}
					
					
				}
				
				// Down here, we are finally completely free of the while loop, so, ostensibly, we have all our
				// possible best traces, and can output them				
				for (TraceState reigningChamp : pathsOfLeastTravel) {					
					System.out.println(reigningChamp.getBoard().toString());
				}
				
			
			
		} catch (FileNotFoundException e) {
			// This is obnoxious; if I try to put out a regular error message, even one tailored to this error, the 
			// tester rejects it. This is the only method of doing this that passes the tester
			System.out.println(e.toString());	
			return;
		} catch (InvalidFileFormatException e) {
			System.out.println(e.toString());
			return;
		}
	}
	
} // class CircuitTracer
