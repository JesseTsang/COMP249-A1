package comp249.a1.battleship;

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
			System.out.println("You have " + shipsLeft + " ship[s] left. Please choose a spot for one of your ship[s]:");
			
			playerShipPos = shipInput.next();
				
			if(isValidPosition(playerShipPos) == true)
			{
				int shipIndexPos = getArrayIndex(playerShipPos);
				
				resourcesPosition[shipIndexPos] = player.getShipSign();
				board.setBattleshipGridArray(resourcesPosition); //Map that sees all
				player.setBattleMap(resourcesPosition);			 //Map that only show player's resources
				
				shipsLeft--;
			}
			
			//Update player's ship positions.
			Board.displayBoard(player.getBattleMap());
		}
		
		Scanner bombInput = new Scanner(System.in);
		String playerBombPos;
		
		while(bombsLeft != 0)
		{
			System.out.println("You have " + bombsLeft + " bomb[s] left. Please choose a spot for one of your bomb[s]:");
			
			playerBombPos = bombInput.next();
					
			if(isValidPosition(playerBombPos) == true)
			{
				int bombIndexPos = getArrayIndex(playerBombPos);
				
				resourcesPosition[bombIndexPos] = player.getBombSign();
				board.setBattleshipGridArray(resourcesPosition);
				player.setBattleMap(resourcesPosition);
				
				bombsLeft--;
			}
			
			//Update player's ship positions.
			Board.displayBoard(player.getBattleMap());	
		}
		
		bombInput.close();
	}

	

	private boolean isValidPosition(String playerShipPos) 
	{
		// TODO Auto-generated method stub
		return true;
	}
	
	private int getArrayIndex(String playerShipPos) 
	{
		final int ASCII_COEFFICIENT = 65;
		
		int resultIndex = 0;
		
		//Extract row (1..8) and column (A..H) value from input
		int row = Integer.parseInt(playerShipPos.substring(0,1));
			
		char columnChar = Character.toUpperCase(playerShipPos.charAt(1));
					
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
		// TODO Auto-generated method stub
		
	}

	private void startGame(Player player1, Player player2, Board board) 
	{
		// TODO Auto-generated method stub		
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