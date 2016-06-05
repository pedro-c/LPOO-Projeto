package gymsimulator.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gymsimulator.game.States.MainMenu;

public class gymSimulator extends Game {

	public static final int V_WIDTH = 1280;
	public static final int V_HEIGHT = 720;
	public static final float PPM = 1;


	public SpriteBatch batch;
	public AssetManager manager = new AssetManager();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		setScreen(new MainMenu(this, manager));
	}

	@Override
	public void render () {
		super.render();
	}
}
