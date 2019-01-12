package interfaceJeu;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Domino;
import model.Jeu;
import model.Joueur;

public class GridTour implements Initializable {

	
	public Label currentPlayer;
	public Label currentPlayerBis;
	public AnchorPane currentDomino;
	public Button validateChoice;
	public ImageView image1 = new ImageView();
	public ImageView image2 = new ImageView();
	public static Domino d;
	public ImageView[][] emptyKingdom;
	public GridPane kingdom = new GridPane();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		emptyKingdom = new ImageView[9][9];
		
		
		for(int i =0; i<9; i++){
	        for(int j=0; j <9; j++){
	            emptyKingdom[i][j] = new ImageView("Tiles/watertile.png");
	            emptyKingdom[i][j].setPreserveRatio(true);
	            emptyKingdom[i][j].setFitHeight(49);
	            emptyKingdom[i][j].setFitWidth(49);
	            kingdom.add(emptyKingdom[i][j], i, j);
	            //ships2d[i][j] = new ImageView("Ships/ship2.png");
	            //ships2d[i][j].setPreserveRatio(true);
	            //ships2d[i][j].setFitWidth(49);
	            //Board.add(ships2d[i][j], i, j);

	        }
	    }
		
		System.out.println("GAME TOUR " + GameTour.currentJoueur.getNomJoueur());
		String str = GameTour.currentJoueur.getNomJoueur();
		currentPlayer.setText(str);
		currentPlayerBis.setText(str);
		d = GameTour.currentJoueur.listOfDominosPerPlayer.get(0);
		displayDominoToPut();
	
		System.out.println("domino picked : " + " num " + d.getNumero() + " t1 : " + d.getTypeTuile1() + " t2 " + d.getTypeTuile2());
		
