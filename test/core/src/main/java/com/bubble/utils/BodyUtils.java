package com.bubble.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.bubble.actors.Beam;
import com.bubble.actors.Bubble;
import com.bubble.actors.Ceiling;
import com.bubble.actors.Floor;
import com.bubble.actors.GameActor;
import com.bubble.actors.Shooter;

public class BodyUtils {
	
	public static boolean bodyIsFloor(Body body) {
		GameActor userData = (GameActor) body.getUserData();

		return userData != null
				&& userData instanceof Floor;
	}

	public static boolean bodyIsCeiling(Body body) {
		GameActor userData = (GameActor) body.getUserData();

		return userData != null
				&& userData instanceof Ceiling;
	}

	public static boolean bodyIsBubble(Body body) {
		GameActor userData = (GameActor) body.getUserData();

		return userData != null
				&& userData instanceof Bubble;
	}

	public static boolean bodyIsShooter(Body body) {
		GameActor userData = (GameActor) body.getUserData();

		return userData != null
				&& userData instanceof Shooter;
	}

	public static boolean bodyIsBeam(Body body) {
		GameActor userData = (GameActor) body.getUserData();

		return userData != null
				&& userData instanceof Beam;
	}

}