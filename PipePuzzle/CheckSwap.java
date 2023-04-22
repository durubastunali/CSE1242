/* Aksanur Konuk 150120049, Duru Baþtunalý 150120075
   CheckSwap class first enables player to choose two tiles to be swapped with mouse, then controls whether these two tiles 
   have specific properties such as the first tile to be moveable (non static, non start, non end) and the second tile to 
   be a free one. If swap is available, implementation continues. */

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CheckSwap {
	private Text nmbOfMoves; //Text field which is meant to be changed in each swap done by the player.
	private GridPane pane;
	private Tile[][] tiles;
	private Stage stage;
	private boolean tileDeclared; //boolean value to indicate whether the first tile is chosen by the player
	private boolean freeDeclared; //boolean value to indicate whether a second tile is chosen by the player
	private int tileRow; //This variable keeps the row of the first tile chosen.
	private int tileCol; //This variable keeps the column of the first tile chosen.
	private int freeRow; //This variable keeps the row of the second tile chosen.
	private int freeCol; //This variable keeps the column of the second tile chosen.
	
	//Constructors
	public CheckSwap() {	
	}
 	
	public CheckSwap(GridPane pane, Tile[][] tiles, Stage stage, Text nmbOfMoves) {
		this.pane = pane;
		this.tiles = tiles;
		this.stage = stage;
		this.nmbOfMoves = nmbOfMoves;
	}
	
	/* getRowCol method: This method enables player to choose two tiles by dragging the mouse. This implementation
	   is done by 'Pressed' and 'Released' events. Pressed event holds the related values of the first tile
	   chosen by the player, and Released event holds the related values of the second tile chosen by the player. */
	public void getRowCol () throws Exception {
		pane.setOnMousePressed(event -> {
			this.tileRow = ((int) event.getY()) / 104; //Adjust the row by dividing the Y coordinate to per tile pixel.
			this.tileCol = ((int) event.getX()) / 104; //Adjust the column by dividing the X coordinate to per tile pixel.
			this.tileDeclared = true; //First tile is declared.
			try {
				checkDeclared(); //Invoke checkDeclared method. (explained right before the method)
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}); 
		
		pane.setOnMouseReleased(event -> {
			this.freeRow = ((int) event.getY()) / 104; //Adjust the row by dividing the Y coordinate to per tile pixel.
			this.freeCol = ((int) event.getX()) / 104; //Adjust the column by dividing the X coordinate to per tile pixel.
 			this.freeDeclared = true; //First tile is declared.
			try {
				checkDeclared(); //Invoke checkDeclared method. (explained right before the method)
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	/* checkDeclared method: This method firstly checks whether the two tiles are chosen or not. Also an additional control of
	 * keeping the tiles in 4x4 playing area is runned to ensure an additional restrict to rows and columns. */
	public void checkDeclared () throws Exception {
		if (freeDeclared && tileDeclared && freeRow < 4 && freeCol < 4 && tileRow < 4 && tileCol < 4) {
			checkAvailable(); //Invoke checkAvailable method. (explained right before the method)
			setFreeDeclared(false);
			setTileDeclared(false);
		} 
	}
	
	/* checkAvailable method: This method firstly checks whether the two tiles are either horizontally or vertically adjacent. For 
	   further check about the moveability details of the chosen tile, it invokes the 'checkTiles' method from 'CheckTiles' class. 
	   If the criterias are matched, updates the number of move and later performs the swap. */
	public void checkAvailable() throws Exception {	
		if (freeRow == tileRow && Math.abs(freeCol - tileCol) == 1 && new CheckTiles(tiles,freeRow,freeCol,tileRow,tileCol).checkTiles()) {
			printMoveNumber(pane); //Invokes printMoveNumber to update the number of moves.
			new SwapTiles(pane, tiles, stage, freeRow, freeCol, tileRow, tileCol).swap();
		}
		if (freeCol == tileCol && Math.abs(freeRow - tileRow) == 1 && new CheckTiles(tiles,freeRow,freeCol,tileRow,tileCol).checkTiles()) {
			printMoveNumber(pane); //Invokes printMoveNumber to update the number of moves.
			new SwapTiles(pane, tiles, stage, freeRow, freeCol, tileRow, tileCol).swap();
		}
	}
	
	//printMoveNumber method: This method updates the number of move done by the player after each correct swap is performed.
	public void printMoveNumber(GridPane pane) {
		PipePuzzle.move++; //Increases the number of move.
		pane.getChildren().remove(nmbOfMoves); //Removes the previous value of number of moves text from the pane.
		this.nmbOfMoves = new Text(PipePuzzle.move + "");
		nmbOfMoves.setFont(Font.font("Corrier", FontWeight.BOLD, 13));
		nmbOfMoves.setFill(Color.WHITE);
		pane.add(nmbOfMoves, 3, 4); //Returns the updated value as a text field to the pane.
	}
	
	//Setters
	public void setFreeDeclared(boolean freeDeclared) {
		this.freeDeclared = freeDeclared;
	}
	
	public void setTileDeclared(boolean tileDeclared) {
		this.tileDeclared = tileDeclared;
	}
}