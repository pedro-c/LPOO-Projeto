package gymsimulator.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gymsimulator.game.Logic.AbsLogic;
import gymsimulator.game.Scenes.Hud;
import gymsimulator.game.gymSimulator;

/**
 * Created by pedro on 31/05/2016.
 */
public class AbsGameState implements Screen {

    private Texture bar;
    private Texture trace;
    private Hud hud;
    private SpriteBatch spriteBatch;
    private AbsLogic absLogic;
    private Texture backToMenu;
    private Texture bro1;
    private Texture bro2;
    private Texture bro3;
    private Texture bro4;
    private Texture bro5;
    private Texture bro6;
    private Texture background;
    private Image imageBackToMenu;
    private Texture play;
    private Image playButton;
    private Texture replay;
    private Image replayButton;
    private gymSimulator game;
    public Stage stage;
    public AssetManager manager;
    public boolean loaded = false;

    public AbsGameState(gymSimulator game, AssetManager manager){
        this.manager=manager;
        this.game=game;
        stage = new Stage();
        hud = new Hud(game.batch);

        spriteBatch = new SpriteBatch();
        absLogic = new AbsLogic();



    }

    public void update(float dt){
       absLogic.update(dt);
        handleInput(dt);


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,(float)150/255,(float)136/255,0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!loaded){
            hud.setLabelPlay("LOADING...");
            manager.load("bar.png", Texture.class);
            manager.load("trace.png", Texture.class);
            manager.load("backButton.png", Texture.class);
            manager.load("bro1.png", Texture.class);
            manager.load("bro2.png", Texture.class);
            manager.load("bro3.png", Texture.class);
            manager.load("bro4.png", Texture.class);
            manager.load("bro5.png", Texture.class);
            manager.load("bro6.png", Texture.class);
            manager.load("playButton.png", Texture.class);
            manager.load("background2.png", Texture.class);
            manager.load("replayButton.png", Texture.class);
            manager.finishLoading();
            bro1 = manager.get("bro1.png", Texture.class);
            bro2 = manager.get("bro2.png", Texture.class);
            bro3 = manager.get("bro3.png", Texture.class);
            bro4 = manager.get("bro4.png", Texture.class);
            bro5 = manager.get("bro5.png", Texture.class);
            bro6 = manager.get("bro6.png", Texture.class);
            bar = manager.get("bar.png", Texture.class);
            play = manager.get("playButton.png", Texture.class);
            replay = manager.get("replayButton.png", Texture.class);
            background = manager.get("background2.png", Texture.class);
            trace = manager.get("trace.png", Texture.class);
            backToMenu = manager.get("backButton.png", Texture.class);
            imageBackToMenu = new Image(backToMenu);
            playButton = new Image(play);
            replayButton = new Image(replay);

            playButton.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y){
                    absLogic.gameStart=true;
                    absLogic.startTimer=true;
                }

            });



            replayButton.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y){
                    absLogic.score=0;
                    absLogic.incScore = false;
                    absLogic.timer=8*100;
                    absLogic.startTimer=false;
                    absLogic.highscoreAbs=0;
                    absLogic.saveScores = false;
                    absLogic.delta = 0;
                    absLogic.lift = false;
                    absLogic.startTimer=true;
                    absLogic.gameStart=true;
                    absLogic.endGame=false;
                    absLogic.saveScores=true;

                }

            });

            Gdx.input.setInputProcessor(stage);
            stage.addActor(imageBackToMenu);
            stage.addActor(playButton);
            stage.addActor(replayButton);
            loaded=true;
        }else {
            update(Gdx.graphics.getDeltaTime());
            float deltaTime = Gdx.graphics.getDeltaTime();
            if (absLogic.startTimer == true) {
                absLogic.timer -= deltaTime;
            }


            hud.setLabelPlay("Timer: " + ((Integer) ((absLogic.timer))).toString() + " Score: " + ((Integer) absLogic.score).toString() + "HighScore: " + ((Integer) absLogic.highscoreAbs).toString());


            spriteBatch.begin();
            spriteBatch.draw(background, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            spriteBatch.draw(bar, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 6, Gdx.graphics.getWidth() / 2, 40);
            spriteBatch.draw(trace, absLogic.trace_x, absLogic.trace_y, 20, 40);
            imageBackToMenu.setPosition(Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4);

            if(absLogic.lift==false){
                spriteBatch.draw(bro1, Gdx.graphics.getWidth() / 2 - 300, 100 ,600, 600);
            }else{
                if(absLogic.delta < 5)
                    spriteBatch.draw(bro1, Gdx.graphics.getWidth() / 2 - 300, 100 ,600, 600);
                else if(absLogic.delta < 10)
                    spriteBatch.draw(bro2, Gdx.graphics.getWidth() / 2 - 300, 100 ,600, 600);
                else if(absLogic.delta < 15)
                    spriteBatch.draw(bro3, Gdx.graphics.getWidth() / 2 - 300, 100 ,600, 600);
                else if(absLogic.delta < 20)
                    spriteBatch.draw(bro4, Gdx.graphics.getWidth() / 2 - 300, 100 ,600, 600);
                else if(absLogic.delta < 25)
                    spriteBatch.draw(bro5, Gdx.graphics.getWidth() / 2 - 300, 100 ,600, 600);
                else if(absLogic.delta < 30)
                    spriteBatch.draw(bro6, Gdx.graphics.getWidth() / 2 - 300, 100 ,600, 600);
                else if(absLogic.delta < 35)
                    spriteBatch.draw(bro5, Gdx.graphics.getWidth() / 2 - 300, 100 ,600, 600);
                else if(absLogic.delta < 45)
                    spriteBatch.draw(bro2, Gdx.graphics.getWidth() / 2 - 300, 100 ,600, 600);
                else if(absLogic.delta > 45)
                    spriteBatch.draw(bro1, Gdx.graphics.getWidth() / 2 - 300, 100 ,600, 600);
            }
            if(absLogic.gameStart==false){
                playButton.setPosition(Gdx.graphics.getWidth()/2-150, Gdx.graphics.getHeight()/2-150);
                spriteBatch.draw(play, Gdx.graphics.getWidth()/2-150, Gdx.graphics.getHeight()/2-150, 300, 300);
            }
            if(absLogic.endGame==true){
                replayButton.setPosition(Gdx.graphics.getWidth()/2-400, Gdx.graphics.getHeight()/2-150);
                spriteBatch.draw(replay, Gdx.graphics.getWidth()/2-400, Gdx.graphics.getHeight()/2-150, 300, 300);
                imageBackToMenu.setPosition(Gdx.graphics.getWidth()/2+100, Gdx.graphics.getHeight()/2-150);
                spriteBatch.draw(backToMenu, Gdx.graphics.getWidth()/2+100, Gdx.graphics.getHeight()/2-150, 300, 300);
            }else{
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
