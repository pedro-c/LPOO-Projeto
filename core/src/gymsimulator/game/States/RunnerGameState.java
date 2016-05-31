package gymsimulator.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


import gymsimulator.game.Logic.TreadmillLogic;
import gymsimulator.game.Scenes.Hud;
import gymsimulator.game.gymSimulator;

public class RunnerGameState implements Screen {

    private static final int        FRAME_COLS = 4;         // #1
    private static final int        FRAME_ROWS = 1;         // #2
    float treadSpeed = 0;
    int score=0;
    private Hud hud;
    private int timer = 8*100;
    float stateTime;



    TreadmillLogic tmLogic;
    Animation walkAnimation;          // #3
    Texture walkSheet;              // #4
    TextureRegion[] walkFrames;             // #5
    SpriteBatch spriteBatch;            // #6
    TextureRegion currentFrame;           // #7





    public RunnerGameState(gymSimulator game) {
        hud = new Hud(game.batch);
        walkSheet = new Texture("flooranimation2.png"); // #9
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);              // #10
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(0.025f, walkFrames);      // #11
        spriteBatch = new SpriteBatch();                // #12
        stateTime = 0f;                         // #13

        tmLogic = new TreadmillLogic();
    }


    public void update(float dt){
        float flag=tmLogic.update(dt);
        float acceleration=1;
        float maxSpeed=5;
        if(tmLogic.endGame==false){
            if(flag == -1){
                if(treadSpeed - acceleration > 0){
                    treadSpeed-=acceleration;
                }else{
                    treadSpeed=0;
                }
            }else{
                if(treadSpeed<maxSpeed)
                    treadSpeed+=acceleration;
                else{
                    treadSpeed=maxSpeed;
                }
            }
            hud.setLabelPlay(((Integer)tmLogic.score).toString());
        }else{
            treadSpeed=0;
        }

    }

    @Override
    public void render(float delta) {
        update(Gdx.graphics.getDeltaTime());
        float deltaTime = Gdx.graphics.getDeltaTime();
        if(tmLogic.startTimer == 1){
            timer -= deltaTime;
        }

        if(timer>0){
            hud.setLabelPlay("Timer: " + ((Integer)((timer/100))).toString() + " Score: " + ((Integer)tmLogic.score).toString());
        }else{
            tmLogic.endGame=true;
            hud.setLabelPlay(" Score: " + ((Integer)tmLogic.score).toString());
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);                        // #14
        stateTime = deltaTime;
        currentFrame = walkAnimation.getKeyFrame(stateTime * treadSpeed, true);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, 200, 10,Gdx.graphics.getWidth()-400,Gdx.graphics.getHeight()-150);             // #17
        spriteBatch.end();
        hud.stage.draw();

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
