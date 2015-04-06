package com.bubble.actors;

import com.badlogic.gdx.physics.box2d.World;
import com.bubble.utils.GameObjectFactory;

public class Floor extends GameActor {

	public Floor(World world) {
		super(world);
		body = GameObjectFactory.getInstance().createFloor(world);
		body.setUserData(this);
		// TODO Auto-generated constructor stub
	}

}