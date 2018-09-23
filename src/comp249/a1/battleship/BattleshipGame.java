package comp249.a1.battleship;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;


/**
* <h1>The Battleship program implements an application that play the
* Battleship game between human and NPC or any combination between them.</h1>
* <p>
* 1. User could run the program by invoking the constructor and providing the type of two players.
* 2. Then invoke the startGame() method.
*
* @author  Jesse Tsang
* @version 1.0
* @since   23th September 2018
*/
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
	
	public BattleshipGame(PlayerType type1, PlayerType type2)
	{
		this.player1 = new Player(PLAYER1_SHIP_SIGN, PLAYER1_BOMB_SIGN, type1);
		this.player2 = new Player(PLAYER2_SHIP_SIGN, PLAYER2_BOMB_SIGN, type2);
		
		this.board = new Board();
	}
	
	private void getPlayerInfo(Player player) 
	{
		if(player.getPlayerType() == PlayerType.HUMAN)
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
		else if(player.getPlayerType() == PlayerType.COMPUTER)
		{
			String playerName = "Computer";
			player.setName(playerName);
		}		
	}
	
	private void setResources(Player player, Board board) 
	{
		if(player.getPlayerType() == PlayerType.HUMAN)
		{
			setShips(player, board);
			setBombs(player, board);
		}
		else if(player.getPlayerType() == PlayerType.COMPUTER)
		{
			generateResources(player, board);	
		}
	}

	private void setShips(Player player, Board board) 
	{
		System.out.println("Hello " + player.getName() + "! " + "You have " + MAX_SHIPS + " ships and " + MAX_BOMBS +" bombs available:");
		System.out.println("Here are all the possible position to place your resources:");
		
		board.displayBoard();
		
		int shipsLeft = MAX_SHIPS;
		
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
		
	}
	
	private void setBombs(Player player, Board board) 
	{
		int bombsLeft = MAX_BOMBS;
		String[] resourcesPosition = board.getBattleshipGridArray();
		
		@SuppressWarnings("resource")
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
	}
	
	private void generateResources(Player player, Board board) 
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


	public void startGame() 
	{
		getPlayerInfo(player1);
		getPlayerInfo(player2);
		
		setResources(player1, board);
		setResources(player2, board);
					
		Player currentPlayer;
		Player otherPlayer;
		
		Queue<Player> waitingQueue = new LinkedList<>();
			
		currentPlayer = generateStartPlayer();
		otherPlayer = getOtherPlayer(currentPlayer);
		
		waitingQueue.add(currentPlayer);
		
		//Continue the game as long as the queue has player[s].				
		while(!waitingQueue.isEmpty())
		{
			System.out.println("----- Current map -----");
			board.displayBoard();
			
			currentPlayer = waitingQueue.remove();
			otherPlayer = getOtherPlayer(currentPlayer);
			
			takeTurn(currentPlayer, otherPlayer, waitingQueue);				
		}
	}
	
	private void takeTurn(Player currentPlayer, Player otherPlayer, Queue<Player> waitingQueue) 
	{
		//Trio variables to get user coordinate input.
		@SuppressWarnings("resource")
		Scanner targetInput = new Scanner(System.in);
		String targetString;
		int targetCoordinate;
		
		String[] turnMap = board.getBattleshipGridArray();
		
		System.out.println("Player " + currentPlayer.getName() + "! It is your turn!");
		System.out.println("Enter a coordinate to bomb:");
		
		targetString = targetInput.next();
		
		if(isValidPosition(targetString) == true)
		{
			targetCoordinate = getArrayIndex(targetString);
			
			//Hits bombs condition
			if(turnMap[targetCoordinate].equalsIgnoreCase("B"))
			{
				System.out.println("Player hits a bomb!! HAHA!!");
				System.out.println("Player's turn end~ Player loses 2 turns!!");
				
				waitingQueue.add(otherPlayer);
				waitingQueue.add(otherPlayer);
			}
			else if(turnMap[targetCoordinate].equalsIgnoreCase("S")) //Hits ships condition
			{
				System.out.println("Player hits a ship!! Very good!!");
				deductHP(targetCoordinate);
				
				waitingQueue.add(otherPlayer);
			}
			else if(turnMap[targetCoordinate].equals("_")) //Hits nothing condition
			{
				System.out.println("Bomb hits nothing!");
				
				waitingQueue.add(otherPlayer);
			}
			else
			{
				System.out.println("Error: takeTurn() - Posision not bombs, not ship, nor empty.");
				System.out.println("Cell data: "+ turnMap[targetCoordinate]);
			}
		}
		
	}

	private void deductHP(int targetCoordinate) 
	{
		String[] battleMap = board.getBattleshipGridArray();
		
		int hp;
		
		if(battleMap[targetCoordinate].equals(PLAYER1_SHIP_SIGN))
		{
			hp = player1.getHp();
			player1.setHp(hp - 1);	
			
			if(player1.getHp() <= 0)
			{
				endGame();				
			}
		}
		else if(battleMap[targetCoordinate].equals(PLAYER2_SHIP_SIGN))
		{
			hp = player2.getHp();
			player2.setHp(hp - 1);
			
			if(player2.getHp() <= 0)
			{
				endGame();				
			}
		}
		else
		{
			System.out.println("Error: deductHP() - Position is not a ship: " + battleMap[targetCoordinate]);	
		}
	}
	
	private Player generateStartPlayer() 
	{
		Player startPlayer;
		Random rand = new Random();
		
		int startNumber = rand.nextInt(2);
		
		if(startNumber == 0)
		{
			startPlayer = player1;			
		}
		else
		{
			startPlayer = player2;
		}
		
		System.out.println("Start number is: " + startNumber);
		System.out.println("Start player is: " + startPlayer.getName());
		
		return startPlayer;
	}
	
	private Player getOtherPlayer(Player currentPlayer) 
	{
		Player otherPlayer;
		
		if(currentPlayer.getName().equals(player1.getName()))
		{
			otherPlayer = player2;
			
			return otherPlayer;
		}
		else if(currentPlayer.getName().equals(player2.getName()))
		{
			otherPlayer = player1;
			
			return otherPlayer;
		}
		else
		{
			System.out.println("Error: getOtherPlayer(): currentPlayer != anyone.");
		}
		
		return null;
	}
	
	private void endGame() 
	{
		int player1HP = player1.getHp();
		int player2HP = player2.getHp();
		
		if(player1HP <= 0 &&(player2.getPlayerType().equals(PlayerType.COMPUTER)))
		{
			System.out.println("Sorry!! NPC won!! Play again!");
		}
		else //Player2 human
		{
			System.out.println("Congratulations " + player2.getName() + "! You won!");			
		}
			
		
		if(player2HP <= 0 &&(player1.getPlayerType().equals(PlayerType.HUMAN)))
		{
			System.out.println("Congratulations " + player1.getName() + "! You won!");
		}
		else //Player1 NPC
		{
			System.out.println("Sorry!! NPC won!! Play again!");			
		}	
	}

	public static void main(String[] args)
	{
		BattleshipGame testGame = new BattleshipGame(PlayerType.HUMAN, PlayerType.COMPUTER);
		testGame.startGame();;
	}
}