package com.bubble.actors;

import com.badlogic.gdx.physics.box2d.World;
import com.bubble.utils.WorldUtils;

public class Beam extends GameActor
{

	public Beam(World world, Shooter shooter) {
		super(world);
		body = WorldUtils.createBeam(world, shooter);
		body.setUserData(this);
	}


}
