/* Aksanur Konuk 150120049, Duru Baþtunalý 150120075
   SwapTiles swaps the two tiles by changing the information of the chosen cells in tiles array, reflects the
   updated situation of the grid pane. Additionally, performs a smooth animation between tiles in each swap. */

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SwapTiles {
	private int tileRow; //This variable keeps the row of the first tile chosen.
	private int tileCol; //This variable keeps the column of the first tile chosen.
	private int freeRow; //This variable keeps the row of the second tile chosen.
	private int freeCol; //This variable keeps the column of the second tile chosen.
	private Tile[][] tiles; //Array of Tile that keeps the information of the GridPane cell by cell.
	private GridPane pane; 
	private Stage stage;
	
	//Constructors
	public SwapTiles() {
	}
	
	public SwapTiles(GridPane pane, Tile[][] tiles, Stage stage, int freeRow, int freeCol, int tileRow, int tileCol) {
		this.freeRow = freeRow;
		this.freeCol = freeCol;
		this.tileRow = tileRow;
		this.tileCol = tileCol;
		this.tiles = tiles;
		this.pane = pane;
		this.stage = stage;
	}
	
	/* swap method: This method swaps the the tiles which are chosen by the player by both updating the informations in cells and
	   performs a smooth animation while the tiles are being swapped. */
	public GridPane swap() throws Exception {
		PathTransition pathTransition = new PathTransition();
		Path path = new Path();
		//Form the path where the first tile will move to.
		MoveTo start = new MoveTo(tileCol * 104 + 52, tileRow * 104 + 52); //The exact point of the first tile.
		path.getElements().add(start);
		if (freeRow == tileRow) { //If the tiles have the same row, create a horizontal line in between.
			HLineTo hLine = new HLineTo(freeCol * 104 + 52);
			path.getElements().add(hLine);
		}
		else if (freeCol == tileCol) { //If the tiles have the same column, create a vertical line in between.
			VLineTo vLine = new VLineTo(freeRow * 104 + 52);
			path.getElements().add(vLine);
		}
		//Determine which type of tile is going to perform the animation.
		ImageView swapImage = new ImageView(tiles[tileRow][tileCol].getImage());
		pane.getChildren().add(swapImage);
		//Create a smooth background for the animation.	
		Image tempFree = new Image("EmptyFree.PNG");
		pane.add(new ImageView(tempFree), tileCol, tileRow);
		pane.add(new ImageView(tempFree), freeCol, freeRow);
		//Set the path and node that will perform the animation.
		pathTransition.setPath(path);
		pathTransition.setDuration(Duration.millis(140));
		swapImage.toFront();
		pathTransition.setNode(swapImage);
		pathTransition.play();
		pathTransition.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					pane.getChildren().remove(swapImage);
					//Update the informations in tiles array right after the animation ends.
					Image imageFree = tiles[freeRow][freeCol].getImage();
					Image imageTile = tiles[tileRow][tileCol].getImage();
					Tile imageTemp = tiles[tileRow][tileCol];
					tiles[tileRow][tileCol] = tiles[freeRow][freeCol];
					tiles[freeRow][freeCol]= imageTemp;
					//Display the updated position of the GridPane.
					pane.add(new ImageView(imageTile), freeCol, freeRow);
					pane.add(new ImageView(imageFree), tileCol, tileRow);
					//After each swap, control whether the level is completed or not.
					new CheckLevel(tiles, stage, pane).control(); 
					} catch (Exception e) {	
					e.printStackTrace();
					}	
				}});
		/* Inform the CheckSwap class that the swap has occured and new inputs from the player
		   about which tiles are about to be moved next is ready to be taken. */
		new CheckSwap().setFreeDeclared(false);
		new CheckSwap().setTileDeclared(false);
		return pane; //Return the updated position of the GridPane.
	}
}