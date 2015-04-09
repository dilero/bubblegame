package com.bubble.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.bubble.enums.GameActorEnum;
import com.bubble.utils.Constants;
import com.bubble.utils.MathUtils;

public class Floor extends GameActor {
	private static Texture texture = new Texture(
			Gdx.files.internal(Constants.FLOOR_IMAGE_PATH));

	public Floor(World world) {
		super(world, texture);
		
		setX(Constants.FLOOR_X);
		setY(Constants.FLOOR_Y);
		setWidth(Constants.FLOOR_WIDTH);
		setHeight(Constants.FLOOR_HEIGHT);
		density = Constants.FLOOR_DENSITY;
		
		activate();
		
		float leftCornerX = MathUtils.findLeftCornerX(Constants.FLOOR_X,
				Constants.FLOOR_WIDTH);
		float leftCornerY = MathUtils.findLeftCornerY(Constants.FLOOR_Y,
				Constants.FLOOR_HEIGHT);
		textureRegionBounds = new Rectangle(leftCornerX, leftCornerY,
				Constants.FLOOR_WIDTH, Constants.FLOOR_HEIGHT);
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(textureRegion, textureRegionBounds.x, textureRegionBounds.y,
				Constants.FLOOR_WIDTH, Constants.FLOOR_HEIGHT);
	}
	
	protected void activate() {
		activate(GameActorEnum.FLOOR);
	}
}