package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.handlers.ObjectHandler;

public class SkeletonEnemy extends Rectangle
{
    public Texture skeletonImg;

    private float velY;

    private boolean allowedToMove = true;

    public SkeletonEnemy()
    {
        skeletonImg = new Texture("Skeleton1.png");

        x = Gdx.graphics.getWidth() + 20;
        y = ObjectHandler.botScreen - 12;
        width = 96;
        height = 96;
    }

    public SkeletonEnemy(float x, float y)
    {
        skeletonImg = new Texture("Skeleton1.png");

        width = 96;
        height = 96;
        this.y = y;
        this.x = x;
    }
    public void move()
    {
        x -= ObjectHandler.speed;

        velY -= 0.5;

        if (this.y <= ObjectHandler.botScreen)
            allowedToMove = true;

        if (allowedToMove)
        {
            velY = (float)10;
            allowedToMove = false;
        }

        y += velY;
    }
    public Rectangle getSkeletonBounds()
    {
        return new Rectangle(x + 16, y + 4, 36, 60);
    }
}
