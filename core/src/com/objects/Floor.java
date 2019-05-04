package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.handlers.ObjectHandler;


public class Floor extends Rectangle
{

    private float velX;
    private float velY;

    public Texture floorImg;

    public Floor()
    {
        floorImg = new Texture("floor.png");

        width = Gdx.graphics.getWidth();
        height = 25;
        x = 0;
        y = ObjectHandler.botScreen - height;



    }
    public void moveFloor()
    {
        x -= ObjectHandler.speed;

        respawnFloor();

        x += velX;
        y += velY;
    }
    public void setVelX(float velX)
    {
        this.velX = velX;
    }
    public float getVelX()
    {
        return velX;
    }
    public void setVelY(float velY)
    {
        this.velY = velY;
    }
    public float getVelY()
    {
        return velY;
    }
    private void respawnFloor()
    {
        if (this.x + this.width - 3 <= 0)
        {
            this.x = 0;
        }

    }
}
