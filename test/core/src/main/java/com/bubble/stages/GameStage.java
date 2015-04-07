package com.bubble.stages;

import java.util.ArrayList;

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
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bubble.actors.Background;
import com.bubble.actors.Beam;
import com.bubble.actors.Bubble;
import com.bubble.actors.Floor;
import com.bubble.actors.LifeBox;
import com.bubble.actors.Shooter;
import com.bubble.utils.BodyUtils;
import com.bubble.utils.Constants;
import com.bubble.utils.GameObjectFactory;

public class GameStage extends Stage implements ContactListener {
	private World world;
	private Floor floor;

	private Shooter shooter;
	private ArrayList<Bubble> bubbles;
	private Beam beam;

	private short healthLeft = Constants.INIT_HEALTH - 1;
	private int score = 0;

	private final float TIME_STEP = 1 / 300f;
	private float accumulator = 0f;

	private OrthographicCamera camera;
	private Box2DDebugRenderer renderer;

	private Rectangle screenRightSide;
	private Rectangle screenLeftSide;
	private Rectangle screenUpSide;

	private Vector3 touchPoint;

	public GameStage() {
		super(new FitViewport(Constants.VIEWPORT_WIDTH,
				Constants.VIEWPORT_HEIGHT, new OrthographicCamera(
						Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT)));

		bubbles = new ArrayList<Bubble>();
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
			shootBeam();
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

	private void setUpLifeBox() {
		for (int i = 1; i <= healthLeft; i++) {
			addActor(new LifeBox());
		}

	}

	private void setUpBackground() {
		addActor(new Background());
	}

	private void setUpWorld() {
		world = GameObjectFactory.getInstance().createWorld();
		// Let the world now you are handling contacts
		world.setContactListener(this);
		setUpBackground();
		setUpFloor();
		setUpLifeBox();
		setUpShooter();
		setUpBubbles();
	}

	private void setUpBubbles() {
		Bubble bubble = new Bubble(world, Constants.BUBBLE_FIRST_RADIUS,
				Constants.BUBBLE_X, Constants.BUBBLE_Y);
		bubbles.add(bubble);
		addActor(bubble);
	}

	private void setUpFloor() {
		floor = new Floor(world);
		addActor(floor);
	}

	private void setUpShooter() {
		shooter = new Shooter(world);
		addActor(shooter);
	}

	private void setupCamera() {
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH,
				Constants.VIEWPORT_HEIGHT);
		camera.position.set(camera.viewportWidth / 2,
				camera.viewportHeight / 2, 0f);
		camera.update();
	}

	private void shootBeam() {
		if (beam == null) {
			beam = new Beam(world, shooter);
			addActor(beam);
		} else {

		}
	}

	private void bubbleShotByBeam(Bubble bubble) {
		Gdx.app.log("Info", " bubble shot \n");
		updateScoreBubbleShot(bubble);
		animateBubbleShot();

		if (bubble.isFirstBubble()) {
			createSmallBubbles(bubble);
		}

		inactivateBeam();

	}

	private void inactivateBeam() {
		// TODO
		Gdx.app.log("Info", " beam inactivated \n");
		beam.setDestroyBody(true);
	}


	private void createSmallBubbles(Bubble bubble) {
		float newRadius = bubble.getRadius() / 2;
		float exX = bubble.getPosition().x;
		float exY = bubble.getPosition().y;
		bubble.inactivate();
		bubbles.clear();
		Bubble newBubble1 = new Bubble(world, newRadius, exX + newRadius, exY);
		bubbles.add(newBubble1);
		addActor(newBubble1);
		Bubble newBubble2 = new Bubble(world, newRadius, exX - newRadius, exY);
		bubbles.add(newBubble2);
		addActor(newBubble2);
	}

	private void animateBubbleShot() {
		// TODO
	}

	private void updateScoreBubbleShot(Bubble bubble) {
		this.score = +bubble.getshotScore();
	}

	private void shooterHitByBall() {
		animateLoseHealth();

		boolean healthLeft = decreaseHealth();
		if (healthLeft) {
			setupNewTry();
		} else {
			animateGameover();
		}
	}

	private void animateLoseHealth() {
		// TODO
	}

	private boolean decreaseHealth() {
		healthLeft--;

		decreaseHealthGUI();

		return healthLeft > 0 ? true : false;
	}

	private void decreaseHealthGUI() {
		// TODO
	}

	private void animateGameover() {
		// TODO
	}

	private void setupNewTry() {
		shooter.setGoToInitScheduled(true);
		if (beam != null) {
			beam.setDestroyBody(true);
		}

		for (Bubble bubble : bubbles) {
			bubble.setDestroyBody(true);
		}

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

		if ((BodyUtils.bodyIsShooter(a) && BodyUtils.bodyIsBubble(b) || (BodyUtils
				.bodyIsBubble(a) && BodyUtils.bodyIsShooter(b)))) {
			shooterHitByBall();
		} else if (BodyUtils.bodyIsBeam(a) && BodyUtils.bodyIsBubble(b)) {
			Bubble bubble = (Bubble) b.getUserData();
			bubbleShotByBeam(bubble);
		} else if (BodyUtils.bodyIsBubble(a) && BodyUtils.bodyIsBeam(b)) {
			Bubble bubble = (Bubble) a.getUserData();
			bubbleShotByBeam(bubble);

		} else if (BodyUtils.bodyIsCeiling(a) && BodyUtils.bodyIsBeam(b)
				|| BodyUtils.bodyIsCeiling(b) && BodyUtils.bodyIsBeam(a)) {
			beam.setDestroyBody(true);
		}

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
