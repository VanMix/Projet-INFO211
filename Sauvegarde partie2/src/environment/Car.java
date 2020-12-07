package environment;

import java.awt.Color;
import java.util.Random;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;

	//TODO Constructeur(s)
	
	//TODO : ajout de methodes


	public Car(Game game, Case leftPosition, boolean leftToRight) {
		this.game = game;
		this.leftPosition = leftPosition;
		this.leftToRight = leftToRight;
		Random r = new Random();
		this.length = 1 + r.nextInt(2);
	}

	public Case getLeftPosition() {
		return leftPosition;
	}

	public int tailleVoit(){
		return length;
	}
	public void avancer (int speed) {
		if(this.leftToRight){
			this.leftPosition = new Case (leftPosition.absc + speed,leftPosition.ord);
		} else {
			this.leftPosition = new Case(leftPosition.absc - speed, leftPosition.ord);
		}
	}


	public void enMouvement (boolean mouvement, int speed){
		if(mouvement) {
			avancer(speed);
		}
		this.addToGraphics();
	}


	public boolean limits(){
		return leftPosition.absc + length > 0 || leftPosition.absc + length < game.width;
	}

	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	public void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

}
