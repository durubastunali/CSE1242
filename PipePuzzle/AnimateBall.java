/* Aksanur Konuk 150120049, Duru Baþtunalý 150120075
   AnimateBall class provides the implementation of animation of the ball when the level is completed.
   In this class, based on the path, animation lines are setted and animation is played. */

import java.util.ArrayList;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimateBall {
	private ArrayList<Integer> pathRow; //The rows of the tiles that forms the path.
	private ArrayList<Integer> pathCol; //The columns of the tiles that forms the path.
	
	//Constructors
	public AnimateBall() {
	}
	
	public AnimateBall(ArrayList<Integer> pathRow, ArrayList<Integer> pathCol) {
		this.pathRow = pathRow;
		this.pathCol = pathCol;
	}
	
	//animateBall method: This method creates the path lines based on the rows and columns of the tiles that forms a path.
	public void animateBall(GridPane pane, Stage stage) throws Exception {
		PathTransition pathTransition = new PathTransition();
		Path path = new Path();
		int i = 0;
		int alignX = PipePuzzle.startCol * 104; //This variable aligns the X coordinate of the ball during the animation.
		int alignY = PipePuzzle.startRow * 104; //This variable aligns the Y coordinate of the ball during the animation.
		//The while loop is used to attach the lines formed between tiles in order to build the main path.
		while (i < pathRow.size() - 1) {
			int fromRow = (pathRow.get(i)); //This variable sets the row where the animation line will start from.
 			int fromCol = (pathCol.get(i)); //This variable sets the column where the animation line will start from.
			int toRow = (pathRow.get(i+1)); //This variable sets the row where the animation line will end.
			int toCol = (pathCol.get(i+1)); //This variable sets the column where the animation line will end.
			MoveTo start = new MoveTo(fromCol * 104 - alignX, fromRow * 104 - alignY); //The exact point where the main line starts.
			path.getElements().add(start);
			if (fromRow == toRow) { //If the tiles that form the smaller path have the same row, create a horizontal line in between.
				HLineTo hLine = new HLineTo (toCol * 104 - alignX);
				path.getElements().add(hLine);
			} 
			else if (fromCol == toCol) { //If the tiles that form the smaller path have the same column, create a vertical line in between.
				VLineTo vLine = new VLineTo (toRow * 104 - alignY);
				path.getElements().add(vLine);	
			}
			i++;
		}
		//Set the path, duration, node to display the animation.
		pathTransition.setPath(path);
		pathTransition.setDuration(Duration.millis(pathRow.size() * 200));
		pathTransition.setNode(PipePuzzle.ball);
		PipePuzzle.ball.toFront();
		pathTransition.play();
		//Wait for the animation to end, when it ends: display the next level -if available- by calling the start method.
		pathTransition.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					new PipePuzzle().start(stage);
				} catch (Exception e) {	
					e.printStackTrace();
				}	
			}
		});	
	}
}