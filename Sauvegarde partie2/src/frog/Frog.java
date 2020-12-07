package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class Frog implements IFrog {
	private Case position;
	private Direction direction;

	protected Game game;

	public Frog(Game game) {
		this.game = game;
		this.position = new Case(game.height / 2, 0);
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
			break;

			case up:
			this.position = new Case(position.absc, position.ord + 1);
			break;

			case left:
				if (0 < this.position.absc)
					this.position = new Case(position.absc - 1, position.ord);
			break;

			case right:
				if (this.position.absc < this.game.width - 1)
					this.position = new Case(position.absc + 1, position.ord);
			break;
		}

	}
}
