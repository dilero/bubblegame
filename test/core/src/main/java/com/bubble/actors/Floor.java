package com.bubble.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.bubble.box2d.FloorUserData;

public class Floor extends GameActor {

	public Floor(Body body) {
		super(body);
		// TODO Auto-generated constructor stub
	}
    @Override
    public FloorUserData getUserData() {
        return (FloorUserData) userData;
    }

}
