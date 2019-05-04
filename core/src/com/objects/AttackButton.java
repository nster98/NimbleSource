package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class AttackButton extends Rectangle
{
    private Texture attackButtonImg1;
    private Texture attackButtonImg2;

    public Texture currentButtonImg;

    public static boolean isPressed = false;

    public AttackButton(float x, float y, float width, float height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        attackButtonImg1 = new Texture("AttackButton1.png");
        attackButtonImg2 = new Texture("AttackButton2.png");

        currentButtonImg = attackButtonImg1;
    }
    public void checkButton()
    {
        currentButtonImg = attackButtonImg1;
        isPressed = false;

        if (Gdx.input.isTouched())
        {
            if (Gdx.input.getX() >= x && Gdx.input.getY() <= Gdx.graphics.getHeight() - y &&
                    Gdx.input.getX() < x + width && Gdx.input.getY() > Gdx.graphics.getHeight() - (y + height))
            {
                currentButtonImg = attackButtonImg2;
                isPressed = true;
            }
        }
    }
}
