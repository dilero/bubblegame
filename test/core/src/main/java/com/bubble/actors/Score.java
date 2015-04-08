package com.bubble.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bubble.utils.Constants;

public class Score extends Actor {

	private int score;
	private String yourScoreName;
	BitmapFont yourBitmapFontName;

    public Score() {
        setScore(0);
        yourScoreName = "score: 0";
        yourBitmapFontName = new BitmapFont();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
    	yourBitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    	yourBitmapFontName.draw(batch, yourScoreName, Constants.SCORE_X, Constants.SCORE_Y); 
    }


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
		yourScoreName="score: "+score;
	}


}
