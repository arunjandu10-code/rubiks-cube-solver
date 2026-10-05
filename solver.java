import java.util.Scanner; 

public class solver {

	//variables
	public Move move = new Move() ;
	public Cube Cube = new Cube() ;

	//variables for solving the white edge pieces       
	//								  0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
	public int[] iWhiteEdgePiecesX = {4,3,5,4,4,3,5,4,4,4, 3, 5, 1, 0, 2, 1, 7, 6, 8, 7, 4, 4, 3, 5};
	public int[] iWhiteEdgePiecesY = {0,1,1,2,3,4,4,5,6,8, 7, 7, 6, 7, 7, 8, 6, 7, 7, 8, 9,11,10,10};
	public int[] iWhiteEdgeColourX = {4,0,8,4,4,1,7,4,4,4, 2, 6, 3, 3, 3, 3, 5, 5, 5, 5, 4, 4, 1, 7};
	public int[] iWhiteEdgeColourY ={11,7,7,3,2,6,6,6,5,9, 7, 7, 4, 1, 7,10, 4, 7, 1,10, 8, 0, 8, 8};
	public int[] iWhiteEdgeFoundX = new int[4];
	public int[] iWhiteEdgeFoundY = new int[4];
	public int[] iWhiteColourFoundX = new int[4];
	public int[] iWhiteColourFoundY = new int[4];
	public int[] iWhitePieceSolvedX = new int[4];
	public int[] iWhitePieceSolvedY = new int[4];
	public int[] iPlacement = new int[4];
	public String[] szWhiteEdgePieceLocation = new String[4];
	
	public Scanner szKeyboard = new Scanner(System.in) ;


	//variables for solving the white corners
	public int[] iWhiteCornersFoundX = new int[4] ;
	public int[] iWhiteCornersFoundY = new int[4] ;
	public String szFace = "" , string = "" , string2 = "";

	//variables for solving the middle layer
	public String szCoord = "";


	//general variables
	public String[][] szNetOfCube = new String[12][9] ;



	public String[] szColourFound = new String[4];

	public String szCase = "", szAlgorithm = "" , szTempCol = "" , szTempCol2 = "" , szTempCol3 = "";
	public int f = 0, k = 0, w1 = 0 , CurrentX = 0, CurrentY = 0 , k2 = 0 , a = 0;
	public boolean b = false , bTrue = false , bRed = false , bOrange = false , bGreen = false , bBlue = false;

	//constructor
	public solver()
	{

	}

	//default

	//specific

	//methods
	
	//=======================================Steps To Solve The Rubik's Cube=======================================

	public void findandMoveWhiteEdges(boolean bToggle , boolean bScramble)
	{
		//Now we need to move each white piece to its desired place.  Based on the determined heirarchy of the arrays,
		//the value stored in iWhiteEdgeFoundX[0] and iWhiteEdgeFoundY[0] relate to the corresponding colour in iWhiteColourFoundX[0] and
		//iWhiteColourFoundY[0] and the colour found is stored in szColourFound[0] and so on for all incrementing values.

		//From here on it's going to be a lot of statements.  There are 23 possible locations for the pieces, and only one correct destination for each.
		//If the corresponding colour is a R, then the piece stored at szNetOfCube[iWhiteEdgeFoundY[i]][iWhiteEdgeFoundX[i]] for when the value stored in
		//szColourFound[i] equals "R", MUST be stored in position szNetOfCube[5][4] because that is where the White Red connector is.

		//With all the faces working accurately, all I need to do is have a number of predetermined cases based on where the piece is stored, rotate the
		//net accordingly, and log the moves so it can be output to the console for the user to use.

		//reset a to 0 for the new array of found white pieces after the first move.  This will continue to loop until the program has gone into
		//the first if loop four times, and each of those four times gone inside the specific nested if statement to clarify that the piece is in
		//the correct place

		//4 across 6 down, 3 across 7 down, 5 across 7 down, 4 across 8 down
		
		System.out.println("========= WHITE EDGES =========");

		
		
		findAndAssignWhite() ;
		
		//Firstly send the net to Move.java
		sendNetToMove() ;

		int d = 0 ;
		for(int b = 0 ; b < 24 ; b++)
		{
			if(iWhiteEdgeFoundX[d] == iWhiteEdgePiecesX[b] && iWhiteEdgeFoundY[d] == iWhiteEdgePiecesY[b])
			{
				if(iWhiteEdgeFoundX[d] == 4 && iWhiteEdgeFoundY[d] == 6  || iWhiteEdgeFoundX[d] == 4 && iWhiteEdgeFoundY[d] == 8
						|| iWhiteEdgeFoundX[d] == 3 && iWhiteEdgeFoundY[d] == 7  || iWhiteEdgeFoundX[d] == 5 && iWhiteEdgeFoundY[d] == 7 )
				{

				} else
				{
					//Then this is the placement of the piece and the case will be accordingly found
					szCase = String.valueOf(b) ;
					bTrue = true ;
					szTempCol = szColourFound[d] ;
					break ;
				}
			}

			if(bTrue == true )
			{
				break ;
			}
		}

		szTempCol = szColourFound[d] ;

		switches(szCase , d , bToggle) ;



		//because the net has changed due to the moving of the pieces, some of the white
		//pieces may have shifted too, so we need to run the entire bit of the program that locates the other white pieces
		//barring the one that we had moved.


		for(int y = 0 ; y < 12 ; y++)
		{
			for(int x = 0 ; x < 9 ; x++)
			{
				szNetOfCube[y][x]  = move.getNet(y, x) ;
			}
		}

		//After this has been run, one (or more depending on the iteration) of the white pieces found should be in the correct place.
		//This white piece will fall into one of the cases, and it will do nothing inside of the case because it is already in the correct place
		
		System.out.println("The algorithm is: " + szAlgorithm);

		if(bScramble == false)
		{
			System.out.println("Press enter when you would like to continue: ");
			szKeyboard.nextLine() ;

		} else
		{
			try
			{
				Thread.sleep(1500);
			}catch(Exception e)
			{
				System.out.println("Error: " + e);
			}
		}
		
		findAndAssignWhite() ;

		sendNetToMove() ;

		d = 0 ;
		bTrue = false ;

		while(bTrue == false)
		{
			if(szColourFound[d].equals(szTempCol))
			{
				bTrue = false ;
				d++ ;
			} else
			{
				bTrue = true ;
			}
		}

		for(int b = 0 ; b < 24 ; b++)
		{
			if(iWhiteEdgeFoundX[d] == iWhiteEdgePiecesX[b] && iWhiteEdgeFoundY[d] == iWhiteEdgePiecesY[b])
			{
				szCase = String.valueOf(b) ;
			}
		}

		szTempCol2 = szColourFound[d] ;

		szAlgorithm = "" ;

		switches(szCase , d , bToggle) ;

		for(int y = 0 ; y < 12 ; y++)
		{
			for(int x = 0 ; x < 9 ; x++)
			{
				szNetOfCube[y][x]  = move.getNet(y, x) ;
			}
		}

		System.out.println("The algorithm is: " + szAlgorithm);

		if(bScramble == false)
		{
			System.out.println("Press enter when you would like to continue: ");
			szKeyboard.nextLine() ;

		} else
		{
			try
			{
				Thread.sleep(1500);
			}catch(Exception e)
			{
				System.out.println("Error: " + e);
			}
		}

		findAndAssignWhite() ;

		sendNetToMove() ;

		d = 0 ;
		bTrue = false ;

		while(bTrue == false)
		{
			if(szColourFound[d].equals(szTempCol) || szColourFound[d].equals(szTempCol2))
			{
				bTrue = false ;
				d++ ;
			} else
			{
				bTrue = true ;
			}
		}


		for(int b = 0 ; b < 24 ; b++)
		{
			if(iWhiteEdgeFoundX[d] == iWhiteEdgePiecesX[b] && iWhiteEdgeFoundY[d] == iWhiteEdgePiecesY[b])
			{
				szCase = String.valueOf(b) ;
			}
		}

		szTempCol3 = szColourFound[d] ;

		szAlgorithm = "" ;

		switches(szCase , d , bToggle) ;

		for(int y = 0 ; y < 12 ; y++)
		{
			for(int x = 0 ; x < 9 ; x++)
			{
				szNetOfCube[y][x]  = move.getNet(y, x) ;
			}
		}

		System.out.println("The algorithm is: " + szAlgorithm);

		if(bScramble == false)
		{
			System.out.println("Press enter when you would like to continue: ");
			szKeyboard.nextLine() ;

		} else
		{
			try
			{
				Thread.sleep(1500);
			}catch(Exception e)
			{
				System.out.println("Error: " + e);
			}
		}

		findAndAssignWhite() ;

		sendNetToMove() ;

		d = 0 ;
		bTrue = false ;

		while(bTrue == false)
		{
			if(szColourFound[d].equals(szTempCol) || szColourFound[d].equals(szTempCol2) || szColourFound[d].equals(szTempCol3))
			{
				bTrue = false ;
				d++ ;
			} else
			{
				bTrue = true ;
			}
		}

		for(int b = 0 ; b < 24 ; b++)
		{
			if(iWhiteEdgeFoundX[d] == iWhiteEdgePiecesX[b] && iWhiteEdgeFoundY[d] == iWhiteEdgePiecesY[b])
			{
				szCase = String.valueOf(b) ;
			}
		}

		szAlgorithm = "" ;

		switches(szCase , d , bToggle) ;

		for(int y = 0 ; y < 12 ; y++)
		{
			for(int x = 0 ; x < 9 ; x++)
			{
				szNetOfCube[y][x]  = move.getNet(y, x) ;
			}
		}

		System.out.println("The algorithm is: " + szAlgorithm);

		if(bScramble == false)
		{
			System.out.println("Press enter when you would like to continue: ");
			szKeyboard.nextLine() ;

		} else
		{
			try
			{
				Thread.sleep(1500);
			}catch(Exception e)
			{
				System.out.println("Error: " + e);
			}
		}

	}
	
	public void findandMoveWhiteCorners(boolean bScramble)
	{
		//The function of this subroutine is to move all the white corners into place and display the subsequent
		//algorithm for the user to use to solve it.  This one will be harder than the white edges as for every 
		//one white corner found, there are TWO other colours
		
		System.out.println("========= WHITE CORNERS =========");
		
		//first we need to find where the four white corner pieces lie, similar to the white edge pieces
		findCorners() ;

		//find out which face the corner piece lies on
		findFace(iWhiteCornersFoundY[0] , iWhiteCornersFoundX[0]) ;

		//now we have all the information needed.  We know the face in which the first corner lies and the corresponding colours so we know
		//where the piece has to go, ultimately.  If the corresponding colours are G - O then the piece has to go in position x = 5, y = 8

		moveCorners(szFace , iWhiteCornersFoundX[0] , iWhiteCornersFoundY[0]) ;
		
		move.display();

		if(bScramble == false)
		{
			System.out.println("Press enter when you would like to continue: ");
			szKeyboard.nextLine() ;

		} else
		{
			try
			{
				Thread.sleep(1500);
			}catch(Exception e)
			{
				System.out.println("Error: " + e);
			}
		}

		//	move.display();

		for(int a = 0 ; a < 3 ; a++)
		{
			//SECOND CORNER
			//update net
			updateNet();

			//After running this subroutine, one of the pieces will be in the correct place but some of the others will be shuffled
			//so we must again run the subroutine findCorners and then work around the piece that is correct

			findCorners() ;

			for(int i = 0 ; i < 4 ; i++)
			{
				if(!  ( ( (iWhiteCornersFoundX[i] == 3 && iWhiteCornersFoundY[i] == 6) 
						&& (szNetOfCube[5][3].equals("R") && szNetOfCube[6][2].equals("B") ) ) ||

						( (iWhiteCornersFoundX[i] == 3 && iWhiteCornersFoundY[i] == 8)
								&& (szNetOfCube[9][3].equals("O") && szNetOfCube[8][2].equals("B") ) ) ||

						( (iWhiteCornersFoundX[i] == 5 && iWhiteCornersFoundY[i] == 6) 
								&& (szNetOfCube[5][5].equals("R") && szNetOfCube[6][6].equals("G") ))||

						( (iWhiteCornersFoundX[i] == 5 && iWhiteCornersFoundY[i] == 8) ) 
						&& (szNetOfCube[9][5].equals("O") && szNetOfCube[8][6].equals("G") )) )
				{

					k = i;
					break;

				} 
			}

			findFace(iWhiteCornersFoundY[k] , iWhiteCornersFoundX[k]);
			moveCorners(szFace , iWhiteCornersFoundX[k] , iWhiteCornersFoundY[k]) ;

			move.display();
			
			if(bScramble == false)
			{
				System.out.println("Press enter when you would like to continue: ");
				szKeyboard.nextLine() ;

			} else
			{
				try
				{
					Thread.sleep(1500);
				}catch(Exception e)
				{
					System.out.println("Error: " + e);
				}
			}
			

		}		

		System.out.println("Your cube should now look like this with the first step complete: ");
		move.display();
		
	}

