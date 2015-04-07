package com.bubble.actors;

import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.bubble.utils.Constants;
import com.bubble.utils.GameObjectFactory;

public class Beam extends GameActor {

	private float currentBeamHeight = Constants.BEAM_HEIGHT;

	public Beam(World world, Shooter shooter) {
		super(world);
		body = GameObjectFactory.getInstance().createBeam(world, shooter);
		body.setUserData(this);
	}

	@Override
	public void act(float delta) {
		currentBeamHeight =  (currentBeamHeight + (currentBeamHeight * 0.1f));
		PolygonShape shape = (PolygonShape) body.getFixtureList().get(0)
				.getShape();
		shape.setAsBox(Constants.BEAM_WIDTH, currentBeamHeight);
		body.createFixture(shape, Constants.BEAM_DENSITY);

	}

}
