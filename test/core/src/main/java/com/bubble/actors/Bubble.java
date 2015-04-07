package com.bubble.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.bubble.enums.BoundEnum;
import com.bubble.utils.Constants;
import com.bubble.utils.GameObjectFactory;
import com.bubble.utils.MathUtils;

public class Bubble extends GameActor {
	private float radius;
	private static Texture texture = new Texture(
			Gdx.files.internal(Constants.RED_BUBBLE_IMAGE_PATH));

	public Bubble(World world, float rad, float x, float y) {
		super(world, texture);
		radius = rad;
		activate(x, y);
		float leftCornerX = MathUtils.findLeftCornerX(x, radius * 2);
		float leftCornerY = MathUtils.findLeftCornerY(y, radius * 2);
		textureRegionBounds = new Rectangle(leftCornerX, leftCornerY,
				radius * 2, radius * 2);
	}

	@Override
	public BoundEnum bodyInBounds() {
		if (body.getPosition().x - radius < 0)
			return BoundEnum.LEFTBOUND;
		else if (body.getPosition().x + radius > Constants.APP_WIDTH)
			return BoundEnum.RIGTBOUND;
		else
			return BoundEnum.INBOUND;
	}

	@Override
	public void act(float delta) {

		if (body != null && !bodyInBounds().equals(BoundEnum.INBOUND)) {
			Vector2 curVelocity = body.getLinearVelocity();
			body.setLinearVelocity(-curVelocity.x, curVelocity.y);
		}
		super.act(delta);
	}

	public boolean isFirstBubble() {
		return radius == Constants.BUBBLE_FIRST_RADIUS;
	}

	// inverse proportion to radius
	public int getshotScore() {
		return (int) (Constants.BUBBLE_GENERAL_SHOT_SCORE / radius);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		float leftCornerX = MathUtils.findLeftCornerX(body.getPosition().x,
				radius * 2);
		float leftCornerY = MathUtils.findLeftCornerY(body.getPosition().y,
				radius * 2);
		textureRegionBounds.setX(leftCornerX);
		textureRegionBounds.setY(leftCornerY);
		batch.draw(textureRegion, textureRegionBounds.x, textureRegionBounds.y,
				radius * 2, radius * 2);
	}

	@Override
	public void activate(float x, float y) {
		super.activate(x, y);
		body = GameObjectFactory.getInstance()
				.createBubble(world, radius, x, y);
		body.setUserData(this);
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

}