	public void solveMiddleLayer(boolean bScramble)
	{

		//The middle layer consists of the ring of edge pieces that go around the red -> green -> orange -> blue
		//faces.  These four pieces are, RED-GREEN, GREEN-ORANGE, ORANGE-BLUE and BLUE-RED and they can be in
		//any of the four spaces around the middle layer OR the four spaces on the yellow face that remains
		//unsolved now that the white layer IS solved.

		//The hard thing about this one is that we are not searching for a generic colour.  With solving the white
		//face, to locate the necessary pieces we were always looking for the letter "W".  Now, we cannot

		//Therefore I must make use of the four arrays that I created to solve the white edges, iWhiteEdgePiecesX[]
		//and iWhiteEdgePiecesY[], iWhiteEdgeColourX[] and iWhiteEdgeColourY, but only use the predetermined values
		//in there that correspond to the yellow face and the three unsolved edges on each of the red,
		//green, blue and orange faces.

		//Yellow Face = Position 0 - 3
		//Red Face (Unsolved) = Position 4 - 6
		//Green Face (Unsolved) = Position 16, 18 and 19
		//Orange Face (Unsolved) = Position 21-23
		//Blue Face (Unsolved) = Position 12 , 13 and 15
		
		System.out.println("========= SOLVE MIDDLE LAYER =========");
		
		int x = 0, y = 0, iLength = 0;
		String colour = "";
		
		if(bScramble == false)
		{
			System.out.println("Press enter when you would like to continue: ");
			szKeyboard.nextLine() ;

		} else
		{
			try
			{
				Thread.sleep(1500);
			}catch(Exception e)
			{
				System.out.println("Error: " + e);
			}
		}
		updateNet() ;

		//initiate for loop
		for(int i = 0 ; i < 4 ; i++)
		{
			//max of four since there are four pieces to be moved and we are trying to be efficient
			switch(i)
			{
				case 0:
				{
					System.out.println("----- OG -----");
					
					szCoord = searchColour("O" , "G");

					x = breakdownCoord(szCoord , 0);
					y = breakdownCoord(szCoord , 1) ;

					iLength = szCoord.length();
					colour = String.valueOf(szCoord.charAt(iLength-1)) ;
					
					//if colour is G then we want to find the orange and move it
					if(colour.equals("G"))
					{
						for(int i2 = 0 ; i2 < 24 ; i2++)
						{
							if(iWhiteEdgePiecesX[i2] == x && iWhiteEdgePiecesY[i2] == y)
							{
								x = iWhiteEdgeColourX[i2];
								y = iWhiteEdgeColourY[i2];
								colour = "O";
							}
						}
					}

					if(x != 5 || y != 10)
					{
						moveMiddle("O", x , y);

						move.moveOrangeFace("B", 1);
						move.moveYellowFace("U'", 1);
						move.moveOrangeFace("B'", 1);
						move.moveYellowFace("U'", 1);
						move.moveGreenFace("R'", 1);
						move.moveYellowFace("U", 1);
						move.moveGreenFace("R", 1);

						System.out.println("B1 , U'1 , B'1 , U'1 , R'1 , U1 , R1");
					}

					updateNet() ;
					
					move.display() ;
					break ;
				}
				
				case 1:
				{
					System.out.println("----- OB -----");
					
					szCoord = searchColour("O" , "B");

					x = breakdownCoord(szCoord , 0);
					y = breakdownCoord(szCoord , 1) ;

					iLength = szCoord.length();
					colour = String.valueOf(szCoord.charAt(iLength-1)) ;
					
					//if colour is G then we want to find the orange and move it
					if(colour.equals("B"))
					{
						for(int i2 = 0 ; i2 < 24 ; i2++)
						{
							if(iWhiteEdgePiecesX[i2] == x && iWhiteEdgePiecesY[i2] == y)
							{
								x = iWhiteEdgeColourX[i2];
								y = iWhiteEdgeColourY[i2];
								colour = "O";
							}
						}
					}

					if(x != 3 || y != 10)
					{
						moveMiddle("O", x , y);

						move.moveOrangeFace("B'", 1);
						move.moveYellowFace("U", 1);
						move.moveOrangeFace("B", 1);
						move.moveYellowFace("U", 1);
						move.moveBlueFace("L", 1);
						move.moveYellowFace("U'", 1);
						move.moveBlueFace("L'", 1);
						
						System.out.println("B' U1 B U L U' L'");

					}
					
					updateNet() ;
					
					move.display() ;
					break ;
				}
					
				case 2:
				{
					System.out.println("----- BR -----");
					
					szCoord = searchColour("B" , "R");

					x = breakdownCoord(szCoord , 0);
					y = breakdownCoord(szCoord , 1) ;

					iLength = szCoord.length();
					colour = String.valueOf(szCoord.charAt(iLength-1)) ;

					//if colour is G then we want to find the orange and move it
					if(colour.equals("R"))
					{
						for(int i2 = 0 ; i2 < 24 ; i2++)
						{
							if(iWhiteEdgePiecesX[i2] == x && iWhiteEdgePiecesY[i2] == y)
							{
								x = iWhiteEdgeColourX[i2];
								y = iWhiteEdgeColourY[i2];
								colour = "B";
							}
						}
					}

					if(x != 1 || y != 6)
					{
						moveMiddle("B", x , y);
						
						move.moveYellowFace("U'", 1);
						move.moveBlueFace("L'", 1);
						move.moveYellowFace("U", 1);
						move.moveBlueFace("L", 1);
						move.moveYellowFace("U", 1);
						move.moveRedFace("F", 1);
						move.moveYellowFace("U'", 1);
						move.moveRedFace("F'", 1);
						
						System.out.println("U' L' U L U F U' F'");
						
						//U' L' U L U F U' F'
					}

					updateNet() ;
					
					move.display() ;
					break ;
				}
				
				case 3:
				{
					System.out.println("----- RG -----");
					
					szCoord = searchColour("R" , "G");

					x = breakdownCoord(szCoord , 0);
					y = breakdownCoord(szCoord , 1) ;

					iLength = szCoord.length();
					colour = String.valueOf(szCoord.charAt(iLength-1)) ;

					//if colour is G then we want to find the orange and move it
					if(colour.equals("R"))
					{
						for(int i2 = 0 ; i2 < 24 ; i2++)
						{
							if(iWhiteEdgePiecesX[i2] == x && iWhiteEdgePiecesY[i2] == y)
							{
								x = iWhiteEdgeColourX[i2];
								y = iWhiteEdgeColourY[i2];
								colour = "G";
							}
						}
					}

					if(x != 7 || y != 6)
					{
						moveMiddle("G", x , y);
						
						move.moveYellowFace("U", 1);
						move.moveGreenFace("R", 1);
						move.moveYellowFace("U'", 1);
						move.moveGreenFace("R'", 1);
						move.moveYellowFace("U'", 1);
						move.moveRedFace("F'", 1);
						move.moveYellowFace("U", 1);
						move.moveRedFace("F", 1);
						
						System.out.println("U R U' R' U' F' U F");
						
						//U R U' R' U' F' U F
					}

					updateNet() ;
					
					move.display() ;
					break ;
				}
			}
			
			szCase = "";
			
			if(bScramble == false)
			{
				System.out.println("Press enter when you would like to continue: ");
				szKeyboard.nextLine() ;

			} else
			{
				try
				{
					Thread.sleep(1500);
				}catch(Exception e)
				{
					System.out.println("Error: " + e);
				}
			}
			
		}

	}

	public void solveYellowEdges(boolean bScramble)
	{
		System.out.println("========= SOLVE YELLOW EDGES =========");
		
		int iState = 0;
		
		//Now most of the cube is solved.  The white face and the first two layers.  But instead of tackling
		//the third layer now, we will focus our attention onto the yellow face, and then finally the yellow
		//edges.

		//to solve the yellow corners per the CFOP method, we need to analyse the cube to see which of 
		//three specific states it matches.

		//State Two:
		//No yellow edges facing upwards -> Orientate -> Yellow Line -> Solved Edge

		//State Zero:
		//Yellow line -> Orientate -> Solved Edge

		//State One:
		//Yellow "L" -> Orientate -> Solved Edge

		iState = realiseyellowState();

		if(iState == 0)
		{
			System.out.println("0");
			
			move.yellowLine();
			move.yellowL();
			
			updateNet() ;

		} else if(iState == 1)
		{
			System.out.println("1");
			move.yellowL() ;
			
			updateNet() ;

		} else if(iState == 2)
		{
			System.out.println("2");
			move.yellowNone() ;

			realiseyellowState() ;

			move.yellowLine() ;

			realiseyellowState() ;

			move.yellowL() ;
			
			updateNet() ;
		}
			
		move.display();
		
		if(bScramble == false)
		{
			System.out.println("Press enter when you would like to continue: ");
			szKeyboard.nextLine() ;

		} else
		{
			try
			{
				Thread.sleep(1500);
			}catch(Exception e)
			{
				System.out.println("Error: " + e);
			}
		}
		
		}
	
	public boolean solveYellowCorners(boolean bScramble)
	{
		boolean bUnsolvable = false ;
		
		System.out.println("========= SOLVE YELLOW CORNERS =========");
		
		int iCount = 0, iUnsolvableCount = 0;
		
		while(iCount != 4)
		{
			
			iCount = checkyellowCornerState() ;
			
			if(iCount == 0)
			{
				//no pieces are orientated upwards.
				yellowcornerRepeatAlg();
				
				updateNet() ;
				
			} else if(iCount == 1)
			{
			
				while(!szNetOfCube[2][3].equals("Y"))
				{
					move.moveYellowFace("U", 1);
					
					System.out.println("U1");
					
					updateNet() ;
					
					if(iUnsolvableCount == 6)
					{
						System.out.println("This cube is unsolvable.  Instructions will follow.");
						bUnsolvable = true ;
						break ;
					}
					
					iUnsolvableCount++;
				}
				
				if(bUnsolvable == false)
				{
					yellowcornerRepeatAlg() ;
					
					updateNet() ;
				}
				
				
			} else if(iCount == 2)
			{
				while(!szNetOfCube[3][3].equals("Y"))
				{
					move.moveYellowFace("U", 1);
					
					System.out.println("U1");
					
					updateNet() ;
					
					if(iUnsolvableCount == 6)
					{
						System.out.println("The cube is unsolvable, sorry.");
						bUnsolvable = true ;
						break ;
					}
					
					iUnsolvableCount++;
				}
				
				if(bUnsolvable == false)
				{
					yellowcornerRepeatAlg() ;
					
					updateNet() ;
				}
			} else
			{
				System.out.println("YELLOW STATE SOLVED");
			}
			
			move.display();
			
			if(bScramble == false)
			{
				System.out.println("Press enter when you would like to continue: ");
				szKeyboard.nextLine() ;

			} else
			{
				try
				{
					Thread.sleep(1500);
				}catch(Exception e)
				{
					System.out.println("Error: " + e);
				}
			}
		
			if(bUnsolvable == true)
			{
				break ;
			}
			
			
		}
		
		
		
		move.display();
		
		return bUnsolvable ; 
	}

