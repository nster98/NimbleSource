package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.handlers.ObjectHandler;

public class SwordObstacle extends Rectangle
{
    public Texture swordImg;

    public SwordObstacle()
    {
        swordImg = new Texture("SwordObstacle.png");

        x = Gdx.graphics.getWidth() + 20;
        y = ObjectHandler.botScreen;
        width = 38;
        height = 150;
    }
    public SwordObstacle(float x, float y)
    {
        swordImg = new Texture("SwordObstacle.png");

        this.x = x;
        this.y = y;
        width = 38;
        height = 150;
    }
    public void move()
    {
        x -= ObjectHandler.speed;
    }
    public boolean reachedEnd()
    {
        if (this.x + this.width <= 0)
        {
            return true;
        }
        return false;
    }
    public Rectangle getUpBounds()
    {
        return new Rectangle(x, y + height, width, 1);
    }
    public Rectangle getLeftBounds()
    {
        return new Rectangle(x - 3, y, 1, height);
    }
}
