package com.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.nimblenorse.Game;
import com.objects.*;

public class HelpScreen implements Screen
{
    private Texture background = new Texture("planksTile.png");
    private Texture buttonImg = new Texture("blankButton.png");
    private Texture backButtonImg = new Texture("backButton.png");
    private Texture leftButtonImg = new Texture("leftButton.png");
    private Texture rightButtonImg = new Texture("rightButton.png");

    private Button mainMenu;
    private Button back;
    private Button foward;

    private SpriteBatch batch;
    private BitmapFont font;
    private BitmapFont text;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter textParameter;

    private SkeletonEnemy skeleton;
    private Wolf wolf;
    private SwordObstacle sword;
    private Meteor meteor;
    private Snake snake;
    private Jotun jotun;
    private Table table;

    public static enum Enemy
    {
        Skeleton, Sword, Wolf, Snake, Jotun, Meteor, Table
    }

    public static Enemy screen = Enemy.Skeleton;

    @Override
    public void show()
    {
        batch = new SpriteBatch();
        font = new BitmapFont();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/slkscr.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        textParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)(Gdx.graphics.getWidth() * 108 / Gdx.graphics.getHeight());
        textParameter.size = (int)(Gdx.graphics.getWidth() * 72 / Gdx.graphics.getHeight());

        font = generator.generateFont(parameter);
        text = generator.generateFont(textParameter);

        mainMenu = new Button(backButtonImg, Gdx.graphics.getWidth() / 32, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 11, Gdx.graphics.getWidth() / 8, Gdx.graphics.getWidth() / 8);
        back = new Button(leftButtonImg, Gdx.graphics.getWidth() / 6, Gdx.graphics.getHeight() / 16, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 8);
        foward = new Button(rightButtonImg, back.x + back.width + Gdx.graphics.getWidth() / 32, back.y, back.width, back.height);

        skeleton = new SkeletonEnemy();
        skeleton.setWidth(172);
        skeleton.setHeight(172);
        skeleton.x = Gdx.graphics.getWidth() / 2 - skeleton.width / 2;
        skeleton.y = (Gdx.graphics.getHeight() / (float)1.75 - skeleton.height / (float)1.75) + Gdx.graphics.getHeight() / 16;

        wolf = new Wolf();
        wolf.setWidth(225);
        wolf.setHeight(112);
        wolf.setX(Gdx.graphics.getWidth() / 2 - wolf.width / 2);
        wolf.setY((Gdx.graphics.getHeight() / (float)1.75 - wolf.height / (float)1.75) + Gdx.graphics.getHeight() / 16);

        sword = new SwordObstacle();
        sword.setWidth(80);
        sword.setHeight(300);
        sword.setX(Gdx.graphics.getWidth() / 2 - sword.width / 2);
        sword.setY((Gdx.graphics.getHeight() / (float)1.75 - sword.height / (float)1.75) + Gdx.graphics.getHeight() / 16);

        meteor = new Meteor();
        meteor.setWidth(200);
        meteor.setHeight(200);
        meteor.setX(Gdx.graphics.getWidth() / 2 - meteor.width / 2);
        meteor.setY((Gdx.graphics.getHeight() / (float)1.75 - meteor.height / (float)1.75) + Gdx.graphics.getHeight() / 16);

        snake = new Snake();
        snake.setWidth(150);
        snake.setHeight(196);
        snake.setX(Gdx.graphics.getWidth() / 2 - snake.width / 2);
        snake.setY((Gdx.graphics.getHeight() / (float)1.75 - snake.height / (float)1.75) + Gdx.graphics.getHeight() / 16);

        jotun = new Jotun();
        jotun.setX(Gdx.graphics.getWidth() / 2 - jotun.width / 2);
        jotun.setY(Gdx.graphics.getHeight() / (float)1.75 - jotun.height / (float)2.5);

