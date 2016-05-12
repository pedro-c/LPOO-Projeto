package gymsimulator.game.States;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import gymsimulator.game.gymSimulator;

public class RunnerGameState implements Screen {

    private static final int FRAME_COLS = 4;
    private static final int FRAME_ROWS = 1;

    Animation walkAnimation;
    Texture walkSheet;
    TextureRegion[] walkFrames;
    SpriteBatch spriteBatch;
    TextureRegion currentFrame;

    float stateTime;

    public RunnerGameState(gymSimulator game) {

        walkSheet = new Texture("flooranimation.png"); // #9
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);              // #10
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
    }





    @Override
    public void show() {

    }

    public void handleInput(float dt){

    }

    public void update(float dt){


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);                        // #14
        stateTime += Gdx.graphics.getDeltaTime();           // #15
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);  // #16
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, 10, 10);             // #17
        spriteBatch.end();

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
