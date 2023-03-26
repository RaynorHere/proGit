import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents a 2D circuit board as read from an input file.
 *  
 * @author mvail
 */
public class CircuitBoard {
	/** current contents of the board */
	private char[][] board;
	/** location of row,col for '1' */
	private Point startingPoint;
	/** location of row,col for '2' */
	private Point endingPoint;

	//constants you may find useful
	private final int ROWS; //initialized in constructor
	private final int COLS; //initialized in constructor
	private final char OPEN = 'O'; //capital 'o'
	private final char CLOSED = 'X';
	private final char TRACE = 'T';
	private final char START = '1';
	private final char END = '2';
	private final String ALLOWED_CHARS = "OXT12";

	/** Construct a CircuitBoard from a given board input file, where the first
	 * line contains the number of rows and columns as ints and each subsequent
	 * line is one row of characters representing the contents of that position.
	 * Valid characters are as follows:
	 *  'O' an open position
	 *  'X' an occupied, unavailable position
	 *  '1' first of two components needing to be connected
	 *  '2' second of two components needing to be connected
	 *  'T' is not expected in input files - represents part of the trace
	 *   connecting components 1 and 2 in the solution
	 * 
	 * @param filename
	 * 		file containing a grid of characters
	 * @throws FileNotFoundException if Scanner cannot read the file
	 * @throws InvalidFileFormatException for any other format or content issue that prevents reading a valid input file
	 */
	public CircuitBoard(String filename) throws FileNotFoundException {
		Scanner fileScan = new Scanner(new File(filename));
		
		// Pull lines in, individually, to parse from File
		String lineIn = fileScan.nextLine();
		
		Scanner dimPop = new Scanner(lineIn);
		int rowIn, colIn = 0;
		
		startingPoint = new Point();
		endingPoint = new Point();
		
		// Pull in number of rows
		if (dimPop.hasNextInt() == false) {
			dimPop.close();
			throw new InvalidFileFormatException("Error: Expect integers for row dimension");
		}
		else
			rowIn = dimPop.nextInt();	
		
		if (rowIn < 1) {
			dimPop.close();
			throw new InvalidFileFormatException("Error: File passes fewer than 1 rows");
		}
		
		// Columns
		if (dimPop.hasNextInt() == false) {
			dimPop.close();
			throw new InvalidFileFormatException("Error: Expect integers for column dimension");
		}
		else
			colIn = dimPop.nextInt();
		
		if (colIn < 1) {
			dimPop.close();
			throw new InvalidFileFormatException("Error: File passes fewer than 1 columns");
		}
		
		// If there's anything left on that top line
		if (dimPop.hasNext()) {
			dimPop.close();
			throw new InvalidFileFormatException("Error: Initial line contains more than just row and column dimensions");
		}

		
		dimPop.close();
		
		board = new char[rowIn][colIn];
		
		// Time to populate array. We will need a couple booleans to ensure our starting and 
		// ending points are accounted for, as well
		boolean found1 = false;
		boolean found2 = false;
		
		// Populate array through nested loops
		// Outer row loop
		for (int i = 0; i < rowIn; i++) {
			
			// Here we check to ensure there are still rows where there are supposed to rows
			if (!fileScan.hasNextLine()) {
				fileScan.close();
				throw new InvalidFileFormatException("Error: File declares " + rowIn + " rows, but ends after row " + i + ".");
			}
			
			lineIn = fileScan.nextLine();
			Scanner boardPop = new Scanner(lineIn);
			
			// Inner column loop
			for (int j = 0; j < colIn; j++) {
								
				// Here we check to ensure there are still columns where there are supposed to be columns
				if (!boardPop.hasNext()) {
					boardPop.close();
					fileScan.close();
					throw new InvalidFileFormatException("Error: Row " + i + " contains fewer columns than it claims");
					
				}
				
				// If there are, we'll initiate this little variable to take in our tokens
				String unconChar = "";
				
				// So, we need to have a character, and String and character don't convert back
				// and forth super easily. So, we'll pull in "unconverted character", as a String...
				unconChar = boardPop.next();
				
				// Now, let's make sure that whatever we just pulled in is one of the valid
				// characters described above
				if (!ALLOWED_CHARS.contains(unconChar)) {
					boardPop.close();
					throw new InvalidFileFormatException("Error: Input file attempts to pass characters other than"
							+ " O, X, T, 1 or 2 as board elements");
				}
				// Assuming all that's cool, we can convert our "STRING" OF ONE &%^$#%# CHARACTER
				// To a character
				else
					board[i][j] = unconChar.charAt(0);
				
				// Special circumstance: starting point
				if (unconChar.equals("1")) {
					if (found1) {
						boardPop.close();
						throw new InvalidFileFormatException("Error: file passes two starting points");
					}
					else {
						found1 = true;
						startingPoint.setLocation(i, j);
					}
					
				}
				
				// Special circumstance: ending point
				if (unconChar.equals("2")) {
					if (found2) {
						boardPop.close();
						throw new InvalidFileFormatException("Error: file passes two ending points");
					}
					else {
						found2 = true;
						endingPoint.setLocation(i, j);
					}
				}
				

				
				// And that's one iteration of the loop populating our character array
			}
			
			// Down here, we ensure that the file keeps to its prescribed number of columns
			// If there's anything left after j hits the limit of colIn, there's too many
			if (boardPop.hasNext()) {
				boardPop.close();
				throw new InvalidFileFormatException("Error: row " + i + " contains too many columns");
			}			
			
			boardPop.close();			
			
		}
		
		// And here, we'll run a couple neat tricks to make sure there's no more rows than
		// what we were told there would be
		if (fileScan.hasNextLine()) {
			// Sometimes there's negative space at the bottom of a file, but that's not TECHNICALLY illegal
			lineIn = fileScan.nextLine().trim();
			
			Scanner blankCheck = new Scanner(lineIn);
			if (blankCheck.hasNext()) {
				blankCheck.close();
				throw new InvalidFileFormatException("Error: input file declares " + rowIn + " rows, but contains more.");
			}
			
			blankCheck.close();
				
		}
		
		// Now, the mirror of making sure we don't have TWO starts/ends above: making sure we have ANY
		if (!found1)
			throw new InvalidFileFormatException("Error: file does not pass a starting point");
		
		if (!found2)
			throw new InvalidFileFormatException("Error: file does not pass an ending point");
		
		ROWS = rowIn; 
		COLS = colIn;
		
		fileScan.close();
	}
	