		//final AnchorPane currentDomino = new AnchorPane();
		//final AnchorPane testAnchor = new AnchorPane();
			}
	
	
	
	
	public void rotationDom() {
		System.out.println(currentDomino.getRotate());
		
		if(currentDomino.getRotate() == 0.0 || currentDomino.getRotate() == 360) {
			currentDomino.setRotate(90);
			System.out.println(currentDomino.getRotate());
		} else if (currentDomino.getRotate() == 90.0) {
			currentDomino.setRotate(180);
			System.out.println(currentDomino.getRotate());
		} else if (currentDomino.getRotate() == 180){
			currentDomino.setRotate(270);
		} else {
			currentDomino.setRotate(360);
		}
		
	}
	
	
	public void displayDominoToPut() {
		String typeTuile1 = d.getTuile1().typeTuile;
		String typeTuile2 = d.getTuile2().typeTuile;
		int nbCouronnes1 = d.getTuile1().getNbCouronnes();
		int nbCouronnes2 = d.getTuile2().getNbCouronnes();
		Image img = new Image("./model/public/"+typeTuile1+nbCouronnes1+".png");
		Image img2 = new Image("./model/public/"+typeTuile2+nbCouronnes2+".png");
		
			image1.setImage(img);
			image2.setImage(img2);
			
		
			//numDomino1 = dom.getNumero();
			//Jeu.dominosGUI(dom);
			
			
			 
			 //image1.setPreserveRatio(true);
			    //source.setFitWidth(80);
			
			
			currentDomino.setOnDragDetected(new EventHandler<MouseEvent>() {
			    public void handle(MouseEvent event) {
			    	System.out.println("Trying to drag");
			        /* drag was detected, start a drag-and-drop gesture*/
			        /* allow any transfer mode */
			        Dragboard db = currentDomino.startDragAndDrop(TransferMode.ANY);
			        
			        /* Put a string on a dragboard */
			        ClipboardContent content = new ClipboardContent();
			        //content.put
			        content.putImage(img);
			        //content.putImage(img2);
			        db.setContent(content);
			        
			        event.consume();
			    }
			});
			
			kingdom.setOnDragOver(new EventHandler<DragEvent>() {
			    public void handle(DragEvent event) {
			    	System.out.println("Trying to drop");
			    	
			    	//Data dropped
			        //If there is an image on the dragboard, read it and use it
			       
			        /* data is dragged over the target */
			        /* accept it only if it is not dragged from the same node 
			         * and if it has a string data */
			       
			    	
			    	if (event.getGestureSource() != kingdom &&
			                event.getDragboard().hasImage()) {
			            /* allow for both copying and moving, whatever user chooses */
			            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			        }
			        
			        event.consume();
			    }
			});
			
			kingdom.setOnDragEntered(new EventHandler<DragEvent>() {
			    public void handle(DragEvent event) {
			    /* the drag-and-drop gesture entered the target */
			    /* show to the user that it is an actual gesture target */
			    	if(event.getGestureSource() != kingdom && event.getDragboard().hasImage()){
		                currentDomino.setVisible(false);
		                kingdom.setOpacity(0.7);
		                System.out.println("Drag entered");
		            }
		            event.consume();
			    }
			});
			
			kingdom.setOnDragExited(new EventHandler<DragEvent>() {
			    public void handle(DragEvent event) {
			        /* mouse moved away, remove the graphical cues */
			        System.out.println("On mouse moved away");

			        currentDomino.setVisible(true);
			        kingdom.setOpacity(1);
		            event.consume();

			    }
			});
			
			kingdom.setOnDragDropped(new EventHandler<DragEvent>() {
			    public void handle(DragEvent event) {
			    	 Dragboard db = event.getDragboard();
				        boolean success = false;
				        Node node = event.getPickResult().getIntersectedNode();
				        if(node != kingdom) {

				            Integer columnIndex = GridPane.getColumnIndex(node);
				            Integer rowIndex = GridPane.getRowIndex(node);
				            int x = columnIndex == null ? 0 : columnIndex;
				            int y = rowIndex == null ? 0 : rowIndex;
				            //target.setText(db.getImage()); --- must be changed to target.add(source, col, row)
				            //target.add(source, 5, 5, 1, 1);
				            //Places at 0,0 - will need to take coordinates once that is implemented
				            ImageView image = new ImageView(db.getImage());

				            // TODO: set image size; use correct column/row span
				            kingdom.add(image, x, y, 1, 1);
				            success = true;
				        }
				        //let the source know whether the image was successfully transferred and used
				        event.setDropCompleted(success);

				        event.consume();
			     }
			});
			
			currentDomino.setOnDragDone(new EventHandler<DragEvent>() {
			    public void handle(DragEvent event) {
			        /* the drag and drop gesture ended */
			        /* if the data was successfully moved, clear it */
			        if (event.getTransferMode() == TransferMode.MOVE) {
			            currentDomino.setVisible(false);
			        }
			        event.consume();
			    }
			});

		
	
	}

	
	
	
	
	public void goBackAction(ActionEvent event) {
		try {
			
			boolean test = false;
			FXMLLoader loader = new FXMLLoader(
			    getClass().getResource(
			        "./public/GameTour.fxml"
			    )
			);
			Parent root = (Parent) loader.load();
			GameTour controller = loader.getController();
			System.out.println(controller);
			controller.newTour(test);

			Scene nextScene = new Scene(root);
			
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(nextScene);
			window.show();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	
	public void validatePut(ActionEvent event) {
		System.out.println("Click");
		try {
			
			/* if validate blabla 1
			 * validate pos
			 * validate grille etc
			 */
			Joueur j = GameTour.currentJoueur;
			System.out.println("Au moment du validate Put taille des joueurs : " + Jeu.joueurs.size());
			Jeu.joueurs.remove(j);
			System.out.println("Apreès le remove Put taille des joueurs : " + Jeu.joueurs.size());
			Jeu.handleOrder(j, d);
			GameTour.listEachTour.remove(d);
			j.listOfDominosPerPlayer.remove(d);
			j.listOfDominosPut.add(d);
			boolean test;
			if(GameTour.listEachTour.size() == 0) {
				test = true;
			} else {
				test = false;
			}
		
			FXMLLoader loader = new FXMLLoader(
			    getClass().getResource(
			        "./public/GameTour.fxml"
			    )
			);
			Parent root = (Parent) loader.load();
			GameTour controller = loader.getController();
			System.out.println(controller);
			controller.newTour(test);
	
			Scene nextScene = new Scene(root);
			
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(nextScene);
			window.show();
		
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
