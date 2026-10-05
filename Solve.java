
public class Solve {

	//variables
	public Move move = new Move() ;
	
	//for the white edge pieces
	public int[] iWhiteEdgePiecesX = {4,3,5,4,4,3,5,4,4,4,3,5,1,0,2,1,7,6,8,7,4,4,3,5};
	public int[] iWhiteEdgePiecesY = {0,1,1,2,3,4,4,5,6,8,7,7,6,7,7,8,6,7,7,8,9,11,10,10} ;
	public int[] iWhiteEdgeColourX = {4,0,8,4,4,1,7,4,4,4,2,6,3,3,3,3,5,5,5,5,4,4,1,7} ;
	public int[] iWhiteEdgeColourY ={11,7,7,3,2,6,6,6,5,9,7,7,4,1,7,10,4,7,1,10,8,0,8,8} ;
	public int[] iWhiteEdgeFoundX = new int[4];
	public int[] iWhiteEdgeFoundY = new int[4];
	public int[] iPlacement = new int[4] ;
	
	
	public String[] szWhiteEdgePieceLocation = new String[4];
	
	
	public String[][] szNetOfCube = new String[12][9] ;
	
	
	
	public String[] szColourFound = new String[4];
	
	
	
	public int f = 0, CurrentX = 0, CurrentY = 0;
	
	//constructor
	public Solve()
	{
		
	}
	//default
	
	//specific
	
	//methods
	public void findWhiteEdges()
	{
		boolean bWhiteEdgeRed = false, bWhiteEdgeBlue = false, bWhiteEdgeGreen = false, bWhiteEdgeOrange = false;
		
		
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
		
					//The x and y coordinates are stored in the two arrays with the found coordinates, so now we need to find the corresponding colour
					findColours() ;
					
					bWhiteEdgeRed = findRed() ;
					bWhiteEdgeOrange = findOrange() ;
					bWhiteEdgeGreen = findGreen() ;
					bWhiteEdgeBlue = findBlue() ;
					
					//so now, the coordinates of the white edge piece is stored at iWhiteEdgeFoundX[iPlacement[i]] 
					//and iWhiteEdgeFoundY[iPlacement[i]] and the corresponding colours are stored at iWhiteEdgeColourX[iPlacement[i]] and 
					//iWhiteEdgeColourY[iPlacement[i]] on the variable array szNetOfCube[y][x].
					//Now we need to be able to manipulate the net to move these pieces into the correct place
					//This will take a lot of consideration.
					
					//There are four white pieces, each needing to be moved into the correct place.  In the class "Move", I have six subroutines which perform the function of moving a face, ie I would call
					//move.moveYellowFace() ;  It would manipulate the face in the required direction and then all the corresponding pieces on the rest of the net.
					
					//Firstly send the net to Move.java
					sendNetToMove() ;
					
					//Move the yellow face TEST
					move.moveWhiteFace("D'", 1);
		}
		
	}
	
	//setters
	
	//getters
	
	public void getCoords(int i2) 
	{
		CurrentX = iWhiteEdgeFoundX[i2];
		CurrentY = iWhiteEdgeFoundY[i2];
	}
	
	//utilities

	public boolean findRed()
	{
		boolean b = false ;
		
		for(int i = 0 ; i < 4 ; i++)
		{
			if(szColourFound[i].equalsIgnoreCase("R"))
			{
				b = true ;
			}
		}
		
		return b ;
	}
	
	public boolean findOrange()
	{
		boolean b = false ;
		
		for(int i = 0 ; i < 4 ; i++)
		{
			if(szColourFound[i].equalsIgnoreCase("O"))
			{
				b = true ;
			}
		}
		
		return b ;
	}
	
	public boolean findGreen()
	{
		boolean b = false ;
		
		for(int i = 0 ; i < 4 ; i++)
		{
			if(szColourFound[i].equalsIgnoreCase("G"))
			{
				b = true ;
			}
		}
		
		return b ;
	}
	
	public boolean findBlue()
	{
		boolean b = false ;
		
		for(int i = 0 ; i < 4 ; i++)
		{
			if(szColourFound[i].equalsIgnoreCase("B"))
			{
				b = true ;
			}
		}
		
		return b ;
	}
	
	public boolean findColours()
	{
		int iXAxis3 = 0, iYAxis3 = 0;
		
		for(int i = 0 ; i < 4 ; i++)
		{
			getCoords(i) ;
			
			for(int i3 = 0 ; i3 < 24 ; i3++)
			{
				if(CurrentX == iWhiteEdgePiecesX[i3] && CurrentY == iWhiteEdgePiecesY[i3])
				{
					szColourFound[i] = szNetOfCube[iWhiteEdgeColourY[i3]][iWhiteEdgeColourX[i3]] ;
					
					iPlacement[i] = i3 ;
				}
			}
			
		}
			
		
		
		return true ;
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
				System.out.println("White edge piece found at " + iXAxis + " , " + iYAxis);
				
				//this stores the coordinates of the white edge pieces for later use
				iWhiteEdgeFoundX[f] = iXAxis;
				iWhiteEdgeFoundY[f] = iYAxis;
				f++;
			}
			
		}
	}

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
	
	public static void main(String[] args) {
		

	}

	

}