	public void fixYellowCorners(boolean bScramble)
	{
		System.out.println("========= FIX YELLOW CORNERS =========");
		
		boolean bDone = false ;
		
		//We are now nearing the end of the solution.  This is the penultimate step and requires
		//the sorting of the four yellow corners into the correct orientation.  So, yellow should face up,
		//and the other two colours should connect to their correct colours too.
		
		//Because of the nature of the cube, if you get two colours of a three sided piece into the correct
		//orientation, then the last piece must be in the correct space, so the upper layer can only be
		//in three states.  State One is one set of two edges are in the correct orientation, State Two is neither are
		//and State Three is both are, which in that case we move on.
		
		while(bDone == false)
		{
			//State One
			if(szNetOfCube[3][3].equals(szNetOfCube[3][5]) && !(szNetOfCube[11][3].equals(szNetOfCube[11][5])))
			{
				//then the pieces facing towards you are correctly positioned and the ones farthest away from you are unsolved.
				//thus, we must spin the yellow face by two, and perform the yellow corner algorithm
				move.moveYellowFace("U", 2);
				System.out.println("U2");
				
				move.yellowCornerAlg() ;
				updateNet() ;
			} else if(szNetOfCube[11][3].equals(szNetOfCube[11][5]) && !(szNetOfCube[3][3].equals(szNetOfCube[3][5])))
			{
				//then the pieces facing farthest away from you are unsolved and so we can just perform the yellow corner algorithm
				move.yellowCornerAlg() ;
				updateNet() ;
			} else if(szNetOfCube[6][8].equals(szNetOfCube[8][8]) && !(szNetOfCube[6][0].equals(szNetOfCube[8][0])))
			{
				move.moveYellowFace("U'",1);
				System.out.println("U'1");
				
				move.yellowCornerAlg() ;
				updateNet() ;
			} else if(szNetOfCube[8][0].equals(szNetOfCube[6][0]) && !(szNetOfCube[6][8].equals(szNetOfCube[8][8])))
			{
				move.moveYellowFace("U",1);
				System.out.println("U1");
				
				move.yellowCornerAlg() ;
				updateNet() ;
			}
			//State Three
			else if(szNetOfCube[3][3].equals(szNetOfCube[3][5]) && (szNetOfCube[11][3].equals(szNetOfCube[11][3]) ) )
			{
				if(szNetOfCube[8][0].equals(szNetOfCube[6][0]) && (szNetOfCube[6][8].equals(szNetOfCube[8][8])))
				{
					System.out.println("This step is complete.");
					bDone = true ;
				}
			} 
			//State Two
			else
			{
				//Just do the yellow corner algorithm and then reassess
				move.yellowCornerAlg() ;
				updateNet() ;
			}
			
			move.display();
			
			if(bScramble == false)
			{
				System.out.println("Press enter when you would like to continue: ");
				szKeyboard.nextLine() ;

			} else
			{
				try
				{
					Thread.sleep(1500);
				}catch(Exception e)
				{
					System.out.println("Error: " + e);
				}
			}
		}
		
		if(szNetOfCube[3][3].equals("B"))
		{
			move.moveYellowFace("U", 1);
			updateNet() ;
			System.out.println("U1");
		} else if(szNetOfCube[3][3].equals("G"))
		{
			move.moveYellowFace("U'", 1);
			updateNet() ;
			System.out.println("U'1");
		} else if(szNetOfCube[3][3].equals("R"))
		{
			//DO NOTHING
		} else if(szNetOfCube[3][3].equals("O"))
		{
			move.moveYellowFace("U", 2);
			updateNet() ;
			System.out.println("U2");
		}
		
		move.display();
		updateNet() ;
		
		if(bScramble == false)
		{
			System.out.println("Press enter when you would like to continue: ");
			szKeyboard.nextLine() ;

		} else
		{
			try
			{
				Thread.sleep(1500);
			}catch(Exception e)
			{
				System.out.println("Error: " + e);
			}
		}
	
	}
	
	public void fixYellowEdges(boolean bScramble)
	{
		int iState = 0;
		
		System.out.println("========= FIX YELLOW EDGES =========");
		
		//this is the final step and it involves switching around the last edge pieces.
		//this step involves four observed states.
		
		//State One: There is one solved side and three unsolved
		//State Two: To get a solved cube, the correct edges are one 180-degree swap from one another.
		//State Three: To get a solved cube, the correct edges are at diagonal to one another.
		//State Four: It is already solved!
		
		//The most complicated ones will be one and two as we will have to differentiate between clockwise
		//and anti clockwise efficiency.  Let's first look for a solved side.  As we know the corners
		//are already correct, we only need to isolate the edge pieces
		
		//first ascertain if it is solved or not
		if(checkIfSolved() == false)
		{
			//then it must be one of the first three states.
			//the subroutine checkAltEdgeState() will return four numbers, 1 - 4, each corresponding to the 
			//desired state.
			iState = checkAltEdgeState() ;
			
			while(iState != 4)
			{
				if(iState == 1)
				{
					move.diagonalAlg() ;
					updateNet() ;
				} else if(iState == 2)
				{
					move.oppositeAlg() ;
					updateNet() ;
				} else if(iState == 3)
				{
					//This one will require the most work to solve
					//If it is in this state then only ONE singular set of final three coloured pieces will be solved.
					//The other three will have one bit (in the middle) that is incorrect.  To solve this we do an
					//algorithm called a U-Perm but first we must get it onto the orange face.
					if(szNetOfCube[3][3].equals(szNetOfCube[3][4]) && szNetOfCube[3][4].equals(szNetOfCube[3][5]))
					{
						//the solved set is on the red face.
						move.moveYellowFace("U", 2);
						System.out.println("U2");
						
						move.UPerm() ;
						updateNet() ;
						
					} else if(szNetOfCube[6][0].equals(szNetOfCube[7][0]) && szNetOfCube[7][0].equals(szNetOfCube[8][0]))
					{
						move.moveYellowFace("U", 1);
						System.out.println("U1");
						
						move.UPerm() ;
						updateNet() ;

					} else if(szNetOfCube[6][8].equals(szNetOfCube[7][8]) && szNetOfCube[7][8].equals(szNetOfCube[8][8]))
					{
						move.moveYellowFace("U'", 1);
						System.out.println("U'1");
						
						move.UPerm() ;
						updateNet() ;

					} else if(szNetOfCube[11][3].equals(szNetOfCube[11][4]) && szNetOfCube[11][4].equals(szNetOfCube[11][5]))
					{
						move.UPerm() ;
						updateNet() ;
					}
				}
				
				move.display();
				
				if(bScramble == false)
				{
					System.out.println("Press enter when you would like to continue: ");
					szKeyboard.nextLine() ;

				} else
				{
					try
					{
						Thread.sleep(1500);
					}catch(Exception e)
					{
						System.out.println("Error: " + e);
					}
				}
				
				iState = checkAltEdgeState() ;
				
			}
		}
		
		
		
		System.out.println("Your cube is solved");
		move.display();
		
	}

	//setters

	//getters
	//=======================================Variable Creators====================================================
	public int breakdownCoord(String szCoord , int a)
	{
		int Return = 0;

		if(a == 0)
		{
			if(String.valueOf(szCoord.charAt(1)).equals(" "))
			{
				Return = Integer.parseInt(String.valueOf(szCoord.charAt(0))) ;

			} else if(String.valueOf(szCoord.charAt(2)).equals(" "))
			{
				Return = Integer.parseInt(String.valueOf(szCoord.charAt(0)) + "" + String.valueOf(szCoord.charAt(1)))  ;
			}
		}


		if(a == 1)
		{
			if(String.valueOf(szCoord.charAt(5)).equals(" "))
			{
				Return =  Integer.parseInt(String.valueOf(szCoord.charAt(4)))  ;

			} else if(String.valueOf(szCoord.charAt(6)).equals(" "))
			{
				Return = Integer.parseInt(String.valueOf(szCoord.charAt(4)) + "" + String.valueOf(szCoord.charAt(5)))  ;
			}
		}

		return Return ;
	}

	public void getCoords(int i2) 
	{
		CurrentX = iWhiteEdgeFoundX[i2];
		CurrentY = iWhiteEdgeFoundY[i2];
	}

	//utilities
	//=======================================Logic=================================================================
	public boolean checkRed()
	{
		for(int i = 0 ; i < 4 ; i++)
		{

			if(szColourFound[i].equals("R"))
			{
				b = true ;
			}

		}

		return true ;
	}

	public boolean checkBlue() 
	{

		for(int i = 0 ; i < 4 ; i++)
		{
			if(szColourFound[i].equals("B"))
			{
				b = true ;
			}
		}


		return true ;
	}

	public boolean checkGreen()
	{

		for(int i = 0 ; i < 4 ; i++)
		{
			if(szColourFound[i].equals("G"))
			{
				b = true ;
			}
		}


		return true ;
	}

	public boolean checkOrange()
	{

		for(int i = 0 ; i < 4 ; i++)
		{
			if(szColourFound[i].equals("O"))
			{
				b = true ;
			}
		}


		return true ;
	}

	public void findColours() 
	{
		//This for loop will cycle through each of the four coordinates found at the white edge piece locations.
		for(int i = 0 ; i < 4 ; i++)
		{
			getCoords(i) ;

			for(int a = 0 ; a < 24 ; a++)
			{
				//this will cycle through all the placements.
				if(CurrentX == iWhiteEdgePiecesX[a] && CurrentY == iWhiteEdgePiecesY[a])
				{
					//then the values for the corresponding colour has been hard-coded into another array
					iWhiteColourFoundX[i] = iWhiteEdgeColourX[a];
					iWhiteColourFoundY[i] = iWhiteEdgeColourY[a];

					//Grab the colour
					szColourFound[i] = szNetOfCube[iWhiteColourFoundY[i]][iWhiteColourFoundX[i]];

				}
			}
		}
	}

	public void moveMiddle(String string, int x , int y)
	{

		System.out.println("The algorithm is: ");

		//there are eight possible positions, sixteen if you include orientation (which I will because I'm working with a 2d array)
		//I am still going to adopt the generic position template used in the last step of solving the cube, because it does maximise
		//efficiency even if it's only marginal

		//So, if the x and y co ords are (3,10) , (5,10) , (7,8) , (7,6), (5,4) , (3,4) , (1,6) , (1,8)
		//then they have to be moved to a generic position on the yellow face.  This generic position will be (4,2)

		if(x == 3 && y == 10)
		{
			//orange face
			move.moveBlueFace("L", 1);
			move.moveYellowFace("U'", 1);
			move.moveBlueFace("L'", 1);
			move.moveOrangeFace("B", 1);
			move.moveBlueFace("L'", 1);
			move.moveOrangeFace("B'", 1);
			move.moveBlueFace("L", 1);

			//It is now in position 4,2
			System.out.println("L1 , U'1 , L'1 , B1 , L'1 , B'1 , L1");


		} else if(x == 5 && y == 10)
		{
			//orange face
			move.moveGreenFace("R'", 1);
			move.moveYellowFace("U", 1);
			move.moveGreenFace("R", 1);
			move.moveOrangeFace("B'", 1);
			move.moveGreenFace("R", 1);
			move.moveOrangeFace("B", 1);
			move.moveGreenFace("R'", 1);

			System.out.println("R'1 , U1 , R1 , B'1 , R1 , B1 , R'1");

		} else if(x == 7 && y == 8)
		{
			//green face
			move.moveOrangeFace("B", 1);
			move.moveYellowFace("U'", 1);
			move.moveOrangeFace("B'", 1);
			move.moveGreenFace("R", 1);
			move.moveOrangeFace("B'", 1);
			move.moveGreenFace("R'", 1);
			move.moveOrangeFace("B", 1);
			move.moveYellowFace("U'", 1);

			System.out.println("B1 , U'1 , B'1 , R1 , B'1 , R'1 , B1 , U'1");

		} else if(x == 7 && y == 6)
		{
			//green face
			move.moveRedFace("F'", 1);
			move.moveYellowFace("U", 1);
			move.moveRedFace("F", 1);
			move.moveYellowFace("U", 1);
			move.moveGreenFace("R", 1);
			move.moveYellowFace("U'", 1);
			move.moveGreenFace("R'", 1);
			move.moveYellowFace("U'", 1);

			System.out.println("F'1 , U1 , F1 , U1 , R1 , U'1 , R'1 , U'1");
		} else if(x == 5 && y == 4)
		{
			move.moveGreenFace("R", 1);
			move.moveYellowFace("U'", 1);
			move.moveGreenFace("R'", 1);
			move.moveYellowFace("U'", 1);
			move.moveRedFace("F'", 1);
			move.moveYellowFace("U", 1);
			move.moveRedFace("F", 1);
			move.moveYellowFace("U", 2);

			System.out.println("R1 , U'1 , R'1 , U'1 , F'1 , U1 , F1 , U2");
		} else if(x == 3 && y == 4)
		{
			move.moveBlueFace("L'", 1);
			move.moveYellowFace("U", 1);
			move.moveBlueFace("L", 1);
			move.moveYellowFace("U", 1);
			move.moveRedFace("F", 1);
			move.moveYellowFace("U'", 1);
			move.moveRedFace("F'", 1);
			move.moveYellowFace("U", 2);

			System.out.println("L'1 , U1 , L1 , U1 , F1 , U'1 , F'1 , U2");

		} else if(x == 1 && y == 6)
		{
			move.moveRedFace("F", 1);
			move.moveYellowFace("U'", 1);
			move.moveRedFace("F'", 1);
			move.moveYellowFace("U'", 1);
			move.moveBlueFace("L'", 1);
			move.moveYellowFace("U", 1);
			move.moveBlueFace("L", 1);
			move.moveYellowFace("U", 1);

			System.out.println("F1 , U'1 , F'1 , U'1 , L'1 , U1 , L1 , U1");

		} else if(x == 1 && y == 8)
		{
			move.moveOrangeFace("B'", 1);
			move.moveYellowFace("U", 1);
			move.moveOrangeFace("B", 1);
			move.moveYellowFace("U", 1);
			move.moveBlueFace("L", 1);
			move.moveYellowFace("U'", 1);
			move.moveBlueFace("L'", 1);
			move.moveYellowFace("U", 1);

			System.out.println("B'1 , U1 , B1 , U1 , L1 , U'1 , L'1 , U1");
		}//if it is on the yellow face it is much simpler 
		else if(x == 4 && y == 2)
		{
			//do nothing
		} else if(x == 3 && y == 1)
		{
			move.moveYellowFace("U'", 1);
			System.out.println("U'");
		} else if(x == 5 && y == 1)
		{

			move.moveYellowFace("U", 1);
			System.out.println("U");
		} else if(x == 4 && y == 0)
		{
			move.moveYellowFace("U", 2);
			System.out.println("U2");
		} else if(x == 0 && y == 7)
		{
			move.moveYellowFace("U'", 1);
			System.out.println("U'1");

			move.flippingAlg();

			move.moveYellowFace("U", 2);
			System.out.println("U2");

		} else if(x == 8 && y == 7)
		{
			move.moveYellowFace("U", 1);
			System.out.println("U1");
			move.flippingAlg();
			move.moveYellowFace("U", 2);
			System.out.println("U2");
		} else if(x == 4 && y == 3)
		{
			move.flippingAlg();
			move.moveYellowFace("U", 2);
			System.out.println("U2");
		} else if(x == 4 && y == 11)
		{
			move.moveYellowFace("U", 2);
			System.out.println("U2");
			move.flippingAlg();
			move.moveYellowFace("U", 2);
			System.out.println("U2");
		}
	}

