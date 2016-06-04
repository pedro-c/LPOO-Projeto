package gymsimulator.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gymsimulator.game.Logic.WeightLiftingLogic;
import gymsimulator.game.Scenes.Hud;
import gymsimulator.game.gymSimulator;

/**
 * Created by Tiago on 31/05/2016.
 */
public class WeightLiftingState implements Screen  {

    private Hud hud;

    WeightLiftingLogic wtLogic;

    Texture bar;
    Texture trace;
    Texture monkey;
    Texture monkeyRarm;
    Texture monkeyLarm;
    Texture weight;
    TextureRegion weightRegion;
    SpriteBatch spriteBatch;

    Texture backToMenu;
    Image imageBackToMenu;
    private gymSimulator game;
    public Stage stage;


    public WeightLiftingState(gymSimulator game) {
        this.game=game;
        stage = new Stage();
        hud = new Hud(game.batch);
        bar = new Texture("bar.png");
        trace = new Texture("trace.png");
        monkey = new Texture("monkey.png");
        monkeyRarm = new Texture("rightMarm.png");
        monkeyLarm = new Texture("leftMArm.png");
        weight = new Texture("weight.png");
        weightRegion = new TextureRegion(weight);
        spriteBatch = new SpriteBatch();

        wtLogic = new WeightLiftingLogic();

        backToMenu = new Texture("backButton.png");
        imageBackToMenu = new Image(backToMenu);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(imageBackToMenu);
    }

    public void update(float delta){
        float flag=wtLogic.update(delta);

    }

    @Override
    public void render(float delta) {
        update(delta);
        handleInput(delta);
        float deltaTime = Gdx.graphics.getDeltaTime();
        hud.setLabelPlay("Timer: " + ((Integer)((wtLogic.liftTimer))).toString() + " Score: " + ((Integer)wtLogic.score).toString() + "HighScore: " +((Integer)wtLogic.highscoreLifting).toString() );


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(bar, Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/6, Gdx.graphics.getWidth()/2, 40 );
        spriteBatch.draw(trace, wtLogic.trace_x, wtLogic.trace_y, 20, 40);
        imageBackToMenu.setPosition(Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/4);
        spriteBatch.draw(backToMenu, Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/4, 200, 200);

        //weight
        spriteBatch.draw(weightRegion,Gdx.graphics.getWidth()/2-400, 370,400,150, 800,300,1,1,wtLogic.weightRotation/3+wtLogic.weightRotation);

         //Monkey
        spriteBatch.draw(monkeyLarm,Gdx.graphics.getWidth()/2-150,400, 100,wtLogic.leftArmSize-wtLogic.weightRotation);
        spriteBatch.draw(monkeyRarm,Gdx.graphics.getWidth()/2+50,400, 100,wtLogic.rightArmSize+wtLogic.weightRotation);
        spriteBatch.draw(monkey,Gdx.graphics.getWidth()/2-300,50, 600,600);





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
