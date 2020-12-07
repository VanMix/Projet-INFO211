package environment;

import environment.Lane;
import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Case;
import util.Direction;

import java.util.ArrayList;

public class EnvironmentInfinity implements IEnvironment {
    private ArrayList<LaneInfinity> lanes = new ArrayList<>();
    private Game game;
    private LaneInfinity first_lane;

    public EnvironmentInfinity(Game game){
        this.game = game;
        this.lanes.add(new LaneInfinity(game, 0, 0.0D));
        this.lanes.add(new LaneInfinity(game, 1, 0.0D));
        this.first_lane = this.lanes.get(1);

        for(int i = 2; i < game.height; i++){
            this.lanes.add(new LaneInfinity(game, i, game.defaultDensity));
        }
    }

    @Override
    public boolean isSafe(Case c) {
        LaneInfinity pos = first_lane;
        return pos.isSafe(c);
    }

    @Override
    public boolean isWinningPosition(Case c) {
        return false;
    }

    //test
    public ArrayList<LaneInfinity> getLanes() {
        return lanes;
    }

    //fin test

    public LaneInfinity getFirstLane() {
        return first_lane;
    }

    public void setFirstLane(LaneInfinity lane) {
        this.first_lane = lane;
    }

    public void deplacementMap(Direction key){
        if(key == Direction.up){
            this.lanes.add(new LaneInfinity(game, game.height + 1, game.defaultDensity));
            for(LaneInfinity l : this.lanes){
                l.setOrd(l.ordonne()-1);
            }
        } else if(key == Direction.down){
            for(LaneInfinity l : this.lanes){
                l.setOrd(l.ordonne()+1);
            }
        }
    }

    @Override
    public void update() {
        for(LaneInfinity l : this.lanes)
            l.update();
    }

}
