package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.handlers.ObjectHandler;

public class Table extends Rectangle
{
    public Texture tableImg;

    public int enemyForTable;

    public Table()
    {
        tableImg = new Texture("Table.png");

        x = Gdx.graphics.getWidth() + 20;
        y = ObjectHandler.botScreen;
        width = 300;
        height = 75;

        enemyForTable = MathUtils.random(0, 1);
    }
    public void moveTable()
    {
        x -= ObjectHandler.speed;
    }
    public boolean reachedEnd()
    {
        if (this.x + this.width <= 0)
        {
            return true;
        }
        return false;
    }
    public Rectangle getUpBounds()
    {
        return new Rectangle(x + 8, y + height - 8, width - 8, 8);
    }
    public Rectangle getRightUpBounds()
    {
        return new Rectangle(x + (width-16), y + height - 8, 16, 10);
    }
}
