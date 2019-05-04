package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.handlers.ObjectHandler;

public class OceanBackground extends Rectangle
{
    private Texture ocean1;
    private Texture ocean2;
    private Texture ocean3;
    private Texture ocean4;
    private Texture ocean5;
    private Texture ocean6;
    private Texture ocean7;
    private Texture ocean8;
    private Texture ocean9;
    private Texture ocean10;
    private Texture ocean11;
    private Texture ocean12;
    private Texture ocean13;
    private Texture ocean14;
    private Texture ocean15;
    private Texture ocean16;
    private Texture ocean17;
    private Texture ocean18;

    public Texture currentImg;

    private int counter = 0;

    public OceanBackground()
    {
        x = 0;
        y = ObjectHandler.botScreen;
        width = Gdx.graphics.getWidth();
        height = (ObjectHandler.topScreen - ObjectHandler.botScreen) / 4;

        ocean1 = new Texture("ocean/frame1.png");
        ocean2 = new Texture("ocean/frame2.png");
        ocean3 = new Texture("ocean/frame3.png");
        ocean4 = new Texture("ocean/frame4.png");
        ocean5 = new Texture("ocean/frame5.png");
        ocean6 = new Texture("ocean/frame6.png");
        ocean7 = new Texture("ocean/frame7.png");
        ocean8 = new Texture("ocean/frame8.png");
        ocean9 = new Texture("ocean/frame9.png");
        ocean10 = new Texture("ocean/frame10.png");
        ocean11 = new Texture("ocean/frame11.png");
        ocean12 = new Texture("ocean/frame12.png");
        ocean13 = new Texture("ocean/frame13.png");
        ocean14 = new Texture("ocean/frame14.png");
        ocean15 = new Texture("ocean/frame15.png");
        ocean16 = new Texture("ocean/frame16.png");
        ocean17 = new Texture("ocean/frame17.png");
        ocean18 = new Texture("ocean/frame18.png");

        currentImg = ocean1;
    }
    public void animate()
    {
        counter++;

        //Max Frames: 18
        //10 Frames per animation

        if (counter < 10)
            currentImg = ocean1;
        else if (counter >= 10 && counter < 20)
            currentImg = ocean2;
        else if (counter >= 20 && counter < 30)
            currentImg = ocean3;
        else if (counter >= 30 && counter < 40)
            currentImg = ocean4;
        else if (counter >= 40 && counter < 50)
            currentImg = ocean5;
        else if (counter >= 50 && counter < 60)
            currentImg = ocean6;
        else if (counter >= 60 && counter < 70)
            currentImg = ocean7;
        else if (counter >= 70 && counter < 80)
            currentImg = ocean8;
        else if (counter >= 80 && counter < 90)
            currentImg = ocean9;
        else if (counter >= 90 && counter < 100)
            currentImg = ocean10;
        else if (counter >= 100 && counter < 110)
            currentImg = ocean11;
        else if (counter >= 110 && counter < 120)
            currentImg = ocean12;
        else if (counter >= 120 && counter < 130)
            currentImg = ocean13;
        else if (counter >= 130 && counter < 140)
            currentImg = ocean14;
        else if (counter >= 140 && counter < 150)
            currentImg = ocean15;
        else if (counter >= 150 && counter < 160)
            currentImg = ocean16;
        else if (counter >= 160 && counter < 170)
            currentImg = ocean17;
        else if (counter >= 170 && counter < 180)
            currentImg = ocean18;

        if (counter == 180)
            counter = 0;

    }
}
