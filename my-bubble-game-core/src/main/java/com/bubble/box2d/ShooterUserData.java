package com.bubble.box2d;

import com.badlogic.gdx.math.Vector2;
import com.bubble.enums.UserDataType;
import com.bubble.utils.Constants;

public class ShooterUserData extends UserData {
    private Vector2 moveRightLinearImpulse;
    private Vector2 moveLeftLinearImpulse;
    private Vector2 shotLinearImpulse;

    public ShooterUserData() {
        super();
        userDataType = UserDataType.SHOOTER;
        moveRightLinearImpulse=Constants.SHOOTER_MOVE_RIGHT_LINEAR_IMPULSE;
        moveLeftLinearImpulse=Constants.SHOOTER_MOVE_LEFT_LINEAR_IMPULSE;
        shotLinearImpulse = Constants.SHOOTER_SHOT_LINEAR_IMPULSE;
        
    }

	public Vector2 getMoveRightLinearImpulse() {
		return moveRightLinearImpulse;
	}

	public void setMoveRightLinearImpulse(Vector2 moveRightLinearImpulse) {
		this.moveRightLinearImpulse = moveRightLinearImpulse;
	}

	public Vector2 getMoveLeftLinearImpulse() {
		return moveLeftLinearImpulse;
	}

	public void setMoveLeftLinearImpulse(Vector2 moveLeftLinearImpulse) {
		this.moveLeftLinearImpulse = moveLeftLinearImpulse;
	}

	public Vector2 getShotLinearImpulse() {
		return shotLinearImpulse;
	}

	public void setShotLinearImpulse(Vector2 shotLinearImpulse) {
		this.shotLinearImpulse = shotLinearImpulse;
	}


}
