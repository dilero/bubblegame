package com.bubble.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.bubble.box2d.CeilingUserData;

public class Ceiling extends GameActor {

	public Ceiling(Body body) {
		super(body);
		// TODO Auto-generated constructor stub
	}
    @Override
    public CeilingUserData getUserData() {
        return (CeilingUserData) userData;
    }

}
