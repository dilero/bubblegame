package com.bubble.actors;

import com.badlogic.gdx.physics.box2d.World;
import com.bubble.utils.GameObjectFactory;

public class Beam extends GameActor
{

	public Beam(World world, Shooter shooter) {
		super(world);
		body = GameObjectFactory.getInstance().createBeam(world, shooter);
		body.setUserData(this);
	}


}
