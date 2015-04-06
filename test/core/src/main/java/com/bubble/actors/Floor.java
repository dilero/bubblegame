package com.bubble.actors;

import com.badlogic.gdx.physics.box2d.World;
import com.bubble.utils.WorldUtils;

public class Floor extends GameActor {

	public Floor(World world) {
		super(world);
		body = WorldUtils.createFloor(world);
		body.setUserData(this);
		// TODO Auto-generated constructor stub
	}

}