import java.util.Scanner;


public class RubixCubeSolver {

	//variables
	public static String szMenuChoice, szScrambleCode, szEnter = "";
	public static Scanner szKeyboard = new Scanner(System.in);
	public static Cube Cube = new Cube() ;
	public static Move Move = new Move() ;
	public static String[][] szNetOfCube = new String[12][9] ;
	public static solver solve = new solver();
	public static boolean bToggle = false , bCheckToggle = false , bScramble = false;
	//setters
	
	//getters
	
	//utilities
	public static void getNetMain()
	{
		for(int i = 0 ; i < 12 ;i++)
		{
			for(int j = 0 ; j < 9 ; j++)
			{
				szNetOfCube[i][j] = Cube.getNet(i, j) ;
			}
		}
	}
	
	public static void enterCurrentDetails()
	{
		
		String string = "" ;
		 
		for(int i = 0 ; i < 12 ; i++)
		{
			for(int j = 0 ; j < 9 ;j++)
			{	
				string = szNetOfCube[i][j] ;
					
				solve.enterCurrentNetDetails(string , i , j);
			}
		}
	}
	
	public static void Menu()
	{
		System.out.println("=========== RUBIX CUBE SOLVER ===========\n"
				+ "The following program will help you solve a simple 3 by 3 rubix cube.  "
				+ "You can have\nscramble codes  generated to scramble an already solved cube, if you are confident,"
				+ "\nor you can choose to enter in the positions of each piece of your scrambled cube."
				+ "\nAlternatively, you can learn the notation that will be relevant to understanding\nhow the program "
				+ "will instruct you to solve."
				+ "\nPlease enter the necessary key to determine which path you would like to take: "
				+ "\n\nNotation (N)"
				+ "\nSolve Cube (C)"
				+ "\nRandomly Generate and Solve (S)"
				+ "\nGenerate Scramble Code (G)") ;
		szMenuChoice = szKeyboard.nextLine();
		
		while(!(szMenuChoice.equalsIgnoreCase("S") || szMenuChoice.equalsIgnoreCase("N")
				|| szMenuChoice.equalsIgnoreCase("G") || szMenuChoice.equalsIgnoreCase("C")))
		{
			System.out.println("Please enter the necessary key."
					+ "\n\nNotation (N)"
					+ "\nSolve Cube (C)"
					+ "\nRandomly Generate and Solve (S)"
					+ "\nGenerate Scramble Code (G)");
			szMenuChoice = szKeyboard.nextLine();
		}
		
	}
	
