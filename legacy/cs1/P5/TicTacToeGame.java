import java.awt.Point;

/*This is our game logic for the Tic-Tac-Toe GUI program. It's intended to explain the newGame,
 * choose, and gameOver functions, as well as tell the computer when/how to designate a winner of a
 * game, if there is one.
 * 
 * @author: Jim "JCIII" Crowell
 * 			setup and debug assist Annika "SnarkKnight" McCain 
 * 
 * @version: 1.0
 * 
 * @established: 12/04/20 
 */


public class TicTacToeGame implements TicTacToe {

	private BoardChoice[][] playingField;
	private static final int BOUNDS = 3;
	private GameState gameState;
	private int flashPoint;
	private Point[] takenMoves;
	private BoardChoice lastChoice;

	
	//Stultefaction, my old friend.... This semester's not quite at end....
	
	//Constructor
	public TicTacToeGame() {
		newGame();
	}	
	
	//New game
	public void newGame() {
		lastChoice = BoardChoice.OPEN;
		takenMoves = new Point[9];
		flashPoint = 0;
		playingField = new BoardChoice[BOUNDS][BOUNDS];
		for (int i = 0; i < playingField.length; i++) {
			for (int j = 0; j < playingField.length; j++) {
				playingField[i][j] = BoardChoice.OPEN;
			}
		}
		gameState = GameState.IN_PROGRESS;
	}

	
	//Mechanics for choosing a space
	public boolean choose(BoardChoice player, int row, int col) {
		if (gameOver()) {
			return false;
		}
		else if (!playingField[row][col].equals(BoardChoice.OPEN)) {
			return false;
		}
		else if (lastChoice == player) {
			return false;
		}
		else if (row < 0 || row > 3 || col < 0 || col > 3) {
			return false;
		}
		else {
			playingField[row][col] = player;
			lastChoice = player;			
			takenMoves[flashPoint] = new Point(row, col);
			flashPoint++;
			return true;
			}
		}

	//Scopes for any possible ways the game may be over
	public boolean gameOver() {
		//Loop checking for horizontal wins 
		for (int i = 0; i < playingField.length; i++) {
			if (playingField[i][0].equals(playingField[i][1]) && playingField[i][1].equals(playingField[i][2])
					&& !playingField[i][2].equals(BoardChoice.OPEN)) {
				if (playingField[i][2].equals(BoardChoice.X)) {
					gameState = GameState.X_WON;
				}
				else { 
					gameState = GameState.O_WON;
				}
				return true;
			}
			//Loop checking for vertical wins
			if (playingField[0][i].equals(playingField[1][i]) && playingField[1][i].equals(playingField[2][i])
					&& !playingField[2][i].equals(BoardChoice.OPEN)) {
				if (playingField[2][i].equals(BoardChoice.X)) {
					gameState = GameState.X_WON;
				}
				else {
					gameState = GameState.O_WON;
				}
				getMoves();
				return true;
			}
		}
	
		//Diagonal from left
		if (playingField[0][0].equals(playingField[1][1]) && playingField[1][1].equals(playingField[2][2])
				&&!playingField[2][2].equals(BoardChoice.OPEN)) {
			if (playingField[2][2].equals(BoardChoice.X)) {
				gameState = GameState.X_WON;
			}
			else {
				gameState = GameState.O_WON;
			}
			return true;
		}
		//Diagonal from right
		if (playingField[2][0].equals(playingField[1][1]) && playingField[1][1].equals(playingField[0][2])
				&&!playingField[0][2].equals(BoardChoice.OPEN)) {
			if (playingField[0][2].equals(BoardChoice.X)) {
				gameState = GameState.X_WON;
			}
			else {
				gameState = GameState.O_WON;
			}
			return true;
		}
					
		//Tie
		if (flashPoint == 9) {
			gameState = GameState.TIE;
			return true;
		}
		return false;
	}
	
	//Returns current boolean state of game; over or not
	public GameState getGameState() {		
		if (gameOver()) {
			return gameState;
		}
		else {
			return GameState.IN_PROGRESS;
		}		
	}
	
	//Obtain a copy of the current board, with moves (if any), return it
	public BoardChoice[][] getGameGrid() {
		BoardChoice[][] boardCopy = new BoardChoice[3][3];
		for (int i = 0; i < playingField.length; i++) {
			for (int j = 0; j < playingField.length; j++) {
				boardCopy[i][j] = playingField[i][j];
			}
		}
		return boardCopy;
	}
 
	//Stenographer
	public Point[] getMoves() {
		Point[] cloneMoves = new Point[flashPoint];
		for (int i = 0; i < flashPoint; i++) {
			cloneMoves[i] = takenMoves[i];
		}
		return cloneMoves;
	}

}
