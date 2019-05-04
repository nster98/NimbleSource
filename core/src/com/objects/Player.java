package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.handlers.ObjectHandler;

public class Player extends Rectangle
{
    public boolean allowedToMove = true;
    public boolean isDead = false;
    public boolean isAttacking = false;

    private boolean jumping = false;

    private Texture playerImgWalking1;
    private Texture playerImgWalking2;
    private Texture playerImgJumping;
    private Texture playerImgAttacking1;
    private Texture playerImgAttacking2;
    private Texture playerImgAttacking3;

    public Texture currentPlayerImg;

    private Sound attackSound = Gdx.audio.newSound(Gdx.files.internal("sounds/woosh.wav"));
    private Sound jumpSound = Gdx.audio.newSound(Gdx.files.internal("sounds/jump.wav"));


    private float velX;
    private float velY;

    private int animationCounter = 0;
    private int attackCounter = 0;

    private boolean startAttackCounter = false;

    public Player()
    {
        playerImgWalking1 = new Texture("VikingWalking1.png");
        playerImgWalking2 = new Texture("VikingWalking2.png");
        playerImgJumping = new Texture("VikingJumping1.png");
        playerImgAttacking1 = new Texture("VikingAttack1.png");
        playerImgAttacking2 = new Texture("VikingAttack2.png");
        playerImgAttacking3 = new Texture("VikingAttack3.png");

        currentPlayerImg = playerImgWalking1;

        x = 100;
        y = ObjectHandler.botScreen;
        width = 96;
        height = 96;


    }
    public void movePlayer()
    {
        animationCounter++;

        if (animationCounter / 10 % 2 == 0)
            currentPlayerImg = playerImgWalking1;
        else
            currentPlayerImg = playerImgWalking2;

        if (this.y == ObjectHandler.botScreen)
        {
            jumping = false;
            allowedToMove = true;
        }

        if (allowedToMove && JumpButton.isPressed)
        {
            velY = (float)15;
            allowedToMove = false;
            jumpSound.setVolume(jumpSound.play(), (float)0.5);
            jumping = true;
        }

        if (!allowedToMove && !ObjectHandler.paused)
            velY -= 0.5;

        if (!allowedToMove)
        {
            currentPlayerImg = playerImgJumping;
        }

        if (AttackButton.isPressed)
        {
            isAttacking = true;
            attackSound.play((float)0.5);
            startAttackCounter = true;
        }
        if (startAttackCounter == true)
        {
            attackCounter++;

            if (attackCounter < 5)
                currentPlayerImg = playerImgAttacking1;
            else if (attackCounter >= 5 && attackCounter < 10)
                currentPlayerImg = playerImgAttacking2;
            else if (attackCounter >= 10)
                currentPlayerImg = playerImgAttacking3;

            if (attackCounter == 15)
            {
                isAttacking = false;
                startAttackCounter = false;
                attackCounter = 0;
            }
        }
        if (!ObjectHandler.paused)
        {
            x += velX;
            y += velY;
        }

        this.y = MathUtils.clamp(this.y, ObjectHandler.botScreen, ObjectHandler.topScreen);

    }
    public void setVelX(float velX)
    {
        this.velX = velX;
    }
    public float getVelX() { return velX; }
    public void setVelY(float velY)
    {
        this.velY = velY;
    }
    public float getVelY()
    {
        return velY;
    }
    public Texture getPlayerImg()
    {
        return playerImgWalking1;
    }
    public void setPlayerImg(Texture img)
    {
        currentPlayerImg = img;
    }
    public Rectangle getPlayerBounds()
    {
        return new Rectangle(x + 16, y + 4, 36, 60);
    }
    public Rectangle getFloorBounds() { return new Rectangle(x + 16, y, width - 32, 16); }
}
