package gymsimulator.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gymsimulator.game.Scenes.Hud;
import gymsimulator.game.gymSimulator;

/**
 * Created by pedro on 06/06/2016.
 */
public class ScoresState implements Screen {

    private Texture weight;
    private Texture label;
    private Texture backToMenu;
    private Image backButton;
    private AssetManager manager;
    private boolean loaded=false;
    private gymSimulator game;
    private SpriteBatch spriteBatch;
    private Stage stage;
    private Hud hud;
    private int runnerScore;
    private int weightScore;
    private int absScore;
    private BitmapFont fontScore;
    private BitmapFont fontText;
    Preferences prefs;
    private FileHandle file;
    String weightHighScore;
    String absHighScore;
    String treadHighScore;

    public ScoresState(gymSimulator game, AssetManager manager){
        this.game=game;
        this.manager=manager;
        spriteBatch = new SpriteBatch();
        stage = new Stage();
        hud = new Hud(game.batch);
        prefs = Gdx.app.getPreferences("GymHighScores");

        fontScore = new BitmapFont();
        fontScore.getData().setScale(6);
        fontText = new BitmapFont();
        fontText.getData().setScale(3);
        String filename;
        filename = "highscores.dat";
        file = Gdx.files.local(filename);

        if(file.exists()){
            weightHighScore = file.readString();
            absHighScore = file.readString();
            treadHighScore = file.readString();
        }

        //hud.setLabelPlay("Timer: " + ((Integer) ((absLogic.timer))).toString() + " Score: " + ((Integer) absLogic.score).toString() + "HighScore: " + ((Integer) absLogic.highscoreAbs).toString());
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        if(!loaded){
            manager.load("roundWeight.png", Texture.class);
            manager.load("scoresLabel.png", Texture.class);
            manager.load("backButton.png", Texture.class);
            manager.finishLoading();
            weight = manager.get("roundWeight.png", Texture.class);
            label = manager.get("scoresLabel.png", Texture.class);
            backToMenu = manager.get("backButton.png", Texture.class);


            backButton = new Image(backToMenu);
            Gdx.input.setInputProcessor(stage);
            stage.addActor(backButton);

            backButton.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y){
                    game.setScreen(new MainMenu(game, manager));
                    dispose();

                }

            });

            loaded=true;
        }else{
            prefs = Gdx.app.getPreferences("GymHighScores");
            absScore = prefs.getInteger("highscoreAbs");
            runnerScore = prefs.getInteger("highscoreTreadmill");
            weightScore = prefs.getInteger("highscoreWeight");
            spriteBatch.begin();
            //spriteBatch.draw(label, 100, Gdx.graphics.getHeight()/3, 326,428);
            spriteBatch.draw(weight, Gdx.graphics.getWidth()-3*Gdx.graphics.getWidth()/4-250, Gdx.graphics.getHeight()/4, 500, 500);
            spriteBatch.draw(weight, Gdx.graphics.getWidth()-2*Gdx.graphics.getWidth()/4-250, Gdx.graphics.getHeight()/4, 500, 500);
            spriteBatch.draw(weight, Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/4-250, Gdx.graphics.getHeight()/4, 500, 500);
            backButton.setPosition(Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4);
            spriteBatch.draw(backToMenu, Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4, 200, 200);
            fontScore.draw(spriteBatch, weightHighScore,  Gdx.graphics.getWidth()-3*Gdx.graphics.getWidth()/4-100, Gdx.graphics.getHeight()/4+200);
            fontScore.draw(spriteBatch, treadHighScore,  Gdx.graphics.getWidth()-2*Gdx.graphics.getWidth()/4-100, Gdx.graphics.getHeight()/4+200);
            fontScore.draw(spriteBatch, absHighScore,  Gdx.graphics.getWidth()-1*Gdx.graphics.getWidth()/4-100, Gdx.graphics.getHeight()/4+200);
            fontText.draw(spriteBatch, "WEIGHT BALANCING",  Gdx.graphics.getWidth()-3*Gdx.graphics.getWidth()/4-200, Gdx.graphics.getHeight()/4);
            fontText.draw(spriteBatch, "TREADMILL",  Gdx.graphics.getWidth()-2*Gdx.graphics.getWidth()/4-120, Gdx.graphics.getHeight()/4);
            fontText.draw(spriteBatch, "WEIGHT LIFTING",  Gdx.graphics.getWidth()-1*Gdx.graphics.getWidth()/4-150, Gdx.graphics.getHeight()/4);

            spriteBatch.end();



        }

        hud.stage.draw();

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
        manager.clear();

    }
}
