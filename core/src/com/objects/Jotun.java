package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.handlers.ObjectHandler;

public class Jotun extends Rectangle
{
    public Texture img = new Texture("frostgiant.png");

    public Jotun()
    {
        x = Gdx.graphics.getWidth() + 20;
        y = ObjectHandler.botScreen;
        width = 175;
        height = 275;
    }
    public void move()
    {
        x -= ObjectHandler.speed;
    }
    public Rectangle getFaceBounds()
    {
        return new Rectangle(x, (y + height) - height / 2, width, height / 2);
    }
}
