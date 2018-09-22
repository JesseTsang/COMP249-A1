package comp249.a1.battleship;

import java.util.Random;
import java.util.Scanner;

public class BattleshipGame 
{
	private Player player1, player2;
	private Board board;
	
	private static final int MAX_SHIPS = 6;
	private static final int MAX_BOMBS = 4;
	
	private static final String PLAYER1_SHIP_SIGN = "S";
	private static final String PLAYER1_BOMB_SIGN = "B";
	
	private static final String PLAYER2_SHIP_SIGN = "s";
	private static final String PLAYER2_BOMB_SIGN = "b";
	
	public BattleshipGame()
	{
		this.player1 = new Player(PLAYER1_SHIP_SIGN, PLAYER1_BOMB_SIGN);
		this.player2 = new Player(PLAYER2_SHIP_SIGN, PLAYER2_BOMB_SIGN);
		
		this.board = new Board();
	}
	
	private void getPlayerInfo(Player player) 
	{
		System.out.println("Hello player, please enter your name: ");
		
		@SuppressWarnings("resource")
		Scanner playerInput = new Scanner(System.in);
		String playerName = playerInput.next();
		
		if(playerName == null)
		{
			System.out.println("No name detected. Defaulted name is used.");
			player.setName("Player1");
		}
		else
		{
			player.setName(playerName);
		}	
	}
	
	private void setShips(Player player, Board board) 
	{
		System.out.println("Hello " + player.getName() + "! " + "You have " + MAX_SHIPS + " ships and " + MAX_BOMBS +" bombs available:");
		System.out.println("Here are all the possible position to place your resources:");
		
		board.displayBoard();
		
		int shipsLeft = MAX_SHIPS;
		int bombsLeft = MAX_BOMBS;
		
		String[] resourcesPosition = board.getBattleshipGridArray();
		
		@SuppressWarnings("resource")
		Scanner shipInput = new Scanner(System.in);
		String playerShipPos;
		
		while(shipsLeft != 0)
		{
			System.out.println("You have " + shipsLeft + " ship[s] left. Please choose a spot for one of your ship[s]: [A..H][1..8]");
			
			playerShipPos = shipInput.next();
				
			if(isValidPosition(playerShipPos) == true)
			{
				if(isPositionFree(playerShipPos, board.getBattleshipGridArray()) == true)
				{
					int shipIndexPos = getArrayIndex(playerShipPos);
					
					resourcesPosition[shipIndexPos] = player.getShipSign();
					board.setBattleshipGridArray(resourcesPosition); //Map that sees all
					player.setBattleMap(resourcesPosition);			 //Map that only show player's resources
					
					shipsLeft--;
					
					//Update player's ship positions.
					Board.displayBoard(player.getBattleMap());
				}
				else
				{
					System.out.println("Invalid input. The position is already occupied. Please re-enter.");
					
					continue;
				}
			}
			else
			{
				System.out.println("Invalid input. The valid input range is [A..H][1..8]. Please re-enter.");
				
				continue;
			}	
		}
		
		Scanner bombInput = new Scanner(System.in);
		String playerBombPos;
		
		while(bombsLeft != 0)
		{
			System.out.println("You have " + bombsLeft + " bomb[s] left. Please choose a spot for one of your bomb[s]:");
			
			playerBombPos = bombInput.next();
					
			if(isValidPosition(playerBombPos) == true)
			{
				if(isPositionFree(playerBombPos, board.getBattleshipGridArray()) == true)
				{
					int bombIndexPos = getArrayIndex(playerBombPos);
					
					resourcesPosition[bombIndexPos] = player.getBombSign();
					board.setBattleshipGridArray(resourcesPosition);
					player.setBattleMap(resourcesPosition);
					
					bombsLeft--;
					
					//Update player's ship positions.
					Board.displayBoard(player.getBattleMap());	
				}
				else //Occupied space
				{
					System.out.println("Invalid input. The position is already occupied. Please re-enter.");
					
					continue;
				}
			}
			else //Invalid position. Out of board range.
			{
				System.out.println("Invalid input. The valid input range is [A..H][1..8]. Please re-enter.");
				
				continue;				
			}			
		}
		
		bombInput.close();
	}

