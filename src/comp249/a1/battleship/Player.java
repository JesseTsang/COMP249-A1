package comp249.a1.battleship;

/**
* <h1>The Player class implements a data type that simulates a player in the Battleship game.</h1>
* <p>
* Each object has a name, health point (hp), player type, their ship and bomb sign, and a map only contain their resources.
* This class also allows you to display the full map or only player's map (if user provide their array).
* 
*
* @author  Jesse Tsang
* @version 1.0
* @since   23th September 2018
*/
public class Player 
{
	private String name;
	private int hp;
	private PlayerType playerType;
	
	private String shipSign;
	private String bombSign;
	
	private String[] battleMap; //A "board" with only this player's resources in it. Use with Board.displayBoard(battleMap) to show user his map.
	
	public static final int NUM_OF_SHIPS = 6;
	public static final int BOARD_SIZE = 64;
	
	public Player(String shipSign, String bombSign, PlayerType playerType)
	{
		this.name = "";
		this.hp = NUM_OF_SHIPS;
		
		this.playerType = playerType;
		
		this.shipSign = shipSign;
		this.bombSign = bombSign;
		
		this.battleMap = new String[BOARD_SIZE];
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getHp() 
	{
		return hp;
	}

	public void setHp(int hp) 
	{
		this.hp = hp;
	}
	
	public PlayerType getPlayerType() 
	{
		return playerType;
	}

	public void setPlayerType(PlayerType playerType) 
	{
		this.playerType = playerType;
	}

	public String getShipSign() 
	{
		return shipSign;
	}

	public String getBombSign() 
	{
		return bombSign;
	}

	public String[] getBattleMap() 
	{
		return battleMap;
	}

	public void setBattleMap(String[] battleMap) 
	{
		this.battleMap = battleMap;
	}
}
