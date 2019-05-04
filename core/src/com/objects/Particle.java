package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Particle extends Rectangle
{
    private Color color;

    private float velX, velY;
    private float originalX, originalY, originalVelX, originalVelY;
    private float accX, accY;

    public Texture img = new Texture("particle.png");

    public Particle(Color color, float x, float y, float width, float height, float velX, float velY, float accX, float accY)
    {
        this.color = color;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        originalX = x;
        originalY = y;
        originalVelX = velX;
        originalVelY = velY;

        this.velX = velX;
        this.velY = velY;

        this.accX = accX;
        this.accY = accY;


    }
    public void move()
    {
        velX += accX;
        velY += accY;

        x += velX;
        y += velY;
    }
    public Color getColor()
    {
        return color;
    }
    public void respawn()
    {
        if (this.x < 0 || this.x > Gdx.graphics.getWidth()) {
            this.velX = originalVelX;
            this.x = originalX;
        }
        if (this.y < 0) {
            this.velY = originalVelY;
            this.y = originalY;
        }
    }
}
