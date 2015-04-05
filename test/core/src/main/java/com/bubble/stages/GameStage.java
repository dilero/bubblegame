package com.bubble.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bubble.actors.Bubble;
import com.bubble.actors.Floor;
import com.bubble.actors.Shooter;
import com.bubble.utils.BodyUtils;
import com.bubble.utils.Constants;
import com.bubble.utils.WorldUtils;

public class GameStage extends Stage implements ContactListener {
	private World world;
	private Floor floor;
	private Shooter shooter;
	private Bubble bubble;

	private final float TIME_STEP = 1 / 300f;
	private float accumulator = 0f;

	private OrthographicCamera camera;
	private Box2DDebugRenderer renderer;

	private Rectangle screenRightSide;
	private Rectangle screenLeftSide;
	private Rectangle screenUpSide;

	private Vector3 touchPoint;

	public GameStage() {
		setUpWorld();
		setupCamera();
		setupTouchControlAreas();
		renderer = new Box2DDebugRenderer();
	}

	private void setupTouchControlAreas() {
		touchPoint = new Vector3();
		screenRightSide = new Rectangle(getCamera().viewportWidth / 2, 0,
				getCamera().viewportWidth / 2, getCamera().viewportHeight / 2);
		screenLeftSide = new Rectangle(0, 0, getCamera().viewportWidth / 2,
				getCamera().viewportHeight / 2);
		screenUpSide = new Rectangle(0, getCamera().viewportHeight / 2,
				getCamera().viewportWidth, getCamera().viewportHeight / 2);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {

		// Need to get the actual coordinates
		translateScreenToWorldCoordinates(x, y);

		if (rightSideTouched(touchPoint.x, touchPoint.y)) {
			shooter.setRightMove(true);
		} else if (leftSideTouched(touchPoint.x, touchPoint.y)) {
			shooter.setLeftMove(true);
		} else if (upSideTouched(touchPoint.x, touchPoint.y)) {
			shooter.shot();
		}

		return super.touchDown(x, y, pointer, button);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		shooter.setRightMove(false);
		shooter.setLeftMove(false);
		return super.touchUp(screenX, screenY, pointer, button);
	}

	private boolean rightSideTouched(float x, float y) {
		return screenRightSide.contains(x, y);
	}

	private boolean leftSideTouched(float x, float y) {
		return screenLeftSide.contains(x, y);
	}

	private boolean upSideTouched(float x, float y) {
		return screenUpSide.contains(x, y);
	}

	/**
	 * Helper function to get the actual coordinates in my world
	 * 
	 * @param x
	 * @param y
	 */
	private void translateScreenToWorldCoordinates(int x, int y) {
		getCamera().unproject(touchPoint.set(x, y, 0));
	}

	private void setUpWorld() {
		world = WorldUtils.createWorld();
		// Let the world now you are handling contacts
		world.setContactListener(this);
		setUpFloor();
		setUpShooter();
		setUpBubble();
	}

	private void setUpBubble() {
		// TODO Auto-generated method stub

	}

	private void setUpFloor() {
		floor = new Floor(WorldUtils.createFloor(world));
		addActor(floor);
	}

	private void setUpShooter() {
		shooter = new Shooter(WorldUtils.createShooter(world));
		addActor(shooter);
	}

	private void setupCamera() {
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH,
				Constants.VIEWPORT_HEIGHT);
		camera.position.set(camera.viewportWidth / 2,
				camera.viewportHeight / 2, 0f);
		camera.update();
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		// Fixed timestep
		accumulator += delta;

		while (accumulator >= delta) {
			world.step(TIME_STEP, 6, 2);
			accumulator -= TIME_STEP;
		}

		// TODO: Implement interpolation

	}

	@Override
	public void draw() {
		super.draw();
		renderer.render(world, camera.combined);
	}

	@Override
	public void beginContact(Contact contact) {

		Body a = contact.getFixtureA().getBody();
		Body b = contact.getFixtureB().getBody();

		// if ((BodyUtils.bodyIsShooter(a) && BodyUtils.bodyIsFloor(b)) ||
		// (BodyUtils.bodyIsFloor(a) && BodyUtils.bodyIsShooter(b))) {
		// shooter.landed();
		// }

	}

	@Override
	public void endContact(Contact contact) {

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

}
