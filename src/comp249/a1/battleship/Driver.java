package comp249.a1.battleship;

public class Driver 
{
	public static void main(String[] args) 
	{
		//BattleshipGame testGame = new BattleshipGame(PlayerType.HUMAN, PlayerType.COMPUTER);
		//testGame.startGame();
		
		BattleshipGame testGame2 = new BattleshipGame(PlayerType.COMPUTER, PlayerType.COMPUTER);
		testGame2.startGame();	
	}
}
