package environment;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

import java.awt.*;
import java.util.Random;

public class CarInfinity {
    private Game game;
    private Case leftPosition;
    private LaneInfinity lane;
    private boolean leftToRight;
    private int length;
    private final Color colorLtR = Color.BLACK;
    private final Color colorRtL = Color.BLUE;

    //TODO Constructeur(s)

    //TODO : ajout de methodes


    public CarInfinity(Game game, LaneInfinity lane, Case leftPosition, boolean leftToRight) {
        this.game = game;
        this.lane = lane;
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
            this.leftPosition = new Case (leftPosition.absc + 1, lane.ordonne());
        } else {
            this.leftPosition = new Case(leftPosition.absc - 1, lane.ordonne());
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
                    .add(new Element(leftPosition.absc + i, lane.ordonne(), color));
        }
    }
}
