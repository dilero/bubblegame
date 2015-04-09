package com.bubble.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.bubble.actors.Shooter;
import com.bubble.enums.GameActorEnum;

public class GameObjectFactory {
	private static GameObjectFactory instance;
	
	 private GameObjectFactory(){}
	
	 public static GameObjectFactory getInstance(){
		    if(instance == null){
		        synchronized (GameObjectFactory.class) {
		            if(instance == null){
		                instance = new GameObjectFactory();
		            }
		        }
		    }
		    return instance;
		}
	
    public World createWorld() {
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public Body createBody(GameActorEnum actorType, World world, float x, float y, float width, float height, float density) {
    	switch(actorType) {
		case BEAM:
			return createBeam(world,x, y, width, height, density);
		case BUBBLE:
			return createBubble(world,x, y, width, density);
		case CEILING:
			return null;
		case FLOOR:
			return createFloor(world,x, y, width, height, density);
		case SHOOTER:
			return createShooter(world,x, y, width, height, density);
		default:
			return null;

    	}
    }
    
    
    public Body createFloor(World world, float x, float y, float width, float height, float density) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(x, y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);
        body.createFixture(shape, density);        
        shape.dispose();
        
        return body;

    }
    
    public Body createShooter(World world, float x, float y, float width, float height, float density) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(x, y));
        bodyDef.gravityScale = Constants.SHOOTER_GRAVITY_SCALE;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, density);
        body.resetMassData();
        
        shape.dispose();
        
        return body;
    }
    
    public Body createBubble(World world,float x, float y, float radius, float density) {
        BodyDef bodyDef = new BodyDef();
       bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(x,y));
        bodyDef.gravityScale = Constants.BUBBLE_GRAVITY_SCALE;
        
        Vector2 initVelocity = new Vector2((x-2*radius), Constants.FLOOR_Y).sub(new Vector2(x, y));
        initVelocity = initVelocity.scl(1f);
        bodyDef.linearVelocity.set(initVelocity);
        
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);
        Body body = world.createBody(bodyDef);
//        body.createFixture(shape, Constants.BUBBLE_DENSITY);
        
        FixtureDef fixtureDef = new FixtureDef();  
        fixtureDef.shape = shape;  
        fixtureDef.density = density;  
        fixtureDef.friction = 0.0f;  
        fixtureDef.restitution = 1;  
        body.createFixture(fixtureDef);  
        
        body.resetMassData();
        shape.dispose();
        
        
        return body;
    }
    
    public Body createBeam(World world, float x, float y, float width, float height,float density ) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x,y);
        
        bodyDef.gravityScale = Constants.BEAM_GRAVITY_SCALE;
        bodyDef.linearVelocity.set(Constants.BEAM_VELOCITY);
    
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);
        Body body = world.createBody(bodyDef);

        body.createFixture(shape, density);
        body.resetMassData();
        
        shape.dispose();

        
        return body;
    }
    

}