	public void yellow(int x , int y)
	{
		//So, on each face, I will move the piece into a generic location.  In the switch case we are isolated to the yellow
		//face currently.  If there is a white corner on the yellow face, I want it to be moved to the position x = 3, y = 0.
		//We also need to find out which colour corresponds with the piece.  So, we search for said piece, find the colour and then
		//move it into a generic position.

		if(x == 3 && y == 0)
		{
			string = szNetOfCube[8][0] ;
			string2 = szNetOfCube[11][3] ;
		} else if(x == 5 && y == 0)
		{
			string = szNetOfCube[8][8] ;
			string2 = szNetOfCube[11][5] ;

			//move pieces into generic location
			move.moveYellowFace("U'", 1);
			System.out.println("U'");

		} else if(x == 3 && y == 2)
		{
			string = szNetOfCube[3][3] ;
			string2 = szNetOfCube[6][0] ;

			//move pieces into generic location
			move.moveYellowFace("U", 1);
			System.out.println("U");

		} else if(x == 5 && y == 2 )
		{
			string = szNetOfCube[3][5] ;
			string2 = szNetOfCube[6][8] ;

			//move pieces into generic location
			move.moveYellowFace("U", 2);
			System.out.println("U2");
		}

		//From there, there will be a selection statement of four possible outcomes depending on the colour, and the subsequent
		//algorithm will be displayed.  This way, there are only four algorithms per face, which means only 24 combinations and not
		//96.
		if(string.equalsIgnoreCase("G") && string2.equalsIgnoreCase("R") 
				|| string.equalsIgnoreCase("R") && string2.equalsIgnoreCase("G"))
		{
			move.moveYellowFace("U", 2);
			move.moveGreenFace("R", 1);
			move.moveYellowFace("U'", 2);
			move.moveGreenFace("R'", 1);
			move.moveYellowFace("U'", 1);
			move.moveRedFace("F", 1);
			move.moveGreenFace("R'" , 1);
			move.moveRedFace("F'", 1);
			move.moveGreenFace("R", 1);

			System.out.println("U2 , R1 , U'2 , R'1 , U'1 , F1 , R'1 , F'1 , R1");

		} else if(string.equalsIgnoreCase("G") && string2.equalsIgnoreCase("O") 
				|| string.equalsIgnoreCase("O") && string2.equalsIgnoreCase("G"))
		{
			
			move.moveYellowFace("U", 1);
			move.moveOrangeFace("B", 1);
			move.moveYellowFace("U'", 1);
			move.moveOrangeFace("B'", 1);
			move.moveYellowFace("U'", 2);
			move.moveGreenFace("R", 1);
			move.moveOrangeFace("B'", 1);
			move.moveGreenFace("R'", 1);
			move.moveOrangeFace("B", 1);

			System.out.println("U1 , B1 , U'1 , B'1 , U'2 , R1 , B'1 , R' , B");

		} else if(string.equalsIgnoreCase("O") && string2.equalsIgnoreCase("B") 
				|| string.equalsIgnoreCase("B") && string2.equalsIgnoreCase("O"))
		{
			
			move.moveBlueFace("L", 1);
			move.moveYellowFace("U'", 1);
			move.moveBlueFace("L'", 1);
			move.moveYellowFace("U", 2);
			move.moveOrangeFace("B", 1);
			move.moveBlueFace("L'", 1);
			move.moveOrangeFace("B'", 1);
			move.moveBlueFace("L", 1);

			System.out.println("L1 , U'1 , L'1 , U2 , B1 , L'1 , B'1 , L1");

		} else if(string.equalsIgnoreCase("R") && string2.equalsIgnoreCase("B") 
				|| string.equalsIgnoreCase("B") && string2.equalsIgnoreCase("R"))
		{
			
			move.moveYellowFace("U'", 1);
			move.moveRedFace("F", 1);
			move.moveYellowFace("U'", 1);
			move.moveRedFace("F'", 1);
			move.moveYellowFace("U", 2);
			move.moveBlueFace("L", 1);
			move.moveRedFace("F'", 1);
			move.moveBlueFace("L'", 1);
			move.moveRedFace("F", 1);


			System.out.println("U'1 , F1 , U'1 , F'1 , U2 , L1 , F'1 , L'1 , F1");
		}

	}

	public void red(int x , int y , String string , String string2 , boolean b)
	{
		boolean bGoBlue = false;

		//Generic position will be x = 3 , y = 3
		if(b == false)
		{
			if(x == 3 && y == 3)
			{
				
				string = szNetOfCube[2][3] ;
				string2 = szNetOfCube[6][0] ;
			} else if(x == 5 && y == 3)
			{	
				string = szNetOfCube[2][5] ;
				string2 = szNetOfCube[6][8] ;

				//If the piece is in this position then it is simply impossible to move it into the generic position on
				//the red face, so we can only move it to another generic position on a different face

				move.moveYellowFace("U", 1);

				System.out.println("U1");

				//It is now moved to the generic position on the blue face, x = 0 y = 6

				//It is now moved to the generic position on the blue face
				bGoBlue = true ;

			} else if(x == 3 && y == 5)
			{
				string = szNetOfCube[6][3] ;
				string2 = szNetOfCube[6][2] ;

				move.moveRedFace("F", 1);
				move.moveYellowFace("U" , 1);
				move.moveRedFace("F'", 1);
				move.moveYellowFace("U'", 1);

				System.out.println("F1 , U1 , F'1 , U'1");

			} else if(x == 5 && y == 5 )
			{
				string = szNetOfCube[6][5] ;
				string2 = szNetOfCube[6][6] ;

				move.moveRedFace("F'", 1);
				move.moveYellowFace("U'", 1);
				move.moveRedFace("F", 1);
				move.moveYellowFace("U", 1);
				move.moveRedFace("F'", 1);
				move.moveYellowFace("U", 1);
				move.moveRedFace("F", 1);
				move.moveYellowFace("U'", 1);

				System.out.println("F'1 , U'1 , F1 , U1 , F'1 , U1 , F1, U'1");
			}
		}

		if(bGoBlue == false)
		{
			if(string.equalsIgnoreCase("G") && string2.equalsIgnoreCase("R") 
					|| string.equalsIgnoreCase("R") && string2.equalsIgnoreCase("G"))
			{
				
				move.moveYellowFace("U'", 1);
				move.moveRedFace("F", 1);
				move.moveGreenFace("R'", 1);
				move.moveRedFace("F'", 1);
				move.moveGreenFace("R", 1);

				System.out.println("U'1 , F1 , R'1 , F'1 , R1");

			} else if(string.equalsIgnoreCase("G") && string2.equalsIgnoreCase("O") 
					|| string.equalsIgnoreCase("O") && string2.equalsIgnoreCase("G"))
			{
				
				move.moveGreenFace("R'", 1);		
				move.moveYellowFace("U'", 2);
				move.moveGreenFace("R", 1);

				System.out.println("R'1 , U'2 , R1");

			} else if(string.equalsIgnoreCase("O") && string2.equalsIgnoreCase("B") 
					|| string.equalsIgnoreCase("B") && string2.equalsIgnoreCase("O"))
			{
				
				move.moveOrangeFace("B'", 1);
				move.moveYellowFace("U", 1);
				move.moveOrangeFace("B", 1);

				System.out.println("B'1 , U1 , B1");

			} else if(string.equalsIgnoreCase("R") && string2.equalsIgnoreCase("B") 
					|| string.equalsIgnoreCase("B") && string2.equalsIgnoreCase("R"))
			{

				move.moveYellowFace("U'", 1);
				move.moveBlueFace("L'", 1);
				move.moveYellowFace("U", 1);
				move.moveBlueFace("L", 1);

				System.out.println("U'1 , L'1 , U1 , L1");
			}
		} else if(bGoBlue == true)
		{
			blue(x , y , string , string2 , true);
		}




	}

	public void blue(int x , int y , String string , String string2 , boolean b	)
	{
		boolean bGoOrange = false ;

		//generic position is x = 0 , y = 6
		if(b == false)
		{
			if(x == 0 && y == 6)
			{	
				string = szNetOfCube[2][3] ;
				string2 = szNetOfCube[3][3] ;
			} else if(x == 2 && y == 6)
			{			
				string = szNetOfCube[5][3] ;
				string2 = szNetOfCube[6][3] ;

				move.moveBlueFace("L'", 1);
				move.moveYellowFace("U'", 1);
				move.moveBlueFace("L", 1);
				move.moveYellowFace("U", 1);

				System.out.println("L'1 , U'1 , L1 , U1");

			} else if(x == 0 && y == 8)
			{
				
				string = szNetOfCube[0][3] ;
				string2 = szNetOfCube[11][3] ;

				//it is impossible to move this one into the generic position so we have to move it 
				//to the generic position on a different face

				move.moveYellowFace("U", 1);

				System.out.println("U1");

				bGoOrange = true ;



			} else if(x == 2 && y == 8 )
			{
				
				string = szNetOfCube[8][3] ;
				string2 = szNetOfCube[9][3] ;

				move.moveBlueFace("L", 1);
				move.moveYellowFace("U", 1);
				move.moveBlueFace("L'", 1);
				move.moveYellowFace("U'", 1);
				move.moveBlueFace("L", 1);
				move.moveYellowFace("U'", 1);
				move.moveBlueFace("L'", 1);
				move.moveYellowFace("U", 1);

				System.out.println("L1 , U1 , L'1 , U'1 , L1 , U'1 , L'1 , U1");

			}
		}

		if(bGoOrange == false)
		{
			if(string.equalsIgnoreCase("G") && string2.equalsIgnoreCase("R") 
					|| string.equalsIgnoreCase("R") && string2.equalsIgnoreCase("G"))
			{
				
				move.moveGreenFace("R",1);
				move.moveYellowFace("U'", 1);
				move.moveGreenFace("R'", 1);

				System.out.println("R1 , U'1 , R'1");

			} else if(string.equalsIgnoreCase("G") && string2.equalsIgnoreCase("O") 
					|| string.equalsIgnoreCase("O") && string2.equalsIgnoreCase("G"))
			{
				
				move.moveYellowFace("U'", 1);
				move.moveOrangeFace("B", 1);
				move.moveYellowFace("U'", 1);
				move.moveOrangeFace("B'", 1);

				System.out.println("U'1 , B1 , U'1 , B'1");

			} else if(string.equalsIgnoreCase("O") && string2.equalsIgnoreCase("B") 
					|| string.equalsIgnoreCase("B") && string2.equalsIgnoreCase("O"))
			{
				
				move.moveYellowFace("U'", 1);
				move.moveBlueFace("L", 1);
				move.moveYellowFace("U", 2);
				move.moveBlueFace("L'", 1);

				System.out.println("U'1 , L1 , U2 , L'1");

			} else if(string.equalsIgnoreCase("R") && string2.equalsIgnoreCase("B") 
					|| string.equalsIgnoreCase("B") && string2.equalsIgnoreCase("R"))
			{
				
				move.moveYellowFace("U", 1);
				move.moveRedFace("F", 1);
				move.moveYellowFace("U'", 1);
				move.moveRedFace("F'", 1);

				System.out.println("U1 , F1 , U'1 , F'1");

			}
		} else if(bGoOrange == true)
		{
			orange(5 , 11 , string , string2 , true);
		}

	}

