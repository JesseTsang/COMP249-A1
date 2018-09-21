package comp249.a1.battleship;

public class Board 
{	
	private String[] battleshipGridArray;
	
	public static final int BOARD_ROW_SIZE = 8;
	public static final int BOARD_COLUMN_SIZE = 8;
	
	public Board()
	{
		int boardSize = BOARD_ROW_SIZE * BOARD_COLUMN_SIZE;
		
		this.battleshipGridArray = new String[boardSize];
	}
	
	public void displayBoard()
	{
		int newLineChecker = 0;
		int lineCounter = 1;
		
		//Print columns labels
		System.out.println("  A B C D E F G H");
		
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
		
		System.out.println("");
	}
	
	public static void displayBoard(String[] board)
	{
		int newLineChecker = 0;
		int lineCounter = 1;
		
		//Print columns labels
		System.out.println("  A B C D E F G H");
		
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
		testBoard.displayBoard();		
	}
}