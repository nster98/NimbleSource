package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.handlers.ObjectHandler;

public class Meteor extends Rectangle
{
    private float velX = -1, velY = (float)-1.5;

    private int animationCounter;

    private Texture meteorImg1;
    private Texture meteorImg2;
    public Texture currentImg;


    public Meteor()
    {
        x = Gdx.graphics.getWidth() + 20;
        y = ObjectHandler.topScreen / 2;
        width = 96;
        height = 96;

        meteorImg1 = new Texture("meteor1.png");
        meteorImg2 = new Texture("meteor2.png");

        currentImg = meteorImg1;

    }
    public Meteor(float x, float y)
    {
        this.x = x;
        this.y = y;
        width = 96;
        height = 96;

        currentImg = new Texture("meteor1.png");
    }
    public void move()
    {
        animationCounter++;

        x -= ObjectHandler.speed;

        x += velX;
        y += velY;

        if (animationCounter / 10 % 2 == 0)
            currentImg = meteorImg2;
        else
            currentImg = meteorImg1;



    }
}
