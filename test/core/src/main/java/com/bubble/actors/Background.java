package com.bubble.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bubble.utils.Constants;

public class Background extends Actor {

    private final TextureRegion textureRegion;
    private Rectangle textureRegionBounds;

    public Background() {
    	Texture texture = new Texture(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH));
        textureRegion = new TextureRegion(texture);
        textureRegionBounds = new Rectangle(0, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, textureRegionBounds.x, textureRegionBounds.y, Constants.APP_WIDTH,
                Constants.APP_HEIGHT);
    }


}
