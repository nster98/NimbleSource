package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class JumpButton extends Rectangle
{
    public Texture currentJumpButtonImg;

    private Texture jumpButtonImg1;
    private Texture jumpButtonImg2;

    public static boolean isPressed = false;

    public JumpButton(float x, float y, float width, float height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        jumpButtonImg1 = new Texture("JumpButton1.png");
        jumpButtonImg2 = new Texture("JumpButton2.png");

        currentJumpButtonImg = jumpButtonImg1;
    }
    public void checkButton()
    {
        currentJumpButtonImg = jumpButtonImg1;
        isPressed = false;

        if (Gdx.input.isTouched())
        {
            if (Gdx.input.getX() >= x && Gdx.input.getY() <= Gdx.graphics.getHeight() - y &&
                    Gdx.input.getX() < x + width && Gdx.input.getY() > Gdx.graphics.getHeight() - (y + height))
            {
                currentJumpButtonImg = jumpButtonImg2;
                isPressed = true;
            }
        }
    }
}
