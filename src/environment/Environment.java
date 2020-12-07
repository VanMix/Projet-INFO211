package environment;
import java.util.ArrayList;

import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Case;

public class Environment implements IEnvironment {
    private ArrayList<Lane> lanes = new ArrayList<>();
    private Game game;

    public Environment(Game game){
        this.game = game;
        this.lanes.add(new Lane(game, 0, 0.0D));

        for(int i = 1; i < game.height - 1; i++){
            this.lanes.add(new Lane(game, i, game.defaultDensity));
        }

        this.lanes.add(new Lane(game, game.height - 1, 0.0D));
    }

    @Override
    public boolean isSafe(Case c) {
        Lane pos = this.lanes.get(c.ord);
        for (Car car : pos.voitures()){
            if(c.absc >= car.getLeftPosition().absc && c.absc < car.getLeftPosition().absc + car.tailleVoit())
                if(c.ord == pos.ordonne())
                    return false;
        }
        return true;
    }

    @Override
    public boolean isWinningPosition(Case c) {
        return c.ord == game.height - 1;
    }

    @Override
    public void update() {
        for(Lane l : this.lanes)
            l.update();
    }

}
