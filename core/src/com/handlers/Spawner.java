package com.handlers;

import com.badlogic.gdx.math.MathUtils;
import com.objects.Floor;
import com.objects.Jotun;
import com.objects.Meteor;
import com.objects.Player;
import com.objects.SkeletonEnemy;
import com.objects.Snake;
import com.objects.SnakeSpear;
import com.objects.SwordObstacle;
import com.objects.Table;
import com.objects.Wall;
import com.objects.Wolf;

import java.util.ArrayList;

public class Spawner
{
    public Player player;
    public ArrayList<Table> tables;
    public ArrayList<SwordObstacle> swords;
    public ArrayList<SkeletonEnemy> skeletons;
    public ArrayList<Meteor> meteors;
    public ArrayList<Jotun> jotuns;
    public ArrayList<Wolf> wolves;
    public ArrayList<Snake> snakes;
    public ArrayList<SnakeSpear> spears;

    public Floor floor;
    public Wall wall;

    private int timer = 0;

    public Spawner()
    {
        tables = new ArrayList<Table>();
        swords = new ArrayList<SwordObstacle>();
        skeletons = new ArrayList<SkeletonEnemy>();
        meteors = new ArrayList<Meteor>();
        jotuns = new ArrayList<Jotun>();
        wolves = new ArrayList<Wolf>();
        snakes = new ArrayList<Snake>();
        spears = new ArrayList<SnakeSpear>();

        floor = new Floor();
        wall = new Wall();
    }
    public void spawnLevel()
    {
        player = new Player();
    }
    public void spawnChecker()
    {
        timer++;

        if (timer % 100 == 0)
        {
            int r = MathUtils.random(0, 6);

            if (r == 0)
            {
                tables.add(new Table());
            }
            if (r == 1)
            {
                swords.add(new SwordObstacle());
            }
            if (r == 2)
            {
                skeletons.add(new SkeletonEnemy());
            }
            if (r == 3)
            {
                meteors.add(new Meteor());
            }
            if (r == 4)
            {
                jotuns.add(new Jotun());
            }
            if (r == 5)
            {
                wolves.add(new Wolf());
            }
            if (r == 6)
            {
                snakes.add(new Snake());
            }
        }
    }
    public void deleteLevel()
    {
        tables.clear();
        skeletons.clear();
        swords.clear();
        meteors.clear();
        jotuns.clear();
        wolves.clear();
        snakes.clear();
        spears.clear();
    }
    public void dispose()
    {
        player.currentPlayerImg.dispose();

        for (Table table : tables)
        {
            table.tableImg.dispose();
        }
        for (SwordObstacle sword : swords)
        {
            sword.swordImg.dispose();
        }
        for (SkeletonEnemy skeleton : skeletons)
        {
            skeleton.skeletonImg.dispose();
        }
    }
}
