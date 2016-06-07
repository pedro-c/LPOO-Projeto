package gymsimulator.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gymsimulator.game.Logic.TreadmillLogic;
import gymsimulator.game.Scenes.Hud;
import gymsimulator.game.gymSimulator;

public class RunnerGameState implements Screen {

    private static final int        FRAME_COLS = 4;
    private static final int        FRAME_ROWS = 1;
    float treadSpeed = 0;
    private Hud hud;
    float stateTime;
    private gymSimulator game;

    TreadmillLogic tmLogic;
    Animation walkAnimation;
    Texture walkSheet;
    Texture footPrintR;
    Texture footPrintL;
    Texture falseFootPrintR;
    Texture falseFootPrintL;
    TextureRegion[] walkFrames;
    SpriteBatch spriteBatch;
    TextureRegion currentFrame;
    Texture backToMenu;
    Image foot1;
    Image foot2;
    Image foot3;
    Image foot4;
    Image falseFoot;
    Image imageBackToMenu;
    private Texture play;
    private Image playButton;
    private Texture shadowUp;
    private Texture shadowDown;
    private Texture replay;
    private Image replayButton;
    public Stage stage;
    public AssetManager manager;
    public boolean loaded=false;



    public RunnerGameState(gymSimulator game, AssetManager manager) {
        this.game=game;
        this.manager=manager;
        hud = new Hud(game.batch);
    }


