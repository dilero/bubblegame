package com.bubble.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.bubble.utils.Constants;
import com.bubble.utils.GameObjectFactory;
import com.bubble.utils.MathUtils;

public class Beam extends GameActor {
	private boolean destroy = false;
	private float currentBeamHeight = Constants.BEAM_HEIGHT;
	private float currentBeamWidth = Constants.BEAM_WIDTH;
	private static Texture texture = new Texture(
			Gdx.files.internal(Constants.BEAM_IMAGE_PATH));

	private Shooter shooter;
	public Beam(World world, Shooter shooter) {
		super(world, texture);
		this.shooter = shooter;
		activate();
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
		PolygonShape shape = (PolygonShape) body.getFixtureList().get(0)
				.getShape();
		if (!destroy) {
			currentBeamHeight = currentBeamHeight
					+ (currentBeamHeight * Constants.BEAM_STEP_SIZE);
			shape.setAsBox(Constants.BEAM_WIDTH, currentBeamHeight);
//			textureRegionBounds.height=currentBeamHeight;
		} else {
			currentBeamHeight = 0.1f;
			currentBeamWidth = 0.1f;
			shape.setAsBox(currentBeamWidth, currentBeamHeight);
//			textureRegionBounds.height=currentBeamHeight;
//			textureRegionBounds.width=currentBeamWidth;
		}
		body.createFixture(shape, Constants.BEAM_DENSITY);

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(textureRegion, textureRegionBounds.x, textureRegionBounds.y,
				currentBeamWidth, currentBeamHeight);
	}

	@Override
	public void activate() {
		super.activate();
		body = GameObjectFactory.getInstance().createBeam(world, shooter);
		body.setUserData(this);
		
	}
}