	public void orange(int x , int y , String string, String string2 , boolean b)
	{
		boolean bGoGreen = false ;
		//generic position is x = 5 y = 11
		if(b == false)
		{
			if(x == 3 && y == 9)
			{
				
				string = szNetOfCube[8][2] ;
				string2 = szNetOfCube[8][3] ;

				move.moveOrangeFace("B'", 1);
				move.moveYellowFace("U'", 1);
				move.moveOrangeFace("B", 1);
				move.moveYellowFace("U", 1);
				move.moveOrangeFace("B'", 1);
				move.moveYellowFace("U'", 2);
				move.moveOrangeFace("B", 1);
				move.moveYellowFace("U", 2);

				System.out.println("B'1 , U'1 , B1 , U1 , B'1 , U'2 , B1 , U2");

			} else if(x == 5 && y == 9)
			{

				string = szNetOfCube[8][6] ;
				string2 = szNetOfCube[8][5] ;

				move.moveOrangeFace("B", 1);
				move.moveYellowFace("U", 1);
				move.moveOrangeFace("B'", 1);
				move.moveYellowFace("U'", 1);

				System.out.println("B1 , U1 , B'1 , U'1");

			} else if(x == 3 && y == 11)
			{
				
				string = szNetOfCube[8][0] ;
				string2 = szNetOfCube[0][3] ;

				//move to the generic position on the green face

				move.moveYellowFace("U", 1);

				System.out.println("U1");

				bGoGreen = true ;

			} else if(x == 5 && y == 11 )
			{
				
				string = szNetOfCube[8][8] ;
				string2 = szNetOfCube[0][5] ;
			}
		}

		if(bGoGreen == false)
		{
			if(string.equalsIgnoreCase("G") && string2.equalsIgnoreCase("R") 
					|| string.equalsIgnoreCase("R") && string2.equalsIgnoreCase("G"))
			{
				
				move.moveRedFace("F'", 1);
				move.moveYellowFace("U", 1);
				move.moveRedFace("F", 1);

				System.out.println("F'1 , U1 , F1");

			} else if(string.equalsIgnoreCase("G") && string2.equalsIgnoreCase("O") 
					|| string.equalsIgnoreCase("O") && string2.equalsIgnoreCase("G"))
			{
				
				move.moveYellowFace("U'", 1);
				move.moveGreenFace("R'", 1);
				move.moveYellowFace("U", 1);
				move.moveGreenFace("R", 1);

				System.out.println("U'1 , R'1 , U1 , R1");

			} else if(string.equalsIgnoreCase("O") && string2.equalsIgnoreCase("B") 
					|| string.equalsIgnoreCase("B") && string2.equalsIgnoreCase("O"))
			{
				
				move.moveYellowFace("U", 1);
				move.moveOrangeFace("B'", 1);
				move.moveYellowFace("U", 2);
				move.moveOrangeFace("B", 1);

				System.out.println("U1 , B'1 , U2 , B1");

			} else if(string.equalsIgnoreCase("R") && string2.equalsIgnoreCase("B") 
					|| string.equalsIgnoreCase("B") && string2.equalsIgnoreCase("R"))
			{
				
				move.moveBlueFace("L'", 1);
				move.moveYellowFace("U", 2);
				move.moveBlueFace("L", 1);

				System.out.println("L'1 , U2 , L1");

			}
		} else if(bGoGreen == true)
		{
			green(8 , 8 , string , string2 , true) ;
		}



	}

	public void green(int x , int y , String string , String string2 , boolean b)
	{
		boolean bGoRed = false ;

		//generic position x = 8 y = 8
		if(b == false)
		{
			if(x == 6 && y == 6)
			{
				string = szNetOfCube[6][5] ;
				string2 = szNetOfCube[5][5] ;

				move.moveGreenFace("R", 1);
				move.moveYellowFace("U", 1);
				move.moveGreenFace("R'", 1);
				move.moveYellowFace("U'", 1);
				move.moveGreenFace("R", 1);
				move.moveYellowFace("U'", 1);
				move.moveGreenFace("R'", 1);
				move.moveYellowFace("U", 1);

				System.out.println("R1 , U1 , R'1 , U'1 , R1 , U'1 , R'1 , U1");

			} else if(x == 8 && y == 6)
			{
				string = szNetOfCube[3][5] ;
				string2 = szNetOfCube[2][5] ;

				//have to move to generic position on red
				move.moveYellowFace("U", 1);

				bGoRed = true ;


			} else if(x == 6 && y == 8)
			{
				string = szNetOfCube[8][5] ;
				string2 = szNetOfCube[9][5] ;

				move.moveGreenFace("R'", 1);
				move.moveYellowFace("U'", 1);
				move.moveGreenFace("R", 1);
				move.moveYellowFace("U", 1);


			} else if(x == 8 && y == 8 )
			{
				string = szNetOfCube[0][5] ;
				string2 = szNetOfCube[11][5] ;
			}
		}

		if(bGoRed == false)
		{
			if(string.equalsIgnoreCase("G") && string2.equalsIgnoreCase("R") 
					|| string.equalsIgnoreCase("R") && string2.equalsIgnoreCase("G"))
			{

				move.moveYellowFace("U'", 1);
				move.moveGreenFace("R", 1);
				move.moveYellowFace("U", 2);
				move.moveGreenFace("R'", 1);

				System.out.println("U'1 , R1 , U2 , R'1");

			} else if(string.equalsIgnoreCase("G") && string2.equalsIgnoreCase("O") 
					|| string.equalsIgnoreCase("O") && string2.equalsIgnoreCase("G"))
			{
				move.moveYellowFace("U", 1);
				move.moveOrangeFace("B", 1);
				move.moveYellowFace("U'", 1);
				move.moveOrangeFace("B'", 1);

				System.out.println("U1 , B1 , U'1 , B'1");

			} else if(string.equalsIgnoreCase("O") && string2.equalsIgnoreCase("B") 
					|| string.equalsIgnoreCase("B") && string2.equalsIgnoreCase("O"))
			{
				move.moveBlueFace("L", 1);
				move.moveYellowFace("U'", 1);
				move.moveBlueFace("L'", 1);

				System.out.println("L1 , U'1 , L'1");

			} else if(string.equalsIgnoreCase("R") && string2.equalsIgnoreCase("B") 
					|| string.equalsIgnoreCase("B") && string2.equalsIgnoreCase("R"))
			{
				move.moveRedFace("F", 1);
				move.moveYellowFace("U", 2);
				move.moveRedFace("F'", 1);

				System.out.println("F1 , U2 , F'1");

			}
		} else if(bGoRed == true)
		{
			red(3 , 3 , string , string2 , true);
		}



	}

	public void white(int x , int y)
	{
		//generic position is a little harder on this one.  Moving the white side is tricky 
		//for this stage as if there are other white corners in place we run the risk of moving them.
		//So for the white edges, each individual piece must be sent to another face's subroutine
		//UNLESS it is in the correct place

		if(x == 3 && y == 6)
		{
			string = szNetOfCube[5][3] ;
			string2 = szNetOfCube[6][2] ;

			if(! (string.equals("R") && string2.equals("B") ) )
			{
				//the piece is not in the correct place so we have to move it.
				//move this piece to the generic position of the red face
				move.moveBlueFace("L'", 1);
				move.moveYellowFace("U'", 1);
				move.moveBlueFace("L", 1);
				move.moveYellowFace("U", 1);

				System.out.println("L'1 , U'1 , L1 , U1");

				red(3 , 3 , string , string2 , true);
			} 



		} else if(x == 5 && y == 6)
		{
			string = szNetOfCube[6][6] ;
			string2 = szNetOfCube[5][5] ;

			if(! (string.equals("G") && string2.equals("R") ) )
			{
				//the piece is not in the correct place so we have to move it.
				//move this piece to the generic position of the green face

				move.moveGreenFace("R", 1);
				move.moveYellowFace("U", 1);
				move.moveGreenFace("R'", 1);
				move.moveYellowFace("U'", 2);


				System.out.println("R1 , U1 , R'1 , U'2");

				green(8 , 8 , string , string2 , true);
			} 


		} else if(x == 3 && y == 8)
		{
			string = szNetOfCube[8][2] ;
			string2 = szNetOfCube[9][3] ;

			//move to generic position on green face
			if(! (string.equals("B") && string2.equals("O") ) )
			{
				//the piece is not in the correct place so we have to move it.
				//move this piece to the generic position of the green face

				move.moveBlueFace("L", 1);
				move.moveYellowFace("U", 1);
				move.moveBlueFace("L'", 1);


				System.out.println("L1 , U1 , L'1");

				green(8 , 8 , string , string2 , true);
			} 



		} else if(x == 5 && y == 8 )
		{
			string = szNetOfCube[9][5] ;
			string2 = szNetOfCube[8][6] ;

			//move to generic position on orange face
			if(! (string.equals("O") && string2.equals("G") ) )
			{
				//the piece is not in the correct place so we have to move it.
				//move this piece to the generic position of the orange face

				move.moveGreenFace("R'", 1);
				move.moveYellowFace("U'", 1);
				move.moveGreenFace("R", 1);
				move.moveYellowFace("U", 1);


				System.out.println("R'1 , U'1 , R1 , U1");

				orange(5 , 11 , string , string2 , true);
			} 
		}
	}

	public void moveCorners( String szFace , int x , int y)
	{

		//I want to generic - ify this solution as much as possible, because otherwise there will be (6*4*4) combinations to
		//sort out all the corners and that is very inefficient.

		System.out.println("The algorithm is :");

		switch (szFace)
		{

		case "Yellow" :
		{

			yellow(x , y) ;
			break ;
		}

		case "Red":
		{
			red(x , y , "" , "" , false) ;
			break ;
		}

		case "White":
		{
			white(x , y);
			break ;
		}

		case "Orange":
		{
			orange(x , y , "" , "" , false);
			break ;
		}

		case "Blue":
		{
			blue(x , y , "" , "" , false);
			break ;
		}

		case "Green":
		{
			green(x , y , "" , "" , false);
			break ;
		}

		}

		//		System.out.println("The corresponding colours to the first white corner is " + string + " " + string2);
	}

	public void switches(String string , int a , boolean bToggle)
	{

		switch(string)
		{
		case "0" :
		{
			//In case zero, the white piece is stored at 4 across, 0 down.  
			//Firstly we need to find out which face it needs to connect to by getting the
			//corresponding colour
			if(szColourFound[a].equals("R"))
			{
				System.out.println("0.1");

				if(bToggle == false)
				{
					move.moveYellowFace("U", 2);
					move.moveRedFace("F", 2);

					System.out.println("U2 F2");

				} else
				{
					System.out.println("Move the top face two rotations in whichever direction you wish."
							+ "\nNext, move the front face twice around in whichever direction you wish.");
				}
				
				
			} else if(szColourFound[a].equals("G"))
			{

				if(bToggle == false)
				{
					move.moveYellowFace("U", 1);
					move.moveGreenFace("R", 2);

					System.out.println("U R2");

				} else
				{
					System.out.println("Move the top face to the left by one iteration."
							+ "\nThen, move the right of your cube down by two iterations.");
				}
				
				

			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("0.3");

				if(bToggle == false)
				{
					move.moveYellowFace("U'",1) ;
					move.moveBlueFace("L'",2) ;

					System.out.println("U' L'2");

				} else
				{
					System.out.println("Move the top face to the right by one iteration."
							+ "\nMove the left side of your cube down by two iterations.");
				}

			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("0.4");

				if(bToggle == false)
				{
					move.moveOrangeFace("B", 2);
					
					System.out.println("B2");
				} else
				{
					System.out.println("Move the back face to in any direction by two iterations.");
				}
			}


			break ;
		}

		case "1":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("1.1");

				move.moveYellowFace("U'", 1);
				move.moveRedFace("F",2);
				szAlgorithm = "U' F2" ;
			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("1.2");

				move.moveYellowFace("U", 2);
				move.moveGreenFace("R", 2);
				szAlgorithm = "U2 R2" ;
			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("1.3");

				move.moveBlueFace("L", 2);
				szAlgorithm = "L2" ;
			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("1.4");

				move.moveYellowFace("U", 1);
				move.moveOrangeFace("B", 2);
				szAlgorithm = "U B2" ;
			}


			break ;
		}

		case "2":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("2.1");

				move.moveYellowFace("U", 1);
				move.moveRedFace("F", 2);
				szAlgorithm = "U F2" ;
			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("2.2");

				move.moveGreenFace("R", 2);
				szAlgorithm = "R2" ;
			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("2.3");

				move.moveYellowFace("U", 2) ;
				move.moveBlueFace("L", 2);
				szAlgorithm = "U2 L2" ;
			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("2.4");

