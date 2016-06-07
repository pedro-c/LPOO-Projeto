package gymsimulator.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by pedro on 07/06/2016.
 */
public class MyTextInputListener implements Input.TextInputListener {
    java.lang.String user;
    @Override
    public void input (String text) {
        this.user=text;
        Gdx.app.log("Username", text);
    }

    @Override
    public void canceled () {
    }

    public String returnUser(){
        return this.user;
    }
}
