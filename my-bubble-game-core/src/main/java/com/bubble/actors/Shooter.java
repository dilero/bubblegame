package com.bubble.actors;

import com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Angular;
import com.badlogic.gdx.physics.box2d.Body;
import com.bubble.box2d.ShooterUserData;
import com.bubble.enums.BoundEnum;
import com.bubble.utils.BodyUtils;
import com.bubble.utils.Constants;

public class Shooter extends GameActor {

	private boolean shotting;
	private boolean leftMove = false;
	private boolean rightMove = false;

	public Shooter(Body body) {
		super(body);
	}

	@Override
	public ShooterUserData getUserData() {
		return (ShooterUserData) userData;
	}

	public void shot() {

		// if (!shotting) {
		// body.applyLinearImpulse(getUserData().getShotLinearImpulse(),
		// body.getWorldCenter(), true);
		// shotting = true;
		// }
		System.out.println("Shot");

	}

	public void moveRight(float delta) {
		if (!BoundEnum.RIGTBOUND.equals(BodyUtils.bodyInBounds(body))) {
			System.out.println("moveRight");
			body.setTransform(
					(body.getPosition().x + (delta * Constants.SHOOTER_STEP_SIZE)),
					body.getPosition().y, 0);
		}
	}

	public void moveLeft(float delta) {
		if (!BoundEnum.LEFTBOUND.equals(BodyUtils.bodyInBounds(body))) {
			System.out.println("moveLeft");
			body.setTransform(
					(body.getPosition().x - (delta * Constants.SHOOTER_STEP_SIZE)),
					body.getPosition().y, 0);
		}
	}

	// public void stop() {
	// System.out.println("stop");
	// body.setLinearVelocity(0, 0);
	// }

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
