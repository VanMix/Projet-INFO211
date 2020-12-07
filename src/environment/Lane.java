package environment;

import gameCommons.Game;
import util.Case;

import java.util.ArrayList;

public class Lane {
	protected Game game;
	private int ord;
	private int speed;
	protected ArrayList<Car> cars = new ArrayList<>();
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

	/** Déplace toutes les voitures en fonction de si elles sont en mouvement et enlève les voitures arrivé en bordures
	 *
	 * @param b un booléen disant si les voitures sont en mouvement
	 */
	public void deplaceVoitures(boolean b){
		for(Car c1 : this.cars){
			removeCar(c1);
			c1.enMouvement(b);
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

	/**Vérifie si la case est vide (donc qu'il n'y a pas de voiture)
	 *
	 * @param firstCase une Case permettant de comparer la position avec nos voitures
	 * @return
	 */
	public boolean isSafe(Case firstCase) {
		for (Car c : cars) {
			if (firstCase.equals(c.getLeftPosition()))
				return false;
		}
		return true;
	}

	/** Enlève la voiture si elle est arrivée en bordure
	 *
	 * @param c un Car
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
