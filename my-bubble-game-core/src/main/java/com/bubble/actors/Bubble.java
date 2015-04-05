package com.bubble.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.bubble.box2d.BubbleUserData;

public class Bubble extends GameActor
{

	public Bubble(Body body) {
		super(body);
	}

	@Override
	public BubbleUserData getUserData() {
		return (BubbleUserData) userData;
	}
	
	public void jump() {
		System.out.println("jump");
		 body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);

	}

}
