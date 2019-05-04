package com.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.handlers.ObjectHandler;
import com.handlers.Spawner;
import com.nimblenorse.Game;
import com.objects.Button;
import com.objects.Particle;

import java.util.ArrayList;

public class MainMenu implements Screen
{
    private ShapeRenderer render;
    private SpriteBatch batch;
    private BitmapFont font, titleFont;
    private Spawner spawn;

    private ArrayList<Particle> particles;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter, titleParameter;

    private Texture background = new Texture("planksTile.png");
    private Texture titleLogo = new Texture("titleLogo.png");

    private float textWidthTitle;
    private float textWidthPlay;
    private float textHeightPlay;
    private float textWidthHelp;
    private float textHeightHelp;

    private Music menuTheme = Gdx.audio.newMusic(Gdx.files.internal("sounds/wavmenutheme.wav"));

    private float buttonX = Gdx.graphics.getWidth() / 4;
    private float buttonWidth = Gdx.graphics.getWidth() / 2;
    private float buttonHeight = Gdx.graphics.getHeight() / 8;

    private Texture buttonTexture = new Texture("blankButton.png");

    private Button buttonPlay = new Button(buttonTexture, buttonX, Gdx.graphics.getHeight() / (float)1.95, buttonWidth, buttonHeight);
    private Button buttonHelp = new Button(buttonTexture, buttonX, Gdx.graphics.getHeight() / (float)4, buttonWidth, buttonHeight);

    public void show()
    {
        render = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        titleFont = new BitmapFont();

        particles = new ArrayList<Particle>();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/slkscr.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        titleParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = (int)(Gdx.graphics.getWidth() * 96 / Gdx.graphics.getHeight());

        titleParameter.size = (int)(Gdx.graphics.getWidth() * 108 / Gdx.graphics.getHeight());
        titleParameter.shadowColor = Color.BLACK;
        titleParameter.shadowOffsetX = 5;
        titleParameter.shadowOffsetY = 5;

        font = generator.generateFont(parameter);
        titleFont = generator.generateFont(titleParameter);
        generator.dispose();

        spawn = new Spawner();

        render.setAutoShapeType(true);
        GlyphLayout layoutTitle = new GlyphLayout(titleFont, "Nimble Norse");
        GlyphLayout layoutPlay = new GlyphLayout(font, "Play");
        GlyphLayout layoutHelp = new GlyphLayout(font, "Help");
        textWidthTitle = layoutTitle.width;
        textWidthPlay = layoutPlay.width;
        textHeightPlay = layoutPlay.height;
        textWidthHelp = layoutHelp.width;
        textHeightHelp = layoutHelp.height;

        for (int i = 0; i < 100; i++)
        {
            particles.add(new Particle(Color.BLUE, MathUtils.random(0, Gdx.graphics.getWidth()),
                                        MathUtils.random(Gdx.graphics.getHeight(), Gdx.graphics.getHeight() * (float)2), 8, 16,
                                        0, -1, 0, (float)-0.25));
        }

    }

    public void render(float delta)
    {
        menuTheme.play();

        batch.begin();

        for (int x = 0; x < Gdx.graphics.getWidth(); x += 128)
            for (int y = 0; y < Gdx.graphics.getHeight(); y += 128)
                batch.draw(background, x, y, 128, 128);

        for (Particle p : particles)
        {
            p.move();
            p.respawn();
            batch.draw(p.img, p.x, p.y, p.width, p.height);
        }

        batch.draw(buttonPlay.currentImg, buttonPlay.x, buttonPlay.y, buttonPlay.width, buttonPlay.height);
        batch.draw(buttonHelp.currentImg, buttonHelp.x, buttonHelp.y, buttonHelp.width, buttonHelp.height);

        batch.draw(titleLogo, Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / (float)3.5, Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);

        //titleFont.draw(batch, "Nimble Norse", Gdx.graphics.getWidth() / 2 - textWidthTitle / 2, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 16);
        font.draw(batch, "Play", Gdx.graphics.getWidth() / 2 - textWidthPlay / 2, (buttonPlay.y) + buttonHeight / 2 + textHeightPlay / 2);
        font.draw(batch, "Help", Gdx.graphics.getWidth() / 2 - textWidthHelp / 2, buttonHelp.y + buttonHeight / 2 + textHeightHelp / 2);

        GlyphLayout scoreLayout = new GlyphLayout(font, "Highscore : " + Game.prefs.getInteger("Score"));
        font.draw(batch, "Highscore : " + Game.prefs.getInteger("Score"), Gdx.graphics.getWidth() / 2 - scoreLayout.width / 2, Gdx.graphics.getHeight() / 10);

        batch.end();

        render.begin();



        render.end();

        if (buttonPlay.justPressed())
        {
            Game.gameState = Game.STATE.Game;
            menuTheme.stop();
            ObjectHandler.spawn.spawnLevel();
            ObjectHandler.paused = false;
        }
        if (buttonHelp.justPressed())
        {
            Game.gameState = Game.STATE.Help;
            HelpScreen.screen = HelpScreen.Enemy.Skeleton;
        }
    }

    public void resize(int width, int height) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void hide() {

    }

    public void dispose()
    {
        batch.dispose();
        render.dispose();
        font.dispose();
    }
}