	public static void main(String[] args) { 
		
		String szToggleChoice = "";
		Scanner szKeyboard = new Scanner(System.in);
		boolean bCheckBlue = false , bCheck = false , bCheck2 = true;
		
		//Menu
		Menu() ;
		
		szMenuChoice = szMenuChoice.toUpperCase();
		
		//switch case for whatever option they chose
		switch(szMenuChoice)
		{
		
		case "N":
			{
				//this is the simplest one.  it calls a function in another file designed to build a GUI that
				//displays the basic instructions
				
				NotationGUI.Build() ; 
				break ;
			}
		
		case "C":
			{
				//This is where 90% of the code will stem from.  The user will enter in the colours on each side of their cube
				//broken down into the white face, yellow face etc etc.
				
				//YYOBWBYGW
				//GYBGYYRGW
				//BYORROGBY
				//GWBGORROR
				//BWGOGROWW
				//YBOOBWWRR
				
				//
				//
				//
				//
				//
				//
				
				while(bCheckBlue == false)
				{

					Cube.setWhiteCentreFace();
					Cube.setYellowCentreFace();
					Cube.setRedCentreFace();
					Cube.setOrangeCentreFace();
					Cube.setGreenCentreFace();
					bCheckBlue = Cube.setBlueCentreFace();
					
				}
				
				Cube.Build();
				Cube.displayNet();
			
				
				//store the net into an array on this side so we can use it and then make sure
				//the values of the net have been sent to the Solve file so they can be used.
				getNetMain();
				enterCurrentDetails() ;

				if(solve.checkIfSolved() == false)
				{

					while(bCheck2 == true)
					{
						System.out.println("\nBefore starting the process to solving your cube, please enter if you would like the program to output"
								+ "\nyour instructions to follow in the Rubik's Cube notation form (which is harder) or in pure text form.  Enter "
								+ "\n0 for the notation or 1 for the pure text.");
						szToggleChoice = szKeyboard.nextLine();
						
						if(szToggleChoice.equals("0"))
						{
							bToggle = false ;
							break;
						} else if(szToggleChoice.equals("1"))
						{
							bToggle = true ;
							break;
						} else
						{
							System.out.println("\nThat is not a zero or a one.");
							bCheck2 = true ;
						}
						
					}
					
					
					
					//Now that the cube that the user wants to solve has been generated, the system must now solve it.  There are six core stages to solving a Rubix Cube.
					//The first of which is to solve the white side.  This can be further broken into multiple stages.
					
					// ---- STEP ONE: SOLVE THE WHITE SIDE ----
					
						// ---- STEP ONE.ONE: THE WHITE CROSS ----
						solve.findandMoveWhiteEdges(bToggle , bScramble);
						
						// ---- STEP ONE.TWO: WHITE CORNERS
						solve.findandMoveWhiteCorners(bScramble);
					
					// ---- STEP TWO: SOLVE THE MIDDLE LAYER ----
						solve.solveMiddleLayer(bScramble) ;

					// ---- STEP THREE: SOLVE THE YELLOW SIDE ----
						
						// ---- STEP THREE.ONE: YELLOW EDGES ----
						
						solve.solveYellowEdges(bScramble);
						
						// ---- STEP THREE.TWO: YELLOW CORNERS ----
						bCheck = solve.solveYellowCorners(bScramble);
						
						if(bCheck == false)
						{
							// ---- STEP FOUR: SOLVE YELLOW CORRESPONDING CORNERS ----
							solve.fixYellowCorners(bScramble);
							solve.fixYellowEdges(bScramble);
						} else
						{
							System.out.println("The yellow corner that is not facing upwards must now be twisted so that it is"
									+ " facing upwards.\nThen restart the program and key in the values on your cube with the yellow"
									+ " face now complete.");
						}
	
					//Should be solved
				} else
				{
					System.out.println("Your cube is already solved.");
				}
				
				
					
				break ;
			}
			
		case "G":
		{
			Cube.setSolvedCube();
			
			getNetMain() ;
			enterCurrentDetails() ;
			
			bToggle = false ;
			bScramble = true ; 
			
			szScrambleCode = solve.generateScramble();
			
			System.out.println("Scramble Code is " + szScrambleCode);
			
			break;
		}
			
		case "S":
		{
			Cube.setSolvedCube();
			getNetMain() ;
			enterCurrentDetails() ;
			bToggle = false ;
			bScramble = true ; 
			szScrambleCode = solve.generateScramble();
	//		System.out.println("Scramble Code is " + szScrambleCode);
	//		System.out.println("Press enter to have the system solve it.");
			szEnter = szKeyboard.nextLine();
			
			// ---- STEP ONE.ONE: THE WHITE CROSS ----
			solve.findandMoveWhiteEdges(bToggle , bScramble);
			
			// ---- STEP ONE.TWO: WHITE CORNERS
			solve.findandMoveWhiteCorners(bScramble);
		
		// ---- STEP TWO: SOLVE THE MIDDLE LAYER ----
			solve.solveMiddleLayer(bScramble) ;

		// ---- STEP THREE: SOLVE THE YELLOW SIDE ----
			
			// ---- STEP THREE.ONE: YELLOW EDGES ----
			
			solve.solveYellowEdges(bScramble);
			
			// ---- STEP THREE.TWO: YELLOW CORNERS ----
			solve.solveYellowCorners(bScramble);
		
		// ---- STEP FOUR: SOLVE YELLOW CORRESPONDING CORNERS ----
			solve.fixYellowCorners(bScramble);
			solve.fixYellowEdges(bScramble);
			
			break ;
		}
		
			 
		}
		
		szKeyboard.close();
	}

}
