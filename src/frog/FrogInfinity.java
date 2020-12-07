package frog;

import environment.Environment;
import environment.EnvironmentInfinity;
import environment.Lane;
import environment.LaneInfinity;
import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

import java.util.ArrayList;

public class FrogInfinity  extends Frog implements IFrog {
    private Direction direction;
    private Environment environment;
    private int score = 0;

    public FrogInfinity(Game game) {
        super(game);
        setPosition(new Case(game.height/2, 1));
    }

    public int getScore() {
        return score;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }


    /**Fait bouger virtuellement la grenouille mais en réalité c'est la Map qui bouge
     *
     * @param key la direction vers où va la grenouille
     */
    @Override
    public void move(Direction key) {
        EnvironmentInfinity env = ((EnvironmentInfinity) game.getEnvironment());
        switch (key){
            case down:
                env.deplacementMap(key);

                int pos_first_lane = env.getLanes().indexOf(env.getFirstLane()); //on prend la position de la première lane
                LaneInfinity next_first_lane = env.getLanes().get(pos_first_lane + 1); //on prend la lane d'en haut
                //on sait qu'il y en a forcément une car on en crée une à chaque fois que Frog avance

                env.setFirstLane(next_first_lane); //on met à jour la première lane
                this.score--;
                break;

            case up:
                env.deplacementMap(key);

                pos_first_lane = env.getLanes().indexOf(env.getFirstLane()); //on prend la position de la première lane
                next_first_lane = env.getLanes().get(pos_first_lane + 1); //on prend la lane d'en haut
                //on sait qu'il y en a forcément une car on en crée une à chaque fois que Frog avance

                env.setFirstLane(next_first_lane); //on met à jour la première lane
                if(!game.testLose())
                    this.score++;
                break;

            case left:
                if (0 < getPosition().absc)
                    setPosition(new Case(getPosition().absc - 1, getPosition().ord));
                break;

            case right:
                if (getPosition().absc < this.game.width - 1)
                    setPosition(new Case(getPosition().absc + 1, getPosition().ord));
                break;
        }

    }
}
