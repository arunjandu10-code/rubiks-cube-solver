# Rubik's Cube Solver
 
A Java-based Rubik's Cube solver that models a 3x3 cube, processes user-defined cube states and generates a sequence of moves for solving the cube.
 
The project was originally developed as my A-Level Computer Science NEA and was one of my first substantial object-oriented programming projects. I have preserved it as part of my software engineering portfolio because it demonstrates the process of translating a physical puzzle into a programmatic representation and implementing the logic required to manipulate and solve it.
 
## Features
 
- Models a 3x3 Rubik's Cube programmatically
- Accepts cube colour states as input
- Represents the cube as a two-dimensional net
- Implements individual Rubik's Cube face rotations
- Identifies and manipulates cube pieces during the solving process
- Generates solving algorithms as sequences of standard cube moves
- Includes a simple Java Swing interface explaining cube notation
- Supports predefined cube-state data for testing
 
## Project Structure
 
```text
rubiks-cube-solver/
├── src/
│ ├── Cube.java
│ ├── Move.java
│ ├── NotationGUI.java
│ ├── RubixCubeSolver.java
│ ├── Solve.java
│ └── solver.java
├──PreSetNet.csv
├──PreSetNet.txt
├── .gitignore
└── README.md
