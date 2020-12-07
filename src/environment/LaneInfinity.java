package environment;

import environment.Lane;
import gameCommons.Game;
import util.Case;

import java.util.ArrayList;

public class LaneInfinity {
    private Game game;
    private int ord;
    private int speed;
    private ArrayList<CarInfinity> cars = new ArrayList<>();
    private boolean leftToRight;
    private double density;
    private int compteur = 0;

    // TODO : Constructeur(s)

    public LaneInfinity(Game game, int ord, double density) {
        this.game = game;
        this.ord = ord;
        this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;
        this.leftToRight = game.randomGen.nextBoolean();
        this.density = density;

        for(int i = 0; i < 20; i++){
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

    public ArrayList<CarInfinity> voitures(){
        return cars;
    }

    public void update() {

        // TODO
        this.compteur++;
        // Toutes les voitures se déplacent d'une case au bout d'un nombre "tic
        // d'horloge" égal à leur vitesse
        // Notez que cette méthode est appelée à chaque tic d'horloge
        if (this.compteur <= this.speed) {
            deplaceVoitures(false);
        } else {
            deplaceVoitures(true);
            this.mayAddCar();
            this.compteur = 0;
        }
        // Les voitures doivent etre ajoutes a l interface graphique meme quand
        // elle ne bougent pas

        // A chaque tic d'horloge, une voiture peut être ajoutée

    }

    public void deplaceVoitures(boolean b){
        for(CarInfinity c1 : this.cars){
            c1.enMouvement(b, speed);
            removeCar(c1);
        }
    }
    // TODO : ajout de methodes

    /*
     * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase()
     */

    /**
     * Ajoute une voiture au début de la voie avec probabilité égale à la
     * densité, si la première case de la voie est vide
     */
    private void mayAddCar() {
        if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
            if (game.randomGen.nextDouble() < density) {
                cars.add(new CarInfinity(game, this, getBeforeFirstCase(), leftToRight));
            }
        }
    }

    public boolean isSafe(Case firstCase) {
        for (CarInfinity c : cars) {
            if (firstCase.absc >= c.getLeftPosition().absc && firstCase.absc < c.getLeftPosition().absc + c.tailleVoit())
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

    public void removeCar(CarInfinity c){
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

