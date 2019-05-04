package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.handlers.ObjectHandler;

public class Wall extends Rectangle
{
    public Color color = Color.FOREST;

    public Texture wallImg;


    public Wall()
    {
        wallImg = new Texture("blackgroundmountains.png");

        x = Gdx.graphics.getWidth();
        y = ObjectHandler.botScreen;
        width = wallImg.getWidth() * 8;
        height = wallImg.getHeight();
    }
    public void moveWall()
    {
        x -= (float)Gdx.graphics.getWidth() / 1000;
        respawnWall();
    }
    private void respawnWall()
    {
        if (this.x + this.width <= 0)
        {
            this.x = Gdx.graphics.getWidth();
        }
    }
}
