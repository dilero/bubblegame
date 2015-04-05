package com.bubble.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.bubble.box2d.UserData;
import com.bubble.enums.BoundEnum;
import com.bubble.enums.UserDataType;

public class BodyUtils {

	public static BoundEnum bodyInBounds(Body body) {
		UserData userData = (UserData) body.getUserData();

		switch (userData.getUserDataType()) {
		case BUBBLE:
		case SHOOTER:
			if(body.getPosition().x - Constants.SHOOTER_WIDTH / 2 < 0)
				return BoundEnum.LEFTBOUND;
			else if(body.getPosition().x + Constants.SHOOTER_WIDTH / 2 > Constants.APP_WIDTH)
				return BoundEnum.RIGTBOUND;
			else
				return BoundEnum.INBOUND;
		}
		return BoundEnum.INBOUND;
	}

	public static boolean bodyIsFloor(Body body) {
		UserData userData = (UserData) body.getUserData();

		return userData != null
				&& userData.getUserDataType() == UserDataType.FLOOR;
	}

	public static boolean bodyIsCeiling(Body body) {
		UserData userData = (UserData) body.getUserData();

		return userData != null
				&& userData.getUserDataType() == UserDataType.CEILING;
	}

	public static boolean bodyIsBubble(Body body) {
		UserData userData = (UserData) body.getUserData();

		return userData != null
				&& userData.getUserDataType() == UserDataType.BUBBLE;
	}

	public static boolean bodyIsShooter(Body body) {
		UserData userData = (UserData) body.getUserData();

		return userData != null
				&& userData.getUserDataType() == UserDataType.SHOOTER;
	}

	public static boolean bodyIsBeam(Body body) {
		UserData userData = (UserData) body.getUserData();

		return userData != null
				&& userData.getUserDataType() == UserDataType.BEAM;
	}

}
