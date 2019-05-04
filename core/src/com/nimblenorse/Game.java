package com.nimblenorse;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.handlers.ObjectHandler;
import com.screens.HelpScreen;
import com.screens.MainMenu;

public class Game extends ApplicationAdapter
{
	public static int score = 0;

	public static Preferences prefs;

	public static Sound buttonClick;

	public enum STATE
	{
		Game, Menu, Help, Loading
	}
	public static STATE gameState = STATE.Loading;

	public SpriteBatch batch;
	public ShapeRenderer render;
	public BitmapFont font;
	public ObjectHandler handler;
	public static OrthographicCamera camera;
	public MainMenu mainMenu;
	public HelpScreen helpScreen;
	public Viewport viewport;

	private Texture logo;
	private int loadingCount = 100;
	
	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		render = new ShapeRenderer();
		font = new BitmapFont();
		handler = new ObjectHandler();
		camera = new OrthographicCamera();
		viewport = new FitViewport(600, 1004);
		viewport.apply();

		prefs = Gdx.app.getPreferences("Save Data");

		camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
		camera.update();

		mainMenu = new MainMenu();
		helpScreen = new HelpScreen();

		mainMenu.show();
		helpScreen.show();

		buttonClick = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonclick.wav"));

		logo = new Texture("Nsterdio.png");

		camera.setToOrtho(false, 600, 1004);

		render.setAutoShapeType(true);

	}

	@Override
	public void render ()
	{
        Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		if (gameState == STATE.Game)
		{
			handler.render();
		}
		else if (gameState == STATE.Menu)
		{
			mainMenu.render(1f);
		}
		else if (gameState == STATE.Help)
		{
			helpScreen.render(1f);
		}
		else if (gameState == STATE.Loading)
		{
			loadingCount--;

			render.begin();
			render.set(ShapeRenderer.ShapeType.Filled);
			render.setColor(Color.WHITE);
			render.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			render.end();

			batch.begin();

			batch.draw(logo,
					Gdx.graphics.getWidth()/32,
					Gdx.graphics.getHeight()/2 - logo.getHeight()/2,
					Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/16,
					Gdx.graphics.getHeight() / (float)1.75);

			batch.end();

			if (loadingCount <= 0)
				gameState = STATE.Menu;
		}

	}
	
	@Override
	public void dispose ()
	{
		batch.dispose();
		render.dispose();
		font.dispose();
		handler.dispose();
	}
	@Override
	public void resize(int width, int height)
	{
        viewport.update(width, height);
		camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
	}
}
