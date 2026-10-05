import java.util.Scanner ;

public class Cube {

	//variables
	String inputColours = "";
	int o = 0;
	String[][] szWhiteFace = new String [3][3] ;
	String[][] szYellowFace = new String [3][3] ;
	String[][] szRedFace = new String [3][3] ;
	String[][] szOrangeFace = new String [3][3] ;
	String[][] szBlueFace = new String [3][3] ;
	String[][] szGreenFace = new String [3][3] ;
	String[][] szNetOfCube = new String[12][9] ;
	String[][] szPreSetNet = new String[12][9] ;
	String szScrambleCode = "";
	boolean bLoop = true ;
	
	int iWhites = 9, iYellows = 9, iReds = 9, iBlues = 9 , iGreens = 9 , iOranges = 9 ;
	
	String[] szNotation = new String[12] ;
	Scanner szKeyboard = new Scanner(System.in) ;
	
	//constructor
	public Cube()
	{
		reset() ;
	}
	
	public void reset()
	{
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				szWhiteFace[i][j] = "";
				szYellowFace[i][j] = "";
				szRedFace[i][j] = "";
				szOrangeFace[i][j] = "";
				szBlueFace[i][j] = "";
				szGreenFace[i][j] = "";
			}
		}
	}
	
	//default
	
	//specific
	//methods
	
	
	//setters
	
	//=============Generating A Cube=============
	public void setSolvedCube()
	{
		
		inputColours = "WWWWWWWWW";
		
		for(int y = 0 ; y < 3 ; y++)
		{
			for(int x = 0 ; x < 3 ; x++)
			{
				szWhiteFace[y][x] = String.valueOf(inputColours.charAt(o)) ;
				o++;
			}
			
		}
		
		o = 0;
		
		inputColours = "YYYYYYYYY";
		
		for(int y = 0 ; y < 3 ; y++)
		{
			for(int x = 0 ; x < 3 ; x++)
			{
				szYellowFace[y][x] = String.valueOf(inputColours.charAt(o)) ;
				o++;
			}
			
		}
		
		o = 0;
		
		inputColours = "RRRRRRRRR";
		
		for(int y = 0 ; y < 3 ; y++)
		{
			for(int x = 0 ; x < 3 ; x++)
			{
				szRedFace[y][x] = String.valueOf(inputColours.charAt(o)) ;
				o++;
			}
			
		}
		
		o = 0;
		
		inputColours = "OOOOOOOOO";
		
		for(int y = 0 ; y < 3 ; y++)
		{
			for(int x = 0 ; x < 3 ; x++)
			{
				szOrangeFace[y][x] = String.valueOf(inputColours.charAt(o)) ;
				o++;
			}
			
		}
		
		o = 0;
		
		inputColours = "BBBBBBBBB";
		
		for(int y = 0 ; y < 3 ; y++)
		{
			for(int x = 0 ; x < 3 ; x++)
			{
				szBlueFace[y][x] = String.valueOf(inputColours.charAt(o)) ;
				o++;
			}
			
		}
		
		o = 0;
		
		inputColours = "GGGGGGGGG";
		
		for(int y = 0 ; y < 3 ; y++)
		{
			for(int x = 0 ; x < 3 ; x++)
			{
				szGreenFace[y][x] = String.valueOf(inputColours.charAt(o)) ;
				o++;
			}
			
		}
		
		Build() ;
		
		for(int i = 0 ; i < 12 ; i++)
		{
			for(int j = 0 ; j < 9 ; j++)
			{
				if(szNetOfCube[i][j] == null)
				{
					szNetOfCube[i][j] = "  ";
				}
			}
		}
		
		displayNet();
	}
	
	//=============Setting A User Face=============
	public boolean setWhiteCentreFace()
	{
		o = 0;

			System.out.println("\n\n\nPlease face the white centre of your Rubix Cube to your face and face the red centre upwards.\nKey in each"
					+ " colour as they appear, moving from left to right and by row.\nFor example if the colour is red, type R.\nOnce you have"
					+ " written in all the letters in a sequential order, click ENTER");
			inputColours = szKeyboard.nextLine();

//			inputColours = "OOYRWGBBR" ;
			
			while(inputColours.length() != 9)
			{
				System.out.println("You have entered either too little or too many colours.\nPlease face the white centre of your Rubix Cube to your face and face the red centre upwards.  Key in each"
						+ " colour as they appear, moving from left to right and by row.\nFor example if the colour is red, type R.\nOnce you have"
						+ " written in all the letters in a sequential order, click ENTER");
				inputColours = szKeyboard.nextLine();
			}
			
			inputColours = inputColours.toUpperCase();
			
			for(int i = 0 ; i < 9 ; i++)
			{
				if(String.valueOf(inputColours.charAt(i)).equals("W"))
				{
					iWhites--;
				} else if(String.valueOf(inputColours.charAt(i)).equals("Y"))
				{
					iYellows--;
				} else if(String.valueOf(inputColours.charAt(i)).equals("R"))
				{
					iReds--;
				} else if(String.valueOf(inputColours.charAt(i)).equals("G"))
				{
					iGreens--;
				} else if(String.valueOf(inputColours.charAt(i)).equals("B"))
				{
					iBlues--;
				} else if(String.valueOf(inputColours.charAt(i)).equals("O"))
				{
					iOranges--;
				} else
				{
					System.out.println("You have entered a false value.");
					bLoop = true ;
					break ;
				}
				
			}	
		
		for(int y = 0 ; y < 3 ; y++)
		{
			for(int x = 0 ; x < 3 ; x++)
			{
				szWhiteFace[y][x] = String.valueOf(inputColours.charAt(o)) ;
				o++;
			}
			
		}
			
		return bLoop ;
	}
	
	public boolean setYellowCentreFace()
	{
		o = 0;
		
		System.out.println("\n\n\nPlease face the yellow centre of your Rubix Cube to your face and face the orange centre upwards.\nKey in each"
				+ " colour as they appear, moving from left to right and by row.\nFor example if the colour is red, type R.\n"
				+ "Once you have written in all the letters in a sequential order, click ENTER");
		
		inputColours = szKeyboard.nextLine();
//		inputColours = "" ;
//		inputColours = "YWBYYWRBW" ;
		
		while(inputColours.length() != 9)
		{
			System.out.println("You have entered either too little or too many colours.\nPlease face the white centre of your Rubix Cube to your face and face the red centre upwards."
					+ "\nKey in each colour as they appear, moving from left to right and by row.\nFor example if the colour is red, type R.\nOnce you have"
					+ " written in all the letters in a sequential order, click ENTER");
			inputColours = szKeyboard.nextLine();
		}
		
		inputColours = inputColours.toUpperCase();
		
		for(int i = 0 ; i < 9 ; i++)
		{
			if(String.valueOf(inputColours.charAt(i)).equals("W"))
			{
				iWhites--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("Y"))
			{
				iYellows--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("R"))
			{
				iReds--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("G"))
			{
				iGreens--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("B"))
			{
				iBlues--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("O"))
			{
				iOranges--;
			} else
			{
				bLoop = true ;
			}
			
		}
		
		for(int y = 0 ; y < 3 ; y++)
		{
			for(int x = 0 ; x < 3 ; x++)
			{
				szYellowFace[y][x] = String.valueOf(inputColours.charAt(o)) ;
				o++;
			}
			
		}
		
		return bLoop ;
	}
	
	public boolean setRedCentreFace()
	{
		o = 0;
		
		System.out.println("\n\n\nPlease face the red centre of your Rubix Cube to your face and face the yellow centre upwards.\nKey in each"
				+ " colour as they appear, moving from left to right and by row.\nFor example if the colour is red, type R.\n"
				+ "Once you have written in all the letters in a sequential order, click ENTER");
		
		inputColours = szKeyboard.nextLine();
//		inputColours = "";
//		inputColours = "WOGGRYGYR" ;
		
		while(inputColours.length() != 9)
		{
			System.out.println("You have entered either too little or too many colours.\nPlease face the white centre of your Rubix Cube to your face and face the red centre upwards."
					+ "\nKey in each colour as they appear, moving from left to right and by row.\nFor example if the colour is red, type R.\nOnce you have"
					+ " written in all the letters in a sequential order, click ENTER");
			inputColours = szKeyboard.nextLine();
		}
		
		inputColours = inputColours.toUpperCase();
		
		for(int i = 0 ; i < 9 ; i++)
		{
			if(String.valueOf(inputColours.charAt(i)).equals("W"))
			{
				iWhites--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("Y"))
			{
				iYellows--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("R"))
			{
				iReds--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("G"))
			{
				iGreens--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("B"))
			{
				iBlues--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("O"))
			{
				iOranges--;
			} else
			{
				bLoop = true ;
			}
			
		}
		
		for(int y = 0 ; y < 3 ; y++)
		{
			for(int x = 0 ; x < 3 ; x++)
			{
				szRedFace[y][x] = String.valueOf(inputColours.charAt(o)) ;
				o++;
			}
			
		}
		
		return bLoop ;
	}
	
	public boolean setOrangeCentreFace()
	{
		o = 0;
		
		System.out.println("\n\n\nPlease face the orange centre of your Rubix Cube to your face and face the white centre upwards.\nKey in each"
				+ " colour as they appear, moving from left to right and by row.\nFor example if the colour is red, type R.\n"
				+ "Once you have written in all the letters in a sequential order, click ENTER");
		
		inputColours = szKeyboard.nextLine();
//		inputColours = "";
//		inputColours = "WYGWORGGO" ;
		
		while(inputColours.length() != 9)
		{
			System.out.println("You have entered either too little or too many colours.\nPlease face the white centre of your Rubix Cube to your face and face the red centre upwards."
					+ "\nKey in each colour as they appear, moving from left to right and by row.\nFor example if the colour is red, type R.\nOnce you have"
					+ " written in all the letters in a sequential order, click ENTER");
			inputColours = szKeyboard.nextLine();
		}
		
		inputColours = inputColours.toUpperCase();
		
		for(int i = 0 ; i < 9 ; i++)
		{
			if(String.valueOf(inputColours.charAt(i)).equals("W"))
			{
				iWhites--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("Y"))
			{
				iYellows--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("R"))
			{
				iReds--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("G"))
			{
				iGreens--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("B"))
			{
				iBlues--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("O"))
			{
				iOranges--;
				bLoop = true;
			}
			
		}
		
		for(int y = 0 ; y < 3 ; y++)
		{
			for(int x = 0 ; x < 3 ; x++)
			{
				szOrangeFace[y][x] = String.valueOf(inputColours.charAt(o)) ;
				o++;
			}
			
		}
		
		return bLoop ;
	}
	
	public boolean setBlueCentreFace()
	{
		boolean bWorks = true ;
		
		o = 0;
		
		System.out.println("\n\n\nPlease face the blue centre of your Rubix Cube to your face and face the red centre upwards.\nKey in each"
				+ " colour as they appear, moving from left to right and by row.\nFor example if the colour is red, type R.\n"
				+ "Once you have written in all the letters in a sequential order, click ENTER");
		
		inputColours = szKeyboard.nextLine();
//		inputColours = "";
//		inputColours = "BOWGBBOBO" ;
		
		while(inputColours.length() != 9)
		{
			System.out.println("You have entered either too little or too many colours.\nPlease face the white centre of your Rubix Cube to your face and face the red centre upwards."
					+ "\nKey in each colour as they appear, moving from left to right and by row.\nFor example if the colour is red, type R.\nOnce you have"
					+ " written in all the letters in a sequential order, click ENTER");
			inputColours = szKeyboard.nextLine();
		}
		
		inputColours = inputColours.toUpperCase();
		
		for(int i = 0 ; i < 9 ; i++)
		{
			if(String.valueOf(inputColours.charAt(i)).equals("W"))
			{
				iWhites--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("Y"))
			{
				iYellows--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("R"))
			{
				iReds--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("G"))
			{
				iGreens--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("B"))
			{
				iBlues--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("O"))
			{
				iOranges--;
				bLoop = true;
			}
			
		}
		
		for(int y = 0 ; y < 3 ; y++)
		{
			for(int x = 0 ; x < 3 ; x++)
			{
				szBlueFace[y][x] = String.valueOf(inputColours.charAt(o)) ;
				o++;
			}
			
		}
		
		if( (iWhites != 0 || iYellows != 0 || iReds != 0 || iGreens != 0 || iBlues != 0 || iOranges != 0 ) && bLoop == true)
		{
			System.out.println("\n\nYou have inputted an incorrect number of colours making this cube unsolveable."
					+ "\nYou will now have to repeat the above steps in order to rectify your mistake.");
			
			bWorks = false ;
		} else if( (iWhites != 0 || iYellows != 0 || iReds != 0 || iGreens != 0 || iBlues != 0 || iOranges != 0 ) )
		{
			bWorks = false ;
		}
		
		return bWorks ;
	}
	
	public boolean setGreenCentreFace()
	{
		o = 0;
		
		System.out.println("\n\n\nPlease face the green centre of your Rubix Cube to your face and face the red centre upwards.\nKey in each"
				+ " colour as they appear, moving from left to right and by row.\nFor example if the colour is red, type R.\n"
				+ "Once you have written in all the letters in a sequential order, click ENTER");
		
		inputColours = szKeyboard.nextLine();
//		inputColours = "";
//		inputColours = "BRRRGOYWY" ;
		
		while(inputColours.length() != 9)
		{
			System.out.println("You have entered either too little or too many colours.\nPlease face the white centre of your Rubix Cube to your face and face the red centre upwards."
					+ "\nKey in each colour as they appear, moving from left to right and by row.\nFor example if the colour is red, type R.\nOnce you have"
					+ " written in all the letters in a sequential order, click ENTER");
			inputColours = szKeyboard.nextLine();
		}
		
		inputColours = inputColours.toUpperCase();
		
		for(int i = 0 ; i < 9 ; i++)
		{
			if(String.valueOf(inputColours.charAt(i)).equals("W"))
			{
				iWhites--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("Y"))
			{
				iYellows--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("R"))
			{
				iReds--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("G"))
			{
				iGreens--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("B"))
			{
				iBlues--;
			} else if(String.valueOf(inputColours.charAt(i)).equals("O"))
			{
				iOranges--;
			} else
			{
				bLoop = true ;
			}
			
		}
		
		for(int y = 0 ; y < 3 ; y++)
		{
			for(int x = 0 ; x < 3 ; x++)
			{
				szGreenFace[y][x] = String.valueOf(inputColours.charAt(o)) ;
				o++;
			}
			
		}
	
	return bLoop ;
	}
	
	//getters
	//=============Moving the Net From Cube To Move=============
	public String getNet(int i , int j)
	{
		return szNetOfCube[i][j] ;
	}
	
	public void getNetFromMove(String string , int j , int i)
	{
		szNetOfCube[j][i] = string ;
	}
	
	//utilities
	
	//=============Displaying And Building The Net=============
	public void displayNet()
	{
		
		for(int i = 0; i < 12 ; i++)
		{
			for(int j = 0 ; j < 9 ; j++)
			{
				if(szNetOfCube[i][j] == null)
				{
					szNetOfCube[i][j] = "  " ;
				}
							
				switch(szNetOfCube[i][j])
				{
				
				case "R":
				{
					System.out.print("\u001B[41m  ");
					break ;
				}
				
				case "Y":
				{
					System.out.print("\u001B[43m  ");
					break ;
				}
				
				case "W":
				{
					System.out.print("\u001B[47m  ");
					break ;
				}
				
				case "B":
				{
					System.out.print("\u001B[44m  ");
					break ;
				}
				
				case "G":
				{
					System.out.print("\u001B[42m  ");
					break ;
				}
				
				case "O":
				{
					System.out.print("\033[48;2;255;165;0m  ");
					break ;
				}
				
				case "  ":
				{
					System.out.print("\u001B[49m  ");
					break ;
				}
				
				}

			}
			System.out.print("\n");
		}
		

	}
	
	public void sendNetToCube(String string , int y , int x)
	{
		szNetOfCube[y][x] = string ;
	}
	
	public void Build()
	{
		//inside of this subroutine we will build the cube based on the layout of each side at that time.  So upon manipulation of the cube
		//to follow a certain algorithm that will help solve it, once the algorithm is done we will run this subroutine again and build the
		//cube back up
		
		//the easiest way to "build" the cube, is to display it as its net structure.  The user doesn't need to see this, this is purely
		//for the reference of the program.  I can write in the checks necessary for the program to then be able to view the net
		//as a perfect 3d cube
		
		//If we use a 9 by 12 array and then populate only the centre three for the first and second row, the full third row and the centre
		//three for the fourth row, then we can create the net.  This is obviously not that efficient in terms of space, but it is the
		//easiest solve that I can think of
		
		//net layout:
		//      Y
		//      R
		//    B W G
		//      O
		
		//Make the parts of the array containing colours store them, in the right orientation.  Where the user inputs the
		//values, you can see that I have asked them to orient the cube in a specific way when reading which colours are where.
		//this is so that when the values are stored in the net, they are stored in such a way that the faces are the right way up
		//to connect with the other faces when the 2d net is made 3d.
		
		//Build the top row (Yellow Face)
		
		for(int j = 3 ; j < 6 ; j++)
		{
			for(int i = 0; i < 3 ; i++)
			{
				szNetOfCube[i][j] = szYellowFace[i][j - 3] ;
			}
		}

		//build the second row (Red Face)
		for(int j = 3 ; j < 6 ; j++)
		{
			for(int i = 3; i < 6 ; i++)
			{
				szNetOfCube[i][j] = szRedFace[i - 3][j - 3] ;
			}
		}
		
		//build the third row, Blue White and Green
		for(int j = 0 ; j < 9; j++)
		{
			if(j <= 2)
			{
				for(int i = 6 ; i < 9 ; i++)
				{
					szNetOfCube[i][j] = szBlueFace[i - 6][j] ;
				}
				
			} else if (j >= 3 && j <= 5)
			{
				for(int i = 6 ; i < 9 ; i++)
				{
					szNetOfCube[i][j] = szWhiteFace[i - 6][j - 3] ;
				}
				
			} else if (j >= 6 && j <= 9)
			{
				for(int i = 6 ; i < 9 ; i++)
				{
					szNetOfCube[i][j] = szGreenFace[i - 6][j - 6];	
				}
			}
		}
		
		//build the fourth and final orange row
		for(int j = 3 ; j < 6 ; j++)
		{
			for(int i = 9 ; i < 12 ; i++)
			{
				szNetOfCube[i][j] = szOrangeFace[i - 9][j - 3] ;
			}
		}
		
		
	}
	
	public void readPreSet()
	{
		
	}
	
	
	public static void main(String[] args) {
		

	}

}
