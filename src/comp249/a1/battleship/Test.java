package comp249.a1.battleship;

public class Test 
{

	public static void main(String[] args) 
	{
		int newLineChecker = 0;
		int lineCounter = 1;
		
		String[] battleshipGridArray = new String[64];
		
		battleshipGridArray[0] = "S";
		battleshipGridArray[3] = "G";
		battleshipGridArray[6] = "S";
		
		battleshipGridArray[7] = "g";
		battleshipGridArray[15] = "s";
		battleshipGridArray[63] = "s";
		
		System.out.println("  A B C D E F G H");
		
		System.out.print(lineCounter + " ");
		lineCounter++;
				
		for(int j = 1; j < battleshipGridArray.length + 1; j++)
		{				
			if(battleshipGridArray[j - 1] == null)
			{
				battleshipGridArray[j - 1] = "_";
			}
				
			System.out.print(battleshipGridArray[j - 1] + " ");
			
			newLineChecker = j % 8; 
			//System.out.print("Checker is " + newLineChecker);
			
			if(newLineChecker == 0 && (lineCounter < 9))
			{
				System.out.println("");
				System.out.print(lineCounter + " ");
				
				lineCounter++;
			}
		}		
	}
}
