package gymsimulator.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import gymsimulator.game.gymSimulator;

/**
 * Created by pedro on 10/05/2016.
 */
public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label play;
    Label arrowLeft;
    Label arrowRight;

    public Hud(SpriteBatch sb){
        worldTimer=300;
        timeCount=0;
        score = 0;

        viewport = new StretchViewport(gymSimulator.V_WIDTH, gymSimulator.V_HEIGHT);

        stage= new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        //table.setWidth(gymSimulator.V_WIDTH * 4);
        table.setFillParent(true);

        play = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        arrowLeft = new Label("<", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        arrowRight = new Label(">", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        arrowLeft.setFontScale(5,5);
        arrowRight.setFontScale(5,5);


        table.add(play).expandX().padTop(10);

        table.row()

        stage.addActor(table);


    }

    public void setLabelPlay(String label){
        play.setText(label);
    }

}
