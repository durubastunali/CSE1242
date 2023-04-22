/* Aksanur Konuk 150120049, Duru Baþtunalý 150120075
   Tile class sets the properties of a tile by its id, available directions, moveability and image. Forms the
   GridPane to display the current level with the given informations from the PipePuzzle class. */

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Tile {
	private String id; //Id of the Tile
	private boolean up; //boolean value for whether any 'up' direction is available for the tile.
	private boolean down; //boolean value for whether any 'down' direction is available for the tile.
	private boolean right; //boolean value for whether any 'right' direction is available for the tile.
	private boolean left; //boolean value for whether any 'left' direction is available for the tile.
	private boolean moveable; //boolean value for whether the tile is moveable or not.
	private Image image; //Image of the associated tile
	
	//Constructors
	public Tile() {
	}
	
	public Tile(String id, boolean up, boolean down,boolean right, boolean left, boolean moveable, int row, int column, GridPane pane) {
		this.id = id;
		this.up = up;
		this.down = down;
		this.right = right;
		this.left = left;
		this.moveable = moveable;	
		this.image = new Image(id + ".PNG"); //Assign an image for the associated id
		addPane(pane, this.image, row, column);
	}
	
	//addPane method: Every time a Tile object is created with second constructor, the image of tiles and ball is added to pane.
	public void addPane(GridPane pane, Image image, int row, int column) {
		pane.add(new ImageView(image), column, row);
		if(this.id.equals("StarterV") || this.id.equals("StarterH")) {
			PipePuzzle.startRow = row; //Get the row of the starter tile.
			PipePuzzle.startCol = column; //Get the column of the starter tile.
			pane.add(PipePuzzle.ball, column, row);
			GridPane.setHalignment(PipePuzzle.ball, HPos.CENTER); //Set horizontal alignment of the ball to the center of the cell.
			GridPane.setValignment(PipePuzzle.ball, VPos.CENTER); //Set vertical alignment of the ball to the center of the cell.
			PipePuzzle.ball.toFront();
		}
	}
	
	//Getters and Setters
	public Image getImage() {
		return image;
	}

	public String getId() {
		return id;
	}

	public boolean isUp() {
		return up;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isRight() {
		return right;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isMoveable() {
		return moveable;
	}
}