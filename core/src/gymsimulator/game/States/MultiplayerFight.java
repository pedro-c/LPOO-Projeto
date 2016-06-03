package gymsimulator.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import gymsimulator.game.Logic.MultiplayerLogic;
import gymsimulator.game.gymSimulator;

/**
 * Created by pedro on 31/05/2016.
 */
public class MultiplayerFight implements Screen {


    private gymSimulator game;
    private MultiplayerLogic mpLogic;
    private Texture fistR;
    private Texture fistB;
    private Texture shieldR;
    private Texture shieldB;
    private Image buttonFistR;
    private Image buttonFistB;
    private Image buttonShieldR;
    private Image buttonShieldB;
    private Texture healthBarRed;
    private Texture healthBarGreen;
    SpriteBatch spriteBatch;



    public MultiplayerFight(gymSimulator game){

        this.game=game;
        mpLogic = new MultiplayerLogic();
        fistR = new Texture("fistR.png");
        fistB = new Texture("fistB.png");
        shieldR = new Texture("shieldR.png");
        shieldB = new Texture("shieldB.png");
        healthBarGreen = new Texture("HBgreen.png");
        healthBarRed = new Texture("HBred.png");
        buttonFistB = new Image(fistB);
        buttonFistR = new Image(fistR);
        buttonShieldB = new Image(shieldB);
        buttonShieldR = new Image(shieldR);
        spriteBatch = new SpriteBatch();




    }

    public void update(float dt){
        mpLogic.update(dt);
        handleInput(dt);
    }

    @Override
    public void render(float delta) {
        update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        spriteBatch.begin();

        spriteBatch.draw(fistB, Gdx.graphics.getWidth()/12, Gdx.graphics.getHeight()/9, 250, 250 );
        spriteBatch.draw(fistR, Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/12-250, Gdx.graphics.getHeight()/9, 250, 250 );
        spriteBatch.draw(shieldB, Gdx.graphics.getWidth()/12, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/9-250, 250, 250 );
        spriteBatch.draw(shieldR, Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/12-250, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/9-250, 250, 250 );
        spriteBatch.draw(healthBarRed, Gdx.graphics.getWidth()/12+(250/2), Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/9+100, 300, 20 );
        spriteBatch.draw(healthBarRed, (Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/12-250)+(250/2), Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/9+100, 50, 400 );
        spriteBatch.draw(healthBarGreen, Gdx.graphics.getWidth()/12+(250/2), Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/9+100, 300, 20 );
        spriteBatch.draw(healthBarGreen, (Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/12-250)+(250/2), Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/9+100, 50, 400 );

        spriteBatch.end();




    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){

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
