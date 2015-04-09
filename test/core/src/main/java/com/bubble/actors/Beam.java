package com.bubble.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.bubble.enums.GameActorEnum;
import com.bubble.utils.Constants;
import com.bubble.utils.MathUtils;

public class Beam extends GameActor {

	private static Texture texture = new Texture(
			Gdx.files.internal(Constants.BEAM_IMAGE_PATH));

	public Beam(World world, Shooter shooter, boolean activate) {
		super(world, texture, shooter.getPosition().x, shooter.getPosition().y+ shooter.getHeight()/2, 
				Constants.BEAM_WIDTH, Constants.BEAM_HEIGHT, Constants.BEAM_DENSITY, activate );
		
		float leftCornerX = MathUtils.findLeftCornerX(body.getPosition().x,
				Constants.BEAM_WIDTH);
		float leftCornerY = MathUtils.findLeftCornerY(body.getPosition().y,
				Constants.BEAM_HEIGHT);
		textureRegionBounds = new Rectangle(leftCornerX, leftCornerY,
				Constants.BEAM_WIDTH, Constants.BEAM_HEIGHT);
	}

	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if(body != null) {
			if(body.getPosition().y > Constants.APP_HEIGHT) {
				inactivate();
			}
		}

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		float leftCornerX = MathUtils.findLeftCornerX(body.getPosition().x,
				getWidth());
		float leftCornerY = MathUtils.findLeftCornerY(body.getPosition().y,
				getHeight());
		textureRegionBounds.setX(leftCornerX);
		textureRegionBounds.setY(leftCornerY);
		batch.draw(textureRegion, textureRegionBounds.x, textureRegionBounds.y,
				getWidth(), getHeight());
	}

	protected void activate() {
		activate(GameActorEnum.BEAM);
	}
	
	public void setCoordinates(Shooter shooter) {
		setX(shooter.getPosition().x);
		setY(shooter.getPosition().y+ shooter.getHeight()/2);
	}

}
