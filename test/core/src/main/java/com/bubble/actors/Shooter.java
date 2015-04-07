package com.bubble.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.bubble.enums.BoundEnum;
import com.bubble.utils.Constants;
import com.bubble.utils.GameObjectFactory;
import com.bubble.utils.MathUtils;

public class Shooter extends GameActor {

	private boolean shotting;
	private boolean leftMove = false;
	private boolean rightMove = false;
	private final TextureRegion textureRegion;
	private Rectangle textureRegionBounds;

	public Shooter(World world) {
		super(world);
		body = GameObjectFactory.getInstance().createShooter(world);
		body.setUserData(this);
		Texture texture = new Texture(
				Gdx.files.internal(Constants.SHOOTER_IMAGE_PATH));
		textureRegion = new TextureRegion(texture);
		float leftCornerX = MathUtils.findLeftCornerX(Constants.SHOOTER_X,
				Constants.SHOOTER_WIDTH);
		float leftCornerY = MathUtils.findLeftCornerY(Constants.SHOOTER_Y,
				Constants.SHOOTER_HEIGHT);
		textureRegionBounds = new Rectangle(leftCornerX, leftCornerY,
				Constants.SHOOTER_WIDTH, Constants.SHOOTER_HEIGHT);
	}

	public void moveRight(float delta) {
		if (!BoundEnum.RIGTBOUND.equals(bodyInBounds())) {
			float lenght = delta * Constants.SHOOTER_STEP_SIZE;
			float positionX = body.getPosition().x;
			float newX = positionX + lenght;
			float leftCornernewX = MathUtils.findLeftCornerX(newX ,Constants.SHOOTER_WIDTH);
			float positionY = body.getPosition().y;
			body.setTransform(newX, positionY, 0);
			textureRegionBounds.setX(leftCornernewX);
		}
	}

	public void moveLeft(float delta) {
		if (!BoundEnum.LEFTBOUND.equals(bodyInBounds())) {
			float lenght = delta * Constants.SHOOTER_STEP_SIZE;
			float positionX = body.getPosition().x;
			float newX = positionX - lenght;
			float leftCornernewX = MathUtils.findLeftCornerX(newX ,Constants.SHOOTER_WIDTH);
			float positionY = body.getPosition().y;
			body.setTransform(newX, positionY, 0);
			textureRegionBounds.setX(leftCornernewX);
		}
	}

	@Override
	public void act(float delta) {
		if (leftMove) {
			moveLeft(delta);
		}
		if (rightMove) {
			moveRight(delta);
		}
		super.act(delta);
	}

	@Override
	public BoundEnum bodyInBounds() {
		if (body.getPosition().x - Constants.SHOOTER_WIDTH / 2 < 0)
			return BoundEnum.LEFTBOUND;
		else if (body.getPosition().x + Constants.SHOOTER_WIDTH / 2 > Constants.APP_WIDTH)
			return BoundEnum.RIGTBOUND;
		else
			return BoundEnum.INBOUND;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(textureRegion, textureRegionBounds.x, textureRegionBounds.y,
				Constants.SHOOTER_WIDTH, Constants.SHOOTER_HEIGHT);
	}

	public boolean isShotting() {
		return shotting;
	}

	public void setShotting(boolean shotting) {
		this.shotting = shotting;
	}

	public boolean isLeftMove() {
		return leftMove;
	}

	public void setLeftMove(boolean leftMove) {
		this.leftMove = leftMove;
	}

	public boolean isRightMove() {
		return rightMove;
	}

	public void setRightMove(boolean rightMove) {
		this.rightMove = rightMove;
	}
}