        table = new Table();
        table.setWidth(450);
        table.setHeight(112);
        table.setX(Gdx.graphics.getWidth() / 2 - table.width / 2);
        table.setY((Gdx.graphics.getHeight() / (float)1.75 - table.height / (float)1.75) + Gdx.graphics.getHeight() / 16);

    }

    @Override
    public void render(float delta)
    {
        batch.begin();

        for (int x = 0; x < Gdx.graphics.getWidth(); x += 128)
            for (int y = 0; y < Gdx.graphics.getHeight(); y += 128)
                batch.draw(background, x, y, 128, 128);

        batch.draw(mainMenu.currentImg, mainMenu.x, mainMenu.y, mainMenu.width, mainMenu.height);
        batch.draw(back.currentImg, back.x, back.y, back.width, back.height);
        batch.draw(foward.currentImg, foward.x, foward.y, foward.width, foward.height);

        // ORDER: SKELETON, SNAKE, JOTUN, WOLF, SWORD, METEOR, TABLE
        if (screen == Enemy.Skeleton)
        {
            // TEXT:
            // Jumps
            // Jump or Attack
            GlyphLayout skeletonFont = new GlyphLayout(font, "Skeleton");
            font.draw(batch, "Skeleton", Gdx.graphics.getWidth() / 2 - skeletonFont.width / 2, Gdx.graphics.getHeight() / (float)1.15);

            GlyphLayout jumpsUpAndDown = new GlyphLayout(text, "Jumps Up and Down");
            text.draw(batch, "Jumps Up and Down", Gdx.graphics.getWidth() / 2 - jumpsUpAndDown.width / 2, Gdx.graphics.getHeight() / (float)2.5);

            batch.draw(skeleton.skeletonImg, skeleton.x, skeleton.y, skeleton.width, skeleton.height);

            if (foward.justPressed())
                screen = Enemy.Snake;
        }
        else if (screen == Enemy.Snake)
        {
            // TEXT
            // Shoots Spears
            // Jump and Attack
            batch.draw(snake.img, snake.x, snake.y, snake.width, snake.height);

            GlyphLayout snakeLayout = new GlyphLayout(font, "Snake");
            font.draw(batch, "Snake", Gdx.graphics.getWidth() / 2 - snakeLayout.width / 2, Gdx.graphics.getHeight() / (float)1.15);

            GlyphLayout throwsSpears = new GlyphLayout(text, "Throws Spears");
            text.draw(batch, "Throws Spears", Gdx.graphics.getWidth() / 2 - throwsSpears.width / 2, Gdx.graphics.getHeight() / (float)2.5);

            if (foward.justPressed())
                screen = Enemy.Jotun;
            if (back.justPressed())
                screen = Enemy.Skeleton;

        }
        else if (screen == Enemy.Jotun)
        {
            // TEXT
            // Big and Tall
            // Attack Head
            batch.draw(jotun.img, jotun.x, jotun.y, jotun.width, jotun.height);

            GlyphLayout jotunLayout = new GlyphLayout(font, "Frost Giant");
            font.draw(batch, "Frost Giant", Gdx.graphics.getWidth() / 2 - jotunLayout.width / 2, Gdx.graphics.getHeight() / (float)1.15);

            GlyphLayout attackHead = new GlyphLayout(text, "Attack Head");
            text.draw(batch, "Attack Head", Gdx.graphics.getWidth() / 2 - attackHead.width / 2, Gdx.graphics.getHeight() / (float)2.5);

            if (foward.justPressed())
                screen = Enemy.Wolf;
            if (back.justPressed())
                screen = Enemy.Snake;

        }
        else if (screen == Enemy.Wolf)
        {
            // TEXT
            // Jumps at you
            // Attack or Jump

            batch.draw(wolf.currentImg, wolf.x, wolf.y, wolf.width, wolf.height);

            GlyphLayout wolfLayout = new GlyphLayout(font, "Wolf");
            font.draw(batch, "Wolf", Gdx.graphics.getWidth() / 2 - wolfLayout.width / 2, Gdx.graphics.getHeight() / (float)1.15);

            GlyphLayout jumpsAtYou = new GlyphLayout(text, "Jumps at You");
            text.draw(batch, "Jumps at You", Gdx.graphics.getWidth() / 2 - jumpsAtYou.width / 2, Gdx.graphics.getHeight() / (float)2.5);

            if (foward.justPressed())
                screen = Enemy.Sword;
            if (back.justPressed())
                screen = Enemy.Jotun;

        }
        else if (screen == Enemy.Sword)
        {
            // TEXT
            // Sticks out
            // Jump Over

            batch.draw(sword.swordImg, sword.x, sword.y, sword.width, sword.height);

            GlyphLayout swordLayout = new GlyphLayout(font, "Sword");
            font.draw(batch, "Sword", Gdx.graphics.getWidth() / 2 - swordLayout.width / 2, Gdx.graphics.getHeight() / (float)1.15);

            GlyphLayout jumpToAvoid = new GlyphLayout(text, "Jump to Avoid");
            text.draw(batch, "Jump to Avoid", Gdx.graphics.getWidth() / 2 - jumpToAvoid.width / 2, Gdx.graphics.getHeight() / (float)2.5);

            if (foward.justPressed())
                screen = Enemy.Meteor;
            if (back.justPressed())
                screen = Enemy.Wolf;

        }
        else if (screen == Enemy.Meteor)
        {
            // TEXT
            // Falls towards you
            // Jump Over

            batch.draw(meteor.currentImg, meteor.x, meteor.y, meteor.width, meteor.height);

            GlyphLayout meteorLayout = new GlyphLayout(font, "Meteor");
            font.draw(batch, "Meteor", Gdx.graphics.getWidth() / 2 - meteorLayout.width / 2, Gdx.graphics.getHeight() / (float)1.15);

            GlyphLayout jumpToAvoid = new GlyphLayout(text, "Avoid");
            text.draw(batch, "Avoid", Gdx.graphics.getWidth() / 2 - jumpToAvoid.width / 2, Gdx.graphics.getHeight() / (float)2.5);

            if (foward.justPressed())
                screen = Enemy.Table;
            if (back.justPressed())
                screen = Enemy.Sword;

        }
        else if (screen == Enemy.Table)
        {
            // TEXT
            // Platform
            // Jump On
            batch.draw(table.tableImg, table.x, table.y, table.width, table.height);

            GlyphLayout tableLayout = new GlyphLayout(font, "Table");
            font.draw(batch, "Table", Gdx.graphics.getWidth() / 2 - tableLayout.width / 2, Gdx.graphics.getHeight() / (float)1.15);

            GlyphLayout justAPlatform = new GlyphLayout(text, "Just a Platform");
            text.draw(batch, "Just a Platform", Gdx.graphics.getWidth() / 2 - justAPlatform.width / 2, Gdx.graphics.getHeight() / (float)2.5);

            if (back.justPressed())
                screen = Enemy.Meteor;
        }

        batch.end();

        if (mainMenu.justPressed())
        {
            Game.gameState = Game.STATE.Menu;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
