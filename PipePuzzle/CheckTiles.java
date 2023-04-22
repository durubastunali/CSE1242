/* Aksanur Konuk 150120049, Duru Baþtunalý 150120075
   CheckTiles class gets the data from the tiles array, which keeps the informations about the GridPane cell by cell, 
   and then checks whether the first cell keeps a moveable tile and second cell keeps a free tile. */

public class CheckTiles {
	private int tileRow; //This variable keeps the row of the first tile chosen.
	private int tileCol; //This variable keeps the column of the first tile chosen.
	private int freeRow; //This variable keeps the row of the second tile chosen.
	private int freeCol; //This variable keeps the column of the second tile chosen.
	private Tile[][] tiles = new Tile[4][4]; //Array of Tile that keeps the information of the GridPane cell by cell.
	
	//Constructors
	public CheckTiles() {
	}
	
	public CheckTiles(Tile[][] tiles, int freeRow, int freeCol, int tileRow, int tileCol) {
		this.tiles = tiles;
		this.freeRow = freeRow;
		this.freeCol = freeCol;
		this.tileRow = tileRow;
		this.tileCol = tileCol;
	}
	
	//checkTiles method: This method checks whether the first tile is moveable and the second tile is free.
	public boolean checkTiles() {
		return tiles[tileRow][tileCol].isMoveable() && tiles[freeRow][freeCol].getId().equalsIgnoreCase("EmptyFree") ;
	}
}