    public void update(float dt){
        tmLogic.update(dt);
        handleInput(dt);
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        if(!loaded){
            hud.setLabelPlay("LOADING...");
            manager.load("flooranimation2.png", Texture.class);
            manager.load("footprintR.png", Texture.class);
            manager.load("footprintL.png", Texture.class);
            manager.load("falseFootR.png", Texture.class);
            manager.load("falseFootL.png", Texture.class);
            manager.load("backButton.png", Texture.class);
            manager.load("playButton.png", Texture.class);
            manager.load("replayButton.png", Texture.class);
            manager.load("shadowUp.png", Texture.class);
            manager.load("shadowDown.png", Texture.class);
            manager.finishLoading();
            walkSheet = manager.get("flooranimation2.png", Texture.class);
            footPrintR = manager.get("footprintR.png", Texture.class);
            footPrintL = manager.get("footprintL.png", Texture.class);
            falseFootPrintR = manager.get("falseFootR.png", Texture.class);
            falseFootPrintL = manager.get("falseFootL.png", Texture.class);
            backToMenu = manager.get("backButton.png", Texture.class);
            play = manager.get("playButton.png", Texture.class);
            replay = manager.get("replayButton.png", Texture.class);
            shadowUp = manager.get("shadowUp.png", Texture.class);
            shadowDown = manager.get("shadowDown.png", Texture.class);

            playButton = new Image(play);
            replayButton = new Image(replay);

            playButton.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y){
                    tmLogic.setGameStart(true);
                }

            });



            replayButton.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y){
                    tmLogic.setScore(0);
                    tmLogic.setStartTimer(0);
                    tmLogic.setTimer(1000);
                    tmLogic.setEndGame(false);
                    tmLogic.setSaveScores(true);
                    tmLogic.setLowerFoot(2);
                    tmLogic.setDeltaY(0);
                    tmLogic.setFoot1_x(200);
                    tmLogic.setFoot3_y(Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/3);
                    tmLogic.setFoot2_x(200+((Gdx.graphics.getWidth()-400)/2));
                    tmLogic.setFoot2_y(Gdx.graphics.getHeight()-2*Gdx.graphics.getHeight()/3);
                    tmLogic.setFoot3_x(200);
                    tmLogic.setFoot1_y(0);
                    tmLogic.setFoot4_x(200+((Gdx.graphics.getWidth()-400)/2));
                    tmLogic.setFoot4_y(Gdx.graphics.getHeight());
                    tmLogic.setFalseFoot_x(200);
                    tmLogic.setFalseFoot_y(Gdx.graphics.getHeight()-2*Gdx.graphics.getHeight()/3);
                    tmLogic.setFalseFoot_xR(200+((Gdx.graphics.getWidth()-400)/2));
                    tmLogic.setFalseFoot_xL(200);
                    tmLogic.setGameReady(false);
                    tmLogic.setGameStart(true);
                    tmLogic.setFoot1Clicked(false);
                    tmLogic.setFoot2Clicked(false);
                    tmLogic.setFoot3Clicked(false);
                    tmLogic.setFoot4Clicked(false);
                    tmLogic.setFootClick(false);
                    tmLogic.setUserName("");
                    replayButton.setPosition(-500,-500);
                    imageBackToMenu.setPosition(-500,-500);
                    playButton.setPosition(-500,-500);
                }

            });

            TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);              // #10
            walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
            int index = 0;
            for (int i = 0; i < FRAME_ROWS; i++) {
                for (int j = 0; j < FRAME_COLS; j++) {
                    walkFrames[index++] = tmp[i][j];
                }
            }

            walkAnimation = new Animation(0.025f, walkFrames);
            spriteBatch = new SpriteBatch();
            stateTime = 0f;
            tmLogic = new TreadmillLogic();
            stage = new Stage();

            imageBackToMenu = new Image(backToMenu);
            foot1 = new Image(footPrintR);
            foot2 = new Image(footPrintR);
            foot3 = new Image(footPrintR);
            foot4 = new Image(footPrintR);
            falseFoot = new Image(footPrintR);


            foot1.setWidth((Gdx.graphics.getWidth()-400)/2);
            foot1.setHeight(Gdx.graphics.getHeight()/3);
            foot2.setWidth((Gdx.graphics.getWidth()-400)/2);
            foot2.setHeight(Gdx.graphics.getHeight()/3);
            foot3.setWidth((Gdx.graphics.getWidth()-400)/2);
            foot3.setHeight(Gdx.graphics.getHeight()/3);
            foot4.setWidth((Gdx.graphics.getWidth()-400)/2);
            foot4.setHeight(Gdx.graphics.getHeight()/3);
            falseFoot.setWidth((Gdx.graphics.getWidth()-400)/2);
            falseFoot.setHeight(Gdx.graphics.getHeight()/3);


            Gdx.input.setInputProcessor(stage);

            stage.addActor(foot1);
            stage.addActor(foot2);
            stage.addActor(foot3);
            stage.addActor(foot4);
            stage.addActor(falseFoot);
            stage.addActor(imageBackToMenu);
            stage.addActor(playButton);
            stage.addActor(replayButton);
            hud.setLabelPlay(" ");
            loaded=true;
        }else{
            update(Gdx.graphics.getDeltaTime());
            float deltaTime = Gdx.graphics.getDeltaTime();
            if(tmLogic.getTimer() > 2000)
                tmLogic.setEndGame(true);
            if(tmLogic.isEndGame()){
                hud.setLabelPlay(" Score: " + ((Integer)(tmLogic.getScore())).toString() +  "   HighScore: " + ((Integer)tmLogic.getHighscoreTreadmill()).toString());
                hud.showLost(true);
            }else{
                hud.setLabelPlay("Timer: " + ((Integer)tmLogic.getTimer()).toString() + " Steps: " + ((Integer)tmLogic.getScore()).toString() +  "   HighScore: " + ((Integer)tmLogic.getHighscoreTreadmill()).toString());
                hud.showLost(false);
            }


            stateTime = deltaTime;
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);
            spriteBatch.begin();
            spriteBatch.draw(currentFrame, 200, 0,Gdx.graphics.getWidth()-400,Gdx.graphics.getHeight());

            if(tmLogic.getFoot1_x()==200){
                spriteBatch.draw(footPrintL, tmLogic.getFoot1_x(), tmLogic.getFoot1_y(),(Gdx.graphics.getWidth()-400)/2,Gdx.graphics.getHeight()/3);
                spriteBatch.draw(falseFootPrintR, tmLogic.getFalseFoot_xR(), tmLogic.getFoot1_y(),(Gdx.graphics.getWidth()-400)/2,Gdx.graphics.getHeight()/3);
            }

            else{
                spriteBatch.draw(footPrintR, tmLogic.getFoot1_x(), tmLogic.getFoot1_y(),(Gdx.graphics.getWidth()-400)/2,Gdx.graphics.getHeight()/3);
                spriteBatch.draw(falseFootPrintL, tmLogic.getFalseFoot_xL(), tmLogic.getFoot1_y(),(Gdx.graphics.getWidth()-400)/2,Gdx.graphics.getHeight()/3);

            }
            if(tmLogic.getFoot2_x()==200){
                spriteBatch.draw(footPrintL, tmLogic.getFoot2_x(), tmLogic.getFoot2_y(),(Gdx.graphics.getWidth()-400)/2,Gdx.graphics.getHeight()/3);
                spriteBatch.draw(falseFootPrintR, tmLogic.getFalseFoot_xR(), tmLogic.getFoot2_y(),(Gdx.graphics.getWidth()-400)/2,Gdx.graphics.getHeight()/3);
            }

            else{
                spriteBatch.draw(footPrintR, tmLogic.getFoot2_x(), tmLogic.getFoot2_y(),(Gdx.graphics.getWidth()-400)/2,Gdx.graphics.getHeight()/3);
                spriteBatch.draw(falseFootPrintL, tmLogic.getFalseFoot_xL(), tmLogic.getFoot2_y(),(Gdx.graphics.getWidth()-400)/2,Gdx.graphics.getHeight()/3);

            }
            if(tmLogic.getFoot3_x()==200){
                spriteBatch.draw(footPrintL, tmLogic.getFoot3_x(), tmLogic.getFoot3_y(),(Gdx.graphics.getWidth()-400)/2,Gdx.graphics.getHeight()/3);
                spriteBatch.draw(falseFootPrintR, tmLogic.getFalseFoot_xR(), tmLogic.getFoot3_y(),(Gdx.graphics.getWidth()-400)/2,Gdx.graphics.getHeight()/3);
            }

            else{
                spriteBatch.draw(footPrintR, tmLogic.getFoot3_x(), tmLogic.getFoot3_y(),(Gdx.graphics.getWidth()-400)/2,Gdx.graphics.getHeight()/3);
                spriteBatch.draw(falseFootPrintL, tmLogic.getFalseFoot_xL(), tmLogic.getFoot3_y(),(Gdx.graphics.getWidth()-400)/2,Gdx.graphics.getHeight()/3);

            }
            if(tmLogic.getFoot4_x()==200){
                spriteBatch.draw(footPrintL, tmLogic.getFoot4_x(), tmLogic.getFoot4_y(),(Gdx.graphics.getWidth()-400)/2,Gdx.graphics.getHeight()/3);
                spriteBatch.draw(falseFootPrintR, tmLogic.getFalseFoot_xR(), tmLogic.getFoot4_y(),(Gdx.graphics.getWidth()-400)/2,Gdx.graphics.getHeight()/3);
            }

            else{
                spriteBatch.draw(footPrintR, tmLogic.getFoot4_x(), tmLogic.getFoot4_y(),(Gdx.graphics.getWidth()-400)/2,Gdx.graphics.getHeight()/3);
                spriteBatch.draw(falseFootPrintL, tmLogic.getFalseFoot_xL(), tmLogic.getFoot4_y(),(Gdx.graphics.getWidth()-400)/2,Gdx.graphics.getHeight()/3);

            }
            spriteBatch.draw(shadowUp, 200, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/3, (Gdx.graphics.getWidth()-400), Gdx.graphics.getHeight()/3);
            spriteBatch.draw(shadowDown, 200, 0, (Gdx.graphics.getWidth()-400), Gdx.graphics.getHeight()-2*Gdx.graphics.getHeight()/3);

            if(tmLogic.isGameStart()==false){
                playButton.setPosition(Gdx.graphics.getWidth()/2-150, Gdx.graphics.getHeight()/2-150);
                spriteBatch.draw(play, Gdx.graphics.getWidth()/2-150, Gdx.graphics.getHeight()/2-150, 300, 300);
            }
            if(tmLogic.isEndGame()==true){
                replayButton.setPosition(Gdx.graphics.getWidth()/2-400, Gdx.graphics.getHeight()/2-150);
                spriteBatch.draw(replay, Gdx.graphics.getWidth()/2-400, Gdx.graphics.getHeight()/2-150, 300, 300);
                imageBackToMenu.setPosition(Gdx.graphics.getWidth()/2+100, Gdx.graphics.getHeight()/2-150);
                spriteBatch.draw(backToMenu, Gdx.graphics.getWidth()/2+100, Gdx.graphics.getHeight()/2-150, 300, 300);
            }else{
                imageBackToMenu.setPosition(Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/4);
                spriteBatch.draw(backToMenu, Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/4, 200, 200);
            }

            foot1.setPosition(tmLogic.getFoot1_x(), tmLogic.getFoot1_y());
            foot2.setPosition(tmLogic.getFoot2_x(), tmLogic.getFoot2_y());
            foot3.setPosition(tmLogic.getFoot3_x(), tmLogic.getFoot3_y());
            foot4.setPosition(tmLogic.getFoot4_x(), tmLogic.getFoot4_y());
            falseFoot.setPosition(tmLogic.getFalseFoot_x(),tmLogic.getFalseFoot_y());

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

        foot1.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button){
                if(tmLogic.getLowerFoot() == 1) {
                    tmLogic.setFootClick(true);
                    tmLogic.setFoot1Clicked(true);
                }
            }

        });

        foot2.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button){
               if(tmLogic.getLowerFoot() == 2) {
                   tmLogic.setFootClick(true);
                   tmLogic.setFoot2Clicked(true);
               }
            }

        });

        foot3.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button){
                if(tmLogic.getLowerFoot() == 3){
                    tmLogic.setFootClick(true);
                    tmLogic.setFoot3Clicked(true);
                }
            }

        });

        foot4.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button){
              if(tmLogic.getLowerFoot() == 4){
                  tmLogic.setFootClick(true);
                  tmLogic.setFoot4Clicked(true);
                }

            }

        });

        falseFoot.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button){
                tmLogic.setEndGame(true);
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
        walkSheet.dispose();
        footPrintR.dispose();
        footPrintL.dispose();
        falseFootPrintL.dispose();
        falseFootPrintR.dispose();
        backToMenu.dispose();
        stage.dispose();
    }
}
