package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.handlers.ObjectHandler;

public class Snake extends Rectangle
{
    private boolean thrown = false;

    private Texture snake1;

    public Texture img;

    public Snake()
    {
        x = Gdx.graphics.getWidth() + 20;
        y = ObjectHandler.botScreen;
        width = 100;
        height = 135;

        snake1 = new Texture("snake1.png");

        img = snake1;
    }
    public void move()
    {
        x -= ObjectHandler.speed;

        if (x <= MathUtils.random(Gdx.graphics.getWidth() / (float)1.5, Gdx.graphics.getWidth()) && !thrown)
        {
            ObjectHandler.spawn.spears.add(new SnakeSpear(x, ObjectHandler.botScreen + ObjectHandler.spawn.player.height / 2));
            thrown = true;
        }
    }
}
