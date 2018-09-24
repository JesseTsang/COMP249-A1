package comp249.a1.battleship;

/**
* <h1>The board class implements a String[] of size determined by
* predefined constants. This array simulate the map of a Battleship game.</h1>
* <p>
* This class provides getter and setter functions to update the array.
* This class also allows you to display the full map or only player's map (if user provide their array).
* 
*
* @author  Jesse Tsang
* @version 1.0
* @since   23th September 2018
*/
public class Board 
{	
	private String[] battleshipGridArray;
	
	public static final int BOARD_ROW_SIZE = 8;
	public static final int BOARD_COLUMN_SIZE = 8;
	
	public static final int ASCII_COEFFICIENT = 65; //Starts with A.
	
	public Board()
	{
		int boardSize = BOARD_ROW_SIZE * BOARD_COLUMN_SIZE;
		
		this.battleshipGridArray = new String[boardSize];
	}
	
	public void displayBoard()
	{
		int newLineChecker = 0;
		int lineCounter = 1;
		
		int asciiValue = ASCII_COEFFICIENT;
		
		//Print columns labels
		System.out.print("  ");
		
		for(int i = 0; i < BOARD_COLUMN_SIZE; i++)			
		{			
			System.out.print((char)asciiValue + " ");
			
			asciiValue = asciiValue + 1;			
		}
		
		System.out.println(" ");
		
		//Print row labels
		System.out.print(lineCounter + " ");
		lineCounter++;
				
		for(int j = 1; j < battleshipGridArray.length + 1; j++)
		{				
			if(battleshipGridArray[j - 1] == null)
			{
				battleshipGridArray[j - 1] = "_";
			}
			
			//Print out node content.
			System.out.print(battleshipGridArray[j - 1] + " ");
			
			//If it's column 8, then we create a new line.
			newLineChecker = j % BOARD_COLUMN_SIZE; 
			
			if(newLineChecker == 0 && (lineCounter <= BOARD_COLUMN_SIZE))
			{
				System.out.println("");
				System.out.print(lineCounter + " ");
				
				lineCounter++;
			}
		}
		
		System.out.println(" ");
	}
	
	public static void displayBoard(String[] board)
	{
		int newLineChecker = 0;
		int lineCounter = 1;
		
		int asciiValue = ASCII_COEFFICIENT;
		
		//Print columns labels
		System.out.print("  ");
		
		for(int i = 0; i < BOARD_COLUMN_SIZE; i++)			
		{
			System.out.print((char)asciiValue + " ");
			
			asciiValue = asciiValue + 1;			
		}
		
		System.out.println(" ");
		
		//Print row labels
		System.out.print(lineCounter + " ");
		lineCounter++;
				
		for(int j = 1; j < board.length + 1; j++)
		{				
			if(board[j - 1] == null)
			{
				board[j - 1] = "_";
			}
			
			//Print out node content.
			System.out.print(board[j - 1] + " ");
			
			//If it's column 8, then we create a new line.
			newLineChecker = j % BOARD_COLUMN_SIZE; 
			
			if(newLineChecker == 0 && (lineCounter <= BOARD_COLUMN_SIZE))
			{
				System.out.println("");
				System.out.print(lineCounter + " ");
				
				lineCounter++;
			}	
		}
		
		System.out.println("");
	}
	
	public String[] getBattleshipGridArray() 
	{
		return battleshipGridArray;
	}

	public void setBattleshipGridArray(String[] battleshipGridArray) 
	{
		this.battleshipGridArray = battleshipGridArray;
	}

	public static void main(String[] args)
	{
		Board testBoard = new Board();
		
		String[] test = testBoard.getBattleshipGridArray();
		
		for(int j = 0; j < test.length; j++)
		{
			test[j] = String.valueOf(j);			
		}
		
		//testBoard.displayBoard();
		Board.displayBoard(test);
	}
}