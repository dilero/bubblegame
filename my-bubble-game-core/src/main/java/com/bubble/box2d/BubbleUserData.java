package com.bubble.box2d;

import com.badlogic.gdx.math.Vector2;
import com.bubble.enums.UserDataType;
import com.bubble.utils.Constants;

public class BubbleUserData extends UserData {
	 private Vector2 jumpingLinearImpulse;
	public BubbleUserData() {
        super();
        userDataType = UserDataType.BUBBLE;
        setJumpingLinearImpulse(Constants.BUBBLE_JUMP_LINEAR_IMPULSE);
    }
	public Vector2 getJumpingLinearImpulse() {
		return jumpingLinearImpulse;
	}
	public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse) {
		this.jumpingLinearImpulse = jumpingLinearImpulse;
	}
}
