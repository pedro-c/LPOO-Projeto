package gymsimulator.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import gymsimulator.game.gymSimulator;

/**
 * Created by pedro on 10/05/2016.
 */
public class Hud {
    public Stage stage;
    private Viewport viewport;
    private Skin skin;
    private Table table = new Table();
    public TextButton playSelectedGame;
    private Label lost;
    BitmapFont font;


    public Hud(SpriteBatch sb){
        skin = new Skin();
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        font= new BitmapFont();
        font.getData().setScale(3);
        skin.add("default", font);

        viewport = new StretchViewport(gymSimulator.V_WIDTH, gymSimulator.V_HEIGHT);

        stage = new Stage(viewport, sb);

        Gdx.input.setInputProcessor(stage);


        TextButton.TextButtonStyle playButtonStyle = new TextButton.TextButtonStyle();

        playButtonStyle.up = skin.newDrawable("white", Color.CLEAR);
        playButtonStyle.down = skin.newDrawable("white", Color.BLUE);
        playButtonStyle.checked = skin.newDrawable("white", Color.CLEAR);
        playButtonStyle.font = skin.getFont("default");

        skin.add("default", playButtonStyle);

        playSelectedGame = new TextButton("", skin);
        lost = new Label("LOST!", new Label.LabelStyle(font, Color.WHITE));
        lost.setVisible(false);

        table.top();
        table.setFillParent(true);
        table.add(playSelectedGame).expandX().padTop(10);
        table.row();
        table.add(lost);
        stage.addActor(table);


    }

    public void setLabelPlay(String label){

        this.playSelectedGame.setText(label);
    }


    public void showLabels(boolean bool){
        table.setVisible(bool);
    }

    public void showLost(boolean bool){
        lost.setVisible(bool);
    }



}
