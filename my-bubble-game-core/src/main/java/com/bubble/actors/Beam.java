package com.bubble.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.bubble.box2d.BeamUserData;

public class Beam extends GameActor
{

	public Beam(Body body) {
		super(body);
	}

	@Override
	public BeamUserData getUserData() {
		return (BeamUserData) userData;
	}

}
