package gymsimulator.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import gymsimulator.game.Logic.AbsLogic;
import gymsimulator.game.Scenes.Hud;
import gymsimulator.game.gymSimulator;

/**
 * Created by pedro on 31/05/2016.
 */
public class AbsGameState implements Screen {

    Texture bar;
    Texture trace;
    private Hud hud;
    SpriteBatch spriteBatch;
    AbsLogic absLogic;
    Texture backToMenu;
    Image imageBackToMenu;
    private gymSimulator game;
    public Stage stage;

    public AbsGameState(gymSimulator game){

        this.game=game;
        stage = new Stage();
        hud = new Hud(game.batch);
        bar = new Texture("bar.png");
        trace = new Texture("trace.png");
        spriteBatch = new SpriteBatch();
        absLogic = new AbsLogic();
        backToMenu = new Texture("backButton.png");
        imageBackToMenu = new Image(backToMenu);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(imageBackToMenu);


    }

    public void update(float dt){
       absLogic.update(dt);
        handleInput(dt);


    }

    @Override
    public void render(float delta) {
        update(Gdx.graphics.getDeltaTime());
        float deltaTime = Gdx.graphics.getDeltaTime();
        if(absLogic.startTimer == true){
            absLogic.timer -= deltaTime;
        }


        hud.setLabelPlay("Timer: " + ((Integer)((absLogic.timer))).toString() + " Score: " + ((Integer)absLogic.score).toString() + "HighScore: " +((Integer)absLogic.highscoreAbs).toString() );

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(bar, Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/6, Gdx.graphics.getWidth()/2, 40 );
        spriteBatch.draw(trace, absLogic.trace_x, absLogic.trace_y, 20, 40);
        imageBackToMenu.setPosition(Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/4);
        spriteBatch.draw(backToMenu, Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/4, 200, 200);

        spriteBatch.end();
        hud.stage.draw();




    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){

        imageBackToMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

                game.setScreen(new MainMenu(game));
                dispose();
            }

        });


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
        bar.dispose();
        trace.dispose();
        spriteBatch.dispose();
        backToMenu.dispose();
        stage.dispose();

    }
}