	/** Copy constructor - duplicates original board
	 * 
	 * @param original board to copy
	 */
	public CircuitBoard(CircuitBoard original) {
		board = original.getBoard();
		startingPoint = new Point(original.startingPoint);
		endingPoint = new Point(original.endingPoint);
		ROWS = original.numRows();
		COLS = original.numCols();
	}

	/** utility method for copy constructor
	 * @return copy of board array */
	private char[][] getBoard() {
		char[][] copy = new char[board.length][board[0].length];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				copy[row][col] = board[row][col];
			}
		}
		return copy;
	}
	
	/** Return the char at board position x,y
	 * @param row row coordinate
	 * @param col col coordinate
	 * @return char at row, col
	 */
	public char charAt(int row, int col) {
		return board[row][col];
	}
	
	/** Return whether given board position is open
	 * @param row
	 * @param col
	 * @return true if position at (row, col) is open 
	 */
	public boolean isOpen(int row, int col) {
		if (row < 0 || row >= board.length || col < 0 || col >= board[row].length) {
			return false;
		}
		return board[row][col] == OPEN;
	}
	
	/** Set given position to be a 'T'
	 * @param row
	 * @param col
	 * @throws OccupiedPositionException if given position is not open
	 */
	public void makeTrace(int row, int col) {
		if (isOpen(row, col)) {
			board[row][col] = TRACE;
		} else {
			throw new OccupiedPositionException("row " + row + ", col " + col + "contains '" + board[row][col] + "'");
		}
	}
	
	/** @return starting Point(row,col) */
	public Point getStartingPoint() {
		return new Point(startingPoint);
	}
	
	/** @return ending Point(row,col) */
	public Point getEndingPoint() {
		return new Point(endingPoint);
	}
	
	/** @return number of rows in this CircuitBoard */
	public int numRows() {
		return ROWS;
	}
	
	/** @return number of columns in this CircuitBoard */
	public int numCols() {
		return COLS;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				str.append(board[row][col] + " ");
			}
			str.append("\n");
		}
		return str.toString();
	}
	
}// class CircuitBoard
