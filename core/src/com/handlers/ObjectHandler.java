package com.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.nimblenorse.Game;
import com.objects.AttackButton;
import com.objects.Button;
import com.objects.Jotun;
import com.objects.JumpButton;
import com.objects.Meteor;
import com.objects.OceanBackground;
import com.objects.SkeletonEnemy;
import com.objects.Snake;
import com.objects.SnakeSpear;
import com.objects.SwordObstacle;
import com.objects.Table;
import com.objects.Wolf;

public class ObjectHandler
{
    public static float speedVariable = 100;

    public static final float topScreen = Gdx.graphics.getHeight() / (float)1.25;
    public static final float botScreen = Gdx.graphics.getHeight() / 4;
    public static final float midScreen = Gdx.graphics.getHeight() / 2;
    public static float speed = Gdx.graphics.getWidth() / speedVariable;

    public static boolean paused = false;

    private float countdown = 120;

    private OrthographicCamera camera;
    private ShapeRenderer render;
    private SpriteBatch batch;
    private BitmapFont scoreFont;
    private BitmapFont countdownFont;
    private BitmapFont font;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameterScore;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameterCountdown;

    private Texture botScreenTexture;
    private Texture topScreenTexture;
    private Texture background;
    private Texture gameOverBackground;
    private Texture homeButton;
    private Texture playAgainButtonImg;
    private Texture pauseButtonImg;
    private OceanBackground ocean;

    private Music gameTheme = Gdx.audio.newMusic(Gdx.files.internal("sounds/gametheme.wav"));

    private Sound gameOver = Gdx.audio.newSound(Gdx.files.internal("sounds/gameover.wav"));
    private Sound wolfDieSound = Gdx.audio.newSound(Gdx.files.internal("sounds/wolfdie.wav"));

    public static Spawner spawn;

    private JumpButton jumpButton;
    private AttackButton attackButton;

    private Button backButton;
    private Button playAgainButton;
    private Button pauseButton;

    public ObjectHandler()
    {
        render = new ShapeRenderer();
        batch = new SpriteBatch();
        scoreFont = new BitmapFont();
        countdownFont = new BitmapFont();
        font = new BitmapFont();
        camera = new OrthographicCamera();

        camera.position.set(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f, 0);
        camera.setToOrtho(false, 600, 1004);
        camera.update();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/slkscr.ttf"));
        parameterScore = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterScore.size = (int)(Gdx.graphics.getWidth() * 196 / Gdx.graphics.getHeight());
        parameterScore.shadowColor = Color.BLACK;
        parameterScore.shadowOffsetX = 5;
        parameterScore.shadowOffsetY = 5;

        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)(Gdx.graphics.getWidth() * 96 / Gdx.graphics.getHeight());

        parameterCountdown = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterCountdown.size = (int)(Gdx.graphics.getWidth() * 400 / Gdx.graphics.getHeight());
        parameterCountdown.shadowColor = Color.BLACK;
        parameterCountdown.shadowOffsetX = 3;
        parameterCountdown.shadowOffsetY = 3;

        scoreFont = generator.generateFont(parameterScore);
        font = generator.generateFont(parameter);
        countdownFont = generator.generateFont(parameterCountdown);

        generator.dispose();

        botScreenTexture = new Texture("botScreenPlanks.png");
        topScreenTexture = new Texture("topScreenPlanks.png");
        background = new Texture("backgroundsky.png");
        gameOverBackground = new Texture("gameOverBackground.png");
        homeButton = new Texture("homeButton.png");
        playAgainButtonImg = new Texture("playAgainButton.png");
        pauseButtonImg = new Texture("pauseButton.png");
        ocean = new OceanBackground();

        spawn = new Spawner();

        jumpButton = new JumpButton(Gdx.graphics.getWidth() / 8, botScreen / 8,
                ((Gdx.graphics.getWidth() / (float)1.125 - (Gdx.graphics.getWidth() / 8)) / 2) - 25, botScreen / 2);

        attackButton = new AttackButton(Gdx.graphics.getWidth() / 2 + (Gdx.graphics.getWidth() / 16), botScreen / 8,
                ((Gdx.graphics.getWidth() / (float)1.125 - (Gdx.graphics.getWidth() / 8)) / 2) - 25, botScreen / 2);

