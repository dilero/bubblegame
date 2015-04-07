package com.bubble.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bubble.utils.Constants;

public class LifeBox extends Actor {

	private final TextureRegion textureRegion;
	private Rectangle textureRegionBounds;
	private static float imageX = Constants.LIFE_X;

	public LifeBox(int reaminingLife) {
		switch (reaminingLife) {
		case 2:

			break;
		case 1:

			break;

		default:
			break;
		}
		Texture texture = new Texture(
				Gdx.files.internal(Constants.LIFE_IMAGE_PATH));
		textureRegion = new TextureRegion(texture);
		textureRegionBounds = new Rectangle(imageX, Constants.LIFE_Y,
				Constants.LIFE_WIDTH, Constants.LIFE_HEIGHT);
		imageX = imageX * 2;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(textureRegion, textureRegionBounds.x, textureRegionBounds.y,
				Constants.LIFE_WIDTH, Constants.LIFE_HEIGHT);
	}

}
