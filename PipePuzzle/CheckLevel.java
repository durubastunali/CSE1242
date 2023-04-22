/* Aksanur Konuk 150120049, Duru Baþtunalý 150120075
   CheckLevel class controls whether the level is completed or not by checking the tiles are forming a path from the starter tile to the end tile. */
import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CheckLevel {
	private boolean levelCompleted; //This variables indicate whether the level is complete or not.
	private ArrayList<Integer> pathRow = new ArrayList<Integer>(); //This variable keeps row value of right path.
	private ArrayList<Integer> pathCol = new ArrayList<Integer>(); //This variable keeps column value of right path.
	private Tile[][] tiles; //This variable keeps reference of Tile class of each tile on the pane.
	private Stage stage; //This variable hosts a scene and scene graph
	private GridPane pane; //This variable is pane that tiles and text nmbOfMoves is placed.
	
	//Constructors
	public CheckLevel() {
	}
	
	public CheckLevel(Tile[][] tiles, Stage stage, GridPane pane) {
		this.tiles = tiles;
		this.stage = stage;
		this.pane = pane;
	}
	
	//The control method control whether the path is complete or not. The control method is controlled every time tiles are swapped.
	public boolean control() throws Exception {
		pathRow.clear();
		pathCol.clear();
		String direction= "NoDirection"; //This variable is the indicate direction of coming path
		int currentRow = PipePuzzle.startRow; //This variable keeps row value of current tile being controlled.
		int currentCol = PipePuzzle.startCol; //This variable keeps column value of current tile being controlled.
		while (true) {
			int preCol = currentCol; //This variable keeps column value of previous tile, after changing the current tile.
			int preRow = currentRow; //This variable keeps row value of previous tile, after changing the current tile.
			if(tiles[currentRow][currentCol].getId().equalsIgnoreCase("StarterH") && currentCol-1 >= 0 && tiles[currentRow][currentCol-1].isRight()) {
				pathRow.add(currentRow); //Add row value to pathRow ArrayList
				pathCol.add(currentCol); //Add column value to pathCol ArrayList
				currentCol--; //Update coordinate of current tile
			}
			else if(tiles[currentRow][currentCol].getId().equalsIgnoreCase("StarterV") && currentRow+1 < 4 && tiles[currentRow+1][currentCol].isUp()) {
				pathRow.add(currentRow); //Add row value to pathRow ArrayList
				pathCol.add(currentCol); //Add column value to pathCol ArrayList
				currentRow ++; //Update coordinate of current tile
			}
			else if(tiles[currentRow][currentCol].getId().equalsIgnoreCase("PipeStaticH") || tiles[currentRow][currentCol].getId().equalsIgnoreCase("PipeH")) {
				if(direction.equalsIgnoreCase("left") && currentCol + 1 < 4 && tiles[currentRow][currentCol+1].isLeft()) {
					pathRow.add(currentRow); //Add row value to pathRow ArrayList
					pathCol.add(currentCol); //Add column value to pathCol ArrayList
					currentCol ++;//Update coordinate of current tile
				} 
				else if(direction.equalsIgnoreCase("right") && currentCol - 1 >= 0 && tiles[currentRow][currentCol-1].isRight()) {
					pathRow.add(currentRow); //Add row value to pathRow ArrayList
					pathCol.add(currentCol); //Add column value to pathCol ArrayList
					currentCol--; //Update coordinate of current tile
				} 
				else break;
			}
			else if (tiles[currentRow][currentCol].getId().equalsIgnoreCase("PipeStaticV") || tiles[currentRow][currentCol].getId().equalsIgnoreCase("PipeV")) {
				if(direction.equalsIgnoreCase("up") && currentRow+1 < 4 && tiles[currentRow+1][currentCol].isUp()) {
					pathRow.add(currentRow); //Add row value to pathRow ArrayList
					pathCol.add(currentCol); //Add column value to pathCol ArrayList
					currentRow ++; //Update coordinate of current tile
				} 
				else if(direction.equalsIgnoreCase("down") && currentRow - 1 >= 0 && tiles[currentRow-1][currentCol].isDown()) {
					pathRow.add(currentRow); //Add row value to pathRow ArrayList
					pathCol.add(currentCol); //Add column value to pathCol ArrayList
					currentRow--; //Update coordinate of current tile
				} 
				else break;
			} 
			else if(tiles[currentRow][currentCol].getId().equalsIgnoreCase("Pipe00") || tiles[currentRow][currentCol].getId().equalsIgnoreCase("PipeStatic00")) {
				if(direction.equalsIgnoreCase("left") && currentRow - 1 >= 0 && tiles[currentRow-1][currentCol].isDown()) {
					pathRow.add(currentRow); //Add row value to pathRow ArrayList
					pathCol.add(currentCol); //Add column value to pathCol ArrayList
					currentRow--;	//Update coordinate of current tile
				} 
				else if(direction.equalsIgnoreCase("up") && currentCol - 1 >= 0 && tiles[currentRow][currentCol-1].isRight()) {
					pathRow.add(currentRow); //Add row value to pathRow ArrayList
					pathCol.add(currentCol); //Add column value to pathCol ArrayList
					currentCol--;//Update coordinate of current tile
				} 
				else break;
			} 
			else if(tiles[currentRow][currentCol].getId().equalsIgnoreCase("Pipe01") || tiles[currentRow][currentCol].getId().equalsIgnoreCase("PipeStatic01")) {
				if(direction.equalsIgnoreCase("right") && currentRow - 1 >= 0 && tiles[currentRow-1][currentCol].isDown()) {
					pathRow.add(currentRow); //Add row value to pathRow ArrayList
					pathCol.add(currentCol); //Add column value to pathCol ArrayList
					currentRow--;//Update coordinate of current tile
				} 
				else if(direction.equalsIgnoreCase("up") && currentCol + 1 < 4 &&  tiles[currentRow][currentCol+1].isLeft()) {
					pathRow.add(currentRow); //Add row value to pathRow ArrayList
					pathCol.add(currentCol); //Add column value to pathCol ArrayList
					currentCol++; //Update coordinate of current tile
				} 
				else break;
			} 
			else if(tiles[currentRow][currentCol].getId().equalsIgnoreCase("Pipe10") || tiles[currentRow][currentCol].getId().equalsIgnoreCase("PipeStatic10")) {
				if(direction.equalsIgnoreCase("down") && currentCol - 1 >= 0 && tiles[currentRow][currentCol-1].isRight()) {
					pathRow.add(currentRow); //Add row value to pathRow ArrayList
					pathCol.add(currentCol); //Add column value to pathCol ArrayList
					currentCol--;//Update coordinate of current tile
				} 
				else if (direction.equalsIgnoreCase("left") && currentRow + 1 < 4 && tiles[currentRow+1][currentCol].isUp()) {
					pathRow.add(currentRow); //Add row value to pathRow ArrayList
					pathCol.add(currentCol); //Add column value to pathCol ArrayList
					currentRow++; //Update coordinate of current tile
				}
				else break;
			} 
			else if(tiles[currentRow][currentCol].getId().equalsIgnoreCase("Pipe11") || tiles[currentRow][currentCol].getId().equalsIgnoreCase("PipeStatic11")) {
				if(direction.equalsIgnoreCase("down") && currentCol + 1 < 4 && tiles[currentRow][currentCol+1].isLeft()) {
					pathRow.add(currentRow); //Add row value to pathRow ArrayList
					pathCol.add(currentCol); //Add column value to pathCol ArrayList
					currentCol++;//Update coordinate of current tile
				} 
				else if(direction.equalsIgnoreCase("right") && currentRow + 1 < 4 && tiles[currentRow+1][currentCol].isUp()) {
					pathRow.add(currentRow); //Add row value to pathRow ArrayList
					pathCol.add(currentCol); //Add column value to pathCol ArrayList
					currentRow++;//Update coordinate of current tile
				} 
				else break;
				
			} 
			else if(tiles[currentRow][currentCol].getId().equalsIgnoreCase("EndH")) {
				pathRow.add(currentRow); //Add row value to pathRow ArrayList
				pathCol.add(currentCol); //Add column value to pathCol ArrayList
				PipePuzzle.move = 0; //Reset the move number for the next level
				PipePuzzle.levelNumber++; // Increase level number
				new AnimateBall(pathRow, pathCol).animateBall(pane, stage); //After the right path is reached, to animate ball, call the animateBall method of AnimateBall class.
				break;
			} 
			else if(tiles[currentRow][currentCol].getId().equalsIgnoreCase("EndV")) {
				pathRow.add(currentRow); //Add row value to pathRow ArrayList
				pathCol.add(currentCol); //Add column value to pathCol ArrayList
				PipePuzzle.move = 0; //Reset the move number for the next level
				PipePuzzle.levelNumber++; // Increase level number
				AnimateBall aBall = new AnimateBall(pathRow, pathCol);
				aBall.animateBall(pane, stage); //After the right path is reached, to animate ball, call the animateBall method of AnimateBall class.
				break;
			} 
			else break;
			//Determine the direction of path that was composed according to previous column, row and next column, row.
			if(currentCol <preCol) {
				direction= "right";
			}
			else if (preCol < currentCol){
				direction= "left";
			}
			else if(preRow < currentRow) {
				direction = "up";
			}
			else if(currentRow < preRow){
				direction = "down";
			}
			else {
				direction= "NoDirection";
			}
		}
		return false;
	}
	
	//Getters and setters
	public ArrayList<Integer> getPathRow() {
		return pathRow;
	}

	public void setPathRow(ArrayList<Integer> pathRow) {
		this.pathRow = pathRow;
	}

	public ArrayList<Integer> getPathCol() {
		return pathCol;
	}

	public void setPathCol(ArrayList<Integer> pathCol) {
		this.pathCol = pathCol;
	}

	public boolean isLevelCompleted() {
		return levelCompleted;
	}

	public void setLevelCompleted(boolean levelCompleted) {
		this.levelCompleted = levelCompleted;
	}	
}