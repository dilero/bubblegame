package com.bubble.actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.bubble.utils.Constants;
import com.bubble.utils.WorldUtils;

public class Bubble extends GameActor
{
	private float radius;

	public Bubble(World world, float rad) {
		super(world);
		body = WorldUtils.createBubble(world, rad);
		radius =rad;
		body.setUserData(this);
	}

	
	public void jump() {
		System.out.println("jump");
		// bounce from ground
		Vector2 curVelocity = body.getLinearVelocity();
		body.setLinearVelocity(curVelocity.x, -curVelocity.y);

	}

	
	@Override
	public void act(float delta) {
		float bubbleY = body.getPosition().y;
		float bubbleX = body.getPosition().x;
		
		if(bubbleY >= (Constants.SHOOTER_Y+radius *4)) {
			Vector2 curVelocity = body.getLinearVelocity();
			body.setLinearVelocity(curVelocity.x, -curVelocity.y);
		} 
		
		if(bubbleX+radius >= (Constants.APP_WIDTH) || bubbleX <= radius) {
			Vector2 curVelocity = body.getLinearVelocity();
			body.setLinearVelocity(-curVelocity.x, curVelocity.y);
		}
		
		super.act(delta);
	}
	
	public boolean isBigBubble() {
		return radius == Constants.BUBBLE_FIRST_RADIUS;
	}
}