	private boolean isValidPosition(String playerShipPos) 
	{		
		final int COL_COORDINATE = 0;
		final int ROW_COORDINATE = 1;
		
		boolean isStingLengthValid = (playerShipPos != null && playerShipPos.length() == 2);
		boolean isColumnLetter = Character.isLetter(playerShipPos.charAt(COL_COORDINATE));
		boolean isColumnInRange;
		boolean isRowNumeric = Character.isDigit(playerShipPos.charAt(ROW_COORDINATE));
		boolean isRowInRange;
		
		//If it is not NULL or length != 2
		//If the first character is a letter
		//If the second character is a number
		if((isStingLengthValid == true) && (isColumnLetter == true) && (isRowNumeric == true))
		{
			char columnChar = Character.toUpperCase(playerShipPos.charAt(COL_COORDINATE));
			isColumnInRange = (columnChar >= 'A' && columnChar <= 'H');
			
			int rowIndex = Character.getNumericValue(playerShipPos.charAt(ROW_COORDINATE));
			isRowInRange = (rowIndex >= 1 && rowIndex <= 8);
			
			if(isColumnInRange == true && isRowInRange == true)
			{
				return true;			
			}
		}
			
		return false;
	}
	
	private boolean isPositionFree(String playerShipPos, String[] boardFullMap) 
	{
		int position = getArrayIndex(playerShipPos);
		
		if(boardFullMap[position].equals("_"))
		{
			return true;
		}
		else
		{
			System.out.println("POSITION NOT EMPTY! " + boardFullMap[position]);
		}
		
		return false;
	}
	
	private int getArrayIndex(String playerShipPos) 
	{
		final int ASCII_COEFFICIENT = 65;
		final int COL_COORDINATE = 0;
		final int ROW_COORDINATE = 1;
		
		int resultIndex = 0;
		
		//Extract row (1..8) and column (A..H) value from input
		int row = Integer.parseInt(playerShipPos.substring(ROW_COORDINATE));
			
		char columnChar = Character.toUpperCase(playerShipPos.charAt(COL_COORDINATE));
					
		//Translate column char value into integer (0..7)
		int column = (int)columnChar - ASCII_COEFFICIENT;
			
		//Variable to store the index of first column of each row.
		int rowCoefficient = (row * Board.BOARD_COLUMN_SIZE) - Board.BOARD_COLUMN_SIZE ;
		
		//Combine row and column value to get real index value.
		resultIndex = rowCoefficient + column;
		
		return resultIndex;	
	}

	private void generateShips(Player player, Board board) 
	{		
		String[] battlemap = board.getBattleshipGridArray();
		
		Random rand = new Random();
		
		int shipsAvailable = MAX_SHIPS;
		int bombsAvailable = MAX_BOMBS;
		
		int boardSize = Board.BOARD_COLUMN_SIZE * Board.BOARD_ROW_SIZE;
		
		int randomPosition;
		
		while(shipsAvailable > 0)
		{
			randomPosition = rand.nextInt(boardSize);
			
			if(battlemap[randomPosition].equals("_"))
			{
				battlemap[randomPosition] = player.getShipSign();
				
				shipsAvailable--;
			}	
		}
		
		while(bombsAvailable > 0)
		{
			randomPosition = rand.nextInt(boardSize);
			
			if(battlemap[randomPosition].equals("_"))
			{
				battlemap[randomPosition] = player.getBombSign();
				
				bombsAvailable--;
			}	
		}
		
	}

	private void startGame(Player player1, Player player2, Board board) 
	{
		board.displayBoard();
	}

	public void run()
	{
		getPlayerInfo(player1);
		setShips(player1, board);
		generateShips(player2, board);
		startGame(player1, player2, board);				
	}
	
	public static void main(String[] args)
	{
		BattleshipGame testGame = new BattleshipGame();
		testGame.run();
	}
}