				move.moveYellowFace("U'", 1);
				move.moveOrangeFace("B", 2);
				szAlgorithm = "U' B2" ;
			}
			break ;
		}

		case "3":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("3.1");

				move.moveRedFace("F",2);
				szAlgorithm = "F2" ;
			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("3.2");

				move.moveYellowFace("U'", 1);
				move.moveGreenFace("R", 2);
				szAlgorithm = "U' R2" ;
			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("3.3");

				move.moveYellowFace("U", 1);
				move.moveBlueFace("L", 2);
				szAlgorithm = "U L2" ;
			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("3.4");

				move.moveYellowFace("U'", 2);
				move.moveOrangeFace("B", 2);
				szAlgorithm = "U'2 B2" ;
			}
			break ;
		}

		case "4":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("4.1");

				move.moveYellowFace("U'", 1);
				move.moveGreenFace("R'", 1);
				move.moveRedFace("F", 1);
				move.moveGreenFace("R", 1);
				szAlgorithm = "U' R' F1 R1" ;
			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("4.2");

				move.moveRedFace("F", 1);
				move.moveGreenFace("R'", 1);
				move.moveRedFace("F'", 1);

				szAlgorithm = "F1, R'1, F'1" ;


			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("4.3");

				move.moveRedFace("F'", 1);
				move.moveBlueFace("L", 1);
				move.moveRedFace("F", 1);
				szAlgorithm = "F'1, L1, F1" ;
			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("4.4");

				move.moveYellowFace("U", 1);
				move.moveBlueFace("L'", 1);
				move.moveOrangeFace("B", 1);
				move.moveBlueFace("L", 1);

				szAlgorithm = "U1 L'1 B1 L1" ;
			}
			break ;
		}

		case "5":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("5.1");
				move.moveBlueFace("L'", 1);
				move.moveYellowFace("U'", 1);
				move.moveBlueFace("L", 1);
				move.moveRedFace("F", 2);

				szAlgorithm = "L'1 , U'1 , L1, F2" ;
			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("5.2");

				move.moveBlueFace("L'", 1);
				move.moveYellowFace("U" , 2);
				move.moveBlueFace("L", 1);
				move.moveGreenFace("R", 2);

				szAlgorithm = "L'1 , U2 , L1 , R2" ;


			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("5.3");

				move.moveBlueFace("L", 1);

				szAlgorithm = "L1" ;
			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("5.4");

				move.moveBlueFace("L'", 1);
				move.moveYellowFace("U",1);
				move.moveBlueFace("L",1);
				move.moveOrangeFace("B", 2);

				szAlgorithm = "L'1 , U1 , L1 , B2" ;
			}
			break ;
		}

		case "6":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("6.1");

				move.moveGreenFace("R", 1);
				move.moveYellowFace("U", 1);
				move.moveGreenFace("R'",1);
				move.moveRedFace("F", 2);

				szAlgorithm = "R1 , U1 , R'1 , F2" ;

			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("6.2");

				move.moveGreenFace("R'", 1);

				szAlgorithm = "R'" ;


			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("6.3");

				move.moveGreenFace("R", 1);
				move.moveYellowFace("U",2);
				move.moveGreenFace("R'", 1);
				move.moveBlueFace("L", 2);

				szAlgorithm = "R1, U2, R'1, L2" ;
			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("6.4");

				move.moveGreenFace("R", 1);
				move.moveYellowFace("U'",1);
				move.moveGreenFace("R'", 1);
				move.moveOrangeFace("B", 2);

				szAlgorithm = "R1 , U'1 , R'1 , B2" ;
			}
			break ;
		}

		case "7":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("7.1");

				move.moveRedFace("F'", 1);
				move.moveGreenFace("R", 1);
				move.moveYellowFace("U", 1);
				move.moveGreenFace("R'", 1);
				move.moveRedFace("F", 2);

				szAlgorithm = "F'1 , R1 , U1, R1, F2" ;

			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("7.2");

				move.moveRedFace("F'",1);
				move.moveGreenFace("R'", 1);
				move.moveRedFace("F", 1);

				szAlgorithm = "F'1 , R'1 , F1" ;


			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("7.3");

				move.moveRedFace("F", 1);
				move.moveBlueFace("L", 1);
				move.moveRedFace("F'", 1);

				szAlgorithm = "F1 L1 F'1" ;
			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("7.4");

				move.moveRedFace("F'", 1);
				move.moveGreenFace("R", 1);
				move.moveYellowFace("U'", 1);
				move.moveGreenFace("R'", 1);
				move.moveOrangeFace("B", 2);

				szAlgorithm = "F'1 , R1 , U'1 , R'1 , B" ;
			}
			break ;
		}

		case "8":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("8.1");

				//DO NOTHING THE PIECE IS ALREADY CORRECT
				szAlgorithm = "Stay the same" ;


			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("8.2");

				move.moveWhiteFace("D'", 1);
				move.moveGreenFace("R", 1);
				move.moveWhiteFace("D", 1);
				move.moveGreenFace("R'", 1);

				szAlgorithm = "D'1, R1, D1, R'1" ;


			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("8.3");

				move.moveWhiteFace("D", 1);
				move.moveBlueFace("L'", 1);
				move.moveWhiteFace("D'", 1);
				move.moveBlueFace("L", 1);

				szAlgorithm = "D1 , L'1 , D'1, L1" ;

			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("8.4");

				move.moveRedFace("F", 2);
				move.moveYellowFace("U", 2);
				move.moveOrangeFace("B", 2);

				szAlgorithm = "F2 , U2 , B2" ;
			}
			break ;
		}

		case "9":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("9.1");

				move.moveOrangeFace("B", 2);
				move.moveYellowFace("U", 2);
				move.moveRedFace("F", 2);

				szAlgorithm = "B2 , U2 , F2" ;

			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("9.2");

				move.moveWhiteFace("D", 1);
				move.moveGreenFace("R", 1);
				move.moveWhiteFace("D'", 1);
				move.moveGreenFace("R'", 1);

				szAlgorithm = "D1, R1, D'1, R'1" ;


			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("9.3");

				move.moveWhiteFace("D", 1);
				move.moveBlueFace("L'", 1);
				move.moveWhiteFace("D'",1);
				move.moveBlueFace("L'", 1);

				szAlgorithm = "D1 , L'1 , D'1, L1" ;

			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("9.4");

				//DO NOTHING THE PIECE IS ALREADY CORRECT
				szAlgorithm = "Stay the same" ;


			}
			break ;
		}

		case "10":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("10.1");

				move.moveWhiteFace("D'", 1);
				move.moveRedFace("F", 1);
				move.moveWhiteFace("D", 1);
				move.moveRedFace("F'", 1);

				szAlgorithm = "D'1 , F1 . D1 , F'1" ;

			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("10.2");

				move.moveWhiteFace("D'", 2);
				move.moveGreenFace("R", 1);
				move.moveWhiteFace("D", 2);
				move.moveGreenFace("R'", 1);

				szAlgorithm = "D2 , R1 , D2 , R'1" ;


			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("10.3");

				//DO NOTHING THE PIECE IS ALREADY CORRECT
				szAlgorithm = "Stay the same" ;


			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("10.4");

				move.moveWhiteFace("D",1);
				move.moveOrangeFace("B", 1);
				move.moveWhiteFace("D'", 1);
				move.moveOrangeFace("B'", 1);

				szAlgorithm = "D1 , B1 , D'1 , B1";
			}
			break ;
		}

		case "11":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("11.1");

				move.moveWhiteFace("D", 1);
				move.moveRedFace("F", 1);
				move.moveWhiteFace("D'", 1);
				move.moveRedFace("F'", 1);

				szAlgorithm = "D1 , F1 , D'1 , F'1" ;

			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("11.2");

				//DO NOTHING THE PIECE IS ALREADY CORRECT
				szAlgorithm = "Stay the same" ;

			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("11.3");

				move.moveWhiteFace("D", 2);
				move.moveBlueFace("L'", 1);
				move.moveWhiteFace("D'", 2);
				move.moveBlueFace("L", 1);

				szAlgorithm = "D2, L'1 , D'2 , L1" ;


			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("11.4");

				move.moveWhiteFace("D'", 1);
				move.moveOrangeFace("B", 1);
				move.moveWhiteFace("D", 1);
				move.moveOrangeFace("B'", 1);

				szAlgorithm = "D'1 , B1 , D1 , B'1";
			}
			break ;
		}

		case "12":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("12.1");

				move.moveRedFace("F'", 1);

				szAlgorithm = "F'1" ;

			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("12.2");

				move.moveRedFace("F", 1);
				move.moveYellowFace("U'", 1);
				move.moveRedFace("F'", 1);
				move.moveGreenFace("R'", 2);

				szAlgorithm = "F1, U'1, F'1 , R'2" ;

			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("12.3");

				move.moveRedFace("F", 1);
				move.moveYellowFace("U", 1);
				move.moveRedFace("F'", 1);
				move.moveBlueFace("L'", 2);

				szAlgorithm = "F1 , U1 , F'1 , L'2" ;


			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("12.4");

				move.moveRedFace("F", 1);
				move.moveYellowFace("U", 2);
				move.moveRedFace("F'", 1);
				move.moveOrangeFace("B", 2);

				szAlgorithm = "F1 , U2 , F'1 , B2";
			}
			break ;
		}

		case "13":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("13.1");

				move.moveBlueFace("L", 1);
				move.moveRedFace("F'", 1);
				move.moveBlueFace("L'", 1);



				szAlgorithm = "L1 , F1 , L'1" ;

			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("13.2");

				move.moveYellowFace("U'", 1);
				move.moveRedFace("F", 1);
				move.moveGreenFace("R'", 1);
				move.moveRedFace("F'", 1);

				szAlgorithm = "U'1 , F1 , R'1 , F'1" ;

			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("13.3");

				move.moveYellowFace("U'", 1);
				move.moveRedFace("F'", 1);
				move.moveBlueFace("L",1);
				move.moveRedFace("F",1);

				szAlgorithm = "U'1 , F'1 , L1 , F1" ;


			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("13.4");

				move.moveBlueFace("L'", 1);
				move.moveOrangeFace("B", 1);
				move.moveBlueFace("L", 1);

				szAlgorithm = "L'1 , B1 , L1";
			}
			break ;
		}

		case "14":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("14.1");

				move.moveBlueFace("L'", 1);
				move.moveRedFace("F'", 1);
				move.moveBlueFace("L", 1);

				szAlgorithm = "L'1 , F'1 , L1" ;

			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("14.2");

				move.moveWhiteFace("D'", 1);
				move.moveRedFace("F'", 1);
				move.moveWhiteFace("D", 1);
				move.moveGreenFace("R'", 1);

				szAlgorithm = "D'1 , F'1 , D1 , R'1" ;

			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("14.3");

				move.moveWhiteFace("D'", 1);
				move.moveRedFace("F", 1);
				move.moveWhiteFace("D", 1);
				move.moveBlueFace("L", 1);

				szAlgorithm = "D'1 , F1 , D1 , L1" ;


			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("14.4");

				move.moveBlueFace("L", 1);
				move.moveOrangeFace("B", 1);

				szAlgorithm = "L1 , B1";
			}
			break ;
		}

		case "15":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("15.1");

				move.moveBlueFace("L", 2);
				move.moveRedFace("F'", 1);
				move.moveBlueFace("L", 2);

				szAlgorithm = "L2 , F'1 , L2" ;

			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("15.2");

				move.moveBlueFace("L", 1);
				move.moveYellowFace("U'", 1);
				move.moveRedFace("F", 1);
				move.moveGreenFace("R'", 1);
				move.moveRedFace("F'", 1);
				move.moveBlueFace("L'", 1);

				szAlgorithm = "L1 , U'1 , F1 , R'1 , F'1 , L'1";

			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("15.3");

				move.moveOrangeFace("B'", 1);
				move.moveYellowFace("U'", 1);
				move.moveOrangeFace("B", 1);
				move.moveBlueFace("L'", 2);

				szAlgorithm = "B'1 , U'1 , B1 , L'2" ;


			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("15.4");

				move.moveOrangeFace("B", 1);

				szAlgorithm = "B1";
			}
			break ;
		}

		case "16":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("16.1");

				move.moveRedFace("F", 1);

				szAlgorithm = "F1" ;

			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("16.2");

				move.moveRedFace("F'", 1);
				move.moveYellowFace("U'", 1);
				move.moveRedFace("F", 1);
				move.moveGreenFace("R'", 2);

				szAlgorithm = "F'1 , U'1 , F1 , R'2";

			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("16.3");

				move.moveRedFace("F'", 1);
				move.moveYellowFace("U", 1);
				move.moveRedFace("F", 1);
				move.moveBlueFace("L", 2);

				szAlgorithm = "F'1 , U1 , F1 , L2" ;


			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("16.4");

				move.moveRedFace("F'", 1);
				move.moveYellowFace("U", 2);
				move.moveRedFace("F", 1);
				move.moveOrangeFace("B", 2);

				szAlgorithm = "F'1 , U2 , F1 , B2";
			}
			break ;
		}

		case "17":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("17.1");

				move.moveGreenFace("R", 1);
				move.moveRedFace("F", 1);
				move.moveGreenFace("R'", 1);

				szAlgorithm = "R1 , F1 , R'1" ;

			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("17.2");

				move.moveGreenFace("R", 1);
				move.moveRedFace("F'", 1);
				move.moveYellowFace("U'", 1);
				move.moveRedFace("F", 1);
				move.moveGreenFace("R", 2);

				szAlgorithm = "R1 , F'1 , U'1 , F1 , R2";

			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("17.3");

				move.moveWhiteFace("D", 1);
				move.moveRedFace("F", 1);
				move.moveWhiteFace("D'", 1);
				move.moveBlueFace("L", 1);

				szAlgorithm = "D1 , F1 , D'1 , L1" ;


			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("17.4");

				move.moveOrangeFace("B", 1);
				move.moveGreenFace("R'", 1);

				szAlgorithm = "B1 , R'1";
			}
			break ;
		}

		case "18":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("18.1");

				move.moveGreenFace("R'", 1);
				move.moveRedFace("F", 1);
				move.moveGreenFace("R", 1);

				szAlgorithm = "R'1 , F1 , R1" ;

			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("18.2");

				move.moveYellowFace("U'", 1);
				move.moveOrangeFace("B'", 1);
				move.moveGreenFace("R", 1);
				move.moveOrangeFace("B", 1);

				szAlgorithm = "U' , B'1 , R1 , B1";

			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("18.3");

				move.moveYellowFace("U", 1);
				move.moveRedFace("F'", 1);
				move.moveBlueFace("L", 1);
				move.moveRedFace("F", 1);

				szAlgorithm = "U1 , F'1 , L1 , F1" ;


			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("18.4");

				move.moveGreenFace("R", 1);
				move.moveOrangeFace("B'", 1);
				move.moveGreenFace("R'", 1);

				szAlgorithm = "R1 , B'1 , R'1";
			}
			break ;
		}

		case "19":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("19.1");

				move.moveGreenFace("R", 2);
				move.moveRedFace("F", 1);
				move.moveGreenFace("R", 2);

				szAlgorithm = "R2 , F1 , R2" ;

			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("19.2");

				move.moveOrangeFace("B", 1);
				move.moveYellowFace("U", 1);
				move.moveOrangeFace("B'", 1);
				move.moveGreenFace("R", 2);

				szAlgorithm = "B1 , U1 , B'1 , R2" ;

			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("19.3");

				move.moveOrangeFace("B", 1);
				move.moveYellowFace("U'", 1);
				move.moveOrangeFace("B'", 1);
				move.moveBlueFace("L'", 2);

				szAlgorithm = "B1 , U'1 , B'1 , L'2" ;


			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("19.4");

				move.moveOrangeFace("B'", 1);

				szAlgorithm = "B'1";
			}
			break ;
		}

		case "20":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("2O.1");

				move.moveWhiteFace("D'", 1);
				move.moveBlueFace("L'", 1);
				move.moveWhiteFace("D", 1);
				move.moveRedFace("F'", 1);


				szAlgorithm = "D'1 , L'1 , D1 , F'1" ;

			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("20.2");

				move.moveOrangeFace("B", 1);
				move.moveGreenFace("R", 1);

				szAlgorithm = "B1 , R1" ;

			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("20.3");

				move.moveOrangeFace("B'", 1);
				move.moveBlueFace("L'", 1);

				szAlgorithm = "B1 , L'1" ;


			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("2O.4");

				move.moveWhiteFace("D", 1);
				move.moveGreenFace("R'", 1);
				move.moveWhiteFace("D'", 1);
				move.moveOrangeFace("B'", 1);

				szAlgorithm = "D1 , R'1 , D'1 , B'1";
			}
			break ;
		}

		case "21":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("21.1");

				move.moveYellowFace("U'", 1);
				move.moveBlueFace("L", 1);
				move.moveRedFace("F'", 1);
				move.moveBlueFace("L'", 1);

				szAlgorithm = "U'1 , L1 , F'1 , L'1" ;

			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("21.2");

				move.moveOrangeFace("B'", 1);
				move.moveGreenFace("R", 1);
				move.moveOrangeFace("B", 1);

				szAlgorithm = "B'1 , R1 , B1" ;

			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("21.3");

				move.moveOrangeFace("B", 1);
				move.moveBlueFace("L'", 1);
				move.moveOrangeFace("B'", 1);

				szAlgorithm = "B1 , L'1 , B'1" ;


			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("21.4");

				move.moveYellowFace("U'", 1);
				move.moveBlueFace("L'", 1);
				move.moveOrangeFace("B", 1);
				move.moveBlueFace("L" , 1);

				szAlgorithm = "U'1 , L'1 , B1 , L1";
			}
			break ;
		}

		case "22":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("22.1");

				move.moveBlueFace("L'", 1);
				move.moveWhiteFace("D" , 1);
				move.moveBlueFace("L'", 1);
				move.moveWhiteFace("D'", 2);
				move.moveRedFace("F'", 1);
				move.moveWhiteFace("D", 1);
				move.moveBlueFace("L", 2);
				move.moveRedFace("F", 1);

				szAlgorithm = "L'1 , D1 , L'1 , D'2 , F'1 , D1 , L2 , F1" ;

			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("22.2");

				move.moveOrangeFace("B", 2);
				move.moveGreenFace("R", 1);
				move.moveOrangeFace("B", 2);

				szAlgorithm = "B2 , R1 , B2" ;

			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("22.3");

				move.moveBlueFace("L'", 1);

				szAlgorithm = "L'" ;


			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("22.4");

				move.moveBlueFace("L", 1);
				move.moveYellowFace("U", 1);
				move.moveBlueFace("L'", 1);
				move.moveOrangeFace("B", 2);

				szAlgorithm = "L1 , U1 , L'1 , B2";
			}
			break ;
		}

		case "23":
		{
			if(szColourFound[a].equals("R"))
			{
				System.out.println("23.1");

				move.moveGreenFace("R'", 1);
				move.moveYellowFace("U", 1);
				move.moveGreenFace("R", 1);
				move.moveRedFace("F", 2);

				szAlgorithm = "R'1 , U1 , R1 , F2" ;

			} else if(szColourFound[a].equals("G"))
			{
				System.out.println("23.2");

				move.moveGreenFace("R", 1);

				szAlgorithm = "R1" ;

			} else if(szColourFound[a].equals("B"))
			{
				System.out.println("23.3");

				move.moveGreenFace("R'", 1);
				move.moveYellowFace("U", 2);
				move.moveGreenFace("R", 1);
				move.moveBlueFace("L'", 2);

				szAlgorithm = "R'1 , U2 , R1 , L'2" ;


			} else if(szColourFound[a].equals("O"))
			{
				System.out.println("23.4");

				move.moveGreenFace("R'", 1);
				move.moveYellowFace("U'", 1);
				move.moveGreenFace("R", 1);
				move.moveOrangeFace("B", 2);

				szAlgorithm = "R'1 , U'1 , R1 , B2";
			}
			break ;
		}
		//										  0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23		
		//		public int[] iWhiteEdgePiecesX = {4,3,5,4,4,3,5,4,4,4, 3, 5, 1, 0, 2, 1, 7, 6, 8, 7, 4, 4, 3, 5};
		//		public int[] iWhiteEdgePiecesY = {0,1,1,2,3,4,4,5,6,8, 7, 7, 6, 7, 7, 8, 6, 7, 7, 8, 9,11,10,10};

		default :
		{
			szAlgorithm = "Stay the same" ;
		}
		}
		move.sendCurrentNetToCube();
		move.display();
		
	}

	public void yellowcornerRepeatAlg()
	{
		move.moveGreenFace("R", 1);
		move.moveYellowFace("U",1);
		move.moveGreenFace("R'", 1);
		move.moveYellowFace("U", 1);
		move.moveGreenFace("R",1);
		move.moveYellowFace("U", 2);
		move.moveGreenFace("R'", 1);
		
		System.out.println("R1 , U1 , R'1 , U1 , R1 , U2 , R'1");

	
	}
	
	public void findAndAssignWhite()
	{
		boolean bWhiteEdgeRed = false, bWhiteEdgeBlue = false, bWhiteEdgeGreen = false, bWhiteEdgeOrange = false;
		f = 0 ;

		//This is a challenging task, as the system has the cube stored as a 2d array.  I need to make sure the system constantly knows which edges of the cube (the ones that are bordered by
		//values containing "null") connect to the edge of the cubes that have colours inputed.  For example, if you look at the net, the blue centred side has spaces above it that are filled
		//by nothing.  This side connects to the left column of the cube that has a red centre (the top row at least).  The left column will connect to the left column of the Yellow face.  And the bottom
		//row will connect to the left column of the Orange face.

		//To find the white edges, we must first look for white pieces that are found in the middle columns/rows.
		//While none of the edges have been found, we must remain in this loop.
		while(bWhiteEdgeRed == false && bWhiteEdgeBlue == false && bWhiteEdgeGreen == false && bWhiteEdgeOrange == false)
		{
			//We are going to scan the entire array for white pieces that can only be found in specific locations.  Edges (the pieces that will make the white cross)
			//can only be found in the centre of the cube's face pieces IE: column 2 or row 2 of each face.

			//Is it easier to look per face or is it easier to look through the entire 9 by 12 array?  I think it would be easier to scan through the entire 2d array
			for(int i = 0 ; i < 12 ; i++)
			{
				for(int j = 0; j < 9 ; j++)
				{
					if(szNetOfCube[i][j].equals("W"))
					{
						//if we find a W we need to make sure that it is an edge and not a corner.  The difference is is that a corner has two other colours attached to it
						//and an edge has only one
						checkForPieceType(j , i) ;
					}		
				}
			}

			//Now the x and y coordinates (corresponding to the placement in the array), have been stored for each white piece.
			//So, the next step is to find out which colours correspond to which.
			findColours() ;

			//Now make sure all the colours have been found
			bWhiteEdgeRed = checkRed() ;
			bWhiteEdgeBlue = checkBlue() ;
			bWhiteEdgeGreen = checkGreen() ;
			bWhiteEdgeOrange = checkOrange() ;
		}

		//		for(int i = 0 ; i < 4 ; i++)
		//		{
		//			System.out.println("White piece found at x = " + iWhiteEdgeFoundX[i]
		//					+ " and y = " + iWhiteEdgeFoundY[i]);
		//		}
	}

	public void findCorners()
	{


		for(int iPos = 0 ; iPos < 4 ; iPos++)
		{
			for(int iYaxis = 0 ; iYaxis < 12 ; iYaxis++)
			{
				for(int iXaxis = 0 ; iXaxis < 9 ; iXaxis++)
				{
					//If the colour is White and not in the cross positions OR centre 
					//OR a correct position then it is a white cross piece
					if(szNetOfCube[iYaxis][iXaxis].equals("W") && (! ( (iYaxis == 7 && iXaxis == 3) ||
							(iYaxis == 6 && iXaxis == 4) ||
							(iYaxis == 7 && iXaxis == 5) ||
							(iYaxis == 8 && iXaxis == 4) ||
							(iYaxis == 7 && iXaxis == 4  ) ) ) )
					{
						
						iWhiteCornersFoundX[iPos] = iXaxis ;
						iWhiteCornersFoundY[iPos] = iYaxis ;
				
						iPos++ ;
					}	
				}
			}
		}
	}
	
	public String findFace(int y , int x)
	{
		if( (x > 2 && x < 6) && y < 3)
		{
			//then the piece is on the yellow face

			szFace = "Yellow" ;
		} else if( (x > 2 && x < 6) && (y > 2 && y < 6) )
		{
			//then the piece is on the red face

			szFace = "Red" ;
		} else if( ( x < 3 ) && (y > 5 && y < 9))
		{
			//then the piece is on the blue face

			szFace = "Blue" ;
		} else if( ( x > 2 && x < 6 ) && (y > 5 && y < 9))
		{
			//then the piece is on the white face

			szFace = "White" ;
		} else if( ( x > 5) && (y > 5 && y < 9))
		{
			//then the piece is on the green face

			szFace = "Green" ;
		} else if( ( x > 2 && x < 6 ) && (y > 8))
		{
			//then the piece is on the blue face

			szFace = "Orange" ;
		}

		return szFace ;
	}
	
	public void checkForPieceType(int iXAxis, int iYAxis)
	{
		int xCoord = 0, yCoord = 0;
		//i guess the easiest way to check is to have a set of pre entered positions that mean that if there is a piece here then it must be an edge, and vica versa for
		//an edge

		//where the subroutine is called, j is put in as a parameter that means the x axis is that
		xCoord = iXAxis ;

		//this is the same with the y axis
		yCoord = iYAxis ;

		for(int i = 0 ; i < 24 ; i++)
		{
			//Now we run through all of the x axis placements and all of the y axis placements
			if(xCoord == iWhiteEdgePiecesX[i] && yCoord == iWhiteEdgePiecesY[i])
			{
				//Then it is a white face in a slot reserved only for edge pieces (note this is a method that
				//will only work easily on a 3 by 3)


				//this stores the coordinates of the white edge pieces for later use
				iWhiteEdgeFoundX[f] = iXAxis;
				iWhiteEdgeFoundY[f] = iYAxis;
				f++;
			}

		}

	}
	
	public String searchColour(String string, String string2)
	{
		String string3 = "";

		//this is a general subroutine that should search for whatever piece you put into the parameters in the
		//given range of the positions allowed

		//Yellow Face = Position 0 - 3
		//Red Face (Unsolved) = Position 4 - 6
		//Green Face (Unsolved) = Position 16, 18 and 19
		//Orange Face (Unsolved) = Position 21-23
		//Blue Face (Unsolved) = Position 12 , 13 and 15

		for(int i = 0 ; i < 24 ; i++)
		{
			if( i <= 6 || i == 16 || i == 18 || i == 19 || i==21 || i == 22 || i == 23 
					|| i == 12 || i ==13  || i == 15)
			{
				
				if( (szNetOfCube[iWhiteEdgePiecesY[i]][iWhiteEdgePiecesX[i]].equals(string) ) )
				{
	
					if(szNetOfCube[iWhiteEdgeColourY[i]][iWhiteEdgeColourX[i]].equals(string2) )
					{
					
						string3 = String.valueOf(iWhiteEdgePiecesX[i]) + " , " + String.valueOf(iWhiteEdgePiecesY[i]
								+ " - " + string) ;
						

					}

				} else if ( (szNetOfCube[iWhiteEdgePiecesY[i]][iWhiteEdgePiecesX[i]].equals(string2) ) )
				{
				
					if( szNetOfCube[iWhiteEdgeColourY[i]][iWhiteEdgeColourX[i]].equals(string) )
					{
						
						string3 = String.valueOf(iWhiteEdgePiecesX[i]) + " , " + String.valueOf(iWhiteEdgePiecesY[i]
								+ " - " + string2) ;
						
					}
				}
			}
		}

		return string3 ;
	}

	public int realiseyellowState()
	{
		int i = 0;
		
			if( ( szNetOfCube[0][4].equals("Y") && szNetOfCube[2][4].equals("Y") )
					&& !(szNetOfCube[1][3].equals("Y") && szNetOfCube[1][5].equals("Y") ))
			{
				//move into generic position
				move.moveYellowFace("U", 1);
				System.out.println("U1");
				
				i = 0;
			} else if( ( szNetOfCube[1][3].equals("Y") && szNetOfCube[1][5].equals("Y") ) 
					&& !(szNetOfCube[0][4].equals("Y") && szNetOfCube[2][4].equals("Y") ))
			{
				//is already in generic position
				
				i = 0;
			} 
			
			else if( ( szNetOfCube[0][4].equals("Y") && szNetOfCube[1][5].equals("Y") ) 
					&& !(szNetOfCube[1][3].equals("Y") && szNetOfCube[2][4].equals("Y") ) )
			{
				//move into the generic position
				move.moveYellowFace("U'", 1);
				
				System.out.println("U'1");
				
				i = 1;
			} else if( ( szNetOfCube[0][4].equals("Y") && szNetOfCube[1][3].equals("Y") )
					&& !(szNetOfCube[1][5].equals("Y") && szNetOfCube[2][4].equals("Y") ) )
			{
				//is already in the generic position
				
				i = 1;
			} else if( ( szNetOfCube[1][3].equals("Y") && szNetOfCube[2][4].equals("Y") )
					&& !(szNetOfCube[1][5].equals("Y") && szNetOfCube[0][4].equals("Y") ) )
			{
				//move into the generic position
				move.moveYellowFace("U", 1);
				
				System.out.println("U1");
				
				i = 1;
			} else if( ( szNetOfCube[2][4].equals("Y") && szNetOfCube[1][5].equals("Y") ) 
					&& !(szNetOfCube[0][4].equals("Y") && szNetOfCube[1][3].equals("Y") ) )
			{
				//move into the generic position
				move.moveYellowFace("U", 2);
				
				System.out.println("U2");
				
				i = 1;
			} 
			
			else if( !(szNetOfCube[0][4].equals("Y") && szNetOfCube[1][3].equals("Y") && 
					szNetOfCube[2][4].equals("Y") && szNetOfCube[1][5].equals("Y")))
			{
				i = 2;
			} else
			{
				i = 3 ;
			}
		
		return i ;
	}

	public int checkyellowCornerState()
	{

		
		int iCount = 0;
		
		if(szNetOfCube[0][3].equals("Y"))
		{
			iCount++ ;
		}
		
		if(szNetOfCube[0][5].equals("Y"))
		{
			iCount++;
		}
		
		if(szNetOfCube[2][3].equals("Y"))
		{
			iCount++;
		}
		
		if(szNetOfCube[2][5].equals("Y"))
		{
			iCount++;
		}
		
		//subtract all the pieces we know are facing upwards which are five of them.  The four edges and
		//the centre.
		return iCount;
		
	}
	
	public String decideState()
	{
		String i = "0";
		
		if(szNetOfCube[3][3].equals(szNetOfCube[3][5]) && szNetOfCube[3][3].equals("R"))
		{
			//only one set is correct, which means we will need to know which colour it is to orientate the
			//last set correctly.
			i = "0" + szNetOfCube[3][3] + "33";
			
				if(szNetOfCube[11][3].equals(szNetOfCube[11][5]) && szNetOfCube[11][3].equals("O") )
				{
					//Both sets are already correct which means 
					i = "1";
				}
		} else if(szNetOfCube[11][3].equals(szNetOfCube[11][5]) && szNetOfCube[11][3].equals("O"))
		{
			//only one set is correct, which means we will need to know which colour it is to orientate the
			//last set correctly.
			i = "0" + szNetOfCube[11][3] + "11-3";
			
				if(szNetOfCube[3][3].equals(szNetOfCube[3][5]) && szNetOfCube[3][3].equals("R") )
				{
					//Both sets are already correct which means no further action is required to orientate
					//all we need to do is maybe rotate it into the correct position
					i = "1";
				}
		} else
		{
			
			i = "2";
			
		}
		
		
		
		return i ;
	}
	
	public void moveSolvedYellowCorners()
	{
		if(szNetOfCube[3][3].equals("O"))
		{
			move.moveYellowFace("U", 2);
			updateNet();
			
			System.out.println("U2");
			
		} else if(szNetOfCube[3][3].equals("R"))
		{
			//do nothing
		} else if(szNetOfCube[3][3].equals("G"))
		{
			move.moveYellowFace("U'", 1);
			updateNet();
			
			System.out.println("U'1");
		} else if(szNetOfCube[3][3].equals("B"))
		{
			move.moveYellowFace("U", 1);
			updateNet();
			
			System.out.println("U1");
		}
	}

	public boolean checkIfSolved() 
	{
		boolean bYellow = false , bRed = false , bWhite = false , bBlue = false , bGreen = false, bOrange = false
				, bDecision = false;
		
		for(int i = 0 ; i < 3 ; i++)
		{
			for(int j = 3 ; j < 6 ; j++)
			{
				if(szNetOfCube[i][j].equals("Y"))
				{
					bYellow = true ;
				} else
				{
					bYellow = false;
					break ;
				}
			}
			
			if(bYellow == false)
			{
				break ;
			}
		}
		
		
		
		for(int i = 3 ; i < 6 ; i++)
		{
			for(int j = 3 ; j < 6 ; j++)
			{
				if(szNetOfCube[i][j].equals("R"))
				{
					bRed = true ;
				} else
				{
					bRed = false;
					break;
				}
			}
			
			if(bRed == false)
			{
				break ;
			}
		}
		
		
		for(int i = 6 ; i < 9 ; i++)
		{
			for(int j = 3 ; j < 6 ; j++)
			{
				if(szNetOfCube[i][j].equals("W"))
				{
					bWhite = true ;
				} else
				{
					bWhite = false;
					break;
				}
			}
			
			if(bWhite == false)
			{
				break ;
			}
		}
		
		
		for(int i = 6 ; i < 9 ; i++)
		{
			for(int j = 0 ; j < 2 ; j++)
			{
				if(szNetOfCube[i][j].equals("B"))
				{
					bBlue = true ;
				} else
				{
					bBlue = false;
					break;
				}
			}
			
			if(bBlue == false)
			{
				break ;
			}
		}
		
		for(int i = 6 ; i < 9 ; i++)
		{
			for(int j = 6 ; j < 9 ; j++)
			{
				if(szNetOfCube[i][j].equals("G"))
				{
					bGreen = true ;
				} else
				{
					bGreen = false;
					break;
				}
			}
			
			if(bGreen == false)
			{
				break ;
			}
		}
		
		for(int i = 9 ; i < 12 ; i++)
		{
			for(int j = 3 ; j < 6 ; j++)
			{
				if(szNetOfCube[i][j].equals("O"))
				{
					bOrange = true ;
				} else
				{
					bOrange = false;
					break;
				}
			}
			
			if(bOrange == false)
			{
				break ;
			}
		}
		
		if(bYellow == true && bRed == true && bWhite == true && bGreen == true && bBlue == true && bOrange == true)
		{
			bDecision = true;
		} else
		{
			bDecision = false;
		}
		
		return bDecision ;
	}

	public int checkAltEdgeState()
	{
		int i = 3;
		
		//State One: Diagonal Shuffle
		//State Two: Opposite Shuffle
		//State Three: U Perm
		//State Four: Solved
		
		if(checkIfSolved() == true)
		{
			i = 4;
		} else
		{
			//There is a possibility that the cube is solved and just rotated maybe one or two or three
			//turns out of sync, which in that case the code is going to get stuck in a loop so we must
			//check to see if this is the case and then return the value of 4
			
			move.moveYellowFace("U", 1);
			updateNet() ;
			
			if(checkIfSolved() == true)
			{
				i = 4;
				System.out.println("U");
			} else
			{
				move.moveYellowFace("U", 1);
				updateNet() ;
				
				if(checkIfSolved() == true)
				{
					i = 4;
					System.out.println("U2");
				} else
				{
					move.moveYellowFace("U", 1);
					updateNet() ;
					
					if(checkIfSolved() == true)
					{
						i = 4;
						System.out.println("U'");
					} else
					{
						move.moveYellowFace("U", 1);
						updateNet() ;
					}
				}
			}
		}
		
		if(i == 3)
		{
			if( (szNetOfCube[3][4].equals("B") && szNetOfCube[7][0].equals("R") )
					&& (szNetOfCube[11][4].equals("G") && szNetOfCube[7][8].equals("O")) )
			{
				
				//Diagonal shuffle required
				i = 1;
			} else if( ( szNetOfCube[3][4].equals("G") && szNetOfCube[7][8].equals("R") ) 
					&& (szNetOfCube[7][0].equals("O") && szNetOfCube[11][4].equals("B") ) )
			{
				//Diagonal shuffle required
				i = 1;
			} else if( (szNetOfCube[3][4].equals("O") && szNetOfCube[11][4].equals("R") )
					&& (szNetOfCube[7][0].equals("G") && szNetOfCube[7][8].equals("B")) )
			{
				//Opposite shuffle
				i = 2;
			} 
		}
		
		return i ;
	}
	
	//=======================================Moving the Net Around====================================================
	public void enterCurrentNetDetails(String string, int i , int j) {
		szNetOfCube[i][j] = string ;
	}

	public void sendNetToMove()
	{
		for(int i = 0 ; i < 12 ; i++)
		{
			for(int j = 0 ; j < 9 ; j++)
			{
				move.getNetFromSolve(szNetOfCube[i][j], i, j);
			}
		}
	}

	public void updateNet()
	{
		for(int i = 0 ; i < 12 ; i++)
		{
			for(int j = 0 ; j < 9 ; j++)
			{
				szNetOfCube[i][j] = move.getNet(i, j);
			}
		}
	}
	
	public String generateScramble()
	{
		String szNotation[] = new String[12] , szScrambleCode = "";
		int Random = 0;
		
		sendNetToMove() ;
		
		szNotation[0] = "R";
		szNotation[1] = "R'" ;
		szNotation[2] = "L" ;
		szNotation[3] = "L'" ;
		szNotation[4] = "F" ;
		szNotation[5] = "F'" ;
		szNotation[6] = "B" ;
		szNotation[7] = "B'" ;
		szNotation[8] = "U";
		szNotation[9] = "U'";
		szNotation[10] = "D";
		szNotation[11] = "D'";
		
		for(int i = 0 ; i < (int) (Math.random() * 50 + 20) ; i++)
		{
			Random = (int) (Math.random() * 11 + 0) ;
			
			if(i == 0)
			{
				szScrambleCode = szNotation[Random] ;
			} else
			{
				szScrambleCode = szScrambleCode + "," + szNotation[Random] ;
			}
			
			if(Random == 0)
			{
				move.moveGreenFace("R", 1);
				updateNet() ;
			} else if(Random == 1)
			{
				move.moveGreenFace("R'", 1);
				updateNet() ;
			} else if(Random == 2)
			{
				move.moveBlueFace("L", 1);
				updateNet() ;
			} else if(Random == 3)
			{
				move.moveBlueFace("L'", 1);		
				updateNet() ;
			} else if(Random == 4)
			{
				move.moveRedFace("F", 1);
				updateNet() ;
			} else if(Random == 5)
			{
				move.moveRedFace("F'", 1);
				updateNet() ;
			} else if(Random == 6)
			{
				move.moveOrangeFace("B", 1);
				updateNet() ;
			} else if(Random == 7)
			{
				move.moveOrangeFace("B'", 1);
				updateNet() ;
			} else if(Random == 8)
			{
				move.moveWhiteFace("D", 1);
				updateNet() ;
			} else if(Random == 9)
			{
				move.moveWhiteFace("D'", 1);
				updateNet() ;
			} else if(Random == 10)
			{
				move.moveYellowFace("U", 1);
				updateNet() ;
			} else if(Random == 11)
			{
				move.moveYellowFace("U'", 1);
				updateNet() ;
			}
			
			updateNet() ;
		}
		System.out.println("\n");
		move.display();
		
		return szScrambleCode ;
	}

	public static void main(String[] args) {


	}


}
