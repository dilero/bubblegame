package com.bubble.actors;

import com.badlogic.gdx.physics.box2d.World;
import com.bubble.enums.BoundEnum;
import com.bubble.utils.BodyUtils;
import com.bubble.utils.Constants;
import com.bubble.utils.WorldUtils;

public class Shooter extends GameActor {

	private boolean shotting;
	private boolean leftMove = false;
	private boolean rightMove = false;

	public Shooter(World world) {
		super(world);
		body = WorldUtils.createShooter(world);
		body.setUserData(this);
	}

	public void moveRight(float delta) {
		if (!BoundEnum.RIGTBOUND.equals(bodyInBounds())) {
			System.out.println("moveRight");
			body.setTransform(
					(body.getPosition().x + (delta * Constants.SHOOTER_STEP_SIZE)),
					body.getPosition().y, 0);
		}
	}

	public void moveLeft(float delta) {
		if (!BoundEnum.LEFTBOUND.equals(bodyInBounds())) {
			System.out.println("moveLeft");
			body.setTransform(
					(body.getPosition().x - (delta * Constants.SHOOTER_STEP_SIZE)),
					body.getPosition().y, 0);
		}
	}

	@Override
	public void act(float delta) {
		if (leftMove) {
			moveLeft(delta);
		}
		if (rightMove) {
			moveRight(delta);
		}
		super.act(delta);
	}

	@Override
	public BoundEnum bodyInBounds() {
		if (body.getPosition().x - Constants.SHOOTER_WIDTH / 2 < 0)
			return BoundEnum.LEFTBOUND;
		else if (body.getPosition().x + Constants.SHOOTER_WIDTH / 2 > Constants.APP_WIDTH)
			return BoundEnum.RIGTBOUND;
		else
			return BoundEnum.INBOUND;
	}

	public boolean isShotting() {
		return shotting;
	}

	public void setShotting(boolean shotting) {
		this.shotting = shotting;
	}

	public boolean isLeftMove() {
		return leftMove;
	}

	public void setLeftMove(boolean leftMove) {
		this.leftMove = leftMove;
	}

	public boolean isRightMove() {
		return rightMove;
	}

	public void setRightMove(boolean rightMove) {
		this.rightMove = rightMove;
	}
}