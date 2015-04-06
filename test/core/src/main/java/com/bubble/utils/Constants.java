package com.bubble.utils;

import com.badlogic.gdx.math.Vector2;

public class Constants {
	public static final int APP_WIDTH = 800;
	public static final int APP_HEIGHT = 480;
	
	public static final int VIEWPORT_WIDTH = 800;
	public static final int VIEWPORT_HEIGHT = 480;
	
	public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);
	
	public static final float FLOOR_WIDTH = APP_WIDTH;
	public static final float FLOOR_HEIGHT = APP_HEIGHT / 20;
	public static final float FLOOR_X = APP_WIDTH / 2;
	public static final float FLOOR_Y = FLOOR_HEIGHT / 2;
	public static final float FLOOR_DENSITY = 0f;
	
	public static final float SHOOTER_WIDTH = APP_WIDTH / 20;
	public static final float SHOOTER_HEIGHT = SHOOTER_WIDTH * 2;
	public static final float SHOOTER_X = (FLOOR_WIDTH - SHOOTER_WIDTH) / 2;
	public static final float SHOOTER_Y = FLOOR_Y + FLOOR_HEIGHT / 2
			+ SHOOTER_HEIGHT / 2;
	public static final float SHOOTER_GRAVITY_SCALE = 3f;
	public static  final float SHOOTER_DENSITY = 0.5f;
	public static final float SHOOTER_STEP_SIZE = SHOOTER_WIDTH;
	public static final Vector2 SHOOTER_MOVE_RIGHT_LINEAR_IMPULSE = new Vector2(
			SHOOTER_WIDTH, 0);
	public static final Vector2 SHOOTER_MOVE_LEFT_LINEAR_IMPULSE = new Vector2(
			-SHOOTER_WIDTH, 0);
	public static final Vector2 SHOOTER_SHOT_LINEAR_IMPULSE = new Vector2(0,
			13f);
	
	public static final float BUBBLE_FIRST_RADIUS = SHOOTER_WIDTH;
	public static final float BUBBLE_X = SHOOTER_X+SHOOTER_WIDTH*4;
	public static final float BUBBLE_Y = SHOOTER_Y+BUBBLE_FIRST_RADIUS *4;
	public static final Vector2 BUBBLE_JUMP_LINEAR_IMPULSE = new Vector2(
			BUBBLE_X, SHOOTER_WIDTH);
	public static  final float BUBBLE_DENSITY = 0.5f;
	public static final float BUBBLE_GRAVITY_SCALE = 40f;
	
	public static final Vector2 BEAM_VELOCITY = new Vector2(
			0, 100);
	public static final float BEAM_WIDTH = SHOOTER_WIDTH / 5;
	public static final float BEAM_HEIGHT = BEAM_WIDTH * 2;
	public static  final float BEAM_DENSITY = 0.1f;
	public static final float BEAM_GRAVITY_SCALE = 0f;
	
	public static final short INIT_HEALTH = 3;
}