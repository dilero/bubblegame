package com.bubble.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bubble.enums.BoundEnum;
import com.bubble.enums.GameActorEnum;
import com.bubble.utils.GameObjectFactory;

public abstract class GameActor extends Actor {
	static int latestID = 0;
	protected int id;
	protected World world;
	protected Body body;
	protected TextureRegion textureRegion;
	protected Rectangle textureRegionBounds;
	protected boolean destroyBody;
	protected float density;
	protected boolean goToInitScheduled = false;
	protected boolean activationScheduled = false;
	
	public boolean isActivationScheduled() {
		return activationScheduled;
	}

	public void setActivationScheduled(boolean activationScheduled) {
		this.activationScheduled = activationScheduled;
	}

	public boolean isDestroyBody() {
		return destroyBody;
	}

	public void setDestroyBody(boolean destroyBody) {
		this.destroyBody = destroyBody;
	}

	public GameActor(World world) {
		this.world = world;
		id = latestID;
		latestID++;

	}

	public GameActor(World world, Texture texture, float x, float y, float width, float height, float density, boolean activate) {
		textureRegion = new TextureRegion(texture);
		this.world = world;
		id = latestID;
		latestID++;
		
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		this.density = density;
		
		if(activate) {
			activate();
		}
		
		
	}

	public boolean compareID(int id) {
		return this.id == id;
	}

	public Vector2 getPosition() {
		return body.getPosition();
	}

	protected void inactivate() {
		if(body != null) {
			setVisible(false);
			world.destroyBody(body);
			body = null;
		}
	}

	protected void activate() {
		// overwrite this method
	}
	
	protected void activate(GameActorEnum actorType) {
		setVisible(true);
		body = GameObjectFactory.getInstance().createBody(actorType,world, getX(), getY(), getWidth(), getHeight(), density);
		body.setUserData(this);
	}
	public int getID() {
		return id;
	}

	public BoundEnum bodyInBounds() {
		return BoundEnum.INBOUND;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if(destroyBody) {
			inactivate();
			body = null;
			destroyBody = false;
		}
		
		if (goToInitScheduled) {
			goInitPosition();
			goToInitScheduled = false;
		}
		
		if(activationScheduled) {
			activate();
			activationScheduled = false;
		}
	}
	
	public void goInitPosition() {
		// Do nothing
	}

	public boolean hasNoBody() {
		return body == null;
	}

	public boolean isGoToInitScheduled() {
		return goToInitScheduled;
	}

	public void setGoToInitScheduled(boolean goToInitScheduled) {
		this.goToInitScheduled = goToInitScheduled;
	}

}