package com.bubble.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bubble.utils.Constants;

public class Gameover extends Actor {

	private String gameoverText;
	BitmapFont gameoverFont;

    public Gameover() {
    	gameoverText= "Gameover";
    	gameoverFont = new BitmapFont();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
    	gameoverFont.setColor(1.0f, 0.0f, 0.0f, 1.0f);
    	gameoverFont.draw(batch, gameoverText, Constants.APP_WIDTH/2, Constants.APP_HEIGHT/2); 
    }


}