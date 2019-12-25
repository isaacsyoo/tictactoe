# Tic-Tac-Toe (3x3)

This 3x3 tic-tac-toe game is created through Java.
This game may be played with 1 player (user vs CP), OR 2 players (user vs user).
The game results in a victory, a tie, or an invalid-entry termination of the program.
When playing against CP, the CP's coordinates are displayed in addition to the updated tic-tac-toe board.

Game Rules:
*  (1) The upper left coordinate is (1, 1). The coordinates run along the x-y axis.
*  (2) Enter your coordinates with no space. [EX: (2,3) would be: 23]
*  (3) Only enter values from 1 to 3, as this is the range of the 3x3 tic-tac-toe board.
*  (4) To terminate the game at any time, enter \"exit\".

## Prerequisites

This game is played on terminal.
A text editor and command prompt is sufficient.

## Steps to Play

* (1) Run code.
* (2) Enter number of players (1 or 2).
* (3) Enter coordinate of desired position, per rules listed above.
* (4) If playing in 2-player mode, repeat step 3 for second player. If playing in 1-player mode, CP will play the next step.
* *Note: Game will terminate if invalid entries are inputted. 

## Methods
* *ticTacToe()* - contains game code, with 1-player & 2-player cases
* *printBoard()* - prints the contents of the board
* *result()* - determines if a victory has occurred or not
* *tie()* - determines if a tie has occurred or not (a tie is defined as all coordinates being filled up with no victory)
* *randomInput()* - randomized coordinate is chosen for CP, and determines if a victory/tie has occurred or not
* *testCases()* - defined below

## Test Cases

TicTacToe.java contains a series of test cases at the end of the file. 
These test cases ensure that the result() method is implemented correctly, which determines if there is a game victory or not.

## Built With

* Java
* Sublime Text 3

## Authors

* **Isaac Yoo** - [LinkedIn](https://www.linkedin.com/in/isaacsyoo/)

## Date

December 2019
