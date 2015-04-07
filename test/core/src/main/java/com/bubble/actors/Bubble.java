package com.bubble.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
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

	public Bubble(World world, float rad) {
		super(world, texture);
		body = GameObjectFactory.getInstance().createBubble(world, rad);
		radius = rad;
		body.setUserData(this);
		float leftCornerX = MathUtils.findLeftCornerX(Constants.BUBBLE_X,
				Constants.BUBBLE_FIRST_RADIUS*2);
		float leftCornerY = MathUtils.findLeftCornerY(Constants.BUBBLE_Y,
				Constants.BUBBLE_FIRST_RADIUS*2);
		textureRegionBounds = new Rectangle(leftCornerX, leftCornerY,
				Constants.BUBBLE_FIRST_RADIUS * 2,
				Constants.BUBBLE_FIRST_RADIUS * 2);
	}

	public void jump() {
		System.out.println("jump");
		// bounce from ground
		Vector2 curVelocity = body.getLinearVelocity();
		body.setLinearVelocity(curVelocity.x, -curVelocity.y);

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

		if (!bodyInBounds().equals(BoundEnum.INBOUND)) {
			Vector2 curVelocity = body.getLinearVelocity();
			body.setLinearVelocity(-curVelocity.x, curVelocity.y);
		}
		float leftCornerX = MathUtils.findLeftCornerX(body.getPosition().x,
				Constants.BUBBLE_FIRST_RADIUS*2);
		float leftCornerY = MathUtils.findLeftCornerY(body.getPosition().y,
				Constants.BUBBLE_FIRST_RADIUS*2);
		textureRegionBounds.setX(leftCornerX);
		textureRegionBounds.setY(leftCornerY);
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
		batch.draw(textureRegion, textureRegionBounds.x, textureRegionBounds.y,
				Constants.BUBBLE_FIRST_RADIUS * 2,
				Constants.BUBBLE_FIRST_RADIUS * 2);
	}

	// @Override
	// public void draw(Batch batch, float parentAlpha) {
	// super.draw(batch, parentAlpha);
	// // Vector2 pos = body.getWorldCenter();
	// // ShapeRenderer shapeRenderer= new ShapeRenderer();
	// // shapeRenderer.begin(ShapeType.Filled);
	// // shapeRenderer.setColor(color);
	// // shapeRenderer.circle(pos.x, pos.y, radius);
	// // shapeRenderer.end();
	//
	// }

}
