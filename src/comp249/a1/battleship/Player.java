package comp249.a1.battleship;

public class Player 
{
	private String name;
	private int hp;
	
	private String shipSign;
	private String bombSign;
	
	private String[] battleMap;
	
	public static final int NUM_OF_SHIPS = 6;
	public static final int BOARD_SIZE = 64;
	
	public Player(String shipSign, String bombSign)
	{
		this.name = "";
		this.hp = NUM_OF_SHIPS;
		
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
