package gymsimulator.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;


import gymsimulator.game.Treadmill;
import gymsimulator.game.gymSimulator;

public class RunnerGameState implements Screen {

    private Treadmill tread;

    public RunnerGameState(gymSimulator game) {

        this.tread = new Treadmill();
        tread.create();


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

        update(delta);

        Gdx.gl.glClearColor(0,(float)150/255,(float)136/255,0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tread.render();


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
