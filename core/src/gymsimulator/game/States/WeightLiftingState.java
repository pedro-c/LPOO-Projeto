package gymsimulator.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
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

    private Texture bar;
    private Texture trace;
    private Texture monkey;
    private Texture monkeyRarm;
    private Texture monkeyLarm;
    private Texture weight;
    private Texture background;
    private TextureRegion weightRegion;
    private SpriteBatch spriteBatch;
    private Texture play;
    private Image playButton;
    private Texture replay;
    private Image replayButton;

    Texture backToMenu;
    Image imageBackToMenu;
    private gymSimulator game;
    public Stage stage;
    public AssetManager manager;
    private boolean loaded=false;


    public WeightLiftingState(gymSimulator game, AssetManager manager) {
        this.game=game;
        this.manager=manager;
        stage = new Stage();
        hud = new Hud(game.batch);

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


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

            if(!loaded){
                hud.setLabelPlay("LOADING...");
                manager.load("bar.png", Texture.class);
                manager.load("trace.png", Texture.class);
                manager.load("monkey.png", Texture.class);
                manager.load("rightMarm.png", Texture.class);
                manager.load("leftMArm.png", Texture.class);
                manager.load("weight.png", Texture.class);
                manager.load("backButton.png", Texture.class);
                manager.load("background3.png", Texture.class);
                manager.load("playButton.png", Texture.class);
                manager.load("replayButton.png", Texture.class);
                manager.finishLoading();
                bar = manager.get("bar.png", Texture.class);
                trace = manager.get("trace.png", Texture.class);
                monkey = manager.get("monkey.png", Texture.class);
                monkeyRarm = manager.get("rightMarm.png", Texture.class);
                monkeyLarm = manager.get("leftMArm.png", Texture.class);
                weight = manager.get("weight.png", Texture.class);
                background = manager.get("background3.png", Texture.class);
                play = manager.get("playButton.png", Texture.class);
                replay = manager.get("replayButton.png", Texture.class);
                weightRegion = new TextureRegion(weight);
                playButton = new Image(play);
                replayButton = new Image(replay);

                playButton.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y){
                        wtLogic.gameStart=true;
                    }

                });



                replayButton.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y){
                        wtLogic.scoresSaved = false;
                        wtLogic.lifted = false;
                        wtLogic.leftArmSize=200;
                        wtLogic.rightArmSize=200;
                        wtLogic. weightRotation=0;
                        wtLogic.changeBarDirection = 1;
                        wtLogic.score = 0;
                        wtLogic.incScore = false;
                        wtLogic.timer=8*100;
                        wtLogic.liftTimer = 150;
                        wtLogic.startTimer= false;
                        wtLogic.endGame=false;
                        wtLogic.saveScores=true;
                        wtLogic.trace_x = wtLogic.statusGreenBarMaxX - (wtLogic.statusGreenBarMaxX - wtLogic.statusGreenBarMinX)/2 ;
                        replayButton.setPosition(-500,-500);
                        imageBackToMenu.setPosition(-500,-500);
                        playButton.setPosition(-500,-500);
                    }

                });
                loaded=true;
                stage.addActor(playButton);
                stage.addActor(replayButton);
                hud.setLabelPlay(" ");
            }else {


                update(delta);
                handleInput(delta);


                float deltaTime = Gdx.graphics.getDeltaTime();
                hud.setLabelPlay("Timer: " + ((Integer) ((wtLogic.liftTimer))).toString() + " Score: " + ((Integer) wtLogic.score).toString() + "HighScore: " + ((Integer) wtLogic.highscoreLifting).toString());


                spriteBatch.begin();
                spriteBatch.draw(background, 0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
                spriteBatch.draw(bar, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 6, Gdx.graphics.getWidth() / 2, 40);
                spriteBatch.draw(trace, wtLogic.trace_x, wtLogic.trace_y, 20, 40);

                //weight
                spriteBatch.draw(weightRegion, Gdx.graphics.getWidth() / 2 - 400, 370, 400, 150, 800, 300, 1, 1, wtLogic.weightRotation / 3 + wtLogic.weightRotation);

                //Monkey
                spriteBatch.draw(monkeyLarm, Gdx.graphics.getWidth() / 2 - 150, 400, 100, wtLogic.leftArmSize - wtLogic.weightRotation*2);
                spriteBatch.draw(monkeyRarm, Gdx.graphics.getWidth() / 2 + 50, 400, 100, wtLogic.rightArmSize + wtLogic.weightRotation*2);
                spriteBatch.draw(monkey, Gdx.graphics.getWidth() / 2 - 300, 50, 600, 600);

                if(wtLogic.gameStart==false){
                    playButton.setPosition(Gdx.graphics.getWidth()/2-150, Gdx.graphics.getHeight()/2-150);
                    spriteBatch.draw(play, Gdx.graphics.getWidth()/2-150, Gdx.graphics.getHeight()/2-150, 300, 300);
                }
                if(wtLogic.endGame==true ){
                    replayButton.setPosition(Gdx.graphics.getWidth()/2-400, Gdx.graphics.getHeight()/2-150);
                    spriteBatch.draw(replay, Gdx.graphics.getWidth()/2-400, Gdx.graphics.getHeight()/2-150, 300, 300);
                    imageBackToMenu.setPosition(Gdx.graphics.getWidth()/2+100, Gdx.graphics.getHeight()/2-150);
                    spriteBatch.draw(backToMenu, Gdx.graphics.getWidth()/2+100, Gdx.graphics.getHeight()/2-150, 300, 300);
                }else{
                    imageBackToMenu.setPosition(Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4);
                    spriteBatch.draw(backToMenu, Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4, 200, 200);
                }

                spriteBatch.end();

            }
        hud.stage.draw();

    }




    @Override
    public void show() {
    }

    public void handleInput(float dt){

        imageBackToMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

                game.setScreen(new MainMenu(game, manager));
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
        spriteBatch.dispose();
        stage.dispose();
        manager.clear();

    }
}
