/* PipePuzzle class is the main class of the project. This class is responsible for setting the main structure of the level.
   PipePuzzle class is responsible for taking the information correctly from the input file and matches the datas of the tiles
   with checks and controls. Later on creates a ball of Circle type. */

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PipePuzzle extends Application {
	private Text nmbOfMoves; //The text field that shows the number of moves.
	private boolean levelCompleted = true; //boolean value indicates whether the level is completed or not.
	private Tile[][] tiles = new Tile[4][4]; //Array of Tile that keeps the information of the GridPane cell by cell.
	public static int startRow; //This variable keeps the row of the starter tile.
	public static int startCol; //This variable keeps the column of the starter tile.
	public static int move; //This variable keeps the number of moves
	public static int levelNumber = 1; //This variable keeps the number of the level
	public static Circle ball; //This variable keeps the ball, created as a Circle object.
	
	//Constructor
	public PipePuzzle() {
	}
	
	public static void main(String[] args) {
		launch(args); //Launch application
	}
	
	/* start method: Creates the pane and displays the essential figures of the level such as tiles, number of moves, ball;
	 sets the level based on the input file.
	 */
	public void start(Stage stage) throws Exception {	
		//Adjust the GridPane
		GridPane pane = new GridPane();
		pane.setStyle("-fx-background-color: BLACK;");
		ball = createBall();	
		//Adjust the text which will show the number of moves
		nmbOfMoves = new Text(PipePuzzle.move + "");
		nmbOfMoves.setFont(Font.font("Corrier", FontWeight.BOLD, 13));
		nmbOfMoves.setFill(Color.WHITE);
		Text nmbOfMovesTxt = new Text("Number of Moves:"); //The text field that shows "Number of Moves: " text.
		nmbOfMovesTxt.setFont(Font.font("Corrier", FontWeight.BOLD, 11));
		nmbOfMovesTxt.setFill(Color.WHITE);
		pane.add(nmbOfMoves, 3, 4);
		pane.add(nmbOfMovesTxt, 2, 4);
		stage.setResizable(false);
		//The while loop is used to show the levels in order, until there's no level left.
		while (levelCompleted) {
			this.levelCompleted = false;
			try {
				File inputFile = new File("level" + levelNumber + ".txt"); 
				Scanner input = new Scanner(inputFile);
				//The while loop is used to for an Array of String each time to make the input file readable by 'addPane' method.
				while (input.hasNext()) {
					String inputLine = input.nextLine(); 
					if (!inputLine.equals("")) { //Skip the empty lines.
						String[] lineParts = inputLine.split(",");	
						pane = readInputFile(lineParts, pane); 
					}	
				}
			} catch (FileNotFoundException e) { //If there's no more input file left, close the game and exit system.
				stage.close();
				System.exit(1);
			}
		}
		new CheckSwap(pane, tiles, stage, nmbOfMoves).getRowCol(); //Invoke 'getRowCol' method of 'CheckSwap' class.
		//Set scene and stage.
		Scene scene = new Scene(pane,416,435);
		stage.setScene(scene);
		stage.setTitle("PipePuzzle");
		stage.show(); 
	}
	
	//readInputFile method: This method reads every line of the input word by word and assigns associated tile.
	public GridPane readInputFile(String[] lineParts, GridPane pane) {
		int number = Integer.parseInt(lineParts[0]); //Takes the first number of each line.
		int row = (number - 1) / 4; //Finds which row to place the tile based on the number. 
		int column; //Finds which column to place the tile based on the number.
		if (number % 4 == 0)
			column = 3;
		else
			column = number % 4 - 1;
		//Add the related tiles to GridPane and tiles Array.
		if (lineParts[1].equalsIgnoreCase("Starter")) {
			if (lineParts[2].equalsIgnoreCase("Vertical")) {
				tiles[row][column] = new Tile("StarterV", false, true, false ,false, false, row, column, pane); //Sets the id and available directions of the tile.
			} 
			else if(lineParts[2].equalsIgnoreCase("Horizontal")) {
				tiles[row][column] = new Tile("StarterH", false, false, false, true, false, row, column, pane); //Sets the id and available directions of the tile.
			}
		} 
		else if (lineParts[1].equalsIgnoreCase("Empty")) {
			if (lineParts[2].equalsIgnoreCase("none")) {
				tiles[row][column] = new Tile("EmptyNone", false, false, false, false, true, row, column, pane); //Sets the id and available directions of the tile.
			} 
			else if (lineParts[2].equalsIgnoreCase("Free")) {
				tiles[row][column] = new Tile("EmptyFree", false, false, false, false, false, row, column, pane); //Sets the id and available directions of the tile.
			}
		} 
		else if (lineParts[1].equalsIgnoreCase("Pipe")) {
			if (lineParts[2].equalsIgnoreCase("Vertical")) {
				tiles[row][column] = new Tile("PipeV", true, true, false, false, true, row, column, pane); //Sets the id and available directions of the tile.
			} 
			else if(lineParts[2].equalsIgnoreCase("Horizontal")) {
				tiles[row][column] = new Tile("PipeH", false, false, true, true, true, row, column, pane);	//Sets the id and available directions of the tile.
			} 
			else if(lineParts[2].equalsIgnoreCase("00")) {
				tiles[row][column] = new Tile("Pipe00", true, false, false, true, true, row, column, pane); //Sets the id and available directions of the tile.
			} 
			else if (lineParts[2].equalsIgnoreCase("01")) {
				tiles[row][column] = new Tile("Pipe01", true, false, true, false, true, row, column, pane); //Sets the id and available directions of the tile.
			} 
			else if (lineParts[2].equalsIgnoreCase("10")) {
				tiles[row][column] = new Tile("Pipe10", false,true,false,true,true,row,column, pane); //Sets the id and available directions of the tile.
			} 
			else if (lineParts[2].equalsIgnoreCase("11")) {
				tiles[row][column] = new Tile("Pipe11", false, true, true, false, true, row, column, pane); //Sets the id and available directions of the tile.
			}
		} 
		else if (lineParts[1].equalsIgnoreCase("PipeStatic")) {
			if(lineParts[2].equalsIgnoreCase("Vertical")) {
				tiles[row][column] = new Tile("PipeStaticV", true, true, false, false, false, row, column, pane); //Sets the id and available directions of the tile.
			} 
			else if(lineParts[2].equalsIgnoreCase("Horizontal")){
				tiles[row][column] = new Tile("PipeStaticH", false, false, true, true, false, row, column, pane); //Sets the id and available directions of the tile.
			} 
			else if(lineParts[2].equalsIgnoreCase("00")) {
				tiles[row][column] = new Tile("PipeStatic00", true, false, false, true, false, row, column, pane); //Sets the id and available directions of the tile.
			} 
			else if(lineParts[2].equalsIgnoreCase("01")) {
				tiles[row][column] = new Tile("PipeStatic01", true, false, true, false, false, row, column, pane); //Sets the id and available directions of the tile.
			} 
			else if(lineParts[2].equalsIgnoreCase("10")) {
				tiles[row][column] = new Tile("PipeStatic10", false,true,false,true,false,row,column, pane); //Sets the id and available directions of the tile.
			} 
			else if(lineParts[2].equalsIgnoreCase("11")) {
				tiles[row][column] = new Tile("PipeStatic11", false, true, true, false, false, row, column, pane); //Sets the id and available directions of the tile.
			}
		} 
		else if (lineParts[1].equalsIgnoreCase("End")) {
			if(lineParts[2].equalsIgnoreCase("Vertical")) {
				tiles[row][column] = new Tile("EndV", false, true, false, false, false, row, column, pane); //Sets the id and available directions of the tile.
			}  
			else {
				tiles[row][column] = new Tile("EndH", false, false, false, true, false, row, column, pane); //Sets the id and available directions of the tile.
			}
		}
		return pane;
	}
	
	//createBall method: creates the ball with the radius of 20 and color of Dark Slate Blue and returns as a Circle object.
	public Circle createBall() {
		Circle ball = new Circle(20);
		ball.setStroke(Color.DARKSLATEBLUE);
		ball.setFill(Color.DARKSLATEBLUE);
		return ball;
	}
}