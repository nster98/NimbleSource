package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.nimblenorse.Game;

public class Button extends Rectangle
{
    public Texture currentImg;

    public boolean isPressed;


    public Button(Texture texture, float x, float y, float width, float height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        currentImg = texture;
    }
    public Button(float x, float y, float width, float height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void checkButton()
    {
        isPressed = false;

        if (Gdx.input.justTouched())
        {
            if (Gdx.input.getX() >= x && Gdx.input.getY() <= Gdx.graphics.getHeight() - y &&
                    Gdx.input.getX() < x + width && Gdx.input.getY() > Gdx.graphics.getHeight() - (y + height))
            {
                isPressed = true;
            }
        }
    }
    public boolean justPressed()
    {
        if (Gdx.input.justTouched())
        {
            if (Gdx.input.getX() >= x && Gdx.input.getY() <= Gdx.graphics.getHeight() - y &&
                    Gdx.input.getX() < x + width && Gdx.input.getY() > Gdx.graphics.getHeight() - (y + height))
            {
                Game.buttonClick.play((float)0.25);
                return true;
            }
        }
        return false;
    }
}