        backButton = new Button(homeButton, (Gdx.graphics.getWidth() / 8) + Gdx.graphics.getWidth() / 8,
                                (Gdx.graphics.getHeight() / 8)  + Gdx.graphics.getWidth() / 4,
                                    Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 12);
        playAgainButton = new Button(playAgainButtonImg, backButton.x + backButton.width + Gdx.graphics.getWidth() / 64,
                                (Gdx.graphics.getHeight() / 8)  + Gdx.graphics.getWidth() / 4,
                                    Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 12);
        pauseButton = new Button(pauseButtonImg, Gdx.graphics.getWidth() / 32,
                                Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 11,
                                Gdx.graphics.getWidth() / 8, Gdx.graphics.getWidth() / 8);


        render.setAutoShapeType(true);
        spawn.spawnLevel();
        spawn.wall.x = Gdx.graphics.getWidth() / 2;
    }
    public void render()
    {
        if (!paused)
            countdown--;

        if (countdown <= 0 && !paused)
        {
            countdown = 0;
            spawn.spawnChecker();
            gameTheme.play();
            gameTheme.setLooping(true);
        }

        speed = Gdx.graphics.getWidth() / speedVariable;

        speedVariable -= .005;

        batch.begin();

        batch.draw(botScreenTexture, 0, 0, Gdx.graphics.getWidth(), botScreen - spawn.floor.height);
        batch.draw(topScreenTexture, 0, topScreen, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - topScreen);

        batch.draw(background, 0, botScreen, Gdx.graphics.getWidth(), topScreen - botScreen);

        batch.draw(ocean.currentImg, ocean.x, ocean.y, ocean.width, ocean.height);

        batch.draw(spawn.floor.floorImg, spawn.floor.x, spawn.floor.y, spawn.floor.width * 2, spawn.floor.height);
        batch.draw(spawn.wall.wallImg, spawn.wall.x, spawn.wall.y, spawn.wall.width, topScreen - botScreen);

        if (!paused && !spawn.player.isDead)
            batch.draw(pauseButton.currentImg, pauseButton.x, pauseButton.y, pauseButton.width, pauseButton.height);

        if (!paused)
            if (pauseButton.justPressed())
                paused = true;

        if (!spawn.player.isDead)
        {
            batch.draw(jumpButton.currentJumpButtonImg, jumpButton.x, jumpButton.y, jumpButton.width, jumpButton.height);
            batch.draw(attackButton.currentButtonImg, attackButton.x, attackButton.y, attackButton.width, attackButton.height);

            attackButton.checkButton();
            jumpButton.checkButton();
        }
        if (countdown <= 0 && !spawn.player.isDead && !paused)
        {
            spawn.floor.moveFloor();
            spawn.wall.moveWall();
            ocean.animate();
        }

        if (!paused)
        {
            for (Table table : spawn.tables)
            {
                batch.draw(table.tableImg, table.x, table.y, table.width, table.height);
                table.moveTable();
            }
            for (SwordObstacle sword : spawn.swords)
            {
                batch.draw(sword.swordImg, sword.x, sword.y, sword.width, sword.height);
                sword.move();
            }
            for (SkeletonEnemy skeleton : spawn.skeletons)
            {
                batch.draw(skeleton.skeletonImg, skeleton.x, skeleton.y, skeleton.width, skeleton.height);
                skeleton.move();
            }
            for (Meteor meteor : spawn.meteors)
            {
                batch.draw(meteor.currentImg, meteor.x, meteor.y, meteor.width, meteor.height);
                meteor.move();
            }
            for (Wolf wolf : spawn.wolves)
            {
                batch.draw(wolf.currentImg, wolf.x, wolf.y, wolf.width, wolf.height);
                wolf.move();
            }
            for (Snake snake : spawn.snakes)
            {
                batch.draw(snake.img, snake.x, snake.y, snake.width, snake.height);
                snake.move();
            }
            for (SnakeSpear spear : spawn.spears)
            {
                batch.draw(spear.img, spear.x, spear.y, spear.width, spear.height);
                spear.move();
            }
            for (Jotun jotun : spawn.jotuns)
            {
                batch.draw(jotun.img, jotun.x, jotun.y, jotun.width, jotun.height);
                jotun.move();
            }
        }

        if (countdown > 0)
        {
            batch.draw(spawn.player.currentPlayerImg, spawn.player.x, spawn.player.y - 12,
                    spawn.player.width, spawn.player.height);

            GlyphLayout scoreLayout = new GlyphLayout(countdownFont, "" + (int)(countdown / 30));

            if (countdown / 30 < 1) {
                GlyphLayout go = new GlyphLayout(countdownFont, "GO");
                countdownFont.draw(batch, "GO", Gdx.graphics.getWidth() / 2 - go.width / 2, midScreen + scoreLayout.height / 2);
            }
            else
                countdownFont.draw(batch, "" + (int)(countdown / 30), Gdx.graphics.getWidth() / 2 - scoreLayout.width / 2, midScreen + scoreLayout.height / 2);
        }

        if (!spawn.player.isDead && countdown <= 0 && !paused)
        {
            Game.score++;

            GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "" + Game.score / 10);
            scoreFont.draw(batch, "" + Game.score / 10, Gdx.graphics.getWidth() / 2 - scoreLayout.width / 2, topScreen + scoreLayout.height / 2 + ((Gdx.graphics.getHeight() - topScreen) / 2));
        }

        if (!spawn.player.isDead && countdown <= 0)
        {
            batch.draw(spawn.player.currentPlayerImg, spawn.player.x, spawn.player.y - 12,
                        spawn.player.width, spawn.player.height);

            spawn.player.movePlayer();

            for (Table table : spawn.tables)
            {
                if (spawn.player.getFloorBounds().overlaps(table.getUpBounds()))
                {
                    spawn.player.y = MathUtils.clamp(spawn.player.y, table.y + table.height, topScreen);
                    spawn.player.allowedToMove = true;
                }
            }
            for (int i = spawn.skeletons.size() - 1; i >= 0; i--)
            {
                if (spawn.player.getPlayerBounds().overlaps(spawn.skeletons.get(i).getSkeletonBounds()) && spawn.player.isAttacking)
                {
                    spawn.skeletons.remove(i);
                }
                else if (spawn.player.getPlayerBounds().overlaps(spawn.skeletons.get(i).getSkeletonBounds()) && !spawn.player.isAttacking)
                {
                    spawn.player.isDead = true;
                    gameOver.play((float)0.5);
                }
            }

            for (int i = spawn.wolves.size() - 1; i >= 0; i--)
            {
                if (spawn.player.getPlayerBounds().overlaps(spawn.wolves.get(i)) && spawn.player.isAttacking)
                {
                    wolfDieSound.play();
                    spawn.wolves.remove(i);
                }
                else if (spawn.player.getPlayerBounds().overlaps(spawn.wolves.get(i)) && !spawn.player.isAttacking) {
                    spawn.player.isDead = true;
                    gameOver.play((float)0.5);
                }
            }
            for (int i = spawn.jotuns.size() - 1; i >= 0; i--)
            {
                if (spawn.player.getPlayerBounds().overlaps(spawn.jotuns.get(i).getFaceBounds()) && spawn.player.isAttacking)
                {
                    spawn.jotuns.remove(i);
                }
                else if (spawn.player.getPlayerBounds().overlaps(spawn.jotuns.get(i)) && !spawn.player.isAttacking) {
                    spawn.player.isDead = true;
                    gameOver.play((float)0.5);
                }
            }
            for (int i = spawn.snakes.size() - 1; i >= 0; i--)
            {
                if (spawn.player.getPlayerBounds().overlaps(spawn.snakes.get(i)) && spawn.player.isAttacking)
                {
                    spawn.snakes.remove(i);
                }
                else if (spawn.player.getPlayerBounds().overlaps(spawn.snakes.get(i)) && !spawn.player.isAttacking) {
                    spawn.player.isDead = true;
                    gameOver.play((float)0.5);
                }
            }
            for (SnakeSpear spear : spawn.spears)
            {
                if (spawn.player.getPlayerBounds().overlaps(spear)) {
                    spawn.player.isDead = true;
                    gameOver.play((float)0.5);
                }
            }
            for (Meteor meteor : spawn.meteors)
            {
                if (spawn.player.getPlayerBounds().overlaps(meteor))
                {
                    gameOver.play();
                    spawn.player.isDead = true;
                }
            }
            for (SwordObstacle sword : spawn.swords)
            {
                if (spawn.player.getPlayerBounds().overlaps(sword))
                {
                    gameOver.play((float)0.5);
                    spawn.player.isDead = true;
                }
            }
        }
        if (paused)
        {
            gameTheme.pause();

            batch.draw(gameOverBackground, Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 8,
                    Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 4), Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 4));

            GlyphLayout pausedFont = new GlyphLayout(font, "Paused");
            GlyphLayout scoreFont = new GlyphLayout(font, "Score");
            GlyphLayout score = new GlyphLayout(font, "" + Game.score / 10);

            batch.draw(backButton.currentImg, backButton.x, backButton.y, backButton.width, backButton.height);
            batch.draw(playAgainButton.currentImg, playAgainButton.x, playAgainButton.y, playAgainButton.width, playAgainButton.height);

            font.draw(batch, "Paused", Gdx.graphics.getWidth() / 2 - pausedFont.width / 2, (Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 4)) + Gdx.graphics.getHeight() / 32);
            font.draw(batch, "Score", Gdx.graphics.getWidth() / 2 - scoreFont.width / 2, Gdx.graphics.getHeight() / (float)1.75);
            font.draw(batch, "" + Game.score / 10, Gdx.graphics.getWidth() / 2 - score.width / 2, Gdx.graphics.getHeight() / 2);

            if (backButton.justPressed())
            {
                spawn.wall.x = Gdx.graphics.getWidth() / 2;
                Game.score = 0;
                countdown = 120;
                speedVariable = 100;
                spawn.player.setPlayerImg(spawn.player.getPlayerImg());
                spawn.deleteLevel();
                gameTheme.stop();
                Game.gameState = Game.STATE.Menu;
            }
            if (playAgainButton.justPressed())
            {
                paused = false;
            }

        }

        batch.end();

        if (spawn.player.isDead)
        {

            spawn.deleteLevel();
            gameTheme.stop();
            putScores(Game.score / 10);
            Game.prefs.flush();
            spawn.player.allowedToMove = false;
            jumpButton.isPressed = false;
            spawn.player.setY(botScreen);

            if (backButton.justPressed())
            {
                spawn.wall.x = Gdx.graphics.getWidth() / 2;
                Game.score = 0;
                countdown = 120;
                speedVariable = 100;
                spawn.player.setPlayerImg(spawn.player.getPlayerImg());
                gameTheme.stop();
                Game.gameState = Game.STATE.Menu;
            }
            if (playAgainButton.justPressed())
            {
                spawn.player.isDead = false;
                spawn.player.isAttacking = false;
                spawn.player.setY(botScreen);
                spawn.player.setPlayerImg(spawn.player.getPlayerImg());
                spawn.player.setVelY(0);
                spawn.wall.x = Gdx.graphics.getWidth() / 2;
                countdown = 120;
                speedVariable = 100;
                Game.score = 0;
            }

            batch.begin();

            batch.draw(gameOverBackground, Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 8,
                    Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 4), Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 4));

            GlyphLayout gameOverFont = new GlyphLayout(font, "Game Over");
            GlyphLayout scoreFont = new GlyphLayout(font, "Score");
            GlyphLayout score = new GlyphLayout(font, "" + Game.score / 10);

            batch.draw(backButton.currentImg, backButton.x, backButton.y, backButton.width, backButton.height);
            batch.draw(playAgainButton.currentImg, playAgainButton.x, playAgainButton.y, playAgainButton.width, playAgainButton.height);

            font.draw(batch, "Game Over", Gdx.graphics.getWidth() / 2 - gameOverFont.width / 2, (Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 4)) + Gdx.graphics.getHeight() / 32);
            font.draw(batch, "Score", Gdx.graphics.getWidth() / 2 - scoreFont.width / 2, Gdx.graphics.getHeight() / (float)1.75);
            font.draw(batch, "" + Game.score / 10, Gdx.graphics.getWidth() / 2 - score.width / 2, Gdx.graphics.getHeight() / 2);

            batch.end();

        }

        render.begin();

        render.set(ShapeRenderer.ShapeType.Filled);


        render.setColor(Color.NAVY);
        render.end();
    }
    public void dispose()
    {
        spawn.floor.floorImg.dispose();
        spawn.wall.wallImg.dispose();
        batch.dispose();
        render.dispose();
        spawn.dispose();
    }
    private void putScores(int score)
    {
        if (score > Game.prefs.getInteger("Score"))
        {
            Game.prefs.putInteger("Score", score);
        }

    }
}
