package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class Frog implements IFrog {
	private Case position;
	private Direction direction;

	private Game game;

	public Frog(Game game) {
		this.game = game;
		this.position = new Case(0, 0);
	}

	@Override
	public Case getPosition() {
		return position;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void move(Direction key) {
		switch (key){
			case down:
			this.position = new Case(position.absc, position.ord - 1);
			this.direction = key;
			break;

			case up:
			this.position = new Case(position.absc, position.ord + 1);
			this.direction = key;
			break;

			case left:
			this.position = new Case(position.absc - 1, position.ord);
			this.direction = key;
			break;

			case right:
			this.position = new Case(position.absc + 1, position.ord);
			this.direction = key;
			break;
		}

	}
}
