package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.handlers.ObjectHandler;
import com.nimblenorse.Game;

public class Wolf extends Rectangle
{
    private float velY, velX;
    private float pointToJump;
    private int animationCounter = 0;

    private boolean jumped = false;

    private Texture wolfImg1;
    private Texture wolfImg2;

    public Texture currentImg;

    private Sound wolfJumpSound = Gdx.audio.newSound(Gdx.files.internal("sounds/wolfjump.wav"));

    public Wolf()
    {
        x = Gdx.graphics.getWidth() + 20;
        y = ObjectHandler.botScreen;
        width = 150;
        height = 75;

        pointToJump = MathUtils.random(Gdx.graphics.getWidth() / 3, Gdx.graphics.getWidth());

        wolfImg1 = new Texture("wolf1.png");
        wolfImg2 = new Texture("wolf2.png");

        currentImg = wolfImg1;
    }
    public Wolf(float x, float y)
    {
        this.x = x;
        this.y = y;
        width = 150;
        height = 75;

        currentImg = new Texture("wolf1.png");
    }
    public void move()
    {
        animationCounter++;

        if (animationCounter / 10 % 2 == 0)
        {
            currentImg = wolfImg2;
        }
        else
            currentImg = wolfImg1;

        velY -= 0.1;

        y += velY;
        x += velX;

        x -= ObjectHandler.speed;

        if (x <= pointToJump && !jumped)
        {
            velY = 3;
            velX = -5;
            currentImg = wolfImg1;
            wolfJumpSound.play();
            jumped = true;
        }
        if (jumped)
            currentImg = wolfImg1;


        this.y = MathUtils.clamp(this.y, ObjectHandler.botScreen, ObjectHandler.topScreen);
    }
}
