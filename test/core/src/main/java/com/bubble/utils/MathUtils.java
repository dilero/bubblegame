package com.bubble.utils;

import com.badlogic.gdx.physics.box2d.Body;

public class MathUtils {
	
	public static float findLeftCornerX(float middleX, float width ) {
		return middleX-(width/2);
		
	}
	
	public static float findLeftCornerY(float middleY, float height ) {
		return middleY-(height/2);
	}
	

}
