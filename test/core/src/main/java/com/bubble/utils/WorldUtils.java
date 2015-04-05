package com.bubble.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.bubble.box2d.FloorUserData;
import com.bubble.box2d.ShooterUserData;

public class WorldUtils {
    public static World createWorld() {
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body createFloor(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.FLOOR_X, Constants.FLOOR_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.FLOOR_WIDTH / 2, Constants.FLOOR_HEIGHT / 2);
        body.createFixture(shape, Constants.FLOOR_DENSITY);        
        body.setUserData(new FloorUserData());
        shape.dispose();
        return body;

    }
    
    public static Body createShooter(World world) {
        BodyDef bodyDef = new BodyDef();
        //TODO bu kinematik mi olacak?
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(new Vector2(Constants.SHOOTER_X, Constants.SHOOTER_Y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.SHOOTER_WIDTH / 2, Constants.SHOOTER_HEIGHT / 2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, Constants.SHOOTER_DENSITY);
        body.resetMassData();
        body.setUserData(new ShooterUserData());
        shape.dispose();
        return body;
    }
    
//    public static Body createBubble(World world) {
//        BodyDef bodyDef = new BodyDef();
//        //TODO bu kinematik mi olacak?
//        bodyDef.type = BodyDef.BodyType.KinematicBody;
//        bodyDef.position.set(new Vector2(Constants.SHOOTER_X, Constants.SHOOTER_Y));
//        PolygonShape shape = new PolygonShape();
//        shape.setRadius(radius);(Constants.SHOOTER_WIDTH / 2, Constants.SHOOTER_HEIGHT / 2);
//        Body body = world.createBody(bodyDef);
//        body.createFixture(shape, Constants.SHOOTER_DENSITY);
//        body.resetMassData();
//        body.setUserData(new ShooterUserData());
//        shape.dispose();
//        return body;
//    }

}
