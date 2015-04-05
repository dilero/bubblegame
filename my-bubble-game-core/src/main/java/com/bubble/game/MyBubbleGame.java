package com.bubble.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bubble.screen.GameScreen;

public class MyBubbleGame extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		setScreen(new GameScreen());
	}

}
