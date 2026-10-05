public class Move{

	public String[][] szNetOfCube = new String[12][9] ;
	public String[][] tempFace = new String[3][3] ;
	public String[] tempSide1 = new String[3], tempSide2 = new String[3] , tempSide3 = new String[3] , tempSide4 = new String[4] ; 
	public Cube Cube = new Cube() ;

	// U --> Red To Blue , U' --> Blue To Green
	// D --> Red To Blue , D' --> Red To Green
	// F --> Yellow To Green , F' --> Green To Yellow
	// B --> Yellow To Blue , B' --> Blue To Yellow
	// R' --> Yellow To Red , R --> Red To Yellow
	// L --> Yellow To Red , L' --> Yellow To Orange
	
	public Move()
	{

	}

	//=======================================Moving the Rubik's Cube=======================================
	
	public void moveYellowFace(String szCommand, int iNumTimes)
	{
		String[][] szYellowFace = new String[3][3] ;
		
		if(!(szCommand.equals("U") || szCommand.equals("U'")))
		{
			System.out.println("You have entered in the value " + szCommand + " which is not an allowed movement"
					+ " for this face.");
		} else
		{
			for(int m = 0 ; m < iNumTimes ; m++)
			{
				if(szCommand.equals("U"))
				{
					//then we have to move the yellow face right.  In this program, the user will be asked to always keep the white face facing down which means that the command "U" (rotated upper level left) or command " U' " (rotate upper level right)
					//will only apply to the yellow face.  This selection statement rotates the Yellow face left

					//Yellow Face Diagram from Net

					//  |(3,0)|(4,0)|(5,0)|
					//	|(3,1)|(4,1)|(5,1)|
					//	|(3,2)|(4,2)|(5,2)|

					//Yellow Face Diagram GENERAL

					//  |(0,0)|(1,0)|(2,0)|
					//	|(0,1)|(1,1)|(2,1)|
					//	|(0,2)|(1,2)|(2,2)|

					//Move the yellow pieces

					//To make it easier I will put the pieces in the net into a smaller array known as szYellowFace[][]
					for(int y = 0 ; y < 12 ; y++)
					{
						for(int x = 0 ; x < 9 ; x++)
						{
							if( (x == 3 && y == 0) || (x == 4 && y == 0) ||(x == 5 && y == 0) 
									||(x == 3 && y == 1) ||(x == 4 && y == 1) || (x == 5 && y == 1)
									||(x == 3 && y == 2) ||(x == 4 && y == 2)||(x == 5 && y == 2))
							{
								szYellowFace[y][x-3] = szNetOfCube[y][x] ;
							}
						}
					}


					//Now move the pieces on the array szYellowFace.  Take the bottom right piece (3,2) and place it into the placeholder array

					//Now we have to rotate the sides.  Populate the temporary variable
					for(int i = 0 ; i < 3 ; i++)
					{
						for(int j = 0 ; j < 3 ; j++)
						{
							tempFace[i][j] = szYellowFace[i][j] ;
							szYellowFace[i][j] = " " ;
						}
					}

					szYellowFace[0][2] = tempFace[0][0] ;
					szYellowFace[1][2] = tempFace[0][1] ;
					szYellowFace[2][2] = tempFace[0][2] ;
					szYellowFace[2][1] = tempFace[1][2] ;
					szYellowFace[2][0] = tempFace[2][2] ;
					szYellowFace[1][0] = tempFace[2][1] ;
					szYellowFace[0][0] = tempFace[2][0] ;
					szYellowFace[0][1] = tempFace[1][0] ;
					szYellowFace[1][1] = tempFace[1][1] ;

					//Change the corresponding colours
					//All the sides affected by the yellow side turning clockwise are R, G, O and B
					//Put the red pieces affected into tempSide1

					tempSide1[0] = szNetOfCube[3][3] ;
					tempSide1[1] = szNetOfCube[3][4] ;
					tempSide1[2] = szNetOfCube[3][5] ;

					//Put all the green pieces affected into tempSide2
					tempSide2[0] = szNetOfCube[6][8] ;
					tempSide2[1] = szNetOfCube[7][8] ;
					tempSide2[2] = szNetOfCube[8][8] ;

					//Put all the orange pieces affected into tempSide3
					tempSide3[0] = szNetOfCube[11][3] ;
					tempSide3[1] = szNetOfCube[11][4] ;
					tempSide3[2] = szNetOfCube[11][5] ;

					//put all the blue pieces affected into tempSide4
					tempSide4[0] = szNetOfCube[6][0] ;
					tempSide4[1] = szNetOfCube[7][0] ;
					tempSide4[2] = szNetOfCube[8][0] ;

					//Now affect the sides in szNetOfCube
					//The values in tempSide1 belong to the red face, and when rotated left they go to the blue face locations
					szNetOfCube[8][0] = tempSide1[0] ;
					szNetOfCube[7][0] = tempSide1[1] ;
					szNetOfCube[6][0] = tempSide1[2] ;

					//the values in tempSide2 belong to the green face and when rotated left they go to the red face locations
					szNetOfCube[3][3] = tempSide2[0] ;
					szNetOfCube[3][4] = tempSide2[1] ;
					szNetOfCube[3][5] = tempSide2[2] ;

					//the values in tempSide3 belong to the orange face and when rotated left they go to the green face locations
					szNetOfCube[6][8] = tempSide3[2] ;
					szNetOfCube[7][8] = tempSide3[1] ;
					szNetOfCube[8][8] = tempSide3[0] ;

					//the values in tempSide4 belongs to the blue face when rotated left they go to the orange face locations
					szNetOfCube[11][5] = tempSide4[2] ;
					szNetOfCube[11][4] = tempSide4[1] ;
					szNetOfCube[11][3] = tempSide4[0] ;




				}
				else if(szCommand == "U'")
				{
					//then we have to move the yellow face right.  In this program, the user will be asked to always keep the white face facing down which means that the command "U" (rotated upper level left) or command " U' " (rotate upper level right)
					//will only apply to the yellow face.  This selection statement rotates the Yellow face left

					//Yellow Face Diagram from Net

					//  |(3,0)|(4,0)|(5,0)|
					//	|(3,1)|(4,1)|(5,1)|
					//	|(3,2)|(4,2)|(5,2)|

					//Yellow Face Diagram GENERAL

					//  |(0,0)|(1,0)|(2,0)|
					//	|(0,1)|(1,1)|(2,1)|
					//	|(0,2)|(1,2)|(2,2)|

					//Move the yellow pieces

					//To make it easier I will put the pieces in the net into a smaller array known as szYellowFace[][]
					for(int y = 0 ; y < 12 ; y++)
					{
						for(int x = 0 ; x < 9 ; x++)
						{
							if( (x == 3 && y == 0) || (x == 4 && y == 0) ||(x == 5 && y == 0) 
							  ||(x == 3 && y == 1) || (x == 4 && y ==1) ||(x == 5 && y == 1) 
							  ||(x == 3 && y == 2) ||(x == 4 && y == 2)||(x == 5 && y == 2))
							{
								szYellowFace[y][x-3] = szNetOfCube[y][x] ;
							}
						}
					}


					//Now move the pieces on the array szYellowFace.  Take the bottom right piece (3,2) and place it into the placeholder array

					//Now we have to rotate the sides.  Populate the temporary variable
					for(int i = 0 ; i < 3 ; i++)
					{
						for(int j = 0 ; j < 3 ; j++)
						{
							tempFace[i][j] = szYellowFace[i][j] ;
							szYellowFace[i][j] = " " ;
						}
					}

					szYellowFace[2][0] = tempFace[0][0] ;
					szYellowFace[1][0] = tempFace[0][1] ;
					szYellowFace[0][0] = tempFace[0][2] ;
					szYellowFace[0][1] = tempFace[1][2] ;
					szYellowFace[0][2] = tempFace[2][2] ;
					szYellowFace[1][2] = tempFace[2][1] ;
					szYellowFace[2][2] = tempFace[2][0] ;
					szYellowFace[2][1] = tempFace[1][0] ;
					szYellowFace[1][1] = tempFace[1][1] ;

					//Change the corresponding colours
					//All the sides affected by the yellow side turning anti clockwise are R, G, O and B
					//Put the red pieces affected into tempSide1

					tempSide1[0] = szNetOfCube[3][3] ;
					tempSide1[1] = szNetOfCube[3][4] ;
					tempSide1[2] = szNetOfCube[3][5] ;

					//Put all the green pieces affected into tempSide2
					tempSide2[0] = szNetOfCube[6][8] ;
					tempSide2[1] = szNetOfCube[7][8] ;
					tempSide2[2] = szNetOfCube[8][8] ;

					//Put all the orange pieces affected into tempSide3
					tempSide3[0] = szNetOfCube[11][3] ;
					tempSide3[1] = szNetOfCube[11][4] ;
					tempSide3[2] = szNetOfCube[11][5] ;

					//put all the blue pieces affected into tempSide4
					tempSide4[0] = szNetOfCube[6][0] ;
					tempSide4[1] = szNetOfCube[7][0] ;
					tempSide4[2] = szNetOfCube[8][0] ;

					//Now affect the sides in szNetOfCube
					//The values in tempSide1 belong to the red face, and when rotated right they go to the green face locations
					szNetOfCube[6][8] = tempSide1[0] ;
					szNetOfCube[7][8] = tempSide1[1] ;
					szNetOfCube[8][8] = tempSide1[2] ;

					//the values in tempSide2 belong to the green face and when rotated right they go to the orange face locations
					szNetOfCube[11][5] = tempSide2[0] ;
					szNetOfCube[11][4] = tempSide2[1] ;
					szNetOfCube[11][3] = tempSide2[2] ;

					//the values in tempSide3 belong to the orange face and when rotated right they go to the blue face locations
					szNetOfCube[6][0] = tempSide3[0] ;
					szNetOfCube[7][0] = tempSide3[1] ;
					szNetOfCube[8][0] = tempSide3[2] ;

					//the values in tempSide4 belongs to the blue face when rotated right they go to the red face locations
					szNetOfCube[3][5] = tempSide4[0] ;
					szNetOfCube[3][4] = tempSide4[1] ;
					szNetOfCube[3][3] = tempSide4[2] ;
				}

				//Put the yellow face back into the net array
				for(int y = 0 ; y < 12 ; y++)
				{
					for(int x = 0 ; x < 9 ; x++)
					{
						if( (y == 0 && (x == 3 || x == 4 || x == 5) ) 
								|| (y == 1 && (x == 3 || x == 4 || x == 5) ) 
								|| (y == 2 && (x == 3 || x == 4 || x == 5) ) )
						{
							szNetOfCube[y][x] = szYellowFace[y][x - 3] ;
						}
					}
				}

				for(int y = 0 ; y < 12 ; y++)
				{
					for(int x = 0 ; x < 9 ; x++)
					{
						Cube.sendNetToCube(szNetOfCube[y][x], y, x);
					}
				}
			}

		}
		
		
		
//		System.out.println("YELLOW FACE " + szCommand + "," + iNumTimes);
//		for(int i = 0 ; i < 12 ; i++)
//		{
//			for(int j = 0 ; j < 9 ; j++)
//			{
//				System.out.print(szNetOfCube[i][j]);
//			}
//			System.out.print("\n");
//		}

//		display() ;
//		System.out.println("\n");
		
	}

	public void moveWhiteFace(String szCommand, int iNumTimes)
	{
		String[][] szWhiteFace = new String[3][3] ;
		
		if(!(szCommand.equals("D") || szCommand.equals("D'")))
		{
			System.out.println("You have entered in the value " + szCommand + " which is not an allowed movement"
					+ " for this face.");
		} else
		{
			for(int m = 0 ; m < iNumTimes ; m++)
			{
				//isolate the white face and store it in both a white face array and a temporary array
				//White Face Diagram from Net

				//  |(3,6)|(4,6)|(5,6)|
				//	|(3,7)|(4,7)|(5,7)|
				//	|(3,8)|(4,8)|(5,8)|

				//White Face Diagram GENERAL

				//  |(0,0)|(1,0)|(2,0)|
				//	|(0,1)|(1,1)|(2,1)|
				//	|(0,2)|(1,2)|(2,2)|
				
				for(int y = 0 ; y < 12 ; y++)
				{
					for(int x = 0 ; x < 9 ; x++)
					{
						if( (x == 3 && y == 6) || (x == 4 && y == 6) || (x == 5 && y == 6)
						 || (x == 3 && y == 7) || (x == 4 && y == 7) || (x == 5 && y == 7)
						 || (x == 3 && y == 8) || (x == 4 && y == 8) || (x == 5 && y == 8))
						{
							szWhiteFace[y-6][x-3] = szNetOfCube[y][x] ;
						}
					}
				}
				
				//Store in the temp face
				for(int i = 0 ; i < 3 ; i++)
				{
					for(int j = 0 ; j < 3 ; j++)
					{
						tempFace[i][j] = szWhiteFace[i][j] ;
					}
				}
				
				//Store the sides to be manipulated into the tempSide variables
				
				//tempSide1 contains the BLUE WHITE connector
				tempSide1[0] = szNetOfCube[6][2] ;
				tempSide1[1] = szNetOfCube[7][2] ;
				tempSide1[2] = szNetOfCube[8][2] ;
				
				//tempSide2 contains the ORANGE WHITE connector
				tempSide2[0] = szNetOfCube[9][3] ;
				tempSide2[1] = szNetOfCube[9][4] ;
				tempSide2[2] = szNetOfCube[9][5] ;
				
				//tempSide3 contains the GREEN WHITE connector
				tempSide3[0] = szNetOfCube[6][6] ;
				tempSide3[1] = szNetOfCube[7][6] ;
				tempSide3[2] = szNetOfCube[8][6] ;
				
				//tempSide4 contains the RED WHITE connector
				tempSide4[0] = szNetOfCube[5][3] ;
				tempSide4[1] = szNetOfCube[5][4] ;
				tempSide4[2] = szNetOfCube[5][5] ;
				
				if(szCommand.equals("D"))
				{
					//rotate the white face left
					szWhiteFace[0][0] = tempFace[0][2] ;
					szWhiteFace[1][0] = tempFace[0][1] ;
					szWhiteFace[2][0] = tempFace[0][0] ;
					szWhiteFace[2][1] = tempFace[1][0] ;
					szWhiteFace[2][2] = tempFace[2][0] ;
					szWhiteFace[1][2] = tempFace[2][1] ;
					szWhiteFace[0][2] = tempFace[2][2] ;
					szWhiteFace[0][1] = tempFace[1][2] ;
					szWhiteFace[1][1] = tempFace[1][1] ;
					
					
					
					//When rotating the white face left (command D):
						//Red --> Blue
					szNetOfCube[6][2] = tempSide4[2];
					szNetOfCube[7][2] = tempSide4[1];
					szNetOfCube[8][2] = tempSide4[0];
					
						//Blue --> Orange
					szNetOfCube[9][3] = tempSide1[0];
					szNetOfCube[9][4] = tempSide1[1];
					szNetOfCube[9][5] = tempSide1[2];
					
						//Orange --> Green
					szNetOfCube[6][6] = tempSide2[2];
					szNetOfCube[7][6] = tempSide2[1];
					szNetOfCube[8][6] = tempSide2[0];
					
						//Green --> Red
					szNetOfCube[5][3] = tempSide3[0];
					szNetOfCube[5][4] = tempSide3[1];
					szNetOfCube[5][5] = tempSide3[2];
					
				} else if(szCommand.equals("D'")) // Down PRIME
				{
					//rotate the white face right
					//  |(0,0)|(1,0)|(2,0)|
					//	|(0,1)|(1,1)|(2,1)|
					//	|(0,2)|(1,2)|(2,2)|
					
					szWhiteFace[0][0] = tempFace[2][0] ;
					szWhiteFace[1][0] = tempFace[2][1] ;
					szWhiteFace[2][0] = tempFace[2][2] ;
					szWhiteFace[2][1] = tempFace[1][2] ;
					szWhiteFace[2][2] = tempFace[0][2] ;
					szWhiteFace[1][2] = tempFace[0][1] ;
					szWhiteFace[0][2] = tempFace[0][0] ;
					szWhiteFace[0][1] = tempFace[1][0] ;
					szWhiteFace[1][1] = tempFace[1][1] ;
					
					//Red --> Green
					szNetOfCube[6][6] = tempSide4[0];
					szNetOfCube[7][6] = tempSide4[1];
					szNetOfCube[8][6] = tempSide4[2];
					
					//Green --> Orange
					szNetOfCube[9][3] = tempSide3[2];
					szNetOfCube[9][4] = tempSide3[1];
					szNetOfCube[9][5] = tempSide3[0];
					
					//Orange --> Blue
					szNetOfCube[6][2] = tempSide2[0];
					szNetOfCube[7][2] = tempSide2[1];
					szNetOfCube[8][2] = tempSide2[2];
					
					//Blue --> Red
					szNetOfCube[5][3] = tempSide1[2];
					szNetOfCube[5][4] = tempSide1[1];
					szNetOfCube[5][5] = tempSide1[0];
					
				}
				
				for(int y = 0 ; y < 12 ; y++)
				{
					for(int x = 0 ; x < 9 ; x++)
					{
						if( (x == 3 && y == 6) || (x == 4 && y == 6) || (x == 5 && y == 6)
						 || (x == 3 && y == 7) || (x == 4 && y == 7) || (x == 5 && y == 7)
						 || (x == 3 && y == 8) || (x == 4 && y == 8) || (x == 5 && y == 8))
						{
							szNetOfCube[y][x] = szWhiteFace[y-6][x-3];
							
						}
					}
				}
				
				for(int y = 0 ; y < 12 ; y++)
				{
					for(int x = 0 ; x < 9 ; x++)
					{
						Cube.sendNetToCube(szNetOfCube[y][x], y, x);
					}
				}
				
			}
		}
		
		
		
//		System.out.println("WHITE FACE " + szCommand + "," + iNumTimes);
//		for(int i = 0 ; i < 12 ; i++)
//		{
//			for(int j = 0 ; j < 9 ; j++)
//			{
//				System.out.print(szNetOfCube[i][j]);
//			}
//			System.out.print("\n");
//		}
		
//		display() ;
//		System.out.println("\n");
	}

	public void moveRedFace(String szCommand, int iNumTimes)
	{
		String szRedFace[][] = new String[3][3] ;
		
		if(!(szCommand.equals("F") || szCommand.equals("F'")))
		{
			System.out.println("You have entered in the value " + szCommand + " which is not an allowed movement"
					+ " for this face.");
		} else
		{
			for(int m = 0 ; m < iNumTimes ; m++)
			{
				//isolate the red face and store it in both a red face array and a temporary array
				//Red Face Diagram from Net

				//  |(3,3)|(4,3)|(5,3)|
				//	|(3,4)|(4,4)|(5,4)|
				//	|(3,5)|(4,5)|(5,5)|

				//Red Face Diagram GENERAL

				//  |(0,0)|(1,0)|(2,0)|
				//	|(0,1)|(1,1)|(2,1)|
				//	|(0,2)|(1,2)|(2,2)|
				
				for(int y = 0 ; y < 12 ; y++)
				{
					for(int x = 0 ; x < 9 ; x++)
					{
						if( (x == 3 && y == 3) || (x == 4 && y == 3) || (x == 5 && y == 3)
						 || (x == 3 && y == 4) || (x == 4 && y == 4) || (x == 5 && y == 4)
						 || (x == 3 && y == 5) || (x == 4 && y == 5) || (x == 5 && y == 5))
						{
							szRedFace[y-3][x-3] = szNetOfCube[y][x] ;
						}
					}
				}
				
				//Store in the temp face
				for(int i = 0 ; i < 3 ; i++)
				{
					for(int j = 0 ; j < 3 ; j++)
					{
						tempFace[i][j] = szRedFace[i][j] ;
					}
				}
				
				//Store the temporary sides.  The sides that are affected are, B Y G and W
				//tempSide1 contains the RED YELLOW
				tempSide1[0] = szNetOfCube[2][3] ;
				tempSide1[1] = szNetOfCube[2][4] ;
				tempSide1[2] = szNetOfCube[2][5] ;
				
				//tempSide2 contains the RED GREEN
				tempSide2[0] = szNetOfCube[6][6] ;
				tempSide2[1] = szNetOfCube[6][7] ;
				tempSide2[2] = szNetOfCube[6][8] ;
				
				//tempSide3 contains the RED WHITE
				tempSide3[0] = szNetOfCube[6][3] ;
				tempSide3[1] = szNetOfCube[6][4] ;
				tempSide3[2] = szNetOfCube[6][5] ;
				
				//tempSide4 contains the RED BLUE
				tempSide4[0] = szNetOfCube[6][0] ;
				tempSide4[1] = szNetOfCube[6][1] ;
				tempSide4[2] = szNetOfCube[6][2] ;
				
				if(szCommand.equals("F"))
				{
					//Rotate the red face right
					//Red Face Diagram GENERAL

					//  |(0,0)|(1,0)|(2,0)|
					//	|(0,1)|(1,1)|(2,1)|
					//	|(0,2)|(1,2)|(2,2)|
					
					szRedFace[0][0] = tempFace[2][0] ;
					szRedFace[1][0] = tempFace[2][1] ;
					szRedFace[2][0] = tempFace[2][2] ;
					szRedFace[2][1] = tempFace[1][2] ;
					szRedFace[2][2] = tempFace[0][2] ;
					szRedFace[1][2] = tempFace[0][1] ;
					szRedFace[0][2] = tempFace[0][0] ;
					szRedFace[0][1] = tempFace[1][0] ;
					szRedFace[1][1] = "R" ;
					
					//Rotate the edges
						//Yellow --> Green
					szNetOfCube[6][6] = tempSide1[2];
					szNetOfCube[6][7] = tempSide1[1];
					szNetOfCube[6][8] = tempSide1[0];
						//Green --> White
					szNetOfCube[6][3] = tempSide2[0];
					szNetOfCube[6][4] = tempSide2[1];
					szNetOfCube[6][5] = tempSide2[2];
						//White --> Blue
					szNetOfCube[6][0] = tempSide3[0];
					szNetOfCube[6][1] = tempSide3[1];
					szNetOfCube[6][2] = tempSide3[2];
						//Blue --> Yellow
					szNetOfCube[2][3] = tempSide4[2];
					szNetOfCube[2][4] = tempSide4[1];
					szNetOfCube[2][5] = tempSide4[0];
					
					
				} else if(szCommand.equals("F'"))
				{
					//Rotate the red face left
					szRedFace[0][0] = tempFace[0][2] ;
					szRedFace[1][0] = tempFace[0][1] ;
					szRedFace[2][0] = tempFace[0][0] ;
					szRedFace[2][1] = tempFace[1][0] ;
					szRedFace[2][2] = tempFace[2][0] ;
					szRedFace[1][2] = tempFace[2][1] ;
					szRedFace[0][2] = tempFace[2][2] ;
					szRedFace[0][1] = tempFace[1][2] ;
					szRedFace[1][1] = "R" ;
					
					//Rotate the edges
						//Yellow --> Blue
					szNetOfCube[6][0] = tempSide1[2];
					szNetOfCube[6][1] = tempSide1[1];
					szNetOfCube[6][2] = tempSide1[0];
						//Blue --> White
					szNetOfCube[6][3] = tempSide4[0];
					szNetOfCube[6][4] = tempSide4[1];
					szNetOfCube[6][5] = tempSide4[2];
						//White --> Green
					szNetOfCube[6][6] = tempSide3[0];
					szNetOfCube[6][7] = tempSide3[1];
					szNetOfCube[6][8] = tempSide3[2];
						//Green --> Yellow
					szNetOfCube[2][3] = tempSide2[2];
					szNetOfCube[2][4] = tempSide2[1];
					szNetOfCube[2][5] = tempSide2[0];
					
					
					
				}
				
				for(int y = 0 ; y < 12 ; y++)
				{
					for(int x = 0 ; x < 9 ; x++)
					{
						if( (x == 3 && y == 3) || (x == 4 && y == 3) || (x == 5 && y == 3)
						 || (x == 3 && y == 4) || (x == 4 && y == 4) || (x == 5 && y == 4)
						 || (x == 3 && y == 5) || (x == 4 && y == 5) || (x == 5 && y == 5))
						{
							szNetOfCube[y][x] = szRedFace[y-3][x-3] ;
						}
					}
				}
				
				for(int y = 0 ; y < 12 ; y++)
				{
					for(int x = 0 ; x < 9 ; x++)
					{
						Cube.sendNetToCube(szNetOfCube[y][x], y, x);
					}
				}
			
			}
		}
		
		
		
//		System.out.println("RED FACE " + szCommand + "," + iNumTimes);
//		for(int i = 0 ; i < 12 ; i++)
//		{
//			for(int j = 0 ; j < 9 ; j++)
//			{
//				System.out.print(szNetOfCube[i][j]);
//			}
//			System.out.print("\n");
//		}
		
//		display() ;
//		System.out.println("\n");
	}
	
	public void moveOrangeFace(String szCommand, int iNumTimes)
	{
		String szOrangeFace[][] = new String[3][3] ;
		
		if(!(szCommand.equals("B") || szCommand.equals("B'")))
		{
			System.out.println("You have entered in the value " + szCommand + " which is not an allowed movement"
					+ " for this face.");
		} else
		{
			for(int m = 0 ; m < iNumTimes ; m++)
			{
				//isolate the orange face and store it in both a orange face array and a temporary array
				//Orange Face Diagram from Net

				//  |( 9,3)|( 9,4) |( 9,5)|
				//	|(10,3)|(10,4)|(10,5)|
				//	|(11,3)|(11,4)|(11,5)|

				//Orange Face Diagram GENERAL

				//  |(0,0)|(1,0)|(2,0)|
				//	|(0,1)|(1,1)|(2,1)|
				//	|(0,2)|(1,2)|(2,2)|
				
				for(int y = 0 ; y < 12 ; y++)
				{
					for(int x = 0 ; x < 9 ; x++)
					{
						if( (x == 3 && y == 9) || (x == 4 && y == 9) || (x == 5 && y == 9)
						 || (x == 3 && y ==10) || (x == 4 && y ==10) || (x == 5 && y ==10)
						 || (x == 3 && y ==11) || (x == 4 && y ==11) || (x == 5 && y ==11))
						{
							szOrangeFace[y-9][x-3] = szNetOfCube[y][x] ;
						}
					}
				}
				
				//Store in the temp face
				for(int i = 0 ; i < 3 ; i++)
				{
					for(int j = 0 ; j < 3 ; j++)
					{
						tempFace[i][j] = szOrangeFace[i][j] ;
					}
				}
				
				//Store the temp sides
					//ORANGE YELLOW
				tempSide1[0] = szNetOfCube[0][3] ;
				tempSide1[1] = szNetOfCube[0][4] ;
				tempSide1[2] = szNetOfCube[0][5] ;
					//ORANGE GREEN
				tempSide2[0] = szNetOfCube[8][6] ;
				tempSide2[1] = szNetOfCube[8][7] ;
				tempSide2[2] = szNetOfCube[8][8] ;
					//ORANGE WHITE
				tempSide3[0] = szNetOfCube[8][3] ;
				tempSide3[1] = szNetOfCube[8][4] ;
				tempSide3[2] = szNetOfCube[8][5] ;
					//ORANGE BLUE
				tempSide4[0] = szNetOfCube[8][0] ;
				tempSide4[1] = szNetOfCube[8][1] ;
				tempSide4[2] = szNetOfCube[8][2] ;
				
				if(szCommand.equals("B'"))
				{
					//Move the orange face left
					
					//Orange Face Diagram GENERAL

					//  |(0,0)|(1,0)|(2,0)|
					//	|(0,1)|(1,1)|(2,1)|
					//	|(0,2)|(1,2)|(2,2)|
					
					szOrangeFace[0][0] = tempFace[0][2] ;
					szOrangeFace[1][0] = tempFace[0][1] ;
					szOrangeFace[2][0] = tempFace[0][0] ;
					szOrangeFace[2][1] = tempFace[1][0] ;
					szOrangeFace[2][2] = tempFace[2][0] ;
					szOrangeFace[1][2] = tempFace[2][1] ;
					szOrangeFace[0][2] = tempFace[2][2] ;
					szOrangeFace[0][1] = tempFace[1][2] ;
					
					//Move the temporary sides
					//Yellow --> Green
					szNetOfCube[8][6] = tempSide1[2];
					szNetOfCube[8][7] = tempSide1[1];
					szNetOfCube[8][8] = tempSide1[0];
					//Green --> White
					szNetOfCube[8][3] = tempSide2[0];
					szNetOfCube[8][4] = tempSide2[1];
					szNetOfCube[8][5] = tempSide2[2];
					//White --> Blue
					szNetOfCube[8][0] = tempSide3[0];
					szNetOfCube[8][1] = tempSide3[1];
					szNetOfCube[8][2] = tempSide3[2];
					//Blue --> Yellow
					szNetOfCube[0][3] = tempSide4[2];
					szNetOfCube[0][4] = tempSide4[1];
					szNetOfCube[0][5] = tempSide4[0];
					
				} else if(szCommand.equals("B"))
				{
					//  |(0,0)|(1,0)|(2,0)|
					//	|(0,1)|(1,1)|(2,1)|
					//	|(0,2)|(1,2)|(2,2)|
					
					//Move the orange face right
					szOrangeFace[0][0] = tempFace[2][0] ;
					szOrangeFace[1][0] = tempFace[2][1] ;
					szOrangeFace[2][0] = tempFace[2][2] ;
					szOrangeFace[2][1] = tempFace[1][2] ;
					szOrangeFace[2][2] = tempFace[0][2] ;
					szOrangeFace[1][2] = tempFace[0][1] ;
					szOrangeFace[0][2] = tempFace[0][0] ;
					szOrangeFace[0][1] = tempFace[1][0] ;
					
					//Move the sides
					//Yellow --> Blue
					szNetOfCube[8][0] = tempSide1[2] ;
					szNetOfCube[8][1] = tempSide1[1] ;
					szNetOfCube[8][2] = tempSide1[0] ;
					//Blue --> White
					szNetOfCube[8][3] = tempSide4[0] ;
					szNetOfCube[8][4] = tempSide4[1] ;
					szNetOfCube[8][5] = tempSide4[2] ;
					//White --> Green
					szNetOfCube[8][6] = tempSide3[0] ;
					szNetOfCube[8][7] = tempSide3[1] ;
					szNetOfCube[8][8] = tempSide3[2] ;
					//Green --> Yellow
					szNetOfCube[0][3] = tempSide2[2] ;
					szNetOfCube[0][4] = tempSide2[1] ;
					szNetOfCube[0][5] = tempSide2[0] ;
	 
				}
				
				for(int y = 0 ; y < 12 ; y++)
				{
					for(int x = 0 ; x < 9 ; x++)
					{
						if( (x == 3 && y == 9) || (x == 4 && y == 9) || (x == 5 && y == 9)
						 || (x == 3 && y ==10) || (x == 4 && y ==10) || (x == 5 && y ==10)
						 || (x == 3 && y ==11) || (x == 4 && y ==11) || (x == 5 && y ==11))
						{
							szNetOfCube[y][x] = szOrangeFace[y-9][x-3];
						}
					}
				}
				
				for(int y = 0 ; y < 12 ; y++)
				{
					for(int x = 0 ; x < 9 ; x++)
					{
						Cube.sendNetToCube(szNetOfCube[y][x], y, x);
					}
				}

			}
		}
		
		
		
//		System.out.println("ORANGE FACE " + szCommand + "," + iNumTimes);
//		for(int i = 0 ; i < 12 ; i++)
//		{
//			for(int j = 0 ; j < 9 ; j++)
//			{
//				System.out.print(szNetOfCube[i][j]);
//			}
//			System.out.print("\n");
//		}
//		
//		display() ;
//		System.out.println("\n");
	}
	
	public void moveGreenFace(String szCommand, int iNumTimes)
	{
		String szGreenFace[][] = new String[3][3] ;
		
		if(!(szCommand.equals("R") || szCommand.equals("R'")))
		{
			System.out.println("You have entered in the value " + szCommand + " which is not an allowed movement"
					+ " for this face.");
		} else
		{
			for(int m = 0 ; m < iNumTimes ; m++)
			{
				//isolate the green face and store it in both a green face array and a temporary array
				//Green Face Diagram from Net

				//  |(6,6)|(6,7)|(6,8)|
				//	|(7,6)|(7,7)|(7,8)|
				//	|(8,6)|(8,7)|(8,8)|

				//Green Face Diagram GENERAL

				//  |(0,0)|(1,0)|(2,0)|
				//	|(0,1)|(1,1)|(2,1)|
				//	|(0,2)|(1,2)|(2,2)|
				
				for(int y = 0 ; y < 12 ; y++)
				{
					for(int x = 0 ; x < 9 ; x++)
					{
						if( (x == 6 && y == 6) || (x == 7 && y == 6) || (x == 8 && y == 6)
						 || (x == 6 && y == 7) || (x == 7 && y == 7) || (x == 8 && y == 7)
						 || (x == 6 && y == 8) || (x == 7 && y == 8) || (x == 8 && y == 8))
						{
							szGreenFace[y-6][x-6] = szNetOfCube[y][x] ;
						}
					}
				}
				
				//Store in the temp face
				for(int i = 0 ; i < 3 ; i++)
				{
					for(int j = 0 ; j < 3 ; j++)
					{
						tempFace[i][j] = szGreenFace[i][j] ;
					}
				}
				
				//Store the sides affected in the tempSide variables.  Affected sides are Y R W O
				//GREEN YELLOW
				tempSide1[0] = szNetOfCube[0][5];
				tempSide1[1] = szNetOfCube[1][5] ;
				tempSide1[2] = szNetOfCube[2][5] ;
				//GREEN RED
				tempSide2[0] = szNetOfCube[3][5];
				tempSide2[1] = szNetOfCube[4][5];
				tempSide2[2] = szNetOfCube[5][5];
				//GREEN WHITE
				tempSide3[0] = szNetOfCube[6][5];
				tempSide3[1] = szNetOfCube[7][5];
				tempSide3[2] = szNetOfCube[8][5];
				//GREEN ORANGE
				tempSide4[0] = szNetOfCube [9][5];
				tempSide4[1] = szNetOfCube[10][5];
				tempSide4[2] = szNetOfCube[11][5];
				
				if(szCommand.equals("R'")) //RIGHT PRIME down
				{
					//Rotate the green face left
					//Green Face Diagram GENERAL

					//  |(0,0)|(1,0)|(2,0)|
					//	|(0,1)|(1,1)|(2,1)|
					//	|(0,2)|(1,2)|(2,2)|
					
					szGreenFace[0][0] = tempFace[0][2] ;
					szGreenFace[1][0] = tempFace[0][1] ;
					szGreenFace[2][0] = tempFace[0][0] ;
					szGreenFace[2][1] = tempFace[1][0] ;
					szGreenFace[2][2] = tempFace[2][0] ;
					szGreenFace[1][2] = tempFace[2][1] ;
					szGreenFace[0][2] = tempFace[2][2] ;
					szGreenFace[0][1] = tempFace[1][2] ;
					
					
					//Rotate around the sides
					//Yellow --> Red
					szNetOfCube[3][5] = tempSide1[0] ;
					szNetOfCube[4][5] = tempSide1[1] ;
					szNetOfCube[5][5] = tempSide1[2] ;
					//Red --> White
					szNetOfCube[6][5] = tempSide2[0] ;
					szNetOfCube[7][5] = tempSide2[1] ;
					szNetOfCube[8][5] = tempSide2[2] ;
					//White --> Orange
					szNetOfCube [9][5] = tempSide3[0] ;
					szNetOfCube[10][5] = tempSide3[1] ;
					szNetOfCube[11][5] = tempSide3[2] ;
					//Orange --> Yellow
					szNetOfCube[0][5] = tempSide4[0] ;
					szNetOfCube[1][5] = tempSide4[1] ;
					szNetOfCube[2][5] = tempSide4[2] ;
					
					
				} else if(szCommand.equals("R")) //RIGHT up
				{
					//Rotate the green face right
					//Green Face Diagram GENERAL

					//  |(0,0)|(1,0)|(2,0)|
					//	|(0,1)|(1,1)|(2,1)|
					//	|(0,2)|(1,2)|(2,2)|
					szGreenFace[0][0] = tempFace[2][0] ;
					szGreenFace[1][0] = tempFace[2][1] ;
					szGreenFace[2][0] = tempFace[2][2] ;
					szGreenFace[2][1] = tempFace[1][2] ;
					szGreenFace[2][2] = tempFace[0][2] ;
					szGreenFace[1][2] = tempFace[0][1] ;
					szGreenFace[0][2] = tempFace[0][0] ;
					szGreenFace[0][1] = tempFace[1][0] ;
					
					//Yellow --> Orange
					szNetOfCube [9][5] = tempSide1[0] ;
					szNetOfCube[10][5] = tempSide1[1] ;
					szNetOfCube[11][5] = tempSide1[2] ;
					//Orange --> White
					szNetOfCube[6][5] = tempSide4[0] ;
					szNetOfCube[7][5] = tempSide4[1] ;
					szNetOfCube[8][5] = tempSide4[2] ;
					//White --> Red
					szNetOfCube[3][5] = tempSide3[0] ;
					szNetOfCube[4][5] = tempSide3[1] ;
					szNetOfCube[5][5] = tempSide3[2] ;
					//Red --> Yellow
					szNetOfCube[0][5] = tempSide2[0] ;
					szNetOfCube[1][5] = tempSide2[1] ;
					szNetOfCube[2][5] = tempSide2[2] ;
				}
				
				for(int y = 0 ; y < 12 ; y++)
				{
					for(int x = 0 ; x < 9 ; x++)
					{
						if( (x == 6 && y == 6) || (x == 7 && y == 6) || (x == 8 && y == 6)
						 || (x == 6 && y == 7) || (x == 7 && y == 7) || (x == 8 && y == 7)
						 || (x == 6 && y == 8) || (x == 7 && y == 8) || (x == 8 && y == 8))
						{
							szNetOfCube[y][x] = szGreenFace[y-6][x-6];
						}
					}
				}
				
				for(int y = 0 ; y < 12 ; y++)
				{
					for(int x = 0 ; x < 9 ; x++)
					{
						Cube.sendNetToCube(szNetOfCube[y][x], y, x);
					}
				}
				
			}
		}
		
		
		
//		System.out.println("GREEN FACE " + szCommand + "," + iNumTimes);
//		for(int i = 0 ; i < 12 ; i++)
//		{
//			for(int j = 0 ; j < 9 ; j++)
//			{
//				System.out.print(szNetOfCube[i][j]);
//			}
//			System.out.print("\n");
//		}
		
//		display() ;
//		System.out.println("\n");
	}
	
	public void moveBlueFace(String szCommand , int iNumTimes)
	{
		String szBlueFace[][] = new String[3][3] ;
		
		if(!(szCommand.equals("L") || szCommand.equals("L'")))
		{
			System.out.println("You have entered in the value " + szCommand + " which is not an allowed movement"
					+ " for this face.");
		} else
		{
			for(int m = 0 ; m < iNumTimes ; m++)
			{
				//isolate the blue face and store it in both a blue face array and a temporary array
				//Blue Face Diagram from Net

				//  |(6,0)|(6,1)|(6,2)|
				//	|(7,0)|(7,1)|(7,2)|
				//	|(8,0)|(8,1)|(8,2)|

				//Blue Face Diagram GENERAL

				//  |(0,0)|(1,0)|(2,0)|
				//	|(0,1)|(1,1)|(2,1)|
				//	|(0,2)|(1,2)|(2,2)|
				
				for(int y = 0 ; y < 12 ; y++)
				{
					for(int x = 0 ; x < 9 ; x++)
					{
						if( (x == 0 && y == 6) || (x == 1 && y == 6) || (x == 2 && y == 6)
						 || (x == 0 && y == 7) || (x == 1 && y == 7) || (x == 2 && y == 7)
						 || (x == 0 && y == 8) || (x == 1 && y == 8) || (x == 2 && y == 8))
						{
							szBlueFace[y-6][x] = szNetOfCube[y][x] ;
						}
					}
				}
				
				//Store in the temp face
				for(int i = 0 ; i < 3 ; i++)
				{
					for(int j = 0 ; j < 3 ; j++)
					{
						tempFace[i][j] = szBlueFace[i][j] ;
					}
				}
				
				//Store the sides affected in the tempSide variables.  Affected sides are Y R W O
				//BLUE YELLOW
				tempSide1[0] = szNetOfCube[0][3];
				tempSide1[1] = szNetOfCube[1][3];
				tempSide1[2] = szNetOfCube[2][3];
				//BLUE RED
				tempSide2[0] = szNetOfCube[3][3];
				tempSide2[1] = szNetOfCube[4][3];
				tempSide2[2] = szNetOfCube[5][3];
				//BLUE WHITE
				tempSide3[0] = szNetOfCube[6][3];
				tempSide3[1] = szNetOfCube[7][3];
				tempSide3[2] = szNetOfCube[8][3];
				//BLUE ORANGE
				tempSide4[0] = szNetOfCube [9][3];
				tempSide4[1] = szNetOfCube[10][3];
				tempSide4[2] = szNetOfCube[11][3];
				
				if(szCommand.equals("L")) //LEFT down
				{
					//rotate the face to the right
					//Blue Face Diagram GENERAL

					//  |(0,0)|(1,0)|(2,0)|
					//	|(0,1)|(1,1)|(2,1)|
					//	|(0,2)|(1,2)|(2,2)|
					
					szBlueFace[0][0] = tempFace[2][0] ;
					szBlueFace[1][0] = tempFace[2][1] ;
					szBlueFace[2][0] = tempFace[2][2] ;
					szBlueFace[2][1] = tempFace[1][2] ;
					szBlueFace[2][2] = tempFace[0][2] ;
					szBlueFace[1][2] = tempFace[0][1] ;
					szBlueFace[0][2] = tempFace[0][0] ;
					szBlueFace[0][1] = tempFace[1][0] ;
					
					//Yellow --> Red
					szNetOfCube[3][3] = tempSide1[0];
					szNetOfCube[4][3] = tempSide1[1];
					szNetOfCube[5][3] = tempSide1[2];
					//Red --> White
					szNetOfCube[6][3] = tempSide2[0];
					szNetOfCube[7][3] = tempSide2[1];
					szNetOfCube[8][3] = tempSide2[2];
					//White --> Orange
					szNetOfCube [9][3] = tempSide3[0];
					szNetOfCube[10][3] = tempSide3[1];
					szNetOfCube[11][3] = tempSide3[2];
					//Orange --> Yellow
					szNetOfCube[0][3] = tempSide4[0];
					szNetOfCube[1][3] = tempSide4[1];
					szNetOfCube[2][3] = tempSide4[2];
					
				} else if(szCommand.equals("L'")) //LEFT PRIME up
				{
					//rotate the face to the left
					//Blue Face Diagram GENERAL

					//  |(0,0)|(1,0)|(2,0)|
					//	|(0,1)|(1,1)|(2,1)|
					//	|(0,2)|(1,2)|(2,2)|

					szBlueFace[0][0] = tempFace[0][2] ;
					szBlueFace[1][0] = tempFace[0][1] ;
					szBlueFace[2][0] = tempFace[0][0] ;
					szBlueFace[2][1] = tempFace[1][0] ;
					szBlueFace[2][2] = tempFace[2][0] ;
					szBlueFace[1][2] = tempFace[2][1] ;
					szBlueFace[0][2] = tempFace[2][2] ;
					szBlueFace[0][1] = tempFace[1][2] ;
					
					//Yellow --> Orange
					szNetOfCube [9][3] = tempSide1[0];
					szNetOfCube[10][3] = tempSide1[1];
					szNetOfCube[11][3] = tempSide1[2];
					//Orange --> White
					szNetOfCube[6][3] = tempSide4[0];
					szNetOfCube[7][3] = tempSide4[1];
					szNetOfCube[8][3] = tempSide4[2];
					//White --> Red
					szNetOfCube[3][3] = tempSide3[0];
					szNetOfCube[4][3] = tempSide3[1];
					szNetOfCube[5][3] = tempSide3[2];
					//Red --> Yellow
					szNetOfCube[0][3] = tempSide2[0];
					szNetOfCube[1][3] = tempSide2[1];
					szNetOfCube[2][3] = tempSide2[2];
				}
			
				for(int y = 0 ; y < 12 ; y++)
				{
					for(int x = 0 ; x < 9 ; x++)
					{
						if( (x == 2 && y == 6) || (x == 1 && y == 6) || (x == 0 && y == 6)
						 || (x == 2 && y == 7) || (x == 1 && y == 7) || (x == 0 && y == 7)
						 || (x == 2 && y == 8) || (x == 1 && y == 8) || (x == 0 && y == 8))
						{
							szNetOfCube[y][x] = szBlueFace[y-6][x];
						}
					}
				}
				
				for(int y = 0 ; y < 12 ; y++)
				{
					for(int x = 0 ; x < 9 ; x++)
					{
						Cube.sendNetToCube(szNetOfCube[y][x], y, x);
					}
				}
				
			}
		}
		
		
		
//		System.out.println("BLUE FACE " + szCommand + "," + iNumTimes);
//		for(int i = 0 ; i < 12 ; i++)
//		{
//			for(int j = 0 ; j < 9 ; j++)
//			{
//				System.out.print(szNetOfCube[i][j]);
//			}
//			System.out.print("\n");
//		}
//		display();
//		System.out.println("\n");
	}
	
	public void moveMVert(String szCommand , int iNumTimes)
	{
		String tempMiddle[] = new String[3] ;
		
		if(!(szCommand.equals("M") || szCommand.equals("M'")))
		{
			System.out.println("You have entered in the value " + szCommand + " which is not an allowed movement"
					+ " for this face.");
		} else
		{
			
		}
		
		for(int i = 0 ; i < iNumTimes ; i++)
		{
			//this will move the vertical middle layer
			if(szCommand.equals("M"))
			{
				//move the middle layer upwards
				tempMiddle[0] = szNetOfCube[0][4];
				tempMiddle[1] = szNetOfCube[1][4];
				tempMiddle[2] = szNetOfCube[2][4];
				
				szNetOfCube[0][4] = szNetOfCube[3][4];
				szNetOfCube[1][4] = szNetOfCube[4][4];
				szNetOfCube[2][4] = szNetOfCube[5][4];
				szNetOfCube[3][4] = szNetOfCube[6][4];
				szNetOfCube[4][4] = szNetOfCube[7][4];
				szNetOfCube[5][4] = szNetOfCube[8][4];
				szNetOfCube[6][4] = szNetOfCube[9][4];
				szNetOfCube[7][4] = szNetOfCube[10][4];
				szNetOfCube[8][4] = szNetOfCube[11][4];
				szNetOfCube[9][4] = tempMiddle[0];
				szNetOfCube[10][4] = tempMiddle[1];
				szNetOfCube[11][4] = tempMiddle[2];
				
			} else if(szCommand.equals("M'"))
			{
				//move the middle layer upwards
				tempMiddle[0] = szNetOfCube[0][4];
				tempMiddle[1] = szNetOfCube[1][4];
				tempMiddle[2] = szNetOfCube[2][4];
				
				szNetOfCube[0][4] = szNetOfCube[9][4];
				szNetOfCube[1][4] = szNetOfCube[10][4];
				szNetOfCube[2][4] = szNetOfCube[11][4];
				szNetOfCube[9][4] =  szNetOfCube[6][4];
				szNetOfCube[10][4] = szNetOfCube[7][4];
				szNetOfCube[11][4] = szNetOfCube[8][4];
				szNetOfCube[6][4] = szNetOfCube[3][4];
				szNetOfCube[7][4] = szNetOfCube[4][4];
				szNetOfCube[8][4] = szNetOfCube[5][4];
				szNetOfCube[3][4] = tempMiddle[0];
				szNetOfCube[4][4] = tempMiddle[1];
				szNetOfCube[5][4] = tempMiddle[2];
			}
			
			for(int y = 0 ; y < 12 ; y++)
			{
				for(int x = 0 ; x < 9 ; x++)
				{
					Cube.sendNetToCube(szNetOfCube[y][x], y, x);
				}
			}
			
		}
		
//		display() ;
//		System.out.println("\n");
		
		
	}
	
	public void getNetFromSolve(String string, int i , int j)
	{
		szNetOfCube[i][j] = string ;
	}
	
	
	//=======================================Moving the array around and displaying=======================
	public void sendCurrentNetToCube()
	{
		for(int j = 0 ; j < 12 ; j++)
		{
			for(int i = 0 ; i < 9 ; i++)
			{
				Cube.sendNetToCube(szNetOfCube[j][i], j, i);
			}
		}
	}
	
	public String sendNetToSolve(int i , int j)
	{
		return szNetOfCube[i][j] ;
	}
	
	public void display()
	{
		Cube.displayNet();
	}

	public String getNet(int i, int j)
	{
		return szNetOfCube[i][j] ;
	}
	
	//=======================================Multiple use algorithms======================================
	
	public void yellowRepeatAlg()
	{
	
		moveRedFace("F", 1);
		moveYellowFace("U", 1);
		moveGreenFace("R", 1);
		moveYellowFace("U'", 1);
		moveGreenFace("R'", 1);
		moveRedFace("F'", 1);
		
		System.out.println("F U R U' R' F'");
	}
	
	public void yellowLine()
	{
		yellowRepeatAlg() ;
	}
	
	public void yellowL()
	{
		yellowRepeatAlg() ;
	}
	
	public void yellowNone()
	{
		
		yellowRepeatAlg() ;
		
		moveYellowFace("U", 1);
		
		System.out.println("U1");
	}
	
	public void flippingAlg()
	{
		moveYellowFace("U",1);
		moveGreenFace("R",1);
		moveYellowFace("U'",1);
		moveGreenFace("R'",1);
		moveYellowFace("U'", 1);
		moveRedFace("F'", 1);
		moveYellowFace("U", 1);
		moveRedFace("F", 1);
		moveGreenFace("R", 1);
		moveYellowFace("U'", 1);
		moveGreenFace("R'", 1);
		moveYellowFace("U'", 1);
		moveRedFace("F'" , 1);
		moveYellowFace("U", 1);
		moveRedFace("F", 1);

		System.out.println("U1 , R1 , U'1 , R'1 , U'1 , F'1 , U1 , F1 , R1 , U'1 , R'1 , U'1 , F'1 , U1 , F1");
	}

	public void yellowCornerAlg()
	{
		moveGreenFace("R'", 1);
		moveRedFace("F", 1);
		moveGreenFace("R'", 1);
		moveOrangeFace("B", 2);
		moveGreenFace("R", 1);
		moveRedFace("F'", 1);
		moveGreenFace("R'", 1);
		moveOrangeFace("B", 2);
		moveGreenFace("R", 2);
		
		System.out.println("R' F R' B2 R F' R' B2 R2");
	}

	public void UPerm()
	{
		moveGreenFace("R", 2);
		moveYellowFace("U", 1);
		moveGreenFace("R", 1);
		moveYellowFace("U", 1);
		moveGreenFace("R'", 1);
		moveYellowFace("U'", 1);
		moveGreenFace("R'", 1);
		moveYellowFace("U'", 1);
		moveGreenFace("R'", 1);
		moveYellowFace("U", 1);
		moveGreenFace("R'", 1);
		
		System.out.println("R2 U R U R' U' R' U' R' U R'");
	}

	public void diagonalAlg()
	{
		
		moveMVert("M'", 2);
		moveYellowFace("U'", 1);
		moveMVert("M'", 2);
		moveYellowFace("U'", 1);
		moveMVert("M", 1);
		moveYellowFace("U", 2);
		moveMVert("M", 2);
		moveYellowFace("U", 2);
		moveMVert("M", 1);
		moveYellowFace("U", 2);
		
		
		System.out.println("M'2 , U' , M'2 , U' , M , U2 , M2 , U2 , M , U2");
		
		//M'2 U' M'2 U' M U2 M2 U2 M2 U2
	}
	
	public void oppositeAlg()
	{
		moveMVert("M", 2);
		moveYellowFace("U'", 1);
		moveMVert("M", 2);
		moveYellowFace("U'", 2);
		moveMVert("M", 2);
		moveYellowFace("U'", 1);
		moveMVert("M", 2);
		
		System.out.println("M2 , U' , M2 , U2 , M2 , U' , M2");
	}
	
	public static void main(String[] args) {


	}

}
