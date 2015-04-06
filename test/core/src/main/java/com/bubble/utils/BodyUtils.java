package com.bubble.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.bubble.actors.Beam;
import com.bubble.actors.Bubble;
import com.bubble.actors.Ceiling;
import com.bubble.actors.Floor;
import com.bubble.actors.GameActor;
import com.bubble.actors.Shooter;
import com.bubble.enums.BoundEnum;

public class BodyUtils {

	public static BoundEnum bodyInBounds(Body body) {
		GameActor userData = (GameActor) body.getUserData();

		if(userData instanceof Bubble)  {
			if(body.getPosition().x - Constants.SHOOTER_WIDTH / 2 < 0)
				return BoundEnum.LEFTBOUND;
			else if(body.getPosition().x + Constants.SHOOTER_WIDTH / 2 > Constants.APP_WIDTH)
				return BoundEnum.RIGTBOUND;
			else
				return BoundEnum.INBOUND;
		}

		
		return BoundEnum.INBOUND;
	}

	public static int findBubbleID(Body a, Body b) {
		
		int bubbleID = -1;
		
		if(BodyUtils.bodyIsBubble(a)) {
			GameActor userData = (GameActor) a.getUserData();
			bubbleID = userData.getID();
		} else if(BodyUtils.bodyIsBubble(b)) {
			GameActor userData = (GameActor) b.getUserData();
			bubbleID = userData.getID();
		}
		
		if(bubbleID == -1) {
			Gdx.app.log("Error", "BubbleID not found \n");
		}
		
		return bubbleID;
	}
	
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