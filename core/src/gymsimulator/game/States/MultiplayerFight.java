package gymsimulator.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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
    public Stage stage;



    public MultiplayerFight(gymSimulator game){

        this.game=game;
        stage = new Stage();
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

        Gdx.input.setInputProcessor(stage);
        stage.addActor(buttonFistB);
        stage.addActor(buttonFistR);
        stage.addActor(buttonShieldB);
        stage.addActor(buttonShieldR);

        buttonFistB.setPosition(100, Gdx.graphics.getHeight()/12);
        buttonFistR.setPosition(Gdx.graphics.getWidth()-350, Gdx.graphics.getHeight()/12);
        buttonShieldB.setPosition(100, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/12-250);
        buttonShieldR.setPosition(Gdx.graphics.getWidth()-350, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/12-250);


        buttonFistB.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                mpLogic.playerBlueAttacking();
            }

        });

        buttonFistR.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                mpLogic.playerRedAttacking();
            }

        });

        buttonShieldB.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button){
                mpLogic.setPlayerBlueDefending(false);
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                mpLogic.setPlayerBlueDefending(true);

                return true;
            }

        });

        buttonShieldR.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button){
                mpLogic.setPlayerRedDefending(false);
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                mpLogic.setPlayerRedDefending(true);

                return true;
            }

        });


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

        spriteBatch.draw(fistB, 100, Gdx.graphics.getHeight()/12, 250, 250 );
        spriteBatch.draw(fistR, Gdx.graphics.getWidth()-350, Gdx.graphics.getHeight()/12, 250, 250 );
        spriteBatch.draw(shieldB, 100, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/12-250, 250, 250 );
        spriteBatch.draw(shieldR, Gdx.graphics.getWidth()-350, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/12-250, 250, 250 );


        spriteBatch.draw(healthBarRed, Gdx.graphics.getWidth()-450, 100+(Gdx.graphics.getHeight()-2*Gdx.graphics.getHeight()/12), 50,Gdx.graphics.getHeight()-2*Gdx.graphics.getHeight()/12);
        spriteBatch.draw(healthBarGreen, Gdx.graphics.getWidth()-450,100+(Gdx.graphics.getHeight()-2*Gdx.graphics.getHeight()/12-mpLogic.redHealth), 50,  mpLogic.redHealth );

        spriteBatch.draw(healthBarRed, 400, 100+(Gdx.graphics.getHeight()-2*Gdx.graphics.getHeight()/12), 50,  Gdx.graphics.getHeight()-2*Gdx.graphics.getHeight()/12 );
        spriteBatch.draw(healthBarGreen, 400, 100+(Gdx.graphics.getHeight()-2*Gdx.graphics.getHeight()/12-mpLogic.blueHealth), 50, mpLogic.blueHealth );

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
