package com.bubble.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.bubble.actors.Shooter;

public class WorldUtils {
	private static WorldUtils instance;
	
	 private WorldUtils(){}
	
	 public static WorldUtils getInstanceUsingDoubleLocking(){
		    if(instance == null){
		        synchronized (WorldUtils.class) {
		            if(instance == null){
		                instance = new WorldUtils();
		            }
		        }
		    }
		    return instance;
		}
	
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
        shape.dispose();
        
        return body;

    }
    
    public static Body createShooter(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.SHOOTER_X, Constants.SHOOTER_Y));
        bodyDef.gravityScale = Constants.SHOOTER_GRAVITY_SCALE;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.SHOOTER_WIDTH / 2, Constants.SHOOTER_HEIGHT / 2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, Constants.SHOOTER_DENSITY);
        body.resetMassData();
        
        shape.dispose();
        
        return body;
    }
    
    public static Body createBubble(World world, float radius) {
        BodyDef bodyDef = new BodyDef();
       bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.BUBBLE_X, Constants.BUBBLE_Y));
        bodyDef.gravityScale = Constants.BUBBLE_GRAVITY_SCALE;
        Vector2 initVelocity = new Vector2((Constants.BUBBLE_X-2*radius), Constants.FLOOR_Y).sub(new Vector2(Constants.BUBBLE_X, Constants.BUBBLE_Y));
        bodyDef.linearVelocity.set(initVelocity);
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, Constants.BUBBLE_DENSITY);
        
//        FixtureDef fixtureDef = new FixtureDef();  
//        fixtureDef.shape = shape;  
//        fixtureDef.density = 1.0f;  
//        fixtureDef.friction = 0.0f;  
//        fixtureDef.restitution = 1;  
//        body.createFixture(fixtureDef);  
        
        body.resetMassData();
        shape.dispose();
        
        
        return body;
    }
    
    public static Body createBeam(World world, Shooter shooter) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(shooter.getPosition().add(0f, Constants.SHOOTER_HEIGHT/2));
        
        bodyDef.gravityScale = Constants.BEAM_GRAVITY_SCALE;
        bodyDef.linearVelocity.set(Constants.BEAM_VELOCITY);
        
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.BEAM_WIDTH / 2, Constants.BEAM_HEIGHT / 2);
        Body body = world.createBody(bodyDef);

        body.createFixture(shape, Constants.BEAM_DENSITY);
        body.resetMassData();
        
        shape.dispose();

        
        return body;
    }
    

}