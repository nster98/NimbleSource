package com.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.handlers.ObjectHandler;
import com.handlers.Spawner;

public class SnakeSpear extends Rectangle
{
    public Texture img = new Texture("spear.png");

    public SnakeSpear(float x, float y)
    {
        this.x = x;
        this.y = y;
        width = 150;
        height = 25;
    }
    public void move()
    {
        x -= ObjectHandler.speed * 1.75;


    }
}
