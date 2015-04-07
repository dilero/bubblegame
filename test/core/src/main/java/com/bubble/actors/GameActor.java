package com.bubble.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bubble.enums.BoundEnum;

public abstract class GameActor extends Actor {
	static int latestID = 0;
	protected int id;
	protected World world;
	protected Body body;
	protected TextureRegion textureRegion;
	protected Rectangle textureRegionBounds;
	protected boolean destroyBody;

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

	public GameActor(World world, Texture texture) {
		textureRegion = new TextureRegion(texture);
		this.world = world;
		id = latestID;
		latestID++;
	}

	public boolean compareID(int id) {
		return this.id == id;

	}

	public Vector2 getPosition() {
		return body.getPosition();
	}

	public void inactivate() {
		if(body != null) {
			world.destroyBody(body);
			body = null;
			setVisible(false);
		}
	}

	public void activate(float x, float y) {
		setVisible(true);
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
	}


}