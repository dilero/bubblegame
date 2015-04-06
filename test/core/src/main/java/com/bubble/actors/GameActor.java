package com.bubble.actors;

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

    public GameActor(World world) {
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
	   body.setAwake(false);
	   setVisible(false);
   }
   
   public void activate() {
	   body.setAwake(true);
	   setVisible(true);
   }
   
   public int getID() {
   	return id;
   }
   
   public BoundEnum bodyInBounds(){
	   return BoundEnum.INBOUND;
   }
   
}