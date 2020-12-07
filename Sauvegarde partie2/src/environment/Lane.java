package environment;

import gameCommons.Game;
import util.Case;

import java.util.ArrayList;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int compteur = 0;

	// TODO : Constructeur(s)

	public Lane(Game game, int ord, double density) {
		this.game = game;
		this.ord = ord;
		this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;
		this.leftToRight = game.randomGen.nextBoolean();
		this.density = density;

		for(int i = 0; i < game.height; i++){
			deplaceVoitures(true);
			mayAddCar();
		}
	}

	public int ordonne(){
		return ord;
	}

	public void setOrd(int i){
		this.ord = i;
	}

	public ArrayList<Car> voitures(){
		return cars;
	}

	public void update() {

		// TODO
		this.compteur++;
		// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
		// d'horloge" �gal � leur vitesse
		// Notez que cette m�thode est appel�e � chaque tic d'horloge
			if (this.compteur <= this.speed) {
				deplaceVoitures(false);
			} else {
				deplaceVoitures(true);
				this.mayAddCar();
				this.compteur = 0;
			}
		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut �tre ajout�e

	}

	public void deplaceVoitures(boolean b){
		for(Car c1 : this.cars){
			removeCar(c1);
			c1.enMouvement(b, speed);
		}
	}
	// TODO : ajout de methodes

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase()
	 */

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	public boolean isSafe(Case firstCase) {
		for (Car c : cars) {
			if (firstCase.equals(c.getLeftPosition()))
				return false;
		}
		return true;
	}
	/*
	public void removeCar(Game game, Lane lane) {
		if (leftToRight)
			if (lane. == game.width)
				if (ladernierecase != null)
					ladernierecase = null;
	}
	*/

	public void removeCar(Car c){
		if(!c.limits())
			this.cars.remove(c);
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

}
