package com.bubble.actors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.physics.box2d.World;
import com.bubble.utils.WorldUtils;

public class ShooterTest {
	World world;
	Shooter shooter;
	float testDelta = 0.1f;

	@Before
	public void init() {
		world = WorldUtils.createWorld();
		shooter = new Shooter(world);
	}

	@Test
	public void testMoveRight() {
		float firstX = shooter.body.getPosition().x;
		float firstY = shooter.body.getPosition().y;
		shooter.moveRight(testDelta);
		float secondX = shooter.body.getPosition().x;
		float secondY = shooter.body.getPosition().y;
		Assert.assertEquals(firstY, secondY);

	}

	@Test
	public void testMoveLeft() {

	}

}